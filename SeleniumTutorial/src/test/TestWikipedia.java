package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestWikipedia {

	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\murdock\\Downloads\\selenium-java-3.8.0\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.wikipedia.org");

		org.openqa.selenium.Point pAtual = driver.manage().window().getPosition();
		
		org.openqa.selenium.Point p = new org.openqa.selenium.Point(-2000,0);
		
		driver.manage().window().setPosition(p);
		
		WebElement link;
		
		Thread.sleep(5000);
		
		System.out.println("Estou clicando.");
		
		link = driver.findElement(By.id("js-link-box-en"));
		
		link.click();
		
		Thread.sleep(5000);
		
		System.out.println("Digitando...");
		
		WebElement searchBox;
		
		searchBox = driver.findElement(By.id("searchInput"));
		
		searchBox.sendKeys("Software");
		
		searchBox.submit();
		
		driver.manage().window().setPosition(pAtual);
		
	}
}
