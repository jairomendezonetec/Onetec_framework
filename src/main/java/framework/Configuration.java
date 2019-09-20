package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

	public Global globals = new Global();
	
	public Global getGlobal(){
		return globals;
	}

	public void loadConfiguration(String configurationPath) throws IOException{
			
			File configFile = new File(configurationPath);
			if (!configFile.exists())
				throw new IOException("File '" + configurationPath + "' doesn't exists.");
			
			if (configFile.isDirectory())
				throw new IOException(configurationPath+" is a directory.");
			
			if (!configFile.isFile())
				throw new IOException(configurationPath+" is not a file.");
			
			Properties properties = new Properties();
			properties.load(new FileInputStream(configurationPath));
			
			globals.APP_PATH = properties.getProperty("app_path");
			globals.APP_ACTIVITY = properties.getProperty("app_activity");
			globals.APP_PACKAGE = properties.getProperty("app_package");
			
			globals.ADDRESS = properties.getProperty("address");
			globals.PORT = properties.getProperty("port");
			globals.DRIVER = properties.getProperty("driver");
			globals.URL = properties.getProperty("url");
			globals.NODE_PATH = properties.getProperty("node_path");
			globals.MAIN_PATH = properties.getProperty("main_path");
			globals.AUTOMATION_NAME = properties.getProperty("automation_name");
			globals.DEVICE_NAME = properties.getProperty("device_name");
			globals.DEVICE_UDID = properties.getProperty("device_udid");
			globals.PLATFORM_NAME = properties.getProperty("platform_name");
			globals.PLATFORM_VERSION = properties.getProperty("platform_version");
			globals.BROWSER = properties.getProperty("browser");
			globals.LANGUAGE = properties.getProperty("language");
			globals.LOCALE = properties.getProperty("locale");
			globals.SDK_PATH = properties.getProperty("sdk_path");
			globals.COVERAGE_CLASS = properties.getProperty("coverage_class");
			globals.CHROMEDRIVER_PORT = properties.getProperty("chromedriver_port");
			globals.BOOTSTRAP_PORT = properties.getProperty("bootstrap_port");
			globals.SELENDROID_PORT = properties.getProperty("selendroid_port");
			globals.DRIVERPATH = properties.getProperty("driver_path");
		}
	
	
	public class Global{
		// General
		public String ADDRESS = null;
		public String PORT = null;
		public String DRIVER = null;
		
		// Web only
		public String URL = null;
		
		// Mobile only
		public String APP_PATH = null;					// Path to the apk. Android only.
		public String APP_ACTIVITY = null;				// App activity. Android only.
		public String APP_PACKAGE = null;				// App package. Android only.
		
		// Appium only
		public String NODE_PATH = null;
		public String MAIN_PATH = null;
		public String AUTOMATION_NAME = null;
		public String DEVICE_NAME = null;
		public String DEVICE_UDID = null;
		public String PLATFORM_NAME = null;
		public String PLATFORM_VERSION = null;
		public String BROWSER = null;
		public String LANGUAGE = null;
		public String LOCALE = null;
		public String SDK_PATH = null;
		public String COVERAGE_CLASS = null;
		public String CHROMEDRIVER_PORT = null;
		public String BOOTSTRAP_PORT = null;
		public String SELENDROID_PORT = null;
		
		public String DRIVERPATH = null;
		
		public Map<String, String> savedData = new HashMap<String, String>();
		
	}
}
