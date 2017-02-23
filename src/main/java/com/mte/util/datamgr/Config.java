package com.mte.util.datamgr;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class Config {

	@SuppressWarnings("static-access")
	public static String getProperty(String key) {
		Properties props = new Properties();
		Encoder_Convert encoderConvert = new Encoder_Convert();
		String keyValue, encoder;
		
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("config.properties"));
			props.load(in);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		keyValue = props.getProperty(key);
		//System.out.println(keyValue);
		encoder = encoderConvert.getEncoding(keyValue);		
		//System.out.println(encoder);
		
		if( encoder == "ISO-8859-1" ){
			keyValue = encoderConvert.ISO2UTF8(keyValue);
		}
		return keyValue;
	}

	
	public static void main(String[] args){
		System.out.println(getProperty("InputMode"));
	}
}
