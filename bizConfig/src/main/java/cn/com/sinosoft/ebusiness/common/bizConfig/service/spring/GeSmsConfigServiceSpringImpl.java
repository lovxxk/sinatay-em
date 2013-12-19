package cn.com.sinosoft.ebusiness.common.bizConfig.service.spring;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeSmsConfigService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeDeptMail;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeSmsConfig;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
/**
 *
 */
public class GeSmsConfigServiceSpringImpl extends
		GenericDaoHibernate<GeSmsConfig, String> implements GeSmsConfigService {
	private GeCodeService geCodeService;
	
	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}
	/**
    * 根据主键查询短信配置
    * @param smsId
    * @return
    */
	@LocusTrace(setDesc="根据主键查询短信配置")
   public GeSmsConfig getGeSmsConfigByPK(String smsId){
	   GeSmsConfig geSmsConfig = super.get(smsId);
	   if(geSmsConfig!=null){
		   geSmsConfig.setSendSmsName(geCodeService.findCodeCName(geSmsConfig.getFunctionID(), "SendSmsType"));
	   }
	   return geSmsConfig;
   }
   /**
    * 根据查询条件查询短信配置列表
    * @param geSmsConfig
    * @param pageNo
    * @param pageSize
    * @param ind
    * @return
    */
	@LocusTrace(setDesc="根据查询条件查询短信配置列表")
   public Page getGeSmsConfigList(GeSmsConfig geSmsConfig,int pageNo,int pageSize){
	   QueryRule queryRule = QueryRule.getInstance();
	   if(geSmsConfig.getFunctionID()!=null&&!"".equals(geSmsConfig.getFunctionID())){
		   queryRule.addEqual("functionID", geSmsConfig.getFunctionID());
	   }
	   if(geSmsConfig.getSmsName()!=null&&!"".equals(geSmsConfig.getSmsName())){
		   queryRule.addLike("smsName", "%"+geSmsConfig.getSmsName()+"%");
	   }
	   if(geSmsConfig.getValidInd()!=null&&!"".equals(geSmsConfig.getValidInd())){
		   queryRule.addEqual("validInd",geSmsConfig.getValidInd());
	   }
	   Page page =  super.find(queryRule,pageNo,pageSize);
	   if(page.getResult()!=null&&page.getResult().size()>0){
	   List<GeSmsConfig> geSmsConfigList = page.getResult();
	   for(GeSmsConfig tempGeSmsConfig:geSmsConfigList){
			if(tempGeSmsConfig.getFunctionID()!=null&&!"".equals(tempGeSmsConfig.getFunctionID())){
				tempGeSmsConfig.setSendSmsName(geCodeService.findCodeCName(tempGeSmsConfig.getFunctionID(), "SendSmsType"));
			}
		}
	   }
	   return page;
   }
   /**
    * 新增一条短信配置
    * @param geSmsConfig
    */
	@LocusTrace(setDesc="新增一条短信配置")
   public void addGeSmsConfig(GeSmsConfig geSmsConfig){
	   super.save(geSmsConfig);
   }
   /**
    * 跟新短信配置
    * @param geSmsConfig
    */
	@LocusTrace(setDesc="跟新短信配置")
   public void updateGeSmsConfig(GeSmsConfig geSmsConfig){
	    super.getSession().clear();
		super.update(geSmsConfig);
   }
   /**
    * 根据主键删除一条短信配置
    * @param smsId
    */
	@LocusTrace(setDesc="根据主键删除一条短信配置")
   public void deleteGeSmsConfigByPK(String smsId){
	   super.deleteByPK(smsId);
   }
   /**
    * 根据适用功能代码和相应规则参数返回短信内容
    * @param functionId
    * @param params
    * @return
    */
	@LocusTrace(setDesc="根据适用功能代码和相应规则参数返回短信内容")
   public String getSmsContent(String functionId,String[] params){
	   GeSmsConfig geSmsConfig = new GeSmsConfig();
	   geSmsConfig.setFunctionID(functionId);
	   geSmsConfig.setValidInd("1");
	   List<GeSmsConfig> geSmsConfigList = getSmsConfigByFunctionId(functionId);
	   String content = "";
	   if(geSmsConfigList!=null&&geSmsConfigList.size()>0){
		   content = ((GeSmsConfig)(geSmsConfigList.get(0))).getSmsContent();
		   if(params!=null&&params.length>0){
			   for(int i=0;i<params.length;i++){
				  if(params[i]!=null){
			         content = content.replaceFirst("#", params[i]);
				  }else{
					 content = content.replaceFirst("#", "");  
				  }
			   }
		   }
	   }
	   return content;
   }
   /**
    * 按功能查询有效的短信配置
    * @param functionid
    * @return
    */
	@LocusTrace(setDesc="按功能查询有效的短信配置")
   public List<GeSmsConfig> getSmsConfigByFunctionId(String functionId){
	   QueryRule queryRule = QueryRule.getInstance();
	   queryRule.addEqual("functionID",functionId);
	   queryRule.addEqual("validInd", "1");
	   List<GeSmsConfig> geSmsConfigList = super.find(queryRule);
	   return geSmsConfigList;
   }
}
