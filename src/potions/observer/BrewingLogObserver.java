package potions.observer;

import potions.base.Potion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Observer that logs brewing state changes.
 * Thread-safe implementation for concurrent brewing operations.
 */
public class BrewingLogObserver implements PotionObserver {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onPotionStateChange(Potion potion) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("[%s] Brewing Log: %s", timestamp, potion));
    }
}
