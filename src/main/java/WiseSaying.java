public class WiseSaying {
    String content;
    String author;
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
