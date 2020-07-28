package com.selenium.mindmatrix.project.CriticalFunctionalityCheck;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.base.BaseTest;
import com.selenium.mindmatrix.project.util.DataUtil;
import com.selenium.mindmatrix.project.util.Xls_Reader;


public class TC5Testfordefaultaswellastemplateatwelcomepage extends BaseTest {

private	Xls_Reader xls;
private	String[] modifiedURL;
private	String urlname;
	
	@DataProvider
	private Object[][] getDataTC5() {
		super.init();
		xls = new Xls_Reader(System.getProperty("user.dir")+prop.getProperty("xlspath"));
		return DataUtil.getTestData(xls,"TC5");
}


    @Test(dataProvider="getDataTC5")

    private void TestTC5(Hashtable<String,String> data) throws InterruptedException {
	
	if(!DataUtil.isRunnable("TC5Testfordefaultaswellastemplateatwelcomepage", xls)){
	
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	test = rep.startTest("TC5=> Test for default as well as template at welcomepage.");
	try {
	String URL = data.get("URL");
	
	switch (URL) {
	
     case "https://dvl-master.amp.vg":
		
		master(data);
		
		break;
	
	case "https://refer.8x8.com":
		
		function8X8(data);
		
		break;
		
    case "https://partnerhub.intersystems.com/user-login":
		
		userloginFunction(data);
		
		break;
		
    case "https://borderstates.amp.vg/user-login":
		
		userloginFunction(data);
		
		break;
		
   case "https://axcient.amp.vg/user-login":
	
	userloginFunction(data);
	
	break;
	
   case "https://levitonpartnerconnection.amp.vg/user-login":
	
	userloginFunction(data);
	
	break;
	
   case "https://partner-marketing.bitdefender.com/user-login":
	
	userloginFunction(data);
	
	break;
	
   case "https://rajantportal.amp.vg/user-login":
		
	userloginFunction(data);
	
	break;
	
   case "https://datto.amp.vg/user-login":
	
	userloginFunction(data);
	
	break;
		
	default :
		
		defaultFunction(data);

    
    }
	
	
	}
	
catch (Exception e) {
		
		test.log(LogStatus.INFO,"script stopped because of "+ e.getMessage());
	}}
    private void defaultFunction(Hashtable<String, String> data) {
    	openBrowser(prop.getProperty("browsername"));
    	navigate(data.get("URL"));
        
    	if(data.get("URL").equals("https://mm-portal.amp.vg") || data.get("URL").equals("https://mm.amp.vg")) {
    		doLoginMMAmpAndPortal();	
    		} else {
    			doLoginForAll();
    		} 
        waitForElementforreportfail("dashboard_xpath");   	
        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("letsgo_xpath");
        
        test.log(LogStatus.INFO,"Reached at user's welcome page.");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
        if(isElementPresent("TesterText_xpath")) {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
        click("welcomepagebutton_xpath");
        test.log(LogStatus.INFO,"Clicked on welcome button.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");
        

  
        clickJS(prop.getProperty("searchbutton_xpath"));
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
      
        wait(2);
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));
        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();

        test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");   

        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("letsgo_xpath");
        test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");
        wait(5);
       
        try {
        driver.switchTo().frame(0);
        test.log(LogStatus.INFO,"Sample Welcome banner is appearing.");
        } catch (Exception e) {
        	reportFail("New welcome banner template is not appearing.");
        }
        
        driver.switchTo().parentFrame();
        
        navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");

        click("welcomepagebutton_xpath");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");   


        clickJS(prop.getProperty("searchbutton_xpath"));
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        test.log(LogStatus.INFO,"Search box functionality is working fine.");
        wait(2);
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));
        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();

        test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");
 
        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("letsgo_xpath");
        test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");

      
        if(isElementPresent("TesterText_xpath")) {
        	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
        } else {
        	reportFail("Sample Welcome page is still appearing after removal.");
        }
      
        test.log(LogStatus.INFO,"TC5 is completed.");
        
        } else {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
            test.log(LogStatus.INFO,"Sample welcome banner template is already selected.");
            navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
            click("welcomepagebutton_xpath");
            test.log(LogStatus.INFO,"Clicked on welcome button.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");

   
            clickJS(prop.getProperty("searchbutton_xpath"));
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            test.log(LogStatus.INFO,"Search box functionality is working fine.");
            wait(2);
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            test.log(LogStatus.INFO,"Unselected the new welcome banner template.");
            wait(3); 
            
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(2);
            
 
            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");

            navigate(data.get("URL")+"/welcome");
            waitForElementforreportfail("letsgo_xpath");
            test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");
            wait(5);
            
            try {
                driver.switchTo().frame(0);
                test.log(LogStatus.INFO,"New welcome banner is appearing. ");
                } catch (Exception e) {
                	reportFail("New welcome banner template is not appearing.");
                }
            
            driver.switchTo().parentFrame();
            
            navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");

            click("welcomepagebutton_xpath");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");   

            clickJS(prop.getProperty("searchbutton_xpath"));
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            
            test.log(LogStatus.INFO,"Search box functionality is working fine.");wait(2);
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(2);
            
            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");
 
            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");

            navigate(data.get("URL")+"/welcome");
            waitForElementforreportfail("letsgo_xpath");
            test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");

         
            if(isElementPresent("TesterText_xpath")) {
            	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
            } else {
            	reportFail("Sample Welcome page is still appearing after removal.");
            }
          
            test.log(LogStatus.INFO,"TC5 is completed.");
        	
        }
    	
    	
    	
    }

    
    private void function8X8 (Hashtable<String, String> data) {
    	
    	openBrowser(prop.getProperty("browsername"));
    	navigate(data.get("URL"));
    	doLoginForAll();
        waitForElementforreportfail("dashboard_xpath");   	
        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("getstarted_xpath");

        test.log(LogStatus.INFO,"Reached at user's welcome page.");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        if(isElementPresent("TesterText_xpath")) {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
        click("welcomepagebutton_xpath");
        test.log(LogStatus.INFO,"Clicked on welcome button.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");

        clickJS(prop.getProperty("searchbutton_xpath"));
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        
        test.log(LogStatus.INFO,"Search box functionality is working fine.");wait(2);
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));
        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();
        test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");

        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("getstarted_xpath");
        test.log(LogStatus.INFO,"waiting for 'Get Started' button on welcome page. ");
        wait(5);
       
        try {
        driver.switchTo().frame(0);
        
        test.log(LogStatus.INFO,"Sample Welcome banner is appearing.");
        } catch (Exception e) {
        	reportFail("New welcome banner template is not appearing.");
        }
    
        driver.switchTo().parentFrame();
        
        navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");

        click("welcomepagebutton_xpath");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");   

        clickJS(prop.getProperty("searchbutton_xpath"));
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        test.log(LogStatus.INFO,"Search box functionality is working fine.");
        wait(2);
        
      
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));
        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();
        test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");

        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("getstarted_xpath");
        test.log(LogStatus.INFO,"waiting for 'Get Started' button on welcome page. ");

      
        if(isElementPresent("TesterText_xpath")) {
        	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
        } else {
        	reportFail("Sample Welcome page is still appearing after removal.");
        }
      
        test.log(LogStatus.INFO,"TC5 is completed.");
        
        } else {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
            test.log(LogStatus.INFO,"Sample welcome banner template is already selected.");
            navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
            click("welcomepagebutton_xpath");
            test.log(LogStatus.INFO,"Clicked on welcome button.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");

            clickJS(prop.getProperty("searchbutton_xpath"));
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
 
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            test.log(LogStatus.INFO,"Unselected the new welcome banner template.");
            wait(2);   
            
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(3);
            
            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");
 
            navigate(data.get("URL")+"/welcome");
            waitForElementforreportfail("getstarted_xpath");
            test.log(LogStatus.INFO,"waiting for 'Get Started' button on welcome page. ");
            wait(5);
            try {
                driver.switchTo().frame(0);
                test.log(LogStatus.INFO,"Sample Welcome banner is appearing.");
                } catch (Exception e) {
                	reportFail("New welcome banner template is not appearing.");
                }
         
            driver.switchTo().parentFrame();
            
            navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");

            click("welcomepagebutton_xpath");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");   

            clickJS(prop.getProperty("searchbutton_xpath")); 
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            test.log(LogStatus.INFO,"Search box functionality is working fine.");
            wait(2);
                   
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(2);
            
            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");
     
            navigate(data.get("URL")+"/welcome");
            waitForElementforreportfail("getstarted_xpath");
            test.log(LogStatus.INFO,"waiting for 'Get Started' button on welcome page. ");
            
            
            if(isElementPresent("TesterText_xpath")) {
            	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
            } else {
            	reportFail("Sample Welcome page is still appearing after removal.");
            }
          
            test.log(LogStatus.INFO,"TC5 is completed.");
        	
        }
    	
    	
    	
    }
    
    private void userloginFunction(Hashtable<String, String> data) {
    	
    	openBrowser(prop.getProperty("browsername"));
    	navigate(data.get("URL"));
    	doLoginForAll();
    	waitForElementforreportfail("dashboard_xpath"); 
    	
    	urlname=	driver.getCurrentUrl();
		modifiedURL = urlname.split("#");
		
       	
        navigate(modifiedURL[0]+"welcome");
        waitForElementforreportfail("letsgo_xpath");

        test.log(LogStatus.INFO,"Reached at user's welcome page.");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        if(isElementPresent("TesterText_xpath")) {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        navigate(modifiedURL[0]+"#setup/dashboard/corporatemarketing");
        click("welcomepagebutton_xpath");
        test.log(LogStatus.INFO,"Clicked on welcome button.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");

        clickJS(prop.getProperty("searchbutton_xpath"));
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        test.log(LogStatus.INFO,"Search box functionality is working fine.");
        wait(2);              
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));
        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();
        test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");

        navigate(modifiedURL[0]+"welcome");
        waitForElementforreportfail("letsgo_xpath");
        test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");
        wait(5);
       
        try {
        driver.switchTo().frame(0);
        test.log(LogStatus.INFO,"Sample Welcome banner is appearing.");
        } catch (Exception e) {
        	reportFail("New welcome banner template is not appearing.");
        }
        
        driver.switchTo().parentFrame();
        
        navigate(modifiedURL[0]+"#setup/dashboard/corporatemarketing");

        click("welcomepagebutton_xpath");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");   

        clickJS(prop.getProperty("searchbutton_xpath"));
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        wait(2);                  
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));
        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();
        test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");
 
        navigate(modifiedURL[0]+"welcome");
        waitForElementforreportfail("letsgo_xpath");
        test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");

      
        if(isElementPresent("TesterText_xpath")) {
        	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
        } else {
        	reportFail("Sample Welcome page is still appearing after removal.");
        }
      
        test.log(LogStatus.INFO,"TC5 is completed.");
        
        } else {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
            test.log(LogStatus.INFO,"Sample welcome banner template is already selected.");
            navigate(modifiedURL[0]+"#setup/dashboard/corporatemarketing");
            click("welcomepagebutton_xpath");
            test.log(LogStatus.INFO,"Clicked on welcome button.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");

            clickJS(prop.getProperty("searchbutton_xpath"));
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            test.log(LogStatus.INFO,"Search box functionality is working fine.");
            wait(2);                        
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            
            test.log(LogStatus.INFO,"Unselected the new welcome banner template.");
            wait(4);
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(2);
            

            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");

            navigate(modifiedURL[0]+"welcome");
            waitForElementforreportfail("letsgo_xpath");
            test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");
            wait(5);
            try {
                driver.switchTo().frame(0);
                test.log(LogStatus.INFO,"Sample Welcome banner is appearing.");
                } catch (Exception e) {
                	reportFail("New welcome banner template is not appearing.");
                }
          
            driver.switchTo().parentFrame();
            
            navigate(modifiedURL[0]+"#setup/dashboard/corporatemarketing");

            click("welcomepagebutton_xpath");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");   

            clickJS(prop.getProperty("searchbutton_xpath"));
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            wait(2);                             
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(2);
            
            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");

            navigate(modifiedURL[0]+"welcome");
            waitForElementforreportfail("letsgo_xpath");
            test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");
            
            if(isElementPresent("TesterText_xpath")) {
            	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
            } else {
            	reportFail("Sample Welcome page is still appearing after removal.");
            }
          
            test.log(LogStatus.INFO,"TC5 is completed.");
        	
        }
    	    	
    }    
    private void master(Hashtable<String, String> data) {
    	openBrowser(prop.getProperty("browsername"));
    	navigate(data.get("URL"));
    	doLoginForMaster();
        waitForElementforreportfail("dashboard_xpath");   	
        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("letsgo_xpath");
        
        test.log(LogStatus.INFO,"Reached at user's welcome page.");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        if(isElementPresent("TesterText_xpath")) {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
        click("welcomepagebutton_xpath");
        test.log(LogStatus.INFO,"Clicked on welcome button.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");

        clickJS(prop.getProperty("searchbutton_xpath"));
        waitforChrisScript();
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");    
        wait(2);                              
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));
        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();

        test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");   

        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("letsgo_xpath");
        test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");
        wait(5);
       
        try {
        driver.switchTo().frame(0);
        test.log(LogStatus.INFO,"Sample Welcome banner is appearing.");
        } catch (Exception e) {
        	reportFail("New welcome banner template is not appearing.");
        }
        
        driver.switchTo().parentFrame();
        
        navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
        waitforChrisScript();
        click("welcomepagebutton_xpath");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        type("searchforwelcomerecord_xpath","Welcome");   

        clickJS(prop.getProperty("searchbutton_xpath"));
        waitforChrisScript();
        test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
        waitForElementforreportfail("welcomebannertemplate_xpath");
        test.log(LogStatus.INFO,"Search box functionality is working fine.");
        wait(2);
                                     
        clickJS(prop.getProperty("welcomebannertemplate_xpath"));

        wait(2);
        
        clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

        welcomeflashMessagevisibilityandinvisibilty();

        test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");
 
        navigate(data.get("URL")+"/welcome");
        waitForElementforreportfail("letsgo_xpath");
        test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");

      
        if(isElementPresent("TesterText_xpath")) {
        	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
        } else {
        	reportFail("Sample Welcome page is still appearing after removal.");
        }
      
        test.log(LogStatus.INFO,"TC5 is completed.");
        
        } else {
        	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
            test.log(LogStatus.INFO,"Sample welcome banner template is already selected.");
            navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
            waitforChrisScript();
            click("welcomepagebutton_xpath");
            test.log(LogStatus.INFO,"Clicked on welcome button.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");


            clickJS(prop.getProperty("searchbutton_xpath"));
            waitforChrisScript();
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            test.log(LogStatus.INFO,"Search box functionality is working fine.");
            wait(2);                                     
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            test.log(LogStatus.INFO,"Unselected the new welcome banner template.");
            wait(3); 
            
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(2);
            
            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");

            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Selected the new welcome banner template and clicked on save button.");

            navigate(data.get("URL")+"/welcome");
            waitForElementforreportfail("letsgo_xpath");
            test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");
            wait(5);
            
            try {
                driver.switchTo().frame(0);
                test.log(LogStatus.INFO,"New welcome banner is appearing. ");
                } catch (Exception e) {
                	reportFail("New welcome banner template is not appearing.");
                }
            
            driver.switchTo().parentFrame();
            
            navigate(data.get("URL")+"/#setup/dashboard/corporatemarketing");
            waitforChrisScript();
            click("welcomepagebutton_xpath");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            type("searchforwelcomerecord_xpath","Welcome");   

            clickJS(prop.getProperty("searchbutton_xpath"));  
            waitforChrisScript();
            test.log(LogStatus.INFO,"Typed 'Welcome' in the search field and pressed entered.");
            waitForElementforreportfail("welcomebannertemplate_xpath");
            
            test.log(LogStatus.INFO,"Search box functionality is working fine.");wait(2);
                                               
            clickJS(prop.getProperty("welcomebannertemplate_xpath"));
            wait(2);
            
            clickJS("//div[@id='modalActionButtons']//div[@class='btn-text'][contains(text(),'Save')]");
     
            welcomeflashMessagevisibilityandinvisibilty();
            test.log(LogStatus.INFO,"Unselected the new welcome banner template and clicked on save button.");
  
            navigate(data.get("URL")+"/welcome");
            waitForElementforreportfail("letsgo_xpath");
            test.log(LogStatus.INFO,"waiting for 'Let's Go!' button on welcome page. ");

         
            if(isElementPresent("TesterText_xpath")) {
            	reportPass("Welcome page is appearing when selected and disappeared when unselected.");
            } else {
            	reportFail("Sample Welcome page is still appearing after removal.");
            }
          
            test.log(LogStatus.INFO,"TC5 is completed.");
        	
        }
    	
    	
    	
    }
    
    
}
