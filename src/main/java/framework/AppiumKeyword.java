package framework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.Logger;

public class AppiumKeyword extends Keywords {

	final private static String CLASS_NAME = "AppiumKeywords";
	private static Logger logger = new Logger(CLASS_NAME);

	final private static long SHORTHEST_TIME_OUT = 1;
	final private static long SHORT_TIME_OUT = 2;
	final private static long TIME_OUT = 15;
	final private static long LONG_TIME_OUT = 30;
	final private static long EXTRA_LONG_TIME_OUT = 100;

	public static Map<String, String> data = new HashMap<String, String>();

	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Este metodo realiza la acción de pulsar sobre un elemento de la pantalla
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @throws Exception
	 */
	public static void pushOn(String[] arrayElement) throws Exception {
		logger.debug("Pushing on " + Arrays.toString(arrayElement) + "...");
		WebElement mobileElement = waitToBeClickable(arrayElement, TIME_OUT);

		if (mobileElement != null) {
			try {
				mobileElement.click();
			} catch (Exception e) {
				logger.error("An unexpected error occurred while trying to click: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("The object " + Arrays.toString(arrayElement) + " has not been found.");
			throw new Exception("Element is not found: " + Arrays.toString(arrayElement)); // TODO Cambiar por
																							// excepciones controladas
		}
	}

	/**
	 * Este metodo realiza la acción de escribir sobre un elemento de la pantalla un
	 * valor pasado por parametro
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param text         Contiene el texto que debe ser introducido
	 * @throws Exception
	 */
	public static void changeContext(String contextParam) throws Exception {
		logger.debug("Changing context to : " + contextParam);
		
		ArrayList<String> contexts = new ArrayList(DriverManager.getAndroidDriver().getContextHandles());
		
		if(contextParam.contains("WEBVIEW")) {
		    for (String context : contexts) {
		        if (context.contains("WEBVIEW")) {
		        	contextParam = context;
		        }
		    }
		}
	    
		DriverManager.getAndroidDriver().context(contextParam);

		logger.debug("Actual context: " + DriverManager.getAndroidDriver().getContext());
	}

	/**
	 * Este metodo realiza la acción de escribir sobre un elemento de la pantalla un
	 * valor pasado por parametro
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param text         Contiene el texto que debe ser introducido
	 * @throws Exception
	 */
	public static void writeInto(String[] arrayElement, String text) throws Exception {
		logger.debug("Writing " + text + " into " + Arrays.toString(arrayElement) + "...");

		WebElement webElement = waitToBePresent(arrayElement, TIME_OUT);

		if (webElement != null) {
			try {
				if (webElement.isDisplayed()) {
					webElement.clear();
					webElement.sendKeys(text);
				} else
					logger.error("Element is not displayed.");
			} catch (Exception e) {
				logger.error("An unexpected error occurred while trying to write into element: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("The object " + Arrays.toString(arrayElement) + " has not been found.");
			throw new Exception("Element is not found: " + Arrays.toString(arrayElement)); // TODO Cambiar por
																							// excepciones controladas
		}
	}

	/**
	 * Este metodo recoge una propiedad(text, value, class...) concreta de un
	 * elemento
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param propName     Contiene la propiedad que debe ser obtenida
	 * @throws Exception
	 */
//	public static String getProperty(String[] arrayElement, String propName) throws Exception {
//		
//		WebElement webElement = waitToBePresent(arrayElement, TIME_OUT);
//		logger.debug("Getting property " + propName + " of " + Arrays.toString(arrayElement));
//
//		if (webElement != null) {
//			if (propName != null && !propName.isEmpty()) {
//				String attribute;
//				if (propName.toLowerCase().contains("text"))
//					attribute = webElement.getText();
//				else
//					attribute = webElement.getAttribute(propName);
//				logger.debug("The found attribute is '" + attribute + "'");
//				return attribute;
//			} else {
//				logger.error("The given attribute name is empty or null.");
//				return "";
//			}
//		} else {
//			logger.error("The object " + Arrays.toString(arrayElement) + " has not been found.");
//			throw new Exception("Element is not found: " + Arrays.toString(arrayElement)); // TODO Cambiar por
//																							// excepciones controladas
//		}
//	}

	/**
	 * Este metodo verificara que aparece un texto concreto en un elemento de la
	 * pantalla
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param text         Contiene el texto a ser buscado en el elemento
	 * @throws Exception
	 */
	public static void verifyText(String[] arrayElement, String text) throws Exception {
		logger.debug("Verifying text '" + text + "' in " + Arrays.toString(arrayElement));

//		WebElement webElement = waitToBePresent(arrayElement, TIME_OUT);
//
//		if (webElement != null) {
//			if (text != null && !text.isEmpty()) {
//
//				String elementText = webElement.getText();
//				if (!text.equals(elementText)) {
//					logger.error("The given text is not equals in the element");
//					throw new Exception("Text '"+ text +"' is not found"); // TODO Cambiar por excepciones controladas
//				} else
//					logger.debug("Text '"+ text+"' encontrado correctamente");
//			} else {
//				logger.debug("The given text is empty or null.");
//				throw new Exception("Text is empty or null"); // TODO Cambiar por excepciones controladas
//			}
//		} else {
//			logger.error("The object " + Arrays.toString(arrayElement) + " has not been found.");
//			throw new Exception("Element is not found: " + Arrays.toString(arrayElement)); // TODO Cambiar por
//																							// excepciones controladas
//		}
	}

	/**
	 * Este metodo verificara que aparece un texto que contiene el esperado
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param text         Contiene el texto a ser buscado en el elemento
	 * @throws Exception
	 */

	public static void containsText(String[] arrayElement, String text) throws Exception {

		logger.debug(
				"Verifying that it contains  the following text '" + text + "' in " + Arrays.toString(arrayElement));
//		WebElement webElement = waitToBePresent(arrayElement, TIME_OUT);
//		
//		if (webElement != null) {
//			if (text != null && !text.isEmpty()) {
//
//				String elementText = webElement.getText();
//				if (!elementText.contains(text)) {
//					logger.error("The given text is not equals in the element");
//					throw new Exception("Text is not found"); // TODO Cambiar por excepciones controladas
//				} else
//					logger.debug("Text encontrado correctamente");
//			} else {
//				logger.debug("The given text is empty or null.");
//				throw new Exception("Text is empty or null"); // TODO Cambiar por excepciones controladas
//			}
//		} else {
//			logger.error("The object " + Arrays.toString(arrayElement) + " has not been found.");
//			throw new Exception("Element is not found: "+ Arrays.toString(arrayElement)); // TODO Cambiar por excepciones controladas
//		}
	}

	/**
	 * Este metodo verificara que existe un elemento en pantalla
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param timeOut      Tiempo de espera hasta encontrar el elemento
	 * @throws Exception
	 */
//	public static boolean exists(String[] arrayElement, int timeOut) throws Exception {
//		logger.debug("Verifying if it exists " + Arrays.toString(arrayElement));

//		try {
//			if (ObjectManager.isObjectPresent(arrayElement, timeOut)) {
//				logger.debug(Arrays.toString(arrayElement) + " exists on screen");
//				return true;
//			} else {
//				logger.error(Arrays.toString(arrayElement) + " does not exist on screen");
//				return false;
//			}
//		} catch (Exception e) {
//			logger.error(Arrays.toString(arrayElement) + " does not exist on screen");
//			return false;
//		}
//	}

	/**
	 * Este metodo verificara un elemento en pantalla. Fallando, en caso de no
	 * existir.
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param timeOut      Tiempo de espera hasta encontrar el elemento
	 * @throws Exception
	 */
	public static void verify(String[] arrayElement, int timeOut) throws Exception {
		logger.debug("Verifying the object: '" + Arrays.toString(arrayElement) + "'");
		if (!exists(arrayElement, timeOut))
			throw new Exception(
					"The element does not exist: " + Arrays.toString(arrayElement) + " in " + timeOut + "s.");
	}

	/**
	 * Este metodo seleccionará una opción de un selector desplegable
	 * 
	 * @author jairo
	 * @param arrayElement Es un array con los datos para localizar el objeto. Este
	 *                     contiene el tag y el valor de la búsqueda del elemento.
	 * @param timeOut      Tiempo de espera hasta encontrar el elemento
	 * @throws Exception
	 */
	public static void selectOptions(String[] arrayElement, String... options) throws Exception {
		logger.debug("Verifying the object: '" + Arrays.toString(arrayElement) + "'");
		WebElement webElement = waitToBePresent(arrayElement, TIME_OUT);
//		
//
//		String strOptions = "";
//		for (String option : options)
//			if (option == null || option.isEmpty())
//				logger.warning("Invalid option was ignored (null or empty).");
//			else
//				strOptions += option + ", ";
//
//		String description = "Select options '" + strOptions + "' from dropdown selector '"
//				+ Arrays.toString(arrayElement) + "'.";
//		logger.debug(description);
//
//			if (webElement != null) {
//				Select dropdownSelect = new Select(webElement);
//				for (String option : options) {
//					try {
//						if (option != null && !option.isEmpty())
//							dropdownSelect.selectByVisibleText(option);
//					} catch (NoSuchElementException e) {
//						logger.error("Couldn't find dropdown selector with value: " + option);
//						throw new Exception("The element does not exist.");
//					}
//				}
//			} else {
//				logger.error("Couldn't find dropdown selector: '" + Arrays.toString(arrayElement) + "'");
//				throw new Exception("The dropdown selector does not exist or it has not been posible found it.");
//			}
	}

	/**
	 * Parar la ejecución los segundos que se determinen. Ojo: se usa la clase
	 * Thread que puede lanzar excepciones.
	 * 
	 * @param El tiempo que se quiera parar la ejecución en segundos.
	 * @author Jairo
	 */
	public static void waitSec(long sec) {
		logger.debug("Waiting " + sec + "s...");
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Activa una espera explícita hasta que el elemento que se le pasa está
	 * presente en la página.
	 * 
	 * @param Es un array que representa un atributo estático de la clase
	 *           PageObject.
	 * @param El tiempo en segundos que dura la espera.
	 * @return El elemento por el que se está esperando a que esté presente.
	 * @author Jairo
	 * @throws Exception
	 */

	public static WebElement waitToBePresent(String[] arrayElement, long sec) throws Exception {
		logger.debug("Waiting to be present... : '" + Arrays.toString(arrayElement) + "' during " + sec + "s...");
		
		WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), sec);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ObjectManager.getByObject(arrayElement)));
		} catch (Exception e) {
			throw new Exception(Arrays.toString(arrayElement) + " has not been found in " + sec + "s");
		}
		return ObjectManager.getObject(arrayElement);
	}

	/**
	 * Activa una espera explícita hasta que el elemento que se le pasa sea clicable
	 * en la página.
	 * 
	 * @param Es un array que representa un atributo estático de la clase
	 *           PageObject.
	 * @param El tiempo en segundos que dura la espera.
	 * @return El elemento por el que se está esperando a que esté presente.
	 * @author Jairo
	 */
	public static WebElement waitToBeClickable(String[] arrayElement, long sec) {
		logger.debug("Waiting to be clickable : '" + Arrays.toString(arrayElement) + "' during " + sec + "s...");
		WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), sec);
		wait.until(ExpectedConditions.elementToBeClickable(ObjectManager.getByObject(arrayElement)));
		return ObjectManager.getObject(arrayElement);
	}

	/**
	 * Activa una espera explícita hasta que el elemento que se le pasa sea visible
	 * en la página.
	 * 
	 * @param Es un array que representa un atributo estático de la clase
	 *           PageObject.
	 * @param El tiempo en segundos que dura la espera.
	 * @return El elemento por el que se está esperando a que esté presente.
	 * @author Jairo
	 */
	public static WebElement waitToBeVisible(String[] arrayElement, long sec) {
		logger.debug("Waiting to be visible... : '" + Arrays.toString(arrayElement) + "' during " + sec + "s...");
		WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), sec);
		wait.until(ExpectedConditions.visibilityOf(ObjectManager.getObject(arrayElement)));
		return ObjectManager.getObject(arrayElement);
	}

	/**
	 * Activa una espera explícita hasta que el elemento que se le pasa sea se
	 * desvanezca en la página.
	 * 
	 * @param Es un array que representa un atributo estático de la clase
	 *           PageObject.
	 * @param El tiempo en segundos que dura la espera.
	 * @author Jairo
	 * @throws ExecutionException
	 */
	public static void waitToVanish(String[] arrayElement, long sec) throws ExecutionException { // TODO probar
		logger.debug("Waiting to vanish ... : '" + Arrays.toString(arrayElement) + "' during " + sec + "s...");
		ObjectManager.isObjectNotPresent(arrayElement, sec);
	}

	/**
	 * Método que poen a esperar al navegador hasta que la página se carga por
	 * completo y JavaScript notifica que así es, efectivamente.
	 * 
	 * @author Jairo
	 */
	public static void waitToLoadPage() {
		logger.debug("Waiting to load full page...");
//		ExpectedCondition<Boolean> pageLoadCondition = new
//                ExpectedCondition<Boolean>() {
//                    public Boolean apply(WebDriver driver) {
//                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
//                    }
//                };
//        WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), LONG_TIME_OUT);
//        wait.until(pageLoadCondition);
	}

	/**
	 * Método que cierra el teclado del movil
	 * 
	 * @author Jairo
	 */
	public static void closeKeyboard() {
		logger.debug("Closing keyboard...");
		try {
			DriverManager.getAndroidDriver().hideKeyboard();
		} catch (Exception e) {
			logger.debug("Keyboard is not present or is closed");
		}
		waitSec(1);
	}

	/**
	 * Método que cierra la app
	 * 
	 * @author Jairo
	 */
	public static void closeApp() {
		logger.debug("Closing app...");
		DriverManager.getAndroidDriver().closeApp();
	}

	/**
	 * Método que abre la app
	 * 
	 * @author Jairo
	 */
	public static void launchApp() {
		logger.debug("Launching app...");
		DriverManager.getAndroidDriver().launchApp();
	}
	
	public static void dragToFind(String[] arrayElement, String dir, int count) {
		logger.debug("Drag to find " + Arrays.toString(arrayElement) + "...");
		
		System.out.println("ENTRA");
		boolean found = false; 
		int i = 0 ;
		while (!found &&  i < count) {
			try {
				WebElement mobileElement = waitToBeClickable(arrayElement,SHORTHEST_TIME_OUT);
				found = true;
			}catch(Exception e) {
				swipeScreen(dir);
				swipeScreen(dir);
				System.out.println("Element is not present on screen... try: "+ i + "/"+ count);
			}
			
			i++;
		}
	}
	
	public static void swipeScreen(String dir) {
	    System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

	    // Animation default time:
	    //  - Android: 300 ms
	    //  - iOS: 200 ms
	    // final value depends on your app and could be greater
	    final int ANIMATION_TIME = 200; // ms

	    final int PRESS_TIME = 1000; // ms

	    int edgeBorder = 10; // better avoid edges
	    PointOption pointOptionStart, pointOptionEnd;

	    // init screen variables
	    Dimension dims = DriverManager.getAndroidDriver().manage().window().getSize();

	    // init start point = center of screen
	    pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

	    switch (dir) {
	        case "DOWN": // center of footer
	            pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
	            break;
	        case "UP": // center of header
	            pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
	            break;
	        case "LEFT": // center of left side
	            pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
	            break;
	        case "RIGHT": // center of right side
	            pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
	            break;
	        default:
	            throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
	    }

	    // execute swipe using TouchAction
	    try {
	        new TouchAction(DriverManager.getAndroidDriver())
	                .press(pointOptionStart)
	                // a bit more reliable when we add small wait
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
	                .moveTo(pointOptionEnd)
	                .release().perform();
	    } catch (Exception e) {
	        System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
	        return;
	    }

	    // always allow swipe action to complete
	    try {
	        Thread.sleep(ANIMATION_TIME);
	    } catch (InterruptedException e) {
	        // ignore
	    }
	}
}
