package com.sevenstar.data;

import com.mte.util.datamgr.GetData;


import java.util.ArrayList;
import java.util.Hashtable;

public class Datamgr {

	String ExeclInput;
	String ExcelVerify;

	public void 设置数据文件(String xls){
		this.ExeclInput = xls;
	}

	public String 获取数据文件(){
		return this.ExeclInput;
	}

	public ArrayList<Hashtable<String,String>> 获取登录信息(String 表名){
		ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();
		try {
			al = GetData.getGroupData(this.ExeclInput, "登录信息", 表名);
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

	public ArrayList<Hashtable<String,String>> 获取赔率设置(String 表名){
		ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();
		try {
			al = GetData.getGroupData(this.ExeclInput, "赔率设置", 表名);
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

	public ArrayList<Hashtable<String,String>> 获取会员资料(String 表名){
		ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();
		try {
			al = GetData.getGroupData(this.ExeclInput, "会员资料", 表名);
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

	public ArrayList<Hashtable<String,String>> 获取下注信息(String 表名){
		ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();
		try {
			al = GetData.getGroupData(this.ExeclInput, "下注信息", 表名);
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

	public ArrayList<Hashtable<String,String>> 获取数据(String 页名,String 表名){
		ArrayList<Hashtable<String, String>> al;
		al = new ArrayList<Hashtable<String, String>>();
		try {
			al = GetData.getGroupData(this.ExeclInput, 页名, 表名);
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


	public static void main(String[] args) {
		Datamgr dm = new Datamgr();
		// TODO Auto-generated method stub

	}

}
