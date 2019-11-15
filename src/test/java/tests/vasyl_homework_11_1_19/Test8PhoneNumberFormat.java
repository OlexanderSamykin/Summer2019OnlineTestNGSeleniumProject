package tests.vasyl_homework_11_1_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserUtils;
import utils.StringUtility;

public class Test8PhoneNumberFormat {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(4000);

        String ExpextedResult = "Phone format is not correct";
        WebElement RegistrationFormInputBox = driver.findElement(By.xpath("//a[.='Registration Form']"));
        RegistrationFormInputBox.click();

        WebElement RegistrationPhoneNumberInputBox = driver.findElement(By.xpath("//input[@placeholder='571-000-0000']"));
        RegistrationPhoneNumberInputBox.sendKeys("5711234354");
        WebElement FailedPhoneNumberInputNotification = driver.findElement(By.xpath("//*[.='Phone format is not correct']"));
        String ActualResult = FailedPhoneNumberInputNotification.getText();

        StringUtility.verifyEquals(ExpextedResult,ActualResult);

        driver.quit();
    }
}
