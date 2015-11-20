package com.java.softsec.core;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Myencoder {
	
		public static String hashedpassword(String input) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
			return encoder.encode(input);
			
		}
	}


