package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.base.Constants;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;



public class TC3Testfordefaultdashboardbanner extends BaseTest{

private	Xls_Reader xls;
private	String urlname;
private	String newurl;
private	String finalURL;
private	String[] modifiedURL;
	
	@DataProvider
	private Object[][] getDataTC3() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir")+prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls,"TC3");
}



    @Test(dataProvider="getDataTC3")

    private void TestTC3(Hashtable<String,String> data) throws InterruptedException {
	
	if(!DataUtil.isRunnable("TC3Testfordefaultdashboardbanner", xls)){
	
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	test = rep.startTest("TC3=> Testfordefaultdashboardbanner.");
try {
	String URL = data.get("URL");
	switch (URL){
	
	case Constants.url1:
        master(data);	 
	
	break;
	
	case "https://datto.amp.vg/user-login":
         datto(data);	 
	
	break;
	
	case "https://msp.amp.vg":		
		msp(data);
		
	break;
	
	
	case "https://borderstates.amp.vg/user-login":		
		userlogin(data);
		
	break;
	
	case "https://rajantportal.amp.vg/user-login":		
		userlogin(data);
		
	break;
	
	case "https://partnerhub.intersystems.com/user-login":		
		userlogin(data);
		
	break;
	
	case "https://axcient.amp.vg/user-login":		
		userlogin(data);
		
	break;
	
	case "https://levitonpartnerconnection.amp.vg/user-login":		
		userlogin(data);
		
	break;
	
	case "https://partner-marketing.bitdefender.com/user-login":		
		userlogin(data);
		
	break;
		
	default :
		
	openBrowser(prop.getProperty("browsername"));
	navigate(data.get("URL"));
	if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
		doLoginMMAmpAndPortal();	
	} else {
		doLoginForAll();
	}
	waitForElementforreportfail("dashboard_xpath");
	
	test.log(LogStatus.INFO, " Navigated to Dashboard Page ");
	
     /*****************Corporate Marketing*******************/
	
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 test.log(LogStatus.INFO, " Verification of persona name =>Corporate Marketing");
		
		if(isElementPresent("personaname_xpath")) {
		String corpratemarketingpersona=getElement("personaname_xpath").getText();
		 if(corpratemarketingpersona.equals("Marketing Portal"))
		 {
		 	reportPass("Corporate Marketing persona matched");
		 }
		 else {
			 reportFail("Corporate Marketing persona does not matched ");
		 }
		} else {
		    
			newurl=(data.get("URL"))+"/#setup/dashboard/corporatemarketing";
			driver.get(newurl);
			click("clicktocustomize_xpath");
			test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
	        waitForElementforreportfail("selectedbannertemplatetext_xpath");
			getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
			test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
			getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
		    wait(2);
			click("selectedbannertemplatetext_xpath");
			wait(2);
			
			test.log(LogStatus.INFO,
					"Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselect it as it was already selected");
			
			

			clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");
			wait(10);
			test.log(LogStatus.INFO, "Clicked on 'Save' button");
			   waitForLoad(driver);
		        driver.navigate().refresh();
		        waitForLoad(driver);
			newurl=(data.get("URL"))+"/#corporatemarketing";
			navigate(newurl);
			test.log(LogStatus.INFO, "Landed to main Dashboard page to verify 'Is selected banner visible or not' ");
			String corpratemarketingpersona=getElement("personaname_xpath").getText();
			 if(corpratemarketingpersona.equals("Marketing Portal"))
			 {
			 	reportPass("Corporate Marketing persona matched");
			 }
			 else {
				 reportFail("Corporate Marketing persona does not matched ");
			 }
		}
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
/******************sales*****************/
		
	newurl=(data.get("URL"))+"/#sales";
	driver.get(newurl);
    waitForElement("personaname_xpath");
    test.log(LogStatus.INFO, " Verification of persona name =>Sales");

    if (isElementPresent("personaname_xpath")) {
	 String salepersona=getElement("personaname_xpath").getText();
	 if(salepersona.equals("Sales Portal"))
	 {
	 	reportPass("Sales persona matched.");
	 } else  {
			
			reportFail("Sales persona does not matched.");
		}
	
} else  {
	
	reportFail("Sales persona does not matched.");
}

    /*****************Channel Partner Option_a**********************/
     newurl = data.get("URL")+"/#channelpartner/option_a";
     driver.get(newurl);
			
		 waitForElementforreportfail("channelpartneroptionaandbwait_xpath");
		 
		  
		  test.log(LogStatus.INFO, " Landed to Channel Partner Option A Page ");

		  /*****************Channel Partner Option_b**********************/		  
		  newurl = data.get("URL")+"/#channelpartner/option_b";
		  driver.get(newurl); 
		  waitForElementforreportfail("channelpartneroptionaandbwait_xpath");
	
		  
		  test.log(LogStatus.INFO, " Landed to Channel Partner Option B Page ");
	
		  /*****************Channel Partner Option_c**********************/	 
		  newurl = data.get("URL")+"/#channelpartner/option_c";
		  driver.get(newurl);
			
		  waitForElementforreportfail("channelpartneroptioncwait_xpath");
		
		 
		  test.log(LogStatus.INFO, " Landed to Channel Partner Option C Page ");

		  
		  /*****************Sales Manager**********************/		
		  
		  newurl = data.get("URL")+"/#salesmanager";
		  driver.get(newurl);
		
		waitForElementforreportfail("personaname_xpath"); 
		test.log(LogStatus.INFO, " Verification of persona name =>Sales Manager");
		if (isElementPresent("personaname_xpath")) {
			 String salesmanagerportalpersona=getElement("personaname_xpath").getText();
			 if(salesmanagerportalpersona.equals("Sales Manager Portal"))
			 {
			 	reportPass("Sales Manager persona matched");
			 } else  {
					
					reportFail("Sales Manager persona does not matched.");
				}
			
		} else  {
			
			reportFail("Sales Manager persona does not matched.");
		}
		  /*****************Marketing Manager**********************/	
		
		  newurl = data.get("URL")+"/#marketingmanager";
		  driver.get(newurl);
		
		  waitForElementforreportfail("personaname_xpath"); 
		 
		 test.log(LogStatus.INFO, " Verification of persona name =>Marketing Manager ");
		 
			if (isElementPresent("personaname_xpath")) {
				 String MarketingManagerpersona=getElement("personaname_xpath").getText();
				 if(MarketingManagerpersona.equals("Marketing Manager Portal"))
				 {
				 	reportPass("Marketing Manager persona matched");
				 } else  {
						
						reportFail("Marketing Manager persona does not matched");
					}
				
			} else  {
				
				reportFail("Marketing Manager persona does not matched");
			}
			  /*****************Channel Manager**********************/
			
			  newurl = data.get("URL")+"/#channelmanager";
			  driver.get(newurl);
		
			  waitForElementforreportfail("personaname_xpath"); 
			 
			 test.log(LogStatus.INFO, " Verification of persona name =>Channel Manager ");
			 
				if (isElementPresent("personaname_xpath")) {
					 String ChannelManagerpersona=getElement("personaname_xpath").getText();
					 if(ChannelManagerpersona.equals("Channel Manager Portal"))
					 {
					 	reportPass("Channel Manager persona matched");
					 } else  {
							
							reportFail("Channel Manager persona does not matched");
						}
					
				} else  {
					
					reportFail("Channel Manager persona does not matched");
				}
			test.log(LogStatus.INFO,"TC3 is completed.");	
	}	
	
} catch (Exception e) {
	
	test.log(LogStatus.INFO,"script stopped because of "+ e.getMessage());
}
    }	
    
    private void msp(Hashtable<String, String> data) {
    	
    	openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		doLoginForAll();
		waitForLoad(driver);
		urlname = driver.getCurrentUrl();
				
		test.log(LogStatus.INFO, " Navigated to Dashboard Page ");
		
	/*****************Corporate Marketing*************************/
		
		
		test.log(LogStatus.INFO, " Verification of persona name =>Corporate Marketing");

			if (isElementPresent("mspimage_xpath")) {
				
				test.log(LogStatus.INFO,"Please verify the Corporate Marketing banner manually.");
				
			} 
		
			else {
								  
				newurl=(data.get("URL"))+"/#setup/dashboard/corporatemarketing";
				driver.get(newurl);
				click("mspclicktocustomize_xpath");
				test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
				waitForElementforreportfail("selectedbannertemplatetext_xpath");
				getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
				test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
				getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
				waitForElementforreportfail("selectedbannertemplatetext_xpath");

				click("unselectbanner_xpath");
				test.log(LogStatus.INFO,
						"Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselected it as it was already selected");
				wait(2);
				
				clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");
				test.log(LogStatus.INFO, "Clicked on 'Save' button");
				waitForElementforreportfail("waitforframe_xpath");
				newurl=(data.get("URL"))+"/#corporatemarketing";
				navigate(newurl);
				test.log(LogStatus.INFO, "Landed to main Dashboard page  ");
			
					
					test.log(LogStatus.INFO,"Please verify the Corporate Marketing banner manually.");
			
					
			
			}
	
			/*****************Sales*************************/
			
		test.log(LogStatus.INFO, " Verification of persona name =>Sales");
		
		newurl=(data.get("URL"))+"/#sales";
		navigate(newurl);
	
		
		if (isElementPresent("mspimage_xpath")) {
			
			test.log(LogStatus.INFO,"Please verify the Sales banner manually.");

			
		} else  {
			
			reportFail("Sales banner is not present.");
		}     
		/*****************Channel Partner option_a*************************/
		
        test.log(LogStatus.INFO, " Verification of Channel Partner option_a");
		
		newurl=(data.get("URL"))+"/#channelpartner/option_a";
		navigate(newurl);
		waitForElementforreportfail("channelpartneroptionaandbwait_xpath");		  
		test.log(LogStatus.INFO, " Landed to Channel Partner Option A Page ");
	
		
		/*****************Channel Partner option_b*************************/
		test.log(LogStatus.INFO, " Verification of Channel Partner option-b");
			
    	newurl=(data.get("URL"))+"/#channelpartner/option_b";
		navigate(newurl);
		waitForElementforreportfail("channelpartneroptionaandbwait_xpath");		  
		test.log(LogStatus.INFO, " Landed to Channel Partner Option b Page ");

		
		/*****************Channel Partner option_c*************************/
		test.log(LogStatus.INFO, " Verification of Channel Partner option-c");
		
    	newurl=(data.get("URL"))+"/#channelpartner/option_c";
		navigate(newurl);
		waitForElementforreportfail("channelpartneroptioncwait_xpath");		  
		test.log(LogStatus.INFO, " Landed to Channel Partner Option c Page ");

		
		test.log(LogStatus.INFO, " Verification of persona name =>Sales Manager");
	
		/*****************Sales Manager*************************/
		
		newurl=(data.get("URL"))+"/#salesmanager";
		navigate(newurl);
		waitForElementforreportfail("personaname_xpath"); 
		
		if (isElementPresent("personaname_xpath")) {
			 String salesmanagerportalpersona=getElement("personaname_xpath").getText();
			 if(salesmanagerportalpersona.equals("Sales Manager Portal"))
			 {
			 	reportPass("Sales Manager persona matched");
			 } else  {
					
					reportFail("Sales Manager persona does not matched.");
				}
			
		} else  {
			
			reportFail("Sales Manager persona does not matched.");
		}
		/*****************Marketing Manager*************************/
		
        test.log(LogStatus.INFO, " Verification of persona name =>Marketing Manager");
		
		newurl=(data.get("URL"))+"/#marketingmanager";
		navigate(newurl);
		waitForElementforreportfail("personaname_xpath"); 
		
		if (isElementPresent("personaname_xpath")) {
			 String salesmanagerportalpersona=getElement("personaname_xpath").getText();
			 if(salesmanagerportalpersona.equals("Marketing Manager Portal"))
			 {
			 	reportPass("Marketing Manager persona matched");
			 } else  {
					
					reportFail("Marketing Manager persona does not matched.");
				}
			
		} else  {
			
			reportFail("Marketing Manager persona does not matched.");
		}
	
		/*****************Channel Manager*************************/
		
        test.log(LogStatus.INFO, " Verification of persona name =>Channel manager");
		
		newurl=(data.get("URL"))+"/#channelmanager";
		navigate(newurl);
		waitForElementforreportfail("personaname_xpath"); 
		
		if (isElementPresent("personaname_xpath")) {
			 String salesmanagerportalpersona=getElement("personaname_xpath").getText();
			 if(salesmanagerportalpersona.equals("Channel Manager Portal"))
			 {
			 	reportPass("Channel Manager persona matched");
			 } else  {
					
					reportFail("Channel Manager persona does not matched.");
				}
			
		} else  {
			
			reportFail("Channel Manager persona does not matched.");
		}
		
		test.log(LogStatus.INFO,"TC3 is completed.");
    }
  
    
    
    
    
    
    private void datto(Hashtable<String,String> data) {
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		doLoginForAll();
        waitForElementforreportfail("dashboard_xpath");
				
		test.log(LogStatus.INFO, " Navigated to Dashboard Page ");
		urlname=	driver.getCurrentUrl();
		modifiedURL = urlname.split("#");
  /*****************Corporate Marketing***********************/
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, " Verification of persona name =>Corporate Marketing");

		
		if(isElementPresent("personaname_xpath")) {
		String corpratemarketingpersona=getElement("personaname_xpath").getText();
		 if(corpratemarketingpersona.equals("Marketing Portal"))
		 {
		 	reportPass("Corporate Marketing persona matched");
		 }
		 else {
			 reportFail("Corporate Marketing persona does not matched ");
		 }
		} else {
			

			  
			newurl=(data.get("URL"))+"/#setup/dashboard/corporatemarketing";
			driver.get(newurl);
			click("clicktocustomize_xpath");
			test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
			waitForElementforreportfail("selectedbannertemplatetext_xpath");
			
			getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
			test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
			getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
			waitForElementforreportfail("selectedbannertemplatetext_xpath");

			click("unselectbanner_xpath");
			test.log(LogStatus.INFO,
					"Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselect it as it was already selected");
			wait(2);
			
		
			clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");
			test.log(LogStatus.INFO, "Clicked on 'Save' button");
			waitForElementforreportfail("waitforframe_xpath");
			newurl=(data.get("URL"))+"/#corporatemarketing";
			navigate(newurl);
			test.log(LogStatus.INFO, "Landed to main Dashboard page to verify 'Is selected banner visible or not' ");
			String corpratemarketingpersona=getElement("personaname_xpath").getText();
			 if(corpratemarketingpersona.equals("Marketing Portal"))
			 {
			 	reportPass("Corporate Marketing persona matched");
			 }
			 else {
				 reportFail("Corporate Marketing persona does not matched ");
			 }
		}
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		  /*****************Sales***********************/
		
		finalURL = modifiedURL[0]+"#sales";
	    System.out.println(finalURL);
		driver.get(finalURL);

	    waitForElement("personaname_xpath");
	    test.log(LogStatus.INFO, " Verification of persona name =>Sales");

	    if (isElementPresent("personaname_xpath")) {
		 String salepersona=getElement("personaname_xpath").getText();
		 if(salepersona.equals("Sales Portal"))
		 {
		 	reportPass("Sales persona matched.");
		 } else  {
				
				reportFail("Sales persona does not matched.");
			}
		
	} else  {
		
		reportFail("Sales persona does not matched.");
	}

	    /*****************Channel Partner option_a***********************/
	    
	finalURL = modifiedURL[0]+"#channelpartner/option_a";
	driver.get(finalURL);
					
			 waitForElementforreportfail("dattooptionaandb_xpath");
			
			  
			  test.log(LogStatus.INFO, " Landed to Channel Partner Option A Page ");
		
			  
			    /*****************Channel Partner option_b***********************/	
			  
			  finalURL = modifiedURL[0]+"#channelpartner/option_b";
			  driver.get(finalURL);
		
			  waitForElementforreportfail("dattooptionaandb_xpath");
		
			  
			  test.log(LogStatus.INFO, " Landed to Channel Partner Option B Page ");
	
			  
			    /*****************Channel Partner option_c***********************/	
			  
			  finalURL = modifiedURL[0]+"#channelpartner/option_c";
			  driver.get(finalURL);
		
			  waitForElementforreportfail("channelpartneroptioncwait_xpath");
			 
			 
			  test.log(LogStatus.INFO, " Landed to Channel Partner Option C Page ");
	
			  
			    /*****************Sales Manager***********************/	
			  
			  finalURL = modifiedURL[0]+"#salesmanager";
			  driver.get(finalURL);
		
			waitForElementforreportfail("personaname_xpath"); 
			test.log(LogStatus.INFO, " Verification of persona name =>Sales Manager");
			if (isElementPresent("personaname_xpath")) {
				 String salesmanagerportalpersona=getElement("personaname_xpath").getText();
				 if(salesmanagerportalpersona.equals("Sales Manager Portal"))
				 {
				 	reportPass("Sales Manager persona matched");
				 } else  {
						
						reportFail("Sales Manager persona does not matched.");
					}
				
			} else  {
				
				reportFail("Sales Manager persona does not matched.");
			}
	
		    /*****************Marketing Manager***********************/
			
			finalURL = modifiedURL[0]+"#marketingmanager";
			  driver.get(finalURL);
			
			  waitForElementforreportfail("personaname_xpath"); 
			 
			 test.log(LogStatus.INFO, " Verification of persona name =>Marketing Manager ");
			 
				if (isElementPresent("personaname_xpath")) {
					 String MarketingManagerpersona=getElement("personaname_xpath").getText();
					 if(MarketingManagerpersona.equals("Marketing Manager Portal"))
					 {
					 	reportPass("Marketing Manager persona matched");
					 } else  {
							
							reportFail("Marketing Manager persona does not matched");
						}
					
				} else  {
					
					reportFail("Marketing Manager persona does not matched");
				}
	
			    /*****************Channel Manager***********************/
				
				finalURL = modifiedURL[0]+"#channelmanager";
				  driver.get(finalURL);
			
				  waitForElementforreportfail("personaname_xpath"); 
				 
				 test.log(LogStatus.INFO, " Verification of persona name =>Channel Manager ");
				 
					if (isElementPresent("personaname_xpath")) {
						 String ChannelManagerpersona=getElement("personaname_xpath").getText();
						 if(ChannelManagerpersona.equals("Channel Manager Portal"))
						 {
						 	reportPass("Channel Manager persona matched");
						 } else  {
								
								reportFail("Channel Manager persona does not matched");
							}
						
					} else  {
						
						reportFail("Channel Manager persona does not matched");
					}
				test.log(LogStatus.INFO,"TC3 is completed.");
    }
   
    
    
    
    
    
    
    
    
    
    
    
    private void userlogin(Hashtable<String,String> data) {
		openBrowser(prop.getProperty("browsername"));
		navigate(data.get("URL"));
		doLoginForAll();
		waitForElementforreportfail("dashboard_xpath");
    urlname=	driver.getCurrentUrl();
	
	modifiedURL = urlname.split("#");
				
		test.log(LogStatus.INFO, " Navigated to Dashboard Page ");
	
		/*************************Corporate Marketing**********************/
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		test.log(LogStatus.INFO, " Verification of persona name =>Corporate Marketing");
			
			if(isElementPresent("personaname_xpath")) {
			String corpratemarketingpersona=getElement("personaname_xpath").getText();
			 if(corpratemarketingpersona.equals("Marketing Portal"))
			 {
			 	reportPass("Corporate Marketing persona matched");
			 }
			 else {
				 reportFail("Corporate Marketing persona does not matched ");
			 }
			} else {
				

				  
				newurl=(modifiedURL[0]+"#setup/dashboard/corporatemarketing");
	
				driver.get(newurl);
				click("clicktocustomize_xpath");
				test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
				waitForElementforreportfail("selectedbannertemplatetext_xpath");
				getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
				test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
				getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
				waitForElementforreportfail("selectedbannertemplatetext_xpath");

				click("unselectbanner_xpath");
				test.log(LogStatus.INFO,
						"Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselect it as it was already selected");
				wait(2);
				
				clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");
				test.log(LogStatus.INFO, "Clicked on 'Save' button");
				waitForElementforreportfail("waitforframe_xpath");
				newurl=(data.get("URL"))+"/#corporatemarketing";
				navigate(newurl);
				test.log(LogStatus.INFO, "Landed to main Dashboard page to verify 'Is selected banner visible or not' ");
				String corpratemarketingpersona=getElement("personaname_xpath").getText();
				 if(corpratemarketingpersona.equals("Marketing Portal"))
				 {
				 	reportPass("Corporate Marketing persona matched");
				 }
				 else {
					 reportFail("Corporate Marketing persona does not matched ");
				 }
			}
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);	
			/*************************Sales**********************/

		finalURL = modifiedURL[0]+"#sales";
	
		driver.get(finalURL);

	    waitForElement("personaname_xpath");
	    test.log(LogStatus.INFO, " Verification of persona name =>Sales");

	    if (isElementPresent("personaname_xpath")) {
		 String salepersona=getElement("personaname_xpath").getText();
		 if(salepersona.equals("Sales Portal"))
		 {
		 	reportPass("Sales persona matched.");
		 } else  {
				
				reportFail("Sales persona does not matched.");
			}
		
	} else  {
		
		reportFail("Sales persona does not matched.");
	}

		/*************************Channel Partner Option_a**********************/
	    
	    
	finalURL = modifiedURL[0]+"#channelpartner/option_a";
	driver.get(finalURL);
					
			 waitForElementforreportfail("channelpartneroptionaandbwait_xpath");
			
			  
			  test.log(LogStatus.INFO, " Landed to Channel Partner Option A Page ");
	
	
			  
				/*************************Channel Partner Option_b**********************/
			  
			  finalURL = modifiedURL[0]+"#channelpartner/option_b";
			  driver.get(finalURL);
		
			  waitForElementforreportfail("channelpartneroptionaandbwait_xpath");
		
			  
			  test.log(LogStatus.INFO, " Landed to Channel Partner Option B Page ");
	
				/*************************Channel Partner Option_c**********************/
			  
			  finalURL = modifiedURL[0]+"#channelpartner/option_c";
			  driver.get(finalURL);
		
			  waitForElementforreportfail("channelpartneroptioncwait_xpath");
			 
			 
			  test.log(LogStatus.INFO, " Landed to Channel Partner Option C Page ");
	
	
				/*************************Sales Manager**********************/
			  
			  finalURL = modifiedURL[0]+"#salesmanager";
			  driver.get(finalURL);
		
			waitForElementforreportfail("personaname_xpath"); 
			test.log(LogStatus.INFO, " Verification of persona name =>Sales Manager");
			if (isElementPresent("personaname_xpath")) {
				 String salesmanagerportalpersona=getElement("personaname_xpath").getText();
				 if(salesmanagerportalpersona.equals("Sales Manager Portal"))
				 {
				 	reportPass("Sales Manager persona matched");
				 } else  {
						
						reportFail("Sales Manager persona does not matched.");
					}
				
			} else  {
				
				reportFail("Sales Manager persona does not matched.");
			}
	
			/*************************Marketing Manager**********************/
			
			finalURL = modifiedURL[0]+"#marketingmanager";
			  driver.get(finalURL);
			
			  waitForElementforreportfail("personaname_xpath"); 
			 
			 test.log(LogStatus.INFO, " Verification of persona name =>Marketing Manager ");
			 
				if (isElementPresent("personaname_xpath")) {
					 String MarketingManagerpersona=getElement("personaname_xpath").getText();
					 if(MarketingManagerpersona.equals("Marketing Manager Portal"))
					 {
					 	reportPass("Marketing Manager persona matched");
					 } else  {
							
							reportFail("Marketing Manager persona does not matched");
						}
					
				} else  {
					
					reportFail("Marketing Manager persona does not matched");
				}
	
		/*************************Channel Manager**********************/
				
				finalURL = modifiedURL[0]+"#channelmanager";
				  driver.get(finalURL);
			
				  waitForElementforreportfail("personaname_xpath"); 
				 
				 test.log(LogStatus.INFO, " Verification of persona name =>Channel Manager ");
				 
					if (isElementPresent("personaname_xpath")) {
						 String ChannelManagerpersona=getElement("personaname_xpath").getText();
						 if(ChannelManagerpersona.equals("Channel Manager Portal"))
						 {
						 	reportPass("Channel Manager persona matched");
						 } else  {
								
								reportFail("Channel Manager persona does not matched");
							}
						
					} else  {
						
						reportFail("Channel Manager persona does not matched");
					}
				test.log(LogStatus.INFO,"TC3 is completed.");
    }
    
    private void master(Hashtable<String,String> data) {
    	openBrowser(prop.getProperty("browsername"));
    	navigate(data.get("URL"));
    	doLoginForMaster();
    	waitForElementforreportfail("dashboard_xpath");
    	
    	test.log(LogStatus.INFO, " Navigated to Dashboard Page ");
    	
    /*****************Corporate Marketing*******************/
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	 test.log(LogStatus.INFO, " Verification of persona name =>Corporate Marketing");
    		
    		if(isElementPresent("personaname_xpath")) {
    		String corpratemarketingpersona=getElement("personaname_xpath").getText();
    		 if(corpratemarketingpersona.equals("Marketing Portal"))
    		 {
    		 	reportPass("Corporate Marketing persona matched");
    		 }
    		 else {
    			 reportFail("Corporate Marketing persona does not matched ");
    		 }
    		} else {
    			  
    			newurl=(data.get("URL"))+"/#setup/dashboard/corporatemarketing";
    			driver.get(newurl);
    			click("clicktocustomize_xpath");
    			test.log(LogStatus.INFO, "Clicked on 'Clicked to customize'");
    			waitForElementforreportfail("selectedbannertemplatetext_xpath");
    			getElement("searchforrecord_xpath").sendKeys("DashboardBanner");
    			test.log(LogStatus.INFO, "'Dashboard Banner' is entered in search for record field ");
    			getElement("searchforrecord_xpath").sendKeys(Keys.ENTER);
    			waitforChrisScript();
    		    wait(2);

    			click("selectedbannertemplatetext_xpath");
    			wait(2);
    			test.log(LogStatus.INFO,
    					"Clicked on Predefined banner'DashboardBanner(Do Not Delete)' to unselect it as it was already selected");
    			
    			clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");
    			wait(10);
    			test.log(LogStatus.INFO, "Clicked on 'Save' button");
    			waitForLoad(driver);
    		        driver.navigate().refresh();
    		        waitForLoad(driver);
    			newurl=(data.get("URL"))+"/#corporatemarketing";
    			navigate(newurl);
    			test.log(LogStatus.INFO, "Landed to main Dashboard page to verify 'Is selected banner visible or not' ");
    			String corpratemarketingpersona=getElement("personaname_xpath").getText();
    			 if(corpratemarketingpersona.equals("Marketing Portal"))
    			 {
    			 	reportPass("Corporate Marketing persona matched");
    			 }
    			 else {
    				 reportFail("Corporate Marketing persona does not matched ");
    			 }
    		}
    		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
    /******************sales*****************/
    		
    	newurl=(data.get("URL"))+"/#sales";
    	driver.get(newurl);
        waitForElement("personaname_xpath");
        test.log(LogStatus.INFO, " Verification of persona name =>Sales");

        if (isElementPresent("personaname_xpath")) {
    	 String salepersona=getElement("personaname_xpath").getText();
    	 if(salepersona.equals("Sales Portal"))
    	 {
    	 	reportPass("Sales persona matched.");
    	 } else  {
    			
    			reportFail("Sales persona does not matched.");
    		}
    	
    } else  {
    	
    	reportFail("Sales persona does not matched.");
    }

        /*****************Channel Partner Option_a**********************/
         newurl = data.get("URL")+"/#channelpartner/option_a";
         driver.get(newurl);
    			
    		 waitForElementforreportfail("channelpartneroptionaandbwait_xpath");
    		 
    		  
    		  test.log(LogStatus.INFO, " Landed to Channel Partner Option A Page ");
    
    		  /*****************Channel Partner Option_b**********************/		  
    		  newurl = data.get("URL")+"/#channelpartner/option_b";
    		  driver.get(newurl); 
    		  waitForElementforreportfail("channelpartneroptionaandbwait_xpath");
    	
    		  
    		  test.log(LogStatus.INFO, " Landed to Channel Partner Option B Page ");
    	
    		  /*****************Channel Partner Option_c**********************/	 
    		  newurl = data.get("URL")+"/#channelpartner/option_c";
    		  driver.get(newurl);
    			
    		  waitForElementforreportfail("channelpartneroptioncwait_xpath");
    		
    		 
    		  test.log(LogStatus.INFO, " Landed to Channel Partner Option C Page ");
 
    		  
    		  /*****************Sales Manager**********************/		
    		  
    		  newurl = data.get("URL")+"/#salesmanager";
    		  driver.get(newurl);
    		
    		waitForElementforreportfail("personaname_xpath"); 
    		test.log(LogStatus.INFO, " Verification of persona name =>Sales Manager");
    		if (isElementPresent("personaname_xpath")) {
    			 String salesmanagerportalpersona=getElement("personaname_xpath").getText();
    			 if(salesmanagerportalpersona.equals("Sales Manager Portal"))
    			 {
    			 	reportPass("Sales Manager persona matched");
    			 } else  {
    					
    					reportFail("Sales Manager persona does not matched.");
    				}
    			
    		} else  {
    			
    			reportFail("Sales Manager persona does not matched.");
    		}
    		  /*****************Marketing Manager**********************/	
    		
    		  newurl = data.get("URL")+"/#marketingmanager";
    		  driver.get(newurl);
    		
    		  waitForElementforreportfail("personaname_xpath"); 
    		 
    		 test.log(LogStatus.INFO, " Verification of persona name =>Marketing Manager ");
    		 
    			if (isElementPresent("personaname_xpath")) {
    				 String MarketingManagerpersona=getElement("personaname_xpath").getText();
    				 if(MarketingManagerpersona.equals("Marketing Manager Portal"))
    				 {
    				 	reportPass("Marketing Manager persona matched");
    				 } else  {
    						
    						reportFail("Marketing Manager persona does not matched");
    					}
    				
    			} else  {
    				
    				reportFail("Marketing Manager persona does not matched");
    			}
    			  /*****************Channel Manager**********************/
    			
    			  newurl = data.get("URL")+"/#channelmanager";
    			  driver.get(newurl);
    		
    			  waitForElementforreportfail("personaname_xpath"); 
    			 
    			 test.log(LogStatus.INFO, " Verification of persona name =>Channel Manager ");
    			 
    				if (isElementPresent("personaname_xpath")) {
    					 String ChannelManagerpersona=getElement("personaname_xpath").getText();
    					 if(ChannelManagerpersona.equals("Channel Manager Portal"))
    					 {
    					 	reportPass("Channel Manager persona matched");
    					 } else  {
    							
    							reportFail("Channel Manager persona does not matched");
    						}
    					
    				} else  {
    					
    					reportFail("Channel Manager persona does not matched");
    				}
    			test.log(LogStatus.INFO,"TC3 is completed.");	
    }
    
    }