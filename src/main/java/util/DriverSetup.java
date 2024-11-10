package util;

import Pages.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class DriverSetup extends ConfigReader {

    public static AndroidDriver driver;

    protected Helpers helpers;
    protected RestAssuredUtility restAssuredUtility;

    protected CookiesAndNotificationsPage cookiesAndNotificationsPage;
    protected IntroSingInPage introSingInPage;
    protected MainPage mainPage;
    protected SignInTabPage signInTabPage;
    protected GeniusLoyaltyProgramPage geniusLoyaltyProgramPage;
    protected AboutGeniusLoyaltyLvlPage aboutGeniusLoyaltyLvlPage;
    protected SelectDatesPage selectDatesPage;
    protected SearchPage searchPage;
    protected SavedTabPage saveTabPage;


    @BeforeMethod
    public void setUp() {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setAvd(getProperty("avd"))
                .setApp(getProperty("app"))
                .setAppPackage(getProperty("app.package"))
                .setAppActivity(getProperty("app.activity"))
                .setNoReset(false)
                .setFullReset(true)
                .setCapability("appium:disableIdLocatorAutocompletion", true);

        try {
            driver = new AndroidDriver(new URI(GlobalVariables.localAppiumServerUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        helpers = new Helpers();
        restAssuredUtility = new RestAssuredUtility();

        cookiesAndNotificationsPage = new CookiesAndNotificationsPage(driver);
        introSingInPage = new IntroSingInPage(driver);
        mainPage = new MainPage(driver);
        signInTabPage = new SignInTabPage(driver);
        geniusLoyaltyProgramPage = new GeniusLoyaltyProgramPage(driver);
        aboutGeniusLoyaltyLvlPage = new AboutGeniusLoyaltyLvlPage(driver);
        selectDatesPage = new SelectDatesPage(driver);
        searchPage = new SearchPage(driver);
        saveTabPage = new SavedTabPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
