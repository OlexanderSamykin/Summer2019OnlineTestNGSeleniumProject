package pages1;

import net.bytebuddy.implementation.bind.annotation.FieldProxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationReader;
import utils.Driver;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css="[USER_LOGIN]")
    private WebElement userName;

    @FindBy(name="USER_PASSWORD")
    private WebElement password;

    @FindBy(className="login-btn")
    private WebElement loginButton;

    public void login(){

        userName.clear();
        password.clear();

        userName.sendKeys(ConfigurationReader.getProperty("user_name"));
        password.sendKeys(ConfigurationReader.getProperty("password"));

    }

}
