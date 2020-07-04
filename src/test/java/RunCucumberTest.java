import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		strict = true,
		tags = {"@NovaConta"},
		glue = {"stepsdefinitions","util"},
		plugin = {"pretty","html:target/cucumber","json:target_json/cucumber.json", "stepsdefinitions.BaseClassStep"})
public class RunCucumberTest {
}
