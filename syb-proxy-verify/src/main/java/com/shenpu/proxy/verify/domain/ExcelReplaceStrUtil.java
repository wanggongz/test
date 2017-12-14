package com.shenpu.proxy.verify.domain;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.shenpu.base.utils.FieldUtil;

public class ExcelReplaceStrUtil {
	
	// excel中文件集合
	private static List<String> excelFileNames = new ArrayList<String>();
	// 更改字段集合
	private static Map<String, List<Map<String, String>>> fileMap = new HashMap<String, List<Map<String, String>>>();
	// 需要更改的文件
	private static List<File> updateFiles = new ArrayList<File>();
	// 每个文件对应的每行信息
	private static Map<String, List<String>> textMap = new HashMap<String, List<String>>();
	//文件集合
	private static List<File> files = new ArrayList<File>();
	
	// 调度，方法入口
	public static void getUpdateFiles() throws Exception {
		//// 准备数据
		// 获取目录下所有文件
		searchPath("D:/demo");
		// 获取excel中的名称和对应的字段
		selectToupdateFile();
		// 目录和excel对应的文件名称对比，获取最终需要改的文件名称。
		removeXStr();
		// 获取对应文件的行数据
		getFileLineText();
		//// 处理数据
		update();
	}
	
	/**
	 * 导出 list：导出的集合 columnNames：表头信息 fileName:保存路径
	 */
	public static Workbook exportExcel(List<Object> list, String[] columnNames, String fileName) throws IOException {
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		String[] keys = getFiledName(list.get(0));
		Map<String, Object> map = null;
		// list为数据
		for (int i = 0; i < list.size(); i++) {
			try {
				map = FieldUtil.entity2Map(list.get(i));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			list1.add(map);
		}
		// 创建一个excel工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建一个sheet页，并命名
		HSSFSheet sheet = wb.createSheet("excel");
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		// 手动设置列宽
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 100));
		}
		// 创建第一行
		Row row = sheet.createRow((short) 0);
		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			System.out.println(columnNames[i]);
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
		}
		// 设置每行每列的值
		for (int i = 0; i < list.size(); i++) {
			// 创建行
			Row row1 = sheet.createRow(i + 1);
			row1.setHeight((short) 1000);
			// 给行每列赋值
			for (int j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				Object obj = list1.get(i).get(keys[j]);
				System.out.println(list1.get(i).get(keys[j]));
				cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
				cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
				;
				if (obj != null) {
					cell.setCellValue(obj.toString());
				} else {
					cell.setCellValue("");
				}
			}
		}
		OutputStream os = new FileOutputStream(fileName + new Date().getTime() + ".xlsx");
		wb.write(os);
		// 文件流设置文件保存位置
		os.close();
		return wb;
	}

	/**
	 * 获取对象的属性名数组
	 */
	private static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
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
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象
		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
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

	public static List<Map<String, Object>> readExcelContent(String fileName, Class<?> clazz) {

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
			sheet = wb.getSheetAt(0);
			// 得到总行数
			int rowNum = sheet.getLastRowNum();
			row = sheet.getRow(0);
			if (row != null) {
				int colNum = row.getPhysicalNumberOfCells();
				// 正文内容应该从第二行开始,第一行为表头的标题
				for (int i = 1; i <= rowNum; i++) {
					row = sheet.getRow(i);
					int j = 0;
					Map<String, Object> map = new HashMap<String, Object>();
					while (j < colNum) {
						// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
						// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
						Cell cell = row.getCell((short) j);
						map.put(filedName[j], cell);
						j++;
					}
					list.add(map);
				}
			}
			return list;
		} catch (Exception e) {
			System.out.println("readExcelContent异常!");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			CellType cellStyle = cell.getCellTypeEnum();
			// 判断当前Cell的Type/getCellType()
			if (cellStyle.equals(CellType.NUMERIC)) {// 如果当前Cell的Type为NUMERIC
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {// 如果是纯数字
						// 取得当前Cell的数值
						// 将数字设置为字符串类型，否则读出来的是double类型无法获取int
					cell.setCellType(CellType.STRING);
					cellvalue = cell.getRichStringCellValue().getString();
				}
			} else if (cellStyle.equals(CellType.STRING)) {// 如果当前Cell的Type为STRIN
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();

			}
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
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}

	public static String read(File src) {
		StringBuffer res = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(src));
			while ((line = reader.readLine()) != null) {
				res.append(line + "\r\n"); // 读成一个字符串
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res.toString();
	}

	// 转换文件格式
	public static boolean write(String cont, File dist) {
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(dist), "UTF-8");
			writer.write(cont);
			writer.flush();
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	

	/**
	 * @param //获取文件夹所有文件
	 */
	public static void searchPath(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				File child = fileList[i];
				if (child.isDirectory()) { // 是文件夹就递归
					String absolutePath = child.getAbsolutePath();
					searchPath(absolutePath);// 文件及
				} else {

					String relpath = child.getName();
					String substring = relpath.substring(relpath.lastIndexOf("\\") + 1);
					int lastIndexOf = substring.lastIndexOf(".");
					// 文件后缀名称
					if (lastIndexOf != -1) {
						String endPre = substring.substring(lastIndexOf + 1, substring.length());
						if (endPre.equals("xml")) {
							files.add(child);
						}
					}
				}
			}
		}
	}

	//获取excel中的xml文件名称和对应文件的修改字段
	public static void selectToupdateFile() {
		String fileName = "D:\\2.xlsx";
		List<Map<String, Object>> excelmMap = ExcelReplaceStrUtil.readExcelContent(fileName, new Filed().getClass());

		String upPath = "";// 上次读到的文件名
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int size = excelmMap.size();
		for (int k=0;k<size;k++) {
			Map<String, Object> m = excelmMap.get(k);
			
			String string = m.get("filedName").toString();
			String oldStr = m.get("oldStr").toString();
			String newStr = m.get("newStr").toString();
			Map<String, String> mm = new HashMap<String, String>();
			mm.put("oldStr", oldStr);
			mm.put("newStr", newStr);
			if (!string.equals("")) {
				list.add(mm);
				fileMap.put(string, list);
			}

			// 和上次的文件名不一样时，添加文件名，防止添加重复的文件名
			if (!string.equals(upPath) && !string.equals("")) {
				excelFileNames.add(string);
			} else {
				// 名字相同时添加，会重复添加，但是最终为最后一次获取的list
				fileMap.put(upPath, list);
			}
			if (string.equals("") && !upPath.equals("")) {
				list = new ArrayList<>();
			}
			upPath = string;
		}
	}

	// 修改数据
	public static void update() {
		for (File file : updateFiles) {// 遍历文件
			// 遍历修改字段
			List<Map<String, String>> list = readDocToGetUpdateFiled(file);
			String remove = remove(file);
			List<String> lines = textMap.get(remove);
			
			List<String> updateLines = null;
			for (Map<String, String> map : list) {
				updateLines = new ArrayList<String>();
				String oldStr = map.get("oldStr");
				String newStr = map.get("newStr");
				int size = lines.size();
				for (int i=0;i<size;i++) {// 遍历文件的每一行
					String str = lines.get(i);
					//替换Column
					String replaceAll = replaceAllColumn(str,oldStr, newStr);
					//替换没有大括号的
					String replaceAllNoBig = replaceAllNoBig(replaceAll, oldStr, newStr);
					updateLines.add(replaceAllNoBig);
					if(i==size-1){
						lines = updateLines;
					}
				}
				textMap.put(remove, lines);
			}
		}
	}

	private static Document doc = null;

	//执行替换Column
	public static String replaceAllColumn(String str,String oldStr, String newStr){
		//如果包含column，则执行替换column+oldStr
		if(str.indexOf("column")!=-1){
			str = str.replace("column=\""+oldStr, "column=\""+newStr);
		}
		return str;
	}
	//执行替换无大括号和引号
	public static String replaceAllNoBig(String line,String oldStr, String newStr){
		
		String ss = "and ts.systemCode like CONCAT('%',#{systemCode,jdbcType=VARCHAR},'%') ";
		
		String before = null;
		String[] split = line.split(oldStr);
		String res = "";
		//每次开始的起始点
		int startLoc = 0;
		if(line.indexOf(oldStr)!=-1){
			for(int i=0;i<split.length-1;i++){
				String str = split[i];
				if(before!=null){
					str = str+before;//当前目标字段前的字符串
				}
				//当前查找字符串的起止位置
				int start = (str).length();
				int end = start+ oldStr.length();
				//下次开始时，目标地段前的字符串=str+oldStr.length
				before = line.substring(0,end);
				//目标字段后一位字符
				String substring = line.substring(end, end+1);
				//目标字段前的字符串=str
				String startStr = line.substring(startLoc, start);
				//目标字符串前一位
				String beforeChar = line.substring(start-1, start);
				//下一次循环的起始位置
				startLoc =  end;
				
				int indexOf = line.indexOf("}", end);
		        int indexOf2 = line.indexOf("{", end);
		        
		        
		        int indexOf3 = line.indexOf(">", end);
		        int indexOf4 = line.indexOf("<", end);
				//拼接结果，先拼接目标字段之前的字符串
				res = res + startStr;
				//拼接更改后的目标字段
				if(!substring.equals("}")&&!beforeChar.equals("\"")&&!beforeChar.equals("{")){
					if(!(indexOf!=-1&&indexOf2==-1)&&!(indexOf4>indexOf3)){
						res = res + newStr;
					}else{
						res = res+oldStr;
					}
				}else{
					res = res+oldStr;
				}
				//如果是最后一个目标字段，拼接末尾字符串
				if(i == split.length-2){
					String endStr = line.substring(end, line.length());
					res = res +endStr ;
				}
			}
			return res;
		}else{
			return line;
		}
	}
	
	
	public ExcelReplaceStrUtil(String xmlPath) {
		// 很明显该类是一个单例,先获取产生DocumentBuilder工厂
		// 的工厂,在通过这个工厂产生一个DocumentBuilder,
		// DocumentBuilder就是用来产生Document的
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 这个Document就是一个XML文件在内存中的镜像
			doc = db.parse(new File(xmlPath));
		} catch (Exception e) {
			System.out.println("获取文档对象失败");
		}
	}

	// 读取doc获取修改字段
	public static List<Map<String, String>> readDocToGetUpdateFiled(File file) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		@SuppressWarnings("unused")
		ExcelReplaceStrUtil xml=new ExcelReplaceStrUtil(file.getAbsolutePath());
		NodeList elementsByTagName = doc.getElementsByTagName("resultMap");
    	int length = elementsByTagName.getLength();
		for(int i=0;i<length;i++){
			Node item = elementsByTagName.item(i);
			NamedNodeMap attributes = item.getAttributes();
			int length2 = attributes.getLength();
			for(int j=0;j<length2;j++){
				Node item2 = attributes.item(j);
				if(item2.getNodeName().equals("type")){
					String nodeValue = item2.getNodeValue();
					nodeValue = nodeValue.substring(nodeValue.lastIndexOf(".")+1,nodeValue.length());
					List<Map<String, String>> list2 = fileMap.get(nodeValue);
					list.addAll(0, list2);
				}
			}
		}
		return list;
	}

	//获取文件对应的每行数据
	public static void getFileLineText() throws Exception {
		// 遍历文件,获取每一行数据
		for (File file : updateFiles) {
			String name = file.getName();
			name = remove(file);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<String> list = new ArrayList<String>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(line );
			}
			textMap.put(name, list);
		}
	}

	// 目录和excel对应的文件名称对比，获取最终需要改的文件名称。
	public static void removeXStr() {
		for (File file : files) {
			String name = remove(file);
			for (String str : excelFileNames) {
				if (name.equals(str)) {
					updateFiles.add(file);
				}
			}
		}
	}

	//去除Mapper和后缀
	public static String remove(File file) {
		// 去除后缀
		String name = file.getName();
		name = name.substring(0, name.lastIndexOf("."));
		// 去除mapper
		int indexOf = name.indexOf("Mapper");
		if (indexOf != -1) {
			name = name.substring(0, indexOf);
		}
		return name;
	}

	// 测试导入
	public static void main(String[] args) throws Exception {
		// 获取更改的文件目录
		getUpdateFiles();
		
		Set<String> keySet = textMap.keySet();
		for(String str: keySet){
			for(File file: files){
				String remove = remove(file);
				if(remove.equals(str)){
					//要写入的数据list---->file
					List<String> list = textMap.get(str);
					StringBuilder sb = new StringBuilder();
					for(String st: list){
						sb.append(st+"\r\n");
					}
					write(sb.toString(), file);
				}
			}
		}
	}
}


