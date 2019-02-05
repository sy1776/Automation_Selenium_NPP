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

public class TMcampaignMaintenance {
	private By link_addCampaign = By.xpath("//button[@formaction='/addCampaignCodes.action']");
	//private By link_edit;
	public WebDriver oDriver;
	public ExtentReports oExtentReports;
	public ExtentTest oExtentTest;
	public HashMapNew dictionary;
		
	
	public TMcampaignMaintenance(WebDriver oDriver, ExtentReports oExtentReports, ExtentTest oExtentTest, HashMapNew dictionary) {
		this.oDriver = oDriver;
		this.oExtentReports = oExtentReports;
		this.oExtentTest = oExtentTest;
		this.dictionary = dictionary;
	}
	
	public boolean fgoToAddCampaign() throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oDriver, 20);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(link_addCampaign));
		
		if(e.isDisplayed()){
			oExtentTest.log(LogStatus.INFO, "TMcampaignMaintenance|fgoToAddCampaign()|Add Campaign link is visible");
		}else{
			oExtentTest.log(LogStatus.FAIL, "TMcampaignMaintenance|fgoToAddCampaign()|Could not find Add Campaign link");
			return false;
		}
		
		//Click on the link
		e.click();
		Thread.sleep(2000);

		return true;
	}
	
	public boolean fAddCampaign() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		
		try {
			
			String strCampaignCode = "campaignCode";
			String strCampaignDescription = "campaignDescription";
			
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='maintainCampaignCodesForm']"));
			WebElement txt_CampaignCode = form.findElement(By.xpath("//tr/td/input[@name='addCampaignCode']"));
			txt_CampaignCode.clear();
			WebElement txt_CampaignDescription = form.findElement(By.xpath("//tr/td/input[@name='addCampaignDesc']"));
			txt_CampaignDescription.clear();
			
			
			
			//fills input control in the form
			txt_CampaignCode.sendKeys(dictionary.get(strCampaignCode));
			txt_CampaignDescription.sendKeys(dictionary.get(strCampaignDescription));
							
			WebElement button_save = oDriver.findElement(By.xpath("//tr/td/button[contains(@formaction, 'addSave')]"));
			
			wait.until(ExpectedConditions.elementToBeClickable(button_save));
			Thread.sleep(4000);
			button_save.click();
			Thread.sleep(4000);
			oExtentTest.log(LogStatus.INFO, "TMcampaignMaintenance|fAddCampaign()|Added a new campaign, " + dictionary.get(strCampaignCode));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMcampaignMaintenance|fAddCampaign()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMcampaignMaintenance|fAddCampaign()|Exception." + e);
			return false;
		}
		return true;
	}
	
	public boolean fClickEditLink() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		//To click on the respective edit link
		try {
			String strCampaignCode = "campaignCode";
			String strCampaignDescription = "campaignDescription";
			String strCampaignCodeID = dictionary.get(strCampaignCode);
			String strCampaignDescriptionID = dictionary.get(strCampaignDescription);
			
			WebElement tableCampaignCodes = oDriver.findElement(By.xpath("//form[@id='maintainCampaignCodesForm']"));
			//WebElement txt_ticketNum = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr/td//following-sibling::td/input[contains(@id, 'TICKET_NUMBER')]
			WebElement link_edit = tableCampaignCodes.findElement(By.xpath("//tr/input[contains(@value, '" + strCampaignCodeID + "')]" 
			+ "//following-sibling::td/button[contains(@value, 'edit')]")); 
							
		
			
			//if edit link is not found due to non-existed ID provided in Calendar file, it will hit the exception. 
			//It will return the exception with 'NoSuchElementException.
			
			
			
			if(link_edit.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMcampaignMaintenance|fClickEditLink()|Campaign ID: " + strCampaignCodeID + " exists. Going to edit");
			}
			else{
				oExtentTest.log(LogStatus.FAIL, "TMcampaignMaintenance|fClickEditLink()|Campaign ID: " + strCampaignCodeID + " doesn't exist");
				return false;
			}
			
			//Click on the link
			link_edit.click();
			Thread.sleep(2000);
	
		} catch (Exception exception) {
			System.out.println("TMcampaignMaintenance|fClickEditLink()|Exception." + exception);
			oExtentTest.log(LogStatus.FAIL, "TMcampaignMaintenance|fClickEditLink()|Exception."+ exception);
			return false;
		} 
		
		return true;
	}
	
	public boolean fEditCampaign() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(oDriver, 20);
		
		try {
			String strcampaignDescriptionEdit = "campaignDescriptionEdit";
			
			WebElement form = oDriver.findElement(By.xpath("//form[@id='maintainCampaignCodesForm']"));
			WebElement txt_campaignDescription = form.findElement(By.xpath("//tr/td/input[@name='campaignDesc']"));
			txt_campaignDescription.clear();
			
			//fills input control in the form
			txt_campaignDescription.sendKeys(dictionary.get(strcampaignDescriptionEdit));
			
			
			
			
			
			
			WebElement button_submit = oDriver.findElement(By.name("update"));
			wait.until(ExpectedConditions.elementToBeClickable(button_submit));
			Thread.sleep(4000);
			button_submit.click();
			Thread.sleep(4000);
			oExtentTest.log(LogStatus.INFO, "TMcampaignMaintenance|fEditCampaign()|Edited the Campaign description  to " 
							+ dictionary.get(txt_campaignDescription));
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println("TMcampaignMaintenance|fEditCampaign()|Exception: " + e);
			oExtentTest.log(LogStatus.FAIL, "TMcampaignMaintenance|fEditCampaign()|Exception." + e);
			return false;
		}
		
		return true;
	}

	public boolean fDeleteCampaign() throws InterruptedException{
	
		
		try {
			String strCampaignCode = "campaignCode";
			String strCampaignDescription = "campaignDescription";
			String strCampaignCodeID = dictionary.get(strCampaignCode);
			String strCampaignDescriptionID = dictionary.get(strCampaignDescription);
			
			WebElement tableCampaignCodes = oDriver.findElement(By.xpath("//form[@id='maintainCampaignCodesForm']"));
			WebElement e = tableCampaignCodes.findElement(By.xpath("//tr/input[contains(@value, '" + strCampaignCodeID + "')]" 
			+ "//following-sibling::td/button[contains(@value, 'delete')]")); 
			
		
			if(e.isDisplayed()){
				oExtentTest.log(LogStatus.INFO, "TMcampaignMaintenance|fDeleteCampaign()|Deleting campaign: " + strCampaignCodeID);
			}else{
				oExtentTest.log(LogStatus.FAIL, "TMcampaignMaintenance|fDeleteCampaign()|Could not delette campaign: " + strCampaignCodeID);
				return false;
			}
			
			//Click the delete button
			e.click();
			Thread.sleep(5000);
			//Confirm alert box will be returned
			Alert javascriptConfirm =  oDriver.switchTo().alert();
		    System.out.println(javascriptConfirm.getText()); // Get text on alert box
		    javascriptConfirm.accept(); //click ok
		    Thread.sleep(12000);
		    //write it to the log
		    oExtentTest.log(LogStatus.INFO, "TMcampaignMaintenance|fDeleteCampaign()|Deleted the Campaign: " + strCampaignCodeID);
			
		    
		} catch (Exception exception) {
			System.out.println("TMcampaignMaintenance|fDeleteCampaign()|Exception: " + exception);
			oExtentTest.log(LogStatus.FAIL, "TMcampaignMaintenance|fDeleteCampaign()|Exception." + exception);
			return false;
		}

		return true;
	}
}
