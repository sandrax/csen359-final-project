# Potions at Hogwarts – CSEN 359 Project

## Authors
- Guru Sushma Kasa  
- Divya Shetty  
- Sandra Xu

## Overview
This project simulates a magical potions class at Hogwarts, where you play as a student learning to brew potions using a variety of magical tools and ingredients. The simulation includes step-by-step potion creation, safety monitoring, grading, and more, all built with object-oriented principles.

## Project Structure
- `src/` – Source code organized by design pattern
- `test/` – Test cases for specific patterns and components
- `Main.java` – Contains a simulation workflow that demonstrates the design patterns in action

## Design Patterns Used

### Creational
- **Singleton**: Used for the wand and grading database to ensure only one instance exists throughout the system. This allows consistent access and avoids duplication of key objects.
- **Builder**: Constructs potions step by step, allowing the configuration of different ingredients and procedures for each potion in a controlled manner.
- **Prototype**: Enables efficient duplication of ingredients like aconite or moonstone without recreating them from scratch.

### Structural
- **Decorator**: Dynamically adds preparation details (like diced or powdered) to ingredients without changing their core structure.
- **Bridge**: Separates liquid ingredient types from their preparation processes, allowing each to evolve independently.
- **Composite**: Allows potion instructions to be represented as a mix of simple and complex steps, making them easy to manage and nest.
- **Flyweight**: Shares textbook data like title and author across students, while storing per-student data like ownership separately to save memory.
- **Proxy**: Controls access to the grading portal—students can only view grades, while professors can edit them.
- **Facade**: Provides a simplified interface (via Professor) to set up equipment and guide potion lessons, hiding internal complexities.

### Behavioral
- **Command**: Represent equipment actions as command objects. The wand sends executes commands like turning on the burner or adding ingredients.
- **State**: Potions transition through states like preparation, brewing, and completion, with behavior that changes based on the current state.
- **Memento**: Stores snapshots of potion states so students can revert to earlier versions if an experiment goes wrong.
- **Observer**: Monitors potion changes; the BrewingLog logs actions and the SafetyMonitor alerts on unsafe conditions.
- **Template Method**: Defines a preset process for professors to demonstrate potion making, ensuring consistent execution of shared steps.
- **Iterator**: Allows uniform traversal through student records to calculate grades, regardless of collection type.
- **Chain of Responsibility**: Handles student disciplinary reports by passing them up the staff hierarchy until someone can address them.
- **Null**: Returns a NullPotion when no valid potion is available, avoiding null checks and runtime errors.
- **Visitor**: Run diagnostics on equipment to evaluate magical stability as an additional operation that does not modify the equipment themselves.
