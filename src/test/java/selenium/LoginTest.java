package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import es.uniovi.asw.Application;

public class LoginTest {
	
	 private WebDriver driver;
	 private String baseUrl;
	 
	@Before
	public void setUp() throws Exception {
		Application.main(new String[] {});
		driver = new HtmlUnitDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@Test
	public void loginUserAdmin() {
		driver.get(baseUrl+"/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "loginButton", 10);
		SeleniumUtils.textoPresentePagina(driver, "Inicia sesion");
		
		SeleniumUtils.clickButton(driver, "loginButton");
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "crear", 10);
		SeleniumUtils.textoPresentePagina(driver, "Crea tu propuesta");
		SeleniumUtils.clickButton(driver, "salir");
		
		SeleniumUtils.escribirInput(driver, "username", "admin");
		SeleniumUtils.clickButton(driver, "loginButton");
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "salir", 10);
		SeleniumUtils.textoNoPresentePagina(driver, "Crea tu propuesta");
	}

}
