package com.sevenstar.task;


import com.mte.base.MteSenseCore;
import com.sevenstar.page.前台登录页;
import org.openqa.selenium.By;

import java.util.Hashtable;

public class 前台公用任务 extends 公用任务 {
	
	private 前台登录页 登录页面 = null;
	
	public 前台公用任务(MteSenseCore senseCore) {
		super(senseCore);
		登录页面 =new 前台登录页(asCore);
		// TODO Auto-generated constructor stub
	}
	
	public void 会员成功登录(String 用户名,String 密码){
		asCore.sendKeys(登录页面.用户名输入框(), "js001aaaa");
		asCore.sendKeys(登录页面.密码输入框(), "asdfasdf1");
		asCore.click(登录页面.登录按钮());
		asCore.click(登录页面.同意按钮());
		asCore.pause(1000);
		asCore.click(登录页面.右下弹框关闭按钮());
	//	MSAssert.verifyEqual(result, "IBM 中国官方网站", "Check the IBM result");
	}

	public void 设置录码模式(Hashtable<String,String> ht){
		String 模式一=ht.get("模式一");
		String 模式二=ht.get("模式二");
		String 模式三=ht.get("模式三");



	}
	
	public void 快打下注(){

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
