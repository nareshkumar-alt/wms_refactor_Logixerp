package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SigninPage extends BasePage {

	public SigninPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath ="//input[@id='username']")
	WebElement txtusername;
	
	@FindBy(xpath ="//input[@id='password']")
	WebElement txtpwd;
	
	@FindBy(xpath ="//button[normalize-space()='Sign in']")
	WebElement clckonsignin;
	
	public void TextUserName(String Username)
	{
		txtusername.sendKeys(Username);
	}
	
	public void TextPassword(String Password) {
		txtpwd.sendKeys(Password);
		
	}
	public void ClickSignIn()
	{
		clckonsignin.click();
	}

}
