package herokuapp.tests;


import herokuapp.core.TestBase;
import herokuapp.pages.DisappearingElementsPage;
import herokuapp.utils.RetryAnalyser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.tests.DragAndDropTests.HOME_PAGE_URL;

public class DisappearingElementsTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/disappearing_elements");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)   // перезапуск тестов
    public void disappearingElementsTestsPositiveTest() {
        new DisappearingElementsPage(app.driver, app.wait)
                .checkDisappearingElement("Gallery");
    }

    @Test
    public void checkStyleButtonTest() {
        new DisappearingElementsPage(app.driver, app.wait)
                .checkDisappearingElement("Gallery");
    }

    // "HOME", #DA4B4B, 18px, #ececec
    // "HOME", #000, 20px, #f2f2f2
    @Test
    public void  checkStyleButtonPositiveTest(){
        String button = "Home";
        new DisappearingElementsPage(app.driver, app.wait)
                .checkStyleButton(button, "#DA4B4B", "18px", "#ECECEC")
                .hoverMouseOnButton(button)
                .checkStyleButton(button, "#000000", "20px", "#f2f2f2")
        ;
    }

}

