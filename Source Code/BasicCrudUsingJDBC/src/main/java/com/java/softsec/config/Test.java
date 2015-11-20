package com.java.softsec.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {
	public static void main(String[] args) {
	
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp now = new java.sql.Timestamp(today.getTime());
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
		 try {
			Date date = format.parse(now.toString());
			System.out.println(format.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
