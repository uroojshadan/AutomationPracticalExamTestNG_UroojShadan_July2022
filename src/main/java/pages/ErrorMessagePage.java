package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ErrorMessagePage {

	WebDriver driver;
	@FindBy(how=How.CSS, using="body") WebElement ERRORMESSAGE_ELEMENT;
	
	public ErrorMessagePage(WebDriver driver) {
		this.driver = driver;
	}
	public String validateError() {
		
		System.out.println(ERRORMESSAGE_ELEMENT.getText());
		return ERRORMESSAGE_ELEMENT.getText();
		
	}
}
