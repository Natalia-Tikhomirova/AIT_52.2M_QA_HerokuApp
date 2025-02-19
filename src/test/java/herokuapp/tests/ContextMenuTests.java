package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.ContextMenuPage;
import herokuapp.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenuTests extends TestBase {

    private static final String HOME_PAGE_URL = "https://the-internet.herokuapp.com/" ;

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/context_menu");
    }

    @Test
    public void rightClickOnBoxAndAlertPositiveTest(){
        new ContextMenuPage(app.driver,app.wait)
                .rightClickOnBox()
                .verifyAlertText("You selected a context menu")
        ;
    }
}
