package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.DropdownPage;
import herokuapp.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropdownTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getDropdownPage();
    }

    @Test
    public void selectDropdownListPositiveTest() {
        new DropdownPage(app.driver, app.wait)
                .selectDropdownList("Option 1")
                .selectDropdownList("Option 2")
                ;
    }

    @Test
    public void selectDropdownListNegativeTest() {
        new DropdownPage(app.driver, app.wait)
                .selectInvalidOption("Option 3")
        ;
    }
}
