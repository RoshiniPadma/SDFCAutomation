package SFDCDataDrivenTestingWithHTMLReports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ReUsableMethods {

	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;


	static String fireFoxBrowser;
	static String chromeBrowser;
	static int j;
	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	static HSSFSheet excelsheet;
	/*
	 * Name of the method : repoRead
	 * Brief Description : To read the object repository
	 * Arguments : path -> path of the test data document, sheet -> sheet name of the test data
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void repoRead(String path,String sheet) throws IOException
	{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		excelsheet = workbook.getSheet(sheet);

	}
	/*
	 * Name of the method : getName
	 * Brief Description : To fetch the name of the object
	 * Arguments : row --> row number of the object
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static String getName(int row)
	{
		String name=excelsheet.getRow(row).getCell(0).getStringCellValue();
		return name;
	}


	/*
	 * Name of the method : getLocType
	 * Brief Description : To fetch the locator type of the object
	 * Arguments : row --> row number of the object
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static String getLocType(int row)
	{
		String type=excelsheet.getRow(row).getCell(1).getStringCellValue();
		return type;
	}


	/*
	 * Name of the method : getLocValue
	 * Brief Description : To fetch the locator value of the object
	 * Arguments : row --> row number of the object
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static String getLocValue(int row)
	{
		String val=excelsheet.getRow(row).getCell(2).getStringCellValue();
		return val;
	}


	/*
	 * Name of the method : getBy
	 * Brief Description : To get the locator type phrase
	 * Arguments : type --> locator type, value --> locator value
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static By getBy(String type, String value)
	{

		switch (type)
		{
		case "id":
			return By.id(value);
		case "xpath":
			return By.xpath(value);
		case "className":
			return By.className(value);
		case "name":
			return By.name(value);
		case "linkText":
			return By.linkText(value);
		case "partialLinkText":
			return By.partialLinkText(value);
		case "cssSelector":
			return By.cssSelector(value);
		case "tagName":
			return By.tagName(value);
		default:
			System.out.println("Unknown type");
			return null;

		}
	}

	/*
	 * Name of the method : objDetails
	 * Brief Description : To get the details of the object
	 * Arguments : row --> row number of the object 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static By objDetails(int row)
	{

		String locatortype = getLocType(row);
		String locvalue = getLocValue(row);
		By temp=getBy(locatortype,locvalue);
		return temp;

	}

	/*
	 * Name of the method : startReport
	 * Brief Description : To generate HTML Reports for test cases
	 * Arguments : scriptName -> Name of the report folder, ReportsPath -> Path of the reports folder
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */


	public static void startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;


		String testScriptName =scriptName;
		j=1;

		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}


	/*
	 * Name of the method : Update_Report
	 * Brief Description : To update the result of the test cases in the HTML report
	 * Arguments : Res_type -> Pass/Fail, Action -> Action performed , result -> Detailed description
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);

		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

				+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
				+ result + "</FONT></TD></TR>");

		} 

	}

	/*
	 * Name of the method : inputRead
	 * Brief Description : To read the test data from the excel document
	 * Arguments : path -> path of the test data document, sheet -> sheet name of the test data
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static String[][] inputRead(String path, String sheet) throws IOException {

		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet excelsheet = workbook.getSheet(sheet);

		int rowcount = excelsheet.getLastRowNum() + 1;
		int colcount = excelsheet.getRow(0).getLastCellNum();

		String[][] temp = new String[rowcount][colcount];

		for (int i = 0; i < rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				temp[i][j] = excelsheet.getRow(i).getCell(j).getStringCellValue();
			}
		}


		fis.close();
		return temp;
	}

	/*
	 * Name of the method : enterText
	 * Brief Description : To enter text in the input field
	 * Arguments : elt -> WebElement being handled, input -> Data to be entered, objName -> Name of the element handled
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void enterText(WebElement elt, String input, String objName) throws IOException
	{
		if(elt.isDisplayed())
		{
			elt.sendKeys(input);
			Update_Report("Pass", ("Enter input --> "+objName), ("Input entered in "+objName+ " --> "+input));
		}
		else
		{
			Update_Report("Fail", ("Enter input --> "+objName), ("Enter input Action has failed"));
		}
	}

	/*
	 * Name of the method : clickButton
	 * Brief Description : To click a button
	 * Arguments : elt ->WebElement being handled, objName -> Name of the web element being handled 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void clickButton(WebElement elt,  String objName) throws IOException{

		if(elt.isDisplayed()){
			elt.click();
			Update_Report("Pass", ("Click --> "+objName), (objName+" is clicked "));
		}else{
			Update_Report("Fail", ("Click --> "+objName), (objName+" is not clicked "));
		}

	}

	/*
	 * Name of the method : validateErrMsg
	 * Brief Description : To validate the generated error message against the expected result
	 * Arguments : expectedResult -> The expected error message , testResult -> The generated error message
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */


	public static void validateErrMsg(String expectedResult, String testResult) throws IOException {
		// TODO Auto-generated method stub
		if (expectedResult.equals(testResult))

		{
			Update_Report("Pass", "Validation",( "The error message generated '"+testResult+"' matches with the expected result . Test Case Passed."));
		} else
			Update_Report("Fail", "Validation",( "The error message generated '"+testResult+"' matched with the expected result. Test Case Failed."));


	}



	/*
	 * Name of the method : validateTestResult
	 * Brief Description : To validate the generated test result against the expected result
	 * Arguments : expectedResult -> The expected error message , testResult -> The generated error message
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void validateTestResult(String expectedResult, String testResult) throws IOException {
		// TODO Auto-generated method stub
		if (expectedResult.equals(testResult))

		{
			Update_Report("Pass", "Validation",( "The test result generated '"+testResult+"' matches with the expected result . Test Case Passed."));
		} else
			Update_Report("Fail", "Validation",( "The test result generated '"+testResult+"' matched with the expected result. Test Case Failed."));


	}


}
