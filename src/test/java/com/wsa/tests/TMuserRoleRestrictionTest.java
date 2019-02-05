package com.wsa.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wsa.pages.LoginCSP;
import com.wsa.pages.HomePage;
import com.wsa.pages.TableMaintenance;
import com.wsa.pages.TMepfPartner;
import com.wsa.pages.TMpinDenominations;
import com.wsa.pages.TMuserRoleRestriction;
import com.wsa.pages.TMvendorThreshold;
import com.wsa.framework.MyTestNGBaseClass;

public class TMuserRoleRestrictionTest extends MyTestNGBaseClass {
	
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
		Assert.assertTrue(obj2.fGoToUserRoleRestrn());
		
		TMuserRoleRestriction  obj3 = new TMuserRoleRestriction(oDriver,oExtentReport,oExtentTest,dataMap);
		
		Assert.assertTrue(obj3.fgoToAddUserRoleRestriction());
		Assert.assertTrue(obj3.fAddUserRoleRestriction());
		Assert.assertTrue(obj3.fClickEditLink());
		Assert.assertTrue(obj3.fEditUserRoleRestrictions());
		Assert.assertTrue(obj3.fClickEditLink());
		Assert.assertTrue(obj3.fDeleteUserRoleRestriction());
	
		/*
		if (sAction.equalsIgnoreCase("TMAddEpfPartnerTest")) {
			Assert.assertTrue(obj3.fgoToAddEpfPartner());
			Assert.assertTrue(obj3.fAddEpfPartner());
		} else if (sAction.equalsIgnoreCase("TMDeleteEpfPartnerTest")) {
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fDeleteEpfPartner());
		} else if (sAction.equalsIgnoreCase("TMEditEpfPartnerTest")) {
			Assert.assertTrue(obj3.fClickEditLink());
			Assert.assertTrue(obj3.fEditEpfPartner());
		}
		*/
	}
}
