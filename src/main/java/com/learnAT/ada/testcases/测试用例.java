package com.learnAT.ada.testcases;

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
	    //登录
        asBaseCore.sendKeys(By.xpath("//input[@id='Account']"),"director");
        asBaseCore.sendKeys(By.xpath("//input[@id='Password']"),"123fff");
        asBaseCore.click(By.xpath("//input[@id='btn-submit']"));
        //快速进入
        asBaseCore.click(By.xpath("//a[@id='btn_quickly']"));
        //点击同意
        asBaseCore.click(By.xpath("//input[@id='agree']"));
        //点击设置
        asBaseCore.click(By.xpath("//ul[@id='nav']/li[9]/a/span"));
        //点击赔率变动设置
        asBaseCore.click(By.xpath("//div[@id='guide_setting']/div[2]/a[3]"));
        //输入号码12xx
        asBaseCore.sendKeys(By.xpath("//input[@id='bet_no']"),"12xx");
        asBaseCore.sendKeys(By.xpath("//input[@id='odds_limit']"),"99");
        asBaseCore.sendKeys(By.xpath("//input[@id='one_item_limit']"),"5000");
        asBaseCore.click(By.xpath("//div[@id='st_odds']/div[2]/form//input[@value='提交']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
        //输入号码56xx
        asBaseCore.sendKeys(By.xpath("//input[@id='bet_no']"),"56xx");
        asBaseCore.sendKeys(By.xpath("//input[@id='odds_limit']"),"99");
        asBaseCore.sendKeys(By.xpath("//input[@id='one_item_limit']"),"5000");
        asBaseCore.click(By.xpath("//div[@id='st_odds']/div[2]/form//input[@value='提交']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
        //修改上限
        asBaseCore.sendKeys(By.xpath(" //tbody[@id='tbody']/tr[2]/td[5]/input"),"1000");
        asBaseCore.click(By.xpath("//*[@id='form3']//input[@value='编辑']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
        //删除
        asBaseCore.click(By.xpath("//form[@id='form3']/div/table/thead/tr/td[1]/label/input"));
        asBaseCore.click(By.xpath("//form[@id='form3']//input[@value='删除']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
        asBaseCore.click(By.xpath("//input[@value='确定']"));
    }

    @AfterClass
    public void tearDown() throws Exception {

    }

}
