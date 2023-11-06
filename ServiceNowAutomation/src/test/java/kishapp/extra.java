package kishapp;

import org.testng.annotations.Test;

import reusables.serviceNowAction;
import utilities.baseTest;

public class extra extends baseTest {
	
	
	serviceNowAction snAction = new serviceNowAction(driver);
	
	
	@Test(description = "\n" + "               1) Logging In" + "\n"
			+ "               2) Impersonating as Abel Tuter" + "\n"
			+ "               3) Moving To The Incidents Page" + "\n"
			+ "               4) Switching To Iframe" + "\n"
			+ "               5) Creating Incident" + "\n"
			+ "               6) Verifying Incidents Created" + "\n"
			+ "               7) Logging Out Of the Instance")
    public void loginToServiceNow() throws InterruptedException {
        action.login("admin", "UltiL6!3y!EV"); 
    	snAction.clickAvatarImage();
    	snAction.clickImpersonateUserButton();
    	snAction.enterTextInTypeaheadInput("Abel Tuter");
    	snAction.clickSeismicHoistElement();
    	snAction.clickImpersonationModalButton();
    	snAction.clickPolarisTabElement();
    	snAction.directClickOnIncidents();
    	snAction.createIncidentAndVerify();
        snAction.logout();
    }
}
