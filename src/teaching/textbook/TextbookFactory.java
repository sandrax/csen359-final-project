package teaching.textbook;

import java.util.HashMap;
import java.util.Map;

public class TextbookFactory {
    private Map<String, Textbook> bookStorage = new HashMap<>();

    public Textbook getBook(String type, String title, String author) {
        if (bookStorage.containsKey(title)) {
            return bookStorage.get(title);
        }
        else {
            Textbook book = switch (type) {
                case "potion" -> new PotionTextbook(title, author);
                case "charms" -> new CharmsTextbook(title, author);
                default -> throw new IllegalArgumentException("No " + type + " books can be ordered at the moment.");
                };
            bookStorage.put(title, book);
            return book;
        }
    }
}
