package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.DragAndDropPage;
import herokuapp.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropTests extends TestBase {

    final static String HOME_PAGE_URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/drag_and_drop");
    }

    @Test
    public void dragAndDropPositiveTest(){
        new DragAndDropPage(app.driver, app.wait)
                .actionDragMe()
                .verifyText("a")
        ;
    }
}
