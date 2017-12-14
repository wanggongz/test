package com.shenpu.proxy.blacklist.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "syb_address_dictionary", type = "addressDictionary", shards = 1, replicas = 0, refreshInterval = "-1") 
public class AddressDictionary {

    private String placeType;

    private String placeCode;

    private String placeName;

    private String upPlaceCode;
    
    private List<AddressDictionary> children;

    @Id
	private Long id;
    
    
	public List<AddressDictionary> getChildren() {
		return children;
	}

	public void setChildren(List<AddressDictionary> children) {
		this.children = children;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType == null ? null : placeType.trim();
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode == null ? null : placeCode.trim();
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getUpPlaceCode() {
        return upPlaceCode;
    }

    public void setUpPlaceCode(String upPlaceCode) {
        this.upPlaceCode = upPlaceCode == null ? null : upPlaceCode.trim();
    }
}