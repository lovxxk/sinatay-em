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
	private BizCommonService bizCommonService; //服务接口
	private GeKind geKind;//实体类   
	private GeRisk geRisk ;
	private GeKindRelate geKindRelate ;
	private Map<String,String> mapBusiness;
	private GeCodeService geCodeService ;
	 /**
	 * json格式化配置
	 */
	private JsonConfig jc;
	private String message;//返回页面的提示信息
	private int flag;//页面提示选择器
	private Page page; //分页
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
	 * json格式化配置
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
	 * 添加险种险别信息
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
		//如果不为取值范围，或其它就直接赋值
		if(!"0".equals(geKind.getCodeType()) && !"3".equals(geKind.getCodeType()) ){
		  newString = str ;
		}else{
			//如果是取值范围取就直接拼接为abc@{a:b,b:c};这样形式
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
			   //如果是其它     输入的时候没有限制
		     }else if("3".equals(geKind.getCodeType())){
		    	 //假如只输入责任一栏，则直接赋值
		    	 if(stt.length==1){
		    		newString =  stt[0];
		    	}else{
		    		  //如果责任一栏为空的 则赋值为空
			    	  if("".equals(stt[0])){
			    		  newString = "" ;
			    	  }else{
			    		  newString +=  stt[0]+"@{";
					      for (int i = 1; i < stt.length; i++) {
					    	if(" ".equals(stt[i])){
					    		continue;   //如果存在空值就不拼接
					    	}else{
						    	if(i%2==0){ //偶数列
						    		if(stt.length-1==i){ //如果为最后一个
						    			newString += stt[i].trim();
						    		}else{
									    newString += stt[i].trim()+",";
						    		}
								}else if(i%2!=0){ //奇数列
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
		//1注册成功 2企业机构号码已经使用 
		if("0".equals(result)){
			message="该险种险别已存在,请重新添加";
		}else{
			message="添加成功";
		}
		flag = 1;
		return SUCCESS;
	}
	/**
	 *  双击域业务领域查询
	 * @return success
	 */
    public String findBusinessArea(){
    	list = geCodeService.findAllByGeCodeType("BusinessArea");
    	return SUCCESS ;
	 }
	
	/**
	 * 双击域查询险种代码
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
	 *  险种代码查询列表
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
			//险别代码
		    if(geKind.getId().getKindCode()!=null&&!geKind.getId().getKindCode().equals("")){
		    	GeKindId kindId = new GeKindId();
		    	queryRule.addSql(" KINDCODE like '%"+geKind.getId().getKindCode()+"%' ");
		    }
		    //险别名称 
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
	 * 查看险别的详细信息
	 */
	public String viewKind(){
		geKind = bizCommonService.findGeKindByCode(geKind.getId().getRiskCode(), geKind.getId().getKindCode());
		return SUCCESS ;
	}
	/**
	 * 
	 * 修改前进行查询
	 */
	public String prepareUpdateGeKind(){
		geKind = bizCommonService.findGeKindByCode(geKind.getId().getRiskCode(), geKind.getId().getKindCode());
		return SUCCESS ;
	}
	/**
	 * @return success
	 *  修改险别信息
	 */
	public String updateGeEnKind(){
		//取到修改页面的隐藏值 关系险别的列表
		String hiddenkindCode[] = super.getRequest().getParameterValues("hiddenkindCode");
		String hiddenRiskCode[] = super.getRequest().getParameterValues("hiddenRiskCode");
		String hiddenkindMainCode[] = super.getRequest().getParameterValues("hiddenkindMainCode");

		if(orderNo!=null&&!orderNo.equals("")){
			geKind.setOrderNo(Integer.parseInt(orderNo));
		}
		String str = geKind.getValuerange();
		String stt[] = str.split(",") ;
		 String newString = "" ;
		 //如果不为取值范围，或其它就直接赋值
		 if(!"0".equals(geKind.getCodeType()) && !"3".equals(geKind.getCodeType()) ){
		  newString = str ;
		 }else{
				//如果是取值范围取就直接拼接为abc@{a:b,b:c};这样形式
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
				   //如果是其它     输入的时候没有限制
			     }else if("3".equals(geKind.getCodeType())){
			    	 //假如只输入责任一栏，则直接赋值
			    	 if(stt.length==1){
			    		newString =  stt[0];
			    	}else{
			    		  //如果责任一栏为空的 则赋值为空
				    	  if("".equals(stt[0])){
				    		  newString = "" ;
				    	  }else{
				    		  newString +=  stt[0]+"@{";
						      for (int i = 1; i < stt.length; i++) {
						    	if(" ".equals(stt[i])){
						    		continue;   //如果存在空值就不拼接
						    	}else{
							    	if(i%2==0){ //偶数列
							    		if(stt.length-1==i){ //如果为最后一个
							    			newString += stt[i].trim();
							    		}else{
										    newString += stt[i].trim()+",";
							    		}
									}else if(i%2!=0){ //奇数列
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
		//取得的险别关系列表
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
		String result = bizCommonService.updateGeEnKind(geKind);//调用修改方法
		message="修改成功";
		flag = 1;
		return SUCCESS;
	}
	/**
	 * 删除险别信息
	 */
	public String deleteByCode(){
		try {
			bizCommonService.deleteById(geKind.getId().getKindCode(),geKind.getId().getRiskCode());
			flag = 1;
			message = "删除成功！";
		} catch (Exception e) {
			e.printStackTrace();
			message = "删除失败！";
		}
		return SUCCESS;
	}
	
	/**
	 *  验证套餐中是否使用该险别
	 * @return none
	 */
	public String checkKindCodeUnique(){
		String  count = bizCommonService.checkKindCodeUnique(geKind.getId().getKindCode(),geKind.getId().getRiskCode());
		if("1".equals(count)){
			this.renderText("01"); //可以删除
		}else{
			this.renderText("02");
		}
		return NONE ;
	}
	/**
	 * 校验险别的排序 orderno
	 * @return
	 */
	public String checkOrderNum(){
		if(orderNo!=null&&!orderNo.equals("")){
			geKind.setOrderNo(Integer.parseInt(orderNo));
		}
		String  count = bizCommonService.checkOrderNo(geKind);
		if("1".equals(count)){
			this.renderText("01");//可以添加此orderno
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
	  * 查询险别列表  用于主险附加险关系
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
				//险别代码
			    if(geKind.getId().getKindCode()!=null&&!geKind.getId().getKindCode().equals("")){
			    	GeKindId kindId = new GeKindId();
			    	queryRule.addSql(" KINDCODE like '%"+geKind.getId().getKindCode()+"%' ");
			    }
			    //险别名称 
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
	  * 创建主险附加险关系的创建
	  * @return
	  */
	 public String createCode(){
		    String result = bizCommonService.addKindRelate(geKindRelate);//String
			//1注册成功 2企业机构号码已经使用 
			if("0".equals(result)){
				message="该险种主险附加险关系已存在,请重新添加";
			}else{
				message="添加成功";
			}
			flag = 1;
			return SUCCESS;
	 }
		/**
		 *  新建关系前的业务领域
		 * @return success
		 */
	    public String  businessAreaCode(){
	    	list = geCodeService.findAllByGeCodeType("BusinessArea");
	    	return SUCCESS ;
		 }
	  
	    /**
	     * 险别关系维护列表
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
	    
				//险别代码
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
	     * 查看险别关系
	     * @return
	     */
	  public String viewKindRelate(){
		 geKindRelate = bizCommonService.viewKindRelate(geKindRelate);//findGeKindByCode
	     //list = geCodeService.findAllByGeCodeType("BusinessArea");
		 super.getRequest().setAttribute("view",super.getRequest().getParameter("view"));
		 return SUCCESS;
	  }   
	  
	 /**
	  * 修改险别关系
	  * @return
	  */
	 public String kindRelatef(){
		 bizCommonService.modifyKindRelate(geKindRelate);
		 message="修改成功";
		 flag = 1;
		 return SUCCESS;
	 }
	 
	 /**
	  * 查询附加险别列表  用于主险附加险关系
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
				//险别代码
			    if(geKind.getId().getKindCode()!=null&&!geKind.getId().getKindCode().equals("")){
			    	queryRule.addSql(" KINDCODE like '%"+geKind.getId().getKindCode()+"%' ");
			    }
			    //险种代码 
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
	  *  回显修改的险别列表
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
	 * 校验关系是否重复
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
	 * 拼装response 用到的json字符串
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
	 * 校验是否存在附加险 若不存在则增加附加险
	 * @return
	 */
	public String checkAddFlag(){
		String result = bizCommonService.checkAddFlag(geKind);
		if("2".equals(result)){
			this.renderText("02"); //没有附加险
		}
		return NONE ;
	}
	/**
	 * 校验是否可以修改险别类型
	 * @return
	 */
	public String checkFlag(){
		String flag = super.getRequest().getParameter("kindflag");
		String result = bizCommonService.checkFlag(geKindRelate, flag);
		if("2".equals(result)){
			this.renderText("02"); //不可以修改险别类型
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
	 * 更新套餐险中顺序排序
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
		message="更新成功";
		flag = 2;
	} catch (Exception e) {
		e.printStackTrace();
		message = "更新失败！";
	}
		return SUCCESS;
	}
}
