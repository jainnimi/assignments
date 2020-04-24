package com.infostretch.kayak.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;



public abstract class DriverManager {
	public static Logger log = LogManager.getLogger(DriverManager.class.getName());

		//for sequential execution use static WebDriver
		//public static WebDriver driver;
		
		//for parallel execution use public WebDriver
		public  WebDriver driver;
		
		public abstract void initializeWebBrowser();
		
		
		/**
		 * Quits driver
		 */
		public void quitWebDriver() {
			if(driver!=null) {
				driver.quit();
				log.info("Closing Browser");
			}
		}
		/**
		 * initialize WebDriver if null
		 * @return WebDriver
		 */
		public WebDriver getWebDriver() {
			if(driver==null) {
				initializeWebBrowser();
			}
			return driver;
		}
}
