package com.wsa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wsa.framework.HashMapNew;

public class HomePage {
	private By link_pinLookup = By.xpath("//a[@href='/pinLookup.action']");
	private By link_createNewOrder = By.xpath("//a[@href='/showOrder.action']");
	private By link_findBatches = By.xpath("//a[@href='/showBatchLocation.action']");
	private By link_tableMaint = By.xpath("//a[@href='/tableMaint.action']");
	private By link_userMaint = By.xpath("//a[@href='/loginMaint.action']");
	private By link_roleMaint = By.xpath("//a[@href='/loginMaintRoles.action']");
	private By link_eventNotification = By.xpath("//a[@href='/eventMaint.action']");
	private By link_systemParam = By.xpath("//a[@href='/appConfiguration.action']");
	private By link_createCoupon = By.xpath("//a[@href='/couponCreation.action']");
	private By link_couponLookup = By.xpath("//a[@href='/couponLookup.action']");
	private By link_campaignCodeMaint = By.xpath("//a[@href='/maintainCampaignCodes.action']");
	
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
	
	public HomePage(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fGoToPinLookup() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_pinLookup));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToPinLookup()|PIN Lookup link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToPinLookup()|Could not find PIN Lookup link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}

	public boolean fGoToCreateNewOrder() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_createNewOrder));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToCreateNewOrder()|Create New Order link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToCreateNewOrder()|Could not find Create New Order link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToFindBatches() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_findBatches));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToFindBatches()|Find Existing PIN Batches link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToFindBatches()|Could not find Find Existing PIN Batches link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToTableMaint() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_tableMaint));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToTableMaint()|Table Maintenance link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToTableMaint()|Could not find Table Maintenance link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToUserMaint() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_userMaint));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToUserMaint()|User Maintenance link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToUserMaint()|Could not find User Maintenance link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToRoleMaint() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_roleMaint));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToRoleMaint()|Role Maintenance link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToRoleMaint()|Could not find Role Maintenance link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToEventNotification() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_eventNotification));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToEventNotification()|Event Notification link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToEventNotification()|Could not find Event Notification link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToSystemParameters() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_systemParam));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToSystemParameters()|System Parameters link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToSystemParameters()|Could not find System Parameters link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToCreateCoupon() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_createCoupon));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToCreateCoupon()|Create Coupon link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToCreateCoupon()|Could not find Create Coupon link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToCouponLookup() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_couponLookup));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToCouponLookup()|Coupon Lookup link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToCouponLookup()|Could not find Coupon Lookup link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToCampaignCodeMaint() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_campaignCodeMaint));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "HomePage|fGoToCampaignCodeMaint()|Campaign Code Maintenance link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "HomePage|fGoToCampaignCodeMaint()|Could not find Campaign Code Maintenance link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
}
