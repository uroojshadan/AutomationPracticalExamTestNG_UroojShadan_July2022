package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TechfiosPage extends BasePage {

	WebDriver driver;
	int sizeBeforeRemoving = 0;
	int sizeAfterRemoving = 0;

	@FindBy(how = How.CSS, using = "ul li input")
	List<WebElement> LISTOFCHECKBOXES_ELEMENT;
	@FindBy(how = How.CSS, using = "input[name='allbox']")
	WebElement TOGGLEALLCHECKBOX_ELEMENT;
	@FindBy(how = How.CSS, using = "input[value='Remove']")
	WebElement REMOVE_BUTTON_ELEMENT;
	@FindBy(how = How.CSS, using = "ul li")
	List<WebElement> LISTOFCHECKBOXES_NAME_ELEMENT;
	@FindBy(how = How.CSS, using = "input[name='data']")
	WebElement CHECKBOXNAME_ELEMENT;
	@FindBy(how = How.CSS, using = "input[value='Add']")
	WebElement ADDBUTTON_ELEMENT;
	@FindBy(how = How.CSS, using = "div a[title='Remove this category']")
	List<WebElement> CATEGORIESLIST_ELEMENT;
	@FindBy(how = How.NAME, using = "categorydata")
	WebElement CATEGORYTEXTBOX_ELEMENT;
	@FindBy(how = How.CSS, using = "input[value='Add category']")
	WebElement ADDCATEGORYBUTTON_ELEMENT;
	@FindBy(how = How.CSS, using = "select[name='colour']")
	WebElement CATEGORYDROPDOWN_ELEMENT;
	@FindBy(how = How.CSS, using = "select[name='due_month']")
	WebElement MONTHSDROPDOWN_ELEMENT;

	public TechfiosPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addCheckBoxesAndClickAddButton() {

		for (int i = 0; i < 4; i++) {
			int randomNo = generateRandomNumbers(999);
			CHECKBOXNAME_ELEMENT.sendKeys("MyCheckBox" + randomNo);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ADDBUTTON_ELEMENT.click();
		}

	}

	public void clickOnToggleAllCheckbox() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TOGGLEALLCHECKBOX_ELEMENT.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean validateThatAllCheckboxesInListAreSelected() {

		boolean checked = true;
		for (int i = 0; i < LISTOFCHECKBOXES_ELEMENT.size(); i++) {
			checked = checked && LISTOFCHECKBOXES_ELEMENT.get(i).isSelected();
		}
		return checked;

	}

	public void clickOnSingleCheckbox() {

		sizeBeforeRemoving = LISTOFCHECKBOXES_ELEMENT.size();
		System.out.println("Size before Removing checkbox/checkboxes: " + sizeBeforeRemoving);

		System.out.println("Names of Checkboxes : ");
		for (int i = 0; i < LISTOFCHECKBOXES_NAME_ELEMENT.size(); i++) {

			System.out.println(LISTOFCHECKBOXES_NAME_ELEMENT.get(i).getText());

		}
		
		LISTOFCHECKBOXES_ELEMENT.get(1).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnRemoveButton() {

		REMOVE_BUTTON_ELEMENT.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sizeAfterRemoving = LISTOFCHECKBOXES_ELEMENT.size();
		System.out.println("Size After Removing checkbox/checkboxes: " + sizeAfterRemoving);
	}

	public boolean validateThatOneCheckboxIsRemoved() {
		
		boolean result = false;
		if (sizeAfterRemoving == sizeBeforeRemoving - 1) {
			result = true;
		}
		return result;

	}

	public boolean validateThatAllCheckBoxesAreRemoved() {

		boolean result = false;
		if (LISTOFCHECKBOXES_ELEMENT.size() == 0) {
			result = true;
		}
		return result;

	}

	public boolean addUniqueCategory(String categoryName) {

		int randomNo = generateRandomNumbers(9999);
		categoryName = categoryName + randomNo;
		CATEGORYTEXTBOX_ELEMENT.sendKeys(categoryName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ADDCATEGORYBUTTON_ELEMENT.click();

		// validating that category name just added is displayed
		boolean result = false;
		for (int i = CATEGORIESLIST_ELEMENT.size() - 1; i > 10; i--) {
			if (categoryName.equalsIgnoreCase(CATEGORIESLIST_ELEMENT.get(i).getText())) {
				result = true;
			}
		}
		System.out.println("Name of category added : " + categoryName);
		return result;

	}

	public String addDuplicateCategoryName() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// String duplicateName = CATEGORIESLIST_ELEMENT.get(0).getText();//for first element
		//or for last element in list of categories
		String duplicateName = CATEGORIESLIST_ELEMENT.get(CATEGORIESLIST_ELEMENT.size() - 1).getText();
		CATEGORYTEXTBOX_ELEMENT.sendKeys(duplicateName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ADDCATEGORYBUTTON_ELEMENT.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return duplicateName;

	}

	public boolean validateListOfMonthDropDown() {

		List<String> listOfMonths = new ArrayList<String>();
		listOfMonths.add("Jan");
		listOfMonths.add("Feb");
		listOfMonths.add("Mar");
		listOfMonths.add("Apr");
		listOfMonths.add("May");
		listOfMonths.add("Jun");
		listOfMonths.add("Jul");
		listOfMonths.add("Aug");
		listOfMonths.add("Sep");
		listOfMonths.add("Oct");
		listOfMonths.add("Nov");
		listOfMonths.add("Dec");

		boolean match = true;

		List<WebElement> listOfMonthsInDropDown = getListFromDropDownList(MONTHSDROPDOWN_ELEMENT);
		for (int i = 0; i < listOfMonthsInDropDown.size(); i++) {
			if (i == 0) {//since at 0th index the value in list is none therefore skipping this iteration
				continue;
			}
			if (!listOfMonthsInDropDown.get(i).getText().equalsIgnoreCase(listOfMonths.get(i - 1))) {

				match = match&&false;//all the values should be matched 
				
			}
		}

		if(match==true)
		System.out.println("success");
		return match;

	}

}
