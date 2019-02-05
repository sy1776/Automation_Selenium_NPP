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

public class TMpinActiveLifeValues {
	private By link_addActiveLifeValues = By.xpath("//a[@href='/tableExpPeriod.action']");
	//private By link_edit;
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMpinActiveLifeValues(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddPinActiveLifeValues() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_addActiveLifeValues));
		
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
	
	public boolean fAddPinActiveLifeValues() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		
		try {
			
			
			String strActiveLife = "activeLife";
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableExpPeriodForm']"));
			WebElement txt_activeLife = form.findElement(By.xpath("//tr/td/input[@name='expirationPeriod']"));
			txt_activeLife.clear();
			
			//fills input control in the form
			txt_activeLife.sendKeys(dictionary.get(strActiveLife));
							
			WebElement button_save = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_save));
			Thread.sleep(5000);
			button_save.click();
			oExtentTest.log(LogStatus.INFO, "TMpinActiveLifeValues|fAddPinActiveLifeValues()|Added a new active life value, " + dictionary.get(strActiveLife));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMpinActiveLifeValues|fAddPinActiveLifeValues()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMpinActiveLifeValues|fAddPinActiveLifeValues()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{
		//To click on the respective edit link
		try {
			String strActiveLife = "activeLife";
			String strID = dictionary.get(strActiveLife);
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableExpPeriodForm']"));
			String strLink = "//a[@href='/tableExpPeriod.action?updateExpirationPeriod=" + strID + "']";
			
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			WebElement link_edit = form.findElement(By.xpath(strLink));
			
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMpinActiveLifeValues|fClickEditLink()|Active life ID: " + strID + " exists. Going to edit");
			}
			else{
				oExtentTest.log(LogStatus.FAIL, "TMpinActiveLifeValues|fClickEditLink()|Active life ID: " + strID + " doesn't exist");
				return false;
			}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMpinActiveLifeValues|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMpinActiveLifeValues|fClickEditLink()|Exception."+ exception);
			return false;
		} 
		
		return true;
	}
	
	public boolean fEditPinActiveLifeValues() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			String strActiveLife = "activeLifeEdit";
			String strActiveLife2 = "activeLife";
			String strID = dictionary.get(strActiveLife);
			String strID2 = dictionary.get(strActiveLife2);
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='tableExpPeriodForm']"));
			WebElement txt_activeLife = form.findElement(By.xpath("//tr/td/input[@name='expirationPeriod']"));
			txt_activeLife.clear();
			txt_activeLife.sendKeys(dictionary.get(strActiveLife));
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			
			oExtentTest.log(LogStatus.INFO, "TMpinActiveLifeValues|fEditPinActiveLifeValues()|Edited the pin active life from: " + strID2 + ", to " 
							+ strID + " . ");
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMpinActiveLifeValues|fEditPinActiveLifeValues()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMpinActiveLifeValues|fEditPinActiveLifeValues()|Exception." + e);
			return false;
		}
		
		return true;
	}
	
	public boolean fDeletePinActiveLifeValues() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 1);
		
		try {
			By button_delete = By.xpath("//button[@name='delete']");
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(button_delete));
			
			String strKeyID = "activeLifeEdit";
			String strID = dictionary.get(strKeyID);
			
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMpinActiveLifeValues|fDeletePinActiveLifeValues()|Deleting pin active life value: " + strID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMpinActiveLifeValues|fDeletePinActiveLifeValues()|Could not delete pin active life value: " + strID);
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
		    oExtentTest.log(LogStatus.INFO, "TMpinActiveLifeValues|fDeletePinActiveLifeValues()|Deleted pin active life value: " + strID);
			
		    
		} catch (Exception exception) {
			System.out.println("TMpinActiveLifeValues|fDeletePinActiveLifeValues()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMpinActiveLifeValues|fDeletePinActiveLifeValues()|Exception." + exception);
			return false;
		}

		return true;
	}
}
