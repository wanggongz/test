package com.shenpu.proxy.verify.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.apache.commons.lang3.StringUtils;
import com.shenpu.base.utils.IdcardUtil;
import com.shenpu.base.utils.ValidateUtil;
import com.shenpu.proxy.verify.check.msg.Message;

@XmlAccessorType(XmlAccessType.FIELD)
public class AppntDTO extends PersonDTO {

	@XmlElement(name = "AppntName")
	private String appntName;

	@XmlElement(name = "IdNo")
	private String idNo;

	@XmlElement(name = "IdType")
	private String idType;

	@XmlElement(name = "Sex")
	private String sex;

	@XmlElement(name = "Birthday")
	private String birthday;

	@XmlElement(name = "Mobile")
	private String mobile;

	@XmlElement(name = "Address")
	private String address;

	@XmlElement(name = "ZipCode")
	private String zipCode;

	@XmlElement(name = "Email")
	private String email;

	@XmlElement(name = "Occupation")
	private String occupation;

	@XmlElement(name = "Degree")
	private String degree;
	
	@XmlElement(name = "ProvId")
	private String provId;

	@XmlElement(name = "CityId")
	private String cityId;

	@XmlElement(name = "CountyId")
	private String countyId;

	@XmlElement(name = "Marriage")
	private String marriage;
	
	@XmlElement(name = "Nativeplace")
	private String nativeplace;
	
	//被保人是否是投保人自己:1-是；0-否
	@XmlElement(name = "Isself", defaultValue="0")
	private String isself;
	
	//受益人是否是法定:1-是；0-否
	@XmlElement(name = "IsStatutory", defaultValue="0")
	private String isStatutory;

	@XmlElement(name = "YearIncom")
	private String YearIncom;
	
	@XmlElement(name = "OccupationName")
	private String occupationName;
	
	@XmlElement(name = "IdTypeName")
	private String idTypeName;

	@XmlElement(name = "MarriageName")
    private String marriageName;
	
	@XmlElement(name = "DegreeName")
    private String degreeName;
	
	@XmlElement(name = "CityIdName")
    private String cityIdName;
	
	@XmlElement(name = "CountyIdName")
    private String countyIdName;
	
	@XmlElement(name = "ProvIdName")
    private String provIdName;
	
	@XmlElement(name = "NativeplaceName")
    private String nativeplaceName;

	
	public String getIdTypeName() {
		return idTypeName;
	}

	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}

	public String getMarriageName() {
		return marriageName;
	}

	public void setMarriageName(String marriageName) {
		this.marriageName = marriageName;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getCityIdName() {
		return cityIdName;
	}

	public void setCityIdName(String cityIdName) {
		this.cityIdName = cityIdName;
	}

	public String getCountyIdName() {
		return countyIdName;
	}

	public void setCountyIdName(String countyIdName) {
		this.countyIdName = countyIdName;
	}

	public String getProvIdName() {
		return provIdName;
	}

	public void setProvIdName(String provIdName) {
		this.provIdName = provIdName;
	}

	public String getNativeplaceName() {
		return nativeplaceName;
	}

	public void setNativeplaceName(String nativeplaceName) {
		this.nativeplaceName = nativeplaceName;
	}

	public String getAppntName() {
		return appntName;
	}

	public void setAppntName(String appntName) {
		this.appntName = appntName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getMarriage() {
		return marriage;
	}
	
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	
	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getIsself() {
		return isself;
	}

	public void setIsself(String isself) {
		this.isself = isself;
	}

	public String getIsStatutory() {
		return isStatutory;
	}

	public void setIsStatutory(String isStatutory) {
		this.isStatutory = isStatutory;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public String getYearIncom() {
		return YearIncom;
	}

	public void setYearIncom(String yearIncom) {
		YearIncom = yearIncom;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	@Override
	public Message validatBirthDay(boolean nillable) {
		Message message = new Message();
		if(nillable) {
			if (StringUtils.isNotBlank(this.birthday)) {
				Boolean isBirthday = ValidateUtil.isLegalFormatDate(this.birthday);
				if (!isBirthday) {
					message.setContinued(false);
					message.setMsg("投保人生日格式不正确");
					message.setReturnValue("001");
					message.setValid(true);
				}else {
					message.setContinued(true);
					message.setMsg("投保人生日格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				}
			}
		}else {
			if (StringUtils.isNotBlank(this.birthday)) {
				Boolean isBirthday = ValidateUtil.isLegalFormatDate(this.birthday);
				if (!isBirthday) {
					message.setContinued(false);
					message.setMsg("投保人生日格式不正确");
					message.setReturnValue("001");
					message.setValid(true);
				}else {
					message.setContinued(true);
					message.setMsg("投保人生日格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				}
			}else {
				message.setContinued(false);
				message.setMsg("投保人生日信息缺失");
				message.setReturnValue("001");
				message.setValid(true);
			}
		}
		return message;
	}

	@Override
	public Message validatMobile(boolean nillable) {
		Message message = new Message();
		if(nillable) {
			if (StringUtils.isNotBlank(this.mobile)) {
				Boolean isMobile = ValidateUtil.isMobile(this.mobile);
				if(isMobile) {
					message.setContinued(true);
					message.setMsg("投保人手机格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				}else {
					message.setContinued(false);
					message.setMsg("投保人手机格式错误");
					message.setReturnValue("001");
					message.setValid(true);
				}
			}
		}else {
			if (StringUtils.isNotBlank(this.mobile)) {
				Boolean isMobile = ValidateUtil.isMobile(this.mobile);
				if(isMobile) {
					message.setContinued(true);
					message.setMsg("投保人手机格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				}else {
					message.setContinued(false);
					message.setMsg("投保人手机格式错误");
					message.setReturnValue("001");
					message.setValid(true);
				}
			}else {
				message.setContinued(false);
				message.setMsg("投保人手机号码不完整");
				message.setReturnValue("001");
				message.setValid(true);
			}
		}
		return message;
	}

	@Override
	public Message validatEmail(boolean nillable) {
		Message message = new Message();
		if(nillable) {
			if (StringUtils.isNotBlank(this.email)) {
				Boolean isEmail = ValidateUtil.isEmail(this.email);
				if (isEmail) {
					message.setContinued(true);
					message.setMsg("投保人电子邮箱格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				} else {
					message.setContinued(false);
					message.setMsg("投保人电子邮箱格式错误");
					message.setReturnValue("001");
					message.setValid(true);
				}
			}
		}else {
			if (StringUtils.isNotBlank(this.email)) {
				Boolean isEmail = ValidateUtil.isEmail(this.email);
				if (isEmail) {
					message.setContinued(true);
					message.setMsg("投保人电子邮箱格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				} else {
					message.setContinued(false);
					message.setMsg("投保人电子邮箱格式错误");
					message.setReturnValue("001");
					message.setValid(true);
				}
			}else {
				message.setContinued(false);
				message.setMsg("投保人电子邮箱缺失");
				message.setReturnValue("002");
				message.setValid(true);
			}
		}
		return message;
	}

	@Override
	public Message validatIDCard(boolean nillable) {
		Message message = new Message();
		if(nillable) {
			if (StringUtils.isNotBlank(this.idType) && StringUtils.isNotBlank(this.idNo)) {
				if ("01".equals(this.idType)) {
					Boolean isCard = ValidateUtil.isIDCard(this.idNo);
					if(!isCard) {
						message.setContinued(false);
						message.setMsg("投保人身份证格式不正确");
						message.setReturnValue("001");
						message.setValid(true);
					}else {
						message.setContinued(true);
						message.setMsg("投保人身份证格式正确");
						message.setReturnValue("000");
						message.setValid(false);
					}
				} else {
					message.setContinued(true);
					message.setMsg("投保人其他证件格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				}
			}
		}else {
			if (StringUtils.isNotBlank(this.idType) && StringUtils.isNotBlank(this.idNo)) {
				if ("01".equals(this.idType)) {
					Boolean isCard = ValidateUtil.isIDCard(this.idNo);
					if(isCard) {
						message.setContinued(true);
						message.setMsg("投保人身份证格式正确");
						message.setReturnValue("000");
						message.setValid(false);
					}else {
						message.setContinued(false);
						message.setMsg("投保人身份证格式错误");
						message.setReturnValue("001");
						message.setValid(true);
					}
				} else {
					message.setContinued(true);
					message.setMsg("投保人其他证件格式正确");
					message.setReturnValue("000");
					message.setValid(false);
				}
			}else {
				message.setContinued(true);
				message.setMsg("投保人证件信息不完整");
				message.setReturnValue("002");
				message.setValid(false);
			}
		}
		return message;
	}

	@Override
	public List<Message> accordanceValidate() {
		List<Message> messages = new ArrayList<>();
		if(StringUtils.isBlank(this.birthday) || !(StringUtils.isNotBlank(this.idType) && StringUtils.isNotBlank(this.idNo))) {
			Message message = new Message();
			message.setContinued(false);
			message.setMsg("投保人生日信息缺失");
			message.setReturnValue("001");
			message.setValid(true);
			messages.add(message);
		}else {
			if ("01".equals(this.idType)) {
				//校验生日与身份证是否一致
				if(StringUtils.isNotBlank(this.birthday)){
					String validateIdNo = IdcardUtil.getBirthByIdCard(this.idNo);
					SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if(!this.birthday.equals(simpleDateFormat2.format(simpleDateFormat1.parse(validateIdNo)))){
							Message message = new Message();
							message.setContinued(false);
							message.setMsg("投保人生日信息与证件信息不一致");
							message.setReturnValue("001");
							message.setValid(true);
							messages.add(message);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				//校验性别与身份证是否一致
				String validateSex = IdcardUtil.getGenderByIdCard(this.idNo);
				if(!this.sex.equalsIgnoreCase(validateSex)) {
					Message message = new Message();
					message.setContinued(false);
					message.setMsg("投保人性别与证件信息不一致");
					message.setReturnValue("001");
					message.setValid(true);
					messages.add(message);
				}
			}
		}
		return messages;
	}

	@Override
	public String toString() {
		return "AppntDTO [appntName=" + appntName + ", idNo=" + idNo + ", idType=" + idType + ", sex=" + sex
				+ ", birthday=" + birthday + ", mobile=" + mobile + ", address=" + address + ", zipCode=" + zipCode
				+ ", email=" + email + ", occupation=" + occupation + ", provId=" + provId + ", cityId=" + cityId
				+ ", countyId=" + countyId + ", marriage=" + marriage + ", nativeplace=" + nativeplace + ", isself="
				+ isself + ", isStatutory=" + isStatutory + ", idTypeName=" + idTypeName + ", marriageName="
				+ marriageName + ", degreeName=" + degreeName + ", cityIdName=" + cityIdName + ", countyIdName="
				+ countyIdName + ", provIdName=" + provIdName + ", nativeplaceName=" + nativeplaceName + "]";
	}


	
}
