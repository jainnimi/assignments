package com.infostretch.kayak.browsermanager;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.safari.SafariDriver;
import com.infostretch.kayak.manager.DriverManager;

/**
 * This class is responsible for initializing Safari driver
 * 
 * @author nimishajain
 *
 */
public class SafariDriverManager extends DriverManager {
	
	public static Logger log =LogManager.getLogger(SafariDriverManager.class.getName());
	/**
	 * initialize Safari browser
	 */
	@Override
	public void initializeWebBrowser() {

		System.setProperty("webdriver.safari.noinstall", "true");
		driver = new SafariDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		log.info("Browser instantiated");

	}

}
