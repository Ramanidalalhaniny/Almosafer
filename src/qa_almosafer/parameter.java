package qa_almosafer;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class parameter {
	WebDriver driver= new ChromeDriver();
	String website_url="https://global.almosafer.com/en";
	Random rand = new Random();
	
	String expected_language="en";
	String expected_currency="SAR";
	String expected_number="+966554400000";
	Boolean qitaf_logo_expected_result=true;
    String expected_result_hotel_tap_not_selected="false";
 // Get tomorrow and the day after tomorrow formatted as "02" instead of "2"
    String tomorrow = String.format("%02d", LocalDate.now().plusDays(1).getDayOfMonth());
    String afterTomorrow = String.format("%02d", LocalDate.now().plusDays(2).getDayOfMonth());
    String [] my_web_site= {"https://global.almosafer.com/en","https://www.almosafer.com/ar?ncr=1"};
	   String [] english_cites_name= {"Jeddah","Riyadh","Dubai"};
	   String [] arabic_cites_names= {"جدة","دبي"};
	   
	   int random_arabic_cities= rand.nextInt(arabic_cites_names.length);
	   int random_english_cities= rand.nextInt(english_cites_name.length);
	   int random_website= rand.nextInt(my_web_site.length);
  	  boolean expected_result_for_the_page_is_fully_loded=true;


	
	public void my_setup_to_enter_the_website() {
		driver.manage().window().maximize();
		
		driver.get(website_url);
		WebElement button_for_the_currency= driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		button_for_the_currency.click();
	}
	
	
	
	public void screen_shot() throws IOException, InterruptedException {
		Thread.sleep(3000);
		Date myDate = new Date();

		String fileName = myDate.toString().replace(":", "-");

		System.out.println(fileName);

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File("./ScreenShot/" + fileName + ".png");
		FileUtils.copyFile(SrcFile, destFile);
	}
	
	public void check_the_language_for_website_to_select_citiy( WebElement hotel_search_bar) throws InterruptedException {
		
		
		 
 	   if(driver.getCurrentUrl().equals("https://www.almosafer.com/ar?ncr=1")) {
 		   String actual_lang= driver.findElement(By.tagName("html")).getAttribute("lang");
 		   String expected_lang= "ar";
 		   Assert.assertEquals(actual_lang,expected_lang);
 		   hotel_search_bar.sendKeys(arabic_cites_names[random_arabic_cities]);
 	   }
 	   else {
 		   String actual_lang= driver.findElement(By.tagName("html")).getAttribute("lang");
 		   String expected_lang= "en";
 		   Assert.assertEquals(actual_lang,expected_lang);
 		   hotel_search_bar.sendKeys(english_cites_name[random_english_cities]);

 	   }
 	   
 	   Thread.sleep(2000);
		
	} 
	
	
	public void enter_random_number_of_visitor_after_select_city() {
		WebElement cities_list=driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
  	  cities_list.findElements(By.tagName("li")).get(1).click();
  	  
  	  WebElement num_of_visitor= driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
  	  Select select = new Select(num_of_visitor);
  	  int random_num_of_visitor= rand.nextInt(2);
  	  select.selectByIndex(random_num_of_visitor);
  	  
  	  WebElement search_button=driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
  	  search_button.click();
		
		
		
	}
	public void SortOptionChecker() {
		WebElement Container = driver.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muirtl-1smo8f0"));

		if (driver.getCurrentUrl().contains("en")) {
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("SAR ", ""));
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("SAR ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);
		} else {
	
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-1l5b3qq"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("ر.س. ", ""));
			System.out.println();
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("ر.س. ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);

		}
	}
	}

