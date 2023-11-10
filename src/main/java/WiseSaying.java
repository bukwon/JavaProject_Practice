public class WiseSaying {
    String content;
    String author;

    public WiseSaying() {

    }

    public WiseSaying(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public void run() {
        new Controller().run();
    }
}
