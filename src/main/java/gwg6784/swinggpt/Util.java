package gwg6784.swinggpt;

import java.nio.ByteBuffer;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class Util {
    private Util() {
    }

    public static Border emptyBorder(int size) {
        return BorderFactory.createEmptyBorder(size, size, size, size);
    }

    public static Border emptyBorder(int hor, int ver) {
        return BorderFactory.createEmptyBorder(ver, hor, ver, hor);
    }

    public static UUID uuidFromBytes(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

    public static byte[] uuidToBytes(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }
}
