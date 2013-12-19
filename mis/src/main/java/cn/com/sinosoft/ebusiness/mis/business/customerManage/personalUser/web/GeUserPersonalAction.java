package cn.com.sinosoft.ebusiness.mis.business.customerManage.personalUser.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPolicy;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPolicyService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.UserServiceException;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAreaAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAreaService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.util.encode.JsonBinder;

/**
 * ��̨���� �û�Action
 * 
 *  
 * 
 */
public class GeUserPersonalAction extends Struts2Action {
	private static final long serialVersionUID = -5043770858381984310L;

	private GeUserPersonalService geUserPersonalService;
	
	private GeCodeService geCodeService;
	
	private GeDepartmentService geDepartmentService;

	private GeUserPersonal geUserPersonal;
	
	private GeUserPolicy geUserPolicy;

	private GeUserPolicyService geUserPolicyService;
	
	private GeAreaService geAreaService;
	
	private Date beginDate;

	private Date endDate;

	private String handle;

	private int flag;

	public GeAreaService getGeAreaService() {
		return geAreaService;
	}

	public void setGeAreaService(GeAreaService geAreaService) {
		this.geAreaService = geAreaService;
	}

	public GeUserPolicyService getGeUserPolicyService() {
		return geUserPolicyService;
	}

	public void setGeUserPolicyService(GeUserPolicyService geUserPolicyService) {
		this.geUserPolicyService = geUserPolicyService;
	}

	public GeUserPolicy getGeUserPolicy() {
		return geUserPolicy;
	}

	public void setGeUserPolicy(GeUserPolicy geUserPolicy) {
		this.geUserPolicy = geUserPolicy;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	private String message;

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public GeUserPersonalService getGeUserPersonalService() {
		return geUserPersonalService;
	}

	public void setGeUserPersonalService(
			GeUserPersonalService geUserPersonalService) {
		this.geUserPersonalService = geUserPersonalService;
	}

	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public GeDepartmentService getGeDepartmentService() {
		return geDepartmentService;
	}

	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}

	public GeUserPersonal getGeUserPersonal() {
		return geUserPersonal;
	}

	public void setGeUserPersonal(GeUserPersonal geUserPersonal) {
		this.geUserPersonal = geUserPersonal;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String queryIndentifyType(){
		List<GeCode> identifyList = geCodeService.findAllByGeCodeType("IdentifyType");//����֤������
		super.getRequest().setAttribute("identifyList", identifyList);
		return SUCCESS;
	}

	/**
	 * ����ҳ����յ������ݽ��в�ѯ�������õ���ҳ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	@LocusTrace(setCode="GeUserPersonalAction_queryGeUserPersonalByDisPage")
	public String queryGeUserPersonalByDisPage() throws Exception {

		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(geUserPersonal.getUserAccount()))
			queryRule.addLike("userAccount", "%" + geUserPersonal.getUserAccount().trim()+ "%");
		if (!StringUtils.isBlank(geUserPersonal.getIdentifyType()))
			queryRule.addEqual("identifyType", geUserPersonal.getIdentifyType());
		if (!StringUtils.isBlank(geUserPersonal.getIdentifyNumber()))
			queryRule.addLike("identifyNumber", "%" + geUserPersonal.getIdentifyNumber().trim() + "%");
		if (beginDate != null)
			queryRule.addGreaterEqual("makeDate", beginDate);
		if (endDate != null)
			queryRule.addLessEqual("makeDate", new Date(endDate.getTime() + 24*60*60*1000 - 1));
		queryRule.addDescOrder("makeDate");
		Page page = geUserPersonalService.findUser(queryRule, pageNo, pageSize);
		
		List<GeCode> identifyList = geCodeService.findAllByGeCodeType("IdentifyType");//����֤������
		super.getRequest().setAttribute("identifyList", identifyList);
		
		super.getRequest().setAttribute("page", page);
		return SUCCESS;
	}
	

	/**
	 * ����ҳ����յ����������в�ѯ�������õ���Ӧ��ʵ������Ϣ�����ݽ��յ�handle���������޸Ļ�չʾҳ��
	 * 
	 * @return
	 */
	@LocusTrace(setCode="GeUserPersonalAction_queryGeUserPersonalForUpdateOrShow")
	public String queryGeUserPersonalForUpdateOrShow() {
		try {
			geUserPersonal = geUserPersonalService.findUserByPK(geUserPersonal
					.getUserID());
			
			HttpServletRequest request = super.getRequest();
			
			String identifyTypeName = null;
			String province = null;
			String city = geUserPersonal.getAreaCode();//�õ����ڵ�������
			
			if(!StringUtils.isBlank(geUserPersonal.getIdentifyType())){
				identifyTypeName = geCodeService.findCodeCName(geUserPersonal.getIdentifyType(), "IdentifyType");
			}
//			if(city != null && city.length() == 6) city = "2" + city;//���Ŵ洢���ڵ���������ϱ�־λ
//			
//			GeDepartment geDepartment = null;
			
			if(city != null ){
				if(city.length() != 6 ) city = city.substring(1);
				if(city.length() != 6 ) city = null;
			}
			
			GeAreaAuthority geAreaCity = null;
			GeAreaAuthority geAreaPro = null;
			
			//չʾ��ʱ���õ����ڵ�����city,������ҵ��industry,����״����income
			if("show".equals(handle)){
				if(!StringUtils.isBlank(city)){
					geAreaCity = geAreaService.findGeAuthorityByPK(city);
					geAreaPro = geAreaService.findGeAuthorityByChildId(city);
					if(geAreaCity != null && geAreaPro != null){
						city = geAreaPro.getGname()+ "-" + geAreaCity.getGname();
					}else {
						city = null;
					}
				}
				
				if(!StringUtils.isBlank(geUserPersonal.getIndustry())){
					request.setAttribute("industry",geCodeService.findCodeCName(geUserPersonal.getIndustry(), "IndustryType"));
				}
				if(!StringUtils.isBlank(geUserPersonal.getIncome())){
					request.setAttribute("income",geCodeService.findCodeCName(geUserPersonal.getIncome(), "IncomeType"));
				}
				if(!StringUtils.isBlank(geUserPersonal.getMarriageStatus())){
					request.setAttribute("marriageStatus",geCodeService.findCodeCName(geUserPersonal.getMarriageStatus(), "MaritalStatus"));
				}
				if(!StringUtils.isBlank(geUserPersonal.getHealth())){
					request.setAttribute("health",geCodeService.findCodeCName(geUserPersonal.getHealth(), "Health"));
				}
				if(!StringUtils.isBlank(geUserPersonal.getUserSource())){
					request.setAttribute("userSource",geCodeService.findCodeCName(geUserPersonal.getUserSource(), "BusinessArea"));
				}
				//ת���޸�ҳ��ʱ��Ҫ������
			}else if("update".equals(handle)){
				
				List<GeAreaAuthority> provinces = null;// ��������ʡ
				List<GeAreaAuthority> cities = null;// ͬ������������
				
				if (StringUtils.isNotBlank(city)) {
					GeAreaAuthority geAreaAuthority = geAreaService.findGeAuthorityByChildId(city);
					if(geAreaAuthority != null) province = geAreaAuthority.getGid();
					if(StringUtils.isNotBlank(province)){
						QueryRule queryRule = QueryRule.getInstance();
						queryRule.addEqual("pgid", province);
						queryRule.addEqual("glevel", "2");
						queryRule.addAscOrder("gid");
						cities = geAreaService.findGeAreas(queryRule);
					}
				}
				
				QueryRule queryRulePro = QueryRule.getInstance();
				queryRulePro.addEqual("pgid", "1");
				queryRulePro.addEqual("glevel", "1");
				queryRulePro.addAscOrder("gid");
				provinces = geAreaService.findGeAreas(queryRulePro);
				
				List<GeCode> incomeList = geCodeService.findAllByGeCodeType("IncomeType");//��������״��
				List<GeCode> industryList = geCodeService.findAllByGeCodeType("IndustryType");//������ҵ	
				List<GeCode> maritalStatusList = geCodeService.findAllByGeCodeType("MaritalStatus");//����״��
				List<GeCode> healthList = geCodeService.findAllByGeCodeType("Health");//����״��
				List<GeCode> bussList = geCodeService.findAllByGeCodeType("BusinessArea");//�û���Դ
				
				request.setAttribute("provinces", provinces);//��ҵ����������е�ʡ����
				request.setAttribute("cities", cities);
				request.setAttribute("incomeList", incomeList);
				request.setAttribute("industryList", industryList);
				request.setAttribute("maritalStatusList", maritalStatusList);
				request.setAttribute("healthList", healthList);
				request.setAttribute("bussList", bussList);
				
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("id.codeType", "GePersonalUserLevel");
				queryRule.addAscOrder("codeEName");
				List<GeCode> userLevelList = geCodeService.findAll(queryRule);//���пͻ��ȼ�	
				
				request.setAttribute("userLevelList", userLevelList);
			}
			
			request.setAttribute("province", province);
			request.setAttribute("city", city);
			request.setAttribute("identifyTypeName", identifyTypeName);
			request.setAttribute("buttonFlag", super.getRequest().getParameter("buttonFlag"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handle;
	}

	//ajax��ѯ����
	@LocusTrace(setCode="GeUserPersonalAction_findDepartment")
	public void findDepartment(){
		String pgid = super.getRequest().getParameter("pgid");
		String glevel = super.getRequest().getParameter("glevel");
		Object[] objs = geAreaService.findGeAreas(pgid, glevel);
		super.render(JsonBinder.buildNonNullBinder().toJson(objs), "application/json;charset=GBK");
	}
	
	/**
	 * ����ҳ����յ������ݽ����޸Ĳ���
	 * 
	 * @return
	 */
	@LocusTrace(setCode="GeUserPersonalAction_updateGeUserPersonal")
	public String updateGeUserPersonal() {
		try {
			if(geUserPersonal.getUserID() == null){
				message = "�ͻ���Ϊ�գ��޷���λ�޸Ķ����λ�ã�";
				return SUCCESS;
			}
			GeUserPersonal geUser = geUserPersonalService.findUserByPK(geUserPersonal.getUserID());
			String city = geUserPersonal.getCity();
			String province = geUserPersonal.getProvinces();
			String userSource = geUser.getUserSource();
			boolean isGroup = "1".equals(userSource);//�ж��û��Ƿ� ����ע��
			
//			if (!StringUtils.isBlank(city)) {
//				if (isGroup) {
//					geUserPersonal.setAreaCode(city.substring(1));//��ҳ���õ��Ļ��������һλ��ʾҵ�����򣬼���û��
//				}else {
//					geUserPersonal.setAreaCode(city);
//				}
//			}else {
//				if (!StringUtils.isBlank(province)) {
//					if(isGroup){
//						geUserPersonal.setAreaCode(province.substring(1));
//					}else {
//						geUserPersonal.setAreaCode(province);
//					}
//				}
//			}
			
			geUserPersonal.setAreaCode(city);
			
			Map map = new HashMap();
			map.put("raInd", geUserPersonal.getRaInd());//RA��֤״̬
			geUserPersonalService.updateUserPart(map, geUserPersonal.getUserID());
			
			geUserPersonal = convertNoUpdateProForUser(geUserPersonal);
			List resultList = geUserPersonalService.updateGeUserPersonalWithLDAP(geUserPersonal);
			if(resultList.contains("1")){
				flag = 1;
				message = "�޸ĳɹ�";
			}else{
				message = "�޸�ʧ�ܣ�";
				if(resultList.contains("2")){
					message += "LDAP�пͻ�������)";
				}else if(resultList.contains("3")){
					message += "�û����ظ�)";
				}else if(resultList.contains("4")){
					message += "�����ظ�)";
				}else if(resultList.contains("5")){
					message += "���֤�ظ�)";
				}else if(resultList.contains("6")){
					message += "�׿����ظ�)";
				}else{
					message = message.substring(0, message.length()-1);
				}
			}
			
		}catch (UserServiceException e) {
			String errMessage = e.showMessage();
			if (StringUtils.isBlank(errMessage)) {
				errMessage = e.getMsg();
			}
			logger.debug("�޸ĸ�����Ϣʧ��("+errMessage+")���ͻ��ţ�"+geUserPersonal.getUserID());
			message = "�޸�ʧ��("+errMessage+")";
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("�޸�ʧ��(���±���RA��֤�����쳣)���ͻ��ţ�"+geUserPersonal.getUserID());
			message = "�޸�ʧ��(���±���RA��֤�����쳣)";
		}
		return SUCCESS;
	}

	//�Ѵ��޸Ķ���Ĳ����޸ĵ�����ֵȫ���滻Ϊ���ݿ������
	private GeUserPersonal convertNoUpdateProForUser(GeUserPersonal target){
		target.setUserName(null);
		target.setSex(null);
		target.setBirthday(null);
		target.setIdentifyType(null);
		target.setIdentifyNumber(null);
		target.setMakeDate(null);
		target.setPiCardNo(null);
		target.setUserSource(null);
		target.setIntegral(null);
		target.setUserRank(null);
		target.setUkey(null);
		target.setPwd(null);
		target.setPwd2(null);
		target.setProvinces(null);
		target.setCity(null);
		target.setArea(null);
		target.setActiveCode(null);
		target.setCheckStatus(null);
		target.setFlag(null);
		target.setRaInd(null);
		return target;
	}
	
	/**
	 * �������
	 * 
	 * @return
	 * @throws Exception
	 */
	@LocusTrace(setCode="GeUserPersonalAction_unBoundPolicy")
	public String unBoundPolicy() throws Exception {
		String idStr = super.getRequest().getParameter("idStr");
		Map map = (Map) super.getSession().getAttribute("permission");
		if(map.containsKey("ROLE_B_PUSE_B")){
			if(idStr != null && idStr.split(",").length > 1) {
				String id = idStr.split(",")[0];
				String bussinessArea = idStr.split(",")[1];
				if((map.containsKey("ROLE_B_PUSE_BC")&&"3".equals(bussinessArea))||(map.containsKey("ROLE_B_PUSE_BY")&&"4".equals(bussinessArea))){
					geUserPolicyService.deleteById(id);
					flag = 2;
					message = "���ɹ�";
				}else{
					message = "��û�и�ҵ������ı������Ȩ��";
				}
			}else{
				message = "���ʧ��";
			}
		}else{
			message = "��û�б�������Ȩ��";
		}
		return SUCCESS;
	}

	public String unBoundCard() {
		flag = 1;
		message = "���ɹ�";
		return SUCCESS;
	}

}
