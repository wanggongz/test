package com.shenpu.base.utils;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlProcessor {
	private Document doc=null;
    /**
     * param1 xmlPath 指定XML文件的本地路径 
     * @param xmlPath
     */
    public XmlProcessor(String xmlPath){
        	//很明显该类是一个单例,先获取产生DocumentBuilder工厂
          //的工厂,在通过这个工厂产生一个DocumentBuilder,
          //DocumentBuilder就是用来产生Document的
          DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
          try{
          DocumentBuilder db=dbf.newDocumentBuilder();  
          //这个Document就是一个XML文件在内存中的镜像
          doc= db.parse(new File(xmlPath)); 
              }catch(Exception e){
              System.out.println("获取文档对象失败");
          }
    }
  
    public String getElementContent(String elementName){
        String strReturn="";
        try{
        	NodeList resultMap = doc.getElementsByTagName("resultMap");
        	System.out.println("resultMap的个数："+resultMap.getLength());
        	int count = 0;;
        	for(int i = 0;i<resultMap.getLength();i++){
        		Node item = resultMap.item(i);
        		NodeList childNodes = item.getChildNodes();
        		NamedNodeMap attributes = item.getAttributes();
        		for(int l =1;l<attributes.getLength();l++){
        			Node item2 = attributes.item(l);
        			System.out.println("resultMap："+item2.getNodeName());
            		System.out.println("resultMap："+item2.getNodeValue());
        		}
        		
        		for(int j = 0;j<childNodes.getLength();j++){
        			Node item2 = childNodes.item(j);
        			NamedNodeMap attributes2 = item2.getAttributes();
//        			System.out.println(attributes2);
        			if(attributes2!=null){
        				count++;
        				int length = attributes2.getLength();
        				for(int k=0;k<length;k++){
        					Node item3 = attributes2.item(k);
        					System.out.println("reslut:"+item3.getNodeName());
        					System.out.println("reslut:"+item3.getNodeValue());
        					if(item3.getNodeName().equals("column")){
        						System.out.println("转换为下划线:"+underscoreName(item3.getNodeValue()).toLowerCase());
        					}
        				}
        			}
        		}
        	}
        	System.out.println("result的个数："+count);
        }catch(Exception e){
            strReturn=e.toString().replaceAll("\n", "");
        }
        return strReturn;
    }
    
    public static String underscoreName(String name) {  
        StringBuilder result = new StringBuilder();  
        if (name != null && name.length() > 0) {  
            // 将第一个字符处理成大写  
            result.append(name.substring(0, 1).toUpperCase());  
            // 循环处理其余字符  
            for (int i = 1; i < name.length(); i++) {  
                String s = name.substring(i, i + 1);  
                // 在大写字母前添加下划线  
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {  
                    result.append("_");  
                }  
                // 其他字符直接转成大写  
                result.append(s.toUpperCase());  
            }  
        }  
        return result.toString();  
    } 
    
    public static void main(String[] args){
        XmlProcessor xml=new XmlProcessor("D:\\AppntMapper.xml");
        System.out.println(xml.getElementContent("resultMap"));
    }
}