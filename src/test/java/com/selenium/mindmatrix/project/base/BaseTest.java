
package com.selenium.mindmatrix.project.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.mindmatrix.project.util.ExtentManager;
import com.selenium.mindmatrix.project.util.ZipFolder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;



public class BaseTest {
	public WebDriver driver;
	public Properties prop;
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public String testName;
	public SoftAssert sassert;
	public ExtentManager object;
	public ZipFolder zipping;
	

	@BeforeMethod
	public void init1() {
		init();
		sassert = new SoftAssert();

	}

	@AfterMethod

	public void quit() {
		try {
			sassert.assertAll();
		} catch (Error e) {
			test.log(LogStatus.FAIL, e.getMessage());

		}

		if (test != null) {

			rep.endTest(test);
			rep.flush();

		}
         if(driver!=null)
         driver.quit();
         try {
			ZipFolder.zipper();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	}

	protected void init() {
		if (prop == null) {
			testName = this.getClass().getSimpleName();
			prop = new Properties();
			FileInputStream fs;
			try {
				fs = new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//projectconfig.properties");
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	protected void openBrowser(String bType) {

		if (bType.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + prop.getProperty("firefox_driver"));
			driver = new FirefoxDriver();
		} else if (bType.equals("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications--");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + prop.getProperty("chrome_driver"));
			
			driver = new ChromeDriver(options);
		} else if (bType.equals("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + prop.getProperty("ie_driver"));

			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser " + bType + " opened and maximized successfully");

	}

	protected void navigate(String urlkey) {
       
		driver.get(urlkey);	
		driver.navigate().refresh();
		waitForLoad(driver);
		test.log(LogStatus.INFO, "Navigated to " + urlkey);
	}
	
	protected void geturl(String urlkey) {

		driver.get(urlkey);	
		driver.navigate().refresh();
		waitForLoad(driver);
		test.log(LogStatus.INFO, "Navigated to " + urlkey);

	}

	protected void click(String locatorkey) {
	    
		waitForElementforreportfail(locatorkey);
		getElement(locatorkey).click();

	}

	protected void clickJS(String path) {

		WebElement element = driver.findElement(By.xpath(path));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);

	}
	
	protected void actionClick(String locatorkey) {
		WebElement element = getElement(locatorkey);
     	Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	protected void type(String locatorkey, String data) {
		getElement(locatorkey).clear();
		getElement(locatorkey).sendKeys(data);
		test.log(LogStatus.INFO, "Typed successfully in " + locatorkey.split("_")[0] + " field.");
	}

	protected void clickAndWait(String locator_clicked, String locator_forwait) {
		
		wait(1);
		int count = 10;
		for (int i = 0; i < count; i++) {
			getElement(locator_clicked).click();
			wait(1);
			if (isElementPresent(locator_forwait))
			break;
		}
	}

	protected void waitForLoad(WebDriver driver) {
//		new WebDriverWait(driver, 90).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
//				.executeScript("return document.readyState").equals("complete"));
		wait(10);
	}

	protected static void wait(int timeToWait) {
		try {
			Thread.sleep(timeToWait * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
	
	

	protected void waitForElement(String item) {

		WebDriverWait wait = new WebDriverWait(driver, 90);

		if (item.endsWith("_xpath")) {

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty(item))));
			
			} catch (Exception e) {
              
				test.log(LogStatus.INFO, item.split("_")[0] + " field is not found.");
			
				
			
			}

		}
		
		
		if (item.endsWith("_id")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty(item))));
			} catch (Exception e) {

				test.log(LogStatus.INFO, "Webelement not found");
			}
		}

	}
	
	
	protected void waitForElementforreportfail(String item) {

		WebDriverWait wait = new WebDriverWait(driver, 90);

		if (item.endsWith("_xpath")) {

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty(item))));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(item))));
			} catch (Exception e) {
              		
			reportFail("WebElement '"+item.split("_")[0]+ "' is not found.");
				
				
			}
			
			

		}
		if (item.endsWith("_id")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty(item))));
				wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty(item))));
			} catch (Exception e) {

				reportFail("WebElement '"+item.split("_")[0]+ "' is not found.");
			}
		}

		if (item.endsWith("_css")) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty(item))));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(item))));
		} catch (Exception e) {
          		
		reportFail("WebElement '"+item.split("_")[0]+ "' is not found.");
			
			
		}
		
		
		}
		if (item.endsWith("_classname")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty(item))));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(item))));
			} catch (Exception e) {
	          		
			reportFail("WebElement '"+item.split("_")[0]+ "' is not found.");
				
				
			}
			
			
			}
	}

	protected WebElement getElement(String locatorkey) {
		WebElement e = null;
	
		try {
			if (locatorkey.endsWith("_xpath")) {
						
			e = driver.findElement(By.xpath(prop.getProperty(locatorkey)));	
		
			}
			else if (locatorkey.endsWith("_id")) {
				e = driver.findElement(By.id(prop.getProperty(locatorkey)));
			}
			
			else if (locatorkey.endsWith("_cssSelector")) {
				e = driver.findElement(By.cssSelector(prop.getProperty(locatorkey)));}
			else if (locatorkey.endsWith("_classname")) {
				e = driver.findElement(By.className(prop.getProperty(locatorkey)));}
			else {
				reportFail("locator not working--" + locatorkey);
				Assert.fail("locator not working--" + locatorkey);
			}
		} catch (Exception ex) {
			reportFail(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test" + ex.getMessage());
		}
			
		return e;
	}
	
	protected WebElement getElementforloop(String locatorkey) {
		WebElement e = null;
	
		try {
			
				e = driver.findElement(By.xpath(locatorkey));
				
			
		} catch (Exception ex) {
			reportFail(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test" + ex.getMessage());
		}
		return e;
	}

	/**************** validate ***************/

	protected boolean isElementPresent(String locatorkey) {
		List<WebElement> elementlist = null;
		if (locatorkey.endsWith("_xpath"))
			elementlist = driver.findElements(By.xpath(prop.getProperty(locatorkey)));
		else if (locatorkey.endsWith("_id"))
			elementlist = driver.findElements(By.id(prop.getProperty(locatorkey)));
		else if (locatorkey.endsWith("_cssSelector"))
			elementlist = driver.findElements(By.id(prop.getProperty(locatorkey)));
		else if (locatorkey.endsWith("_classname"))
			elementlist = driver.findElements(By.id(prop.getProperty(locatorkey)));
		else {
			reportFail("locator not working--" + locatorkey);
			Assert.fail("locator not working--" + locatorkey);
		}
		if (elementlist.size() == 0)
			return false;
		else
			return true;

	}

	protected boolean verifyText(String locatorkey, String expectedtextkey) {
		String actual = getElement(locatorkey).getText();
		String expected = prop.getProperty(expectedtextkey);
		if (actual.equals(expected))
			return true;
		else
			return false;

	}

	/**************** reporting ***************/

	protected void reportPass(String msg) {
		test.log(LogStatus.PASS, msg);

	}

	protected void reportFail(String errmsg) {

		if (errmsg.contains("Unable to locate element")) {

			String msg = " / CHECK YOUR INTERNET CONNECTION OR DVL-MASTER MIGHT BE DOWN.";
			test.log(LogStatus.FAIL, errmsg + msg);

		}

		else {

			test.log(LogStatus.FAIL, errmsg);
		}
       
		takeScreenshot();
		email(errmsg);
		Assert.fail(errmsg);

	}
	protected void takeScreenshot() {
		// fileName of the screenshot
				Date d = new Date();
				String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		// store screenshot in that file
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "//Reports//"+ExtentManager.folderName+"//" + screenshotFile));
				} catch (IOException e) {
					e.printStackTrace();
				}
		// put screenshot file in reports
				test.log(LogStatus.INFO, "Screenshot-> "
						+ test.addScreenCapture("..//"+ExtentManager.folderName+"//" +"//"+ screenshotFile));

			}



	/************** appFunctions ************/

	protected void doLoginMMAmpAndPortal() {
		type("username_xpath", "hkhdjkhjds");
		type("password_xpath", "djkjkjdkjs@2001");
		wait(2);
		clickJS("//*[@id='btnLogin']");

		test.log(LogStatus.INFO, "Clicked on 'LogIn' button ");

	}
	
	
	protected void doLoginForAll() {

		type("username_xpath", System.getenv("AMPINSTALLU"));
		type("password_xpath",  System.getenv("AMPINSTALLP"));
		wait(2);
		clickJS("//*[@id='btnLogin']");

		test.log(LogStatus.INFO, "Clicked on 'LogIn' button ");

	}
	
	
	protected void doLoginForMaster() {

		type("username_xpath", "dhuhe");
		type("password_xpath", "ejiuewiouj");
		wait(2);
		clickJS("//*[@id='btnLogin']");

		test.log(LogStatus.INFO, "Clicked on 'LogIn' button ");

	}
	
	protected void flashMessagevisibilityandinvisibilty() {
       
			
			if (isElementPresent("flashmessage_xpath")) {
			WebDriverWait wait = new WebDriverWait(driver,90);
	        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(prop.getProperty("flashmessage_xpath")))));
	        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(prop.getProperty("flashmessage_xpath")))));
	        wait(2);
		} 
			else {
		}
		
	}	
	
	
	protected void welcomeflashMessagevisibilityandinvisibilty() {
	       
		
		if (isElementPresent("welcomeflashmessage_xpath")) {
		WebDriverWait wait = new WebDriverWait(driver,90);
       
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(prop.getProperty("welcomeflashmessage_xpath")))));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(prop.getProperty("welcomeflashmessage_xpath")))));
        wait(2);
	} 
		else {
	}
	
}	
	
	
	protected boolean verifyFrameText(String text, long waiting, int framelocation)
	{
		WebDriverWait wait=new WebDriverWait(driver, waiting);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocation));
		wait(2);
		
		if(driver.getPageSource().contains(text))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}

	protected void loadForFirstFrame()
	{
		
		WebDriverWait wait=new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		wait(2);
		driver.switchTo().defaultContent();
		
		
	}
	
	protected void waitforChrisScript() {
	
	try {
		
		if (isElementPresent("chrisscriptwait_xpath")) {
		WebDriverWait wait = new WebDriverWait(driver,45);
        wait.until(ExpectedConditions.invisibilityOf(getElement("chrisscriptwait_xpath")));
	} 
		else {
	}
	}
	catch (Exception e) {
		reportFail("AJAX script is still present on the webpage."+e.getMessage());
	}
	
}
	
	protected int getNumber(String companiescount_xpath) {

		 WebElement s=getElement(companiescount_xpath);

		 String totalorg =s.getText();
		String withoutfirstbracket = totalorg.substring(3);

		String withoutlastbracket = withoutfirstbracket.substring(0, withoutfirstbracket.length() - 1);

		int org = Integer.parseInt(withoutlastbracket);
	
		return org;
	}
	
    protected String decrypt(String value) {
		String decodedvalue;
		byte[] decoded = Base64.getDecoder().decode(prop.getProperty(value));
		decodedvalue=new String(decoded, StandardCharsets.UTF_8);
		return decodedvalue ;
		
	}
	
/************************Send Email function starts***************************/	
	
	protected void email(String errormessage)

	    {
	    //	TestUtil.zip("C:\\rep");
	    
	                String[] to={"munish@revclerx.com"};

	                String[] cc={};
	                String[] bcc={};

	                //This is for google
                   
	                sendMail("sangam@revclerx.com",
	                		            "Test@123",
	                		            "mail.revclerx.com",
	                		            "465",
	                		            "true",
	                		            "true",
	                		            true,
	                		            "javax.net.ssl.SSLSocketFactory",
	                		            "false",
	                		            to,
	                		            cc,
	                		            bcc,
	                		            "Notification of the failed Test case, suite name is 'CriticalFunctionalityCheck'.",
	                		            "Hello Sir,\n\nTest case-> '"+testName+"' is failed for the below URL :\n\n"+driver.getCurrentUrl()+"\n\nFailed => "+errormessage+"\n\n Please see the detailed report which is saved in the reports folder of the Automation project.\n\nRegards,\nChandigarh Automation Testing Team\n\n***This is an automatically generated email.***",
	                		        	"",
	                		        	"");
	      

	    }
	protected  static boolean sendMail(String userName,
	        		String passWord,
	        		String host,
	        		String port,
	        		String starttls,
	        		String auth,
	        		boolean debug,
	        		String socketFactoryClass,
	        		String fallback,
	        		String[] to,
	        		String[] cc,
	        		String[] bcc,
	        		String subject,
	        		String text,
	        		String attachmentPath,
	        		String attachmentName){


	                Properties props = new Properties();

	                //Properties props=System.getProperties();

	        props.put("mail.smtp.user", userName);

	        props.put("mail.smtp.host", host);

	                if(!"".equals(port))

	        props.put("mail.smtp.port", port);

	                if(!"".equals(starttls))

	        props.put("mail.smtp.starttls.enable",starttls);

	        props.put("mail.smtp.auth", auth);
	       // props.put("mail.smtps.auth", "true");


	                if(debug){

	                props.put("mail.smtp.debug", "true");

	                }else{

	                props.put("mail.smtp.debug", "false");         

	                }

	                if(!"".equals(port))

	        props.put("mail.smtp.socketFactory.port", port);

	                if(!"".equals(socketFactoryClass))

	        props.put("mail.smtp.socketFactory.class",socketFactoryClass);

	                if(!"".equals(fallback))

	        props.put("mail.smtp.socketFactory.fallback", fallback);

	 

	        try

	        {

	                        Session session = Session.getDefaultInstance(props, null);

	            session.setDebug(debug);

	            MimeMessage msg = new MimeMessage(session);

	            msg.setText(text);

	            msg.setSubject(subject);
	            //attachment start
	            // create the message part 
	/*           
	            Multipart multipart = new MimeMultipart();
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            DataSource source = 
	              new FileDataSource(attachmentPath);
	            messageBodyPart.setDataHandler(
	              new DataHandler(source));
	            messageBodyPart.setFileName(attachmentName);
	            multipart.addBodyPart(messageBodyPart);
	            
	            // attachment ends

	            // Put parts in message
	            msg.setContent(multipart);
	  */          
	            msg.setFrom(new InternetAddress("munish@revclerx.com"));

	                        for(int i=0;i<to.length;i++){

	            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));

	                        }

	                        for(int i=0;i<cc.length;i++){

	            msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));

	                        }

	                        for(int i=0;i<bcc.length;i++){

	            msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));

	                        }

	            msg.saveChanges();

	                        Transport transport = session.getTransport("smtp");

	                        transport.connect(host, userName, passWord);

	                        transport.sendMessage(msg, msg.getAllRecipients());

	                        transport.close();
  
	                        return true;

	        }

	        catch (Exception mex)

	        {

	            mex.printStackTrace();

	                        return false;

	        }

	        }
	        
/************************Send Email function ends here***************************/	
	
}