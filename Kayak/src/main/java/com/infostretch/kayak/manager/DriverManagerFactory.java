package com.infostretch.kayak.manager;

import com.infostretch.kayak.browsermanager.ChromeDriverManager;
import com.infostretch.kayak.browsermanager.FirefoxDriverManager;
import com.infostretch.kayak.browsermanager.IEDriverManager;
import com.infostretch.kayak.browsermanager.SafariDriverManager;

/**
 * Factory class to return specific driver manager
 * 
 * @author nimishajain
 *
 */
public class DriverManagerFactory {
	/**
	 * Returns the driver manager depending on browser type string, by default
	 * returns Chrome driver manager
	 * 
	 * @param browserType
	 * @return DriverManager
	 */
	public static DriverManager getDriverManager(String browserType) {

		if (browserType.equalsIgnoreCase("ie")) {
			return new IEDriverManager();
		} else if (browserType.equalsIgnoreCase("firefox")) {
			return new FirefoxDriverManager();
		} else if (browserType.equalsIgnoreCase("safari")) {
			return new SafariDriverManager();
		} else {
			return new ChromeDriverManager();
		}

	}

}
