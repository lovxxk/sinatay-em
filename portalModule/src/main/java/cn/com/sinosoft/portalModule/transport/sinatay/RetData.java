package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

/**
 * RetData�ڵ�DTO
 *
 */
public class RetData implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/** ���ر�־ CD25 ���ر�־ 0��ʧ�� 1���ɹ� */
	private String flag;
	
	/** ������Ϣ FlagΪ0��������Ϣ */
	private String desc;

	
	/**
	 * ���� flag �� getter ����
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * ���� flag �� setter ����
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ���� desc �� getter ����
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * ���� desc �� setter ����
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
