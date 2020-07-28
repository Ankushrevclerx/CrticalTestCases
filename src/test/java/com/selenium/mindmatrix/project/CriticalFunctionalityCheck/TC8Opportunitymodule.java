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

public class TC8Opportunitymodule extends BaseTest {

	private Xls_Reader xls;

	@DataProvider
	public Object[][] getDataTC8() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir") + prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, "TC8");
	}

	@Test(dataProvider = "getDataTC8")
	private void TestTC2(Hashtable<String, String> data) throws InterruptedException {

		

		if (!DataUtil.isRunnable("TC8Opportunitymodule", xls)) {

			throw new SkipException("Skipping the test as runmode is N");
		}

		test = rep.startTest("TC8=> Opportunity Module");

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

				openBrowserandNavigate(data);
				doLoginForAll();
				waitForElementforreportfail("dashboard_xpath");
				test.log(LogStatus.INFO, " Reached at Dashboard Page ");
				navigate(data.get("URL") + "/#setup/opportunity");
				waitForElementforreportfail("create_xpath");
				test.log(LogStatus.INFO, " Reached at Opportunity Homepage.");
				click("create_xpath");
				test.log(LogStatus.INFO, " Clicked on create button.");
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				String name = "Dummy";
				String combine = name + timeStamp;
				type("name_id", combine);
				click("opportunitynext_xpath");
				wait(2);
				click("opportunitynext_xpath");
				wait(2);
				waitForElementforreportfail("opportunityfinishButton_xpath");
				clickJS(prop.getProperty("opportunitysave_xpath"));

				waitForElementforreportfail("confermpopup_xpath");
				click("confermpopup_xpath");
				driver.navigate().refresh();

				try {
					customWaitForElement(
							"//*[@id='inbox-table']/child::tbody/tr/td[2]/div/span[text()='" + combine + "']");
					test.log(LogStatus.PASS, "Default opportunity " + combine + "  is created");

				}

				catch (Exception e) {
					test.log(LogStatus.FAIL, "Default opportunity   " + combine + "  is not created.");
					reportFail(" Default opportunity  " + combine + "  is not created.");

				}
				editopportunity("//*[@id='inbox-table']/child::tbody/tr/td[2]/div/span[text()='" + combine + "']",
						combine);
				deleteopportunity(combine + "Test");

				break;
			}
			test.log(LogStatus.INFO, "TC8 is completed.");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	private void partnerhub(Hashtable<String, String> data) {

		waitForElementforreportfail("dashboard_xpath");
		test.log(LogStatus.INFO, " Reached at Dashboard Page ");
		String urlname = driver.getCurrentUrl();
		String[] modifiedURL = urlname.split("#");

		navigate(modifiedURL[0] + "#setup/opportunity");
		test.log(LogStatus.INFO, " Reached at Companies Homepage.");
		click("create_xpath");
		test.log(LogStatus.INFO, " Clicked on create button.");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String name = "Dummy";
		String combinee = name + timeStamp;
		type("name_id", combinee);
		clickJS(prop.getProperty("opportunitynext_xpath"));
		wait(3);
		clickJS(prop.getProperty("opportunitynext_xpath"));
		waitForElementforreportfail("opportunityfinishButton_xpath");
		wait(2);

		// click("opportunitysave_xpath");
		clickJS(prop.getProperty("opportunitysave_xpath"));
		waitForElementforreportfail("confermpopup_xpath");
		click("confermpopup_xpath");
		driver.navigate().refresh();

		try {
			customWaitForElement("//*[@id='inbox-table']/child::tbody/tr/td[2]/div/span[text()='" + combinee + "']");

			test.log(LogStatus.INFO, "Default opportunity  " + combinee + "  is created");

		}

		catch (Exception e) {
			test.log(LogStatus.FAIL, "Default opportunity   " + combinee + "  is not created.");
			reportFail(" Default opportunity  " + combinee + "  is not created.");

		}

		editopportunity("//*[@id='inbox-table']/child::tbody/tr/td[2]/div/span[text()='" + combinee + "']", combinee);

		deleteopportunity(combinee + "Test");

	}

	private void deleteopportunity(String xpath)

	{
		driver.navigate().refresh();
		driver.findElement(By.xpath("//*[@id='msg1']/td[2]/div/span[text()='" + xpath
				+ "']/parent::div/parent::td/preceding-sibling::td/child::div/label/span")).click();

		waitForElementforreportfail("OpportunityDeleteButton_xpath");
		click("OpportunityDeleteButton_xpath");
		click("confermpopup_xpath");

		test.log(LogStatus.PASS, "Opportunity " + xpath + "  is deleted.");

	}

	private void editopportunity(String xpath, String name) {
		driver.navigate().refresh();
		clickJS(xpath);
		clickJS(prop.getProperty("organizationViewDropdown_xpath"));
		waitForElementforreportfail("orgListForDropdown_xpath");
		wait(2);
		// clickJS(prop.getProperty("orginfoclick_xpath"));
		click("orginfoclick_xpath");
		waitForElement("opportunityname_xpath");
		wait(2);
		type("opportunityname_xpath", name + "Test");
		wait(2);
		clickJS(prop.getProperty("orgsaveButton_xpath"));

		try {
			customWaitForElement(
					"//*[@id='inbox-table']/child::tbody/tr/td[2]/div/span[text()='" + name + "Test" + "']");

			test.log(LogStatus.PASS, "Newly created Opportunity is edited with name  " + name + "Test");

		}

		catch (Exception e) {
			test.log(LogStatus.FAIL, "Default opportunity   " + name + "Test" + "  is not Edited.");
			reportFail(" Default opportunity  " + name + "  is not Edited.");

		}

	}

	private void customWaitForElement(String xpath) {
		WebDriverWait aaa = new WebDriverWait(driver, 40);
		aaa.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void openBrowserandNavigate(Hashtable<String, String> data) {
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));

	}

}
