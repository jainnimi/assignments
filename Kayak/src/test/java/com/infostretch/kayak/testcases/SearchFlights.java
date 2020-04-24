package com.infostretch.kayak.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.infostretch.kayak.manager.DriverManager;
import com.infostretch.kayak.manager.DriverManagerFactory;
import com.infostretch.kayak.page.objects.KayakHomePage;
import com.infostretch.kayak.page.objects.KayakSearchResultPage;
import com.infostretch.kayak.utils.TestDataUtility;

public class SearchFlights {

	public static Logger log = LogManager.getLogger(SearchFlights.class.getName());
	
	
	
	WebDriver driver;
	DriverManager driverManager;
	KayakHomePage homePage;
	
	@Parameters ({"browserName"})
	@BeforeMethod
	public void initBrowser(String browser) {
		
		driverManager= DriverManagerFactory.getDriverManager(browser);
		driver = driverManager.getWebDriver();
		
		}

	
	@Test(dataProvider="searchDataProvider")
	public void searchFlights(String origin, String destination, String fromDate, String ToDate, String isOriginNearby, String isDestNearby, String recordNum) throws InterruptedException {
		
		
		driver.get("https://www.kayak.com/");

		homePage=PageFactory.initElements(driver, KayakHomePage.class);
		
		homePage.clearElementIfPresentFrom();

		homePage.clickOnOrigBtn();
		
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 WebElement ele = driver.findElement(By.xpath("//input[contains(@id, 'origin-airport')]"));		 
		 wait.until(ExpectedConditions.visibilityOf(ele));
		 
		  homePage.selectOrigin().sendKeys((CharSequence) origin);
//			WebDriverWait w = new WebDriverWait(driver, 30);
//			w.until(ExpectedConditions.visibilityOf(homePage.clickDestination()));
		  Thread.sleep(1000);
			homePage.selectOrigin().sendKeys(Keys.ENTER);
			log.info("Sucessfully entered Origin:" + origin);

			// if NearBy Origin is true then selecting the near by check box
			if (Boolean.parseBoolean(isOriginNearby)== true) {
				homePage.getOrigin().click();
				homePage.getOriginNearby().click();
				//w = new WebDriverWait(driver, 30);
				//w.until(ExpectedConditions.visibilityOf(homePage.clickDestination()));
				homePage.selectOrigin().sendKeys(Keys.ENTER);
				log.info("enter near by airport for  origin" );
			}
			
			
			
			homePage.clickDestination().click();
			Thread.sleep(1000);
//			w = new WebDriverWait(driver, 30);
//			w.until(ExpectedConditions.visibilityOf(homePage.calenderOpen()));

			// Enters destination
			homePage.selectDestination().sendKeys((CharSequence)destination);
			Thread.sleep(1000);
			
			homePage.selectDestination().sendKeys(Keys.ENTER);
			log.info("Sucessfully entered Destination:" + destination);

			// if NearBy destination is true then selecting the near by check box
			if (Boolean.parseBoolean(isDestNearby)== true) {
				homePage.clickDestination().click();
				homePage.getDestNearby().click();
				//w = new WebDriverWait(driver, 30);
				//w.until(ExpectedConditions.visibilityOf(homePage.calenderOpen()));
				homePage.selectDestination().sendKeys(Keys.ENTER);
				log.info("enter near by airport for  Destination");
			}

			// opens calender
			homePage.calenderOpen().click();
		 
			
			// get the current calendar months displayed
			WebElement month = homePage.getCalenderMonth();

			
			Date fromdate=TestDataUtility.getDate(fromDate);
			
			// selecting the required start month
			while (!(month.getText()).contains(new SimpleDateFormat("MMMM").format(fromdate))) {
				homePage.moveToNextMonth().click();
				Thread.sleep(1000);
				
				//w = new WebDriverWait(driver, 30);
				//w.until(ExpectedConditions.visibilityOf(homePage.selectDestination()));
				month = homePage.getCalenderMonth();
			}

			// selecting from date
			homePage.selectDate(fromdate.getTime()).click();
			log.warn("Sucessfully selected toDate:" + ToDate);
			
			
			Date todate=TestDataUtility.getDate(ToDate);
			// selecting the required end month
			while (!(month.getText()).contains(new SimpleDateFormat("MMMM").format(todate))) {
				homePage.moveToNextMonth().click();
				Thread.sleep(1000);
				
				//w = new WebDriverWait(driver, 30);
				//w.until(ExpectedConditions.visibilityOf(homePage.selectDestination()));
				month = homePage.getCalenderMonth();
			}

			// selecting to date
			homePage.selectDate(todate.getTime()).click();
			log.info("Sucessfully selected fromDate:" + fromDate);

			// click on search
			homePage.search().click();
			
			// switching to search result child window
			Set<String> windowIds = driver.getWindowHandles();
			Iterator<String> id = windowIds.iterator();
			String parentId = id.next();
			if (id.hasNext()) {
				String ch1 = id.next();
				driver.switchTo().window(ch1);
			}
			

			//Handled flight price alert popup
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//*[contains(@class, 'Flights-Results-FlightPriceAlertDriveBy')]"))));
			String i = driver.findElement(By.xpath("//*[contains(@class, 'Flights-Results-FlightPriceAlertDriveBy')]"))
					.getAttribute("id");
			driver.findElement(By.id("" + i + "-dialog-close")).click();
			
			KayakSearchResultPage searchResultPage=PageFactory.initElements(driver, KayakSearchResultPage.class);
			
			//Getting search results
			List<WebElement> results = searchResultPage.getResults();
			log.info("Number of results:" + results.size());
			
			int recNum = Integer.parseInt(recordNum);

			if (results != null && results.size() > 0) {

				if (results.size() >= recNum) {
					WebElement selectedRecord = results.get(recNum - 1);
					wait.until(ExpectedConditions.visibilityOf(selectedRecord));
					selectedRecord.click();

				} else {

					log.warn("If record to be selected is greater than number of record displayed");
				}

			} else {

				log.warn("No results found");
			}
			
			//assert only if nearby not selected
			if(Boolean.getBoolean(isDestNearby) && Boolean.getBoolean(isOriginNearby)) {
				List<String> list = searchResultPage.getList();
				String org=list.get(0);
				String orgwocode=null ;
				if(null!=org){
					 orgwocode = org.substring(org.indexOf("["), org.indexOf("]")).trim();
				}
				 Assert.assertEquals(origin , orgwocode);
				 log.info("Assert validated successfully");
				
			}else {
				log.warn("Not asserting when Nearby true");
			}
			

	}
	
	
	
	
	
	
	@DataProvider(name= "searchDataProvider", parallel = false)
	 public static Object[][] dataProviderMethodLogin() {
		
		
		return new Object[][]{
			 {"Oakland", "Charlotte", "6/12/2020", "7/15/2020", "false", "false", "4"},
			 {"Dallas", "Mumbai", "8/4/2020", "10/20/2020", "false", "true", "4"},
			 {"San Jose", "Delhi", "6/20/2020", "7/9/2020", "true", "false", "4"},
			 
			 };
		/**
		 * This code not in use currently but can be configured for reading data from excel and passing to Data provider 
		 */
//		//get tje text data from Excel sheel
//		ArrayList<ArrayList<Object>> testDataFromExcel=null;
//		Object[][] array = null;
//		try {
//			 testDataFromExcel = TestDataUtility.getTestDataFromExcel();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(null!=testDataFromExcel && testDataFromExcel.size()>0) {
//			
//			 array = new Object[testDataFromExcel.size()][];
//			for (int i = 0; i < testDataFromExcel.size(); i++) {
//			    ArrayList<Object> row = testDataFromExcel.get(i);
//			    array[i] = row.toArray(new Object[row.size()]);
//			}
//			
//			
//		}
		
//	 return array;
			 
	 

	}
	
	@AfterMethod
	public void closeSession() {

		driverManager.quitWebDriver();
		
	}

}
