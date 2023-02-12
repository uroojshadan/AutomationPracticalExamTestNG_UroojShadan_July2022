package pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

	public static WebDriver driver;
	

	public static WebDriver initDriver() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://techfios.com/test/101/");
		return driver;
	}

	public static void tearDown() {
		driver.close();
		driver.quit();
	}
}
