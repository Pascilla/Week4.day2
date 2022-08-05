package Week4.Day2;

	import java.time.Duration;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Set;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import io.github.sukgu.Shadow;

	public class Architect {

		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			driver.get("https://login.salesforce.com/");
			driver.manage().window().maximize();
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			String text="";
			String text1="";
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

			//choose role as salesforce architect and get summary
			driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']")).click();
			String summary= driver.findElement(By.xpath("(//h1[@class='cert-site_title slds-p-vertical--large']/following::div)[1]")).getText();
			System.out.println("The summary of salesforce architect is");
			System.out.println(summary);

			//list of certifications available
			List<WebElement> listofcert=new ArrayList<WebElement>();
			listofcert=driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
			List<String> cert=new ArrayList<String>();
			for(int i=0;i<listofcert.size();i++) {
				text=listofcert.get(i).getText();
				cert.add(text);
			}
			System.out.println("The certifications available are");
			for (String s : cert) {
				System.out.println(s);
			}

			//click on application architect and get certifications available
			driver.findElement(By.linkText("Application Architect")).click();
			List<WebElement> listofcert1=new ArrayList<WebElement>();
			listofcert1=driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
			List<String> cert1=new ArrayList<String>();
			for(int i=0;i<listofcert1.size();i++) {
				text1=listofcert1.get(i).getText();
				cert1.add(text1);
			}
			System.out.println("The architect certifications available are");
			for (String s1 : cert1) {
				System.out.println(s1);
			}
		}

	}