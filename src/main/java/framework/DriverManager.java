package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utils.Logger;

public class DriverManager {

    protected static WebDriver driver;
    private ChromeOptions options;
	final private static String CLASS_NAME = "DriverManager";
	private static Logger logger = new Logger(CLASS_NAME);

    protected void startDriver() {
    	logger.debug("Starting driver...");
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
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