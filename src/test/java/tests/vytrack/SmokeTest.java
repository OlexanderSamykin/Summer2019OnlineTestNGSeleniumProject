package tests.vytrack;

import org.testng.annotations.DataProvider;

public class SmokeTest extends TestBase{

    @DataProvider(name="navigationInfo")
    public Object[][] navigationInfo(){
        return new Object[][]{
                {"Dashboards","Dashboard","Dashboard"},
                {"Dashboards","Manage Dashboards","All Manage Dashboards"},
                {"Fleet", "Vehicles", "All Cars"},
                {"Customers", "Accounts", "All Accounts"},

        };
    }

}
