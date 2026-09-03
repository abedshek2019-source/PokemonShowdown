import java.util.Random;
import java.util.Scanner;

public class PokemonShowdown {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   Kanto Region 3v3 Pokémon Showdown!    ");
        System.out.println("=========================================\n");

        // STEP 4: Generate 3 random specialized Pokemon for each player
        Pokemon[] team1 = { getRandomKantoPokemon(), getRandomKantoPokemon(), getRandomKantoPokemon() };
        Pokemon[] team2 = { getRandomKantoPokemon(), getRandomKantoPokemon(), getRandomKantoPokemon() };

        System.out.println("Player 1's Team:");
        for (Pokemon p : team1) System.out.println("- " + p.getName());
        
        System.out.println("\nPlayer 2's Team:");
        for (Pokemon p : team2) System.out.println("- " + p.getName());

        int p1Active = 0;
        int p2Active = 0;

        System.out.println("\n--- BATTLE START ---");
        System.out.println("Player 1 sends out " + team1[p1Active].getName() + "!");
        System.out.println("Player 2 sends out " + team2[p2Active].getName() + "!\n");

        // BATTLE LOOP: Continues as long as both players have at least one conscious Pokémon
        while (p1Active < 3 && p2Active < 3) {
            Pokemon p1 = team1[p1Active];
            Pokemon p2 = team2[p2Active];

            // PLAYER 1 TURN
            System.out.println("-----------------------------------------");
            p1.displayStats();
            p2.displayStats();
            System.out.println("-----------------------------------------");
            
            System.out.println("What will Player 1's " + p1.getName() + " do?");
            p1.showMoves();
            System.out.print("Choose move (1-4) or 5 for Unique Ability: ");
            int choice1 = scanner.nextInt();
            
            System.out.println("\n*** PLAYER 1 ATTACKS ***");
            if (choice1 >= 1 && choice1 <= 4) {
                p1.useMove(choice1 - 1, p2); // Inherited standard attack
            } else {
                p1.useUniqueAbility(); // Specialized behavior!
            }

            // Check if Player 2's Pokémon fainted
            if (!p2.isConscious()) {
                p2Active++;
                if (p2Active < 3) {
                    System.out.println("\nPlayer 2 sends out " + team2[p2Active].getName() + "!\n");
                }
                continue; // Skip Player 2's turn if they just died
            }

            // PLAYER 2 TURN (Simplified to random selection for the opponent to speed up gameplay)
            System.out.println("\n*** PLAYER 2 ATTACKS ***");
            int choice2 = new Random().nextInt(5); // 0-3 for moves, 4 for ability
            if (choice2 < 4) {
                p2.useMove(choice2, p1);
            } else {
                p2.useUniqueAbility();
            }

            // Check if Player 1's Pokémon fainted
            if (!p1.isConscious()) {
                p1Active++;
                if (p1Active < 3) {
                    System.out.println("\nPlayer 1 sends out " + team1[p1Active].getName() + "!\n");
                }
            }
        }

        // WINNER CHECK
        System.out.println("\n=========================================");
        if (p1Active < 3) {
            System.out.println("Player 2 is out of Pokémon! PLAYER 1 WINS!");
        } else {
            System.out.println("Player 1 is out of Pokémon! PLAYER 2 WINS!");
        }
        System.out.println("=========================================");
        scanner.close();
    }

    // HELPER: Generates a random specialized Kanto Pokémon
    public static Pokemon getRandomKantoPokemon() {
        Random rand = new Random();
        int dex = rand.nextInt(6); // 0 to 5

        // Creating 4 Moves for each Pokémon
        Move tackle = new Move("Tackle", 10);
        Move slam = new Move("Slam", 15);
        Move elementalWeak = new Move("Elemental Burst", 18);
        Move elementalStrong = new Move("Hyper Beam", 25);
        Move[] standardMoves = { tackle, slam, elementalWeak, elementalStrong };

        // Returns a specialized subclass based on the random number
        switch (dex) {
            case 0: return new FirePokemon("Charizard", 100, standardMoves);
            case 1: return new FirePokemon("Arcanine", 110, standardMoves);
            case 2: return new WaterPokemon("Blastoise", 120, standardMoves);
            case 3: return new WaterPokemon("Gyarados", 115, standardMoves);
            case 4: return new GrassPokemon("Venusaur", 125, standardMoves);
            default: return new GrassPokemon("Exeggutor", 130, standardMoves);
        }
        // To add all 151, you just expand this switch statement!
    }
}

// Data class to hold attack info
class Move {
    String name;
    int power;

    public Move(String name, int power) {
        this.name = name;
        this.power = power;
    }
}

// STEP 1: General Parent Class
class Pokemon {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected Move[] moves; // Array of 4 attacks

    public Pokemon(String name, int maxHp, Move[] moves) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.moves = moves;
    }

    // Shared behavior inherited by ALL Pokémon
    public void useMove(int moveIndex, Pokemon target) {
        Move m = moves[moveIndex];
        System.out.println(name + " used " + m.name + "!");
        target.takeDamage(m.power);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
        System.out.println(name + " took " + damage + " damage!");
        if (hp == 0) System.out.println(name + " fainted!");
    }

    public void showMoves() {
        for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + ") " + moves[i].name + " (Power: " + moves[i].power + ")");
        }
    }

    // To be overridden by subclasses
    public void useUniqueAbility() {
        System.out.println(name + " glared menacingly!");
    }

    public boolean isConscious() { return this.hp > 0; }
    public String getName() { return name; }
    public void displayStats() { System.out.println(name + " HP: " + hp + "/" + maxHp); }
}

// STEP 2 & 3: Specialized Subclass 1
class FirePokemon extends Pokemon {
    public FirePokemon(String name, int maxHp, Move[] moves) {
        super(name, maxHp, moves);
    }

    // Specialized behavior unique to Fire type
    @Override
    public void useUniqueAbility() {
        System.out.println(name + " activates BLAZE! (Its body erupts in flames, healing 10 HP)");
        this.hp += 10;
        if (this.hp > this.maxHp) this.hp = maxHp;
    }
}

// STEP 2 & 3: Specialized Subclass 2
class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int maxHp, Move[] moves) {
        super(name, maxHp, moves);
    }

    // Specialized behavior unique to Water type
    @Override
    public void useUniqueAbility() {
        System.out.println(name + " activates TORRENT! (A massive wave crashes down!)");
        System.out.println("It does 15 recoil damage to itself, but looks incredibly cool!");
        this.hp -= 15; // Unique quirk
    }
}

// STEP 2 & 3: Specialized Subclass 3
class GrassPokemon extends Pokemon {
    public GrassPokemon(String name, int maxHp, Move[] moves) {
        super(name, maxHp, moves);
    }

    // Specialized behavior unique to Grass type
    @Override
    public void useUniqueAbility() {
        System.out.println(name + " uses SYNTHESIS! (Absorbs sunlight to heal 20 HP)");
        this.hp += 20;
        if (this.hp > this.maxHp) this.hp = maxHp;
    }
}

/*
 === STEP 5: DESIGN EXPLANATION ===
 1. What was defined once in Pokemon:
    - Shared attributes: 'name', 'hp', 'maxHp', and the 'Move[] moves' array (handling 4 attacks).
    - Shared behaviors: 'useMove()', 'takeDamage()', 'showMoves()', and 'isConscious()'.

 2. Inheritance Relationship Line:
    - 'class FirePokemon extends Pokemon', 'class WaterPokemon extends Pokemon', etc.
    - Meaning: The 'extends' keyword establishes that Fire, Water, and Grass Pokémon 
      are specialized types of Pokemon ("IS-A" relationship). They inherit the 4-move array 
      and HP tracking automatically.

 3. Why this design is better:
    - We didn't have to write the 4-move attack logic or HP tracking 3 different times. 
    - The subclasses only needed to define their ONE unique trait ('useUniqueAbility()'), 
      making adding the remaining 145 Kanto Pokémon incredibly easy!
*/
