package stepdefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		strict = true,
		tags = {"@buying"},
		plugin = {"pretty","html:target/cucumber","json:target_json/cucumber.json",})
public class RunCucumberTest {
}
