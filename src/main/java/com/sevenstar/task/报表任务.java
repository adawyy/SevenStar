package com.sevenstar.task;

import com.mte.base.MSAssert;
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

public class 报表任务 extends 公用任务 {

	private 公用页 公用 = null;
	private 前台公用任务 前台任务 = null;

	Datamgr 总表 = new Datamgr();

	ArrayList<Hashtable<String,String>> al_赔率计算 = null;
	Hashtable<String,String> ht_总拦货金额,ht_下级总投金额,ht_总总投金额,ht_总计算赚水,ht_各级总赚水= null;
	Hashtable<String,String> ht_未中奖总占成盈亏,ht_未中奖总盈亏,ht_未中奖各级盈亏,ht_未中奖总占成计算盈亏= null;
	Hashtable<String,String> ht_中奖总占成盈亏,ht_中奖总盈亏,ht_中奖各级盈亏,ht_中奖总占成计算盈亏= null;

	/**
	 * 总货明细页面的任务定义
	 * @param senseCore
	 * driver
	 */

	public 报表任务(MteSenseCore senseCore) {
		super(senseCore);
		公用 = new 公用页(asCore);
		前台任务 = new 前台公用任务(asCore);

		总表.设置数据文件("datapool/ST_汇总.xls");

		// TODO Auto-generated constructor stub

	}

	public By get_会员笔数(String 名称){
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][1]");
	}

	public By get_会员总投(String 名称){
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][2]");
	}

	public By get_会员盈亏(String 名称){
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][3]");
	}

	public By get_角色总投(String 名称){
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][4]");
	}

	public By get_角色盈亏(String 名称){
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][5]");
	}

	public By get_上级占成总额(String 名称,boolean 是否会员){
		if(是否会员){
			return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][4]");
		}
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][6]");
	}

	public By get_上级占成盈亏(String 名称,boolean 是否会员){
		if(是否会员){
			return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][5]");
		}
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][7]");
	}

	public By get_上级赚水(String 名称,boolean 是否会员){
		if(是否会员){
			return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][6]");
		}
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][8]");
	}

	public By get_上级总盈亏(String 名称,boolean 是否会员){
		if(是否会员){
			return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][7]");
		}
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][9]");
	}

	public By get_上上级总投(String 名称,boolean 是否会员){
	//	System.out.println(是否会员);
		if(是否会员){
			return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][8]");
		}
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][10]");
	}

	public By get_上上级盈亏(String 名称,boolean 是否会员){
		if(是否会员){
			return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][9]");
		}
		return By.xpath("//td[preceding-sibling::td/a[contains(text(),'"+名称+"')]][11]");
	}

	public void 验证报表(String 账号名,String 角色,String 笔数,boolean 是否结账,boolean 是否中奖){
		asCore.log_Task("准备验证报表，是否结账："+是否结账+" 是否中奖："+是否中奖);
		刷新总表数据();
		al_赔率计算 = 总表.获取数据("Summary","赔率计算");
		ht_总拦货金额 = 总表.获取数据("Summary","总拦货金额").get(0);
		ht_下级总投金额 = 总表.获取数据("Summary","下级总投金额").get(0);
		ht_总总投金额 = 总表.获取数据("Summary","总总投金额").get(0);
		ht_总计算赚水 = 总表.获取数据("Summary","总计算赚水").get(0);
		ht_各级总赚水 = 总表.获取数据("Summary","各级总赚水").get(0);


		String ac_会员笔数 = asCore.getText(get_会员笔数(账号名));
		String ac_会员总投 = asCore.getText(get_会员总投(账号名));
		String ac_会员盈亏 = asCore.getText(get_会员盈亏(账号名));

		String ac_本级总投 = asCore.getText(get_角色总投(账号名));
		String ac_本级盈亏 = asCore.getText(get_角色盈亏(账号名));

		String ac_上级占成总额 = asCore.getText(get_上级占成总额(账号名,角色.equals("会员")));
		String ac_上级占成盈亏 = asCore.getText(get_上级占成盈亏(账号名,角色.equals("会员")));
		String ac_上级赚水 = asCore.getText(get_上级赚水(账号名,角色.equals("会员")));
		String ac_上级总盈亏 = asCore.getText(get_上级总盈亏(账号名,角色.equals("会员")));

		String ac_上上级总投 = asCore.getText(get_上上级总投(账号名,角色.equals("会员")));
		String ac_上上级盈亏 = asCore.getText(get_上上级盈亏(账号名,角色.equals("会员")));

		String 角色上级 = null,角色上上级 = null;

		if(角色.equals("大股东")){
			角色上级 = "总监";
			角色上上级 = "出货";
		}else if(角色.equals("股东")){
			角色上级 = "大股东";
			角色上上级 = "总监";
		}else if(角色.equals("总代理")){
			角色上级 = "股东";
			角色上上级 = "大股东";
		}else if(角色.equals("代理")){
			角色上级 = "总代理";
			角色上上级 = "股东";
		}else if(角色.equals("会员")){
			角色上级 = "代理";
			角色上上级 = "总代理";
		}

		String ex_会员笔数 = 笔数;
		String ex_会员总投 = ht_总拦货金额.get("总值加此次");
		String ex_会员盈亏 = null;

		String ex_本级总投 = ht_下级总投金额.get(角色);
		String ex_本级盈亏 = null;

//		System.out.println(ex_本级总投);

		String ex_上级占成总额 = ht_总拦货金额.get(角色上级);
		String ex_上级占成盈亏 = null;
		String ex_上级赚水 = ht_各级总赚水.get(角色上级);
		String ex_上级盈亏 = null;

//		System.out.println(ex_上级赚水);
		String ex_上上级总投 = ht_总总投金额.get(角色上上级);
		String ex_上上级盈亏 = null;

		if(是否结账){

			if(是否中奖){
				ht_中奖总占成盈亏 = 总表.获取数据("Summary","中奖总占成盈亏").get(0);
				ht_中奖总盈亏 = 总表.获取数据("Summary","中奖总盈亏").get(0);
				ht_中奖各级盈亏 = 总表.获取数据("Summary","中奖各级盈亏").get(0);
				ht_中奖总占成计算盈亏 = 总表.获取数据("Summary","中奖总占成计算盈亏").get(0);

				ex_会员盈亏 = ht_中奖总占成盈亏.get("会员");

				if(Double.parseDouble(ht_中奖各级盈亏.get(角色上级))<0){
					ex_本级盈亏 = String.valueOf(-Double.parseDouble(ht_中奖各级盈亏.get(角色上级)));
				}else{
					ex_本级盈亏 = "-"+ht_中奖各级盈亏.get(角色上级);
				}

				ex_上级占成盈亏 = ht_中奖总占成盈亏.get(角色上级);
				ex_上级盈亏 = ht_中奖总盈亏.get(角色上级);
				ex_上上级盈亏 = ht_中奖各级盈亏.get(角色上上级);

			}else{
				ht_未中奖总占成盈亏 = 总表.获取数据("Summary","未中奖总占成盈亏").get(0);
				ht_未中奖总盈亏 = 总表.获取数据("Summary","未中奖总盈亏").get(0);
				ht_未中奖各级盈亏 = 总表.获取数据("Summary","未中奖各级盈亏").get(0);
				ht_未中奖总占成计算盈亏 = 总表.获取数据("Summary","未中奖总占成计算盈亏").get(0);

				ex_会员盈亏 = "-"+String.valueOf(Double.parseDouble(ht_未中奖总占成计算盈亏.get("总监"))
						+ Double.parseDouble(ht_未中奖总占成计算盈亏.get("大股东"))
						+ Double.parseDouble(ht_未中奖总占成计算盈亏.get("股东"))
						+ Double.parseDouble(ht_未中奖总占成计算盈亏.get("总代理"))
						+ Double.parseDouble(ht_未中奖总占成计算盈亏.get("代理"))
						+ Double.parseDouble(ht_总计算赚水.get("赚水总金额")));

				ex_本级盈亏 = "-"+ht_未中奖各级盈亏.get(角色上级);
				ex_上级占成盈亏 = ht_未中奖总占成盈亏.get(角色上级);
				ex_上级盈亏 = ht_未中奖总盈亏.get(角色上级);
				ex_上上级盈亏 = ht_未中奖各级盈亏.get(角色上上级);
			}
		}

		MSAssert.verifyEqual(ac_会员笔数, ex_会员笔数, "检查会员笔数");
		MSAssert.verifyEqual(ac_会员总投, 取整(ex_会员总投), "检查会员总投");

		if(!角色.equals("会员")){
			MSAssert.verifyEqual(ac_本级总投, 取整(ex_本级总投), "检查" + 角色 + "总投");
		}
		MSAssert.verifyEqual(ac_上级占成总额, 取整(ex_上级占成总额), "检查上级" + 角色上级 + "占成金额");
		MSAssert.verifyEqual(ac_上级赚水, 取整(ex_上级赚水), "检查上级" + 角色上级 + "赚水");
		MSAssert.verifyEqual(ac_上上级总投, 取整(ex_上上级总投), "检查上上级" + 角色上上级 + "总投");

		if(是否结账) {
			MSAssert.verifyEqual(ac_会员盈亏, 取整(ex_会员盈亏), "检查会员盈亏");
			if(!角色.equals("会员")){
				MSAssert.verifyEqual(ac_本级盈亏, 取整(ex_本级盈亏), "检查本级" + 角色 + "盈亏");
			}
			if(角色.equals("大股东")){
				MSAssert.verifyEqual(ac_上级占成盈亏, 取整(ex_上级盈亏), "上级" + 角色上级 + "占成盈亏");
				MSAssert.verifyEqual(ac_上级总盈亏,取整(ex_上级占成盈亏), "检查上级" + 角色上级 + "盈亏");
			}else {
				MSAssert.verifyEqual(ac_上级占成盈亏, 取整(ex_上级占成盈亏), "上级" + 角色上级 + "占成盈亏");
				MSAssert.verifyEqual(ac_上级总盈亏, 取整(ex_上级盈亏), "检查上级" + 角色上级 + "盈亏");
			}
			MSAssert.verifyEqual(ac_上上级盈亏, 取整(ex_上上级盈亏), "检查上上级" + 角色上上级 + "盈亏");
		}
	}

	public void 点击账号名(String 账号名,String 角色){
		if(角色.equals(asCore.getText(By.xpath("//*[@id='report']/div[2]/table/thead/tr[2]/td[1]")))){
			asCore.click(By.xpath("//*[@id='report']/div[2]/table/tbody/tr[1]/td[1]/a[contains(text(),'"+账号名+"')]"));
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
