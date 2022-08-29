package com.ezen.farmocean.mypage.service;

import java.util.regex.Pattern;

public class MypageFunction {
	
	// 닉네임 형식 체크
	public static boolean checkNickName(String value) {
		String pattern = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$";
		
		return Pattern.matches(pattern, value);
	}

}
