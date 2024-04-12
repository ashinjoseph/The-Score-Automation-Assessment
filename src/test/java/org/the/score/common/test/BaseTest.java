package org.the.score.common.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.the.score.common.utils.FilesUtility;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public BaseTest() {
        super();
    }


    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected String Execution_Environment;

    protected AndroidDriver driver;

    public BaseTest(AndroidDriver driver) {
        this.driver = driver;
    }
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        FilesUtility.init();
    }

    @BeforeTest(alwaysRun = true)
    public void setUp(ITestContext context) throws MalformedURLException {
        final String APP =  new File(Thread.currentThread().getContextClassLoader().getResource(FilesUtility.getAutomationProperties("app.name")).getFile()).getPath();
        Execution_Environment = context.getCurrentXmlTest().getParameter("execution-env");
        Execution_Environment = Execution_Environment != null ? Execution_Environment : "default";
        DesiredCapabilities caps;
        URL url;

        switch (Execution_Environment) {
            case "sauce-lab":
                url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                caps = new DesiredCapabilities();
                caps.setCapability("platformName", "Android");
                caps.setCapability("appium:app", FilesUtility.getAutomationProperties("app.name"));
                caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
                caps.setCapability("appium:platformVersion", "12.0");
                caps.setCapability("appium:automationName", "UiAutomator2");
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", "oauth-ashinjoseph.joseph-e773a");
                sauceOptions.setCapability("accessKey", "23075c50-48d5-4839-a235-90d7a8b77e3b");
                sauceOptions.setCapability("build", "appium-build-GW13D");
                sauceOptions.setCapability("name", "<your test name>");
                sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
                caps.setCapability("sauce:options", sauceOptions);
                break;
            default:
                url = new URL(FilesUtility.getAutomationProperties("emulator.base.uri"));
                caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(MobileCapabilityType.APP, APP);
                break;
        }
        driver = new AndroidDriver<>(url, caps);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
