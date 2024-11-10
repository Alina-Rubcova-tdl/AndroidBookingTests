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
import util.Helpers;

public class SelectDatesPage {
    protected AndroidDriver driver;
    private Helpers helpers;

    @AndroidFindBy(id = "com.booking:id/facet_date_picker_title")
    private RemoteWebElement selectDatesTitle;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.booking:id/facet_date_picker_confirm\"]")
    private RemoteWebElement selectDatesButton;


    public SelectDatesPage(AndroidDriver driver) {
        this.driver = driver;
        this.helpers = new Helpers();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean selectDatesPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(selectDatesTitle)).isDisplayed();
    }

    public void selectDateRange(String dateRange) {
        helpers.swipe(driver, 678, 2507, 683, 1644);

        String[] dates = dateRange.split("-");
        String startDate = helpers.formatDate(dates[0]);
        String endDate = helpers.formatDate(dates[1]);

        By startDateLocator = By.xpath("//android.view.View[@content-desc='" + startDate + " December 2024']");
        By endDateLocator = By.xpath("//android.view.View[@content-desc='" + endDate + " December 2024']");

        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(startDateLocator));
        driver.findElement(startDateLocator).click();

        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(endDateLocator));
        driver.findElement(endDateLocator).click();

        selectDatesButton.click();
    }

}
