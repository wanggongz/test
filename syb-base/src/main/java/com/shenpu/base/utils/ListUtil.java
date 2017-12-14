package com.shenpu.base.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtil {
	
	public static List<Object> iteratorToList(Iterator<?> iterable){
		List<Object> list = new ArrayList<Object>();
		while(iterable.hasNext()){
			list.add(iterable.next());
		}
		return list;
	}
	
	
	
}
