package potions.memento;

import java.util.ArrayDeque;
import java.util.Deque;
import potions.base.Potion;
import potions.base.NullPotion;

/**
 * Caretaker class that manages the history of potion states.
 * Uses a double-ended queue for efficient history management.
 */
public class PotionCaretaker {
    private final Deque<PotionMemento> history;
    private static final int MAX_HISTORY = 10;
    private static final int MIN_HISTORY = 1;

    public PotionCaretaker() {
        this.history = new ArrayDeque<>(MAX_HISTORY);
    }

    /**
     * Saves the current state of the potion.
     *
     * @param potion The potion whose state to save
     * @throws IllegalArgumentException if potion is null or invalid
     */
    public void saveState(Potion potion) {
        if (potion == null || potion instanceof NullPotion) {
            throw new IllegalArgumentException("Cannot save state of null or invalid potion");
        }

        // Remove oldest state if we exceed max size
        if (history.size() >= MAX_HISTORY) {
            history.removeLast();
        }

        // Save new state
        PotionMemento memento = potion.save();
        if (memento != null) {
            history.addFirst(memento);
            System.out.println("Saved potion state: " + memento);
        } else {
            System.out.println("Failed to save potion state");
        }
    }

    /**
     * Restores the potion to its previous state.
     *
     * @param potion The potion to restore
     * @throws IllegalArgumentException if potion is null or invalid
     */
    public void undo(Potion potion) {
        if (potion == null || potion instanceof NullPotion) {
            throw new IllegalArgumentException("Cannot restore state to null or invalid potion");
        }

        if (history.isEmpty()) {
            System.out.println("No previous states to restore!");
            return;
        }

        // Get and remove the most recent state
        PotionMemento memento = history.removeFirst();
        if (memento != null) {
            potion.restore(memento);
            System.out.println("Restored potion to previous state: " + memento);
        } else {
            System.out.println("Failed to restore potion state");
        }
    }

    /**
     * Shows the history of potion states.
     * Lists states from most recent to oldest.
     */
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No history available.");
            return;
        }

        System.out.println("\nPotion History (Most Recent First):");
        int count = 1;
        for (PotionMemento memento : history) {
            System.out.println(count + ". " + memento);
            count++;
        }
        System.out.println();
    }

    /**
     * Gets the number of states in history.
     * @return The current size of the history
     */
    public int getHistorySize() {
        return history.size();
    }

    /**
     * Checks if there are any states in history.
     * @return true if history is empty, false otherwise
     */
    public boolean isHistoryEmpty() {
        return history.isEmpty();
    }

    /**
     * Clears all saved states from history.
     */
    public void clearHistory() {
        history.clear();
        System.out.println("History cleared.");
    }
}
