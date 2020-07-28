package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class MDFRequestAndapproval extends BaseTest {
	SoftAssert sassert;
	String testCaseName;
	Xls_Reader xls;
	String Requestid;

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

		switch (URL) {

		case "https://dvl-master.amp.vg":
			openBrowserandNavigate(data);

			doLoginForMaster();

			break;

		case "https://mm.amp.vg":
			
			//driver.manage().addCookie(obj);
			openBrowserandNavigate(data);
			doLoginMMAmpAndPortal();

			break;
		default:
			System.out.println("");

		}
	
	waitForElementforreportfail("dashboard_xpath");
	System.out.println(driver.manage().getCookies());
//		String urlname = driver.getCurrentUrl();
//		String[] modifiedURL = urlname.split("#");
//		navigate(modifiedURL[0] + "#manage/mdf/funds");
//		waitForElement("createbutton_xpath");
//		click("createbutton_xpath");
//		wait(2);
//		// type("mdfrequestname_xpath", "ShimlaTrip");
//		type("mdfamount_xpath", "10");
//		click("orgsaveButton_xpath");
//		
//		waitForElement("firsRequest_xpath");
//		 Requestid=getElement("firsRequest_xpath").getText();
//		 //String parentWindow= driver.getWindowHandle();
		 String parentWindowHandle = driver.getWindowHandle();
		 
		 
		 
		 
		 
//			System.out.println("Parent window's handle -> " + parentWindowHandle);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		navigate(data.get("URL"));
		approval(data);
		//driver.switchTo().Window(driver.WindowHandles.Last());
		
			
		
			
		
		
		driver.get("https://www.toolsqa.com/selenium-webdriver/handling-multiple-browsers-in-selenium-webdriver/");
		wait(15);
		click("reburshmentrequest_xpath");
		waitForElement("createbutton_xpath");
		click("createbutton_xpath");
		wait(2);
		type("mdfamount_xpath", "10");
		click("orgsaveButton_xpath");
		
	}

	public void approval(Hashtable<String, String> data) {

		String URL = data.get("URL");

		switch (URL) {

		case "https://dvl-master.amp.vg":
			//openBrowserandNavigate(data);

			doLoginForMaster();

			break;

		case "https://mm.amp.vg":
			//openBrowserandNavigate(data);
			doLoginMMAmpAndPortal();

			break;
		default:
			System.out.println("");

		}
//		waitForElementforreportfail("dashboard_xpath");
//		String urlname = driver.getCurrentUrl();
//		String finalurl = urlname.split("/#")[0];
//
//		navigate(finalurl + "/#manage/mdf/viewrequests");
//		waitForElement("requestid_xpath");
//		driver.findElement(By.xpath((prop.getProperty("requestid_xpath")+"/span[text()="+Requestid+"]"))).click();
//		waitForElement("dropdownlistclick_xpath");
//		click("dropdownlistclick_xpath");
//		waitForElement("dropdownlist_xpath");
//		click("selectapprove_xpath");
//		type("descriptioncomment_xpath", "Approved request");
//		type("secondamount_xpath", "10");
//		click("save_xpath");

	}

	public void openBrowserandNavigate(Hashtable<String, String> data) {
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));

	}

	public void openbrowserr() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications--");
		options.addArguments("--incognito");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + prop.getProperty("chrome_driver"));

		driver = new ChromeDriver(options);
	}

}
