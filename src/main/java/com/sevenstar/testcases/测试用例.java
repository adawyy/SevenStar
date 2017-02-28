package com.sevenstar.testcases;

import com.sevenstar.task.测试任务;
import com.sevenstar.task.前台公用任务;
import com.sevenstar.task.后台公用任务;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;

public class 测试用例 extends 七星彩基础用例 {

    //定义任务
    private 测试任务 测试;
	private 前台公用任务 前台公用任务集;
    private 后台公用任务 后台公用任务集;

    //定义数据集
    ArrayList<Hashtable<String,String>> al_登录信息, al_定盘信息, al_分批赔率, al_录码模式, al_赔率设置, al_下注场景 = null;
    Hashtable<String,String> ht_登录信息, ht_定盘信息, ht_录码模式 = null;

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeTest
    public void setUp() throws Exception {
	    //用例初始化
        beforeClass();
        test = extent.startTest(testCaseName, "示例用例");
        asBaseCore.initialTest(testCaseName,test);

        //任务列初始化
        测试 =new 测试任务(asBaseCore);
        前台公用任务集 =new 前台公用任务(asBaseCore);
        后台公用任务集 =new 后台公用任务(asBaseCore);

        //读取数据
        用例表.设置数据文件("datapool/ST_1.xls");
        al_登录信息 = 用例表.获取登录信息("登录组一");
        al_定盘信息 = 用例表.获取赔率设置("定盘信息[口口XX]");
        al_分批赔率 = 用例表.获取赔率设置("分批赔率[口口XX]");
        al_录码模式 = 用例表.获取会员资料("录码模式二");
        al_赔率设置 = 用例表.获取会员资料("赔率设置");
        al_下注场景 = 用例表.获取下注信息("下注场景一");
        ht_登录信息 = al_登录信息.get(0);
        ht_定盘信息 = al_定盘信息.get(0);
        ht_录码模式 = al_录码模式.get(0);
    }

    @Test(groups = {"Demo"})
    public void 测试方法() throws Exception {

        测试.快速登录(ht_登录信息.get("总监"),ht_登录信息.get("总监密码"));
//        后台公用任务集.点击菜单("设置");
//        后台公用任务集.点击设置菜单("定盘");
//        后台公用任务集.设置定盘数据(ht_定盘信息);
//        后台公用任务集.设置分批数据(al_分批赔率);
//        公用.跳转页(前台地址);
//        公用.快速登录(ht_登录信息.get("会员"),ht_登录信息.get("会员密码"));
//        前台公用任务集.点击菜单("会员资料");
//        前台公用任务集.设置录码模式(ht_录码模式);
//        前台公用任务集.设置交易回水(al_赔率设置,"口口XX");
//        前台公用任务集.快打下注(al_下注场景,1);
    }

    @Test(dependsOnMethods = {"示例方法"})
    public void 测试方法二() throws Exception {

    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }

}
