package qa_almosafer;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class parameter {
	WebDriver driver= new ChromeDriver();
	String website_url="https://global.almosafer.com/en";
	Random rand = new Random();
	
	String expected_language="en";
	String expected_currency="SAR";

	
	public void my_setup_to_enter_the_website() {
		driver.manage().window().maximize();
		
		driver.get(website_url);
		WebElement button_for_the_currency= driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		button_for_the_currency.click();
	}
	
	
	
	public void screen_shot() throws IOException {
		Date myDate = new Date();

		String fileName = myDate.toString().replace(":", "-");

		System.out.println(fileName);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File("./ScreenShot/" + fileName + ".png");
		FileUtils.copyFile(SrcFile, destFile);
	}
	}

