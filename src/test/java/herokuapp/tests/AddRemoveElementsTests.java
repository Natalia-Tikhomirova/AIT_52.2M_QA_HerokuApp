package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.AddRemoveElementsPage;
import herokuapp.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddRemoveElementsTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getAddRemoveElementsPage();
    }

    @Test
    public void AddRemoveElementsPositiveTest() {
        new AddRemoveElementsPage(app.driver, app.wait)
                .clickOnAddElementButton()
                .verifyDeleteButtonText("Delete")
                .clickOnDeleteButton();
    }
}
