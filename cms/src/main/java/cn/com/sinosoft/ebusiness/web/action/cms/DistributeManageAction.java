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
	//ȥ�ư�,������޸ĵ����£��򷢲����£����û��ֱ�ӷ���(��ʱ��ȫ������)
	public String goPub() {
		List docList = cmsDistributeService.getChlModifyDoc(nodeID);
		cmsDistributeService.pubChannel(nodeID);
//		if(docList == null || docList.size() < 1) {//û�и������£�����������ȫ�����·���
//			return SUCCESS;
//		} else {//�и�������
//			this.getRequest().setAttribute("docList", docList);
//			return "TO_DOC_SELECT";
//		}
		return SUCCESS;
	}
	
	//��������
	public String goSearch() {
		List docList = cmsDistributeService.getChlModifyDoc(nodeID);
		this.getRequest().setAttribute("docList", docList);
		return SUCCESS;
	}
	
//	//������������
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


















