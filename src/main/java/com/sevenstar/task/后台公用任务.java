package com.sevenstar.task;

import com.mte.base.MteSenseCore;
import com.mte.util.DateTimeUtil;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 后台公用任务 extends 公用任务 {


	public 后台公用任务(MteSenseCore senseCore) {
		super(senseCore);

		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * 点击后台的总菜单
	 * @param 选项 总货明细，贡献度，分类账，报表，开奖号码。。。
	 */

	public void 点击菜单(String 选项){
		asCore.click(By.xpath("//*[@id='nav']//a/span[text()='"+选项+"']"));
	}

	/**
	 * 点击后台的设置页菜单
	 * @param 选项 基本设置，开盘设置，赔率变动设置，定盘。。。
	 */

	public void 点击设置菜单(String 选项){
		asCore.click(By.xpath("//*[@id='guide_setting']/div[2]/a[text()='"+选项+"']"));
	}

	/**
	 * 设置后台的定盘数据
	 * @param ht 详情见ST_1.xls中的赔率设置-定盘信息[口口XX]
	 */

	public void 设置定盘数据(Hashtable<String,String> ht){
		String 类别=ht.get("类别");
		String 最小下注=ht.get("最小下注");
		String 赔率上限=ht.get("赔率上限");
		String 单注上限=ht.get("单注上限");
		String 单项上限=ht.get("单项上限");
		String 赔率下限=ht.get("赔率下限");
		String 排序=ht.get("排序");

		asCore.sendKeys(By.xpath("//*[@id='1']/td[2]/input[3]"),最小下注);
		asCore.sendKeys(By.xpath("//*[@id='1']/td[3]/input"),赔率上限);
		asCore.sendKeys(By.xpath("//*[@id='1']/td[4]/input"),单注上限);
		asCore.sendKeys(By.xpath("//*[@id='1']/td[5]/input"),单项上限);
		asCore.sendKeys(By.xpath("//*[@id='1']/td[6]/input"),赔率下限);

		//点提交
		asCore.click(By.xpath("//*[@id='st_handicap']/div[3]/form/div[2]/input"));
		asCore.click(By.xpath("//input[@type='button' and @value='确定']"));
		写入总表数据("赔率计算","定盘赔率上限",赔率上限);
	}

	/**
	 * 设置后台的分批赔率
	 * @param al 详情见ST_1.xls中的赔率设置-分批赔率[口口XX]
	 */

	public void 设置分批数据(ArrayList<Hashtable<String,String>> al){
		int size = al.size();
		asCore.pause(1000);
		asCore.click(By.xpath("//*[@id='1']/td[8]/a[2]"));
		asCore.click(By.xpath("//*[@id='selectAll']"));
		int i=0;
		while(i<size){
			if(!(i==size-1)){
				asCore.sendKeys(By.xpath("//input[@name='end_money"+i+"']"),al.get(i).get("截止金额"));
			}
			asCore.sendKeys(By.xpath("//input[@name='odds_limit"+i+"']"),al.get(i).get("赔率上限"));
			if(i==0){
				写入总表数据("赔率计算","总监赔率",al.get(i).get("赔率上限"));
			}
			i++;
		}
		asCore.click(By.xpath("//input[@value='编辑']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}

	/**
	 * 关盘
	 * @param 关盘密码 输入关盘密码 ccc0000
	 */

	public void 关盘(String 关盘密码){
		if(asCore.getText(By.xpath("//*[@id='systime']")).equals("已封盘")){
			//什么都不做
		}else {
			点击菜单("设置");
			点击设置菜单("开盘设置");
			asCore.click(By.xpath("//*[@id='bd_serverinfo']/table/tbody/tr/td[7]/label[2]/input"));
			asCore.sendKeys(By.xpath("//input[@name='open_pwd']"), 关盘密码);
			asCore.click(By.xpath("//*[@id='btnOpenClose']"));
			asCore.click(By.xpath("//input[@value='确定']"));
			if (asCore.isTextContentsDisplayed("当前期尚未开盘，不能进行关盘")) {
				asCore.click(By.xpath("//input[@value='确定']"));
			}
		}
	}

	/**
	 * 开盘 默认设置当前时间一分钟后开盘
	 * @param 七位号码 1234567
	 * @param 开盘密码 ccc0000
	 */

	//默认设置一分钟后开盘
	public void 开盘(String 七位号码,String 开盘密码){
		设置开奖号码(七位号码);
		开盘设置(开盘密码);
	}

	/**
	 * 设置开奖号码，并结账
	 * @param 七位号码 1234567
	 */

	public void 设置开奖号码(String 七位号码){
		点击菜单("开奖号码");
		asCore.sendKeys(By.xpath("//*[@id='thousand_no']"),七位号码.substring(0,1));
		asCore.sendKeys(By.xpath("//*[@id='hundred_no']"),七位号码.substring(1,2));
		asCore.sendKeys(By.xpath("//*[@id='ten_no']"),七位号码.substring(2,3));
		asCore.sendKeys(By.xpath("//*[@id='one_no']"),七位号码.substring(3,4));
		asCore.sendKeys(By.xpath("//*[@id='ball5']"),七位号码.substring(4,5));
		asCore.sendKeys(By.xpath("//*[@id='ball6']"),七位号码.substring(5,6));
		asCore.sendKeys(By.xpath("//*[@id='ball7']"),七位号码.substring(6,7));
		asCore.click(By.xpath("//*[@id='btnCheckout']"));
		asCore.click(By.xpath("//input[@value='确定']"));
		asCore.pause(2000);
		asCore.click(By.xpath("//input[@value='确定']"));
		asCore.click(By.xpath("//*[@id='btnShowFrontendPeriod']"));
		asCore.click(By.xpath("//input[@value='确定']"));
		asCore.pause(2000);
		asCore.click(By.xpath("//input[@value='确定']"));
	}

	/**
	 * 设置开盘选项
	 * @param 开盘密码 ccc0000
	 */

	public void 开盘设置(String 开盘密码){
		点击菜单("设置");
		点击设置菜单("开盘设置");
		//提交期号
		asCore.sendKeys(By.xpath("//*[@id='form2']/table/tbody/tr/td[5]/input"),开盘密码);
		//等待期号出现
		asCore.pause(2000);
		asCore.click(By.xpath("//*[@id='btnCreatenew']"));
		asCore.click(By.xpath("//input[@value='确定']"));
		//等待新建期号
		asCore.pause(2000);
		asCore.click(By.xpath("//input[@value='确定']"));
		//等待开盘页面出现
		asCore.pause(4000);
		//开盘
		asCore.sendKeys(By.xpath("//input[@name='open_datetime']"), DateTimeUtil.addMinutesByFormatter(1,"yyyy-MM-dd HH:mm:ss"));
		asCore.sendKeys(By.xpath("//input[@name='close_datetime']"), DateTimeUtil.addDaysByFormatter(1,"yyyy-MM-dd HH:mm:ss"));
		asCore.click(By.xpath("//input[@name='is_open']"));
		asCore.sendKeys(By.xpath("//*[@id='bd_serverinfo']/table/tbody/tr/td[9]/input"),开盘密码);
		asCore.click(By.xpath("//*[@id='btnOpenClose']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}



	public void 赔率变动设置(Hashtable<String,String> ht){

	}





}
