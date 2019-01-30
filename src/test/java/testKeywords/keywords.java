package testKeywords;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import framework.DriverManager;
import framework.Keywords;
import framework.ManageBrowser;

public class keywords extends DriverManager {
		
//	@Before
//	public void start() {
//		startDriver();
//		ManageBrowser.navigateTo("https://intranet.grupoonetec.com/inicio");
//	}
//	
//	@Test
//	public void pushOn() throws Exception {
//		System.out.println("Pulso sobre el botón aceptar...");
//		Keywords.pushOn(new String[] {"value","Acceder"});
//		Assert.assertTrue("Si no ha saltado la excepción, la espera no ha sido en vano",true);
//	}
//	
//	@Test
//	public void writeInto() throws Exception {
//		System.out.println("Escribo prueba en el campo ...");
//		Keywords.writeInto(new String[] {"name", "_58_login"}, "Prueba");
//		Assert.assertTrue("Funciona correctamente el keyword",true);
//	}
//	
//	@Test
//	public void getProperty() throws Exception {
//		System.out.println("Recojo el texto Acceder del botón...");
//		Assert.assertEquals("Acceder", Keywords.getProperty(new String[] {"value","Acceder"}, "value"));
//		Assert.assertTrue("Funciona correctamente el keyword",true);
//	}
//	
//	@Test
//	public void verifyText() throws Exception {
//		System.out.println("Comparo el texto del botón con Acceder...");
//		Keywords.verifyText(new String[] {"xpath","//*[@for='_58_login']"}, "Nombre de usuario");
//		Assert.assertTrue("Funciona correctamente el keyword",true);
//	}
//	
//	@Test
//	public void exists() throws Exception {
//		System.out.println("Comparo el texto del botón con Acceder...");
//		Assert.assertEquals(Keywords.exists(new String[] {"value","Acceder"}, 2), true);
//		Assert.assertTrue("Funciona correctamente el keyword",true);
//	}
//	
//	@Test
//	public void verify() throws Exception {
//		System.out.println("Verifico que aparece el botón Acceder...");
//		Keywords.verify(new String[] {"value","Acceder"},1);
//		Assert.assertTrue("Funciona correctamente el keyword",true);
//	}
//	
//	@Test
//	public void saveAndRecover() throws Exception {
//		System.out.println("Verifico que guardo y recupero una variable...");
//		String inicial = "valor";
//		Keywords.saveData("clave", inicial);
//		Assert.assertEquals(inicial, Keywords.recoverData("clave"));
//	}
//	
//	@After
//	public void closeNav() {
//		close();
//		quit();
//	}
}
