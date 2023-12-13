package pokemon;

import java.util.Scanner;
/**
 *  All Pokemon types in the Pokemon franchise as of November 29, 2023.
 *  There are currently 18 types.
 */
public enum PokemonType {
    NORMAL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    ICE,
    FIGHTING,
    POISON,
    GROUND,
    FLYING,
    PSYCHIC,
    BUG,
    ROCK,
    GHOST,
    DARK,
    DRAGON,
    STEEL,
    FAIRY;


    /**
     * To be removed once GUI has "Add Pokemon" implemented.
     * @return
     */
    public static PokemonType pickType() {
        Scanner scanner = new Scanner(System.in);
        int type_no = 0;

        PokemonType[] types = PokemonType.values();

        for (PokemonType type : types) {
            System.out.println(type_no++ + ". " + type);
        }

        System.out.print("Enter type of choice (#0-17): ");
        int choice = scanner.nextInt();


        return types[choice];
    }
}
