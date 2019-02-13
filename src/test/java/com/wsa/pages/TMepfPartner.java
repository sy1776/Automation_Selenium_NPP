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

public class TMepfPartner {
	private By link_addEpfPartner = By.xpath("//a[@href='/tableEPF.action']");
	//private By link_edit;
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMepfPartner(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddEpfPartner() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_addEpfPartner));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMepfPartner|fgoToEpfPartner()|Add EPF Partner link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMepfPartner|fgoToEpfPartner()|Could not find Add EPF Partner link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fAddEpfPartner() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			String strKeyName = "Name";
			String strKeyDescription = "Description";
			String strKeyAckTimeout = "ackTimeout";
			String strKeyConfTimeout = "confTimeout";
			String strKeyFtpAccount = "ftpAccount";
			String strKeyFtpPassword = "ftpPassword";
			String strKeyIpAddress = "ipAddress";
			String strKeyLocalDirectory = "localDirectory";
			String strKeyRemoteDirectory = "remoteDirectory";
			String strKeyTransferMethod = "transferMethod";
			String strKeyPinType = "pinType";
			String strKeyGpgKeyID = "gpgKeyID";
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableEPFForm']"));
			WebElement txt_name = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyName.toLowerCase() + "']"));
			WebElement textarea_description = form.findElement(By.xpath("//tr/td/textarea[@name='" + strKeyDescription.toLowerCase() + "']"));
			WebElement txt_ackTimeout = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyAckTimeout + "']"));
			WebElement txt_confTimeout = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyConfTimeout + "']"));
			WebElement txt_ftpAccount = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyFtpAccount + "']"));
			WebElement txt_ftpPassword = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyFtpPassword + "']"));
			WebElement txt_ipAddress = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyIpAddress + "']"));
			WebElement txt_localDirectory = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyLocalDirectory + "']"));
			WebElement txt_remoteDirectory = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyRemoteDirectory + "']"));
			WebElement txt_gpgKeyID = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyGpgKeyID + "']"));
			
			//fills out each input controls in the form
			txt_name.sendKeys(dictionary.get(strKeyName));
			textarea_description.sendKeys(dictionary.get(strKeyDescription));
			txt_ackTimeout.sendKeys(dictionary.get(strKeyAckTimeout));
			txt_confTimeout.sendKeys(dictionary.get(strKeyConfTimeout));
			txt_ftpAccount.sendKeys(dictionary.get(strKeyFtpAccount));
			txt_ftpPassword.sendKeys(dictionary.get(strKeyFtpPassword));
			txt_ipAddress.sendKeys(dictionary.get(strKeyIpAddress));
			txt_localDirectory.sendKeys(dictionary.get(strKeyLocalDirectory));
			txt_remoteDirectory.sendKeys(dictionary.get(strKeyRemoteDirectory));
			txt_gpgKeyID.sendKeys(dictionary.get(strKeyGpgKeyID));
			
			new Select(form.findElement(By.xpath("//tr/td/select[@name='" + strKeyTransferMethod + "']"))).selectByVisibleText(dictionary.get(strKeyTransferMethod));
			new Select(form.findElement(By.xpath("//tr/td/select[@name='" + strKeyPinType + "']"))).selectByVisibleText(dictionary.get(strKeyPinType)); 
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			oExtentTest.log(LogStatus.INFO, "TMepfPartner|fAddEpfPartner()|Added a new EPF Partner, " + dictionary.get(strKeyName));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMepfPartner|fAddEpfPartner()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMepfPartner|fAddEpfPartner()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{
		WebElement form = oDriver.findElement(By.xpath("//form[@id='tableEPFForm']"));
		try {
			String strKeyID = "ID";
			String strID = dictionary.get(strKeyID);
			String strLink = "//a[@href='/tableDenom.action?updateDenomination=" + strID + "']";
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			WebElement link_edit = form.findElement(By.xpath(strLink));
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMepfPartner|fClickEditLink()|Record ID: " + strID + " exists. Going to edit");
			}
			//}else{
			//	oExtentTest.log(LogStatus.FAIL, "TMepfPartner|fClickEditLink()|Record ID: " + strID + " doesn't exist");
			//	return false;
			//}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMepfPartner|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMepfPartner|fClickEditLink()|Exception."+ exception);
			return false;
		}
		
		return true;
	}
	
	public boolean fEditEpfPartner() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			String strKeyID = "ID";
			String strKeyName = "Name";
			String strKeyDescription = "Description";
			String strID = dictionary.get(strKeyID);
			String strName = dictionary.get(strKeyName);
			String strDescription = dictionary.get(strKeyDescription);
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableEPFForm']"));
			WebElement txt_name = form.findElement(By.xpath("//tr/td/input[@name='" + strKeyName.toLowerCase() + "']"));
			WebElement textarea_description = form.findElement(By.xpath("//tr/td/textarea[@name='" + strKeyDescription.toLowerCase() + "']"));
			
			//fills out each input controls in the form
			String strOldValue = txt_name.getAttribute("value");
			txt_name.clear();
			textarea_description.clear();
			txt_name.sendKeys(strName);
			textarea_description.sendKeys(strDescription);
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			oExtentTest.log(LogStatus.INFO, "TMepfPartner|fEditEpfPartner()|Edited the EPF Partner ID: " + strID + ", From " 
							+ strOldValue + " To " + strName);
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMepfPartner|fAddEpfPartner()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMepfPartner|fAddEpfPartner()|Exception." + e);
			return false;
		}
		
		return true;
	}
	
	public boolean fDeleteEpfPartner() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 1);
		
		try {
			By button_delete = By.xpath("//button[@name='delete']");
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(button_delete));
			String strKeyID = "ID";
			String strID = dictionary.get(strKeyID);
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMepfPartner|fDeleteEpfPartner()|Deleting EPF Partner ID: " + strID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMepfPartner|fDeleteEpfPartner()|Could not delette EPF Partner ID: " + strID);
				return false;
			}
			
			//Click the delete button
			e.click();
			//Confirm alert box will be returned
			Alert javascriptConfirm =  oDriver.switchTo().alert();
		    System.out.println(javascriptConfirm.getText()); // Get text on alert box
		    javascriptConfirm.accept(); //click ok
		    
		    //write it to the log
		    oExtentTest.log(LogStatus.INFO, "TMepfPartner|fDeleteEpfPartner()|Deleted the EPF Partner ID: " + strID);
			
		    Thread.sleep(2000);
		} catch (Exception exception) {
			System.out.println("TMepfPartner|fDeleteEpfPartner()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMepfPartner|fDeleteEpfPartner()|Exception." + exception);
			return false;
		}

		return true;
	}
}
