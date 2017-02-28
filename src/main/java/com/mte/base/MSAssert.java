package com.mte.base;

import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
			getTest().log(LogStatus.PASS, "<B>"+details+"</B>" + "<br>[期望]:"+expected+" [实际]:"+actual);
		}else{
			getTest().log(LogStatus.FAIL, "<B>"+details+"</B>" + "<br>[期望]:"+expected+" [实际]:"+actual);
		}
		softAssert.assertEquals(actual, expected,details);
	}

	public static void verifyEqual(Double actual,Double expected,String details){
		if(actual.equals(expected)){
			getTest().log(LogStatus.PASS, "<B>"+details+"</B>" + "<br>[期望]:"+expected+" [实际]:"+actual);
		}else{
			getTest().log(LogStatus.FAIL, "<B>"+details+"</B>" + "<br>[期望]:"+expected+" [实际]:"+actual);
		}
		softAssert.assertEquals(actual, expected,details);
	}
	
	public static void verifyContains(String main,String expected,String details){
		if(main==null){
			getTest().log(LogStatus.FAIL, "<B>"+details+"</B>" + "<br>[期望]:"+expected+" [全部字符]: Null");
		}else{
			if(main.contains(expected)){
				getTest().log(LogStatus.PASS, "<B>"+details+"</B>" + "<br>[期望]:"+expected+" [全部字符]:"+main);
			}else{
				getTest().log(LogStatus.FAIL, "<B>"+details+"</B>" + "<br>[期望]:"+expected+" [全部字符]:"+main);
			}
		}
		softAssert.assertTrue(main.contains(expected));
	}


	
}
