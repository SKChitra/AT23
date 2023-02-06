package HomeGainMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;

public class homegainmethod {

	By strTitleText = By.xpath("//div[@class='mainCard inPageCard Step']//h3[contains(text(), 'Connect with top local real estate agents')]");
	By strPlaceholderText=By.xpath("//input[@id='rewLocation']");
	By GoButton = By.xpath("//div[@class='mainCard overlayPageCard Step']//button[@class='goBtn rewGetQuoteGo']");
	By h3Text = By.xpath("//h3[normalize-space()='What type of Real Estate Info do you need?']");
	By homevalues =By.xpath("//div[@id='REQuoteWrapper']//li[3]");
	By ContinueBtn = By.xpath("//button[@class='goBtn rewGetCategoryClick']");
	
	
	public String strTexttoCompare;
	public static WebDriver driver;
		public void navigateTo(String URL) {
			System.getProperty("Webdriver.chrome.driver", "/src/test/resources/driver/chromedriver");
			driver=new ChromeDriver();
		    driver.manage().window().maximize();
		    driver.navigate().to(URL);
		    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		}
		
		public void verifyText(String strAttribute, String strText) 
		{
			if(strAttribute.equalsIgnoreCase("title text")) {
				strTexttoCompare= driver.findElement(strTitleText).getText();
						
				System.out.println(strTexttoCompare);
			}
			else if(strAttribute.equalsIgnoreCase("placeholder text")){
				strTexttoCompare = driver.findElement(strPlaceholderText).getAttribute("placeholder");
				System.out.println(strTexttoCompare);
			}
			else if(strAttribute.equalsIgnoreCase("button text")){
				strTexttoCompare=driver.findElement(GoButton).getText();
			}
			else if(strAttribute.equalsIgnoreCase("h3titletext")){
				strTexttoCompare=driver.findElement(h3Text).getText();
			}
			if(strTexttoCompare.equals(strText)) {
			//System.out.println("Pass! " +strText + " is Present");
		}
		else {
			System.out.println("Fail! " +strTexttoCompare + " is not Present");
		}		
	}
		public void sendInput(String strAttribute, String strText)
		{
			WebElement sendText =null;
			if(strAttribute.equalsIgnoreCase("zipcode")) {
			sendText = driver.findElement(strPlaceholderText);
			}
			sendText.click();
			sendText.clear();
			sendText.sendKeys(strText);
		}
		
		public void clickEnter(String strAttribute) {
			if(strAttribute.equalsIgnoreCase("GoButton")) {
				driver.findElement(GoButton).click();
			}
			else if(strAttribute.equalsIgnoreCase("ContinueBtn")) {
				driver.findElement(ContinueBtn).click();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				
			}
			
			
			
		}
		public void selectHomeValues() {
			
			WebElement BtnToClick = driver.findElement(homevalues);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", BtnToClick);
			BtnToClick.click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
			
		}

		@After
		public void tearDown() {
			driver.close();
		}

}
