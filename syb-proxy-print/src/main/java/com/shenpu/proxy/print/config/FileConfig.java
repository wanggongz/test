package com.shenpu.proxy.print.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class FileConfig {

	@Value("${font_realPath}")
	public String fontRealPath;
	
	@Value("${pdf_realPath}")
	public String pdfRealPath;
	
	@Value("${vm_realPath}")
	public String vmRealPath;
	
	@Value("${vm_name}")
	public String vmName;
	
	@Value("${imgpath}")
	public String imgpath;

	public String getFontRealPath() {
		return fontRealPath;
	}

	public void setFontRealPath(String fontRealPath) {
		this.fontRealPath = fontRealPath;
	}

	public String getPdfRealPath() {
		return pdfRealPath;
	}

	public void setPdfRealPath(String pdfRealPath) {
		this.pdfRealPath = pdfRealPath;
	}

	public String getVmRealPath() {
		return vmRealPath;
	}

	public void setVmRealPath(String vmRealPath) {
		this.vmRealPath = vmRealPath;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	
}
