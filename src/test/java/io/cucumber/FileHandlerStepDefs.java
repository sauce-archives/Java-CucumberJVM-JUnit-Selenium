package io.cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.get;

public class FileHandlerStepDefs
{
    private String filePath;
    private Response webResponse;

    @Given("I have a File")
    public void iHaveAFile() {
        filePath = "https://the-internet.herokuapp.com/download/some-file.txt";
        TestContext.isApiTest = true;
    }

    @When("I download the file")
    public void iDownloadTheFile() {
        webResponse = get(filePath);
    }

    @Then("The file is successfully downloaded")
    public void theFileIsSuccessfullyDownloaded() {
        webResponse.then().statusCode(200);
    }

    @Then("The file is the correct type")
    public void theFileIsTheCorrectType()
    {
        Assert.assertTrue(webResponse.getHeader("Content-Disposition").contains("some-file.txt"));
    }
}
