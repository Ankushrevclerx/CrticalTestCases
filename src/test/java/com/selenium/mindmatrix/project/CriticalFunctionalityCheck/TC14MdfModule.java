package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC14MdfModule extends BaseTest{
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
		

			switch (URL) {

			
			case "https://dvl-master.amp.vg":
				openBrowserandNavigate(data);
				doLoginForMaster();
				
				
				break;
				
			case "https://mm.amp.vg":
				openBrowserandNavigate(data);
				doLoginMMAmpAndPortal();
				
				break;
			default:
				System.out.println("");

				
			
		}
				
				
				
		
		
		waitForElementforreportfail("dashboard_xpath");
		String urlname = driver.getCurrentUrl();
		String[] modifiedURL = urlname.split("#");
		navigate(modifiedURL[0] + "#manage/mdf/fundplans");
		
		
		
		
		waitForElement("createbutton_xpath");
		click("createbutton_xpath");
		wait(2);
		click("opportunitynext_xpath");
		click("addactivity_xpath");
		type("mdfinputfield_xpath", "ManaliTrip");
		type("mdfamount_xpath", "100");
		click("mdfattachementlink_xpath");
		wait(15);
		click("mdfuploadbutton_xpath");
		click("mdfuploadingfromcomputer_xpath");
		wait(2);
		try {
			uploading("G:\\ram.txt");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait(3);
click("mdfuploadedfile_xpath");
wait(2);
click("mdfdocumentselection_xpath");
wait(2);
click("mdfadddocument_xpath");
click("orgsaveButton_xpath");
wait(2);
click("opportunitynext_xpath");
wait(9);
clickJS(prop.getProperty("mdfselectAll_xpath"));
waitForElement("mdfAdd_xpath");
click("mdfAdd_xpath");
click("opportunitynext_xpath");
waitForElement("allcheckbox_xpath");
clickJS(prop.getProperty("allcheckbox_xpath"));
click("contactcfdelete_xpath");
click("yesbutton_xpath");
wait(13);
	}
	
	
	public void uploading(String path) throws AWTException
	{
		setClipboardData(path);
		Robot robot = new Robot();
		
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	public void openBrowserandNavigate(Hashtable<String, String> data)
	{
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		
	}
}