package com.learnAT.frank.testcases;

import com.sevenstar.testcases.七星彩基础用例;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 测试用例 extends 七星彩基础用例 {

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeClass
    public void setUp() throws Exception {
	    //用例初始化
        beforeClass();
        test = getExtent().startTest(testCaseName, "XXX");
        asBaseCore.initialTest(testCaseName,test);

    }
    @Test
    public void 测试方法() throws Exception {

	    //输入账号和密码
        asBaseCore.sendKeys(By.xpath("//input[@id='Account']"),"director");
        asBaseCore.sendKeys(By.xpath("//input[@id='Password']"),"123fff");
        //点击登录
        asBaseCore.click(By.xpath("//input[@id='btn-submit']"));
        //点击快速进入
        asBaseCore.pause(2000);
        asBaseCore.click(By.xpath("//a[@id='btn_quickly']"));
        //点击同意进入系统
        asBaseCore.click(By.xpath("//input[@id='agree']"));
        //点击设置
        asBaseCore.click(By.xpath("//ul[@id='nav']//span[text()='设置']"));
        //点击赔率变动设置
        asBaseCore.click(By.xpath("//div[@id='guide_setting']//a[text()='赔率变动设置']"));
        //输入热门号码12xx及其赔率上限99，单项上限5000
        asBaseCore.pause(2000);
        asBaseCore.sendKeys(By.xpath("//input[@id='bet_no']"),"12xx");
        asBaseCore.sendKeys(By.xpath("//input[@id='odds_limit']"),"99");
        asBaseCore.sendKeys(By.xpath("//input[@id='one_item_limit']"),"5000");
        //点击提交/确定
        asBaseCore.click(By.xpath("//div[@id='st_odds']//input[@value='提交']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
        //输入热门号码34xx及其赔率上限99，单项上限5000
        asBaseCore.pause(2000);
        asBaseCore.sendKeys(By.xpath("//input[@id='bet_no']"),"34xx");
        asBaseCore.sendKeys(By.xpath("//input[@id='odds_limit']"),"99");
        asBaseCore.sendKeys(By.xpath("//input[@id='one_item_limit']"),"5000");
        //点击提交/确定
        asBaseCore.click(By.xpath("//div[@id='st_odds']//input[@value='提交']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
        //清除34xx的单项上限5000
        asBaseCore.pause(2000);
        asBaseCore.clear(By.xpath("//tbody[@id='tbody']/tr[@bet_no='34XX']/td[5]/input")); // 两个框都清除了
        //输入34xx新的单项上限10000
        asBaseCore.sendKeys(By.xpath("//tbody[@id='tbody']/tr[@bet_no='34XX']/td[5]/input"),"10000");
        //点击编辑/确定
        asBaseCore.click(By.xpath("//form[@id='form3']//input[@value='编辑']"));
        asBaseCore.click(By.xpath(".//input[@value='确定']"));
        //选择全部删除
        asBaseCore.click(By.xpath("//form[@id='form3']//input[@type='checkbox']"));
        //点击删除/确定
        asBaseCore.click(By.xpath("//form[@id='form3']//input[@value='删除']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));

    }

    @AfterClass
    public void tearDown() throws Exception {

    }

}
