package io.cucumber;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;

public class StepDefinitions {

    WebDriver driver;
    String sessionId;
    WebDriverWait wait;

    final String username = System.getenv("SAUCE_USERNAME");
    final String accesskey = System.getenv("SAUCE_ACCESS_KEY");

    final String BASE_URL = "https://www.saucedemo.com";
    final String SAUCE_REMOTE_URL = "https://ondemand.saucelabs.com/wd/hub";

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        DesiredCapabilities caps = DesiredCapabilities.firefox();

        caps.setCapability("version", "60.0");
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("username", username);
        caps.setCapability("accessKey", accesskey);
        caps.setCapability("name", scenario.getName());

        driver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), caps);
        sessionId = ((RemoteWebDriver)driver).getSessionId().toString();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown(Scenario scenario){
        driver.quit();
        SauceUtils.UpdateResults(username, accesskey, !scenario.isFailed(), sessionId);

    }

    @Given("^I go to the login page$")
    public void go_to_login_page() {
        driver.get(BASE_URL);
    }

    @Given("I am on the inventory page")
    public void go_to_the_inventory_page(){
        driver.get(BASE_URL + "/inventory.html");
    }

    @When("I login as a valid user")
    public void login_as_valid_user() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.className("login-button")).click();
    }

    @When("I login as an invalid user")
    public void login_as_invalid_user() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys("doesnt_exist");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.className("login-button")).click();
    }

    @When("^I add (\\d+) items? to the cart$")
    public void add_items_to_cart(int items){
        By itemButton = By.className("add-to-cart-button");

        for (int i = 0; i < items; i++){
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(itemButton)));
            driver.findElement(itemButton).click();
        }
    }

    @And("I remove an item")
    public void remove_an_item(){
        By itemButton = By.className("remove-from-cart-button");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(itemButton)));
        driver.findElement(itemButton).click();
    }

    @Then("I have (\\d) items? in my cart")
    public void one_item_in_cart(Integer items){
        String expected_items = items.toString();

        By itemsInCart = By.className("shopping_cart_badge");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(itemsInCart)));
        Assert.assertEquals(driver.findElement(itemsInCart).getText(), expected_items);
    }

    @Then("The item list is not displayed")
    public void item_list_is_not_diplayed() {
        Assert.assertEquals(driver.findElements(By.id("inventory_container")).size(), 0);
    }

    @Then("The item list is displayed")
    public void item_list_is_diplayed() {
        Assert.assertTrue(driver.findElement(By.id("inventory_container")).isDisplayed());
    }
}
