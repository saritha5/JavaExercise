package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Saritha.Dhanala on 28/05/2018.
 */
public class VehicleEnquiryPage extends DVLAPage {
    private WebDriver driver;

    public VehicleEnquiryPage(WebDriver driver){
        super(driver);
    }

    public void enterRegistrationNumber(String registrationNumber){
        driver.findElement(By.xpath("//input[@id='Vrm']")).sendKeys(registrationNumber);
        driver.findElement(By.xpath("//button[@name='Continue'][@type='submit']")).click();
    }

    public List<String> getVehicleDetails(){
        List<String> vehicleData = null;
        List<WebElement> vehicleDetails = driver.findElements(By.xpath("//li/span[2]"));
        for(int i =0 ;i<vehicleDetails.size(); i++){
            vehicleData.add(vehicleDetails.get(i).getText());
        }
        return vehicleData;
    }
}
