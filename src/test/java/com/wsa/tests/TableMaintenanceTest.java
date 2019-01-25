package com.wsa.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.wsa.pages.LoginCSP;
import com.wsa.pages.HomePage;
import com.wsa.pages.TableMaintenance;
import com.wsa.framework.MyTestNGBaseClass;

public class TableMaintenanceTest extends MyTestNGBaseClass{
	
	@Test	
	public void validation() throws Throwable
	{
		//getData();
		oExtentTest = oExtentReport.startTest("Table Maintenance Test");
		
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
		Assert.assertTrue(obj2.fGoToEPF());
	}
}
