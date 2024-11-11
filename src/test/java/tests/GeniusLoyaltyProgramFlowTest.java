package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;

public class GeniusLoyaltyProgramFlowTest extends DriverSetup {

    @Test(testName = "User login flow and genius level navigation test")
    public void geniusLoyaltyProgramFlowTest() {
        Assert.assertTrue(cookiesAndNotificationsPage.cookiesPageLoaded(), "Cookies page is not loaded");
        cookiesAndNotificationsPage.clickAcceptCookiesButton();

        Assert.assertTrue(cookiesAndNotificationsPage.notificationsPageLoaded(), "Notifications page is not loaded");
        cookiesAndNotificationsPage.clickNotificationsCloseButton();

        Assert.assertTrue(introSingInPage.singInPageLoaded(), "SingIn page is not loaded");
        introSingInPage.clickCloseSingInPage();

        Assert.assertTrue(mainPage.mainPageLoaded(), "Main page is not loaded");

        mainPage.clickSingInTabButton();
        Assert.assertTrue(signInTabPage.signInTabLoaded(), "Sign In tab is not loaded");

        Assert.assertTrue(signInTabPage.validateUserIsNotLoggedIn(), "User is logged in");

        signInTabPage.clickGeniusLoyaltyProgramButton();
        Assert.assertTrue(geniusLoyaltyProgramPage.geniusLoyaltyProgramPageLoaded(), "Genius Loyalty Program Page NOT Loaded");

        geniusLoyaltyProgramPage.scrollToAndClickAboutGeniusLevels();
        Assert.assertTrue(aboutGeniusLoyaltyLvlPage.AboutGeniusLoyaltyLvlPageLoaded(), "About Genius Loyalty Lvl Page is NOT Loaded");

        Assert.assertTrue(aboutGeniusLoyaltyLvlPage.swipeRightToGeniusLevel3AndValidate(), "Genius Level 3 is NOT displayed.");
        aboutGeniusLoyaltyLvlPage.clickGotItButton();
        aboutGeniusLoyaltyLvlPage.clickBackButton();
    }

}
