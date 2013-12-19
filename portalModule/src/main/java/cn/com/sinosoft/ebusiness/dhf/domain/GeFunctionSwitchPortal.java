package cn.com.sinosoft.ebusiness.dhf.domain;

// default package
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeFunctionSwitch
 */
@Entity
@Table(name = "GE_FUNCTION_SWITCH_PORTAL")
public class GeFunctionSwitchPortal implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性功能开关ID */
	private String functiontId;

	/** 属性功能描述 */
	private String functionInfo;

	/** 属性开关状态(0 未开通 1 开通) */
	private String status;
	
	private String portalChannel;
	
	private String riskCode;

	/**
	 * 类GeFunctionSwitch的默认构造方法
	 */
	public GeFunctionSwitchPortal() {
	}

	/**
	 * 属性功能开关ID的getter方法
	 */
	@Id
	@Column(name = "FUNCTIONTID")
	public String getFunctiontId() {
		return this.functiontId;
	}

	/**
	 * 属性功能开关ID的setter方法
	 */
	public void setFunctiontId(String functiontId) {
		this.functiontId = functiontId;
	}

	/**
	 * 属性功能描述的getter方法
	 */

	@Column(name = "FUNCTIONINFO")
	public String getFunctionInfo() {
		return this.functionInfo;
	}

	/**
	 * 属性功能描述的setter方法
	 */
	public void setFunctionInfo(String functionInfo) {
		this.functionInfo = functionInfo;
	}

	/**
	 * 属性用户状态(0 无效 1 有效 2未开通)的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * 属性用户状态(0 无效 1 有效 2未开通)的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PORTALCHANNEL")
	public String getPortalChannel() {
		return portalChannel;
	}

	public void setPortalChannel(String portalChannel) {
		this.portalChannel = portalChannel;
	}

	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	
	
}
