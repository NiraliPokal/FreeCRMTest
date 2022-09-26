package com.crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.base.TestBase;

public class ContactsPage extends TestBase{

	Actions actions = new Actions(driver);

	@FindBy(xpath = "//div[@class='ui header item mb5 light-black']")
	WebElement contactsLabel;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]"
			+ "/div[2]/div[1]/div[2]/form[1]"
			+ "/div[2]/div[2]/div[1]/div[1]/input[1]")
	WebElement companyName;
	
	@FindBy(xpath = "//i[@class='save icon']")
	WebElement savebutton;
	
	//Initializing the Page Objects;
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td//following-sibling::td[3]"));
		actions.moveToElement(element).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td//preceding-sibling::td/div")).click();
		}
	
	public void createNewContact(String ftname, String ltname, String comp) throws InterruptedException {
		
		Actions action = new Actions(driver);
		//action.moveToElement(lastName);
		Thread.sleep(2000);
		action.moveToElement(companyName);
		
		
		firstName.sendKeys(ftname);
		lastName.sendKeys(ltname);
		companyName.sendKeys(comp);
		savebutton.click();
	}
}

