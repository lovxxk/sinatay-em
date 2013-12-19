package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.util.ArrayList;
import java.util.List;

public class InfoTable extends ValueInfo{
	/**
	 * @ProjectName:
	 * @Package:     
	 * @ClassName:   
	 * @Description: 公有对象：标题头   列值
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-9-17
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	//信息头
	private List<String> heads=new ArrayList<String>();
	//每个标题头下 每一行的值
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
