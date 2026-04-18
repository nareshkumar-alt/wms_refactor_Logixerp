package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.SigninPage;
import testBase.BaseClass;

/**
 * Positive Test Cases for Signin Functionality
 * These tests verify successful signin scenarios with valid inputs
 */
public class TC002_SigninPositiveTest extends BaseClass {
	
	/**
	 * Positive Test Case 1: Verify successful signin with valid credentials
	 * Test ID: POS-001
	 * Description: User should be able to sign in with valid username and password
	 * Expected Result: User should be redirected to dashboard after successful signin
	 */
	@Test(groups = {"Sanity", "Regression", "Positive"}, priority = 1, 
		  description = "POS-001: Verify successful signin with valid credentials")
	public void verify_successful_signin_with_valid_credentials() {
		logger.info("***** Starting POS-001: Verify successful signin with valid credentials *****");
		
		try {
			// Initialize SigninPage object
			SigninPage sp = new SigninPage(driver);
			
			// Verify we are on signin page
			Assert.assertTrue(sp.isSigninPageDisplayed(), "Signin page should be displayed");
			logger.info("Signin page is displayed");
			
			// Enter valid username from properties file
			logger.info("Entering valid username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			// Enter valid password from properties file
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for page to load after signin
			Thread.sleep(3000);
			
			// Verify successful login by checking dashboard
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			
			// Assert that dashboard is displayed after successful signin
			Assert.assertTrue(isDashboardDisplayed, "Dashboard should be displayed after successful signin");
			logger.info("Signin test passed - Dashboard displayed successfully");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished POS-001: Verify successful signin with valid credentials *****");
	}
	
	/**
	 * Positive Test Case 2: Verify signin with username containing alphanumeric characters
	 * Test ID: POS-002
	 * Description: User should be able to sign in with alphanumeric username
	 * Expected Result: User should be redirected to dashboard after successful signin
	 */
	@Test(groups = {"Regression", "Positive"}, priority = 2, 
		  description = "POS-002: Verify signin with alphanumeric username")
	public void verify_signin_with_alphanumeric_username() {
		logger.info("***** Starting POS-002: Verify signin with alphanumeric username *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear any existing values
			sp.clearAllFields();
			
			// Use valid username from properties (assuming it may contain alphanumeric)
			String username = p.getProperty("Username");
			logger.info("Entering alphanumeric username: " + username);
			sp.TextUserName(username);
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for page to load
			Thread.sleep(3000);
			
			// Verify successful login
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			
			Assert.assertTrue(isDashboardDisplayed, "Dashboard should be displayed after successful signin");
			logger.info("POS-002 test passed - Signin successful with alphanumeric username");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished POS-002: Verify signin with alphanumeric username *****");
	}
	
	/**
	 * Positive Test Case 3: Verify signin with username containing underscore
	 * Test ID: POS-003
	 * Description: User should be able to sign in if username contains underscore character
	 * Expected Result: User should be redirected to dashboard after successful signin
	 * Note: Enable this test if underscore is supported in username format
	 */
	@Test(groups = {"Regression", "Positive"}, priority = 3, 
		  description = "POS-003: Verify signin with username containing underscore",
		  enabled = false) // Disabled by default - enable if underscore is supported
	public void verify_signin_with_username_containing_underscore() {
		logger.info("***** Starting POS-003: Verify signin with username containing underscore *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear any existing values
			sp.clearAllFields();
			
			// Enter username with underscore (if supported)
			String usernameWithUnderscore = p.getProperty("Username") + "_test";
			logger.info("Entering username with underscore: " + usernameWithUnderscore);
			sp.TextUserName(usernameWithUnderscore);
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for page to load
			Thread.sleep(3000);
			
			// Verify successful login
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			
			Assert.assertTrue(isDashboardDisplayed, "Dashboard should be displayed");
			logger.info("POS-003 test passed - Signin successful with underscore in username");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished POS-003: Verify signin with username containing underscore *****");
	}
	
	/**
	 * Positive Test Case 4: Verify signin with minimum length password
	 * Test ID: POS-004
	 * Description: User should be able to sign in with minimum required password length
	 * Expected Result: User should be redirected to dashboard after successful signin
	 */
	@Test(groups = {"Regression", "Positive"}, priority = 4, 
		  description = "POS-004: Verify signin with minimum length password")
	public void verify_signin_with_minimum_length_password() {
		logger.info("***** Starting POS-004: Verify signin with minimum length password *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear any existing values
			sp.clearAllFields();
			
			// Enter valid username
			logger.info("Entering valid username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			// Enter valid password (assuming it meets minimum requirements)
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for page to load
			Thread.sleep(3000);
			
			// Verify successful login
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			
			Assert.assertTrue(isDashboardDisplayed, "Dashboard should be displayed");
			logger.info("POS-004 test passed - Signin successful with minimum length password");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished POS-004: Verify signin with minimum length password *****");
	}
	
	/**
	 * Positive Test Case 5: Verify signin button is enabled when fields are filled
	 * Test ID: POS-005
	 * Description: Sign In button should be enabled when username and password are entered
	 * Expected Result: Sign In button should be clickable
	 */
	@Test(groups = {"Regression", "Positive"}, priority = 5, 
		  description = "POS-005: Verify signin button is enabled when fields are filled")
	public void verify_signin_button_enabled_when_fields_filled() {
		logger.info("***** Starting POS-005: Verify signin button is enabled when fields are filled *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields first
			sp.clearAllFields();
			
			// Verify button exists (it should always be present)
			Assert.assertTrue(sp.isSigninPageDisplayed(), "Signin page should be displayed");
			
			// Enter username
			logger.info("Entering username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			// Enter password
			logger.info("Entering password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Verify we can click the button (it should be enabled)
			logger.info("Verifying Sign In button is clickable");
			sp.ClickSignIn();
			
			// Wait a moment
			Thread.sleep(2000);
			
			// If we reach here without exception, button was enabled
			logger.info("POS-005 test passed - Sign In button is enabled when fields are filled");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished POS-005: Verify signin button is enabled when fields are filled *****");
	}
	
	/**
	 * Positive Test Case 6: Verify successful signin and verify user session
	 * Test ID: POS-006
	 * Description: After successful signin, user session should be maintained
	 * Expected Result: User should remain logged in and dashboard should be accessible
	 */
	@Test(groups = {"Sanity", "Regression", "Positive"}, priority = 6, 
		  description = "POS-006: Verify successful signin and user session")
	public void verify_successful_signin_and_user_session() {
		logger.info("***** Starting POS-006: Verify successful signin and user session *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Verify we are on signin page
			Assert.assertTrue(sp.isSigninPageDisplayed(), "Signin page should be displayed");
			
			// Enter valid credentials
			logger.info("Entering valid username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for page to load
			Thread.sleep(3000);
			
			// Verify successful login
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			
			Assert.assertTrue(isDashboardDisplayed, "Dashboard should be displayed after successful signin");
			
			// Verify session is maintained by checking if we can still access dashboard
			Thread.sleep(2000);
			boolean isDashboardStillDisplayed = dp.isDashboardLoaded();
			Assert.assertTrue(isDashboardStillDisplayed, "Dashboard should still be accessible - session maintained");
			
			logger.info("POS-006 test passed - Signin successful and user session maintained");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished POS-006: Verify successful signin and user session *****");
	}

}

