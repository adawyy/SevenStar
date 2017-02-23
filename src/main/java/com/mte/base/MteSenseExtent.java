package com.mte.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class MteSenseExtent {
    protected ExtentReports extent;
    protected ExtentTest test;

    @AfterMethod
    protected void afterEachTest(ITestResult result) {
        if (!result.isSuccess()) {
            test.log(LogStatus.FAIL, result.getThrowable());
        }
        if(test==null){
        	System.out.println("ASDQQWE");
        }
        extent.endTest(test);        
        extent.flush();
    }
    
    @AfterSuite
    protected void afterSuite() {
        extent.close();
    }
}