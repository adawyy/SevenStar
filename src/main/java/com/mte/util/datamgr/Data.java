package com.mte.util.datamgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Data {

	/**
	 * @Description
	 * @author jiang.bian
	 * @param
	 * @return boolean
	 * @date Sep 11, 2013
	 */
	String ExeclInput;
	String ExcelVerify;


	public static void main(String[] args) {
		Data datapool = new Data();
		datapool.setExeclInput("datapool/ST_1.xls");
		ArrayList<Hashtable<String,String>> al;

		al = datapool.getSummaryData("分批赔率[口口XX]");
		Hashtable<String,String> ht;

		System.out.println(al.size());

		ht = al.get(0);
		System.out.println(ht.get("是否放出"));
//		Iterator<String> key=ht.keySet().iterator();
//
//		ArrayList<String> cols = new ArrayList<String>();
//		ArrayList<String> cols_ = new ArrayList<String>();
//
//		while(key.hasNext()){
//			Object o= key.next();
//			if(!o.toString().contains("Fields")){
//				System.out.println(o.toString());
//				cols.add(o.toString());
//			}
//		}
////		System.out.println(cols.size());
//		Iterator iter = cols.iterator();
//		while(iter.hasNext())
//		{
//			String col = (String)iter.next();
////			System.out.println(col);
//			for(int j = 0;j<=7;j++){
//				ht = al.get(j);
//				System.out.println( ht.get("Fields"));
//				System.out.println( ht.get(col));
//			}
//		}

		// TODO Auto-generated method stub
//		ExcelFile.writeExcel("E:\\IBM_ADMIN\\IBM\\rationalsdp\\workspace\\GPE Regression Automation\\datapool\\GPE_Position.xls", "OEM", "OEMHardware_UAT", "Description", "555");
	}
	
	public void setExeclInput(String xls){
		this.ExeclInput = xls;
	}
	
	public String getExeclInput(){
		return this.ExeclInput;
	}
	
	public void setExcelVerify(String xls){
		this.ExcelVerify = xls;
	}
	
	public String getExcelVerify(){
		return this.ExcelVerify;
	}
	
	public ArrayList<Hashtable<String,String>> getBidInfo(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "BidInfo", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getIBMBandedData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "Labor", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getSubContractData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "Labor", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getVendedLaborData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "Labor", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public ArrayList<Hashtable<String,String>> getOEMHwData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "OEM", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getOEMSwData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "OEM", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getMiscData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "Other", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getIPSCData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "Other", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getSWGData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "Other", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getAddElementData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "AddtionalElement", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getPassThroughData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "AddtionalElement", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getPowersReservedData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "PowersReserved", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getIBMHwData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "IBM", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getIBMSwData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "IBM", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getIBMBandedVerifyData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExcelVerify, "Verify", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getSummaryData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "赔率设置", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getIEData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "IEAdjustment", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getBSData(String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, "BillingSchedule", tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Hashtable<String,String>> getData(String sheetname,String tablename){
	    ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();		
		try {
			al = GetData.getGroupData(this.ExeclInput, sheetname, tablename);
	//		ht = al.get(0);
			if (al.size() > 0) {

			} else {
				System.out.println("no Data found from excel!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	


}
