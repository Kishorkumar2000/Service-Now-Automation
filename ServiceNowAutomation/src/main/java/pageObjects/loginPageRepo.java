package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.baseTest;

public class loginPageRepo extends baseTest {
	public loginPageRepo(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "user_name")
	public WebElement usernameInput;

	@FindBy(id = "user_password")
	public WebElement passwordInput;

	@FindBy(id = "sysverb_login")
	public WebElement loginButton;

}