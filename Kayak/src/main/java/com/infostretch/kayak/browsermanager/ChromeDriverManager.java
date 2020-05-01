package com.infostretch.kayak.browsermanager;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.infostretch.kayak.manager.DriverManager;



/**
 * This class is responsible for initializing Chrome driver
 * @author nimishajain
 *
 */
public class ChromeDriverManager extends DriverManager{
	
	
	public static Logger log =LogManager.getLogger(ChromeDriverManager.class.getName());
	/**
	 * initialize chrome browser
	 */
	@Override
	public void initializeWebBrowser() {
		ChromeOptions options=new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/java/com/infostretch/kayak/resources/chromedriver");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		log.info("Chrome Browser instantiated");
		
		
		
	}

}
