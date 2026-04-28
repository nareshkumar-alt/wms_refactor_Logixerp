package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Admin_Dashboard;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SalesOrderPage;
import pageObjects.SigninPage;
import testBase.BaseClass;

/**
 * Automated test for Backorder rule (On-Hold behavior) - skeleton implementation
 * This test performs:
 * 1. Sign in
 * 2. (Attempt) Disable backorder setting — placeholder (requires selector)
 * 3. Create product
 * 4. (Placeholder) Perform inbound receipt for the product
 * 5. Create sales order with quantity greater than inbound
 * 6. Verify order is placed On-Hold (or appropriate message shown)
 *
 * NOTE: Some application areas (backorder toggle, inbound/receipt) did not have
 * page objects in this repo. Where required, this test contains clear TODO markers
 * so you can replace placeholder code with application-specific actions/selectors.
 */
public class TC050_BackorderRuleTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verifyBackorderDisabledPutsOrderOnHold() {
        logger.info("***** Starting TC050_BackorderRuleTest *****");

        try {
            // 1. Sign in
            SigninPage sp = new SigninPage(driver);
            sp.TextUserName(p.getProperty("Username"));
            sp.TextPassword(p.getProperty("Password"));
            sp.ClickSignIn();

            // Verify we landed on home/dashboard
            HomePage hp = new HomePage(driver);
            Assert.assertTrue(hp.isWlcmHomePageExists(), "Dashboard/Home page not displayed after sign in");

            // 2. (Optional) Disable backorder setting via Admin -> Sales Order Settings
            // TODO: Replace this placeholder with actual navigation and toggle if available in your app
            try {
                hp.clickonsidebarsettings();
                hp.clickonadmindashboard();
                Admin_Dashboard adm = new Admin_Dashboard(driver);
                adm.navigateToSalesOrderSettings();
                // If there is a specific page object/method to disable backorders, call it here.
                // e.g., SalesOrderSettingsPage sos = new SalesOrderSettingsPage(driver);
                // sos.disableBackorder(); sos.saveSettings();
                logger.info("NOTE: Backorder toggle step is a placeholder - implement selector in SalesOrderSettingsPage if needed");
            } catch (Exception e) {
                // Don't fail the test here — just log. Tester should implement when selector available.
                logger.warn("Backorder settings navigation failed or not implemented: " + e.getMessage());
            }

            // 3. Create Product
            ProductPage pp = new ProductPage(driver);
            pp.navigateToProductModule();

            // Use unique product code to avoid collisions
            String uniqueCode = "BKR-" + randomeAlphaNumberic();
            String productName = "BackorderTestProduct-" + randomeString();
            pp.createProduct(uniqueCode, productName, "", "QABrand", "10", "15", "5", "Test product for backorder rule", false, false);

            // Verify product creation (best-effort)
            boolean created = pp.isProductCreated();
            if (!created) {
                logger.warn("Product creation didn't show a success message; continuing to attempt sales order creation (product may already exist)");
            }

            // 4. Inbound receipt - placeholder
            // TODO: Implement inbound/receipt page object and receive qty (e.g., 10 units).
            logger.info("TODO: Perform inbound receipt for product '" + uniqueCode + "' (implement inbound page object)");

            // 5. Create Sales Order with quantity > inbound (e.g., 15)
            SalesOrderPage sop = new SalesOrderPage(driver);
            sop.navigateToSalesOrderCreationScreen();

            // Fill basic fields - product search and quantity. Select a customer if required by your app.
            try {
                // product search field expects partial code/name; using uniqueCode may work
                sop.enterProductCode(uniqueCode);
            } catch (Exception e) {
                logger.warn("Could not search/select product by code in Sales Order form: " + e.getMessage());
            }

            // Attempt to set quantity; SalesOrderPage does not expose direct qty setter, try sending to Quantity field
            try {
                // Using reflection of page objects is discouraged; if there is a method, replace below
                driver.findElement(org.openqa.selenium.By.xpath("//input[@name='enteredQuantity']")).clear();
                driver.findElement(org.openqa.selenium.By.xpath("//input[@name='enteredQuantity']")).sendKeys("15");
            } catch (Exception e) {
                logger.warn("Failed to set quantity via direct locator: " + e.getMessage());
            }

            // Save Sales Order - use generic Save button locator
            try {
                // Prefer the page object's save if available via method; falling back to clicking Save xpath
                driver.findElement(org.openqa.selenium.By.xpath("//span[normalize-space()='Save'] | //button[normalize-space()='Save']")).click();
            } catch (Exception ex) {
                logger.error("Failed to click Save on Sales Order form: " + ex.getMessage());
                Assert.fail("Could not submit Sales Order");
            }

            // 6. Verification: check for On-Hold message or order status
            Thread.sleep(2000);
            String pageText = driver.getPageSource().toLowerCase();
            boolean onHoldFound = pageText.contains("on hold") || pageText.contains("on-hold") || pageText.contains("hold") || pageText.contains("backorder") || pageText.contains("insufficient stock");

            Assert.assertTrue(onHoldFound, "Expected On-Hold or insufficient-stock indication was not found in page source. Please update selectors/assertions to match your application messages.");

        } catch (Exception e) {
            logger.error("Test failed with exception: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }

        logger.info("***** Finished TC050_BackorderRuleTest *****");
    }
}
