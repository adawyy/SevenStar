package com.sevenstar.task;

import com.mte.base.MteSenseBaseTask;
import com.mte.base.MteSenseCore;
import com.sevenstar.page.前台登录页;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Hashtable;

public class 后台公用任务 extends MteSenseBaseTask {

	private 前台登录页 登录页面 = null;

	public 后台公用任务(MteSenseCore senseCore) {
		super(senseCore);
		登录页面 =new 前台登录页(asCore);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void 后台成功登录(String 用户名,String 密码){
		asCore.sendKeys(登录页面.用户名输入框(), 用户名);
		asCore.sendKeys(登录页面.密码输入框(), 密码);
		asCore.click(登录页面.登录按钮());
		asCore.click(登录页面.同意按钮());
		asCore.pause(1000);
		asCore.click(登录页面.右下弹框关闭按钮());
		//	MSAssert.verifyEqual(result, "IBM 中国官方网站", "Check the IBM result");
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

	}

}
