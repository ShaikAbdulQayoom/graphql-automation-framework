import org.testng.annotations.DataProvider;

public class DataProviderClass extends CrfByBuild{

    @DataProvider(name = "buildsiteidProvider")
    public static Object[][] provideBuildsiteId() {
        // Fetch the buildsiteid from your shared resource (e.g., a file or database)
        String buildsiteid = ""; // Replace with actual logic to fetch the buildsiteid
        return new Object[][]{{buildsiteid}};
    }
}
