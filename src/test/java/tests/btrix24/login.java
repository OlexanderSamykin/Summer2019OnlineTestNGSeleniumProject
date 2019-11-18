package tests.btrix24;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.StringUtility;
import utils.BrowserFactory;

public class login {
    //As a user, i should able to login
    /*
    1. login successfully with valid credential
            1.login page --> enter credential --> click login bbt --> verify login
    2. user cannot login with Invalid credential
            1.login page --> enter invalid credential --> click login bt --> verify error mes
    3. user cannot login without any credentials
            1. login page --> click login bbt --> error message
     */
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://login1.nextbasecrm.com/");
    }

    @Test(priority=1)
    public void LoginSuccessfully() {
        WebElement username = driver.findElement(By.name("USER_LOGIN"));
        username.sendKeys("marketing61@cybertekschool.com");
        driver.findElement(By.name("USER_PASSWORD")).sendKeys("UserUser");
        driver.findElement(By.className("login-btn")).click();
        String expectedTitle = "Portal";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(driver.getTitle(), "Portal");
    }

    @Test(priority=2)
    public void InvalidLogin() {
        WebElement username = driver.findElement(By.name("USER_LOGIN"));
        Faker dommyData = new Faker();
        String name = dommyData.name().firstName();
        username.sendKeys(name);
        driver.findElement(By.name("USER_PASSWORD")).sendKeys(dommyData.animal().name());
        driver.findElement(By.className("login-btn")).click();
        //verify the error message
        String expectedMessage = "Incorrect login or password";
        WebElement errorMessage = driver.findElement(By.className("errortext"));
        String actualMessage = errorMessage.getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
     }

}