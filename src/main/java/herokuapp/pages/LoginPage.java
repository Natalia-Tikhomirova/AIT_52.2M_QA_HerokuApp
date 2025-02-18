package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "username")
    WebElement userName;

    @FindBy(id = "password")
    WebElement userPassword;

    public LoginPage enterPersonalData(String user, String password) {
        type(userName,user);
        type(userPassword,password);
        return this;
    }

    @FindBy(css = "i.fa.fa-2x.fa-sign-in")
    WebElement loginButton;

    public SecurePage clickLoginButton() {
        click(loginButton);
        return new SecurePage(driver, wait);
    }

}
