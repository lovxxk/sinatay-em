package cn.com.sinosoft.ebusiness.cms.util;


import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.domain.CmsChannel;
import cn.com.sinosoft.ebusiness.cms.domain.CmsChannelTemplet;
import cn.com.sinosoft.ebusiness.cms.domain.CmsDocument;
import cn.com.sinosoft.ebusiness.cms.domain.CmsEnclosure;
import cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSManageService;
import cn.com.sinosoft.util.string.StringUtils;

/**
 * Cms的底层功能类
 */
public class CmsChannelFunc {
	@Autowired
	private CMSManageService cmsManageService;//底层实现类
	protected int tag = 0;
	
	private static Logger loger = LoggerFactory.getLogger(CmsChannelFunc.class);
	
	public void setCmsManageService(CMSManageService cmsManageService) {
		this.cmsManageService = cmsManageService;
	}
	/**
	 * @param list 栏目CmsChannel全集
	 * @param channelID 栏目CmsChannel全集
	 * @return 找到所有子节点的集合
	 */
	public static String findChildrenId(List<Object> list, String channelID){
		StringBuffer sbInter = new StringBuffer();
		CmsChannel cmsChannel= new CmsChannel();
		for(int i = 0; i < list.size(); ++i){
			//4是PARENTID
			cmsChannel = (CmsChannel)list.get(i);
			if(channelID.equals(cmsChannel.getParentID())){
				if(sbInter.length()!=0){
					sbInter.append(",");
				}
				sbInter.append(Integer.toString(i));
			}
		}
		return sbInter.toString();
	}
	/**
	 * @param list 栏目CmsChannel全集
	 * @param channelID 栏目CmsChannel全集
	 * @param list 栏目CmsChannel全集
	 * @return 所有栏目集合
	 */
	private static String channelPares(List<Object> list, String channelID, String channelStatus) {
		CmsChannel cmsChannel= new CmsChannel();
		String strChildren = findChildrenId(list, channelID);
		StringBuffer xmlString = new StringBuffer();
		if("".endsWith(strChildren)){
			return "";
		}
		String[] children = strChildren.split(",");
		for(int i = 0; i < children.length; ++i){	
			int inter = Integer.parseInt(children[i]);
			cmsChannel = (CmsChannel) list.get(inter);
			if(!channelStatus.equals(cmsChannel.getStatus()))continue;
			xmlString.append("<item id='" + cmsChannel.getChannelID().toString() + "' text='" + cmsChannel.getChnlDesName().toString() + "'>\n<userdata name='type'>" + channelID + "</userdata>");
			xmlString.append(channelPares(list, cmsChannel.getChannelID().toString(), channelStatus));
			xmlString.append("</item>\n");
		}
		return xmlString.toString();
	}
	/**
	 * @param channelId 栏目id
	 * @return 所有栏目集合
	 */
	public String channelRelation(String channelId) {
		StringBuffer sbStr = new StringBuffer(); 
		CmsChannel cmsChannel= new CmsChannel();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addNotEqual("status", "1");
		queryRule.addDescOrder("chnlOrder");
		List<Object> list=cmsManageService.searchCMS(cmsChannel, queryRule);
		sbStr.append("<tree id='0'>");
		sbStr.append(channelPares(list, channelId, "0"));
		sbStr.append("</tree>");
		return sbStr.toString();
	}
	
	/**
	 * 根据栏目id 得到栏目对象
	 * @param channelId
	 * @return
	 */
	public List findChannelByChannelID(int channelId){
		CmsChannel cmsChannel = new CmsChannel();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("channelID", channelId);
		List<Object> list = cmsManageService.searchCMS(cmsChannel, queryRule);
		return list;
	}
	
	/**
	 * 查找一个节点下所有子节点，包括该节点
	 * @param nodeID
	 * @return
	 */
	public String findChildNode(String nodeID){
		String tmps = "";
		tag++;
		if(tag==1){
			tmps = nodeID + ",";
		}
		CmsChannel cmsChannel = new CmsChannel();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("parentID", nodeID);
		queryRule.addNotEqual("status","1");
		queryRule.addIn("chnlType", "0","4");
		queryRule.addDescOrder("chnlOrder");
		List<Object> channelList = cmsManageService.searchCMS(cmsChannel, queryRule);
		for(int i = 0; i<channelList.size(); i++){
			cmsChannel = (CmsChannel)channelList.get(i);
			int id = cmsChannel.getChannelID();
			tmps += String.valueOf(id)+",";
			tmps += findChildNode(String.valueOf(id));
		}
		return tmps;
	}
	
	/**
	 * 将栏目id为chnlID下的文章状态修改为已删除
	 * @param chnlID
	 */
	public void updateDocStatus(String chnlID){
		CmsDocument cmsDocument = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docChannel", chnlID);
		List<Object> documentList = cmsManageService.searchCMS(cmsDocument, queryRule);
		for(int i = 0; i<documentList.size(); i++){
			int docID = ((CmsDocument)documentList.get(i)).getDocID();
			CmsDocument document = new CmsDocument();
			QueryRule qr = QueryRule.getInstance();
			qr.addEqual("docID", docID);
			((CmsDocument)cmsManageService.searchCMS(document, qr).get(0)).setDocStatus("3");
			cmsManageService.updateCMS((CmsDocument)cmsManageService.searchCMS(document, qr).get(0));
		}
	}
	
	/**
	 * 将栏目id为chnlID下的文章包括该文章下的附件删除
	 * @param chnlID
	 */
	public void deleteDoc(String chnlID){
		CmsDocument cmsDocument = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docChannel", chnlID);
		List<Object> documentList = cmsManageService.searchCMS(cmsDocument, queryRule);
		for(int i = 0; i<documentList.size(); i++){
			this.deleteEnc(((CmsDocument)documentList.get(i)).getDocID()+"");
			try {
				cmsManageService.deleteCMS((CmsDocument)documentList.get(i));
			} catch (Exception e) {
				
			}
		}
	}
	/**
	 * 递归修改栏目树的Status字段为已删除(“1”)，如一个节点下有子节点，修改节点为chnlID本身和该节点下所有的子节点
	 * @param chnlID
	 */
	public void updateChnlStatus(String chnlID){
		CmsChannel channel = new CmsChannel();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("channelID", Integer.parseInt(chnlID));
		((CmsChannel)cmsManageService.searchCMS(channel, queryRule).get(0)).setStatus("1");
		cmsManageService.updateCMS((CmsChannel)cmsManageService.searchCMS(channel, queryRule).get(0));
		
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("parentID", chnlID);
		List<Object> chnlList = cmsManageService.searchCMS(channel, qr);
		for(int i = 0; i<chnlList.size(); i++){
			updateChnlStatus(String.valueOf(((CmsChannel)chnlList.get(i)).getChannelID()));
		}
	}
	
	/**
	 * 递归删除栏目id为chnlID及它的子栏目
	 * @param chnlID
	 */
	public void deleteChnl(String chnlID){
		CmsChannel channel = new CmsChannel();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("channelID", Integer.parseInt(chnlID));
		((CmsChannel)cmsManageService.searchCMS(channel, queryRule).get(0)).setStatus("1");
		try {
			cmsManageService.deleteCMS((CmsChannel)cmsManageService.searchCMS(channel, queryRule).get(0));
		} catch (Exception e) {
			
		}
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("parentID", chnlID);
		List<Object> chnlList = cmsManageService.searchCMS(channel, qr);
		for(int i = 0; i<chnlList.size(); i++){
			deleteChnl(String.valueOf(((CmsChannel)chnlList.get(i)).getChannelID()));
		}
	}
	
	/**
	 * 删除栏目id为chnlID下的所有模板
	 * @param chnlID
	 */
	public void deleteTpl(String chnlID){
		CmsTemplet templet = new CmsTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("tplChnlID", chnlID);
		List<Object> temList = cmsManageService.searchCMS(templet, queryRule);
		for(int i = 0; i<temList.size(); i++){
			try {
				cmsManageService.deleteCMS((CmsTemplet)temList.get(i));
			} catch (Exception e) {
				
			}
		}
	}
	
	/**
	 * 删除文章id为docID下的所有附件
	 * @param docID
	 */
	public void deleteEnc(String docID){
		CmsEnclosure enclosure = new CmsEnclosure();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("attDocID",docID);
		List<Object> temList = cmsManageService.searchCMS(enclosure, queryRule);
		for(int i = 0; i<temList.size(); i++){
			try {
				cmsManageService.deleteCMS((CmsEnclosure)temList.get(i));
			} catch (Exception e) {
				
			}
		}
	}
	
	public List findDocByDocID(int docID){
		CmsDocument document = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docID", docID);
		List<Object> list = cmsManageService.searchCMS(document, queryRule);
		return list;
	}
	
	public String findDocIDByDocChnlAndDocTypeAndDocTitleAndDocKeyWord(String docChnl,String docType,String docTitle,String docKeyWord){
		CmsDocument document = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		if(!"".equals(docChnl)){
			queryRule.addEqual("docChannel", docChnl);
		}
		if(!"".equals(docType)){
			queryRule.addEqual("docType", docType);		
		}
		if(!"".equals(docTitle)){
			queryRule.addLike("docTitle", docTitle);
		}
		if(!"".equals(docKeyWord)){
			queryRule.addLike("docKeysWord", docKeyWord);
		}
		List<Object> docList = cmsManageService.searchCMS(document, queryRule);
		System.out.println("docList.size()="+docList.size());
		StringBuffer sb = new StringBuffer();
		if(docList!=null && docList.size()>0){
			for(int i = 0; i<docList.size(); i++){
				if(i==docList.size()-1){
					sb.append(((CmsDocument)docList.get(i)).getDocID());
				}else{
					sb.append(((CmsDocument)docList.get(i)).getDocID()+",");
				}
			}
		}else{
			sb.append("");
		}
		return sb.toString();
	}
	
	public void deleteDocByDocID(String docID){
		CmsDocument document = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docID", Integer.parseInt(docID));
		List<Object> docList = cmsManageService.searchCMS(document, queryRule);
		for(int i = 0; i<docList.size(); i++){
			try {
				cmsManageService.deleteCMS((CmsDocument)docList.get(i));
			} catch (Exception e) {
				
			}
		}
	}
	
	public List findTempletByTempType(String tplType){
		CmsTemplet templet = new CmsTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("tplType", tplType);
		List<Object> tempList = cmsManageService.searchCMS(templet, queryRule);
		return tempList;
	}
	
	public String findTempletIDByChnlID(String chnlID){
		String templetIDs = "";
		CmsChannelTemplet chnlTemp = new CmsChannelTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("chlid", chnlID);
		List<Object> chnlTempList = cmsManageService.searchCMS(chnlTemp, queryRule);
		if(chnlTempList == null || chnlTempList.size()==0){
			return templetIDs;
		}else{
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<chnlTempList.size();i++){
				if(i!=chnlTempList.size()-1){
					sb.append(((CmsChannelTemplet)chnlTempList.get(i)).getTid()+",");
				}else{
					sb.append(((CmsChannelTemplet)chnlTempList.get(i)).getTid());
				}
			}
			templetIDs = sb.toString();
		}
		return templetIDs;
	}
	
	public CmsTemplet findTempletByTempletIDAndTempletTypeAndTempletStoreName(int tID, String tType, String tSName){
		CmsTemplet templet = new CmsTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		if("".equals(tType) && "".equals(tSName)){
			queryRule.addEqual("tplID", tID);
			return (CmsTemplet)cmsManageService.searchCMS(templet, queryRule).get(0);
		}
		if(!"".equals(tType)){
			queryRule.addEqual("tplType", tType);
		}
		if(!"".equals(tSName)){
			queryRule.addLike("tplStoreName", tSName);
		}
		queryRule.addEqual("tplID", tID);
		return (CmsTemplet)cmsManageService.searchCMS(templet, queryRule).get(0);
	}
	
	public void deleteCmsChannelTempletByTempletID(String templetID){
		CmsChannelTemplet chnlTemp = new CmsChannelTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("tid", templetID);
		List<Object> chnlTempList = cmsManageService.searchCMS(chnlTemp, queryRule);
		for(int i=0;i<chnlTempList.size();i++){
			try {
				cmsManageService.deleteCMS((CmsChannelTemplet)chnlTempList.get(i));
			} catch (Exception e) {
				
			}
		}
	}
	
	public List findTempletByTempletTypeAndTempletStoreName(String templetType,String templetStoreName){
		CmsTemplet templet = new CmsTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		if(!"".equals(templetType)){
			queryRule.addEqual("tplType", templetType);
		}
		if(!"".equals(templetStoreName)){
			queryRule.addLike("tplStoreName", templetStoreName);
		}
		return cmsManageService.searchCMS(templet, queryRule);
	}
	
	public void deleteTempletByTempletID(String templetID){
		CmsTemplet templet = new CmsTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("tplID", Integer.parseInt(templetID));
		List<Object> templetList = cmsManageService.searchCMS(templet,queryRule);
		for(int i = 0 ; i<templetList.size(); i++){
			try {
				cmsManageService.deleteCMS((CmsTemplet)templetList.get(i));
			} catch (Exception e) {
				loger.info("CMS根据模板ID删除模板:"+StringUtils.exception2String(e));
			}
		}
	}
	
	public CmsTemplet findTempletByTempletID(String templetID){
		CmsTemplet templet = new CmsTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("tplID", Integer.parseInt(templetID));
		return (CmsTemplet)cmsManageService.searchCMS(templet, queryRule).get(0);
	}
}		




















