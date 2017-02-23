package com.mte.base;

import com.mte.util.ExcelUtil;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Hashtable;

public class MteSenseData {
	
	public static HashMap getTestData(int nameIndex, int dataIndex, String file,String sheet)
	{
		HashMap hm = new HashMap();
		String testDataName,testDataValue;
		String[] testDataNameArray, testDataValueArray;

		testDataName = ExcelUtil.readExcel(file, sheet, nameIndex);
		testDataValue = ExcelUtil.readExcel(file, sheet, dataIndex);
		
		testDataNameArray = testDataName.split(",");
		testDataValueArray = testDataValue.split(",");
		
		int l = testDataValueArray.length;
		
		for(int i = 0; i < l; i++)
		{
			if(!testDataNameArray[i].equals("-"))
			{
				if(!testDataValueArray[i].trim().equals(""))
					hm.put(testDataNameArray[i], testDataValueArray[i]);
			}
		}
		
		return hm;
	}

	public static HashMap getTestDataWithHashMap(String file,String sheet,String title)
	{		
		return ExcelUtil.readExcelWithHashMap(file,sheet,title);
	}
	
	public static ArrayList getTestDataReturnArraylist(String file,String sheet,String title)
	{		
		return ExcelUtil.readExcelReturnArrayList(file,sheet,title);
	}

	public static ArrayList getTestDataReturnArraylistIncludeDataKey(String file,String sheet,String title)
	{		
		return ExcelUtil.readExcelReturnArrayListIncludeDataKey(file,sheet,title);
	}

	public static String getTestData(String file,String sheet,String title)
	{
		return ExcelUtil.readExcel(file, sheet, title);
	}

	public static ArrayList getTestDataWithStringArray(String file,String sheet,String title)
	{
		return ExcelUtil.readExcelWithStringArray(file,sheet,title);
	}
	
	public static void writeExcelWithString(String file,String sheet,String title,String value)
	{
		ExcelUtil.writeExcel(file, sheet, title, value);
	}
	
	public static void writeExcelWithString(String file,String sheet,String title,String key,String value)
	{
		ExcelUtil.writeExcel(file, sheet, title, key, value);
	}
	
	public static String[] getTestDataValue(int dataIndex, String file,String sheet)
	{
		String testDataValue;
		String[] testDataValueArray;
		String[] result = null;
		String s = "" ;
		String ss;

		testDataValue = ExcelUtil.readExcel(file, sheet, dataIndex);

		testDataValueArray = testDataValue.split(",");
		
		int l = testDataValueArray.length;

		for(int i = 0; i<l; i++)
		{
			if(!testDataValueArray[i].trim().equals(""))
			{
				ss = testDataValueArray[i];
				if(i == 0)
					s = ss;
				else
					s = s + "," +ss;
			}
		}

		result = s.split(",");
		
		return result;
	}
	
	
	public static ArrayList getTestDataReturnArraylist4MultiData(String file,String sheet,String title)
	{		
		return ExcelUtil.readExcelReturnArrayList4MultiData(file,sheet,title);
	}
	
	public static ArrayList getTestDataReturnArrayListWithMultiData(String file,String sheet,String title)
	{
		ArrayList<Hashtable<String, String>> result =new ArrayList();
		ArrayList orgRecordData =  ExcelUtil.readExcelReturnArrayList4MultiData(file,sheet,title);
		int size = orgRecordData.size();
		for (int i=0;i<size;i++)
		{
			Hashtable ht = new Hashtable();
			ArrayList temp = (ArrayList)orgRecordData.get(i);
			int size2 = temp.size();
			for (int j=0;j<size2;j++)
			{
				String[] sa = (String[]) temp.get(j);
				String fieldName = sa[0];
				String fieldValue = sa[1];
				ht.put(fieldName, fieldValue);
			}
			result.add(ht);
		}
		return result;
	}
	
	//this method will return the action data that read from excel file
	public static ArrayList getGroupData(String file,String sheet,String title)
	{	
		ArrayList<Hashtable<String, String>> result =new ArrayList();

		ArrayList orgRecordData =  ExcelUtil.readExcelReturnArrayList4MultiDataTest(file, sheet, title); 
		
		int size = orgRecordData.size();
		for (int i=0;i<size;i++)
		{
			Hashtable ht = new Hashtable();
			ArrayList temp = (ArrayList)orgRecordData.get(i);
			int size2 = temp.size();
			for (int j=0;j<size2;j++)
			{
				String[] sa = (String[]) temp.get(j);
				String fieldName = sa[0];
				String fieldValue = sa[1];
//				System.out.println("field"+fieldValue);
				ht.put(fieldName, fieldValue);
			}
			result.add(ht);
		}
		return result;		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
