package com.wsa.framework;

import java.util.HashMap;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.wsa.framework.HashMapNew;
import com.wsa.framework.DataDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class MyTestNGBaseClass {
	
	public WebDriver oDriver;
	public static ExtentReports oExtentReport;
	public static ExtentTest oExtentTest;

	DataDriver oDataDriver = new DataDriver();
	HashMap<String, HashMap<String, String>> myMap = new HashMap<String, HashMap<String,String>>();
	//public HashMap<String, String> dataMap = new HashMap<String, String>();
	//public HashMap<String, String> keysMap = new HashMap<String, String>();
	protected static HashMapNew dataMap = new HashMapNew();
	protected static HashMapNew keysMap = new HashMapNew();

	@BeforeSuite
	public void BeforeSuite() throws Throwable{
		oExtentReport = new ExtentReports("Reports/TestSuiteReport.html", true);
		oExtentReport.loadConfig(new File("config.xml"));
	}
	
	//********************************************************
	//preconditions
	//********************************************************
	@Parameters({"BrowserToOpen","AppUrl", "Action"})
	@BeforeClass
	public void automationSetup(String sBrowserName,String sUrl, String sAction) throws Exception
	{

		//myMap = oDataDriver.getData();
		//To fetch input data from calendar 
		//myMap = DataDriver.getData(this.getClass().getSimpleName().toString());
		myMap = DataDriver.getData(sAction);
		//dataMap has final input data
		dataMap = fConvertHashToHashNew(myMap.get("Dictionary"));
		//keysMap has keywords to be written in KR
		keysMap = fConvertHashToHashNew(myMap.get("KeysToWrite"));
		
		oDriver = CommonLib.getDriver(sBrowserName);
		//oDriver = CommonLib.getRemoteDriver("http://192.168.228.2:4444/wd/hub", sBrowserName);
		oDriver.get(sUrl);
		Thread.sleep(6000);
	}

	//********************************************************
	//End of execution
	//********************************************************
	@AfterClass
	public void automationTeardown() throws Exception
	{
		oDriver.quit();
		//DataDriver oDataDriver = new DataDriver();
		//oDataDriver.fWriteKeepRefer(AutomationConstants.CALNEDAR_PATH, keysMap, dataMap);
	}
	
	@AfterSuite
	public void afterSuite()  throws Throwable{
		oExtentReport.endTest(oExtentTest);
		oExtentReport.flush();
	}


	public static HashMapNew fConvertHashToHashNew(HashMap<String, String> hashOld) {
		
		try {
			HashMapNew hashNew = (HashMapNew)hashOld;
			return hashNew;
		}
		catch(Exception e){
			return null;
		}
		
		
	}
	
}