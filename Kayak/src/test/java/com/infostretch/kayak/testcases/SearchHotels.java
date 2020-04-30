package com.infostretch.kayak.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.infostretch.kayak.manager.DriverManager;
import com.infostretch.kayak.manager.DriverManagerFactory;

public class SearchHotels {
	
	WebDriver driver;
	DriverManager driverManager;
	
	//@Parameters({"browserName"})
	@BeforeMethod
	public void setup() {
		
		driverManager = DriverManagerFactory.getDriverManager("chrome");
		driver = driverManager.getWebDriver();
		driver.get("https://www.kayak.com/hotels");
		
	}
	
	@Test
	public void searchHotles() {
		
		
		
	}

}
