package com.sevenstar.task;

import com.mte.util.datamgr.ExcelFile;
import com.sevenstar.data.Datamgr;
import com.sevenstar.page.公用页;
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

	ArrayList<Hashtable<String,String>> al_赔率计算 = null;

	public 公用任务(MteSenseCore senseCore) {
		super(senseCore);
		公用 = new 公用页(asCore);

		总表.设置数据文件("datapool/ST_汇总.xls");
		al_赔率计算 = 总表.获取数据("Summary","赔率计算");
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
		asCore.waitClickAbleToClick(公用.右下弹框关闭按钮(),5);
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
	 * @param 文件 ST_汇总.xls
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
	 * 用于在填入数据后手动刷新Excel表的计算，正常情况下如果不刷新，计算过程就不会运行
	 */

	public void 刷新总表数据(){
		ExcelFile.resetSummarySheetFormula();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
