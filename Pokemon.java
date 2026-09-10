public abstract class Pokemon {
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

    public abstract void performSpecialAction(Pokemon target);

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
