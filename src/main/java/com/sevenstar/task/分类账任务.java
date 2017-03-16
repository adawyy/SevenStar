package com.sevenstar.task;

import com.mte.base.MteSenseCore;
import com.mte.util.WebDriverTable;
import com.relevantcodes.extentreports.ExtentTest;
import com.sevenstar.data.Datamgr;
import com.sevenstar.page.公用页;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 分类账任务 extends 公用任务 {

	private 公用页 公用 = null;

	Datamgr 总表 = new Datamgr();

	ArrayList<Hashtable<String,String>> al_赔率计算 = null;

	WebDriverTable WT = null;

	public 分类账任务(MteSenseCore senseCore) {
		super(senseCore);
		公用 = new 公用页(asCore);

		总表.设置数据文件("datapool/ST_汇总.xls");
		al_赔率计算 = 总表.获取数据("Summary","赔率计算");
		// TODO Auto-generated constructor stub
	}


	public void 点击账号(String 账号名){
	//	WT = new WebDriverTable(asCore,By.xpath("//*[@id='memberadmin']/div[3]/table"));
	}

	public void 点击修改账号(String 账号名){

	}

	public void 修改账号信息(Hashtable<String,String> ht){

	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
