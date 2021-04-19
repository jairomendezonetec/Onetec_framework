package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

	public static Global globals = new Global();
	
	public Global getGlobal(){
		return globals;
	}

	public static void loadConfiguration(String configurationPath) throws IOException{
			
			File configFile = new File(configurationPath);

			if (!configFile.exists())
				throw new IOException("File '" + configurationPath + "' doesn't exists.");
			
			if (configFile.isDirectory())
				throw new IOException(configurationPath+" is a directory.");
			
			if (!configFile.isFile())
				throw new IOException(configurationPath+" is not a file.");
			
			Properties properties = new Properties();
			properties.load(new FileInputStream(configurationPath));
			
			globals.TARGET_DEVICE = properties.getProperty("target_devices").split(";");
			
			
			Properties propertiesDevice = new Properties();
			System.out.println("OS: "+ System.getProperty("os.name"));
			String separation = "\\";
			if(!System.getProperty("os.name").contains("Windows"))
				separation ="/";
			propertiesDevice.load(new FileInputStream(configurationPath.replace("framework.properties", "devices"+separation+globals.TARGET_DEVICE[0]+".properties")));
			
			globals.APP_PATH = propertiesDevice.getProperty("app_path");
			globals.APP_ACTIVITY = propertiesDevice.getProperty("app_activity");
			globals.APP_PACKAGE = propertiesDevice.getProperty("app_package");
			
			globals.ADDRESS = propertiesDevice.getProperty("address");
			globals.PORT = propertiesDevice.getProperty("port");
			globals.DRIVER = propertiesDevice.getProperty("driver");
			System.out.println("System: " + propertiesDevice.getProperty("driver"));
			globals.URL = propertiesDevice.getProperty("url");
			globals.NODE_PATH = propertiesDevice.getProperty("node_path");
			globals.MAIN_PATH = propertiesDevice.getProperty("main_path");
			globals.AUTOMATION_NAME = propertiesDevice.getProperty("automation_name");
			globals.DEVICE_NAME = propertiesDevice.getProperty("device_name");
			globals.DEVICE_UDID = propertiesDevice.getProperty("device_udid");
			globals.PLATFORM_NAME = propertiesDevice.getProperty("platform_name");
			globals.PLATFORM_VERSION = propertiesDevice.getProperty("platform_version");
			globals.BROWSER = propertiesDevice.getProperty("browser");
			globals.LANGUAGE = propertiesDevice.getProperty("language");
			globals.LOCALE = propertiesDevice.getProperty("locale");
			globals.SDK_PATH = propertiesDevice.getProperty("sdk_path");
			globals.COVERAGE_CLASS = propertiesDevice.getProperty("coverage_class");
			globals.CHROMEDRIVER_PORT = propertiesDevice.getProperty("chromedriver_port");
			globals.BOOTSTRAP_PORT = propertiesDevice.getProperty("bootstrap_port");
			globals.SELENDROID_PORT = propertiesDevice.getProperty("selendroid_port");
			globals.DRIVERPATH = propertiesDevice.getProperty("driver_path");
			globals.NORESET = propertiesDevice.getProperty("noreset");
			globals.FULLRESET = propertiesDevice.getProperty("fullreset");
		}
	
	
	public static  class Global{
		// General
		public String[] TARGET_DEVICE = null;
		public String ADDRESS = null;
		public String PORT = null;
		public String DRIVER = null;
		
		// Web only
		public String URL = null;
		
		// Mobile only
		public String APP_PATH = null;					// Path to the apk. Android only.
		public String APP_ACTIVITY = null;				// App activity. Android only.
		public String APP_PACKAGE = null;				// App package. Android only.
		public String NORESET = null;
		public String FULLRESET = null;
		
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
