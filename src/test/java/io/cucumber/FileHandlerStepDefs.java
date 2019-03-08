package io.cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.get;

public class FileHandlerStepDefs
{
    private String excelFilePath;
    private Response webResponse;

    @Given("I have an Excel File")
    public void iHaveAnExcelFile() {
        excelFilePath = "https://the-internet.herokuapp.com/download/xpath.xlsx";
    }

    @When("I download the file")
    public void iDownloadTheFile() {
        webResponse = get(excelFilePath);
    }

    @Then("The file is successfully downloaded")
    public void theFileIsSuccessfullyDownloaded() {
        webResponse.then().statusCode(200);
    }

    @Then("The file is the correct type")
    public void theFileIsTheCorrectType()
    {
        Assert.assertTrue(webResponse.getHeader("Content-Disposition").contains("xpath.xlsx"));
    }
}
