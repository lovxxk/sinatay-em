package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

/**
 * ������DTO
 *
 */
public class LCInsured extends PartyRolePolicyDTO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** ���������˹�ϵ �̶�00:���� */
	private String relaToMain;
	
	/** ��Ͷ���˹�ϵ ���� */
	private String relaToAppnt;
	
	/** ��֪�� */
	private Integer tellInfoCount;
	
	/** ��֪ */
	private List<TellInfo> tellInfos = new ArrayList<TellInfo>(0);

	/** ������ */
	private Integer riskCount;
	
	/** ���� */
	private List<Risk> risks = new ArrayList<Risk>(0);
	
	/** ���䷶Χ*/
	private String ageRange;
	/**
	 * ���� relaToMain �� getter ����
	 * @return the relaToMain
	 */
	public String getRelaToMain() {
		return relaToMain;
	}

	/**
	 * ���� relaToMain �� setter ����
	 * @param relaToMain the relaToMain to set
	 */
	public void setRelaToMain(String relaToMain) {
		this.relaToMain = relaToMain;
	}

	/**
	 * ���� relaToAppnt �� getter ����
	 * @return the relaToAppnt
	 */
	public String getRelaToAppnt() {
		return relaToAppnt;
	}

	/**
	 * ���� relaToAppnt �� setter ����
	 * @param relaToAppnt the relaToAppnt to set
	 */
	public void setRelaToAppnt(String relaToAppnt) {
		this.relaToAppnt = relaToAppnt;
	}

	/**
	 * ���� tellInfoCount �� getter ����
	 * @return the tellInfoCount
	 */
	public Integer getTellInfoCount() {
		return tellInfoCount;
	}

	/**
	 * ���� tellInfoCount �� setter ����
	 * @param tellInfoCount the tellInfoCount to set
	 */
	public void setTellInfoCount(Integer tellInfoCount) {
		this.tellInfoCount = tellInfoCount;
	}

	/**
	 * ���� tellInfos �� getter ����
	 * @return the tellInfos
	 */
	public List<TellInfo> getTellInfos() {
		return tellInfos;
	}

	/**
	 * ���� tellInfos �� setter ����
	 * @param tellInfos the tellInfos to set
	 */
	public void setTellInfos(List<TellInfo> tellInfos) {
		this.tellInfos = tellInfos;
	}

	/**
	 * ���� riskCount �� getter ����
	 * @return the riskCount
	 */
	public Integer getRiskCount() {
		return riskCount;
	}

	/**
	 * ���� riskCount �� setter ����
	 * @param riskCount the riskCount to set
	 */
	public void setRiskCount(Integer riskCount) {
		this.riskCount = riskCount;
	}

	/**
	 * ���� risks �� getter ����
	 * @return the risks
	 */
	public List<Risk> getRisks() {
		return risks;
	}

	/**
	 * ���� risks �� setter ����
	 * @param risks the risks to set
	 */
	public void setRisks(List<Risk> risks) {
		this.risks = risks;
	}

	/**
	 * ���� ageRange �� getter ����
	 * @return the ageRange
	 */
	public String getAgeRange() {
		return ageRange;
	}

	/**
	 * ���� ageRange �� setter ����
	 */
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
}
