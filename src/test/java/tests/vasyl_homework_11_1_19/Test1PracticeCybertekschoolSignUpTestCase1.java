package tests.vasyl_homework_11_1_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1PracticeCybertekschoolSignUpTestCase1 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();

        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement SignUpForMailingList = driver.findElement(By.linkText("Sign Up For Mailing List"));
        //System.out.println(SignUpForMailingList.getText());
        SignUpForMailingList.click();
        WebElement FullNameInputBox = driver.findElement(By.name("full_name"));
        //System.out.println(FullNameInputBox);
        FullNameInputBox.sendKeys("Alex Sam");
        WebElement EmailInputBox = driver.findElement(By.name("email"));
        //System.out.println(EmailInputBox);
        EmailInputBox.sendKeys("elinoice@yahoo.com");
        WebElement SignUpButton = driver.findElement(By.name("wooden_spoon"));
        SignUpButton.click();
        String ExpectedSignUpConfirmationMessage = "Thank you for signing up. Click the button below to return to the home page.";
        WebElement ActualSignUpConfirmationMessage = driver.findElement(By.name("signup_message"));
        System.out.println("The sign up confirmation text message is:    "+ActualSignUpConfirmationMessage.getText());

        if (ExpectedSignUpConfirmationMessage.equals(ActualSignUpConfirmationMessage.getText())){

            System.out.println("THE TEST PASSED");
        }else{
            System.out.println("THE TEST FAILED");
        }

        Thread.sleep(3000);

        driver.close();
    }
}


//Sign Up For Mailing List
//<a href="/sign_up">Sign Up For Mailing List</a>