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
 * �����ֵ����
 * @ClassName: GeCodeAction
 *  
 *
 */
public class GeCodeAction extends Struts2Action {
	private static final long serialVersionUID = -6880531077318123588L;
	private GeCode geCode;
	private GeCodeService geCodeService;/**����ע�������ֵ����*/
	private GeCodeTypeService geCodeTypeService;/** ����ע���������ͷ�����*/
	private String message;/**��ʾ��Ϣ*/
	private int flag;//ҳ����ʾѡ����

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
	 * ��ѯ����ҳ��
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
	 * ��ҳ��ѯ�����ֵ���Ϣ
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
	 * ��ѯ�����ֵ���Ϣ
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
	 * �½������ֵ���Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_createGeCode")
	public String createGeCode(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geCode.getId().getCodeCode()))queryRule.addEqual("id.codeCode", geCode.getId().getCodeCode());
			if(!StringUtils.isBlank(geCode.getId().getCodeType()))queryRule.addEqual("id.codeType", geCode.getId().getCodeType());
			GeCode gc = geCodeService.findGeCode(queryRule);
			if(gc == null){/**�ж����ݿ��Ƿ��Ѵ���*/
				if(geCodeService.save(geCode)){
					message = "�½������ֵ�ɹ�";
					flag = 1;
				} else {
					message = "�½������ֵ�ʧ��";
					flag = 0;
				}
			} else {
				message = "�������ֵ��Ѿ�����";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
	/**
	 * �½������ֵ���Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_createGeCode")
	public String createGeCodeNew(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geCode.getId().getCodeCode()))queryRule.addEqual("id.codeCode", geCode.getId().getCodeCode());
			if(!StringUtils.isBlank(geCode.getId().getCodeType()))queryRule.addEqual("id.codeType", geCode.getId().getCodeType());
			GeCode gc = geCodeService.findGeCode(queryRule);
			if(gc == null){/**�ж����ݿ��Ƿ��Ѵ���*/
				if(geCodeService.save(geCode)){
					
					super.getRequest().setAttribute("geCodeType",geCode.getId().getCodeType());
					message = "�½������ֵ�ɹ�";
					flag = 3;
				} else {
					message = "�½������ֵ�ʧ��";
					flag = 0;
				}
			} else {
				message = "�������ֵ��Ѿ�����";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
	/**
	 * ��ѯҪ�����ֵ���ϸ��Ϣ
	 * @return
	 */
	public String queryForShow(){
		QueryRule queryRule = QueryRule.getInstance();/** ��ȡQueryRule�����Instance*/
		String[] strId = geCode.getId().getCodeType().split(",");
		queryRule.addEqual("id.codeCode", strId[0]);/** ����id�Ĳ�ѯ����*/
		queryRule.addEqual("id.codeType", strId[1]);/** ����id�Ĳ�ѯ����*/
		geCode = geCodeService.findGeCode(queryRule);
		super.getRequest().setAttribute("geCodeForShow", geCode);
		Map<String, String> codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
		return SUCCESS;
	}
	
	/**
	 * ��ѯҪ�޸������ֵ���Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_queryForUpdate")
	public String queryForUpdate(){
		QueryRule queryRule = QueryRule.getInstance();/** ��ȡQueryRule�����Instance*/
		String[] strId = geCode.getId().getCodeType().split(",");
		queryRule.addEqual("id.codeCode", strId[0]);/** ����id�Ĳ�ѯ����*/
		queryRule.addEqual("id.codeType", strId[1]);/** ����id�Ĳ�ѯ����*/
		geCode = geCodeService.findGeCode(queryRule);
		super.getRequest().setAttribute("geCodeForUpdate", geCode);
		Map<String, String> codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
		return SUCCESS;
	}
	
	/**
	 * �޸������ֵ���Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_updateGeCode")
	public String updateGeCode(){
		try {
			if(geCodeService.updateGeCode(geCode,new String[]{"id","flag","geCodeType"})){
				message = "�޸������ֵ�ɹ�";
				super.getRequest().setAttribute("geCodeType",geCode.getId().getCodeType());
				flag = 3;
			}else{
				message = "�޸������ֵ�ʧ��.";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
	/**
	 * ɾ�������ֵ���Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_deleteGeCode")
	public String deleteGeCodeById(){
		try {
			if(geCodeService.delete(geCode.getId())){
				message = "ɾ�������ֵ�ɹ�";
				super.getRequest().setAttribute("geCodeType",geCode.getId().getCodeType());
				flag = 3;
			}else{
				message = "ɾ�������ֵ�ʧ��.";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
}
