package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ErrorMessagePage;
import pages.TechfiosPage;
import pages.TestBase;

public class AutomationPracticalTestsTestNG {

	WebDriver driver;
	TechfiosPage techfiosPage;
	ErrorMessagePage errorMessagePage;

	@BeforeMethod
	public void setUp() {
		driver = TestBase.initDriver();
		techfiosPage = PageFactory.initElements(driver, TechfiosPage.class);
		errorMessagePage = PageFactory.initElements(driver, ErrorMessagePage.class);
	}

	@Test(priority = 1)
	public void userShouldBeAbleToAddUniqueCategoryName() {
		boolean result = techfiosPage.addUniqueCategory("MyTestNG");
		Assert.assertTrue(result, "Unable to add Category");
	}

	@Test(priority = 2)
	public void userShouldBeUnableToAddDupicateCategoryName() {
		String duplicateName = techfiosPage.addDuplicateCategoryName();
		String errorMessage = errorMessagePage.validateError();
		Assert.assertTrue(errorMessage.contains(duplicateName), "Error! Duplicate Category added");

	}

	@Test(priority = 3)
	public void validateMonthsDropDown() {
		boolean match = techfiosPage.validateListOfMonthDropDown();
		Assert.assertTrue(match, "Months do not match");
	}

	@AfterMethod
	public void tearDown() {
		TestBase.tearDown();
	}
}
