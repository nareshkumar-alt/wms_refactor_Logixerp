package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	 WebDriverWait wait;
	 Actions actions=new Actions(driver);

	public HomePage(WebDriver driver) {
		super(driver);
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// TODO Auto-generated constructor stub
	}
	/*
	 * @FindBy(xpath ="//span[normalize-space()='My Account']") WebElement
	 * lnkmyaccount;
	 * 
	 * @FindBy(xpath ="//a[normalize-space()='Register']") WebElement lnkRegister;
	 */
   
   @FindBy(xpath ="//a[@href='#logixerp_home_dashboard']")
   WebElement wlcmhome;
   
   @FindBy(xpath = "//*[@id='settings']")
   WebElement settings;
   
   @FindBy(xpath ="//a[normalize-space()='Logout']")
   WebElement Logout;
   
   @FindBy(xpath ="//a[@href='#one17' and normalize-space()='Settings']")
   WebElement settings1;
   
   @FindBy(xpath ="//a[contains(normalize-space(), 'Admin Dashboard')]")
   WebElement Admin_Dashboard1;

   
   public void mousehoveronsetting() throws InterruptedException
   {
	   actions.moveToElement(settings).perform();
	   Thread.sleep(3000);
	   Logout.click();
   }
       
  
	public boolean isWlcmHomePageExists() {
		try {
			// Wait for element to be visible with explicit wait
			wait.until(ExpectedConditions.visibilityOf(wlcmhome));
			return wlcmhome.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
 public void clickonsidebarsettings()
 {
	 settings1.click();
 }
 
 public void clickonadmindashboard()
 {
	 Admin_Dashboard1.click();
 }
}
