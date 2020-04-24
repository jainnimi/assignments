package com.infostretch.kayak.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWebDriverWait {
	
	public WebDriver driver;
	
	
	public ExplicitWebDriverWait(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriverWait waitExplicit = new WebDriverWait(driver, 15);

}
