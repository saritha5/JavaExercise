package stepdefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import pageobjects.DVLAPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Saritha.Dhanala on 25/05/2018.
 */
public class VehicleRegistrationSteps {

    private WebDriver driver;

    @Before
    public void setup(){
        System.out.println(System.getProperty("user.dir")+"\\geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");

    }

    @Given("I am on DVLA page")
    public void openDVLAPAge(){
        driver.navigate().to("https://www.gov.uk/get-vehicle-information-from-dvla");
    }

    @When("I go to vehicle enquiry service page")
    public void openVehicleEnquiryPage(){
        DVLAPage page = new DVLAPage(driver);
        page.clickStart();

    }

    @Then("^I should see the vehicle details displayed should match with the data in below files:$")
    public void verfiyDatadisplayed(DataTable fileNames){
   //Get the file names from DataTable
        List<List<String>> data = fileNames.raw();

   }
}
