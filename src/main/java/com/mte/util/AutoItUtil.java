package com.mte.util;

import java.io.File;

import autoitx4java.AutoItX;

import com.jacob.com.LibraryLoader;

public class AutoItUtil {
	
	private static AutoItX instance;
	
	public static AutoItX getInstance()
	{
		if (instance == null)
	{
			synchronized(AutoItX.class) { //1
				if (instance == null) //2
					instance = new AutoItX(); //3
			}
		}
		return instance;
	}
	
	public static void baiscAuth(String login, String password) {
        File file = new File("Lib", "jacob-1.18-x86.dll");
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
        AutoItX x = AutoItUtil.getInstance();
        if (x.winWait("Authentication Required", null, 20)) {
        	x.winActivate("Authentication Required");
        	x.sleep(1000);
        	x.send(login + "{TAB}" + password + "{ENTER}{ENTER}", false);
        }
	}
	
	public static void baiscAuth_withEnter() {
        File file = new File("Lib", "jacob-1.18-x86.dll");
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
        AutoItX x = AutoItUtil.getInstance();
        if (x.winWait("Authentication Required", null, 20)) {
        	x.winActivate("Authentication Required");
        	x.sleep(1000);
        	x.send("{ENTER}", false);
        }
	}
}
