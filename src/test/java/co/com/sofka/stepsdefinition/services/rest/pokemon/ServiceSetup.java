package co.com.sofka.stepsdefinition.services.rest.pokemon;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.HashMap;

public class ServiceSetup {
    protected static final String URL_BASE = "https://pokeapi.co/api/v2/";
    protected static final String RESOURCE_POKEMON = "pokemon/";
    protected static final String RESOURCE_ABILITY = "ability/";

    protected final Actor actor = new Actor("Maestro Pokemón");

    protected void setup(){
        actorCan();
    }

    private void actorCan(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected HashMap<String, Object> headers(){
        HashMap<String, Object> headersCollection = new HashMap<>();
        headersCollection.put("Content-Type", "application/json");
        return headersCollection;
    }
}
