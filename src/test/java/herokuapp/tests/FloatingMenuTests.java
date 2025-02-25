package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.FloatingMenuPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.tests.DragAndDropTests.HOME_PAGE_URL;

public class FloatingMenuTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/floating_menu");
    }

    @Test
    public void floatingMenuPositiveTest(){
        new FloatingMenuPage(app.driver,app.wait)
                .scrollToEndOfPage()
                .scrollToCenterPage()
                .verifyFloatingMenuIsPresent()
        ;
    }
}
