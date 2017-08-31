package SFDCDataDrivenTestingWithHTMLReports;

import java.awt.AWTException;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AutomationScripts extends ReUsableMethods{


	static WebDriver driver;
	static By byVar;

	/*
	 * Name of the method : testCase1
	 * Brief Description : SFDC Login with correct user name and no password
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase1() throws IOException
	{
		startReport("Test Case 1","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");

		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 1.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet="Sheet1";


		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String userName = exceldata[1][2];
		String password = exceldata[1][3];
		String expectedResult = exceldata[1][4];

		initializeDriver(URL);

		WebElement uname, pwd, login;


		By byVar = objDetails(1);//user name field		
		uname = driver.findElement(byVar);
		enterText(uname, userName, getName(1));

		byVar = objDetails(2);//Password field		
		pwd = driver.findElement(byVar);
		enterText(pwd, password, getName(2));

		byVar = objDetails(3);//Login button		
		login = driver.findElement(byVar);
		clickButton(login, getName(3));

		byVar=objDetails(4);//Login error message
		String testResult = driver.findElement(byVar).getText();

		validateErrMsg(expectedResult, testResult);
		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase2
	 * Brief Description : SFDC Login with correct user name and correct password
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase2() throws IOException
	{
		startReport("Test Case 2","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");

		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 2.xls";
		String sheetname = "Sheet1";



		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];

		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet="Sheet1";		
		repoRead(repopath,reposheet);

		initializeDriver(url);

		WebElement uname, pwd, login;

		byVar = objDetails(1);//user name field		
		uname = driver.findElement(byVar);
		enterText(uname, username, getName(1));

		byVar = objDetails(2);//password field		
		pwd = driver.findElement(byVar);
		enterText(pwd, password, getName(2));

		byVar = objDetails(3);//Login button		
		login = driver.findElement(byVar);
		clickButton(login, getName(3));

		byVar=objDetails(5);//User navigation menu
		WebElement usermenu = driver.findElement(byVar);
		if(usermenu.isDisplayed()==true)
		{
			Update_Report("Pass", "Login Action", "Login Successful");
		}
		else
		{
			Update_Report("Fail", "Login Action", "Login Not Successful");
		}

		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase3
	 * Brief Description : SFDC Login -> Remember Me Checkbox
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase3() throws IOException, InterruptedException
	{
		startReport("Test Case 3","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");

		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 3.xls";
		String sheetname = "Sheet1";
		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];

		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet="Sheet1";		
		repoRead(repopath,reposheet);

		initializeDriver(url);

		WebElement uname, pwd, login, remember, usermenu, logout;

		byVar = objDetails(1);//user name field		
		uname = driver.findElement(byVar);
		enterText(uname, username, getName(1));

		byVar = objDetails(2);//password field		
		pwd = driver.findElement(byVar);
		enterText(pwd, password, getName(2));

		byVar=objDetails(6);//Remember me checkbox
		remember = driver.findElement(byVar);
		if (remember.isSelected() == false)
			clickButton(remember, getName(6));

		byVar = objDetails(3);//Login button		
		login = driver.findElement(byVar);
		clickButton(login, getName(3));

		byVar = objDetails(5);//User navigation menu
		usermenu = driver.findElement(byVar);
		clickButton(usermenu,getName(5));

		Thread.sleep(2000);
		byVar = objDetails(7);//Logout 
		logout = driver.findElement(byVar);
		clickButton(logout, getName(7));

		byVar = objDetails(8);//Saved username
		WebElement elt=driver.findElement(byVar);
		if (elt.isDisplayed() == true) {
			Update_Report("Pass", "Remember Me", "Saved Username present");
		} else {
			Update_Report("Fail", "Remember Me", "Saved Username not present");
		}

		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase4A
	 * Brief Description : SFDC Login -> Forgot your password? link
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase4A() throws IOException, InterruptedException
	{
		startReport("Test Case 4A","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");

		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 4A.xls";
		String sheetname = "Sheet1";
		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String expectedResult=exceldata[1][3];

		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet="Sheet1";
		repoRead(repopath,reposheet);

		initializeDriver(url);

		WebElement forgot, uname, submit;

		byVar=objDetails(9);//Forgot password link
		forgot = driver.findElement(byVar);
		clickButton(forgot, getName(9));
		Thread.sleep(2000);

		byVar=objDetails(10);//recovery user name
		uname = driver.findElement(byVar);
		enterText(uname, username, getName(10));
		Thread.sleep(2000);

		byVar=objDetails(11);//Continue
		submit = driver.findElement(byVar);
		clickButton(submit,getName(11));
		Thread.sleep(2000);

		byVar=objDetails(12);//Email Confirmation
		String testResult=driver.findElement(byVar).getText();
		validateTestResult(expectedResult, testResult);
		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase4B
	 * Brief Description : SFDC Login -> Wrong user name and wrong password
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase4B() throws IOException

	{
		startReport("Test Case 4B","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 4B.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url=exceldata[1][1];
		String username=exceldata[1][2];
		String password=exceldata[1][3];
		String expectedResult=exceldata[1][4];

		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet="Sheet1";
		repoRead(repopath,reposheet);

		initializeDriver(url);


		userLogin(username,password);

		byVar=objDetails(4);//Login Error Message
		String testResult = driver.findElement(byVar).getText();
		validateErrMsg(expectedResult, testResult);
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase5
	 * Brief Description : SFDC Login -> Wrong user name and wrong password
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase5() throws IOException, InterruptedException
	{
		startReport("Test Case 5",
				"C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 5.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String[] alloptions = exceldata[1][4].split(",");

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		WebElement usermenu;

		userLogin(username, password);

		byVar = objDetails(5);// User Nav Button
		usermenu = driver.findElement(byVar);
		clickButton(usermenu, getName(5));

		Thread.sleep(3000);

		byVar = objDetails(13);// user navigation menu
		List<WebElement> al = driver.findElement(byVar).findElements(By.tagName("a"));
		int count = al.size();
		WebElement elt;
		ArrayList<String> opts = new ArrayList<String>();
		for (int i = 1; i <= count; i++) {
			elt = driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[" + i + "]"));
			opts.add(elt.getText());
			validateTestResult(alloptions[i - 1], opts.get(i - 1));

		}

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase6
	 * Brief Description : SFDC Login -> My profile option in User Menu
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase6() throws IOException, InterruptedException, AWTException
	{
		startReport("Test Case 6","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 6.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String lastname=exceldata[1][4];
		String post=exceldata[1][5];
		String fname=exceldata[1][6];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		WebElement usermenu, profile, editProfile, aboutTab, lastName, save;

		userLogin(username, password);

		byVar=objDetails(5);//user navigation button
		usermenu = driver.findElement(byVar);
		clickButton(usermenu,getName(5));

		byVar=objDetails(14);//my profile option
		profile=driver.findElement(byVar);
		clickButton(profile, getName(14));
		
		byVar=objDetails(15);// Edit profile button
		editProfile=driver.findElement(byVar);
		clickButton(editProfile	,getName(15));

		byVar=objDetails(16);//iframe 
		WebElement iframe=driver.findElement(byVar);

		driver.switchTo().frame(iframe);
		byVar=objDetails(17);//about tab
		aboutTab=driver.findElement(byVar);
		clickButton(aboutTab, getName(17));

		byVar=objDetails(18);//last name field
		lastName=driver.findElement(byVar);
		lastName.clear();
		enterText(lastName, lastname, getName(18));

		byVar=objDetails(19);//save all button
		save=driver.findElement(byVar);
		clickButton(save, getName(19));
		
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		
		byVar=objDetails(20);//post text area
		enterText(driver.findElement(byVar), post, getName(20));
		Thread.sleep(3000);
		
		byVar=objDetails(21);//share button
		clickButton(driver.findElement(byVar), getName(21));
		Thread.sleep(5000);
		
		byVar=objDetails(30);//posted texts
		ArrayList<WebElement> al=(ArrayList<WebElement>) driver.findElements(byVar);
		validateTestResult(al.get(0).getText(),post);
		
		byVar=objDetails(22);//file link
		clickButton(driver.findElement(byVar), getName(22));
		Thread.sleep(2000);
		
		byVar=objDetails(23);//upload button
		clickButton(driver.findElement(byVar), getName(23));
		Thread.sleep(3000);
		
		byVar=objDetails(24);//browse button
		clickButton(driver.findElement(byVar), getName(24));
		Thread.sleep(2000);
		
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_A);
		r.keyPress(KeyEvent.VK_B);
		r.keyPress(KeyEvent.VK_C);
		r.keyPress(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_T);
		r.keyPress(KeyEvent.VK_X);
		r.keyPress(KeyEvent.VK_T);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);	
		r.keyRelease(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_B);
		r.keyRelease(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_PERIOD);
		r.keyRelease(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_X);
		r.keyRelease(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_ENTER);	
		
		driver.switchTo().defaultContent();
		
		byVar=objDetails(21);//share button
		clickButton(driver.findElement(byVar), getName(21));

		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		byVar=objDetails(31);//overview page
		clickButton(driver.findElement(byVar), getLocType(31));
		
		byVar=objDetails(32);//recent files
		ArrayList<WebElement> recent=(ArrayList<WebElement>) driver.findElements(byVar);
		String topfile=recent.get(0).getText();
		
		validateTestResult(fname, topfile);
		
		/*byVar=objDetails(25);//upload profile photo
		WebElement moderator=driver.findElement(byVar);
		Actions act=new Actions(driver);
		act.moveToElement(moderator).build().perform();
		clickButton(driver.findElement(byVar), getName(25));
		
		byVar=objDetails(26);//iframe for profile photo upload
		WebElement iframe2=driver.findElement(byVar);
		driver.switchTo().frame(iframe2);
		
		byVar=objDetails(27);//browse button for profile photo
		clickButton(driver.findElement(byVar), getName(27));
		Thread.sleep(3000);
		
		r.keyPress(KeyEvent.VK_A);
		r.keyPress(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_P);
		r.keyPress(KeyEvent.VK_N);
		r.keyPress(KeyEvent.VK_G);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_ENTER);	
		Thread.sleep(3000);
		
		r.keyRelease(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_PERIOD);
		r.keyRelease(KeyEvent.VK_P);
		r.keyRelease(KeyEvent.VK_N);
		r.keyRelease(KeyEvent.VK_G);
		r.keyRelease(KeyEvent.VK_ENTER);	
		
		byVar=objDetails(28);//save button for profile photo
		clickButton(driver.findElement(byVar), getName(28));
		Thread.sleep(3000);
		
		byVar=objDetails(26);//iframe for profile photo upload
		iframe2=driver.findElement(byVar);
		driver.switchTo().frame(iframe2);
		
		byVar=objDetails(29);//save button for cropping
		clickButton(driver.findElement(byVar), getName(29));
		Thread.sleep(3000);*/
		
		bw.close();
		driver.close();

	}


	/*
	 * Name of the method : testCase7
	 * Brief Description : SFDC Login -> My settings option in User Menu
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase7() throws IOException, AWTException, InterruptedException
	{
		startReport("Test Case 7","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 7.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String emailname=exceldata[1][4];
		String emailaddr=exceldata[1][5];
		String dropval=exceldata[1][6];
		String expectedResult=exceldata[1][7];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);
		WebElement usermenu, settings;

		userLogin(username, password);

		byVar=objDetails(5);//user nav button
		usermenu = driver.findElement(byVar);
		clickButton(usermenu,getName(5));

		byVar=objDetails(33);//settings tab
		settings=driver.findElement(byVar);
		clickButton(settings, getName(33));
		
		byVar=objDetails(34);//personal link
		clickButton(driver.findElement(byVar),getName(34));
		
		byVar=objDetails(35);//login history link
		clickButton(driver.findElement(byVar),getName(35));
		
		byVar=objDetails(36);//download login history link
		clickButton(driver.findElement(byVar),getName(36));
		
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);

		byVar=objDetails(37);//display and layout
		clickButton(driver.findElement(byVar), getName(37));
		
		byVar=objDetails(38);//customize my tabs
		clickButton(driver.findElement(byVar), getName(38));
		
		
		byVar=objDetails(39);//custom app drop down
		WebElement sel=driver.findElement(byVar);
		Select select=new Select(sel);
		select.selectByValue(dropval);
		
		byVar=objDetails(40);//reports option in available tabs
		clickButton(driver.findElement(byVar),getName(40));		
		
		byVar=objDetails(41);//add button
		clickButton(driver.findElement(byVar),getName(41));
		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar),getName(42));

		Thread.sleep(2000);
		
		byVar=objDetails(43);//email link
		clickButton(driver.findElement(byVar),getName(43));
		
		byVar=objDetails(44);//my email settings
		clickButton(driver.findElement(byVar), getName(44));
		
		byVar=objDetails(45);//email name field
		driver.findElement(byVar).clear();
		enterText(driver.findElement(byVar),emailname ,getName(45));
		
		byVar=objDetails(46);//email address field
		driver.findElement(byVar).clear();
		enterText(driver.findElement(byVar), emailaddr,getName(46));
		
		byVar=objDetails(47);//automatic bcc radio
		clickButton(driver.findElement(byVar),getName(47));
		
		byVar=objDetails(42);//save button
		clickButton(driver.findElement(byVar), getName(42));

		byVar=objDetails(48);//validate success message
		String testResult=driver.findElement(byVar).getText();

		validateTestResult(expectedResult, testResult);
		
		byVar=objDetails(49);//calendars and reminders
		clickButton(driver.findElement(byVar), getName(49));
		
		byVar=objDetails(50);//activity reminders
		clickButton(driver.findElement(byVar), getName(50));
		
		byVar=objDetails(51);//open a test reminder
		clickButton(driver.findElement(byVar),getName(51));
		
		int count=driver.getWindowHandles().size();
		if(count>1)
			Update_Report("Pass", "Open Test Reminder", "Test Reminder opened");
		else
			Update_Report("Fail", "Open Test Reminder", "Test Reminder not opened");
		
			

		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase8
	 * Brief Description : SFDC Login -> Developer Console option in user menu
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase8() throws IOException
	{
		startReport("Test Case 8","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 8.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(5);//user nav button
		clickButton(driver.findElement(byVar), getName(5));

		byVar=objDetails(52);//developer console
		clickButton(driver.findElement(byVar), getName(52));
		
		Set<String> windows=driver.getWindowHandles();
		String[] allwind=windows.toArray(new String[windows.size()]);

		driver.switchTo().window(allwind[1]);
		driver.close();
		driver.switchTo().window(allwind[0]);
		
		byVar=objDetails(5);//user nav button
		WebElement usermenu = driver.findElement(byVar);
		if (usermenu.isDisplayed() == true) {
			Update_Report("Pass", "Close Developer Console	", "Developer Console closed");
		} else {
			Update_Report("Fail", "Close Developer Console	", "Developer Console not closed");
		}

		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase9
	 * Brief Description : SFDC Login -> Logout
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase9() throws IOException 
	{
		startReport("Test Case 9","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 9.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String pgtitle=exceldata[1][4];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(5);//user nav button
		clickButton(driver.findElement(byVar),getName(5));

		byVar=objDetails(7);//logout
		clickButton(driver.findElement(byVar),getName(7));
		
		byVar=objDetails(53);//get title
		String title=driver.findElement(byVar).getText();

		if (title.equals(pgtitle))
			Update_Report("Pass", "Logout", "Logout successful");
		else
			Update_Report("Fail", "Logout", "Logout not successful");
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase10
	 * Brief Description : Create an Account
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase10() throws IOException, InterruptedException
	{
		startReport("Test Case 10","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 10.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String accountname=exceldata[1][4];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(54);//accounts tab
		clickButton(driver.findElement(byVar),getName(54));
		
		byVar=objDetails(55);//new account
		clickButton(driver.findElement(byVar),getName(55));
		
		byVar=objDetails(56);//account name
		enterText(driver.findElement(byVar), accountname, getName(56));
		
		Thread.sleep(3000);
		
		byVar=objDetails(42);
		clickButton(driver.findElement(byVar),getName(42));
		Thread.sleep(3000);
		
		byVar=objDetails(57);//account page header
		if((driver.findElement(byVar).getText()).equals(accountname))
			Update_Report("Pass", "Create Account", "Account Created");
		else
			Update_Report("Fail", "Create Account", "Account not Created");
		Thread.sleep(1000);
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase11
	 * Brief Description : Create new View
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase11() throws IOException, InterruptedException
	{
		startReport("Test Case 11","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 11.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String viewname=exceldata[1][4];
		String uniqueviewname=exceldata[1][5];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(54);//accounts tab
		clickButton(driver.findElement(byVar),getName(54));
		
		byVar=objDetails(58);//create new view
		clickButton(driver.findElement(byVar), getName(58));
		
		byVar=objDetails(59);//view name field
		enterText(driver.findElement(byVar), viewname,getName(59));
		
		byVar=objDetails(60);//unique view name
		WebElement uniqueview=driver.findElement(byVar);
		uniqueview.clear();
		enterText(uniqueview, uniqueviewname,getName(60));
		Thread.sleep(2000);
		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar), getName(42));
		Thread.sleep(2000);
		
		
		byVar=objDetails(61);//validate view
		Select sel=new Select(driver.findElement(byVar));
		String vname=sel.getFirstSelectedOption().getText();
		if(vname.equals(viewname))
			Update_Report("Pass", "Create View", "View created");
		else
			Update_Report("Fail", "Create View", "View not created");
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase12
	 * Brief Description : Edit View
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase12() throws IOException, InterruptedException
	{
		startReport("Test Case 12","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 12.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String viewval=exceldata[1][4];
		String fieldcol=exceldata[1][5];
		String operatorcol=exceldata[1][6];
		String valuecol=exceldata[1][7];
		String fieldstodisplay=exceldata[1][8];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(54);//account tab
		clickButton(driver.findElement(byVar), getName(54));

		byVar=objDetails(62);//view drop down
		WebElement view=driver.findElement(byVar);
		Select sel=new Select(view);
		sel.selectByValue(viewval);
		String viewname=sel.getFirstSelectedOption().getText();
		
		byVar=objDetails(63);//edit
		clickButton(driver.findElement(byVar)	, getName(63));

		byVar=objDetails(59);//view name field
		enterText(driver.findElement(byVar), "New "+viewname, getName(59)); 
		Thread.sleep(1000);
		
		byVar=objDetails(64);//field column
		Select field=new Select(driver.findElement(byVar));
		field.selectByVisibleText(fieldcol);

		byVar=objDetails(65);//operator column
		Select operator=new Select(driver.findElement(byVar));
		operator.selectByVisibleText(operatorcol);

		byVar=objDetails(66);//value field
		enterText(driver.findElement(byVar)	, valuecol, getName(66));
		

		Thread.sleep(3000);

		byVar=objDetails(67);//available field
		Select sel2=new Select(driver.findElement(byVar));
		sel2.selectByValue(fieldstodisplay);
		
		byVar=objDetails(68);//add button
		clickButton(driver.findElement(byVar),getName(68));
		Thread.sleep(1000);
		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar),getName(42)); 

		byVar=objDetails(61);//view drop down
		Select sel3=new Select(driver.findElement(byVar));
		String newvname=sel3.getFirstSelectedOption().getText();
		if(newvname.equals("New "+viewname))
			Update_Report("Pass", "Edit View", "View Edited");
		else
			Update_Report("Fail", "Edit View", "View not edited");
		Thread.sleep(3000);

		byVar=objDetails(69);//last activity
		if((driver.findElement(byVar).isDisplayed())==true)
			Update_Report("Pass", "Add Last Activity Field", "Last Activity Field Added");
		else
			Update_Report("Fail", "Add Last Activity Field", "Last Activity Field not Added");

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase13
	 * Brief Description : Merge Accounts
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase13() throws IOException, AWTException
	{
		startReport("Test Case 13","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 13.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String searchtxt=exceldata[1][4];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(54);//account tab
		clickButton(driver.findElement(byVar), getName(54));
		
		byVar=objDetails(70);//merge accounts
		clickButton(driver.findElement(byVar),getName(70));
		
		byVar=objDetails(71);//Search field
		enterText(driver.findElement(byVar),searchtxt,getName(71));
		
		byVar=objDetails(72);//find accounts
		clickButton(driver.findElement(byVar),getName(72));

		byVar=objDetails(73);//account 1
		if((driver.findElement(byVar).isSelected())==false)
			clickButton(driver.findElement(byVar),getName(73));

		byVar=objDetails(74);//account 2
		if((driver.findElement(byVar).isSelected())==false)
			clickButton(driver.findElement(byVar),getName(74));
		
		byVar=objDetails(75);//account 3
		if((driver.findElement(byVar).isSelected())==false)
			clickButton(driver.findElement(byVar),getName(75));

		byVar=objDetails(76);//next
		clickButton(driver.findElement(byVar),getName(76));

		byVar=objDetails(77);//merge
		clickButton(driver.findElement(byVar),getName(77));

		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);

		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase14
	 * Brief Description : Create Account Report
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase14() throws IOException, InterruptedException
	{
		startReport("Test Case 14","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 14.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String reportname=exceldata[1][4];
		String reportuniquename=exceldata[1][5];
		String datefield=exceldata[1][6];

		initializeDriver(url);

		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Account_Tab']")), "Accounts Tab");
		clickButton(driver.findElement(By.linkText("Accounts with last activity > 30 days")), "Accounts with last activity > 30 days");


		enterText(driver.findElement(By.xpath("//*[@id='ext-gen20']")), datefield, "Date Field");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String Date=dateFormat.format(date);

		enterText(driver.findElement(By.xpath("//*[@id='ext-comp-1042']")), Date, "From Date");
		enterText(driver.findElement(By.xpath("//*[@id='ext-comp-1045']")), Date, "To Date");

		clickButton(driver.findElement(By.xpath("//*[@id='ext-gen49']")), "Save");

		enterText(driver.findElement(By.xpath("//*[@id='saveReportDlg_reportNameField']")), reportname, "Report Name");
		driver.findElement(By.xpath("//*[@id='saveReportDlg_DeveloperName']")).clear();
		enterText(driver.findElement(By.xpath("//*[@id='saveReportDlg_DeveloperName']")), reportuniquename, "Report Unique Name");

		clickButton(driver.findElement(By.xpath("//*[@id='ext-gen345']")), "Save And Run Report");
		Thread.sleep(2000);

		if((driver.findElement(By.xpath("//*[@id='noTableContainer']/div/div[1]/div[1]/div[1]/h1")).getText().equals(reportname)))
			Update_Report("Pass", "Create Report", "Report created");
		else
			Update_Report("Fail", "Create Report", "Report not created");

		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase15
	 * Brief Description : Opportunity Tab
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase15() throws IOException, InterruptedException
	{
		startReport("Test Case 15","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 15.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String[] alloptions=exceldata[1][4].split(", ");

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(78);//opportunity tab
		clickButton(driver.findElement(byVar),getName(78));

		Thread.sleep(3000);
		
		byVar=objDetails(79);//opportunity drop down
		Select sel=new Select(driver.findElement(byVar));

		List<WebElement> al=sel.getOptions();

		int count=al.size();
		ArrayList<String> opts=new ArrayList<String>();

		for(int i=0;i<count;i++)
		{
			opts.add(al.get(i).getText());
			validateTestResult(alloptions[i], opts.get(i));
		}

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase16
	 * Brief Description : Create new opportunity
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase16() throws IOException, InterruptedException
	{
		startReport("Test Case 16","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 16.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String opporname=exceldata[1][4];
		String accname=exceldata[1][5];
		String probability= exceldata[1][6];
		String stageval=exceldata[1][7];
		String sourceval=exceldata[1][8];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(78);//oppor tab
		clickButton(driver.findElement(byVar),getName(78));

		Thread.sleep(1000);
		
		byVar=objDetails(80);//new
		clickButton(driver.findElement(byVar),getName(80));
		
		byVar=objDetails(81);//oppor name field
		enterText(driver.findElement(byVar), opporname, getName(81));
		
		byVar=objDetails(82);//accnt name
		enterText(driver.findElement(byVar), accname,getName(82));
		
		byVar=objDetails(83);//close date
		clickButton(driver.findElement(byVar),getName(83));

		byVar=objDetails(84);//stage dropdown
		Select stage=new Select(driver.findElement(byVar));
		stage.selectByValue(stageval);
		
		byVar=objDetails(85);//probability
		enterText(driver.findElement(byVar), probability,getName(85));

		byVar=objDetails(86);//source dropdown
		Select source=new Select(driver.findElement(byVar));
		source.selectByValue(sourceval);

		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar),getName(42));
		
		byVar=objDetails(87);//validation
		if((driver.findElement(byVar).getText().equals(opporname)))
			Update_Report("Pass", "Create opportunity", "Opportunity created");
		else
			Update_Report("Fail", "Creare opportunity", "Opportunity not created");
		Thread.sleep(3000);

		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase17
	 * Brief Description : Opportunity Pipeline
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase17() throws IOException, InterruptedException
	{
		startReport("Test Case 17","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 17.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(78);//oppor tab
		clickButton(driver.findElement(byVar),getName(78));
		
		byVar=objDetails(88);//oppor pipeline
		clickButton(driver.findElement(byVar),getName(88));
		
		byVar=objDetails(89);//oppor pipeline header
		if((driver.findElement(byVar).getText().equals("Opportunity Pipeline")))
			Update_Report("Pass", "Opportunity Pipeline Link", "Report of Opportunity Pipelined is opened");
		else
			Update_Report("Fail", "Opportunity Pipeline Link", "Report of Opportunity Pipelined is not opened");

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase18
	 * Brief Description : Stuck Opportunities
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase18() throws IOException, InterruptedException
	{
		startReport("Test Case 18","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 18.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(78);//oppor tab
		clickButton(driver.findElement(byVar),getName(78));
		
		byVar=objDetails(90);//stuck oppor
		clickButton(driver.findElement(byVar), getName(90));
		
		byVar=objDetails(91);//stuck oppor header
		if((driver.findElement(byVar).getText().equals("Stuck Opportunities")))
			Update_Report("Pass", "Stuck Opportunities Link", "Report of Stuck Opportunities is opened");
		else
			Update_Report("Fail", "Stuck Opportunities Link", "Report of Stuck Opportunities is not opened");

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase19
	 * Brief Description : Quarterly Report summary
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase19() throws IOException, InterruptedException
	{
		startReport("Test Case 19","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 19.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String interval=exceldata[1][4];
		String includeval=exceldata[1][5];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(78);//oppor tab
		clickButton(driver.findElement(byVar),getName(78));
		
		byVar=objDetails(92);//interval dropdown
		Select intervalselect=new Select(driver.findElement(byVar));
		intervalselect.selectByValue(interval);
		String value=intervalselect.getFirstSelectedOption().getText();
		
		byVar=objDetails(93);//include dropdown
		Select includeselect=new Select(driver.findElement(byVar));
		includeselect.selectByValue(includeval);

		byVar=objDetails(94);//run report
		clickButton(driver.findElement(byVar),getName(94));
		
		
		byVar=objDetails(95);//report header
		if((driver.findElement(byVar).getText().equals("Opportunity Report")))
		{
			byVar=objDetails(92);//interval check
			Select res=new Select(driver.findElement(byVar));
			String result=res.getFirstSelectedOption().getText();
			if(result.equals(value))
			{
				Update_Report("Pass", "Quarterly Summary", "Report generated correctly");
			}
			else
				Update_Report("Fail", "Quarterly Summary", "Report not generated correctly");

		}
		else
			Update_Report("Fail", "Quarterly Summary", "Report not generated");

		bw.close();
		driver.close();
	}


	/*
	 * Name of the method : testCase20
	 * Brief Description : Leads Tab
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase20() throws IOException, InterruptedException
	{
		startReport("Test Case 20","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 20.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(96);//leads tab
		clickButton(driver.findElement(byVar),getName(96));
		
		byVar=objDetails(97);//leads header
		if ((driver.findElement(byVar).getText().equals("Leads"))) {
			byVar = objDetails(98);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Leads Tab", "Leads Home page is opened");

			}
		} else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not opened");

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase21
	 * Brief Description : Opportunity Tab
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase21() throws IOException, InterruptedException
	{
		startReport("Test Case 21","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 21.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String[] alloptions=exceldata[1][4].split(", ");

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(96);//leads tab
		clickButton(driver.findElement(byVar),getName(96));
		Thread.sleep(3000);

		byVar=objDetails(97);//leads dropdown
		Select sel=new Select(driver.findElement(byVar));

		List<WebElement> al=sel.getOptions();

		int count=al.size();
		ArrayList<String> opts=new ArrayList<String>();

		for(int i=0;i<count;i++)
		{
			opts.add(al.get(i).getText());
			validateTestResult(alloptions[i], opts.get(i));
		}
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase22
	 * Brief Description : Today's Leads
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase22() throws IOException, InterruptedException
	{
		startReport("Test Case 22","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 22.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String leadvalue=exceldata[1][4];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(96);//leads tab
		clickButton(driver.findElement(byVar),getName(96));
		Thread.sleep(3000);

		byVar=objDetails(97);//leads dropdown
		Select drop=new Select(driver.findElement(byVar));
		drop.selectByValue(leadvalue);
		String leadValue=drop.getFirstSelectedOption().getText();

		byVar=objDetails(5);//user nav button
		clickButton(driver.findElement(byVar), getName(5));

		byVar=objDetails(7);//logout
		clickButton(driver.findElement(byVar),getName(7));

		driver.get(url);
		userLogin(username, password);

		byVar=objDetails(96);//leads tab
		clickButton(driver.findElement(byVar),getName(96));
		
		byVar=objDetails(97);//leads header
		if ((driver.findElement(byVar).getText().equals("Leads"))) {
			byVar = objDetails(98);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Leads Tab", "Leads Home page is opened");

			}
		} else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not opened");

		byVar=objDetails(97);//leads dropdown
		Select drop2=new Select(driver.findElement(byVar));
		String valselected=drop2.getFirstSelectedOption().getText();

		if(leadValue.equals(valselected))
			Update_Report("Pass", "Select Test Lead", "Test Lead Selected");
		else
			Update_Report("Fail", "Select Test Lead", "Test Lead not Selected");
		
		byVar=objDetails(100);//go button
		clickButton(driver.findElement(byVar),getName(100));

		byVar=objDetails(101);
		Select res=new Select(driver.findElement(byVar));
		String result=res.getFirstSelectedOption().getText();

		validateTestResult(leadValue, result);
		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase23
	 * Brief Description : Today's Leads
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase23() throws IOException, InterruptedException
	{
		startReport("Test Case 23","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 23.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String leadvalue=exceldata[1][4];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(96);//leads tab
		clickButton(driver.findElement(byVar),getName(96));
		
		byVar=objDetails(97);//leads header
		if ((driver.findElement(byVar).getText().equals("Leads"))) {
			byVar = objDetails(98);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Leads Tab", "Leads Home page is opened");

			}
		} else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not opened");

		byVar=objDetails(97);//leads dropdown
		Select drop=new Select(driver.findElement(byVar));
		drop.selectByValue(leadvalue);
		String leadValue=drop.getFirstSelectedOption().getText();

		Thread.sleep(3000);
		
		byVar=objDetails(97);//leads dropdown
		Select res=new Select(driver.findElement(byVar));
		String result=res.getFirstSelectedOption().getText();
		validateTestResult(leadValue, result);

		bw.close();
		driver.close();

	}
	/*
	 * Name of the method : testCase24
	 * Brief Description : New Lead
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase24() throws IOException, InterruptedException
	{
		startReport("Test Case 24","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 24.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String lastname=exceldata[1][4];
		String companyname=exceldata[1][5];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);

		byVar=objDetails(96);//leads tab
		clickButton(driver.findElement(byVar),getName(96));
		
		byVar=objDetails(97);//leads header
		if ((driver.findElement(byVar).getText().equals("Leads"))) {
			byVar = objDetails(98);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Leads Tab", "Leads Home page is opened");

			}
		} else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not opened");

		byVar=objDetails(102);//new
		clickButton(driver.findElement(byVar),getName(102));
		
		byVar=objDetails(103);//last name
		enterText(driver.findElement(byVar), lastname,getName(103));
		
		byVar=objDetails(104);//company
		enterText(driver.findElement(byVar), companyname,getName(104));
		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar),getName(42));
		
		byVar=objDetails(105);//header
		if(driver.findElement(byVar).getText().equals(lastname))
			Update_Report("Pass", "Create Lead", "Lead created");
		else
			Update_Report("Fail", "Create Lead", "Lead not created");

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase25
	 * Brief Description : Contacts Tab
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase25() throws IOException, InterruptedException
	{
		startReport("Test Case 25","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 25.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String lastname=exceldata[1][4];
		String accountname=exceldata[1][5];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);
		
		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		byVar = objDetails(102);// new
		clickButton(driver.findElement(byVar),getName(102));
		
		byVar = objDetails(109);//last name
		enterText(driver.findElement(byVar), lastname, getName(109));
		
		byVar = objDetails(110);//account name
		enterText(driver.findElement(byVar), accountname, getName(110));
		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar),getName(42));
		
		byVar=objDetails(105);//header
		if(driver.findElement(byVar).getText().equals(lastname))
			Update_Report("Pass", "Create Contact", "Contact created");
		else
			Update_Report("Fail", "Create Contact", "Contact not created");

		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase26
	 * Brief Description : Create View in Contacts Tab
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase26() throws IOException, InterruptedException
	{
		startReport("Test Case 26","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 26.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String contactview=exceldata[1][4];
		String uniquecontactview=exceldata[1][5];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");
		
		byVar=objDetails(111);//create new view
		clickButton(driver.findElement(byVar), getName(111));
		
		byVar=objDetails(112);//view name
		enterText(driver.findElement(byVar), contactview, getName(112));
		
		byVar=objDetails(113);//unique view name
		driver.findElement(byVar).clear();
		enterText(driver.findElement(byVar), uniquecontactview, getName(113));
		
		byVar=objDetails(114);//save button
		clickButton(driver.findElement(byVar),getName(114));

		byVar=objDetails(101);//dropdown
		Select res=new Select(driver.findElement(byVar));
		String result=res.getFirstSelectedOption().getText();

		validateTestResult(contactview, result);
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase27
	 * Brief Description : Recently Created Contacts Drop down
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase27() throws IOException, InterruptedException
	{
		startReport("Test Case 27","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 27.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String value=exceldata[1][4];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		byVar=objDetails(115);//recent contacts drop down
		Select recent=new Select(driver.findElement(byVar));
		recent.selectByValue(value);


		bw.close();
		driver.close();

	}
	/*
	 * Name of the method : testCase28
	 * Brief Description : Select My Contacts Option from drop down
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase28() throws IOException, InterruptedException
	{
		startReport("Test Case 28","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 28.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String value=exceldata[1][4];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		byVar=objDetails(116);//dropdown
		Select drop=new Select(driver.findElement(byVar));
		drop.selectByValue(value);
		value=drop.getFirstSelectedOption().getText();

		Thread.sleep(5000);
		byVar=objDetails(117);//dropdown
		Select res=new Select(driver.findElement(byVar));
		String result=res.getFirstSelectedOption().getText();
		validateTestResult(value, result);

		bw.close();
		driver.close();

	}

	/*
	 * Name of the method : testCase29
	 * Brief Description : Click on a contact name
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase29() throws IOException, InterruptedException
	{
		startReport("Test Case 29","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 29.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String contact=exceldata[1][4];		
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		byVar=objDetails(118);
		clickButton(driver.findElement(byVar),getName(118));

		Thread.sleep(4000);

		byVar=objDetails(105);//header
		if ((driver.findElement(byVar).getText().equals(contact))) {
			Update_Report("Pass", "Open a contact", "Contact info opened");
		} else

			Update_Report("Fail", "Open a contact", "Contact info not opened");

		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase30
	 * Brief Description : Create new view without view name and with only unique view name
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase30() throws IOException, InterruptedException
	{
		startReport("Test Case 30","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 30.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String uniqueViewName=exceldata[1][4];		
		String expectedResult=exceldata[1][5];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
		
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");
		
		byVar=objDetails(119);//create new view
		clickButton(driver.findElement(byVar),getName(119));
		
		byVar=objDetails(120);//unique view name
		enterText(driver.findElement(byVar), uniqueViewName, getName(120));
		
		byVar=objDetails(121);//view name
		driver.findElement(byVar).clear();
		
		
		byVar=objDetails(122);//save
		clickButton(driver.findElement(byVar),getName(121));

		byVar=objDetails(123);//validation
		String testResult=driver.findElement(byVar).getText();
		validateErrMsg(expectedResult, testResult);

		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase31
	 * Brief Description : Create View in Contacts Tab
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase31() throws IOException, InterruptedException
	{
		startReport("Test Case 31","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 31.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String contactview=exceldata[1][4];
		String uniquecontactview=exceldata[1][5];
		
		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		byVar=objDetails(119);//create new view
		clickButton(driver.findElement(byVar),getName(119));
		
		byVar=objDetails(121);//view name
		driver.findElement(byVar).clear();
		enterText(driver.findElement(byVar), contactview, getName(121));
		
		byVar=objDetails(120);//unique view name
		driver.findElement(byVar).clear();
		enterText(driver.findElement(byVar), uniquecontactview, getName(120));
		
		byVar=objDetails(122);//save
		clickButton(driver.findElement(byVar),getName(121));
		
		Select res=new Select(driver.findElement(By.className("title")));
		String result=res.getFirstSelectedOption().getText();

		validateTestResult(contactview, result);
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase32
	 * Brief Description : Contacts Tab - create new contact
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase32() throws IOException, InterruptedException
	{
		startReport("Test Case 32","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 32.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String lastname=exceldata[1][4];
		String accountname=exceldata[1][5];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);
	
		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(106);//contacts tab
		clickButton(driver.findElement(byVar), getName(106));
		
		byVar=objDetails(107);//contacts header
		if ((driver.findElement(byVar).getText().equals("Contacts"))) {
			byVar = objDetails(108);// home header
			if (driver.findElement(byVar).getText().equals("Home")) {
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		} else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");
		
		byVar=objDetails(102);//new
		clickButton(driver.findElement(byVar), getName(102));
		
		byVar=objDetails(109);//last name
		enterText(driver.findElement(byVar), lastname, getName(109));
		
		byVar=objDetails(110);//account name
		enterText(driver.findElement(byVar), accountname, getName(110));
		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar),getName(42));
		
		
		byVar=objDetails(105);
		if(driver.findElement(byVar).getText().equals(lastname))
			Update_Report("Pass", "Create Contact", "Contact created");
		else
			Update_Report("Fail", "Create Contact", "Contact not created");

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase33
	 * Brief Description : Home Tab--> Click Username
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase33() throws IOException, InterruptedException
	{
		startReport("Test Case 33","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 33.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String proftitle=exceldata[1][4];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(124);//home tab
		clickButton(driver.findElement(byVar),getName(124));
		
		byVar=objDetails(125);//user name
		clickButton(driver.findElement(byVar),getName(125));
		
		String curtitle=driver.getTitle();
		System.out.println(curtitle);
		validateTestResult(proftitle, curtitle);

		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase34
	 * Brief Description : Verify that edited last name is updated at various places
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void testCase34() throws IOException, InterruptedException
	{
		startReport("Test Case 34","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 34.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String newlastname=exceldata[1][4];

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='home_Tab']/a")), "Home Tab");

		String oldname=driver.findElement(By.xpath("//*[@id='ptBody']/div/div[2]/span[1]/h1/a")).getText();
		System.out.println(oldname);
		clickButton(driver.findElement(By.xpath("//*[@id='ptBody']/div/div[2]/span[1]/h1/a")), "User : FirstName LastName");

		clickButton(driver.findElement(By.xpath("//*[@id='chatterTab']/div[2]/div[2]/div[1]/h3/div/div/a/img")), "Edit");

		WebElement iframe=driver.findElement(By.id("contactInfoContentId"));

		driver.switchTo().frame(iframe);
		clickButton(driver.findElement(By.xpath("//*[@id='aboutTab']/a")), "About Tab ");

		driver.findElement(By.xpath("//*[@id='lastName']")).clear();
		enterText(driver.findElement(By.xpath("//*[@id='lastName']")), newlastname, "Last Name");
		clickButton(driver.findElement(By.xpath("//*[@id='TabPanel']/div/div[2]/form/div/input[1]")), "Save All");

		clickButton(driver.findElement(By.xpath("//*[@id='home_Tab']/a")), "Home Tab");

		String topname=driver.findElement(By.xpath("//*[@id='ptBody']/div/div[2]/span[1]/h1/a")).getText();
		if(topname.equals(oldname)==false)
		{
			Update_Report("Pass", "Update Name", "Name updated at the top left hand side of the page.");
		}
		else
		{
			Update_Report("Fail", "Update Name", "Name not updated at the top left hand side of the page.");
		}	
		String usernavname=driver.findElement(By.xpath("//*[@id='userNavButton']")).getText();
		if(usernavname.equals(oldname)==false)
		{
			Update_Report("Pass", "Update Name", "Name updated at the user navigation menu");
		}
		else
		{
			Update_Report("Fail", "Update Name", "Name not updated at the user navigation menu.");
		}	

		bw.close();
		driver.close();
	}
	/*
	 * Name of the method : testCase35
	 * Brief Description : Verify that a tab is removed
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase35() throws IOException, InterruptedException
	{
		startReport("Test Case 35","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 35.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String tabvalue = exceldata[1][4];

		String repopath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\SFDC Data Driven Testing\\Object Repository.xls";
		String reposheet = "Sheet1";
		repoRead(repopath, reposheet);

		initializeDriver(url);

		userLogin(username, password);
		
		byVar=objDetails(126);//+ button
		clickButton(driver.findElement(byVar), getName(126));
		
		byVar=objDetails(127);//customize my tabs
		clickButton(driver.findElement(byVar), getName(127));

		byVar=objDetails(128);//selected tabs
		Select tab=new Select(driver.findElement(byVar));
		tab.selectByValue(tabvalue);

		byVar=objDetails(129);//Remove
		clickButton(driver.findElement(byVar),getName(129));
		
		byVar=objDetails(42);//save
		clickButton(driver.findElement(byVar),getName(42));
		
		byVar=objDetails(5);//user nav button
		clickButton(driver.findElement(byVar),getName(5));
		
		byVar=objDetails(7);//logout
		clickButton(driver.findElement(byVar),getName(7));

		driver.get(url);
		userLogin(username, password);
		
		byVar=objDetails(130);//file tab
		if(driver.findElements(byVar).size() == 0){
			Update_Report("Pass", "Remove a tab", "The tab "+tabvalue+" is removed");
		}else{
			Update_Report("Fail", "Remove a tab", "The tab "+tabvalue+" is not removed");
		}
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase36
	 * Brief Description : Create an Event
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void testCase36() throws IOException, InterruptedException
	{
		startReport("Test Case 36","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 36.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='home_Tab']/a")), "Home Tab");
		clickButton(driver.findElement(By.xpath("//*[@id='ptBody']/div/div[2]/span[2]/a")), "Date Time");
		clickButton(driver.findElement(By.xpath("//*[@id='p:f:j_id25:j_id61:28:j_id64']/a")), "8.00 PM slot");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("New Evene")))
			Update_Report("Pass", "Create New Event", "New Event Page Opened for the slot");
		else
			Update_Report("Fail", "Create New Event", "New Event Page Opened not for the slot");


		clickButton(driver.findElement(By.xpath("//*[@id='EndDateTime_time']")), "End Time");
		clickButton(driver.findElement(By.xpath("//*[@id='timePickerItem_42']")), "9.00 PM option");
		String winHandleBefore = driver.getWindowHandle();
		
		clickButton(driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img")), "Subject Combo Box");
		
		Set<String> windows=driver.getWindowHandles();

		String[] allwind=windows.toArray(new String[windows.size()]);

		driver.switchTo().window(allwind[1]);
		clickButton(driver.findElement(By.xpath("html/body/div[2]/ul/li[5]/a")), "Other option");
		System.out.println(driver.getWindowHandles().size());

		driver.switchTo().window(winHandleBefore);
		clickButton(driver.findElement(By.xpath("//*[@id='topButtonRow']/input[1]")), "Save");

		if((driver.findElement(By.xpath("//*[@id='p:f:j_id25:j_id69:24:j_id71:0:j_id72:calendarEvent:j_id84']/a/span")).getText().equals("Other")))
			System.out.println("Success");
		else
			System.out.println("Fail");

		bw.close();
		driver.close();
	}



	/*
	 * Name of the method : initializeDriver
	 * Brief Description : To launch the browser and URL 
	 * Arguments : url -> The URL to be launched 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	public static void initializeDriver(String url)
	{
		System.setProperty("webdriver.gecko.driver",
				"C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/geckodriver.exe");

		driver = new FirefoxDriver();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/*
	 * Name of the method : userLogin
	 * Brief Description : To login to salesforce
	 * Arguments : username, password
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */

	public static void userLogin(String username, String password) throws IOException
	{
		WebElement uname,pwd,login;

		byVar=objDetails(1);
		uname=driver.findElement(byVar);
		enterText(uname, username,getName(1));

		byVar=objDetails(2);
		pwd=driver.findElement(byVar);
		enterText(pwd, password, getName(2));

		byVar=objDetails(3);
		login=driver.findElement(byVar);
		clickButton(login, getName(3));
	}
}
