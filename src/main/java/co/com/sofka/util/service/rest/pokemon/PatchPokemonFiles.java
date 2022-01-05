package co.com.sofka.util.service.rest.pokemon;


public enum PatchPokemonFiles {

    ABILITY_DROUGHT(System.getProperty("user.dir")
            + "\\src\\test\\resources\\fileS\\services\\rest\\pokeapi\\response\\abilityDrought.Json"),
    ABILITY_SADOW_TAG(System.getProperty("user.dir")
            + "\\src\\test\\resources\\fileS\\services\\rest\\pokeapi\\response\\abilitySadowTag.Json");

    private final String value;

    PatchPokemonFiles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getPathAbility(String ability){
        switch (ability){
            case "shadow-tag":
                return ABILITY_SADOW_TAG.getValue();

            case "drought":
                return ABILITY_DROUGHT.getValue();

                default:return "";
        }
    }
}
