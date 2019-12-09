package tests.homework2;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;
import utils.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Homework2 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        //explicit wait
        wait = new WebDriverWait(driver, 10);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //maximize browser
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
    }

    @Test(description = "Registration")
    public void test5() {
        WebElement RegistrationFormButton = driver.findElement(By.partialLinkText("Registration Form"));
        RegistrationFormButton.click();

        WebElement FirstNameInputBox = driver.findElement(By.xpath("//input[@name='firstname']"));
        FirstNameInputBox.sendKeys("Alex");

        WebElement LastNameInputBox = driver.findElement(By.xpath("//input[@name='lastname']"));
        LastNameInputBox.sendKeys("Sami");

        WebElement UserNameInputBox = driver.findElement(By.xpath("//input[@name='username']"));
        UserNameInputBox.sendKeys("AlexSami");

        WebElement EmailInputBox = driver.findElement(By.xpath("//input[@name='email']"));
        EmailInputBox.sendKeys("elinoice@ywalla.com");

        WebElement PasswordInputBox = driver.findElement(By.xpath("//input[@name='password']"));
        PasswordInputBox.sendKeys("UserUser123");

    }

        @Test(description = "Status code")
        public void test9() {

            WebElement StatusCodesButton = driver.findElement(By.partialLinkText("Status Codes"));
            StatusCodesButton.click();

            WebElement TwoHundredButton = driver.findElement(By.xpath("//a[@href='status_codes/200']"));
            TwoHundredButton.click();

            WebElement StatusCodesMessage = driver.findElement(By.xpath("//div[@id='content']"));

            System.out.println(StatusCodesMessage.getText());


        }


//    @AfterMethod
//    public void teardown() {
//        driver.quit();
//    }
}