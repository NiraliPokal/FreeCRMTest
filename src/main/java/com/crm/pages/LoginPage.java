package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class LoginPage extends TestBase{
        
	//Page Factory - OR;
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	WebElement loginButton;
	
	@FindBy(xpath="//span[contains(text(),'Log In')]")
	WebElement loginButtonLink;
	
	@FindBy(xpath="//span[contains(text(),'sign up')]")
	WebElement signUpButton;
	
	//initializing the page objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public HomePage login(String em, String pwd) {
		loginButtonLink.click();
		email.sendKeys(em);
		password.sendKeys(pwd);
		loginButton.click();
		
		return new HomePage();
	}
}
