package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.util.ArrayList;
import java.util.List;

public class InfoTable extends ValueInfo{
	/**
	 * @ProjectName:
	 * @Package:     
	 * @ClassName:   
	 * @Description: ���ж��󣺱���ͷ   ��ֵ
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-9-17
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	//��Ϣͷ
	private List<String> heads=new ArrayList<String>();
	//ÿ������ͷ�� ÿһ�е�ֵ
	private List<ValueInfo> valueInfos=new  ArrayList<ValueInfo>();
	public List<String> getHeads() {
		return heads;
	}
	public void setHeads(List<String> heads) {
		this.heads = heads;
	}
	public List<ValueInfo> getValueInfos() {
		return valueInfos;
	}
	public void setValueInfos(List<ValueInfo> valueInfos) {
		this.valueInfos = valueInfos;
	}

	
	
}
