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

public class TMuserRoleRestriction {
	private By link_AddUserRoleRestriction = By.xpath("//a[@href='/tableUserRoleRestrn.action']");
	//private By link_edit;
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMuserRoleRestriction(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddUserRoleRestriction() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_AddUserRoleRestriction));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMuserRoleRestriction|fgoToAddUserRoleRestriction()|Add user role restriction link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMuserRoleRestriction|fgoToAddUserRoleRestriction()|Could not find user role restriction  link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fAddUserRoleRestriction() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		
		try {
			
			String strRole = "role";
			String strRestrictionType = "restrictionType";
			
			
			//fills input controls in the form
			new Select(oDriver.findElement(By.name("roleID"))).selectByVisibleText(dictionary.get(strRole));
			new Select(oDriver.findElement(By.name("restrictionTypes"))).selectByVisibleText(dictionary.get(strRestrictionType));
	
	
			
		
							
			WebElement button_save = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_save));
			Thread.sleep(5000);
			button_save.click();
			oExtentTest.log(LogStatus.INFO, "TMuserRoleRestriction|fAddUserRoleRestriction()|Added a new user role restriction, " + dictionary.get(strRole) +"," + dictionary.get(strRestrictionType));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMuserRoleRestriction|fAddUserRoleRestriction()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMuserRoleRestriction|fAddUserRoleRestriction()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{
		String strUserRoleID= "16";
	
		
		
		//To click on the respective edit link
		try {
			
			String strUserRole = dictionary.get("userRole");
			String strVendor = dictionary.get("vendor");
			//To create the link key, use to access edit button based on vendor & user role
			//Needs to be added remaining vendor and user roles
			if (strUserRole == "Amdin") {
				 strUserRoleID = "16";
			}else if (strVendor == "User Amdin") {
				strUserRoleID = "2";
			}
			
			String ID = strUserRoleID;
			
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableUserRoleRestrnForm']"));
			String strLink = "//a[@href='/tableUserRoleRestrn.action?updateRoleId=" + ID + "']";
			
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			WebElement link_edit = form.findElement(By.xpath(strLink));
			
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMuserRoleRestriction|fClickEditLink()|User Role ID: " + ID + " exists. Going to edit");
			}
			else{
				oExtentTest.log(LogStatus.FAIL, "TMuserRoleRestriction|fClickEditLink()|User Role ID: " + ID + " doesn't exist");
				return false;
			}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMuserRoleRestriction|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMuserRoleRestriction|fClickEditLink()|Exception."+ exception);
			return false;
		} 
		
		return true;
	}
	
	public boolean fEditUserRoleRestrictions() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			
			String strRestrictionType = "restrictionTypeEdit";
			
			
			//fills input control in the form
			new Select(oDriver.findElement(By.name("restrictionTypes"))).selectByVisibleText(dictionary.get(strRestrictionType));
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			
			oExtentTest.log(LogStatus.INFO, "TMuserRoleRestriction|fEditUserRoleRestrictions()|Edited the restriction type to: " + dictionary.get(strRestrictionType)  
							+ " . ");
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMuserRoleRestriction|fEditUserRoleRestrictions()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMuserRoleRestriction|fEditUserRoleRestrictions()|Exception." + e);
			return false;
		}
		
		return true;
	}
	
	public boolean fDeleteUserRoleRestriction() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 1);
		
		try {
			By button_delete = By.xpath("//button[@name='delete']");
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(button_delete));
			String strKeyID = "role";
			
			String strID = dictionary.get(strKeyID);
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMuserRoleRestriction|fDeleteUserRoleRestriction()|Deleting role: " + strID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMuserRoleRestriction|fDeleteUserRoleRestriction()|Could not delete role: " + strID);
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
		    oExtentTest.log(LogStatus.INFO, "TMuserRoleRestriction|fDeleteUserRoleRestriction()|Deleted the Vendor threshold: " + strID);
			
		    
		} catch (Exception exception) {
			System.out.println("TMuserRoleRestriction|fDeleteUserRoleRestriction()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMuserRoleRestriction|fDeleteUserRoleRestriction()|Exception." + exception);
			return false;
		}

		return true;
	}
}
