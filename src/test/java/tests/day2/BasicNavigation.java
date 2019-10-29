package tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicNavigation {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        //to maximize browser
        driver.manage().window().maximize();
        driver.get("http://google.com");
        //we want to navigate to another page
        driver.navigate().to("http://amazon.com");
        //navigating to the previous page
        driver.navigate().back();
        //if I wanna know the url of the current website
        String url=driver.getCurrentUrl();
        System.out.println(url);


        driver.quit();
    }
}
