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
 * ���ܿ��ع���
 * @ClassName: GeFunctionSwitchAction
 *  
 *
 */
public class GeFunctionSwitchAction extends Struts2Action {

	private GeFunctionSwitch geFunctionSwitch;/**���ܿ���ʵ����*/
	private GeFunctionSwitchService geFunctionSwitchService;/**����ע�빦�ܿ��ط�����*/
	private String message;/**��ʾ��Ϣ*/
	private int flag;//ҳ����ʾѡ����
	
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
	 * ��ҳ��ѯ���ܿ�����Ϣ
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
	 * ɾ�����ܿ�����Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_deleteAllGeFunctionSwitch")
	public String deleteAllGeFunctionSwitch(){
		try {
			String idStr = super.getRequest().getParameter("idStr");
			if(geFunctionSwitchService.deleteAllGeFunctionSwitch(idStr)){
				message = "ɾ���ɹ�";
				flag = 1;
			}else{
				message = "ɾ��ʧ��";
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
	 * �½����ܿ�����Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_createGeFunctionSwitch")
	public String createGeFunctionSwitch(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geFunctionSwitch.getFunctiontId()))queryRule.addLike("functiontId", geFunctionSwitch.getFunctiontId());
			GeFunctionSwitch geSwitch = geFunctionSwitchService.findGeFunctionSwitch(queryRule);
			
			if(geSwitch == null){/**�ж����ݿ��Ƿ��Ѵ���*/
				if(geFunctionSwitchService.save(geFunctionSwitch)){
					message = "�½����ܿ��سɹ�";
					flag = 1;
				} else {
					message = "�½����ܿ�����Ϣʧ��";
					flag = 0;
				}
			} else {
				message = "�ù��ܿ����Ѿ�����";
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
	 * ��ѯ���ܿ�����ϸ��Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_queryForUpdate")
	public String queryForShow(){
		QueryRule queryRule = QueryRule.getInstance();/**��ȡQueryRule�����Instance*/
		queryRule.addEqual("functiontId", geFunctionSwitch.getFunctiontId());/**����id�Ĳ�ѯ����*/
		geFunctionSwitch = geFunctionSwitchService.findGeFunctionSwitch(queryRule);
		super.getRequest().setAttribute("geFunctionSwitchForShow", geFunctionSwitch);
		return SUCCESS;
	}
	
	/**
	 * ��ѯҪ�޸Ĺ��ܿ�����Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_queryForUpdate")
	public String queryForUpdate(){
		QueryRule queryRule = QueryRule.getInstance();/**��ȡQueryRule�����Instance*/
		queryRule.addEqual("functiontId", geFunctionSwitch.getFunctiontId());/**����id�Ĳ�ѯ����*/
		geFunctionSwitch = geFunctionSwitchService.findGeFunctionSwitch(queryRule);
		super.getRequest().setAttribute("geFunctionSwitchForUpdate", geFunctionSwitch);
		return SUCCESS;
	}
	
	/**
	 * �޸Ĺ��ܿ�����Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeFunctionSwitchAction_updateGeFunctionSwitch")
	public String updateGeFunctionSwitch(){
		try {
			
			if(geFunctionSwitchService.updateGeFunctionSwitch(geFunctionSwitch)){
				message = "�޸Ĺ��ܿ��سɹ�";
				flag = 1;
			}else{
				message = "�޸Ĺ��ܿ���ʧ��";
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
