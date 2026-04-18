package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerPage extends BasePage {

	private WebDriverWait wait;

	public CustomerPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	// Navigation Elements
	@FindBy(xpath ="//a[contains(normalize-space(), 'Master Data') or contains(@href, 'master')]")
	WebElement masterDataMenu;
	
	@FindBy(xpath ="//a[contains(normalize-space(), 'Customer') or contains(@href, 'customer')]")
	WebElement customerModule;
	
	// Customer Form Fields - Customer Detail Section
	@FindBy(xpath ="//input[@name='customerCode' or @id='customerCode']")
	WebElement customerCode;
	
	@FindBy(xpath ="//input[@name='name' or @id='name']")
	WebElement customerName;
	
	@FindBy(xpath ="//input[@name='mobile' or @id='mobile']")
	WebElement mobile;
	
	@FindBy(xpath ="//input[@name='fax' or @id='fax']")
	WebElement fax;
	
	@FindBy(xpath ="//select[@name='country' or @id='country']")
	WebElement country;
	
	@FindBy(xpath ="//select[@name='state' or @id='state']")
	WebElement state;
	
	@FindBy(xpath ="//select[@name='city' or @id='city']")
	WebElement city;
	
	@FindBy(xpath ="//select[@name='customerGroup' or @id='customerGroup']")
	WebElement customerGroup;
	
	@FindBy(xpath ="//input[@name='link' or @id='link']")
	WebElement link;
	
	@FindBy(xpath ="//input[@name='aadhaarNumber' or @id='aadhaarNumber']")
	WebElement aadhaarNumber;
	
	@FindBy(xpath ="//input[@name='openingDate' or @id='openingDate']")
	WebElement openingDate;
	
	@FindBy(xpath ="//input[@type='checkbox' and (@name='vendorAccess' or @id='vendorAccess')]")
	WebElement vendorAccessCheckbox;
	
	// Customer Alias Section
	@FindBy(xpath ="//input[@name='customerAlias' or @id='customerAlias']")
	WebElement customerAlias;
	
	@FindBy(xpath ="//input[@name='contactPerson' or @id='contactPerson']")
	WebElement contactPerson;
	
	@FindBy(xpath ="//input[@name='email' or @id='email']")
	WebElement email;
	
	@FindBy(xpath ="//textarea[@name='address' or @id='address']")
	WebElement address;
	
	@FindBy(xpath ="//input[@name='whatsApp' or @id='whatsApp']")
	WebElement whatsApp;
	
	@FindBy(xpath ="//input[@name='pincode' or @id='pincode']")
	WebElement pincode;
	
	@FindBy(xpath ="//input[@name='panNumber' or @id='panNumber']")
	WebElement panNumber;
	
	@FindBy(xpath ="//input[@name='gstinNumber' or @id='gstinNumber']")
	WebElement gstinNumber;
	
	@FindBy(xpath ="//input[@name='tanNumber' or @id='tanNumber']")
	WebElement tanNumber;
	
	@FindBy(xpath ="//input[@name='camera' or @id='camera']")
	WebElement camera;
	
	// Account Master Details Section
	@FindBy(xpath ="//input[@name='accountMasterCode' or @id='accountMasterCode']")
	WebElement accountMasterCode;
	
	@FindBy(xpath ="//input[@name='openingBalanceDate' or @id='openingBalanceDate']")
	WebElement openingBalanceDate;
	
	@FindBy(xpath ="//select[@name='currency' or @id='currency']")
	WebElement currency;
	
	@FindBy(xpath ="//select[@name='openingBalanceCredit' or @id='openingBalanceCredit']")
	WebElement openingBalanceCredit;
	
	@FindBy(xpath ="//select[@name='openingBusinessCredit' or @id='openingBusinessCredit']")
	WebElement openingBusinessCredit;
	
	// Buttons
	@FindBy(xpath ="//button[contains(normalize-space(), 'Reset') or contains(@class, 'reset')]")
	WebElement resetButton;
	
	@FindBy(xpath ="//button[contains(normalize-space(), 'Save') or contains(@class, 'save')]")
	WebElement saveButton;
	
	// Success/Error Message
	@FindBy(xpath ="//div[contains(@class, 'success') or contains(@class, 'alert')]")
	WebElement successMessage;
	
	/**
	 * Navigate to Master Data module
	 */
	public void clickMasterDataModule() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(masterDataMenu));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", masterDataMenu);
			Thread.sleep(500);
			masterDataMenu.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", masterDataMenu);
		}
	}
	
	/**
	 * Navigate to Customer module
	 */
	public void clickCustomerModule() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(customerModule));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", customerModule);
			Thread.sleep(500);
			customerModule.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", customerModule);
		}
	}
	
	/**
	 * Fill customer code
	 */
	public void setCustomerCode(String code) {
		wait.until(ExpectedConditions.visibilityOf(customerCode));
		customerCode.clear();
		customerCode.sendKeys(code);
	}
	
	/**
	 * Fill customer name
	 */
	public void setCustomerName(String name) {
		wait.until(ExpectedConditions.visibilityOf(customerName));
		customerName.clear();
		customerName.sendKeys(name);
	}
	
	/**
	 * Fill mobile number
	 */
	public void setMobile(String mobileNumber) {
		wait.until(ExpectedConditions.visibilityOf(mobile));
		mobile.clear();
		mobile.sendKeys(mobileNumber);
	}
	
	/**
	 * Fill fax
	 */
	public void setFax(String faxNumber) {
		wait.until(ExpectedConditions.visibilityOf(fax));
		fax.clear();
		fax.sendKeys(faxNumber);
	}
	
	/**
	 * Select country from dropdown
	 */
	public void selectCountry(String countryName) {
		wait.until(ExpectedConditions.elementToBeClickable(country));
		country.click();
		WebElement countryOption = driver.findElement(
			By.xpath("//select[@name='country' or @id='country']//option[contains(text(), '" + countryName + "')]"));
		countryOption.click();
	}
	
	/**
	 * Select state from dropdown
	 */
	public void selectState(String stateName) {
		wait.until(ExpectedConditions.elementToBeClickable(state));
		state.click();
		WebElement stateOption = driver.findElement(
			By.xpath("//select[@name='state' or @id='state']//option[contains(text(), '" + stateName + "')]"));
		stateOption.click();
	}
	
	/**
	 * Select city from dropdown
	 */
	public void selectCity(String cityName) {
		wait.until(ExpectedConditions.elementToBeClickable(city));
		city.click();
		WebElement cityOption = driver.findElement(
			By.xpath("//select[@name='city' or @id='city']//option[contains(text(), '" + cityName + "')]"));
		cityOption.click();
	}
	
	/**
	 * Select customer group
	 */
	public void selectCustomerGroup(String groupName) {
		wait.until(ExpectedConditions.elementToBeClickable(customerGroup));
		customerGroup.click();
		WebElement groupOption = driver.findElement(
			By.xpath("//select[@name='customerGroup' or @id='customerGroup']//option[contains(text(), '" + groupName + "')]"));
		groupOption.click();
	}
	
	/**
	 * Fill contact person
	 */
	public void setContactPerson(String person) {
		wait.until(ExpectedConditions.visibilityOf(contactPerson));
		contactPerson.clear();
		contactPerson.sendKeys(person);
	}
	
	/**
	 * Fill email
	 */
	public void setEmail(String emailAddress) {
		wait.until(ExpectedConditions.visibilityOf(email));
		email.clear();
		email.sendKeys(emailAddress);
	}
	
	/**
	 * Fill address
	 */
	public void setAddress(String addressText) {
		wait.until(ExpectedConditions.visibilityOf(address));
		address.clear();
		address.sendKeys(addressText);
	}
	
	/**
	 * Fill WhatsApp number
	 */
	public void setWhatsApp(String whatsAppNumber) {
		wait.until(ExpectedConditions.visibilityOf(whatsApp));
		whatsApp.clear();
		whatsApp.sendKeys(whatsAppNumber);
	}
	
	/**
	 * Fill pincode
	 */
	public void setPincode(String pincodeValue) {
		wait.until(ExpectedConditions.visibilityOf(pincode));
		pincode.clear();
		pincode.sendKeys(pincodeValue);
	}
	
	/**
	 * Fill PAN number
	 */
	public void setPanNumber(String pan) {
		wait.until(ExpectedConditions.visibilityOf(panNumber));
		panNumber.clear();
		panNumber.sendKeys(pan);
	}
	
	/**
	 * Fill GSTIN number
	 */
	public void setGstinNumber(String gstin) {
		wait.until(ExpectedConditions.visibilityOf(gstinNumber));
		gstinNumber.clear();
		gstinNumber.sendKeys(gstin);
	}
	
	/**
	 * Fill TAN number
	 */
	public void setTanNumber(String tan) {
		wait.until(ExpectedConditions.visibilityOf(tanNumber));
		tanNumber.clear();
		tanNumber.sendKeys(tan);
	}
	
	/**
	 * Select opening date
	 */
	public void selectOpeningDate(String date) {
		wait.until(ExpectedConditions.visibilityOf(openingDate));
		openingDate.clear();
		openingDate.sendKeys(date);
	}
	
	/**
	 * Select currency
	 */
	public void selectCurrency(String currencyName) {
		wait.until(ExpectedConditions.elementToBeClickable(currency));
		currency.click();
		WebElement currencyOption = driver.findElement(
			By.xpath("//select[@name='currency' or @id='currency']//option[contains(text(), '" + currencyName + "')]"));
		currencyOption.click();
	}
	
	/**
	 * Click Save button
	 */
	public void clickSaveButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(saveButton));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
			Thread.sleep(500);
			saveButton.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
		}
	}
	
	/**
	 * Click Reset button
	 */
	public void clickResetButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(resetButton));
			resetButton.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetButton);
		}
	}
	
	/**
	 * Verify customer form is displayed
	 */
	public boolean isCustomerFormDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(customerCode));
			return customerCode.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Verify success message is displayed
	 */
	public boolean isSuccessMessageDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			return successMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Get success message text
	 */
	public String getSuccessMessageText() {
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			return successMessage.getText();
		} catch (Exception e) {
			return "";
		}
	}
}
