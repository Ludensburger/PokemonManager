package pokemon;

import java.io.Serializable;


/**
 * Implements Serializable to be used to convert Pokemon Object
 * into a byte stream for read/write data.
 */
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

    /**
     *
     * @param builder
     *       builds Pokemon object by part before instantiating in order to account if Pokemon only has
     *       one (1) type or two (2) types. If Pokemon only has one (1) type, set secondary type to null
     *       SAMPLE INSTANTIATION
     *                 Pokemon.PokemonBuilder newPokemon = new Pokemon.PokemonBuilder(String name, int id)
     *                                             .setPrimaryType(PokemonType primaryType)
     *                                             .setDescription(String description)
     *                                             .setSecondaryType(PokemonType secondaryType) -> null if no secondary
     *
     */
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
            /*
             * TO USE: when building a new Pokemon object
             */
            this.name = name;
            this.id = id;
        }

        public PokemonBuilder(Pokemon pokemon) {
            /*
             * TO USE: when updating existing Pokemon object
             * SAMPLE INSTANTIATION (changing secondary type)
             * Pokemon pokemonToModify = pokedex.selectPokemon(toModifyId);
             * Pokemon.PokemonBuilder pokemonBuilder = new Pokemon.PokemonBuilder(pokemonToModify)
             *                                 .setDescription(pokemonToModify.getDescription())
             *                                 .setPrimaryType(pokemonToModify.getPrimaryType())
             *                                 .setSecondaryType(newSecondaryType);
             *                         pokedex.updatePokemon(toModifyId, pokemonBuilder.build());
             */
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