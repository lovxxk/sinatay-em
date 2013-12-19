package cn.com.sinosoft.ebusiness.web.action.cms;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.domain.CmsChannel;
import cn.com.sinosoft.ebusiness.cms.domain.CmsDocument;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSManageService;
import cn.com.sinosoft.util.string.StringUtils;

public class ColumnsManageAction extends Struts2Action{
	private static final long serialVersionUID = -9132431675180880544L;

	@Autowired
	private CMSManageService cmsManageService;
	
	private CmsChannel channel;
	
	private CmsDocument document;

	private String message;
	
	private static Logger loger = LoggerFactory.getLogger(ColumnsManageAction.class);
	
	public CmsChannel getChannel() {
		return channel;
	}

	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}

	public void setCmsManageService(CMSManageService cmsManageService) {
		this.cmsManageService = cmsManageService;
	}
	
	public CmsDocument getDocument() {
		return document;
	}

	public void setDocument(CmsDocument document) {
		this.document = document;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCmsChannelByChannelId(){
		String channelId = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID").trim();
		try {
			List channelList = cmsManageService.findChannelByChannelID(Integer.parseInt(channelId));
			channel = (CmsChannel)channelList.get(0);
			super.getRequest().setAttribute("channel", channel);
		} catch (Exception e) {
			loger.info("cms根据栏目id查找栏目失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String getCmsChannelInfo(){
		String channelId = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID").trim();
		try {
			List channelList = cmsManageService.findChannelByChannelID(Integer.parseInt(channelId));
			channel = (CmsChannel)channelList.get(0);
			super.getRequest().setAttribute("channel", channel);
			//判断该栏目下是否有子栏目
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("parentID", channelId);
			queryRule.addNotEqual("status", "1");
			queryRule.addEqual("chnlType", "0");
			channel = new CmsChannel();
			boolean tag = false;
			List result = cmsManageService.searchCMS(channel, queryRule);
			if(result.size()>0){
				tag = true;
			}
			super.getRequest().setAttribute("tag", tag);
			//查找该栏目下是否有文章
			String docId = "";
			document = new CmsDocument();
			QueryRule qr = QueryRule.getInstance();
			qr.addEqual("docChannel", channelId);
			List<Object> listDocumentList = cmsManageService.searchCMS(document, qr);
			if(listDocumentList.size()!=0){
				docId = ((CmsDocument)listDocumentList.get(0)).getDocID()+"";
			}else{
				docId = "";
			}
			super.getRequest().setAttribute("docId", docId);
		} catch (Exception e) {
			loger.info("cms获取栏目信息失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String addNode(){
		String nodeID = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID").trim();
		String chnlName = super.getRequest().getParameter("ChnlName") == null ? "" : super.getRequest().getParameter("ChnlName").trim();
		String chnlDESName = super.getRequest().getParameter("ChnlDESName") == null ? "" : super.getRequest().getParameter("ChnlDESName").trim();
		String chnlType = super.getRequest().getParameter("ChnlType") == null ? "" : super.getRequest().getParameter("ChnlType").trim();
		String linkURL = super.getRequest().getParameter("LinkURL")== null ? "" : super.getRequest().getParameter("LinkURL").trim();
		
		System.out.println(super.getRequest().getCharacterEncoding());
		channel = new CmsChannel();
		QueryRule qr1 = QueryRule.getInstance();
		qr1.addEqual("chnlName", chnlName);
		List result1 = cmsManageService.searchCMS(channel, qr1);
		if(result1.size()>0){
			message = "reChnlName";
			super.getRequest().setAttribute("message", message);
		}else{
			channel = new CmsChannel();
			QueryRule qr2 = QueryRule.getInstance();
			qr2.addEqual("parentID", nodeID);
			qr2.addEqual("linkUrl", linkURL);
			List result2 = cmsManageService.searchCMS(channel, qr2);
			if(result2.size()>0){
				message = "reLinkURL";
				super.getRequest().setAttribute("message", message);
			}else{
				try {
					channel = new CmsChannel();
					channel.setSiteID("siteID");
					channel.setChnlName(chnlName);
					channel.setChnlDesc("chnlDesc");
					channel.setParentID(nodeID);
					channel.setChnlOrder(new BigDecimal(0));
					channel.setChnlDataPath("chnlDataPath");
					channel.setChnlType(chnlType);
					channel.setCrUser("crUser");
					channel.setStatus("0");
					channel.setLinkUrl(linkURL);
					channel.setChnlDesName(chnlDESName);
					channel.setChannelPower("0");
					channel.setMirrorID("0");
					cmsManageService.createCMS(channel);
					message = "createDone";
					super.getRequest().setAttribute("message", message);
				} catch (Exception e) {
					message = "createFailed";
					super.getRequest().setAttribute("message", message);
					loger.info("cms增加节点失败:"+StringUtils.exception2String(e));
				}
			}
		}
		return SUCCESS;
	}
	
	public String updateNode(){
		String channelID = super.getRequest().getParameter("ChannelID") == null ? "" : super.getRequest().getParameter("ChannelID").trim();
		String chnlName = super.getRequest().getParameter("ChnlName") == null ? "" : super.getRequest().getParameter("ChnlName").trim();
		String chnlDesName = super.getRequest().getParameter("ChnlDESName") == null ? "" : super.getRequest().getParameter("ChnlDESName").trim();
		String linkURL = super.getRequest().getParameter("LinkURL") == null ? "" : super.getRequest().getParameter("LinkURL").trim();
		String chnlType = super.getRequest().getParameter("ChnlType") == null ? "" : super.getRequest().getParameter("ChnlType").trim();
		
		channel = new CmsChannel();
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("channelID", Integer.parseInt(channelID));
		channel = (CmsChannel)cmsManageService.searchCMS(channel, qr).get(0);
		try {
			channel.setChnlName(chnlName);
			channel.setChnlDesName(chnlDesName);
			channel.setLinkUrl(linkURL);
			channel.setChnlType(chnlType);
			cmsManageService.updateCMS(channel);
			message = "updateDone";
			super.getRequest().setAttribute("message", message);
			super.getRequest().setAttribute("msgFlag", "1");
		} catch (Exception e) {
			message = "updateFailed";
			super.getRequest().setAttribute("message", message);
			loger.info("cms更新节点失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String getAllChildChannelID(){
		String nodeID = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID").trim();
		super.getRequest().setAttribute("nodeID", nodeID);
		try {
			channel = new CmsChannel();
			QueryRule qr = QueryRule.getInstance();
			qr.addEqual("parentID", nodeID);
			qr.addNotEqual("status", "1");
			qr.addDescOrder("chnlOrder");
			List<Object> list = cmsManageService.searchCMS(channel, qr);
			StringBuffer sb = new StringBuffer();
			String childIDStr = "";
			if(list == null || list.size()== 0){
				childIDStr = "";
			}else{
				super.getRequest().setAttribute("channelList", list);
				for(int i = 0; i<list.size(); i++){
					if(i != list.size()-1){
						sb.append(((CmsChannel)list.get(i)).getChannelID()+ ",");
					}else{
						sb.append(((CmsChannel)list.get(i)).getChannelID());
					}
					
				}
				childIDStr = sb.toString();
			}
			super.getRequest().setAttribute("childIDStr", childIDStr);
			super.getRequest().setAttribute("message", "");
		} catch (Exception e) {
			loger.info("cms获得全部栏目子节点失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String updateOrder(){
		String forderValue = super.getRequest().getParameter("forderValue") == null ? "" : super.getRequest().getParameter("forderValue").trim();
		System.out.println(forderValue);
		String[] chnlId=forderValue.split(",");
		int length=chnlId.length;
		System.out.println(length);
		try {
			for(int i = 0; i<length; i++){
				channel = new CmsChannel();
				QueryRule qr = QueryRule.getInstance();
				qr.addEqual("channelID", Integer.parseInt(chnlId[i]));
				List<Object> channelList = cmsManageService.searchCMS(channel, qr);
				for(int j=0;j<channelList.size();j++){
					((CmsChannel)channelList.get(j)).setChnlOrder(new BigDecimal(length-i));
					cmsManageService.updateCMS((CmsChannel)channelList.get(j));
				}
			}
			message = "orderDone";
			super.getRequest().setAttribute("message", message);
		} catch (Exception e) {
			message = "orderFailed";
			super.getRequest().setAttribute("message", message);
			loger.info("cms更新节点失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String deleteNode(){
		try {
			String nodeID = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID");
			super.getRequest().setAttribute("nodeID", nodeID);
			String idArray = cmsManageService.findChildNode(nodeID);
			String[]  ID = idArray.split(",");
			for(int i = 0; i<ID.length; i++){
				cmsManageService.deleteDoc(ID[i]);
				cmsManageService.deleteTpl(ID[i]);
			}
			cmsManageService.deleteChnl(nodeID);
			message = "deleteDone";
			super.getRequest().setAttribute("message",message);
		} catch (Exception e) {
			message = "deleteFailed";
			super.getRequest().setAttribute("message",message);
			loger.info("cms删除节点失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
}
