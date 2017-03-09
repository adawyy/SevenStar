package com.learnAT.stefan.page;

import com.mte.base.MteSenseBasePage;
import com.mte.base.MteSenseCore;
import com.mte.base.MteSenseLocator;
import org.openqa.selenium.By;

/**
 * 创建人 Jackson
 * 时间 2017/2/18
 */

public class 公用页 extends MteSenseBasePage {

	MteSenseLocator locator = new MteSenseLocator("object/公用.yaml");

	private String pageName = this.getClass().getSimpleName();

	public 公用页(MteSenseCore asCore) {
		super(asCore);
		// TODO Auto-generated constructor stub
	}

	public By 用户名输入框() {
		return locator.getLocator("用户名");
	}

    public By 密码输入框(){
		return locator.getLocator("密码");
    }

    public By 登录按钮(){
    	return locator.getLocator("登录按钮");
    }

	public By 快速进入按钮(){
		return locator.getLocator("快速进入");
	}

	public By 同意按钮(){
		return locator.getLocator("同意按钮");
	}

	public By 不同意按钮(){
		return locator.getLocator("不同意按钮");
	}

	public By 右下弹框关闭按钮(){
		return locator.getLocator("右下弹框关闭按钮");
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
