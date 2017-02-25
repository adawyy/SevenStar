package com.sevenstar.page;

import com.mte.base.MteSenseBasePage;
import com.mte.base.MteSenseCore;
import com.mte.base.MteSenseLocator;
import org.openqa.selenium.WebElement;

public class 公用页 extends MteSenseBasePage {

	MteSenseLocator locator = new MteSenseLocator("object/公用.yaml");

	private String pageName = this.getClass().getSimpleName();

	public 公用页(MteSenseCore asCore) {
		super(asCore);
		// TODO Auto-generated constructor stub
	}

    public WebElement 用户名输入框() {
        return asCore.findElement(locator.getLocator("用户名"));
    }
    
    public WebElement 密码输入框(){
    	return asCore.findElement(locator.getLocator("密码"));
    }

    public WebElement 登录按钮(){
    	return asCore.findElement(locator.getLocator("登录按钮"));
    }

	public WebElement 快速进入按钮(){
		return asCore.findElement(locator.getLocator("快速进入"));
	}

	public WebElement 同意按钮(){
		return asCore.findElement(locator.getLocator("同意按钮"));
	}

	public WebElement 不同意按钮(){
		return asCore.findElement(locator.getLocator("不同意按钮"));
	}

	public WebElement 右下弹框关闭按钮(){
		return asCore.findElement(locator.getLocator("右下弹框关闭按钮"));
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
