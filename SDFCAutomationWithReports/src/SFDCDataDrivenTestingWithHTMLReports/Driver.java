package SFDCDataDrivenTestingWithHTMLReports;

import java.awt.AWTException;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		AutomationScripts.testCase1();
		AutomationScripts.testCase2();
		AutomationScripts.testCase3();
		AutomationScripts.testCase4A();
		AutomationScripts.testCase4B();
		AutomationScripts.testCase5();
		
		//AutomationScripts.testCase6(); //Work in Progress
		//AutomationScripts.testCase7(); //Work in Progress
		
		AutomationScripts.testCase8();
		AutomationScripts.testCase9();
		AutomationScripts.testCase10();
		AutomationScripts.testCase11();
		AutomationScripts.testCase12();
		AutomationScripts.testCase13();
		AutomationScripts.testCase14(); //Work in Progress
		
	}
	
	
}
