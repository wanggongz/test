package com.shenpu.base.utils;

public class ExcelModule {
	
	/**
	 * 主键
	 */
	
	/**
	 * 关联险种表
	 */
	
	/**
	 * 保险期间
	 */
	private String insuranceTime;

	/**
	 * 保险期间单位
	 */
	
	private String workspace;
	/**
	 * 交费期间
	 */
	private String payTime;
	
	/**
	 * 交费期间单位
	 */
	private String payWorkplace;
	
	/**
	 * 性别
	 */
	
	private String sex;
	
	/**
	 * 投保年龄
	 */
	
	private Integer age;
	
	/**
	 * 保单年度
	 */
	private Integer policyYear;
	
	/**
	 * 基本保额
	 */
	private Integer coverage;
	
	/**
	 * 现金价值
	 */
	private String cost;

	

	public String getInsuranceTime() {
		return insuranceTime;
	}

	public void setInsuranceTime(String insuranceTime) {
		this.insuranceTime = insuranceTime;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayWorkplace() {
		return payWorkplace;
	}

	public void setPayWorkplace(String payWorkplace) {
		this.payWorkplace = payWorkplace;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPolicyYear() {
		return policyYear;
	}

	public void setPolicyYear(Integer policyYear) {
		this.policyYear = policyYear;
	}

	public Integer getCoverage() {
		return coverage;
	}

	public void setCoverage(Integer coverage) {
		this.coverage = coverage;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ExcelModule [ insuranceTime=" + insuranceTime
				+ ", workspace=" + workspace + ", payTime=" + payTime + ", payWorkplace=" + payWorkplace + ", sex="
				+ sex + ", age=" + age + ", policyYear=" + policyYear + ", coverage=" + coverage + ", cost=" + cost
				+ "]";
	}

}
