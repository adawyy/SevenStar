package com.sevenstar.testcases;

import java.io.File;

import com.sevenstar.data.Datamgr;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.Screen;

import com.mte.base.MteSenseBaseCase;
import com.mte.util.PropUtil;

public class 七星彩基础用例 extends MteSenseBaseCase {

	Datamgr 用例表 = new Datamgr();

    Datamgr 总表 = new Datamgr();

    public PropUtil 配置文件 = new PropUtil("./config/sevenstar.properties");

    protected String 浏览器类型 = 配置文件.get("sevenstar.browserType");

    protected String 前台地址 = 配置文件.get("sevenstar.frontendUrl");

    protected String 后台地址 = 配置文件.get("sevenstar.backendUrl");
    
    public 七星彩基础用例(){
        总表.设置数据文件("datapool/ST_汇总.xls");
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        七星彩基础用例 o = new 七星彩基础用例();
        System.out.println(o.浏览器类型);
        System.out.println(o.前台地址);
        System.out.println(o.后台地址);
    }

}
