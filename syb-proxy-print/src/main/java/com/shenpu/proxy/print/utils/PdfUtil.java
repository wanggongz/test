package com.shenpu.proxy.print.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.shenpu.proxy.print.config.FileConfig;
import com.shenpu.proxy.print.domain.Appnt;
import com.shenpu.proxy.print.domain.Bene;
import com.shenpu.proxy.print.domain.Insured;
import com.shenpu.proxy.print.domain.Policy;
import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
@Component
public class PdfUtil {

	private static final Logger logger = LoggerFactory.getLogger(PdfUtil.class);
	
	@Autowired
	private FileConfig fileConfig;
	
	@SuppressWarnings("resource")
	public void createPdf(String policyName, VelocityContext context, String pathToPDFvm, String PDFvmName,
			String pathPdf) throws Exception {
		try {
			// step 1
			Document document = new Document();
			// step 2
			String policyPDFname = pathPdf + policyName;
			File file = new File(policyPDFname);
			file.getParentFile().mkdirs();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(policyPDFname));
			// step 3
			document.open();
			String value = get(context, pathToPDFvm, PDFvmName);
			String TempPolicy = pathToPDFvm + policyName.replaceAll(".pdf", "") + "temp.html";

			File txt = new File(TempPolicy);
			if (!txt.exists()) {
				txt.createNewFile();
			}
//			logger.info("保单名称：" + policyPDFname);
			PrintWriter printWriter = new PrintWriter(TempPolicy);
			printWriter.print(value);
			printWriter.flush();
			FontProvider fontProvider = new DefaultFontProvider();
//			logger.info("html名称：" + TempPolicy);
			@SuppressWarnings("unused")
			FileInputStream fileInput = new FileInputStream(TempPolicy);
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(TempPolicy), null,
					Charset.forName("UTF-8"), fontProvider);
			// step 5
			document.close();
			if (txt.exists()) {
				txt.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String get(VelocityContext context, String path, String name) throws Exception {
		// 初始化并取得Velocity引擎
		Properties properties = new Properties();
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
		VelocityEngine ve = new VelocityEngine();
		ve.init(properties);
		// 取得velocity的模版
		Template t = ve.getTemplate(name, "UTF-8");

		// 取得velocity的上下文context
		StringWriter writer = new StringWriter();

		// 把数据填入上下文
		t.merge(context, writer);

		// 输出
		String out = writer.toString();
		logger.info(writer.toString());
		return out;
	}

	
	class DefaultFontProvider implements FontProvider {
		@Override
		public Font getFont(String arg0, String arg1, boolean arg2, float arg3, int arg4, BaseColor arg5) {
			BaseFont baseFont;
			Font font14_normal = null;
			//simsun.ttc
			try {
				baseFont = BaseFont.createFont(fileConfig.getFontRealPath(), BaseFont.IDENTITY_H,
						BaseFont.NOT_EMBEDDED);
				font14_normal = new Font(baseFont, 8, Font.NORMAL);
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return font14_normal;
		}

		@Override
		public boolean isRegistered(String arg0) {
			return false;
		}
	}

	public void createPolicyToPdf(String policyNo, Appnt appnt, List<Insured> insureds, List<Bene> benes,Policy policy) {
		
		String pathPdf = fileConfig.getPdfRealPath();
		String str = "";
		String policyName = policyNo + "_policy.pdf";// -----------------------此处需要进行修改
		VelocityContext context = new VelocityContext();
		String pathToPDFvm = fileConfig.getVmRealPath();
		String PDFvmName = fileConfig.getVmName();
		// 签署声明的PNG图片
		GenerateImage(str, pathToPDFvm + policyNo + ".png");
		context.put("appnt", appnt);
		context.put("insureds", insureds);
		context.put("benes", benes);
		context.put("policy", policy);
//		context.put("insureDetail", insureDetail);
//		context.put("impartList", impartInformtionList);
		context.put("imgpath", fileConfig.getImgpath());
		context.put("base64str", policyNo + ".png");
		context.put("nowTime", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		try {
			createPdf(policyName, context, pathToPDFvm, PDFvmName, pathPdf);
			File f = new File(pathToPDFvm + policyNo + ".png"); // 输入要删除的文件位置
			logger.info("流水号："+policy.getApplyNo()+"，出单成功。");
			if (f.exists())
				f.delete();
		} catch (Exception e) {
			logger.error("流水号"+policy.getApplyNo()+"出单异常：{}",e);
		}
	}
	
	
	public boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	public static void main(String[] args) {
//		VelocityEngine ve = new VelocityEngine();
//		
//		
//		Properties properties = new Properties();   
//	    String basePath = "D:/20170309";   
//	    // 设置模板的路径   
//	    properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, basePath);   
//	    // 初始花velocity 让设置的路径生效   
//	    ve.init(properties);
//	    Template t = ve.getTemplate("/policyPDF.vm","GBK");
//		
//		System.out.println(t);
//	}


	
	
}
