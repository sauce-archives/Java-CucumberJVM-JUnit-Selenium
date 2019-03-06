package io.cucumber;

import com.saucelabs.saucerest.SauceREST;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.IntStream;

public class StepDefinitions {
    private WebDriver driver;
    private String sessionId;
    private WebDriverWait wait;

    private String username;
    private String accesskey;

    private final String BASE_URL = "https://www.saucedemo.com";
    private SauceUtils sauceUtils;

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        //Set up the ChromeOptions object, which will store the capabilities for the Sauce run
        ChromeOptions caps = new ChromeOptions();
        caps.setCapability("version", "72.0");
        caps.setCapability("platform", "Windows 10");
        caps.setExperimentalOption("w3c", true);

        //Create a map of capabilities called "sauce:options", which contain the info necessary to run on Sauce
        // Labs, using the credentials stored in the environment variables. Also runs using the new W3C standard.
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accesskey);
        sauceOptions.setCapability("seleniumVersion", "3.141.59");
        sauceOptions.setCapability("name", scenario.getName());

        //Assign the Sauce Options to the base capabilities
        caps.setCapability("sauce:options", sauceOptions);

        //Create a new RemoteWebDriver, which will initialize the test execution on Sauce Labs servers
        String SAUCE_REMOTE_URL = "https://ondemand.saucelabs.com/wd/hub";
        driver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), caps);
        sessionId = ((RemoteWebDriver)driver).getSessionId().toString();
        wait = new WebDriverWait(driver, 10);

        username = System.getenv("SAUCE_USERNAME");
        accesskey = System.getenv("SAUCE_ACCESS_KEY");
        if (username == null || username.isEmpty() || accesskey == null || accesskey.isEmpty()) {
            throw new RuntimeException("Error with Sauce Labs credentials: either SAUCE_USERNAME or SAUCE_ACCESS_KEY environment variable was not set");
        }

        SauceREST sauceREST = new SauceREST(username, accesskey);
        sauceUtils = new SauceUtils(sauceREST);
    }

    @After
    public void tearDown(Scenario scenario){
        driver.quit();
        sauceUtils.updateResults(!scenario.isFailed(), sessionId);
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

        driver.findElement(By.className("login-button")).click();
    }

    @When("^I add (\\d+) items? to the cart$")
    public void add_items_to_cart(int items){
        By itemButton = By.className("add-to-cart-button");

        IntStream.range(0, items).forEach(i -> {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(itemButton)));
            driver.findElement(itemButton).click();
        });
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
