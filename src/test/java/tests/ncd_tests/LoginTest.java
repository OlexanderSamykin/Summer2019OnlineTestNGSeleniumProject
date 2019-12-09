package tests.ncd_tests;

import org.testng.annotations.Test;
import pages1.LoginPage;
import utils.Driver;

public class LoginTest extends TestBase {

    @Test(description = "Verify login")
    public void test1(){
        LoginPage loginPage = new LoginPage();
        loginPage.login();

        System.out.println(Driver.get().getTitle());

    }

}
