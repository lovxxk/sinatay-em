package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.riskAndKindManage.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.JSONUtils;

import org.codehaus.jackson.annotate.JsonIgnore;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindId;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelate;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelateId;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;

public class GeKindManageAction extends Struts2Action {
	private static final long serialVersionUID = -3517814498044475227L;
	private BizCommonService bizCommonService; //����ӿ�
	private GeKind geKind;//ʵ����   
	private GeRisk geRisk ;
	private GeKindRelate geKindRelate ;
	private Map<String,String> mapBusiness;
	private GeCodeService geCodeService ;
	 /**
	 * json��ʽ������
	 */
	private JsonConfig jc;
	private String message;//����ҳ�����ʾ��Ϣ
	private int flag;//ҳ����ʾѡ����
	private Page page; //��ҳ
	private List<GeCode> list ;
	private GeCode geCode;
	private String orderNo ;
	public List<GeCode> getList() {
		return list;
	}
	public void setList(List<GeCode> list) {
		this.list = list;
	}

	public GeCodeService getGeCodeService() {
		return geCodeService;
	}
	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public GeCode getGeCode() {
		return geCode;
	}
	public void setGeCode(GeCode geCode) {
		this.geCode = geCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public BizCommonService getBizCommonService() {
		return bizCommonService;
	}
	public void setBizCommonService(BizCommonService bizCommonService) {
		this.bizCommonService = bizCommonService;
	}
	public GeKind getGeKind() {
		return geKind;
	}
	public void setGeKind(GeKind geKind) {
		this.geKind = geKind;
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
	 * json��ʽ������
	 */
	@JsonIgnore
	public JsonConfig getJc() {
		if (jc == null) {
			jc = new JsonConfig();
			// jc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			// jc.setIgnoreDefaultExcludes(true);
			//
			jc.setExcludes(new String[] { "geKind", "geKindRelate" });

			jc.setJsonPropertyFilter(new PropertyFilterImpl());
			jc.registerJsonValueProcessor(Date.class,
					new JsonValueProcessorImpl());
			jc.registerJsonBeanProcessor(Date.class,
					new JsDateJsonBeanProcessor());
		}
		return jc;
	}

	
	public GeKindRelate getGeKindRelate() {
		return geKindRelate;
	}
	public void setGeKindRelate(GeKindRelate geKindRelate) {
		this.geKindRelate = geKindRelate;
	}

	public Map<String, String> getMapBusiness() {
		return mapBusiness;
	}
	public void setMapBusiness(Map<String, String> mapBusiness) {
		this.mapBusiness = mapBusiness;
	}
	public GeRisk getGeRisk() {
		return geRisk;
	}
	public void setGeRisk(GeRisk geRisk) {
		this.geRisk = geRisk;
	}
	/**
	 * @return success
	 * ��������ձ���Ϣ
	 */
	public String addKind(){
		/*
		 if(orderNo!=null&&!orderNo.equals("")){
			geKind.setOrderNo(Integer.parseInt(orderNo));
		}
		*/
		String str = geKind.getValuerange();
		String stt[] = str.split(",") ;
		String newString = "" ;
		//�����Ϊȡֵ��Χ����������ֱ�Ӹ�ֵ
		if(!"0".equals(geKind.getCodeType()) && !"3".equals(geKind.getCodeType()) ){
		  newString = str ;
		}else{
			//�����ȡֵ��Χȡ��ֱ��ƴ��Ϊabc@{a:b,b:c};������ʽ
			if("0".equals(geKind.getCodeType())){
				newString +=  stt[0]+"@{";
			    for (int i = 1; i < stt.length; i++) {
				    	if(i%2==0){
							newString += stt[i].trim()+",";
						}if(i%2!=0){
							newString += stt[i].trim()+":";
						}
				}
			    newString = newString.substring(0,newString.lastIndexOf(","))+"}";
			   //���������     �����ʱ��û������
		     }else if("3".equals(geKind.getCodeType())){
		    	 //����ֻ��������һ������ֱ�Ӹ�ֵ
		    	 if(stt.length==1){
		    		newString =  stt[0];
		    	}else{
		    		  //�������һ��Ϊ�յ� ��ֵΪ��
			    	  if("".equals(stt[0])){
			    		  newString = "" ;
			    	  }else{
			    		  newString +=  stt[0]+"@{";
					      for (int i = 1; i < stt.length; i++) {
					    	if(" ".equals(stt[i])){
					    		continue;   //������ڿ�ֵ�Ͳ�ƴ��
					    	}else{
						    	if(i%2==0){ //ż����
						    		if(stt.length-1==i){ //���Ϊ���һ��
						    			newString += stt[i].trim();
						    		}else{
									    newString += stt[i].trim()+",";
						    		}
								}else if(i%2!=0){ //������
									newString += stt[i].trim()+":";
								}
					    	}
						  }
					      newString = newString+"}" ;
		    	    }
		    	 }
		    }
		   geKind.setValuerange(newString);
		}
		List<GeKindRelate> geKindRelates = geKind.getGeKindRelateList();
		for(GeKindRelate ge : geKindRelates){
			ge.getId().getKindCode();
			ge.getId().getRelateKindCode();
			ge.getId().getRiskCode();
			ge.setGeKind(geKind);
		}
		String result = bizCommonService.addKind(geKind);//String
		//1ע��ɹ� 2��ҵ���������Ѿ�ʹ�� 
		if("0".equals(result)){
			message="�������ձ��Ѵ���,���������";
		}else{
			message="��ӳɹ�";
		}
		flag = 1;
		return SUCCESS;
	}
	/**
	 *  ˫����ҵ�������ѯ
	 * @return success
	 */
    public String findBusinessArea(){
    	list = geCodeService.findAllByGeCodeType("BusinessArea");
    	return SUCCESS ;
	 }
	
	/**
	 * ˫�����ѯ���ִ���
	 * @return success
	 */
	public String findKindCode(){
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		
		QueryRule queryRule = QueryRule.getInstance();
		if(geRisk!=null){
			if(geRisk.getBusinessArea()!=null&&!geRisk.getBusinessArea().equals("")){
				queryRule.addEqual("businessArea", geRisk.getBusinessArea().trim());
			}
			 if(geRisk.getRiskCName()!=null&&!geRisk.getRiskCName().equals("")){
					queryRule.addEqual("riskCName",geRisk.getRiskCName());
			 }
			 if(geRisk.getRiskCode()!=null&&!geRisk.getRiskCode().equals("")){
					queryRule.addEqual("riskCode",geRisk.getRiskCode());
			 }
		}
		page = bizCommonService.findGeRiskList(queryRule, pageNo, pageSize);
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
		}
		//list  =  geCodeService.findAllByGeCodeType("BusinessArea");
		mapBusiness = geCodeService.findAllCodeAndName("BusinessArea");
		return SUCCESS;
	}
	
	/**
	 *  ���ִ����ѯ�б�
	 * @return SUCCESS
	 */
	public String findKindList(){
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		
		QueryRule queryRule = QueryRule.getInstance();
		if(geKind!=null){
			//�ձ����
		    if(geKind.getId().getKindCode()!=null&&!geKind.getId().getKindCode().equals("")){
		    	GeKindId kindId = new GeKindId();
		    	queryRule.addSql(" KINDCODE like '%"+geKind.getId().getKindCode()+"%' ");
		    }
		    //�ձ����� 
		    if(geKind.getKindCName()!=null&&!geKind.getKindCName().equals("")){
		    	queryRule.addLike("kindCName","%"+geKind.getKindCName()+"%");
		    }
		}
		page = bizCommonService.findGeKindList(queryRule, pageNo, pageSize);
		
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
		}
		return SUCCESS;
	}
	/**
	 * 
	 * �鿴�ձ����ϸ��Ϣ
	 */
	public String viewKind(){
		geKind = bizCommonService.findGeKindByCode(geKind.getId().getRiskCode(), geKind.getId().getKindCode());
		return SUCCESS ;
	}
	/**
	 * 
	 * �޸�ǰ���в�ѯ
	 */
	public String prepareUpdateGeKind(){
		geKind = bizCommonService.findGeKindByCode(geKind.getId().getRiskCode(), geKind.getId().getKindCode());
		return SUCCESS ;
	}
	/**
	 * @return success
	 *  �޸��ձ���Ϣ
	 */
	public String updateGeEnKind(){
		//ȡ���޸�ҳ�������ֵ ��ϵ�ձ���б�
		String hiddenkindCode[] = super.getRequest().getParameterValues("hiddenkindCode");
		String hiddenRiskCode[] = super.getRequest().getParameterValues("hiddenRiskCode");
		String hiddenkindMainCode[] = super.getRequest().getParameterValues("hiddenkindMainCode");

		if(orderNo!=null&&!orderNo.equals("")){
			geKind.setOrderNo(Integer.parseInt(orderNo));
		}
		String str = geKind.getValuerange();
		String stt[] = str.split(",") ;
		 String newString = "" ;
		 //�����Ϊȡֵ��Χ����������ֱ�Ӹ�ֵ
		 if(!"0".equals(geKind.getCodeType()) && !"3".equals(geKind.getCodeType()) ){
		  newString = str ;
		 }else{
				//�����ȡֵ��Χȡ��ֱ��ƴ��Ϊabc@{a:b,b:c};������ʽ
				if("0".equals(geKind.getCodeType())){
					newString +=  stt[0]+"@{";
				    for (int i = 1; i < stt.length; i++) {
					    	if(i%2==0){
								newString += stt[i].trim()+",";
							}if(i%2!=0){
								newString += stt[i].trim()+":";
							}
					}
				    newString = newString.substring(0,newString.lastIndexOf(","))+"}";
				   //���������     �����ʱ��û������
			     }else if("3".equals(geKind.getCodeType())){
			    	 //����ֻ��������һ������ֱ�Ӹ�ֵ
			    	 if(stt.length==1){
			    		newString =  stt[0];
			    	}else{
			    		  //�������һ��Ϊ�յ� ��ֵΪ��
				    	  if("".equals(stt[0])){
				    		  newString = "" ;
				    	  }else{
				    		  newString +=  stt[0]+"@{";
						      for (int i = 1; i < stt.length; i++) {
						    	if(" ".equals(stt[i])){
						    		continue;   //������ڿ�ֵ�Ͳ�ƴ��
						    	}else{
							    	if(i%2==0){ //ż����
							    		if(stt.length-1==i){ //���Ϊ���һ��
							    			newString += stt[i].trim();
							    		}else{
										    newString += stt[i].trim()+",";
							    		}
									}else if(i%2!=0){ //������
										newString += stt[i].trim()+":";
									}
						    	}
							  }
						      newString = newString+"}" ;
			    	    }
			    	 }
			    }
			   geKind.setValuerange(newString);
			}
		//ȡ�õ��ձ��ϵ�б�
			List<GeKindRelate> geKindRelates = geKind.getGeKindRelateList();
			if(geKindRelates.size()>0){  
				for(GeKindRelate ge : geKindRelates){
					ge.getId().getKindCode();
					ge.getId().getRelateKindCode();
					ge.getId().getRiskCode();
					ge.setGeKind(geKind);
		    	}
			}else if(null!=hiddenkindCode && hiddenkindCode.length>0){
				List<GeKindRelate>  tempList = new ArrayList<GeKindRelate>();
				for(int i=0;i<hiddenkindCode.length;i++){
					GeKindRelate relate = new GeKindRelate(); 
					GeKindRelateId relateId  = new GeKindRelateId();
					relateId.setKindCode(hiddenkindMainCode[i]);
					relateId.setRiskCode(hiddenRiskCode[i]);
					relateId.setRelateKindCode(hiddenkindCode[i]);
					relate.setId(relateId);
					tempList.add(relate);
				 }
				geKind.setGeKindRelateList(tempList);
			}
		String result = bizCommonService.updateGeEnKind(geKind);//�����޸ķ���
		message="�޸ĳɹ�";
		flag = 1;
		return SUCCESS;
	}
	/**
	 * ɾ���ձ���Ϣ
	 */
	public String deleteByCode(){
		try {
			bizCommonService.deleteById(geKind.getId().getKindCode(),geKind.getId().getRiskCode());
			flag = 1;
			message = "ɾ���ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ��ʧ�ܣ�";
		}
		return SUCCESS;
	}
	
	/**
	 *  ��֤�ײ����Ƿ�ʹ�ø��ձ�
	 * @return none
	 */
	public String checkKindCodeUnique(){
		String  count = bizCommonService.checkKindCodeUnique(geKind.getId().getKindCode(),geKind.getId().getRiskCode());
		if("1".equals(count)){
			this.renderText("01"); //����ɾ��
		}else{
			this.renderText("02");
		}
		return NONE ;
	}
	/**
	 * У���ձ������ orderno
	 * @return
	 */
	public String checkOrderNum(){
		if(orderNo!=null&&!orderNo.equals("")){
			geKind.setOrderNo(Integer.parseInt(orderNo));
		}
		String  count = bizCommonService.checkOrderNo(geKind);
		if("1".equals(count)){
			this.renderText("01");//������Ӵ�orderno
		}else{
			this.renderText("02");
		}
		
		return NONE;
	}
	
	 public String searchKindCode(){
		 super.getRequest().setAttribute("validInd",super.getRequest().getParameter("validInd"));
	   return SUCCESS ;
	}
	 
	 /**
	  * ��ѯ�ձ��б�  �������ո����չ�ϵ
	  * @return
	  */
	 public String findKindMainOrAdd(){
		 if (pageNo == 0) {
				pageNo = 1;
			}
			if (pageSize == 0) {
				pageSize = 10;
			}
			
			QueryRule queryRule = QueryRule.getInstance();
			if(geKind!=null){
				//�ձ����
			    if(geKind.getId().getKindCode()!=null&&!geKind.getId().getKindCode().equals("")){
			    	GeKindId kindId = new GeKindId();
			    	queryRule.addSql(" KINDCODE like '%"+geKind.getId().getKindCode()+"%' ");
			    }
			    //�ձ����� 
			    if(geKind.getKindCName()!=null&&!geKind.getKindCName().equals("")){
			    	queryRule.addLike("kindCName","%"+geKind.getKindCName()+"%");
			    }if(geKind.getValidInd()!=null &&!geKind.getValidInd().equals("")){
			    	queryRule.addLike("validInd",geKind.getValidInd());
			    }
			}
			page = bizCommonService.findGeKindList(queryRule, pageNo, pageSize);
			if(page!=null){
				super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
				super.getRequest().setAttribute("pageSize", page.getPageSize());
				super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
				super.getRequest().setAttribute("totalCount", page.getTotalCount());
			}
			return SUCCESS;
	 }
	 /**
	  * �������ո����չ�ϵ�Ĵ���
	  * @return
	  */
	 public String createCode(){
		    String result = bizCommonService.addKindRelate(geKindRelate);//String
			//1ע��ɹ� 2��ҵ���������Ѿ�ʹ�� 
			if("0".equals(result)){
				message="���������ո����չ�ϵ�Ѵ���,���������";
			}else{
				message="��ӳɹ�";
			}
			flag = 1;
			return SUCCESS;
	 }
		/**
		 *  �½���ϵǰ��ҵ������
		 * @return success
		 */
	    public String  businessAreaCode(){
	    	list = geCodeService.findAllByGeCodeType("BusinessArea");
	    	return SUCCESS ;
		 }
	  
	    /**
	     * �ձ��ϵά���б�
	     * @return
	     */
	    public  String  queryKindRelate(){
    	    if (pageNo == 0) {
				pageNo = 1;
			}
			if (pageSize == 0) {
				pageSize = 10;
			}
	    	QueryRule rule = QueryRule.getInstance();
	    
				//�ձ����
		    if(geKindRelate.getId().getKindCode()!=null&&!geKindRelate.getId().getKindCode().equals("")){
		    	rule.addSql(" kindCode like '%"+geKindRelate.getId().getKindCode()+"%' ");
		    }
		    if(geKindRelate.getId().getRelateKindCode()!=null &&!geKindRelate.getId().getRelateKindCode().equals("")){
		    	rule.addSql(" relateKindCode like '%"+geKindRelate.getId().getRelateKindCode()+"%' ");
		    }
			
	    	page = bizCommonService.findKindRelateList(rule, pageNo, pageSize);
	    	list = geCodeService.findAllByGeCodeType("BusinessArea");
	    	if(page!=null){
				super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
				super.getRequest().setAttribute("pageSize", page.getPageSize());
				super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
				super.getRequest().setAttribute("totalCount", page.getTotalCount());
			}
	    	return SUCCESS ;
	    } 
	    /**
	     * �鿴�ձ��ϵ
	     * @return
	     */
	  public String viewKindRelate(){
		 geKindRelate = bizCommonService.viewKindRelate(geKindRelate);//findGeKindByCode
	     //list = geCodeService.findAllByGeCodeType("BusinessArea");
		 super.getRequest().setAttribute("view",super.getRequest().getParameter("view"));
		 return SUCCESS;
	  }   
	  
	 /**
	  * �޸��ձ��ϵ
	  * @return
	  */
	 public String kindRelatef(){
		 bizCommonService.modifyKindRelate(geKindRelate);
		 message="�޸ĳɹ�";
		 flag = 1;
		 return SUCCESS;
	 }
	 
	 /**
	  * ��ѯ�����ձ��б�  �������ո����չ�ϵ
	  * @return
	  */
	 public String addKindList(){
		 if (pageNo == 0) {
				pageNo = 1;
			}
			if (pageSize == 0) {
				pageSize = 10;
			}
			QueryRule queryRule = QueryRule.getInstance();
				//�ձ����
			    if(geKind.getId().getKindCode()!=null&&!geKind.getId().getKindCode().equals("")){
			    	queryRule.addSql(" KINDCODE like '%"+geKind.getId().getKindCode()+"%' ");
			    }
			    //���ִ��� 
			    if(geKind.getId().getRiskCode()!=null&&!geKind.getId().getRiskCode().equals("")){
			    	queryRule.addSql(" riskCode like '%"+geKind.getId().getRiskCode()+"%' ");
			    }
			   queryRule.addEqual("kindflag","02");
			   queryRule.addEqual("validInd","1");
			page = bizCommonService.findGeKindList(queryRule, pageNo, pageSize);
			if(page!=null){
				super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
				super.getRequest().setAttribute("pageSize", page.getPageSize());
				super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
				super.getRequest().setAttribute("totalCount", page.getTotalCount());
			}
			return SUCCESS;
	 }
	 /**
	  *  �����޸ĵ��ձ��б�
	  * @return
	  */
	public String  recommendKind(){
		String riskCode = super.getRequest().getParameter("riskV");
		String idsValue = super.getRequest().getParameter("idsValue");
		List<GeKind> tempList = new ArrayList<GeKind>();
		 if(!"".equals(riskCode)&&!"".equals(idsValue)){
        	String[] ma = idsValue.split("-");
    		for(int i=0;i<ma.length;i++){
    			String kindCode = ma[i];
    			geKind = bizCommonService.findGeKindByCode(riskCode,kindCode);
    			tempList.add(geKind);
    		}
		}
		super.getRequest().setAttribute("kinds",tempList);
		return SUCCESS;
	}
	
	/**
	 * У���ϵ�Ƿ��ظ�
	 * @return
	 */
	public String checkRelate(){
		String kindMain = super.getRequest().getParameter("kindMain");
		String risk = super.getRequest().getParameter("risk");
		String kindAddCodes = super.getRequest().getParameter("kindAddCodes");
		if(!"".equals(kindAddCodes)){
			GeKindRelateId relateId = new GeKindRelateId();
			relateId.setKindCode(kindMain);
			relateId.setRiskCode(risk);
			relateId.setRelateKindCode(kindAddCodes);
			Map<String,Object> tempMap = bizCommonService.checkRelate(relateId);
			JSONObject jsonObject = getResponseJSONObject(tempMap, getJc());
			renderText(jsonObject.toString());
		}
	  return NONE;
	}
	
	public String vaildateRelateCode(){
		String kindCode = super.getRequest().getParameter("kindCode");
		String riskCode = super.getRequest().getParameter("riskCode");
		Map<String,Object> tempMap  = bizCommonService.vaildateRelateCode(riskCode, kindCode);
		JSONObject jsonObject = getResponseJSONObject(tempMap, getJc());
		renderText(jsonObject.toString());
		return NONE;
	}
	    
	 /**
	 * ƴװresponse �õ���json�ַ���
	 * 
	 * @param map
	 * @param jc
	 * @return
	 */
	private JSONObject getResponseJSONObject(Map<String, Object> map,
			JsonConfig jc) {
		JSONObject jsonObject = new JSONObject();
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd" }));
		if (jc == null) {
			jc = getJc();
		}
		jsonObject.putAll(map, jc);
		logger.debug(jsonObject);
		return jsonObject;
	}
	
	/**
	 * У���Ƿ���ڸ����� �������������Ӹ�����
	 * @return
	 */
	public String checkAddFlag(){
		String result = bizCommonService.checkAddFlag(geKind);
		if("2".equals(result)){
			this.renderText("02"); //û�и�����
		}
		return NONE ;
	}
	/**
	 * У���Ƿ�����޸��ձ�����
	 * @return
	 */
	public String checkFlag(){
		String flag = super.getRequest().getParameter("kindflag");
		String result = bizCommonService.checkFlag(geKindRelate, flag);
		if("2".equals(result)){
			this.renderText("02"); //�������޸��ձ�����
		}
		return NONE;
	}
	public String findGeKindList(){
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 100;
		}
		
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addAscOrder("kindflag");
		queryRule.addAscOrder("orderNo");
		page = bizCommonService.findGeKindList(queryRule, pageNo, pageSize);
		
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
		}
		return SUCCESS;
	}
	/**
	 * �����ײ�����˳������
	 * @return
	 */
	public String sortRiskKind(){
		String []riskCodes = getRequest().getParameterValues("geKindRiskCode");
		String []kindCodes = getRequest().getParameterValues("geKindKindCode");
	try {
		for(int i=0;i<riskCodes.length;i++){
			GeKind geKind = bizCommonService.findGeKindByCode(riskCodes[i], kindCodes[i]);
			geKind.setOrderNo(i);
		    bizCommonService.updateGeEnKindOrderNo(geKind);
		}
		message="���³ɹ�";
		flag = 2;
	} catch (Exception e) {
		e.printStackTrace();
		message = "����ʧ�ܣ�";
	}
		return SUCCESS;
	}
}
