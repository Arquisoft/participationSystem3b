package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import es.uniovi.asw.Application;

public class AllTest {
	
	 private static WebDriver driver;
	 private static String baseUrl;
	 
	@BeforeClass
	public static void setUp() throws Exception {
		Application.main(new String[] {});
		driver = new HtmlUnitDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@Test
	public void loginUserAdmin() {
		driver.get(baseUrl+"/");
		SeleniumUtils.entrarComoUsuario(driver);
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "crear", 10);
		SeleniumUtils.textoPresentePagina(driver, "Crea tu propuesta");
		SeleniumUtils.clickButton(driver, "salir");
		
		SeleniumUtils.entrarComoAdmin(driver);
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "salir", 10);
		SeleniumUtils.textoNoPresentePagina(driver, "Crea tu propuesta");
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
	}
	
	@Test
	public void BasicAdminTasks() {
		driver.get(baseUrl+"/");
		SeleniumUtils.entrarComoAdmin(driver);
		/*
		driver.findElement(By.name("categorias")).click();
		driver.findElement(By.name("nombre")).sendKeys("TestCat");
		SeleniumUtils.escribirInput(driver, "fechamin", "01/03/2017");
		SeleniumUtils.escribirInput(driver, "fechamax", "01/03/2018");
		SeleniumUtils.escribirInput(driver, "numVotos", "18");
		SeleniumUtils.escribirInput(driver, "denegadas", "test;test1;test2");
		SeleniumUtils.clickButton(driver, "crear");
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "salir", 10);
		SeleniumUtils.textoPresentePagina(driver, "TestCat");

		SeleniumUtils.clickButton(driver, "editTestCat");
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "guardarcambios", 10);
		SeleniumUtils.textoPresentePagina(driver, "test;test1;test2");
		SeleniumUtils.clickButton(driver, "atrasBoton");
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "salir", 10);

		SeleniumUtils.clickButton(driver, "eliminTestCat");
		
		SeleniumUtils.EsperaCargaPagina(driver, "id", "salir", 10);
		SeleniumUtils.textoNoPresentePagina(driver, "TestCat");*/
	}

}
