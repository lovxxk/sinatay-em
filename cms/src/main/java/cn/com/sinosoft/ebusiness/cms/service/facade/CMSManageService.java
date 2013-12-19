package cn.com.sinosoft.ebusiness.cms.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

public interface CMSManageService {
	/**
	 * ����CMS��Ŀ
	 * @param obj ָ��CMSҵ�����
	 */
	public void createCMS(Object obj);

	/**
	 * ��ѯCMS��Ŀ
	 * @param queryRule ��ѯ��������
	 * @param pageNo �ڼ�ҳ
	 * @param pageSize ÿҳ����
	 */
	public List<Object> searchCMS(Object obj,QueryRule queryRule);

	/**
	 * ɾ��CMS��Ŀ
	 * @param obj ָ��CMSҵ�����  
	 */
	public void deleteCMS(Object obj) throws Exception;
	
	/**
	 * ����CMS��Ŀ
	 * @param obj ָ��CMSҵ�����  
	 */
	public void updateCMS(Object obj);
	
	/**
	 * ������Ŀid�õ���Ŀ����
	 * @param channelId
	 * @return 
	 */
	public List findChannelByChannelID(int channelId);
	
	/**
	 * ��������id�õ����¶���
	 * @param channelId
	 * @return 
	 */
	public List findDocByDocID(int docID);
	/**
	 * ����һ���ڵ��������ӽڵ㣬�����ýڵ�
	 * @param nodeID
	 * @return
	 */
	public String findChildNode(String nodeID);
	
	/**
	 * ����Ŀ�����µ�״̬�޸�Ϊ��ɾ��
	 * @param chnlID
	 */
	public void updateDocStatus(String chnlID);
	
	/**
	 * �ݹ��޸���Ŀ����Status�ֶ�Ϊ��ɾ��(��1��)����һ���ڵ������ӽڵ㣬�޸Ľڵ�ΪchnlID����͸ýڵ������е��ӽڵ�
	 * @param chnlID
	 */
	public void updateChnlStatus(String chnlID);
	
	/**
	 * ����ĿidΪchnlID�µ�ģ��ɾ��
	 * @param chnlID
	 */
	public void deleteTpl(String chnlID);
	
	/**
	 * ������idΪdocID�µĸ���ɾ��
	 * @param docID
	 */
	public void deleteEnc(String docID);
	
	/**
	 * ����ĿidΪchnlID�µ����°����������µĸ���ɾ��
	 * @param chnlID
	 */
	public void deleteDoc(String chnlID);
	
	/**
	 * �ݹ�ɾ����ĿidΪchnlID����������Ŀ
	 * @param chnlID
	 */
	public void deleteChnl(String chnlID);
	/**
	 * ����������Ŀ,�ĵ�����,���±���,���¹ؼ��ֲ�������id
	 * @param docChnl
	 * @param docType
	 * @param docTitle
	 * @param docKeyWord
	 * @return
	 */
	public String findDocIDByDocChnlAndDocTypeAndDocTitleAndDocKeyWord(String docChnl,String docType,String docTitle,String docKeyWord);
	
	/**
	 * ��������idɾ������
	 * @param docID
	 */
	public void deleteDocByDocID(String docID);
	
	/**
	 * ����ģ�����Ͳ���ģ�����
	 * @param tplType
	 * @return
	 */
	public List findTempletByTempType(String tplType);
	
	/**
	 * ��ģ����Ŀ�󶨱��и�����Ŀid����ģ��id
	 * @param chnlID
	 * @return
	 */
	public String findTempletIDByChnlID(String chnlID);
	
	/**
	 * ����ģ��id,ģ�����ͣ�ģ��洢������ģ�����
	 * @param tID
	 * @param tType
	 * @param tSName
	 * @return
	 */
	public CmsTemplet findTempletByTempletIDAndTempletTypeAndTempletStoreName(int tID, String tType, String tSName);
	
	/**
	 * ����ģ��idɾ����Ŀģ��󶨶���
	 * @param templetID
	 */
	public void deleteCmsChannelTempletByTempletID(String templetID);
	
	/**
	 * ����ģ�����ͣ�ģ��洢������ģ�����
	 * @param templetType
	 * @param templetStoreName
	 * @return
	 */
	public List findTempletByTempletTypeAndTempletStoreName(String templetType,String templetStoreName);
	
	/**
	 * ����ģ��idɾ��ģ�����
	 * @param TempletID
	 */
	public void deleteTempletByTempletID(String templetID);
	
	/**
	 * ����ģ��id����ģ�����
	 * @param templetID
	 * @return
	 */
	public CmsTemplet findTempletByTempletID(String templetID);
}
