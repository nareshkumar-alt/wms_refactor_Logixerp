package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PackUomPage extends BasePage{

	private WebDriverWait wait;
	
	public PackUomPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	// Navigation Elements
	@FindBy(xpath = "//aside[@class='main-sidebar']")
	WebElement sidebar;
	
	@FindBy(xpath = "//a[@href='#logixerp_home_dashboard' or contains(@href,'home') or contains(@href,'dashboard')]")
	WebElement dashboardLink;
	
	@FindBy(xpath = "//a[normalize-space()='Warehouse Master']")
	WebElement warehouseMaster;
	
	@FindBy(xpath = "//a[normalize-space()='Pack UOM']")
	WebElement packUOM;
	
	// Pack UOM Page Elements
	@FindBy(xpath = "//button[normalize-space()='Add Pack UOM']")
	WebElement addPackUOMBtn;
	
	@FindBy(xpath = "//input[@name='code']")
	WebElement codeInput;
	
	@FindBy(xpath = "//input[@name='name']")
	WebElement nameInput;
	
	@FindBy(xpath = "//input[@name='description']")
	WebElement descriptionInput;
	
	// Dimension fields for volume calculation
	@FindBy(xpath = "//input[@name='length']")
	WebElement lengthInput;
	
	@FindBy(xpath = "//input[@name='breadth']")
	WebElement breadthInput;
	
	@FindBy(xpath = "//input[@name='height']")
	WebElement heightInput;
	
	@FindBy(xpath = "//input[@name='volume']")
	WebElement volumeInput;
	
	// Action buttons
	@FindBy(xpath = "//span[normalize-space()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//span[normalize-space()='Cancel']")
	WebElement cancelBtn;
	
	// Error and success messages
	@FindBy(xpath = "//div[contains(@class,'error-message') or contains(@class,'alert-danger')]")
	WebElement errorMessage;
	
	@FindBy(xpath = "//div[contains(@class,'success-message') or contains(@class,'alert-success')]")
	WebElement successMessage;
	
	// Edit and inactive buttons
	@FindBy(xpath = "//button[contains(@class,'edit-btn') or contains(text(),'Edit')]")
	WebElement editBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Inactive']")
	WebElement inactiveBtn;
	
	// Search and filter elements
	@FindBy(xpath = "//input[@placeholder='Search' or @name='search']")
	WebElement searchInput;
	
	@FindBy(xpath = "//button[normalize-space()='Search' or contains(@class,'search-btn')]")
	WebElement searchBtn;
	
	// Table/List elements
	@FindBy(xpath = "//table//tbody//tr[1]")
	WebElement firstRecordRow;
	
	// Product Master Integration
	@FindBy(xpath = "//select[@name='packConfig' or @name='packConfiguration']")
	WebElement packConfigDropdown;
	
	// Validation message for mandatory fields
	@FindBy(xpath = "//span[contains(@class,'validation-error') or contains(@class,'field-error')]")
	WebElement validationError;
	
	// Navigation Methods
	
	/**
	 * Verify dashboard is loaded
	 */
	public boolean isDashboardLoaded() {
		try {
			wait.until(ExpectedConditions.visibilityOf(sidebar));
			return sidebar.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Click on Dashboard link (if needed to return to dashboard)
	 */
	public void clickDashboard() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(dashboardLink));
			dashboardLink.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			// Dashboard might already be active
		}
	}
	
	/**
	 * Click on Warehouse Master menu
	 */
	public void clickWarehouseMaster() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(warehouseMaster));
			warehouseMaster.click();
			Thread.sleep(1000); // Wait for submenu to expand
		} catch (Exception e) {
			throw new RuntimeException("Failed to click Warehouse Master menu: " + e.getMessage());
		}
	}
	
	/**
	 * Click on Pack UOM submenu
	 */
	public void clickPackUOM() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(packUOM));
			packUOM.click();
			Thread.sleep(2000); // Wait for Pack UOM page to load
		} catch (Exception e) {
			throw new RuntimeException("Failed to click Pack UOM submenu: " + e.getMessage());
		}
	}
	
	/**
	 * Complete navigation: Login → Dashboard → Warehouse Master → Pack UOM
	 */
	public void navigateToPackUOM() {
		// Verify dashboard is loaded first
		if (!isDashboardLoaded()) {
			throw new RuntimeException("Dashboard is not loaded. Cannot navigate to Pack UOM.");
		}
		
		// Navigate: Dashboard → Warehouse Master → Pack UOM
		clickWarehouseMaster();
		clickPackUOM();
	}
	
	// Pack UOM Operations
	public void clickAddPackUOM() {
		wait.until(ExpectedConditions.elementToBeClickable(addPackUOMBtn));
		addPackUOMBtn.click();
	}
	
	public void enterCode(String code) {
		wait.until(ExpectedConditions.visibilityOf(codeInput));
		codeInput.clear();
		codeInput.sendKeys(code);
	}
	
	public void enterName(String name) {
		wait.until(ExpectedConditions.visibilityOf(nameInput));
		nameInput.clear();
		nameInput.sendKeys(name);
	}
	
	public void enterDescription(String description) {
		wait.until(ExpectedConditions.visibilityOf(descriptionInput));
		descriptionInput.clear();
		descriptionInput.sendKeys(description);
	}
	
	public void clickSave() {
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();
	}
	
	public void clickCancel() {
		wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
		cancelBtn.click();
	}
	
	// Dimension methods for volume calculation
	public void enterLength(String length) {
		wait.until(ExpectedConditions.visibilityOf(lengthInput));
		lengthInput.clear();
		lengthInput.sendKeys(length);
	}
	
	public void enterBreadth(String breadth) {
		wait.until(ExpectedConditions.visibilityOf(breadthInput));
		breadthInput.clear();
		breadthInput.sendKeys(breadth);
	}
	
	public void enterHeight(String height) {
		wait.until(ExpectedConditions.visibilityOf(heightInput));
		heightInput.clear();
		heightInput.sendKeys(height);
	}
	
	public String getCalculatedVolume() {
		wait.until(ExpectedConditions.visibilityOf(volumeInput));
		return volumeInput.getAttribute("value");
	}
	
	// Error and success message methods
	public boolean isErrorMessageDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(errorMessage));
			return errorMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		return errorMessage.getText();
	}
	
	public boolean isSuccessMessageDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			return successMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		return successMessage.getText();
	}
	
	public boolean isValidationErrorDisplayed() {
		try {
			return validationError.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	// Edit and inactive methods
	public void clickEdit() {
		wait.until(ExpectedConditions.elementToBeClickable(editBtn));
		editBtn.click();
	}
	
	public void clickInactive() {
		wait.until(ExpectedConditions.elementToBeClickable(inactiveBtn));
		inactiveBtn.click();
	}
	
	// Search methods
	public void enterSearchText(String searchText) {
		wait.until(ExpectedConditions.visibilityOf(searchInput));
		searchInput.clear();
		searchInput.sendKeys(searchText);
	}
	
	public void clickSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		searchBtn.click();
	}
	
	public void searchPackUOM(String searchText) {
		enterSearchText(searchText);
		clickSearch();
	}
	
	public boolean isRecordDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(firstRecordRow));
			return firstRecordRow.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	// Combined method for creating Pack UOM
	public void createPackUOM(String code, String name, String description) {
		clickAddPackUOM();
		enterCode(code);
		enterName(name);
		enterDescription(description);
		clickSave();
	}
	
	// Combined method for creating Pack UOM with dimensions
	public void createPackUOMWithDimensions(String code, String name, String description, 
			String length, String breadth, String height) {
		clickAddPackUOM();
		enterCode(code);
		enterName(name);
		enterDescription(description);
		enterLength(length);
		enterBreadth(breadth);
		enterHeight(height);
		clickSave();
	}

}
