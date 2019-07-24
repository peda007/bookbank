package com.msh.bookbank.util;

/**
 * JSP 에서 사용하는 Adder
 * @author MSH
 * @since 2019.07.24.
 */
public class Adder {

	/**
	 * obj1과 obj2가 같으면 addStr 반환
	 * @param obj1
	 * @param obj2
	 * @param addStr
	 * @return
	 */
	public static String ifSame(String str1, String str2, String addStr) {
		String resultStr = "";
		try {
			if(CommonUtil.isNullOrEmpty(str1) || CommonUtil.isNullOrEmpty(str2)) {
				throw new NullPointerException();
			}
			
			if(str1.equals(str2)) {
				resultStr = addStr;
			}
		} catch(Exception e) {}
		return resultStr;
	}
}
