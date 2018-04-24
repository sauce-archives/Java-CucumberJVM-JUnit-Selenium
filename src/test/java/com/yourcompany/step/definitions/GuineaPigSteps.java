package com.yourcompany.step.definitions;

import com.yourcompany.utils.SauceUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.util.UUID;
import static org.junit.Assert.*;


import com.yourcompany.Pages.*;

import static org.hamcrest.CoreMatchers.containsString;

public class GuineaPigSteps {

    public static final String USERNAME = System.getenv("SAUCE_USERNAME");
    public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static WebDriver driver;
    public static GuineaPigPage page;
    public String commentInputText;
    public String sessionId;

    @Before
    public void setUp(Scenario scenario) throws Exception {
        String platformProperty = System.getenv("PLATFORM");

		String platform = (platformProperty != null) ? platformProperty : "windows_10_edge";

        DesiredCapabilities caps = SauceUtils.createCapabilities(platform);

        caps.setCapability("name", scenario.getName());
        caps.setCapability("build", SauceUtils.getBuildName());

        driver = new RemoteWebDriver(new URL(URL), caps);

        sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
    }

    @Given("^I am on the Guinea Pig homepage$")
    public void user_is_on_guinea_pig_page() throws Exception {
        page = GuineaPigPage.visitPage(driver);
    }

    @When("^I click on the link$")
    public void user_click_on_the_link() throws Exception {
        page.followLink();
    }

    @When("^I submit a comment$")
    public void user_submit_comment() throws Exception {
        commentInputText = UUID.randomUUID().toString();
        page.submitComment(commentInputText);
    }

    @Then("^I should be on another page$")
    public void new_page_displayed() throws Exception {
        assertFalse(page.isOnPage());
    }

    @Then("^I should see that comment displayed$")
    public void comment_displayed() throws Exception {
        assertThat(page.getSubmittedCommentText(), containsString(commentInputText));
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        driver.quit();
        SauceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(), sessionId);
    }
}