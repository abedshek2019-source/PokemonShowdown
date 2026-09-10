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
   java PokemonShowdown
# Reflection

**A. METHOD**
I overrode the `performSpecialAction(Pokemon target)` method. It belongs in the parent contract because every member of the Pokémon family shares the capability to execute a unique battlefield action, establishing a uniform interface across all subtypes.

**B. DIFFERENCE**
Subclasses implement this method with distinct, specialized behaviors. For example, `FirePokemon` prints a message about flaring up with intense heat, `WaterPokemon` creates a humid mist, and `GrassPokemon` rustles its leaves to absorb sunlight.

**C. COLLECTION**
The declared type of the collection is `Pokemon[]` (and `Pokemon` for active references), and it actually contains instantiated objects from subclasses like `FirePokemon`, `WaterPokemon`, `GrassPokemon`, and `NormalPokemon`.

**D. DISPATCH**
Java uses dynamic method dispatch at runtime. When `p1.performSpecialAction(p2)` is invoked through the parent-typed reference, the Java Virtual Machine checks the actual object type created with `new` in memory and automatically executes that specific subclass's overridden method version.

**E. REVISION**
I updated the codebase to include an abstract method in the parent class and overrode it across all subclasses, moving away from centralized conditional checks so the program properly uses true runtime polymorphism.
