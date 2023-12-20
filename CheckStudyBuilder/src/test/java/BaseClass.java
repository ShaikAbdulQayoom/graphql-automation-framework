import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    protected String sb_token;

    @BeforeClass
    public void setup() throws IOException, FileNotFoundException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("config.properties");
        prop.load(input);
        sb_token = prop.getProperty("sb_token");
    }
}
