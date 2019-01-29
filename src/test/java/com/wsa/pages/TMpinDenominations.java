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

public class TMpinDenominations {
	private By link_addPinDenominations = By.xpath("//a[@href='/tableDenom.action']");
	//private By link_edit;
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMpinDenominations(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddPinDenominations() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_addPinDenominations));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMpinDenominations|fgoToAddPinDenominations()|Add Denomination link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMpinDenominations|fgoToAddPinDenominations()|Could not find Add Denomination link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fAddPinDenomination() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		
		try {
			
			String strValue = "value";
			String strActiveLife = "activeLife";
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableDenomForm']"));
			WebElement txt_value = form.findElement(By.xpath("//tr/td/input[@name='denomination']"));
			txt_value.clear();
			new Select(oDriver.findElement(By.name("expirationPeriod"))).selectByVisibleText(dictionary.get(strActiveLife));
			//new Select(form.findElement(By.xpath("//tr/td/select[@name='expirationPeriod']"))).selectByVisibleText(dictionary.get(strActiveLife));
			
			//fills input control in the form
			txt_value.sendKeys(dictionary.get(strValue));
							
			WebElement button_save = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_save));
			Thread.sleep(5000);
			button_save.click();
			oExtentTest.log(LogStatus.INFO, "TMpinDenominations|fAddPinDenominations()|Added a new denomination, " + dictionary.get(strValue));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMpinDenominations|fAddpinDenominations()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMpinDenominations|fAddpinDenominations()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{
		//To go to Pin Denominations screen
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		By link_pinDenomination = By.xpath("//a[contains(@href, 'denominations')]");
		WebElement f = wait.until(ExpectedConditions.visibilityOfElementLocated(link_pinDenomination));
		
		if(f.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMpinDenominations|fClickEditLink()| Denomination link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMpinDenominations|fClickEditLink()|Could not find  Denomination link");
			return false;
		}
		
		//Click on the link
		f.click();
		Thread.sleep(2000);
		
		//To click on the respective edit link
		try {
			String strValue = "value";
			String strID = dictionary.get(strValue);
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableDenomForm']"));
			String strLink = "//a[@href='/tableDenom.action?updateDenomination=" + strID + "']";
			
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			WebElement link_edit = form.findElement(By.xpath(strLink));
			
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMpinDenominations|fClickEditLink()|Denomination ID: " + strID + " exists. Going to edit");
			}
			else{
				oExtentTest.log(LogStatus.FAIL, "TMpinDenominations|fClickEditLink()|Denomination ID: " + strID + " doesn't exist");
				return false;
			}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMpinDenominations|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMpinDenominations|fClickEditLink()|Exception."+ exception);
			return false;
		} 
		
		return true;
	}
	
	public boolean fEditpinDenominations() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			String strValue = "value";
			String strID = dictionary.get(strValue);
			String strActiveLife = "activeLifeEdit";
			
			
			new Select(oDriver.findElement(By.name("expirationPeriod"))).selectByVisibleText(dictionary.get(strActiveLife));
			
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			
			oExtentTest.log(LogStatus.INFO, "TMpinDenomination|fEditPinDenomination()|Edited the active life of  Denomination value : " + strID + ", to " 
							+ strActiveLife + " . ");
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMpinDenomination|fEditPinDenomination()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMpinDenomination|fEditPinDenomination()|Exception." + e);
			return false;
		}
		
		return true;
	}
	
	public boolean fDeletePinDenominations() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 1);
		
		try {
			By button_delete = By.xpath("//button[@name='delete']");
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(button_delete));
			String strKeyID = "valueEdit";
			
			String strID = dictionary.get(strKeyID);
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMpinDenominations|fDeletePinDenominations()|Deleting Pin Denomination: " + strID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMpinDenominations|fDeletePinDenominations()|Could not delette Pin Denomination: " + strID);
				return false;
			}
			
			//Click the delete button
			e.click();
			Thread.sleep(3000);
			//Confirm alert box will be returned
			Alert javascriptConfirm =  oDriver.switchTo().alert();
		    System.out.println(javascriptConfirm.getText()); // Get text on alert box
		    javascriptConfirm.accept(); //click ok
		    Thread.sleep(2000);
		    //write it to the log
		    oExtentTest.log(LogStatus.INFO, "TMpinDenominations|fDeletePinDenominations()|Deleted the Pin Denomination: " + strID);
			
		    
		} catch (Exception exception) {
			System.out.println("TMpinDenominations|fDeletePinDenominations()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMpinDenominations|fDeletePinDenominations()|Exception." + exception);
			return false;
		}

		return true;
	}
}
