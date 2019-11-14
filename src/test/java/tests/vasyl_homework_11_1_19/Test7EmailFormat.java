package tests.vasyl_homework_11_1_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserUtils;
import utils.StringUtility;

public class Test7EmailFormat {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(4000);

        String ExpextedResult = "email address is not a valid Email format is not correct";

        WebElement RegistrationFormInputBox = driver.findElement(By.xpath("//a[.='Registration Form']"));
        RegistrationFormInputBox.click();

        WebElement RegistrationEmailAddressInputBox = driver.findElement(By.xpath("//input[@placeholder='email@email.com']"));
        RegistrationEmailAddressInputBox.sendKeys("testers@email");
        WebElement FailedEmailAddressInputNotification1 = driver.findElement(By.xpath("//*[contains(@data-bv-validator,'emailAddress')]"));
        String TempResult1 = FailedEmailAddressInputNotification1.getText();
        WebElement FailedEmailAddressInputNotification2 = driver.findElement(By.xpath("//*[.='Email format is not correct']"));
        String TempResult2 = FailedEmailAddressInputNotification2.getText();

        String ActualResult=TempResult1+" "+TempResult2;
        System.out.println(ActualResult);

        StringUtility.verifyEquals(ExpextedResult,ActualResult);

        driver.quit();
    }
}