package com.wsa.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wsa.pages.LoginCSP;
import com.wsa.pages.HomePage;
import com.wsa.pages.TableMaintenance;
import com.wsa.pages.TMepfPartner;
import com.wsa.pages.TMpinActiveLifeValues;
import com.wsa.pages.TMpinDenominations;
import com.wsa.framework.MyTestNGBaseClass;

public class TMpinActiveLifeValuesTest extends MyTestNGBaseClass {
	
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
		Assert.assertTrue(obj2.fGoToActiveLifeValues());
		
		TMpinActiveLifeValues  obj3 = new TMpinActiveLifeValues(oDriver,oExtentReport,oExtentTest,dataMap);
		
		//Assert.assertTrue(obj3.fgoToAddPinActiveLifeValues());
		//Assert.assertTrue(obj3.fAddPinActiveLifeValues());
		//Assert.assertTrue(obj3.fClickEditLink());
		//Assert.assertTrue(obj3.fEditPinActiveLifeValues());
		//Assert.assertTrue(obj3.fClickEditLink());
		//Assert.assertTrue(obj3.fDeletePinActiveLifeValues());
	
		
		if (sAction.equalsIgnoreCase("TMpinActiveLifeValuesTest")) {
			Assert.assertTrue(obj3.fgoToAddPinActiveLifeValues());
			Assert.assertTrue(obj3.fAddPinActiveLifeValues());
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fEditPinActiveLifeValues());
		} else if (sAction.equalsIgnoreCase("TMpinActiveLifeValuesTestDel")) {
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fDeletePinActiveLifeValues());
		
		}
		
	}
}
