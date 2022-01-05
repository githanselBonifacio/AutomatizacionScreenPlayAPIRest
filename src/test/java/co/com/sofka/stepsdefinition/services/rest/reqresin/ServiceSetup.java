package co.com.sofka.stepsdefinition.services.rest.reqresin;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.HashMap;

public class ServiceSetup {
    protected static final String URL_BASE = "https://reqres.in/";
    protected static final String LIST_RESOURCES = "api/unknown";
    protected static final String USER = "api/users";

    protected final Actor actor = new Actor("Usuario");

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
