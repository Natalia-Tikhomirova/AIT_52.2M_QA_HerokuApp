package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HorizontalSliderPage extends BasePage {
    public HorizontalSliderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(tagName = "input")
    WebElement sliderContainer;
    double min = Double.parseDouble(sliderContainer.getDomAttribute("min"));
    double max = Double.parseDouble(sliderContainer.getDomAttribute("max"));

    public HorizontalSliderPage moveSliderWithActions(double targetValue) {
        if (targetValue < 0 || targetValue > 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid target value: %.1f. Expected range: [%.1f to %.1f]", targetValue, min, max));
        }

        int sliderWidth = sliderContainer.getSize().getWidth();
        double range = max - min;
        int offset = (int) ((targetValue - min) / range * sliderWidth);

        Actions actions = new Actions(driver);
        actions.moveToElement(sliderContainer, 0, 0).click().perform();
        actions.clickAndHold(sliderContainer).moveByOffset(offset, 0).release().perform();

        return this;
    }

    @FindBy(id = "range")
    WebElement valueDisplay;

    public HorizontalSliderPage verifySliderValue(double expectedValue) {
        String actualValue = valueDisplay.getText();
        if (!actualValue.equals(String.valueOf(expectedValue))) {
            throw new AssertionError(
                    String.format("Expected slider value: %.1f but got: %s", expectedValue, actualValue));
        }

        return this;
    }

    public HorizontalSliderPage moveSliderWithRobot(double targetValue) {
        double min = Double.parseDouble(sliderContainer.getDomAttribute("min"));
        double max = Double.parseDouble(sliderContainer.getDomAttribute("max"));

        if (targetValue < min || targetValue > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid target value: %.1f. Expected range: [%.1f to %.1f]", targetValue, min, max));
        }

        try {
            Robot robot = new Robot();
            double currentValue = Double.parseDouble(sliderContainer.getAttribute("value"));
            boolean moveRight = currentValue < targetValue;

            while (currentValue != targetValue) {
                if (moveRight) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                } else {
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                }
                Thread.sleep(100);
                currentValue = Double.parseDouble(sliderContainer.getAttribute("value"));
            }
        } catch (AWTException | InterruptedException e) {
            throw new RuntimeException("Error controlling slider with Robot", e);
        }
        return this;
    }
}
