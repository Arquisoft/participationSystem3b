package steps;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AllTest {
	
	 private static WebDriver driver = new HtmlUnitDriver();
	 private static String baseUrl = "http://localhost:8080";
	 
	//@BeforeClass
	public static void setUp() throws Exception {
		//Application.main(new String[] {});
		driver = new HtmlUnitDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@Given("^the user is on the login page$")
	  public void a_list_of_users() throws Throwable {
		driver.get(baseUrl+"/");
		SeleniumUtils.entrarComoUsuario(driver);
			
		SeleniumUtils.EsperaCargaPagina(driver, "id", "crear", 10);
		SeleniumUtils.textoPresentePagina(driver, "Crea tu propuesta");
	 }
	
	@When("^I login with name \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_login_with_name_and_password(String arg1, String arg2) throws Throwable {
		driver.get(baseUrl+"/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "loginButton", 10);
		SeleniumUtils.escribirInput(driver, "username", arg1);
		
		SeleniumUtils.clickButton(driver, "loginButton");
	}
	
	@Then("^I receive a list of suggestions, with \"([^\"]*)\"$")
	public void i_receive_a_list_of_suggestions_with(String arg1) throws Throwable {
		driver.get(baseUrl+"/");
		SeleniumUtils.entrarComoUsuario(driver);
			
		SeleniumUtils.EsperaCargaPagina(driver, "id", "crear", 10);
		SeleniumUtils.textoPresentePagina(driver, arg1);
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
