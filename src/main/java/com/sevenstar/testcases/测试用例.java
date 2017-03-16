package com.sevenstar.testcases;

import com.mte.base.MSAssert;
import com.mte.util.WebDriverTable;
import com.sevenstar.task.公用任务;
import com.sevenstar.task.前台公用任务;
import com.sevenstar.task.后台公用任务;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 测试用例 extends 七星彩基础用例 {

    //定义任务
    private 公用任务 测试;
	private 前台公用任务 前台公用任务集;
    private 后台公用任务 后台公用任务集;

    //定义数据集
    ArrayList<Hashtable<String,String>> al_登录信息, al_定盘信息, al_分批赔率, al_录码模式, al_赔率设置, al_下注场景 = null;
    Hashtable<String,String> ht_登录信息, ht_定盘信息, ht_录码模式 = null;

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeClass
    public void setUp() throws Exception {
	    //用例初始化
        beforeClass();
        test = getExtent().startTest(testCaseName, "示例用例");
        asBaseCore.initialTest(testCaseName,test);

        //任务列初始化
        测试 = new 公用任务(asBaseCore);
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

    @Test
    public void 测试方法1() throws Exception {

        测试.快速登录(ht_登录信息.get("总监"),ht_登录信息.get("总监密码"));
        后台公用任务集.点击菜单("越级操作");
//        WebDriverTable WT = new WebDriverTable(asBaseCore,By.xpath("//*[@id='memberadmin']/div[3]/table"),1);

//        System.out.println(WT.获取总记录行数());
//        System.out.println(WT.获取总列数(2));
 //       System.out.println(WT.获取所有列名());
//        System.out.println(WT.获取列数("信用额度"));
//        System.out.println(WT.获取单元格值(5,"修改时间"));
//       System.out.println(WT.获取文字所在行数("AT01(大股东)","账号"));
 //      WT.通过条件获取单元格元素("账号","AT01(大股东)","内容").findElement(By.tagName("a")).click();
//        WT.通过条件获取单元格元素("账号","AT01(大股东)","内容").click();
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
 //       MSAssert.verifyEqual("THIS","THAT","CHECK STATUS1");
    }

    @Test
    public void 测试方法2() throws Exception {
//        MSAssert.verifyEqual("THAT","THAT","CHECK STATUS2");

    }

    @AfterClass
    public void tearDown() throws Exception {
        MSAssert.softAssert.assertAll();
        afterClass();
    }

}
