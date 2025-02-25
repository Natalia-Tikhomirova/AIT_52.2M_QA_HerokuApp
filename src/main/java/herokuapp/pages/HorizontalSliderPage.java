package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class HorizontalSliderPage extends BasePage {
    public HorizontalSliderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(tagName = "input")
    WebElement sliderContainer1;


    public HorizontalSliderPage moveSliderWithActions(double targetValue) {
        double min = Double.parseDouble(sliderContainer1.getDomAttribute("min"));
        double max = Double.parseDouble(sliderContainer1.getDomAttribute("max"));

        if (targetValue < 0 || targetValue > 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid target value: %.1f. Expected range: [%.1f to %.1f]", targetValue, min, max));
        }

        int sliderWidth = sliderContainer1.getSize().getWidth();
        double range = max - min;
        int offset = (int) ((targetValue - min) / range * sliderWidth);

        Actions actions = new Actions(driver);
        actions.moveToElement(sliderContainer1, 0, 0).click().perform();
        actions.clickAndHold(sliderContainer1).moveByOffset(offset, 0).release().perform();

        System.out.println("Min value: " + min);
        System.out.println("Max value: " + max);
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
        double min = Double.parseDouble(sliderContainer1.getDomAttribute("min"));
        double max = Double.parseDouble(sliderContainer1.getDomAttribute("max"));

        if (targetValue < min || targetValue > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid target value: %.1f. Expected range: [%.1f to %.1f]", targetValue, min, max));
        }

        try {
            Robot robot = new Robot();
            double currentValue = Double.parseDouble(sliderContainer1.getAttribute("value"));
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
                currentValue = Double.parseDouble(sliderContainer1.getAttribute("value"));
            }
        } catch (AWTException | InterruptedException e) {
            throw new RuntimeException("Error controlling slider with Robot", e);
        }
        return this;
    }

    @FindBy(xpath = " //input[@type='range']")
    private WebElement sliderContainer;

    @FindBy(xpath = "//span[@id='range']") // Локатор текстового значення
    private WebElement sliderValue;

    public HorizontalSliderPage moveSlider(Float targetValue) {
        System.out.println("Target value to move slider: [" + targetValue + "]");

        float min = Float.parseFloat(Objects.requireNonNull(sliderContainer1.getDomAttribute("min"))); // 0
        float max = Float.parseFloat(Objects.requireNonNull(sliderContainer1.getDomProperty("max")));  // 5
        float step = Float.parseFloat(Objects.requireNonNull(sliderContainer1.getDomProperty("step"))); // 0.5
        float currentValue = 0.0F; // Текущее значение слайдера
        float finalValue = 0.0F; // Финальное значение слайдера
        int steps = 0; // Сколько шагов делать


        //Проверка на диапазон
        if (targetValue == null || targetValue < min || targetValue > max) {
            throw new IllegalArgumentException(
                    String.format("❌ Invalid target value: %.1f. Expected range: [%.1f to %.1f]", targetValue, min, max)
            );
        }

        // Проверка на кратность 0,5
        // Если остаток от деления не равен 0, то считается кратным 0,5
        if (targetValue % step != 0) {
            throw new IllegalArgumentException(
                    String.format("❌ Invalid target value: %.1f. Value must be a multiple of 0.5 ", targetValue)
            );
        }

        // Если значения равны - идём дальше ничего не предпринимая
        if (currentValue == targetValue) {
            return this;
        }
        System.out.println("Min value: [" + min +" ]");
        System.out.println("Max value: [" + max + "]");
// Текущее значение слайдера DO клика по нему
        currentValue = Float.parseFloat(sliderValue.getText().trim());
        System.out.println("Current value before click: ["+currentValue +"]");

        click(sliderContainer);
// Текущее значение слайдера ПОСЛЕ клика по нему
        currentValue = Float.parseFloat(sliderValue.getText().trim());
        System.out.println("Current value after click: ["+currentValue +"]");
// Вычисляем количество шагов
        steps = Math.round((targetValue-currentValue)/step);
        System.out.println("Steps to move slider: [" + steps + "], steps " + ((steps<0 ? "left" : "right")));

        // Двигаем слайдер вправо/влево стрелками клавиатуры
        try {
            Robot robot = new Robot();
            int key = steps > 0 ? KeyEvent.VK_RIGHT : KeyEvent.VK_LEFT;
            for (int i = 0; i < Math.abs(steps); i++) {
                robot.keyPress(key);
                robot.keyRelease(key);
                robot.delay(500);
            }
//            wait.until(ExpectedConditions.attributeToBe(sliderValue, "value",
//                    String.valueOf(targetValue)));
            pause(1000);
            finalValue = Float.parseFloat(sliderValue.getText().trim());
            System.out.println("Final value: [" + finalValue + "]");
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public HorizontalSliderPage verifySliderValue(Float setSlider) {
        shouldHaveText(sliderValue, String.valueOf(setSlider), 5000);
        return this;
    }
}
