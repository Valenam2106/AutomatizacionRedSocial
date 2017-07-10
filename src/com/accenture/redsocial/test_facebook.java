package com.accenture.redsocial;

import org.testng.annotations.Test;

import com.accenture.redsocial.dto.Credenciales;
import com.acenture.redsocial.archivos.DataDrivenUsers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.apache.xalan.xsltc.compiler.sym;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.lang.Object;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class test_facebook {

	int row = 0;
	boolean sw;
	String status;

	public static AppiumDriver<WebElement> driver;

	DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeMethod
	public void setUpAppium() throws MalformedURLException, InterruptedException {

		String packagename = "com.facebook.katana";
		String URL = "http://127.0.0.1:4723/wd/hub";
		String activityname = "com.facebook.katana.LoginActivity";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Name");
		//capabilities.setCapability("udid", "192.168.3.142:5556");
		capabilities.setCapability("udid", "46X4C16A19006995");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", packagename);
		capabilities.setCapability("appActivity", activityname);
		capabilities.setCapability("autoDismissAlerts", true);

		driver = new AndroidDriver<WebElement>(new URL(URL), capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@AfterTest
	public void CleanUpAppium() {

		driver.quit();

	}

	@Test
	public void mytest() throws InterruptedException {

		DataDrivenUsers ddu;
		try {
			ddu = new DataDrivenUsers("C://Users//Administrator//workspace//TestRedSocial//Credenciales.xlsx");
			ddu.setPassword();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Credenciales cre = new Credenciales();
		// Objeto de a clase WebDriverWait que sirve para esperar 20 segundos o
		// lo que sea necesario para esperar que
		// La pagina cargue

		do {
			try {

				WebDriverWait wait = new WebDriverWait(driver, 20);

				sw = true;
				System.out.println("Sw en true");

				status = "Success";
				System.out.println("status success");

				System.out.println("Usuario: " + row);
				// El assert ingresa a la aplicacion por 1 segundo

				Assert.assertNotNull(driver.getContext());
				Thread.sleep(1000);

				DataDrivenUsers dataDriveUsers = new DataDrivenUsers(
						"C://Users//Administrator//workspace//TestRedSocial//Credenciales.xlsx");

				// Se obtienen la cantidad de filas

				cre = dataDriveUsers.getCellData(row);
				
				if(cre==null) break;
				
				// se da click en el correo electronico y luego se le manda
				// el correo
				WebElement user = verificarInterfaz(By.id("com.facebook.katana:id/login_username"));

				// if (user == null) {
				// user =
				// verificarInterfaz(By.xpath("//android.widget.EditText[@bounds='[96,881][984,1014]']"));
				// }

				boolean var = false;

				// h();//@bounds='[96,881][984,1014]' or
				// //android.widget.EditText[@bounds='[96,885][984,1018]]"
				user.click();
				// user.sendKeys("uditeamacn2@gmail.com");
				user.sendKeys(cre.getUser());
				Thread.sleep(1000);
				System.out.println("Correo electronico ingresado: " + cre.getUser());

				// se da click en el boton contraseña y se ingresa la
				// contraseña del correo
				WebElement pass = driver.findElement(By.id("com.facebook.katana:id/login_password"));
				pass.click();
				// pass.sendKeys("123456789abc");
				pass.sendKeys(cre.getPass());
				Thread.sleep(1000);
				System.out.println("Contraseña ingresada: " + cre.getPass());
				//

				// se inicia sesion en facebook
				WebElement boton = driver.findElement(By.id("com.facebook.katana:id/login_login"));
				boton.click();
				System.out.println("Boton de ingreso");

				if (verificarInterfaz(By.id("com.facebook.katana:id/button1")) != null) {
					// login incorrecto
					driver.findElement(By.id("com.facebook.katana:id/button1")).click();
					driver.resetApp();
					status = "default";
					dataDriveUsers.setStatus(row,
							"C://Users//Administrator//workspace//TestRedSocial//Credenciales.xlsx", status);
					row++;
					continue;
				}

				// try {
				//
				// WebElement credencialesInvalidas =
				// verificarInterfaz(By.id("com.facebook.katana:id/button1"));
				//
				// if (credencialesInvalidas != null) {
				// System.out.println("Usuario/Contraseña incorrecto");
				// ((AndroidDeviceActionShortcuts)
				// driver).pressKeyCode(AndroidKeyCode.BACK);
				//
				// // user.clear();
				// System.out.println("Click Ok");
				// sw = false;
				// System.out.println("Sw se pone en falso");
				// status = "default";
				// }
				//
				// } catch (Exception e) {
				// System.out.println("Seguir");
				// }

				if (sw == true) {
					wait.until(ExpectedConditions.presenceOfElementLocated(
					 By.xpath("//android.view.ViewGroup[@bounds='[0,650][1080,890]']")));
					//
					WebElement estado = driver
					.findElement(By.xpath("//android.view.ViewGroup[@bounds='[0,650][1080,890]']"));
					estado.click();
					Thread.sleep(2000);
					System.out.println("Selecciona estado");
					//
					WebElement btnGif = driver
					.findElement(By.xpath("//android.view.ViewGroup[@bounds='[0,1668][1080,1812]']"));
					btnGif.click();
					Thread.sleep(3000);
					System.out.println("Click para elegir gif ");
					//
					WebElement gif = driver
					.findElement(By.xpath("//android.widget.ImageView[@bounds='[6,369][537,897]']"));
					gif.click();
					Thread.sleep(7000);
					System.out.println("Gif elegido listo para publicar");
					//
					WebElement btnPub =
					driver.findElement(By.id("com.facebook.katana:id/primary_named_button"));
					btnPub.click();
					Thread.sleep(3000);
					System.out.println("Publica gift");

					// SALIR DE FACEBOOK

					WebElement opc = driver.findElement(By.id("com.facebook.katana:id/bookmarks_tab"));
					opc.click();
					Thread.sleep(3000);
					System.out.println("Ingresa a las opciones ");

					driver.swipe(500, 1737, 500, 228, 1000);
					driver.swipe(500, 1737, 500, 228, 1000);
					driver.swipe(500, 1737, 500, 228, 1000);
					driver.swipe(500, 1737, 500, 228, 1000);
					System.out.println("Scroll");

					WebElement offbtn = driver.findElement(By.xpath(
							"//com.facebook.fbui.widget.contentview.ContentView [@bounds='[0,1644][1080,1812]']"));
					offbtn.click();
					Thread.sleep(3000);
					System.out.println("Click en cerrar sesión");

					WebElement btnoff = driver.findElement(By.id("com.facebook.katana:id/button1"));
					btnoff.click();
					Thread.sleep(5000);
					System.out.println("Click confirmar cerrar sesión");

					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.id("com.facebook.katana:id/login_add_account_group")));

					// ((AndroidDeviceActionShortcuts)
					// driver).pressKeyCode(AndroidKeyCode.BACK);

					System.out.println("Click al volver");

					WebElement newCuenta = driver.findElement(By.id("com.facebook.katana:id/login_add_account_group"));
					newCuenta.click();
					Thread.sleep(4000);
					System.out.println("Nueva cuenta");
				}

				System.out.println("Terminado, escribe en excel");

				dataDriveUsers.setStatus(row, "C://Users//Administrator//workspace//TestRedSocial//Credenciales.xlsx",
						status);

			} catch (Exception e) {

				// e.printStackTrace();
				System.out.print("\n\t Se presento Excepción " + e);
			}
			row++;
		} while (cre != null);

	}

	private WebElement verificarInterfaz(By by) {

		WebElement element = null;

		try {
			element = driver.findElement(by);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}

	private Boolean verificarInterfazboolean(By by) {

		WebElement element = null;

		try {
			element = driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}