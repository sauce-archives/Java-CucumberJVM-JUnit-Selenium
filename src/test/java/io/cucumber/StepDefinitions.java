package io.cucumber;

import com.saucelabs.simplesauce.Browser;
import com.saucelabs.simplesauce.SauceOptions;
import com.saucelabs.simplesauce.SaucePlatform;
import com.saucelabs.simplesauce.SauceSession;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.IntStream;

public class StepDefinitions {
    private SauceSession session;
    private WebDriverWait wait;
    protected WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        SauceOptions options = new SauceOptions();
        options.setName(scenario.getName());

        if (System.getenv("START_TIME") != null) {
            options.setBuild("Build Time: " + System.getenv("START_TIME"));
        }

        String platform;
        if (System.getProperty("platform") != null) {
            platform = System.getProperty("platform");
        } else {
            platform = "default";
        }

        switch(platform) {
            case "windows_10_edge":
                options.setPlatformName(SaucePlatform.WINDOWS_10);
                options.setBrowserName(Browser.EDGE);
                break;
            case "mac_sierra_chrome":
                options.setPlatformName(SaucePlatform.MAC_SIERRA);
                options.setBrowserName(Browser.CHROME);
                break;
            case "windows_8_ff":
                options.setPlatformName(SaucePlatform.WINDOWS_8);
                options.setBrowserName(Browser.FIREFOX);
                break;
            case "windows_8_1_ie":
                options.setPlatformName(SaucePlatform.WINDOWS_8_1);
                options.setBrowserName(Browser.INTERNET_EXPLORER);
                break;
            case "mac_mojave_safari":
                options.setPlatformName(SaucePlatform.MAC_MOJAVE);
                options.setBrowserName(Browser.SAFARI);
                break;
            default:
                // accept Sauce defaults
                break;
        }

        session = new SauceSession(options);
        driver = session.start();

        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown(Scenario scenario){
        session.stop(!scenario.isFailed());
    }

    @Given("^I go to the login page$")
    public void go_to_login_page() {
        driver.get("https://www.saucedemo.com");
    }

    @Given("I am on the inventory page")
    public void go_to_the_inventory_page(){
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @When("I login as a valid user")
    public void login_as_valid_user() {
        login("standard_user", "secret_sauce");
    }

    @When("I login as an invalid user")
    public void login_as_invalid_user() {
        login("doesnt_exist", "secret_sauce");
    }

    /**
     * Use this method to send any number of login/password parameters, to test different edge cases or roles within
     * the software. This method exists to show an example of how steps can call other parameterized methods.
     * @param username The user name to login with
     * @param password The password to use (for testing the password field
     */
    private void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        driver.findElement(By.id("user-name")).sendKeys(username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.className("btn_action")).click();
    }

    @When("^I add (\\d+) items? to the cart$")
    public void add_items_to_cart(int items){
        By itemButton = By.className("btn_primary");

        IntStream.range(0, items).forEach(i -> {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(itemButton)));
            driver.findElement(itemButton).click();
        });
    }

    @And("I remove an item")
    public void remove_an_item(){
        By itemButton = By.className("btn_secondary");

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
