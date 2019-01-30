package testObjectManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import framework.DriverManager;
import framework.ManageBrowser;
import framework.ObjectManager;

public class DefaultTagsTest extends DriverManager {
	
//	@Before
//	public void start() {
//		startDriver();
//		ManageBrowser.navigateTo("https://the-internet.herokuapp.com/");
//	}
//	
//	@Test
//	public void noSuch() {
//		System.out.println("Vamos a buscar un elemento que no existe en la página");
//		WebElement webElement = ObjectManager.getObject(new String[] {"it","asd"});
//		Assert.assertTrue(webElement != null);
//	}
//	
//	@Test
//	public void nullPointer() {
//		System.out.println("Vamos a buscar un elemento con un vacío");
//		WebElement webElement = ObjectManager.getObject(new String[2]);
//		Assert.assertTrue(webElement != null);
//	}
//	
//	@Test
//	public void customTag() {
//		System.out.println("Buscamos un elemento tirando al nuevo modo por defecto de atributos");
//		WebElement webElement = ObjectManager.getObject(new String[] {"href","/checkboxes"});
//		Assert.assertTrue(webElement.getText().equals("Checkboxes"));
//	}
//	
//	@After
//	public void closeNav() {
//		close();
//		quit();
//	}
}
