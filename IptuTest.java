package com.mycompany.myapp.selenium;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IptuTest {
	public static WebDriver driver;

	
	public static FirefoxProfile FirefoxDriverProfile() throws Exception {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        //Set downloadPath
        profile.setPreference("browser.download.dir", "C:\\Users\\wenderson\\Downloads");
        //Set File Open &amp; Save preferences
        profile.setPreference("browser.helperApps.neverAsk.openFile","text/csv,application/x-msexcel,application/pdf,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
            "text/csv,application/x-msexcel,application/pdf,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", false);
        return profile;
    }
	
	
	@Before
	//Setando o driver e abrindo a pagina
	public void setupTeste() throws Exception {
		
		if(FirefoxDriverProfile() != null)
			System.out.println("profile okay !" + FirefoxDriverProfile());
		
		
		System.setProperty("webdriver.gecko.driver", "C:\\Develop\\geckodriver.exe");
		driver = new FirefoxDriver();	
		
		driver.get("http://iptuonline.siatu.pbh.gov.br/IptuOnline/index.xhtml");
	}
	
	@Test
	public void acessarIptu() throws InterruptedException{
		//entra na pagina e aguarda 20milisegundos
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//reforço: espera enquanto o botão não for clicável
		wait.until(ExpectedConditions.elementToBeClickable(By.id("pesquisar")));
		
		//WebElement indice = driver.findElement(By.id("inputIndice"));
		WebElement cpf = driver.findElement(By.id("cpf"));
		WebElement cep = driver.findElement(By.id("cep"));
		
		cpf.click();
		cpf.clear();
		cpf.sendKeys("01213870623");

		//indice.click();
		//indice.sendKeys("");
		
		cep.click();
		cep.sendKeys("30580040");
		
		//aguarda 5 segundos
		Thread.sleep(6000);
		
		driver.findElement(By.id("pesquisar")).click(); //apertar o botao
		
		Assert.assertTrue(driver.getPageSource().contains("Imóveis Encontrados"));
		//driver.findElement(By.xpath(xpathExpression))
		
		Thread.sleep(1000);
		System.out.println("OK![1]");
		//JOptionPane.showMessageDialog(null, "OK!");
		
		driver.findElement(By.xpath("//*[@id=\"form:timoveis_data\"]/tr/td[2]/div/a[2]/img")).click();
		//JOptionPane.showMessageDialog(null, "OK!");
		Thread.sleep(1000);
		System.out.println("OK![2]");
		
		Thread.sleep(1000);
		
		//Mudando a janela
		
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle);
		    if(!driver.getPageSource().contains("IMPOSTO PREDIAL E TERRITORIAL URBANO")) {
		        driver.close();
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
