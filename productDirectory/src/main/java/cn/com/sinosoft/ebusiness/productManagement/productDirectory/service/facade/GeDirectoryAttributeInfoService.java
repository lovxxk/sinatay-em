package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import org.springframework.beans.BeansException;

import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeInfo;

public interface GeDirectoryAttributeInfoService {

	public abstract List<GeDirectoryAttributeInfo> searchGeDirectoryAttributeInfo(QueryRule queryRule);

	public abstract void deleteGeDirectoryByAttrId(String attrID);

	public abstract void addGeDirectoryAttributeInfo(GeDirectoryAttributeInfo directoryAttributeInfo);

	public abstract String getAttributeXMLTree(String attrID, String openAttrID);

	public abstract GeDirectoryAttributeInfo findGeDirectoryAttributeInfoByAttrID(String attrID);
	
	public void updateGeDirectoryAttributeInfo(GeDirectoryAttributeInfo geDirectoryAttributeInfo) throws BeansException;

	public List<GeDirectoryAttributeInfo> findGeDirectoryAttributeInfoByEidAndParentID(String eid,String parentAttrID);

	public abstract List<GeDirectoryAttributeInfo> findGeDirectoryAttributeInfoByParentAttrID(String attrID);

}
