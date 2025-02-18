package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.NewWindowPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultipleWindowsPageTest extends TestBase {

    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getMultipleWindowsPage();
    }

    @Test
    public void multipleWindowsPositiveTest() {
        new NewWindowPage(app.driver, app.wait)
                .openNewWindow()
                .switchToNewWindow()
                .verifyNewWindowTitle("New Window")
        ;
    }
}
