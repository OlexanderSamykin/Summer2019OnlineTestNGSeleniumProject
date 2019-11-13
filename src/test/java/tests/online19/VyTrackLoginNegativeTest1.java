package tests.online19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;

public class VyTrackLoginNegativeTest1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        Thread.sleep(4000);

        driver.findElement(By.id("prependedInput")).sendKeys("username");
        driver.findElement(By.id("prependedInput2")).sendKeys("password");

        driver.findElement(By.id("_submit")).click();

        WebElement InvalidLoginCredentials = driver.findElement(By.xpath("//div[contains(@class,'alert alert-error')]"));
        String ExpectedFailedLoginNotificationText="Invalid user name or password.";
        String ActualFailedLoginNotificationText=InvalidLoginCredentials.getText();
        System.out.println(ActualFailedLoginNotificationText);
        if (ExpectedFailedLoginNotificationText.equals(ActualFailedLoginNotificationText)){
            System.out.println("THE TEST PASSED");
        }
        else{
            System.out.println("THE TEST FAILED");
        }
        Thread.sleep(2000);
        driver.close();
    }
}


