package teste;

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

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

System.setProperty("webdriver.gecko.driver", "C:\\Users\\murdock\\Downloads\\selenium-java-3.8.0\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://iptuonline.siatu.pbh.gov.br/IptuOnline/index.xhtml");
		
		Thread.sleep(8000);
		
		WebElement link;
		
		link = driver.findElement(By.id("cpf"));
	
		
		link.clear();
		link.sendKeys("01873521600");
		
		Thread.sleep(5000);
		
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
		
		Thread.sleep(2000);
		
		File screenshotLocation = new File("C:\\Users\\murdock\\Desktop\\Captcha_screenshot.png");
		FileUtils.copyToDirectory(screenshot, screenshotLocation);
		System.out.println("TERMINEI");
	}

}
