package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.baseTest;

public class serviceNowRepo extends baseTest {

	public serviceNowRepo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName = "macroponent-f51912f4c700201072b211d4d8c26010")
	public WebElement mainHostShadow;

	@FindBy(id = "sysverb_new")
	public WebElement newButton;

	@FindBy(tagName = "select")
	public WebElement urgencyDropDown;

	@FindBy(css = ".form-control option:nth-child(2)")
	public WebElement urgencyHigh;

	@FindBy(css = ".question_textarea_input")
	public WebElement description;

	@FindBy(id = "submit_button")
	public WebElement submitButton;

	@FindBy(css = "input.form-control[aria-label='Short description']")
	public WebElement shortDescription;

	@FindBy(xpath = "//button[contains(text(), 'Resolve')]")
	public WebElement resolveButton;

	@FindBy(css = ".list_row")
	public WebElement createdIncident;

	public String mainHost = "macroponent-f51912f4c700201072b211d4d8c26010";
	public String firstShadow = "sn-polaris-layout";
	public String secondShadow = "sn-polaris-header";
	public String thirdShadow = "now-avatar";
	public String avatarImage = "img";
	public String logout = "div.user-menu-footer > button";
	public String shortDesc = "input.form-control[aria-label='Short description']";
}
