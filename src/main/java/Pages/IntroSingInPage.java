package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class IntroSingInPage {
    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.booking:id/identity_header_title\"]")
    private RemoteWebElement singInTitle;

    @AndroidFindBy(accessibility = "Navigate up")
    private RemoteWebElement closeSingInPageButton;

    public IntroSingInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean singInPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(singInTitle)).isDisplayed();
    }

    public void clickCloseSingInPage() {
        closeSingInPageButton.click();
    }

}
