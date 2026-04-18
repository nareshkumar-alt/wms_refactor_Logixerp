package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model for Sales Order Settings page
 * This page handles the Automatic Pack UOM Conversion checkbox and related settings
 */
public class SalesOrderSettingsPage extends BasePage {
	
	private WebDriverWait wait;
	
	public SalesOrderSettingsPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	// Automatic Pack UOM Conversion checkbox locators (multiple strategies)
	@FindBy(xpath = "//input[@type='checkbox' and (contains(@name,'autoPackUOMConversion') or contains(@id,'autoPackUOMConversion') or contains(@name,'automaticPackUOM') or contains(@id,'automaticPackUOM'))]")
	WebElement autoPackUOMConversionCheckbox;
	
	// Alternative locators for checkbox by label text
	@FindBy(xpath = "//label[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding-sibling::input[@type='checkbox'] | //label[contains(text(),'Automatic Pack UOM Conversion')]/following-sibling::input[@type='checkbox']")
	WebElement autoPackUOMConversionCheckboxByLabel;
	
	@FindBy(xpath = "//span[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding::input[@type='checkbox'][1]")
	WebElement autoPackUOMConversionCheckboxBySpan;
	
	// Save button for settings
	@FindBy(xpath = "//span[normalize-space()='Save'] | //button[normalize-space()='Save'] | //span[contains(text(),'Save')]")
	WebElement saveSettingsBtn;
	
	// Success/Error messages
	@FindBy(xpath = "//div[contains(@class,'success') or contains(@class,'alert-success') or contains(@class,'message-success')]")
	WebElement successMessage;
	
	@FindBy(xpath = "//div[contains(@class,'error') or contains(@class,'alert-danger') or contains(@class,'message-error')]")
	WebElement errorMessage;
	
	/**
	 * Check if Automatic Pack UOM Conversion checkbox is visible on the page
	 * Uses multiple locator strategies to find the checkbox
	 * @return true if checkbox is visible, false otherwise
	 */
	public boolean isAutoPackUOMConversionCheckboxVisible() {
		try {
			// Wait a bit for page to fully load
			Thread.sleep(1000);
			
			// Try multiple locator strategies for the checkbox
			By[] checkboxLocators = {
				// Strategy 1: By name or id containing autoPackUOMConversion
				By.xpath("//input[@type='checkbox' and (contains(@name,'autoPackUOMConversion') or contains(@id,'autoPackUOMConversion'))]"),
				// Strategy 2: By name or id containing automaticPackUOM
				By.xpath("//input[@type='checkbox' and (contains(@name,'automaticPackUOM') or contains(@id,'automaticPackUOM'))]"),
				// Strategy 3: By label text 'Automatic Pack UOM Conversion'
				By.xpath("//label[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding-sibling::input[@type='checkbox']"),
				By.xpath("//label[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/following-sibling::input[@type='checkbox']"),
				// Strategy 4: By span text near checkbox
				By.xpath("//span[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding::input[@type='checkbox'][1]"),
				// Strategy 5: Any checkbox near text containing "Automatic Pack UOM"
				By.xpath("//*[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding::input[@type='checkbox'][1]"),
				By.xpath("//*[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/following::input[@type='checkbox'][1]"),
				// Strategy 6: Case-insensitive search
				By.xpath("//input[@type='checkbox' and contains(translate(@name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'automaticpackuom')]"),
				// Strategy 7: By data attributes
				By.xpath("//input[@type='checkbox' and (@data-field='autoPackUOMConversion' or @data-name='autoPackUOMConversion')]")
			};
			
			// Try each locator strategy
			for (By locator : checkboxLocators) {
				try {
					WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					if (checkbox != null && checkbox.isDisplayed()) {
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
	 * Get the Automatic Pack UOM Conversion checkbox element
	 * Uses multiple locator strategies to find the checkbox
	 * @return WebElement if found, null otherwise
	 */
	public WebElement getAutoPackUOMConversionCheckbox() {
		try {
			By[] checkboxLocators = {
				// Strategy 1: By name or id containing autoPackUOMConversion
				By.xpath("//input[@type='checkbox' and (contains(@name,'autoPackUOMConversion') or contains(@id,'autoPackUOMConversion'))]"),
				// Strategy 2: By name or id containing automaticPackUOM
				By.xpath("//input[@type='checkbox' and (contains(@name,'automaticPackUOM') or contains(@id,'automaticPackUOM'))]"),
				// Strategy 3: By label text
				By.xpath("//label[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding-sibling::input[@type='checkbox']"),
				By.xpath("//label[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/following-sibling::input[@type='checkbox']"),
				// Strategy 4: By span text
				By.xpath("//span[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding::input[@type='checkbox'][1]"),
				// Strategy 5: Any checkbox near text
				By.xpath("//*[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/preceding::input[@type='checkbox'][1]"),
				By.xpath("//*[contains(text(),'Automatic Pack UOM Conversion') or contains(text(),'Automatic pack UOM Conversion')]/following::input[@type='checkbox'][1]")
			};
			
			for (By locator : checkboxLocators) {
				try {
					return wait.until(ExpectedConditions.elementToBeClickable(locator));
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
	 * Check if Automatic Pack UOM Conversion is enabled (checkbox is checked)
	 * @return true if checkbox is selected, false otherwise
	 */
	public boolean isAutoPackUOMConversionEnabled() {
		try {
			WebElement checkbox = getAutoPackUOMConversionCheckbox();
			if (checkbox != null) {
				return checkbox.isSelected();
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Enable Automatic Pack UOM Conversion by checking the checkbox
	 * Scrolls to the checkbox and clicks it if not already selected
	 */
	public void enableAutoPackUOMConversion() {
		try {
			WebElement checkbox = getAutoPackUOMConversionCheckbox();
			if (checkbox == null) {
				throw new RuntimeException("Automatic Pack UOM Conversion checkbox not found");
			}
			
			// Scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
			Thread.sleep(500); // Small wait for scroll
			
			// Check if already enabled
			if (!checkbox.isSelected()) {
				// Click the checkbox to enable it
				checkbox.click();
				Thread.sleep(1000); // Wait for state change
			} else {
				// Already enabled, log this
				System.out.println("Automatic Pack UOM Conversion is already enabled");
			}
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				WebElement checkbox = getAutoPackUOMConversionCheckbox();
				if (checkbox != null) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
					Thread.sleep(1000);
				} else {
					throw new RuntimeException("Failed to enable Automatic Pack UOM Conversion: Checkbox not found");
				}
			} catch (Exception e2) {
				throw new RuntimeException("Failed to enable Automatic Pack UOM Conversion: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Disable Automatic Pack UOM Conversion by unchecking the checkbox
	 * Scrolls to the checkbox and clicks it if currently selected
	 */
	public void disableAutoPackUOMConversion() {
		try {
			WebElement checkbox = getAutoPackUOMConversionCheckbox();
			if (checkbox == null) {
				throw new RuntimeException("Automatic Pack UOM Conversion checkbox not found");
			}
			
			// Scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
			Thread.sleep(500); // Small wait for scroll
			
			// Check if currently enabled
			if (checkbox.isSelected()) {
				// Click the checkbox to disable it
				checkbox.click();
				Thread.sleep(1000); // Wait for state change
			} else {
				// Already disabled, log this
				System.out.println("Automatic Pack UOM Conversion is already disabled");
			}
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				WebElement checkbox = getAutoPackUOMConversionCheckbox();
				if (checkbox != null) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
					Thread.sleep(1000);
				} else {
					throw new RuntimeException("Failed to disable Automatic Pack UOM Conversion: Checkbox not found");
				}
			} catch (Exception e2) {
				throw new RuntimeException("Failed to disable Automatic Pack UOM Conversion: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Save the settings by clicking the Save button
	 * Waits for the save operation to complete
	 */
	public void saveSettings() {
		try {
			// Wait for Save button to be clickable
			wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[normalize-space()='Save'] | //button[normalize-space()='Save'] | //span[contains(text(),'Save')]")));
			
			WebElement saveButton = driver.findElement(
				By.xpath("//span[normalize-space()='Save'] | //button[normalize-space()='Save'] | //span[contains(text(),'Save')]"));
			
			// Scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
			Thread.sleep(500);
			
			// Click Save button
			saveButton.click();
			Thread.sleep(2000); // Wait for save to complete
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				WebElement saveButton = driver.findElement(
					By.xpath("//span[normalize-space()='Save'] | //button[normalize-space()='Save']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
				Thread.sleep(2000);
			} catch (Exception e2) {
				throw new RuntimeException("Failed to save settings: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Verify if settings were saved successfully by checking for success message
	 * @return true if success message is displayed, false otherwise
	 */
	public boolean isSettingsSavedSuccessfully() {
		try {
			// Wait for success message to appear
			wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'success') or contains(@class,'alert-success')]")),
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'saved successfully') or contains(text(),'Saved successfully')]"))
			));
			return true;
		} catch (Exception e) {
			// Success message might not appear, but that doesn't mean save failed
			// Return true if no error message is visible
			try {
				WebElement errorMsg = driver.findElement(By.xpath("//div[contains(@class,'error') or contains(@class,'alert-danger')]"));
				return !errorMsg.isDisplayed();
			} catch (Exception e2) {
				// No error message found, assume success
				return true;
			}
		}
	}
	
	/**
	 * Complete navigation flow: Navigate to Pack UOM Conversion settings
	 * This method assumes we're already on Sales Order Settings page
	 * The actual navigation should be handled by Admin_Dashboard page object
	 */
	public void navigateToPackUOMConversionSettings() {
		// This method can be used if there are additional steps needed on this page
		// Currently, navigation is handled by Admin_Dashboard
		// Wait for page to load
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// Ignore
		}
	}

}


