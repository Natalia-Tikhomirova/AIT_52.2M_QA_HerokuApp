package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.KeyPressPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeyPressTests extends TestBase {


    final static String HOME_PAGE_URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/key_presses");
    }

    @Test
    public void keyPressPositiveTest(){
        String letter = "a";
        new KeyPressPage(app.driver,app.wait)
                .pressKey(letter)
                .verifyPressedKey(letter)
        ;
    }
}
