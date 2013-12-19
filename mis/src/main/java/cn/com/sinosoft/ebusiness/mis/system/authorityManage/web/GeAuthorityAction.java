package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAuthorityService;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.io.PathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

/**
 * 权限Action
 * 
 * 
 * 
 */
public class GeAuthorityAction extends Struts2Action {
	private static final long serialVersionUID = 54765879094334L;
	private GeAuthorityService geAuthorityService;
	private GeCodeService geCodeService;
	/** 数据字典服务类 */
	private GeAuthority geAuthority;
	private String message = "";
	private int flag;

	static {
		PropertyFileUtils.init(PathUtil.getClassBuildPath() + "/config/companyInfo.properties");
	}

	private static String companyName = PropertyFileUtils.getConfig("companyName");

	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public void setGeAuthorityService(GeAuthorityService geAuthorityService) {
		this.geAuthorityService = geAuthorityService;
	}

	public GeAuthority getGeAuthority() {
		return geAuthority;
	}

	public void setGeAuthority(GeAuthority geAuthority) {
		this.geAuthority = geAuthority;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * 异步加载菜单
	 * 
	 * @return
	 * @throws IOException
	 */
	@LocusTrace(setCode = "GeAuthorityAction_findLeftMenu")
	public String findLeftMenu() throws IOException {
		String parentID = super.getRequest().getParameter("id");
		String parentIDCopy = parentID;
		if (parentID.indexOf("↑↑") != -1)
			parentIDCopy = parentIDCopy.split("↑↑")[0];
		StringBuilder str = new StringBuilder();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>\n<tree id='" + parentID + "'>\n");
		if ("0".equals(parentID)) {
			str.append(findLeftMenuItem("ROOT"));
		} else {
			str.append(findLeftMenuItem(parentIDCopy));
		}
		str.append("</tree>");

		try {
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
		return NONE;
	}

	/**
	 * 异步加载左侧菜单
	 * 
	 * @param treeID
	 */
	private String findLeftMenuItem(String treeID) {
		StringBuffer str = new StringBuffer("");
		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(treeID))
			queryRule.addEqual("parentID", treeID);
		queryRule.addEqual("isMenu", "1");
		queryRule.addEqual("systype", "misSystem");
		queryRule.addAscOrder("authorityorder");
		List<GeAuthority> geAuthorityList = geAuthorityService.findAllAuthoritys(queryRule);
		for (int i = 0; i < geAuthorityList.size(); i++) {
			GeAuthority geAuthority = (GeAuthority) geAuthorityList.get(i);
			Map permission = (Map) super.getSession().getAttribute("permission");
			if (!permission.containsKey(geAuthority.getAuthorityID()))
				continue;
			/** xml替换特殊字符 */
			String authorityLink = geAuthority.getAuthorityLink();
			if (!StringUtils.isBlank(authorityLink)) {
				if (authorityLink.indexOf("&") != -1)
					authorityLink = authorityLink.replaceAll("&", "&amp;");
				if (authorityLink.indexOf("<") != -1)
					authorityLink = authorityLink.replaceAll("<", "&lt;");
				if (authorityLink.indexOf(">") != -1)
					authorityLink = authorityLink.replaceAll(">", "&gt;");
				if (authorityLink.indexOf("'") != -1)
					authorityLink = authorityLink.replaceAll("'", "&apos;");
				if (authorityLink.indexOf("\"") != -1)
					authorityLink = authorityLink.replaceAll("\"", "&quot;");
			}

			if (geAuthorityService.isHasChildMenu(geAuthority.getAuthorityID())) {
				String id = geAuthority.getAuthorityID();

				if (!StringUtils.isBlank(authorityLink))
					id += "↑↑" + authorityLink + "↑↑" + geAuthority.getOpentype();
				str.append("<item id=\"" + id + "\" child=\"1\" text=\"" + geAuthority.getAuthorityName() + "\">");
			} else {
				String id = geAuthority.getAuthorityID();
				if (!StringUtils.isBlank(authorityLink))
					id += "↑↑" + authorityLink + "↑↑" + geAuthority.getOpentype();
				str.append("<item id=\"" + id + "\" child=\"0\" text=\"" + geAuthority.getAuthorityName() + "\">");
			}
			str.append("<userdata name=\"ud_block\">ud_data</userdata>");
			str.append("</item>\n");
		}
		return str.toString();
	}

	/**
	 * 获取权限用于增删改查 注：没有复选框/全部权限/异步加载
	 */
	@LocusTrace(setCode = "GeAuthorityAction_findMisAuthorityTree")
	public void findMisAuthorityTree() {
		List<GeCode> geCodes = geCodeService.findAllByGeCodeType("SystemType");

		String treeID = super.getRequest().getParameter("id") == null ? "" : super.getRequest().getParameter("id").trim();
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			if ("0".equals(treeID)) {
				str.append("<item id=\"ROOT\" child=\"1\" nocheckbox=\"1\" open=\"1\" text=\"" + companyName + "权限\">");
				for (int i = 0; i < geCodes.size(); i++) {
					GeCode geCode = geCodes.get(i);
					str.append("<item id=\"" + geCode.getId().getCodeCode() + "\" child=\"1\" nocheckbox=\"1\" text=\"" + geCode.getCodeCName() + "\"></item>");
				}
				str.append("</item>");
			} else {
				str.append(findMisAuthorityTreeItem(treeID));
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
	 * 获取权限用于增删改查 注：没有复选框/全部权限/异步加载
	 */
	private String findMisAuthorityTreeItem(String treeID) {
		StringBuffer str = new StringBuffer();
		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(treeID)) {
			if (treeID.indexOf("ROLE_") != -1) {
				queryRule.addEqual("parentID", treeID);
			} else {
				queryRule.addEqual("systype", treeID);
				queryRule.addEqual("parentID", "ROOT");
			}
		}
		queryRule.addAscOrder("authorityorder");
		List<GeAuthority> list = geAuthorityService.findAllAuthoritys(queryRule);
		for (int i = 0; i < list.size(); i++) {
			GeAuthority geAuthority = (GeAuthority) list.get(i);

			List childList = geAuthorityService.getChildList(geAuthority.getAuthorityID());
			/** 找到节点下所有子节点 */
			if (childList.size() > 0) {
				str.append("<item id=\"" + geAuthority.getAuthorityID() + "\" child=\"1\" text=\"" + geAuthority.getAuthorityName() + "\">");
			} else {
				str.append("<item id=\"" + geAuthority.getAuthorityID() + "\" child=\"0\" text=\"" + geAuthority.getAuthorityName() + "\">");
			}
			str.append("<userdata name=\"ud_block\">ud_data</userdata>");
			str.append("</item>");
		}
		return str.toString();
	}

	/**
	 * 查询机构信息用于展示
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "GeAuthorityAction_findMisAuthorityInfo")
	public String findMisAuthorityInfo() {
		try {
			geAuthority = geAuthorityService.findGeAuthorityByPK(geAuthority.getAuthorityID());
			Map map = (Map) super.getSession().getAttribute("permission");
			String groupRoleAuthDeptId = (String) map.get("ROLE_S_ROLE_M");
			List<Map<String, String>> geRoles = geAuthorityService.findAuthorityRole(geAuthority.getAuthorityID(), groupRoleAuthDeptId);
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.codeCode", geAuthority.getSystype());
			GeCode geCode = geCodeService.findGeCode(queryRule);
			super.getRequest().setAttribute("geRoles", geRoles);
			super.getRequest().setAttribute("geCode", geCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String findRoleByAuth() {
		try {
			String groupRoleAuthDeptId = super.getRequest().getParameter("authorityid");
			String authorityString = super.getRequest().getParameter("authority");
			Map map = (Map) super.getSession().getAttribute("permission");
			// String groupRoleAuthDeptId = (String) map.get("ROLE_S_ROLE_M");
			if (StringUtils.isBlank(groupRoleAuthDeptId)) {
				groupRoleAuthDeptId = (String) map.get("ROLE_S_ROLE_M");
			}
			Page page = geAuthorityService.findAuthorityRole(authorityString, groupRoleAuthDeptId, pageNo, pageSize);
			String pageStr = JsonBinder.buildNonNullBinder().toJson(page);
			super.render(pageStr, "application/json;charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 进入到新建机构界面
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "GeAuthorityAction_frmCreateMisAuthority")
	public String frmCreateMisAuthority() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新建权限
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "GeAuthorityAction_createMisAuthority")
	public String createMisAuthority() {
		try {
			GeAuthority gea = geAuthorityService.findGeAuthorityByPK(geAuthority.getAuthorityID());
			if (gea != null) {
				message = "该权限已经存在";
			} else {
				if (geAuthorityService.save(geAuthority)) {
					Map map = (Map) super.getServletContext().getAttribute("AuthorityTreeData");
					if (!map.containsKey(geAuthority.getParentID())) {
						List list = new ArrayList();
						list.add(geAuthority);
						map.put(geAuthority.getParentID(), list);
					} else {
						List list = (List) map.get(geAuthority.getParentID());
						list.add(geAuthority);
					}
					message = "新建权限成功";
					flag = 1;
				} else {
					message = "新建权限失败";
				}
			}
		} catch (Exception e) {
			message = "新建权限失败";
		}
		return SUCCESS;
	}

	/**
	 * 获取修改的权限信息
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "GeAuthorityAction_findMisAuthorityForUpdate")
	public String findMisAuthorityForUpdate() {
		try {
			geAuthority = geAuthorityService.findGeAuthorityByPK(geAuthority.getAuthorityID());
			List<GeCode> geCodes = geCodeService.findAllByGeCodeType("SystemType");
			super.getRequest().setAttribute("geCodes", geCodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 更新权限信息
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "GeAuthorityAction_updatesMisAuthority")
	public String updatesMisAuthority() {
		try {
			if (geAuthorityService.updates(geAuthority)) {
				flag = 1;
				message = "权限修改成功";
				Map map = (Map) super.getServletContext().getAttribute("AuthorityTreeData");
				List<GeAuthority> list = (List<GeAuthority>) map.get(geAuthority.getParentID());
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getAuthorityID().equals(geAuthority.getAuthorityID())) {
						list.remove(i);
						list.add(i, geAuthority);
						break;
					}
				}
				/*
				 * String oldSystype = super.getRequest()
				 * .getAttribute("oldSystype").toString();
				 * super.getRequest().setAttribute("oldSystype", oldSystype);
				 */
			} else {
				message = "权限修改失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除权限
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "GeAuthorityAction_deleteMisAuthority")
	public String deleteMisAuthority() {
		try {
			if (geAuthorityService.existRoleAuthority(geAuthority.getAuthorityID())) {
				message = "该权限已被角色使用，请先解除使用";
			} else {
				GeAuthority geAuth = geAuthorityService.findGeAuthorityByPK(geAuthority.getAuthorityID());
				if (geAuthorityService.delete(geAuth)) {
					message = "删除权限成功";
					flag = 1;
					Map map = (Map) super.getServletContext().getAttribute("AuthorityTreeData");
					List<GeAuthority> list = (List<GeAuthority>) map.get(geAuth.getParentID());
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getAuthorityID().equals(geAuth.getAuthorityID())) {
							list.remove(i);
							if (list.size() == 0) {
								map.remove(geAuth.getParentID());
							}
							break;
						}
					}
				} else {
					message = "删除权限失败";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 校验
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "GeAuthorityAction_validateLogin")
	public String validateLogin() {
		try {
			JSONObject backJson = new JSONObject();
			if (super.getSession().getAttribute("geOperator") != null) {
				backJson.put("flg2", "success");
			} else {
				backJson.put("flg2", "false");
			}
			super.render(backJson.toString(), "application/json;charset=GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}

}
