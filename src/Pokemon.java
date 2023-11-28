/**
 *  Pokemon class containing the following characteristics:
 *           name           (Pokemon's name)
 *           id             (Pokemon's identification number)
 *           type           (Pokemon's type, i.e 'FIRE' if single-type
 *                          and 'FIRE/FIGHTING' if dual-type)
 *           description    (Pokemon's description, to be input by user)
 *           isDualType     (Boolean to identify if Pokemon is dual-type
 *                           or single-type)
 *
 */

class Pokemon {
    private final String name;
    private int id;
    private final String type;
    private final String description;
    private boolean isDualType = false;

    public Pokemon(String name, int id, String description, String type, Boolean isDualType) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.type = type;
        this.isDualType = isDualType;
    }

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

    public boolean isDualType() {
        return isDualType;
    }

}