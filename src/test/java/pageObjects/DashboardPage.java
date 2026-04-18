package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

	private WebDriverWait wait;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@FindBy(xpath ="//aside[@class='main-sidebar']")
	WebElement sidebar;
	
	@FindBy(xpath ="//a[normalize-space()='WMS Sales']")
	WebElement wms_sales;
	
	@FindBy(xpath ="//a[normalize-space()='Sales Order']")
	WebElement Sales_Order;
	
	// Fixed: Added // at the start of XPath
	@FindBy(xpath ="//span[contains(@class,'dijitButtonText') and normalize-space()='Filter']")
	WebElement filter;
	
	// Alternative locator for filter button (if the above doesn't work)
	@FindBy(xpath ="//button[contains(@class,'filter') or contains(text(),'Filter')]")
	WebElement filterButton;
	
	// Verification elements
	@FindBy(xpath ="//a[@href='#logixerp_home_dashboard' or contains(@href,'home')]")
	WebElement homeLink;
	
	// Zone filter field locators (multiple strategies)
	@FindBy(xpath ="//input[@name='zone' or @id='zone' or contains(@id,'zone')]")
	WebElement zoneFilterField;
	
	@FindBy(xpath ="//label[contains(text(),'Zone') or normalize-space()='Zone']/following::input[1]")
	WebElement zoneFilterFieldByLabel;
	
	@FindBy(xpath ="//input[contains(@placeholder,'Zone') or contains(@name,'zone')]")
	WebElement zoneFilterFieldByPlaceholder;
	
	@FindBy(xpath ="//select[@name='zone' or @id='zone' or contains(@id,'zone')]")
	WebElement zoneFilterDropdown;
	
	@FindBy(xpath ="//div[contains(@class,'zone') or contains(@id,'zone')]//input | //div[contains(@class,'zone') or contains(@id,'zone')]//select")
	WebElement zoneFilterElement;
	
	// Admin Dashboard navigation element
	@FindBy(xpath ="//a[contains(normalize-space(), 'Admin Dashboard')]")
	WebElement adminDashboardLink;

	/**
	 * Verify dashboard/home page is loaded
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
	 * Click on WMS Sales menu item
	 */
	public void clickonwms_sales() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(wms_sales));
			// Scroll into view first
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wms_sales);
			Thread.sleep(500); // Small wait for scroll
		wms_sales.click();
			Thread.sleep(1000); // Wait for menu to expand
		} catch (Exception e) {
			// Try JavaScript click as fallback
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", wms_sales);
		}
	}
	
	/**
	 * Click on Sales Order submenu
	 */
	public void clickonsalesorder() {
		try {
			// Wait for Sales Order link to be visible (it appears after WMS Sales expands)
			wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[normalize-space()='Sales Order']")));
			
			WebElement salesOrderLink = driver.findElement(By.xpath("//a[normalize-space()='Sales Order']"));
			wait.until(ExpectedConditions.elementToBeClickable(salesOrderLink));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", salesOrderLink);
			Thread.sleep(500);
			salesOrderLink.click();
			
			// Wait for page to navigate to Sales Order page
			Thread.sleep(2000);
		} catch (Exception e) {
			// Fallback: Use stored element
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Sales_Order);
		}
	}
	
	/**
	 * Click on Filter button on Sales Order page
	 */
	public void clickonfilter() {
		try {
			// Wait for filter button to be visible
			wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(@class,'dijitButtonText') and normalize-space()='Filter'] | //button[contains(@class,'filter') or contains(text(),'Filter')]")));
			
			// Try primary locator
			try {
				wait.until(ExpectedConditions.elementToBeClickable(filter));
		filter.click();
			} catch (Exception e1) {
				// Try alternative locator
				wait.until(ExpectedConditions.elementToBeClickable(filterButton));
				filterButton.click();
			}
			
			Thread.sleep(1000); // Wait for filter dialog/screen to appear
		} catch (Exception e) {
			// Try JavaScript click as fallback
			WebElement filterElement = driver.findElement(
				By.xpath("//span[contains(@class,'dijitButtonText') and normalize-space()='Filter'] | //button[contains(@class,'filter')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", filterElement);
		}
	}
	
	/**
	 * Verify that Sales Order page has loaded
	 */
	public boolean isSalesOrderPageLoaded() {
		try {
			// Wait for Sales Order page elements to appear
			wait.until(ExpectedConditions.or(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'dijitButtonText') and normalize-space()='Filter']")),
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'filter')]")),
				ExpectedConditions.titleContains("Sales Order")
			));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Verify that Filter screen/dialog is displayed
	 */
	public boolean isFilterScreenDisplayed() {
		try {
			// Wait for filter dialog/form to appear
			wait.until(ExpectedConditions.or(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'filter') or contains(@class,'dialog')]")),
				ExpectedConditions.presenceOfElementLocated(By.xpath("//form[contains(@class,'filter')]")),
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@role,'dialog')]"))
			));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Verify that Zone filter field is visible on Sales Order Filter screen
	 * Tries multiple locator strategies to find the Zone field
	 */
	public boolean isZoneFilterFieldVisible() {
		try {
			// Wait for filter screen to be fully loaded first
			Thread.sleep(1000);
			
			// Try multiple locator strategies for Zone field
			By[] zoneLocators = {
				// Strategy 1: By name or id containing 'zone'
				By.xpath("//input[@name='zone' or @id='zone' or contains(@id,'zone')]"),
				// Strategy 2: By label text 'Zone'
				By.xpath("//label[contains(text(),'Zone') or normalize-space()='Zone']/following::input[1] | //label[contains(text(),'Zone')]/following::select[1]"),
				// Strategy 3: By placeholder containing 'Zone'
				By.xpath("//input[contains(@placeholder,'Zone') or contains(@name,'zone')]"),
				// Strategy 4: Dropdown/select with zone
				By.xpath("//select[@name='zone' or @id='zone' or contains(@id,'zone')]"),
				// Strategy 5: Div containing zone with input/select inside
				By.xpath("//div[contains(@class,'zone') or contains(@id,'zone')]//input | //div[contains(@class,'zone') or contains(@id,'zone')]//select"),
				// Strategy 6: Any element with Zone text near input
				By.xpath("//*[contains(text(),'Zone')]/following::input[1] | //*[contains(text(),'Zone')]/following::select[1]"),
				// Strategy 7: Case-insensitive search
				By.xpath("//input[contains(translate(@name,'ZONE','zone'),'zone')] | //input[contains(translate(@id,'ZONE','zone'),'zone')]"),
				// Strategy 8: By data attributes
				By.xpath("//input[@data-field='zone' or @data-name='zone'] | //select[@data-field='zone' or @data-name='zone']")
			};
			
			// Try each locator strategy
			for (By locator : zoneLocators) {
				try {
					WebElement zoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					if (zoneElement != null && zoneElement.isDisplayed()) {
						return true;
					}
				} catch (Exception e) {
					// Try next locator strategy
					continue;
				}
			}
			
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Get Zone filter field element (for further operations)
	 */
	public WebElement getZoneFilterField() {
		try {
			By[] zoneLocators = {
				By.xpath("//input[@name='zone' or @id='zone' or contains(@id,'zone')]"),
				By.xpath("//label[contains(text(),'Zone')]/following::input[1] | //label[contains(text(),'Zone')]/following::select[1]"),
				By.xpath("//input[contains(@placeholder,'Zone')]"),
				By.xpath("//select[@name='zone' or @id='zone']"),
				By.xpath("//div[contains(@class,'zone')]//input | //div[contains(@class,'zone')]//select")
			};
			
			for (By locator : zoneLocators) {
				try {
					return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				} catch (Exception e) {
					continue;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Complete navigation flow: Navigate to Sales Order Filter screen
	 */
	public void navigateToSalesOrderFilter() {
		// Step 1: Verify dashboard is loaded
		if (!isDashboardLoaded()) {
			throw new RuntimeException("Dashboard is not loaded. Cannot proceed with navigation.");
		}
		
		// Step 2: Click on WMS Sales menu
		clickonwms_sales();
		
		// Step 3: Click on Sales Order
		clickonsalesorder();
		
		// Step 4: Verify Sales Order page loaded
		if (!isSalesOrderPageLoaded()) {
			throw new RuntimeException("Sales Order page did not load successfully.");
		}
		
		// Step 5: Click on Filter button
		clickonfilter();
		
		// Step 6: Verify Filter screen is displayed
		if (!isFilterScreenDisplayed()) {
			throw new RuntimeException("Filter screen did not appear after clicking filter button.");
		}
	}
	
	/**
	 * Navigate to Admin Dashboard from Dashboard
	 * This method clicks on the Admin Dashboard link in the sidebar or navigation menu
	 */
	public void navigateToAdminDashboard() {
		try {
			// Wait for Admin Dashboard link to be visible
			wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(normalize-space(), 'Admin Dashboard')]")));
			
			WebElement adminDashboardElement = driver.findElement(
				By.xpath("//a[contains(normalize-space(), 'Admin Dashboard')]"));
			
			// Scroll into view if needed
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminDashboardElement);
			Thread.sleep(500); // Small wait for scroll
			
			// Click on Admin Dashboard link
			adminDashboardElement.click();
			Thread.sleep(2000); // Wait for Admin Dashboard page to load
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				WebElement adminDashboardElement = driver.findElement(
					By.xpath("//a[contains(normalize-space(), 'Admin Dashboard')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", adminDashboardElement);
				Thread.sleep(2000);
			} catch (Exception e2) {
				throw new RuntimeException("Failed to navigate to Admin Dashboard: " + e.getMessage());
			}
		}
	}

}
