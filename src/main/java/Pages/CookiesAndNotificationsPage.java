package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class CookiesAndNotificationsPage {

    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.booking:id/gdpr_consent_heading\"]")
    private RemoteWebElement cookiesTitle;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.booking:id/bt_accept\"]")
    private RemoteWebElement cookiesAcceptButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please turn on notifications\"]")
    private RemoteWebElement notificationsTitle;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Navigate up\"]")
    private RemoteWebElement notificationsCloseButton;

    public CookiesAndNotificationsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean cookiesPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(cookiesTitle)).isDisplayed();
    }

    public void clickAcceptCookiesButton() {
        cookiesAcceptButton.click();
    }

    public boolean notificationsPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(notificationsTitle)).isDisplayed();
    }

    public void clickNotificationsCloseButton() {
        notificationsCloseButton.click();
    }
}
