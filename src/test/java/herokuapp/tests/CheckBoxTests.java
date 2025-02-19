package herokuapp.tests;

import herokuapp.core.TestBase;
import herokuapp.pages.CheckBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBoxTests extends TestBase {

    private static final String HOME_PAGE_URL = "https://the-internet.herokuapp.com/" ;

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/checkboxes");
    }

    @Test
    public void printCheckboxPositiveTest(){
        new CheckBoxPage(app.driver,app.wait)
               .printCheckBox();
    }

    @Test
    public void  selectCheckboxByTextPositiveTest(){
        String checkBoxName = "checkbox 1";
        new CheckBoxPage(app.driver,app.wait)
                .selectCheckboxByText(checkBoxName)
                .verifyCheckbox(checkBoxName)
        ;
    }

    @Test
    public void  selectCheckboxByTextWithActionsPositiveTest(){
        String checkBoxName = "checkbox 1";
        new CheckBoxPage(app.driver,app.wait)
                .selectCheckboxByTextWithActions(checkBoxName)
                .verifyCheckbox(checkBoxName)
        ;
    }

    @Test
    public void  selectCheckboxByTextWithRobotPositiveTest(){
        String checkBoxName = "checkbox 1";
        new CheckBoxPage(app.driver,app.wait)
                .selectCheckboxByTextWithRobot(checkBoxName)
                .verifyCheckbox(checkBoxName)
        ;
    }
}
