package com.selenium.mindmatrix.project.util;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html


import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
    public static String  folderName;
	public static ExtentReports getInstance() {
		if (extent == null) {
		//	Date d=new Date();
			String fileName="Report.html";
			Date d = new Date();
    		folderName=d.toString().replace(":", "_");
			new File(System.getProperty("user.dir")+"\\Reports\\"+folderName).mkdirs();
			extent = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\"+folderName+"\\"+fileName, true, DisplayOrder.NEWEST_FIRST);

			
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo(
					"Environment", "QA");
		}
		return extent;
	}
}
