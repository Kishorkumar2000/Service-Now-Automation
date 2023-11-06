package kishapp;

import org.testng.Assert;
import org.testng.annotations.Test;

import reusables.serviceNowAction;
import utilities.baseTest;

public class login extends baseTest {

	serviceNowAction snAction = new serviceNowAction(driver);

	@Test(description = "\n" + "               1) Logging In" + "\n" + "               2) Impersonating as Abel Tuter"
			+ "\n" + "               3) Moving To The Incidents Page" + "\n" + "               4) Switching To Iframe"
			+ "\n" + "               5) Creating Incident" + "\n" + "               6) Verifying Incidents Created"
			+ "\n" + "               7) Logging Out Of the Instance")
	public void loginToServiceNow() throws InterruptedException {

		try {
			action.login("admin", "UltiL6!3y!EV");
			logMethodStatus("login", "PASS");

			snAction.clickAvatarImage();
			logMethodStatus("clickAvatarImage", "PASS");

			snAction.clickImpersonateUserButton();
			logMethodStatus("clickImpersonateUserButton", "PASS");

			snAction.enterTextInTypeaheadInput("Abel Tuter");
			logMethodStatus("enterTextInTypeaheadInput", "PASS");

			snAction.clickSeismicHoistElement();
			logMethodStatus("clickSeismicHoistElement", "PASS");

			snAction.clickImpersonationModalButton();
			logMethodStatus("clickImpersonationModalButton", "PASS");

			snAction.clickPolarisTabElement();
			logMethodStatus("clickPolarisTabElement", "PASS");

			snAction.enterTextInFilter("Incidents");
			logMethodStatus("enterTextInFilter", "PASS");

			snAction.clickCollapsibleListElement();
			logMethodStatus("clickCollapsibleListElement", "PASS");

			snAction.createIncidentAndVerify();
			logMethodStatus("createIncidentAndVerify", "PASS");

			snAction.logout();
			logMethodStatus("logout", "PASS\n");
		} catch (Exception e) {
			logMethodStatus(e.getStackTrace()[0].getMethodName(), "FAIL\n");
			Assert.fail("Test case failed due to an exception: " + e.getMessage());
		}
	}
}
