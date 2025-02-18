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
        String option = "Option 1";
        new DropdownPage(app.driver, app.wait)
                .selectDropdownList(option)
                .selectDropdownList("Option 2")
                .verifyDropdownList(option)
                ;
    }
    @Test
    public void selectDropdownListPositive2Test() {
        String option = "Option 1";
        new DropdownPage(app.driver, app.wait)
                .selectDropdownList(option)
                .verifyDropdownListHashSet(option)
        ;
    }

    @Test
    public void selectDropdownListNegativeTest() {
        new DropdownPage(app.driver, app.wait)
                .selectInvalidOption("Option 3")
                .verifyDropdownList("Please select an option")
        ;
    }
}
