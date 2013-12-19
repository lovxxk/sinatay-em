
package cn.com.sinosoft.ebusiness.common.bizConfig.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeFunctionSwitch;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeSmsConfig;


/**
 *
 */
public interface GeSmsConfigService {
   /**
    * 根据主键查询短信配置
    * @param smsId
    * @return
    */
   public GeSmsConfig getGeSmsConfigByPK(String smsId);
   /**
    * 根据查询条件查询相应的短信配置
    * @param geSmsConfig
    * @param pageNo
    * @param pageSize
    * @param ind
    * @return
    */
   public Page getGeSmsConfigList(GeSmsConfig geSmsConfig,int pageNo,int pageSize);
   /**
    * 新增一条短信配置
    * @param geSmsConfig
    */
   public void addGeSmsConfig(GeSmsConfig geSmsConfig);
   /**
    * 跟新短信配置
    * @param geSmsConfig
    */
   public void updateGeSmsConfig(GeSmsConfig geSmsConfig);
   /**
    * 根据主键删除一条短信配置
    * @param smsId
    */
   public void deleteGeSmsConfigByPK(String smsId);
   /**
    * 根据适用功能代码和相应规则参数返回短信内容
    * @param functionId
    * @param params
    * @return
    */
   public String getSmsContent(String functionId,String[] params);
   /**
    * 按功能查询有效的短信配置
    * @param functionid
    * @return
    */
   public List<GeSmsConfig> getSmsConfigByFunctionId(String functionId);
}
