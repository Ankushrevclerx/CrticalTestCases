package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;


import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;

public class TC2TestwithvaliduserIDandvalidpassword extends BaseTest {
	SoftAssert sassert; 
	String testCaseName;
	Xls_Reader xls;
	
	@DataProvider
	private Object[][] getDataTC2() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir")+prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls,"TC2");
}

    @Test(dataProvider="getDataTC2")
    private void TestTC2(Hashtable<String,String> data) throws InterruptedException {
	
	if(!DataUtil.isRunnable("TC2TestwithvaliduserIDandvalidpassword", xls)){
	
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	test = rep.startTest("TC2=> Test with valid userID and valid password.");
	
	openBrowser(prop.getProperty("browsername"));
	navigate(data.get("URL"));
	if(data.get("URL").equals("https://dvl-master.amp.vg"))
	{
		doLoginForMaster();
	}
	else if(data.get("URL").equals("https://mm.amp.vg")|| data.get("URL").equals("https://mm-portal.amp.vg"))
	{
		doLoginMMAmpAndPortal();
	}
	else{
		doLoginForAll();
	}
	
	test.log(LogStatus.INFO, " Reached at Dashboard Page ");


	if (isElementPresent("dashboard_xpath"))  {
		
		reportPass("User is Logged In into the AMP.");
		
	} else {
		reportFail("Dashboard text is not appearing.");
	}
	test.log(LogStatus.INFO,"TC2 is completed.");
}
	
}
	
	 
	
	
	
	

		

