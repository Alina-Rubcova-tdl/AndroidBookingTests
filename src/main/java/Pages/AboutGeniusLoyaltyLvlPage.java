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

public class AboutGeniusLoyaltyLvlPage {
    protected AndroidDriver driver;
    protected Helpers helpers;

    @AndroidFindBy(id = "com.booking:id/genius_levels_title")
    private RemoteWebElement AboutLoyaltyLvlText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.booking:id/genius_level_title\" and @text=\"Genius Level 3\"]")
    private RemoteWebElement geniusLevel3Text;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.booking:id/action_button\"]")
    private RemoteWebElement gotItButton;

    @AndroidFindBy(accessibility = "Navigate up")
    private RemoteWebElement backButton;

    public AboutGeniusLoyaltyLvlPage(AndroidDriver driver) {
        this.driver = driver;
        this.helpers = new Helpers();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean AboutGeniusLoyaltyLvlPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(AboutLoyaltyLvlText)).isDisplayed();
    }

    public boolean swipeRightToGeniusLevel3AndValidate() {
        helpers.swipeRightToElementWithText(driver, "Genius Level 3");
        return new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(geniusLevel3Text)).isDisplayed();
    }

    public void clickGotItButton(){
        gotItButton.click();
    }

    public void clickBackButton(){
        backButton.click();
    }

}
