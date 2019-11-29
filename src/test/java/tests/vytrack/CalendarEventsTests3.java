package tests.vytrack;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalendarEventsTests3 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
//explicit wait
        wait = new WebDriverWait(driver, 10);
//implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//maximize browser
        Actions action = new Actions(driver);
        driver.manage().window().maximize();
        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        //activitiesElement.click();
        action.moveToElement(activitiesElement).perform();

        WebElement calendarEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();
    }

    @Test(description = "Verify that page subtitle \"Options\" is displayed")
    public void test1(){
        WebElement SubtitleOptions = driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/div/div/div[1]/div/div/div/div[2]/div"));
        Assert.assertTrue(SubtitleOptions.isDisplayed());
    }

    @Test(description = "Verify that page number is equal to \"1\"")
    public void test2(){
        WebElement PageNumber = driver.findElement(By.xpath("//*[@type=\"number\"]"));
        System.out.println(PageNumber.getAttribute("value"));
        Assert.assertTrue(PageNumber.getAttribute("value").equals("1"));
    }

    @Test(description = "Verify that view per page number is equals to \"25\"")
    public void test3() {
        WebElement ViewPerPageNumber = driver.findElement(By.xpath("//button[contains(text(),'25')]"));
        System.out.println(ViewPerPageNumber.getText());
        Assert.assertTrue(ViewPerPageNumber.getText().equals("25"));
    }

    @Test(description = "Verify that number of calendar events (rows in the table) is equal to number of records")
    public void test4() {
        List elements = driver.findElements(By.xpath("//tr[contains(@class,'grid-row row-click-action')]"));
        for(int i=0;i<elements.size();i++){
            System.out.println(elements.get(i));
        }
        int ActualSize = elements.size();
        System.out.println(ActualSize);
        WebElement TotalOfRecords = driver.findElement(By.xpath("//label[contains(text(),'Total of 19 records')]"));
        //System.out.println(TotalOfRecords.getText());
        String ExpectedString = "Total Of "+ActualSize+" Records";
        String ActualString = TotalOfRecords.getText();
        Assert.assertTrue(ExpectedString.equals(ActualString));
    }


    @Test(description = "Verify that all calendar events were selected")
    public void test5() {
        //input[contains(@type,'checkbox')]
        WebElement TopCheckBox = driver.findElement(By.xpath("//input[@data-select]"));
        //System.out.println(TopCheckBox.isSelected());
        BrowserUtils.wait(5);
        Actions action = new Actions(driver);
        action.moveToElement(TopCheckBox).perform();
        wait.until(ExpectedConditions.visibilityOf(TopCheckBox));
        wait.until(ExpectedConditions.elementToBeClickable(TopCheckBox));
        TopCheckBox.click();

        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[contains(@data-role,'select-row-cell')]"));
        //By.cssSelector("[type='checkbox']")
        //tr[contains(@class,'grid-row row-click-action')]
        System.out.println(checkboxes.size());

        int index = 1;
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                System.out.println("Checkbox " + index + " is selected");
            } else {
                System.out.println("Checkbox " + index + " is not selected");
            }
            index++;
        }
        //        WebElement checkbox = driver.findElement(By.xpath(“//button[@class=‘btn btn-default btn-small dropdown-toggle’]“));
////                checkbox.click();
//        WebElement allTick = driver.findElement(By.xpath(“/html/body/ul/li[1]/a”));
//        allTick.click();
    }
        @Test(description = "Verify that testers meeting data is displayed")
        public void test6() {
            WebElement TestersMeetingRow = driver.findElement(By.xpath("//*[.='Testers Meeting']"));
            //WebElement TestersMeetingRow = driver.findElement(By.cssSelector("td[Title*='Testers Meeting']"));
            BrowserUtils.wait(5);
            Actions action = new Actions(driver);
            action.moveToElement(TestersMeetingRow).perform();
            wait.until(ExpectedConditions.visibilityOf(TestersMeetingRow));
            wait.until(ExpectedConditions.elementToBeClickable(TestersMeetingRow));
            TestersMeetingRow.click();

            WebElement TestersMeetingInfo = driver.findElement(By.className("row-fluid"));
            System.out.println(TestersMeetingInfo.getText());

            String ExpectedInfo = "Title\nTesters meeting\nDescription\nThis is a a weekly testers meeting\nStart\nNov 27, 2019, 9:30 AM\nEnd\nNov 27, 2019, 10:30 AM\nAll-Day Event\nNo\nOrganizer\nStephan Haley\nGuests\nTom Smith\nRecurrence\nWeekly every 1 week on Wednesday\nCall Via Hangout\nNo";

            String ActualInfo = TestersMeetingInfo.getText();
            Assert.assertEquals(ExpectedInfo,ActualInfo);
        }

        @AfterMethod
        public void teardown(){
            driver.quit();
        }
    }

