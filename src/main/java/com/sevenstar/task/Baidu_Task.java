package com.sevenstar.task;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mte.base.MSAssert;
import com.mte.base.MteSenseCore;
import com.sevenstar.page.Baidu_SearchPage;

public class Baidu_Task extends MainTask {
	
	private Baidu_SearchPage searchPage = null;
	
	public Baidu_Task(MteSenseCore senseCore) {
		super(senseCore);
		searchPage =new Baidu_SearchPage(asCore);
		// TODO Auto-generated constructor stub
	}
	
	public void VerifyBaiduResult(){
		WebElement searchbox = searchPage.getSearchbox();
		WebElement searchbutton = searchPage.getSearchButton();
		asCore.sendKeys(searchbox, "IBM");;
		asCore.click(searchbutton);
		asCore.pause(5000);
		String result = asCore.getText(By.xpath("//*[@id='content_left']/div/div/div/div/div/h2/a"),5);
		System.out.println(result);
		MSAssert.verifyEqual(result, "IBM 中国官方网站", "Check the IBM result");
	}
	
	public void VerifyBaiduResultImage(){
		asCore.sikuli_Exist("BaiduSearch",98,"BaiduSearch IBM result Verify");
		asCore.sikuli_Click("IBM");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
