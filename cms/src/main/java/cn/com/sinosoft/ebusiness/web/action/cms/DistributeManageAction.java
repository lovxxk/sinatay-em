package cn.com.sinosoft.ebusiness.web.action.cms;

import ins.framework.web.Struts2Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.service.facade.CMSDistributeService;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSManageService;
import cn.com.sinosoft.ebusiness.cms.util.CmsChannelFunc;

public class DistributeManageAction  extends Struts2Action {
	private static final long serialVersionUID = 7675836171762521039L;
	@Autowired
	private CMSManageService cmsManageService;
	@Autowired
	private CmsChannelFunc cmsChannelFunc;
	@Autowired
	private CMSDistributeService cmsDistributeService;
	private String nodeID;
	private String message;
	public void setCmsManageService(CMSManageService cmsManageService) {
		this.cmsManageService = cmsManageService;
	}
	public void setCmsChannelFunc(CmsChannelFunc cmsChannelFunc) {
		this.cmsChannelFunc = cmsChannelFunc;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNodeID() {
		return nodeID;
	}
	//去酒吧,如果有修改的文章，则发布文章，如果没有直接发布(暂时先全部发布)
	public String goPub() {
		List docList = cmsDistributeService.getChlModifyDoc(nodeID);
		cmsDistributeService.pubChannel(nodeID);
//		if(docList == null || docList.size() < 1) {//没有更新文章，将所有内容全部重新发布
//			return SUCCESS;
//		} else {//有更新文章
//			this.getRequest().setAttribute("docList", docList);
//			return "TO_DOC_SELECT";
//		}
		return SUCCESS;
	}
	
	//查找文章
	public String goSearch() {
		List docList = cmsDistributeService.getChlModifyDoc(nodeID);
		this.getRequest().setAttribute("docList", docList);
		return SUCCESS;
	}
	
//	//发布部分文章
//	public String goPubDoc() {
//		String[] docStr = this.getRequest().getParameter("ID").split(",");
//		CmsDocument doc = new CmsDocument();
//		for (int i = 0; i < docStr.length; i++) {
//			QueryRule q = QueryRule.getInstance();
//			q.addEqual("DocID", Integer.valueOf(docStr[i]));
//			List l = cmsManageService.searchCMS(doc, q);
//			docList.add(l.get(0));
//		}
//		cmsDistributeService.pubChannel(nodeID, docList);
//		return SUCCESS;
//	}

}


















