package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.HorizontalSliderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HorizontalSliderTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getHorizontalSliderPage();

    }

    @Test
    public void horizontalSliderPositiveTestWithActions() {
        double setSlider = 0.0;
        new HorizontalSliderPage(app.driver, app.wait)
                .moveSliderWithActions(setSlider)
                .verifySliderValue(setSlider);
    }

    @Test
    public void horizontalSliderPositiveTestWithRobot() {
        double setSlider = 5.0;
        new HorizontalSliderPage(app.driver, app.wait)
                .moveSliderWithRobot(setSlider)
                .verifySliderValue(setSlider);
    }
}