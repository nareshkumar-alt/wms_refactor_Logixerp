package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SigninPage;
import testBase.BaseClass;

public class TC001_SigninTest extends BaseClass {
	
@Test
public  void verify_account_signin()
{
	logger.info("**** Starting TC001_SigninTest *****");
	
	try {
	SigninPage sp=new SigninPage(driver);
	sp.TextUserName(p.getProperty("Username"));
	logger.info("**** Enter Username logixerp *****");
	sp.TextPassword(p.getProperty("Password"));
	logger.info("**** Enter Password logixerp *****");
	sp.ClickSignIn();
	logger.info("**** Clickled on Signin  *****");
	HomePage hp=new HomePage(driver);

	//WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
//List<WebElement>Homscrn	=mywait.until(ExpectedConditions.visibilityof)));
	
  logger.info("Validating Signin Successful!");
	boolean	Homscrn=hp.isWlcmHomePageExists();

Assert.assertTrue(Homscrn);
	}
	catch(Exception e)
	{
		
		Assert.fail();
	}
	logger.info("***** Finished TC001_SigninTest  ****");
	
}

	}


