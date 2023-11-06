package reusables;

import org.openqa.selenium.WebDriver;
import pageObjects.loginPageRepo;

public class loginPageAction extends loginPageRepo {
	public loginPageAction(WebDriver driver) {
		super(driver);
	}

	public void login(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
	}

}