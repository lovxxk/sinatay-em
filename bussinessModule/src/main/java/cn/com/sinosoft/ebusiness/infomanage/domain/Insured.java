package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;

public class Insured extends PersonInfo implements Serializable {
	/**
	 * @ProjectName: online
	 * @Package: cn.com.sinosoft.ebusiness.infomanage.domain
	 * @ClassName: Insured
	 * @Description: ��������Ϣ
	 * @Author: jack_xiao
	 * @CreateDate: 2013-09-04
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = -4977128271259575951L;
	/** ���������˹�ϵ �̶�00:���� */
	private String relaToMain;

	/** ��Ͷ���˹�ϵ ���� */
	private String relaToAppnt;

	public String getRelaToMain() {
		return relaToMain;
	}

	public void setRelaToMain(String relaToMain) {
		this.relaToMain = relaToMain;
	}

	public String getRelaToAppnt() {
		return relaToAppnt;
	}

	public void setRelaToAppnt(String relaToAppnt) {
		this.relaToAppnt = relaToAppnt;
	}
	
}
