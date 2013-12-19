package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

/**
 * 被保人DTO
 *
 */
public class LCInsured extends PartyRolePolicyDTO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** 与主被保人关系 固定00:本人 */
	private String relaToMain;
	
	/** 与投保人关系 不填 */
	private String relaToAppnt;
	
	/** 告知数 */
	private Integer tellInfoCount;
	
	/** 告知 */
	private List<TellInfo> tellInfos = new ArrayList<TellInfo>(0);

	/** 险种数 */
	private Integer riskCount;
	
	/** 险种 */
	private List<Risk> risks = new ArrayList<Risk>(0);
	
	/** 年龄范围*/
	private String ageRange;
	/**
	 * 属性 relaToMain 的 getter 方法
	 * @return the relaToMain
	 */
	public String getRelaToMain() {
		return relaToMain;
	}

	/**
	 * 属性 relaToMain 的 setter 方法
	 * @param relaToMain the relaToMain to set
	 */
	public void setRelaToMain(String relaToMain) {
		this.relaToMain = relaToMain;
	}

	/**
	 * 属性 relaToAppnt 的 getter 方法
	 * @return the relaToAppnt
	 */
	public String getRelaToAppnt() {
		return relaToAppnt;
	}

	/**
	 * 属性 relaToAppnt 的 setter 方法
	 * @param relaToAppnt the relaToAppnt to set
	 */
	public void setRelaToAppnt(String relaToAppnt) {
		this.relaToAppnt = relaToAppnt;
	}

	/**
	 * 属性 tellInfoCount 的 getter 方法
	 * @return the tellInfoCount
	 */
	public Integer getTellInfoCount() {
		return tellInfoCount;
	}

	/**
	 * 属性 tellInfoCount 的 setter 方法
	 * @param tellInfoCount the tellInfoCount to set
	 */
	public void setTellInfoCount(Integer tellInfoCount) {
		this.tellInfoCount = tellInfoCount;
	}

	/**
	 * 属性 tellInfos 的 getter 方法
	 * @return the tellInfos
	 */
	public List<TellInfo> getTellInfos() {
		return tellInfos;
	}

	/**
	 * 属性 tellInfos 的 setter 方法
	 * @param tellInfos the tellInfos to set
	 */
	public void setTellInfos(List<TellInfo> tellInfos) {
		this.tellInfos = tellInfos;
	}

	/**
	 * 属性 riskCount 的 getter 方法
	 * @return the riskCount
	 */
	public Integer getRiskCount() {
		return riskCount;
	}

	/**
	 * 属性 riskCount 的 setter 方法
	 * @param riskCount the riskCount to set
	 */
	public void setRiskCount(Integer riskCount) {
		this.riskCount = riskCount;
	}

	/**
	 * 属性 risks 的 getter 方法
	 * @return the risks
	 */
	public List<Risk> getRisks() {
		return risks;
	}

	/**
	 * 属性 risks 的 setter 方法
	 * @param risks the risks to set
	 */
	public void setRisks(List<Risk> risks) {
		this.risks = risks;
	}

	/**
	 * 属性 ageRange 的 getter 方法
	 * @return the ageRange
	 */
	public String getAgeRange() {
		return ageRange;
	}

	/**
	 * 属性 ageRange 的 setter 方法
	 */
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
}
