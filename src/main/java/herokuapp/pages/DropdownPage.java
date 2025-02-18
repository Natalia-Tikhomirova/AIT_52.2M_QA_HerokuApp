package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public DropdownPage verifyDropdownList(String option) {
        shouldHaveText(dropdown, option, 5000);
        return this;
    }

    @FindBy(id = "dropdown")
    WebElement listDropdawn;

    public DropdownPage verifyDropdownListHashSet(String expected) {
        Select select = new Select(listDropdawn);
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        List<String> selectedText = new ArrayList<>();
        for (WebElement option : selectedOptions) {
            selectedText.add(option.getText());
            System.out.println(option.getText());
        }
        boolean isListSelected = new HashSet<>(selectedText).contains(expected);
        Assert.assertTrue(isListSelected);
        return this;
    }
}
