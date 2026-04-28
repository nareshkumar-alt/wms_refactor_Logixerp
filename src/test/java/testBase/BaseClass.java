package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.ITestResult;

import utilities.VideoRecorder;


public class BaseClass {
	public static WebDriver driver;
	public Logger logger;  //Log4j
	public Properties p;
	
	// Video recording configuration flags
	private boolean videoRecordingEnabled = false;
	private boolean recordOnlyOnFailure = false;
	private String currentTestMethodName = null;
	
	@Parameters({"os","browser"})
		@BeforeClass(groups= {"Sanity","Regression","Master"})
	public void setupClass(@Optional("linux") String os, @Optional("chrome") String browser) throws IOException
		{
			// Initialize logger first
			logger = LogManager.getLogger(this.getClass());
			
		// Load config.properties file for class-level setup
		p = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (inputStream != null) {
				p.load(inputStream);
				logger.info("Loaded config.properties from classpath");
			} else {
				// Fallback to relative path if classpath loading fails
				logger.warn("config.properties not found in classpath, trying relative path");
				try (FileReader file = new FileReader("./src//test//resources//config.properties")) {
					p.load(file);
					logger.info("Loaded config.properties from relative path");
				} catch (IOException e) {
					logger.error("Failed to load config.properties from relative path: " + e.getMessage());
					throw new RuntimeException("Could not load config.properties file", e);
				}
			}
		} catch (IOException e) {
			logger.error("Failed to load config.properties from classpath: " + e.getMessage());
			throw new RuntimeException("Could not load config.properties file", e);
		}
		
		// Load video recording configuration
		videoRecordingEnabled = Boolean.parseBoolean(p.getProperty("video_recording_enabled", "false"));
		recordOnlyOnFailure = Boolean.parseBoolean(p.getProperty("video_record_only_on_failure", "false"));
		
		if (videoRecordingEnabled) {
			logger.info("Video recording is ENABLED");
			if (recordOnlyOnFailure) {
				logger.info("Video recording mode: Only on failure");
			} else {
				logger.info("Video recording mode: All tests");
			}
		} else {
			logger.info("Video recording is DISABLED");
		}
	}
	
	/**
	 * Setup method that runs before each test method
	 * Creates a new driver instance for each test case
	 * Starts video recording if enabled
	 */
	@Parameters({"os","browser"})
	@BeforeMethod(groups= {"Sanity","Regression","Master"}, alwaysRun = true)
	public void setup(@Optional("linux") String os, @Optional("chrome") String browser, ITestResult result) throws IOException
	{
		// Start video recording if enabled (before driver setup)
		if (videoRecordingEnabled && result != null) {
			try {
				// Get test method name
				String testMethodName = result.getMethod().getMethodName();
				currentTestMethodName = testMethodName;
				
				// Start video recording
				boolean recordingStarted = VideoRecorder.startRecording(testMethodName, recordOnlyOnFailure);
				if (recordingStarted) {
					logger.info("Video recording started for test method: " + testMethodName);
				} else {
					logger.warn("Failed to start video recording for test method: " + testMethodName);
				}
			} catch (Exception e) {
				logger.error("Error starting video recording: " + e.getMessage(), e);
			}
		}
		// Initialize logger if not already initialized
		if (logger == null) {
			logger = LogManager.getLogger(this.getClass());
		}
		
				// Load properties if not already loaded
				if (p == null) {
					p = new Properties();
					try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
						if (inputStream != null) {
							p.load(inputStream);
							logger.info("Loaded config.properties from classpath");
						} else {
							try (FileReader file = new FileReader("./src//test//resources//config.properties")) {
								p.load(file);
								logger.info("Loaded config.properties from relative path");
							}
						}
					} catch (IOException e) {
						logger.error("Failed to load config.properties: " + e.getMessage());
					}
				}
	
		// Ensure driver is null before creating new one (in case previous test didn't close properly)
		if (driver != null) {
				try {
			driver.quit();
				} catch (Exception e) {
			logger.warn("Previous driver session was not properly closed: " + e.getMessage());
		}
		driver = null;
		}
		
		// Suppress Selenium java.util.logging messages
		java.util.logging.Logger seleniumLogger = java.util.logging.Logger.getLogger("org.openqa.selenium");
		seleniumLogger.setLevel(java.util.logging.Level.SEVERE);
		java.util.logging.Logger devtoolsLogger = java.util.logging.Logger.getLogger("org.openqa.selenium.devtools");
		devtoolsLogger.setLevel(java.util.logging.Level.SEVERE);
		java.util.logging.Logger tracingLogger = java.util.logging.Logger.getLogger("org.openqa.selenium.remote.tracing");
		tracingLogger.setLevel(java.util.logging.Level.SEVERE);
		
		logger.info("Setting up new driver instance for test execution");
				
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			org.openqa.selenium.Capabilities capabilities = null;
			
			// browser - using modern Selenium 4.x approach
			switch (browser.toLowerCase()) {
			    case "chrome":
			        ChromeOptions chromeOptions = new ChromeOptions();
			        if(os.equalsIgnoreCase("linux")) {
			            chromeOptions.setPlatformName("linux");
			        } else if(os.equalsIgnoreCase("mac")) {
			            chromeOptions.setPlatformName("mac");
			        } else {
			            chromeOptions.setPlatformName("windows");
			        }
			        capabilities = chromeOptions;
			        break;

			    case "edge":
			        EdgeOptions edgeOptions = new EdgeOptions();
			        if(os.equalsIgnoreCase("linux")) {
			            edgeOptions.setPlatformName("linux");
			        } else if(os.equalsIgnoreCase("mac")) {
			            edgeOptions.setPlatformName("mac");
			        } else {
			            edgeOptions.setPlatformName("windows");
			        }
			        capabilities = edgeOptions;
			        break;

			    case "firefox":
			        FirefoxOptions firefoxOptions = new FirefoxOptions();
			        if(os.equalsIgnoreCase("linux")) {
			            firefoxOptions.setPlatformName("linux");
			        } else if(os.equalsIgnoreCase("mac")) {
			            firefoxOptions.setPlatformName("mac");
			        } else {
			            firefoxOptions.setPlatformName("windows");
			        }
			        capabilities = firefoxOptions;
			        break;

			    default:
			        logger.error("No matching browser: " + browser);
			        return;
			}

			// Initialize RemoteWebDriver with Selenium Grid hub URL
			String gridHubURL = p.getProperty("gridHubURL", "http://localhost:4444/wd/hub");
			boolean fallbackToLocal = Boolean.parseBoolean(p.getProperty("fallbackToLocal", "true"));
			
			try {
				logger.info("Attempting to connect to Selenium Grid at: " + gridHubURL);
				driver = new RemoteWebDriver(new URL(gridHubURL), capabilities);
				logger.info("Successfully connected to Selenium Grid");
			} catch (Exception e) {
				logger.error("Failed to connect to Selenium Grid at " + gridHubURL + ": " + e.getMessage());
				if (fallbackToLocal) {
					logger.warn("Falling back to local execution mode");
					// Fallback to local execution
					switch(browser.toLowerCase())
					{
					case "chrome" : driver=new ChromeDriver();
					break;
					
					case "edge" : driver=new EdgeDriver();
					break;
					case "firefox": driver=new FirefoxDriver();
					break;
					default : 
						logger.error("Invalid browser name: " + browser);
						return;
					}
				} else {
					logger.error("Remote execution failed and fallback is disabled. Please start Selenium Grid or set fallbackToLocal=true");
					throw new RuntimeException("Could not connect to Selenium Grid. Please ensure Grid is running at: " + gridHubURL, e);
				}
			}
		}
		
		// ✅ LOCAL SECTION — Docker/Headless support added
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome":
				ChromeOptions localChromeOptions = new ChromeOptions();
				// Docker/Headless environment check
				if(System.getenv("DOCKER_ENV") != null || 
				   p.getProperty("headless", "false").equalsIgnoreCase("true")) {
					localChromeOptions.addArguments("--headless");
					localChromeOptions.addArguments("--no-sandbox");
					localChromeOptions.addArguments("--disable-dev-shm-usage");
					localChromeOptions.addArguments("--disable-gpu");
					localChromeOptions.addArguments("--window-size=1920,1080");
					localChromeOptions.addArguments("--remote-debugging-port=9222");
					logger.info("Running Chrome in HEADLESS mode (Docker environment)");
				} else {
					logger.info("Running Chrome in NORMAL mode (Local environment)");
				}
				driver = new ChromeDriver(localChromeOptions);
				break;

			case "edge":
				EdgeOptions localEdgeOptions = new EdgeOptions();
				if(System.getenv("DOCKER_ENV") != null || 
				   p.getProperty("headless", "false").equalsIgnoreCase("true")) {
					localEdgeOptions.addArguments("--headless");
					localEdgeOptions.addArguments("--no-sandbox");
					localEdgeOptions.addArguments("--disable-dev-shm-usage");
					logger.info("Running Edge in HEADLESS mode");
				} else {
					logger.info("Running Edge in NORMAL mode (Local environment)");
				}
				driver = new EdgeDriver(localEdgeOptions);
				break;

			case "firefox":
				FirefoxOptions localFirefoxOptions = new FirefoxOptions();
				if(System.getenv("DOCKER_ENV") != null || 
				   p.getProperty("headless", "false").equalsIgnoreCase("true")) {
					localFirefoxOptions.addArguments("--headless");
					logger.info("Running Firefox in HEADLESS mode");
				} else {
					logger.info("Running Firefox in NORMAL mode (Local environment)");
				}
				driver = new FirefoxDriver(localFirefoxOptions);
				break;

			default:
				logger.error("Invalid browser name: " + browser);
				return;
			}
		}
		
		// Navigate to application URL (only if driver was just created)
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL1")); // reading url from properties file.
		driver.manage().window().maximize();
	}
	
	/**
	 * Teardown method that runs after each test method
	 * Closes and quits the driver after every test case execution
	 * Stops video recording and saves video file
	 */
	@AfterMethod(groups= {"Sanity","Regression","Master"}, alwaysRun = true)
	public void tearDown(ITestResult result)
		{
		// Stop video recording if enabled
		if (videoRecordingEnabled) {
			try {
				// Determine test result
				boolean testPassed = result.getStatus() == ITestResult.SUCCESS;
				String testMethodName = currentTestMethodName != null ? currentTestMethodName : result.getMethod().getMethodName();
				
				// Stop recording
				String videoPath = VideoRecorder.stopRecording(testMethodName, testPassed, recordOnlyOnFailure);
				
				if (videoPath != null) {
					logger.info("Video recording stopped and saved: " + videoPath);
				} else if (recordOnlyOnFailure && testPassed) {
					logger.info("Video recording stopped and deleted (test passed, recordOnlyOnFailure=true)");
				} else {
					logger.warn("Video recording stopped but file path is null");
				}
			} catch (Exception e) {
				logger.error("Error stopping video recording: " + e.getMessage(), e);
				// Force stop recording in case of error
				VideoRecorder.forceStopRecording();
			}
		}
		
		// Close and quit driver after each test case
		if (driver != null) {
			try {
				logger.info("Closing driver after test case execution");
				driver.quit();
				driver = null;
				logger.info("Driver closed successfully after test case");
			} catch (Exception e) {
				logger.warn("Error closing driver after test case: " + e.getMessage());
				driver = null;
			}
		} else {
			logger.info("Driver is already null, no cleanup needed");
		}
		
		// Reset test method name
		currentTestMethodName = null;
	}
	
	/**
	 * Cleanup method that runs after all test methods in a class
	 * This is a safety net in case any driver wasn't closed properly
	 */
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDownClass()
	{
		// Safety cleanup - ensure driver is closed after all tests in class
		if (driver != null) {
			try {
				logger.info("Final cleanup: Closing driver after test class completion");
				driver.quit();
				driver = null;
				logger.info("Driver closed successfully after test class");
			} catch (Exception e) {
				logger.warn("Error closing driver in AfterClass: " + e.getMessage());
				driver = null;
			}
		}
		logger.info("Test class completed. All driver sessions closed.");
	}
	
	/**
	 * Cleanup method that runs after all test suites
	 * Final safety net to ensure no driver instances remain
	 */
	@AfterSuite(groups= {"Sanity","Regression","Master"})
	public void closeDriver()
	{
		// Final cleanup - ensure no driver instances remain after all tests
		if (driver != null) {
			try {
				logger.info("Final suite cleanup: Closing any remaining driver instances");
				driver.quit();
				driver = null;
				logger.info("All driver sessions closed successfully after test suite");
			} catch (Exception e) {
				logger.warn("Error closing driver in AfterSuite: " + e.getMessage());
				driver = null;
			}
		}
		logger.info("All tests completed. All driver sessions closed.");
	}
	
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomeAlphaNumberic()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
}