package co.com.sofka.runners.services.rest.pokemon;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/services/rest/pokeapi"},
        glue = {"co.com.sofka.stepsdefinition.services.rest.pokemon"}
)
public class PokemonTest {

}
