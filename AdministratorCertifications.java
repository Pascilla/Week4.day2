package Week4.Day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class AdministratorCertifications {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));

		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Learn More']")));
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		//switch to second window
		Set<String> allwindows=driver.getWindowHandles();
		List<String> listofwindows=new ArrayList<String>(allwindows);
		String secondwindow=listofwindows.get(1);
		driver.switchTo().window(secondwindow);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//locate shadow root element resources
		Shadow dom=new Shadow(driver);
		WebElement resources=dom.findElementByXPath("//span[text()='Resources']");
		resources.click();
		
		//mousehover on learning on trailhead and select salesforce certification
		WebElement learning=dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions builder=new Actions(driver);
		Thread.sleep(3000);
		builder.moveToElement(learning).perform();
		WebElement salesforce = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		builder.scrollToElement(salesforce).perform();
		salesforce.click();
		
		//click on certification administrator and verify title
		driver.findElement(By.linkText("Administrator")).click();
		String title=driver.findElement(By.xpath("//div[@class='certification-banner-text']/div")).getText();
		System.out.println(title);
		if(title.equalsIgnoreCase("Administrator")) {
			System.out.println("Title verified");
		}
		else {
			System.out.println("Wrong title");
		}
	}

}