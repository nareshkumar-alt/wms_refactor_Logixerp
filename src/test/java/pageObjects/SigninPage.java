package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPage extends BasePage {

	private WebDriverWait wait;

	public SigninPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(xpath ="//input[@id='username']")
	WebElement txtusername;
	
	@FindBy(xpath ="//input[@id='password']")
	WebElement txtpwd;
	
	@FindBy(xpath ="//button[normalize-space()='Sign in']")
	WebElement clckonsignin;
	
	// Error message locators - common patterns for error messages
	@FindBy(xpath ="//div[contains(@class,'error') or contains(@class,'alert') or contains(@class,'danger')]")
	WebElement errorMessage;
	
	@FindBy(xpath ="//span[contains(@class,'error')]")
	WebElement errorSpan;
	
	@FindBy(xpath ="//div[contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'wrong')]")
	WebElement invalidCredentialsError;
	
	/**
	 * Enter username in the username field
	 * @param Username - The username to enter
	 */
	public void TextUserName(String Username)
	{
		txtusername.clear();
		txtusername.sendKeys(Username);
	}
	
	/**
	 * Enter password in the password field
	 * @param Password - The password to enter
	 */
	public void TextPassword(String Password) {
		txtpwd.clear();
		txtpwd.sendKeys(Password);
	}
	
	/**
	 * Click on the Sign In button
	 */
	public void ClickSignIn()
	{
		clckonsignin.click();
	}
	
	/**
	 * Clear username field
	 */
	public void clearUsername() {
		txtusername.clear();
	}
	
	/**
	 * Clear password field
	 */
	public void clearPassword() {
		txtpwd.clear();
	}
	
	/**
	 * Clear both username and password fields
	 */
	public void clearAllFields() {
		clearUsername();
		clearPassword();
	}
	
	/**
	 * Check if error message is displayed on the signin page
	 * @return true if error message is visible, false otherwise
	 */
	public boolean isErrorMessageDisplayed() {
		try {
			// Wait a moment for error message to appear
			Thread.sleep(1000);
			
			// Try multiple locator strategies for error messages
			By[] errorLocators = {
				By.xpath("//div[contains(@class,'error') or contains(@class,'alert') or contains(@class,'danger')]"),
				By.xpath("//span[contains(@class,'error')]"),
				By.xpath("//div[contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'wrong')]"),
				By.xpath("//*[contains(text(),'Invalid username or password')]"),
				By.xpath("//*[contains(text(),'Please enter')]"),
				By.xpath("//*[contains(@role,'alert')]")
			};
			
			for (By locator : errorLocators) {
				try {
					WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
					if (errorElement != null && errorElement.isDisplayed()) {
						return true;
					}
				} catch (Exception e) {
					continue;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Get the error message text if displayed
	 * @return Error message text, or empty string if not found
	 */
	public String getErrorMessage() {
		try {
			Thread.sleep(1000);
			
			By[] errorLocators = {
				By.xpath("//div[contains(@class,'error') or contains(@class,'alert') or contains(@class,'danger')]"),
				By.xpath("//span[contains(@class,'error')]"),
				By.xpath("//div[contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'wrong')]"),
				By.xpath("//*[contains(@role,'alert')]")
			};
			
			for (By locator : errorLocators) {
				try {
					WebElement errorElement = driver.findElement(locator);
					if (errorElement != null && errorElement.isDisplayed()) {
						return errorElement.getText();
					}
				} catch (Exception e) {
					continue;
				}
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * Check if still on signin page (useful for negative test cases)
	 * @return true if signin page elements are still visible, false otherwise
	 */
	public boolean isSigninPageDisplayed() {
		try {
			return txtusername.isDisplayed() && txtpwd.isDisplayed() && clckonsignin.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Check if username field is empty
	 * @return true if username field is empty, false otherwise
	 */
	public boolean isUsernameFieldEmpty() {
		try {
			return txtusername.getAttribute("value") == null || txtusername.getAttribute("value").isEmpty();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Check if password field is empty
	 * @return true if password field is empty, false otherwise
	 */
	public boolean isPasswordFieldEmpty() {
		try {
			return txtpwd.getAttribute("value") == null || txtpwd.getAttribute("value").isEmpty();
		} catch (Exception e) {
			return false;
		}
	}

}
