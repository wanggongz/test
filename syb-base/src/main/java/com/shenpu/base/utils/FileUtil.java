package com.shenpu.base.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * @author wang_sheng
 *
 */
public class FileUtil {
	
	public static void main(String[] args) {   
        String path = "/Users/wang_sheng/image/images/笑笑/";  
        List<String> names = new ArrayList<String>();
        getAllFileNameByDir(names,path); 
        System.out.println(names.size());
    }   
    
	/**
	 * 获取文件夹下所有文件名称
	 * @param names
	 * @param path
	 */
	public static void getAllFileNameByDir(List<String> names,String path){   
        File file = new File(path);   
        // get the folder list   
        File[] array = file.listFiles();   
          
        for(int i=0;i<array.length;i++){   
            if(array[i].isFile()){  
            	if(!array[i].getName().equals(".DS_Store")){//mac下存在.DS_Store文件
                    names.add(array[i].getName());
            	}
            }else if(array[i].isDirectory()){   
            	getAllFileNameByDir(names,array[i].getPath());   
            }   
        }   
    }
    
    /**
	 * 获取文件夹下所有文件路径
	 * @param names
	 * @param path
	 */
	public static void getAllFilePathByDir(List<String> names,String path){   
        File file = new File(path);   
        // get the folder list   
        File[] array = file.listFiles();   
          
        for(int i=0;i<array.length;i++){   
            if(array[i].isFile()){  
            	if(!array[i].getName().equals(".DS_Store")){//mac下存在.DS_Store文件
                    names.add(array[i].getPath());
            	}
            }else if(array[i].isDirectory()){   
            	getAllFilePathByDir(names,array[i].getPath());   
            }   
        }   
    }
    
    
    
}
