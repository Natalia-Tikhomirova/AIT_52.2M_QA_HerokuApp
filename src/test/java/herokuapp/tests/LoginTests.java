package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.data.UserData;
import herokuapp.pages.HomePage;
import herokuapp.pages.LoginPage;
import herokuapp.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getFormAuthenticationPage();

    }

    @Test
    public void loginExistedUserPositiveTest() {
        String successMessage  = new LoginPage(app.driver, app.wait)
                .enterPersonalData(UserData.VALID_EMAIL, UserData.VALID_PASSWORD)
                .clickLoginButton()
                .getSuccessMessage()  // сообщение об успешном входе
        ;
        Assert.assertTrue(successMessage.contains("You logged into a secure area!"));
   }

//    @Test
//    public void loginExistedUserNegativeTest() {
//        Assert.fail("Принудительный провал, чтобы проверить скриншот!");
//    }


   @Test(dataProvider = "loginDataProvider",dataProviderClass = DataProviders.class)
    public void loginExistedUserDataProviderPositiveTest(String email, String password) {
        String successMessage  = new LoginPage(app.driver, app.wait)
                .enterPersonalData(email, password)
                .clickLoginButton()
                .getSuccessMessage()  // сообщение об успешном входе
                ;

        Assert.assertTrue(successMessage.contains("You logged into a secure area!"));
    }

   @Test
   @Parameters({"invalidUser","invalidPassword"})
    public void loginNegativeTest(){
        String errorMessage = new LoginPage(app.driver, app.wait)
                .enterPersonalData(UserData.INVALID_EMAIL,UserData.INVALID_PASSWORD)
                .clickLoginButton()
                .getErrorMessage()
        ;

        Assert.assertTrue(errorMessage.contains("Your username is invalid!"));
   }

   @Test(dataProvider = "loginNegativeDataProvider",dataProviderClass = DataProviders.class)
   public void loginNegativeDataProviderTest(String invalidEmail, String invalidPassword){
        String errorMessage = new LoginPage(app.driver, app.wait)
                .enterPersonalData(invalidEmail,invalidPassword)
                .clickLoginButton()
                .getErrorMessage()
                ;

        Assert.assertTrue(errorMessage.contains("Your username is invalid!"));
    }

//    @Test
//    public void loginNegativeWOFieldTest(){
//        String errorMessage = new LoginPage(app.driver, app.wait)
//                .enterPersonalData(" "," ")
//                .clickLoginButton()
//                .getErrorMessage()
//                ;
//
//        Assert.assertTrue(errorMessage.contains("Your username is invalid!"));
//    }
}
