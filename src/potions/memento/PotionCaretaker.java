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
     * @param memento The potion whose state to save
     * @throws IllegalArgumentException if potion is null or invalid
     */
    public void saveState(PotionMemento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("No memento provided to save.");
        }

        // Remove oldest state if we exceed max size
        if (history.size() >= MAX_HISTORY) {
            history.removeLast();
        }

        System.out.println("Saving potion state:\n" + memento);
        history.addFirst(memento);
    }

    /**
     * Restores the potion to its previous state.
     *
     * @throws IllegalArgumentException if potion is null or invalid
     */
    public PotionMemento goBack() {
        if (history.isEmpty()) {
            System.out.println("No previous states to restore!");
            return null;
        }
        
        return history.removeFirst();
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
