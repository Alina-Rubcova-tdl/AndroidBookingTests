package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class SavedTabPage {

    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"My next trip\"])[1]")
    private RemoteWebElement myNextTripText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1 properties']")
    private RemoteWebElement onePropertyText;

    public SavedTabPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean saveTabLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(myNextTripText)).isDisplayed();
    }

    public boolean isPropertySaved() {
        try {
            new WebDriverWait(driver, GlobalVariables.globalTimeout)
                    .until(ExpectedConditions.visibilityOf(onePropertyText));

            return onePropertyText.getText().equals("1 properties");
        } catch (Exception e) {
            return false;
        }
    }

}
