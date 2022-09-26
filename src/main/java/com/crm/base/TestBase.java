package com.crm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.util.TestUtil;
import com.crm.util.WebEventListener;

public class TestBase {
      
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("F:\\QA JDK and Eclipse Tools\\Selenium_Workspace\\FreeCRMTest\\src\\main\\"
					+ "java\\com\\crm\\configration\\config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("Firefox")) {
			 System.setProperty("webdriver.gecko.driver", "F:/QA JDK and Eclipse Tools/Seleniumjars/geckodriver.exe");
		      driver = new FirefoxDriver();
		}
		else if (browserName.equals("Chrome")) {
		     System.setProperty("webdriver.chrome.driver", "F:/QA JDK and Eclipse Tools/Seleniumjars/chromedriver.exe");
			 driver = new ChromeDriver(); 
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		 
		driver.get(prop.getProperty("url"));
	}
}
