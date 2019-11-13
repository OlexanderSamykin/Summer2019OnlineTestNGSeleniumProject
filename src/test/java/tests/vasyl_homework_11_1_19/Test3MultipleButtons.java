package tests.vasyl_homework_11_1_19;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
    public class Test3MultipleButtons {
        public static void main(String[] args) throws InterruptedException {
            WebDriverManager.chromedriver().setup();
            ChromeDriver driver = new ChromeDriver();
            driver.get("https://practice-cybertekschool.herokuapp.com");
            driver.manage().window().maximize();
            Thread.sleep(4000);
            String expectedResult = "Clicked on button one!";
            driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[33]/a")).click();
            driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/button[1]")).click();
            String actualResult = driver.findElement(By.id("result")).getText();
            System.out.println("Expected Result: " + expectedResult);
            System.out.println("Actual Result: " + actualResult);

            if(expectedResult.equals(actualResult)){
                System.out.println("THE TEST PASSED");
            }else{
                System.out.println("THE TEST FAILED");
            }

            driver.close();
}
    }
////*[@id="content"]/div[1]/button[1]