package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.HoversPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class HoversTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getHoversPage();
    }

    @Test
    public void hoversPositiveTest() {
        new HoversPage(app.driver, app.wait)
                .hoverOverUser(0)
                .veryfyUserName("name: user1");
    }
}
