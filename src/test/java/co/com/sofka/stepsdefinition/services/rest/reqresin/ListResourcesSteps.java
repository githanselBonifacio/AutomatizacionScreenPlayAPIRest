package co.com.sofka.stepsdefinition.services.rest.reqresin;

import co.com.sofka.util.service.rest.reqresin.PatchReqresFiles;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static co.com.sofka.tasks.DoGet.doGet;
import static co.com.sofka.tasks.DoPost.doPost;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ListResourcesSteps extends ServiceSetup {
    private static final Logger logger = LogManager.getLogger(ListResourcesSteps.class);

    @When("el usuario ejecute la petición para obtener la lista completa de recursos")
    public void elUsuarioEjecuteLaPeticionParaObtenerLaListaCompletaDeRecursos() {
        try {
            super.setup();
            actor.attemptsTo(
                    doGet()
                            .withTheResource(LIST_RESOURCES)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest("")
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            logger.warn("error petición\n" + e.getMessage());
        }
    }

    @Then("el usuario deberá obtener la lista completa de recursos")
    public void elUsuarioDeberaObtenerLaListaCompletaDeRecursos() {
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                File fileJson = new File(PatchReqresFiles.LIST_RESOURCES.getValue());
                                JsonPath resExpected =JsonPath.given(fileJson) ;
                                JsonPath jsonPath = resp.extract().body().jsonPath();

                                resp.statusCode(HttpStatus.SC_OK);
                                resp.body("",equalTo(resExpected.getJsonObject("")));

                                logger.info("código de respuesta: "+resp.extract().statusCode());
                            }
                    ));
        } catch (AssertionError e) {
            Assertions.fail(e.getMessage());
            logger.warn("Error en la validación\n" + e);
        }
    }

    @When("el usuario ejecute la petición con {string} como criterio de busqueda")
    public void elUsuarioEjecuteLaPeticionConComoCriterioDeBusqueda(String recursoBuscado) {
        try {
            super.setup();
            actor.attemptsTo(
                    doGet()
                            .withTheResource(recursoBuscado)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest("")
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            logger.warn("error petición\n" + e.getMessage());
        }
    }
    @Then("el usuario deberá obtener un errror")
    public void elUsuarioDeberaObtenerUnErrror() {
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                resp.statusCode(HttpStatus.SC_NOT_FOUND);
                                logger.info("código de respuesta: "+resp.extract().statusCode());
                            }
                    ));
        } catch (AssertionError e) {
            Assertions.fail(e.getMessage());
            logger.warn("Error en la validación\n" + e);
        }
    }

}