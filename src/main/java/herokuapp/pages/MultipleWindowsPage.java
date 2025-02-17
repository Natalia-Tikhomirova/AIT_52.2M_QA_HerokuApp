package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultipleWindowsPage extends BasePage {
    public MultipleWindowsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(tagName = "h3")
    WebElement newWindow;

    public MultipleWindowsPage verifyNewWindowTitle(String text) {
        shouldHaveText(newWindow, text, 5000);
        return this;
    }


}
