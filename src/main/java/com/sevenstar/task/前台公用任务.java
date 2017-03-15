package com.sevenstar.task;


import com.mte.base.MSAssert;
import com.mte.base.MteSenseCore;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 前台公用任务 extends 公用任务 {

	
	public 前台公用任务(MteSenseCore senseCore) {
		super(senseCore);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 点击总菜单
	 * @param 选项 换线路，下注明细，历史账单，会员资料。。。
	 */

	public void 点击菜单(String 选项){
		asCore.click(By.xpath("//*[@id='nav']//a/span[text()='"+选项+"']"));
	}

	/**
	 * 点击副菜单，选择下注方式
	 * @param 选项 二字定，快打，快选，赔率变动表。。。
	 */

	public void 点击副菜单(String 选项){
		asCore.click(By.xpath("//*[@id='subnav']/a[text()='"+选项+"']"));
	}

	/**
	 * 选择录码模式
	 * @param ht 详情查看ST_1.xls中会员资料-录码模式一
	 */

	public void 设置录码模式(Hashtable<String,String> ht){
		String tp1=ht.get("模式一");
		String tp2=ht.get("模式二");
		String tp3=ht.get("模式三");

//		System.out.println(tp1+tp2+tp3);

		if(tp1.equals("自动")){
			asCore.click(By.xpath("//input[@name='input_mode' and @value='0']"));
		}else{
			asCore.click(By.xpath("//input[@name='input_mode' and @value='1']"));
		}

		if(tp2.equals("小票打印")){
			asCore.click(By.xpath("//input[@name='show_mode' and @value='0']"));
		}else{
			asCore.click(By.xpath("//input[@name='show_mode' and @value='1']"));
		}

		if(tp3.equals("实际赔率")){
			asCore.click(By.xpath("//input[@name='odds_type' and @value='1']"));
			写入总表数据("赔率计算","实际(1)/转换赔率(0)","1");
		}else{
			asCore.click(By.xpath("//input[@name='odds_type' and @value='0']"));
			写入总表数据("赔率计算","实际(1)/转换赔率(0)","0");
		}

//		asCore.click(By.xpath("//input[@value='提交']"));
//		asCore.click(By.xpath("//input[@value='确定']"));
	}

	/**
	 * 设置所有类别中的交易回水
	 * @param al 详情查看ST_1.xls中会员资料-赔率设置
	 */

	public void 设置所有交易回水(ArrayList<Hashtable<String,String>> al){
		int size = al.size();
		int i = 0;
		while(i<size){
			asCore.selectByValue(By.xpath("//*[@id='1']"),al.get(i).get("交易回水"));
//			asCore.selectByValue(By.xpath("//*[@id='tbody']/tr[2]/td[8]/select"),al.get(i).get("赔率"));
			i++;
		}
		asCore.click(By.xpath("//input[@value='提交']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}

	/**
	 * 设置单个类别中的交易回水
	 * @param al 详情查看ST_1.xls中会员资料-赔率设置
	 */

	public void 设置交易回水(ArrayList<Hashtable<String,String>> al,String 类别){
		int size = al.size();
		int i = 0;
		while(i<size){
			if(al.get(i).get("类别").equals(类别)){
				asCore.selectByValue(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/select[@_name='return_water']"),al.get(i).get("交易回水"));
				写入总表数据("赔率计算","会员静态回水",al.get(i).get("交易回水"));
			}
			i++;
		}
		asCore.pause(500);
		String 会员静态赔率 = asCore.getSelectedText(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']]/select[@_name='odds_limit']"));
		写入总表数据("赔率计算","会员静态赔率",会员静态赔率);
		刷新总表数据();
		asCore.click(By.xpath("//input[@value='提交']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}

	/**
	 * 快打下注
	 * @param 下注信息 详情查看ST_1.xls中下注信息-下注场景一
	 * @param 次数_ 选择下几次注，不能超过excel中表列数
	 * @param 是否中奖 true 中奖
	*/

	public void 快打下注(ArrayList<Hashtable<String,String>> 下注信息,String 会员,String 次数_,boolean 是否中奖){
		double 最终赔率 = 0;
		点击副菜单("快打");
		int 次数 = Integer.parseInt(次数_);
		int i = 0;
		while(i<次数){

			String 下单号码 = 下注信息.get(i).get("号码");
			String 下注金额 = 下注信息.get(i).get("金额");

			//开始下注
			asCore.sendKeys(By.xpath("//*[@id='betno']"),下单号码);
			asCore.sendKeys(By.xpath("//*[@id='betmoney']"),下注金额);
			最终赔率 = Double.valueOf(asCore.getText(By.xpath("//*[@id='limit_odds']"),5));
			asCore.click(By.xpath("//input[@value='确认下注']"));
			asCore.clear(By.xpath("//*[@id='betno']"));
			asCore.clear(By.xpath("//*[@id='betmoney']"));
			asCore.pause(1000);

			//写入总表Summary下注金额
			写入总表数据("下注信息","下注金额",下注金额);
			刷新总表数据();

			//验证赔率
			验证最终赔率(String.valueOf(最终赔率));

			//累加盈亏总金额到SUmmary表
			盈亏累加(是否中奖);

			//写入已有金额，并清空已有金额
			刷新总表数据();
			al_下注信息=总表.获取数据("Summary","下注信息");
			String 已有金额 = al_下注信息.get(0).get("已有金额");
			String 上次下注金额 = al_下注信息.get(0).get("下注金额");
			String 新已有金额 = String.valueOf(Double.parseDouble(已有金额)+Double.parseDouble(上次下注金额));
			写入总表数据("下注信息","已有金额",新已有金额);
			刷新总表数据();

			//写入历史注单
			刷新总表数据();
			ht_总共回水总额 = 总表.获取数据("Summary","总共回水总额").get(0);
			String 单次回水总额 = ht_总共回水总额.get("总额");
			String 实收下线 = ht_总共回水总额.get("实收下线");
			String 会员最终回水比率 = al_赔率计算.get(0).get("会员最终回水比率");
			String 赔率上限 = al_赔率计算.get(0).get("定盘赔率上限");

			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","次数",String.valueOf(i+1),String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","会员",会员,String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","号码",下单号码,String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","下注金额",下注金额,String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","最终赔率",取小数点后两位(String.valueOf(最终赔率)),String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","会员最终回水比率",取小数点后两位(会员最终回水比率),String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","下线回水",取小数点后两位(单次回水总额),String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","实收下线",取小数点后两位(实收下线),String.valueOf(i+1));
			写入总表数据("./datapool/ST_汇总.xls","History","下注历史信息","赔率上限",赔率上限,String.valueOf(i+1));

			//下注金额清空
			写入总表数据("下注信息","下注金额","0");
			刷新总表数据();

			i++;
		}
	}

//	public void 注单记录(int 次数){
//		String 注单编号,号码,赔率,金额,状态;
//
//
//	}

	public void 盈亏累加(boolean 是否中奖){
		if(!是否中奖) {
			ht_未中奖此次占成计算盈亏 = 总表.获取数据("Summary", "未中奖此次占成计算盈亏").get(0);
			ht_未中奖总占成计算盈亏 = 总表.获取数据("Summary", "未中奖总占成计算盈亏").get(0);

			Double 此次总监盈亏 = 截取小数点后两位(ht_未中奖此次占成计算盈亏.get("总监"));
			Double 此次大股东盈亏 = 截取小数点后两位(ht_未中奖此次占成计算盈亏.get("大股东"));
			Double 此次股东盈亏 = 截取小数点后两位(ht_未中奖此次占成计算盈亏.get("股东"));
			Double 此次总代理盈亏 = 截取小数点后两位(ht_未中奖此次占成计算盈亏.get("总代理"));
			Double 此次代理盈亏 = 截取小数点后两位(ht_未中奖此次占成计算盈亏.get("代理"));
			Double 此次会员盈亏 = 截取小数点后两位(ht_未中奖此次占成计算盈亏.get("会员"));

			String 总监总盈亏 = String.valueOf(截取小数点后两位(ht_未中奖总占成计算盈亏.get("总监")) + 此次总监盈亏);
			String 大股东总盈亏 = String.valueOf(截取小数点后两位(ht_未中奖总占成计算盈亏.get("大股东")) + 此次大股东盈亏);
			String 股东总盈亏 = String.valueOf(截取小数点后两位(ht_未中奖总占成计算盈亏.get("股东")) + 此次股东盈亏);
			String 总代理总盈亏 = String.valueOf(截取小数点后两位(ht_未中奖总占成计算盈亏.get("总代理")) + 此次总代理盈亏);
			String 代理总盈亏 = String.valueOf(截取小数点后两位(ht_未中奖总占成计算盈亏.get("代理")) + 此次代理盈亏);
			String 会员总盈亏 = String.valueOf(截取小数点后两位(ht_未中奖总占成计算盈亏.get("会员")) + 此次会员盈亏);

			写入总表数据("未中奖总占成计算盈亏", "总监", 总监总盈亏);
			写入总表数据("未中奖总占成计算盈亏", "大股东", 大股东总盈亏);
			写入总表数据("未中奖总占成计算盈亏", "股东", 股东总盈亏);
			写入总表数据("未中奖总占成计算盈亏", "总代理", 总代理总盈亏);
			写入总表数据("未中奖总占成计算盈亏", "代理", 代理总盈亏);
//		写入总表数据("未中奖总占成计算盈亏","会员",会员总盈亏);
		}else {
			ht_中奖此次占成计算盈亏 = 总表.获取数据("Summary","中奖此次占成计算盈亏").get(0);
			ht_中奖总占成计算盈亏 = 总表.获取数据("Summary","中奖总占成计算盈亏").get(0);

			Double 此次总监盈亏 = 截取小数点后两位(ht_中奖此次占成计算盈亏.get("总监"));
			Double 此次大股东盈亏 = 截取小数点后两位(ht_中奖此次占成计算盈亏.get("大股东"));
			Double 此次股东盈亏 = 截取小数点后两位(ht_中奖此次占成计算盈亏.get("股东"));
			Double 此次总代理盈亏 = 截取小数点后两位(ht_中奖此次占成计算盈亏.get("总代理"));
			Double 此次代理盈亏 = 截取小数点后两位(ht_中奖此次占成计算盈亏.get("代理"));
			Double 此次会员盈亏 = 截取小数点后两位(ht_中奖此次占成计算盈亏.get("会员"));

			String 总监总盈亏 = String.valueOf(截取小数点后两位(ht_中奖总占成计算盈亏.get("总监")) + 此次总监盈亏);
			String 大股东总盈亏 = String.valueOf(截取小数点后两位(ht_中奖总占成计算盈亏.get("大股东")) + 此次大股东盈亏);
			String 股东总盈亏 = String.valueOf(截取小数点后两位(ht_中奖总占成计算盈亏.get("股东")) + 此次股东盈亏);
			String 总代理总盈亏 = String.valueOf(截取小数点后两位(ht_中奖总占成计算盈亏.get("总代理")) + 此次总代理盈亏);
			String 代理总盈亏 = String.valueOf(截取小数点后两位(ht_中奖总占成计算盈亏.get("代理")) + 此次代理盈亏);
			String 会员总盈亏 = String.valueOf(截取小数点后两位(ht_中奖总占成计算盈亏.get("会员")) + 此次会员盈亏);

			写入总表数据("中奖总占成计算盈亏", "总监", 总监总盈亏);
			写入总表数据("中奖总占成计算盈亏", "大股东", 大股东总盈亏);
			写入总表数据("中奖总占成计算盈亏", "股东", 股东总盈亏);
			写入总表数据("中奖总占成计算盈亏", "总代理", 总代理总盈亏);
			写入总表数据("中奖总占成计算盈亏", "代理", 代理总盈亏);
			写入总表数据("中奖总占成计算盈亏","会员",会员总盈亏);
		}

		刷新总表数据();
	}

	/**
	 * 验证赔率上限
	 * 将summary表中的代理赔率和会员资料中的上限赔率比较
	 */
	public void 验证赔率上限(String 类别){
		刷新总表数据();
		al_单次赔率 = 总表.获取数据("Summary","单次赔率");
		asCore.pause(200);
		String 代理单次赔率 = al_单次赔率.get(0).get("代理");
		String 赔率上限 = asCore.getText(By.xpath("//td[preceding-sibling::td[text()='"+类别+"']][2]"));
		MSAssert.verifyEqual(Double.parseDouble(赔率上限),截取小数点后两位(代理单次赔率),"验证单次赔率上限是否和Excel中的计算一致");
	}

	/**
	 * 验证最终赔率
	 * @param 实际最终赔率 80
	 */

	public void 验证最终赔率(String 实际最终赔率){
		刷新总表数据();
		al_赔率计算 = 总表.获取数据("Summary","赔率计算");

		MSAssert.verifyEqual(Double.parseDouble(实际最终赔率),截取小数点后两位(al_赔率计算.get(0).get("会员最终赔率")),"验证最终赔率是否正确");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
