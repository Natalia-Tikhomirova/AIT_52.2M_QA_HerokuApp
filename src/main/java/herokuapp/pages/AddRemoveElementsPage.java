package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddRemoveElementsPage extends BasePage {
    public AddRemoveElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


   @FindBy(css = "div>button")
    WebElement addElementButton;

    public AddRemoveElementsPage clickOnAddElementButton() {
       addElementButton.click();
        return this;
    }

    @FindBy(className = "added-manually")
    WebElement deleteButton;

    public AddRemoveElementsPage verifyDeleteButtonText(String text) {
        shouldHaveText(deleteButton, text, 5000);
        String deleteButtonText = deleteButton.getText();
        System.out.println("Появилась кнопка: "  + deleteButtonText);
        return this;
    }

    public AddRemoveElementsPage clickOnDeleteButton() {
        deleteButton.click();
        wait.until(ExpectedConditions.invisibilityOf(deleteButton));
        System.out.println("Кнопка удалена");
        return this;
    }
}
