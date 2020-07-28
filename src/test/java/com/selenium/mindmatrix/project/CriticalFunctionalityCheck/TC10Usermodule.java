package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;


import java.util.Hashtable;

import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC10Usermodule extends BaseTest {

private	Xls_Reader xls;
	
	@DataProvider
	public Object[][] getDataTC10() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir")+prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls,"TC10");
}

    @Test(dataProvider="getDataTC10")
    private void TestTC2(Hashtable<String,String> data) throws InterruptedException {
	
	if(!DataUtil.isRunnable("TC10Usermodule", xls)){
	
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	test = rep.startTest("TC10=> User Module");
try {	
	String URL = data.get("URL");
	switch (URL){
	
	case "https://partnerhub.intersystems.com/user-login":
		userLogin(data);
		  break;
		  
	case "https://rajantportal.amp.vg/user-login":
		userLogin(data);
		  break;
		  
	case "https://datto.amp.vg/user-login":
		userLogin(data);
		  break;
		  
	case "https://axcient.amp.vg/user-login":
		userLogin(data);
		  break;
		  
	case "https://levitonpartnerconnection.amp.vg/user-login":
		userLogin(data);
		  break;
		  
	case "https://partner-marketing.bitdefender.com/user-login":
		userLogin(data);
		  break;
		  
	case "https://borderstates.amp.vg/user-login":
		userLogin(data);
		  break;
		
	default:
		
	openBrowser(prop.getProperty("browsername"));
	navigate(data.get("URL"));
	waitForElementforreportfail("username_xpath");
	if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
		doLoginMMAmpAndPortal();		
		}else if (data.get("URL").equals("https://dvl-master.amp.vg")) {
			doLoginForMaster();
		}else {
		
			doLoginForAll();
		} 
	waitForElementforreportfail("dashboard_xpath");
	test.log(LogStatus.INFO, " Reached at Dashboard Page ");
    navigate(data.get("URL")+"/#setup/users/list");
    test.log(LogStatus.INFO, " Reached at Users Homepage.");
    int initialusers = getNumber("companiescount_xpath");
    click("create_xpath");
    test.log(LogStatus.INFO, " Clicked on create button.");
	String name = data.get("User's emailID");
	String[] combine = name.split("@");
	type("name_id", combine[0]);
	type("email_id", data.get("User's emailID"));
	type("firstname_id", combine[0]);
	click("contactinfo_xpath");
	test.log(LogStatus.INFO, " After entering '"+combine[0]+"' as username and firstname, and entered '" +data.get("User's emailID")+ "' in email field then clicked on 'contact info' button.");
	type("address_id", "Flat no. 82B Riverdale Aerovista");
	type("mobile_id", "9779986649");	
	click("country_id");
	type("searchcountry_xpath", "India");
	click("selectcountry_xpath");
	test.log(LogStatus.INFO, " Entered address , mobile no. and selected country in the required field.");
	click("setting_xpath");
	test.log(LogStatus.INFO, " Clicked on setting tab.");
	click("timezone_id");
	click("selecttimezone_xpath");
    click("unsubscribecheckbox_xpath");
    test.log(LogStatus.INFO, "Selected time zone and ticked unsubscribe checkbox.");
    click("wizardnext_id");
    test.log(LogStatus.INFO, " Clicked on Next button.");
    waitForElementforreportfail("waitfornumber_xpath");
    click("wizardnext_id");
    test.log(LogStatus.INFO, " Clicked on Next button.");
    waitForElementforreportfail("finish_xpath");
    waitForElementforreportfail("waitfornumber_xpath");
    click("wizardnext_id");
    test.log(LogStatus.INFO, " Clicked on Finish button.");
    wait(3);
    driver.navigate().refresh();
    waitForLoad(driver);
    wait(3);
    driver.navigate().refresh();
    waitForLoad(driver);
    waitForElementforreportfail("companiescount_xpath");
    type("opportunitysearch_xpath", combine[0]);
    getElement("opportunitysearch_xpath").sendKeys(Keys.ENTER);
    
    test.log(LogStatus.INFO, "New user is created with the name '"+combine[0]+"'.");
    getElementforloop("//*[text()='"+combine[0]+"']").click();
    test.log(LogStatus.PASS, "User is created.");
    wait(2);
    click("veiws_id");
    wait(2);
    click("info_xpath");
    test.log(LogStatus.INFO, "Opened new user and click on info dropdown to edit the user.");
    getElement("name_id").clear();
    type("name_id", combine[0]+"1");   
    click("save_xpath");
    driver.navigate().refresh();
    waitForLoad(driver);
    waitForElementforreportfail("companiescount_xpath");
    type("opportunitysearch_xpath", combine[0]+"1");
    getElement("opportunitysearch_xpath").sendKeys(Keys.ENTER);  
    test.log(LogStatus.INFO, "new user is edited and the new name is '"+combine[0]+"1' .");      
    getElementforloop("//*[text()='"+combine[0]+"1"+"']/parent::td/preceding-sibling::td/div").click();
    test.log(LogStatus.PASS, "User is edited.");
    click("deleteuser_xpath");
    test.log(LogStatus.INFO, "Selected the checkbox of the edited new user and clicked on delete button.");
    click("yesbutton_xpath");  
    flashMessagevisibilityandinvisibilty();
    driver.navigate().refresh();
    waitForElementforreportfail("companiescount_xpath");
    int finalusers = getNumber("companiescount_xpath");
     if (initialusers==finalusers) {	
	 reportPass("User deleted successfully");
 }else {
	 reportFail("Total remaining users are not "+initialusers+".");
 }
    test.log(LogStatus.INFO, "TC10 is completed");
	}   
	
} catch (Exception e) {
	reportFail(e.getMessage());
}
	
}

	private void userLogin(Hashtable<String, String> data) {
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		waitForElementforreportfail("username_xpath");
		doLoginForAll();
		waitForElementforreportfail("dashboard_xpath");
		test.log(LogStatus.INFO, " Reached at Dashboard Page ");
		String urlname=	driver.getCurrentUrl();
	    String[]	modifiedURL = urlname.split("#");
	    navigate(modifiedURL[0]+"/#setup/users/list");
	    test.log(LogStatus.INFO, " Reached at Users Homepage.");
	    int initialusers = getNumber("companiescount_xpath");
	    click("create_xpath");
	    test.log(LogStatus.INFO, " Clicked on create button.");
	    String name = data.get("User's emailID");
		String[] combine = name.split("@");
		type("name_id", combine[0]);
		type("email_id", data.get("User's emailID"));
		type("firstname_id", combine[0]);
		click("contactinfo_xpath");
		test.log(LogStatus.INFO, " After entering '"+combine[0]+"' as username and firstname, and entered '" +data.get("User's emailID")+ "' in email field then clicked on 'contact info' button.");
		type("address_id", "Flat no. 82B Riverdale Aerovista");
		type("mobile_id", "9779986649");	
		click("country_id");
		type("searchcountry_xpath", "India");
		click("selectcountry_xpath");
		test.log(LogStatus.INFO, " Entered address , mobile no. and selected country in the required field.");
		click("setting_xpath");
		test.log(LogStatus.INFO, " Clicked on setting tab.");
		click("timezone_id");
		click("selecttimezone_xpath");
	    click("unsubscribecheckbox_xpath");
	    test.log(LogStatus.INFO, "Selected time zone and ticked unsubscribe checkbox.");
	    click("wizardnext_id");
	    test.log(LogStatus.INFO, " Clicked on Next button.");
	    waitForElementforreportfail("waitfornumber_xpath");
	    click("wizardnext_id");
	    test.log(LogStatus.INFO, " Clicked on Next button.");
	    waitForElementforreportfail("finish_xpath");
	    waitForElementforreportfail("waitfornumber_xpath");
	    click("wizardnext_id");
	    test.log(LogStatus.INFO, " Clicked on Finish button.");
	    wait(3);
	    driver.navigate().refresh();
	    waitForLoad(driver);
	    wait(3);
	    driver.navigate().refresh();
	    waitForLoad(driver);
	    waitForElementforreportfail("companiescount_xpath");
	    type("opportunitysearch_xpath", combine[0]);
	    getElement("opportunitysearch_xpath").sendKeys(Keys.ENTER);
	    
	    test.log(LogStatus.INFO, "New user is created with the name '"+combine[0]+"'.");
	    getElementforloop("//*[text()='"+combine[0]+"']").click();
	    test.log(LogStatus.PASS, "User is created.");
	    wait(2);
	    click("veiws_id");
	    wait(2);
	    click("info_xpath");
	    test.log(LogStatus.INFO, "Opened new user and click on info dropdown to edit the user.");
	    getElement("name_id").clear();
	    type("name_id", combine[0]+"1");   
	    click("save_xpath");
	    driver.navigate().refresh();
	    waitForLoad(driver);
	    waitForElementforreportfail("companiescount_xpath");
	    type("opportunitysearch_xpath", combine[0]+"1");
	    getElement("opportunitysearch_xpath").sendKeys(Keys.ENTER);
	   
	    test.log(LogStatus.INFO, "new user is edited and the new name is '"+combine[0]+"1' .");      
	    getElementforloop("//*[text()='"+combine[0]+"1"+"']/parent::td/preceding-sibling::td/div").click();
	    test.log(LogStatus.PASS, "User is edited.");
	    click("deleteuser_xpath");
	    test.log(LogStatus.INFO, "Selected the checkbox of the edited new user and clicked on delete button.");
	    click("yesbutton_xpath");  
	    flashMessagevisibilityandinvisibilty();
	    driver.navigate().refresh();
	    waitForElementforreportfail("companiescount_xpath");
	    int finalusers = getNumber("companiescount_xpath");
	     if (initialusers==finalusers) {	
		 reportPass("User deleted successfully");
	 }else {
		 reportFail("Total remaining users are not "+initialusers+".");
	 }
	    test.log(LogStatus.INFO, "TC10 is completed");
}   
	
		
	}
	
