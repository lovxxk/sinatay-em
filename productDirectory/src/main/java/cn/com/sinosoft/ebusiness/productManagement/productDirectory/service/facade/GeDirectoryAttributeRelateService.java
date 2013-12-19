package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeRelate;


public interface GeDirectoryAttributeRelateService {

	public abstract String getProductAttributeXMLTree(String attrID, String eId);

	public abstract void addAllGeDirectoryAttributeRelate(List<GeDirectoryAttributeRelate> directoryAttributeRelateList);

	public abstract void addGeDirectoryAttributeRelate(GeDirectoryAttributeRelate directoryAttributeRelate);

	public abstract void deleteGeDirectoryAttributeRelateByEid(String eId);

	public abstract List<GeDirectoryAttributeRelate> findProductAttributeRelateByAttrId(String attrId);

}
