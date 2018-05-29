package stepdefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Saritha.Dhanala on 25/05/2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberHtmlReport"})
public class VehicleRegistrationTest {

}
