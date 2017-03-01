package com.sevenstar.task;


import com.mte.base.MSAssert;
import com.mte.base.MteSenseCore;
import org.apache.xpath.SourceTree;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Hashtable;

public class 前台公用任务 extends 公用任务 {

	
	public 前台公用任务(MteSenseCore senseCore) {
		super(senseCore);
		// TODO Auto-generated constructor stub
	}

	public void 点击菜单(String 选项){
		asCore.click(By.xpath("//*[@id='nav']//a/span[text()='"+选项+"']"));
	}

	public void 点击副菜单(String 选项){
		asCore.click(By.xpath("//*[@id='subnav']/a[text()='"+选项+"']"));
	}

	public void 设置录码模式(Hashtable<String,String> ht){
		String tp1=ht.get("模式一");
		String tp2=ht.get("模式二");
		String tp3=ht.get("模式三");

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

	public void 设置交易回水(ArrayList<Hashtable<String,String>> al,String 类别){
		int size = al.size();
		int i = 0;
		while(i<size){
			if(al.get(i).get("类别").equals(类别)){
//				System.out.println("循环设置交易回水"+i);
				asCore.selectByValue(By.xpath("//*[@id='1']"),al.get(i).get("交易回水"));
				写入总表数据("赔率计算","会员静态回水",al.get(i).get("交易回水"));
//				asCore.selectByValue(By.xpath("//*[@id='tbody']/tr[2]/td[8]/select"),al.get(i).get("赔率"));
			}
			i++;
		}
		asCore.pause(500);
		String 会员静态赔率 = asCore.getSelectedText(By.xpath("//*[@id='tbody']/tr[2]/td[8]/select"));
		写入总表数据("赔率计算","会员静态赔率",会员静态赔率);
		刷新总表数据();
		asCore.click(By.xpath("//input[@value='提交']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}
	
	public void 快打下注(ArrayList<Hashtable<String,String>> 下注信息,int 次数){
		double finalodds = 0;
		点击副菜单("快打");

		int i = 0;
		while(i<次数){
			asCore.sendKeys(By.xpath("//*[@id='betno']"),下注信息.get(i).get("号码"));
			asCore.sendKeys(By.xpath("//*[@id='betmoney']"),下注信息.get(i).get("金额"));
			finalodds = Double.valueOf(asCore.getText(By.xpath("//*[@id='limit_odds']"),5));
			asCore.click(By.xpath("//input[@value='确认下注']"));
			asCore.clear(By.xpath("//*[@id='betno']"));
			asCore.clear(By.xpath("//*[@id='betmoney']"));
			asCore.pause(500);
			System.out.println("用户最终赔率为:"+finalodds);
			System.out.println("预计用户最终赔率为:"+al_赔率计算.get(0).get("会员最终赔率"));
			MSAssert.verifyEqual(String.valueOf(finalodds),al_赔率计算.get(0).get("会员最终赔率"),"验证最终赔率是否正确");
			i++;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
