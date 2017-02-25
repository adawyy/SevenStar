package com.sevenstar.task;


import com.mte.base.MteSenseCore;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Hashtable;

public class 前台公用任务 extends 公用任务 {

	
	public 前台公用任务(MteSenseCore senseCore) {
		super(senseCore);
		// TODO Auto-generated constructor stub
	}

	public void 点击菜单(String 选项){
		if(选项.equals("会员资料")){
			asCore.click(By.xpath("//*[@id='nav']/li[4]/a"));
		}
	}

	public void 点击副菜单(String 选项){
		if(选项.equals("快打")){
			asCore.click(By.xpath("//*[@id='subnav']/a[2]"));
		}
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
		}else{
			asCore.click(By.xpath("//input[@name='odds_type' and @value='0']"));
		}
	}

	public void 设置交易回水(ArrayList<Hashtable<String,String>> al){
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
//				asCore.selectByValue(By.xpath("//*[@id='tbody']/tr[2]/td[8]/select"),al.get(i).get("赔率"));
			}
			i++;
		}
		asCore.click(By.xpath("//input[@value='提交']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}
	
	public void 快打下注(ArrayList<Hashtable<String,String>> al){
		点击副菜单("快打");
		asCore.sendKeys(By.xpath("//*[@id='betno']"),al.get(0).get("号码"));
		asCore.sendKeys(By.xpath("//*[@id='betmoney']"),al.get(0).get("金额"));
		asCore.click(By.xpath("//input[@value='确认下注']"));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
