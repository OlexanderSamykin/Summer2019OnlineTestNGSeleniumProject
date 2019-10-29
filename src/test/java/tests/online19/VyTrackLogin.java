package tests.online19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VyTrackLogin {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();

        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        Thread.sleep(4000);
        WebElement UserNameLocator=driver.findElement(By.name("_username"));
        WebElement PasswordLocator=driver.findElement(By.name("_password"));
        WebElement LoginBox=driver.findElement(By.name("_submit"));
        //System.out.println(UserNameLocator);
        String Vy_Main_Username="storemanager222";
        String Vy_Main_Password="UserUser123";
        UserNameLocator.sendKeys(Vy_Main_Username);
        PasswordLocator.sendKeys(Vy_Main_Password);
        LoginBox.click();
        //driver.findElement(By.name("_submit")).click();
        String WebUrl=driver.getCurrentUrl();
        String Title=driver.getTitle();
        System.out.println(WebUrl);
        System.out.println(Title);

        //driver.close();
    }
}
