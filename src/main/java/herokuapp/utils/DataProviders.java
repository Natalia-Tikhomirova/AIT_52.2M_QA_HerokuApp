package herokuapp.utils;

import herokuapp.data.UserData;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {UserData.VALID_EMAIL, UserData.VALID_PASSWORD},
                {"tomsmith","SuperSecretPassword!"}
        };
    }

    @DataProvider
    public Object[][] loginNegativeDataProvider() {
        return new Object[][]{
                {UserData.INVALID_EMAIL, UserData.INVALID_PASSWORD},
                {"invalidUser", "invalidPassword"}
        };
    }
}
