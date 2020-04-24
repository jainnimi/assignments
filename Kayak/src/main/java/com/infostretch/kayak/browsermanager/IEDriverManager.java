package com.infostretch.kayak.browsermanager;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.infostretch.kayak.manager.DriverManager;

/**
 * This class is responsible for initializing IE driver
 * 
 * @author nimishajain
 *
 */
public class IEDriverManager extends DriverManager {
	
	public static Logger log =LogManager.getLogger(IEDriverManager.class.getName());
	/**
	 * initialize IE browser
	 */
	@Override
	public void initializeWebBrowser() {

		System.setProperty("webdriver.ie.driver",
				System.getProperty("user.dir") + "/src/main/java/com/infostretch/kayak/resources/geckodriver");

		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		log.info("Browser instantiated");

	}

}
