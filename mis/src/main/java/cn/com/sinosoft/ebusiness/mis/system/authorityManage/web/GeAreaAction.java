package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAreaAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAreaService;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

public class GeAreaAction extends Struts2Action {
	private GeAreaAuthority geAreaAuthority;
	private GeAreaService geAreaService;
	private String message;
	private int flag;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public GeAreaAuthority getGeAreaAuthority() {
		return geAreaAuthority;
	}
	public void setGeAreaAuthority(GeAreaAuthority geAreaAuthority) {
		this.geAreaAuthority = geAreaAuthority;
	}
	public GeAreaService getGeAreaService() {
		return geAreaService;
	}
	public void setGeAreaService(GeAreaService geAreaService) {
		this.geAreaService = geAreaService;
	}
	/**
	 * �첽����������
	 */
	@LocusTrace(setCode="GeAreaAction_findGeAreaActionTree")
	public void findGeAreaActionTree() {

		String treeID = super.getRequest().getParameter("id") == null ? ""
				: super.getRequest().getParameter("id").trim();
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			if ("0".equals(treeID)) {
				str.append("<item id=\"1\" child=\"1\" nocheckbox=\"1\" open=\"1\" text=\"�й�\">");
				str.append(getItemsForArea("0"));
				str.append("</item>");
			} else {
				str.append(getItemsForArea(treeID));
			}
			str.append("</tree>");
			System.out.println(str);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/xml; charset=GBK");
			response.setHeader("Cache-Control", "no-cache");

			PrintWriter pw = response.getWriter();
			pw.write(str.toString());
			pw.flush();
			pw.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �첽�������������÷���
	 */
	private String getItemsForArea(String treeID) {
		StringBuffer str = new StringBuffer();
		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(treeID)) queryRule.addEqual("pgid", treeID);
		queryRule.addAscOrder("gid");
		List list = geAreaService.findGeAreas(queryRule);
		for (int i = 0; i < list.size(); i++) {
			GeAreaAuthority geAreaAuthority1 = (GeAreaAuthority) list.get(i);

			List childList = geAreaService.getChildList(geAreaAuthority1.getGid());
			/** �ҵ��ڵ��������ӽڵ� */
			if (childList.size() > 0) {
					str.append("<item id=\"" + geAreaAuthority1.getGid()
							+ "\" child=\"1\" text=\""
							+ geAreaAuthority1.getGname() + "\">");
				
			} else {
					str.append("<item id=\"" + geAreaAuthority1.getGid()
							+ "\" child=\"0\" text=\""
							+ geAreaAuthority1.getGname() + "\">");
			}
			str.append("<userdata name=\"ud_block\">ud_data</userdata>");
			str.append("</item>");
		}
		return str.toString();
	}
	/**
	 * �鿴ĳ���������ϸ��Ϣ
	 */
	@LocusTrace(setCode="GeAreaAction_findAreaManage")
	public String findAreaManage(){
		geAreaAuthority = geAreaService.findGeAuthorityByPK(geAreaAuthority.getGid());
		return SUCCESS;
	}
	/**
	 * ��������
	 */
	@LocusTrace(setCode="GeAreaAction_createArea")
	public String createArea(){
		try {
			if(geAreaService.exists(geAreaAuthority.getGid())){
				message = "�������Ѿ�����";
			}else{
				if(geAreaAuthority.getGlevel().equals("1")){
					geAreaAuthority.setPgid("1");
				}
				if(geAreaService.save(geAreaAuthority)){
					message = "�½�����ɹ�";
					flag = 1;
				}else{
					message = "�½�����ʧ��";
				}
			}
		} catch (Exception e) {
			message = "�½�����ʧ��";
		}
		return SUCCESS;
	}
	/**
	 * ��������
	 */
	@LocusTrace(setCode="GeAreaAction_updatesGeArea")
	public String updatesGeArea(){
		try {
			if(geAreaService.updates(geAreaAuthority)){
				flag = 1;
				message = "�����޸ĳɹ�";
			}else{
				message = "�����޸�ʧ��";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * ɾ������
	 */
	@LocusTrace(setCode="GeAreaAction_deleteGeArea")
	public String deleteGeArea(){
		try {
			if(geAreaService.isUsedDep(geAreaAuthority.getGid())){
				message = "�������ѱ���������,�����Խ���ɾ������";
			}else{
				if(geAreaService.delete(geAreaAuthority)){
					message = "����ɾ���ɹ�";
					flag = 1;
				}else{
					message = "ɾ������ʧ��";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
