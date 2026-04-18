package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesOrderPage extends BasePage {

	private WebDriverWait wait;
	
	public SalesOrderPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath ="//div[@design='sidebar']")
	WebElement sidebar;
	
	@FindBy(xpath ="span[@id='logixerp_submoudle_salesinvoice_sales_order']")
	WebElement lnksalesorder;
	
	@FindBy(xpath ="//button[@onclick='logixerp_sales_order_createNewSalesOrder();']")
	WebElement createsalesorder;
	
	@FindBy(xpath ="//input[@id='salesinvoice_sales_order_customer']")
	WebElement clientdetails;
	
	@FindBy(xpath ="//input[@name='number']")
	WebElement salesordernum;
	
	@FindBy(xpath ="//input[@name='referenceNumber']")
	WebElement salesorderrefnum;
	
	@FindBy(xpath ="//span[normalize-space()='To Date']")
	WebElement datepicker;
	
	@FindBy (xpath ="//span[normalize-space()='Select Time']")
	WebElement selecttime;
	
	@FindBy(xpath ="//input[@id='salesinvoice_sales_order_supplier_combo']")
	WebElement Supplier;
	
	@FindBy(xpath ="//input[@id='dijitv2_dijit_form_FilteringSelect_6']")
	WebElement Payment_Mode;
	
	@FindBy(xpath ="//span[normalize-space()='Enter channel detail']")
	WebElement channel;
	
	//span[normalize-space()='Enter channel detail']
	
	@FindBy(xpath ="//input[@name='transporterId']")
	WebElement Transporter;
	
	@FindBy(xpath ="//input[@name='transporterServiceCode']")
	WebElement Transporter_Service;
	@FindBy(xpath ="//input[@placeholder='Remarks']")
	WebElement Remarks;
	@FindBy(xpath ="//input[@placeholder='Select/Search Product Code']")
	WebElement product_code;
	@FindBy(xpath ="//input[@name='selectedPartNumber']")
	WebElement Part_Num;
	@FindBy(xpath ="//input[@name='batchNumbers']")
	WebElement Batch_Num;
	@FindBy(xpath ="//input[@name='enteredQuantity']")
	WebElement Quantity;
	@FindBy(xpath ="//input[@name='sellingPrice']")
	WebElement Selling_Price;
	@FindBy(xpath ="//input[@name='productAmount']")
	WebElement Amount;
	@FindBy(xpath ="//input[@name='inventoryCategoriesArray']")
	WebElement Inventory_Category;
	@FindBy(xpath ="//textarea[@id='salesinvoice_salesOrderAdd_description_7f774974-0cea-452d-85bd-c05abb6c8713']")
	WebElement Description;
	
	@FindBy(xpath ="//span[normalize-space()='Save']")
	WebElement SaveSalesorder;
	
	// Additional locators for inventory category dropdown/search
	@FindBy(xpath = "//div[contains(@class,'inventoryCategory') or contains(@id,'inventoryCategory')]//input")
	WebElement inventoryCategorySearchInput;
	
	@FindBy(xpath = "//ul[contains(@class,'dropdown') or contains(@class,'menu')]//li")
	List<WebElement> inventoryCategoryOptions;
	
	/**
	 * Scroll down sidebar
	 */
	public void scrolldownsidebar()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", sidebar);
	}
	
	/**
	 * Navigate to Sales Order creation screen
	 * This method navigates from Dashboard to Sales Order page and clicks Create New Sales Order button
	 */
	public void navigateToSalesOrderCreationScreen() {
		try {
			// Wait for Create Sales Order button to be visible
			wait.until(ExpectedConditions.elementToBeClickable(createsalesorder));
			
			// Scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createsalesorder);
			Thread.sleep(500);
			
			// Click Create New Sales Order button
			createsalesorder.click();
			Thread.sleep(2000); // Wait for Sales Order creation form to load
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", createsalesorder);
				Thread.sleep(2000);
			} catch (Exception e2) {
				throw new RuntimeException("Failed to navigate to Sales Order creation screen: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Enter product code in the product search field
	 * @param productCode - The product code to search (e.g., "A")
	 */
	public void enterProductCode(String productCode) {
		try {
			wait.until(ExpectedConditions.visibilityOf(product_code));
			product_code.clear();
			product_code.sendKeys(productCode);
			Thread.sleep(1000); // Wait for product search results
			
			// Press Enter or Tab to select the product
			product_code.sendKeys(Keys.ENTER);
			Thread.sleep(2000); // Wait for product to be selected and form to populate
		} catch (Exception e) {
			throw new RuntimeException("Failed to enter product code: " + e.getMessage());
		}
	}
	
	/**
	 * Check if Inventory Category field is visible and enabled
	 * @return true if field is visible and enabled, false otherwise
	 */
	public boolean isInventoryCategoryFieldVisible() {
		try {
			wait.until(ExpectedConditions.visibilityOf(Inventory_Category));
			return Inventory_Category.isDisplayed() && Inventory_Category.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Click on Inventory Category field to open search/dropdown
	 */
	public void clickInventoryCategoryField() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Inventory_Category));
			
			// Scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Inventory_Category);
			Thread.sleep(500);
			
			// Click the field
			Inventory_Category.click();
			Thread.sleep(1000); // Wait for dropdown/search to appear
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Inventory_Category);
				Thread.sleep(1000);
			} catch (Exception e2) {
				throw new RuntimeException("Failed to click Inventory Category field: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Search for inventory category by entering search text
	 * @param searchText - The text to search for in inventory category
	 */
	public void searchInventoryCategory(String searchText) {
		try {
			// First click the field to open search
			clickInventoryCategoryField();
			
			// Try to find search input field within dropdown
			By[] searchLocators = {
				By.xpath("//input[contains(@placeholder,'Search') or contains(@placeholder,'search') or contains(@name,'search')]"),
				By.xpath("//input[@type='text' and contains(@class,'search')]"),
				By.xpath("//div[contains(@class,'dropdown')]//input[@type='text']"),
				By.xpath("//ul[contains(@class,'menu')]//input[@type='text']")
			};
			
			WebElement searchInput = null;
			for (By locator : searchLocators) {
				try {
					searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					break;
				} catch (Exception e) {
					continue;
				}
			}
			
			if (searchInput != null) {
				searchInput.clear();
				searchInput.sendKeys(searchText);
				Thread.sleep(1000); // Wait for search results
			} else {
				// If no separate search field, try typing directly in the category field
				Inventory_Category.clear();
				Inventory_Category.sendKeys(searchText);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to search inventory category: " + e.getMessage());
		}
	}
	
	/**
	 * Verify that inventory category dropdown/search is functional
	 * This checks if the field responds to clicks and shows search options
	 * @return true if searchable, false otherwise
	 */
	public boolean isInventoryCategorySearchable() {
		try {
			// Check if field is visible and enabled
			if (!isInventoryCategoryFieldVisible()) {
				return false;
			}
			
			// Click the field to open search/dropdown
			clickInventoryCategoryField();
			
			// Check if dropdown or search options appear
			By[] dropdownLocators = {
				By.xpath("//ul[contains(@class,'dropdown') or contains(@class,'menu')]"),
				By.xpath("//div[contains(@class,'dropdown')]"),
				By.xpath("//div[contains(@class,'select')]//ul"),
				By.xpath("//*[contains(@class,'options') or contains(@class,'list')]")
			};
			
			for (By locator : dropdownLocators) {
				try {
					WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					if (dropdown != null && dropdown.isDisplayed()) {
						return true;
					}
				} catch (Exception e) {
					continue;
				}
			}
			
			// If dropdown doesn't appear, check if field accepts input (searchable)
			try {
				Inventory_Category.sendKeys("test");
				Thread.sleep(500);
				String fieldValue = Inventory_Category.getAttribute("value");
				// Clear the test input
				Inventory_Category.clear();
				return fieldValue != null && fieldValue.contains("test");
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Get available inventory category options from dropdown
	 * @return List of WebElements representing category options
	 */
	public List<WebElement> getInventoryCategoryOptions() {
		try {
			clickInventoryCategoryField();
			Thread.sleep(1000);
			
			By optionsLocator = By.xpath("//ul[contains(@class,'dropdown') or contains(@class,'menu')]//li | //div[contains(@class,'option')]");
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Verify that inventory category has options available (indicating sufficient stock)
	 * @return true if options are available, false otherwise
	 */
	public boolean hasInventoryCategoryOptions() {
		try {
			List<WebElement> options = getInventoryCategoryOptions();
			return options != null && !options.isEmpty();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickonsalesorder()
	{
		lnksalesorder.click();
	}
	public void clickoncreatesalesorder()
	{
		createsalesorder.click();
	}
	
	public void fillclientdetails(String Hero)
	{
		clientdetails.sendKeys(Hero);
	}
	public void fillsalesordernum(String num)
	{
		salesordernum.sendKeys(num);
	}
	public void fillsalesorderrefnum( String refnum)
	{
	  salesorderrefnum.sendKeys(refnum);
	}
	
	public void datepicker()
	{
		// switch to frame
		driver.switchTo().frame(0);
		// Method1 : using sendkeys()
        driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("04/05/2024");
        
        // method2 : using date picker
        // expected data
        String year="2025";
        String month="October";
        String date="31";
        driver.findElement(By.xpath("//input[@id='datepicker']")).click();
        
        // select month
        while (true) {
        	
			String currentmonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String currentyear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			
			if(currentmonth.equals(month)&& currentyear.equals(year))
			{
				break;
			}
			
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click(); // Next
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();   // Previous
			
			// Select the date
			List<WebElement>alldates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calender']//tbody/"));
					for(WebElement dt:alldates)
					{
						if(dt.getText().equals(date))
						{
							dt.click();
							break;
						}
					}
		} 
							}

}
