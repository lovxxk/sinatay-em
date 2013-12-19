package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;

public class Appnt extends PersonInfo implements Serializable{
	/**
	 * @ProjectName: online
	 * @Package:     cn.com.sinosoft.ebusiness.infomanage.domain
	 * @ClassName:   Appnt
	 * @Description: 投保人信息
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-09-04
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = 1689233864681019993L;
	/** 与被保人关系 CD17 */
	private String relaToInsured;
	public String getRelaToInsured() {
		return relaToInsured;
	}
	public void setRelaToInsured(String relaToInsured) {
		this.relaToInsured = relaToInsured;
	}
	
}
