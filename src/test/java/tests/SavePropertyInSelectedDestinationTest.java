package tests;

import data.DateValuesTestData;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SavePropertyInSelectedDestinationTest extends DateValuesTestData {

    @Test(testName = "Search and Save Property with Date Validation test", dataProvider = "date_values")
    public void validateSavedProperty(String DateValues) throws InterruptedException {

        Assert.assertTrue(cookiesAndNotificationsPage.cookiesPageLoaded(), "Cookies page is not loaded");
        cookiesAndNotificationsPage.clickAcceptCookiesButton();

        Assert.assertTrue(cookiesAndNotificationsPage.notificationsPageLoaded(), "Notifications page is not loaded");
        cookiesAndNotificationsPage.clickNotificationsCloseButton();

        Assert.assertTrue(introSingInPage.singInPageLoaded(), "SingIn page is not loaded");
        introSingInPage.clickCloseSingInPage();

        Assert.assertTrue(mainPage.mainPageLoaded(), "Main page is not loaded");

        mainPage.enterDestination("Skopje");
        Assert.assertTrue(selectDatesPage.selectDatesPageLoaded());

        selectDatesPage.selectDateRange(DateValues);
        mainPage.selectRoomsAndAdults("2", "3");

        mainPage.searchBooking();

        searchPage.validateDestinationAndDate("Skopje", DateValues);

        searchPage.clickHeartButton();
        Assert.assertTrue(searchPage.confirmTripWasSaved(), "Trip wasn't saved");

        searchPage.clickBackToMaiPage();
        Assert.assertTrue(mainPage.mainPageLoaded(), "Main page is not loaded");

        mainPage.clickSavedTabButton();

        Assert.assertTrue(saveTabPage.saveTabLoaded(), "Save tab is not loaded");

        Assert.assertTrue(saveTabPage.isPropertySaved(), "Property is not shown in Saved tab.");
    }

}
