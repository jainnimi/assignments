package com.infostretch.kayak.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KayakHomePage {
	
		WebDriver driver;
	    WebDriverWait wait;
	
	@FindBy(how=How.XPATH, using ="//div[contains(@id,'origin-input-wrapper')]//button")
	WebElement fromElementPresent;
	@FindBy(how=How.XPATH, using ="//*[@class='search-form-inner']//*[contains(@id, 'origin')]//*[contains(@id,'origin-input-wrapper')]")
	WebElement origBtn;
	//WebElement locators
	private By origin = By.xpath("//div[(contains(@id,'origin-airport'))]");
	private By selectedOrigin = By.xpath("//div[contains(@id,'origin-airport-smarty-multi-container')]/div/div[2]/button");
	private By enterOrigin = By.name("origin");
	private By destination = By.xpath("//div[contains(@id,'destination-airport')]");
	private By enterDestination = By.name("destination");
	private By openCalender = By.xpath("//div[contains(@id,'dateRangeInput-display-start-inner')]");
	private By calenderMonth = By.xpath("//div[contains(@id,'stl-jam-cal-2020')] [@aria-hidden='false']/div/div[1]");
	private By gotoNextMonth = By.cssSelector("#stl-jam-cal-nextMonth");
	//private By sDate = By.xpath("//div[@data-val='"+ epochDate +"']");
	private By searchbtn = By.xpath("//button[@title='Search flights']");
	private By originNearby = By.xpath("//label[@class='js-nearby-select-all-checkbox']");
	private By desNearby = By.xpath("//label[@class='js-nearby-select-all-checkbox'] [contains(@for,'destination-airport-nearbyCheck')]");
	
	
	//WebElement Locator Methods
	public KayakHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	
	public WebElement getOrigin() {
		return driver.findElement(origin);
	}
	
	public WebElement removePreSelectedOrigin() {
		return driver.findElement(selectedOrigin);
	}
	
	public WebElement selectOrigin() {
		return driver.findElement(enterOrigin);
	}
	
	public WebElement clickDestination() {
		return driver.findElement(destination);
	}
	
	public WebElement selectDestination() {
		return driver.findElement(enterDestination);
	}
	
	public WebElement calenderOpen() {
		return driver.findElement(openCalender);
	}
	
	public WebElement getCalenderMonth() {
		return driver.findElement(calenderMonth);
	}
	
	public WebElement moveToNextMonth() {
		return driver.findElement(gotoNextMonth);
	}
	
	public WebElement selectDate(long epochDate) {
		
		return driver.findElement(By.xpath("//div[@data-val='"+ epochDate +"']"));
	}
	
	public WebElement search() {
		return driver.findElement(searchbtn);
	}
	
	public WebElement getOriginNearby() {
		return driver.findElement(originNearby);
	}
	
	public WebElement getDestNearby() {
		return driver.findElement(desNearby);
	}
	
	public void clearElementIfPresentFrom() {
		
		try {
			
			if (fromElementPresent.isDisplayed()) {
				
				fromElementPresent.click();
			}
			
		} catch (NoSuchElementException e) {
			

		}
	}
	
	public void clickOnOrigBtn() {
		
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(origBtn));
		try {
			
			try {
				
				origBtn.click();
			} catch (WebDriverException e) {
				
				action.moveToElement(origBtn).click().perform();
			}			
			
		}catch (NoSuchElementException e) {
			
			throw e;
		}
	}

}
