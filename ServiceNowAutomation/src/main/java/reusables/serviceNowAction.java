package reusables;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.serviceNowRepo;
import utilities.baseTest;

public class serviceNowAction extends serviceNowRepo {
	public serviceNowAction(WebDriver driver) {
		super(driver);
	}

	baseTest testInstance = new baseTest();

	public void clickAvatarImage() {
		sleepSeconds(11);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, secondShadow, thirdShadow, avatarImage);
	}

	public void clickImpersonateUserButton() {
		sleepSeconds(5);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, secondShadow, ".impersonateUser");
	}

	public void enterTextInTypeaheadInput(String text) {
		sleepSeconds(6);
		testInstance.sendKeysToElementInShadowDom(text, mainHost, firstShadow, "sn-impersonation", "now-typeahead",
				".now-typeahead-native-input");
	}

	public void clickSeismicHoistElement() {
		sleepSeconds(3);
		testInstance.clickElementInShadowDom("seismic-hoist", "div:nth-child(1) > div:nth-child(1) > div:nth-child(1)");
	}

	public void clickImpersonationModalButton() {
		sleepSeconds(4);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, "sn-impersonation", "now-modal",
				"now-button:nth-child(2)", "button");
	}

	public void clickPolarisTabElement() {
		sleepSeconds(5);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, secondShadow, ".sn-polaris-tab");
	}

	public void enterTextInFilter(String text) {
		sleepSeconds(3);
		testInstance.sendKeysToElementInShadowDom(text, mainHost, firstShadow, secondShadow, "sn-polaris-menu",
				"#filter");
		sleepSeconds(2);
//    	clearTextInShadowDom("macroponent-f51912f4c700201072b211d4d8c26010", "sn-polaris-layout", "sn-polaris-header", "sn-polaris-menu", "#filter");
	}

	public void clickCollapsibleListElement() {
		sleepSeconds(3);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, secondShadow, "sn-polaris-menu",
				"sn-collapsible-list", "a");
		sleepSeconds(3);
	}

	public void directClickOnIncidents() {
		sleepSeconds(3);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, secondShadow, "sn-polaris-menu",
				"sn-collapsible-list", "a[aria-label='Incidents']");
	}

	public void logout() throws InterruptedException {
		sleepSeconds(5);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, secondShadow, thirdShadow, avatarImage);
		sleepSeconds(5);
		testInstance.clickElementInShadowDom(mainHost, firstShadow, secondShadow, logout);
		Thread.sleep(7000);
	}

	@Test
	public void switchToIframe() {
		sleepSeconds(8);
		WebElement element = driver.findElement(By.tagName("macroponent-f51912f4c700201072b211d4d8c26010"));
		String componentId = element.getAttribute("component-id");
		System.out.println("Component ID: " + componentId);
		sleepSeconds(2);
		SearchContext shadow = driver
				.findElement(By
						.cssSelector("macroponent-f51912f4c700201072b211d4d8c26010[component-id=" + componentId + "]"))
				.getShadowRoot();
		sleepSeconds(2);
		driver.switchTo().frame(shadow.findElement(By.cssSelector("#gsft_main")));
	}

	public void clickNewButton() {
		sleepSeconds(3);
		newButton.click();
	}

	public void createIncident() {
		sleepSeconds(3);
		urgencyDropDown.click();
		sleepSeconds(3);
		urgencyHigh.click();
		sleepSeconds(3);
		description.sendKeys("Test Incident");
		sleepSeconds(3);
		submitButton.click();
		sleepSeconds(10);
	}

	public void resolveAndVerifyIncident() {
		String shortDescriptionValue = shortDescription.getAttribute("value");
		Assert.assertEquals(shortDescriptionValue, "Test Incident",
				"Short description value does not match expected value");
		sleepSeconds(3);
		;
		resolveButton.click();
		sleepSeconds(5);
		createdIncident.isDisplayed();
		sleepSeconds(2);
	}

	public void createIncidentAndVerify() throws InterruptedException {
		WebElement element = driver.findElement(By.tagName("macroponent-f51912f4c700201072b211d4d8c26010"));
		String componentId = element.getAttribute("component-id");
//        System.out.println("Component ID: " + componentId);
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
		WebElement urgencyDropDown = driver.findElement(By.tagName("select"));
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
		driver.switchTo().defaultContent();
	}

}
