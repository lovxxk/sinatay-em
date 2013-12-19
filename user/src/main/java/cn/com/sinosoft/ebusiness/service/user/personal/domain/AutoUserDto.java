package cn.com.sinosoft.ebusiness.service.user.personal.domain;

import java.util.Date;

public class AutoUserDto {
	/** 属性姓名 */
	private String fullName;

	/** 属性证件类型 */
	private Integer idType;

	/** 属性证件号码 */
	private String idNumber;
	
	/** 属性性别 */
	private Integer gender;

	/** 属性出生日期 */
	private Date birthDate;
	
	private String mobilePhone;
	
	private String email;
	
	/**是否自动注册标示 */
	private String regFlag;
	
	/**明文密码 */
	private String plaintextPassword;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}

	public String getPlaintextPassword() {
		return plaintextPassword;
	}

	public void setPlaintextPassword(String plaintextPassword) {
		this.plaintextPassword = plaintextPassword;
	}

}
