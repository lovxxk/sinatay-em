
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
    * ����������ѯ��������
    * @param smsId
    * @return
    */
   public GeSmsConfig getGeSmsConfigByPK(String smsId);
   /**
    * ���ݲ�ѯ������ѯ��Ӧ�Ķ�������
    * @param geSmsConfig
    * @param pageNo
    * @param pageSize
    * @param ind
    * @return
    */
   public Page getGeSmsConfigList(GeSmsConfig geSmsConfig,int pageNo,int pageSize);
   /**
    * ����һ����������
    * @param geSmsConfig
    */
   public void addGeSmsConfig(GeSmsConfig geSmsConfig);
   /**
    * ���¶�������
    * @param geSmsConfig
    */
   public void updateGeSmsConfig(GeSmsConfig geSmsConfig);
   /**
    * ��������ɾ��һ����������
    * @param smsId
    */
   public void deleteGeSmsConfigByPK(String smsId);
   /**
    * �������ù��ܴ������Ӧ����������ض�������
    * @param functionId
    * @param params
    * @return
    */
   public String getSmsContent(String functionId,String[] params);
   /**
    * �����ܲ�ѯ��Ч�Ķ�������
    * @param functionid
    * @return
    */
   public List<GeSmsConfig> getSmsConfigByFunctionId(String functionId);
}
