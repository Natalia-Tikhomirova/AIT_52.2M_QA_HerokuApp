package herokuapp.core;

import herokuapp.pages.HomePage;
import herokuapp.pages.LoginPage;
import herokuapp.pages.SecurePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;
    HomePage homePage;
    LoginPage loginPage;
    SecurePage securePage;

    /** Switch-case — это конструкция в программировании, которая позволяет выполнять различные
     * блоки кода в зависимости от значения переменной.
     * Она заменяет длинные цепочки условий if-else if, делая код более читаемым и удобным для понимания.
     */
    public void init() {
        String browser = System.getProperty("browser", "chrome");
        // Проверяет значение переменной browser и в зависимости от результата инициализирует нужный драйвер
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default: // Это резервный сценарий на случай, если значение browser не совпадает ни с одним из указанных случаев
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        //driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // неявное
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        securePage = new SecurePage(driver, wait);
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public SecurePage getSecurePage() {
        return securePage;
    }

    public void stop() {
        driver.quit();
    }
}