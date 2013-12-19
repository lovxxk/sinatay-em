package cn.com.sinosoft.ebusiness.mis.business.customerManage.blacklist.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeBlackList;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService;

public class GeBlackListAction extends Struts2Action {
	private static final long serialVersionUID = -3517814498044475227L;
	private GeBlackListService geBlackListService;//服务接口
	private GeBlackList geBlackList;//实体类
	private String message;//返回页面的提示信息
	private int flag;//页面提示选择器
	
	private GeCodeService geCodeService;/**数据字典服务类*/
	private List<GeCode> idTypeList;/**证件类型列表*/
	private List<GeCode> sexList;/**性别类型列表*/
	private List<GeCode> bussList;/**业务领域列表*/
	
	private final  static String IdentifyType = "IdentifyType";
	public List<GeCode> getBussList() {
		return bussList;
	}

	public void setBussList(List<GeCode> bussList) {
		this.bussList = bussList;
	}

	private final  static String Sex = "Sex";
	private final  static String BusinessArea = "BusinessArea";
	
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

	public GeBlackListService getGeBlackListService() {
		return geBlackListService;
	}

	public void setGeBlackListService(GeBlackListService geBlackListService) {
		this.geBlackListService = geBlackListService;
	}

	public GeBlackList getGeBlackList() {
		return geBlackList;
	}

	public void setGeBlackList(GeBlackList geBlackList) {
		this.geBlackList = geBlackList;
	}
	
	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public List<GeCode> getIdTypeList() {
		return idTypeList;
	}

	public void setIdTypeList(List<GeCode> idTypeList) {
		this.idTypeList = idTypeList;
	}

	public List<GeCode> getSexList() {
		return sexList;
	}

	public void setSexList(List<GeCode> sexList) {
		this.sexList = sexList;
	}
	
	/**
	 * 进入新建页面
	 * @return
	 */
	@LocusTrace(setCode="GeBlackListAction_frmCreate")
	public String frmCreate() throws Exception{
		/**查询数据字典的证件类型*/
		idTypeList = geCodeService.findAllByGeCodeTypeAndValidInd(IdentifyType,"1");
		/**查询数据字典的性别类型*/
		sexList = geCodeService.findAllByGeCodeTypeAndValidInd(Sex,"1");
		/**查询数据字典的业务领域类型*/
		bussList = geCodeService.findAllByGeCodeTypeAndValidInd(BusinessArea,"1");
		return SUCCESS;
	}
	
	/**
	 * 进入到查询条件页面
	 * @return
	 */
	@LocusTrace(setCode="GeBlackListAction_frmSearch")
	public String frmSearch() throws Exception{
		/**查询数据字典的证件类型*/
		idTypeList = geCodeService.findAllByGeCodeTypeAndValidInd(IdentifyType, "1");
		/**查询数据字典的性别类型*/
		//sexList = geCodeService.findAllByGeCodeType(Sex);
		return SUCCESS;
	}

	/**根据页面接收到的数据进行查询操作，得到分页信息*/
	@LocusTrace(setCode="GeBlackListAction_queryBlackListByDisPage")
	public String queryBlackListByDisPage() throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		if(!StringUtils.isBlank(geBlackList.getUserName()))	queryRule.addLike("userName", "%"+ geBlackList.getUserName().trim()+"%");
		if(!StringUtils.isBlank(geBlackList.getIdentifyType()))	queryRule.addEqual("identifyType", geBlackList.getIdentifyType());
		if(!StringUtils.isBlank(geBlackList.getIdentifyNumber()))	queryRule.addLike("identifyNumber","%" +  geBlackList.getIdentifyNumber().trim()+"%");
		Page page = geBlackListService.findList(queryRule, pageNo, pageSize);
		
		/**查询数据字典的证件类型*/
		idTypeList = geCodeService.findAllByGeCodeType(IdentifyType);
		/**查询数据字典的性别类型*/
		sexList = geCodeService.findAllByGeCodeType(Sex);
		/**查询数据字典的业务领域类型*/
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		
		super.getRequest().setAttribute("page", page);
		return SUCCESS;
	}
	
	/**根据页面接收到的idStr(由一个或多个id组成，之间用','分隔)参数，进行相应的删除操作*/
	@LocusTrace(setCode="GeBlackListAction_deleteGeBlackList")
	public String deleteGeBlackList(){
		String idStr = super.getRequest().getParameter("idStr");
		String[] ids = idStr.split(",");
		try {
			if (ids.length==1) {
				geBlackListService.deleteById(idStr);
			}else{
				List<GeBlackList> list = new ArrayList<GeBlackList>();
				for (int i = 0; i < ids.length; i++) {
					idStr = ids[i];
					GeBlackList geBlackList2 = new GeBlackList();
					geBlackList2.setId(idStr);
					list.add(geBlackList2);
				}
				geBlackListService.deleteByList(list);
			}
			flag = 1;
			message = "删除成功！";
		} catch (Exception e) {
			e.printStackTrace();
			message = "删除失败！";
		}
		return SUCCESS;
	}
	
	/**根据页面接收到的主键进行查询操作，得到相应的实体类信息，返回详细页面*/
	@LocusTrace(setCode="GeBlackListAction_queryGeBlackListForShow")
	public String queryGeBlackListForShow(){
		try {
			geBlackList = geBlackListService.findById(geBlackList.getId());
			
			HttpServletRequest request = super.getRequest();
			if(StringUtils.isNotBlank(geBlackList.getIdentifyType())){
				request.setAttribute("identifyType",geCodeService.findCodeCName(geBlackList.getIdentifyType(), IdentifyType));
			}
			if(StringUtils.isNotBlank(geBlackList.getSex())){
				request.setAttribute("sex",geCodeService.findCodeCName(geBlackList.getSex(), Sex));
			}
			if(StringUtils.isNotBlank(geBlackList.getBusinessArea())){
				request.setAttribute("businessArea",geCodeService.findCodeCName(geBlackList.getBusinessArea(), BusinessArea));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**根据页面接收到的主键进行查询操作，得到相应的实体类信息，返回修改页面*/
	@LocusTrace(setCode="GeBlackListAction_queryGeBlackListForUpdate")
	public String queryGeBlackListForUpdate(){
		try {
			geBlackList = geBlackListService.findById(geBlackList.getId());
			
			/**查询数据字典的证件类型*/
			idTypeList = geCodeService.findAllByGeCodeType(IdentifyType);
			/**查询数据字典的性别类型*/
			sexList = geCodeService.findAllByGeCodeType(Sex);
			/**查询数据字典的业务领域类型*/
			bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**根据页面接收到的数据进行修改操作*/
	@LocusTrace(setCode="GeBlackListAction_updateGeBlackList")
	public String updateGeBlackList(){
		try {
			/**5要素判断数据库是否有相同记录*/
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geBlackList.getUserName())) queryRule.addEqual("userName", geBlackList.getUserName());
			else queryRule.addIsNull("userName");
			if(!StringUtils.isBlank(geBlackList.getSex())) queryRule.addEqual("sex", geBlackList.getSex());
			else queryRule.addIsNull("sex");
			if(!StringUtils.isBlank(geBlackList.getIdentifyType()))queryRule.addEqual("identifyType", geBlackList.getIdentifyType());
			else queryRule.addIsNull("identifyType");
			if(!StringUtils.isBlank(geBlackList.getIdentifyNumber()))queryRule.addEqual("identifyNumber", geBlackList.getIdentifyNumber());
			else queryRule.addIsNull("identifyNumber");
			if(geBlackList.getBirthDay()!=null) queryRule.addEqual("birthDay", geBlackList.getBirthDay());
			else queryRule.addIsNull("birthDay");
			GeBlackList geBlackList2 = geBlackListService.findByRule(queryRule);
			
			if (geBlackList2 == null ) {//修改后的记录在数据库中没有重复
				geBlackListService.modify(geBlackList);
				flag = 1;
				message = "修改成功";
			}else if(geBlackList2.getId().equals(geBlackList.getId())){//修改后的记录5要素没有改变
				BeanUtils.copyProperties(geBlackList, geBlackList2);
				geBlackListService.modify(geBlackList2);
				flag = 1;
				message = "修改成功";
			}else{//修改后的记录在数据库中有重复
				message = "修改失败，修改后的记录在数据库中有重复";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "修改失败";
		}	
		return SUCCESS;
	}
	
	/**根据页面接收到的数据进行添加操作*/
	@LocusTrace(setCode="GeBlackListAction_addGeBlackList")
	public String addGeBlackList(){
		try {
			/**5要素判断数据库是否有相同记录*/
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geBlackList.getUserName())) queryRule.addEqual("userName", geBlackList.getUserName());
			else queryRule.addIsNull("userName");
			if(!StringUtils.isBlank(geBlackList.getSex())) queryRule.addEqual("sex", geBlackList.getSex());
			else queryRule.addIsNull("sex");
			if(!StringUtils.isBlank(geBlackList.getIdentifyType()))queryRule.addEqual("identifyType", geBlackList.getIdentifyType());
			else queryRule.addIsNull("identifyType");
			if(!StringUtils.isBlank(geBlackList.getIdentifyNumber()))queryRule.addEqual("identifyNumber", geBlackList.getIdentifyNumber());
			else queryRule.addIsNull("identifyNumber");
			if(geBlackList.getBirthDay()!=null) queryRule.addEqual("birthDay", geBlackList.getBirthDay());
			else queryRule.addIsNull("birthDay");
			GeBlackList geBlackList2 = geBlackListService.findByRule(queryRule);
			
			if (geBlackList2 == null) {
				geBlackListService.insert(geBlackList);
				flag = 1;
				message = "添加成功";
			}else{
				message = "添加失败，该客户已在黑名单中";
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "添加黑名单失败";
		}
		return SUCCESS;
	}
	
}
