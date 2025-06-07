import teaching.textbook.*;

public class FlyweightTest {
    public static void main(String[] args) {
        TextbookFactory publisher = new TextbookFactory();
        
        // give books to different students
        Textbook book1 = publisher.getBook("potion", "Advanced Potion-Making", "L. Borage");
        book1.assign("Ron");
        
        Textbook book2 = publisher.getBook("potion", "Advanced Potion-Making", "L. Borage");
        book2.assign("Harry");
        System.out.println("Same potion book version? " + (book1 == book2));
        
        Textbook book3 = publisher.getBook("charms", "A Book of Spells", "M. Goshawk");
        book3.assign("Draco");
        
        Textbook book4 = publisher.getBook("charms", "A Book of Spells", "M. Goshawk");
        book4.assign("Pansy");
        System.out.println("Same charms book version? " + (book3 == book4));
        
        Textbook book5 = publisher.getBook("charms", "An Advanced Book of Spells", "M. Goshawk");
        book5.assign("Hermione");
        System.out.println("Different charms book version? " + (book3 == book5));
    }
}
