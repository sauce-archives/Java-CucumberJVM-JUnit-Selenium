package com.yourcompany.saucecucumberjvm;
 
import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

 
@RunWith(Cucumber.class)
@Cucumber.Options(tags = {"@guineapig2"},format={"pretty", "html:target/cucumber"})
public class RunTests2AT {
}
