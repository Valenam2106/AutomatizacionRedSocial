package com.accenture.redsocial;

import org.testng.annotations.Test;

import com.accenture.redsocial.dto.Credenciales;
import com.acenture.redsocial.archivos.DataDrivenUsers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

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

	int row;
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
		capabilities.setCapability("udid", "46X4C16A19006995");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", packagename);
		capabilities.setCapability("appActivity", activityname);
		capabilities.setCapability("autoDismissAlerts", true);

		driver = new AndroidDriver<WebElement>(new URL(URL), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterTest
	public void CleanUpAppium() {

		driver.quit();
	}

	@Test
	public void mytest() throws InterruptedException {
		// Objeto de a clase WebDriverWait que sirve para esperar 20 segundos o
		// lo que sea necesario para esperar que
		// La pagina cargue
		try {
			Credenciales cre = new Credenciales();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			row = 0;
			DataDrivenUsers dataDrivenUsers = new DataDrivenUsers(
					"C://Users//Administrator//workspace//TestRedSocial//Credenciales.xlsx");
			cre = dataDrivenUsers.getCellData(row);
			do {
				status = "Success";
				sw = true;
				try {

					row = cre.getNumRow();
					// El assert ingresa a la aplicacion por 1 segundo
					Assert.assertNotNull(driver.getContext());

					Thread.sleep(1000);

					// se da click en el correo electronico y luego se le manda
					// el correo
					WebElement user = driver.findElement(By.id("com.facebook.katana:id/login_username"));
					user.click();
					// user.sendKeys("uditeamacn2@gmail.com");
					user.sendKeys(cre.getUser());
					Thread.sleep(1000);
					System.out.println("Correo electronico ingresado:" + cre.getUser());

					// se da click en el boton contraseña y se ingresa la
					// contraseña del correo
					WebElement pass = driver.findElement(By.id("com.facebook.katana:id/login_password"));
					pass.click();
					// pass.sendKeys("123456789abc");
					pass.sendKeys(cre.getPass());
					Thread.sleep(1000);
					System.out.println("Contraseña ingresada" + cre.getPass());

					// se inicia sesion en facebook
					WebElement boton = driver.findElement(By.id("com.facebook.katana:id/login_login"));
					boton.click();
					Thread.sleep(1000);
					System.out.println("Boton de ingreso");
					try {

						WebElement passInc = driver.findElementById("com.facebook.katana:id/friend_requests_tab");
						System.out.println("Contraseña correcta");

					} catch (Exception e) {
						sw = false;
						status = "default";
						
						((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
						((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
						Assert.assertNotNull(driver.getContext());
						System.out.println("Status default");
						
					}

					if (sw) {

						// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.ViewGroup[@bounds='[0,650][1080,890]']")));
						//
						// WebElement estado =
						// driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[0,650][1080,890]']"));
						// estado.click();
						// Thread.sleep(2000);
						// System.out.println("Selecciona estado");
						//
						//
						// WebElement btnGif =
						// driver.findElement(By.xpath("//android.view.ViewGroup
						// [@bounds='[0,1668][1080,1812]']"));
						// btnGif.click();
						// Thread.sleep(3000);
						// System.out.println("Click para elegir gif ");
						//
						//
						// WebElement gif =
						// driver.findElement(By.xpath("//android.widget.ImageView
						// [@bounds='[6,369][537,897]']"));
						// gif.click();
						// Thread.sleep(7000);
						// System.out.println("Gif elegido listo para
						// publicar");
						//
						//
						// WebElement btnPub =
						// driver.findElement(By.id("com.facebook.katana:id/primary_named_button"));
						// btnPub.click();
						// Thread.sleep(3000);
						// System.out.println("Publica gift");

						// SALIR DE FACEBOOK

						WebElement opc = driver.findElement(By.id("com.facebook.katana:id/bookmarks_tab"));
						opc.click();
						Thread.sleep(8000);
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
						Thread.sleep(4000);
						System.out.println("Click confirmar cerrar sesión");

						// TouchAction touchAction = new TouchAction(driver);
						// touchAction.tap(300, 200).perform();

						((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);

						System.out.println("Click al volver");

						WebElement newCuenta = driver
								.findElement(By.id("com.facebook.katana:id/login_add_account_group"));
						newCuenta.click();
						Thread.sleep(4000);
						System.out.println("Nueva cuenta");

					}

					System.out.println("Actualiza status");

					dataDrivenUsers.setStatus(row,
							"C://Users//Administrator//workspace//TestRedSocial//Credenciales.xlsx", status);

				} catch (Exception e) {

					//e.printStackTrace();
					System.out.print("\n\t Se presento Excepción " + e);
				}

				row++;
				cre = dataDrivenUsers.getCellData(row);
			} while (cre != null);
		} catch (Exception e) {
			System.out.print("\n\t Se presento Excepción " + e);
		}
	}

}
