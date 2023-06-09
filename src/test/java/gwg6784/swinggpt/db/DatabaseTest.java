// Written by James P. (21154854)

package gwg6784.swinggpt.db;

import gwg6784.swinggpt.services.conversation.models.Conversation;
import gwg6784.swinggpt.services.conversation.models.ConversationEntry;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class DatabaseTest {
    private Database db;
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.db = Database.connect();
    }
    
    @After
    public void tearDown() {
        try {
              this.db.deleteAllConversations();      
        } catch (SQLException e) {
            
        }

        this.db = null;
    }

    /**
     * Test whether database can connect successfully
     */
    @Test
    public void testConnect() {
        assertNotNull(db);
    }

    /**
     * Test of getConversations method, of class Database.
     */
    @Test
    public void testGetConversations() throws Exception {
        UUID conversationId1 = db.createConversation("Conversation 1", new Date());
        UUID conversationId2 = db.createConversation("Conversation 2", new Date());
        
        List<Conversation> result = db.getConversations();
        
        assertNotNull(result);
        assertEquals(result.size(), 2);
        
        db.deleteConversation(conversationId1);
        db.deleteConversation(conversationId2);
    }

    /**
     * Test of getConversationHistory method, of class Database.
     */
    @Test
    public void testGetConversationHistory() throws Exception {
        UUID conversationId1 = db.createConversation("Conversation 1", new Date());
        UUID entryId1 = db.createEntry(conversationId1, "hello", "there", new Date());

        
        List<ConversationEntry> result = db.getConversationHistory(conversationId1);
        
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).prompt, "hello");
        
        db.deleteConversation(conversationId1);
    }

    /**
     * Test of createConversation method, of class Database.
     */
    @Test
    public void testCreateConversation() throws Exception {
        UUID conversationId1 = db.createConversation("Conversation 1", new Date());
        assertNotNull(conversationId1);
        // Means was successfully created, we test
        // getting in the above tests
        
        db.deleteConversation(conversationId1);
    }

    /**
     * Test of createEntry method, of class Database.
     */
    @Test
    public void testCreateEntry() throws Exception {
        UUID conversationId1 = db.createConversation("Conversation 1", new Date());
        UUID entryId1 = db.createEntry(conversationId1, "hello", "there", new Date());
        

        assertNotNull(conversationId1);
        assertNotNull(entryId1);
        // Means was successfully created, we test
        // getting in the above tests
        
        db.deleteConversation(conversationId1);
    }

    /**
     * Test of deleteConversation method, of class Database.
     */
    @Test
    public void testDeleteConversation() throws Exception {
        UUID conversationId1 = db.createConversation("Conversation 1", new Date());
        db.deleteConversation(conversationId1);

        List<Conversation> result = db.getConversations();
        assertEquals(result.size(), 0);
        
    }

    /**
     * Test the deleteAllConversations method of class Database.
     */
    @Test
    public void testDeleteAllConversations() throws Exception {
        UUID conversationId1 = db.createConversation("Conversation 1", new Date());
        UUID conversationId2 = db.createConversation("Conversation 2", new Date());
        UUID conversationId3 = db.createConversation("Conversation 3", new Date());
        db.deleteAllConversations();

        List<Conversation> result = db.getConversations();
        assertEquals(result.size(), 0);
    }
    
}
