package testManageBrowser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import framework.DriverManager;
import framework.Keywords;
import framework.ManageBrowser;
import framework.ObjectManager;

public class ManageBrowserTest extends DriverManager {
	
//	@Before
//	public void start() {
//		startDriver();
//		ManageBrowser.navigateTo("https://the-internet.herokuapp.com/");;
//	}
//	
//	@Test
//	public void title() {
//		System.out.println("Se comprueba el título de la página");
//		Assert.assertTrue("The Internet".equals(ManageBrowser.getTitle()));
//	}
//	
//	@Test
//	public void cookies() {
//		System.out.println("¡Vamos a borrar cookies!");
//		System.out.println("Hay todas estas cookies guardadas"+ManageBrowser.getCookies().size());
//		ManageBrowser.deleteCookies();
//		Assert.assertTrue(ManageBrowser.getCookies().size() == 0);
//	}
//	
//	@Test
//	public void fullScreen() {
//		System.out.println("Entramos en modo pantalla completa");
//		ManageBrowser.fullScreen();
//		Assert.assertTrue("Si todo ha ido bien, perfe",true);
//	}
//	
//	@Test
//	public void buttons() {
//		System.out.println("Prueba de botones. Cambio de web.");
//		ManageBrowser.navigateTo("https://the-internet.herokuapp.com/checkboxes");
//		System.out.println("Voy para atrás");
//		ManageBrowser.backButton();
//		System.out.println("Refresco");
//		ManageBrowser.refresh();
//		System.out.println("Vuelvo adelante");
//		ManageBrowser.forwardButton();
//		Assert.assertTrue(ObjectManager.getObject(new String[] {"tag","h3"}).getText().equals("Checkboxes"));
//	}
//	
//	@Test
//	public void dimensions() {
//		System.out.println("Minimizamos y reposicionamos la ventana");
//		ManageBrowser.setSize(300, 300);
//		ManageBrowser.setPosition(15, 15);
//		System.out.println("El tamaño actual es: "+ManageBrowser.getSize().toString());
//		System.out.println("La posición actual es: "+ManageBrowser.getPosition().toString());
//		Assert.assertTrue(ManageBrowser.getSize().getHeight() == 300);
//	}
//	
//	
//	@After
//	public void closeNav() {
//		close();
//		quit();
//	}

}
