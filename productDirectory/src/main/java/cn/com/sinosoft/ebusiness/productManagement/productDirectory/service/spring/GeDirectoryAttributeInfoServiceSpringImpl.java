package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeInfo;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryAttributeInfoService;

/**
 *  
 * 
 */
public class GeDirectoryAttributeInfoServiceSpringImpl extends GenericDaoHibernate<GeDirectoryAttributeInfo, String> implements GeDirectoryAttributeInfoService {
	
	private Log log = LogFactory.getLog(GeDirectoryAttributeInfoServiceSpringImpl.class);
	
	@LocusTrace(setDesc="�������")
	public void addGeDirectoryAttributeInfo(GeDirectoryAttributeInfo directoryAttributeInfo) {
		super.save(directoryAttributeInfo);
	}
	
	@LocusTrace(setDesc="ɾ������")
	public void deleteGeDirectoryByAttrId(String attrID) {
		super.deleteByPK(attrID);
	}
	
	@LocusTrace(setDesc="��������attrID ��ѯ����")
	public GeDirectoryAttributeInfo findGeDirectoryAttributeInfoByAttrID(String attrID) {
		return super.get(attrID);
	}
	
	@LocusTrace(setDesc="����������")
	public String getAttributeXMLTree(String attrID, String openAttrID) {
		log.error("====================********************************========================");
		StringBuffer str = new StringBuffer();
		str.append("<?xml version='1.0' encoding='utf-8'?>");
		System.out.println("id==================" + attrID);
		try {
			long start = System.currentTimeMillis();
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addAscOrder("seqIndex");
			if ("0".equals(attrID)) {
				queryRule.addIsNull("geDirectoryAttributeInfo.attrID");
			} else {
				queryRule.addEqual("geDirectoryAttributeInfo.attrID", attrID);
			}
			
			str.append("<tree id='" + attrID + "'>");
			List<GeDirectoryAttributeInfo> list = super.find(queryRule);
			for (int i = 0; i < list.size(); i++) {
				GeDirectoryAttributeInfo directoryAttributeInfo = (GeDirectoryAttributeInfo) list.get(i);
				if (directoryAttributeInfo.getAttrID().equals(openAttrID)) {
					str.append("<item id='" + directoryAttributeInfo.getAttrID() + "' open='1' text='" + directoryAttributeInfo.getAttrName() + "'>");
				} else {
					str.append("<item id='" + directoryAttributeInfo.getAttrID() + "' text='" + directoryAttributeInfo.getAttrName() + "'>");
				}
				
				str.append(getProductAttributeXMLTreeItem(directoryAttributeInfo.getAttrID(), openAttrID));
				str.append("</item>");
			}
			str.append("</tree>");
			long end = System.currentTimeMillis();
			log.error("handle time===============" + (end - start));
			log.error(str.toString());
			log.error("====================********************************========================");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	public String getProductAttributeXMLTreeItem(GeDirectoryAttributeInfo directoryAttributeInfo) {
		StringBuffer str = new StringBuffer();
		try {
			List<GeDirectoryAttributeInfo> list = directoryAttributeInfo.getGeDirectoryAttributeInfos();
			for (int i = 0; i < list.size(); i++) {
				GeDirectoryAttributeInfo chileDirectoryAttributeInfo = (GeDirectoryAttributeInfo) list.get(i);
				String childNodeXml = getProductAttributeXMLTreeItem(chileDirectoryAttributeInfo);
				str.append("<item id='" + directoryAttributeInfo.getAttrID() + "' text='" + directoryAttributeInfo.getAttrName() + "'>");
				str.append(childNodeXml);
				str.append("</item>");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public String getProductAttributeXMLTreeItem(String attrID, String openAttrID) {
		StringBuffer str = new StringBuffer();
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geDirectoryAttributeInfo.attrID", attrID);
			queryRule.addAscOrder("seqIndex");
			List<GeDirectoryAttributeInfo> list = super.find(GeDirectoryAttributeInfo.class, queryRule);
			for (int i = 0; i < list.size(); i++) {
				GeDirectoryAttributeInfo directoryAttributeInfo = (GeDirectoryAttributeInfo) list.get(i);
				String childNodeXml = getProductAttributeXMLTreeItem(directoryAttributeInfo.getAttrID(), openAttrID);
				if (directoryAttributeInfo.getAttrID().equals(openAttrID)) {
					str.append("<item id='" + directoryAttributeInfo.getAttrID() + "' open='1' text='" + directoryAttributeInfo.getAttrName() + "'>");
				} else {
					str.append("<item id='" + directoryAttributeInfo.getAttrID() + "' text='" + directoryAttributeInfo.getAttrName() + "'>");
				}
				str.append(childNodeXml);
				str.append("</item>");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	@LocusTrace(setDesc="����������ѯ����")
	public List<GeDirectoryAttributeInfo> searchGeDirectoryAttributeInfo(QueryRule queryRule) {
		try {
			queryRule.addAscOrder("seqIndex");
			return super.find(queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���²�Ʒ������Ϣ
	 */
	@LocusTrace(setDesc="���²�Ʒ������Ϣ")
	public void updateGeDirectoryAttributeInfo(GeDirectoryAttributeInfo geDirectoryAttributeInfo) throws BeansException {
			GeDirectoryAttributeInfo update = super.findUnique("attrID", geDirectoryAttributeInfo.getAttrID());
			List<String> ignorePropertyList = new ArrayList<String>();
			ignorePropertyList.add("attrID");
			ignorePropertyList.add("parentAttrID");
			if (update.getAttrSmallPictureOne() != null && geDirectoryAttributeInfo.getAttrSmallPictureOne() == null) {
				ignorePropertyList.add("attrSmallPictureOne");
			}
			if (update.getAttrSmallPictureTwo() != null && geDirectoryAttributeInfo.getAttrSmallPictureTwo() == null) {
				ignorePropertyList.add("attrSmallPictureTwo");
			}
			if (update.getAttrMiddlePictureOne() != null && geDirectoryAttributeInfo.getAttrMiddlePictureOne() == null) {
				ignorePropertyList.add("attrMiddlePictureOne");
			} 
			if (update.getAttrMiddlePictureTwo() != null && geDirectoryAttributeInfo.getAttrMiddlePictureTwo() == null) {
				ignorePropertyList.add("attrMiddlePictureTwo");
			} 
			if (update.getAttrBigPictureOne() != null && geDirectoryAttributeInfo.getAttrBigPictureOne() == null) {
				ignorePropertyList.add("attrBigPictureOne");
			} 
			if (update.getAttrBigPictureTwo() != null && geDirectoryAttributeInfo.getAttrBigPictureTwo() == null) {
				ignorePropertyList.add("attrBigPictureTwo");
			} 
			String[] ignoreProperties = new String[ignorePropertyList.size()];
			BeanUtils.copyProperties(geDirectoryAttributeInfo, update, ignorePropertyList.toArray(ignoreProperties));
			super.update(update);
	}
	
	@LocusTrace(setDesc="��������ID��ѯ�����Ĳ�ƷĿ¼EID")
	public List<GeDirectoryAttributeInfo> findGeDirectoryAttributeInfoByEidAndParentID(String eid,String parentAttrID) {
		try {
			StringBuffer hql = new StringBuffer("select directoryAttributeInfo from GeDirectoryAttributeRelate as relate inner join relate.geDirectoryAttributeInfo as  directoryAttributeInfo  where 1 = 1 ");
			hql.append("and relate.geDirectory.eid = ? ");
			hql.append("and directoryAttributeInfo.geDirectoryAttributeInfo.attrID = ? ");
			hql.append("order by seqIndex");
			List paramValueList = new ArrayList();
			paramValueList.add(eid);
			paramValueList.add(parentAttrID);
			List<GeDirectoryAttributeInfo> geDirectoryAttributeInfoList = super.findByHql(hql.toString(), paramValueList.toArray());
			return geDirectoryAttributeInfoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * ͨ��������ID��ѯ�����������Ϣ
	 * @param attrID ������ID
	 * @return
	 */
	@LocusTrace(setDesc="ͨ��������ID��ѯ�����������Ϣ")
	public List<GeDirectoryAttributeInfo> findGeDirectoryAttributeInfoByParentAttrID(String attrID) {
		
		if (attrID != null) {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geDirectoryAttributeInfo.attrID", attrID);
			queryRule.addAscOrder("seqIndex");
			return super.find(queryRule);
		} else {
			return null;
		}
	}
}
