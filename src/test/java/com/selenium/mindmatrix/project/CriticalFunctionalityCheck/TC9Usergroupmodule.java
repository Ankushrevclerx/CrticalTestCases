package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC9Usergroupmodule extends BaseTest {
	
	private Xls_Reader xls;	
	private String combine;
	private String namefield;
	

	@DataProvider
	private Object[][] getDataTC9() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir") + prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, "TC9");
	}

	@Test(dataProvider = "getDataTC9")

	private void TestTC9(Hashtable<String, String> data) throws InterruptedException {

		if (!DataUtil.isRunnable("TC9Usergroupmodule", xls)) {

			throw new SkipException("Skipping the test as runmode is N");
		}
		test = rep.startTest("TC9 User group module");
		try {
			openBrowser(prop.getProperty("browsername"));

			navigate(data.get("URL"));
			waitForElementforreportfail("loginbutton_id");
			
			if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
				doLoginMMAmpAndPortal();		
				}else if (data.get("URL").equals("https://dvl-master.amp.vg")) {
					
					doLoginForMaster();
				}else {
				
					doLoginForAll();
				} 
			waitForElement("dashboard_xpath");

			String urlname = driver.getCurrentUrl();

			String finalurl = urlname.split("/#")[0];

			navigate(finalurl + "/#setup/groups");

			WebDriverWait waitingpagination = new WebDriverWait(driver, 30);
			waitingpagination.until(ExpectedConditions.visibilityOfAllElements(getElement("createbutton_xpath")));
			JavascriptExecutor Test2 = (JavascriptExecutor) driver;

			/************/
			test.log(LogStatus.INFO, "Navigated to Contact home page");

			Test2.executeScript("arguments[0].click();", getElement("createbutton_xpath"));
			wait(2);
			waitForElementforreportfail("usernamefield_xpath");
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String name = "Dummyuser";
			combine = name + timeStamp;
			getElement("usernamefield_xpath").sendKeys(combine);
			String timeStamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).replace(".", "");
			test.log(LogStatus.INFO, "Typed successfully in  Name field. Data =>" + timeStamp1);

			click("ugroupdes_xpath");

			getElement("ugroupdes_xpath").clear();

			getElement("ugroupdes_xpath").sendKeys("TEST");

			test.log(LogStatus.INFO, "User group name and description is entered.");

			click("nextbutton_xpath");

			if (getElement("searchfield_xpath").isEnabled()) {
				test.log(LogStatus.PASS, " Search for records Box is present and enabled");
			} else {

				test.log(LogStatus.FAIL, " Search for records Box is not present");
			}

			click("all_xpath");
			wait(2);
			getElement("usersearchbox_xpath").sendKeys("pkumar");
			wait(1);
			getElement("usersearchbox_xpath").sendKeys(Keys.ENTER);

			wait(2);
			waitForElement("allvalue_xpath");
			String value = getElement("allvalue_xpath").getText();
			click("add1_xpath");
			WebDriverWait Test1 = new WebDriverWait(driver, 30);
			Test1.until(ExpectedConditions.textToBe(By.xpath(prop.getProperty("add1_xpath")), "remove"));

			click("added_xpath");
			waitForElement("firstval_xpath");
			String valueadded = getElement("firstval_xpath").getText();

			
			if (value.equals(valueadded)) {
				reportPass("User added is same");
			} else {
				reportFail("User added is not same");
			}
			click("usrgronotadded_xpath");
			wait(2);
			getElement("usersearchbox_xpath").sendKeys(value);
			getElement("usersearchbox_xpath").sendKeys(Keys.ENTER);

			waitForElement("usergrpsiteadminsorrymessage_xpath");
			if (isElementPresent1(prop.getProperty("usergrpsiteadminsorrymessage_xpath"))) {

				reportPass("User added is not present in 'NotAdded' folder ");
			} else {
				reportFail("User added is present in 'NotAdded' folder ");
			}

			click("all_xpath");

			wait(2);
			getElement("usersearchbox_xpath").sendKeys("pkumar");
			wait(1);
			getElement("usersearchbox_xpath").sendKeys(Keys.ENTER);

			wait(2);

			waitForElement("allvalue_xpath");

			click("add1_xpath");

			Test1.until(ExpectedConditions.textToBe(By.xpath(prop.getProperty("add1_xpath")), "add"));

			click("next_xpath");
			click("usergrpsiteadminfinish_xpath");
waitForElement("smallpopupbox_xpath");
			
			wait(1);
			Test2.executeScript("arguments[0].click();", getElement("smallpopupbox_xpath"));
			wait(1);
			waitForElement("added_xpath");
wait(1);
Test2.executeScript("arguments[0].click();", getElement("crosscontact_xpath"));
			test.log(LogStatus.INFO, "Clicked on cross button");
			
			waitForElement("searchfield_xpath");
			getElement("searchfield_xpath").sendKeys(combine);
			getElement("searchfield_xpath").sendKeys(Keys.ENTER);
			wait(1);
			Test2.executeScript("arguments[0].click();", getElement("namehonepage_xpath"));

			click("viewbutton_xpath");
			click("viewbuttoninfo_xpath");

			waitForElement("usernamefield_xpath");
			getElement("usernamefield_xpath").clear();
			String namee = "UserEdit";
			namefield = namee + timeStamp;
			getElement("usernamefield_xpath").sendKeys(namefield);
			String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).replace(".", "");
			test.log(LogStatus.INFO, "Typed successfully in  Name field. Data =>" + timeStamp2);

			click("usergroupsave_xpath");

			waitForElement("searchfield_xpath");
			getElement("searchfield_xpath").clear();
			getElement("searchfield_xpath").sendKeys(namefield);
			wait(1);
			getElement("searchfield_xpath").sendKeys(Keys.ENTER);
			
			wait(1);

			waitForElement("dectexthomepage_xpath");
			String desvalue = getElement("dectexthomepage_xpath").getText();

			if (desvalue.equals(namefield)) {
				reportPass("Edit field functionality is working fine");
			} else {
				reportFail("Edit field functionality is not working fine");
			}
			waitForElement("searchfield_xpath");
			getElement("searchfield_xpath").clear();
			getElement("searchfield_xpath").sendKeys("UserEdit");

			getElement("searchfield_xpath").sendKeys(Keys.ENTER);

			WebDriverWait wait1 = new WebDriverWait(driver, 10);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'UserEdit')]")));
			wait(1);

			Test2.executeScript("arguments[0].click();", getElement("delete_xpath"));
			Test2.executeScript("arguments[0].click();", getElement("contactcfdelete_xpath"));

			test.log(LogStatus.INFO, "Clicked on delete button");

			Test2.executeScript("arguments[0].click();", getElement("orgdispmsgyes_xpath"));
			test.log(LogStatus.INFO, "User Group created is Deleted");
			test.log(LogStatus.INFO, "TC9 is completed.");

		} catch (Exception e) {

			test.log(LogStatus.INFO, "script stopped because of " + e.getMessage());
		}

	}

	private boolean isElementPresent1(String locatorkey) {
		List<WebElement> elementlist = null;

		elementlist = driver.findElements(By.xpath(locatorkey));

		if (elementlist.size() == 0)
			return false;
		else
			return true;

	}
}