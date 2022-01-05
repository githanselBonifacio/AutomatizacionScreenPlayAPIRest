package co.com.sofka.stepsdefinition.services.rest.pokemon;

import co.com.sofka.util.service.rest.pokemon.PatchPokemonFiles;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static co.com.sofka.tasks.DoGet.doGet;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class PokemonAbiliySteps extends ServiceSetup {
    private static final Logger logger = LogManager.getLogger(PokemonAbiliySteps.class);

    @When("el maestro pokemón ejecute la petición con la habilidad {string}  para buscar pokemones")
    public void elMaestroPokemonEjecuteLaPeticionConLaHabilidadParaBuscarPokemones(String ability) {
        try {
            super.setup();
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_ABILITY + ability)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest("")
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            logger.warn("error petición\n" + e.getMessage());
        }
    }
    @Then("el maestro pokemón debería obtener los pokemones con la habilidad {string}")
    public void elMaestroPokemonDeberiaObtenerLosPokemonesConLaHabilidad(String ability) {
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                File fileJson = new File(PatchPokemonFiles.ABILITY_DROUGHT.getPathAbility(ability));
                                JsonPath resExpected =JsonPath.given(fileJson) ;
                                JsonPath jsonPath = resp.extract().body().jsonPath();

                                resp.statusCode(HttpStatus.SC_OK);
                                resp.body("pokemon",equalTo(resExpected.getJsonObject("pokemon")));

                                logger.info("código de respuesta: "+resp.extract().statusCode());
                            }
                    ));
        } catch (AssertionError e) {
            Assertions.fail(e.getMessage());
            logger.warn("Error en la validación\n" + e);
        }
    }

    @Then("el maestro pokemón no obtendrá coincidencia en la busqueda de esa habilidad")
    public void elMaestroPokemonNoObtendraCoincidenciaEnLaBusquedaDeEsaHabilidad() {
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                resp.statusCode(HttpStatus.SC_NOT_FOUND);
                                logger.info("código de respuesta: "+resp.extract().statusCode());
                                logger.info("respuesta: "+resp.extract().body().asString());
                            }
                    ));
        } catch (AssertionError e) {
            Assertions.fail(e.getMessage());
            logger.warn("Error en la validación\n" + e);
        }
    }
}
