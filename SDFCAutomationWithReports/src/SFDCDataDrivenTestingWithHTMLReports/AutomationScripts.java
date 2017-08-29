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

		String[][] exceldata = inputRead(filepath, sheetname);

		String URL = exceldata[1][1];
		String userName = exceldata[1][2];
		String password = exceldata[1][3];
		String expectedResult = exceldata[1][4];

		initializeDriver(URL);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, userName, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		String testResult = driver.findElement(By.xpath("//*[@id='error']")).getText();

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

		initializeDriver(url);

		WebElement uname, pwd, login;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		WebElement usermenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
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
		
		initializeDriver(url);

		WebElement uname, pwd, login, remember, usermenu, logout;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		remember = driver.findElement(By.id("rememberUn"));
		if (remember.isSelected() == false)
			clickButton(remember, "Remember me checkbox");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		usermenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
		clickButton(usermenu,"User Menu");

		Thread.sleep(2000);
		logout = driver.findElement(By.linkText("Logout"));
		clickButton(logout, "Logout");
		
		WebElement elt=driver.findElement(By.xpath("//*[@id='hint_back_chooser']"));
		if(elt.isDisplayed()==true)
		{
			Update_Report("Pass", "Remember Me","Saved Username present");
		}
		else
		{
			Update_Report("Fail", "Remember Me","Saved Username not present");
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

		initializeDriver(url);

		WebElement forgot, uname, submit;

		forgot = driver.findElement(By.xpath("//*[@id='forgot_password_link']"));
		clickButton(forgot, "Forgot your Password?");
		Thread.sleep(2000);

		uname = driver.findElement(By.xpath("//*[@id='un']"));
		enterText(uname, username, "Username");
		Thread.sleep(2000);

		submit = driver.findElement(By.xpath("//*[@id='continue']"));
		clickButton(submit,"Continue");
		Thread.sleep(2000);
		
		String testResult=driver.findElement(By.xpath("//*[@id='forgotPassForm']/div/p[1]")).getText();
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
		WebElement uname,pwd, login;
		uname=driver.findElement(By.xpath("//*[@id='username']"));
		enterText(uname, username, "Username");

		pwd=driver.findElement(By.xpath("//*[@id='password']"));
		enterText(pwd, password, "Password");

		login=driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		String testResult = driver.findElement(By.xpath("//*[@id='error']")).getText();
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
		startReport("Test Case 5","C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Html Reports/");
		String filepath = "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/SFDC Data Driven Testing/Test Case 5.xls";
		String sheetname = "Sheet1";

		String[][] exceldata = inputRead(filepath, sheetname);
		String url = exceldata[1][1];
		String username = exceldata[1][2];
		String password = exceldata[1][3];
		String[] alloptions=exceldata[1][4].split(",");
		
		
		initializeDriver(url);

		WebElement uname, pwd, login, usermenu;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		usermenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
		clickButton(usermenu,"User Menu");

		Thread.sleep(3000);
		
		List<WebElement> al=driver.findElement(By.xpath("//*[@id='userNavMenu']")).findElements(By.tagName("a"));
		int count=al.size();
		WebElement elt;
		ArrayList<String> opts = new ArrayList<String>();
		for(int i=1;i<=count;i++)
		{
			elt=driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a["+i+"]"));
			opts.add(elt.getText());
			validateTestResult(alloptions[i-1], opts.get(i-1));
			
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

		initializeDriver(url);

		WebElement uname, pwd, login, usermenu, profile, editProfile, aboutTab, lastName, save;

		uname = driver.findElement(By.id("username"));
		enterText(uname, username, "Username");

		pwd = driver.findElement(By.id("password"));
		enterText(pwd, password, "Password");

		login = driver.findElement(By.id("Login"));
		clickButton(login, "Login");

		usermenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
		clickButton(usermenu,"User Menu");

		profile=driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[1]"));
		clickButton(profile,  "My Profile");

		editProfile=driver.findElement(By.xpath("//*[@id='chatterTab']/div[2]/div[2]/div[1]/h3/div/div/a/img"));
		clickButton(editProfile	, "Edit Profile (next to Contact)");

		WebElement iframe=driver.findElement(By.id("contactInfoContentId"));

		driver.switchTo().frame(iframe);
		aboutTab=driver.findElement(By.xpath("//*[@id='aboutTab']/a"));
		clickButton(aboutTab, "About Tab ");

		lastName=driver.findElement(By.xpath("//*[@id='lastName']"));
		lastName.clear();
		enterText(lastName, lastname, "Last Name");

		save=driver.findElement(By.xpath(".//*[@id='TabPanel']/div/div[2]/form/div/input[1]"));
		clickButton(save, "Save All");

		
		WebElement share, fileLink, upload, browse;
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
		
		clickButton(driver.findElement(By.xpath("//*[@id='Account_Tab']")), "Accounts Tab");
		clickButton(driver.findElement(By.xpath(".//*[@id='filter_element']/div/span/span[2]/a[2]")), "Create new view");
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
	 * Name of the method : validateTestResult
	 * Brief Description : To validate the generated test result against the expected result
	 * Arguments : expectedResult -> The expected error message , testResult -> The generated error message
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : August 28, 2017
	 * Last Modified : August 28, 2017
	 */
	private static void validateTestResult(String expectedResult, String testResult) throws IOException {
		// TODO Auto-generated method stub
		if (expectedResult.equals(testResult))

		{
			Update_Report("Pass", "Validation",( "The test result generated '"+testResult+"' matches with the expected result . Test Case Passed."));
		} else
			Update_Report("Fail", "Validation",( "The test result generated '"+testResult+"' matched with the expected result. Test Case Failed."));


	}
}
