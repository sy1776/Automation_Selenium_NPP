package com.wsa.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.wsa.pages.LoginCSP;
import com.wsa.framework.MyTestNGBaseClass;

public class LoginCSPTest extends MyTestNGBaseClass{
	
	@Test	
	public void validation() throws Throwable
	{
		//getData();
		oExtentTest = oExtentReport.startTest("Login CSP Test");
		
		//Validate home page
		LoginCSP obj= new LoginCSP(oDriver,oExtentReport,oExtentTest,dataMap);

		//Login
		Assert.assertTrue(obj.fLogin());
		
	}
}