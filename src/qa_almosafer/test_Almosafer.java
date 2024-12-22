package qa_almosafer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test_Almosafer extends parameter {

		@BeforeTest
		public void my_setup() {
			 my_setup_to_enter_the_website();
		}
		
		
		@Test(priority=1, enabled = true)
		public void check_en_language_is_defult() throws IOException {
			String actual_language=driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(actual_language, expected_language);
			 screen_shot();
			 }
		
		@Test (priority=2, enabled = true)
		public void check_the_currency_is_SAR() {
			String Actual_currency=driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
			Assert.assertEquals(Actual_currency, expected_currency);
			
			

		}
        @Test (priority=3, enabled = false)
			public void check_contact_number() {
				String actual_number=driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
				String expected_currency="+966554400000";
				Assert.assertEquals(actual_number, expected_currency);

			}
	
       @Test (priority=4 , enabled = false)
        public void check_qitaf_logo_is_there_in_footer() {
	
	Boolean actual_result=driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
	Boolean expected_result=true;
	Assert.assertEquals(actual_result, expected_result);

     }
       
       @Test (priority=5 , enabled = false)
       public void check_hotel_tap_is_not_select() {
       String actual_result=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
       String expected_result="false";
   	Assert.assertEquals(actual_result, expected_result);

       
       }
       @Test (priority=6 , enabled = false)
       public void check_date() {
    	   
	// Get tomorrow and the day after tomorrow formatted as "02" instead of "2"
    String tomorrow = String.format("%02d", LocalDate.now().plusDays(1).getDayOfMonth());
    String afterTomorrow = String.format("%02d", LocalDate.now().plusDays(2).getDayOfMonth());

    // Validate tomorrow's date
    String actualResult = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
    Assert.assertEquals(actualResult, tomorrow);

    // Validate the day after tomorrow's date
    String actualResultAfterTomorrow = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
    Assert.assertEquals(actualResultAfterTomorrow, afterTomorrow);
       
       }
       
       @Test (priority=7, enabled = false)
       public void randomly_change_the_language() throws InterruptedException {
    	   
    	   String [] my_web_site= {"https://global.almosafer.com/en","https://www.almosafer.com/ar?ncr=1"};
    	   String [] english_cites_name= {"Jeddah","Riyadh","Dubai"};
    	   String [] arabic_cites_names= {"جدة","دبي"};
    	   
    	   int random_arabic_cities= rand.nextInt(arabic_cites_names.length);
    	   int random_english_cities= rand.nextInt(english_cites_name.length);
    	   int random_website= rand.nextInt(my_web_site.length);
    	   
    	   driver.get(my_web_site[random_website]);

    	   WebElement hotel_tap= driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
    	   
    	   hotel_tap.click();
    	   WebElement hotel_search_bar= driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
    	   
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
    	  WebElement cities_list=driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
    	  cities_list.findElements(By.tagName("li")).get(1).click();
    	  
    	  WebElement num_of_visitor= driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
    	  Select select = new Select(num_of_visitor);
    	  int random_num_of_visitor= rand.nextInt(2);
    	  select.selectByIndex(random_num_of_visitor);
    	  
    	  WebElement search_button=driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
    	  search_button.click();
    	  
    	  Thread.sleep(30000);
    	WebElement num_of_locations=  driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
    	  boolean actual_result= num_of_locations.getText().contains("found")||num_of_locations.getText().contains("مكان");
    	  boolean expected_result=true;
		   Assert.assertEquals(actual_result,expected_result);
		   
		   WebElement low_price_button=driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		   low_price_button.click();
		   

    			  
       }
       @Test (priority=7 , enabled = false)
       public void sort() throws InterruptedException {
    	 
    	Thread.sleep(15000);
    	   WebElement sort_option=   driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
    	sort_option.click();
    	WebElement container_of_hotels=driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[5]/div/div[2]"));
    	List<WebElement> price= container_of_hotels.findElements(By.cssSelector(".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
    		int lowest_price=Integer.parseInt(price.get(0).getText().replace("SAR ", ""));
    		int highest_price=Integer.parseInt(price.get(price.size()-1).getText().replace("SAR ", ""));
    		boolean actual_result=(lowest_price<highest_price);
    		boolean expected_result=true;
    		System.out.println(actual_result);

    		Assert.assertEquals(actual_result, expected_result);

    	   
       }

}
