package com.sevenstar.task;

import com.mte.base.MteSenseBaseTask;
import com.mte.base.MteSenseCore;
import com.mte.util.WebDriverTable;
import com.mte.util.datamgr.ExcelFile;
import com.relevantcodes.extentreports.ExtentTest;
import com.sevenstar.data.Datamgr;
import com.sevenstar.page.公用页;
import com.sevenstar.page.测试页;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 越级操作任务 extends 公用任务 {

	private 公用页 公用 = null;

	Datamgr 总表 = new Datamgr();

	ArrayList<Hashtable<String,String>> al_赔率计算 = null;

	WebDriverTable WT = null;

	public 越级操作任务(MteSenseCore senseCore,ExtentTest test) {
		super(senseCore,test);
		公用 = new 公用页(asCore);

		总表.设置数据文件("datapool/ST_汇总.xls");
		al_赔率计算 = 总表.获取数据("Summary","赔率计算");
		// TODO Auto-generated constructor stub
	}

	/**
	 * 在超级操作中点击表格中的账号
	 * @param 账号名 AT01
	 * @param 角色 大股东
	 */

	public void 点击账号(String 账号名,String 角色){
		String 拼接账号 = 账号名+"("+角色+")";
		asCore.click(By.xpath("//*[@id='tbody']//a[contains(text(),'"+拼接账号+"')]"));
	}

	/**
	 * 在超级操作中点击修改表格中的账号
	 * @param 账号名 AT01
	 * @param 角色 大股东
	 */

	public void 点击修改账号(String 账号名,String 角色){
		String 拼接账号 = 账号名+"("+角色+")";
		String xpath = "//td[preceding-sibling::td/a[contains(text(),'"+拼接账号+"')]]/a[contains(text(),'修改')]";
//		System.out.println(xpath);
		asCore.pause(1000);
		asCore.click(By.xpath(xpath));
	}

	/**
	 * 在超级操作中点击修改表格中的账号
	 * @param 账号名 AT01
	 * @param 角色 大股东
	 */

	public void 点击父账号(String 账号名,String 角色) throws InterruptedException {
		String 拼接账号 = 角色+"："+账号名;
		String xpath = "//a[text()='"+拼接账号+"']";
//		System.out.println(xpath);
		Thread.sleep(500);
		asCore.click(By.xpath(xpath));
	}

	/**
	 * 点击账号列表 回到超级操作主页面
	 */

	public void 点击账号列表(){
		asCore.click(By.xpath("//a[text()='账户列表']"));
	}

	public void 点击账号月报表(String 账号名,String 角色){
		String 拼接账号 = 账号名+"("+角色+")";
		String xpath = "//td[preceding-sibling::td/a[contains(text(),'"+拼接账号+"')]]/a[contains(text(),'月报表')]";
//		System.out.println(xpath);
		asCore.pause(1000);
		asCore.click(By.xpath(xpath));
	}

	public void 输入账号或代号查询(String 账号,String 代号){
		asCore.sendKeys(By.xpath("//*[@id='memberadmin']//input[@name='account']"),账号);
		asCore.sendKeys(By.xpath("//*[@id='memberadmin']//input[@name='nick_name']"),代号);
		asCore.click(By.xpath("//*[@id='btn_search']"));
	}

	/**
	 * 修改账号信息
	 * @param ht 详情查看ST_1.xls 中的各级信息
	 */

	public void 修改账号信息(Hashtable<String,String> ht){
		String type = ht.get("类别");
		asCore.pause(1000);
		if(ht.get("是否启用").equals("启用")){
			asCore.click(By.xpath("//input[@name='is_disable' and @value='0']"));
		}else if(ht.get("是否启用").equals("停用")){
			asCore.click(By.xpath("//input[@name='is_disable' and @value='1']"));
		}else if(ht.get("是否启用").equals("暂停下注")){
			asCore.click(By.xpath("//input[@name='is_disable' and @value='2']"));
		}

		if(ht.get("密码").equals("空")){

		}else{
			asCore.sendKeys(By.xpath("//input[@name='login_pwd']"),ht.get("密码"));
		}

		if(ht.get("贡献度占成上限").equals("空")){

		}else{
			asCore.selectByValue(By.xpath("//select[@name='contribution_max_limit']"),ht.get("贡献度占成上限"));
		}

		if(ht.get("代号").equals("空")){

		}else{
			asCore.sendKeys(By.xpath("//input[@name='nick_name']"),ht.get("代号"));
		}

		if(ht.get("联系电话").equals("空")){

		}else{
			asCore.sendKeys(By.xpath("//input[@name='contact_phone']"),ht.get("联系电话"));
		}

		asCore.selectByValue(By.xpath("//select[@name='parent_hold_rate']"),ht.get("上级"));
		asCore.selectByValue(By.xpath("//select[@name='self_hold_rate']"),ht.get("本级"));

		String 上级分成 = String.valueOf(Integer.parseInt(ht.get("上级"))*0.1);

		if(ht.get("角色").equals("大股东")){
			写入总表数据("分成比例","总监",上级分成);
		}else if(ht.get("角色").equals("股东")){
			写入总表数据("分成比例","大股东",上级分成);
		}else if(ht.get("角色").equals("总代理")){
			写入总表数据("分成比例","股东",上级分成);
		}else if(ht.get("角色").equals("代理")){
			写入总表数据("分成比例","总代理",上级分成);
		}else if(ht.get("角色").equals("用户")){
			写入总表数据("分成比例","代理",上级分成);
		}

		if(ht.get("信用额度").equals("空")){

		}else{
			asCore.sendKeys(By.xpath("//input[@name='credit']"),ht.get("信用额度"));
		}

		if(ht.get("单注上限").equals("空")){

		}else {
			asCore.sendKeys(By.xpath("//tbody[@id='tbody']//td[preceding-sibling::td[text()='"+type+"']]/input[@_name='one_bet_limit']"),ht.get("单注上限"));
		}

		if(ht.get("单项上限").equals("空")){

		}else {
			asCore.sendKeys(By.xpath("//tbody[@id='tbody']//td[preceding-sibling::td[text()='"+type+"']]/input[@_name='one_item_limit']"),ht.get("单项上限"));
		}

		if(ht.get("福利").equals("空")){

		}else {
			asCore.selectByValue(By.xpath("//tbody[@id='tbody']//td[preceding-sibling::td[text()='"+type+"']]/select[@_name='return_water']"),ht.get("福利"));

			if(ht.get("角色").equals("大股东")){
				写入总表数据("赚水比例","总监",ht.get("福利"));
			}else if(ht.get("角色").equals("股东")){
				写入总表数据("赚水比例","大股东",ht.get("福利"));
			}else if(ht.get("角色").equals("总代理")){
				写入总表数据("赚水比例","股东",ht.get("福利"));
			}else if(ht.get("角色").equals("代理")){
				写入总表数据("赚水比例","总代理",ht.get("福利"));
			}else if(ht.get("角色").equals("用户")){
				写入总表数据("赚水比例","代理",ht.get("福利"));
			}
				刷新总表数据();
			}

		asCore.click(By.xpath("//input[@type='submit' and @value='提交']"));
		asCore.click(By.xpath("//input[@value='确定']"));
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
