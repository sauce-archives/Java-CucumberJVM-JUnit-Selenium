import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
    features = {"classpath:features/CommentText.feature"},
    format = {"html:target/cucumber-parallel/1.html", "junit:target/cucumber-parallel/1.junit", "pretty"},
    monochrome = true,
    tags = {"~@ignore"},
    glue = { "com.yourcompany.step.definitions" })
public class Parallel01IT {
}