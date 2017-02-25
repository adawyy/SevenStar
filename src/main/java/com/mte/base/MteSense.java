package com.mte.base;

import com.mte.util.PropUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/25/15
 */
public class MteSense {

    private PropUtil props = new PropUtil("./config/mtesense.properties");
    private PropUtil projprops = new PropUtil("./config/sevenstar.properties");

    public String getSessionId() {
        return sessionId;
    }

    private String sessionId = null;

    private IOSDriver ios = null;
    private AndroidDriver android = null;

    private WebDriver driver;

    int pageLoadTimeout = Integer.valueOf(props.get("mte.loadTime.second"));
    int waitTimeout = Integer.valueOf(props.get("mte.timeOut.second"));
    int scriptTimeout = Integer.valueOf(props.get("mte.pauseTime.second"));

    protected static MteSenseCore mteSenseCore = null;
    
    protected static BrowserMobProxy proxy ;

    public MteSenseCore getMteSenseCore() {
        return mteSenseCore;
    }

    public void setMteSenseCore(MteSenseCore mteBaseCore) {
        this.mteSenseCore = mteBaseCore;
    }
    
//    public void setBrowserMobProxy(BrowserMobProxy proxy){
//    	this.proxy = proxy;
//    }
//
//    public static synchronized BrowserMobProxy getProxy() {
//        if(proxy == null) {
//        	proxy = new BrowserMobProxyServer();
//        }
//        return proxy;
//    }


    public WebDriver getFirefoxDriver() {
  //      System.setProperty("webdriver.firefox.bin", props.get("mte.firefoxdriver.path"));
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new FirefoxDriver(capabilities);
            System.out.println("Testing the "+driver);
            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);

            driver.manage().window().maximize();
            MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.firefox,driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }

    public WebDriver getFirefoxDriver(DesiredCapabilities capabilities) {
 //       System.setProperty("webdriver.firefox.bin", props.get("mte.firefoxdriver.path"));
        try {
            driver = new FirefoxDriver(capabilities);

            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);

            driver.manage().window().maximize();
            MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.firefox,driver);
        } catch (Exception e) {

        }

        return driver;
    }

    public WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", props.get("mte.chromedriver.path"));
//        System.setProperty("webdriver.chrome.bin", "chrome_dir");
        try {
  //      	Proxy seleniumProxy = ClientUtil.createSeleniumProxy(getProxy());
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
   //         capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
            driver = new ChromeDriver(capabilities);

            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);

            driver.manage().window().maximize();
            MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.chrome,driver);
        } catch (Exception e) {

        }

        return driver;
    }

    public WebDriver getChromeDriver(DesiredCapabilities capabilities) {
        System.setProperty("webdriver.chrome.driver", props.get("mte.chromedriver.path"));
//        System.setProperty("webdriver.chrome.bin", "chrome_dir");
        try {
            driver = new ChromeDriver(capabilities);

            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);

            driver.manage().window().maximize();
            MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.chrome,driver);
        } catch (Exception e) {

        }

        return driver;
    }


    public WebDriver getIEDriver() {
        System.setProperty("webdriver.ie.driver", props.get("mte.iedriver.path"));
        try {
            DesiredCapabilities capabilities = DesiredCapabilities
                    .internetExplorer();
 //           Proxy seleniumProxy = ClientUtil.createSeleniumProxy(getProxy());
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
 //           capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
            capabilities
                    .setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
            driver = new InternetExplorerDriver(capabilities);

            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);

            driver.manage().window().maximize();
            MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.ie,driver);
        } catch (Exception e) {

        }

        return driver;
    }


    public WebDriver getIEDriver(DesiredCapabilities capabilities) {
        System.setProperty("webdriver.ie.driver", props.get("mte.iedriver.path"));
        try {
            driver = new InternetExplorerDriver(capabilities);

            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);

            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);

            driver.manage().window().maximize();
            MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.ie,driver);
        } catch (Exception e) {

        }

        return driver;
    }

    /**
     * base on mtesense.properties to create AppiumDriver include ios and android
     *
     * @return IOSDriver
     */

    public IOSDriver getIOSDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", props.get("mte.ios.platformVersion"));
        capabilities.setCapability("platformName", props.get("mte.ios.platformName"));
        capabilities.setCapability("deviceName", props.get("mte.ios.deviceName"));
        capabilities.setCapability("app", new File(props.get("mte.ios.app.path")));

        return getIOSDriver(capabilities, props.get("mte.url"));
    }

    /**
     * base on mtesense.properties to create AppiumDriver include ios and android
     *
     * @return AndroidDriver
     */


    public AndroidDriver getAndroidDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", props.get("mte.android.platformVersion"));
        capabilities.setCapability("platformName", props.get("mte.android.platformName"));
        capabilities.setCapability("deviceName", props.get("mte.android.deviceName"));
        capabilities.setCapability("appPackage", props.get("mte.android.appPackage"));
        capabilities.setCapability("appActivity", props.get("mte.android.appActivity"));
        capabilities.setCapability("app", new File(props.get("mte.android.app.path")));

        return getAndroidDriver(capabilities, props.get("mte.url"));
    }

    public IOSDriver getIOSDriver(DesiredCapabilities capabilities, String url) {
        try {
            if (capabilities != null) {
                ios = new IOSDriver(new URL(url), capabilities);
                sessionId = ios.getSessionId().toString();
                MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.ios,driver);
            } else {

                return null;
            }
        } catch (Exception e) {

        }
        return ios;
    }

    public AndroidDriver getAndroidDriver(DesiredCapabilities capabilities, String url) {
        try {
            if (capabilities != null) {
                android = new AndroidDriver(new URL(url), capabilities);
                sessionId = android.getSessionId().toString();
                MteSenseAssistant.setMteSenseDriverMap(MteSenseAssistant.android,driver);
            } else {

                return null;
            }
        } catch (Exception e) {

        }
        return android;
    }

}
