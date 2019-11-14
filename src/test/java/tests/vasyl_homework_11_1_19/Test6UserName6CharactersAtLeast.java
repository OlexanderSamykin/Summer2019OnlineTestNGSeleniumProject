package tests.vasyl_homework_11_1_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserUtils;
import utils.StringUtility;

public class Test6UserName6CharactersAtLeast {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(4000);

        String ExpextedResult = "The username must be more than 6 and less than 30 characters long";

        //WebElement RegistrationFormInputBox = driver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li[40]/a"));
        WebElement RegistrationFormInputBox = driver.findElement(By.xpath("//a[.='Registration Form']"));
        RegistrationFormInputBox.click();

        WebElement RegistrationUserNameInputBox = driver.findElement(By.xpath("//input[@placeholder='username']"));
                RegistrationUserNameInputBox.sendKeys("user");
        WebElement FailedUserNameInputNotification = driver.findElement(By.xpath("//*[contains(@data-bv-result,'INVALID')]"));
                String ActualResult = FailedUserNameInputNotification.getText();


        StringUtility.verifyEquals(ExpextedResult,ActualResult);


        driver.quit();

    }

}
