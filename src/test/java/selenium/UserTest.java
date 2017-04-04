package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import es.uniovi.asw.Application;

public class UserTest {
	
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
	public void addCategoria() {
		driver.get(baseUrl+"/");
		SeleniumUtils.entrarComoUsuario(driver);
	
		SeleniumUtils.clickButton(driver, "crear");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "titulo", 10);
		SeleniumUtils.textoPresentePagina(driver, "Titulo");
		
		SeleniumUtils.escribirInput(driver, "titulo", "Prueba");
		SeleniumUtils.clickButton(driver, "add");
		
		//SeleniumUtils.EsperaCargaPagina(driver, "id", "crear", 10);
		//SeleniumUtils.textoPresentePagina(driver, "Prueba");
	}

}
