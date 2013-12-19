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
 * �����ֵ����͹���
 * @ClassName: GeCodeTypeAction
 *  
 *
 */
public class GeCodeTypeAction extends Struts2Action {
	private static final long serialVersionUID = 7454027008366196739L;
	private GeCodeType geCodeType;
	private GeCodeService geCodeService;/**����ע�������ֵ����*/
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
	/** ����ע���������ͷ�����*/
	private String message;/**��ʾ��Ϣ*/
	private int flag;//ҳ����ʾѡ����
	private List<GeCode> bussList;/**ҵ�������б�*/
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
	 * ��ѯ�����ֵ���Ϣ���ӱ�
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
	 * �����½�ҳ��
	 * @return
	 */
	@LocusTrace(setCode="GeBlackListAction_frmCreate")
	public String frmCreate() throws Exception{
		/**��ѯ�����ֵ��ҵ����������*/
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		return SUCCESS;
	}
	
	
	/**
	 * �½������ֵ���Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_createGeCode")
	public String createGeCodeType(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(geCodeType.getCodeType()))
				queryRule.addEqual("codeType", geCodeType.getCodeType());
			GeCodeType gct = geCodeTypeService.findGeCodeType(queryRule);
			if(gct == null){/**�ж����ݿ��Ƿ��Ѵ���*/
				if(geCodeTypeService.save(geCodeType)){
					message = "�½������ֵ����ͳɹ�";
					flag = 1;
				} else {
					message = "�½������ֵ�����ʧ��";
					flag = 0;
				}
			} else {
				message = "�������ֵ������Ѿ�����";
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
		String  codeType= geCodeType.getCodeType();
		queryRule.addEqual("codeType", codeType);/** ����id�Ĳ�ѯ����*/
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
//	 * ��ѯҪ�޸������ֵ���Ϣ
//	 * @return
//	 */
//	@LocusTrace(setCode="GeCodeAction_queryForUpdate")
//	public String queryForUpdate(){
//		QueryRule queryRule = QueryRule.getInstance();/** ��ȡQueryRule�����Instance*/
//		String[] strId = geCode.getId().getCodeType().split(",");
//		queryRule.addEqual("id.codeCode", strId[0]);/** ����id�Ĳ�ѯ����*/
//		queryRule.addEqual("id.codeType", strId[1]);/** ����id�Ĳ�ѯ����*/
//		geCode = geCodeService.findGeCode(queryRule);
//		super.getRequest().setAttribute("geCodeForUpdate", geCode);
//		Map codeAndDescMap = geCodeTypeService.findAllCodeAndDesc();
//		super.getRequest().setAttribute("codeAndDescMap", codeAndDescMap);
//		return SUCCESS;
//	}
	
	/**
	 * �޸������ֵ���Ϣ
	 * @return
	 */
	@LocusTrace(setCode="GeCodeAction_updateGeCode")
	public String updateGeCodeType(){
		try {
			if(geCodeTypeService.updateGeCodeType(geCodeType)){
				message = "�޸������ֵ����ͳɹ�";
				flag = 1;
			}else{
				message = "�޸������ֵ�����ʧ��.";
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
//	@LocusTrace(setCode="GeCodeAction_deleteGeCode")
	public String deleteGeCodeTypeById(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.codeType", geCodeType.getCodeType());
			GeCode gc = geCodeService.findGeCode(queryRule);
			if(gc==null){
				if(geCodeTypeService.delete(geCodeType.getCodeType())){
					message = "ɾ�������ֵ����ͳɹ�";
					flag = 1;
				}else{
					message = "ɾ�������ֵ�����ʧ��.";
					flag = 0;
				}
			}else{
				message = "ɾ�������ֵ�����ʧ��(��Ӧ�����ֵ����������������ֵ䣬����ɾ���������µ����������ֵ���ɾ��������)";
				flag = 0;
			}
		} catch (BizConfigCommonException e) {
			message = e.getMsg();
			flag = 0;
		}
		return SUCCESS;
	}
	
}
