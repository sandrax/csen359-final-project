package potions.base;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ingredients.Ingredient;
import equipment.Burner;
import potions.state.PotionState;
import potions.state.PreparationState;
import potions.observer.PotionObserver;
import potions.memento.PotionMemento;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RealPotion implements Potion {
    // Instance variables
    private String name;
    private final List<Ingredient> ingredients;
    private Burner.HeatLevel temperature;
    private int brewingTime;
    private String color;
    private int potency;
    private PotionState state;
    private final List<PotionObserver> observers = Collections.synchronizedList(new ArrayList<>());
    private int stirCount;

    // Thread safety
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // Constants
    private static final DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int MAX_STIR_COUNT = 10;
    private static final int MIN_BREWING_TIME = 1;
    private static final int MAX_BREWING_TIME = 120; // 2 hours max

    // Constructor
    public RealPotion() {
        this.ingredients = new ArrayList<>();
        this.state = new PreparationState();
        this.stirCount = 0;
        this.temperature = Burner.HeatLevel.OFF;
    }

    // Public methods for state-dependent operations
    public void addIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }

        lock.writeLock().lock();
        try {
            state.addIngredient(this, ingredient);
            ingredients.add(ingredient);  // Add to internal list after state validation
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void heat(Burner.HeatLevel level) {
        if (level == null) {
            throw new IllegalArgumentException("Heat level cannot be null");
        }

        lock.writeLock().lock();
        try {
            if (level != temperature) {  // Only change if different
                state.heat(this, level);
                temperature = level;
                notifyObservers();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void stir(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Stirring duration must be positive");
        }

        lock.writeLock().lock();
        try {
            // Let the state validate the operation
            state.stir(this, duration);

            // If validation passes, increment stir count
            stirCount++;
            System.out.println("Stirring count: " + stirCount + "/" + MAX_STIR_COUNT);
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Package-private method for state transitions to stir directly
    private void stirInternal(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Stirring duration must be positive");
        }

        if (stirCount >= MAX_STIR_COUNT) {
            throw new IllegalStateException("Maximum stir count reached (" + MAX_STIR_COUNT + ")");
        }

        stirCount++;
        System.out.println("Stirring count: " + stirCount + "/" + MAX_STIR_COUNT);
        notifyObservers();
    }

    // Public method for state-managed stirring
    public void performStateStir(int duration) {
        stirInternal(duration);
    }

    // Public getters with read locks
    public String getName() {
        lock.readLock().lock();
        try {
            return name;
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Ingredient> getIngredients() {
        lock.readLock().lock();
        try {
            return Collections.unmodifiableList(new ArrayList<>(ingredients));
        } finally {
            lock.readLock().unlock();
        }
    }

    public Burner.HeatLevel getTemperature() {
        lock.readLock().lock();
        try {
            return temperature;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getBrewingTime() {
        lock.readLock().lock();
        try {
            return brewingTime;
        } finally {
            lock.readLock().unlock();
        }
    }

    public String getColor() {
        lock.readLock().lock();
        try {
            return color;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getPotency() {
        lock.readLock().lock();
        try {
            return potency;
        } finally {
            lock.readLock().unlock();
        }
    }

    public PotionState getState() {
        lock.readLock().lock();
        try {
            return state;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getStirCount() {
        lock.readLock().lock();
        try {
            return stirCount;
        } finally {
            lock.readLock().unlock();
        }
    }

    // Setters for Builder pattern
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        lock.writeLock().lock();
        try {
            this.name = name.trim();
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setTemperature(Burner.HeatLevel level) {
        if (level == null) {
            throw new IllegalArgumentException("Temperature level cannot be null");
        }

        lock.writeLock().lock();
        try {
            this.temperature = level;
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setBrewingTime(int brewingTime) {
        if (brewingTime < MIN_BREWING_TIME || brewingTime > MAX_BREWING_TIME) {
            throw new IllegalArgumentException(
                String.format("Brewing time must be between %d and %d minutes",
                    MIN_BREWING_TIME, MAX_BREWING_TIME));
        }

        lock.writeLock().lock();
        try {
            this.brewingTime = brewingTime;
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }

        lock.writeLock().lock();
        try {
            this.color = color.trim();
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setPotency(int potency) {
        if (potency < 0) {
            throw new IllegalArgumentException("Potency cannot be negative");
        }

        lock.writeLock().lock();
        try {
            this.potency = potency;
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setState(PotionState newState) {
        if (newState == null) {
            throw new IllegalArgumentException("New state cannot be null");
        }

        lock.writeLock().lock();
        try {
            // Validate state transition
            if (state != null && !newState.canTransitionFrom(state)) {
                throw new IllegalStateException(
                    String.format("Cannot transition from %s to %s",
                        state.getStateName(), newState.getStateName()));
            }

            this.state = newState;
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Observer pattern methods
    public void addObserver(PotionObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        synchronized (observers) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }
    }

    public void removeObserver(PotionObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    public void clearObservers() {
        synchronized (observers) {
            observers.clear();
        }
    }

    public int getObserverCount() {
        synchronized (observers) {
            return observers.size();
        }
    }

    public void notifyObservers() {
        List<PotionObserver> observersCopy;
        synchronized (observers) {
            observersCopy = new ArrayList<>(observers);
        }

        for (PotionObserver observer : observersCopy) {
            try {
                observer.onPotionStateChange();
            } catch (Exception e) {
                System.err.println("Error notifying observer: " + e.getMessage());
                // Continue notifying other observers even if one fails
            }
        }
    }

    // Memento pattern methods
    public PotionMemento save() {
        lock.readLock().lock();
        try {
            return new PotionMemento(
                name,
                new ArrayList<>(ingredients),
                temperature,
                brewingTime,
                color,
                potency,
                state,
                stirCount,
                LocalDateTime.now().format(formatter)
            );
        } finally {
            lock.readLock().unlock();
        }
    }

    public void restore(PotionMemento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("Memento cannot be null");
        }

        lock.writeLock().lock();
        try {
            this.name = memento.getName();
            this.ingredients.clear();
            this.ingredients.addAll(memento.getIngredients());
            this.temperature = memento.getTemperature();
            this.brewingTime = memento.getBrewingTime();
            this.color = memento.getColor();
            this.potency = memento.getPotency();
            this.state = memento.getState();
            this.stirCount = memento.getStirCount();
            System.out.println("Restoring potion...");
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String toString() {
        lock.readLock().lock();
        try {
            return String.format("%s Potion (State: %s, Color: %s, Potency: %d, Stirs: %d/%d)",
                name != null ? name : "Unnamed",
                state != null ? state.getStateName() : "No State",
                color,
                potency,
                stirCount,
                MAX_STIR_COUNT);
        } finally {
            lock.readLock().unlock();
        }
    }

    // Protected method for direct temperature update from states
    private void updateTemperature(Burner.HeatLevel level) {
        if (level == null) {
            throw new IllegalArgumentException("Temperature level cannot be null");
        }
        lock.writeLock().lock();
        try {
            this.temperature = level;
            notifyObservers();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Public method for state-managed temperature updates
    public void setStateTemperature(Burner.HeatLevel level) {
        updateTemperature(level);
    }
}
