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
	private BizCommonService bizCommonService;//����ӿ�
	private GeRisk geRisk;//ʵ����
	private Page page;
	private QueryRule queryRule;
	private String flag;//��־
	private String message;//��Ϣ
	private GeCodeService geCodeService;//�����ֵ������
	private List<GeCode> bussList;//ҵ�������б�
	private Map<String,String> businessAreaMap;
	private String riskCode;
	private String areaName;
	private final static String BusinessArea = "BusinessArea";
	
	
	/*�ֱ�����Ӧ��set �� get ����*/
	
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
	/* ��ѯ�����ֵ��ҵ���������ͣ��������ֵ�׼������*/
	public String findBusinessArea() throws Exception{	
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		return SUCCESS;
	}
	/* ��ѯ�����ֵ��ҵ���������ͣ���Ҫ���ڸ���ҳ������Լ���*/
	public String findBusinessArea2() throws Exception{	
		
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		return SUCCESS;
	}
	/*�������ַ���*/
	public String addGeRisk(){		
		
		//�������Ĭ��ֵ,��ʱ���ù�,����Ϊ���ַ���
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
				message = "�����½��ɹ�";
			}else if("2".equals(flag)){
				flag = "0";
				message = "�Ѵ������ִ��룬���ִ��벻���ظ�";
			}
		}catch(Exception e){
			flag = "0";
			message = "���ֽ�ʧ��";
		}
	
		return SUCCESS;
	}

	//�˷�����ʱû��
//	public String findGeRiskList1(){
//		String riskCode=geRisk.getRiskCode();
//		String riskCName=geRisk.getRiskCName();
//		String businessArea=geRisk.getBusinessArea();
//		System.out.println("��ѯ����:"+riskCode+"--"+riskCName);
//		if (pageNo == 0) {
//			pageNo = 1;
//		}
//		if (pageSize == 0) {
//			pageSize = 10;
//		}
//		//�жϲ�ѯ����
//		
//		if ("".equals(riskCName)&&("".equals(riskCode))&&businessArea!=null){
//			System.out.println("��������ѯ");
//			page = bizCommonService.findGeRiskList(queryRule, pageNo, pageSize);
//		}else {
//			System.out.println("����������ѯ��"+riskCode);
//		//	List<GeRisk> page=bizCommonService.findGeRiskListBy(String businessArea,riskCode, riskCName);
//		}	
//		return SUCCESS;
//	}
	/*��ѯ���������������б�*/
	public String findGeRiskList(){
		
		pageNo = (pageNo == 0 ? 1 : pageNo);
		pageSize = (pageSize == 0 ? 20 : pageSize);

		page = bizCommonService.getGeCardProductList(geRisk, pageNo, pageSize);
		//��ȡMap���ϣ���ҪΪ����ʾҵ�������������Ӧ������
		businessAreaMap  = geCodeService.findAllCodeAndName("BusinessArea");
		
		return SUCCESS;
		
	}
	/*����code��ѯһ������*/
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
	/*�༭����*/
	
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
			message = "���ָ��³ɹ�";
		}catch(Exception e){
			flag = "0";
			message = "���ָ���ʧ��";
		}
		return SUCCESS;
	}
	public String deleteGeRiskList(){	
		try{
			List<GeKind> geKindList=bizCommonService.findKindByRiskCode(riskCode);
			if(geKindList!=null&&geKindList.size()>0){
				flag = "0";
				message = "�Բ��𣬴����ֲ��ܱ�ɾ����";
			}else{
				bizCommonService.deleteGeRiskListById(riskCode);
				   flag = "1";
				   message = "����ɾ���ɹ�";
				}
			}catch(Exception e){
		       flag = "0";
			   message = "����ɾ��ʧ��";
			}
			return SUCCESS;
			
	}
	
	
}
