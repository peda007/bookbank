package com.msh.bookbank.util;

import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 공통 유틸
 * @author moon
 * @since 2019.07.20.
 */
public class CommonUtil {

	/**
	 * null이나 empty인지 확인
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		boolean result = true;
		if(obj != null) {
			if(obj instanceof String) {
				result = ((String)obj).isEmpty();
			} else if(obj instanceof String[]) {
				result = ((String[])obj).length == 0;
			} else if(obj instanceof Map<?,?>) {
				result = ((Map<?,?>)obj).isEmpty();
			} else if(obj instanceof Collection<?>) {
				result = ((Collection<?>)obj).isEmpty();
			} else if(obj instanceof JSONObject) {
				result = ((JSONObject)obj).isEmpty();
			} else if(obj instanceof JSONArray) {
				result = ((JSONArray)obj).isEmpty();
			} else {
				result = false;
			}
		}
		return result;
	}
	
}
