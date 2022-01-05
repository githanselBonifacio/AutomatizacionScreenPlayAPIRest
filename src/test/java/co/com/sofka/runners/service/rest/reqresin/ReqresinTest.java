package co.com.sofka.runners.service.rest.reqresin;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/services/rest/reqresin"},
        glue = {"co.com.sofka.stepsdefinition.services.rest.reqresin"}
)
public class ReqresinTest {

}
