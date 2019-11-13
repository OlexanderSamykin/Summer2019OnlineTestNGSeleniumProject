package tests.online19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserUtils;
import utils.StringUtility;

public class LoginNegativeTesting {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();

        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement UserNameLocator = driver.findElement(By.name("_username"));
        WebElement PasswordLocator = driver.findElement(By.name("_password"));
        WebElement LoginBox=driver.findElement(By.name("_submit"));
        String Vy_Main_Username="user1";
        String Vy_Main_Password="UserUser";
        UserNameLocator.sendKeys(Vy_Main_Username);
        PasswordLocator.sendKeys(Vy_Main_Password);
        LoginBox.click();
        WebElement InvalidLoginCredentials=driver.findElement(By.xpath("//div[contains(@class,'alert alert-error')]"));
          String ExpectedFailedLoginNotificationText="Invalid user name or password.";
          String ActualFailedLoginNotificationText=InvalidLoginCredentials.getText();
          System.out.println(ActualFailedLoginNotificationText);
        if (ExpectedFailedLoginNotificationText.equals(ActualFailedLoginNotificationText)){
           System.out.println("PASSED");
        }
        else{
            System.out.println("FAILED");
        }
        driver.close();


    }
}

//*[@id="login-form"]/fieldset/div[1]
//div[contains(@class,'alert alert-error')]
