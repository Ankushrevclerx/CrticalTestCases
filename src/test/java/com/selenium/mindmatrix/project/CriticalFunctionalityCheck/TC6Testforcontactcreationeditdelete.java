package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC6Testforcontactcreationeditdelete extends BaseTest {
	
	private Xls_Reader xls;
	
	private String urlname;
	
	private String combine;

	@DataProvider
	private Object[][] getDataTC6() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir") + prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, "TC6");
	}

	@Test(dataProvider = "getDataTC6")

	private void TestTC6(Hashtable<String, String> data) throws InterruptedException {

		if (!DataUtil.isRunnable("TC6Testforcontactcreationeditdelete", xls)) {

			throw new SkipException("Skipping the test as runmode is N");
		}
		test = rep.startTest("TC6 Test for Contact creation edit delete.");
		try {
			openBrowser(prop.getProperty("browsername"));

			navigate(data.get("URL"));
			
			if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
				doLoginMMAmpAndPortal();		
				}else if (data.get("URL").equals("https://dvl-master.amp.vg")) {
					
					doLoginForMaster();
				}else {
				
					doLoginForAll();
				} 
			waitForElement("dashboard_xpath");

			 urlname = driver.getCurrentUrl();

				String finalurl = urlname.split("/#")[0];

			navigate(finalurl + "/#connections/contacts");

			WebDriverWait Test1 = new WebDriverWait(driver, 30);
			Test1.until(ExpectedConditions.visibilityOfAllElements(getElement("createbutton_xpath")));
			JavascriptExecutor ab = (JavascriptExecutor) driver;

			/************/
			test.log(LogStatus.INFO, "Navigated to Contact home page");

			driver.findElement(By.xpath("//span[contains(text(),'Last Activity')]")).click();
			wait(2);
			driver.findElement(By.xpath("//a[contains(text(),'Created On')]")).click();
			wait(2);
			ab.executeScript("arguments[0].click();", getElement("createbutton_xpath"));

			test.log(LogStatus.INFO, "Clicked in Info stepwizard.");
			test.log(LogStatus.INFO, "Clicked on 'Create' button on Contact home page");
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String name = "DummyContact";
			combine = name + timeStamp;
			getElement("firstnametextfield_cssSelector").sendKeys(combine);
			wait(2);
			String timeStamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).replace(".", "");
			test.log(LogStatus.INFO, "Typed successfully in First Name field. Data =>" + timeStamp1);

			String emailid = "contact" + timeStamp1 + "@mailinator.com";
			getElement("emailtextfield_xpath").sendKeys(emailid);
			wait(2);
			test.log(LogStatus.INFO, "Typed successfully in Email field. Data =>" + emailid);

			getElement("mobilenumber_cssSelector").sendKeys("123456789");
			test.log(LogStatus.INFO, "Typed successfully in Mobile number field.");
			getElement("companyname_cssSelector").sendKeys("Testing");
			test.log(LogStatus.INFO, "Typed successfully in Company Name field.");

			ab.executeScript("arguments[0].click();", getElement("addressstepwizard_xpath"));
			test.log(LogStatus.INFO, "Clicked in Address stepwizard.");
			getElement("addressfield_cssSelector").sendKeys("Testing Address Fields");
			test.log(LogStatus.INFO, "Data is entered in Address field.");
			getElement("zipcode_cssSelector").sendKeys("123456789");
			test.log(LogStatus.INFO, "Data is entered in Zipcode field.");

			click("preferencestepwizard_xpath");
			test.log(LogStatus.INFO, "Clicked in Preference stepwizard.");
			click("enablecheckbox_xpath");
			click("intereststatus_xpath");
			click("dropdownintereststatus_xpath");
			test.log(LogStatus.INFO,
					"Auto Adjust checkbox is unselected and closed dropdown is selected  from interest status.");
			ab.executeScript("arguments[0].click();", getElement("savecontact_xpath"));
			wait(1);
			test.log(LogStatus.INFO, "Clicked in Save button.");
			waitForElement("smallpopupbox_xpath");
			
			wait(1);
			ab.executeScript("arguments[0].click();", getElement("smallpopupbox_xpath"));
			wait(1);
			waitForElement("converttouser_xpath");
wait(1);
			ab.executeScript("arguments[0].click();", getElement("crosscontact_xpath"));
			test.log(LogStatus.INFO, "Clicked on cross button");
			waitForElementforreportfail("opportunitysearch_xpath");
			getElement("opportunitysearch_xpath").sendKeys(emailid);
			wait(2);
			test.log(LogStatus.INFO, "Navigated to contact home page.");
			test.log(LogStatus.INFO, "Search box functionality is working fine.");
			ab.executeScript("arguments[0].click();", getElement("opportunityiconofsearch_xpath"));
			waitForElementforreportfail("emailverification_xpath");
			String email = getElement("emailverification_xpath").getText();

			if (email.equals(emailid)) {
				reportPass("Contact is created");
			} else {
				reportFail("Contact is not created");
			}

			waitForElementforreportfail("contactcreated_xpath");

			WebDriverWait Test2 = new WebDriverWait(driver, 60);
			Test2.until(ExpectedConditions.elementToBeClickable(getElement("contactcreated_xpath")));
			waitForElementforreportfail("contactcreated_xpath");

			ab.executeScript("arguments[0].click();", getElement("contactcreated_xpath"));

			test.log(LogStatus.INFO, "Clicked on contact created.");

			Test2.until(ExpectedConditions.visibilityOfAllElements(getElement("companyname_cssSelector")));
			
			wait(1);
			getElement("companyname_cssSelector").clear();
			wait(1);
			test.log(LogStatus.INFO, "Company name is removed");

			ab.executeScript("arguments[0].click();", getElement("savecontact_xpath"));

			test.log(LogStatus.INFO, "Clicked on Save button.");
			wait(2);

			ab.executeScript("arguments[0].click();", getElement("smallpopupbox_xpath"));

			test.log(LogStatus.INFO, "Clicked on  'Success' popup .");

			ab.executeScript("arguments[0].click();", getElement("crosscontact_xpath"));
			wait(2);
			test.log(LogStatus.INFO, "Clicked on  cross button .");
			waitForElementforreportfail("opportunitysearch_xpath");
			getElement("opportunitysearch_xpath").sendKeys(emailid);
			wait(2);
			ab.executeScript("arguments[0].click();", getElement("opportunityiconofsearch_xpath"));

			WebDriverWait wait1 = new WebDriverWait(driver, 10);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[6]//div[1]")));
			String company = getElement("companypath_xpath").getText();

			if (company.equals("No Company")) {
				reportPass("Edit field functionality is working fine");
			} else {
				reportFail("Edit field functionality is not working fine,Company name present is- " + company);
			}
			
			waitForElementforreportfail("opportunitysearch_xpath");
			getElement("opportunitysearch_xpath").sendKeys("DummyContact");
			
			wait(1);
			
			getElement("opportunitysearch_xpath").sendKeys(Keys.ENTER);
			wait(1);
			Test2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'DummyContact')]")));
			
			wait(1);
			ab.executeScript("arguments[0].click();", getElement("allcheckbox_xpath"));

			ab.executeScript("arguments[0].click();", getElement("contactcfdelete_xpath"));

			test.log(LogStatus.INFO, "Clicked on delete button");

			click("orgdispmsgyes_xpath");

			test.log(LogStatus.PASS, "Contact created is Deleted");
			test.log(LogStatus.INFO, "TC6 is completed.");
		} catch (Exception e) {

			test.log(LogStatus.INFO, "script stopped because of " + e.getMessage());
		}

	}

}
