package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;

/**
 * @ProjectName: online
 * @Package:     cn.com.sinosoft.ebusiness.policy.domain
 * @ClassName:   Edor
 * @Description: ��ȫ��Ϣ��
 * @Author:      jack_xiao
 * @CreateDate:  2013-09-04
 * @UpdateUser: 
 * @UpdateDate: 
 * @UpdateRemark:
 * @Version: v1.0
 * 
 */

public class Edor implements Comparable<Edor>,Serializable{
	private static final long serialVersionUID = 1L;
	//��ȫ�����
	private String acceptNo;
	//��������
	private String edorType;
	//�˷ѽ��
	private String getMoney;
	//��ȫ��Ч����
	private String edorValiDate;
	//��ȫ������Դ
	private String edorSource;
	//��ȫ��������·��
	private String  url;
	
	public String getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(String acceptNo) {
		this.acceptNo = acceptNo;
	}
	public String getEdorType() {
		return edorType;
	}
	public void setEdorType(String edorType) {
		this.edorType = edorType;
	}
	
	public String getGetMoney() {
		return getMoney;
	}
	public void setGetMoney(String getMoney) {
		this.getMoney = getMoney;
	}
	
	
	public String getEdorValiDate() {
		return edorValiDate;
	}
	public void setEdorValiDate(String edorValiDate) {
		this.edorValiDate = edorValiDate;
	}
	public String getEdorSource() {
		return edorSource;
	}
	public void setEdorSource(String edorSource) {
		this.edorSource = edorSource;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int compareTo(Edor o) {
		//�Ȱ�����Ч�������� �����Ч������ͬ���������������
		if(o.getEdorValiDate().endsWith(this.getEdorValiDate())){
			return o.getAcceptNo().compareTo(this.getAcceptNo());
		}
		return o.getEdorValiDate().compareTo(this.getEdorValiDate());
	}
	

}
