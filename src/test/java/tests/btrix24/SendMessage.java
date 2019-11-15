package tests.btrix24;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import utils.BrowserFactory;

public class SendMessage {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://login1.nextbasecrm.com/");
    }



}
