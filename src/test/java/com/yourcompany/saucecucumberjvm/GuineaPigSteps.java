package com.yourcompany.saucecucumberjvm;

import java.io.*;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.net.URL;

public class GuineaPigSteps {
	
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	public static WebDriver driver;
	public String sessionId;
	public boolean testResults;
	public String jobId;
	
	public void UpdateResults() throws JSONException, ClientProtocolException, IOException {
		HttpClient httpclient = HttpClientBuilder.create().build();
		String apiUrl = "https://" + USERNAME + ":" + ACCESS_KEY + "@saucelabs.com/rest/v1/"+ USERNAME +"/jobs/" + jobId;
		System.out.println(apiUrl);
        HttpPut putRequest = new HttpPut(apiUrl);
        
        putRequest.addHeader("content-type", "application/json");
        putRequest.addHeader("Accept", "application/json");
        
        JSONObject keyArg = new JSONObject();
        keyArg.put("passed", testResults);
        
    	StringEntity params = new StringEntity(keyArg.toString());
    	putRequest.setEntity(params);
        
        HttpResponse response = httpclient.execute(putRequest);
        String bodyAsString = EntityUtils.toString(response.getEntity());
    	System.out.println("Response to put request is : " + bodyAsString);
	}
	
	public class SimpleOnFailed extends TestWatcher {
	    @Override
	    protected void failed(Throwable e, Description description) {
	        System.out.println("Only outed when a test fails");
	    };
	}
	
    @Rule
    public SimpleOnFailed ruleExample = new SimpleOnFailed();
    
	@Before
	public void setUp() throws Throwable {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", "Windows 8.1");
        caps.setCapability("version", "40.0");
        caps.setCapability("name", "Java Cucumber Test");

	    driver = new RemoteWebDriver(new URL(URL), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        jobId = (((RemoteWebDriver) driver).getSessionId()).toString();
        testResults = false;
	}
	
	@Given("^I am on the Sauce Labs Guinea Pig test page$")
	public void user_is_on_guinea_pig_page() throws Throwable {
        driver.get("https://saucelabs.com/test/guinea-pig");
	}
 
	@When("^I click on the link$")
	public void user_click_on_the_link() throws Throwable {
		driver.findElement(By.linkText("i am a link")).click();
	}
 
	@Then("^I should see a new page$")
	public void new_page_displayed() throws Throwable {
		String page_title = driver.getTitle();
		if (Objects.equals(page_title, "I am another page title - Sauce Labs")) {
			testResults = true;
		}
		assertEquals("I am another page title - Sauce Labs", page_title);
	}
	
	@After
	public void tearDown() throws Throwable {
		driver.quit();
		UpdateResults();
	}
}