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

public class TMskuMaintenance {
	private By link_addSku = By.xpath("//a[@href='/maintainSKU.action']");
	//private By link_edit;
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMskuMaintenance(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddSku() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_addSku));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMskuMaintenance|fgoToAddSku()|Add Sku link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMskuMaintenance|fgoToAddSku()|Could not find Add Sku link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fAddSku() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		
		try {
			
			String strSku = "sku";
			String strDescription = "description";
			String strPinType = "pinType";
			String strPinDenomination = "pinDenomination";
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='maintainSKUForm']"));
			WebElement txt_sku = form.findElement(By.xpath("//tr/td/input[@name='skuID']"));
			txt_sku.clear();
			WebElement txt_description = form.findElement(By.xpath("//tr/td/input[@name='description']"));
			txt_description.clear();
			
			new Select(oDriver.findElement(By.name("pinType"))).selectByVisibleText(dictionary.get(strPinType));
			new Select(oDriver.findElement(By.name("denomination"))).selectByVisibleText(dictionary.get(strPinDenomination));
			
			//fills input control in the form
			txt_sku.sendKeys(dictionary.get(strSku));
			txt_description.sendKeys(dictionary.get(strDescription));		
			
			WebElement button_save = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_save));
			Thread.sleep(7000);
			button_save.click();
			oExtentTest.log(LogStatus.INFO, "TMskuMaintenance|fAddSku()|Added a new sku, " + dictionary.get(strSku));
			Thread.sleep(8000);
			
		} catch (Exception e) {
			System.out.println("TMskuMaintenance|fAddSku()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMskuMaintenance|fAddSku()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{

		//To click on the respective edit link
		try {
			String strSku = "sku";
			String strID = dictionary.get(strSku);
			WebElement form = oDriver.findElement(By.xpath("//form[@id='maintainSKUForm']"));
			String strLink = "//tr/td/a[@href='/maintainSKU.action?updateSKU=" +strID+ "']";
			
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			WebElement link_edit = form.findElement(By.xpath(strLink));
			
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMskuMaintenance|fClickEditLink()|Sku ID: " + strID + " exists. Going to edit");
			}
			else{
				oExtentTest.log(LogStatus.FAIL, "TMskuMaintenance|fClickEditLink()|Sku ID: " + strID + " doesn't exist");
				return false;
			}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMskuMaintenance|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMskuMaintenance|fClickEditLink()|Exception."+ exception);
			return false;
		} 
		
		return true;
	}
	
	public boolean fEditSku() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			String strDescription = "descriptionEdit";
			String strSku = "sku";
			String strID = dictionary.get(strSku);
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='maintainSKUForm']"));
			WebElement txt_description = form.findElement(By.xpath("//tr/td/input[@name='description']"));
			txt_description.clear();
			
			txt_description.sendKeys(dictionary.get(strDescription));
			
			WebElement button_submit = oDriver.findElement(By.name("save"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(5000);
			button_submit.click();
			
			oExtentTest.log(LogStatus.INFO, "TMskuMaintenance|fEditSku()|Edited SKU description : " + strID + ", to " 
							+ dictionary.get(strDescription) + " . ");
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMskuMaintenance|fEditSku()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMskuMaintenance|fEditSku()|Exception." + e);
			return false;
		}
		
		return true;
	}
	
	public boolean fDeleteSku() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 1);
		
		try {
			By button_delete = By.xpath("//button[@name='delete']");
			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(button_delete));
			String strKeyID = "sku";
			
			String strID = dictionary.get(strKeyID);
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMskuMaintenance|fDeleteSku()|Deleting Sku: " + strID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMskuMaintenance|fDeleteSku()|Could not delette Sku: " + strID);
				return false;
			}
			
			//Click delete button
			e.click();
			Thread.sleep(3000);
			//Confirm alert box will be returned
			Alert javascriptConfirm =  oDriver.switchTo().alert();
		    System.out.println(javascriptConfirm.getText()); // Get text on alert box
		    javascriptConfirm.accept(); //click ok
		    Thread.sleep(2000);
		    //write it to the log
		    oExtentTest.log(LogStatus.INFO, "TMskuMaintenance|fDeleteSku()|Deleted the Sku: " + strID);
			
		    
		} catch (Exception exception) {
			System.out.println("TMskuMaintenance|fDeleteSku()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMskuMaintenance|fDeleteSku()|Exception." + exception);
			return false;
		}

		return true;
	}
}
