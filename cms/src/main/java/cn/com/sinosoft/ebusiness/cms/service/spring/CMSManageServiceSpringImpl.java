package cn.com.sinosoft.ebusiness.cms.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.domain.CmsChannel;
import cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSManageService;
import cn.com.sinosoft.ebusiness.cms.util.CmsChannelFunc;

public class CMSManageServiceSpringImpl extends GenericDaoHibernate<CmsChannel, String> implements CMSManageService {
	@Autowired 
	private CmsChannelFunc cmsChannelFunc;
	
	public void setCmsChannelFunc(CmsChannelFunc cmsChannelFunc) {
		this.cmsChannelFunc = cmsChannelFunc;
	}
	
	public void createCMS(Object obj) {
		super.save(obj);
	}

	
	public List<Object> searchCMS(Object obj, QueryRule queryRule) {
		System.out.println(obj.getClass());
		return super.find(obj.getClass(),queryRule);
	}

	
	public void deleteCMS(Object obj) throws Exception {
		super.delete(obj);
	}

	
	public void updateCMS(Object obj) {
		super.update(obj);

	}
	
	
	public List findChannelByChannelID(int channelId) {
		return cmsChannelFunc.findChannelByChannelID(channelId);
	}
	
	
	public String findChildNode(String nodeID) {
		return cmsChannelFunc.findChildNode(nodeID);
	}
	
	
	public void updateDocStatus(String chnlID) {
		cmsChannelFunc.updateDocStatus(chnlID);
	}
	
	
	public void updateChnlStatus(String chnlID) {
		cmsChannelFunc.updateChnlStatus(chnlID);
	}
	
	
	public void deleteTpl(String chnlID) {
		cmsChannelFunc.deleteTpl(chnlID);
	}
	
	
	public void deleteEnc(String docID) {
		cmsChannelFunc.deleteEnc(docID);
	}
	
	
	public void deleteDoc(String chnlID) {
		cmsChannelFunc.deleteDoc(chnlID);
	}
	
	
	public void deleteChnl(String chnlID) {
		cmsChannelFunc.deleteChnl(chnlID);
	}
	
	
	public List findDocByDocID(int docID) {
		return cmsChannelFunc.findDocByDocID(docID);
	}
	
	
	public String findDocIDByDocChnlAndDocTypeAndDocTitleAndDocKeyWord(
			String docChnl, String docType, String docTitle, String docKeyWord) {
		return cmsChannelFunc.findDocIDByDocChnlAndDocTypeAndDocTitleAndDocKeyWord(docChnl, docType, docTitle, docKeyWord);
	}
	
	
	public void deleteDocByDocID(String docID) {
		cmsChannelFunc.deleteDocByDocID(docID);
	}
	
	
	public List findTempletByTempType(String tplType) {
		return cmsChannelFunc.findTempletByTempType(tplType);
	}
	
	
	public String findTempletIDByChnlID(String chnlID) {
		return cmsChannelFunc.findTempletIDByChnlID(chnlID);
	}
	
	
	public CmsTemplet findTempletByTempletIDAndTempletTypeAndTempletStoreName(
			int tID, String tType, String tSName) {
		return cmsChannelFunc.findTempletByTempletIDAndTempletTypeAndTempletStoreName(tID, tType, tSName);
	}
	
	
	public void deleteCmsChannelTempletByTempletID(String templetID) {
		cmsChannelFunc.deleteCmsChannelTempletByTempletID(templetID);
	}
	
	
	public List findTempletByTempletTypeAndTempletStoreName(String templetType,
			String templetStoreName) {
		return cmsChannelFunc.findTempletByTempletTypeAndTempletStoreName(templetType, templetStoreName);
	}
	
	public void deleteTempletByTempletID(String templetID) {
		cmsChannelFunc.deleteTempletByTempletID(templetID);
	}
	
	public CmsTemplet findTempletByTempletID(String templetID) {
		return cmsChannelFunc.findTempletByTempletID(templetID);
	}
	
	
}
