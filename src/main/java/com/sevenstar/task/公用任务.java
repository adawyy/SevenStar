package com.sevenstar.task;

import com.sevenstar.page.公用页;
import org.openqa.selenium.By;
import com.mte.base.MteSenseBaseTask;
import com.mte.base.MteSenseCore;

public class 公用任务 extends MteSenseBaseTask {

	private 公用页 公用 = null;

	public 公用任务(MteSenseCore senseCore) {
		super(senseCore);
		公用 = new 公用页(asCore);
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
		asCore.click(公用.快速进入按钮());
		asCore.click(公用.同意按钮());
		asCore.pause(1000);
		asCore.click(公用.右下弹框关闭按钮());
		asCore.pause(1000);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
