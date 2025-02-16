package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecurePage extends BasePage {
    public SecurePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    @FindBy(id = "flash")
    WebElement successMessage;

    public String getSuccessMessage() {
        return successMessage.getText().trim();
    }

   @FindBy(id = "flash")
   WebElement errorMessage;
    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }
}
