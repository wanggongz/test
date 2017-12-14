package com.shenpu.proxy.blacklist.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "syb_occupation_dictionary", type="occupationDictionary", shards = 1, replicas = 0, refreshInterval = "-1")
public class OccupationDictionary {

    private String business;

    private String occupationType;

    private String occupationCode;

    private String occupationName;

    private String occupationCategory;

    @Id
    private String id;
    
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType == null ? null : occupationType.trim();
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode == null ? null : occupationCode.trim();
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName == null ? null : occupationName.trim();
    }

    public String getOccupationCategory() {
        return occupationCategory;
    }

    public void setOccupationCategory(String occupationCategory) {
        this.occupationCategory = occupationCategory == null ? null : occupationCategory.trim();
    }
}