package co.com.sofka.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.Map;

public class DoDelete implements Task {
    private String resource;
    private Map<String, Object> headers;
    private String bodyRequest;

    public DoDelete withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoDelete andTheHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public DoDelete andTheBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
               Delete.from (resource)
                     .with(
                             requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .headers(headers)
                                        .body(bodyRequest)
                     )
        );
    }
    public static DoDelete doDelete(){
        return new DoDelete();
    }
}
