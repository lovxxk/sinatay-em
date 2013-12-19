package cn.com.sinosoft.ebusiness.mis.business.customerManage.party.web;



import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeType;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeTypeService;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterContact;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterInfo;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterSerialNumber;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterService;
import cn.com.sinosoft.ebusiness.common.party.service.facade.ThirdParterService;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;


public class PartyAction extends Struts2Action {
	private static final long serialVersionUID = PartyAction.class.hashCode();
	//dto
	private Page page;
	private GeThirdParterInfo geThirdParterInfo;
	private GeThirdParterService geThirdParterService;
	private GeThirdParterSerialNumber geThirdParterSerialNumber;
	private List<GeThirdParterInfo>  geThirdParterInfoList; 
	private List<GeThirdParterSerialNumber> geThirdParterSerialNumberList;
	private Map<String,String> geCodeThirdCompanyTypeMap;
	private String opertion;
	private File attrPicture;
	private String attrPictureFileName;
	private List<GeCode> geCodeSexList;//性别下拉表
	private List<GeCode> geCodeIdentifyTypeList;//证件类型下拉列表
	private List<GeCode> geCodeCompanyTypeList;//公司类型下拉列表
	private List<GeCode> geBusinessAreaTypeList;//业务类型领域
	private String message;//返回页面的提示信息
	private int flag;//页面提示选择器
	private String nameCount;
	private String searialNo;
	private String deptId;
	private static final String imagePath = "upload/images/imageP/";
	//private static final String imageAbsolutePath = "D:/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/Jane-PCNode01Cell/mis_war.ear/mis.war/upload/images/imageL/";
	//private static final String imageOnlineAbsolutePath = "D:/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/Jane-PCNode01Cell/onlineWeb.ear/online.war/upload/images/imageL/";
	private static final String imageAbsolutePath = "D:/Program Files/develop/eclipsedevelop/eclipse/workspace/chinaLifeDevelop/mis/src/main/webapp/upload/images/imageP/";
	private static final String imageOnlineAbsolutePath = "D:/Program Files/develop/eclipsedevelop/eclipse/workspace/chinaLifeDevelop/mis/src/main/webapp/upload/images/imageP/";
	//service
	private ThirdParterService thirdParterService;
	private GeCodeTypeService geCodeTypeService;
	private GeCodeService geCodeService;
	private BizCommonService bizCommonService;
	private GeDepartmentService geDepartmentService;
	//business method
	//选择机构编号，使用公共的
	public String prepareAddThirdParterInfoAndThirdParterContact() throws Exception{
		geCodeSexList = getGecode("Sex");//
		//geCodeIdentifyTypeList = getGecode("IdentifyType");//
		geCodeIdentifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");	
		geCodeCompanyTypeList = geCodeService.findAllByGeCodeType("ThirdCompanyType");//
		geBusinessAreaTypeList = geCodeService.findAllByGeCodeType("BusinessArea");//
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityids = (String) map.get("ROLE_B_PTPI");//拿到该功能的所有机构权限
		for (int i = geBusinessAreaTypeList.size() -1; i >=0; i--) {
			String baStr = geBusinessAreaTypeList.get(i).getId().getCodeCode();
			if(baStr.equals("1")){
				geBusinessAreaTypeList.remove(i);
			}else{
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					geBusinessAreaTypeList.remove(i);

				}
			}
			
		}
		//拿到 登录人的 在营销活动中的 业务领域   end	
		return SUCCESS;
	}
	/**
	 * 保存 第三方合作伙伴信息表-GE_ThirdParter_Info
	 * 保存 第三方联系人信息表-GE_ThirdParter_Contact
	 */
	
	public String addThirdParterInfoAndThirdParterContact(){
		/*start
		//geThirdParterInfo.sethThirdParterID(null);
		GeThirdParterInfo geThirdParterInfoTemp = thirdParterService.findGeThirdParterInfoByThirdParterID(geThirdParterInfo.getThirdParterID());
		if(geThirdParterInfoTemp!=null){
			message="该公司代码已存在";
			flag = 1;
		}else{
//			String businessAreas = geThirdParterInfo.getBusinessArea();
//			businessAreas = businessAreas.replace(" ", "");
//			//
//			geThirdParterInfo.setBusinessArea(businessAreas);//
			thirdParterService.addGeThirdParterInfo(geThirdParterInfo);
			flag = 1;
			message="添加成功";
			
		}
		*/
		String busine=super.getRequest().getParameter("BusinessArea");
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		geThirdParterInfo.setBusinessArea(busine);
		geThirdParterInfo.setCreateCode(operator.getOperatorid());
		thirdParterService.addGeThirdParterInfo(geThirdParterInfo);
		flag = 1;
		message="添加成功";
		return SUCCESS;		
	}
	
	/**
	 * 将要进到查询页面
	 * @return
	 */
	public String prepareFindThirdParterInfo(){
		
		geCodeCompanyTypeList = geCodeService.findAllByGeCodeType("ThirdCompanyType");
		if(opertion!=null&&!opertion.equals("")){
			if(opertion.equals("fordbclick")){
				return "thirdService";//进入第三方产品维护界面中选择供应商维护
			}
			if("marketing".equals(opertion)){
				boolean isParentFlag = setIsParentFlagProperty(deptId);
				super.getRequest().setAttribute("isParentFlag", isParentFlag?  "yes" : "no");
				super.getRequest().setAttribute("getDepartName", (!isParentFlag)?   getDepartName(deptId) :  "no");
				return "marketing";//是从营销活动选 商品-->选 第三方公司 的渠道来的
			}
		}
		
		//正常 的第三方公司的查询页面
		return SUCCESS;
		
	}
	
	private boolean setIsParentFlagValue(){
		String isParentFlag  = super.getRequest().getParameter("isParentFlag");//是否是父结点
		if(isParentFlag!=null&&!"".equals(isParentFlag)&&isParentFlag.equals("yes")){//父结点
			return true;
		}else{
			return false;
		}
			
	}
	
	private boolean setnewDeptIdIsNullValue(){
		String newDeptId = geThirdParterInfo.getDeptID();
		if(newDeptId==null||"".equals(newDeptId)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 查询第三方合作伙伴信息
	 * @return
	 */
	public String findThirdParterInfo(){
		//common
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		if(opertion!=null&&!"".equals(opertion)){
			if(opertion.equals("fordbclick")){//又击域通过营销活动进来的
				boolean isParentFlag = setIsParentFlagValue();//是否为父结点
				boolean newDeptIdIsNull = setnewDeptIdIsNullValue(); //地区里是否值
				if(isParentFlag){//父结点
					if(newDeptIdIsNull){//为空
						geThirdParterInfo.setDeptID(deptId);
						geThirdParterInfo.setDeptName("isParentFlag");//借用字段告诉为父结点
						page = thirdParterService.findGeThirdParterInfo(geThirdParterInfo,pageNo,pageSize);
					}else{//可以选 一个地区  可以选一群地区
						page = thirdParterService.findGeThirdParterInfoByDeptManayBydeptId(geThirdParterInfo,pageNo,pageSize);
					}
				}else{//子结点
					if(geThirdParterInfo.getDeptID()==null&&geThirdParterInfo.getDeptID().equals("")){
						GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
						geThirdParterInfo.setBusinessArea(operator.getBusinessarea());
						//page = thirdParterService.findGeThirdParterInfo(geThirdParterInfo,pageNo,pageSize);//2012年6月28日10:32:49改
						Map map = (Map) getSession().getAttribute("permission");
						String authorityid = (String) map.get("ROLE_B_PTPI");//第三方最外层的权限名字
						if(authorityid!=null&&!authorityid.equals("")){//如果当前操作员没有第三方使用权限。机构从页面传来
							geThirdParterInfo.setDeptID(authorityid);
						}else{
							geThirdParterInfo.setDeptID(deptId);
						}
						page = thirdParterService.findGeThirdParterInfoByDeptManayBydeptId(geThirdParterInfo,pageNo,pageSize);
					}else{
						page = thirdParterService.findGeThirdParterInfoByDeptManayBydeptId(geThirdParterInfo,pageNo,pageSize);
					}
					
				}
			}
			
			if(opertion.equals("normal")){//正常渠道用来的  既第三方的
				if(geThirdParterInfo.getDeptID()==null||geThirdParterInfo.getDeptID().equals("")){//没有选择机构权限默认从session里读
//					page = getDefaultPermissionComcode(super.getSession());
					GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
					geThirdParterInfo.setBusinessArea(operator.getBusinessarea());
					Map map = (Map) getSession().getAttribute("permission");
					String authorityid = (String) map.get("ROLE_B_PTPI");//第三方最外层的权限名字
					geThirdParterInfo.setDeptID(authorityid);
					page = thirdParterService.findGeThirdParterInfoByDeptManayBydeptId(geThirdParterInfo,pageNo,pageSize);
				}else{//选择了机构权限
//					page = thirdParterService.findGeThirdParterInfo(geThirdParterInfo,pageNo,pageSize);
					page = thirdParterService.findGeThirdParterInfoByDeptManayBydeptId(geThirdParterInfo,pageNo,pageSize);
				}
			}
			
		}
		
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
		}
		geCodeThirdCompanyTypeMap = geCodeService.findAllCodeAndName("ThirdCompanyType");
		String forward = (opertion!=null&&!opertion.equals("")&&opertion.equals("fordbclick")) ? "dbfindThirdParterInfo" : SUCCESS;
		return forward;
	}
	
	/**通过默认的登录来找出该用户所拥有的机构**/
	private Page getDefaultPermissionComcode(HttpSession session){
		Map map = (Map) session.getAttribute("permission");
		String authorityid = (String) map.get("ROLE_B_PTPI");//第三方最外层的权限名字
				
		List<String> values = Arrays.asList(authorityid.split(","));
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(values.size() >= 1000){
			String sql1 = authorityid.substring(0,authorityid.indexOf(values.get(values.size()/2+1)));
			sql1 = sql1.substring(0,sql1.length()-1);
			String sql2 = authorityid.substring(authorityid.indexOf(values.get(values.size()/2+1)));
			String sql3 = " (DEPTID in ('" + sql1.replaceAll(",", "','") + "') or DEPTID in ('" + sql2.replaceAll(",", "','") + "')) ";
			queryRule.addSql(sql3);
		}else{
			queryRule.addIn("deptID", values);
		}	
		if(geThirdParterInfo!=null){//thirdPartterName
			if(geThirdParterInfo.getThirdParterName()!=null&&!"".equals(geThirdParterInfo.getThirdParterName())){
				queryRule.addLike("thirdParterName", "%"+geThirdParterInfo.getThirdParterName()+"%");
			}
			//Telephone
			if(geThirdParterInfo.getCompanyPhone()!=null&&!"".equals(geThirdParterInfo.getCompanyPhone())){
				queryRule.addEqual("companyPhone", geThirdParterInfo.getCompanyPhone());
			}
			//Address
			if(geThirdParterInfo.getAddress()!=null&&!"".equals(geThirdParterInfo.getAddress())){
				queryRule.addLike("address", "%"+geThirdParterInfo.getAddress()+"%");
			}
			//Email
			if(geThirdParterInfo.getEmail()!=null&&!"".equals(geThirdParterInfo.getEmail())){
				queryRule.addEqual("email", geThirdParterInfo.getEmail());
			}
			//URL
			if(geThirdParterInfo.getUrl()!=null&&!"".equals(geThirdParterInfo.getUrl())){
				queryRule.addLike("url", "%"+geThirdParterInfo.getUrl()+"%");
			}
			//公司类型
			if(geThirdParterInfo.getCompanyType()!=null&&!"".equals(geThirdParterInfo.getCompanyType())){
				queryRule.addEqual("companyType", geThirdParterInfo.getCompanyType());
			}
			queryRule.addDescOrder("createDate");
		}
		return thirdParterService.findGeThirdParterInfoByCondition(queryRule,pageNo,pageSize);
	}
	/**
	 * 准备更新第三方合作伙伴信息
	 * 将查询的信息带到页面上
	 * @return
	 * @throws Exception 
	 */
	public String prepareUpdateThirdParterInfo() throws Exception{
		geThirdParterInfo = thirdParterService.findGeThirdParterInfoByThirdParterID(geThirdParterInfo.getThirdParterID());
		geCodeSexList = getGecode("Sex");//
		geCodeIdentifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");	
		geCodeCompanyTypeList = geCodeService.findAllByGeCodeType("ThirdCompanyType");//
		geBusinessAreaTypeList = geCodeService.findAllByGeCodeType("BusinessArea");//新加第三方活动地域
		geThirdParterInfo.setDeptName(getDeptName(geThirdParterInfo.getDeptID()));//查出业务领域和活动地域
		String  businessAreaDept=null;
		String splitDeptName[] =getDeptName(geThirdParterInfo.getDeptID()).split("/");
		for (int i = 0; i < splitDeptName.length; i++) {
			if(splitDeptName[i].equals("集团")){
				businessAreaDept="1";
			}
			if(splitDeptName[i].equals("财险")){
				businessAreaDept="3";
			}
			if(splitDeptName[i].equals("寿险")){
				businessAreaDept="2";
			}
			if(splitDeptName[i].equals("养老险")){
				businessAreaDept="4";
			}
			
		}
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityids = (String) map.get("ROLE_B_PTPI");//拿到该功能的所有机构权限
		if(authorityids!=null&&!authorityids.equals("")){
			for (int i = geBusinessAreaTypeList.size() -1; i >=0; i--) {
				String baStr = geBusinessAreaTypeList.get(i).getId().getCodeCode();
				if(baStr.equals("1")){
					geBusinessAreaTypeList.remove(i);
				}else{
					if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
						continue;
					}else {
						geBusinessAreaTypeList.remove(i);

					}
				}
				
			}
		}else{
			authorityids = (String) map.get("ROLE_B_PTPI_U");//拿到该功能的所有机构权限
			for (int i = geBusinessAreaTypeList.size() -1; i >=0; i--) {
				String baStr = geBusinessAreaTypeList.get(i).getId().getCodeCode();
				if(baStr.equals("1")){
					geBusinessAreaTypeList.remove(i);
				}else{
					if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
						continue;
					}else {
						geBusinessAreaTypeList.remove(i);

					}
				}
				
			}
		}
	
		super.getRequest().setAttribute("businessAreaDept", businessAreaDept);
		System.out.println("9-----------------"+businessAreaDept);
		return SUCCESS;
	}
	/**
	 * 将geCode的方法封装了一下
	 * @param codeType
	 * @return
	 * @throws Exception
	 */
	private List<GeCode> getGecode(String codeType) throws Exception{
		List<GeCode> geCodes = new ArrayList<GeCode>();
		QueryRule queryRuleTemp  = QueryRule.getInstance();
		queryRuleTemp.addEqual("businessArea", "3");//业务领域(1-集团；2-寿险；3-财险；4-养老险；9-其他)
		queryRuleTemp.addEqual("validInd", "1");//0无效1有效
		queryRuleTemp.addEqual("codeType", codeType);
		GeCodeType geCodeTypeSex  = geCodeTypeService.findGeCodeType(queryRuleTemp);
		geCodes  =  removeInValidInd(geCodeTypeSex.getGeCodes());
		Collections.sort(geCodes, new OrderComparator());//排序
		return geCodes;
	}
	/**
	 *	将状态为ValidInd=0无效的去掉
	 * @return
	 */
	private List<GeCode> removeInValidInd(List<GeCode> geCodeList){
		if(geCodeList!=null&&geCodeList.size()>0){
			Iterator iterator = geCodeList.iterator();
			while(iterator.hasNext()){
				GeCode geCode =(GeCode)iterator.next();
				if(geCode.getValidInd()!=null&&geCode.getValidInd().equals("0")){
					iterator.remove();
				}
			}
		}
		return geCodeList;
	}
	/**
	 * 内容排序
	 *  
	 *
	 */
	class OrderComparator implements Comparator<GeCode>{
		@Override
		public int compare(GeCode o1, GeCode o2) {
			// TODO Auto-generated method stub
			return o1.getOrderNo().compareTo(o2.getOrderNo());
		}
	}
	/**
	 * 修改第三方合作伙伴及联系人信息
	 * @return
	 * @throws Exception 
	 */
	public String updateThirdParterInfoAndThirdParterContact() throws Exception{
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		geThirdParterInfo.setUpdateCode(operator.getOperatorid());
		List<GeThirdParterContact> list = geThirdParterInfo.getGeThirdParterContacts();
		for(GeThirdParterContact geThirdParterContact:list){
			geThirdParterContact.setGeThirdParterInfo(geThirdParterInfo);
		}
		//
		//String businessAreas = geThirdParterInfo.getBusinessArea();
		//businessAreas = businessAreas.replace(" ", "");
		//geThirdParterInfo.setBusinessArea(businessAreas);//
		//
		String businessArea =super.getRequest().getParameter("BusinessArea");
		String geThirdParterInfoCreateDate  = super.getRequest().getParameter("geThirdParterInfoCreateDate");  //创建日期
		geThirdParterInfo.setBusinessArea(businessArea);
		geThirdParterInfo.setCreateDate(getStringToDateToSecond(geThirdParterInfoCreateDate));
		thirdParterService.updateGeThirdParterInfoAndGeThirdParterContact(geThirdParterInfo);
		message = "修改成功";
		flag = 1;
		return SUCCESS;
	}
	
	//查询第三方供应商
	public String viewCom(){
		geThirdParterInfo =   thirdParterService.findGeThirdParterInfoByThirdParterID(geThirdParterInfo.getThirdParterID());
		//geThirdParterSerialNumberList = dealWithMap(geThirdParterInfo.getMap());
		try {
			geCodeSexList = getGecode("Sex");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		//geCodeIdentifyTypeList = getGecode("IdentifyType");//
		geCodeIdentifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");	
		geCodeCompanyTypeList = geCodeService.findAllByGeCodeType("ThirdCompanyType");//
		geBusinessAreaTypeList = geCodeService.findAllByGeCodeType("BusinessArea");//
		geThirdParterInfo.setDeptName(getDeptName(geThirdParterInfo.getDeptID()));
		return SUCCESS;
	}
	
	private String getDeptName(String deptId){
		GeDepartment geDepartment = geDepartmentService.findGeDepartmentByPK(deptId);
		String comName = "";
		String pId =deptId;
		for(int i=Integer.parseInt(geDepartment.getDeptlevel());i>0;i--){
			boolean isFlag ;
			if(i==Integer.parseInt(geDepartment.getDeptlevel())){
				comName = geDepartment.getDeptname();
				GeDepartment geDepartmentTempNew = geDepartmentService.findGeDepartmentByPK(pId);
				pId  = geDepartmentTempNew.getParentid();
			}else{
				GeDepartment geDepartmentTemp = geDepartmentService.findGeDepartmentByPK(pId);
				comName = geDepartmentTemp.getDeptname()+"/" + comName;
				pId  = geDepartmentTemp.getParentid();
			}
		}
		return comName;
	}
	/**
	 * 删除第三方合作伙伴
	 * @return
	 */
	public String deleteThirdParterInfoAndThridParterContact(){
		boolean deleteFlag = thirdParterService.deleteGeThirdParterInfo(geThirdParterInfo.getThirdParterID());
		flag = 1;
		if(deleteFlag){//
			message = "删除成功！";
		}else{
			message = "删除失败,请先删除该公司下的商品才能删除公司！";
		}
		return SUCCESS;
	}
	/**
	 * 删除第三方联系人
	 * @return
	 */
	public String deleteGeThirdParterContact(){
		String contactID = this.getRequest().getParameter("contactID");
		thirdParterService.deleteGeThirdParterContactByContactID(contactID);
		this.renderText("success");
		return NONE;
	}
	/**
	 * 准备增加第三方产品
	 * @return
	 */
	public String prepareAddGeThirdParterService(){
		geThirdParterInfoList =  thirdParterService.findGeThirdParterInfoAll();
		List<GeThirdParterService> parterServices = new ArrayList<GeThirdParterService>();
		if(opertion!=null&&!"".equals(opertion)){
			if(opertion.equals("select")){
				return "select";
			}
			if(opertion.equals("userMarketing")){//通过增值服务渠道来的
				return "userMarketing";
			}if(opertion.equals("updateUserMarketing")){
				String itemID = super.getRequest().getParameter("itemId");
				if(itemID !=null&&!itemID.equals("")){
				    String[] str = itemID.split(",");
				    
				    for(int i=0;i<str.length;i++){
				    	GeThirdParterService parterService = thirdParterService.findGeThirdParterServiceByItemID(str[i]);
				    	parterServices.add(parterService);
				    }
				}
				super.getRequest().setAttribute("geThirdParterServiceList", parterServices);
				return "updateUserMarketing" ;
			}
		}
		return SUCCESS;
	}
	//查询是否为你结点还是子结点
	private boolean setIsParentFlagProperty(String saleDept){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("parentid", saleDept);
		List<GeDepartment> geDepartmentList = geDepartmentService.findAllDepartments(queryRule);
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			return  true;
		}else{
			return false;
		}
	}
	//得到名称
	private String getDepartName(String deptId){
		GeDepartment geDepartment =  geDepartmentService.findGeDepartmentByPK(deptId);
		return geDepartment.getDeptname();
	}
	/**
	 * 获取项目物理路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public String getProjectLocalPath() {
		try {
			String path = PartyAction.class.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
				path += "/mis_war.ear/mis.war/upload/images/imageP/";
			}else if(path.lastIndexOf("/target") > -1){
				path = path.substring(0, path.lastIndexOf("/target"));
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
				
				path += "/src/main/webapp/upload/images/imageP/";
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 增加第三方产品
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String addGeThirdParterService() throws IOException, Exception{
		//
		/*
		if(smallPictureOne != null){
			String smallPictureOnefileName = geDirectory.getEid() + "_SmallPictureOne" + FileUtils.getFileNameExt(smallPictureOneFileName);
			File smallPictureOnePictureFile = new File(imageAbsolutePath + smallPictureOnefileName);
			File onlineSmallPictureOnePictureFile = new File(imageOnlineAbsolutePath + smallPictureOnefileName);
			FileUtils.write(FileUtils.readBytes(smallPictureOne), smallPictureOnePictureFile);
			FileUtils.write(FileUtils.readBytes(smallPictureOne), onlineSmallPictureOnePictureFile);
			geDirectory.setSmallPictureOne(imagePath + smallPictureOnefileName);
		}*/
		try {
		if(attrPicture!=null){
			//用时间戳作为文件名
//			String attrPictureName = getDateMinutesToString()+FileUtils.getFileNameExt(attrPictureFileName);
//			File attrPictureForMisFile = new File(imageAbsolutePath + attrPictureName);//往mis上传图片
//			File attrPictureNameForOnline = new File(imageOnlineAbsolutePath + attrPictureName);//往online上传图片
//			
//			FileUtils.write(FileUtils.readBytes(attrPicture), attrPictureForMisFile);
//			FileUtils.write(FileUtils.readBytes(attrPicture), attrPictureNameForOnline);
//			geThirdParterService.setPictureUrl(imagePath+attrPictureName);
			
			geThirdParterService.setAttrPictureFileName(attrPictureFileName);//文件的名字
			String imageAbsolutePathTemp = getRequest().getRealPath(java.io.File.separator+"upload"+java.io.File.separator+"images"+java.io.File.separator+"imageP");
			System.out.println("*******************上传MIS路径为*********************"+imageAbsolutePathTemp);
			geThirdParterService.setImageAbsolutePath(imageAbsolutePathTemp);//mis的绝对路径
			//geThirdParterService.setImageOnlineAbsolutePath(imageOnlineAbsolutePath);//online的绝对路径
			geThirdParterService.setAttrPicture(attrPicture);
			geThirdParterService.setImagePath(imagePath);
			thirdParterService.addGeThirdParterService(geThirdParterService);
			message="添加成功";
			flag = 1;
		}
		}
		catch (Exception e) {
			message="添加失败";
			flag = 1;
		}
		
		
		/*
		try {
			geThirdParterService.setItemPicture(FileUtils.readBytes(attrPicture));
			thirdParterService.addGeThirdParterService(geThirdParterService);
			message="添加成功";
			flag = 1;
		} catch (Exception e) {
			e.printStackTrace();
			message = "添加失败！";
		} 
		*/  
		return SUCCESS;
	}
	/**
	 * 查询第三方产品
	 * 这里面分两个分支，一个是第三方产品维护时需要使用这个方法，一个是营销活动查询商品时也使用这个方法 
	 * @return
	 */
	public String findGeThirdParterService(){
		//common
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 20;
		}
		//会走分支的
		if(opertion!=null&&!opertion.equals("")&&opertion.equals("marketing")){//来自营销活动的去选 商品的渠道
			geThirdParterService.setItemName(geThirdParterService.getItemName());
			geThirdParterService.getGeThirdParterInfo().setThirdParterID(geThirdParterService.getGeThirdParterInfo().getThirdParterID());
			long totalCount = 0l;
			long totalPage = 0l;
			
			GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
			geThirdParterService.getGeThirdParterInfo().setBusinessArea(operator.getBusinessarea());
			totalCount = thirdParterService.findGeThirdParterServiceByDeptIdCount(deptId,geThirdParterService);
			totalPage = totalCount % pageSize != 0 ? totalCount / pageSize + 1L : totalCount / pageSize;
			List<GeThirdParterService> geThirdParterServiceList= thirdParterService.findGeThirdParterServiceByDeptId(deptId ,pageNo, pageSize,geThirdParterService);
			//
			super.getRequest().setAttribute("geThirdParterServiceList", geThirdParterServiceList);
			super.getRequest().setAttribute("totalCount", totalCount);
			super.getRequest().setAttribute("totalPage", totalPage);
			super.getRequest().setAttribute("pageNo", pageNo);
			super.getRequest().setAttribute("pageSize", pageSize);
			
		}else{//正常的第三方商品维护走的渠道
			
			if(geThirdParterService.getGeThirdParterInfo()!=null
					&&geThirdParterService.getGeThirdParterInfo().getThirdParterID()!=null
					&&!(geThirdParterService.getGeThirdParterInfo().getThirdParterID().equals(""))){//即存在机构
					
					page = thirdParterService.findGeThirdParterService(geThirdParterService, pageNo, pageSize);
					
			}else{//不存在机构那么按默认的来
				GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
				geThirdParterService.getGeThirdParterInfo().setBusinessArea(operator.getBusinessarea());
				//先得一个公司列表的ID,然后再用这个ID来查询
				page = thirdParterService.findGeThirdParterServiceByDefaultPermession(super.getSession(),geThirdParterService, pageNo, pageSize);
			}
			
			if(page!=null){
				super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
				super.getRequest().setAttribute("pageSize", page.getPageSize());
				super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
				super.getRequest().setAttribute("totalCount", page.getTotalCount());
			}
		}
		//以下为common部分
		return (opertion!=null&&!opertion.equals("")&&opertion.equals("marketing")) ? "marketing" : SUCCESS;
	}
	/**
	 * 查询第三方产品不分页
	 * @return
	 */
	public String findGeThirdParterServiceNoPage(){
		List<GeThirdParterService> geThirdParterServiceList = thirdParterService.findGeThirdParterService(geThirdParterService);
		List<Map> backList = new ArrayList<Map>();
		if(geThirdParterServiceList!=null&&geThirdParterServiceList.size()>0){
			for(GeThirdParterService geThirdParterServiceTemp:geThirdParterServiceList){
				Map<String,String> map = new HashMap<String, String>();
				map.put("itemID", geThirdParterServiceTemp.getItemID());
				map.put("itemName", geThirdParterServiceTemp.getItemName());
				backList.add(map);
			}
		}
		
		JSONObject backJson = new JSONObject();
		backJson.put("backList", backList);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;

	}
	/**
	 * 准备修改第三方服务产品
	 * 将信息查出显示在页面上
	 * @return
	 * @throws IOException 
	 */
	public String prepareUpdateGeThirdParterService() throws IOException {
		//得到服务产品的id
		//然后去通过主键去查
		geThirdParterInfoList =  thirdParterService.findGeThirdParterInfoAll();
		String itemID = geThirdParterService.getItemID();
		geThirdParterService = thirdParterService.findGeThirdParterServiceByItemID(itemID);
		
		if(opertion!=null&&opertion.equals("itemPicture")){//用于图片的显示
			//设置头信息
			HttpServletResponse response = getResponse();
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			//
			byte[] pictureByte = geThirdParterService.getItemPicture();//byte[]
			if(pictureByte==null){
				return NONE;
			}
			InputStream buffin = new ByteArrayInputStream(pictureByte, 0,pictureByte.length);//将byte[]变为输入流
			// Prepare streams.
		    BufferedInputStream input = null;
		    BufferedOutputStream output = null;
		    try {
		        // Open streams.
		        input = new BufferedInputStream(buffin, pictureByte.length);
		        output = new BufferedOutputStream(response.getOutputStream(),
		        		pictureByte.length);
		        // Write file contents to response.
		        byte[] buffer = new byte[pictureByte.length];
		        int length;
		        while ((length = input.read(buffer)) > 0) {
		            output.write(buffer, 0, length);
		        }
		    } finally {
		        // Gently close streams.
		        output.close();
		        input.close();
		    }
		    return NONE;
		}
		return SUCCESS;
	}
	/**
	 * 修改第三方服务产品
	 * @return
	 */
	public String updateGeThirdParterService(){
		String thirdParterID = geThirdParterService.getGeThirdParterInfo().getThirdParterID();
		GeThirdParterInfo geThirdParterInfoTemp = thirdParterService.findGeThirdParterInfoByThirdParterID(thirdParterID);
		geThirdParterService.setGeThirdParterInfo(geThirdParterInfoTemp);
		try {
			//geThirdParterService.setItemPicture(FileUtils.readBytes(attrPicture));
			geThirdParterService.setItemPicture(null);
			if(attrPicture!=null){
				
				geThirdParterService.setAttrPictureFileName(attrPictureFileName);
				geThirdParterService.setAttrPicture(attrPicture);
				geThirdParterService.setImagePath(imagePath);
				String imageAbsolutePathTemp = getRequest().getRealPath(java.io.File.separator+"upload"+java.io.File.separator+"images"+java.io.File.separator+"imageP");
				geThirdParterService.setImageAbsolutePath(imageAbsolutePathTemp);
				//geThirdParterService.setImageOnlineAbsolutePath(imageOnlineAbsolutePath);
			}
			thirdParterService.updateGeThirdParterService(geThirdParterService);
			flag = 1;
			message = "修改成功";
			
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		flag = 1;
		return SUCCESS;
	}
	//查询第三方产品
	public String view(){
		//
		geThirdParterService =   thirdParterService.findGeThirdParterServiceByItemIDForView(geThirdParterService.getItemID());
		geThirdParterSerialNumberList = dealWithMap(geThirdParterService.getMap());
		return SUCCESS;
	}
	private List<GeThirdParterSerialNumber> dealWithMap(java.util.Map<String,List<GeThirdParterSerialNumber>> map){
		//深圳 买出多少
		List<GeThirdParterSerialNumber> geThirdParterSerialNumberList= new ArrayList<GeThirdParterSerialNumber>();
		if(map!=null&&map.size()>0){
			for (Map.Entry<String,List<GeThirdParterSerialNumber>> entry :  map.entrySet()) {
				GeThirdParterSerialNumber geThirdParterSerialNumber = new GeThirdParterSerialNumber();
				geThirdParterSerialNumber.setProposalAreaName(entry.getValue().get(0).getProposalAreaName());//投保地区
				geThirdParterSerialNumber.setCount(""+(entry.getValue().size()));//数量
				geThirdParterSerialNumberList.add(geThirdParterSerialNumber);
			}
		}
		return geThirdParterSerialNumberList;
	}
	
	private void setQueryCondition(String opertion,String thirdServiceDeptId){
		if(opertion!=null&&!opertion.equals("")&&opertion.equals("marketing")){//从营销活动过来的
			//业务领域(1-集团；2-寿险；3-财险；4-养老险；9-其他)
			if(thirdServiceDeptId.equals("1")){
				//没有任何改变
			}else if(thirdServiceDeptId.equals("2")){//寿险
				
			}else if(thirdServiceDeptId.equals("3")){//财险
				
			}else if(thirdServiceDeptId.equals("4")){//财险
				
			}else{
				
				//具体的城市下的所有的公司
				//先查询出这个城市的多个公司
				//再查询 每个 公司对应的产品
				
			}
		}
	}
	/**
	 * 删除第三方服务产品
	 * @return
	 */
	public String deleteGeThirdPartterService(){
		String itemID = geThirdParterService.getItemID();
		//以下这几行代码以前是用于批量使用的
		//java.util.Map<String, List<GeThirdParterService>> map= thirdParterService.deleteGeThirdPartterServiceByItemID(itemID);
//		flag = 1;
//		message = dealWithGeThirdParterServiceList(map);
		flag = 1;
		boolean deleteFlag = thirdParterService.deleteGeThirdPartterServiceByItemID(itemID);
		message =   deleteFlag==true ? "删除成功" : "该商品用于营销活动中，暂未不能删除";
		return SUCCESS;
	}
	private String dealWithGeThirdParterServiceList(java.util.Map<String, List<GeThirdParterService>> map){
		String result = "";
		if(map!=null&&map.size()>0){
			if(map.get("delete")!=null){//已删除的
				if(map.get("notDelete")!=null){//即产生流水号的
					List<GeThirdParterService> geThirdParterServiceList = map.get("notDelete");
					String itemName = "";
					for (GeThirdParterService geThirdParterServiceTemp : geThirdParterServiceList) {
						itemName+=geThirdParterServiceTemp.getItemName()+" ";
					}
					//哪个哪个不能删除,已用于增值活动   ,
					result = itemName+"已用于增值活动不能删除,其它商品已删除";
				}else{
					result = "删除成功！";
				}
			}else{//没有要删除的东西
				if(map.get("notDelete")!=null){
					List<GeThirdParterService> geThirdParterServiceList = map.get("notDelete");
					String itemName = "";
					for (GeThirdParterService geThirdParterServiceTemp : geThirdParterServiceList) {
						itemName+=geThirdParterServiceTemp.getItemName()+" ";
					}
					//哪个哪个不能删除,已用于增值活动   ,
					result = itemName+"已用于增值活动不能删除";
				}
			}
		}
		return result;
	}
	/**
	 * 查询分页的商品管理
	 * @return
	 */
	public String findGeThirdParterSerialNumber(){
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 20;
		}
		//是滞是默认的
		boolean isDefaultPermission  =  (geThirdParterSerialNumber.getProposalArea()!=null&&!geThirdParterSerialNumber.getProposalArea().equals(""))? false : true;
		page = thirdParterService.findGeThirdParterSerialNumber(isDefaultPermission,super.getSession(),geThirdParterSerialNumber, pageNo, pageSize);
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
		}
		return SUCCESS;
	}
	//查询单项赠送商品
	public String viewNum(){
		geThirdParterSerialNumber =   thirdParterService.findGeThirdParterSerialNumberByNo(geThirdParterSerialNumber.getSearialNo());
		GeDepartment geDepartment =  geDepartmentService.findGeDepartmentByPK(geThirdParterSerialNumber.getProposalArea());
		geThirdParterSerialNumber.setProposalAreaName(geDepartment.getDeptname());//投保地区
		return SUCCESS;
	}
	public String updateGeThirdParterSerialNumberValidInd(){
		thirdParterService.updateGeThirdParterSerialNumberValidInd(searialNo);
		flag=1;
		message="商品已赠送";
		return SUCCESS;
	}
	
	public String updateGeThirdParterSerialNumberInValidInd(){
		thirdParterService.updateGeThirdParterSerialNumberInValidInd(searialNo);
		flag=1;
		message="商品已回退";
		return SUCCESS;
	}
	
	private String getDateMinutesToString(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpleDateFormat.format(date);
	}
	/**
	 * 查询省
	 * @return
	 */
	public String selectProvince(){
		/**
		 * 1.businessID  是什么就是什么
		 * 2.parentId    集团不用调用你    寿财养老时parentID=1
		 * 3.寿险具有的       那个人本人具有的机构权限
		 *///ROLE_B_PTPI_U
		Map map = (Map) super.getSession().getAttribute("permission");//省市
		String authorityid = (String) map.get("ROLE_B_PTPI_I");
		String thirdParter=getRequest().getParameter("thirdParter");
		String parentId = getRequest().getParameter("BusinessArea");//从页面上取的
		List<String[]> geDepartmentList = new ArrayList<String[]>();//getAllChildAuthDepart();
		List<String[]> geDepartmentListup = new ArrayList<String[]>();//getAllChildAuthDepart();
		//
		if(authorityid!=null&&!authorityid.equals("")){
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);
		}else{
			authorityid = (String) map.get("ROLE_B_PTPI_U");
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);
		}
		String deptBusinessArea="";
		if(thirdParter!=null&&!thirdParter.equals("")&&!thirdParter.equals("null")){
			geThirdParterInfo = thirdParterService.findGeThirdParterInfoByThirdParterID(thirdParter);
			authorityid=geThirdParterInfo.getDeptID();
			if(authorityid.equals("1")||authorityid.equals("2")||authorityid.equals("3")||authorityid.equals("4")){
				deptBusinessArea=authorityid;
			}
			geDepartmentListup = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);
		}
		//getDeptName
		
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentList);
		backJson.put("backdataup", geDepartmentListup);
		backJson.put("deptBusinessArea", deptBusinessArea);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}

   /**
	 * 查询城市
	 * @return
	 */
	public String selectCity(){
		String authorityidDeptId="";
		Map map = (Map) super.getSession().getAttribute("permission");//省市
		String authorityid = (String) map.get("ROLE_B_PTPI_I");
		String parentId = getRequest().getParameter("ProvinceId");//从页面上取的
		String thirdParter=getRequest().getParameter("thirdParterCity");
		List<String[]> geDepartmentList = new ArrayList<String[]>();//getAllChildAuthDepart();
		if(thirdParter!=null&&!thirdParter.equals("")&&!thirdParter.equals("null")){//判断是否为修改页面进来的
			geThirdParterInfo = thirdParterService.findGeThirdParterInfoByThirdParterID(thirdParter);
			authorityidDeptId=geThirdParterInfo.getDeptID();
		}
		if(authorityid!=null&&!authorityid.equals("")){//取操作员新建权限
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);
		}else{                                         //未找到取修改权限
			authorityid = (String) map.get("ROLE_B_PTPI_U");
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);
		}
		
		
		boolean municipalityFlag = false;
		if(geDepartmentList.size()==0){//直辖市 北京天津等
			municipalityFlag = true;
			GeDepartment  geDepartment= geDepartmentService.findGeDepartmentByPK(parentId);
			geDepartmentList.add(new String[]{parentId,geDepartment.getDeptname()});
		}
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentList);
		backJson.put("municipalityFlag",municipalityFlag);
		backJson.put("authorityidDeptId",authorityidDeptId);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}
	/**测试*/
	public String testSerialNo(){
		//String riskCode = bizCommonService.getSerialNo("02", new Date(),GeRisk.class);
		String riskCode = bizCommonService.takeSerialNo("02", new Date(),GeRisk.class);
		GeRisk geRisk = new GeRisk();
		geRisk.setRiskCode(riskCode);
		geRisk.setValidInd("0");
		bizCommonService.addGeRisk(geRisk);
		return "success";
	}
	private Date getStringToDateToSecond(String geThirdParterInfoCreateDate){
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(geThirdParterInfoCreateDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//get and set method
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public GeThirdParterInfo getGeThirdParterInfo() {
		return geThirdParterInfo;
	}
	public void setGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo) {
		this.geThirdParterInfo = geThirdParterInfo;
	}
	public void setThirdParterService(ThirdParterService thirdParterService) {
		this.thirdParterService = thirdParterService;
	}
	public List<GeThirdParterInfo> getGeThirdParterInfoList() {
		return geThirdParterInfoList;
	}
	public void setGeThirdParterInfoList(
			List<GeThirdParterInfo> geThirdParterInfoList) {
		this.geThirdParterInfoList = geThirdParterInfoList;
	}
	public GeThirdParterService getGeThirdParterService() {
		return geThirdParterService;
	}
	public void setGeThirdParterService(GeThirdParterService geThirdParterService) {
		this.geThirdParterService = geThirdParterService;
	}
	public String getOpertion() {
		return opertion;
	}
	public void setOpertion(String opertion) {
		this.opertion = opertion;
	}
	public File getAttrPicture() {
		return attrPicture;
	}
	public void setAttrPicture(File attrPicture) {
		this.attrPicture = attrPicture;
	}
	public GeCodeTypeService getGeCodeTypeService() {
		return geCodeTypeService;
	}
	public void setGeCodeTypeService(GeCodeTypeService geCodeTypeService) {
		this.geCodeTypeService = geCodeTypeService;
	}
	public List<GeCode> getGeCodeSexList() {
		return geCodeSexList;
	}
	public void setGeCodeSexList(List<GeCode> geCodeSexList) {
		this.geCodeSexList = geCodeSexList;
	}
	public List<GeCode> getGeCodeIdentifyTypeList() {
		return geCodeIdentifyTypeList;
	}
	public void setGeCodeIdentifyTypeList(List<GeCode> geCodeIdentifyTypeList) {
		this.geCodeIdentifyTypeList = geCodeIdentifyTypeList;
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
	public String getNameCount() {
		return nameCount;
	}
	public void setNameCount(String nameCount) {
		this.nameCount = nameCount;
	}
	public String getAttrPictureFileName() {
		return attrPictureFileName;
	}
	public void setAttrPictureFileName(String attrPictureFileName) {
		this.attrPictureFileName = attrPictureFileName;
	}
	public List<GeCode> getGeCodeCompanyTypeList() {
		return geCodeCompanyTypeList;
	}
	public void setGeCodeCompanyTypeList(List<GeCode> geCodeCompanyTypeList) {
		this.geCodeCompanyTypeList = geCodeCompanyTypeList;
	}
	public GeCodeService getGeCodeService() {
		return geCodeService;
	}
	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}
	public String getSearialNo() {
		return searialNo;
	}
	public void setSearialNo(String searialNo) {
		this.searialNo = searialNo;
	}
	public GeThirdParterSerialNumber getGeThirdParterSerialNumber() {
		return geThirdParterSerialNumber;
	}
	public void setGeThirdParterSerialNumber(
			GeThirdParterSerialNumber geThirdParterSerialNumber) {
		this.geThirdParterSerialNumber = geThirdParterSerialNumber;
	}
	public BizCommonService getBizCommonService() {
		return bizCommonService;
	}
	public void setBizCommonService(BizCommonService bizCommonService) {
		this.bizCommonService = bizCommonService;
	}
	public List<GeCode> getGeBusinessAreaTypeList() {
		return geBusinessAreaTypeList;
	}
	public void setGeBusinessAreaTypeList(List<GeCode> geBusinessAreaTypeList) {
		this.geBusinessAreaTypeList = geBusinessAreaTypeList;
	}
	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}
	public List<GeThirdParterSerialNumber> getGeThirdParterSerialNumberList() {
		return geThirdParterSerialNumberList;
	}
	public void setGeThirdParterSerialNumberList(
			List<GeThirdParterSerialNumber> geThirdParterSerialNumberList) {
		this.geThirdParterSerialNumberList = geThirdParterSerialNumberList;
	}
	public Map<String, String> getGeCodeThirdCompanyTypeMap() {
		return geCodeThirdCompanyTypeMap;
	}
	public void setGeCodeThirdCompanyTypeMap(
			Map<String, String> geCodeThirdCompanyTypeMap) {
		this.geCodeThirdCompanyTypeMap = geCodeThirdCompanyTypeMap;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public static void main(String[] args) {
//		short s1 = 1; s1 =  (short) (s1 + (short)1);
//		System.out.println(s1);
		Integer i1 = 400;
		Integer i2 = 400;
		Integer i3 = 0;
		Integer i4 = new Integer(400);
		Integer i5 = new Integer(400);
		Integer i6 = new Integer(0);
		System.out.println("i1=i2\t" + (i1 == i2));
		System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));
		System.out.println("i4=i5\t" + (i4 == i5));
		System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));
		
		String s ="abc";
		String s1 = new String("abc");
		System.out.println(s==s1.intern());

		
	}
}
