package pokemon;

/**
 *  pokemon.Pokemon class containing the following characteristics:
 *           name           (Pokemon's name)
 *           id             (Pokemon's identification number)
 *           type           (Pokemon's type, i.e 'FIRE' if single-type
 *                          and 'FIRE/FIGHTING' if dual-type)
 *           description    (Pokemon's description, to be input by user)
 *
 *
 */

public class Pokemon {
    private final String name;
    private int id;
    private final String type;
    private final String description;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    private Pokemon(PokemonBuilder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.type = builder.type;
        this.description = builder.description;
    }

    public static class PokemonBuilder{
        private final String name;
        private final int id;
        private final String type;
        private final String description;

        public PokemonBuilder(String name, int id, String description, String type) {
            this.name = name;
            this.id = id;
            this.description = description;
            this.type = type;
        }

        public Pokemon build() {
            return new Pokemon(this);
        }
    }
}