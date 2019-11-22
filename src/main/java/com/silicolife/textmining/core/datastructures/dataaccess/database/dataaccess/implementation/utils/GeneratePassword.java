package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class GeneratePassword {

	public static String generate(String rawPassword) {
		BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
		return pass.encode(rawPassword);
	}
	
	public static Boolean checkPassword(String rawPassword, String hashPassword) {
		BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
		return pass.matches(rawPassword, hashPassword);
	}

	//	public static String generate(String password, Object salt) {
	//		ShaPasswordEncoder pass = new ShaPasswordEncoder(256);
	//		pass.setIterations(1000000);
	//		String a = pass.encodePassword(password, salt);
	//		return a;
	//	}

	//	public static void main(String[] args) {
	//
	//		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	//		encoder.setIterations(13);
	//		String salt = encoder.encodePassword("1", null);
	//		
	//		System.out.print(GeneratePassword.generate("teste", salt));
	//
	//	}
}
