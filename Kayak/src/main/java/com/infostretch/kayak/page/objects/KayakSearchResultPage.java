package com.infostretch.kayak.page.objects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KayakSearchResultPage {

	public WebDriver driver;
	WebDriverWait wait;
	
	public KayakSearchResultPage(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	
	
	@FindBy(how=How.XPATH, using ="//*[contains(@class,'js-selection-display')]")
	List<WebElement> list;
	
	private By searchResult = By.xpath("//div[contains(@class,'Results-HorizonResult')]");
	
	/**
	 * get the search results
	 * @return List of web elements
	 */
	public List<WebElement> getResults(){
		return driver.findElements(searchResult);
	}
	
	public List<String > getList() {
		
		List<String> al = new ArrayList<String>();
		for (int i=0; i<list.size(); i++) {
			
			if(!list.get(i).getText().isEmpty())
			al.add(list.get(i).getText().trim());
		}
		
		return al;
	}
	
}
