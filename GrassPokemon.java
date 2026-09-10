public class GrassPokemon extends Pokemon {
    public GrassPokemon(String name, int maxHp, Move[] moves) {
        super(name, "Grass", maxHp, moves);
    }

    @Override
    public void performSpecialAction(Pokemon target) {
        System.out.println(name + " rustles its leaves, absorbing sunlight!");
    }
}
