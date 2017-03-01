package com.sevenstar.testcases;

import com.sevenstar.task.公用任务;
import com.sevenstar.task.前台公用任务;
import com.sevenstar.task.后台公用任务;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;

public class 开关盘用例 extends 七星彩基础用例 {

    //定义任务
    private 公用任务 公用;
	private 前台公用任务 前台公用任务集;
    private 后台公用任务 后台公用任务集;

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
        前台公用任务集 =new 前台公用任务(asBaseCore);
        后台公用任务集 =new 后台公用任务(asBaseCore);

        //读取数据
        用例表.设置数据文件("datapool/ST_1.xls");
        al_登录信息 = 用例表.获取登录信息("登录组一");
        ht_登录信息 = al_登录信息.get(0);
    }

    @Test(groups = {"Demo"})
    public void 关开盘方法() throws Exception {
        公用.快速登录(ht_登录信息.get("总监"),ht_登录信息.get("总监密码"));

        后台公用任务集.关盘("ccc0000");
        后台公用任务集.开盘("1234567","ccc0000");

        公用.跳转页(前台地址);
        公用.快速登录(ht_登录信息.get("会员"),ht_登录信息.get("会员密码"));

    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }

}
