package pokemon;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private final String name;
    private final int id;
    private final PokemonType primaryType;
    private final PokemonType secondaryType;
    private final String description;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public PokemonType getPrimaryType() {
        return primaryType;
    }

    public PokemonType getSecondaryType() {
        return secondaryType;
    }

    public String getDescription() {
        return description;
    }

    private Pokemon(PokemonBuilder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.primaryType = builder.primaryType;
        this.secondaryType = builder.secondaryType;
        this.description = builder.description;
    }

    public static class PokemonBuilder {
        private final String name;
        private final int id;
        private PokemonType primaryType;
        private PokemonType secondaryType;
        private String description;

        public PokemonBuilder(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public PokemonBuilder(Pokemon pokemon) {
            this.name = pokemon.name;
            this.id = pokemon.id;
        }

        public PokemonBuilder setPrimaryType(PokemonType primaryType) {
            this.primaryType = primaryType;
            return this;
        }

        public PokemonBuilder setSecondaryType(PokemonType secondaryType) {
            this.secondaryType = secondaryType;
            return this;
        }

        public PokemonBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Pokemon build() {
            return new Pokemon(this);
        }
    }
}