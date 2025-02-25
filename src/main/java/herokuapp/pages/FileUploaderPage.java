package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_ENTER;

public class FileUploaderPage extends BasePage {
    public FileUploaderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "file-upload")
    WebElement file_upload;

    public FileUploaderPage chooseFileByChooseFileButton(String filePath) {
        //type(file_upload,filePath);
        file_upload.sendKeys(filePath);
        return this;
    }

    @FindBy(id = "file-submit")
    WebElement file_submit;

    public FileUploaderPage clickOnUploadButton() {
        click(file_submit);
        return this;
    }

    @FindBy(id = "uploaded-files")
    WebElement uploaded_files;

    public FileUploaderPage verifyFileName(String expectedFileName) {
        shouldHaveText(uploaded_files, expectedFileName, 5000);
        return this;
    }

    //* **************************
    @FindBy(id = "drag-drop-upload")
    WebElement boxContainer;

    public FileUploaderPage chooseFileInBox(String filePath) {
        click(boxContainer);
        try {
            // Устанавливаем путь в буфер обмена
                StringSelection buffer = new StringSelection(filePath); // Переменная хранит строку для вставки в буфер обмена
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(buffer, null); // переменную `buffer` устанавливаем в память компьютера в буфер
            System.out.println(filePath);
            System.out.println(buffer);

            Robot robot =  new Robot();
            robot.delay(1000); // Даем время окну загрузки открыться

            if (System.getProperty("os.name").contains("Mac")) {
                // Переключаем фокус на браузер (уже работает)
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"Google Chrome\" to activate"
                });
                robot.keyPress(VK_META);
                robot.keyPress(VK_TAB);
                robot.keyRelease(VK_TAB);
                robot.keyRelease(VK_META);
                robot.keyPress(VK_META);
                robot.keyPress(VK_V);
                robot.keyRelease(VK_V);
                robot.keyRelease(VK_META);
                robot.delay(500);
                robot.keyPress(VK_ENTER);
                robot.keyRelease(VK_ENTER);
                robot.delay(500);
                robot.keyPress(VK_ENTER);
                robot.keyRelease(VK_ENTER);
            } else if(System.getProperty("os.name").contains("Win")){
                // Ctrl + V
                robot.keyPress(KeyEvent.VK_CONTROL); // нажали Ctrl
                robot.keyPress(KeyEvent.VK_V); // нажали V
                robot.keyRelease(KeyEvent.VK_V); // отпустили V
                robot.keyRelease(KeyEvent.VK_CONTROL); // отпустили Ctrl
                // Enter
                robot.keyPress(KeyEvent.VK_ENTER); // нажали Enter
                robot.keyRelease(KeyEvent.VK_ENTER); // отпустили Enter
            }

        } catch (AWTException | IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @FindBy(className = "dz-filename")
    WebElement dz_filename;
    public FileUploaderPage verifyFileNameInBox(String fileName) {
        shouldHaveText(dz_filename, fileName, 5000);
        return this;
    }
}
