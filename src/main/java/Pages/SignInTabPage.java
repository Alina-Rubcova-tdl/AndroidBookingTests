package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class SignInTabPage {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/facet_profile_header_sign_in_cta")
    private RemoteWebElement signInButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Genius loyalty program\"]")
    private RemoteWebElement geniusLoyaltyProgramButton;

    public SignInTabPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean signInTabLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(geniusLoyaltyProgramButton)).isDisplayed();
    }

    public boolean validateUserIsNotLoggedIn() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed();
    }

    public void clickGeniusLoyaltyProgramButton() {
        geniusLoyaltyProgramButton.click();
    }
}
