package com.shenpu.proxy.print.domain;

import java.io.Serializable;

public class Insured implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String insuredId;

    private String policyNo;

    private String orderNo;

    private String applyNo;

    private String insuredGrade;

    private String insuredName;

    private String sex;

    private Integer age;

    private String birthday;

    private String idType;

    private String idNo;

    private String occupation;

    private String occupationName;

    private String mobile;

    private String email;

    private String zipCode;

    private String nationality;

    private String nativeplace;

    private String provId;

    private String cityId;

    private String countyId;

    private String mapCoordinates;

    private String marriage;

    private String insuredRelation;

    private String yearIncom;

    private String height;

    private String weight;

    private String status;

    private String insertTime;

    private String updateTime;

    private String insuredType;

    private String address;

    private String premium;

    private String idTypeName;

    private String insuredRelationName;

    private String marriageName;

    private String countyIdName;

    private String cityIdName;

    private String provIdName;

    private String nativeplaceName;

    public String getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId == null ? null : insuredId.trim();
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public String getInsuredGrade() {
        return insuredGrade;
    }

    public void setInsuredGrade(String insuredGrade) {
        this.insuredGrade = insuredGrade == null ? null : insuredGrade.trim();
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName == null ? null : insuredName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName == null ? null : occupationName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace == null ? null : nativeplace.trim();
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId == null ? null : provId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    public String getMapCoordinates() {
        return mapCoordinates;
    }

    public void setMapCoordinates(String mapCoordinates) {
        this.mapCoordinates = mapCoordinates == null ? null : mapCoordinates.trim();
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }

    public String getInsuredRelation() {
        return insuredRelation;
    }

    public void setInsuredRelation(String insuredRelation) {
        this.insuredRelation = insuredRelation == null ? null : insuredRelation.trim();
    }

    public String getYearIncom() {
        return yearIncom;
    }

    public void setYearIncom(String yearIncom) {
        this.yearIncom = yearIncom == null ? null : yearIncom.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime == null ? null : insertTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getInsuredType() {
        return insuredType;
    }

    public void setInsuredType(String insuredType) {
        this.insuredType = insuredType == null ? null : insuredType.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium == null ? null : premium.trim();
    }

    public String getIdTypeName() {
        return idTypeName;
    }

    public void setIdTypeName(String idTypeName) {
        this.idTypeName = idTypeName == null ? null : idTypeName.trim();
    }

    public String getInsuredRelationName() {
        return insuredRelationName;
    }

    public void setInsuredRelationName(String insuredRelationName) {
        this.insuredRelationName = insuredRelationName == null ? null : insuredRelationName.trim();
    }

    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName == null ? null : marriageName.trim();
    }

    public String getCountyIdName() {
        return countyIdName;
    }

    public void setCountyIdName(String countyIdName) {
        this.countyIdName = countyIdName == null ? null : countyIdName.trim();
    }

    public String getCityIdName() {
        return cityIdName;
    }

    public void setCityIdName(String cityIdName) {
        this.cityIdName = cityIdName == null ? null : cityIdName.trim();
    }

    public String getProvIdName() {
        return provIdName;
    }

    public void setProvIdName(String provIdName) {
        this.provIdName = provIdName == null ? null : provIdName.trim();
    }

    public String getNativeplaceName() {
        return nativeplaceName;
    }

    public void setNativeplaceName(String nativeplaceName) {
        this.nativeplaceName = nativeplaceName == null ? null : nativeplaceName.trim();
    }

	@Override
	public String toString() {
		return "Insured [insuredId=" + insuredId + ", policyNo=" + policyNo + ", orderNo=" + orderNo + ", applyNo="
				+ applyNo + ", insuredGrade=" + insuredGrade + ", insuredName=" + insuredName + ", sex=" + sex
				+ ", age=" + age + ", birthday=" + birthday + ", idType=" + idType + ", idNo=" + idNo + ", occupation="
				+ occupation + ", occupationName=" + occupationName + ", mobile=" + mobile + ", email=" + email
				+ ", zipCode=" + zipCode + ", nationality=" + nationality + ", nativeplace=" + nativeplace + ", provId="
				+ provId + ", cityId=" + cityId + ", countyId=" + countyId + ", mapCoordinates=" + mapCoordinates
				+ ", marriage=" + marriage + ", insuredRelation=" + insuredRelation + ", yearIncom=" + yearIncom
				+ ", height=" + height + ", weight=" + weight + ", status=" + status + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", insuredType=" + insuredType + ", address=" + address + ", premium="
				+ premium + ", idTypeName=" + idTypeName + ", insuredRelationName=" + insuredRelationName
				+ ", marriageName=" + marriageName + ", countyIdName=" + countyIdName + ", cityIdName=" + cityIdName
				+ ", provIdName=" + provIdName + ", nativeplaceName=" + nativeplaceName + "]";
	}
    
    
}