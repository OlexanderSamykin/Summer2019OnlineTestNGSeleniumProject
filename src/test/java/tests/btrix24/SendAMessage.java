package tests.btrix24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class SendAMessage {
    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://login1.nextbasecrm.com/");
        //enter email
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("helpdesk65@cybertekschool.com");
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("UserUser");
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.className("feed-add-post-micro-title")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//*[@id='blog-submit-button-save']")).click();
        BrowserUtils.wait(2);
        WebElement noMessage = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-notice-blockblogPostForm\"]/div/span[2]"));
        String expectedResult = "The message title is not specified";
        String actualResult = noMessage.getText();
        if(expectedResult.equals(actualResult)){
            System.out.println("Whoo-Hoo: Test passed");
            System.out.println(actualResult);
        }else{
            System.out.println("Test failed");
        }
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//div[@onclick='showUserMenu()']")).click();
        BrowserUtils.wait(2);
        //driver.findElement(By.xpath("//*[@id=\"popup-window-content-menu-popup-user-menu\"]/div/div/a[3]")).click();
        BrowserUtils.wait(2);
        //driver.quit();
    }
}
