package com.wsa.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wsa.pages.LoginCSP;
import com.wsa.pages.HomePage;
import com.wsa.pages.TableMaintenance;
import com.wsa.pages.TMepfPartner;
import com.wsa.pages.TMpinDenominations;
import com.wsa.pages.TMskuMaintenance;
import com.wsa.framework.MyTestNGBaseClass;

public class TMskuMaintenanceTest extends MyTestNGBaseClass {
	
	@Parameters({"Action"})
	@Test	
	public void validation(String sAction) throws Throwable
	{
		//getData();
		oExtentTest = oExtentReport.startTest("Table Maintenance: " + sAction);
		
		//Login
		LoginCSP obj= new LoginCSP(oDriver,oExtentReport,oExtentTest,dataMap);

		//Validate Login page
		Assert.assertTrue(obj.fLogin());
		
		//Home Page
		HomePage obj1 = new HomePage(oDriver,oExtentReport,oExtentTest,dataMap);
		
		//Validate the table maint link in Home Page
		Assert.assertTrue(obj1.fGoToTableMaint());
		
		//Table Maintenance
		TableMaintenance obj2 = new TableMaintenance(oDriver,oExtentReport,oExtentTest,dataMap);
		
		//validate the table maintenance
		Assert.assertTrue(obj2.fGoToSKU());
		
		TMskuMaintenance  obj3 = new TMskuMaintenance(oDriver,oExtentReport,oExtentTest,dataMap);
		
		Assert.assertTrue(obj3.fgoToAddSku());
		Assert.assertTrue(obj3.fAddSku());
		Assert.assertTrue(obj3.fClickEditLink());
		Assert.assertTrue(obj3.fEditSku());
		Assert.assertTrue(obj3.fClickEditLink());
		Assert.assertTrue(obj3.fDeleteSku());
	
		/*
		if (sAction.equalsIgnoreCase("TMAddkuMaintenance")) {
			Assert.assertTrue(obj3.fgoToAddSku());
			Assert.assertTrue(obj3.fAddSku());
		} else if (sAction.equalsIgnoreCase("TMEditkuMaintenance")) {
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fEditSku());
		} else if (sAction.equalsIgnoreCase("TMDeleteSkuMaintenance")) {
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fDeleteSku());
		}
		*/
	}
}
