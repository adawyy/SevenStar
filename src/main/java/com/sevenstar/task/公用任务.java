package com.sevenstar.task;

import org.openqa.selenium.By;
import com.mte.base.MteSenseBaseTask;
import com.mte.base.MteSenseCore;

public class 公用任务 extends MteSenseBaseTask {

	public 公用任务(MteSenseCore senseCore) {
		super(senseCore);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void Login(String name,String password){
		asCore.sendKeys(By.xpath("//*[@id='Account']"), name);
		asCore.sendKeys(By.xpath("//*[@id='Password']"), password);
		asCore.click(By.xpath("//*[@id='btn-submit']"));
	}

	public int getTabNumber(){
		boolean ex = asCore.isElementExist(By.xpath("//*[@id='leftdrawer']/div/ul/li"),5);
		int tabNum = 0;
		while(ex==true){
			 tabNum ++;
			 ex = asCore.isElementExist(By.xpath("//*[@id='leftdrawer']/div/ul/li["+(tabNum+1)+"]"));
		}
		return tabNum;
	}
	
	public int getTabIndex(String tabName){
		boolean ex = asCore.isElementExist(By.xpath("//*[@id='leftdrawer']/div/ul/li"),5);
		int tabIndex = 1;
		while(ex==true){
			 ex = asCore.isElementExist(By.xpath("//*[@id='leftdrawer']/div/ul/li["+(tabIndex+1)+"]"));
			 if(ex){
				 if(tabName.equals(asCore.getText(By.xpath("//*[@id='leftdrawer']/div/ul/li["+(tabIndex+1)+"]")))){
					 return tabIndex;
				 }
			 }
			 tabIndex ++;
		}
		return -1;
	}
	
	/**
	 * Navigation to Tab
	 * 
	 * @param Tab (Home,IBMerHub,Manager Hub)
	 */
	
	public void NavTo(String Tab){
		
		int index = getTabIndex(Tab);
		asCore.click(By.xpath("//*[@id='leftdrawer']/div/ul/li["+(index+1)+"]/a"));	
		
/*		if(Tab.equals("Home")){
			asCore.click(By.xpath("//*[@id='leftdrawer']/div/ul/li[1]/a"));
		}else if(Tab.equals("IBMer Hub")){
			asCore.click(By.xpath("//*[@id='leftdrawer']/div/ul/li[2]/a"));
		}else if(Tab.equals("Manager Hub")){
			asCore.click(By.xpath("//*[@id='leftdrawer']/div/ul/li[3]/a"));
		}else if(Tab.equals("Admin Hub")){
			asCore.click(By.xpath("//*[@id='leftdrawer']/div/ul/li[4]/a"));
		}*/
	}
	
	/**
	 * Get the Medium card number on one page.
	 * 
	 * @return number
	 */
	
	public int getMediumCardsNumber(){
		boolean ex = asCore.isElementExist(By.xpath("//ul[@class='grid animated main-grid']/li"),5);
		int cardNum = 0;
		while(ex==true){
			 cardNum ++;
			 ex = asCore.isElementExist(By.xpath("//ul[@class='grid animated main-grid']/li["+(cardNum+1)+"]"));
		}
		return cardNum;
	}
	
	/**
	 * Get the Mini card number on one page.
	 * 
	 * @return number
	 */
	
	public int getMiniCardsNumber(){
		int cardNum = 0;
		boolean ex = asCore.isElementExist(By.xpath("//*[@id='rightdrawer']/ul/li"),5);
		while(ex==true){
			if(asCore.getText(By.xpath("//*[@id='rightdrawer']/ul/li"))==""){
				return cardNum;
			}else{
				cardNum ++;
				ex = asCore.isElementExist(By.xpath("//*[@id='rightdrawer']/ul/li["+(cardNum+1)+"]"));
			}
		}
		return cardNum;
	}
	
	/**
	 * return the card index from the cardName
	 * 
	 * @param cardName
	 * @return card index
	 */
	
	public int getMediumCardsIndex(String cardName){
		boolean ex = asCore.isElementExist(By.xpath("//ul[@class='grid animated main-grid']/li"));
		int cardsIndex = 1;
		while(ex==true){
			 ex = asCore.isElementExist(By.xpath("//ul[@class='grid animated main-grid']/li["+cardsIndex+"]"));
			 if(ex){
				 if(cardName.equals(asCore.getText(By.xpath("//ul[@class='grid animated main-grid']/li["+cardsIndex+"]/div/div/div[1]/div/header/h2")))){
					 return cardsIndex;
				 }
			 }
			 cardsIndex ++;
		}
		return -1;
	}
	
	/**
	 * return mini card index from cardName
	 * 
	 * @param cardName
	 * @return
	 */
	
	public int getMiniCardsIndex(String cardName){
		boolean ex = asCore.isElementPresent(By.xpath("//*[@id='rightdrawer']/ul/li"),5);
		int cardsIndex = 1;
		while(ex==true){
			 ex = asCore.isElementPresent(By.xpath("//*[@id='rightdrawer']/ul/li["+cardsIndex+"]"),5);
			 if(ex){
				 if(cardName.equals(asCore.getText(By.xpath("//*[@id='rightdrawer']/ul/li["+cardsIndex+"]")))){
					 return cardsIndex;						
				 }
			 }
			 cardsIndex ++;
		}
		return -1;
	}
	
	/**
	 * Drag from DragCard to DropCard
	 * 
	 * @param DragCard
	 * @param DropCard
	 */
	
	public void DragToCard(String DragCard,String DropCard){
		int DragIndex = getMediumCardsIndex(DragCard);
		int DropIndex = getMediumCardsIndex(DropCard);
		if((DragIndex>0)&&(DragIndex>0)){
			asCore.DragAndDrop(By.xpath("//ul[@data-component='grid']/li["+DragIndex+"]//*[@data-primitive='drag-icon']"), By.xpath("//ul[@data-component='grid']/li["+DropIndex+"]//*[@data-primitive='drag-icon']"));
		}
	}
	
	/**
	 * Drag card to mini card
	 * 
	 * @param DragCard
	 */
	
	public void DragToMiniCard(String DragCard){
		int DragIndex = getMediumCardsIndex(DragCard);
		if(DragIndex>0){
			asCore.DragAndDrop(By.xpath("//ul[@data-component='grid']/li["+DragIndex+"]//*[@data-primitive='drag-icon']"), By.xpath("//*[@id='rightdrawer']/ul/li[1]"));
		}
	}
	
	/**
	 * Drag mini card to medium card
	 * 
	 * @param miniCard
	 */
	
	public void MiniToCard(String miniCard){
		int DragIndex = getMiniCardsIndex(miniCard);
		if(DragIndex>0){
			asCore.DragAndDrop(By.xpath("//*[@id='rightdrawer']/ul/li["+DragIndex+"]"),By.xpath("//ul[@data-component='grid']/li//*[@data-primitive='drag-icon']"));
		}
	}
	
	
	public void DragtoCard(String card){
		if(getMiniCardsIndex(card)<0){
			MiniToCard(card);
		}
	}
	
	/**
	 * Open large card
	 * @param cardName
	 */
	
	public void openLargeCard(String cardName){
		int idx = getMediumCardsIndex(cardName);
		if(idx>0){
			asCore.click(By.xpath("ul[@class='grid animated main-grid']/li["+idx+"]/div/div/div[1]/div/header/div/span[2]/button"));
		}
	}
	
	/**
	 * Close large card
	 * 
	 */
	
	public void closeLargeCard(){
		
	} 
	


}
