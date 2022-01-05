package co.com.sofka.stepsdefinition.services.rest.pokemon;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static org.hamcrest.CoreMatchers.equalTo;
import static co.com.sofka.tasks.DoGet.doGet;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class PokemonNameSteps extends ServiceSetup {
    private static final Logger logger = LogManager.getLogger(PokemonNameSteps.class);

    @When("el maestro pokemón ejecute la petición con el nombre {string}  para buscar un pokemón")
    public void elMaestroPokemonEjecuteLaPeticionConElNombreParaBuscarUnPokemon(String pokemonName) {
        try {
            super.setup();
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_POKEMON + pokemonName)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest("")
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            logger.warn("error petición\n" + e.getMessage());
        }
    }

    @Then("el maestro pokemón debería obtener {string} en la especie e información adicional")
    public void elMaestroPokemonDeberiaObtenerEnLaEspecieEInformacionAdicional(String pokemonName) {
        try {
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            resp -> {
                                    resp.statusCode(HttpStatus.SC_OK);
                                    resp.body("name",equalTo(pokemonName));
                                    JsonPath jsonPath = resp.extract().body().jsonPath();
                                    logger.info("código de respuesta: "+resp.extract().statusCode());
                                    logger.info("respuesta esperada : "+pokemonName);
                                    logger.info("Respuesta obtenida <nombre del pokemom> <"+ jsonPath.getJsonObject("name")+">");
                                    }
                        ));
        } catch (AssertionError e) {
            Assertions.fail(e.getMessage());
            logger.warn("Error en la validación\n" + e);
        }
    }

    @Then("el maestro pokemón no obtendrá coincidencia en la busqueda")
    public void elMaestroPokemonNoObtendraCoincidenciasEnLaBusqueda() {
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
