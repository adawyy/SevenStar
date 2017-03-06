package com.mte.base;

import com.mte.util.PropUtil;
import com.relevantcodes.extentreports.NetworkMode;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeSuite;

public abstract class MteSenseExtent {

    public ExtentReports getExtent() {
        return extent;
    }

    public void setExtent(ExtentReports extent) {
        this.extent = extent;
    }

    protected static ExtentReports extent = null;

    protected ExtentTest test = null;

    public PropUtil props = new PropUtil("./config/mtesense.properties");

    @AfterMethod
    protected void afterEachTest(ITestResult result) {
        if (!result.isSuccess()) {
            test.log(LogStatus.FAIL, result.getThrowable());
        }
        if(test==null){
            System.out.println("No test for ExtendReport");
        }
        extent.endTest(test);
        extent.flush();
    }

    @BeforeSuite
    protected void beforesuite(){
//        if(extent==null){
//            extent = new ExtentReports(props.get("mte.reportFile.path"), NetworkMode.OFFLINE);
//            setExtent(extent);
//        }
//        if(getExtent()!=null){
//            System.out.println("Test suite is about to started");
//        }
        extent = ExtentManager.getReporter(props.get("mte.reportFile.path"));
    }

//    @AfterMethod
//    protected void afterEachTest(ITestResult result) {
//        if (!result.isSuccess()) {
//            test.log(LogStatus.FAIL, result.getThrowable());
//        }
//        if(test==null){
//        	System.out.println("ASDQQWE");
//        }
//    }
    
    @AfterSuite
    protected void afterSuite() {
        extent.close();
    }
}