package com.java.softsec.core;


	public class Otp {
		public static String generatePIN() 
		   {
		        //generate a 4 digit integer 1000 <10000
		        Integer randomPIN = (int)(Math.random()*9000)+1000;

		        //Store integer in a string
		        String pin=randomPIN.toString();
		        return pin;
		   }	
}
	


