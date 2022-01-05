package co.com.sofka.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.Map;

public class DoGet implements Task {
    private String resource;
    private Map<String, Object> headers;
    private String bodyRequest;

    public DoGet withTheResource (String resource){
        this.resource=resource;
        return this;
    }
    public DoGet andTheHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }
    public DoGet andTheBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(resource)
                        .with(requestSpecification->requestSpecification.relaxedHTTPSValidation()
                                .headers(headers)
                                .body(bodyRequest))
        );
    }

    public static DoGet doGet(){return new DoGet();}
}
