package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

/**
 * Ͷ����DTO
 *
 */
public class LCAppnt extends PartyRolePolicyDTO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	/* Ͷ���˼���(ȡת���ַ�) */
	private String licenseType;
	
	/** �뱻���˹�ϵ CD17 */
	private String relaToInsured;
	
	/** ��֪�� */
	private Integer tellInfoCount;
	
	/** ��֪ */
	private List<TellInfo> tellInfos = new ArrayList<TellInfo>(0);

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getRelaToInsured() {
		return relaToInsured;
	}

	public void setRelaToInsured(String relaToInsured) {
		this.relaToInsured = relaToInsured;
	}

	public Integer getTellInfoCount() {
		return tellInfoCount;
	}

	public void setTellInfoCount(Integer tellInfoCount) {
		this.tellInfoCount = tellInfoCount;
	}

	public List<TellInfo> getTellInfos() {
		return tellInfos;
	}

	public void setTellInfos(List<TellInfo> tellInfos) {
		this.tellInfos = tellInfos;
	}

	

}
