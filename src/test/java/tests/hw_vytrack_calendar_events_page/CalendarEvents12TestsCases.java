package tests.hw_vytrack_calendar_events_page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;
import utils.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalendarEvents12TestsCases {

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
        driver.manage().window().maximize();
        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);

        WebElement loaderMask = null;

        if (driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size() > 0) {
            loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        }

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        activitiesElement.click();

        WebElement calendarEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();

        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
    }

    @Test(description = "Hover on three dots “...” for “Testers meeting” calendar event")
    public void test1() {
        Actions a = new Actions(driver);
        WebElement ThreeDots = driver.findElement(By.xpath("//tr[13]//td[9]"));
        a.moveToElement(ThreeDots).build().perform();

        WebElement view = ThreeDots.findElement(By.xpath("//i[@class='fa-eye hide-text']"));
        //a[@title='View']
        a.moveToElement(view).build().perform();
        //BrowserUtils.wait(5);
        wait.until(ExpectedConditions.visibilityOf(view));
        Assert.assertTrue(view.isDisplayed());

        WebElement edit = ThreeDots.findElement(By.xpath("//i[@class='fa-pencil-square-o hide-text']"));
        //a[@title='Edit']
        a.moveToElement(edit).build().perform();
        wait.until(ExpectedConditions.visibilityOf(edit));
        Assert.assertTrue(edit.isDisplayed());

        WebElement delete = ThreeDots.findElement(By.xpath("//i[@class='fa-trash-o hide-text']"));
        //a[@title='Delete'])[3]
        a.moveToElement(delete).build().perform();
        wait.until(ExpectedConditions.visibilityOf(delete));
        Assert.assertTrue(delete.isDisplayed());

    }

    @Test(description = "Click on “Grid Options” button,Deselect all options except “Title,Verify that “Title” column still displayed")
    public void TestCase2() {//done
        //Click “Grid Options” button
        WebElement GridButton = driver.findElement(By.xpath("//i[@class='fa-cog hide-text']"));
        GridButton.click();
        //find table row and column for demo
        int row = driver.findElements(By.xpath("//div[@class='table-wrapper']/table/tbody/tr")).size();
        int column = driver.findElements(By.xpath("//div[@class='table-wrapper']/table/tbody/tr[1]/td")).size();
        System.out.println("row size= " + row);        //7
        System.out.println("column size= " + column);  //3
        //I use the row size for loop stopping point,starting point is 2 bc I dont want to deselect Title (it is column#1)
        for (int i = 2; i <= row; i++) {
            WebElement deselect = driver.findElement(By.xpath("//div[@class='table-wrapper']/table/tbody/tr[" + i + "]/td[3]/input"));
            deselect.click();
            BrowserUtils.wait(2);//for demo
        }
        //this is a another table
        WebElement TitleColumn = driver.findElement(By.xpath("//table[@class='grid table-hover table table-bordered table-condensed']//tr[1]//th[2]/a"));
        Assert.assertTrue(TitleColumn.isDisplayed(), "Title column not displayed");
        System.out.println("Test Pass: " + TitleColumn.getText());//Test Pass: TITLE
    }
    //E[contains(@A,'t')]

    @Test(description = "Navigate to “Activities -> Create Calendar Event")
    public void TestCase3() {
        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        driver.findElement(By.xpath("//a[@class='btn-success btn dropdown-toggle']")).click();
        BrowserUtils.wait(3);

        WebElement SaveAndCloseDropdown = driver.findElement(By.xpath("//button[@class='action-button dropdown-item'][contains(text(),'Save and Close')]"));
        BrowserUtils.wait(5);
        Assert.assertEquals(SaveAndCloseDropdown.getText(), "Save And Close");

        WebElement SaveAndNewDropdown = driver.findElement(By.xpath("//button[@class='main-group action-button dropdown-item'][contains(text(),'Save and New')]"));
        BrowserUtils.wait(5);
        Assert.assertEquals(SaveAndNewDropdown.getText(), "Save And New");

        WebElement Save = driver.findElement(By.xpath("//button[@data-action='{\"route\":\"oro_calendar_event_update\",\"params\":{\"id\":\"$id\"}}'][contains(text(),'Save')]"));
        BrowserUtils.wait(5);
        Assert.assertEquals(Save.getText(), "Save");

    }

    @Test(description = "All kinda stuff")
    public void TestCase4() {
        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        WebElement CancelCalendarEventButton = driver.findElement(By.xpath("//a[@title='Cancel']"));
        CancelCalendarEventButton.click();

        WebElement AllCalendarEvents = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        Assert.assertTrue(AllCalendarEvents.isDisplayed());

    }

    @Test(description = "Verify that difference between end and start time is exactly 1 hour")
    public void TestCase5() {
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement start = driver.findElement(By.cssSelector("[class*='start ui']"));
        String startTime = start.getAttribute("value");
        System.out.println(startTime);
        WebElement end = driver.findElement(By.cssSelector("[class*='end ui']"));
        wait.until(ExpectedConditions.elementToBeClickable(end));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", end);
        String endTime = driver.findElement(By.cssSelector("[class*='ui-timepicker-selected']")).getText();
        System.out.println(endTime);
        Assert.assertEquals(-1, (Integer.parseInt(startTime.substring(0, startTime.indexOf(":"))) - Integer.parseInt(endTime.substring(0, startTime.indexOf(":")))));
    }

    @Test(description = "Verify that end time is equals to 10:00pm when 9:00 PM start time is selected")
    public void TestCase6() {
        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);
        WebElement StartingTime = driver.findElement(By.xpath("//input[contains(@name,'time_selector_oro_calendar_event_form_start-uid')]"));
        BrowserUtils.wait(5);
        StartingTime.click();
        List<WebElement> list = driver.findElements(By.xpath("//li[contains(@class,'ui-timepicker')]"));
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getText().equalsIgnoreCase("9:00 PM")) {
                Assert.assertEquals(list.get(i).getText(), "9:00 PM", "expected result 9 pm");
                list.get(i).click();
                break;
            }
        }
        BrowserUtils.wait(3);
        WebElement EndingTime = driver.findElement(By.xpath("//input[contains(@name,'time_selector_oro_calendar_event_form_end-uid')]"));
        EndingTime.click();
        List<WebElement> list1 = driver.findElements(By.xpath("//li[contains(@class,'ui-timepicker')]"));
        for (int i = 0; i < list1.size() - 1; i++) {
            if (list1.get(i).getText().equalsIgnoreCase("10:00 PM")) {
                Assert.assertEquals(list1.get(i).getText(), "10:00 PM", "expected result 10 pm");
                list1.get(i).click();
                break;
            }
        }
        BrowserUtils.wait(3);
    }

    @Test(description = "All kinda stuff")
    public void TestCase7() {
        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        WebElement AllDayEventCheckBox = driver.findElement(By.xpath("//input[@name='oro_calendar_event_form[allDay]']"));
        AllDayEventCheckBox.click();
        Assert.assertTrue(AllDayEventCheckBox.isSelected());
        boolean AllDayEventCheckBoxSelected = AllDayEventCheckBox.isSelected();
        System.out.println("All Day Event Check Box is selected =  " + AllDayEventCheckBoxSelected);
        BrowserUtils.wait(3);

        boolean StartingTime = driver.findElement(By.xpath("//input[contains(@name,'time_selector_oro_calendar_event_form_start-uid')]")).isDisplayed();
        System.out.println("Starting Time InputBoxis Displayed =  " + StartingTime);
        BrowserUtils.wait(3);

        boolean EndingTime = driver.findElement(By.xpath("//input[contains(@name,'time_selector_oro_calendar_event_form_end-uid')]")).isDisplayed();
        System.out.println("Ending Time InputBox is Displayed =  " + EndingTime);
        BrowserUtils.wait(3);

        WebElement StartingDateBox = driver.findElement(By.xpath("//input[contains(@class,'input-small datepicker-input start hasDatepicker')]"));
        System.out.println("Starting Date InputBox is Displayed =  " + StartingDateBox.isDisplayed());

        //WebElement StartingDateBox = driver.findElement(By.id("date_selector_oro_calendar_event_form_start-uid-5de42f6cc4b17"));
        WebElement EndingDateBox = driver.findElement(By.xpath("//input[contains(@class,'input-small datepicker-input end hasDatepicker')]"));
        System.out.println("Ending Date InputBox is Displayed =  " + EndingDateBox.isDisplayed());

    }

    @Test(description = "Verify that “Repeat” checkbox is selected; Verify that “Daily” is selected by default and following options are available in “Repeats” drop-down:")
    public void TestCase8() {
        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        WebElement RepeatCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][@data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        boolean RepeatCheckBoxIsSelected = RepeatCheckBox.isSelected();
        System.out.println("Repeat Check Box is selected =  " + RepeatCheckBoxIsSelected);
        BrowserUtils.wait(3);

        WebElement RepeatsDropdownBox = driver.findElement(By.xpath("//select[@data-name='recurrence-repeats'][@class='recurrence-repeats__select']"));       RepeatsDropdownBox.click();

        ArrayList<String> ExpectedOptions = new ArrayList<>();
        ExpectedOptions.add("Daily");
        ExpectedOptions.add("Weekly");
        ExpectedOptions.add("Monthly");
        ExpectedOptions.add("Yearly");

        for (String ExpectedOption : ExpectedOptions) {
            System.out.println("Repeats Dropdown Box Expected Options: " + ExpectedOption);
        }

        ArrayList<String> ActualOptions = new ArrayList<>();

        Select select = new Select(RepeatsDropdownBox);
        List<WebElement> Options = select.getOptions();
        for (WebElement option : Options) {
            System.out.println("Repeats Dropdown Box Actual Options: " + option.getText());
            ActualOptions.add(option.getText());
        }
        System.out.println(ActualOptions);

        Assert.assertEquals(ActualOptions, ExpectedOptions, "Expected Options are not equal to Actual Options");
    }


    @Test(description = "All kinda stuff")
    public void TestCase9() {
        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        WebElement RepeatCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][@data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        boolean RepeatCheckBoxIsSelected = RepeatCheckBox.isSelected();
        System.out.println("Repeat Check Box is selected =  " + RepeatCheckBoxIsSelected);
        BrowserUtils.wait(3);

        WebElement RepeatEveryRadioButton = driver.findElement(By.xpath("//input[contains(@checked,'checked')]"));
        boolean isSelected = RepeatEveryRadioButton.isSelected();
        Assert.assertTrue(isSelected);
        BrowserUtils.wait(3);

        WebElement EndsRadioButtonNever = driver.findElement(By.xpath("//div[@class='controls recurrence-subview-control recurrence-subview-control__items']//div[1]//label[1]//input[1]"));
        boolean EndsRadioButtonNeverIsSelected = EndsRadioButtonNever.isSelected();
        System.out.println("Ends Radio Button is selected As Never =  " + EndsRadioButtonNeverIsSelected);
        BrowserUtils.wait(3);

        String SummaryTextActual = driver.findElement(By.xpath("//div[@class='control-group recurrence-summary alert-info']")).getText();
        System.out.println(SummaryTextActual);

        String SummaryTextExpected = "Summary:\nDaily every 1 day";

        Assert.assertEquals(SummaryTextActual, SummaryTextExpected);

    }

    @Test(description = "All kinda stuff")
    public void TestCase10() {
        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        WebElement RepeatCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][@data-name='recurrence-repeat']"));
        BrowserUtils.wait(5);
        RepeatCheckBox.click();
        BrowserUtils.wait(5);

        WebElement EndsAfterRadioButton = driver.findElement(By.xpath("//span[contains(text(),'After')]"));
        EndsAfterRadioButton.click();
        BrowserUtils.wait(5);

        WebElement OccurrencesNumberInputBox = driver.findElement(By.xpath("//input[@data-related-field='occurrences']"));
        BrowserUtils.wait(5);
        OccurrencesNumberInputBox.sendKeys("10");
        OccurrencesNumberInputBox.click();

        String SummaryTextActual = driver.findElement(By.xpath("//div[@class='control-group recurrence-summary alert-info']")).getText();
        System.out.println(SummaryTextActual);

        String SummaryTextExpected = "Summary:\nDaily every 1 day, end after 10 occurrences";

        Assert.assertEquals(SummaryTextExpected, SummaryTextActual);
    }

    @Test(description = "After Selecting 'By Nov 18 2021' Verify that summary message is properly displayed")
    public void testCase11() {

        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        WebElement RepeatCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][@data-name='recurrence-repeat']"));
        BrowserUtils.wait(5);
        RepeatCheckBox.click();
        BrowserUtils.wait(5);

        WebElement EndsByRadioButton = driver.findElement(By.xpath("//*[@class='recurrence-subview-control__text'][contains(text(),'By')]"));
        EndsByRadioButton.click();
        BrowserUtils.wait(5);

        WebElement ChooseADateInputBox = driver.findElement(By.cssSelector("[class='datepicker-input hasDatepicker']"));
        ChooseADateInputBox.sendKeys("Nov 18, 2021");
        BrowserUtils.wait(5);

        String SummaryTextActual = driver.findElement(By.xpath("//div[@class='control-group recurrence-summary alert-info']")).getText();
        System.out.println(SummaryTextActual);

        String SummaryTextExpected ="Summary:\nDaily every 1 day, end by Nov 18, 2021";

        Assert.assertEquals(SummaryTextExpected, SummaryTextActual);

    }

    @Test(description = "After selecting Repeat, Weekly, Monday and Friday. Verify that summary message is correct")
    public void testCase12() {

        WebElement CreateCalendarEventButton = driver.findElement(By.xpath("//a[@title='Create Calendar event']"));
        CreateCalendarEventButton.click();
        BrowserUtils.wait(5);

        WebElement RepeatCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][@data-name='recurrence-repeat']"));
        BrowserUtils.wait(5);
        RepeatCheckBox.click();
        BrowserUtils.wait(5);

        WebElement RepeatsDropdownBox = driver.findElement(By.xpath("//select[@data-name='recurrence-repeats'][@class='recurrence-repeats__select']"));
        RepeatsDropdownBox.click();

        Select ExpectedOptions = new Select(RepeatsDropdownBox);
        ExpectedOptions.selectByVisibleText("Weekly");

        WebElement MondayInputBox = driver.findElement(By.xpath("//input[@type='checkbox'][@value='monday']"));
        MondayInputBox.click();
        WebElement FridayInputBox = driver.findElement(By.xpath("//input[@type='checkbox'][@value='friday']"));
        FridayInputBox.click();

        boolean MondayisSelected = MondayInputBox.isSelected();
        Assert.assertTrue(MondayisSelected);
        BrowserUtils.wait(3);

        boolean FridayisSelected = FridayInputBox.isSelected();
        Assert.assertTrue(FridayisSelected);
        BrowserUtils.wait(3);

        String SummaryTextActual = driver.findElement(By.xpath("//div[@class='control-group recurrence-summary alert-info']")).getText();
        System.out.println(SummaryTextActual);

        String SummaryTextExpected = "Summary:\nWeekly every 1 week on Monday, Friday";
        Assert.assertEquals(SummaryTextExpected, SummaryTextActual);
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}