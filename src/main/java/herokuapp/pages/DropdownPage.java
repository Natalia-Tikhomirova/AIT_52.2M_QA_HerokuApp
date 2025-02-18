package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DropdownPage extends BasePage {
    public DropdownPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

   @FindBy(id = "dropdown")
   WebElement dropdown;

    public DropdownPage selectDropdownList(String option) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);

        // Проверяем, что выбран правильный элемент
        String selectedOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption, option, "Ошибка: выбрана неверная опция!");

        System.out.println("Selected option: " + selectedOption);
        return this;
    }

    public DropdownPage selectInvalidOption(String option) {

        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(option);
        } catch (Exception e) {
            System.out.println("Error: Option '" + option + "' does not exist in the dropdown!");
        }
        return this;
    }
}
