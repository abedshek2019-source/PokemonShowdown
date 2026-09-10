public class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int maxHp, Move[] moves) {
        super(name, "Water", maxHp, moves);
    }

    @Override
    public void performSpecialAction(Pokemon target) {
        System.out.println(name + " splashes water around, creating a humid mist!");
    }
}
