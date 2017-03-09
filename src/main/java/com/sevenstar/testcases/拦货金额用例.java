package com.sevenstar.testcases;

import com.sevenstar.task.公用任务;
import com.sevenstar.task.前台公用任务;
import com.sevenstar.task.后台公用任务;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 * 此用例用于测试开盘 关盘
 */

public class 拦货金额用例 extends 七星彩基础用例 {

    //定义任务
    private 公用任务 公用;
    private 后台公用任务 后台公用任务集;

    //定义数据集
    ArrayList<Hashtable<String,String>> al_登录信息 = null;
    Hashtable<String,String> ht_登录信息,ht_大股东拦货金额,ht_股东拦货金额,ht_总代理拦货金额,ht_代理拦货金额 = null;

    String excelFile,tableName = null;

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeTest
    public void setUp() throws Exception {
	    //用例初始化
        beforeClass();
        test = extent.startTest(testCaseName, "示例用例");
        asBaseCore.initialTest(testCaseName,test);

        //任务列初始化
        公用 =new 公用任务(asBaseCore);
        后台公用任务集 =new 后台公用任务(asBaseCore);

        //读取数据
        excelFile = "datapool/ST_1.xls";
        tableName = "登录组一";

        用例表.设置数据文件("datapool/ST_1.xls");
        al_登录信息 = 用例表.获取登录信息("登录组一");
        ht_登录信息 = al_登录信息.get(0);
        ht_大股东拦货金额 = 用例表.获取各级设置信息("大股东拦货金额").get(0);
        ht_股东拦货金额 = 用例表.获取各级设置信息("股东拦货金额").get(0);
        ht_总代理拦货金额 = 用例表.获取各级设置信息("总代理拦货金额").get(0);
        ht_代理拦货金额 = 用例表.获取各级设置信息("代理拦货金额").get(0);
    }

    @Test(groups = {"Demo"})
    public void 拦货金额方法() throws Exception {
        公用.可靠登录(ht_登录信息.get("大股东"),ht_登录信息.get("大股东密码"),ht_登录信息.get("大股东新密码"),"大股东",excelFile,tableName);
        后台公用任务集.点击菜单("设置");
        后台公用任务集.设置拦货金额(ht_大股东拦货金额);

        公用.跳转页(后台地址);
        公用.可靠登录(ht_登录信息.get("股东"),ht_登录信息.get("股东密码"),ht_登录信息.get("股东新密码"),"股东",excelFile,tableName);
        后台公用任务集.点击菜单("设置");
        后台公用任务集.设置拦货金额(ht_股东拦货金额);

        公用.跳转页(后台地址);
        公用.可靠登录(ht_登录信息.get("总代理"),ht_登录信息.get("总代理密码"),ht_登录信息.get("总代理新密码"),"总代理",excelFile,tableName);
        后台公用任务集.点击菜单("设置");
        后台公用任务集.设置拦货金额(ht_总代理拦货金额);

        公用.跳转页(后台地址);
        公用.可靠登录(ht_登录信息.get("代理"),ht_登录信息.get("代理密码"),ht_登录信息.get("代理新密码"),"代理",excelFile,tableName);
        后台公用任务集.点击菜单("设置");
        后台公用任务集.设置拦货金额(ht_代理拦货金额);
    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }

}
