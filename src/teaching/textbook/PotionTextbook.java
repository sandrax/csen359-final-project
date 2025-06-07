package teaching.textbook;

public class PotionTextbook implements Textbook {
    private String title;
    private String author;

    public PotionTextbook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public void assign(String name) {
        System.out.println("A copy of the potions book, " + this.title + ", has been claimed by " + name);
    }
}
