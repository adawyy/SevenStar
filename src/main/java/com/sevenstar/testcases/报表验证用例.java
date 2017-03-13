package com.sevenstar.testcases;

import com.sevenstar.task.公用任务;
import com.sevenstar.task.前台公用任务;
import com.sevenstar.task.后台公用任务;
import com.sevenstar.task.报表任务;
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

public class 报表验证用例 extends 七星彩基础用例 {

    //定义任务
    private 公用任务 公用;
    private 后台公用任务 后台公用任务集;
    private 报表任务 报表任务集;

    //定义数据集
    ArrayList<Hashtable<String,String>> al_登录信息 = null;
    Hashtable<String,String> ht_登录信息 = null;

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
        报表任务集 = new 报表任务(asBaseCore);

        //读取数据
        用例表.设置数据文件("datapool/ST_1.xls");
        al_登录信息 = 用例表.获取登录信息("登录组一");
        ht_登录信息 = al_登录信息.get(0);
    }

    @Test(groups = {"Demo"})
    public void 结账前报表验证() throws Exception {
        公用.快速登录(ht_登录信息.get("总监"),ht_登录信息.get("总监密码"));
        后台公用任务集.点击菜单("报表");
        报表任务集.验证报表(ht_登录信息.get("大股东"),"大股东","5",false);
        报表任务集.点击账号名(ht_登录信息.get("大股东"),"大股东");
        报表任务集.验证报表(ht_登录信息.get("股东"),"股东","5",false);
        报表任务集.点击账号名(ht_登录信息.get("股东"),"股东");
        报表任务集.验证报表(ht_登录信息.get("总代理"),"总代理","5",false);
        报表任务集.点击账号名(ht_登录信息.get("总代理"),"总代理");
        报表任务集.验证报表(ht_登录信息.get("代理"),"代理","5",false);
        报表任务集.点击账号名(ht_登录信息.get("代理"),"代理");
        报表任务集.验证报表(ht_登录信息.get("会员"),"会员","5",false);
        报表任务集.点击账号名(ht_登录信息.get("会员"),"会员");
    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }

}
