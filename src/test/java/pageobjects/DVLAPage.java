package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Saritha.Dhanala on 28/05/2018.
 */
public class DVLAPage {
    private WebDriver driver;

   public DVLAPage(WebDriver driver){
       this.driver = driver;
   }

   public void clickStart(){
       driver.findElement(By.xpath("//*[@id='get-started']/a")).click();
   }

}
