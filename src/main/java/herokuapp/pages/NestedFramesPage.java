package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class NestedFramesPage extends BasePage {
    public NestedFramesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(tagName = "frame")
    List<WebElement> frames;

    // Получаем весь список фреймов
    public NestedFramesPage getListOfNestedFrames() {
        driver.switchTo().defaultContent(); // Возвращаемся в главное окно
        int numberOfNestedFrames = ((Long) js.executeScript("return window.length")).intValue();
        //System.out.println(numberOfNestedFrames);
        if (frames == null || frames.isEmpty()) {
            System.out.println("No frames was found using @FindBy");
            return this;
        }
        System.out.println("Number of frames detected: [" + numberOfNestedFrames + "]");
        System.out.println("Frames found using @FindBy: [" + frames.size() + "]");

        for (WebElement frame : frames) {
            String frameSRC = frame.getAttribute("src");
            String frameName = frame.getAttribute("name");

            System.out.println("Iframe found - SRC: [" + (frameSRC != null ? frameSRC : "No SRC") +
                    "], Name: [" + (frameName != null ? frameName : "No Name") + "]");
        }
        return this;
    }

    // Переключение в frame по src и проверка name
    public NestedFramesPage switchToFrameBySrcAndVerifyName(String src, String expectedName) {

        if (!src.equals("/frame_middle")) {
            driver.switchTo().defaultContent();
        }
        for (WebElement frame : frames) {
            String frameSRC = frame.getAttribute("src");
            String frameName = frame.getAttribute("name");

            if (frameSRC != null && frameSRC.contains(src)) {
                driver.switchTo().frame(frame);
                System.out.println("Switched to frame with src: " + src);

                if (expectedName.equals("frame-top")) {
                    return this;
                }
                // Проверяем, что `name` соответствует ожидаемому значению
                Assert.assertEquals(frameName, expectedName,
                        "Expected frame name: [" + expectedName + "], but got: [" + frameName + "]");
                return this;
            }
        }

        // Если `frame-middle` не найден, ищем его внутри `frame-top`
        if (src.equals("/frame_middle")) {
            driver.switchTo().frame(0); // Переключаемся в frame-top
            WebElement middleFrame = driver.findElement(By.name("frame-middle"));
            driver.switchTo().frame(middleFrame);
            System.out.println("Switched to nested iframe: frame-middle");
            return this;
        }

        throw new AssertionError("No frame found with src containing: " + src);
    }

    @FindBy(tagName = "body")
    WebElement frameBody;

    // Проверка текста в `frame`
    public NestedFramesPage verifyFrameText(String expectedText, int timeout) {
        shouldHaveText(frameBody, expectedText, timeout);
        return this;
    }

    // Возвращение в главное окно
    public NestedFramesPage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }
}


