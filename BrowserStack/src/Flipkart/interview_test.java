package Flipkart;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class interview_test {
	//public static final String huburl = "http://127.0.0.1/wd/hub";
	public static void main(String[] args) throws Exception {
		WebDriver driver;
		// Define desired_capabilities for a desktop web browser of your choice.
		
		String username = "shuvajeetroy1";
		String accessKey = "WTZiEy2VQsysYsWMxnBp";
		String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
		String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
		String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
		String URL = "https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("os_version", "10");
		capabilities.setCapability("browser", "Firefox");
		capabilities.setCapability("browser_version", "latest");
		capabilities.setCapability("name", "BStack-[Java] Sample Test"); // test buildName
		capabilities.setCapability("build", buildName);  // CI/CD job name using BROWSERSTACK_BUILD_NAME env variable
		capabilities.setCapability("browserstack.local", browserstackLocal);
		capabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
		driver = new RemoteWebDriver(new URL(URL), capabilities);

		System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver-v0.27.0-win32\\geckodriver.exe");
		
		driver = new FirefoxDriver(); //Instantiate webdriver over here.
		test_case(driver);
		driver.quit();
}
	
	public static void test_case(WebDriver driver) throws Exception {
		
		// Maximize the browser window
		driver.manage().window().maximize();
		
		// Add 30 seconds implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Resolve http://flipkart.com/
		driver.get("https://www.flipkart.com/");
		String s = driver.getTitle();
		Assert.assertEquals(driver.getTitle(), s);
		driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).sendKeys(Keys.ESCAPE);
		
		// Search for iPhone 6
		driver.findElement(By.className("LM6RPg")).sendKeys("iPhone 6");
		
		// Click on search
		driver.findElement(By.className("vh79eN")).click();
		
		// Click on mobile
		Thread.sleep(2000);
		driver.findElement(By.className("sUG0yY")).click();
		
		// Click on min
		driver.findElement(By.className("_1qKb_B")).click();
		
		// Click on 30000INR
		WebElement min_dd = driver.findElement(By.className("fPjUPw"));
		Select min = new Select(min_dd);
		min.selectByIndex(9);
		
		
		// Click on apple
		Thread.sleep(2000);
		driver.findElement(By.className("_1GEhLw")).click();
		
		// Click on assured
		Thread.sleep(2000);
		driver.findElement(By.className("_2rIV_l")).click();
		
		// Create a list to display price, product name and link to the product details page on console
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		System.out.println(url+"\n");
		
		WebElement productName = driver.findElement(By.xpath("//div[@id='container']"));
		List<WebElement> list = productName.findElements(By.className("_2cLu-l"));
		System.out.println("Total Products : "+list.size());
		
		String details[][] = new String[list.size()][2];
		int i,j;
		i=j=0;
		
		Iterator<WebElement> itr = list.iterator();
		while(itr.hasNext() && i<list.size()) {
			details[i][0] = itr.next().getText();
			i++;
		}
		
		WebElement productPrice = driver.findElement(By.xpath("//div[@id='container']"));
		List<WebElement> listPrice = productPrice.findElements(By.className("_1vC4OE"));
		
		Iterator<WebElement> itr1 = listPrice.iterator();
		while(itr1.hasNext() && j<list.size()) {
			String price = itr1.next().getText();
			details[j][1] = price.substring(1);
			j++;
		}
		for(i=0; i<list.size(); i++) {
			for(j=0; j<2; j++) {
				System.out.print(details[i][j]+", ");
			}
			System.out.println();
		}
	}
}
