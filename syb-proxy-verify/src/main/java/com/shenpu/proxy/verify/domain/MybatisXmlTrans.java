package com.shenpu.proxy.verify.domain;


import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author ws
 * 修改mybatis的xml中的数据库对应字段（驼峰改为'_'）
 */
public class MybatisXmlTrans {
	private Document doc=null;
    
	/**
	 *  入口
	 * @param filePath：需要修改的xml文件
	 * @param headTag：可不传
	 * @return
	 */
    public static String _ToA(String filePath,String headTag){
    	MybatisXmlTrans xml=new MybatisXmlTrans(filePath);
    	return xml.getElementContent("mapper");
    }
	
	
	/**
     * param1 xmlPath 指定XML文件的本地路径 
     * @param xmlPath
     */
    public MybatisXmlTrans(String xmlPath){
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
        	NodeList elementsByTagName = doc.getElementsByTagName(elementName);
    		
        	NodeList childNodes = elementsByTagName.item(0).getChildNodes();
        	for(int i=0;i<childNodes.getLength();i++){
        		
        		Node item = childNodes.item(i);
        		if(item.getAttributes()!=null){
        			dg(item);
        		}
        	}
        	//XML转字符串  
        	TransformerFactory   tf   =   TransformerFactory.newInstance();  
        	Transformer t = tf.newTransformer();  
        	ByteArrayOutputStream   bos   =   new   ByteArrayOutputStream();  
        	t.transform(new DOMSource(doc), new StreamResult(bos));  
        	String xmlStr = bos.toString(); 
        	System.out.println(xmlStr);
        	return xmlStr;
        }catch(Exception e){
            strReturn=e.toString().replaceAll("\n", "");
        }
        return strReturn;
    }
    
    public void dg(Node item){
    	NodeList childNodes = item.getChildNodes();
    	int length = childNodes.getLength();
    	NamedNodeMap attributes = item.getAttributes();
    	Node firstChild = item.getFirstChild();
    	Node lastChild = item.getLastChild();
    	if(firstChild!=null&&firstChild.getAttributes()==null){
    		String textContent = firstChild.getTextContent();
    		firstChild.setTextContent(getUpdate(textContent));
    	}
    	if(lastChild!=null&&lastChild.getAttributes()==null){
    		String textContent = lastChild.getTextContent();
    		lastChild.setTextContent(getUpdate(textContent));
    	}
    	if(length==0){
    		if(attributes!=null){
    			int length2 = attributes.getLength();
        		for(int i=0;i<length2;i++){
        			Node item2 = attributes.item(i);
        			if(item2.getNodeName().equals("column")){
        				//替换
        				item2.setNodeValue(getUpdate(item2.getNodeValue()));
        			}
        		}
    		}
    	}
    	if(length>1){
    		if(attributes!=null){
        		for(int i=0;i<length;i++){
        			Node item2 = childNodes.item(i);
        			if(item2!=null){
        				NamedNodeMap attributes12 = item2.getAttributes();
            			if(attributes12!=null){
            				dg(item2);
            			}else{
            				continue;
            			}
        			}else{
        				continue;
        			}
        		}
    		}
    	}
    	if(length==1){
    		//如果没有子节点读取内容
    		if(item!=null){
        		if(attributes!=null){
            		item.setTextContent(getUpdate(item.getTextContent()));
        		}
    		}
    	}
    }
    
    public String getUpdate(String textContent){
    	StringBuilder result= new  StringBuilder();
		for(int i=0;i<textContent.length();i++){
			String res= "";
	    	String s = textContent.substring(i, i + 1);  
			int indexOf = textContent.indexOf("}", i);
	        int indexOf2 = textContent.indexOf("{", i);
	        
	        String before = null;
	        //获取前一个字符
	        if(i>2){
	        	before = textContent.substring(i-1, i);
	        }
	    	// 在大写字母前添加下划线  
	        if (s.equals(s.toUpperCase()) 
	        		&& s.matches( "[a-zA-Z]")
	        		&&(!((indexOf2==-1&&indexOf!=-1)||(indexOf2>indexOf))&&!before.equals(""))) {  
	        	if(!before.equals(before.toUpperCase())){
	        		res = res+"_";
					s = s.toLowerCase();
				}else{
					s = s.toLowerCase();
				}
	        }
			result.append(res+s);
		}
    	return result.toString();
    }
    
    public static void main(String[] args){
//    	MybatisXmlTrans xml=new MybatisXmlTrans("D:\\2.xml");
        System.out.println(_ToA("D:\\2.xml","mapper"));
    }
}