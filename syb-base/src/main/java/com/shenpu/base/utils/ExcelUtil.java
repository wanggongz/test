package com.shenpu.base.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	/**
	 * 导出
	 * list：导出的集合
	 * columnNames：表头信息
	 * fileName:保存路径
	 */
	public static Workbook exportExcel(List<Object> list,String[] columnNames,String fileName) throws IOException{
		List<Map<String ,Object >> list1=new ArrayList<Map<String ,Object >>();
		String[] keys = getFiledName(list.get(0));
		Map<String ,Object > map=null;
		//list为数据
		for(int i=0;i<list.size();i++){
			try {
				map = FieldUtil.entity2Map(list.get(i));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			list1.add(map);
		}
		//创建一个excel工作簿
	    HSSFWorkbook wb = new HSSFWorkbook();
		//创建一个sheet页，并命名
		HSSFSheet sheet=wb.createSheet("excel");
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);;
		style.setVerticalAlignment(VerticalAlignment.CENTER);;
		//手动设置列宽
		for(int i=0;i<keys.length;i++){
			sheet.setColumnWidth((short)i, (short)(35.7*100));
		}
		//创建第一行
		Row row = sheet.createRow((short)0);
		//设置列名
		for(int i=0;i<columnNames.length;i++){
			System.out.println(columnNames[i]);
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
		}
		//设置每行每列的值
		for(int i=0;i<list.size();i++){
			//创建行
			Row row1 = sheet.createRow(i+1);
			row1.setHeight((short)1000); 
			//给行每列赋值
			for(int j=0;j<keys.length;j++){
				Cell cell = row1.createCell(j);
				Object obj = list1.get(i).get(keys[j]);
				System.out.println(list1.get(i).get(keys[j]));
				cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
				cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);;
				if(obj!=null){
					cell.setCellValue(obj.toString());
				}else{
					cell.setCellValue("");
				}
			}
		}
		OutputStream os=new FileOutputStream(fileName+new Date().getTime()+".xlsx");
		wb.write(os);
	    //文件流设置文件保存位置
		os.close();
		return wb;
	}
	
	/** 
    * 获取对象的属性名数组 
    * */  
   private static String[] getFiledName(Object o){  
	   	
	   	Field[] fields=o.getClass().getDeclaredFields();  
        String[] fieldNames=new String[fields.length];  
	    
        for(int i=0;i<fields.length;i++){  
	        fieldNames[i]=fields[i].getName();  
        }  
	    return fieldNames;  
	    
   }
   
   /**
    * 将对象转换为map
    * @param type
    * @param map
    * @return
    */
   public static Object convertMap(Class<?> type, Map<?, ?> map)
           throws IntrospectionException, IllegalAccessException,
           InstantiationException, InvocationTargetException {
       BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
       Object obj = type.newInstance(); // 创建 JavaBean 对象
       // 给 JavaBean 对象的属性赋值
       PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
       for (int i = 0; i< propertyDescriptors.length; i++) {
           PropertyDescriptor descriptor = propertyDescriptors[i];
           String propertyName = descriptor.getName();

           if (map.containsKey(propertyName)) {
               // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
               Object value = map.get(propertyName);

               Object[] args = new Object[1];
               args[0] = value;

               descriptor.getWriteMethod().invoke(obj, args);
           }
       }
       return obj;
   }  
   
   /**
    * 读取Excel数据内容
    * @param fileName:读取文件的全路径；Clazz返回集合对应的实体类
    */
   private static POIFSFileSystem fs;
   private static Workbook wb;
   private static Sheet sheet;
   private static Row row;
   
   public static List<Map<String, Object>> readExcelContent(String fileName,Class<?> clazz) {
	   
	   List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	   String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	   
	   try {
			Object object = clazz.newInstance();
			String[] filedName = getFiledName(object);
			InputStream is = new FileInputStream(fileName);
			
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(is);
			} else {
				System.out.println("您输入的excel格式不正确");
			}
//		   try {
//		       fs = new POIFSFileSystem(is);
//		       wb = new HSSFWorkbook(fs);
//		   } catch (IOException e) {
//		       e.printStackTrace();
//		   }
		   sheet = wb.getSheetAt(0);
		   // 得到总行数
		   int rowNum = sheet.getLastRowNum();
		   row = sheet.getRow(0);
		   int colNum = row.getPhysicalNumberOfCells();
		   // 正文内容应该从第二行开始,第一行为表头的标题
		   for (int i = 1; i <= rowNum; i++) {
		       row = sheet.getRow(i);
		       int j = 0;
		       Map< String, Object> map=new HashMap< String, Object>();
		       while (j < colNum) {
		           // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
		           // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
		           // str += getStringCellValue(row.getCell((short) j)).trim() +
		           // "-";
		    	   Cell cell = row.getCell((short) j);
		    	   
		    	   
		    	   map.put(filedName[j], cell);
//		    	   System.out.println(getCellFormatValue(row.getCell((short) j)));
		           j++;
		       }
		       list.add(map);
		   }
		   return list;
	} catch (Exception e) {
		System.out.println("未找到指定路径的文件!");
		e.printStackTrace();
		return null;
	}
   }
   
   
   
   
   /**
    * 根据HSSFCell类型设置数据
    * @param cell
    * @return
    */
private static String getCellFormatValue(Cell cell) {
       String cellvalue = "";
       if (cell != null) {
           CellType cellStyle = cell.getCellTypeEnum();
           
           // 判断当前Cell的Type/getCellType()
           if(cellStyle.equals(CellType.NUMERIC)){// 如果当前Cell的Type为NUMERIC
    		// 判断当前的cell是否为Date
               if (HSSFDateUtil.isCellDateFormatted(cell)) {
                   // 如果是Date类型则，转化为Data格式
            	   //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                   //cellvalue = cell.getDateCellValue().toLocaleString();
                   //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                   Date date = cell.getDateCellValue();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                   cellvalue = sdf.format(date);
               }else {// 如果是纯数字
                   // 取得当前Cell的数值
            	   //将数字设置为字符串类型，否则读出来的是double类型无法获取int
            	   cell.setCellType(CellType.STRING);
                   cellvalue = cell.getRichStringCellValue().getString();
               }
    	   }else if(cellStyle.equals(CellType.STRING)){// 如果当前Cell的Type为STRIN
    		   // 取得当前的Cell字符串
               cellvalue = cell.getRichStringCellValue().getString();
               
    	   }
//           switch (cell.getCellType()) {
//           case Cell.CELL_TYPE_NUMERIC: {
//               break;
//           }
//           case HSSFCell.CELL_TYPE_STRING:
//               break;
//           // 默认的Cell值
//           default:
//               cellvalue = " ";
//           }
       } else {
           cellvalue = "";
       }
       return cellvalue;
   }
	
   
   /**
    * 读取Excel表格表头的内容
    * @param InputStream
    * @return String 表头内容的数组
    */
   public String[] readExcelTitle(InputStream is) {
	   System.out.println("读取Excel表格表头的内容");
	   try {
           fs = new POIFSFileSystem(is);
           wb = new HSSFWorkbook(fs);
       } catch (IOException e) {
           System.out.println("异常");
    	   e.printStackTrace();
       }
       sheet = wb.getSheetAt(0);
       row = sheet.getRow(0);
       // 标题总列数
       int colNum = row.getPhysicalNumberOfCells();
       System.out.println("colNum:" + colNum);
       String[] title = new String[colNum];
       for (int i = 0; i < colNum; i++) {
           //title[i] = getStringCellValue(row.getCell((short) i));
           title[i] = getCellFormatValue(row.getCell((short) i));
       }
       return title;
   }
   
   
//   //测试导入
//   public static void main(String[] args) {
//	   try {
//           String fileName="D:\\mins\\1gfbj.xlsx";
////           System.out.println("获得Excel表格的内容:");
//           List<Map<String, Object>> map = ExcelUtil.readExcelContent(fileName,new Fied().getClass());
//           int count=0;
//           
//           for(Map<String, Object> m: map){
//        	   String code1 = m.get("code1").toString();
//        	   
//        	   for(Map<String, Object> m2: map){
//        		   String code2 = m2.get("code2").toString();
//        		   if(code1.equals(code2)){
//        			   System.out.println(code2);
//        			   count = count+1;
//        		   }
//        	   }
//           }
//           System.out.println(count);
//	   } catch (Exception e) {
//           e.printStackTrace();
//       }
//   }
   
   //测试导出
//   public static void main(String[] args) throws IOException {
//	   	ExcelModule module= new ExcelModule();
//		module.setAge(1);
//		module.setCost("1");
//		module.setCoverage(1);
//		module.setInsuranceTime("1");
//		module.setPayTime("1");
//		module.setPayWorkplace("1");
//		module.setPolicyYear(1);
//		module.setSex("m");
//		module.setWorkspace("1");
//		List<Object> list1	= new ArrayList<Object>(); 
//	    list1.add(module);
//	    String columnNames[]={"保险期间","保险期间单位","交费期间","交费期间单位","性别","投保年龄","保单年度","基本保额","现金价值"};
//	    String fileName="d://firstExcel2";
//	    exportExcel(list1,columnNames,fileName);
//	    System.out.println("导出成功！");
//   }
   
}
class Fied{
	public String code1;
	public String code2;
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	
	
}
