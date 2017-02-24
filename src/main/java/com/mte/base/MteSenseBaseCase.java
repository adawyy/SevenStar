package com.mte.base;

import io.appium.java_client.AppiumDriver;

import com.mte.util.PropUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

import net.lightbody.bmp.BrowserMobProxy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.Screen;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/28/15
 */
public class MteSenseBaseCase extends MteSenseExtent{

    public PropUtil props = new PropUtil("./config/mtesense.properties");

    protected WebDriver driver = null;

    private MteSense sense = new MteSense();

	protected Screen s = new Screen();
	
	//protected BrowserMobProxy proxy = MteSense.getProxy();
    
    public String getSessionId() {
        return sense.getSessionId();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public AppiumDriver getMobileDriver() {
        return (AppiumDriver) driver;
    }

    protected MteSenseCore asBaseCore;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new MteSenseBaseCase().props.get("mte.test.platform"));
	}
    
    public void beforeClass() {
        beforeClass(null);
    }

    public void beforeClass(String driverType) {
  //  	proxy.start(0);
        if (driverType == null) {
            driverType = props.get("mte.test.platform");
        }
        initWebDriver(driverType);
        if(driver==null){
            System.out.println("driver initial failed");
        }
        asBaseCore = new MteSenseCore(driver);
        if(!driverType.equals("ios")&&!driverType.equals("android")){
//            System.out.println("Browser is ready to open");
            asBaseCore.get(props.get("mte.url"),3);
        }
        sense.setMteSenseCore(asBaseCore);
        
//        proxy.newHar("w3dev.somerslab.ibm.com");
        extent = new ExtentReports(props.get("mte.reportFile.path"), NetworkMode.OFFLINE);
    }

    public void beforeClass(String driverType, DesiredCapabilities capabilities, String url) {

        if (driverType == null) {
            driverType = props.get("mte.test.platform");
        }
        initWebDriver(driverType, capabilities, url);
        asBaseCore = new MteSenseCore(driver);
        if(!driverType.equals("ios")&&!driverType.equals("android")){
            asBaseCore.get(url,3);
        }
        sense.setMteSenseCore(asBaseCore);
  //      proxy.start(0);
        extent = new ExtentReports(props.get("mte.reportFile.path"), NetworkMode.OFFLINE);
    }
    
    public void beforeClass(boolean start){
    	asBaseCore = new MteSenseCore(true);
    	sense.setMteSenseCore(asBaseCore);
  //  	proxy.start(0);
        extent = new ExtentReports(props.get("mte.reportFile.path"), NetworkMode.OFFLINE);
    }

    public void initWebDriver(String driverType) {

        switch (driverType.trim()) {
            case "IOS":
//                System.out.println("IOS is ready");
                driver = sense.getIOSDriver();
                break;
            case "Android":
//                System.out.println("Android is ready");
                driver = sense.getAndroidDriver();
                break;
            case "FF":
//                System.out.println("FF is ready");
                driver = sense.getFirefoxDriver();
                break;
            case "Chrome":
//                System.out.println("Chrome is ready");
                driver = sense.getChromeDriver();
                break;
            case "IE":
//                System.out.println("IE is ready");
                driver = sense.getIEDriver();
                break;
            default:
                driver = null;
        }
    }


    public void initWebDriver(String driverType, DesiredCapabilities capabilities, String url) {

        switch (driverType.trim()) {
            case "IOS":
                driver = sense.getIOSDriver(capabilities, url);
                break;
            case "Android":
                driver = sense.getAndroidDriver(capabilities, url);
                break;
            case "FF":
                driver = sense.getFirefoxDriver(capabilities);
                break;
            case "Chrome":
                driver = sense.getChromeDriver(capabilities);
                break;
            case "IE":
                driver = sense.getIEDriver(capabilities);
                break;
            default:
                driver = null;
        }

    }

    public void afterClass() {

//        driver.quit();

    }

}
