package com.shenpu.base.utils;

public class TestReplace {

	
	public static void main(String[] args) {
		String ss = "      		<if test=\"systemShortName != null\">systemShortName=#{systemShortName},</if>";
		String old = "systemShortName";
		String newS = "systemShort_name";
		replaceAllNoBig(ss, old, newS);
	}
	//执行替换无大括号和前一位没有.的
	public static String replaceAllNoBig(String line,String oldStr, String newStr){
		String before = null;
		StringBuilder sb = new StringBuilder();
		String[] split = line.split(oldStr);
		
		String res = "";
		
		int startLoc = 0;
		for(int i=0;i<split.length-1;i++){
			String str = split[i];
			sb.append(str);
			if(before!=null){
				str = str+before;
			}
			//当前查找字符串的起止位置
			int start = (str).length();
			int end = start+ oldStr.length();
			
//				System.out.println(line.subSequence(start, end));
			before = line.substring(0,end);
			String substring = line.substring(end, end+1);
			String startStr = line.substring(startLoc, start);
			startLoc =  end;
			
			res = res + startStr;
//			System.out.println(substring);
			if(!substring.equals("}")){
				res = res + newStr;
			}else{
				res = res+oldStr;
			}
			if(i == split.length-2){
				String endStr = line.substring(end, line.length());
				res = res +endStr ;
			}
		}
		System.out.println(res);
		return line;
	}
	
}
