package com.wsa.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

//import AutoHackathon.Project.DataDriver.HashMapNew;
import com.wsa.framework.HashMapNew;


public class DataDriver {
	
	//Variables
		String rootPath;
		String executionPath;
		String datasheetPath;
		String unixlogPath;
		String curExecutionFolder;
		String htmlReportsPath;
		String snapShotsPath;
		String dataSheetsPath;
		String dataSheet;
		String User;	
		String driverType;
		String enviromentsPath;
		String username = System.getProperty("user.name")+"_";
		String oceapiresponsePath;
		String oceapixmlPath;
		String wtReportingPath;

		HashMap <String, String> orgDictionary = new HashMap<String, String>();
		HashMapNew Dictionary = new HashMapNew();
		HashMap <String, String> Environment = new HashMap<String, String>();
		public static ArrayList<Object> arlst = new ArrayList<Object>();
		public static ArrayList<Object> catoList = new ArrayList<Object>();
		public static ArrayList<String> ToWriteList = new ArrayList<String>();
		static HashMap <String,HashMap <String,String>> MultipleDictionary = new HashMap <String,HashMap <String,String>>();
		HashMapNew KeysToWrite = new HashMapNew();
		static DataDriver obj = new DataDriver();

		//dataSheet 
		Sheet Sheet = null;
	
	public static HashMap getData(String TC) {
		System.out.println(AutomationConstants.CALNEDAR_PATH);
		obj.fGetData(AutomationConstants.CALNEDAR_PATH, TC);	
		return MultipleDictionary;
	}
			
	public HashMap fGetData(String calendarFile, String testName){
		
		fGetDataForTest(calendarFile, testName);
		MultipleDictionary.put("Dictionary", Dictionary);
		MultipleDictionary.put("KeysToWrite", KeysToWrite);
		return MultipleDictionary;
	}
	
	public boolean fGetDataForTest(String calendarFile, String testName)
	{
		
		//DataSheet
		final String mainSheet = "MAIN";
		final String testNameColumn = "ACTION";
		//final String testNameColumn = "TEST_NAME";
		final String KeepReferSheet = "KEEP_REFER";
		
		//Clear Dictionary
		Dictionary.clear();
		orgDictionary.clear();
		KeysToWrite.clear();

		//Shin - formatter
		DataFormatter formatter = new DataFormatter();
		
		try{

			//Get column index of test name column
			int iColTestName = fGetColumnIndex(calendarFile, mainSheet, testNameColumn);

			//Iterate through each rows from first sheet
			int iRowCnt = Sheet.getLastRowNum();

			//Loop
			int iRow;
			for(iRow=0;iRow<iRowCnt;iRow++)
			{
				//Get row with test name and exist
				if(Sheet.getRow(iRow).getCell(iColTestName).getStringCellValue().equalsIgnoreCase(testName)) 
					break;
			}

			//Check if test found
			if(iRow == iRowCnt){
				System.out.println("Test with name: " + testName + " not found in datasheet: " +  dataSheet);
				return false;
			}
		
			if(iRow < iRowCnt){ // it will execute only if the test case exists in the excel
				//Retrieve Skip value
				Dictionary.put("SKIP", Sheet.getRow(iRow-1).getCell(fGetColumnIndex(calendarFile, mainSheet, "SKIP")).getStringCellValue());
				if (Dictionary.get("SKIP").isEmpty())
				{
					//Set Header & DataRow
					Row headerRow = Sheet.getRow(iRow-1);
					Row dataRow = Sheet.getRow(iRow);

					//Get Column count for test-1 row
					int iParamCnt = headerRow.getLastCellNum();		 

					String key="";
					String value="";

					//Loop through params
					int iCol;
					for(iCol=0;iCol<iParamCnt;iCol++){

						//Fetch the value for the Header Row
						if (headerRow.getCell(iCol, org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK) == null)
						{
							key = "";
						}else{
							key = headerRow.getCell(iCol).getStringCellValue();
						}

						//Fetch the value for the Header Row
						if (dataRow.getCell(iCol, org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK) == null)
						{
							value = "";
						}else{
							//Shin's change implemented here to retrieve the numeric cell value
							Cell cell = dataRow.getCell(iCol);
							value = formatter.formatCellValue(cell);
							//value = dataRow.getCell(iCol).getStringCellValue();
							//Rahul's changes start here
							if(value!="") {
								if(value.charAt(0) == '&'){
									//Get column index of Key column
									int iColKey = fGetColumnIndex(calendarFile, KeepReferSheet, "KEY");
									//Get column index of Value column
									int iColVal = fGetColumnIndex(calendarFile, KeepReferSheet, "VALUE");
									
									String strKey = value.substring(1);
									int iRowCntKR = Sheet.getLastRowNum();

									//Loop
									int iRowKR;
									for(iRowKR=0;iRowKR<iRowCntKR+1;iRowKR++)
									{
										//Get row with test name and exist
										if(Sheet.getRow(iRowKR).getCell(iColKey).getStringCellValue().equalsIgnoreCase(strKey)) 
											break;
									}
									if(iRowKR == iRowCntKR+1) {
										value = "";
									}else {
										value = Sheet.getRow(iRowKR).getCell(iColVal).getStringCellValue();
									}									
								}else if(value.charAt(0) == '@'){
									KeysToWrite.put(key,value.substring(1));
								}																						
							}
							//Rahul's changes end here						
						}
						
						//Check key value
						Dictionary.put(key,value);
						orgDictionary.put(key,value);
					} 	
				}
			}
			//Retrieve Skip value
			// Dictionary.put("SKIP", sheet.getRow(iRow-1).getCell(fGetColumnIndex(dataSheet, mainSheet, "SKIP_" + driverType)).getStringCellValue());

			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception " + e + " occured while fetching data from datasheet " + dataSheet + " for test " + testName);
			return false;
		}	
		
	}	
	
	public void fWriteKeepRefer(String calendarFile, HashMapNew KeysToWrite, HashMapNew Dictionary) {
		//Added by Rahul to write data in Keep Refer sheet
		Row oRow;
		Cell oCell;
		final String KeepReferSheet = "KEEP_REFER";
		if(!KeysToWrite.isEmpty()) {
			int iKeysCnt = KeysToWrite.size();
			ArrayList<String> ToWriteList = new ArrayList<String>(KeysToWrite.values());
			//ArrayList<String> ArrListKeys = new ArrayList<String>();
			//HashMap <Integer, String> dKeysRows = new HashMap<Integer, String>();
			HashMapNew dKeysRows = new HashMapNew();
			//Get column index of Key column
			int iColKey = fGetColumnIndex(calendarFile, KeepReferSheet, "KEY");
			//Get column index of Value column
			int iColVal = fGetColumnIndex(calendarFile, KeepReferSheet, "VALUE");
			int iRowCnt = Sheet.getLastRowNum();
			int iRow;
			
			try {
				FileInputStream file = new FileInputStream(new File(calendarFile));		     
				//Get the workbook instance for XLS file 
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				Sheet = workbook.getSheet(KeepReferSheet);
				//To store all the keys from Keep Refer in a dictionary
				for(iRow=1;iRow<=iRowCnt;iRow++) {
					//ArrListKeys.add(Sheet.getRow(iRow).getCell(iColKey).getStringCellValue());
					
					Cell cell = Sheet.getRow(iRow).getCell(iColKey, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//To check if the cell is blank to avoid null pointer exception
					if(cell.getCellTypeEnum().name() != "BLANK") {
					dKeysRows.put(Integer.toString(iRow), Sheet.getRow(iRow).getCell(iColKey).getStringCellValue());
					}else {
						continue;
					}
				}
				
				int i;
				String Keyword = null;
				int iRowNum = 0;
				int flag = 0;
				for(i=0;i<iKeysCnt;i++) {
					String sKey = ToWriteList.get(i);
					for (Object o : KeysToWrite.keySet()) {
						if (KeysToWrite.get(o).equals(sKey)) {
							Keyword = o.toString();
						}
					}
					String sValue = Dictionary.get(Keyword);

					//To check if the key exists in KR, and to overwrite it
						for (Object o1 : dKeysRows.keySet()) {
							if (dKeysRows.get(o1).equals(sKey)) {
								//iRowNum = (Integer) o1;
								iRowNum = Integer.valueOf((String) o1);
								oRow = Sheet.getRow(iRowNum);
								oCell = oRow.getCell(iColVal);
								oCell.setCellValue(sValue);
								flag = 1;
							}
						}
						
					//This will be run if the key does not exist in Keep Refer
					if(flag!=1) {
						oRow = Sheet.getRow(iRowCnt+1);
						if(oRow==null) {
							Sheet.createRow(iRowCnt+1);
							oRow = Sheet.getRow(iRowCnt+1);
						}
						oCell = oRow.createCell(iColVal);
						//oCell = oRow.getCell(iColVal);
						oCell.setCellValue(sValue);
						
						oCell = oRow.createCell(iColKey);
						//oCell = oRow.getCell(iColVal);
						oCell.setCellValue(sKey);
					}
				}
				
					FileOutputStream outputStream = new FileOutputStream(calendarFile);
		            workbook.write(outputStream);
		            workbook.close();
		            outputStream.close();
			}
			catch(Exception e){
				System.out.println("Got exception while finding the Index column. Exception is " + e);
			}
		
		}
	}
	
	public int fGetColumnIndex (String strXLSX, String strSheetName, String strColumnName)
	{
		try
		{
			//Create the FileInputStream object			
			FileInputStream file = new FileInputStream(new File(strXLSX));		     
			//Get the workbook instance for XLS file 
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first sheet from the workbook
			Sheet = workbook.getSheet(strSheetName);

			//Iterate through each rows from first sheet
			Row row = Sheet.getRow(0);

			//Get the Column count
			int iColCount = row.getLastCellNum();
			int iCell = 0;
			int iIndex = -1;
			String strTemp = "";

			//Loop through all the columns
			for (iCell = 0; iCell < iColCount; iCell ++)
			{
				//Get the index for Version and Enviornment
				strTemp = Sheet.getRow(0).getCell(iCell).getStringCellValue().trim().toUpperCase();

				//if the strColumnName contains Header then check for HEADER or HEADER_IND
				if (strColumnName.equals("HEADER_IND") || strColumnName.equals("HEADER"))
				{
					if (strTemp.equals("HEADER") || strTemp.equals("HEADER_IND"))
					{
						iIndex = iCell;
						//Exit the Loop
						break;
					}

				}else{ 
					if (strTemp.equals(strColumnName.trim().toUpperCase()))
					{
						iIndex = iCell;
						//Exit the Loop
						break;
					}
				}
			}
			//close the workboox
			workbook.close();
			//Close the file
			file.close();

			//Validate if index is returned properly or not
			if (iIndex != -1)
			{
				return iIndex;

			}else{
				System.out.println("Failed to find the Column Id for Column " + strColumnName);
				return -1;
			}
		}catch (Exception e){
			System.out.println("Got exception while finding the Index column. Exception is " + e);
			return -1;
		}
	}
	
}