package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC4Testfortemplatedashboardbanner extends BaseTest {

	private Xls_Reader xls;


	@DataProvider
	private Object[][] getDataTC4() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir") + prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, "TC4");
	}

	@Test(dataProvider = "getDataTC4")
	private void TestTC4(Hashtable<String, String> data) throws InterruptedException {

		if (!DataUtil.isRunnable("TC4Testfortemplatedashboardbanner", xls)) {

			throw new SkipException("Skipping the test as runmode is N");
		}

		test = rep.startTest("TC4=> Test for template dashboard banner.");

		try {

			String URL = data.get("URL");
			switch (URL) {

			case "https://dvl-master.amp.vg":

				master(data);

				break;

			case "https://msp.amp.vg":

				mspdashboard(data);

				break;

			case "https://levitonpartnerconnection.amp.vg/user-login":

				levitrondashboard(data);

				break;

			default:

				openBrowser(prop.getProperty("browsername"));
				navigate(data.get("URL"));
				
				if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
					doLoginMMAmpAndPortal();	
				} else {
					doLoginForAll();
				}
				waitForElementforreportfail("dashboard_xpath");
				WebElement source = getElement("webbanner_xpath");
				System.out.println(source.getAttribute("innerHTML"));
				String urlname = driver.getCurrentUrl();

				String finalurl = urlname.split("/#")[0];

				navigate(finalurl + "/#corporatemarketing");
				waitForElement("dashboard_xpath");
				waitForElementforreportfail("dashboard_xpath");
				test.log(LogStatus.INFO, " Reached at Dashboard Page ");

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				if (isElementPresent("personaname_xpath")) {

					if (isElementPresent("markitingportal_xpath")) {
						test.log(LogStatus.INFO, "Default Marketing Portal persona is present");

						navigate(finalurl + "/#setup/dashboard/corporatemarketing");

						waitForElementforreportfail("personaname_xpath");

						clickJS(prop.getProperty("clicktocustomize_xpath"));

						test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'to select WebBanner Template");
						wait(3);
						waitForElement("bannertemplatearea_xpath");

						waitForElementforreportfail("searchfields_xpath");
						getElement("searchfields_xpath").sendKeys("DashboardBanner");
						wait(1);
						test.log(LogStatus.INFO,
								"Predefined 'Dashboard Banner' is entered in search for record field ");
						getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);

						waitForElementforreportfail("selectedbannertemplatetext_xpath");

						test.log(LogStatus.INFO, "Waiting for banner to appear on screen ");

					} else {
						reportFail("Default persona does not matched ");
					}

				} else {
					driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
					test.log(LogStatus.INFO, "Default Marketing Portal persona is not present in dashboard page");
					navigate(finalurl + "/#setup/dashboard/corporatemarketing");
					waitForElement("clicktocustomize_xpath");
					loadForFirstFrame();
					clickJS(prop.getProperty("clicktocustomize_xpath"));
					test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
					waitForElementforreportfail("bannertemplatearea_xpath");
					getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
					wait(1);
					test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
					getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
					waitForElementforreportfail("selectedbannertemplatetext_xpath");

					click("unselectbanner_xpath");
					test.log(LogStatus.INFO,
							".Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselected it as it was already selected");
				}
				wait(1);

				JavascriptExecutor ab = (JavascriptExecutor) driver;
				ab.executeScript("arguments[0].click();", getElement("selectedbannertemplatetext_xpath"));

				test.log(LogStatus.INFO, "1Predefined banner 'DashboardBanner(Do Not Delete)' is selected");
				click("savebuttononwebbannertemplatepage_xpath");
				test.log(LogStatus.INFO, "Clicked on 'Save' button");
				waitForElement("clicktocustomize_xpath");
				waitForElementforreportfail("waitforframe_xpath");
				navigate(finalurl + "/#corporatemarketing");
				test.log(LogStatus.INFO,
						"Landed to main Dashboard page to verify 'Is selected banner visible or not' ");

				loadForFirstFrame();
				if (verifyFrameText("Sample Dashboard Banner", 60, 0)) {
					test.log(LogStatus.PASS, "Dashboard banner is appearing.");
					test.log(LogStatus.INFO, "selected banner is visible on main Dashboard page ");
				} else {

					reportFail("Dashboard banner is not appearing.");
				}

				driver.switchTo().parentFrame();

				navigate(finalurl + "/#setup/dashboard/corporatemarketing");
				waitForElement("clicktocustomize_xpath");

				loadForFirstFrame();
				clickJS(prop.getProperty("clicktocustomize_xpath"));

				test.log(LogStatus.INFO, "Clicked on 'Clicked to customize' button");

				waitForElementforreportfail("selectedbannertemplatetext_xpath");
				getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
				wait(1);
				test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
				getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
				waitForElementforreportfail("selectedbannertemplatetext_xpath");

				click("unselectbanner_xpath");
				test.log(LogStatus.INFO, "Clicked on selected dashboard banner to unselect it");
				click("savebuttononwebbannertemplatepage_xpath");
				test.log(LogStatus.INFO, "Clicked on 'Save' button");
				loadForFirstFrame();

				navigate(finalurl + "/#corporatemarketing");

				test.log(LogStatus.INFO, "Landed to main Dashboard page to verify default banner is visible or not' ");
				waitForElement("personaname_xpath");
				waitForElementforreportfail("personaname_xpath");

				if (isElementPresent("markitingportal_xpath")) {
					reportPass("Default WebTemplate banner is visible ");
				} else {
					reportFail("Default WebTemplate banner is not visible ");
				}
				test.log(LogStatus.INFO, "TC4 is completed.");

			}
		} catch (Exception e) {
			test.log(LogStatus.INFO, e.getMessage());
		}
	}

	private void levitrondashboard(Hashtable<String, String> data) {

		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		
		doLoginForAll();
		test.log(LogStatus.INFO, "Clicked on 'LogIn' button ");

		waitForLoad(driver);
		waitForElement("dashboard_xpath");
		waitForElementforreportfail("dashboard_xpath");
		test.log(LogStatus.INFO, " Reached at Dashboard Page ");
		String urlname = driver.getCurrentUrl();

		String finalurl = urlname.split("/#")[0];

		navigate(finalurl + "/#corporatemarketing");

		waitForElement("dashboard_xpath");
		waitForElementforreportfail("dashboard_xpath");
		if (isElementPresent("personaname_xpath")) {

			if (isElementPresent("markitingportal_xpath")) {
				test.log(LogStatus.INFO, "Default Marketing Portal persona is present");

				navigate(finalurl + "/#setup/dashboard/corporatemarketing");

				waitForElementforreportfail("personaname_xpath");
				loadForFirstFrame();

				clickJS(prop.getProperty("clicktocustomize_xpath"));

				test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'to select WebBanner Template");

				waitForElementforreportfail("selectedbannertemplatetext_xpath");
				getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
				wait(1);
				test.log(LogStatus.INFO, "Predefined 'Dashboard Banner' is entered in search for record field ");
				getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);

				waitForElementforreportfail("selectedbannertemplatetext_xpath");

				test.log(LogStatus.INFO, "Waiting for banner to appear on screen ");

			} else {
				reportFail("Default persona does not matched ");
			}

		} else {
			test.log(LogStatus.INFO, "Default Marketing Portal persona is not present in dashboard page");
			navigate(finalurl + "/#setup/dashboard/corporatemarketing");
			waitForElement("clicktocustomize_xpath");
			clickJS(prop.getProperty("clicktocustomize_xpath"));

			test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");

			waitForElementforreportfail("selectedbannertemplatetext_xpath");
			getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
			wait(1);
			test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
			getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
			waitForElementforreportfail("selectedbannertemplatetext_xpath");

			click("unselectbanner_xpath");
			test.log(LogStatus.INFO,
					"..Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselected it as it was already selected");
		}
		wait(1);

		JavascriptExecutor ab = (JavascriptExecutor) driver;
		ab.executeScript("arguments[0].click();", getElement("selectedbannertemplatetext_xpath"));

		test.log(LogStatus.INFO, "Predefined banner 'DashboardBanner(Do Not Delete)' is selected");
		click("savebuttononwebbannertemplatepage_xpath");
		test.log(LogStatus.INFO, "Clicked on 'Save' button");
		waitForElement("clicktocustomize_xpath");
		waitForElementforreportfail("waitforframe_xpath");
		navigate(finalurl + "/#corporatemarketing");
		test.log(LogStatus.INFO, "Landed to main Dashboard page to verify 'Is selected banner visible or not' ");

		loadForFirstFrame();
		if (verifyFrameText("Sample Dashboard Banner", 50, 0)) {
			test.log(LogStatus.PASS, "Dashboard banner is appearing.");
			test.log(LogStatus.INFO, "selected banner is visible on main Dashboard page ");
		} else {

			reportFail("Dashboard banner is not appearing.");
		}

		driver.switchTo().parentFrame();

		navigate(finalurl + "/#setup/dashboard/corporatemarketing");

		waitForElement("clicktocustomize_xpath");
		clickJS(prop.getProperty("clicktocustomize_xpath"));

		test.log(LogStatus.INFO, "Clicked on 'Clicked to customize' button");

		waitForElementforreportfail("selectedbannertemplatetext_xpath");

		getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
		wait(1);
		test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
		getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
		wait(1);
		waitForElementforreportfail("selectedbannertemplatetext_xpath");

		click("unselectbanner_xpath");
		test.log(LogStatus.INFO, "Clicked on selected dashboard banner to unselect it");
		click("savebuttononwebbannertemplatepage_xpath");
		test.log(LogStatus.INFO, "Clicked on 'Save' button");
		waitForElementforreportfail("personaname_xpath");
		navigate(finalurl + "/#corporatemarketing");

		test.log(LogStatus.INFO, "Landed to main Dashboard page to verify default banner is visible or not' ");

		waitForElementforreportfail("personaname_xpath");

		String corpratemarketingpersonaverification = getElement("personaname_xpath").getText();
		if (corpratemarketingpersonaverification.equals("Marketing Portal")) {
			reportPass("Default WebTemplate banner is visible ");
		} else {
			reportFail("Default WebTemplate banner is not visible ");
		}
		test.log(LogStatus.INFO, "TC4 is completed.");

	}

	private void mspdashboard(Hashtable<String, String> data) {
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		
		doLoginForAll();
		
		waitForElementforreportfail("dashboard_xpath");
		String urlname = driver.getCurrentUrl();

		String finalurl = urlname.split("/#")[0];

		navigate(finalurl + "/#corporatemarketing");
		waitForElement("dashboard_xpath");
		waitForElementforreportfail("dashboard_xpath");
		test.log(LogStatus.INFO, " Reached at Dashboard Page ");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (isElementPresent("mspimage_xpath")) {

			test.log(LogStatus.INFO, "Default Marketing Portal persona is present");

			navigate(finalurl + "/#setup/dashboard/corporatemarketing");

			waitForElementforreportfail("personaname_xpath");

			clickJS(prop.getProperty("clicktocustomize_xpath"));

			test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'to select WebBanner Template");

			waitForElementforreportfail("selectedbannertemplatetext_xpath");
			getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
			wait(1);
			test.log(LogStatus.INFO, "Predefined 'Dashboard Banner' is entered in search for record field ");
			getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);

			waitForElementforreportfail("selectedbannertemplatetext_xpath");

			test.log(LogStatus.INFO, "Waiting for banner to appear on screen ");

		} else {
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			test.log(LogStatus.INFO, "Default Marketing Portal persona is not present in dashboard page");
			navigate(finalurl + "/#setup/dashboard/corporatemarketing");
			wait(5);
			waitForElement("clicktocustomize_xpath");
			clickJS(prop.getProperty("clicktocustomize_xpath"));

			test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
			if (isElementPresent("mspclose_xpath")) {
				click("mspclose_xpath");
				wait(2);

				click("clicktocustomize_xpath");
			} else {

			}
			waitForElement("selectedbannertemplatetext_xpath");

			getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
			wait(1);
			test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
			getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
			waitForElementforreportfail("selectedbannertemplatetext_xpath");

			click("unselectbanner_xpath");
			test.log(LogStatus.INFO,
					"...Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselected it as it was already selected");

		}

		wait(1);
		JavascriptExecutor ab = (JavascriptExecutor) driver;
		ab.executeScript("arguments[0].click();", getElement("selectedbannertemplatetext_xpath"));

		test.log(LogStatus.INFO, "Predefined banner 'DashboardBanner(Do Not Delete)' is selected");
		click("savebuttononwebbannertemplatepage_xpath");
		test.log(LogStatus.INFO, "Clicked on 'Save' button");
		waitForElement("clicktocustomize_xpath");
		waitForElementforreportfail("waitforframe_xpath");
		navigate(finalurl + "/#corporatemarketing");
		test.log(LogStatus.INFO, "Landed to main Dashboard page to verify 'Is selected banner visible or not' ");

		loadForFirstFrame();
		if (verifyFrameText("Sample Dashboard Banner", 50, 0)) {
			test.log(LogStatus.PASS, "Dashboard banner is appearing.");
			test.log(LogStatus.INFO, "selected banner is visible on main Dashboard page ");
		} else {

			reportFail("Dashboard banner is not appearing.");
		}

		driver.switchTo().parentFrame();

		driver.switchTo().defaultContent();
		navigate(finalurl + "/#setup/dashboard/corporatemarketing");

		click("clicktocustomize_xpath");
		test.log(LogStatus.INFO, "Clicked on 'Clicked to customize' button");
		if (isElementPresent("mspclose_xpath")) {
			click("mspclose_xpath");
			wait(2);

			click("clicktocustomize_xpath");
		} else {

		}
		waitForElement("selectedbannertemplatetext_xpath");
		getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
		wait(1);
		test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
		getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
		waitForElementforreportfail("selectedbannertemplatetext_xpath");
		wait(1);
		click("unselectbanner_xpath");
		wait(1);
		test.log(LogStatus.INFO, "Clicked on selected dashboard banner to unselect it");
		ab.executeScript("arguments[0].click();", getElement("savebuttononwebbannertemplatepage_xpath"));

		test.log(LogStatus.INFO, "Clicked on 'Save' button");

		waitForElementforreportfail("personaname_xpath");
		navigate(finalurl + "/#corporatemarketing");

		test.log(LogStatus.INFO, "Landed to main Dashboard page to verify default banner is visible or not' ");
		waitForElementforreportfail("personaname_xpath");

		if (isElementPresent("mspimage_xpath")) {
			reportPass("Default WebTemplate banner is visible ");
		} else {
			reportFail("Default WebTemplate banner is not visible ");
		}

		test.log(LogStatus.INFO, "TC4 is completed.");

	}

	private void master(Hashtable<String, String> data) {

		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		
		
		doLoginForMaster();
		waitForElementforreportfail("dashboard_xpath");

		String urlname = driver.getCurrentUrl();

		String finalurl = urlname.split("/#")[0];

		navigate(finalurl + "/#corporatemarketing");
		waitForElement("dashboard_xpath");
		waitForElementforreportfail("dashboard_xpath");
		test.log(LogStatus.INFO, " Reached at Dashboard Page ");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (isElementPresent("personaname_xpath")) {

			if (isElementPresent("markitingportal_xpath")) {
				test.log(LogStatus.INFO, "Default Marketing Portal persona is present");

				navigate(finalurl + "/#setup/dashboard/corporatemarketing");

				waitForElementforreportfail("personaname_xpath");
				loadForFirstFrame();
				clickJS(prop.getProperty("clicktocustomize_xpath"));
				// click("clicktocustomize_xpath");

				test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'to select WebBanner Template");

				waitForElement("searchforrecord_xpath");
				waitForElementforreportfail("searchfields_xpath");
				getElement("searchfields_xpath").sendKeys("DashboardBanner");
				wait(2);
				test.log(LogStatus.INFO, "Predefined 'Dashboard Banner' is entered in search for record field ");
				getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);

				waitforChrisScript();

			} else {
				reportFail("Default persona does not matched ");
			}

		} else {
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			test.log(LogStatus.INFO, "Default Marketing Portal persona is not present in dashboard page");
			navigate(finalurl + "/#setup/dashboard/corporatemarketing");
			waitForElement("clicktocustomize_xpath");
			loadForFirstFrame();
			clickJS(prop.getProperty("clicktocustomize_xpath"));
			test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
			waitForElementforreportfail("bannertemplatearea_xpath");
			getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
			wait(2);
			test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
			getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);

			waitforChrisScript();
			waitForElementforreportfail("selectedbannertemplatetext_xpath");

			click("unselectbanner_xpath");
			test.log(LogStatus.INFO,
					".Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselected it as it was already selected");
		}
		wait(2);

		waitForElementforreportfail("selectedbannertemplatetext_xpath");
		JavascriptExecutor ab = (JavascriptExecutor) driver;
		ab.executeScript("arguments[0].click();", getElement("selectedbannertemplatetext_xpath"));
		test.log(LogStatus.INFO, "Predefined banner 'DashboardBanner(Do Not Delete)' is selected");
		clickJS(prop.getProperty("savebuttononwebbannertemplatepage_xpath"));
		test.log(LogStatus.INFO, "Clicked on 'Save' button");
		waitForElement("clicktocustomize_xpath");
		driver.navigate().refresh();

		navigate(finalurl + "/#corporatemarketing");
		waitforChrisScript();

		test.log(LogStatus.INFO, "Landed to main Dashboard page to verify 'Is selected banner visible or not' ");
waitforChrisScript();
		loadForFirstFrame();
		if (verifyFrameText("Sample Dashboard Banne", 60, 0)) {
			test.log(LogStatus.PASS, "Dashboard banner is appearing.");
			test.log(LogStatus.INFO, "selected banner is visible on main Dashboard page ");
		} else {

			reportFail("Dashboard banner is not appearing.");
		}

		driver.switchTo().parentFrame();

		navigate(finalurl + "/#setup/dashboard/corporatemarketing");
		waitForElement("clicktocustomize_xpath");

		loadForFirstFrame();
		clickJS(prop.getProperty("clicktocustomize_xpath"));

		test.log(LogStatus.INFO, "Clicked on 'Clicked to customize' button");

		waitForElementforreportfail("selectedbannertemplatetext_xpath");
		getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
		wait(2);
		test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
		getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);

		waitforChrisScript();
		waitForElementforreportfail("selectedbannertemplatetext_xpath");

		click("unselectbanner_xpath");
		test.log(LogStatus.INFO, "Clicked on selected dashboard banner to unselect it");
		clickJS(prop.getProperty("savebuttononwebbannertemplatepage_xpath"));
		test.log(LogStatus.INFO, "Clicked on 'Save' button");
		driver.navigate().refresh();
		loadForFirstFrame();

		navigate(finalurl + "/#corporatemarketing");
		waitforChrisScript();
		test.log(LogStatus.INFO, "Landed to main Dashboard page to verify default banner is visible or not' ");

		waitForElementforreportfail("personaname_xpath");

		if (isElementPresent("markitingportal_xpath")) {
			reportPass("Default WebTemplate banner is visible ");
		} else {
			reportFail("Default WebTemplate banner is not visible ");
		}
		test.log(LogStatus.INFO, "TC4 is completed.");
	}
}