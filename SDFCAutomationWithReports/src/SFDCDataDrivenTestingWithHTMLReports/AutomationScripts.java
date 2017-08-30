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
/*
		enterText(driver.findElement(objDetails(20)), "Hey Everyone", getName(20));

		Thread.sleep(5000);
		enterText(driver.findElement(By.xpath("//*[@id='publishereditablearea']")), "Hello All! How are you!", "'Post' Text Area");
		clickButton(driver.findElement(By.xpath("//*[@id='publishersharebutton']")), "Share");

		fileLink=driver.findElement(By.xpath("//*[@id='publisherAttachContentPost']/span[1]"));
		clickButton(fileLink, "File Link");

		upload=driver.findElement(By.xpath("//*[@id='chatterUploadFileAction']"));
		clickButton(upload, "Upload a file from the computer ");

		browse=driver.findElement(By.xpath("//*[@id='chatterFile']"));
		clickButton(browse, "Browse");

		Robot r=new Robot();

		r.keyPress(KeyEvent.VK_A);
		r.keyPress(KeyEvent.VK_B);
		r.keyPress(KeyEvent.VK_C);

		r.keyPress(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_T);
		r.keyPress(KeyEvent.VK_X);
		r.keyPress(KeyEvent.VK_T);

		r.keyPress(KeyEvent.VK_ENTER);

		r.keyRelease(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_B);
		r.keyRelease(KeyEvent.VK_C);

		r.keyRelease(KeyEvent.VK_PERIOD);
		r.keyRelease(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_X);
		r.keyRelease(KeyEvent.VK_T);

		r.keyRelease(KeyEvent.VK_ENTER);

		share=driver.findElement(By.xpath("//*[@id='publishersharebutton']"));
		clickButton(share, "Share ");

		upload=driver.findElement(By.xpath("//*[@id='uploadLink']"));
		clickButton(upload, "Profile Photo Upload");

		WebElement iframe1=driver.findElement(By.id("uploadPhotoContentId"));

		driver.switchTo().frame(iframe1);
		browse=driver.findElement(By.xpath("//*[@id='j_id0:uploadFileForm:uploadInputFile']"));
		clickButton(browse, "Browse ");

		r.keyPress(KeyEvent.VK_A);
		r.keyPress(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_P);
		r.keyPress(KeyEvent.VK_N);
		r.keyPress(KeyEvent.VK_G);

		r.keyPress(KeyEvent.VK_ENTER);

		r.keyRelease(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_PERIOD);
		r.keyRelease(KeyEvent.VK_P);
		r.keyRelease(KeyEvent.VK_N);
		r.keyRelease(KeyEvent.VK_G);

		r.keyRelease(KeyEvent.VK_ENTER);

		save=driver.findElement(By.xpath("//*[@id='j_id0:uploadFileForm:save']"));
		clickButton(save, "Save");

		//work on cropping the image

		save=driver.findElement(By.xpath("//*[@id='j_id0:j_id7:save']"));
		clickButton(save, "Save");
*/
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

		initializeDriver(url);
		WebElement uname, pwd, login, usermenu, settings;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		usermenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
		clickButton(usermenu,"User Menu");

		settings=driver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[2]"));
		clickButton(settings, "Settings");

		clickButton(driver.findElement(By.xpath("//*[@id='PersonalInfo']/a")),"Personal Link");
		clickButton(driver.findElement(By.xpath("//*[@id='LoginHistory_font']")),"Login History Link");
		clickButton(driver.findElement(By.xpath("//*[@id='RelatedUserLoginHistoryList_body']/div/a")),"Download Login History Link");

		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_ENTER);


		clickButton(driver.findElement(By.xpath("//*[@id='DisplayAndLayout']/a")), "Display and Layout");
		clickButton(driver.findElement(By.xpath("//*[@id='CustomizeTabs_font']")), "Customize my Tabs");

		WebElement sel=driver.findElement(By.xpath("//*[@id='p4']"));
		Select select=new Select(sel);
		select.selectByValue("02uf400000112FF");

		clickButton(driver.findElement(By.xpath("//*[@id='duel_select_0']/option[33]")), "Reports Tab");		
		clickButton(driver.findElement(By.xpath("//*[@id='duel_select_0_right']/img")), "Add Button");
		clickButton(driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")), "Save");

		clickButton(driver.findElement(By.xpath("//*[@id='EmailSetup']/a")), "Email Link");
		clickButton(driver.findElement(By.xpath("//*[@id='EmailSettings_font']")), "My Email Settings");
		enterText(driver.findElement(By.xpath("//*[@id='sender_name']")),emailname ,"Email Name");
		enterText(driver.findElement(By.xpath("//*[@id='sender_email']")), emailaddr, "Email Address");
		clickButton(driver.findElement(By.xpath("//*[@id='auto_bcc1']")), "Automatic Bcc --> Yes");
		clickButton(driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")), "Save");


		clickButton(driver.findElement(By.xpath("//*[@id='CalendarAndReminders']/a")), "Calendars and Reminders");
		clickButton(driver.findElement(By.xpath("//*[@id='Reminders_font']")), "Activity Reminders");
		clickButton(driver.findElement(By.xpath("//*[@id='testbtn']")),	"Open a Test Reminder");


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
		initializeDriver(url);

		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='userNavButton']")), "User Menu");

		clickButton(driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[3]")), "Developer Console");
		Set<String> windows=driver.getWindowHandles();

		String[] allwind=windows.toArray(new String[windows.size()]);

		driver.switchTo().window(allwind[1]);
		driver.close();
		driver.switchTo().window(allwind[0]);
		WebElement usermenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
		if(usermenu.isDisplayed()==true)
		{
			Update_Report("Pass", "Close Developer Console	", "Developer Console closed");
		}
		else
		{
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
		initializeDriver(url);

		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='userNavButton']")), "User Menu");

		clickButton(driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[5]")), "Logout");
		String title=driver.findElement(By.tagName("title")).getText();

		if(title.equals("Login | Salesforce"))
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
		initializeDriver(url);

		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Account_Tab']")), "Accounts Tab");
		clickButton(driver.findElement(By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input")), "New Account ");
		enterText(driver.findElement(By.xpath("//*[@id='acc2']")), accountname, "Account Name ");
		Thread.sleep(3000);
		clickButton(driver.findElement(By.xpath("//*[@id='topButtonRow']/input[1]")),"Save");
		Thread.sleep(3000);
		if((driver.findElement(By.xpath("//h2[@class='topName']")).getText()).equals(accountname))
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
		initializeDriver(url);

		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Account_Tab']/a")), "Accounts Tab");
		clickButton(driver.findElement(By.linkText("Create New View")), "Create new view");
		enterText(driver.findElement(By.xpath("//*[@id='fname']")), viewname, "View Name");
		WebElement uniqueview=driver.findElement(By.xpath("//*[@id='devname']"));
		uniqueview.clear();
		enterText(uniqueview, uniqueviewname, "View Unique Name");
		Thread.sleep(2000);
		clickButton(driver.findElement(By.xpath("//*[@id='editPage']/div[1]/table/tbody/tr/td[2]/input[1]")), "Save");
		Thread.sleep(2000);

		Select sel=new Select(driver.findElement(By.xpath("//*[@class='title']")));
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
		String viewname=exceldata[1][4];
		String fieldcol=exceldata[1][5];
		String operatorcol=exceldata[1][6];
		String valuecol=exceldata[1][7];
		String fieldstodisplay=exceldata[1][8];

		initializeDriver(url);

		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Account_Tab']")), "Accounts Tab");

		WebElement view=driver.findElement(By.xpath("//*[@id='fcf']"));
		Select sel=new Select(view);

		sel.selectByVisibleText(viewname);
		clickButton(driver.findElement(By.linkText("Edit"))	, "Edit ");

		enterText(driver.findElement(By.xpath("//*[@id='fname']")), "New "+viewname, "New View Name");
		Thread.sleep(1000);
		Select field=new Select(driver.findElement(By.xpath("//*[@id='fcol1']")));
		field.selectByVisibleText(fieldcol);

		Select operator=new Select(driver.findElement(By.xpath("//*[@id='fop1']")));
		operator.selectByVisibleText(operatorcol);

		enterText(driver.findElement(By.id("fval1"))	, valuecol, "Value ");

		Thread.sleep(3000);

		Select sel2=new Select(driver.findElement(By.xpath("//select[@name='colselector_select_0']")));
		sel2.selectByValue(fieldstodisplay);
		clickButton(driver.findElement(By.xpath("//*[@id='colselector_select_0_right']/img")),"Add");
		Thread.sleep(1000);
		clickButton(driver.findElement(By.xpath("//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]")), "Save");

		Select sel3=new Select(driver.findElement(By.xpath("//*[@class='title']")));
		String newvname=sel3.getFirstSelectedOption().getText();
		if(newvname.equals("New "+viewname))
			Update_Report("Pass", "Edit View", "View Edited");
		else
			Update_Report("Fail", "Edit View", "View not edited");
		Thread.sleep(3000);

		if((driver.findElement(By.xpath("//*[@title='Last Activity']")).isDisplayed())==true)
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

		initializeDriver(url);

		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Account_Tab']")), "Accounts Tab");
		clickButton(driver.findElement(By.linkText("Merge Accounts")), "Merge Accounts");
		enterText(driver.findElement(By.xpath("//*[@id='srch']")), searchtxt, "Search --> Account");
		clickButton(driver.findElement(By.xpath("//*[@id='stageForm']/div/div[2]/div[4]/input[2]")), "Find Accounts");

		if((driver.findElement(By.xpath("//*[@id='cid0']")).isSelected())==false)
			clickButton(driver.findElement(By.xpath("//*[@id='cid0']")), "Select Account");

		if((driver.findElement(By.xpath("//*[@id='cid1']")).isSelected())==false)
			clickButton(driver.findElement(By.xpath("//*[@id='cid1']")), "Select Account");

		if((driver.findElement(By.xpath("//*[@id='cid2']")).isSelected())==false)
			clickButton(driver.findElement(By.xpath("//*[@id='cid2']")), "Select Account");

		clickButton(driver.findElement(By.xpath(".//*[@id='stageForm']/div/div[2]/div[5]/div/input[1]")), "Next");

		clickButton(driver.findElement(By.xpath("//*[@id='stageForm']/div/div[2]/div[1]/div/input[2]")), "Merge");

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


		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Opportunity_Tab']")), "Opportunity Tab");


		Thread.sleep(3000);

		Select sel=new Select(driver.findElement(By.xpath("//*[@id='fcf']")));

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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Opportunity_Tab']")), "Opportunity Tab");

		Thread.sleep(1000);
		clickButton(driver.findElement(By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input")), "New");
		enterText(driver.findElement(By.xpath("//*[@id='opp3']")), opporname, "Opportunity Name");
		enterText(driver.findElement(By.xpath("//*[@id='opp4']")), accname, "Account Name");
		clickButton(driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a")), "Close Date");

		Select stage=new Select(driver.findElement(By.xpath("//*[@id='opp11']")));
		stage.selectByValue("Prospecting");

		enterText(driver.findElement(By.xpath("//*[@id='opp12']")), probability, "Probability");

		Select source=new Select(driver.findElement(By.xpath("//*[@id='opp6']")));
		source.selectByValue("Partner Referral");

		clickButton(driver.findElement(By.xpath("//*[@id='topButtonRow']/input[1]")), "Save");

		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals(opporname)))
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")), "Opportunity Tab");

		clickButton(driver.findElement(By.xpath("//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a")), "Opportunities Pipeline");
		if((driver.findElement(By.xpath("//*[@id='noTableContainer']/div/div[1]/div[1]/div[1]/h1")).getText().equals("Opportunity Pipeline")))
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")), "Opportunity Tab");

		clickButton(driver.findElement(By.xpath("//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a")), "Stuck Opportunities");
		if((driver.findElement(By.xpath("//*[@id='noTableContainer']/div/div[1]/div[1]/div[1]/h1")).getText().equals("Stuck Opportunities")))
			Update_Report("Pass", "Stuck Opportunities Link", "Report of Stuck Opportunities is opened");
		else
			Update_Report("Fail", "Stuck Opportunities Link", "Report of Stuck Opportunities is not opened");

		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase19
	 * Brief Description : Stuck Opportunities
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")), "Opportunity Tab");

		Select intervalselect=new Select(driver.findElement(By.xpath("//*[@id='quarter_q']")));
		intervalselect.selectByValue(interval);
		String value=intervalselect.getFirstSelectedOption().getText();

		Select includeselect=new Select(driver.findElement(By.xpath("//*[@id='open']")));
		includeselect.selectByValue(includeval);

		clickButton(driver.findElement(By.xpath("//*[@id='lead_summary']/table/tbody/tr[3]/td/input")), "Run Report");

		if((driver.findElement(By.xpath("//*[@id='noTableContainer']/div/div[1]/div[1]/div[1]/h1")).getText().equals("Opportunity Report")))
		{
			Select res=new Select(driver.findElement(By.xpath("//*[@id='quarter_q']")));
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='Lead_Tab']/a")), "Leads Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Leads")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Leads Tab", "Leads Home page is openeed");

			}
		}
		else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not openeed");

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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a")), "Leads Tab");
		Thread.sleep(3000);

		Select sel=new Select(driver.findElement(By.xpath("//*[@id='fcf']")));

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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a")), "Leads Tab");
		Thread.sleep(3000);

		Select drop=new Select(driver.findElement(By.xpath("//*[@id='fcf']")));
		drop.selectByValue(leadvalue);
		String leadValue=drop.getFirstSelectedOption().getText();

		clickButton(driver.findElement(By.xpath("//*[@id='userNavButton']")), "User Menu");
		clickButton(driver.findElement(By.linkText("Logout")), "Logout");

		driver.get(url);
		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");

		clickButton(driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a")), "Leads Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Leads")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Leads Tab", "Leads Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not opened");

		Select drop2=new Select(driver.findElement(By.xpath("//*[@id='fcf']")));
		String valselected=drop2.getFirstSelectedOption().getText();

		if(leadValue.equals(valselected))
			Update_Report("Pass", "Select Test Lead", "Test Lead Selected");
		else
			Update_Report("Fail", "Select Test Lead", "Test Lead not Selected");
		clickButton(driver.findElement(By.xpath(".//*[@id='filter_element']/div/span/span[1]/input")), "Go");

		Select res=new Select(driver.findElement(By.className("title")));
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a")), "Leads Tab");
		Thread.sleep(3000);
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Leads")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Leads Tab", "Leads Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not opened");

		Select drop=new Select(driver.findElement(By.xpath("//*[@id='fcf']")));
		drop.selectByValue(leadvalue);
		String leadValue=drop.getFirstSelectedOption().getText();

		Thread.sleep(3000);

		Select res=new Select(driver.findElement(By.xpath("//*[@id='fcf']")));
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a")), "Leads Tab");
		Thread.sleep(3000);
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Leads")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Leads Tab", "Leads Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Leads Tab", "Leads Home page is not opened");

		clickButton(driver.findElement(By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input")), "New");
		enterText(driver.findElement(By.xpath("//*[@id='name_lastlea2']")), lastname," Last Name");
		enterText(driver.findElement(By.xpath("//*[@id='lea3']")),companyname,"Company");

		clickButton(driver.findElement(By.xpath("//*[@id='topButtonRow']/input[1]")), "Save");

		if(driver.findElement(By.xpath("//*[@id='contactHeaderRow']/div[2]/h2")).getText().equals(lastname))
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		clickButton(driver.findElement(By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input")), "New");
		enterText(driver.findElement(By.xpath("//*[@id='name_lastcon2']")), lastname, "Last Name");
		enterText(driver.findElement(By.xpath("//*[@id='con4']")), accountname, "Account Name");
		clickButton(driver.findElement(By.xpath("//*[@id='topButtonRow']/input[1]")), "Save");

		if(driver.findElement(By.xpath("//*[@id='contactHeaderRow']/div[2]/h2")).getText().equals(lastname))
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		clickButton(driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[2]/a")), "Create New View");
		enterText(driver.findElement(By.xpath("//*[@id='fname']")), contactview, "View Name");
		driver.findElement(By.xpath("//*[@id='devname']")).clear();
		enterText(driver.findElement(By.xpath("//*[@id='devname']")), uniquecontactview, "View Unique Name");
		clickButton(driver.findElement(By.xpath("//*[@id='editPage']/div[1]/table/tbody/tr/td[2]/input[1]")), "Save");

		Select res=new Select(driver.findElement(By.className("title")));
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		Select recent=new Select(driver.findElement(By.xpath("//*[@id='hotlist_mode']")));
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		Select drop=new Select(driver.findElement(By.id("fcf")));
		drop.selectByValue(value);
		value=drop.getFirstSelectedOption().getText();

		Thread.sleep(5000);

		Select res=new Select(driver.findElement(By.name("fcf")));
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
		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		clickButton(driver.findElement(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")),"Contact");

		Thread.sleep(4000);

		if((driver.findElement(By.xpath("//*[@id='contactHeaderRow']/div[2]/h2")).getText().equals(contact)))
		{
			Update_Report("Pass", "Open a contact", "Contact info opened");
		}
		else

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
		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		clickButton(driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[2]/a[2]")), "Create New View");
		enterText(driver.findElement(By.xpath("//*[@id='devname']")), uniqueViewName, "Unique View Name");
		driver.findElement(By.xpath("//*[@id='fname']")).clear();
		clickButton(driver.findElement(By.xpath("//*[@id='editPage']/div[1]/table/tbody/tr/td[2]/input[1]")), "Save");

		String testResult=driver.findElement(By.xpath("//*[@id='editPage']/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]")).getText();
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		clickButton(driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[2]/a")), "Create New View");
		enterText(driver.findElement(By.xpath("//*[@id='fname']")), contactview, "View Name");
		driver.findElement(By.xpath("//*[@id='devname']")).clear();
		enterText(driver.findElement(By.xpath("//*[@id='devname']")), uniquecontactview, "View Unique Name");
		clickButton(driver.findElement(By.xpath("//*[@id='editPage']/div[1]/table/tbody/tr/td[2]/input[1]")), "Save");

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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");
		clickButton(driver.findElement(By.xpath("//*[@id='Contact_Tab']/a")), "Contacts Tab");
		if((driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText().equals("Contacts")))
		{
			if(driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText().equals("Home"))
			{
				Update_Report("Pass", "Contacts Tab", "Contacts Home page is opened");

			}
		}
		else
			Update_Report("Fail", "Contacts Tab", "Contacts Home page is not opened");

		clickButton(driver.findElement(By.xpath("//*[@id='hotlist']/table/tbody/tr/td[2]/input")), "New");
		enterText(driver.findElement(By.xpath("//*[@id='name_lastcon2']")), lastname, "Last Name");
		enterText(driver.findElement(By.xpath("//*[@id='con4']")), accountname, "Account Name");
		clickButton(driver.findElement(By.xpath("//*[@id='topButtonRow']/input[1]")), "Save");

		if(driver.findElement(By.xpath("//*[@id='contactHeaderRow']/div[2]/h2")).getText().equals(lastname))
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='home_Tab']/a")), "Home Tab");
		clickButton(driver.findElement(By.xpath("//*[@id='ptBody']/div/div[2]/span[1]/h1/a")), "User : FirstName LastName");
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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");


		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		clickButton(driver.findElement(By.xpath("//*[@id='AllTab_Tab']/a/img")), "+");
		clickButton(driver.findElement(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/table/tbody/tr/td[2]/input")), "Customize My Tabs");

		Select tab=new Select(driver.findElement(By.xpath("//*[@id='duel_select_1']")));
		tab.selectByValue(tabvalue);

		clickButton(driver.findElement(By.xpath("//*[@id='duel_select_0_left']/img")), "Remove");

		clickButton(driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")), "Save");
		clickButton(driver.findElement(By.xpath("//*[@id='userNavButton']")), "User Menu");
		clickButton(driver.findElement(By.linkText("Logout")), "Logout");

		driver.get(url);
		enterText(driver.findElement(By.id("username")), username, "Username");
		enterText(driver.findElement(By.id("password")), password, "Password");
		clickButton(driver.findElement(By.id("Login")), "Login");
		if(driver.findElements(By.xpath("//*[@id='File_Tab']")).size() == 0){
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

		clickButton(driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img")), "Subject Combo Box");
		String winHandleBefore = driver.getWindowHandle();
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
