package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.StatusCodePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.tests.DragAndDropTests.HOME_PAGE_URL;

public class StatusCodeTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/status_codes");
    }

    @Test
    public void statusCodePositiveTest(){
        new StatusCodePage(app.driver, app.wait)
                .checkAllURL()
                .checkBrokenLinks()
        ;
    }
}
