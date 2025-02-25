package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.FileUploaderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.tests.DragAndDropTests.HOME_PAGE_URL;

public class FileUploaderTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/upload");
    }

    @Test
    public void fileUploaderPositiveTest() {
        new FileUploaderPage(app.driver, app.wait)
                .chooseFileByChooseFileButton("C:\\AIT_TR_QA\\HerokuApp\\Лось.png")
                .clickOnUploadButton()
                .verifyFileName("Лось.png")
        ;
    }

    @Test
    public void  fileUploaderInBoxPositiveTest(){
        new FileUploaderPage(app.driver,app.wait)
                .chooseFileInBox("C:\\AIT_TR_QA\\HerokuApp\\Лось.png")
                .verifyFileNameInBox("Лось.png")
        ;
    }

}