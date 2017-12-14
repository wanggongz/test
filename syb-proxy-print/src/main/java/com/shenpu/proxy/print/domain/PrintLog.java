package com.shenpu.proxy.print.domain;

import java.io.Serializable;
import java.util.Date;

public class PrintLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String applyNo;

    private Date printTime;

    private Integer status;

    private Integer isMsg;

    private Integer isPdf;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsMsg() {
        return isMsg;
    }

    public void setIsMsg(Integer isMsg) {
        this.isMsg = isMsg;
    }

    public Integer getIsPdf() {
        return isPdf;
    }

    public void setIsPdf(Integer isPdf) {
        this.isPdf = isPdf;
    }

	@Override
	public String toString() {
		return "PrintLog [applyNo=" + applyNo + ", printTime=" + printTime + ", status=" + status + ", isMsg=" + isMsg
				+ ", isPdf=" + isPdf + "]";
	}
    
}