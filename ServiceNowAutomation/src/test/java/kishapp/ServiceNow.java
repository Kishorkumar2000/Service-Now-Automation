package kishapp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ServiceNow {

	public static WebDriver driver;

	@Test
	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev191077.service-now.com");

		WebElement usernameInput = driver.findElement(By.id("user_name"));
		WebElement passwordInput = driver.findElement(By.id("user_password"));
		WebElement loginButton = driver.findElement(By.id("sysverb_login"));

		usernameInput.sendKeys("admin");
		passwordInput.sendKeys("UltiL6!3y!EV");
		loginButton.click();

		Thread.sleep(10000);

		clickElementInShadowDom("macroponent-f51912f4c700201072b211d4d8c26010", "sn-polaris-layout",
				"sn-polaris-header", "now-avatar", "img");

		Thread.sleep(2000);

		clickElementInShadowDom("macroponent-f51912f4c700201072b211d4d8c26010", "sn-polaris-layout",
				"sn-polaris-header", ".impersonateUser");

		Thread.sleep(2000);
		sendKeysToElementInShadowDom("macroponent-f51912f4c700201072b211d4d8c26010", "sn-polaris-layout",
				"sn-impersonation", "now-typeahead", ".now-typeahead-native-input");

		Thread.sleep(4000);
		clickElementInShadowDom("seismic-hoist", "div:nth-child(1) > div:nth-child(1) > div:nth-child(1)");

		Thread.sleep(4000);
		clickElementInShadowDom("macroponent-f51912f4c700201072b211d4d8c26010", "sn-polaris-layout", "sn-impersonation",
				"now-modal", "now-button:nth-child(2)", "button");

		Thread.sleep(3000);
		clickElementInShadowDom("macroponent-f51912f4c700201072b211d4d8c26010", "sn-polaris-layout",
				"sn-polaris-header", ".sn-polaris-tab");

		Thread.sleep(2000);
		clickElementInShadowDom("macroponent-f51912f4c700201072b211d4d8c26010", "sn-polaris-layout",
				"sn-polaris-header", "sn-polaris-menu", "sn-collapsible-list", "li:nth-child(8) > span");

		Thread.sleep(5000);

		WebElement element = driver.findElement(By.tagName("macroponent-f51912f4c700201072b211d4d8c26010"));

		String componentId = element.getAttribute("component-id");

		System.out.println("Component ID: " + componentId);

		Thread.sleep(1000);
		SearchContext shadow = driver
				.findElement(By
						.cssSelector("macroponent-f51912f4c700201072b211d4d8c26010[component-id=" + componentId + "]"))
				.getShadowRoot();
		Thread.sleep(1000);
		driver.switchTo().frame(shadow.findElement(By.cssSelector("#gsft_main")));

		Thread.sleep(1000);
		WebElement newButton = driver.findElement(By.id("sysverb_new"));
		newButton.click();

		Thread.sleep(3000);
		WebElement urgencyDropDown = driver.findElement(By.cssSelector(".form-control"));
		urgencyDropDown.click();

		Thread.sleep(3000);
		WebElement urgencyHigh = driver.findElement(By.cssSelector(".form-control option:nth-child(2)"));
		urgencyHigh.click();

		Thread.sleep(3000);
		WebElement description = driver.findElement(By.cssSelector(".question_textarea_input"));
		description.sendKeys("Test Incident");

		Thread.sleep(3000);
		WebElement submitButton = driver.findElement(By.id("submit_button"));
		submitButton.click();

		Thread.sleep(10000);
		WebElement shortDescription = driver
				.findElement(By.cssSelector("input.form-control[aria-label='Short description']"));
		String shortDescriptionValue = shortDescription.getAttribute("value");
		Assert.assertEquals(shortDescriptionValue, "Test Incident",
				"Short description value does not match expected value");

		Thread.sleep(3000);
		WebElement resolveButton = driver.findElement(By.xpath("//button[contains(text(), \"Resolve\")]"));
		resolveButton.click();

		Thread.sleep(5000);
		WebElement createdIncident = driver.findElement(By.cssSelector(".list_row"));
		createdIncident.isDisplayed();

		System.out.println("Element clicked and keys sent successfully");
		Thread.sleep(10000);
		driver.quit();
	}

	private static void clickElementInShadowDom(String... selectors) {
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

	private static void sendKeysToElementInShadowDom(String... selectors) {
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
			element.sendKeys("Abel Tuter");
		} else {
			System.out.println("Element not found.");
		}
	}
}
