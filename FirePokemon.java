public class FirePokemon extends Pokemon {
    public FirePokemon(String name, int maxHp, Move[] moves) {
        super(name, "Fire", maxHp, moves);
    }

    @Override
    public void performSpecialAction(Pokemon target) {
        System.out.println(name + " flares up with intense heat, warming the battlefield!");
    }
}
