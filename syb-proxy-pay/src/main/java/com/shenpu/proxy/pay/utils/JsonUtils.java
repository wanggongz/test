package com.shenpu.proxy.pay.utils;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * json工具类
 * @author zhaopeng
 * @date Feb 29, 2016
 */
public class JsonUtils {
	private static Gson gson = new Gson();
	/**
	 * 
	 * @param jsonclass
	 * @param json
	 * @return
	 */
	public static Object json2Obj(Class<Object> jsonclass, String json) {
		Object obj = null;
		obj = gson.fromJson(json, jsonclass);
		return obj;

	}

	public static String obj2Json(Object obj) {

		String jsonStr = gson.toJson(obj);

		return jsonStr;
	}

	public static <T> T json2Obj(String json,Type tp) {

		T obj = gson.fromJson(json, tp);

		return obj;
	}
	
	public static <T> T json2Obj(String json,Class<T> tp) {

		T response = gson.fromJson(json, tp);

		return response;
	}
	
	public static Map<String,String> json2Map(String json) {

		Type tp = new TypeToken<Map<String,String>>() {}.getType();

		return gson.fromJson(json, tp);
	}
	
	public static String ObjectToJson(Object obj){
		 GsonBuilder gb =new GsonBuilder();
		 gb.disableHtmlEscaping();
		 gb.create().toJson(obj);
		 return gb.create().toJson(obj);
	}

}
