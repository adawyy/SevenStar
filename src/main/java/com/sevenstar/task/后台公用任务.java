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
		asCore.pause(500);
		asCore.click(By.xpath("//*[@id='nav']//a/span[text()='"+选项+"']"));
	}

	/**
	 * 点击后台的设置页菜单
	 * @param 选项 基本设置，开盘设置，赔率变动设置，定盘。。。
	 */

	public void 点击设置菜单(String 选项){
		asCore.pause(500);
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

		asCore.sendKeys(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/input[@_name='min_bet']"),最小下注);
		asCore.sendKeys(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/input[@_name='odds_max_limit']"),赔率上限);
		asCore.sendKeys(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/input[@_name='one_bet_limit']"),单注上限);
		asCore.sendKeys(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/input[@_name='one_item_limit']"),单项上限);
		asCore.sendKeys(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/input[@_name='odds_min_limit']"),赔率下限);

		//点提交
//		asCore.click(By.xpath("//input[@value='提交' and @type='submit']"));
		asCore.click(By.xpath("//*[@id='st_handicap']/div[3]/form/div[2]/input"));
		asCore.click(By.xpath("//input[@type='button' and @value='确定']"));
		写入总表数据("赔率计算","定盘赔率上限",赔率上限);

		写入总表数据("定盘信息","类别",类别);
		写入总表数据("定盘信息","最小下注",最小下注);
		写入总表数据("定盘信息","赔率上限",赔率上限);
		写入总表数据("定盘信息","单注上限",单注上限);
		写入总表数据("定盘信息","赔率下限",赔率下限);

	}

	/**
	 * 根据类别点击分批赔率
	 * @param 类别 口口XX
	 */

	public void 点击分批赔率(String 类别){
		asCore.click(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/a[2]"));
	}

	/**
	 * 删除所有分批数据
	 */
	public void 删除所有分批数据(){
		asCore.click(By.xpath("//*[@id='selectAll']"));
		asCore.click(By.xpath("//input[@value='删除']"));
		asCore.click(By.xpath("//input[@value='确定']"));
		//操作成功!
		asCore.click(By.xpath("//input[@value='确定']"));
		asCore.pause(500);
		asCore.click(By.xpath("//input[@value='新增分批']"));
	}

	/**
	 * 新增分批赔率
	 * @param 批次 10
	 * @param 第一批金额 1000
	 * @param 循环递增 500
	 */

	public void 新增分批赔率(String 批次,String 第一批金额,String 循环递增){
		//设置10批
		asCore.sendKeys(By.xpath("//input[@name='batch_count']"),批次);
		//设置第一批截止金额放
		asCore.sendKeys(By.xpath("//input[@name='first_batch_end_money']"),第一批金额);
		//设置下批截止金额循环递增
		asCore.sendKeys(By.xpath("//input[@name='increment_money']"),循环递增);

		asCore.click(By.xpath("//input[@value='提交']"));
		//保存成功
		asCore.click(By.xpath("//input[@value='确定']"));
	}

	/**
	 * 设置后台的分批赔率
	 * @param al 详情见ST_1.xls中的赔率设置-分批赔率[口口XX]
	 */

	public void 设置分批数据(ArrayList<Hashtable<String,String>> al){
		int size = al.size();
		String 类别 = al.get(0).get("类别");
		asCore.pause(1000);
//		asCore.click(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/a[2]"));
		asCore.click(By.xpath("//*[@id='selectAll']"));
		asCore.pause(500);
		asCore.click(By.xpath("//*[@id='selectAll']"));
		int i=0;
		while(i<size){
			if(al.get(i).get("是否放出").equals("是")){
				asCore.click(By.xpath("//input[@name='is_use"+i+"']"));
			}

			asCore.sendKeys(By.xpath("//input[@name='end_money"+i+"']"),al.get(i).get("截止金额"));

			asCore.sendKeys(By.xpath("//input[@name='odds_limit"+i+"']"),al.get(i).get("赔率上限"));
//			if(i==0){
//				写入总表数据("赔率计算","总监赔率",al.get(i).get("赔率上限"));
//			}
			写入总表行数据("分批赔率","是否放出",al.get(i).get("是否放出"),String.valueOf(i+1));
			写入总表行数据("分批赔率","截止金额",al.get(i).get("截止金额"),String.valueOf(i+1));
			写入总表行数据("分批赔率","赔率上限",al.get(i).get("赔率上限"),String.valueOf(i+1));
			i++;
		}
		asCore.click(By.xpath("//input[@value='编辑']"));
		asCore.click(By.xpath("//input[@value='确定']"));
		刷新总表数据();
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
		asCore.sendKeys(By.xpath("//input[@name='open_datetime']"), DateTimeUtil.addSecondsByFormatter(10,"yyyy-MM-dd HH:mm:ss"));
		asCore.sendKeys(By.xpath("//input[@name='close_datetime']"), DateTimeUtil.addDaysByFormatter(1,"yyyy-MM-dd HH:mm:ss"));
		asCore.click(By.xpath("//input[@name='is_open']"));
		asCore.sendKeys(By.xpath("//*[@id='bd_serverinfo']/table/tbody/tr/td[9]/input"),开盘密码);
		asCore.click(By.xpath("//*[@id='btnOpenClose']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}

	/**
	 * 设置各级的拦货金额
	 * @param ht 设置summary表中的具体拦货金额数据 方便计算
	 */

	public void 设置拦货金额(Hashtable<String,String> ht){
		String 角色 = ht.get("角色");
		String 类别 = ht.get("类别");
		String 拦货金额 = ht.get("拦货金额");
		String 贡献度 = ht.get("贡献度占成上限");
		asCore.selectByValue(By.xpath("//select[@name='contribution_rate']"),贡献度);
		asCore.sendKeys(By.xpath("//tbody[@id='tbody']//td[preceding-sibling::td[text()='"+类别+"']]/input[@_name='hold_money']"),拦货金额);
		asCore.click(By.xpath("//*[@id='form']//input[@value='提交']"));
//		"如果庄家先吃满，则不以所设成数来分配，以实际分配到拦货中金额为准，你同意吗？"
		asCore.click(By.xpath("//input[@value='确定']"));
		//保存成功!
		asCore.click(By.xpath("//input[@value='确定']"));
		写入总表数据("拦货金额上限",角色,拦货金额);
		刷新总表数据();
	}



	public void 赔率变动设置(Hashtable<String,String> ht){

	}





}
