package tests.online19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;

public class VyTrackLoginNegativeTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://qa2.vytrack.com/user/login");

        driver.findElement(By.id("prependedInput")).sendKeys("username");
        driver.findElement(By.id("prependedInput2")).sendKeys("password");

        driver.findElement(By.id("_submit")).click();

        String expectedErrorMsg = "Invalid user name or password.";
        WebElement actualResult = driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/div[1]/div"));
        String actualErrorMsg = actualResult.getText();

        if(actualErrorMsg.equals(expectedErrorMsg)){
            System.out.println("Test Passed");
        }else{
            System.out.println("Test Failed");
        }
        Thread.sleep(2000);
        driver.close();
    }
}

