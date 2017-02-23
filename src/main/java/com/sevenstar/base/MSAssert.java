package com.sevenstar.base;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  1/09/16
 */
public class MSAssert{
	
	public static SoftAssert softAssert = new SoftAssert();
	
	private static ExtentTest test = null;
	
	public MSAssert(ExtentTest etest){
		this.setTest(etest);
	}
	
	public static ExtentTest getTest() {
		return test;
	}

	public static void setTest(ExtentTest test) {
		MSAssert.test = test;
	}
	
	public static void verifyTrue(boolean status,String details){
		if(status == true){
			getTest().log(LogStatus.PASS, "<B>"+details+"</B>");
		}else{
			getTest().log(LogStatus.PASS, "<B>"+details+"</B>");
		}
		softAssert.assertTrue(status, details);
	}
	
	public static void verifyEqual(String actual,String expected,String details){
		if(actual.equals(expected)){
			getTest().log(LogStatus.PASS, "<B>"+details+"</B>" + "<br>[Expected]:"+expected+" [Actual]:"+actual);
		}else{
			getTest().log(LogStatus.FAIL, "<B>"+details+"</B>" + "<br>[Expected]:"+expected+" [Actual]:"+actual);
		}
		softAssert.assertEquals(actual, expected,details);
	}
	
	public static void verifyContains(String main,String expected,String details){
		if(main==null){
			getTest().log(LogStatus.FAIL, "<B>"+details+"</B>" + "<br>[Expected]:"+expected+" [Full String]: Null");
		}else{
			if(main.contains(expected)){
				getTest().log(LogStatus.PASS, "<B>"+details+"</B>" + "<br>[Expected]:"+expected+" [Full String]:"+main);
			}else{
				getTest().log(LogStatus.FAIL, "<B>"+details+"</B>" + "<br>[Expected]:"+expected+" [Full String]:"+main);
			}
		}
		softAssert.assertTrue(main.contains(expected));
	}


	
}
