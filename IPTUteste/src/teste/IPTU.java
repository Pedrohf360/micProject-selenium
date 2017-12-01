package teste;

import org.openqa.selenium.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//org.openqa.selenium.Point pAtual = driver.manage().window().getPosition();

//org.openqa.selenium.Point p = new org.openqa.selenium.Point(-2000,0);

//driver.manage().window().setPosition(p);

public class IPTU {
	
	private WebElement link;

	private WebDriver driver;

	private String nomeImagemCaptcha;
	
	private Point posicaoOriginalTela;
	
	public IPTU() throws IOException, InterruptedException
	{	
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\murdock\\Downloads\\selenium-java-3.8.0\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		
		this.posicaoOriginalTela = driver.manage().window().getPosition();
		
		Point foraTela = new Point(-2000,0); 
		
		driver.get("http://iptuonline.siatu.pbh.gov.br/IptuOnline/index.xhtml");
		
		driver.manage().window().setPosition(foraTela);
		
		link = driver.findElement(By.id("CaptchaImgID"));
		
		// Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		
		org.openqa.selenium.Point point = link.getLocation();
		
		// Get the location of element on the page
		int linkWidth = link.getSize().getWidth();
		int linkHeight = link.getSize().getHeight();
		
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
			    linkWidth, linkHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		
		File screenshotLocation = new File("C:\\micProject\\IPTUteste\\bin");
		FileUtils.copyToDirectory(screenshot, screenshotLocation);
		
		this.nomeImagemCaptcha = screenshot.getName();
	}
	
	public String getNomeImagemCaptcha()
	{
		return this.nomeImagemCaptcha;
	}
	
	public void ConsultarIndiceCad(String indCad, String captcha)
	{
		driver.navigate();
		
		// Procura elemento textBox para preencher índice cadastral.
		link = driver.findElement(By.id("inputIndice"));
		
		link.clear();
		link.sendKeys(indCad);
		
		// Procura elemento textBox para preencher captcha, que será salvo no construtor desta classe.
		link = driver.findElement(By.id("CaptchaID"));
		
		link.clear();
		link.sendKeys(captcha);
		
		link = driver.findElement(By.id("pesquisar"));
		
		link.click();
		
		driver.manage().window().setPosition(this.posicaoOriginalTela);
	}

	public void ConsultarCpf(String numCpf, String numCepImovel, String captcha)
	{
		driver.navigate();
		
		// Procura elemento textBox para preencher cpf.
		link = driver.findElement(By.id("cpf"));
		
		// .clear é necessário para digitar algo no campo cpf.
		link.clear();
		link.sendKeys(numCpf);
		
		// Procura elemento textBox para preencher cep.
		link = driver.findElement(By.id("cep"));
		
		link.clear();
		link.sendKeys(numCepImovel);
		
		// Procura elemento textBox para preencher captcha, que será salvo no construtor desta classe.
		link = driver.findElement(By.id("CaptchaID"));
		
		link.clear();
		link.sendKeys(captcha);
		
		link = driver.findElement(By.id("pesquisar"));
		
		link.click();
		
		driver.manage().window().setPosition(this.posicaoOriginalTela);
	}

	public void ConsultarCpnj(String cnpj, String numCepImovel, String captcha)
	{
		driver.navigate();
		
		link = driver.findElement(By.id("cnpj"));
		
		link.clear();
		link.sendKeys(cnpj);
		
		// Procura elemento textBox para preencher cep.
		link = driver.findElement(By.id("cep"));
		
		link.clear();
		link.sendKeys(numCepImovel);
		
		// Procura elemento textBox para preencher captcha, que será salvo no construtor desta classe.
		link = driver.findElement(By.id("CaptchaID"));
		
		link.clear();
		link.sendKeys(captcha);
		
		link = driver.findElement(By.id("pesquisar"));
		
		link.click();
		
		driver.manage().window().setPosition(this.posicaoOriginalTela);
	}
}
