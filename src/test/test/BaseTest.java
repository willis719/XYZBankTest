package test.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import test.utils.DriverUtil;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    Logger Log = Logger.getLogger(BaseTest.class);

    static WebDriver driver;



    @BeforeTest
    public void setUp() {
        PropertyConfigurator.configure("/Users/willierose/Desktop/XYZBank/src/test/resources/log4j.properties");
        driver = DriverUtil.getDriver();
        driver.manage().window().maximize();
        Log.info("New driver instantiated");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void beforeSuite() {

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
