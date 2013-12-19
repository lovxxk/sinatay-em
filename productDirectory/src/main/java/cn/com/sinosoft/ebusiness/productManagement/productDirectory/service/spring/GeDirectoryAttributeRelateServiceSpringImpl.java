package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeInfo;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeRelate;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryAttributeRelateService;

public class GeDirectoryAttributeRelateServiceSpringImpl extends GenericDaoHibernate<GeDirectoryAttributeRelate, String> implements GeDirectoryAttributeRelateService {
	
	@LocusTrace(setDesc="添加产品属性关联")
	public void addGeDirectoryAttributeRelate(GeDirectoryAttributeRelate directoryAttributeRelate) {
		super.save(directoryAttributeRelate);
	}
	
	@LocusTrace(setDesc="添加针对一款产品的所有属性关联")
	public void addAllGeDirectoryAttributeRelate( List<GeDirectoryAttributeRelate> directoryAttributeRelateList) {
		try {
			super.saveAll(directoryAttributeRelateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@LocusTrace(setDesc="删除针对一款产品的所有属性关联")
	public void deleteGeDirectoryAttributeRelateByEid(String eId) {
		try {
			super.deleteAll(findProductAttributeRelateByEId(eId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@LocusTrace(setDesc="根据属性attrId查询与属性关联的所有产品")
	public List<GeDirectoryAttributeRelate> findProductAttributeRelateByAttrId(String attrId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geDirectoryAttributeInfo.attrID", attrId);
		return super.find(queryRule);
	}
	@LocusTrace(setDesc="根据产品的EId查询该产品关联的所有属性")
	public List<GeDirectoryAttributeRelate> findProductAttributeRelateByEId(String eId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geDirectory.eid", eId);
		return super.find(queryRule);
	}
	
	@LocusTrace(setDesc="生成产品属性关联树")
	public String getProductAttributeXMLTree(String attrID, String eId) {
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\'1.0\' encoding=\'GBK\'?>");
		try {
			ArrayList<String> attributes = new ArrayList<String>();
			List<GeDirectoryAttributeRelate> prodcuteAttributeRelateList = findProductAttributeRelateByEId(eId);
			for (int i = 0; i < prodcuteAttributeRelateList.size(); i++) {
				attributes.add(prodcuteAttributeRelateList.get(i)
						.getGeDirectoryAttributeInfo().getAttrID());
			}
			QueryRule queryRule = QueryRule.getInstance();
			if ("0".equals(attrID)) {
				queryRule.addIsNull("geDirectoryAttributeInfo.attrID");
			} else {
				queryRule.addEqual("geDirectoryAttributeInfo.attrID", attrID);
			}
			str.append("<tree id=\'" + attrID + "\'>");
			List<GeDirectoryAttributeInfo> list = super.find(GeDirectoryAttributeInfo.class, queryRule);
			for (int i = 0; i < list.size(); i++) {
				GeDirectoryAttributeInfo directoryAttributeInfo = (GeDirectoryAttributeInfo) list.get(i);
				str.append("<item id=\'" + directoryAttributeInfo.getAttrID()
						+ "\' open=\'1\'  text=\'"
						+ directoryAttributeInfo.getAttrName() + "\'>");
				str.append(getProductAttributeXMLTreeItem(
						directoryAttributeInfo.getAttrID(), attributes));
				str.append("</item>");
			}
			str.append("</tree>");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public String getProductAttributeXMLTreeItem(String attrID, List prodcuteAttributeRelateNodeList) {
		StringBuffer str = new StringBuffer();
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geDirectoryAttributeInfo.attrID", attrID);
			queryRule.addAscOrder("seqIndex");
			List<GeDirectoryAttributeInfo> list = super.find(GeDirectoryAttributeInfo.class, queryRule);
			for (int i = 0; i < list.size(); i++) {
				GeDirectoryAttributeInfo directoryAttributeInfo = (GeDirectoryAttributeInfo) list.get(i);
				String childNodeXml = getProductAttributeXMLTreeItem(directoryAttributeInfo.getAttrID(), prodcuteAttributeRelateNodeList);
				if (childNodeXml.length() == 0 && prodcuteAttributeRelateNodeList.contains(directoryAttributeInfo.getAttrID())) {
					str.append("<item id=\'" + directoryAttributeInfo.getAttrID() + "\' open=\'1\' checked=\'1\' text=\'" + directoryAttributeInfo.getAttrName() + "\'>");
					str.append("</item>");
				} else {
					str.append("<item id=\'" + directoryAttributeInfo.getAttrID() + "\' open=\'1\' text=\'" + directoryAttributeInfo.getAttrName() + "\'>");
					str.append(childNodeXml);
					str.append("</item>");
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

}
