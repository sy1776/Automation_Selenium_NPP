package com.wsa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wsa.framework.HashMapNew;

public class TMvendorThreshold {
	private By link_addVendorThreshold = By.xpath("//a[@href='/tableThreshold.action']");
	//private By link_edit;
	
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMvendorThreshold(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddVendorThresholds() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_addVendorThreshold));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMvendorThreshold|fgoToAddVendorThresholds()|Add Vendor Threshold link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMvendorThreshold|fgoToAddVendorThresholds()|Could not find add Vendor  Threshold link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fAddVendorThresholds() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		
		try {
			
			String strUserRole = "userRole";
			String strVendor = "vendor";
			String strHourVolume = "hVolume";
			String strDailyVolume= "dVolume";
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableThresholdForm']"));
			WebElement txt_dVolume = form.findElement(By.xpath("//tr/td/input[@name='volumePerDay']"));
			txt_dVolume.clear();
			
			WebElement txt_hVolume = form.findElement(By.xpath("//tr/td/input[@name='volumePerHour']"));
			txt_hVolume.clear();
			
			
			//fills input controls in the form
			txt_hVolume.sendKeys(dictionary.get(strHourVolume));
			txt_dVolume.sendKeys(dictionary.get(strDailyVolume));
			
			new Select(oDriver.findElement(By.name("roleID"))).selectByVisibleText(dictionary.get(strUserRole));
			new Select(oDriver.findElement(By.name("vendorId"))).selectByVisibleText(dictionary.get(strVendor));
							
			WebElement button_save = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_save));
			Thread.sleep(5000);
			button_save.click();
			oExtentTest.log(LogStatus.INFO, "TMvendorThreshold|fAddVendorThresholds()|Added a new vendor threshold, " + dictionary.get(strUserRole) + dictionary.get(strVendor));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMvendorThreshold|fAddVendorThresholds()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMvendorThreshold|fAddVendorThresholds()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{
		String strUserRoleID= "2-";;
		String strVendorID = "1";;
		
		
		//To click on the respective edit link
		try {
			
			String strUserRole = dictionary.get("userRole");
			String strVendor = dictionary.get("vendor");
			//To create the link key, use to access edit button based on vendor & user role
			//Needs to be added remaining vendor and user roles
			if (strUserRole == "User Amdin") {
				 strUserRoleID = "2-";
			}else if (strVendor == "User Amdin") {
				strVendorID = "1";
			}
			
			String ID = strUserRoleID + strVendorID;
			
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableThresholdForm']"));
			String strLink = "//a[@href='/tableThreshold.action?thresholdKey=" + ID + "']";
			
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			WebElement link_edit = form.findElement(By.xpath(strLink));
			
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMvendorThreshold|fClickEditLink()|Threshold ID: " + ID + " exists. Going to edit");
			}
			else{
				oExtentTest.log(LogStatus.FAIL, "TMvendorThreshold|fClickEditLink()|Threshold ID: " + ID + " doesn't exist");
				return false;
			}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMvendorThreshold|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMvendorThreshold|fClickEditLink()|Exception."+ exception);
			return false;
		} 
		
		return true;
	}
	
	public boolean fEditVendorThresholds() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			
			String strHourVolume = "hVolumeEdit";
			String strDailyVolume= "dVolumeEdit";
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableThresholdForm']"));
			WebElement txt_dVolume = form.findElement(By.xpath("//tr/td/input[@name='volumePerDay']"));
			txt_dVolume.clear();
			
			WebElement txt_hVolume = form.findElement(By.xpath("//tr/td/input[@name='volumePerHour']"));
			txt_hVolume.clear();
			
			
			//fills input control in the form
			txt_hVolume.sendKeys(dictionary.get(strHourVolume));
			txt_dVolume.sendKeys(dictionary.get(strDailyVolume));
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			
			oExtentTest.log(LogStatus.INFO, "TMvendorThreshold|fEditVendorThresholds()|Edited the vendor threshold per hour volume and day volume to: " + dictionary.get(strHourVolume) + ", and " 
							+ dictionary.get(strDailyVolume) + " . ");
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMvendorThreshold|fEditVendorThresholds()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMvendorThreshold|fEditVendorThresholds()|Exception." + e);
			return false;
		}
		
		return true;
	}
	
	public boolean fDeleteVendorThresholds() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 1);
		
		try {
			By button_delete = By.xpath("//button[@name='delete']");
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(button_delete));
			String strKeyID = "vendor";
			
			String strID = dictionary.get(strKeyID);
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMvendorThreshold|fDeleteVendorThresholds()|Deleting Vendor threshold: " + strID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMvendorThreshold|fDeleteVendorThresholds()|Could not delette Vendor threshold: " + strID);
				return false;
			}
			
			//Click the delete button
			e.click();
			Thread.sleep(3000);
			//Confirm alert box will be returned
			Alert javascriptConfirm =  oDriver.switchTo().alert();
		    System.out.println(javascriptConfirm.getText()); // Get text on alert box
		    javascriptConfirm.accept(); //click ok
		    Thread.sleep(12000);
		    //write it to the log
		    oExtentTest.log(LogStatus.INFO, "TMvendorThreshold|fDeleteVendorThresholds()|Deleted the Vendor threshold: " + strID);
			
		    
		} catch (Exception exception) {
			System.out.println("TMvendorThreshold|fDeleteVendorThresholds()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMvendorThreshold|fDeleteVendorThresholds()|Exception." + exception);
			return false;
		}

		return true;
	}
}
