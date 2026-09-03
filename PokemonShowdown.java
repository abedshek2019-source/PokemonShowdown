import java.util.Random;
import java.util.Scanner;

public class PokemonShowdown {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pokemon[] team1 = { getRandomPokemon(), getRandomPokemon(), getRandomPokemon() };
        Pokemon[] team2 = { getRandomPokemon(), getRandomPokemon(), getRandomPokemon() };

        System.out.println("=== KANTO 3v3 POKEMON SHOWDOWN ===");
        System.out.println("Player 1 and Player 2, get ready!\n");

        while (hasTeamConscious(team1) && hasTeamConscious(team2)) {
            
            Pokemon p1 = getActivePokemon(team1);
            Pokemon p2 = getActivePokemon(team2);

            if (p1 == null || !p1.isConscious()) {
                team1 = switchActive(team1, scanner, "Player 1");
                if (team1 == null) break;
                p1 = getActivePokemon(team1);
            }
            if (p2 == null || !p2.isConscious()) {
                team2 = switchActive(team2, scanner, "Player 2");
                if (team2 == null) break;
                p2 = getActivePokemon(team2);
            }

            p1 = getActivePokemon(team1);
            p2 = getActivePokemon(team2);

            System.out.println("\n--- PLAYER 1's TURN ---");
            System.out.println("Your Active: " + p1.getName() + " (HP: " + p1.getHp() + "/" + p1.getMaxHp() + ")");
            System.out.println("Opponent Active: " + p2.getName() + " (HP: " + p2.getHp() + "/" + p2.getMaxHp() + ")");
            
            p1.showMoves();
            System.out.println("5) Switch Pokémon");
            System.out.print("Choose action (1-5): ");
            int choice1 = scanner.nextInt();
            scanner.nextLine();

            if (choice1 >= 1 && choice1 <= 4) {
                p1.useMove(choice1 - 1, p2);
            } else if (choice1 == 5) {
                team1 = manualSwitch(team1, scanner, "Player 1");
                p1 = getActivePokemon(team1);
                System.out.println("Switched active Pokémon!");
            }

            if (!hasTeamConscious(team2)) break;

            System.out.println("\nGive the device to player 2. Press Enter when ready.");
            scanner.nextLine();

            p2 = getActivePokemon(team2);
            p1 = getActivePokemon(team1);

            System.out.println("\n--- PLAYER 2's TURN ---");
            System.out.println("Your Active: " + p2.getName() + " (HP: " + p2.getHp() + "/" + p2.getMaxHp() + ")");
            System.out.println("Opponent Active: " + p1.getName() + " (HP: " + p1.getHp() + "/" + p1.getMaxHp() + ")");
            
            p2.showMoves();
            System.out.println("5) Switch Pokémon");
            System.out.print("Choose action (1-5): ");
            int choice2 = scanner.nextInt();
            scanner.nextLine();

            if (choice2 >= 1 && choice2 <= 4) {
                p2.useMove(choice2 - 1, p1);
            } else if (choice2 == 5) {
                team2 = manualSwitch(team2, scanner, "Player 2");
                p2 = getActivePokemon(team2);
                System.out.println("Switched active Pokémon!");
            }

            if (!hasTeamConscious(team1)) break;

            System.out.println("\nGive the device to player 1. Press Enter when ready.");
            scanner.nextLine();
        }

        System.out.println("\n=== GAME OVER ===");
        if (hasTeamConscious(team1)) {
            System.out.println("PLAYER 1 WINS!");
        } else {
            System.out.println("PLAYER 2 WINS!");
        }
        scanner.close();
    }

    public static boolean hasTeamConscious(Pokemon[] team) {
        for (Pokemon p : team) {
            if (p.isConscious()) return true;
        }
        return false;
    }

    public static Pokemon getActivePokemon(Pokemon[] team) {
        for (Pokemon p : team) {
            if (p.isConscious()) return p;
        }
        return null;
    }

    public static Pokemon[] manualSwitch(Pokemon[] team, Scanner scanner, String playerName) {
        System.out.println("\nChoose a Pokémon to switch to:");
        for (int i = 0; i < team.length; i++) {
            String status = team[i].isConscious() ? "HP: " + team[i].getHp() : "Fainted";
            System.out.println((i + 1) + ") " + team[i].getName() + " [" + status + "]");
        }
        System.out.print("Enter team slot (1-3): ");
        int slot = scanner.nextInt();
        scanner.nextLine();
        if (slot >= 1 && slot <= 3 && team[slot - 1].isConscious()) {
            Pokemon temp = team[0];
            team[0] = team[slot - 1];
            team[slot - 1] = temp;
        } else {
            System.out.println("Invalid choice, staying with current Pokémon.");
        }
        return team;
    }

    public static Pokemon[] switchActive(Pokemon[] team, Scanner scanner, String playerName) {
        System.out.println("\n" + playerName + "'s active Pokémon fainted! Choose next Pokémon:");
        for (int i = 0; i < team.length; i++) {
            if (team[i].isConscious()) {
                System.out.println((i + 1) + ") " + team[i].getName());
            }
        }
        System.out.print("Enter choice: ");
        int slot = scanner.nextInt();
        scanner.nextLine();
        if (slot >= 1 && slot <= 3 && team[slot - 1].isConscious()) {
            Pokemon temp = team[0];
            team[0] = team[slot - 1];
            team[slot - 1] = temp;
        }
        return team;
    }

    public static Pokemon getRandomPokemon() {
        Random rand = new Random();
        int dexNumber = rand.nextInt(12) + 1;
        switch (dexNumber) {
            case 1: return make("Grass", "Bulbasaur", 90, "Tackle", "Normal", 10, "Razor Leaf", "Grass", 20, "Leech Seed", "Grass", 15, "Solar Beam", "Grass", 40);
            case 2: return make("Grass", "Venusaur", 160, "Tackle", "Normal", 10, "Petal Dance", "Grass", 30, "Body Slam", "Normal", 25, "Solar Beam", "Grass", 40);
            case 3: return make("Grass", "Oddish", 95, "Absorb", "Grass", 15, "Acid", "Normal", 20, "Mega Drain", "Grass", 25, "Petal Dance", "Grass", 30);
            case 4: return make("Fire", "Charmander", 85, "Scratch", "Normal", 10, "Ember", "Fire", 15, "Slash", "Normal", 20, "Flamethrower", "Fire", 35);
            case 5: return make("Fire", "Charizard", 156, "Slash", "Normal", 20, "Wing Attack", "Normal", 25, "Flamethrower", "Fire", 35, "Fire Blast", "Fire", 45);
            case 6: return make("Fire", "Vulpix", 80, "Tackle", "Normal", 10, "Ember", "Fire", 15, "Quick Attack", "Normal", 15, "Flamethrower", "Fire", 35);
            case 7: return make("Water", "Squirtle", 88, "Tackle", "Normal", 10, "Bubble", "Water", 15, "Bite", "Normal", 20, "Water Gun", "Water", 25);
            case 8: return make("Water", "Blastoise", 158, "Bite", "Normal", 20, "Water Gun", "Water", 25, "Surf", "Water", 35, "Hydro Pump", "Water", 45);
            case 9: return make("Water", "Psyduck", 100, "Scratch", "Normal", 10, "Confusion", "Normal", 20, "Water Gun", "Water", 25, "Surf", "Water", 35);
            case 10: return make("Normal", "Pidgey", 80, "Tackle", "Normal", 10, "Gust", "Normal", 15, "Quick Attack", "Normal", 15, "Wing Attack", "Normal", 20);
            case 11: return make("Normal", "Rattata", 70, "Tackle", "Normal", 10, "Quick Attack", "Normal", 15, "Bite", "Normal", 20, "Hyper Fang", "Normal", 30);
            default: return make("Normal", "Snorlax", 200, "Tackle", "Normal", 10, "Headbutt", "Normal", 25, "Body Slam", "Normal", 35, "Hyper Beam", "Normal", 50);
        }
    }

    private static Pokemon make(String type, String name, int hp, 
                                String m1, String t1, int p1, 
                                String m2, String t2, int p2, 
                                String m3, String t3, int p3, 
                                String m4, String t4, int p4) {
        Move[] moves = { new Move(m1, t1, p1), new Move(m2, t2, p2), new Move(m3, t3, p3), new Move(m4, t4, p4) };
        if (type.equals("Fire")) return new FirePokemon(name, hp, moves);
        if (type.equals("Water")) return new WaterPokemon(name, hp, moves);
        if (type.equals("Grass")) return new GrassPokemon(name, hp, moves);
        return new NormalPokemon(name, hp, moves); 
    }
}

class Move {
    String name;
    String type;
    int power;

    public Move(String name, String type, int power) {
        this.name = name;
        this.type = type;
        this.power = power;
    }
}

abstract class Pokemon {
    protected String name;
    protected String type;
    protected int hp;
    protected int maxHp;
    protected Move[] moves;

    public Pokemon(String name, String type, int maxHp, Move[] moves) {
        this.name = name;
        this.type = type;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.moves = moves;
    }

    public void useMove(int moveIndex, Pokemon target) {
        Move m = moves[moveIndex];
        System.out.println(name + " used " + m.name + "!");

        double multiplier = 1.0;
        
        if (m.type.equals("Fire") && target.type.equals("Grass")) multiplier = 2.0;
        else if (m.type.equals("Fire") && target.type.equals("Water")) multiplier = 0.5;
        else if (m.type.equals("Water") && target.type.equals("Fire")) multiplier = 2.0;
        else if (m.type.equals("Water") && target.type.equals("Grass")) multiplier = 0.5;
        else if (m.type.equals("Grass") && target.type.equals("Water")) multiplier = 2.0;
        else if (m.type.equals("Grass") && target.type.equals("Fire")) multiplier = 0.5;

        if (multiplier == 2.0) System.out.println("It's super effective!");
        else if (multiplier == 0.5) System.out.println("It's not very effective...");

        int finalDamage = (int) (m.power * multiplier);
        target.takeDamage(finalDamage);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
        System.out.println(name + " took " + damage + " damage!");
        if (hp == 0) System.out.println(name + " fainted!");
    }

    public void showMoves() {
        for (int i = 0; i < moves.length; i++) {
            System.out.println((i + 1) + ") " + moves[i].name + " [" + moves[i].type + "] (Power: " + moves[i].power + ")");
        }
    }

    public boolean isConscious() { return this.hp > 0; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
}

class FirePokemon extends Pokemon {
    public FirePokemon(String name, int maxHp, Move[] moves) {
        super(name, "Fire", maxHp, moves);
    }
}

class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int maxHp, Move[] moves) {
        super(name, "Water", maxHp, moves);
    }
}

class GrassPokemon extends Pokemon {
    public GrassPokemon(String name, int maxHp, Move[] moves) {
        super(name, "Grass", maxHp, moves);
    }
}

class NormalPokemon extends Pokemon {
    public NormalPokemon(String name, int maxHp, Move[] moves) {
        super(name, "Normal", maxHp, moves);
    }
}
