package co.com.sofka.stepsdefinition.services.rest.reqresin;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.tasks.DoPut.doPut;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UpdateUserByIDSteps extends ServiceSetup{
    private static final Logger logger = LogManager.getLogger(UpdateUserByIDSteps.class);

    @When("el administrador del sistema ingrese {string} y {string} como nuevas credenciales al usuario de id {int}")
    public void elAdministradorDelSistemaIngreseYComoNuevasCredencialesAlUsuarioDeId(String name, String job, Integer id) {
        try {
            super.setup();
            actor.attemptsTo(
                    doPut()
                            .withTheResource(USER)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest("{\"name\":\""+name+"\", \"job\":\""+job+"\"}")
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            logger.warn("error petición\n" + e.getMessage());
        }
    }

    @Then("se deberá mostrar el nombre como {string} y {string} como trabajo")
    public void seDeberaMostrarElNombreComoYComoTrabajo(String name, String job) {
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                resp.statusCode(HttpStatus.SC_OK);
                                resp.body("name",equalTo(name));
                                resp.body("job",equalTo(job));
                                resp.body("updatedAt",notNullValue());

                                logger.info("código de respuesta: "+resp.extract().statusCode());
                            }
                    ));
        } catch (AssertionError e) {
            logger.warn("Error en la validación\n" + e);
            Assertions.fail(e.getMessage());
        }
    }

}
