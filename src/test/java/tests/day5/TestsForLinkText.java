package tests.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;

public class TestsForLinkText {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = BrowserFactory.getDriver("chrome");

        driver.get("http://practice.cybertekschool.com/");

        driver.findElement(By.linkText("Autocomplete"));

        Thread.sleep(3000);

        driver.quit();
    }
}
