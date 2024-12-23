package qa_almosafer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test_Almosafer extends parameter {

		@BeforeTest
		public void my_setup() {
			 my_setup_to_enter_the_website();
		}
		
		
		@Test(priority=1, enabled = false)
		public void check_en_language_is_defult() throws IOException, InterruptedException {
			String actual_language=driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(actual_language, expected_language);
			 screen_shot();
			 }
		
		@Test (priority=2, enabled = false)
		public void check_the_currency_is_SAR() throws IOException, InterruptedException {

			String Actual_currency=driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
			Assert.assertEquals(Actual_currency, expected_currency);
			 screen_shot();

		}
		
        @Test (priority=3, enabled = false)
			public void check_contact_number() {
				String actual_number=driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
				Assert.assertEquals(actual_number, expected_number);

			}
	
       @Test (priority=4 , enabled = false)
        public void check_qitaf_logo_is_there_in_footer() {
	
	Boolean actual_result=driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
	Assert.assertEquals(actual_result, qitaf_logo_expected_result);

     }
       
       @Test (priority=5 , enabled = true)
       public void check_hotel_tap_is_not_select() {
       String actual_result=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
   	Assert.assertEquals(actual_result, expected_result_hotel_tap_not_selected);

       
       }
       @Test (priority=6 , enabled = true)
       public void check_date() {
    	   
	    String actualResult = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
    Assert.assertEquals(actualResult, tomorrow);

    String actualResultAfterTomorrow = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
    Assert.assertEquals(actualResultAfterTomorrow, afterTomorrow);
       
       }
       
       
       
       
       @Test (priority=7, enabled = true)
       public void randomly_change_the_language() throws InterruptedException {
    	     
    	   driver.get(my_web_site[random_website]);
    	   WebElement hotel_tap= driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
    	   hotel_tap.click();
    	   WebElement hotel_search_bar= driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
    	  
    	   check_the_language_for_website_to_select_citiy(  hotel_search_bar);
    	    
    	   enter_random_number_of_visitor_after_select_city();
    	  		  
       }
       
       
       
       @Test (priority=8 , enabled = true)
       public void check_the_page_is_fully_loded() throws InterruptedException {
     	  Thread.sleep(30000);
    	   WebElement num_of_locations=  driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
     	  boolean actual_result= num_of_locations.getText().contains("found")||num_of_locations.getText().contains("مكان");
 		   Assert.assertEquals(actual_result,expected_result_for_the_page_is_fully_loded);  
       }
       
       @Test (priority=9 , enabled = true)
       public void sort() throws InterruptedException {
    	 
    	   Thread.sleep(15000);

   		WebElement SortOption = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
   		SortOption.click();

   		Thread.sleep(3000);


   		SortOptionChecker();
    	   
       }

}
