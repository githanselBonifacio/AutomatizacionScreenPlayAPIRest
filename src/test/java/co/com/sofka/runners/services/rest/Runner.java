package co.com.sofka.runners.services.rest;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/services/rest"},
        glue = {"co.com.sofka.stepsdefinition.services.rest"}
)
public class Runner {
}
