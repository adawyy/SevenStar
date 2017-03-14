package com.sevenstar.testcases;

import com.sevenstar.task.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 场景用例 extends 七星彩基础用例 {

    //定义任务
    private 公用任务 公用;
	private 前台公用任务 前台公用任务集;
    private 后台公用任务 后台公用任务集;
    private 越级操作任务 越级操作任务集;
    private 报表任务 报表任务集;
    private String 次数;

    //定义数据集
    ArrayList<Hashtable<String,String>> al_登录信息, al_定盘信息, al_分批赔率, al_录码模式, al_赔率设置, al_下注场景 = null;
    Hashtable<String,String> ht_登录信息, ht_定盘信息, ht_录码模式 = null;
    Hashtable<String,String> ht_大股东拦货金额,ht_股东拦货金额,ht_总代理拦货金额,ht_代理拦货金额 = null;
    Hashtable<String,String> ht_大股东设置,ht_股东设置,ht_总代理设置,ht_代理设置,ht_会员设置 = null;

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
        前台公用任务集 =new 前台公用任务(asBaseCore);
        后台公用任务集 =new 后台公用任务(asBaseCore);
        越级操作任务集 =new 越级操作任务(asBaseCore);
        报表任务集 = new 报表任务(asBaseCore);

        //读取数据
        excelFile = "datapool/ST_1.xls";
        tableName = "登录组一";
        次数 = "1";

        用例表.设置数据文件("datapool/ST_1.xls");
        al_登录信息 = 用例表.获取登录信息("登录组一");
        al_定盘信息 = 用例表.获取赔率设置("定盘信息三");
        al_分批赔率 = 用例表.获取赔率设置("分批赔率一");
        al_录码模式 = 用例表.获取会员资料("录码模式一");
        al_赔率设置 = 用例表.获取会员资料("赔率设置");
        al_下注场景 = 用例表.获取下注信息("下注场景一");
        ht_登录信息 = al_登录信息.get(0);
        ht_定盘信息 = al_定盘信息.get(0);
        ht_录码模式 = al_录码模式.get(0);
        ht_大股东拦货金额 = 用例表.获取各级设置信息("大股东拦货金额").get(0);
        ht_股东拦货金额 = 用例表.获取各级设置信息("股东拦货金额").get(0);
        ht_总代理拦货金额 = 用例表.获取各级设置信息("总代理拦货金额").get(0);
        ht_代理拦货金额 = 用例表.获取各级设置信息("代理拦货金额").get(0);
        ht_大股东设置 = 用例表.获取各级设置信息("大股东设置").get(0);
        ht_股东设置 = 用例表.获取各级设置信息("股东设置").get(0);
        ht_总代理设置 = 用例表.获取各级设置信息("总代理设置").get(0);
        ht_代理设置 = 用例表.获取各级设置信息("代理设置").get(0);
        ht_会员设置 = 用例表.获取各级设置信息("会员设置").get(0);
    }

    @Test
    public void 设置定盘数据测试(){
	    公用.总表数据初始化();
        公用.快速登录(ht_登录信息.get("总监"), ht_登录信息.get("总监密码"));
        后台公用任务集.点击菜单("设置");
        后台公用任务集.点击设置菜单("定盘");
        后台公用任务集.设置定盘数据(ht_定盘信息);
    }

    @Test(dependsOnMethods = { "设置定盘数据测试" })
    public void 关盘测试(){
        后台公用任务集.关盘("ccc0000");
    }

    @Test(dependsOnMethods = { "关盘测试" })
    public void 分批赔率测试(){
        后台公用任务集.点击设置菜单("定盘");
        后台公用任务集.点击分批赔率("口口XX");
        后台公用任务集.删除所有分批数据();
        后台公用任务集.新增分批赔率("10","1000","1000");
        后台公用任务集.设置分批数据(al_分批赔率);
    }

    @Test(dependsOnMethods = { "分批赔率测试" })
    public void 越级操作测试() throws Exception{
        后台公用任务集.点击菜单("越级操作");
        越级操作任务集.点击修改账号(ht_登录信息.get("大股东"),"大股东");
        越级操作任务集.修改账号信息(ht_大股东设置);
        asBaseCore.pause(1000);
        越级操作任务集.点击账号列表();

        越级操作任务集.点击账号(ht_登录信息.get("大股东"),"大股东");
        越级操作任务集.点击修改账号(ht_登录信息.get("股东"),"股东");
        越级操作任务集.修改账号信息(ht_股东设置);
        越级操作任务集.点击父账号(ht_登录信息.get("大股东"),"大股东");

        越级操作任务集.点击账号(ht_登录信息.get("股东"),"股东");
        越级操作任务集.点击修改账号(ht_登录信息.get("总代理"),"总代理");
        越级操作任务集.修改账号信息(ht_总代理设置);
        越级操作任务集.点击父账号(ht_登录信息.get("股东"),"股东");

        越级操作任务集.点击账号(ht_登录信息.get("总代理"),"总代理");
        越级操作任务集.点击修改账号(ht_登录信息.get("代理"),"代理");
        越级操作任务集.修改账号信息(ht_代理设置);
        越级操作任务集.点击父账号(ht_登录信息.get("总代理"),"总代理");

        越级操作任务集.点击账号(ht_登录信息.get("代理"),"代理");
        越级操作任务集.点击修改账号(ht_登录信息.get("会员"),"会员");
        越级操作任务集.修改账号信息(ht_会员设置);
        越级操作任务集.点击父账号(ht_登录信息.get("代理"),"代理");
    }

    @Test(dependsOnMethods = { "越级操作测试" })
    public void 拦货金额测试(){
        公用.跳转页(后台地址);
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

    @Test(dependsOnMethods = { "拦货金额测试" })
    public void 开盘测试(){
        公用.跳转页(后台地址);
        公用.快速登录(ht_登录信息.get("总监"), ht_登录信息.get("总监密码"));
        后台公用任务集.开盘("1234567","ccc0000");
        公用.等待开盘(11);
    }

    @Test(dependsOnMethods = { "开盘测试" })
    public void 快打下注测试(){
        公用.跳转页(前台地址);
        公用.快速登录(ht_登录信息.get("会员"),ht_登录信息.get("会员密码"));
        前台公用任务集.点击菜单("会员资料");
        前台公用任务集.验证赔率上限("口口XX");
        前台公用任务集.设置录码模式(ht_录码模式);
        前台公用任务集.设置交易回水(al_赔率设置,"口口XX");
        前台公用任务集.快打下注(al_下注场景,ht_登录信息.get("会员"),次数);
    }

    @Test(dependsOnMethods = { "开盘测试" })
    public void 结账前报表验证() throws Exception {
        公用.跳转页(后台地址);
        公用.快速登录(ht_登录信息.get("总监"),ht_登录信息.get("总监密码"));
        后台公用任务集.点击菜单("报表");
        报表任务集.验证报表(ht_登录信息.get("大股东"),"大股东",次数,false,false);
        报表任务集.点击账号名(ht_登录信息.get("大股东"),"大股东");
        报表任务集.验证报表(ht_登录信息.get("股东"),"股东",次数,false,false);
        报表任务集.点击账号名(ht_登录信息.get("股东"),"股东");
        报表任务集.验证报表(ht_登录信息.get("总代理"),"总代理",次数,false,false);
        报表任务集.点击账号名(ht_登录信息.get("总代理"),"总代理");
        报表任务集.验证报表(ht_登录信息.get("代理"),"代理",次数,false,false);
        报表任务集.点击账号名(ht_登录信息.get("代理"),"代理");
        报表任务集.验证报表(ht_登录信息.get("会员"),"会员",次数,false,false);
        报表任务集.点击账号名(ht_登录信息.get("会员"),"会员");
    }

    @Test(dependsOnMethods = { "结账前报表验证" })
    public void 关盘结账() throws Exception {
        后台公用任务集.点击菜单("设置");
        后台公用任务集.关盘("ccc0000");
        后台公用任务集.设置开奖号码("7654321");
    }

    @Test(dependsOnMethods = { "关盘结账" })
    public void 结账后报表验证() throws Exception {
        后台公用任务集.点击菜单("报表");
        报表任务集.验证报表(ht_登录信息.get("大股东"),"大股东",次数,true,false);
        报表任务集.点击账号名(ht_登录信息.get("大股东"),"大股东");
        报表任务集.验证报表(ht_登录信息.get("股东"),"股东",次数,true,false);
        报表任务集.点击账号名(ht_登录信息.get("股东"),"股东");
        报表任务集.验证报表(ht_登录信息.get("总代理"),"总代理",次数,true,false);
        报表任务集.点击账号名(ht_登录信息.get("总代理"),"总代理");
        报表任务集.验证报表(ht_登录信息.get("代理"),"代理",次数,true,false);
        报表任务集.点击账号名(ht_登录信息.get("代理"),"代理");
        报表任务集.验证报表(ht_登录信息.get("会员"),"会员",次数,true,false);
        报表任务集.点击账号名(ht_登录信息.get("会员"),"会员");
    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }

}
