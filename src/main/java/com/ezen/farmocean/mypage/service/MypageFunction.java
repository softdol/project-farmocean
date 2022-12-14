package com.ezen.farmocean.mypage.service;

import java.util.regex.Pattern;

public class MypageFunction {
	
	// 닉네임 형식 체크
	public static boolean checkNickName(String value) {
		String pattern = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{3,10}$";
		
		return Pattern.matches(pattern, value);
	}
	
	// 비밀번호 형식 체크
	public static boolean checkPassword(String value) {		
		//비밀번호 (숫자, 문자, 특수문자 최소 1개씩 포함 8~15자리 이내)	
		String pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$";
		
		return Pattern.matches(pattern, value);
	}
	
	// 이메일 형식 체크
	public static boolean checkEmail(String value) {		
		//String patterns = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";		
		String pattern = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$/i";	
//						  ^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$
//						  ^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$
		//String num = "^[0-9]+$";
		
		return Pattern.matches(pattern, value);
	}
	
	// 핸드폰 번호 형식 체크
	public static boolean checkPhone(String value) {		
		String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";	
		
		return Pattern.matches(pattern, value);
	}
	
	// 숫자만 입력 가능
	public static boolean checkNumber(String value) {		
		String pattern = "^[0-9]+$";	
		
		return Pattern.matches(pattern, value);
	}

}
