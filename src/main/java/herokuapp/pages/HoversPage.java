package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HoversPage extends BasePage {
    public HoversPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = ".figure")
    List<WebElement> users;

    @FindBy(css = ".figcaption h5")
    List<WebElement> userNames;

    public HoversPage hoverOverUser(int index) {
        WebElement user = users.get(index);
        WebElement userName = userNames.get(index);
        Actions actions = new Actions(driver);
        actions.moveToElement(user).perform(); // Наведение курсора
        wait.until(ExpectedConditions.visibilityOf(userName)); // Ожидание видимости имени
        System.out.println("Навели курсор, появилось: " + userName.getText());
        return this;
    }


    public HoversPage veryfyUserName(String text) {
        shouldHaveText(userNames.get(0), text, 5000);
        String userName = userNames.get(0).getText();
        System.out.println("Появилось имя: " + userName);
        return this;
    }
}
