package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;
import util.Helpers;

public class GeniusLoyaltyProgramPage {

    protected AndroidDriver driver;
    private Helpers helpers;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Booking.com's loyalty program\"]")
    private RemoteWebElement loyaltyTex;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"About Genius Levels\"]")
    private RemoteWebElement aboutGeniusLevelsText;

    public GeniusLoyaltyProgramPage(AndroidDriver driver) {
        this.driver = driver;
        this.helpers = new Helpers();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean geniusLoyaltyProgramPageLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(loyaltyTex)).isDisplayed();
    }

    public void scrollToAndClickAboutGeniusLevels() {
        helpers.scrollToElementWithText(driver, "About Genius Levels");
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(aboutGeniusLevelsText))
                .click();
    }

}
