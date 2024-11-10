package data;

import org.testng.annotations.DataProvider;
import util.DriverSetup;

public class DateValuesTestData extends DriverSetup {

    @DataProvider(name = "date_values")
    public Object[][] DateValues() {
        return new Object[][] {
                {"24-28"},
                {"20-23"},
                {"2-8"}
        };
    }
}
