package pageObjects;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class Admin_Dashboard extends BasePage {
	public Admin_Dashboard(WebDriver driver) {
		   super(driver);
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
