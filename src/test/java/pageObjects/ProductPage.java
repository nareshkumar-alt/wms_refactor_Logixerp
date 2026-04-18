package pageObjects;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
	private static final Logger logger = LogManager.getLogger(ProductPage.class);
	private final WebDriverWait wait;
	private final JavascriptExecutor js;

public ProductPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.js = (JavascriptExecutor) driver;
	}

	// Navigation Elements
	@FindBy(xpath = "//a[normalize-space()='Master Data']")
	WebElement masterDataMenu;

	@FindBy(xpath = "//span[normalize-space()='Product']")
	WebElement productSubmodule;

	// Add Product Elements
	@FindBy(xpath = "//span[@id='logixerp_marketing_product_header_create_button']//button")
	WebElement createProductButton;

	// Product Form Elements - Based on Screenshot
	@FindBy(xpath = "//input[@name='code']")
	WebElement productCodeField;

	@FindBy(xpath = "//input[@id='dijitv2_dijit_form_ValidationTextBox_15']")
	WebElement productNameField;

	@FindBy(xpath = "//input[@name='barcode']")
	WebElement barcodeField;

	@FindBy(xpath = "//input[@name='eanCode']")
	WebElement eanBarcodeField;

	@FindBy(xpath = "//input[@id='dijitv2_dijit_form_FilteringSelect_12']")
	WebElement packageTypeDropdown;

	@FindBy(xpath = "//input[@name='pickingRules']")
	WebElement packingBasisField;

	@FindBy(xpath = "//input[@name='brand']")
	WebElement brandField;

	@FindBy(xpath = "//input[@name='selectedProductCategory']")
	WebElement categoryDropdown;

	@FindBy(xpath = "//input[@id='wmsmaster_products_supplier_combo']")
	WebElement supplierDropdown;

	@FindBy(xpath = "//input[@name='supplierCode']")
	WebElement supplierProductCodeField;
	
	@FindBy(xpath = "//input[@name='packageUnitMeasurement']")
	List<WebElement>packagemeasurementdropdown;

	@FindBy(xpath = "//input[@name='minimumLevel']")
	WebElement warehouseStockminField;
	
	@FindBy(xpath ="//input[@name='maximumLevel']")
	WebElement warehousemaxfield;
	
	@FindBy(xpath = "//input[@name='warehouseStockLimit']")
	WebElement warehouseStockLimitField;

	@FindBy(xpath = "//input[@name='buyingPrice']")
	WebElement buyingPriceField;

	@FindBy(xpath = "//input[@id='dijitv2_dijit_form_NumberTextBox_1']")
	WebElement sellingPriceField;

	@FindBy(xpath = "//input[@name='unitPrice']")
	WebElement unitPriceField;

	@FindBy(xpath ="//input[@name='volume']")
	WebElement volume;
	
	@FindBy(xpath ="//input[@name='shelfLife']")
	WebElement shelflifefield;
	
	@FindBy(xpath ="//input[@name='hsnCode']")
	WebElement hscodefield;
	
	@FindBy(xpath ="//input[@name='eanCode']")
	WebElement eancodefield;
	
	@FindBy(xpath = "//input[@name='gstRate']")
	WebElement gstRateField;
	@FindBy(xpath ="//input[@id='wmsmaster_createProduct_eanScannable_checkboxId']")
	WebElement eancheckbox;
	
	@FindBy(xpath ="//div[@widgetid='wmsmaster_createProducts_OU_field']")
	List<WebElement> operatingunitdropdown;
	
	@FindBy(xpath ="//input[@name='composition']")
	WebElement composition;

	@FindBy(xpath = "//select[@name='unitOfMeasurement']")
	WebElement unitOfMeasurementDropdown;
	
	@FindBy(xpath = "//input[@name='composition']")
	WebElement compositionField;

	@FindBy(xpath ="//input[@id='dijitv2_dijit_form_FilteringSelect_7']")
	List<WebElement>Palletization_Rule;
	@FindBy(xpath ="//input[@name='binStandard']")
	WebElement palletization_qty;
	
	@FindBy(xpath = "//input[@name='packType']")
	WebElement packTypeDropdown;

	@FindBy(xpath = "//input[@name='sellableUnit']")
	List<WebElement> sellablepackDropdown;
	
	@FindBy(xpath = "//select[@name='packSize']")
	WebElement packSizeDropdown;

	@FindBy(xpath = "//input[@name='manufacturer']")
	WebElement manufacturerField;

	@FindBy(xpath = "//textarea[@name='description']")
	WebElement descriptionField;

	@FindBy(xpath = "//input[@type='checkbox' and @name='isConsumable']")
	WebElement isConsumableCheckbox;

	@FindBy(xpath = "//input[@type='checkbox' and @name='isExpirable']")
	WebElement isExpirableCheckbox;
	
	@FindBy(xpath ="//input[@id='dijitv2_dijit_form_FilteringSelect_10']")
List<WebElement> type;
	
	@FindBy(xpath ="//th[normalize-space()='Part Number']")
	WebElement part_number;
	
	@FindBy(xpath ="//th[normalize-space()='SKU Pack Configuration']")
	WebElement sku_pack_config;
	
	@FindBy(xpath ="//th[normalize-space()='Name']")
	WebElement name;
	
	@FindBy(xpath ="//th[normalize-space()='EAN Number']")
	WebElement eannum;
	
	@FindBy(xpath = "//th[normalize-space()='Color']")
	WebElement color;
	

	@FindBy(xpath ="//th[normalize-space()='Size']")
	WebElement size;
	
	@FindBy(xpath ="//th[normalize-space()='Dimension UOM']")
	WebElement Dimension_Uom;
	
	@FindBy(xpath ="//th[normalize-space()='L']")
	WebElement lenghth;
	
	@FindBy(xpath = "//th[normalize-space()='B']")
	WebElement breadth;
	
	@FindBy(xpath ="//th[normalize-space()='H']")
	WebElement height;
	
	@FindBy(xpath ="//span[@class='dijitPlaceHolder dijitInputField' and   normalize-space()='Volume']")
	WebElement volume1;
	
	@FindBy(xpath ="//span[@class='dijitPlaceHolder dijitInputField' and   normalize-space()='Weight']")
	WebElement weight;
	
	@FindBy(xpath ="//input[@class='dijitReset dijitInputInner' and   @placeholder='Unit Price']")
	WebElement unitprice;
	
	@FindBy(xpath ="//input[@class='dijitReset dijitInputInner' and   @placeholder='Quantity']")
	WebElement quantity;
	
	@FindBy(xpath ="//input[@class='dijitReset dijitInputInner' and   @placeholder='SubItem Quantity']")
	WebElement subitem_quantity;
	
	@FindBy(xpath ="//textarea[@name='skuDescriptionList']")
	WebElement description;
	
	@FindBy(xpath ="//th[normalize-space()='Scannable']")
	WebElement scannable;
	
	@FindBy(xpath ="//button[normalize-space()='Customer']")
	WebElement customer;
	@FindBy(xpath ="//input[@name='customerCodeList']")
	WebElement customercode;
	@FindBy(xpath ="//span[normalize-space()='Customer Product Code']")
	WebElement productcode;
	
	@FindBy(xpath ="//span[@class='cursor' and @onclick='logixerp_wmsmaster_removeRowFromProductMasterSKUDetails('logixerp_wmsmaster_productCustomer_tbodyRow')']")
	WebElement removecustomer;
	
	
	
	
	// Action Buttons
	@FindBy(xpath = "//span[normalize-space()='Save' and @class='dijitReset dijitInline dijitButtonText' and @id='dijitv2_dijit_form_Button_32_label' ]")
	WebElement saveButton;

	@FindBy(xpath = "//a[@href='#' and  normalize-space()='Cancel' and @class='blue-icon mt-6' ]")
	WebElement cancelButton;

	@FindBy(xpath = "//button[contains(text(),'Reset')] | //span[contains(text(),'Reset')]")
	WebElement resetButton;

	// Verification Elements
	@FindBy(xpath = "//div[contains(@class,'success')] | //*[contains(text(),'successfully created') or contains(text(),'Success')]")
	WebElement successMessage;

	@FindBy(xpath = "//div[contains(@class,'error')] | //*[contains(text(),'Error') or contains(text(),'Failed')]")
	WebElement errorMessage;

	@FindBy(xpath = "//table[@id='productTable'] | //table[contains(@class,'product')] | //div[contains(@class,'grid')]//table")
	WebElement productTable;

	@FindBy(xpath = "//table//tbody//tr")
	List<WebElement> productRows;

	// Navigation Methods
	public void navigateToMasterData() {
		wait.until(ExpectedConditions.elementToBeClickable(masterDataMenu));
		js.executeScript("arguments[0].scrollIntoView(true);", masterDataMenu);
		try {
			masterDataMenu.click();
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", masterDataMenu);
		}
		logger.info("Clicked on Master Data menu");
	}

	public void navigateToProductSubmodule() {
		wait.until(ExpectedConditions.elementToBeClickable(productSubmodule));
		js.executeScript("arguments[0].scrollIntoView(true);", productSubmodule);
		try {
			productSubmodule.click();
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", productSubmodule);
		}
		logger.info("Clicked on Product submodule");
	}

	public void navigateToProductModule() {
		logger.info("Starting navigation to Product module");
		
		// Step 1: Navigate to Master Data
		navigateToMasterData();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		// Step 2: Navigate to Product Submodule
		navigateToProductSubmodule();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		// Step 3: Check if we need to click "Add Product" or "Create Product"
		logger.info("Checking if Product form is already visible or if we need to click Add/Create");
		
		// Check if form is already visible
		boolean formVisible = isProductFormVisible();
		if (formVisible) {
			logger.info("✅ Product form is already visible");
			return;
		}
		
		// If form not visible, try to find and click Add/Create Product button
		logger.info("Product form not visible, looking for Add/Create Product button");
		boolean buttonClicked = clickAddOrCreateProduct();
		
		if (buttonClicked) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			// Verify form is now visible
			formVisible = isProductFormVisible();
			if (formVisible) {
				logger.info("✅ Product form is now visible after clicking Add/Create button");
			} else {
				logger.warn("⚠️ Product form still not visible after clicking button");
			}
		} else {
			logger.warn("⚠️ Could not find Add/Create Product button");
		}
		
		logger.info("Navigation to Product module completed");
	}

	private boolean isProductFormVisible() {
		try {
			// Check for Product Code field (mandatory field)
			List<WebElement> codeFields = driver.findElements(By.xpath("//input[@name='productCode'] | //input[contains(@name,'productCode')]"));
			if (!codeFields.isEmpty() && codeFields.get(0).isDisplayed()) {
				logger.info("Product form detected: Product Code field found");
				return true;
			}
			
			// Check for Product Name field
			List<WebElement> nameFields = driver.findElements(By.xpath("//input[@name='productName'] | //input[contains(@name,'productName')]"));
			if (!nameFields.isEmpty() && nameFields.get(0).isDisplayed()) {
				logger.info("Product form detected: Product Name field found");
				return true;
			}
			
			// Check for "Add Product" text on page
			String pageSource = driver.getPageSource();
			if (pageSource.contains("Add Product") && (pageSource.contains("Product Code") || pageSource.contains("productCode"))) {
				logger.info("Product form detected: Add Product page content found");
				return true;
			}
			
			return false;
		} catch (Exception e) {
			logger.debug("Error checking form visibility: " + e.getMessage());
			return false;
		}
	}

	private boolean clickAddOrCreateProduct() {
		boolean clicked = false;
		
		// Strategy 1: Use the correct Create Product button locator
		try {
			wait.until(ExpectedConditions.elementToBeClickable(createProductButton));
			js.executeScript("arguments[0].scrollIntoView(true);", createProductButton);
			Thread.sleep(500);
			js.executeScript("arguments[0].click();", createProductButton);
			clicked = true;
			logger.info("✅ Strategy 1: Create Product button clicked using correct locator");
		} catch (Exception e1) {
			logger.debug("Strategy 1 failed: " + e1.getMessage());
		}
		
		// Strategy 2: Look for "Add Product" button
		if (!clicked) {
			try {
				WebElement addBtn = driver.findElement(By.xpath("//button[contains(text(),'Add Product')] | //a[contains(text(),'Add Product')] | //*[contains(@onclick,'add') and contains(@onclick,'product')]"));
				js.executeScript("arguments[0].scrollIntoView(true);", addBtn);
				Thread.sleep(500);
				js.executeScript("arguments[0].click();", addBtn);
				clicked = true;
				logger.info("✅ Strategy 2: Add Product button clicked");
			} catch (Exception e2) {
				logger.debug("Strategy 2 failed: " + e2.getMessage());
			}
		}
		
		// Strategy 3: Look for "Create Product" button
		if (!clicked) {
			try {
				WebElement createBtn = driver.findElement(By.xpath("//button[contains(text(),'Create Product')] | //a[contains(text(),'Create Product')] | //*[contains(@onclick,'create') and contains(@onclick,'product')]"));
				js.executeScript("arguments[0].scrollIntoView(true);", createBtn);
				Thread.sleep(500);
				js.executeScript("arguments[0].click();", createBtn);
				clicked = true;
				logger.info("✅ Strategy 3: Create Product button clicked");
			} catch (Exception e3) {
				logger.debug("Strategy 3 failed: " + e3.getMessage());
			}
		}
		
		// Strategy 4: Look for generic "Add" button
		if (!clicked) {
			try {
				WebElement addBtn = driver.findElement(By.xpath("//button[contains(text(),'Add')] | //a[contains(text(),'Add')] | //*[contains(@class,'add')] | //i[contains(@class,'fa-plus')]"));
				js.executeScript("arguments[0].scrollIntoView(true);", addBtn);
				Thread.sleep(500);
				js.executeScript("arguments[0].click();", addBtn);
				clicked = true;
				logger.info("✅ Strategy 4: Generic Add button clicked");
			} catch (Exception e4) {
				logger.debug("Strategy 4 failed: " + e4.getMessage());
			}
		}
		
		return clicked;
	}

	// Form Filling Methods
	public void fillProductCode(String code) {
		logger.info("Attempting to fill Product Code: " + code);
		
		WebElement codeField = null;
		boolean fieldFound = false;
		
		// Strategy 1: Original locator
		try {
			codeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='productCode']")));
			fieldFound = true;
			logger.info("✅ Strategy 1: Found Product Code field with @name='productCode'");
		} catch (Exception e1) {
			logger.debug("Strategy 1 failed: " + e1.getMessage());
		}
		
		// Strategy 2: Contains productCode
		if (!fieldFound) {
			try {
				codeField = driver.findElement(By.xpath("//input[contains(@name,'productCode')]"));
				if (codeField.isDisplayed()) {
					fieldFound = true;
					logger.info("✅ Strategy 2: Found Product Code field with contains(@name,'productCode')");
				}
			} catch (Exception e2) {
				logger.debug("Strategy 2 failed: " + e2.getMessage());
			}
		}
		
		// Strategy 3: Look for field near "Product Code" label
		if (!fieldFound) {
			try {
				codeField = driver.findElement(By.xpath("//label[contains(text(),'Code')]/following-sibling::input | //label[contains(text(),'Code')]/..//input"));
				if (codeField.isDisplayed()) {
					fieldFound = true;
					logger.info("✅ Strategy 3: Found Product Code field near label");
				}
			} catch (Exception e3) {
				logger.debug("Strategy 3 failed: " + e3.getMessage());
			}
		}
		
		if (fieldFound && codeField != null) {
			try {
				js.executeScript("arguments[0].scrollIntoView(true);", codeField);
				Thread.sleep(500);
				js.executeScript("arguments[0].focus();", codeField);
				codeField.clear();
				Thread.sleep(300);
				codeField.sendKeys(code);
				Thread.sleep(300);
				codeField.sendKeys(Keys.TAB);
				logger.info("✅ Successfully filled Product Code: " + code);
			} catch (Exception e) {
				logger.error("❌ Error filling Product Code field: " + e.getMessage());
				throw new RuntimeException("Could not fill Product Code field", e);
			}
		} else {
			logger.error("❌ Product Code field not found with any strategy");
			throw new RuntimeException("Could not fill Product Code");
		}
	}

	public void fillProductName(String name) {
		try {
			wait.until(ExpectedConditions.visibilityOf(productNameField));
			js.executeScript("arguments[0].scrollIntoView(true);", productNameField);
			Thread.sleep(500);
			js.executeScript("arguments[0].focus();", productNameField);
			productNameField.clear();
			Thread.sleep(300);
			productNameField.sendKeys(name);
			Thread.sleep(300);
			productNameField.sendKeys(Keys.TAB);
			logger.info("✅ Product Name filled: " + name);
		} catch (Exception e) {
			logger.error("❌ Error filling Product Name: " + e.getMessage());
			throw new RuntimeException("Could not fill Product Name", e);
		}
	}

	public void fillBarcode(String barcode) {
		try {
			wait.until(ExpectedConditions.visibilityOf(barcodeField));
			js.executeScript("arguments[0].scrollIntoView(true);", barcodeField);
			Thread.sleep(500);
			barcodeField.clear();
			barcodeField.sendKeys(barcode);
			logger.info("✅ Barcode filled: " + barcode);
		} catch (Exception e) {
			logger.warn("⚠️ Barcode field not available: " + e.getMessage());
		}
	}

	public void fillEANBarcode(String eanBarcode) {
		try {
			wait.until(ExpectedConditions.visibilityOf(eanBarcodeField));
			js.executeScript("arguments[0].scrollIntoView(true);", eanBarcodeField);
			Thread.sleep(500);
			eanBarcodeField.clear();
			eanBarcodeField.sendKeys(eanBarcode);
			logger.info("✅ EAN Barcode filled: " + eanBarcode);
		} catch (Exception e) {
			logger.warn("⚠️ EAN Barcode field not available: " + e.getMessage());
		}
	}

	public void selectPackageType(String packageType) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(packageTypeDropdown));
			Select packageSelect = new Select(packageTypeDropdown);
			packageSelect.selectByVisibleText(packageType);
			logger.info("✅ Package Type selected: " + packageType);
		} catch (Exception e) {
			logger.warn("⚠️ Package Type field not available: " + e.getMessage());
		}
	}

	public void fillPackingBasis(String packingBasis) {
		try {
			wait.until(ExpectedConditions.visibilityOf(packingBasisField));
			js.executeScript("arguments[0].scrollIntoView(true);", packingBasisField);
			packingBasisField.clear();
			packingBasisField.sendKeys(packingBasis);
			logger.info("✅ Packing Basis filled: " + packingBasis);
		} catch (Exception e) {
			logger.warn("⚠️ Packing Basis field not available: " + e.getMessage());
		}
	}

	public void fillBrand(String brand) {
		try {
			wait.until(ExpectedConditions.visibilityOf(brandField));
			js.executeScript("arguments[0].scrollIntoView(true);", brandField);
			brandField.clear();
			brandField.sendKeys(brand);
			logger.info("✅ Brand filled: " + brand);
		} catch (Exception e) {
			logger.warn("⚠️ Brand field not available: " + e.getMessage());
		}
	}

	public void selectCategory(String category) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
			Select categorySelect = new Select(categoryDropdown);
			categorySelect.selectByVisibleText(category);
			logger.info("✅ Category selected: " + category);
		} catch (Exception e) {
			logger.warn("⚠️ Category field not available: " + e.getMessage());
		}
	}

	public void selectSupplier(String supplier) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(supplierDropdown));
			Select supplierSelect = new Select(supplierDropdown);
			supplierSelect.selectByVisibleText(supplier);
			logger.info("✅ Supplier selected: " + supplier);
		} catch (Exception e) {
			logger.warn("⚠️ Supplier field not available: " + e.getMessage());
		}
	}

	public void fillSupplierProductCode(String supplierProductCode) {
		try {
			wait.until(ExpectedConditions.visibilityOf(supplierProductCodeField));
			js.executeScript("arguments[0].scrollIntoView(true);", supplierProductCodeField);
			supplierProductCodeField.clear();
			supplierProductCodeField.sendKeys(supplierProductCode);
			logger.info("✅ Supplier Product Code filled: " + supplierProductCode);
		} catch (Exception e) {
			logger.warn("⚠️ Supplier Product Code field not available: " + e.getMessage());
		}
	}

	public void fillWarehouseStockLimit(String stockLimit) {
		try {
			wait.until(ExpectedConditions.visibilityOf(warehouseStockLimitField));
			js.executeScript("arguments[0].scrollIntoView(true);", warehouseStockLimitField);
			warehouseStockLimitField.clear();
			warehouseStockLimitField.sendKeys(stockLimit);
			logger.info("✅ Warehouse Stock Limit filled: " + stockLimit);
		} catch (Exception e) {
			logger.warn("⚠️ Warehouse Stock Limit field not available: " + e.getMessage());
		}
	}

	public void fillBuyingPrice(String buyingPrice) {
		try {
			wait.until(ExpectedConditions.visibilityOf(buyingPriceField));
			js.executeScript("arguments[0].scrollIntoView(true);", buyingPriceField);
			buyingPriceField.clear();
			buyingPriceField.sendKeys(buyingPrice);
			logger.info("✅ Buying Price filled: " + buyingPrice);
		} catch (Exception e) {
			logger.warn("⚠️ Buying Price field not available: " + e.getMessage());
		}
	}
	
	public void fillSellingPrice(String sellingPrice) {
		try {
			wait.until(ExpectedConditions.visibilityOf(sellingPriceField));
			js.executeScript("arguments[0].scrollIntoView(true);", sellingPriceField);
			sellingPriceField.clear();
			sellingPriceField.sendKeys(sellingPrice);
			logger.info("✅ Selling Price filled: " + sellingPrice);
		} catch (Exception e) {
			logger.warn("⚠️ Selling Price field not available: " + e.getMessage());
		}
	}

	public void fillUnitPrice(String unitPrice) {
		try {
			wait.until(ExpectedConditions.visibilityOf(unitPriceField));
			js.executeScript("arguments[0].scrollIntoView(true);", unitPriceField);
			unitPriceField.clear();
			unitPriceField.sendKeys(unitPrice);
			logger.info("✅ Unit Price filled: " + unitPrice);
		} catch (Exception e) {
			logger.warn("⚠️ Unit Price field not available: " + e.getMessage());
		}
	}

	public void fillGSTRate(String gstRate) {
		try {
			wait.until(ExpectedConditions.visibilityOf(gstRateField));
			js.executeScript("arguments[0].scrollIntoView(true);", gstRateField);
			gstRateField.clear();
			gstRateField.sendKeys(gstRate);
			logger.info("✅ GST Rate filled: " + gstRate);
		} catch (Exception e) {
			logger.warn("⚠️ GST Rate field not available: " + e.getMessage());
		}
	}

	public void selectUnitOfMeasurement(String uom) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(unitOfMeasurementDropdown));
			Select uomSelect = new Select(unitOfMeasurementDropdown);
			uomSelect.selectByVisibleText(uom);
			logger.info("✅ Unit of Measurement selected: " + uom);
		} catch (Exception e) {
			logger.warn("⚠️ Unit of Measurement field not available: " + e.getMessage());
		}
	}

	public void fillComposition(String composition) {
		try {
			wait.until(ExpectedConditions.visibilityOf(compositionField));
			js.executeScript("arguments[0].scrollIntoView(true);", compositionField);
			compositionField.clear();
			compositionField.sendKeys(composition);
			logger.info("✅ Composition filled: " + composition);
		} catch (Exception e) {
			logger.warn("⚠️ Composition field not available: " + e.getMessage());
		}
	}

	public void selectPackType(String packType) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(packTypeDropdown));
			Select packTypeSelect = new Select(packTypeDropdown);
			packTypeSelect.selectByVisibleText(packType);
			logger.info("✅ Pack Type selected: " + packType);
		} catch (Exception e) {
			logger.warn("⚠️ Pack Type field not available: " + e.getMessage());
		}
	}

	public void selectPackSize(String packSize) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(packSizeDropdown));
			Select packSizeSelect = new Select(packSizeDropdown);
			packSizeSelect.selectByVisibleText(packSize);
			logger.info("✅ Pack Size selected: " + packSize);
		} catch (Exception e) {
			logger.warn("⚠️ Pack Size field not available: " + e.getMessage());
		}
	}

	public void fillManufacturer(String manufacturer) {
		try {
			wait.until(ExpectedConditions.visibilityOf(manufacturerField));
			js.executeScript("arguments[0].scrollIntoView(true);", manufacturerField);
			manufacturerField.clear();
			manufacturerField.sendKeys(manufacturer);
			logger.info("✅ Manufacturer filled: " + manufacturer);
		} catch (Exception e) {
			logger.warn("⚠️ Manufacturer field not available: " + e.getMessage());
		}
	}

	public void fillDescription(String description) {
		try {
			wait.until(ExpectedConditions.visibilityOf(descriptionField));
			js.executeScript("arguments[0].scrollIntoView(true);", descriptionField);
			descriptionField.clear();
			descriptionField.sendKeys(description);
			logger.info("✅ Description filled: " + description);
		} catch (Exception e) {
			logger.warn("⚠️ Description field not available: " + e.getMessage());
		}
	}

	public void setConsumableStatus(boolean isConsumable) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(isConsumableCheckbox));
			if (isConsumableCheckbox.isSelected() != isConsumable) {
				isConsumableCheckbox.click();
			}
			logger.info("✅ Consumable status set: " + isConsumable);
		} catch (Exception e) {
			logger.warn("⚠️ Consumable checkbox not available: " + e.getMessage());
		}
	}

	public void setExpirableStatus(boolean isExpirable) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(isExpirableCheckbox));
			if (isExpirableCheckbox.isSelected() != isExpirable) {
				isExpirableCheckbox.click();
			}
			logger.info("✅ Expirable status set: " + isExpirable);
		} catch (Exception e) {
			logger.warn("⚠️ Expirable checkbox not available: " + e.getMessage());
		}
	}

	// Action Methods
	public void clickSave() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(saveButton));
			js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
			Thread.sleep(500);
			saveButton.click();
			logger.info("✅ Save button clicked");
		} catch (Exception e) {
			try {
				js.executeScript("arguments[0].click();", saveButton);
				logger.info("✅ Save button clicked using JavaScript");
			} catch (Exception e2) {
				logger.error("❌ Error clicking Save button: " + e2.getMessage());
				throw new RuntimeException("Could not click Save button", e2);
			}
		}
	}

	public void clickCancel() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
			cancelButton.click();
			logger.info("✅ Cancel button clicked");
		} catch (Exception e) {
			logger.warn("⚠️ Cancel button not available: " + e.getMessage());
		}
	}

	public void clickReset() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(resetButton));
			resetButton.click();
			logger.info("✅ Reset button clicked");
		} catch (Exception e) {
			logger.warn("⚠️ Reset button not available: " + e.getMessage());
		}
	}

	// Verification Methods
	public boolean isProductCreated() {
		try {
			// Wait for success message or page change
			Thread.sleep(2000);
			
			// Check for success message
			try {
				WebElement successMsg = driver.findElement(By.xpath("//div[contains(@class,'success')] | //*[contains(text(),'success')] | //*[contains(text(),'created')] | //*[contains(text(),'saved')]"));
				if (successMsg.isDisplayed()) {
					String message = successMsg.getText();
					logger.info("✅ Success message found: " + message);
					return true;
				}
			} catch (Exception e) {
				logger.debug("No success message found: " + e.getMessage());
			}
			
			// Check if URL changed (indicating successful save)
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl.contains("product") && !currentUrl.contains("create")) {
				logger.info("✅ URL indicates successful save: " + currentUrl);
				return true;
			}
			
			return false;
		} catch (Exception e) {
			logger.warn("❌ Error checking product creation: " + e.getMessage());
			return false;
		}
	}

	public boolean isFormCleared() {
		try {
			// Check if mandatory fields are cleared
			String codeValue = productCodeField.getAttribute("value");
			String nameValue = productNameField.getAttribute("value");
			
			boolean cleared = (codeValue == null || codeValue.isEmpty()) && 
							 (nameValue == null || nameValue.isEmpty());
			
			if (cleared) {
				logger.info("✅ Form appears to be cleared after save");
			}
			
			return cleared;
		} catch (Exception e) {
			logger.debug("Could not check form clear status: " + e.getMessage());
			return false;
		}
	}

	public boolean isProductInTable(String productCode, String productName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(productTable));
			
			for (WebElement row : productRows) {
				String rowText = row.getText();
				if (rowText.contains(productCode) && rowText.contains(productName)) {
					logger.info("✅ Product found in table: " + productCode);
					return true;
				}
			}
			
			logger.warn("⚠️ Product not found in table: " + productCode);
			return false;
		} catch (Exception e) {
			logger.error("❌ Error checking Product table: " + e.getMessage());
			return false;
		}
	}

	// Complete Product Creation Method
	public void createProduct(String code, String name, String barcode, String brand, 
							 String buyingPrice, String sellingPrice, String gstRate, 
							 String description, boolean isConsumable, boolean isExpirable) {
		
		logger.info("Starting Product creation process");
		
		// Fill mandatory fields
		fillProductCode(code);
		fillProductName(name);
		
		// Fill optional fields
		if (barcode != null && !barcode.isEmpty()) {
			fillBarcode(barcode);
		}
		
		if (brand != null && !brand.isEmpty()) {
			fillBrand(brand);
		}
		
		if (buyingPrice != null && !buyingPrice.isEmpty()) {
			fillBuyingPrice(buyingPrice);
		}
		
		if (sellingPrice != null && !sellingPrice.isEmpty()) {
			fillSellingPrice(sellingPrice);
		}
		
		if (gstRate != null && !gstRate.isEmpty()) {
			fillGSTRate(gstRate);
		}
		
		if (description != null && !description.isEmpty()) {
			fillDescription(description);
		}
		
		setConsumableStatus(isConsumable);
		setExpirableStatus(isExpirable);
		
		// Save the Product
		clickSave();
		
		logger.info("Product creation process completed");
	}
}


