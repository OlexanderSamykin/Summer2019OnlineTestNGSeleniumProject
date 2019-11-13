package tests.vasyl_homework_11_1_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserUtils;
import utils.StringUtility;

import java.util.List;

public class Test2TheSizeMatters {

        public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();

        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(4000);

            List elements = driver.findElements(By.className("list-group-item"));

            for(int i=0;i<elements.size();i++){
                System.out.println(elements.get(i));
            }

            int ActualSize = elements.size();
            System.out.println(ActualSize);

            int ExpectedSize = 48;

            if(ActualSize==ExpectedSize){
                System.out.println("THE TEST PASSED");
            }else{
                System.out.println("THE TEST FAILED");
            }

            Thread.sleep(3000);

            driver.close();
    }

}