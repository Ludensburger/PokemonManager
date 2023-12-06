package pokemon.ui.PokedexGUI;

public class IndexManager {
    private static IndexManager instance;
    private static Integer panelIndex = 0;
    private static Integer pokemonIndex = 1;
    private static Integer startIndex = 1;

    private IndexManager() {}

    public static IndexManager getInstance() {
        if (instance == null) {
            instance = new IndexManager();
        }
        return instance;
    }

    public Integer getPanelIndex() {
        return panelIndex;
    }

    public void setPanelIndex(Integer index) {
        IndexManager.panelIndex = index;
    }

    public void incrementPanel() {
        panelIndex++;
    }

    public void decrementPanel() {
        panelIndex--;
    }

    public Integer getPokemonIndex() {
        return pokemonIndex;
    }

    public void setPokemonIndex(Integer pokemonIndex) {
        IndexManager.pokemonIndex = pokemonIndex;
    }

    public void incrementPokemon() {
        pokemonIndex++;
    }

    public void decrementPokemon(){
        pokemonIndex--;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        IndexManager.startIndex = startIndex;
    }

    public void incrementStart() {
        startIndex++;
    }

    public void decrementStart() {
        startIndex--;
    }
}

