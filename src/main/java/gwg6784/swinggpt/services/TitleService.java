package gwg6784.swinggpt.services;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class TitleService {
    private String title;
    private final Set<Consumer<String>> titleObservers = new HashSet<>();

    public TitleService(String title) {
        this.title = title;
    }

    public void addObserver(Consumer<String> observer) {
        this.titleObservers.add(observer);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        for (Consumer<String> con : this.titleObservers) {
            con.accept(title);
        }
    }
}
