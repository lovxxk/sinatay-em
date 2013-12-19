package cn.com.sinosoft.ebusiness.cms.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

public interface CMSManageService {
	/**
	 * 创建CMS项目
	 * @param obj 指定CMS业务对象
	 */
	public void createCMS(Object obj);

	/**
	 * 查询CMS项目
	 * @param queryRule 查询条件对象
	 * @param pageNo 第几页
	 * @param pageSize 每页数据
	 */
	public List<Object> searchCMS(Object obj,QueryRule queryRule);

	/**
	 * 删除CMS项目
	 * @param obj 指定CMS业务对象  
	 */
	public void deleteCMS(Object obj) throws Exception;
	
	/**
	 * 更新CMS项目
	 * @param obj 指定CMS业务对象  
	 */
	public void updateCMS(Object obj);
	
	/**
	 * 根据栏目id得到栏目对象
	 * @param channelId
	 * @return 
	 */
	public List findChannelByChannelID(int channelId);
	
	/**
	 * 根据文章id得到文章对象
	 * @param channelId
	 * @return 
	 */
	public List findDocByDocID(int docID);
	/**
	 * 查找一个节点下所有子节点，包括该节点
	 * @param nodeID
	 * @return
	 */
	public String findChildNode(String nodeID);
	
	/**
	 * 将栏目下文章的状态修改为已删除
	 * @param chnlID
	 */
	public void updateDocStatus(String chnlID);
	
	/**
	 * 递归修改栏目树的Status字段为已删除(“1”)，如一个节点下有子节点，修改节点为chnlID本身和该节点下所有的子节点
	 * @param chnlID
	 */
	public void updateChnlStatus(String chnlID);
	
	/**
	 * 将栏目id为chnlID下的模板删除
	 * @param chnlID
	 */
	public void deleteTpl(String chnlID);
	
	/**
	 * 将文章id为docID下的附件删除
	 * @param docID
	 */
	public void deleteEnc(String docID);
	
	/**
	 * 将栏目id为chnlID下的文章包括该文章下的附件删除
	 * @param chnlID
	 */
	public void deleteDoc(String chnlID);
	
	/**
	 * 递归删除栏目id为chnlID及它的子栏目
	 * @param chnlID
	 */
	public void deleteChnl(String chnlID);
	/**
	 * 根据文章栏目,文档类型,文章标题,文章关键字查找文章id
	 * @param docChnl
	 * @param docType
	 * @param docTitle
	 * @param docKeyWord
	 * @return
	 */
	public String findDocIDByDocChnlAndDocTypeAndDocTitleAndDocKeyWord(String docChnl,String docType,String docTitle,String docKeyWord);
	
	/**
	 * 根据文章id删除文章
	 * @param docID
	 */
	public void deleteDocByDocID(String docID);
	
	/**
	 * 根据模板类型查找模板对象
	 * @param tplType
	 * @return
	 */
	public List findTempletByTempType(String tplType);
	
	/**
	 * 在模板栏目绑定表中根据栏目id查找模板id
	 * @param chnlID
	 * @return
	 */
	public String findTempletIDByChnlID(String chnlID);
	
	/**
	 * 根据模板id,模板类型，模板存储名查找模板对象
	 * @param tID
	 * @param tType
	 * @param tSName
	 * @return
	 */
	public CmsTemplet findTempletByTempletIDAndTempletTypeAndTempletStoreName(int tID, String tType, String tSName);
	
	/**
	 * 根据模板id删除栏目模板绑定对象
	 * @param templetID
	 */
	public void deleteCmsChannelTempletByTempletID(String templetID);
	
	/**
	 * 根据模板类型，模板存储名查找模板对象
	 * @param templetType
	 * @param templetStoreName
	 * @return
	 */
	public List findTempletByTempletTypeAndTempletStoreName(String templetType,String templetStoreName);
	
	/**
	 * 根据模板id删除模板对象
	 * @param TempletID
	 */
	public void deleteTempletByTempletID(String templetID);
	
	/**
	 * 根据模板id查找模板对象
	 * @param templetID
	 * @return
	 */
	public CmsTemplet findTempletByTempletID(String templetID);
}
