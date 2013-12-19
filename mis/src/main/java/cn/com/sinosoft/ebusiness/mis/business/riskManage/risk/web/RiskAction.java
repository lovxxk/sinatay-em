package cn.com.sinosoft.ebusiness.mis.business.riskManage.risk.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

public class RiskAction extends Struts2Action{
	
	private static final long serialVersionUID = 1L;
	private BizCommonService bizCommonService;//服务接口
	private GeRisk geRisk;//实体类
	private Page page;
	private QueryRule queryRule;
	private String flag;//标志
	private String message;//信息
	private GeCodeService geCodeService;//数据字典服务类
	private List<GeCode> bussList;//业务领域列表
	private Map<String,String> businessAreaMap;
	private String riskCode;
	private String areaName;
	private final static String BusinessArea = "BusinessArea";
	
	
	/*分别给其对应的set 和 get 方法*/
	
	public Map<String, String> getBusinessAreaMap() {
		return businessAreaMap;
	}

	public void setBusinessAreaMap(Map<String, String> businessAreaMap) {
		this.businessAreaMap = businessAreaMap;
	}
	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public QueryRule getQueryRule() {
		return queryRule;
	}

	public void setQueryRule(QueryRule queryRule) {
		this.queryRule = queryRule;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public BizCommonService getBizCommonService() {
		return bizCommonService;
	}

	public void setBizCommonService(BizCommonService bizCommonService) {
		this.bizCommonService = bizCommonService;
	}

	public GeRisk getGeRisk() {
		return geRisk;
	}

	public void setGeRisk(GeRisk geRisk) {
		this.geRisk = geRisk;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public List<GeCode> getBussList() {
		return bussList;
	}

	public void setBussList(List<GeCode> bussList) {
		this.bussList = bussList;
	}
	/* 查询数据字典的业务领域类型，增加险种的准备工作*/
	public String findBusinessArea() throws Exception{	
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		return SUCCESS;
	}
	/* 查询数据字典的业务领域类型，主要用于更新页面的属性加载*/
	public String findBusinessArea2() throws Exception{	
		
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		return SUCCESS;
	}
	/*增加险种方法*/
	public String addGeRisk(){		
		
		//添加属性默认值,暂时不用管,先设为空字符串
		geRisk.setFlag("");
		geRisk.setInsuAccFlag("1");
		geRisk.setValidInd("1");
		geRisk.setCreateDate(new Date());
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		geRisk.setOperatorID(operator.getOperatorid());
			
		try{
			flag =bizCommonService.saveGeRisk(geRisk);
			if("1".equals(flag)){
				flag = "1";
				message = "险种新建成功";
			}else if("2".equals(flag)){
				flag = "0";
				message = "已存在险种代码，险种代码不能重复";
			}
		}catch(Exception e){
			flag = "0";
			message = "险种建失败";
		}
	
		return SUCCESS;
	}

	//此方法暂时没用
//	public String findGeRiskList1(){
//		String riskCode=geRisk.getRiskCode();
//		String riskCName=geRisk.getRiskCName();
//		String businessArea=geRisk.getBusinessArea();
//		System.out.println("查询条件:"+riskCode+"--"+riskCName);
//		if (pageNo == 0) {
//			pageNo = 1;
//		}
//		if (pageSize == 0) {
//			pageSize = 10;
//		}
//		//判断查询条件
//		
//		if ("".equals(riskCName)&&("".equals(riskCode))&&businessArea!=null){
//			System.out.println("无条件查询");
//			page = bizCommonService.findGeRiskList(queryRule, pageNo, pageSize);
//		}else {
//			System.out.println("根据条件查询："+riskCode);
//		//	List<GeRisk> page=bizCommonService.findGeRiskListBy(String businessArea,riskCode, riskCName);
//		}	
//		return SUCCESS;
//	}
	/*查询符合条件的险种列表*/
	public String findGeRiskList(){
		
		pageNo = (pageNo == 0 ? 1 : pageNo);
		pageSize = (pageSize == 0 ? 20 : pageSize);

		page = bizCommonService.getGeCardProductList(geRisk, pageNo, pageSize);
		//获取Map集合，主要为了显示业务领域代码所对应的名字
		businessAreaMap  = geCodeService.findAllCodeAndName("BusinessArea");
		
		return SUCCESS;
		
	}
	/*根据code查询一种险种*/
	public String viewGeRisk(){
		geRisk=bizCommonService.findGeRiskByCode(geRisk.getRiskCode());
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		String stres="";
		for (Iterator<GeCode> iterator = bussList.iterator(); iterator.hasNext();) {
			GeCode geCode = (GeCode) iterator.next();
			String id=geCode.getId().getCodeCode();
			String cName=geCode.getCodeCName();
			stres=stres+id+","+cName+";";	
		}
		String[] str=stres.split(";");
		for (int i = 0; i < str.length; i++) {
			if(str[i].contains(geRisk.getBusinessArea())){
				areaName=str[i].split(",")[1];
			}
		}
		
		return SUCCESS;
	}
	/*编辑险种*/
	
	public String prepareUpdateGeRisk(){
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		
		geRisk=bizCommonService.findGeRiskByCode(geRisk.getRiskCode());
		
		return SUCCESS;
	}
	
	public String updateGeRisk(){	
		geRisk.setFlag("");
		geRisk.setInsuAccFlag("1");
		geRisk.setValidInd("1");
		geRisk.setCreateDate(new Date());
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		geRisk.setOperatorID(operator.getOperatorid());
		try{
			flag=bizCommonService.updateGeRisk(geRisk);
			message = "险种更新成功";
		}catch(Exception e){
			flag = "0";
			message = "险种更新失败";
		}
		return SUCCESS;
	}
	public String deleteGeRiskList(){	
		try{
			List<GeKind> geKindList=bizCommonService.findKindByRiskCode(riskCode);
			if(geKindList!=null&&geKindList.size()>0){
				flag = "0";
				message = "对不起，此险种不能被删除！";
			}else{
				bizCommonService.deleteGeRiskListById(riskCode);
				   flag = "1";
				   message = "险种删除成功";
				}
			}catch(Exception e){
		       flag = "0";
			   message = "险种删除失败";
			}
			return SUCCESS;
			
	}
	
	
}
