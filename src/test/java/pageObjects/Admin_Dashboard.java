package pageObjects;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;

public class Admin_Dashboard extends BasePage {
	
	private WebDriverWait wait;
	
	public Admin_Dashboard(WebDriver driver) {
		   super(driver);
		   this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath ="//span[normalize-space()='Custom Fields']")
	WebElement Custom_field;
	
	@FindBy(xpath ="//button[@class='btn btn-xs btn-create']")
	WebElement custom_field_btn;
	
	@FindBy(xpath ="//input[@name='masterCustomLabel']")
	WebElement customfieldlevel;
	
	@FindBy(xpath ="//input[@name='masterCustomCode']")
	WebElement customcode;
	
	@FindBy(xpath ="//input[@name='selectedOU']")
	WebElement selectou;
	
	@FindBy(xpath ="//input[@name='selectedCustomer']")
	WebElement selectcustomer;
	
	@FindBy(xpath ="//input[@id='dijitv2_dijit_form_FilteringSelect_1']/following-sibling::input[@name='documentType']")
	WebElement Doctype;
	
	@FindBy(xpath ="//input[@name='selectedCustomFieldType']")
	WebElement Type;
	
	@FindBy(xpath ="//span[normalize-space()='Save']")
	WebElement save;
	
	// Sales Order Settings navigation elements
	@FindBy(xpath ="//a[normalize-space()='Sales Order Settings' or contains(text(),'Sales Order Settings')]")
	WebElement salesOrderSettings;
	
	@FindBy(xpath ="//a[normalize-space()='Pack UOM Conversion' or contains(text(),'Pack UOM Conversion')]")
	WebElement packUOMConversion;
	
	/**
	 * Navigate to Sales Order Settings from Admin Dashboard
	 * This method clicks on the Sales Order Settings menu item
	 */
	public void navigateToSalesOrderSettings() {
		try {
			// Wait for Sales Order Settings link to be visible
			wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[normalize-space()='Sales Order Settings' or contains(text(),'Sales Order Settings')]")));
			
			WebElement salesOrderSettingsElement = driver.findElement(
				By.xpath("//a[normalize-space()='Sales Order Settings' or contains(text(),'Sales Order Settings')]"));
			
			// Scroll into view if needed
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", salesOrderSettingsElement);
			Thread.sleep(500); // Small wait for scroll
			
			// Click on Sales Order Settings link
			salesOrderSettingsElement.click();
			Thread.sleep(2000); // Wait for Sales Order Settings page to load
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				WebElement salesOrderSettingsElement = driver.findElement(
					By.xpath("//a[normalize-space()='Sales Order Settings' or contains(text(),'Sales Order Settings')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", salesOrderSettingsElement);
				Thread.sleep(2000);
			} catch (Exception e2) {
				throw new RuntimeException("Failed to navigate to Sales Order Settings: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Navigate to Pack UOM Conversion section from Sales Order Settings
	 * This method clicks on the Pack UOM Conversion menu item or section
	 */
	public void navigateToPackUOMConversion() {
		try {
			// Wait for Pack UOM Conversion link to be visible
			wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[normalize-space()='Pack UOM Conversion' or contains(text(),'Pack UOM Conversion')]")));
			
			WebElement packUOMConversionElement = driver.findElement(
				By.xpath("//a[normalize-space()='Pack UOM Conversion' or contains(text(),'Pack UOM Conversion')]"));
			
			// Scroll into view if needed
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", packUOMConversionElement);
			Thread.sleep(500); // Small wait for scroll
			
			// Click on Pack UOM Conversion link
			packUOMConversionElement.click();
			Thread.sleep(2000); // Wait for Pack UOM Conversion section to load
		} catch (Exception e) {
			// Try JavaScript click as fallback
			try {
				WebElement packUOMConversionElement = driver.findElement(
					By.xpath("//a[normalize-space()='Pack UOM Conversion' or contains(text(),'Pack UOM Conversion')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", packUOMConversionElement);
				Thread.sleep(2000);
			} catch (Exception e2) {
				throw new RuntimeException("Failed to navigate to Pack UOM Conversion: " + e.getMessage());
			}
		}
	}
	
	public void clickoncustomfield_admindash() {
		Custom_field.click();
		
	}
	public void clickonthecustomfield()
	{
		customfieldlevel.click();
	}
	
	public void fillcustname(String custcode)
	{
		customcode.sendKeys("custcode");
	}
	public void fillselectou(String ou)
	{
		selectou.sendKeys("ou");
	}
	public void fillcustomername(String customer)
	{
	  selectcustomer.sendKeys("customer");
	}

	public void DocumentType(String doctype)
	{
		Doctype.sendKeys("doctype");
	}
	
	public void type(String type)
	{
		Type.sendKeys("type");
	}
	 
	public void savecust()
	{
		save.click();
	}
	
}
