package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.SigninPage;
import testBase.BaseClass;

public class TC001_SigninTest extends BaseClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_signin() {
		logger.info("***** Starting TC001_SigninTest *****");
		
		try {
			// Sign in page
			SigninPage sp = new SigninPage(driver);
			
			logger.info("Entering username");
			sp.TextUserName(p.getProperty("Username"));
			
			logger.info("Entering password");
			sp.TextPassword(p.getProperty("Password"));
			
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Verify successful login by checking dashboard
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			
			if(isDashboardDisplayed) {
				logger.info("Sign in test passed - Dashboard displayed");
				Assert.assertTrue(true);
			} else {
				logger.error("Sign in test failed - Dashboard not displayed");
				Assert.fail("Dashboard not displayed after sign in");
			}
			
		} catch(Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished TC001_SigninTest *****");
	}
}
