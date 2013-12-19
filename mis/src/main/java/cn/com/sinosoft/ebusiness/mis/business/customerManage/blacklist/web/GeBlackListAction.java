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
	private GeBlackListService geBlackListService;//����ӿ�
	private GeBlackList geBlackList;//ʵ����
	private String message;//����ҳ�����ʾ��Ϣ
	private int flag;//ҳ����ʾѡ����
	
	private GeCodeService geCodeService;/**�����ֵ������*/
	private List<GeCode> idTypeList;/**֤�������б�*/
	private List<GeCode> sexList;/**�Ա������б�*/
	private List<GeCode> bussList;/**ҵ�������б�*/
	
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
	 * �����½�ҳ��
	 * @return
	 */
	@LocusTrace(setCode="GeBlackListAction_frmCreate")
	public String frmCreate() throws Exception{
		/**��ѯ�����ֵ��֤������*/
		idTypeList = geCodeService.findAllByGeCodeTypeAndValidInd(IdentifyType,"1");
		/**��ѯ�����ֵ���Ա�����*/
		sexList = geCodeService.findAllByGeCodeTypeAndValidInd(Sex,"1");
		/**��ѯ�����ֵ��ҵ����������*/
		bussList = geCodeService.findAllByGeCodeTypeAndValidInd(BusinessArea,"1");
		return SUCCESS;
	}
	
	/**
	 * ���뵽��ѯ����ҳ��
	 * @return
	 */
	@LocusTrace(setCode="GeBlackListAction_frmSearch")
	public String frmSearch() throws Exception{
		/**��ѯ�����ֵ��֤������*/
		idTypeList = geCodeService.findAllByGeCodeTypeAndValidInd(IdentifyType, "1");
		/**��ѯ�����ֵ���Ա�����*/
		//sexList = geCodeService.findAllByGeCodeType(Sex);
		return SUCCESS;
	}

	/**����ҳ����յ������ݽ��в�ѯ�������õ���ҳ��Ϣ*/
	@LocusTrace(setCode="GeBlackListAction_queryBlackListByDisPage")
	public String queryBlackListByDisPage() throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		if(!StringUtils.isBlank(geBlackList.getUserName()))	queryRule.addLike("userName", "%"+ geBlackList.getUserName().trim()+"%");
		if(!StringUtils.isBlank(geBlackList.getIdentifyType()))	queryRule.addEqual("identifyType", geBlackList.getIdentifyType());
		if(!StringUtils.isBlank(geBlackList.getIdentifyNumber()))	queryRule.addLike("identifyNumber","%" +  geBlackList.getIdentifyNumber().trim()+"%");
		Page page = geBlackListService.findList(queryRule, pageNo, pageSize);
		
		/**��ѯ�����ֵ��֤������*/
		idTypeList = geCodeService.findAllByGeCodeType(IdentifyType);
		/**��ѯ�����ֵ���Ա�����*/
		sexList = geCodeService.findAllByGeCodeType(Sex);
		/**��ѯ�����ֵ��ҵ����������*/
		bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		
		super.getRequest().setAttribute("page", page);
		return SUCCESS;
	}
	
	/**����ҳ����յ���idStr(��һ������id��ɣ�֮����','�ָ�)������������Ӧ��ɾ������*/
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
			message = "ɾ���ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ��ʧ�ܣ�";
		}
		return SUCCESS;
	}
	
	/**����ҳ����յ����������в�ѯ�������õ���Ӧ��ʵ������Ϣ��������ϸҳ��*/
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
	
	/**����ҳ����յ����������в�ѯ�������õ���Ӧ��ʵ������Ϣ�������޸�ҳ��*/
	@LocusTrace(setCode="GeBlackListAction_queryGeBlackListForUpdate")
	public String queryGeBlackListForUpdate(){
		try {
			geBlackList = geBlackListService.findById(geBlackList.getId());
			
			/**��ѯ�����ֵ��֤������*/
			idTypeList = geCodeService.findAllByGeCodeType(IdentifyType);
			/**��ѯ�����ֵ���Ա�����*/
			sexList = geCodeService.findAllByGeCodeType(Sex);
			/**��ѯ�����ֵ��ҵ����������*/
			bussList = geCodeService.findAllByGeCodeType(BusinessArea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**����ҳ����յ������ݽ����޸Ĳ���*/
	@LocusTrace(setCode="GeBlackListAction_updateGeBlackList")
	public String updateGeBlackList(){
		try {
			/**5Ҫ���ж����ݿ��Ƿ�����ͬ��¼*/
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
			
			if (geBlackList2 == null ) {//�޸ĺ�ļ�¼�����ݿ���û���ظ�
				geBlackListService.modify(geBlackList);
				flag = 1;
				message = "�޸ĳɹ�";
			}else if(geBlackList2.getId().equals(geBlackList.getId())){//�޸ĺ�ļ�¼5Ҫ��û�иı�
				BeanUtils.copyProperties(geBlackList, geBlackList2);
				geBlackListService.modify(geBlackList2);
				flag = 1;
				message = "�޸ĳɹ�";
			}else{//�޸ĺ�ļ�¼�����ݿ������ظ�
				message = "�޸�ʧ�ܣ��޸ĺ�ļ�¼�����ݿ������ظ�";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "�޸�ʧ��";
		}	
		return SUCCESS;
	}
	
	/**����ҳ����յ������ݽ�����Ӳ���*/
	@LocusTrace(setCode="GeBlackListAction_addGeBlackList")
	public String addGeBlackList(){
		try {
			/**5Ҫ���ж����ݿ��Ƿ�����ͬ��¼*/
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
				message = "��ӳɹ�";
			}else{
				message = "���ʧ�ܣ��ÿͻ����ں�������";
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "��Ӻ�����ʧ��";
		}
		return SUCCESS;
	}
	
}
