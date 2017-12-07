package com.mycompany.myapp.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.sourceforge.tess4j.TesseractException;

public class IptuTest {

	public static WebDriver driver;
	
	@Before
	public void setupTest()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Develop\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		
		driver.get("http://iptuonline.siatu.pbh.gov.br/IptuOnline/index.xhtml");	
	}
	
	@Test
	public void acessarIptu() throws InterruptedException, AWTException, IOException, TesseractException
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("pesquisar")));
		
//		Busca por cpf
		WebElement cpf = driver.findElement(By.id("cpf"));
		
		Thread.sleep(1000);
		cpf.click();
		
		cpf.sendKeys("01213870623");
		
//		Busca por cnpj
//		WebElement cnpj = driver.findElement(By.id("cnpj"));
//		cnpj.click();
//		cnpj.sendKeys("71285704000104");

//		Busca por Indice
//		WebElement ind_cad = driver.findElement(By.id("inputIndice"));
//		ind_cad.click();
//		ind_cad.sendKeys("");
		
		WebElement cep = driver.findElement(By.id("cep"));
		cep.click();
		cep.sendKeys("30580040");
		
		File imgCaptcha = this.CapturarImgCaptcha();
		String textoCaptcha = OcrReader.getImgText(imgCaptcha);
		WebElement captcha = driver.findElement(By.id("CaptchaID"));
		
		captcha.click();
		captcha.sendKeys(textoCaptcha);
		
		String currentUrl = driver.getCurrentUrl();
		
		driver.findElement(By.id("pesquisar")).click();
		
		Thread.sleep(4000);
		
		while (driver.getPageSource().contains("É necessário digitar a palavra da forma que aparece na tela."))
		{
			imgCaptcha = this.CapturarImgCaptcha();
			
			textoCaptcha = OcrReader.getImgText(imgCaptcha);
			
			captcha = driver.findElement(By.id("CaptchaID"));
			
			captcha.click();
			
			captcha.sendKeys(textoCaptcha);
			
			driver.findElement(By.id("pesquisar")).click();
			
			Thread.sleep(2000);
		}
		driver.findElement(By.xpath("//*[@id=\"form:timoveis_data\"]/tr/td[2]/div/a[2]")).click();

		//Strings 
		String num_guia = null;
		String data_vencimento = null;
		String indice_cad = null;
		
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle);
		    if(driver.getPageSource().contains("IMPOSTO PREDIAL E TERRITORIAL URBANO")) {
		    	
				num_guia = driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[4]/td/table/tbody/tr/td[5]/table/tbody/tr[2]/td/b")).getText();
				data_vencimento = driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[4]/td/table/tbody/tr/td[7]/table/tbody/tr[2]/td")).getText();
		 		indice_cad = driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td/b")).getText();
		    	    	
		    	driver.findElement(By.xpath("//*[@id=\"j_idt10\"]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[3]/a")).click();
		    	Robot robot = new Robot();
		        robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		        robot.delay(5000);
		        robot.keyPress(KeyEvent.VK_1);
		        robot.delay(2000);
		        robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		    }
		}
		 		//Cria o objeto JSON e escreve os valores e chaves
		 		JSONObject obj = new JSONObject();
				
				obj.put("Número da Guia", num_guia);
				obj.put("Data de vencimento", data_vencimento);
				obj.put("Índice cadastral", indice_cad);
								
				try (FileWriter file = new FileWriter("C:\\Develop\\ProjetoAPI\\retornoJSON.txt"))
				{
						//Escreve o arquivo com JSON
						file.write(obj.toJSONString());				
				}		
				
		 		driver.quit();	
	}
	
	private File CapturarImgCaptcha() throws IOException
	{
		WebElement link = driver.findElement(By.id("CaptchaImgID"));
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		
		Point point = link.getLocation();
		
		int linkWidth = link.getSize().getWidth();
		int linkHeight = link.getSize().getHeight();
		
		BufferedImage elemScreenshot = fullImg.getSubimage(point.getX(), point.getY(), linkWidth, linkHeight);
		
		ImageIO.write(elemScreenshot, "png", screenshot);
		
		return screenshot;
		}
	
}
