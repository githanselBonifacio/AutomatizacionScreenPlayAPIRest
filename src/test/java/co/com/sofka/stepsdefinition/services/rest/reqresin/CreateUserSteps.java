package co.com.sofka.stepsdefinition.services.rest.reqresin;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.tasks.DoPost.doPost;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CreateUserSteps extends ServiceSetup{
    private static final Logger logger = LogManager.getLogger(CreateUserSteps.class);

    @When("el administrador del sistema envíe de forma incorrecta el cuerpo de la petición")
    public void elAdministradorDelSistemaEnvieDeFormaIncorrectaElCuerpoDeLaPeticion() {
        try {
            super.setup();
            actor.attemptsTo(
                    doPost()
                            .withTheResource(CREATE_USER)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest("{\"name\":juan,\"job\":leader}")
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            logger.warn("error petición\n" + e.getMessage());
        }
    }
    @Then("se deberá presentar un error en la petición")
    public void seDeberaPresentarUnErrorEnLaPeticion() {
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                resp.statusCode(HttpStatus.SC_BAD_REQUEST);
                                logger.info("código de respuesta: "+resp.extract().statusCode());
                            }
                    ));
        } catch (AssertionError e) {
            logger.warn("Error en la validación\n" + e);
            Assertions.fail(e.getMessage());
        }
    }
}
