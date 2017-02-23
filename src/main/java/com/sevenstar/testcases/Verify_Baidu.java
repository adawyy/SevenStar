package com.sevenstar.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sevenstar.task.Baidu_Task;

public class Verify_Baidu extends SevenStarBaseCase {

	private Baidu_Task Baidu;
	
	private String testCaseName = this.getClass().getSimpleName();
	
	@DataProvider(name = "iteration")
    public static Object[][] feedbackData() {
        return new Object[][] {
        		{ 5, "Error", "give rate 5 of Error",true }, 
        		{ 4, "Suggestion" , "give rate 4 of Suggestion" ,false}, 
        		{ 3, "Compliment", "give rate 3 of Compliment" ,true },
        		{ 2, "Complaint", "give rate 2 of Complaint", false }, 
        		{ 1, "Error", "give rate 1 of Error" ,true } 
        	};
    }
	
	@BeforeTest
    public void setUp() throws Exception {
        beforeClass();
        test = extent.startTest(testCaseName, "This test case is about sent 5 feedback comment to the Feedback Tray");
        asBaseCore.initialTest(testCaseName,test);
        Baidu=new Baidu_Task(asBaseCore);
//        feedback.NavTo("IBMer Hub");
    }

    @Test
    public void testMain() throws Exception {

    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }


}
