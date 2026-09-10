# Kanto 3v3 Pokémon Showdown

A Java console game built to demonstrate object-oriented programming principles, specifically runtime polymorphism, abstract classes, and method overriding.

## Project Structure
- `PokemonShowdown.java`: Main runner containing game loops, player turns, and team management functions.
- `Pokemon.java`: Abstract parent class defining core state, combat logic, and the abstract template contract.
- `Move.java`: Class handling individual move details (name, type, power).
- `FirePokemon.java`, `WaterPokemon.java`, `GrassPokemon.java`, `NormalPokemon.java`: Specialized subclasses implementing polymorphic behavior.

## How to Compile and Run
1. Open your terminal in the project directory.
2. Compile all Java source files:
   ```bash
   javac *.java
