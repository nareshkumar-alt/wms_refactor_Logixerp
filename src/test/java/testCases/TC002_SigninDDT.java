package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SigninPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid - login success-test pass- logout
 * Data is valid -- login failed - test fail
 * 
 * Data is invalid - login success - test fail - logout
 * Data is invalid - login failed - test pass
 * */

public class TC002_SigninDDT extends BaseClass {
	
	@Test(dataProvider ="LoginData", dataProviderClass =DataProviders.class)
	public void verify_loginDDT(String Username,String pwd,String exp) throws InterruptedException
	{
		logger.info("***** Starting TC_003_SigninDDT *****");
		try {
			SigninPage sp=new SigninPage(driver);
			sp.TextUserName(null);
			sp.TextPassword(null);
			sp.ClickSignIn();
			HomePage hp=new HomePage(driver);
		boolean	targetPage=hp.isWlcmHomePageExists();
		if (exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true)
			{
				hp.mousehoveronsetting();
				Assert.assertTrue(false);
			}
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					hp.mousehoveronsetting();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
			
		}
			
			
		} catch (Exception e) {
			Assert.fail();
		}
		
		Thread.sleep(3000);
		logger.info("***** Finished TC_003_LoginDDT ******");
		
	}
	
	

}
