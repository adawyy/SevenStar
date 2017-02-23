package com.sevenstar.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/23/15
 */
public class ExcelUtil {
	
	static String prjectPath = System.getProperty("user.dir")+"/";

    public static Map<String, String> importDataTable(String dir,
                                                      String excelname, String sheetname, String findContent) {
        String excelpath = System.getProperty("user.dir") + File.separator
                + dir + File.separator + excelname;

        Map<String, String> mapdata = new LinkedMap();
        mapdata = getSpecifySheet(excelpath, sheetname, findContent);

        System.out.println("Imported data,the imported Map data is:" + mapdata);
        return mapdata;
    }

    public static List<Map<String, String>> importDataTable(String dir,
                                                            String excelname, String sheetname) {
        String excelpath = System.getProperty("user.dir") + File.separator
                + dir + File.separator + excelname;

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list = getSpecifySheet(excelpath, sheetname);

        System.out.println("Imported data,the imported List data is:" + list);
        return list;
    }

    public static Map<String, String> getSpecifySheet(String excelpath,
                                                      String sheetname, String caseName) {

        List<String> header = null;
        Map<String, String> rowmap = new HashMap<String, String>();

        boolean findrow = false;
        int rownumber = 0;

        try {
            Workbook workbook = Workbook.getWorkbook(new File(excelpath));
            Sheet sheet = workbook.getSheet(sheetname);
            // the row index begin with 0
            int rows = sheet.getRows();
            // the column index begin with 0
            int columns = sheet.getColumns();
            header = new ArrayList<String>();
            for (int columnindex = 0; columnindex < columns; columnindex++) {
                String headerelement = sheet.getCell(columnindex, 0)
                        .getContents().trim();
                header.add(columnindex, headerelement);
            }
            System.out.println("Current excel header is :" + header);
            for (int rowindex = 1; rowindex < rows; rowindex++) {
                String cellcontent = sheet.getCell(0, rowindex).getContents()
                        .toLowerCase().trim();
                System.out.println("found the first column content in excel is:"
                        + cellcontent);

                if (cellcontent.equalsIgnoreCase(caseName)) {
                    System.out.println("Found the correct cell data,the content we found in excel is:"
                            + cellcontent);
                    findrow = true;
                    rownumber = rowindex;
                    break;
                } else {
                    findrow = false;
                }
            }

            if (findrow) {
                for (int columnindex = 0; columnindex < columns; columnindex++) {
                    String findcontent = sheet.getCell(columnindex, rownumber)
                            .getContents().trim();
                    String mapheader = header.get(columnindex);
                    rowmap.put(mapheader, findcontent);
                }
            }
            System.out.println("current Row data is :" + rowmap);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowmap;
    }

    public static List<Map<String, String>> getSpecifySheet(String excelpath,
                                                            String sheetname) {

        List<String> header = null;
        Map<String, String> rowmap = new HashMap<String, String>();
        List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();

        try {
            Workbook workbook = Workbook.getWorkbook(new File(excelpath));
            Sheet sheet = workbook.getSheet(sheetname);
            // the row index begin with 0
            int rows = sheet.getRows();
            // the column index begin with 0
            int columns = sheet.getColumns();
            header = new ArrayList<String>();
            for (int columnindex = 0; columnindex < columns; columnindex++) {
                String headerelement = sheet.getCell(columnindex, 0)
                        .getContents().trim();
                header.add(columnindex, headerelement);
            }
            System.out.println("Current excel header is :" + header);
            for (int rowindex = 1; rowindex < rows; rowindex++) {
                for (int columnindex = 0; columnindex < columns; columnindex++) {
                    String findcontent = sheet.getCell(columnindex, rowindex)
                            .getContents().trim();
                    String mapheader = header.get(columnindex);
                    rowmap.put(mapheader, findcontent);
                    System.out.println("current Row data is :" + rowmap);
                }
                rowList.add(rowmap);
            }
            System.out.println("current RowList data is :" + rowList);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowList;
    }
    
	public static String[] readExcelData(String fileName, String sheetName, int rowNumber) {
		fileName = prjectPath + fileName;
		File file = new File(fileName);
		FileInputStream in = null;
		String[] s = null;
		String temp = "";
		int l = 0;

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);

			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFCell cell = null;
			int rowNum = rowNumber;

			row = sheet.getRow((short) rowNum);
			l = row.getLastCellNum();
			s = new String[l];

			for (int i = 0; i < l; i++) {
				cell = row.getCell((short) i);
				if (cell != null) {
					if (cell.getCellType() == 1)
						temp = cell.getStringCellValue();
					else
						temp = String.valueOf((int) cell.getNumericCellValue());
					s[i] = temp;
				} else {
					s[i] = "";
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println("" + file.getAbsolutePath() + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return s;
	}
	
	public static String readExcel(String fileName, String sheetName, int rowNumber) {
		fileName = prjectPath + fileName;

		File file = new File(fileName);
		FileInputStream in = null;
		String s = "";
		String temp = "";
		int l = 0;

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);

			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFCell cell = null;
			int rowNum = rowNumber;

			row = sheet.getRow((short) rowNum);
			l = row.getLastCellNum();

			for (int i = 0; i < l; i++) {
				cell = row.getCell((short) i);
				if (cell != null) {
					if (cell.getCellType() == 1)
						temp = cell.getStringCellValue();
					else
						temp = String.valueOf((int) cell.getNumericCellValue());

					if (i == 0)
						s = temp;
					else
						s = s + "," + temp;
				} else {
					if (i == 0)
						s = "";
					else
						s = s + "," + " ";
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println("Excel File " + file.getAbsolutePath() + " read error: " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return s;
	}
	
	public static String readExcel_(String fileName, String sheetName, int rowNumber) {
//		fileName = prjectPath + fileName;

		File file = new File(fileName);
		FileInputStream in = null;
		String s = "";
		String temp = "";
		int l = 0;

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);

			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFCell cell = null;
			int rowNum = rowNumber;

			row = sheet.getRow((short) rowNum);
			l = row.getLastCellNum();

			for (int i = 0; i < l; i++) {
				cell = row.getCell((short) i);
				if (cell != null) {
					if (cell.getCellType() == 1)
						temp = cell.getStringCellValue();
					else
						temp = String.valueOf((int) cell.getNumericCellValue());

					if (i == 0)
						s = temp;
					else
						s = s + "," + temp;
				} else {
					if (i == 0)
						s = "";
					else
						s = s + "," + " ";
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println("Excel File " + file.getAbsolutePath() + " read error: " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return s;
	}
	
	public static HashMap readExcelWithHashMap(String fileName, String sheetName, String title) {
		fileName = prjectPath + fileName;

		HashMap hm = new HashMap();
		File file = new File(fileName);
		FileInputStream in = null;
		String temp = "";
		String temp1 = "";
		int l = 0;

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;
			HSSFCell cell1 = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				row1 = sheet.getRow((short) (i + 1));
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							l = row.getLastCellNum();
							for (int ii = 1; ii < l; ii++) {
								cell = row.getCell((short) ii);
								cell1 = row1.getCell((short) ii);
								if (cell != null) {
									if (cell.getCellType() == 1)
										temp = cell.getStringCellValue();
									else
										temp = String.valueOf((int) cell.getNumericCellValue());

									if (!temp.trim().equals("")) {
										if (cell1 == null) {
											temp1 = " ";
										} else {
											if (cell1.getCellType() == 1)
												temp1 = cell1.getStringCellValue();
											else {
												temp1 = String.valueOf((int) cell1.getNumericCellValue());
												if (temp1.equals("0"))
													temp1 = " ";
											}
										}
										hm.put(temp, temp1);
									}
								}
							}
							break;
						}
					}
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println("Excel Path " + file.getAbsolutePath() + " read error: " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return hm;
	}
	
	public static ArrayList readExcelReturnArrayListIncludeDataKey(String fileName, String sheetName, String title) {
		fileName = prjectPath + fileName;
		ArrayList hm = new ArrayList();
		File file = new File(fileName);
		FileInputStream in = null;
		String temp = "";
		String temp1 = "";
		int l = 0;

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;
			HSSFCell cell1 = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				row1 = sheet.getRow((short) (i + 1));
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							String[] t = new String[2];
							t[0] = title;
							t[1] = "";
							hm.add(t);

							l = row.getLastCellNum();
							for (int ii = 1; ii < l; ii++) {
								cell = row.getCell((short) ii);
								cell1 = row1.getCell((short) ii);
								if (cell != null) {
									if (cell.getCellType() == 1)
										temp = cell.getStringCellValue();
									else
										temp = String.valueOf((int) cell.getNumericCellValue());

									if ((!temp.trim().equals("")) && (!temp.trim().equals("0"))) {
										if (cell1 == null) {
											temp1 = " ";
										} else {
											if (cell1.getCellType() == 1)
												temp1 = cell1.getStringCellValue();
											else {
												temp1 = String.valueOf((int) cell1.getNumericCellValue());
												if (temp1.equals("0"))
													temp1 = " ";
											}
										}
										String[] sa = new String[2];
										sa[0] = temp;
										sa[1] = temp1;
										hm.add(sa);
									}
								}
							}
							break;
						}
					}
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println("Excel File " + file.getAbsolutePath() + " read error: " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return hm;
	}

	public static ArrayList readExcelReturnArrayList(String fileName, String sheetName, String title) {
		fileName = prjectPath + fileName;
		ArrayList hm = new ArrayList();
		File file = new File(fileName);
		FileInputStream in = null;
		String temp = "";
		String temp1 = "";
		int l = 0;
		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;
			HSSFCell cell1 = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				row1 = sheet.getRow((short) (i + 2));
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							l = row.getLastCellNum();
							for (int ii = 1; ii < l; ii++) // control ---------
							{
								cell = row.getCell((short) ii);
								cell1 = row1.getCell((short) ii);
								if (cell != null) {
									if (cell.getCellType() == 1)
										temp = cell.getStringCellValue();
									else
										temp = String.valueOf((int) cell.getNumericCellValue());

									if ((!temp.trim().equals("")) && (!temp.trim().equals("0"))) {
										if (cell1 == null) {
											temp1 = " ";
										} else {
											if (cell1.getCellType() == 1)
												temp1 = cell1.getStringCellValue();
											else {
												temp1 = String.valueOf((int) cell1.getNumericCellValue());
												if (temp1.equals("0"))
													temp1 = " ";
											}
										}
										String[] sa = new String[2];
										sa[0] = temp;
										sa[1] = temp1;
										// System.out.println(temp);
										// System.out.println(temp1);
										hm.add(sa);
									}
								}
							}
							break;
						}
					}
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println("Excel File " + file.getAbsolutePath() + " read error: " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return hm;
	}

	public static ArrayList readExcelWithStringArray(String fileName, String sheetName, String title) {
		fileName = prjectPath + fileName;
		File file = new File(fileName);
		FileInputStream in = null;
		ArrayList list = new ArrayList();

		String temp = "";
		int l = 0;

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFCell cell = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							l = row.getLastCellNum();
							for (int ii = 1; ii < l; ii++) {
								cell = row.getCell((short) ii);
								if (cell != null) {
									if (cell.getCellType() == 1)
										temp = cell.getStringCellValue();
									else {
										temp = String.valueOf((int) cell.getNumericCellValue());
										if (temp.equals("0"))
											temp = "";
									}

									if (!temp.trim().equals(""))
										list.add(temp);

								}
							}

							break;
						}
					}
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println(file.getAbsolutePath() + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	public static String readExcel(String fileName, String sheetName, String title) {
		fileName = prjectPath + fileName;
		File file = new File(fileName);
		FileInputStream in = null;
		String temp = "";
		String result = "";

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFCell cell = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							cell = row.getCell((short) 3);
							if (cell.getCellType() == 1)
								result = cell.getStringCellValue();
							else
								result = String.valueOf((int) cell.getNumericCellValue());

							break;
						}
					}
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println("Excel File " + file.getAbsolutePath() + " read error: " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return result;
	}

	public static short readExcel(String fileName, String sheetName, String title, short cellNumber) {
		fileName = prjectPath + fileName;
		File file = new File(fileName);
		FileInputStream in = null;
		String temp = "";
		short result = -1;

		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFCell cell = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				if (row != null) {
					cell = row.getCell(cellNumber);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title.trim())) {
							result = (short) i;
							break;
						}
					}
				}
			}

			in.close();
		} catch (Exception e) {
			System.out.println(file.getAbsolutePath() + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return result;
	}


	public static void writeExcel(String fileName, String sheetName, int rowNumber, int columnNumber, String s) {

		FileOutputStream fOut = null;
		try {

			fileName = prjectPath + fileName;

			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(sheetName);
			HSSFRow row = sheet.getRow(rowNumber);
			if (row == null)
				row = sheet.createRow(rowNumber);

			HSSFCell cell = row.getCell((short) columnNumber);
			if (cell == null)
				cell = row.createCell((short) columnNumber);

			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// cell.setCellType(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(s);
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Excel File Write Error: " + e);
		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void writeExcel(String fileName, String sheetName,String title, String value) {
		FileOutputStream fOut = null;
		String temp;
		try {

			fileName = prjectPath + fileName;
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					fileName));

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(sheetName);
			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							row1 = sheet.getRow((short) (i + 1));
							if (row1 == null)
								row1 = sheet.createRow((short) (i + 1));
							cell = row1.getCell((short) 1);
							if (cell == null) {
								cell = row1.createCell((short) 1);
							}
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(value);

							break;
						}
					}
				}
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Excel File Write Error: " + e);
		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void writeExcel(String fileName, String sheetName,String title, String key, String value) {

		FileOutputStream fOut = null;
		String temp;
		try {
			fileName = prjectPath + fileName;
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					fileName));

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(sheetName);
			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							short l = row.getLastCellNum();
							short r = (short) (i + 1);
							short c = 0;
							for (int n = 1; n < l; n++) {
								cell = row.getCell((short) n);
								if (cell != null) {
									temp = cell.getStringCellValue();
									if (temp.trim().equals(key.trim())) {
										c = (short) n;
										break;
									}
								}
							}
							if (c != 0) {
								row1 = sheet.getRow((short) r);
								if (row1 == null)
									row1 = sheet.createRow((short) r);
								cell = row1.getCell((short) c);
								if (cell == null) {
									cell = row1.createCell((short) c);
								}
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(value);
								break;
							}
						}
					}
				}
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Excel File Write Error: " + e);
		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void writeExcel(String fileName, String sheetName,String title, String key, String value,String rowIndex) {

		FileOutputStream fOut = null;
		String temp;
		try {
			fileName = prjectPath + fileName;
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					fileName));

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(sheetName);
			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;

			for (int i = 0; i < 1000; i++) {
				row = sheet.getRow((short) i);
				if (row != null) {
					cell = row.getCell((short) 0);
					if (cell != null) {
						temp = cell.getStringCellValue();
						if (temp.trim().equals(title)) {
							short l = row.getLastCellNum();
							short r = (short) (i + Short.parseShort(rowIndex));
							short c = 0;
							for (int n = 1; n < l; n++) {
								cell = row.getCell((short) n);
								if (cell != null) {
									temp = cell.getStringCellValue();
									if (temp.trim().equals(key.trim())) {
										c = (short) n;
										break;
									}
								}
							}
							if (c != 0) {
								row1 = sheet.getRow((short) r);
								if (row1 == null)
									row1 = sheet.createRow((short) r);
								cell = row1.getCell((short) c);
								if (cell == null) {
									cell = row1.createCell((short) c);
								}
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(value);
								break;
							}
						}
					}
				}
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Excel File Write Error: " + e);
		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	public static void writeExcel(String fileName, String sheetName,int rowNumber, String s) {

		FileOutputStream fOut = null;
		try {

			fileName = prjectPath + fileName;

			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					fileName));

			String[] ss = s.split(",");
			int l = ss.length;

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(sheetName);
			HSSFRow row = sheet.getRow(rowNumber);
			if (row == null)
				row = sheet.createRow(rowNumber);

			HSSFCell cell = null;
			for (int i = 0; i < l; i++) {
				cell = row.getCell((short) i);
				if (cell == null)
					cell = row.createCell((short) i);

				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// cell.setCellType(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ss[i]);
			}
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Excel File Write Error: " + e);
		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	public static ArrayList readExcelReturnArrayList4MultiData(String fileName, String sheetName, String title) {
		fileName = prjectPath + fileName;
		ArrayList hm = new ArrayList();

		ArrayList rhm = new ArrayList();
		ArrayList rhm2 = null;

		ArrayList a = new ArrayList();

		File file = new File(fileName);
		FileInputStream in = null;
		String temp = "";
		String temp1 = "";
		int l = 0;
		try {

			in = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;
			HSSFCell cell1 = null;

			for (int i = 0; i < 1000; i++) {
				int colSize = sheet.getLastRowNum();
				for (int j = 1; j < colSize + 1; j++) {
					row = sheet.getRow((short) i);
					row1 = sheet.getRow((short) (i + j));

					if (row != null && row1 != null) {
						cell = row.getCell((short) 0);
						if (cell != null) {
							temp = cell.getStringCellValue();
							if (temp.trim().equals(title)) 
							{
								l = row.getLastCellNum();
								for (int ii = 1; ii < l; ii++) // control
								
								{
									cell = row.getCell((short) ii);
									cell1 = row1.getCell((short) ii);
									if (cell != null) {
										if (cell.getCellType() == 1) {
											temp = cell.getStringCellValue();
										} else {
											temp = String.valueOf((int) cell.getNumericCellValue());
										}
										if ((!temp.trim().equals("")) && (!temp.trim().equals("0"))) {
											if (cell1 == null) {
												temp1 = " ";
											} else {
												if (cell1.getCellType() == HSSFCell.CELL_TYPE_STRING) {
													temp1 = cell1.getStringCellValue();
											//		System.out.println("AAA"+temp1);
												}
												// added for float number
												// process
												else if (cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
													temp1 = String.valueOf(cell1.getNumericCellValue());
													
												} else {
													temp1 = String.valueOf((int) cell1.getNumericCellValue());
											//		System.out.println("BBB"+temp1);
													if (temp1.equals("0")) {
														temp1 = " ";
													}

												}
											}
											String[] sa = new String[2];
											sa[0] = temp;
											sa[1] = temp1;
						//					 System.out.println("AAA1"+temp);
						//					 System.out.println("BBB1"+temp1);
											hm.add(sa);

										}
									}

								}
								rhm.add(hm.clone());
								hm.clear();// vacant temp container
							}
						}
					} else {
						break;
					}

				}

			}

			in.close();
		} catch (Exception e) {
			System.out.println("Excel File " + file.getAbsolutePath() + " read error: " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return rhm;
	}

	public static ArrayList readExcelReturnArrayList4MultiDataTest(String fileName, String sheetName, String title) {

		ArrayList hm = new ArrayList();

		ArrayList rhm = new ArrayList();
		ArrayList rhm2 = null;

		ArrayList a = new ArrayList();

		File file = new File(fileName);
		
		FileInputStream in = null;
		String temp = "";
		String temp1 = "";
		int l = 0;
		try {

			in = new FileInputStream(file);

			HSSFWorkbook workbook = new HSSFWorkbook(in);
			
			HSSFSheet sheet = workbook.getSheet(sheetName);
			
			HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);

			HSSFRow row = null;
			HSSFRow row1 = null;
			HSSFCell cell = null;
			HSSFCell cell1 = null;
			
	//		logutil.logInfo("Total Rows in Summary Sheet: "+sheet.getLastRowNum());
			
			for (int i = 0; i < 300; i++) {
				int colSize = sheet.getLastRowNum();
				for (int j = 1; j < colSize + 1; j++) {
					row = sheet.getRow((short) i);
					row1 = sheet.getRow((short) (i + j));

					if (row == null && row1 == null) {
						break;
					}

					if (row != null && row1 != null) {
						cell = row.getCell((short) 0);
						if (cell != null)
						{
				//			System.out.println("Title is exist");
							if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								temp = cell.getStringCellValue();
							}else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
								cell = e.evaluateInCell(cell);
								temp = String.valueOf(cell.getStringCellValue());
							}
		//					System.out.println(j+temp);
							if (temp.trim().equals(title)) 
							{
								l = row.getLastCellNum();
								for (int ii = 1; ii < l; ii++) // control

								{
						//			System.out.println(ii);
									cell = row.getCell((short) ii);
									cell1 = row1.getCell((short) ii);
									
									if (cell != null && cell1 != null)
									{
										if (cell.getCellType() == 1) {
											
											temp = cell.getStringCellValue();
										} else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
											cell = e.evaluateInCell(cell);
											temp = String.valueOf( cell.getStringCellValue());
										}
										if ((!temp.trim().equals("")) && (!temp.trim().equals("0"))) {
											if (cell1 == null) {
												temp1 = " ";
											} else {
												if (cell1.getCellType() == HSSFCell.CELL_TYPE_STRING) {
													temp1 = cell1.getStringCellValue();
							//						System.out.println("OOO"+temp1);
												}
												// added for float number
												// process
												else if (cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
													temp1 = String.valueOf(cell1.getNumericCellValue());
									//				System.out.println("AAA"+temp1);
												} else if(cell1.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
													cell1 = e.evaluateInCell(cell1);
													if(cell1.getCellType() == 0){
														temp1 = String.valueOf(cell1.getNumericCellValue());}
													else if(cell1.getCellType() == 1){
														temp1 = String.valueOf(cell1.getStringCellValue());}
													
													}
									//				System.out.println("FFF"+temp1);
											
												
												else {
													temp1 = String.valueOf((Double) cell1.getNumericCellValue());
									//				System.out.println("BBB"+temp1);
													if (temp1.equals("0")) {
														temp1 = " ";
													}

												}
											}
											String[] sa = new String[2];
											sa[0] = temp;
											sa[1] = temp1;
											hm.add(sa);
					//						System.out.println("AAA1"+temp);
					//						System.out.println("BBB1"+temp1);
										}
									}

								}
								if (hm.size() > 0) {
									rhm.add(hm.clone());
									hm.clear();
								}

								else
								{
									break;
								}

							}
						}
					} else {
						break;
					}

				}

			}

			in.close();
		} catch (Exception e) {
//			System.out.println("Excel File " + file.getAbsolutePath() + " read error: " + e);
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return rhm;
	}

}


