package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.SigninPage;
import testBase.BaseClass;

/**
 * Negative Test Cases for Signin Functionality
 * These tests verify that the system properly handles invalid inputs and prevents unauthorized access
 */
public class TC003_SigninNegativeTest extends BaseClass {
	
	/**
	 * Negative Test Case 1: Verify signin fails with invalid username
	 * Test ID: NEG-001
	 * Description: User should not be able to sign in with invalid username
	 * Expected Result: User should remain on signin page and error message should be displayed
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 1, 
		  description = "NEG-001: Verify signin fails with invalid username")
	public void verify_signin_fails_with_invalid_username() {
		logger.info("***** Starting NEG-001: Verify signin fails with invalid username *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear any existing values
			sp.clearAllFields();
			
			// Enter invalid username
			String invalidUsername = "invalid_user_12345";
			logger.info("Entering invalid username: " + invalidUsername);
			sp.TextUserName(invalidUsername);
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for error message or page response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page (login failed)
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page after invalid username");
			
			// Verify error message is displayed
			boolean isErrorDisplayed = sp.isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				String errorMessage = sp.getErrorMessage();
				logger.info("Error message displayed: " + errorMessage);
				Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
			} else {
				logger.warn("Error message not displayed, but signin failed as expected");
			}
			
			logger.info("NEG-001 test passed - Signin correctly failed with invalid username");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-001: Verify signin fails with invalid username *****");
	}
	
	/**
	 * Negative Test Case 2: Verify signin fails with invalid password
	 * Test ID: NEG-002
	 * Description: User should not be able to sign in with invalid password
	 * Expected Result: User should remain on signin page and error message should be displayed
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 2, 
		  description = "NEG-002: Verify signin fails with invalid password")
	public void verify_signin_fails_with_invalid_password() {
		logger.info("***** Starting NEG-002: Verify signin fails with invalid password *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear any existing values
			sp.clearAllFields();
			
			// Enter valid username
			logger.info("Entering valid username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			// Enter invalid password
			String invalidPassword = "wrong_password_123";
			logger.info("Entering invalid password");
			sp.TextPassword(invalidPassword);
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for error message or page response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page (login failed)
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page after invalid password");
			
			// Verify error message is displayed
			boolean isErrorDisplayed = sp.isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				String errorMessage = sp.getErrorMessage();
				logger.info("Error message displayed: " + errorMessage);
				Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
			} else {
				logger.warn("Error message not displayed, but signin failed as expected");
			}
			
			logger.info("NEG-002 test passed - Signin correctly failed with invalid password");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-002: Verify signin fails with invalid password *****");
	}
	
	/**
	 * Negative Test Case 3: Verify signin fails with both invalid username and password
	 * Test ID: NEG-003
	 * Description: User should not be able to sign in with both invalid username and password
	 * Expected Result: User should remain on signin page and error message should be displayed
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 3, 
		  description = "NEG-003: Verify signin fails with both invalid username and password")
	public void verify_signin_fails_with_both_invalid_credentials() {
		logger.info("***** Starting NEG-003: Verify signin fails with both invalid credentials *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear any existing values
			sp.clearAllFields();
			
			// Enter invalid username
			String invalidUsername = "invalid_user_xyz";
			logger.info("Entering invalid username: " + invalidUsername);
			sp.TextUserName(invalidUsername);
			
			// Enter invalid password
			String invalidPassword = "invalid_pass_123";
			logger.info("Entering invalid password");
			sp.TextPassword(invalidPassword);
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for error message or page response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page (login failed)
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page after invalid credentials");
			
			// Verify error message is displayed
			boolean isErrorDisplayed = sp.isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				String errorMessage = sp.getErrorMessage();
				logger.info("Error message displayed: " + errorMessage);
				Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
			}
			
			logger.info("NEG-003 test passed - Signin correctly failed with both invalid credentials");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-003: Verify signin fails with both invalid credentials *****");
	}
	
	/**
	 * Negative Test Case 4: Verify signin fails with empty username
	 * Test ID: NEG-004
	 * Description: User should not be able to sign in with empty username field
	 * Expected Result: User should remain on signin page and validation message should be displayed
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 4, 
		  description = "NEG-004: Verify signin fails with empty username")
	public void verify_signin_fails_with_empty_username() {
		logger.info("***** Starting NEG-004: Verify signin fails with empty username *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Leave username empty
			logger.info("Leaving username field empty");
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for validation or error message
			Thread.sleep(2000);
			
			// Verify that we are still on signin page
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page when username is empty");
			
			// Verify username field is empty
			boolean isUsernameEmpty = sp.isUsernameFieldEmpty();
			Assert.assertTrue(isUsernameEmpty, "Username field should be empty");
			
			// Check for error message or validation message
			boolean isErrorDisplayed = sp.isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				String errorMessage = sp.getErrorMessage();
				logger.info("Validation/Error message displayed: " + errorMessage);
			}
			
			logger.info("NEG-004 test passed - Signin correctly failed with empty username");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-004: Verify signin fails with empty username *****");
	}
	
	/**
	 * Negative Test Case 5: Verify signin fails with empty password
	 * Test ID: NEG-005
	 * Description: User should not be able to sign in with empty password field
	 * Expected Result: User should remain on signin page and validation message should be displayed
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 5, 
		  description = "NEG-005: Verify signin fails with empty password")
	public void verify_signin_fails_with_empty_password() {
		logger.info("***** Starting NEG-005: Verify signin fails with empty password *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Enter valid username
			logger.info("Entering valid username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			// Leave password empty
			logger.info("Leaving password field empty");
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for validation or error message
			Thread.sleep(2000);
			
			// Verify that we are still on signin page
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page when password is empty");
			
			// Verify password field is empty
			boolean isPasswordEmpty = sp.isPasswordFieldEmpty();
			Assert.assertTrue(isPasswordEmpty, "Password field should be empty");
			
			// Check for error message or validation message
			boolean isErrorDisplayed = sp.isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				String errorMessage = sp.getErrorMessage();
				logger.info("Validation/Error message displayed: " + errorMessage);
			}
			
			logger.info("NEG-005 test passed - Signin correctly failed with empty password");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-005: Verify signin fails with empty password *****");
	}
	
	/**
	 * Negative Test Case 6: Verify signin fails with both username and password empty
	 * Test ID: NEG-006
	 * Description: User should not be able to sign in when both fields are empty
	 * Expected Result: User should remain on signin page and validation message should be displayed
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 6, 
		  description = "NEG-006: Verify signin fails with both fields empty")
	public void verify_signin_fails_with_both_fields_empty() {
		logger.info("***** Starting NEG-006: Verify signin fails with both fields empty *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Leave both fields empty
			logger.info("Leaving both username and password fields empty");
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for validation or error message
			Thread.sleep(2000);
			
			// Verify that we are still on signin page
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page when both fields are empty");
			
			// Verify both fields are empty
			boolean isUsernameEmpty = sp.isUsernameFieldEmpty();
			boolean isPasswordEmpty = sp.isPasswordFieldEmpty();
			Assert.assertTrue(isUsernameEmpty, "Username field should be empty");
			Assert.assertTrue(isPasswordEmpty, "Password field should be empty");
			
			// Check for error message or validation message
			boolean isErrorDisplayed = sp.isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				String errorMessage = sp.getErrorMessage();
				logger.info("Validation/Error message displayed: " + errorMessage);
			}
			
			logger.info("NEG-006 test passed - Signin correctly failed with both fields empty");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-006: Verify signin fails with both fields empty *****");
	}
	
	/**
	 * Negative Test Case 7: Verify signin fails with SQL injection attempt in username
	 * Test ID: NEG-007
	 * Description: System should prevent SQL injection attacks in username field
	 * Expected Result: User should remain on signin page and login should fail
	 */
	@Test(groups = {"Security", "Negative"}, priority = 7, 
		  description = "NEG-007: Verify signin fails with SQL injection attempt in username")
	public void verify_signin_fails_with_sql_injection_in_username() {
		logger.info("***** Starting NEG-007: Verify signin fails with SQL injection in username *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Enter SQL injection attempt in username
			String sqlInjectionUsername = "admin' OR '1'='1";
			logger.info("Entering SQL injection attempt in username: " + sqlInjectionUsername);
			sp.TextUserName(sqlInjectionUsername);
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page (attack prevented)
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page - SQL injection prevented");
			
			// Verify login did not succeed
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			Assert.assertFalse(isDashboardDisplayed, "Dashboard should not be displayed - SQL injection prevented");
			
			logger.info("NEG-007 test passed - SQL injection attempt correctly prevented");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-007: Verify signin fails with SQL injection in username *****");
	}
	
	/**
	 * Negative Test Case 8: Verify signin fails with XSS attempt in username
	 * Test ID: NEG-008
	 * Description: System should prevent XSS attacks in username field
	 * Expected Result: User should remain on signin page and login should fail
	 */
	@Test(groups = {"Security", "Negative"}, priority = 8, 
		  description = "NEG-008: Verify signin fails with XSS attempt in username")
	public void verify_signin_fails_with_xss_attempt_in_username() {
		logger.info("***** Starting NEG-008: Verify signin fails with XSS attempt in username *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Enter XSS attempt in username
			String xssUsername = "<script>alert('XSS')</script>";
			logger.info("Entering XSS attempt in username: " + xssUsername);
			sp.TextUserName(xssUsername);
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page (attack prevented)
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page - XSS prevented");
			
			// Verify login did not succeed
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			Assert.assertFalse(isDashboardDisplayed, "Dashboard should not be displayed - XSS prevented");
			
			logger.info("NEG-008 test passed - XSS attempt correctly prevented");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-008: Verify signin fails with XSS attempt in username *****");
	}
	
	/**
	 * Negative Test Case 9: Verify signin fails with very long username
	 * Test ID: NEG-009
	 * Description: System should handle very long username input appropriately
	 * Expected Result: User should remain on signin page or receive appropriate error
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 9, 
		  description = "NEG-009: Verify signin fails with very long username")
	public void verify_signin_fails_with_very_long_username() {
		logger.info("***** Starting NEG-009: Verify signin fails with very long username *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Generate very long username (more than 255 characters)
			StringBuilder longUsername = new StringBuilder();
			for (int i = 0; i < 300; i++) {
				longUsername.append("a");
			}
			
			logger.info("Entering very long username (300 characters)");
			sp.TextUserName(longUsername.toString());
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page with very long username");
			
			logger.info("NEG-009 test passed - Very long username correctly handled");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-009: Verify signin fails with very long username *****");
	}
	
	/**
	 * Negative Test Case 10: Verify signin fails with username containing only spaces
	 * Test ID: NEG-010
	 * Description: System should not accept username with only whitespace characters
	 * Expected Result: User should remain on signin page and validation error should be displayed
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 10, 
		  description = "NEG-010: Verify signin fails with username containing only spaces")
	public void verify_signin_fails_with_username_containing_only_spaces() {
		logger.info("***** Starting NEG-010: Verify signin fails with username containing only spaces *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Enter username with only spaces
			String spacesOnlyUsername = "     ";
			logger.info("Entering username with only spaces");
			sp.TextUserName(spacesOnlyUsername);
			
			// Enter valid password
			logger.info("Entering valid password");
			sp.TextPassword(p.getProperty("Password"));
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page with spaces-only username");
			
			// Check for error message
			boolean isErrorDisplayed = sp.isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				String errorMessage = sp.getErrorMessage();
				logger.info("Error message displayed: " + errorMessage);
			}
			
			logger.info("NEG-010 test passed - Spaces-only username correctly rejected");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-010: Verify signin fails with username containing only spaces *****");
	}
	
	/**
	 * Negative Test Case 11: Verify signin fails with case-sensitive password mismatch
	 * Test ID: NEG-011
	 * Description: System should be case-sensitive for password (if applicable)
	 * Expected Result: User should remain on signin page with wrong case password
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 11, 
		  description = "NEG-011: Verify signin fails with case-sensitive password mismatch")
	public void verify_signin_fails_with_case_sensitive_password_mismatch() {
		logger.info("***** Starting NEG-011: Verify signin fails with case-sensitive password mismatch *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Enter valid username
			logger.info("Entering valid username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			// Enter password with different case (uppercase)
			String originalPassword = p.getProperty("Password");
			String wrongCasePassword = originalPassword.toUpperCase();
			logger.info("Entering password with wrong case: " + wrongCasePassword);
			sp.TextPassword(wrongCasePassword);
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page with wrong case password");
			
			// Verify login did not succeed
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			Assert.assertFalse(isDashboardDisplayed, "Dashboard should not be displayed with wrong case password");
			
			logger.info("NEG-011 test passed - Case-sensitive password correctly enforced");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-011: Verify signin fails with case-sensitive password mismatch *****");
	}
	
	/**
	 * Negative Test Case 12: Verify signin fails with special characters in password
	 * Test ID: NEG-012
	 * Description: System should handle special characters in password appropriately
	 * Expected Result: User should remain on signin page if password format is invalid
	 */
	@Test(groups = {"Regression", "Negative"}, priority = 12, 
		  description = "NEG-012: Verify signin fails with invalid password format")
	public void verify_signin_fails_with_invalid_password_format() {
		logger.info("***** Starting NEG-012: Verify signin fails with invalid password format *****");
		
		try {
			SigninPage sp = new SigninPage(driver);
			
			// Clear all fields
			sp.clearAllFields();
			
			// Enter valid username
			logger.info("Entering valid username: " + p.getProperty("Username"));
			sp.TextUserName(p.getProperty("Username"));
			
			// Enter password with only special characters (invalid format)
			String invalidPasswordFormat = "!@#$%^&*()";
			logger.info("Entering password with invalid format: " + invalidPasswordFormat);
			sp.TextPassword(invalidPasswordFormat);
			
			// Click Sign In button
			logger.info("Clicking Sign In button");
			sp.ClickSignIn();
			
			// Wait for response
			Thread.sleep(2000);
			
			// Verify that we are still on signin page
			boolean isStillOnSigninPage = sp.isSigninPageDisplayed();
			Assert.assertTrue(isStillOnSigninPage, "Should remain on signin page with invalid password format");
			
			// Verify login did not succeed
			DashboardPage dp = new DashboardPage(driver);
			boolean isDashboardDisplayed = dp.isDashboardLoaded();
			Assert.assertFalse(isDashboardDisplayed, "Dashboard should not be displayed with invalid password format");
			
			logger.info("NEG-012 test passed - Invalid password format correctly rejected");
			
		} catch (Exception e) {
			logger.error("Test failed with exception: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		logger.info("***** Finished NEG-012: Verify signin fails with invalid password format *****");
	}

}

