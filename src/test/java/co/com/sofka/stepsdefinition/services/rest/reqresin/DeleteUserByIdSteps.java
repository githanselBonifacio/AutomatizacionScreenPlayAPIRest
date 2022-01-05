package co.com.sofka.stepsdefinition.services.rest.reqresin;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.tasks.DoDelete.doDelete;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteUserByIdSteps extends ServiceSetup{
    private static final Logger logger = LogManager.getLogger(DeleteUserByIdSteps.class);

    @When("el administrador del sistema ingrese {int} como id para eliminar el registro del usuario")
    public void elAdministradorDelSistemaIngreseComoIdParaEliminarElRegistroDelUsuario(int id){
        try {
            super.setup();
            actor.attemptsTo(
                    doDelete()
                            .withTheResource(USER+"/"+id)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest("")
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            logger.warn("error petición\n" + e.getMessage());
        }
    }
    @Then("el sistema debe borrar el registro satisfactoriamente")
    public void elSistemaDebeBorrarElRegistroSatisfactoriamente(){
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                resp.statusCode(HttpStatus.SC_NO_CONTENT);
                                logger.info("código de respuesta: "+resp.extract().statusCode());
                            }
                    ));
        } catch (AssertionError e) {
            Assertions.fail(e.getMessage());
            logger.warn("Error en la validación\n" + e);
        }
    }
}
