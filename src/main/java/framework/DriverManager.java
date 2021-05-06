package framework;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import utils.Logger;

public class DriverManager {

	protected static WebDriver driver = null;
	public static AppiumDriver androidDriver = null;
	private ChromeOptions options;
	final private static String CLASS_NAME = "DriverManager";
	private static Logger logger = new Logger(CLASS_NAME);
	private DesiredCapabilities capabilities;
	private static String driverToExecute = null;
	private static Configuration configuration = new Configuration();

	public static void setUp() {
		String path = "testResources/framework.properties";
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static AndroidDriver getAndroidDriver() {
		if (driver instanceof AndroidDriver)
			return (AndroidDriver) driver;
		else
			return null;
	}

	protected void startDriver() throws MalformedURLException {
		driverToExecute = configuration.getGlobal().DRIVER;

		switch (driverToExecute.toLowerCase()) {
		case "chrome":
			logger.debug("Starting chrome driver...");
			String sSistemaOperativo = System.getProperty("os.name");
			if (sSistemaOperativo.contains("Linux"))
				System.setProperty("webdriver.chrome.driver", "/var/lib/drivers/chromedriver");
			else
				System.setProperty("webdriver.chrome.driver", configuration.getGlobal().DRIVERPATH);
			options = new ChromeOptions();
//			options.setHeadless(true);
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
//			driver.manage().window().maximize();
			break;
		case "android":
			capabilities = new DesiredCapabilities();
			loadCapabilities(capabilities, true);
			System.out.println(capabilities.toString());
			int i = 0;
			boolean worked = false;
			driver= (AppiumDriver) new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			while (i < 3 && !worked) {
				try {
					driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
					System.out.println("Context: " + getAndroidDriver().getContext());
					worked = true;
				} catch (Exception e) {
					System.out.println("Trying launch driver again... (" + (i + 1) + "/3)");
					if (i==2) {
						throw new Error(e);
					}
					i++;
				}
			}

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

		if (configuration.getGlobal().APP_PATH != null && configuration.getGlobal().APP_PATH != "") {
			capabilities.setCapability("app",
					System.getProperty("user.dir").replace("\\", "/") + "/" + configuration.getGlobal().APP_PATH);
		}
		if (configuration.getGlobal().APP_PACKAGE != null && isAndroid)
			capabilities.setCapability("appPackage", configuration.getGlobal().APP_PACKAGE);
		if (configuration.getGlobal().APP_ACTIVITY != null && isAndroid)
			capabilities.setCapability("appActivity", configuration.getGlobal().APP_ACTIVITY);
//		if (configuration.getGlobal().APP_ACTIVITY != null && !isAndroid)
//			capabilities.setCapability("bundleId", configuration.getGlobal().APP_ACTIVITY);
//		if (configuration.getGlobal().BROWSER != null) {
//			String browser = configuration.getGlobal().BROWSER;
//			if (browser.equalsIgnoreCase("chrome")) {
//				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
//				logger.info("Loading Chrome browser for " + (isAndroid ? "Android" : "iOS") + " device");
//			} else if (browser.equalsIgnoreCase("chromium")) {
//				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROMIUM);
//				logger.info("Loading Chromium browser for " + (isAndroid ? "Android" : "iOS") + " device");
//			} else if (browser.equalsIgnoreCase("safari") && !isAndroid) {
//				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
//				logger.info("Loading Safari browser for iOS device");
//			} else if (browser.equalsIgnoreCase("browser") && isAndroid) {
//				logger.warning(
//						"Using AOSP browser (native Android browser). This browser is unmaintained by Google and it may only works on old Android devices. Use it carefully.");
//				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.BROWSER);
//				logger.info("Loading AOSP browser for Android device");
//			} else
//				logger.error(
//						"Unrecognized browser '" + browser + "' for " + (isAndroid ? "Android" : "iOS") + " device");
//		}
		if (configuration.getGlobal().PLATFORM_NAME != null)
			capabilities.setCapability("platformName", configuration.getGlobal().PLATFORM_NAME);
//		if (configuration.getGlobal().AUTOMATION_NAME != null && isAndroid)
//			capabilities.setCapability("automationName", configuration.getGlobal().AUTOMATION_NAME);
		if (configuration.getGlobal().PLATFORM_VERSION != null)
			capabilities.setCapability("platformVersion", configuration.getGlobal().PLATFORM_VERSION);
		if (configuration.getGlobal().DEVICE_NAME != null) {
			capabilities.setCapability("deviceName", configuration.getGlobal().DEVICE_NAME);
			capabilities.setCapability("avd", configuration.getGlobal().DEVICE_NAME);
		}
		if (configuration.getGlobal().DEVICE_UDID != null)
			capabilities.setCapability("udid", configuration.getGlobal().DEVICE_UDID);
		if (configuration.getGlobal().FULLRESET != null)
			capabilities.setCapability("fullReset", configuration.getGlobal().FULLRESET);
		else if (configuration.getGlobal().NORESET != null)
			capabilities.setCapability("noReset", configuration.getGlobal().NORESET);

//		capabilities.setCapability("bootstrapPath",
//				"/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent");
//		capabilities.setCapability("agentPath",
//				"/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj");
		String extension = ".exe";
		String chromedriverpath = configuration.getGlobal().DRIVERPATH;

		if (System.getProperty("os.name").contains("Windows"))
			chromedriverpath = chromedriverpath + ".exe";

		if (configuration.getGlobal().DRIVERPATH != null) {
			capabilities.setCapability("chromedriverExecutable",
					System.getProperty("user.dir").replace("\\", "/") + "/" + chromedriverpath);
		}
		capabilities.setCapability("newCommandTimeout", 0);
		
	}

	protected void close() {
		logger.debug("Closing browser...");
		driver.close();
	}

	protected void quit() {
		logger.debug("Tear down driver...");
		driver.quit();
	}

	protected void closeAppium() {
		logger.debug("Closing device...");
		driver.close();
	}

	protected void quitAppium() {
		logger.debug("Tear down driver...");
		driver.quit();
	}

	public static String getAppPath() {
		return System.getProperty("user.dir").replace("\\", "/") + "/" + configuration.getGlobal().APP_PATH;
	}

}