package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    plugin = {"pretty", 
              "html:target/cucumber-report.html", 
              "json:target/cucumber-report.json",
              "junit:target/cucumber-report.xml"
    }  
)

public class TestRunnerTestNg extends AbstractTestNGCucumberTests{

}
