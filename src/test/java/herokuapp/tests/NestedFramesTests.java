package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.NestedFramesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NestedFramesTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getNestedFramesPage();
    }

    @Test
    public void framesPositiveTest() {
        new NestedFramesPage(app.driver, app.wait)
                .getListOfNestedFrames();
    }

    @Test
    public void switchToMiddleFramePositiveTest() {
        new NestedFramesPage(app.driver, app.wait)
                .switchToFrameBySrcAndVerifyName("/frame_top", "frame-top")
                .switchToFrameBySrcAndVerifyName("/frame_middle", "frame-middle")
                .verifyFrameText("MIDDLE", 5000);
    }

    @Test
    public void switchToBottomFramePositiveTest() {
        new NestedFramesPage(app.driver, app.wait)
                .switchToMainPage()
                .switchToFrameBySrcAndVerifyName("/frame_bottom", "frame-bottom")
                .verifyFrameText("BOTTOM", 5000);
    }
}
