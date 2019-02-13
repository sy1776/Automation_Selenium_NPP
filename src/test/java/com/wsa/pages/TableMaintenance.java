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

public class TableMaintenance {
	private By link_epf = By.xpath("//a[@href='/tableMaint.action?category=epf']");
	private By link_ppp = By.xpath("//a[@href='/tableMaint.action?category=ppp']");
	private By link_salesChannels = By.xpath("//a[@href='/tableMaint.action?category=saleschannels']");
	private By link_denominations = By.xpath("//a[@href='/tableMaint.action?category=denominations']");
	private By link_activeLifeValues = By.xpath("//a[@href='/tableMaint.action?category=expirationperiod']");
	private By link_threshold = By.xpath("//a[@href='/tableMaint.action?category=threshold']");
	private By link_vendor = By.xpath("//a[@href='/tableMaint.action?category=vendor']");
	private By link_userRoleRestrn = By.xpath("//a[@href='/tableMaint.action?category=userrolerestrn']");
	private By link_sku = By.xpath("//a[@href='/tableMaint.action?category=sku']");
	private By link_campaignMaint = By.xpath("//a[@href='/maintainCampaignCodes.action']");
	
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
	
	public TableMaintenance(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fGoToEPF() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_epf));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToEPF()|Electronic PIN File Partners link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToEPF()|Could not find Electronic PIN File Partners link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToPPP() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_ppp));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToPPP()|PPP Vendors link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToPPP()|Could not find PPP Vendors link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToSalesChannels() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_salesChannels));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToSalesChannels()|Sales Channels link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToSalesChannels()|Could not find Sales Channels link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToDenominations() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_denominations));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToDenominations()|PIN Denominations link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToDenominations()|Could not find PIN Denominations link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToActiveLifeValues() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_activeLifeValues));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToActiveLifeValues()|PIN Active Life Values link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToActiveLifeValues()|Could not find PIN Active Life Values link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fGoToThresholds() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_threshold));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToPinActiveLifeValues()|Vendor Threshold link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToPinActiveLifeValues()|Could not find Vendor Threshold link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}	
	
	public boolean fGoToVendor() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_vendor));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToPinActiveLifeValues()|Vendor Table link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToPinActiveLifeValues()|Could not find Vendor Table link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}	
	
	public boolean fGoToUserRoleRestrn() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_userRoleRestrn));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToUserRoleRestrn()|User Role Restriction link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToUserRoleRestrn()|Could not find User Role Restriction link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}	
	
	public boolean fGoToSKU() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_sku));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToSKU()|SKU Maintenance link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToSKU()|Could not find SKU Maintenance link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}	
	
	public boolean fGoToCampaignMaint() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		//WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_campaignMaint));
		WebElement e = oDriver.findElement(By.xpath("//a[contains(text(),'Campaign Maintenance')]"));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TableMaintenance|fGoToCampaignMaint()|Campaign Maintenance link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TableMaintenance|fGoToCampaignMaint()|Could not find Campaign Maintenance link link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}	
}
