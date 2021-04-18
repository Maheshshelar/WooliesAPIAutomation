package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/wooliesx/features/api", "src/test/java/com/wooliesx/features/web"},
        glue = {"com.wooliesx.framework.steps"},
        plugin = {"html:target/site/cucumber-pretty", "json:target/cucumber.json"}
)
public class TestRunner {
}
