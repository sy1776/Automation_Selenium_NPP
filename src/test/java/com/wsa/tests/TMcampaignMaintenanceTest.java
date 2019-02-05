package com.wsa.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wsa.pages.LoginCSP;
import com.wsa.pages.TMcampaignMaintenance;
import com.wsa.pages.HomePage;
import com.wsa.pages.TableMaintenance;
import com.wsa.pages.TMepfPartner;
import com.wsa.pages.TMpinDenominations;
import com.wsa.pages.TMvendorTable;
import com.wsa.framework.MyTestNGBaseClass;

public class TMcampaignMaintenanceTest extends MyTestNGBaseClass {
	
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
		Assert.assertTrue(obj2.fGoToCampaignMaint());
		
		TMcampaignMaintenance  obj3 = new TMcampaignMaintenance(oDriver,oExtentReport,oExtentTest,dataMap);
		
		Assert.assertTrue(obj3.fgoToAddCampaign());
		Assert.assertTrue(obj3.fAddCampaign());
		Assert.assertTrue(obj3.fClickEditLink());
		Assert.assertTrue(obj3.fEditCampaign());
		Assert.assertTrue(obj3.fDeleteCampaign());
	
		/*
		if (sAction.equalsIgnoreCase("TMAddCampaignMaintenance")) {
			Assert.assertTrue(obj3.fgoToAddCampaign());
			Assert.assertTrue(obj3.fAddCampaign());
		} else if (sAction.equalsIgnoreCase("TMEditCampaignMaintenance")) {
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fEditCampaign());
		} else if (sAction.equalsIgnoreCase("TMDeleteCampaignMaintenance")) {
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fDeleteCampaign());
		}
		*/
	}
}
