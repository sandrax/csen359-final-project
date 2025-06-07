package teaching.textbook;

public class CharmsTextbook implements Textbook {
    private String title;
    private String author;

    public CharmsTextbook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public void assign(String name) {
        System.out.println("A copy of the charms book, " + this.title + ", has been claimed by " + name);
    }
}
