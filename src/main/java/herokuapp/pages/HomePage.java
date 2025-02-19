package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "a[href='/login']")
    WebElement formAuthenticationPage;

    public LoginPage getFormAuthenticationPage() {
        click(formAuthenticationPage);
        return new LoginPage(driver, wait);
    }

    @FindBy(xpath = "//a[text()='Nested Frames']")
    WebElement nestedFrames;

    public NestedFramesPage getNestedFramesPage() {
        click(nestedFrames);
        return new NestedFramesPage(driver, wait);
    }

    @FindBy(css = "a[href='/windows']")
    WebElement multipleWindow;

    public MultipleWindowsPage getMultipleWindowsPage() {
        click(multipleWindow);
        return new MultipleWindowsPage(driver, wait);
    }


    @FindBy(css = "div#content>ul>li:nth-of-type(11)>a")
    WebElement dropdown;

    public DropdownPage getDropdownPage() {
        click(dropdown);
        return new DropdownPage(driver, wait);
    }

    @FindBy(id = "dropdown")
    WebElement dropdownPage;

    public DropdownPage dropdownPage() {
        click(dropdownPage);
        return new DropdownPage(driver, wait);
    }



    @FindBy(xpath = "(//div[@id='content']//a)[2]")
    WebElement AddRemoveElements;

    public AddRemoveElementsPage getAddRemoveElementsPage() {
        click(AddRemoveElements);
        return new AddRemoveElementsPage(driver, wait);
    }

    @FindBy(css = "ul>li:nth-of-type(25)>a")
    WebElement hovers;

    public HoversPage getHoversPage() {
        click(hovers);
        return new HoversPage(driver, wait);
    }
}
