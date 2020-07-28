package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC7Companiesmodule extends BaseTest {
	
private	Xls_Reader xls;
	
	@DataProvider
	private Object[][] getDataTC7() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir")+prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls,"TC7");
}

    @Test(dataProvider="getDataTC7")
    private void TestTC2(Hashtable<String,String> data) throws InterruptedException {
	
	if(!DataUtil.isRunnable("TC7Companiesmodule", xls)){
	
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	test = rep.startTest("TC7=> Companies Module");

try {

	String URL = data.get("URL");
	switch (URL){
	
	case "https://partnerhub.intersystems.com/user-login":
	      partnerhub(data);
		  break;
		  
	case "https://rajantportal.amp.vg/user-login":
	      partnerhub(data);
		  break;
		  
	case "https://datto.amp.vg/user-login":
	      partnerhub(data);
		  break;
		  
	case "https://axcient.amp.vg/user-login":
	      partnerhub(data);
		  break;
		  
	case "https://levitonpartnerconnection.amp.vg/user-login":
	      partnerhub(data);
		  break;
		  
	case "https://partner-marketing.bitdefender.com/user-login":
	      partnerhub(data);
		  break;
		  
	case "https://borderstates.amp.vg/user-login":
	      partnerhub(data);
		  break;
		
	default:
		
	openBrowser(prop.getProperty("browsername"));
	navigate(data.get("URL"));
	if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
		doLoginMMAmpAndPortal();
		}else if (data.get("URL").equals("https://dvl-master.amp.vg")) {
			doLoginForMaster();
		}else {
		
			doLoginForAll();
		} 
	waitForElementforreportfail("dashboard_xpath");
	test.log(LogStatus.INFO, " Reached at Dashboard Page ");
    navigate(data.get("URL")+"/#setup/companies");
    test.log(LogStatus.INFO, " Reached at Companies Homepage.");
    int initialcompanies = getNumber("companiescount_xpath");
    test.log(LogStatus.INFO, " Total existing companies are "+initialcompanies+".");
    click("create_xpath");
    test.log(LogStatus.INFO, " Clicked on create button.");
    wait(2);
    click("useparentdata_xpath");
    wait(2);
    click("useparentdata_xpath");
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	String name = "Testing";
	String combine = name + timeStamp;
	type("name_id", combine);
	type("zipcode_id", "171001");
	click("setting_xpath");
	test.log(LogStatus.INFO, " After selecting and unselecting 'use  parentdata' checkbox, entering "+combine+" as company name, entering zipcode then clicked on setting tab button.");
	type("smscost_id", "9779986649");
    click("defaultcurrency_id");
    click("searchcountry_xpath");
	click("selectcurrency_xpath");
	click("senduseremail_xpath");
	click("save_xpath");
	WebDriverWait wait = new WebDriverWait(driver,20);
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(prop.getProperty("save_xpath")))));
	test.log(LogStatus.INFO, "Typed in SMS Cost, selecting currency from drop down and selecting 'send user email' checkbox then clicked on save button.");
	driver.navigate().refresh();
	waitForLoad(driver);
	test.log(LogStatus.PASS, "New company is created.");
    getElementforloop("//*[text()='"+combine+"']/parent::div/parent::td/preceding-sibling::td/div").click();
    click("copybutton_xpath");   
    test.log(LogStatus.INFO, " Clicked on newly created company's checkbox and then clicked on copy button.");
    click("yesbutton_xpath");
    flashMessagevisibilityandinvisibilty();
    getElement("name_id").clear();
    type("name_id", combine+"1"); 
    test.log(LogStatus.PASS, "New company  is editable.");
    click("save_xpath");
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(prop.getProperty("save_xpath")))));
    test.log(LogStatus.PASS, "Copied company is created.");
    
    test.log(LogStatus.INFO, " New copied company is created with the name "+combine+"1");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+combine+"1"+"']/parent::div/parent::td/preceding-sibling::td/div")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+combine+"1"+"']/parent::div/parent::td/preceding-sibling::td/div")));
			
	getElementforloop("//*[text()='"+combine+"1"+"']/parent::div/parent::td/preceding-sibling::td/div").click();
    getElementforloop("//*[text()='"+combine+"']/parent::div/parent::td/preceding-sibling::td/div").click();
    click("deletebutton_xpath");
    click("yesbutton_xpath");
    flashMessagevisibilityandinvisibilty();
    waitForElementforreportfail("companiescount_xpath");
    int finalcompanies = getNumber("companiescount_xpath");
    if (initialcompanies==finalcompanies) {	
   	 reportPass("Both new company and copied company are deleted.");
    }else {
   	 reportFail("Total remaining companies are not "+initialcompanies+".");
    }
 test.log(LogStatus.INFO, "TC7 is completed.");

	}   
} catch(Exception e) {
	
	reportFail(e.getMessage());
}
	
	
}

	private void partnerhub(Hashtable<String, String> data) {
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		doLoginForAll();
		waitForElementforreportfail("dashboard_xpath");
		test.log(LogStatus.INFO, " Reached at Dashboard Page ");
		String urlname=	driver.getCurrentUrl();
	    String[]	modifiedURL = urlname.split("#");
	    navigate(modifiedURL[0]+"#setup/companies");
	    test.log(LogStatus.INFO, " Reached at Companies Homepage.");
	    int initialcompanies = getNumber("companiescount_xpath");
	    test.log(LogStatus.INFO, " Total existing companies are "+initialcompanies+".");
	    click("create_xpath");
	    test.log(LogStatus.INFO, " Clicked on create button.");
	    click("useparentdata_xpath");
	    wait(2);
	    click("useparentdata_xpath");
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String name = "Testing";
		String combine = name + timeStamp;
		type("name_id", combine);
		type("zipcode_id", "171001");
		click("setting_xpath");
		test.log(LogStatus.INFO, " After selecting and unselecting 'use  parentdata' checkbox, entering "+combine+" as company name, entering zipcode then clicked on setting tab button.");
		type("smscost_id", "9779986649");
	    click("defaultcurrency_id");
	    click("searchcountry_xpath");
		click("selectcurrency_xpath");
		click("senduseremail_xpath");
		click("save_xpath");
		WebDriverWait wait = new WebDriverWait(driver,20);
	    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(prop.getProperty("save_xpath")))));
		test.log(LogStatus.INFO, "Typed in SMS Cost, selecting currency from drop down and selecting 'send user email' checkbox then clicked on save button.");
		driver.navigate().refresh();
		waitForLoad(driver);
		test.log(LogStatus.PASS, "New company is created.");		
	    getElementforloop("//*[text()='"+combine+"']/parent::div/parent::td/preceding-sibling::td/div").click();
	    click("copybutton_xpath");   
	    test.log(LogStatus.INFO, " Clicked on newly created company's checkbox and then clicked on copy button.");
	    click("yesbutton_xpath");
	    flashMessagevisibilityandinvisibilty();
	    getElement("name_id").clear();
	    type("name_id", combine+"1"); 
	    test.log(LogStatus.PASS, "New company  is editable.");
	    click("save_xpath");
	    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(prop.getProperty("save_xpath")))));
	    test.log(LogStatus.PASS, "Copied company is created.");
	    test.log(LogStatus.INFO, " New copied company is created with the name "+combine+"1");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+combine+"1"+"']/parent::div/parent::td/preceding-sibling::td/div")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+combine+"1"+"']/parent::div/parent::td/preceding-sibling::td/div")));
		getElementforloop("//*[text()='"+combine+"1"+"']/parent::div/parent::td/preceding-sibling::td/div").click();
	    getElementforloop("//*[text()='"+combine+"']/parent::div/parent::td/preceding-sibling::td/div").click();
	    click("deletebutton_xpath");
	    click("yesbutton_xpath");
	    flashMessagevisibilityandinvisibilty();
	    waitForElementforreportfail("companiescount_xpath");
	    int finalcompanies = getNumber("companiescount_xpath");
	    if (initialcompanies==finalcompanies) {	
	      	 reportPass("Both new company and copied company are deleted.");
	       }else {
	      	 reportFail("Total remaining companies are not "+initialcompanies+".");
	       }
	    test.log(LogStatus.INFO, "TC7 is completed.");
		
	}
	
/***********************function for counting**********************/
	
	

}
	
