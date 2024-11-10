package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class SearchPage {

    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Save property to list\"])[1]")
    private RemoteWebElement heartButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.booking:id/snackbar_text\"]")
    private RemoteWebElement heartConfirmationPopUp;

    @AndroidFindBy(accessibility = "Navigate up")
    private RemoteWebElement backToMainPageButton;

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void validateDestinationAndDate(String expectedDestination, String expectedDateRange) {
        By destinationLocator = By.id("com.booking:id/searchbox_destination");
        By dateLocator = By.id("com.booking:id/searchbox_dates");

        try {
            new WebDriverWait(driver, GlobalVariables.globalTimeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(destinationLocator));
            new WebDriverWait(driver, GlobalVariables.globalTimeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(dateLocator));

            String actualDestination = driver.findElement(destinationLocator).getText();
            String actualDateRange = driver.findElement(dateLocator).getText();

            if (!actualDestination.equals(expectedDestination)) {
                throw new AssertionError("Destination is incorrect. Expected: " + expectedDestination + " but found: " + actualDestination);
            }

            String expectedFormattedDateRange = formatExpectedDateRange(expectedDateRange);

            if (!actualDateRange.equals(expectedFormattedDateRange)) {
                throw new AssertionError("Date range is incorrect. Expected: " + expectedFormattedDateRange + " but found: " + actualDateRange);
            }

        } catch (Exception e) {
            throw new AssertionError("Error occurred during destination and date validation: " + e.getMessage());
        }
    }

    private String formatExpectedDateRange(String dateRange) {
        String[] dateParts = dateRange.split("-");
        return "Dec " + dateParts[0] + " - Dec " + dateParts[1];
    }

    public void clickHeartButton() {
        try {
            new WebDriverWait(driver, GlobalVariables.globalTimeout)
                    .until(ExpectedConditions.elementToBeClickable(heartButton));
            heartButton.click();
        } catch (Exception e) {
            throw new AssertionError("Failed to click on heart button: " + e.getMessage());
        }
    }

    public boolean confirmTripWasSaved() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(heartConfirmationPopUp)).isDisplayed();
    }

    public void clickBackToMaiPage() {
        backToMainPageButton.click();
    }
}
