package com.shenpu.proxy.scheduler.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="t_barrage")
public class Barrage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "名称不能为空")
	private String name;
	
	@NotEmpty(message = "循环机制不能为空")
	private String cycle;
	
	private boolean isRun;
	
	private Integer times;
	
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
