package framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import utils.Logger;

public class ManageBrowser extends DriverManager {

	final private static String CLASS_NAME = "ManageBrowser";
	private static Logger logger = new Logger(CLASS_NAME);
	final private static long TIME_OUT = 10;
  
	/**
	 * Este metodo realiza la acción de navegar a la url que se le pasa por
	 * parametro
	 * 
	 * @author jairo
	 * @param url Es la url a la que se quiere navegar.
	 */
	public static void navigateTo(String url) {
		logger.debug("Navigating to " + url + "...");
		driver.get(url);
	}

	/**
	 * Refresca la ventana que se está mostrando actualmente.
	 * 
	 * @author Miguel
	 */
	public static void refresh() {
		logger.debug("Refreshing browser...");
		driver.navigate().refresh();
	}

	/**
	 * Pulsar el botón Forward del navegador.
	 * 
	 * @author Miguel
	 */
	public static void forwardButton() {
		logger.debug("Pushing on forward button...");
		driver.navigate().forward();
	}

	/**
	 * Pulsar el botón Backward en el navegador.
	 * 
	 * @author Miguel
	 */
	public static void backButton() {
		logger.debug("Pushing on back button...");
		driver.navigate().back();
	}

	/**
	 * Devuelve el título de la página que está actualmente en el navegador.
	 * 
	 * @return String con el título de la págian que está en el navegador.
	 * @author Miguel
	 */
	public static String getTitle() {
		logger.debug("Getting browser title...");
		return driver.getTitle();
	}

	/**
	 * Borra todas las cookies del navegador.
	 * 
	 * @author Miguel
	 */
	public static void deleteCookies() {
		logger.debug("Deleting cookies...");
		driver.manage().deleteAllCookies();
	}

	/**
	 * Maximiza la ventana del navegador.
	 * 
	 * @author Miguel
	 */
	public static void maximize() {
		driver.manage().window().maximize();
	}

	/**
	 * Entra en el modo pantalla completa del navegador.
	 * 
	 * @author Miguel
	 */
	public static void fullScreen() {
		driver.manage().window().fullscreen();
	}

	/**
	 * Establece el tamaño de la ventana del navegador.
	 * 
	 * @param Anchura.
	 * @param Altura.
	 * @author Miguel
	 */
	public static void setSize(int width, int height) {
		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
	}

	/**
	 * Establece la posición que debe tener la ventana en la pantalla.
	 * 
	 * @param Horizontal.
	 * @param Vertical.
	 * @author Miguel
	 */
	public static void setPosition(int width, int height) {
		Point point = new Point(width, height);
		driver.manage().window().setPosition(point);
	}

	/**
	 * Obtiene el tamaño de la ventana del navegador en la pantalla.
	 * 
	 * @return Objeto Dimension con el tamaño de la pantalla.
	 * @author Miguel
	 */
	public static Dimension getSize() {
		return driver.manage().window().getSize();
	}

	/**
	 * Obtiene el punto exacto de la pantalla en el que se encuentra la ventana del
	 * navegador.
	 * 
	 * @return Objeto Point con el punto en la pantalla.
	 * @author Miguel
	 */
	public static Point getPosition() {
		return driver.manage().window().getPosition();
	}

	/**
	 * Obtiene una lista de todas las cookies que almacena el navegador.
	 * 
	 * @return Lista con las cookies que tiene el navegador gaurdadas.
	 * @author Miguel
	 */
	public static List<Cookie> getCookies() {
		List<Cookie> cookies = new ArrayList<>();
		cookies.addAll(driver.manage().getCookies());
		return cookies;
	}

	/**
	 * Hace un cambio de pestaña en el navegador
	 * 
	 * @return Devuelve el nombre identificativo de la pestaña objetivo a la que se
	 *         va.
	 * @author Jairo
	 */
	public static String switchToWindow() {
		return switchToWindow(new String[] { driver.getWindowHandle() });
	}

	/**
	 * Cambia a de una pestaña a otra en el navegador, recibiendo qué pestañas debe
	 * ignorar en el cambio.
	 * 
	 * @param toIgnore: Array de String con los nombres identificadores de cada
	 *        pestaña a ignorar en el cambio.
	 * @return Devuelve el nombre identificativo de la pestaña objetivo a la que se
	 *         va.
	 * @author Jairo
	 */
	public static String switchToWindow(String[] toIgnore) {
		try {
			logger.debug("Switching to another window...");
			String window = null;
			Set<String> ignoreList = new HashSet<String>(Arrays.asList(toIgnore));

			int counter = 0;
			boolean exit = false;
			while (!exit && counter++ <= 60) {
				Set<String> handles = driver.getWindowHandles();
				for (String tempWindow : handles) {
					if (!ignoreList.contains(tempWindow)) {
						driver.switchTo().window(tempWindow);
						window = tempWindow;
						exit = true;
					}
				}
				if (!exit)
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
			}

			if (window == null)
				logger.error("There were not any window to switch.");
			return window;
		} catch (NoSuchWindowException e) {
			logger.error("An error ocurred while switching to window: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Cambia a una pestaña en concreto en el navegador.
	 * 
	 * @param String con el nombre identificador de la pestaña objetivo.
	 * @author Jairo
	 */
	public static void switchToWindow(String targetWindow) {
		logger.debug("Switching to '" + targetWindow + "' window.");
		try {
			int counter = 0;
			boolean exit = false;
			while (!exit && counter++ <= 60) {
				Set<String> handles = driver.getWindowHandles();
				for (String window : handles) {
					if (window.equalsIgnoreCase(targetWindow)) {
						driver.switchTo().window(window);
						exit = true;
					}
				}
				if (!exit)
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
			}

			if (!exit)
				logger.warning("Unable to switch to window '" + targetWindow + "'. No such window found.");
		} catch (NoSuchWindowException e) {
			logger.error("An error ocurred while switching to window: " + e.getMessage());
		}
	}

	public static void switchToFrame(String[] arrayElement) throws Exception {
		driver.switchTo().defaultContent();
		WebElement webElement = Keywords.waitToBePresent(arrayElement, TIME_OUT);
		logger.debug("Switching to frame : " + Arrays.toString(arrayElement));
		if (webElement != null){
			try{
				driver.switchTo().frame(webElement);
			} catch (NoSuchFrameException | StaleElementReferenceException e){
				logger.error("An error ocurred while switching to frame: "+e.getMessage());
				throw new Exception("The frame does not exist or it is not a frame.");
			}
		}else{
			logger.error("Frame '"+Arrays.toString(arrayElement)+"' was not found.");
			throw new Exception("The element does not exist.");
		}
	}
}
