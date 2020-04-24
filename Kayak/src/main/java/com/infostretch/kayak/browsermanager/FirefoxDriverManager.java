package com.infostretch.kayak.browsermanager;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.infostretch.kayak.manager.DriverManager;

/**
 * This class is responsible for initializing Gecko driver
 * 
 * @author nimishajain
 *
 */
public class FirefoxDriverManager extends DriverManager {
	
	public static Logger log =LogManager.getLogger(FirefoxDriverManager.class.getName());
	/**
	 * initialize firefox browser
	 */
	@Override
	public void initializeWebBrowser() {

		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "/src/main/java/com/infostretch/kayak/resources/geckodriver");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		log.info("Friefox Browser instantiated");

	}

}
