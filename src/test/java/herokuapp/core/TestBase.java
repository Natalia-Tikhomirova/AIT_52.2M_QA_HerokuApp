package herokuapp.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
        // PageFactory упрощает работу с веб-элементами и делает код более чистым и читаемым
        // PageFactory — это утилита в Selenium WebDriver,
        // которая упрощает инициализацию веб-элементов на странице
        // PageFactory инициализирует элементы, помеченные аннотациями @FindBy в вашем классе страницы (this)
        PageFactory.initElements(driver,this);
    }

    // Метод ожидания элемента перед кликом
    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Метод для клика с ожиданием
    public void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    // Ввод текста с предварительным кликом и очисткой поля
    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    // Ввод текста через JavaScript с прокруткой
    public void typeWithJS(WebElement element, String text, int x, int y) {
        if (text != null) {
            js.executeScript("window.scrollBy("+x+","+y+")");
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    // Клик с JavaScript-прокруткой
    public void clickWithJS(WebElement element, int x, int y){
        // js.executeScript("window.scrollBy(100,200)");
        // x - сколько пикселей прокрутить по горизонтали
        // y - сколько пикселей прокрутить по вертикали
        //  js.executeScript("window.scrollBy(0,500)");
        js.executeScript("window.scrollBy(" + x + "," + y + ")");  // скроллинг на заданное количество пикселей
        //js.executeScript("window.scrollBy({},{})",x,y);
        click(element);
    }

    // Метод для скрытия рекламы (если она мешает)
    public void hideAds() {
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }
}
