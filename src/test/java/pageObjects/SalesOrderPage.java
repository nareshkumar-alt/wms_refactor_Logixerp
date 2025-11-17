package pageObjects;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesOrderPage extends BasePage {

	public SalesOrderPage(WebDriver driver) {
		super(driver);
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
	
	public void scrolldownsidebar()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", sidebar);
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
