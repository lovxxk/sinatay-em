package cn.com.sinosoft.ebusiness.mis.system.configManage.dataDictionary.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.util.List;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeType;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeTypeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 * 数据字典类型管理
 * @ClassName: GeCodeTypeAction
 *  
 *
 */
public class GeCodeTypeAction extends Struts2Action {
	private static final long serialVersionUID = 7454027008366196739L;
	private GeCodeType geCodeType;
	private GeCodeService geCodeService;/**依赖注入数据字典服务*/
	private final  static String BusinessArea = "BusinessArea";
	private GeCodeTypeService geCodeTypeService;public GeCodeService getGeCodeService() {
		return geCodeService;
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
	/** 依赖注入数据类型服务类*/
	private String message;/**提示信息*/
	private int flag;//页面提示选择器
	private List<GeCode> bussList;/**业务领域列表*/
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

	public GeCodeType getGeCodeType() {
		return geCodeType;
	}

	public void setGeCodeType(GeCodeType geCodeType) {
		this.geCodeType = geCodeType;
	}

	public GeCodeTypeService getGeCodeTypeService() {
		return geCodeTypeService;
	}

	public void setGeCodeTypeService(GeCodeTypeService geCodeTypeService) {
		this.geCodeTypeService = geCodeTypeService;
	}

	/**
	 * 查询数据字典信息（子表）
	 * @return
	 */
	@LocusTrace(setCode="GeCodeTypeAction_findAllGeCodeType")
	public String findAllGeCodeType(){
		try{
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geCodeType.getBusinessArea()))queryRule.addEqual("businessArea", geCodeType.getBusinessArea());
			if(!StringUtils.isBlank(geCodeType.getCodeType()))queryRule.addLike("codeType", "%"+geCodeType.getCodeType()+"%");
			if(!StringUtils.isBlank(geCodeType.getCodeTypeCDesc()))queryRule.addLike("codeTypeCDesc", "%"+geCodeType.getCodeTypeCDesc()+"%");
			if(!StringUtils.isBlank(geCodeType.getValidInd()))queryRule.addEqual("validInd", geCodeType.getValidInd());
			queryRule.addAscOrder("codeType");
			Page page = geCodeTypeService.findGeCodeType(queryRule, pageNo, pageSize);
			super.getRequest().setAttribute("geCodeTypeList", page.getResult());
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
			bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		}catch (Exception e) {
			//return ERROR;
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 进入新建页面
	 * @return
	 */
	@LocusTrace(setCode="GeBlackListAction_frmCreate")
	public String frmCreate() throws Exception{
		/**查询数据字典的业务领域类型*/
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		return SUCCESS;
	}
	
	
	/**
	 * 新建数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_createGeCode")
	public String createGeCodeType(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geCodeType.getCodeType()))
				queryRule.addEqual("codeType", geCodeType.getCodeType());
			GeCodeType gct = geCodeTypeService.findGeCodeType(queryRule);
			if(gct == null){/**判断数据库是否已存在*/
				if(geCodeTypeService.save(geCodeType)){
					message = "新建数据字典类型成功";
					flag = 1;
				} else {
					message = "新建数据字典类型失败";
					flag = 0;
				}
			} else {
				message = "该数据字典类型已经存在";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}

	/**
	 * 查询要数据字典详细信息
	 * @return
	 */
	public String queryForShow(){
		QueryRule queryRule = QueryRule.getInstance();/** 获取QueryRule对象的Instance*/
		String  codeType= geCodeType.getCodeType();
		queryRule.addEqual("codeType", codeType);/** 增加id的查询条件*/
		geCodeType = geCodeTypeService.findGeCodeType(queryRule);
		super.getRequest().setAttribute("geCodeTypeForShow", geCodeType);
//		Map codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
		if(StringUtils.isNotBlank(geCodeType.getBusinessArea())){
			super.getRequest().setAttribute("businessArea",geCodeService.findCodeCName(geCodeType.getBusinessArea(), BusinessArea));
			bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		}
//		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
		return SUCCESS;
	}
	
//	/**
//	 * 查询要修改数据字典信息
//	 * @return
//	 */
//	@LocusTrace(setCode="GeCodeAction_queryForUpdate")
//	public String queryForUpdate(){
//		QueryRule queryRule = QueryRule.getInstance();/** 获取QueryRule对象的Instance*/
//		String[] strId = geCode.getId().getCodeType().split(",");
//		queryRule.addEqual("id.codeCode", strId[0]);/** 增加id的查询条件*/
//		queryRule.addEqual("id.codeType", strId[1]);/** 增加id的查询条件*/
//		geCode = geCodeService.findGeCode(queryRule);
//		super.getRequest().setAttribute("geCodeForUpdate", geCode);
//		Map codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
//		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
//		return SUCCESS;
//	}
	
	/**
	 * 修改数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_updateGeCode")
	public String updateGeCodeType(){
		try {
			if(geCodeTypeService.updateGeCodeType(geCodeType)){
				message = "修改数据字典类型成功";
				flag = 1;
			}else{
				message = "修改数据字典类型失败.";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
	/**
	 * 删除数据字典信息
	 * @return
	 */
//	@LocusTrace(setCode="GeCodeAction_deleteGeCode")
	public String deleteGeCodeTypeById(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.codeType", geCodeType.getCodeType());
			GeCode gc = geCodeService.findGeCode(queryRule);
			if(gc==null){
				if(geCodeTypeService.delete(geCodeType.getCodeType())){
					message = "删除数据字典类型成功";
					flag = 1;
				}else{
					message = "删除数据字典类型失败.";
					flag = 0;
				}
			}else{
				message = "删除数据字典类型失败(对应数据字典类型下面有数据字典，请先删除该类型下的所有数据字典再删除该类型)";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
	
}
