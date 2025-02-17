package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class NewWindowPage extends BasePage {
    public NewWindowPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "div#content>div>a")
    WebElement openNewWindow;

    public NewWindowPage openNewWindow() {
        click(openNewWindow);
        return this;
    }

    @FindBy(tagName = "h3")
    private WebElement newWindowHeading;

    public NewWindowPage switchToNewWindow() {
        click(newWindowHeading);
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        System.out.println("Switched to new window.");
        return this;
    }

   @FindBy(tagName = "div")
   WebElement sampleHeading;
    public NewWindowPage verifyNewWindowTitle(String text) {
        shouldHaveText(sampleHeading, text, 5000);
        return this;
    }

    public NewWindowPage closeNewWindowAndReturn() {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(windows.get(0));
        System.out.println("Closed new window and returned to main page.");
        return this;
    }
}
