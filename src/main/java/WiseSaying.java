import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class WiseSaying {
    @Getter
    @Setter
    String content;
    @Getter
    @Setter
    String author;
    @Getter
    @Setter
    int id;

    public WiseSaying() {

    }

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public void run() {
        new Controller().run();
    }
}
