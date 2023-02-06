package TestRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features", glue= "StepDefinition",
dryRun=true,
plugin = {"pretty", "html:target/cucumber-reports"},
monochrome=true)
public class PetstoreApi {

}
