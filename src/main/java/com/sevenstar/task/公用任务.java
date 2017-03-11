package com.sevenstar.task;

import com.mte.base.MteSenseAlert;
import com.mte.util.datamgr.ExcelFile;
import com.sevenstar.data.Datamgr;
import com.sevenstar.page.公用页;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import com.mte.base.MteSenseBaseTask;
import com.mte.base.MteSenseCore;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 公用任务 extends MteSenseBaseTask {

	private 公用页 公用 = null;

	Datamgr 总表 = new Datamgr();

	ArrayList<Hashtable<String,String>> al_赔率计算,al_单次赔率,al_下注信息 = null;

	public 公用任务(MteSenseCore senseCore) {
		super(senseCore);
		公用 = new 公用页(asCore);

		总表.设置数据文件("datapool/ST_汇总.xls");

		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>直接在本页面中跳转页面</p>
	 * @param Url 页面的地址URL
	 */

	public void 跳转页(String Url){
		asCore.get(Url,2);
	}

	/**
	 * <p>页面登录方法，可以用于所有前后台登录</p>
	 * @param 用户名 director
	 * @param 密码 123fff
	 */

	public void 登录(String 用户名,String 密码){
		asCore.sendKeys(公用.用户名输入框(), 用户名);
		asCore.sendKeys(公用.密码输入框(), 密码);
		asCore.click(公用.登录按钮());
	}

	/**
	 * 页面快速登录方法，直接点击快速进入和同意按钮
	 * @param 用户名 director
	 * @param 密码 123fff
	 */

	public void 快速登录(String 用户名,String 密码){
		登录(用户名,密码);
		asCore.skipClick(公用.快速进入按钮(),2);
		asCore.click(公用.同意按钮());
//		asCore.pause(1500);
//		asCore.skipClick(公用.右下弹框关闭按钮(),2);
//		asCore.pause(1000);
		asCore.pause(2000);
		asCore.waitClickAbleToClick(公用.右下弹框关闭按钮(),5);
	}

	public void 可靠登录(String 用户名,String 原密码, String 新密码,String 角色, String ExcelFile, String tableName){
		登录(用户名,原密码);
		asCore.skipClick(公用.快速进入按钮(),2);
		asCore.click(公用.同意按钮());
		asCore.pause(2000);
		if(asCore.isElementPresent(By.xpath("//*[@id='oldPwd']"))){
			asCore.sendKeys(By.xpath("//*[@id='oldPwd']"),原密码);
			asCore.sendKeys(By.xpath("//*[@id='newPwd']"),新密码);
			asCore.sendKeys(By.xpath("//*[@id='repeatNewPwd']"),新密码);
			asCore.click(By.xpath("//*[@id='btn-submit']"));
			//修改密码成功，需要重新登录才生效。
//			Alert alert = asCore.switchTo().alert();
//			MteSenseAlert msa = new MteSenseAlert(alert);
//			msa.accept();
			asCore.chooseOKOnAlert(2);
			写入总表数据(ExcelFile,"登录信息",tableName,角色+"密码",新密码);
			写入总表数据(ExcelFile,"登录信息",tableName,角色+"新密码",原密码);
			登录(用户名,新密码);
			asCore.skipClick(公用.快速进入按钮(),2);
			asCore.click(公用.同意按钮());

		}
		asCore.waitClickAbleToClick(公用.右下弹框关闭按钮(),5);
	}

	public void 等待开盘(int 秒){
		asCore.pause(秒*1000);
	}

	/**
	 * 写入到总表的数据，用于数据计算
	 * @param 文件 ST_汇总.xls
	 * @param sheet名 Summary
	 * @param 表名 分批赔率
	 * @param 列名 开始金额
	 * @param 值 1000
	 * @param 行数 1
	 */

	public void 写入总表数据(String 文件,String sheet名,String 表名,String 列名,String 值,String 行数){
		ExcelFile.writeExcel(文件,sheet名,表名,列名,值,行数);
	}

	/**
	 * 写入到总表的数据，用于数据计算
	 * @param 文件 ST_汇总.xls
	 * @param sheet名 Summary
	 * @param 表名 分批赔率
	 * @param 列名 开始金额
	 * @param 值 1000
	 */

	public void 写入总表数据(String 文件,String sheet名,String 表名,String 列名,String 值){
		ExcelFile.writeExcel(文件,sheet名,表名,列名,值);
	}

	/**
	 * 写入到ST_汇总.xls的数据，用于数据计算，文件默认ST_汇总.xls
	 * @param sheet名 Summary
	 * @param 表名 分批赔率
	 * @param 列名 开始金额
	 * @param 值 1000
	 */

	public void 写入总表数据(String sheet名,String 表名,String 列名,String 值){
		写入总表数据("./datapool/ST_汇总.xls",sheet名,表名,列名,值);
	}

	/**
	 * 写入到ST_汇总.xls的数据，用于数据计算，文件默认ST_汇总.xls，sheet默认Summary
	 * @param 表名 分批赔率
	 * @param 列名 开始金额
	 * @param 值 1000
	 */

	public void 写入总表数据(String 表名,String 列名,String 值){
		写入总表数据("Summary",表名,列名,值);
	}

	/**
	 * 写入到ST_汇总.xls的数据，用于数据计算，文件默认ST_汇总.xls，sheet默认Summary
	 * @param 表名 分批赔率
	 * @param 列名 开始金额
	 * @param 值 1000
	 * @param 行数 2
	 */

	public void 写入总表行数据(String 表名,String 列名,String 值,String 行数){
		写入总表数据("./datapool/ST_汇总.xls","Summary",表名,列名,值,行数);
	}

	/**
	 * 用于在填入数据后手动刷新Excel表的计算，正常情况下如果不刷新，计算过程就不会运行
	 */

	public void 刷新总表数据(){
		ExcelFile.resetSummarySheetFormula();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * 总表数据初始化
	 */
	public void 总表数据初始化(){
		写入总表数据("赔率计算","赔率变动设置","0");
		写入总表数据("下注信息","已有金额","0");
		写入总表数据("下注信息","下注金额","0");
		刷新总表数据();

	}


}
