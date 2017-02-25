package com.sevenstar.task;

import com.mte.base.MteSenseBaseTask;
import com.mte.base.MteSenseCore;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Hashtable;

public class 后台公用任务 extends MteSenseBaseTask {


	public 后台公用任务(MteSenseCore senseCore) {
		super(senseCore);

		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void 点击菜单(String 选项){
		if(选项.equals("设置")){
			asCore.click(By.xpath("//*[@id='nav']/li[9]"));
		}
	}

	public void 点击设置菜单(String 选项){
		if(选项.equals("定盘")){
			asCore.click(By.xpath("//*[@id='guide_setting']/div[2]/a[4]"));
		}
	}

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
	}

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
			i++;
		}
		asCore.click(By.xpath("//input[@value='编辑']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}

	public void 赔率变动设置(Hashtable<String,String> ht){

	}





}
