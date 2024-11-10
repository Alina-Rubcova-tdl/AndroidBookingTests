package util;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class Helpers {

    public String formatDate(String date) {
        return (date.length() == 1) ? "0" + date : date;
    }

    public void scrollToElementWithText(AndroidDriver driver, String text) {
        boolean elementFound = false;
        int maxScrollAttempts = 10;
        int scrollAttempts = 0;

        while (!elementFound && scrollAttempts < maxScrollAttempts) {
            try {
                if (driver.findElements(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")")).size() > 0) {
                    elementFound = true;
                    break;
                }

                driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList().scrollForward()"));
                scrollAttempts++;

            } catch (Exception e) {
                scrollAttempts++;
                if (scrollAttempts == maxScrollAttempts) {
                    System.out.println("Elements '" + text + "' nav atrasts pēc " + scrollAttempts + " ritināšanas mēģinājumiem.");
                }
            }
        }
    }

    public void swipeRightToElementWithText(AndroidDriver driver, String text) {
        boolean elementFound = false;
        int maxSwipeAttempts = 5;
        int swipeAttempts = 0;

        while (!elementFound && swipeAttempts < maxSwipeAttempts) {
            try {
                WebElement element = driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList()" +
                                ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"));

                elementFound = element.isDisplayed();
            } catch (Exception e) {
                swipeAttempts++;
                if (swipeAttempts == maxSwipeAttempts) {
                    System.out.println("Elements '" + text + "' nav atrasts pēc " + swipeAttempts + " ritināšanas mēģinājumiem.");
                }
            }
        }
    }

    public void swipe(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        var start = new Point(startX, startY);
        var end = new Point(endX, endY);

        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
}
