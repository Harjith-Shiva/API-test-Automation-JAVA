package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        plugin = {"pretty", "html:cucumber-report/html-report.html",
                "junit:cucumber-report/junit-report.xml",
                "json:cucumber-report/json-report.json"},
        //tags = "@test",
        glue = {"stepDefinitions"}
)
public class TestRunner {

}
