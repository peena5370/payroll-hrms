package com.company.payroll.utils;

public class Generator {

	/** 
	 * Method of generating random string with specific length
	 * @param
	 * @return	stringbuilder
	 * */
	public static String generateRandomString(int len) {
		String randomChar = "ABCDFEGHIJKLMNOPQRSTUVWXYZ1234567890";
//		for servlet use
//		StringBuffer sb = new StringBuffer(7);
		StringBuilder sb = new StringBuilder(len);
		
		for(int i=0; i<len; i++) {
			int index = (int)(randomChar.length() * Math.random());
			
			sb.append(randomChar.charAt(index));
		}
		return sb.toString();
	}
}
