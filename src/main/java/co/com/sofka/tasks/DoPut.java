package co.com.sofka.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.Map;

public class DoPut implements Task {
    private String resource;
    private Map<String, Object> headers;
    private String bodyRequest;

    public DoPut withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPut andTheHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public DoPut andTheBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .headers(headers)
                                        .body(bodyRequest)
                        )
        );
    }
    public static DoPut doPut(){
        return new DoPut();
    }
}
