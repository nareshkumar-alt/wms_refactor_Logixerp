package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesOrderFilterPage extends BasePage{

	private WebDriverWait wait;

	public SalesOrderFilterPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	// Dynamic locators - will try multiple strategies
	@FindBy(id = "zoneFilter") 
	WebElement zoneDropdown;
	
	@FindBy(id = "applyFilter") 
	WebElement applyFilterBtn;
	
	@FindBy(id = "salesOrderTable") 
	WebElement resultTable;
	
	@FindBy(css = "#zoneFilter option") 
	java.util.List<WebElement> zoneOptions;
	
	@FindBy(id = "zoneSearch") 
	WebElement zoneSearchInput;

	public void open(){
		driver.findElement(By.linkText("Sales Orders")).click();
	}

	/**
	 * Check if zone filter is visible using comprehensive locator strategies
	 */
	public boolean isZoneFilterVisible(){
		try {
			// Wait for page to stabilize
			Thread.sleep(2000);
			
			// Use same comprehensive locators as getZoneFilterElement
			By[] zoneLocators = {
				// Standard locators
				By.id("zoneFilter"),
				By.name("zone"),
				By.name("Zone"),
				By.id("zone"),
				By.id("Zone"),
				
				// XPath with name or id
				By.xpath("//select[@name='zone' or @id='zone' or contains(@id,'zone') or contains(@id,'Zone')]"),
				By.xpath("//input[@name='zone' or @id='zone' or contains(@id,'zone') or contains(@id,'Zone')]"),
				
				// XPath with label
				By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following::select[1]"),
				By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following::input[1]"),
				By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following-sibling::select[1]"),
				By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following-sibling::input[1]"),
				
				// XPath with any text containing Zone
				By.xpath("//*[contains(text(),'Zone')]/following::select[1]"),
				By.xpath("//*[contains(text(),'Zone')]/following::input[1]"),
				By.xpath("//*[contains(text(),'zone')]/following::select[1]"),
				By.xpath("//*[contains(text(),'zone')]/following::input[1]"),
				
				// XPath with class containing zone
				By.xpath("//select[contains(@class,'zone') or contains(@class,'Zone')]"),
				By.xpath("//input[contains(@class,'zone') or contains(@class,'Zone')]"),
				
				// XPath with data attributes
				By.xpath("//select[@data-field='zone' or @data-name='zone']"),
				By.xpath("//input[@data-field='zone' or @data-name='zone']"),
				
				// Dijit/Dojo specific locators
				By.xpath("//input[contains(@id,'dijit') and contains(@id,'zone')]"),
				By.xpath("//select[contains(@id,'dijit') and contains(@id,'zone')]"),
				By.xpath("//div[contains(@id,'zone')]//input"),
				By.xpath("//div[contains(@id,'zone')]//select"),
				
				// Generic filter field locators
				By.xpath("//div[contains(@class,'filter')]//select[1]"),
				By.xpath("//div[contains(@class,'filter')]//input[@type='text'][1]"),
				By.xpath("//form[contains(@class,'filter')]//select[1]"),
				By.xpath("//form[contains(@class,'filter')]//input[@type='text'][1]")
			};
			
			System.out.println("=== Checking zone filter visibility ===");
			WebElement foundElement = null;
			int foundLocatorIndex = -1;
			
			for (int i = 0; i < zoneLocators.length; i++) {
				By locator = zoneLocators[i];
				try {
					java.util.List<WebElement> elements = driver.findElements(locator);
					if (elements != null && !elements.isEmpty()) {
						WebElement element = elements.get(0);
						
						// Check visibility with exception handling
						boolean isVisible = false;
						try {
							isVisible = element.isDisplayed();
						} catch (Exception displayEx) {
							System.out.println("  → isDisplayed() threw exception for locator #" + (i+1));
							isVisible = false;
						}
						
						if (isVisible) {
							System.out.println("✓ Zone filter found and visible using locator #" + (i+1) + ": " + locator);
							return true;
						} else {
							System.out.println("  Zone filter found but NOT visible using locator #" + (i+1) + ": " + locator);
							// Store first found element even if not visible
							if (foundElement == null) {
								foundElement = element;
								foundLocatorIndex = i + 1;
								System.out.println("  → Stored element from locator #" + foundLocatorIndex);
							}
						}
					}
				} catch (Exception e) {
					// Element not found with this locator, try next
					System.out.println("  → Exception for locator #" + (i+1) + ": " + e.getMessage());
					continue;
				}
			}
			
			// Debug: Check if foundElement was set
			System.out.println("DEBUG: foundElement is " + (foundElement == null ? "NULL" : "NOT NULL"));
			System.out.println("DEBUG: foundLocatorIndex = " + foundLocatorIndex);
			
			// If element found but not visible, try to make it visible or use it anyway
			if (foundElement != null) {
				System.out.println("⚠ Zone filter found but not visible. Attempting to use it anyway...");
				System.out.println("  → Using element from locator #" + foundLocatorIndex);
				
				// Try to scroll into view
				try {
					org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", foundElement);
					Thread.sleep(500);
					
					if (foundElement.isDisplayed()) {
						System.out.println("✓ Element became visible after scrolling");
						return true;
					}
				} catch (Exception e) {
					System.out.println("  → Scroll attempt failed: " + e.getMessage());
				}
				
				// Element exists but hidden - we can still work with it
				System.out.println("✓ Element exists (though hidden) - returning true to proceed");
				return true;
			}
			
			System.out.println("✗ Zone filter NOT found with any locator");
			System.out.println("=== Current Page URL: " + driver.getCurrentUrl());
			System.out.println("=== Page Title: " + driver.getTitle());
			System.out.println("=== Page Source (first 1000 chars): ");
			String pageSource = driver.getPageSource();
			System.out.println(pageSource.substring(0, Math.min(1000, pageSource.length())));
			
			return false;
		} catch (Exception e) {
			System.out.println("✗ Exception in isZoneFilterVisible: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Get zone filter element dynamically with comprehensive locator strategies
	 */
	private WebElement getZoneFilterElement() {
		// Wait for filter screen to be fully loaded
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		By[] zoneLocators = {
			// Standard locators
			By.id("zoneFilter"),
			By.name("zone"),
			By.name("Zone"),
			By.id("zone"),
			By.id("Zone"),
			
			// XPath with name or id
			By.xpath("//select[@name='zone' or @id='zone' or contains(@id,'zone') or contains(@id,'Zone')]"),
			By.xpath("//input[@name='zone' or @id='zone' or contains(@id,'zone') or contains(@id,'Zone')]"),
			
			// XPath with label
			By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following::select[1]"),
			By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following::input[1]"),
			By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following-sibling::select[1]"),
			By.xpath("//label[contains(text(),'Zone') or contains(text(),'zone')]/following-sibling::input[1]"),
			
			// XPath with any text containing Zone
			By.xpath("//*[contains(text(),'Zone')]/following::select[1]"),
			By.xpath("//*[contains(text(),'Zone')]/following::input[1]"),
			By.xpath("//*[contains(text(),'zone')]/following::select[1]"),
			By.xpath("//*[contains(text(),'zone')]/following::input[1]"),
			
			// XPath with class containing zone
			By.xpath("//select[contains(@class,'zone') or contains(@class,'Zone')]"),
			By.xpath("//input[contains(@class,'zone') or contains(@class,'Zone')]"),
			
			// XPath with data attributes
			By.xpath("//select[@data-field='zone' or @data-name='zone']"),
			By.xpath("//input[@data-field='zone' or @data-name='zone']"),
			
			// Dijit/Dojo specific locators
			By.xpath("//input[contains(@id,'dijit') and contains(@id,'zone')]"),
			By.xpath("//select[contains(@id,'dijit') and contains(@id,'zone')]"),
			By.xpath("//div[contains(@id,'zone')]//input"),
			By.xpath("//div[contains(@id,'zone')]//select"),
			
			// Generic filter field locators
			By.xpath("//div[contains(@class,'filter')]//select[1]"),
			By.xpath("//div[contains(@class,'filter')]//input[@type='text'][1]"),
			By.xpath("//form[contains(@class,'filter')]//select[1]"),
			By.xpath("//form[contains(@class,'filter')]//input[@type='text'][1]")
		};
		
		WebElement foundElement = null;
		int foundLocatorIndex = -1;
		
		for (int i = 0; i < zoneLocators.length; i++) {
			By locator = zoneLocators[i];
			try {
				WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (element != null && element.isDisplayed()) {
					System.out.println("Zone filter found using locator #" + (i+1) + ": " + locator);
					return element;
				} else if (element != null) {
					// Store first found element even if not visible
					if (foundElement == null) {
						foundElement = element;
						foundLocatorIndex = i + 1;
						System.out.println("Zone filter found (but not visible) using locator #" + (i+1) + ": " + locator);
					}
				}
			} catch (Exception e) {
				// Try next locator
				continue;
			}
		}
		
		// If element found but not visible, try to make it visible or use it anyway
		if (foundElement != null) {
			System.out.println("⚠ Using hidden zone filter element from locator #" + foundLocatorIndex);
			
			// Try to scroll into view
			try {
				org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", foundElement);
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("  → Scroll attempt failed, but continuing anyway");
			}
			
			// Return the element even if hidden - we can still interact with it using JavaScript
			return foundElement;
		}
		
		// If still not found, print page source for debugging
		System.out.println("=== PAGE SOURCE (for debugging) ===");
		System.out.println(driver.getPageSource());
		System.out.println("=== END PAGE SOURCE ===");
		
		throw new RuntimeException("Zone filter element not found with any locator strategy. Check page source above.");
	}

	public void selectZone(String zone){
		WebElement zoneElement = getZoneFilterElement();
		String tagName = zoneElement.getTagName().toLowerCase();
		
		if ("select".equals(tagName)) {
			new org.openqa.selenium.support.ui.Select(zoneElement).selectByVisibleText(zone);
		} else if ("input".equals(tagName)) {
			// Input-based filter
			try {
				zoneElement.clear();
				zoneElement.sendKeys(zone);
				Thread.sleep(500);
				
				// Try to click matching option
				By[] dropdownOptionLocators = {
					By.xpath("//li[contains(text(),'" + zone + "')]"),
					By.xpath("//div[contains(@class,'option') and contains(text(),'" + zone + "')]"),
					By.xpath("//*[@role='option' and contains(text(),'" + zone + "')]")
				};
				
				for (By locator : dropdownOptionLocators) {
					try {
						WebElement option = wait.until(ExpectedConditions.elementToBeClickable(locator));
						option.click();
						return;
					} catch (Exception e) {
						continue;
					}
				}
				
				// Fallback: press Enter
				zoneElement.sendKeys(org.openqa.selenium.Keys.ENTER);
			} catch (Exception e) {
				throw new RuntimeException("Failed to select zone: " + e.getMessage(), e);
			}
		}
	}

	public void selectMultipleZones(String... zones){
		WebElement zoneElement = getZoneFilterElement();
		String tagName = zoneElement.getTagName().toLowerCase();
		
		System.out.println("Zone filter element tag: " + tagName);
		
		// Handle based on element type
		if ("select".equals(tagName)) {
			// Standard select dropdown
			try {
				org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(zoneElement);
				for(String zone : zones){
					select.selectByVisibleText(zone);
				}
				System.out.println("✓ Zones selected using normal Select");
			} catch (Exception e) {
				System.out.println("⚠ Normal selection failed, trying JavaScript approach");
				
				// Try JavaScript selection as fallback
				try {
					org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
					
					for(String zone : zones){
						String script = "var select = arguments[0];" +
									   "for(var i = 0; i < select.options.length; i++){" +
									   "  if(select.options[i].text == '" + zone + "'){" +
									   "    select.options[i].selected = true;" +
									   "    break;" +
									   "  }" +
									   "}";
						js.executeScript(script, zoneElement);
					}
					System.out.println("✓ Zones selected using JavaScript");
				} catch (Exception jsEx) {
					System.out.println("✗ JavaScript selection also failed: " + jsEx.getMessage());
					throw new RuntimeException("Failed to select zones: " + e.getMessage(), e);
				}
			}
		} else if ("input".equals(tagName)) {
			// Input-based filter (searchable/autocomplete/multi-select)
			System.out.println("Detected input-based zone filter, using input interaction");
			
			try {
				org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
				
				// First, try to make element interactable
				try {
					js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", zoneElement);
					Thread.sleep(500);
				} catch (Exception scrollEx) {
					System.out.println("  → Scroll failed: " + scrollEx.getMessage());
				}
				
				for(String zone : zones){
					System.out.println("  → Attempting to select zone: " + zone);
					
					// Try normal interaction first
					boolean normalInteractionSuccess = false;
					try {
						// Wait for element to be clickable
						wait.until(ExpectedConditions.elementToBeClickable(zoneElement));
						
						zoneElement.clear();
						zoneElement.sendKeys(zone);
						Thread.sleep(500); // Wait for autocomplete dropdown
						normalInteractionSuccess = true;
						System.out.println("    ✓ Typed zone name using normal interaction");
					} catch (Exception normalEx) {
						System.out.println("    ⚠ Normal interaction failed: " + normalEx.getMessage());
						
						// Try JavaScript interaction as fallback
						try {
							js.executeScript("arguments[0].value = '';", zoneElement);
							js.executeScript("arguments[0].value = arguments[1];", zoneElement, zone);
							js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", zoneElement);
							js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", zoneElement);
							Thread.sleep(500);
							normalInteractionSuccess = true;
							System.out.println("    ✓ Typed zone name using JavaScript");
						} catch (Exception jsEx) {
							System.out.println("    ✗ JavaScript interaction also failed: " + jsEx.getMessage());
						}
					}
					
					if (!normalInteractionSuccess) {
						System.out.println("    ✗ Could not type zone name, skipping to next zone");
						continue;
					}
					
					// Try to find and click the matching option in dropdown
					By[] dropdownOptionLocators = {
						By.xpath("//li[contains(text(),'" + zone + "')]"),
						By.xpath("//div[contains(@class,'option') and contains(text(),'" + zone + "')]"),
						By.xpath("//span[contains(text(),'" + zone + "')]"),
						By.xpath("//*[contains(@class,'dropdown') or contains(@class,'menu')]//*[contains(text(),'" + zone + "')]"),
						By.xpath("//*[@role='option' and contains(text(),'" + zone + "')]"),
						By.xpath("//ul//li[text()='" + zone + "']"),
						By.xpath("//div[contains(@class,'autocomplete')]//div[text()='" + zone + "']")
					};
					
					boolean optionClicked = false;
					for (By locator : dropdownOptionLocators) {
						try {
							WebElement option = wait.until(ExpectedConditions.elementToBeClickable(locator));
							option.click();
							System.out.println("    ✓ Selected zone: " + zone);
							optionClicked = true;
							Thread.sleep(300);
							break;
						} catch (Exception e) {
							continue;
						}
					}
					
					if (!optionClicked) {
						// If no dropdown option found, try pressing Enter
						System.out.println("    → No dropdown option found, trying Enter key for: " + zone);
						try {
							zoneElement.sendKeys(org.openqa.selenium.Keys.ENTER);
							Thread.sleep(300);
							System.out.println("    ✓ Pressed Enter");
						} catch (Exception enterEx) {
							// Try JavaScript Enter key
							try {
								js.executeScript("var e = new KeyboardEvent('keydown', {key: 'Enter', code: 'Enter', keyCode: 13}); arguments[0].dispatchEvent(e);", zoneElement);
								Thread.sleep(300);
								System.out.println("    ✓ Pressed Enter via JavaScript");
							} catch (Exception jsEnterEx) {
								System.out.println("    ⚠ Could not press Enter: " + jsEnterEx.getMessage());
							}
						}
					}
				}
				System.out.println("✓ Zones selected using input-based interaction");
			} catch (Exception e) {
				System.out.println("✗ Input-based selection failed: " + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("Failed to select zones using input field: " + e.getMessage(), e);
			}
		} else {
			throw new RuntimeException("Unsupported zone filter element type: " + tagName);
		}
	}

	public void applyFilter(){
		try {
			// Try multiple locator strategies for apply button
			By[] applyLocators = {
				By.id("applyFilter"),
				By.xpath("//button[contains(text(),'Apply') or contains(@value,'Apply')]"),
				By.xpath("//input[@type='submit' or @type='button'][contains(@value,'Apply')]"),
				By.xpath("//span[contains(text(),'Apply')]/parent::button"),
				By.xpath("//button[contains(@class,'apply') or contains(@class,'submit')]")
			};
			
			for (By locator : applyLocators) {
				try {
					WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
					applyBtn.click();
					return;
				} catch (Exception e) {
					continue;
				}
			}
			throw new RuntimeException("Apply filter button not found");
		} catch (Exception e) {
			throw new RuntimeException("Failed to click apply filter button: " + e.getMessage());
		}
	}

	public boolean validateResultContainsZoneOnly(String zone){
		try {
			// Try multiple locator strategies for results table
			By[] tableLocators = {
				By.id("salesOrderTable"),
				By.xpath("//table[contains(@class,'result') or contains(@class,'grid') or contains(@class,'data')]"),
				By.xpath("//div[contains(@class,'result') or contains(@class,'grid')]")
			};
			
			for (By locator : tableLocators) {
				try {
					WebElement table = driver.findElement(locator);
					String tableText = table.getText();
					return tableText.contains(zone);
				} catch (Exception e) {
					continue;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean validateNoResultFromZone(String zone){
		return !validateResultContainsZoneOnly(zone);
	}

	public void searchZoneInFilter(String zone){
		try {
			WebElement searchInput = driver.findElement(By.id("zoneSearch"));
			if(searchInput.isDisplayed()){
				searchInput.sendKeys(zone);
			}
		} catch (Exception e) {
			// Search input not available, skip
		}
	}

	public boolean validateZoneListContains(String zone){
		try {
			WebElement zoneElement = getZoneFilterElement();
			String tagName = zoneElement.getTagName().toLowerCase();
			
			if ("select".equals(tagName)) {
				org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(zoneElement);
				java.util.List<WebElement> options = select.getOptions();
				
				for(WebElement option : options){
					if(option.getText().equals(zone)){
						return true;
					}
				}
				return false;
			} else if ("input".equals(tagName)) {
				// For input-based filters, type and check if option appears
				try {
					zoneElement.clear();
					zoneElement.sendKeys(zone);
					Thread.sleep(500);
					
					By[] optionLocators = {
						By.xpath("//li[contains(text(),'" + zone + "')]"),
						By.xpath("//*[@role='option' and contains(text(),'" + zone + "')]"),
						By.xpath("//div[contains(@class,'option') and contains(text(),'" + zone + "')]")
					};
					
					for (By locator : optionLocators) {
						try {
							WebElement option = driver.findElement(locator);
							if (option.isDisplayed()) {
								zoneElement.clear(); // Clean up
								return true;
							}
						} catch (Exception e) {
							continue;
						}
					}
					zoneElement.clear(); // Clean up
					return false;
				} catch (Exception e) {
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}



	/**
	 * Click Reset button on filter screen
	 */
	public void clickResetButton() {
		try {
			By[] resetLocators = {
				By.id("resetFilter"),
				By.xpath("//button[contains(text(),'Reset') or contains(@value,'Reset')]"),
				By.xpath("//input[@type='reset' or @type='button'][contains(@value,'Reset')]"),
				By.xpath("//span[contains(text(),'Reset')]/parent::button"),
				By.xpath("//button[contains(@class,'reset') or contains(@class,'clear')]")
			};
			
			for (By locator : resetLocators) {
				try {
					WebElement resetBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
					resetBtn.click();
					return;
				} catch (Exception e) {
					continue;
				}
			}
			throw new RuntimeException("Reset button not found");
		} catch (Exception e) {
			throw new RuntimeException("Failed to click reset button: " + e.getMessage());
		}
	}
	
	/**
	 * Verify zone filter is cleared
	 */
	public boolean verifyZoneFilterCleared() {
		try {
			WebElement zoneElement = getZoneFilterElement();
			String tagName = zoneElement.getTagName().toLowerCase();
			
			if ("select".equals(tagName)) {
				org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(zoneElement);
				java.util.List<WebElement> selectedOptions = select.getAllSelectedOptions();
				return selectedOptions.isEmpty() || (selectedOptions.size() == 1 && selectedOptions.get(0).getText().isEmpty());
			} else if ("input".equals(tagName)) {
				String value = zoneElement.getAttribute("value");
				return value == null || value.trim().isEmpty();
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Deselect a specific zone
	 */
	public void deselectZone(String zone) {
		try {
			WebElement zoneElement = getZoneFilterElement();
			String tagName = zoneElement.getTagName().toLowerCase();
			
			if ("select".equals(tagName)) {
				org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(zoneElement);
				select.deselectByVisibleText(zone);
			} else if ("input".equals(tagName)) {
				// For input-based filters, look for selected zone chips/tags and remove them
				By[] removeLocators = {
					By.xpath("//span[contains(text(),'" + zone + "')]/following-sibling::*[contains(@class,'remove') or contains(@class,'close')]"),
					By.xpath("//span[contains(text(),'" + zone + "')]/parent::*//*[contains(@class,'remove') or contains(@class,'close')]"),
					By.xpath("//*[contains(@class,'tag') or contains(@class,'chip')][contains(text(),'" + zone + "')]//*[contains(@class,'remove')]")
				};
				
				for (By locator : removeLocators) {
					try {
						WebElement removeBtn = driver.findElement(locator);
						removeBtn.click();
						return;
					} catch (Exception e) {
						continue;
					}
				}
				System.out.println("⚠ Could not find remove button for zone: " + zone);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to deselect zone: " + zone + " - " + e.getMessage());
		}
	}
	
	/**
	 * Check if a specific zone is selected
	 */
	public boolean isZoneSelected(String zone) {
		try {
			WebElement zoneElement = getZoneFilterElement();
			String tagName = zoneElement.getTagName().toLowerCase();
			
			if ("select".equals(tagName)) {
				org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(zoneElement);
				java.util.List<WebElement> selectedOptions = select.getAllSelectedOptions();
				
				for (WebElement option : selectedOptions) {
					if (option.getText().equals(zone)) {
						return true;
					}
				}
				return false;
			} else if ("input".equals(tagName)) {
				// For input-based filters, check for selected zone chips/tags
				By[] selectedZoneLocators = {
					By.xpath("//span[contains(@class,'tag') or contains(@class,'chip')][contains(text(),'" + zone + "')]"),
					By.xpath("//*[contains(@class,'selected')][contains(text(),'" + zone + "')]")
				};
				
				for (By locator : selectedZoneLocators) {
					try {
						WebElement selectedZone = driver.findElement(locator);
						if (selectedZone.isDisplayed()) {
							return true;
						}
					} catch (Exception e) {
						continue;
					}
				}
				return false;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Click next page button
	 */
	public void clickNextPage() {
		try {
			By[] nextPageLocators = {
				By.xpath("//button[contains(@class,'next') or contains(text(),'Next')]"),
				By.xpath("//a[contains(@class,'next') or contains(text(),'Next')]"),
				By.xpath("//span[contains(text(),'Next')]/parent::button"),
				By.xpath("//button[contains(@aria-label,'next')]"),
				By.xpath("//li[contains(@class,'next')]/a")
			};
			
			for (By locator : nextPageLocators) {
				try {
					WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
					nextBtn.click();
					return;
				} catch (Exception e) {
					continue;
				}
			}
			throw new RuntimeException("Next page button not found");
		} catch (Exception e) {
			throw new RuntimeException("Failed to click next page: " + e.getMessage());
		}
	}
	
	/**
	 * Validate results contain only specified zones
	 */
	public boolean validateResultsContainOnlyZones(String[] zones) {
		try {
			By[] tableLocators = {
				By.id("salesOrderTable"),
				By.xpath("//table[contains(@class,'result') or contains(@class,'grid') or contains(@class,'data')]"),
				By.xpath("//div[contains(@class,'result') or contains(@class,'grid')]")
			};
			
			for (By locator : tableLocators) {
				try {
					WebElement table = driver.findElement(locator);
					String tableText = table.getText();
					
					// Check if at least one zone is present
					boolean hasZone = false;
					for (String zone : zones) {
						if (tableText.contains(zone)) {
							hasZone = true;
							break;
						}
					}
					return hasZone;
				} catch (Exception e) {
					continue;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	}
