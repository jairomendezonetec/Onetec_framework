package framework;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.Logger;

public class DriverManager {

	protected static WebDriver driver;
	private ChromeOptions options;
	final private static String CLASS_NAME = "DriverManager";
	private static Logger logger = new Logger(CLASS_NAME);
	private DesiredCapabilities capabilities;
	private static String driverToExecute = null;
	private static Configuration configuration = new Configuration();

	public static void loadConfiguration(String browser) {
//		driverToExecute = configuration.getGlobal().DRIVER;
		driverToExecute = browser;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	protected void startDriver() throws MalformedURLException {

		switch (driverToExecute.toLowerCase()) {
		case "chrome":
			logger.debug("Starting chrome driver...");
			options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			break;
		case "android":
			capabilities = new DesiredCapabilities();
			loadCapabilities(capabilities, true);
			driver = new AndroidDriver(new URL(
					"http://" + configuration.getGlobal().ADDRESS + ":" + configuration.getGlobal().PORT + "/wd/hub"),
					capabilities);
			break;
		case "ios":
			capabilities = new DesiredCapabilities();
			loadCapabilities(capabilities, true);
			driver = new IOSDriver(new URL(
					"http://" + configuration.getGlobal().ADDRESS + ":" + configuration.getGlobal().PORT + "/wd/hub"),
					capabilities);
			break;
		default:
			throw new Error("driver is not found");
		}

	}

	public void loadCapabilities(DesiredCapabilities capabilities, boolean isAndroid) {
		if (configuration.getGlobal().APP_PATH != null && configuration.getGlobal().APP_PATH != "")
			capabilities.setCapability("app", configuration.getGlobal().APP_PATH);
		if (configuration.getGlobal().APP_PACKAGE != null && isAndroid)
			capabilities.setCapability("appPackage", configuration.getGlobal().APP_PACKAGE);
		if (configuration.getGlobal().APP_ACTIVITY != null && isAndroid)
			capabilities.setCapability("appActivity", configuration.getGlobal().APP_ACTIVITY);
		if (configuration.getGlobal().APP_ACTIVITY != null && !isAndroid)
			capabilities.setCapability("bundleId", configuration.getGlobal().APP_ACTIVITY);
		if (configuration.getGlobal().BROWSER != null) {
			String browser = configuration.getGlobal().BROWSER;
			if (browser.equalsIgnoreCase("chrome")) {
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
				logger.info("Loading Chrome browser for " + (isAndroid ? "Android" : "iOS") + " device");
			} else if (browser.equalsIgnoreCase("chromium")) {
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROMIUM);
				logger.info("Loading Chromium browser for " + (isAndroid ? "Android" : "iOS") + " device");
			} else if (browser.equalsIgnoreCase("safari") && !isAndroid) {
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
				logger.info("Loading Safari browser for iOS device");
			} else if (browser.equalsIgnoreCase("browser") && isAndroid) {
				logger.warning(
						"Using AOSP browser (native Android browser). This browser is unmaintained by Google and it may only works on old Android devices. Use it carefully.");
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.BROWSER);
				logger.info("Loading AOSP browser for Android device");
			} else
				logger.error(
						"Unrecognized browser '" + browser + "' for " + (isAndroid ? "Android" : "iOS") + " device");
		}
		if (configuration.getGlobal().PLATFORM_NAME != null)
			capabilities.setCapability("platformName", configuration.getGlobal().PLATFORM_NAME);
		if (configuration.getGlobal().AUTOMATION_NAME != null && isAndroid)
			capabilities.setCapability("automationName", configuration.getGlobal().AUTOMATION_NAME);
		if (configuration.getGlobal().PLATFORM_VERSION != null)
			capabilities.setCapability("platformVersion", configuration.getGlobal().PLATFORM_VERSION);
		if (configuration.getGlobal().DEVICE_NAME != null)
			capabilities.setCapability("deviceName", configuration.getGlobal().DEVICE_NAME);
		if (configuration.getGlobal().DEVICE_UDID != null)
			capabilities.setCapability("udid", configuration.getGlobal().DEVICE_UDID);
		capabilities.setCapability("bootstrapPath",
				"/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent");
		capabilities.setCapability("agentPath",
				"/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj");
	}

	protected void close() {
		logger.debug("Closing browser...");
		driver.close();
	}

	protected void quit() {
		logger.debug("Tear down driver...");
		driver.quit();
	}

}