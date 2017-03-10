package com.sevenstar.testcases;

import com.mte.base.MSAssert;
import com.sevenstar.task.前台公用任务;
import com.sevenstar.task.后台公用任务;
import com.sevenstar.task.测试任务;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 测试用例2 extends 七星彩基础用例 {

	private String testCaseName = this.getClass().getSimpleName();
	
	@BeforeClass
    public void setUp() throws Exception {
	    //用例初始化
        beforeClass();
        test = getExtent().startTest(testCaseName, "XXX");
        asBaseCore.initialTest(testCaseName,test);

    }

    @Test
    public void 测试方法3() throws Exception {


    }

    @Test
    public void 测试方法4() throws Exception {


    }

    @Test
    public void 测试方法5() throws Exception {


    }
    @AfterClass
    public void tearDown() throws Exception {

    }

}
