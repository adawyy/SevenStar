package com.sevenstar.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sevenstar.task.*;
import com.sevenstar.data.Datamgr;

import java.util.ArrayList;
import java.util.Hashtable;

public class 示例用例 extends 七星彩基础用例 {

	private 前台公用任务 前台公用任务集;
    private 后台公用任务 后台公用任务集;

    Hashtable<String,String> ht = null;

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeTest
    public void setUp() throws Exception {
        beforeClass();
        test = extent.startTest(testCaseName, "示例用例");
        asBaseCore.initialTest(testCaseName,test);

        //任务列初始化
        前台公用任务集 =new 前台公用任务(asBaseCore);
        后台公用任务集 =new 后台公用任务(asBaseCore);

        //读取数据
        Datamgr 数据表 = new Datamgr();
        数据表.设置数据文件("datapool/ST_1.xls");
        ArrayList<Hashtable<String,String>> al;
        al = 数据表.获取赔率设置("定盘信息[口口XX]");
        ht = al.get(0);
    }

    @Test
    public void testMain() throws Exception {

        后台公用任务集.后台成功登录("director","123fff");
        后台公用任务集.点击菜单("设置");
        后台公用任务集.点击设置菜单("定盘");
        后台公用任务集.设置定盘数据(ht);
    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }


}
