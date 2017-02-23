package com.sevenstar.page;

import org.openqa.selenium.WebElement;

import com.mte.base.MteSenseBasePage;
import com.mte.base.MteSenseCore;
import com.mte.base.MteSenseLocator;

public class Baidu_SearchPage extends MteSenseBasePage {
	
	MteSenseLocator locator = new MteSenseLocator("object/Baidu.yaml");
	
	private String pageName = this.getClass().getSimpleName();

	public Baidu_SearchPage(MteSenseCore asCore) {
		super(asCore);
		// TODO Auto-generated constructor stub
	}

    public WebElement getSearchbox() {
        return asCore.findElement(locator.getLocator("searchbox"));
    }
    
    public WebElement getSearchButton(){
    	return asCore.findElement(locator.getLocator("searchbutton"));
    }

    public WebElement getSearchResult(){
    	return asCore.findElement(locator.getLocator("SearchResult"));
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
