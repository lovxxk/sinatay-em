package cn.com.sinosoft.ebusiness.dhf.domain;

// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeFunctionSwitch
 */
@Entity
@Table(name = "GE_FUNCTION_SWITCH_PORTAL")
public class GeFunctionSwitchPortal implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Թ��ܿ���ID */
	private String functiontId;

	/** ���Թ������� */
	private String functionInfo;

	/** ���Կ���״̬(0 δ��ͨ 1 ��ͨ) */
	private String status;
	
	private String portalChannel;
	
	private String riskCode;

	/**
	 * ��GeFunctionSwitch��Ĭ�Ϲ��췽��
	 */
	public GeFunctionSwitchPortal() {
	}

	/**
	 * ���Թ��ܿ���ID��getter����
	 */
	@Id
	@Column(name = "FUNCTIONTID")
	public String getFunctiontId() {
		return this.functiontId;
	}

	/**
	 * ���Թ��ܿ���ID��setter����
	 */
	public void setFunctiontId(String functiontId) {
		this.functiontId = functiontId;
	}

	/**
	 * ���Թ���������getter����
	 */

	@Column(name = "FUNCTIONINFO")
	public String getFunctionInfo() {
		return this.functionInfo;
	}

	/**
	 * ���Թ���������setter����
	 */
	public void setFunctionInfo(String functionInfo) {
		this.functionInfo = functionInfo;
	}

	/**
	 * �����û�״̬(0 ��Ч 1 ��Ч 2δ��ͨ)��getter����
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * �����û�״̬(0 ��Ч 1 ��Ч 2δ��ͨ)��setter����
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
