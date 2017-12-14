package com.shenpu.proxy.blacklist.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "syb_blacklist", type = "blacklist", shards = 1, replicas = 0, refreshInterval = "-1") 
public class Blacklist {
    
	@Id
	private String blacklistId;

    private String realName;

    private Integer idType;

    private String idNo;

    private String reason;

    private String remark;

    private Integer status;

    public String getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(String blacklistId) {
        this.blacklistId = blacklistId == null ? null : blacklistId.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}