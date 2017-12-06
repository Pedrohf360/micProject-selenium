package com.mycompany.myapp.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IptuTest {

	public static WebDriver driver;
	
	@Before
	public void setupTest()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Desenvolvimento\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		
		driver.get("http://iptuonline.siatu.pbh.gov.br/IptuOnline/index.xhtml");	
	}
	
	@Test
	public void acessarIptu() throws InterruptedException, AWTException
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("pesquisar")));
		
		WebElement cpf = driver.findElement(By.id("cpf"));
		
		cpf.click();
		
		cpf.sendKeys("01213870623");
		
		WebElement cep = driver.findElement(By.id("cep"));
		
		cep.click();
		
		cep.sendKeys("30580040");
		
		Thread.sleep(10000);
		
		driver.findElement(By.id("pesquisar")).click();
		
		driver.findElement(By.xpath("//*[@id=\"form:timoveis_data\"]/tr/td[2]/div/a[2]")).click();

		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle);
		    if(driver.getPageSource().contains("IMPOSTO PREDIAL E TERRITORIAL URBANO")) {
		    	driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[3]/a")).click();
		    	Robot robot = new Robot();
		        robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		        robot.delay(3000);
		        robot.keyPress(KeyEvent.VK_1);
		        robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		    }
		}
		
		String num_guia = driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[4]/td/table/tbody/tr/td[5]/table/tbody/tr[2]/td/b")).getText();
		 		System.out.println("Numero guia: " + num_guia);
		 		
		 		String data_vencimento = driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[4]/td/table/tbody/tr/td[7]/table/tbody/tr[2]/td")).getText();
		 		System.out.println("Data de vencimento: " + data_vencimento);
		 		
		 		String indice_cad = driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td/b")).getText();
		 		System.out.println("Numero índice cadastral: " + indice_cad);
		 		
		 		Thread.sleep(1000);
		 		System.out.println("Saindo...");
		 		driver.quit();	
	}
	
}
