package com.sevenstar.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	private File file = null;

	public FileUtil() {

	}

	public void createFile(String directory, String fileName) {
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(directory + fileName + ".txt");

		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createProperties(String directory, String fileName) {
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(directory + fileName + ".properties");
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File createPNGFile(String directory, String fileName) {
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(directory + fileName + ".png");
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public void writeLine(String content) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
			fw.write(content + "\r\n");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List read(String directory, String fileName) {
		List list = new ArrayList();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(directory + fileName + ".txt");
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
