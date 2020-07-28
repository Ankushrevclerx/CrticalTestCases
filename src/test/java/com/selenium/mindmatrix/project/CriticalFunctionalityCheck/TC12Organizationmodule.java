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
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;

import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC12Organizationmodule extends BaseTest {
	SoftAssert sassert;
	String testCaseName;
	Xls_Reader xls;

	@DataProvider
	private Object[][] getDataTC12() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir") + prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, "TC12");
	}

	@Test(dataProvider = "getDataTC12")
	private void TestTC2(Hashtable<String, String> data) throws InterruptedException {

		
		
		
		
		if (!DataUtil.isRunnable("TC12Organizationmodule", xls)) {

			throw new SkipException("Skipping the test as runmode is N");
		}
		
		test = rep.startTest("TC12=> Organization module");

		String URL = data.get("URL");
		try {

			switch (URL) {

			case "https://partnerhub.intersystems.com/user-login":
				openBrowserandNavigate(data);
				doLoginForAll();
				partnerhub(data);

				break;

			case "https://rajantportal.amp.vg/user-login":
				openBrowserandNavigate(data);
				doLoginForAll();
				partnerhub(data);

				break;

			case "https://datto.amp.vg/user-login":

				openBrowserandNavigate(data);
				doLoginForAll();
				partnerhub(data);

				break;

			case "https://axcient.amp.vg/user-login":
				openBrowserandNavigate(data);
				doLoginForAll();
				partnerhub(data);

				break;

			case "https://levitonpartnerconnection.amp.vg/user-login":
				openBrowserandNavigate(data);
				doLoginForAll();
				partnerhub(data);

				break;

			case "https://partner-marketing.bitdefender.com/user-login":
				openBrowserandNavigate(data);
				doLoginForAll();
				partnerhub(data);

				break;

			case "https://borderstates.amp.vg/user-login":
				openBrowserandNavigate(data);
				doLoginForAll();
				partnerhub(data);

				break;
			case "https://dvl-master.amp.vg":
				openBrowserandNavigate(data);
				doLoginForMaster();
				partnerhub(data);
				
				break;
				
			case "https://mm.amp.vg":
				openBrowserandNavigate(data);
				doLoginMMAmpAndPortal();
				partnerhub(data);
				break;
			default:

				openBrowser(prop.getProperty("browsername"));
				navigate(data.get("URL"));
				// waitForElementforreportfail("username_xpath");
				doLoginForAll();
				waitForElementforreportfail("dashboard_xpath");
				test.log(LogStatus.INFO, " Reached at Dashboard Page ");
				navigate(data.get("URL") + "/#setup/organizations");
				waitForElementforreportfail("create_xpath");
				test.log(LogStatus.INFO, " Reached at Organization Homepage.");
				click("create_xpath");
				test.log(LogStatus.INFO, " Clicked on create button.");
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				String name = "Dummy";
				String combine = name + timeStamp;
				type("name_id", combine);
				clickJS(prop.getProperty("opportunitynext_xpath"));
				wait(2);
				clickJS(prop.getProperty("opportunitynext_xpath"));
				waitForElementforreportfail("opportunityfinishButton_xpath");
				wait(2);

				clickJS(prop.getProperty("opportunitysave_xpath"));

				driver.navigate().refresh();
				try {
					customWaitForElement(
							"//*[@id='inbox-table']/child::tbody/tr/td[3]/div/span[text()='" + combine + "']");

					test.log(LogStatus.INFO, "Organization  " + combine + "  is created");
					test.log(LogStatus.PASS, "Organization is created.");

				}

				catch (Exception e) {
					test.log(LogStatus.FAIL, "Organization   " + combine + "  is not created.");
					reportFail(" Organization  " + combine + "  is not created.");

				}

				editCompany("//*[@id='inbox-table']/child::tbody/tr/td[3]/div/span[text()='" + combine + "']", combine);
				deleteopportunity(combine + "Test");

			}
			test.log(LogStatus.INFO, "TC12 is completed");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	private void partnerhub(Hashtable<String, String> data) {
		
		waitForElementforreportfail("dashboard_xpath");
		test.log(LogStatus.INFO, " Reached at Dashboard Page ");
		String urlname = driver.getCurrentUrl();
		String[] modifiedURL = urlname.split("#");
		navigate(modifiedURL[0] + "#setup/organizations");
		test.log(LogStatus.INFO, " Reached at Organization Homepage.");
		click("create_xpath");
		test.log(LogStatus.INFO, " Clicked on create button.");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String name = "Dummy";
		String combinee = name + timeStamp;
		type("name_id", combinee);
		// click("opportunitynext_xpath");
		clickJS(prop.getProperty("opportunitynext_xpath"));
		wait(2);
		clickJS(prop.getProperty("opportunitynext_xpath"));
		wait(2);
		waitForElementforreportfail("opportunityfinishButton_xpath");
		// click("opportunitysave_xpath");
		clickJS(prop.getProperty("opportunitysave_xpath"));

		driver.navigate().refresh();
		try {
			customWaitForElement("//*[@id='inbox-table']/child::tbody/tr/td[3]/div/span[text()='" + combinee + "']");

			test.log(LogStatus.INFO, "Organization  " + combinee + "  is created");
			test.log(LogStatus.PASS, "Organization is created.");

		}

		catch (Exception e) {
			test.log(LogStatus.FAIL, "Organization   " + combinee + "  is not created.");
			reportFail(" Organization   " + combinee + "  is not created.");

		}

		editCompany("//*[@id='inbox-table']/child::tbody/tr/td[3]/div/span[text()='" + combinee + "']", combinee);

		deleteopportunity(combinee + "Test");

	}

	private void deleteopportunity(String xpath)

	{
		driver.navigate().refresh();
		driver.findElement(By.xpath("//*[@id='msg1']/td[3]/div/span[text()='" + xpath
				+ "']/parent::div/parent::td/preceding-sibling::td/child::div/label/span")).click();

		waitForElementforreportfail("OpportunityDeleteButton_xpath");
		click("OpportunityDeleteButton_xpath");
		click("confermpopup_xpath");

		test.log(LogStatus.INFO, "Organization   " + xpath + "  is deleted.");
		test.log(LogStatus.PASS, "Newly created organization is deleted.");

	}

	private void editCompany(String xpath, String name) {
		driver.navigate().refresh();
		clickJS(xpath);
		clickJS(prop.getProperty("organizationViewDropdown_xpath"));
		waitForElementforreportfail("orgListForDropdown_xpath");
		// clickJS(prop.getProperty("orginfoclick_xpath"));
		click("orginfoclick_xpath");
		waitForElement("orgsaveButton_xpath");
		wait(2);
		type("opportunityname_xpath", name + "Test");
		clickJS(prop.getProperty("orgsaveButton_xpath"));
		// click("orgsaveButton_xpath");
		wait(2);

		driver.navigate().refresh();
		try {
			customWaitForElement(
					"//*[@id='inbox-table']/child::tbody/tr/td[3]/div/span[text()='" + name + "Test" + "']");

			test.log(LogStatus.INFO, "Organization   " + name + "Test" + "  is Editable.");
			test.log(LogStatus.PASS, "Newly created organization is edited.");
		}

		catch (Exception e) {
			test.log(LogStatus.FAIL, "Organization   " + name + "Test" + "  is Edited.");
			reportFail(" Organization  " + name + "Test" + "  is Edited.");

		}

	}

	private void customWaitForElement(String xpath) {
		WebDriverWait aaa = new WebDriverWait(driver, 40);
		aaa.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	
	public void openBrowserandNavigate(Hashtable<String, String> data)
	{
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		
	}
}
