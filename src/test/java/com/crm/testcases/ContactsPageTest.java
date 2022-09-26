package com.crm.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
#comment
import com.crm.base.TestBase;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.util.TestUtil;


public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	//TestUtil testUtil;
    ContactsPage contactsPage;
    String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialization();
		//testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
		contactsPage = homePage.clickOnContactsLink();
		//testUtil.switchToFrame();
		}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		//testUtil.switchToFrame();
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page"); 
	}
	
	@Test(priority=2)
	public void selectContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Nir Pokal");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Nir Pokal");
		contactsPage.selectContactsByName("test12 point abion");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContacts(String firstname, String lastname, String company) throws InterruptedException {
		homePage.clickOnNewContactLink();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		//contactsPage.createNewContact("Kirt", "Pokal", "Dmart");
		contactsPage.createNewContact(firstname, lastname, company);
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
