package cn.com.sinosoft.ebusiness.web.action.cms;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.domain.CmsChannel;
import cn.com.sinosoft.ebusiness.cms.domain.CmsChannelTemplet;
import cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSManageService;
import cn.com.sinosoft.ebusiness.cms.util.CmsLoader;
import cn.com.sinosoft.util.string.StringUtils;

public class TempletManageAction extends Struts2Action{
	private static final long serialVersionUID = -4583353456275388669L;

	@Autowired
	private CMSManageService cmsManageService;
	
	private String templetFile;
	private String templetName;
	private String templetType;
	
	private String result;
	private String nodeID;
	private String[] tplType; 
	private List tplList;
	
	private static Logger loger = LoggerFactory.getLogger(TempletManageAction.class);
	
	public List getTplList() {
		return tplList;
	}
	public void setTplList(List tplList) {
		this.tplList = tplList;
	}
	public String[] getTplType() {
		return tplType;
	}
	public void setTplType(String[] tplType) {
		this.tplType = tplType;
	}
	public String getNodeID() {
		return nodeID;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTempletFile() {
		return templetFile;
	}

	public void setTempletFile(String templetFile) {
		this.templetFile = templetFile;
	}

	public String getTempletName() {
		return templetName;
	}

	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

	public String getTempletType() {
		return templetType;
	}

	public void setTempletType(String templetType) {
		this.templetType = templetType;
	}

	public void setCmsManageService(CMSManageService cmsManageService) {
		this.cmsManageService = cmsManageService;
	}
	

	public String toHead() {
		return SUCCESS;
	}
	
	//�½��ϴ���ģ��
	public String createTemplet() {
		try {
			CmsTemplet ct = new CmsTemplet();
			ct.setTplName(templetFile);
			ct.setTplType(templetType);
			ct.setTplStoreName(templetName);
			cmsManageService.createCMS(ct);
			this.result = "�ɹ�";
			super.getRequest().setAttribute("result", result);
		} catch (Exception e) {
			loger.info("cms�½��ϴ�ģ��ʧ��:"+StringUtils.exception2String(e));
			this.result = "ʧ��";
			super.getRequest().setAttribute("result", result);
		}
		return SUCCESS;
	}

	//�õ������½�ģ�����Ϣ
	public String getAllTempletInfo(){
		List docListTemp = cmsManageService.findTempletByTempType("�����б�");
		List docDetailTemp = cmsManageService.findTempletByTempType("������ϸ");
		List singleDocTemp = cmsManageService.findTempletByTempType("��ƪ����");
		List specialConTemp = cmsManageService.findTempletByTempType("���ⶨ��");
		List firstChildTemp = cmsManageService.findTempletByTempType("��ת����Ŀ");
		List includeTemp = cmsManageService.findTempletByTempType("����");
		super.getRequest().setAttribute("docListTemp", docListTemp);
		super.getRequest().setAttribute("docDetailTemp", docDetailTemp);
		super.getRequest().setAttribute("singleDocTemp", singleDocTemp);
		super.getRequest().setAttribute("specialConTemp", specialConTemp);
		super.getRequest().setAttribute("firstChildTemp", firstChildTemp);
		super.getRequest().setAttribute("includeTemp", includeTemp);
		super.getRequest().setAttribute("msgFlag", "1");
		
		//Ŀǰ��Ŀ��ģ�����Ϣ
		CmsChannelTemplet chnlTemp = new CmsChannelTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("chlid", nodeID);
		List<Object> chnlTempList = cmsManageService.searchCMS(chnlTemp, queryRule);
		tplList = new ArrayList();
		for (int i = 0; i < chnlTempList.size(); i++) {
			chnlTemp = (CmsChannelTemplet) chnlTempList.get(i);
			CmsTemplet tpl = new CmsTemplet();
			queryRule = QueryRule.getInstance();
			queryRule.addEqual("tplID", Integer.valueOf(chnlTemp.getTid()));
			List tplL = cmsManageService.searchCMS(tpl, queryRule);
			tplList.add(tplL.get(0));
		}
		return SUCCESS;
	}
	
	//����ʽ
	public String createCmsChannelTemplet(){
		try {
			String del = super.getRequest().getParameter("del") == null ? "" : super.getRequest().getParameter("del");
			String nodeID = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID");
			//ɾ��
			if (!"no".equals(del)) {
				CmsChannelTemplet chnlTemp = new CmsChannelTemplet();
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("chlid", nodeID);
				queryRule.addEqual("tid", del);
				List<Object> list = cmsManageService.searchCMS(chnlTemp, queryRule);
				for(int j = 0;j<list.size();j++){
					cmsManageService.deleteCMS((CmsChannelTemplet)list.get(j));
				}
				this.result = "createDone";
				super.getRequest().setAttribute("result", result);
				return SUCCESS;
			}
			
			String fc = super.getRequest().getParameter("fc") == null ? "" : super.getRequest().getParameter("fc");
			String num = super.getRequest().getParameter("num") == null ? "0" : super.getRequest().getParameter("num");
			CmsChannelTemplet chnlTemp = new CmsChannelTemplet();
			int numInt = Integer.parseInt(num);
			for (int i = 0; i < numInt; i++) {
				String tplID = super.getRequest().getParameter(fc + String.valueOf(i)) == null ? "" : super.getRequest().getParameter(fc + String.valueOf(i));
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("chlid", nodeID);
				queryRule.addEqual("tid", tplID);
				List<Object> list = cmsManageService.searchCMS(chnlTemp, queryRule);
				for(int j = 0;j<list.size();j++){
					cmsManageService.deleteCMS((CmsChannelTemplet)list.get(j));
				}
				chnlTemp = new CmsChannelTemplet();
				chnlTemp.setChlid(nodeID);
				chnlTemp.setTid(tplID);
				cmsManageService.createCMS(chnlTemp);
			}
			
			//�����һ����Ŀ��Ҫ��leftPanel
			CmsChannel chl = (CmsChannel) cmsManageService.findChannelByChannelID(Integer.valueOf(nodeID)).get(0);
			if("1".equals(chl.getParentID())) {
				
				List lpList = cmsManageService.findTempletByTempType("���˵�");
				CmsTemplet tpl = new CmsTemplet();
				CmsTemplet tpl_real = new CmsTemplet();
				for (int i = 0; i < lpList.size(); i++) {
					tpl_real = (CmsTemplet) lpList.get(i);
					System.out.println("ģ���ļ���---------------   " + tpl_real.getTplName());
					System.out.println("��Ŀ��ʾ����---------------   " + chl.getChnlName());
					System.out.println("ģ������---------------   " + tpl_real.getTplStoreName());
					System.out.println("=============================================");
					if(chl.getChnlDesName().equals(tpl_real.getTplStoreName())) {
						tpl = tpl_real;
						break;
					} else if ("���˵�".equals(tpl_real.getTplStoreName())) {
						tpl = tpl_real;
					}
				}
				chnlTemp = new CmsChannelTemplet();
				
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("chlid", nodeID);
				queryRule.addEqual("tid", String.valueOf(tpl.getTplID()));
				List<Object> list = cmsManageService.searchCMS(chnlTemp, queryRule);
				for(int j = 0;j<list.size();j++){
					cmsManageService.deleteCMS((CmsChannelTemplet)list.get(j));
				}
				chnlTemp.setChlid(nodeID);
				chnlTemp.setTid(String.valueOf(tpl.getTplID()));
				cmsManageService.createCMS(chnlTemp);
			}
			
			
			//Ŀǰ����ģ��
			chnlTemp = new CmsChannelTemplet();
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("chlid", nodeID);
			List<Object> chnlTempList = cmsManageService.searchCMS(chnlTemp, queryRule);
			tplList = new ArrayList();
			for (int i = 0; i < chnlTempList.size(); i++) {
				chnlTemp = (CmsChannelTemplet) chnlTempList.get(i);
				CmsTemplet tpl = new CmsTemplet();
				queryRule = QueryRule.getInstance();
				queryRule.addEqual("tplID", Integer.valueOf(chnlTemp.getTid()));
				List tplL = cmsManageService.searchCMS(tpl, queryRule);
				tplList.add(tplL.get(0));
			}
			this.result = "createDone";
			super.getRequest().setAttribute("result", result);
		} catch (Exception e) {
			this.result = "createFailed";
			super.getRequest().setAttribute("result", result);
			loger.info("cms����ʽʧ��:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String getCmsTemplet(){
		String nodeID = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID");
//		String templetType = super.getRequest().getParameter("templetType") == null ? "" : super.getRequest().getParameter("templetType");
		String templetTitle = super.getRequest().getParameter("templetTitle") == null ? "" : super.getRequest().getParameter("templetTitle");
//		List<Object> templetList = cmsManageService.findTempletByTempletTypeAndTempletStoreName(templetType, templetTitle);
		CmsTemplet tpl = new CmsTemplet();
		QueryRule q = QueryRule.getInstance();
		if(templetTitle != null && !"".equals(templetTitle)) {
			q.addEqual("tplName", templetTitle);
		}
		List templetList = cmsManageService.searchCMS(tpl, q);
		int listCount = 0;
		if (templetList != null) {
			listCount = templetList.size();
		}
		super.getRequest().setAttribute("templetList", templetList);
		super.getRequest().setAttribute("listCount", listCount);
		return SUCCESS;
	}
	
	public String deleteTemplet() throws IOException{
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		String[] idStr = ID.split(",");
		List arrayList = new ArrayList();
		arrayList = Arrays.asList(idStr);
		CmsChannelTemplet chnnlTemp = new CmsChannelTemplet();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("tid", arrayList);
		if(cmsManageService.searchCMS(chnnlTemp, queryRule).size()!= 0){
			result = "binded";
			super.getRequest().setAttribute("result", result);
		}else{
			try {
				for(int i=0; i<idStr.length; i++){
					cmsManageService.deleteTempletByTempletID(idStr[i]);
				}
				result = "deleteDone";
				super.getRequest().setAttribute("result", result);
			} catch (Exception e) {
				result = "deleteFailed";
				super.getRequest().setAttribute("result", result);
				loger.info("ʧ��:"+StringUtils.exception2String(e));
			}
		}
		
		return SUCCESS;
	}
	
	public String getCmsTempletForUpdate(){
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		CmsTemplet templet = cmsManageService.findTempletByTempletID(ID);
		super.getRequest().setAttribute("templet", templet);
		super.getRequest().setAttribute("msgFlag", "1");
		tplType = CmsLoader.templetTypeArray;
		return SUCCESS;
	}
	
	public String updateTemplet(){
		String templetID = super.getRequest().getParameter("templetID") == null ? "" : super.getRequest().getParameter("templetID").trim();
		System.out.println("templetID="+templetID);
		String tplStoreName = super.getRequest().getParameter("templetTitle") == null ? "" : super.getRequest().getParameter("templetTitle").trim();
		String tplType = super.getRequest().getParameter("templetType") == null ? "" : super.getRequest().getParameter("templetType").trim();
		System.out.println("tplType="+tplType);
		String tplName = super.getRequest().getParameter("templetFile") == null ? "" : super.getRequest().getParameter("templetFile").trim();
		
		CmsTemplet templet = new CmsTemplet();
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("tplID", Integer.parseInt(templetID));
		templet = (CmsTemplet)cmsManageService.searchCMS(templet, qr).get(0);
		try {
			templet.setTplName(tplName);
			templet.setTplType(tplType);
			templet.setTplStoreName(tplStoreName);
			cmsManageService.updateCMS(templet);
			result = "updateDone";
			super.getRequest().setAttribute("templet", templet);
			super.getRequest().setAttribute("result", result);
		} catch (Exception e) {
			result = "updateFailed";
			super.getRequest().setAttribute("result", result);
			super.getRequest().setAttribute("templet", templet);
			loger.info("cms����ģ��ʧ��:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	//��̬���ģ������
	public String createTpl() {
		tplType = CmsLoader.templetTypeArray;
		result = "";
		return SUCCESS;
	}
}




