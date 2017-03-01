package com.sevenstar.task;

import com.mte.util.datamgr.ExcelFile;
import com.sevenstar.data.Datamgr;
import com.sevenstar.page.公用页;
import org.openqa.selenium.By;
import com.mte.base.MteSenseBaseTask;
import com.mte.base.MteSenseCore;

import java.util.ArrayList;
import java.util.Hashtable;

public class 公用任务 extends MteSenseBaseTask {

	private 公用页 公用 = null;

	Datamgr 总表 = new Datamgr();

	ArrayList<Hashtable<String,String>> al_赔率计算 = null;

	public 公用任务(MteSenseCore senseCore) {
		super(senseCore);
		公用 = new 公用页(asCore);

		总表.设置数据文件("datapool/ST_汇总.xls");
		al_赔率计算 = 总表.获取数据("Summary","赔率计算");
		// TODO Auto-generated constructor stub
	}

	public void 跳转页(String Url){
		asCore.get(Url,2);
	}

	public void 登录(String 用户名,String 密码){
		asCore.sendKeys(公用.用户名输入框(), 用户名);
		asCore.sendKeys(公用.密码输入框(), 密码);
		asCore.click(公用.登录按钮());
	}

	public void 快速登录(String 用户名,String 密码){
		登录(用户名,密码);
		asCore.skipClick(公用.快速进入按钮(),2);
		asCore.click(公用.同意按钮());
//		asCore.pause(1500);
//		asCore.skipClick(公用.右下弹框关闭按钮(),2);
//		asCore.pause(1000);
		asCore.waitClickAbleToClick(公用.右下弹框关闭按钮(),5);
	}

	public void 写入总表数据(String 文件,String sheet名,String 表名,String 列名,String 值){
		ExcelFile.writeExcel(文件,sheet名,表名,列名,值);
	}

	public void 写入总表数据(String sheet名,String 表名,String 列名,String 值){
		写入总表数据("./datapool/ST_汇总.xls",sheet名,表名,列名,值);
	}

	public void 写入总表数据(String 表名,String 列名,String 值){
		写入总表数据("Summary",表名,列名,值);
	}

	public void 刷新总表数据(){
		ExcelFile.resetSummarySheetFormula();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
