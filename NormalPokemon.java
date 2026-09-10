public class NormalPokemon extends Pokemon {
    public NormalPokemon(String name, int maxHp, Move[] moves) {
        super(name, "Normal", maxHp, moves);
    }

    @Override
    public void performSpecialAction(Pokemon target) {
        System.out.println(name + " stands firm, bracing for the upcoming clash!");
    }
}
