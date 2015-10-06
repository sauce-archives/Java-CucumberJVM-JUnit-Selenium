package com.yourcompany.saucecucumberjvm;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import java.net.URL;



public class GuineaPigSteps {
	
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	public static WebDriver driver;
	
	@Given("^I am on the Sauce Labs Guinea Pig test page$")
	public void user_is_on_guinea_pig_page() throws Throwable {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", "Windows 8.1");
        caps.setCapability("version", "40.0");
        caps.setCapability("name", "Java Cucumber Test");
        
	    driver = new RemoteWebDriver(new URL(URL), caps);
	    
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://saucelabs.com/test/guinea-pig");
	}
 
	@When("^I click on the link$")
	public void user_click_on_the_link() throws Throwable {
		driver.findElement(By.linkText("i am a link")).click();
	}
 
	@Then("^I should see a new page$")
	public void new_page_displayed() throws Throwable {
		String page_title = driver.getTitle();
		assertEquals("I am another page title - Sauce Labs", page_title);
		driver.quit();
	}
}