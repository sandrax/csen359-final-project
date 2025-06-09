package potions.observer;

import potions.base.Potion;

/**
 * Observer interface for monitoring potion state changes.
 */
public interface PotionObserver {
    /**
     * Called when a potion's state changes.
     * Implementations should handle null potion gracefully.
     *
     * @param potion The potion that changed state
     * @throws IllegalArgumentException if the potion is null
     */
    void onPotionStateChange();
}
