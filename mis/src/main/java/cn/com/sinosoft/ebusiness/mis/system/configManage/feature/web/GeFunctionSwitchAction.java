/**
 * 
 */
package cn.com.sinosoft.ebusiness.mis.system.configManage.feature.web;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeFunctionSwitch;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

/**
 * 功能开关管理
 * @ClassName: GeFunctionSwitchAction
 *  
 *
 */
public class GeFunctionSwitchAction extends Struts2Action {

	private GeFunctionSwitch geFunctionSwitch;/**功能开关实体类*/
	private GeFunctionSwitchService geFunctionSwitchService;/**依赖注入功能开关服务类*/
	private String message;/**提示信息*/
	private int flag;//页面提示选择器
	
	public GeFunctionSwitch getGeFunctionSwitch() {
		return geFunctionSwitch;
	}

	public void setGeFunctionSwitch(GeFunctionSwitch geFunctionSwitch) {
		this.geFunctionSwitch = geFunctionSwitch;
	}

	public GeFunctionSwitchService getGeFunctionSwitchService() {
		return geFunctionSwitchService;
	}

	public void setGeFunctionSwitchService(
			GeFunctionSwitchService geFunctionSwitchService) {
		this.geFunctionSwitchService = geFunctionSwitchService;
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
	 * 分页查询功能开关信息
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_findAllGeFunctionSwitch")
	public String findAllGeFunctionSwitch(){
		QueryRule queryRule = QueryRule.getInstance();
		if(!StringUtils.isBlank(geFunctionSwitch.getFunctiontId()))queryRule.addLike("functiontId", "%"+geFunctionSwitch.getFunctiontId().trim()+"%");
		if(!StringUtils.isBlank(geFunctionSwitch.getStatus()))queryRule.addEqual("status", geFunctionSwitch.getStatus());
		
		Page page = geFunctionSwitchService.findGeFunctionSwitch(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geFunctionSwitchList", page.getResult());
		super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", page.getPageSize());
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}

	/**
	 * 删除功能开关信息
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_deleteAllGeFunctionSwitch")
	public String deleteAllGeFunctionSwitch(){
		try {
			String idStr = super.getRequest().getParameter("idStr");
			if(geFunctionSwitchService.deleteAllGeFunctionSwitch(idStr)){
				message = "删除成功";
				flag = 1;
			}else{
				message = "删除失败";
				flag = 0;
			}
			
		} catch (BizConfigCommonException e) {
			flag = 0;
			message = e.getMsg();
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	/**
	 * 新建功能开关信息
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_createGeFunctionSwitch")
	public String createGeFunctionSwitch(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geFunctionSwitch.getFunctiontId()))queryRule.addLike("functiontId", geFunctionSwitch.getFunctiontId());
			GeFunctionSwitch geSwitch = geFunctionSwitchService.findGeFunctionSwitch(queryRule);
			
			if(geSwitch == null){/**判断数据库是否已存在*/
				if(geFunctionSwitchService.save(geFunctionSwitch)){
					message = "新建功能开关成功";
					flag = 1;
				} else {
					message = "新建功能开关信息失败";
					flag = 0;
				}
			} else {
				message = "该功能开关已经存在";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询功能开关详细信息
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_queryForUpdate")
	public String queryForShow(){
		QueryRule queryRule = QueryRule.getInstance();/**获取QueryRule对象的Instance*/
		queryRule.addEqual("functiontId", geFunctionSwitch.getFunctiontId());/**增加id的查询条件*/
		geFunctionSwitch = geFunctionSwitchService.findGeFunctionSwitch(queryRule);
		super.getRequest().setAttribute("geFunctionSwitchForShow", geFunctionSwitch);
		return SUCCESS;
	}
	
	/**
	 * 查询要修改功能开关信息
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_queryForUpdate")
	public String queryForUpdate(){
		QueryRule queryRule = QueryRule.getInstance();/**获取QueryRule对象的Instance*/
		queryRule.addEqual("functiontId", geFunctionSwitch.getFunctiontId());/**增加id的查询条件*/
		geFunctionSwitch = geFunctionSwitchService.findGeFunctionSwitch(queryRule);
		super.getRequest().setAttribute("geFunctionSwitchForUpdate", geFunctionSwitch);
		return SUCCESS;
	}
	
	/**
	 * 修改功能开关信息
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_updateGeFunctionSwitch")
	public String updateGeFunctionSwitch(){
		try {
			
			if(geFunctionSwitchService.updateGeFunctionSwitch(geFunctionSwitch)){
				message = "修改功能开关成功";
				flag = 1;
			}else{
				message = "修改功能开关失败";
				flag = 0;
			}
			
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
			return SUCCESS;
		}
		return SUCCESS;
	}
}
