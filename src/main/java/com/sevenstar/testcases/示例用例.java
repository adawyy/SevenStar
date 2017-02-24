package com.sevenstar.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sevenstar.task.前台登录任务;

public class 示例用例 extends 七星彩基础用例 {

	private 前台登录任务 登录任务集;
	
	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeTest
    public void setUp() throws Exception {
        beforeClass();
        test = extent.startTest(testCaseName, "一个验证登录的测试用例");
        asBaseCore.initialTest(testCaseName,test);
        登录任务集 =new 前台登录任务(asBaseCore);
//        feedback.NavTo("IBMer Hub");
    }

    @Test
    public void testMain() throws Exception {
        登录任务集.会员成功登录(user,name);
    }

    @AfterTest
    public void tearDown() throws Exception {
        afterClass();
    }


}
