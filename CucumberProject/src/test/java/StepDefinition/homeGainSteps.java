package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import HomeGainMethod.homegainmethod;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class homeGainSteps {

	static homegainmethod homegain = new homegainmethod();
	
	WebDriver driver; 
	
	
	@Given("user goes to {string}")
	public void navigate_to(String strURL) {
	    homegain.navigateTo(strURL);
	    
	}
	@Given("user verifies if {string}: {string} is present")
	public void verifytext(String strAttribute, String strText) {
	   homegain.verifyText(strAttribute, strText);
	}
	@Then("user checks {string}: {string} is present")
	public void verify_Text(String strAttribute, String strText) {
		homegain.verifyText(strAttribute, strText);	}


	@Then("user clicks {string} to send: {string}")
	public void send_Input(String strAttribute, String strText) {
		homegain.sendInput(strAttribute, strText);
	}

	@Then("user press {string}")
	public void click_Enter(String strAttribute) {
		homegain.clickEnter(strAttribute);
	   
	}
	@Then("user selects Home Values")
	public void select_HomeValues() {
	    homegain.selectHomeValues();
	}


	
	
	/*	@Given("user goes to (.*)")
		public void navigate_to(String URL) {
			homegain.navigateTo(URL);
	}

		@And("user verifies if (.*): (.*) is present")
		public void verify_text(String strAttribute, String strText) 
		{
			homegain.verifyText(strAttribute, strText);
		}
		@Then("user checks (.*): (.*) is present")
		public void verify_textPresent(String strAttribute, String strText) {
			homegain.verifyText(strAttribute, strText);
			
		}*/
		
}
