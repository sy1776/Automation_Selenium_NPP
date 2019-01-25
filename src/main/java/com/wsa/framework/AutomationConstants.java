package com.wsa.framework;

public class AutomationConstants 

{
	
	public static String sIEDriverPath      	= "/Browsers/IEDriverServer.exe";
	public static String sChromeDriverPath  	= "/Browsers/chromedriver.exe";
	public static String sGeckoDriverPath   	= "/Browsers/geckodriver.exe";
	
	public static String sProxyHostName 		= "genproxy.amdocs.com";
	public static int iProxyPort 				= 8080;
	
	public static long lngPageLoadTimeout 		= 60L;
	public static long lngImplicitWaitTimeout	= 30L;
	
	public static String TEST_DATA_PATH = System.getProperty("user.dir")+ "\\TestData";
	public static String CALENDAR = "Calendar.xlsx";
	public static String CALNEDAR_PATH = TEST_DATA_PATH + "\\" + CALENDAR;
	 
}
