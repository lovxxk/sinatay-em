package cn.com.sinosoft.ebusiness.mis.system.configManage.dataDictionary.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeType;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeTypeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 * 数据字典管理
 * @ClassName: GeCodeAction
 *  
 *
 */
public class GeCodeAction extends Struts2Action {
	private static final long serialVersionUID = -6880531077318123588L;
	private GeCode geCode;
	private GeCodeService geCodeService;/**依赖注入数据字典服务*/
	private GeCodeTypeService geCodeTypeService;/** 依赖注入数据类型服务类*/
	private String message;/**提示信息*/
	private int flag;//页面提示选择器

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public void setGeCodeTypeService(GeCodeTypeService geCodeTypeService) {
		this.geCodeTypeService = geCodeTypeService;
	}

	public GeCode getGeCode() {
		return geCode;
	}

	public void setGeCode(GeCode geCode) {
		this.geCode = geCode;
	}

	public GeCodeService getGeCodeService() {
		return geCodeService;
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
	 * 查询条件页面
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_frmSearch")
	public String frmSearch(){
		List<GeCodeType> codeTypeList = geCodeTypeService.findAll();
		super.getRequest().setAttribute("codeTypeList", codeTypeList);
		super.getRequest().setAttribute("geCodeType.codeType", super.getRequest().getParameter("geCodeType.codeType"));
		return SUCCESS;
	}
	
	/**
	 * 分页查询数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_findAllGeCode")
	public String findAllGeCode(){
		QueryRule queryRule = QueryRule.getInstance();
		if(!StringUtils.isBlank(geCode.getId().getCodeCode()))queryRule.addLike("id.codeCode", "%"+geCode.getId().getCodeCode().trim()+"%");
		if(!StringUtils.isBlank(geCode.getId().getCodeType()))queryRule.addEqual("id.codeType", geCode.getId().getCodeType());
		if(!StringUtils.isBlank(geCode.getCodeCName()))queryRule.addLike("codeCName", "%"+geCode.getCodeCName().trim()+"%");
		if(!StringUtils.isBlank(geCode.getValidInd()))queryRule.addEqual("validInd", geCode.getValidInd());
		
		Page page = geCodeService.findGeCode(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geCodeList", page.getResult());
		super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", page.getPageSize());
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		
		Map<String, String> codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
		return SUCCESS;
	}
	/**
	 * 查询数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_findAllGeCode")
	public String findAllGeCodeList(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType",super.getRequest().getParameter("geCodeType.codeType") );
		List<GeCode> geCodeList = geCodeService.findAll(queryRule);
		super.getRequest().setAttribute("geCodeList", geCodeList);
		QueryRule queryRule1 = QueryRule.getInstance();
		queryRule1.addEqual("codeType", super.getRequest().getParameter("geCodeType.codeType"));
		GeCodeType geCodeType=geCodeTypeService.findGeCodeType(queryRule1);
		super.getRequest().setAttribute("geCodeType",geCodeType );
		super.getRequest().setAttribute("businessArea",geCodeService.findCodeCName(geCodeType.getBusinessArea(), "BusinessArea"));
		super.getRequest().setAttribute("totalCount", geCodeList.size());
		return SUCCESS;
	}
	
	/**
	 * 新建数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_createGeCode")
	public String createGeCode(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geCode.getId().getCodeCode()))queryRule.addEqual("id.codeCode", geCode.getId().getCodeCode());
			if(!StringUtils.isBlank(geCode.getId().getCodeType()))queryRule.addEqual("id.codeType", geCode.getId().getCodeType());
			GeCode gc = geCodeService.findGeCode(queryRule);
			if(gc == null){/**判断数据库是否已存在*/
				if(geCodeService.save(geCode)){
					message = "新建数据字典成功";
					flag = 1;
				} else {
					message = "新建数据字典失败";
					flag = 0;
				}
			} else {
				message = "该数据字典已经存在";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
	/**
	 * 新建数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_createGeCode")
	public String createGeCodeNew(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geCode.getId().getCodeCode()))queryRule.addEqual("id.codeCode", geCode.getId().getCodeCode());
			if(!StringUtils.isBlank(geCode.getId().getCodeType()))queryRule.addEqual("id.codeType", geCode.getId().getCodeType());
			GeCode gc = geCodeService.findGeCode(queryRule);
			if(gc == null){/**判断数据库是否已存在*/
				if(geCodeService.save(geCode)){
					
					super.getRequest().setAttribute("geCodeType",geCode.getId().getCodeType());
					message = "新建数据字典成功";
					flag = 3;
				} else {
					message = "新建数据字典失败";
					flag = 0;
				}
			} else {
				message = "该数据字典已经存在";
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
		String[] strId = geCode.getId().getCodeType().split(",");
		queryRule.addEqual("id.codeCode", strId[0]);/** 增加id的查询条件*/
		queryRule.addEqual("id.codeType", strId[1]);/** 增加id的查询条件*/
		geCode = geCodeService.findGeCode(queryRule);
		super.getRequest().setAttribute("geCodeForShow", geCode);
		Map<String, String> codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
		return SUCCESS;
	}
	
	/**
	 * 查询要修改数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_queryForUpdate")
	public String queryForUpdate(){
		QueryRule queryRule = QueryRule.getInstance();/** 获取QueryRule对象的Instance*/
		String[] strId = geCode.getId().getCodeType().split(",");
		queryRule.addEqual("id.codeCode", strId[0]);/** 增加id的查询条件*/
		queryRule.addEqual("id.codeType", strId[1]);/** 增加id的查询条件*/
		geCode = geCodeService.findGeCode(queryRule);
		super.getRequest().setAttribute("geCodeForUpdate", geCode);
		Map<String, String> codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
		return SUCCESS;
	}
	
	/**
	 * 修改数据字典信息
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_updateGeCode")
	public String updateGeCode(){
		try {
			if(geCodeService.updateGeCode(geCode,new String[]{"id","flag","geCodeType"})){
				message = "修改数据字典成功";
				super.getRequest().setAttribute("geCodeType",geCode.getId().getCodeType());
				flag = 3;
			}else{
				message = "修改数据字典失败.";
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
	@LocusTrace(setCode="GeCodeAction_deleteGeCode")
	public String deleteGeCodeById(){
		try {
			if(geCodeService.delete(geCode.getId())){
				message = "删除数据字典成功";
				super.getRequest().setAttribute("geCodeType",geCode.getId().getCodeType());
				flag = 3;
			}else{
				message = "删除数据字典失败.";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
}
