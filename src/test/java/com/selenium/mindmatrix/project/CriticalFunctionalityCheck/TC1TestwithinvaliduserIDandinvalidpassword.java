package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.util.Hashtable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;

import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC1TestwithinvaliduserIDandinvalidpassword extends BaseTest {
	SoftAssert sassert;
	String testCaseName;
	Xls_Reader xls;

	@DataProvider
	private Object[][] getDataTC1() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir") + prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls, "TC1");
	}

	@Test(dataProvider = "getDataTC1")
	private void TestTC1(Hashtable<String, String> data) throws InterruptedException {

		
		test = rep.startTest("TC1=> Test with invalid userID and invalid password.");

		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		type("username_xpath", "wronguser");
		type("password_xpath",  "123munish");
		wait(2);
		clickJS("//*[@id='btnLogin']");

		test.log(LogStatus.INFO, "Clicked on 'LogIn' button ");

		wait(5);

		if (isElementPresent("username_xpath")) {

			reportPass("User is not  Logged In into t"
					+ "he AMP.");
		} else {
			reportFail("Dashboard text is appearing.");
		}

		test.log(LogStatus.INFO, "TC1 is completed.");

	}

}
