package com.learnAT.martin.testcases;

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

    public boolean IsFindElement(By local) throws Exception{
	    asBaseCore.findElement(local);
        if(IsFindElement(local)){
            return true;
        }else{
            return  false;
        }
    }

    @Test
    public void 测试方法() throws Exception {

//        输入用户名、密码
        asBaseCore.sendKeys(By.xpath("//input[@id='Account']"),"director");
        asBaseCore.sendKeys(By.xpath("//input[@id='Password']"),"123fff");

//        点击登录
        asBaseCore.click(By.xpath("//input[@id='btn-submit']"));

//        快速进入/同意声
        asBaseCore.pause(2000);
        asBaseCore.click(By.xpath("//a[@id='btn_quickly']"));
        asBaseCore.click(By.xpath("//input[@id='agree']"));
//        点击设置/赔率变动设置
        asBaseCore.click(By.xpath("//ul[@id='nav']//span[text()='设置']"));
        asBaseCore.click(By.xpath("//div[@id='guide_setting']//a[text()='赔率变动设置']"));

//        添加12XX号码赔率变动
        asBaseCore.sendKeys(By.xpath("//input[@id='bet_no']"),"12XX");
        asBaseCore.sendKeys(By.xpath("//input[@id='odds_limit']"),"99");
        asBaseCore.sendKeys(By.xpath("//input[@id='one_item_limit']"),"500");

        asBaseCore.click(By.xpath("//input[@class='btn' and @value='提交']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));

//        添加34XX号码赔率变动
        asBaseCore.pause(2000);
        asBaseCore.sendKeys(By.xpath("//input[@id='bet_no']"),"34XX");
        asBaseCore.sendKeys(By.xpath("//input[@id='odds_limit']"),"95");
        asBaseCore.sendKeys(By.xpath("//input[@id='one_item_limit']"),"200");

        asBaseCore.click(By.xpath("//input[@class='btn' and @value='提交']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));

//        修改34XX上限
        asBaseCore.pause(2000);
        asBaseCore.clear(By.xpath("//tbody[@id='tbody']/tr[@bet_no='34XX']/td[5]/input"));
        asBaseCore.sendKeys(By.xpath("//tbody[@id='tbody']/tr[@bet_no='34XX']/td[5]/input"),"1000");
        asBaseCore.click(By.xpath("//form[@id='form3']//input[@value='编辑']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));


//        点击全选删除
        asBaseCore.pause(2000);
        asBaseCore.click(By.xpath("//form[@id='form3']//thead//input[@type='checkbox']"));
        asBaseCore.click(By.xpath("//form[@id='form3']//input[@value='删除']"));

        asBaseCore.click(By.xpath("//input[@value='确定']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
    }

    @AfterClass
    public void tearDown() throws Exception {

    }

}
