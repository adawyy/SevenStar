package com.sevenstar.testcases;

import com.mte.base.MSAssert;
import com.sevenstar.task.公用任务;
import com.sevenstar.task.后台公用任务;
import com.sevenstar.task.越级操作任务;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 越级操作用例 extends 七星彩基础用例 {

    //定义任务
    private 公用任务 公用;
    private 后台公用任务 后台公用任务集;
    private 越级操作任务 越级操作任务集;

    //定义数据集
    ArrayList<Hashtable<String,String>> al_登录信息= null;
    Hashtable<String,String> ht_登录信息,ht_大股东设置,ht_股东设置,ht_总代理设置,ht_代理设置,ht_用户设置 = null;

    private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeClass
    public void setUp() throws Exception {
	    //用例初始化
        beforeClass();
        test = getExtent().startTest(testCaseName, "超级操作用例的描述");
        asBaseCore.initialTest(testCaseName,test);

        //任务列初始化
        公用 = new 公用任务(asBaseCore);
        后台公用任务集 =new 后台公用任务(asBaseCore);
        越级操作任务集 =new 越级操作任务(asBaseCore);

        //读取数据
        用例表.设置数据文件("datapool/ST_1.xls");
        al_登录信息 = 用例表.获取登录信息("登录组一");
        ht_登录信息 = al_登录信息.get(0);
        ht_大股东设置 = 用例表.获取各级设置信息("大股东设置").get(0);
        ht_股东设置 = 用例表.获取各级设置信息("股东设置").get(0);
        ht_总代理设置 = 用例表.获取各级设置信息("总代理设置").get(0);
        ht_代理设置 = 用例表.获取各级设置信息("代理设置").get(0);
        ht_用户设置 = 用例表.获取各级设置信息("用户设置").get(0);
    }

    @Test
    public void 超级操作测试() throws Exception {

        公用.快速登录(ht_登录信息.get("总监"),ht_登录信息.get("总监密码"));
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
        越级操作任务集.修改账号信息(ht_用户设置);
        越级操作任务集.点击父账号(ht_登录信息.get("代理"),"代理");

    }


    @AfterClass
    public void tearDown() throws Exception {
        MSAssert.softAssert.assertAll();
        afterClass();
    }

}
