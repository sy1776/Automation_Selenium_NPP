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

public class TMvendorTable {
	private By link_addPinDenominations = By.xpath("//a[@href='/tableVendor.action']");
	//private By link_edit;
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMvendorTable(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddVendorTable() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_addPinDenominations));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMvendorTable|fgoToAddVendorTable()|Add Vendor table link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMvendorTable|fgoToAddVendorTable()|Could not find Add Vendor table link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fAddVendorTable() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		
		try {
			
			String strVendor = "vendor";
			String strVendorName = "vendorName";
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableVendorForm']"));
			WebElement txt_vendor = form.findElement(By.xpath("//tr/td/input[@name='vendorID']"));
			txt_vendor.clear();
			WebElement txt_vendorName = form.findElement(By.xpath("//tr/td/input[@name='vendorName']"));
			txt_vendorName.clear();
			
			
			
			//fills input controls in the form
			txt_vendor.sendKeys(dictionary.get(strVendor));
			txt_vendorName.sendKeys(dictionary.get(strVendorName));
							
			WebElement button_save = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_save));
			Thread.sleep(5000);
			button_save.click();
			oExtentTest.log(LogStatus.INFO, "TMvendorTable|fAddVendorTable()|Added a new vendor name, " + dictionary.get(strVendorName));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMvendorTable|fAddVendorTable()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMvendorTable|fAddVendorTable()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{
	
		
		//To click on the respective edit link
		try {
			String strVendor = "vendor";
			String strID = dictionary.get(strVendor);
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableVendorForm']"));
			String strLink = "//a[@href='/tableVendor.action?updateVendorID=" + strID + "']";
			
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			WebElement link_edit = form.findElement(By.xpath(strLink));
			
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMvendorTable|fClickEditLink()|Vendor table ID: " + strID + " exists. Going to edit");
			}
			else{
				oExtentTest.log(LogStatus.FAIL, "TMvendorTable|fClickEditLink()|Vendor table ID: " + strID + " doesn't exist");
				return false;
			}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMvendorTable|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMvendorTable|fClickEditLink()|Exception."+ exception);
			return false;
		} 
		
		return true;
	}
	
	public boolean fEditVendorTable() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			String strVendorNameEdit = "vendorNameEdit";
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableVendorForm']"));
			WebElement txt_vendorName = form.findElement(By.xpath("//tr/td/input[@name='vendorName']"));
			txt_vendorName.clear();
			
			//fills input control in the form
			txt_vendorName.sendKeys(dictionary.get(strVendorNameEdit));
			
			
			
			
			
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			
			oExtentTest.log(LogStatus.INFO, "TMvendorTable|fEditVendorTable()|Edited the Vendor name  to " 
							+ dictionary.get(strVendorNameEdit));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMvendorTable|fEditVendorTable()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMvendorTable|fEditVendorTable()|Exception." + e);
			return false;
		}
		
		return true;
	}
	
	public boolean fDeleteVendorTable() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 1);
		
		try {
			By button_delete = By.xpath("//button[@name='delete']");
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(button_delete));
			String strKeyID = "vendor";
			
			String strID = dictionary.get(strKeyID);
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMvendorTable|fDeleteVendorTable()|Deleting Vendor: " + strID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMvendorTable|fDeleteVendorTable()|Could not delette Vendor: " + strID);
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
		    oExtentTest.log(LogStatus.INFO, "TMvendorTable|fDeleteVendorTable()|Deleted the Pin Denomination: " + strID);
			
		    
		} catch (Exception exception) {
			System.out.println("TMvendorTable|fDeleteVendorTable()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMvendorTable|fDeleteVendorTable()|Exception." + exception);
			return false;
		}

		return true;
	}
}
