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

public class 分批赔率用例 extends 七星彩基础用例 {

    //定义任务
    private 公用任务 公用;
    private 后台公用任务 后台公用任务集;

    //定义数据集
    ArrayList<Hashtable<String,String>> al_登录信息,al_分批赔率 = null;
    Hashtable<String,String> ht_登录信息 = null;

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeTest
    public void setUp() throws Exception {
	    //用例初始化
        beforeClass();
        test = extent.startTest(testCaseName, "示例用例");
        asBaseCore.initialTest(testCaseName,test);

        //任务列初始化
        公用 =new 公用任务(asBaseCore,test);
        后台公用任务集 =new 后台公用任务(asBaseCore,test);

        //读取数据
        用例表.设置数据文件("datapool/ST_1.xls");
        al_登录信息 = 用例表.获取登录信息("登录组一");
        ht_登录信息 = al_登录信息.get(0);
        al_分批赔率 = 用例表.获取赔率设置("分批赔率一");
    }

    /*
    目前分批赔率只支持3行分批 如果想要更多分批需要在Summary表中新添列和计算方式
     */

    @Test(groups = {"Demo"})
    public void 分批赔率方法() throws Exception {
        公用.快速登录(ht_登录信息.get("总监"),ht_登录信息.get("总监密码"));
        后台公用任务集.点击菜单("设置");
        后台公用任务集.关盘("ccc0000");
        后台公用任务集.点击设置菜单("定盘");
        后台公用任务集.点击分批赔率("口口XX");
        后台公用任务集.删除所有分批数据();
        后台公用任务集.新增分批赔率("10","1000","1000");
        后台公用任务集.设置分批数据(al_分批赔率);

    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }

}
