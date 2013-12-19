package cn.com.sinosoft.ebusiness.mis.system.listener;

import ins.framework.common.QueryRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAuthorityService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.util.spring.SpringContextUtil;
public class LoadDataServlet extends HttpServlet {
	private static final long serialVersionUID = 43456765789021L;
	
	private GeDepartmentService geDepartmentService;
	private GeAuthorityService geAuthorityService; 

	public void init() throws ServletException {
		try {
			/***加载机构树*/
			ServletContext sc = getServletContext();
			SpringContextUtil.setAc(WebApplicationContextUtils.getRequiredWebApplicationContext(sc));
			geDepartmentService = (GeDepartmentService) SpringContextUtil.getBean("geDepartmentService");
			
			Map deptMap = new HashMap();
			getDeptTreeList("0",deptMap);
			sc.setAttribute("orgTreeData",deptMap);
			
			/**加载权限树*/
			geAuthorityService = (GeAuthorityService) SpringContextUtil.getBean("geAuthorityService");
			Map AuthorityMap = new HashMap();
			getAuthorityTreeList("ROOT",AuthorityMap);
			sc.setAttribute("AuthorityTreeData",AuthorityMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDeptTreeList(String parentId,Map map){
		try {
			QueryRule qRule = QueryRule.getInstance();
			qRule.addEqual("parentid", parentId);
			qRule.addAscOrder("deptid");
			List geDeptList = geDepartmentService.findAllDepartments(qRule);
			if(geDeptList != null && geDeptList.size() > 0){
				if(!map.containsKey(parentId)){
					map.put(parentId, geDeptList);
				}
				for(int i = 0; i < geDeptList.size(); i++){
					GeDepartment geDepartment = (GeDepartment)geDeptList.get(i);
					getDeptTreeList(geDepartment.getDeptid(),map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getAuthorityTreeList(String parentId,Map map){
		try {
			QueryRule qRule = QueryRule.getInstance();
			qRule.addEqual("parentID", parentId);
			qRule.addAscOrder("authorityorder");
			List geAuthorityList = geAuthorityService.findAllAuthoritys(qRule);
			if(geAuthorityList != null && geAuthorityList.size() > 0){
				if(!map.containsKey(parentId)){
					map.put(parentId, geAuthorityList);
				}
				for(int i = 0; i < geAuthorityList.size(); i++){
					GeAuthority geAuthority =(GeAuthority)geAuthorityList.get(i);
					getAuthorityTreeList(geAuthority.getAuthorityID(),map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
