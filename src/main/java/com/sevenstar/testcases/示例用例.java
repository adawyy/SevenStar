package com.sevenstar.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sevenstar.task.*;
import com.sevenstar.data.Datamgr;

import java.util.ArrayList;
import java.util.Hashtable;

public class 示例用例 extends 七星彩基础用例 {

    private 公用任务 公用;
	private 前台公用任务 前台公用任务集;
    private 后台公用任务 后台公用任务集;

    ArrayList<Hashtable<String,String>> al,al1,al2,al3,al4;
    Hashtable<String,String> ht,ht1 = null;

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeTest
    public void setUp() throws Exception {
        beforeClass();
        test = extent.startTest(testCaseName, "示例用例");
        asBaseCore.initialTest(testCaseName,test);

        //任务列初始化

        公用 =new 公用任务(asBaseCore);
        前台公用任务集 =new 前台公用任务(asBaseCore);
        后台公用任务集 =new 后台公用任务(asBaseCore);

        //读取数据
        Datamgr 数据表 = new Datamgr();
        数据表.设置数据文件("datapool/ST_1.xls");
        al = 数据表.获取赔率设置("定盘信息[口口XX]");
        al1 = 数据表.获取赔率设置("分批赔率[口口XX]");
        al2 = 数据表.获取会员资料("录码模式二");
        al3 = 数据表.获取会员资料("赔率设置");
        al4 = 数据表.获取下注信息("下注场景一");
        ht = al.get(0);
        ht1 = al2.get(0);
    }

    @Test
    public void testMain() throws Exception {

        公用.快速登录("director","123fff");
        后台公用任务集.点击菜单("设置");
        后台公用任务集.点击设置菜单("定盘");
        后台公用任务集.设置定盘数据(ht);
        后台公用任务集.设置分批数据(al1);
        公用.跳转页(前台地址);
        公用.快速登录("js001aaaa","asdfasdf1");
        前台公用任务集.点击菜单("会员资料");
        前台公用任务集.设置录码模式(ht1);
        前台公用任务集.设置交易回水(al3,"口口XX");
        前台公用任务集.快打下注(al4);

    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }


}
