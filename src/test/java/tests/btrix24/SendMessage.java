package tests.btrix24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;

public class SendMessage {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://login1.nextbasecrm.com/");

        WebElement username = driver.findElement(By.name("USER_LOGIN"));
        username.sendKeys("marketing61@cybertekschool.com");
        driver.findElement(By.name("USER_PASSWORD")).sendKeys("UserUser");
        driver.findElement(By.className("login-btn")).click();

    }

    @Test
    public void SendMessage(){

        WebElement SendMessageBox = driver.findElement(By.className("feed-add-post-micro-title"));
        SendMessageBox.click();
        WebElement MessageInputBox = driver.findElement(By.xpath("//*[@style='min-height: 184px;']"));
        MessageInputBox.click();
        //WebElement MessageTopicInputBox = driver.findElement(By.xpath("//input[@placeholder='Topic']"));
        //MessageTopicInputBox.click();
        //WebElement MessageSubModule = driver.findElement(By.className("feed-add-post-inp feed-add-post-inp-active"));
        //MessageSubModule.click();
        //WebElement MessageInputBox = driver.findElement(By.xpath("//*[contains(@style,'min-height: 13')]"));
        //MessageInputBox.sendKeys("1234567");
        //WebElement MessageTopicBox = driver.findElement(By.xpath("//input[@placeholder='Topic']"));
        //MessageTopicBox.sendKeys("Norm");


        //driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-message']//span[contains(text(),'Task')]")).click();
        //WebElement SendMessageBox1 = driver.findElement(By.xpath("//body[@contenteditable='true']"));
        //SendMessageBox1.sendKeys("Dear friends, we're in the beginning of Sprint 1. Let's start!!!");
    }

}
