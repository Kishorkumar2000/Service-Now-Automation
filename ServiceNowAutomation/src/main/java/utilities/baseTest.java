package utilities;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.JavascriptExecutor;

import reusables.loginPageAction;

public class baseTest {
	public static WebDriver driver;
	public WebDriverWait wait;
	protected loginPageAction action;

	public void waitForElementToBeClickable(By string) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(string));
	}

	public void waitForElementsToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void sleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickElementInShadowDom(String... selectors) {
		WebElement element = driver.findElement(By.cssSelector(selectors[0]));

		for (int i = 1; i < selectors.length; i++) {
			if (element != null) {
				element = (WebElement) ((JavascriptExecutor) driver).executeScript(
						"return arguments[0].shadowRoot.querySelector(arguments[1])", element, selectors[i]);
			} else {
				System.out.println("Element not found.");
				break;
			}
		}

		if (element != null) {
			element.click();
		} else {
			System.out.println("Element not found.");
		}
	}

	public void sendKeysToElementInShadowDom(String text, String... selectors) {
		WebElement element = driver.findElement(By.cssSelector(selectors[0]));

		for (int i = 1; i < selectors.length; i++) {
			if (element != null) {
				element = (WebElement) ((JavascriptExecutor) driver).executeScript(
						"return arguments[0].shadowRoot.querySelector(arguments[1])", element, selectors[i]);
			} else {
				System.out.println("Element not found.");
				break;
			}
		}

		if (element != null) {
			element.sendKeys(text);
		} else {
			System.out.println("Element not found.");
		}
	}

	protected void clickElement(WebElement element) {
		if (element != null) {
			element.click();
		} else {
			System.out.println("Element not found.");
		}
	}

	@BeforeMethod
	@Parameters(value = { "browser" })
	public void beforeMethod(String browser) throws IOException {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			driver.manage().window().maximize();
		}
		driver.get("https://dev191077.service-now.com");
		action = PageFactory.initElements(driver, loginPageAction.class);
	}

	protected void logMethodStatus(String methodName, String status) {
		String formattedMessage = String.format("Method: %-30s ------> Status : %s", methodName, status);
		System.out.println(formattedMessage);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		String testName = result.getName().toUpperCase();
		String status = getTestStatus(result.getStatus());
		String testDescription = result.getMethod().getDescription() != null ? result.getMethod().getDescription() : "";

		String separator = "------------------------------------------------------------------------------";
		String message = String.format(
				"\n%s\nTest Case: %-25sTest Status: %-10sTest Name: %-25s\nTest Description:\n%s\n%s\n", separator,
				testName, status, testName, testDescription, separator);

		Reporter.log(message);
		System.out.println(message);

		driver.quit();
	}

	@AfterSuite
	public void onTestStart() {
		String message = "All tests have been run successfully!";
		Reporter.log(message);
		System.out.println(message);
	}

	private String getTestStatus(int status) {
		switch (status) {
		case ITestResult.SUCCESS:
			return "SUCCESS";
		case ITestResult.FAILURE:
			return "FAILURE";
		case ITestResult.SKIP:
			return "SKIP";
		default:
			return "UNKNOWN";
		}
	}

}