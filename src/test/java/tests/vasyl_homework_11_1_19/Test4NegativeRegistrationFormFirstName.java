package tests.vasyl_homework_11_1_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test4NegativeRegistrationFormFirstName {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(4000);

        String ExpextedResult = "first name can only consist of alphabetical letters";
        WebElement RegistrationFormInputBox = driver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li[40]/a"));
        RegistrationFormInputBox.click();
        WebElement RegistrationFirstNameInputBox = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[1]/div/input"));
        RegistrationFirstNameInputBox.sendKeys("123");
        WebElement FailedFirstNameInputNotification = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/form/div[1]/div/small[3]"));
        String ActualResult = FailedFirstNameInputNotification.getText();
        System.out.println(ActualResult);

        if(ExpextedResult.equals(ActualResult)){
            System.out.println("THE TEST PASSED");
        }else{
            System.out.println("THE TEST FAILED");
        }

        driver.close();

    }
}