package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class TC11Organizationgroupmodule extends BaseTest {
	
	private Xls_Reader xls;	
	private String combine;
	private String namefield;
	List<Object> senddata = new ArrayList<Object>();

	@DataProvider
	private Object[][] getDataTC11() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir") + prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, "TC11");
	}

	@Test(dataProvider = "getDataTC11")

	private void TestTC11(Hashtable<String, String> data) throws InterruptedException {

		if (!DataUtil.isRunnable("TC11Organizationgroupmodule", xls)) {

			throw new SkipException("Skipping the test as runmode is N");
		}
		test = rep.startTest("TC11=> Organization group module.");
		try {
			String URL = data.get("URL");
			switch (URL) {

			case "https://dvl-master.amp.vg":
				dvlmaster(data);
				break;

			default:
				openBrowser(prop.getProperty("browsername"));

				navigate(data.get("URL"));

				if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
				doLoginMMAmpAndPortal();	
					}else  {
						doLoginForAll();
					}
				waitForElement("dashboard_xpath");

				String urlname = driver.getCurrentUrl();

				String finalurl = urlname.split("/#")[0];

				navigate(finalurl + "/#setup/organization/groups");

				WebDriverWait waitingpagination = new WebDriverWait(driver, 30);
				waitingpagination.until(ExpectedConditions.visibilityOfAllElements(getElement("createbutton_xpath")));
				JavascriptExecutor ab = (JavascriptExecutor) driver;

				/************/
				test.log(LogStatus.INFO, "Navigated to Contact home page");

				driver.findElement(By.xpath("//span[contains(text(),'Name')]")).click();
				wait(2);
				driver.findElement(By.xpath("//a[contains(text(),'Updated On')]")).click();
				wait(2);

				ab.executeScript("arguments[0].click();", getElement("createbutton_xpath"));
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

				waitForElement("searchfield_xpath");

				click("all_xpath");
				test.log(LogStatus.INFO, "'All' folder is clickable.");
				click("added_xpath");
				test.log(LogStatus.INFO, "'Added' folder is clickable.");
				click("usrgronotadded_xpath");
				test.log(LogStatus.INFO, "'NotAdded' folder is clickable.");

				click("nextbutton_xpath");
				test.log(LogStatus.INFO, "Clicked on Next button.");
				waitForElement("searchfield_xpath");

				if (getElement("searchfield_xpath").isEnabled()) {
					test.log(LogStatus.PASS, " Search for records Box is present and enabled");
				} else {

					test.log(LogStatus.FAIL, " Search for records Box is not present");
				}
				waitForElement("orguser_xpath");
				waitForElement("usersearchbox_xpath");
				wait(2);
				getElement("usersearchbox_xpath").sendKeys("pkumar");
				wait(1);
				getElement("usersearchbox_xpath").sendKeys(Keys.ENTER);

				wait(2);

				waitForElement("allvalue_xpath");
				String value = getElement("allvalue_xpath").getText();
				click("add1_xpath");

				WebDriverWait Test1 = new WebDriverWait(driver, 30);
				Test1.until(ExpectedConditions.textToBe(By.xpath(prop.getProperty("add1_xpath")), "unassign"));

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
wait(3);
				click("all_xpath");

				waitForElement("allvalue_xpath");

				click("add1_xpath");

				Test1.until(ExpectedConditions.textToBe(By.xpath(prop.getProperty("add1_xpath")), "assign"));

				click("usergrpsiteadminfinish_xpath");

				waitForElement("smallpopupbox_xpath");
				ab.executeScript("arguments[0].click();", getElement("smallpopupbox_xpath"));
				waitForElement("added_xpath");
				waitForElement("crosscontact_xpath");
				wait(1);
				ab.executeScript("arguments[0].click();", getElement("crosscontact_xpath"));

				test.log(LogStatus.INFO, "Clicked on cross button");
				wait(2);
				waitForElement("searchfield_xpath");
				getElement("searchfield_xpath").sendKeys(combine);
				getElement("searchfield_xpath").sendKeys(Keys.ENTER);

				ab.executeScript("arguments[0].click();", getElement("namehomepage_xpath"));

				waitForElement("viewbutton_xpath");
				wait(1);
				ab.executeScript("arguments[0].click();", getElement("viewbutton_xpath"));
				ab.executeScript("arguments[0].click();", getElement("viewbuttoninfo_xpath"));
				waitForElement("usernamefield_xpath");
				getElement("usernamefield_xpath").clear();
				wait(1);
				String namee = "OrganizationGroupEdit";
				namefield = namee + timeStamp;
				getElement("usernamefield_xpath").sendKeys(namefield);
				String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).replace(".", "");
				test.log(LogStatus.INFO, "Typed successfully in  Name field. Data =>" + timeStamp2);

				
				ab.executeScript("arguments[0].click();", getElement("save_xpath"));
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
				getElement("searchfield_xpath").sendKeys("OrganizationGroupEdit");

				getElement("searchfield_xpath").sendKeys(Keys.ENTER);
				WebDriverWait Test2 = new WebDriverWait(driver, 10);
				Test2.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//span[contains(text(),'OrganizationGroupEdit')]")));
				wait(1);

				ab.executeScript("arguments[0].click();", getElement("delete_xpath"));

				ab.executeScript("arguments[0].click();", getElement("contactcfdelete_xpath"));

				test.log(LogStatus.INFO, "Clicked on delete button");

				ab.executeScript("arguments[0].click();", getElement("orgdispmsgyes_xpath"));
				test.log(LogStatus.INFO, "Organization Group created is Deleted");
				test.log(LogStatus.INFO, "TC11 is completed.");

			}
		} catch (Exception e) {

			test.log(LogStatus.INFO, "script stopped because of " + e.getMessage());
		}
	}

	private void dvlmaster(Hashtable<String, String> data) {
		openBrowser(prop.getProperty("browsername"));

		navigate(data.get("URL"));

		
		doLoginForMaster();
		waitForElement("dashboard_xpath");

		String urlname = driver.getCurrentUrl();

		String finalurl = urlname.split("/#")[0];

		navigate(finalurl + "/#setup/organization/groups");

		WebDriverWait waitingpagination = new WebDriverWait(driver, 30);
		waitingpagination.until(ExpectedConditions.visibilityOfAllElements(getElement("createbutton_xpath")));
		JavascriptExecutor Test3 = (JavascriptExecutor) driver;

		/************/
		test.log(LogStatus.INFO, "Navigated to Contact home page");

		Test3.executeScript("arguments[0].click();", getElement("createbutton_xpath"));
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

		waitForElement("searchfield_xpath");

		click("all_xpath");
		test.log(LogStatus.INFO, "'All' folder is clickable.");
		click("added_xpath");
		test.log(LogStatus.INFO, "'Added' folder is clickable.");
		click("usrgronotadded_xpath");
		test.log(LogStatus.INFO, "'NotAdded' folder is clickable.");

		click("nextbutton_xpath");

		waitForElement("searchfield_xpath");

		if (getElement("searchfield_xpath").isEnabled()) {
			test.log(LogStatus.PASS, " Search for records Box is present and enabled");
		} else {

			test.log(LogStatus.FAIL, " Search for records Box is not present");
		}
waitForElement("orguser_xpath");
		waitForElement("usersearchbox_xpath");
		getElement("usersearchbox_xpath").sendKeys("pkumar");
		wait(2);
		getElement("usersearchbox_xpath").sendKeys(Keys.ENTER);
		wait(3);

		waitForElement("allvalue_xpath");
		String value = getElement("allvalue_xpath").getText();
		click("add1_xpath");

		WebDriverWait waiting77 = new WebDriverWait(driver, 30);
		waiting77.until(ExpectedConditions.textToBe(By.xpath(prop.getProperty("add1_xpath")), "unassign"));

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
		wait(3);
		click("all_xpath");

		wait(1);
		waitForElement("usersearchbox_xpath");
		getElement("usersearchbox_xpath").sendKeys("pkumar");
		wait(1);
		getElement("usersearchbox_xpath").sendKeys(Keys.ENTER);
		wait(1);

		waitForElement("allvalue_xpath");

		click("add1_xpath");

		waiting77.until(ExpectedConditions.textToBe(By.xpath(prop.getProperty("add1_xpath")), "assign"));

		click("usergrpsiteadminfinish_xpath");
		waitForElement("smallpopupbox_xpath");
		Test3.executeScript("arguments[0].click();", getElement("smallpopupbox_xpath"));
		waitForElement("added_xpath");
		waitForElement("crosscontact_xpath");
		wait(1);
		Test3.executeScript("arguments[0].click();", getElement("crosscontact_xpath"));
		test.log(LogStatus.INFO, "Clicked on cross button");
		waitForElement("searchfield_xpath");
		getElement("searchfield_xpath").sendKeys(combine);
		getElement("searchfield_xpath").sendKeys(Keys.ENTER);

		Test3.executeScript("arguments[0].click();", getElement("namehomepage_xpath"));

		Test3.executeScript("arguments[0].click();", getElement("viewbutton_xpath"));
		Test3.executeScript("arguments[0].click();", getElement("viewbuttoninfo_xpath"));
		waitForElement("usernamefield_xpath");
		getElement("usernamefield_xpath").clear();
		wait(2);
		String namee = "OrganizationGroupEdit";
		namefield = namee + timeStamp;
		getElement("usernamefield_xpath").sendKeys(namefield);
		String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).replace(".", "");
		test.log(LogStatus.INFO, "Typed successfully in  Name field. Data =>" + timeStamp2);

		click("save_xpath");

		waitForElement("searchfield_xpath");
		getElement("searchfield_xpath").clear();
		getElement("searchfield_xpath").sendKeys(namefield);
		getElement("searchfield_xpath").sendKeys(Keys.ENTER);
		wait(2);
		waitForElement("dectexthomepage_xpath");
		String desvalue = getElement("dectexthomepage_xpath").getText();

		if (desvalue.equals(namefield)) {
			reportPass("Edit field functionality is working fine");
		} else {
			reportFail("Edit field functionality is not working fine" + " value present -" + desvalue
					+ "expected value- " + namefield);
		}
		waitForElement("searchfield_xpath");
		getElement("searchfield_xpath").clear();
		getElement("searchfield_xpath").sendKeys("OrganizationGroupEdit");
		getElement("searchfield_xpath").sendKeys(Keys.ENTER);

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[contains(text(),'OrganizationGroupEdit')]")));
		wait(1);
		Test3.executeScript("arguments[0].click();", getElement("delete_xpath"));

		System.out.println("deleted");
		Test3.executeScript("arguments[0].click();", getElement("contactcfdelete_xpath"));

		test.log(LogStatus.INFO, "Clicked on delete button");

		Test3.executeScript("arguments[0].click();", getElement("orgdispmsgyes_xpath"));
		test.log(LogStatus.INFO, "Organization Group created is Deleted");
		test.log(LogStatus.INFO, "TC11 is completed.");

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
