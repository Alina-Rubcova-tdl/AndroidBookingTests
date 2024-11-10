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

public class MainPage {
    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.booking:id/navigation_bar_item_icon_view\"])[4]")
    private RemoteWebElement singInTabButton;

    @AndroidFindBy(accessibility = "Enter your destination")
    private RemoteWebElement destinationFieldButton;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private RemoteWebElement destinationField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"1 room, 2 adults, 0 children\"]")
    private RemoteWebElement roomAndAdultSelectionField;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.booking:id/group_config_apply_button\"]")
    private RemoteWebElement applyAdultsAndRoomsButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Accommodation search box\"]/android.view.View/android.widget.Button")
    private RemoteWebElement searchButton;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.booking:id/navigation_bar_item_icon_view\"])[2]")
    private RemoteWebElement savedTabButton;

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
        this.searchButton = searchButton;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean mainPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(singInTabButton)).isDisplayed();
    }

    public void clickSingInTabButton() {
        singInTabButton.click();
    }

    public void enterDestination(String destination){
        destinationFieldButton.click();
        destinationField.sendKeys(destination);
        By destinationOption = By.xpath("//android.widget.TextView[@text='" + destination + "']");
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(destinationOption))
                .click();
    }

    public void selectRoomsAndAdults(String roomsCount, String adultsCount) {
        roomAndAdultSelectionField.click();

        By roomsLocator = By.xpath("(//android.widget.TextView[@resource-id=\"com.booking:id/bui_input_stepper_value\"])[1]");
        By increaseRoomsButton = By.xpath("(//android.widget.Button[@content-desc=\"Increase\"])[1]");

        By adultsLocator = By.xpath("(//android.widget.TextView[@resource-id=\"com.booking:id/bui_input_stepper_value\"])[2]");
        By increaseAdultsButton = By.xpath("(//android.widget.Button[@content-desc=\"Increase\"])[2]");

        while (!isValueIncreased(roomsLocator, roomsCount)) {
            driver.findElement(increaseRoomsButton).click();
        }

        while (!isValueIncreased(adultsLocator, adultsCount)) {
            driver.findElement(increaseAdultsButton).click();
        }

        applyAdultsAndRoomsButton.click();
    }

    private boolean isValueIncreased(By locator, String expectedValue) {
        String currentValue = driver.findElement(locator).getText();
        return currentValue.equals(expectedValue);
    }

    public void searchBooking(){
        searchButton.click();
    }

    public void clickSavedTabButton() {
        savedTabButton.click();
    }
}
