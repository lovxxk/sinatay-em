
package cn.com.sinosoft.ebusiness.common.bizConfig.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeDeptMail;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeSmsConfig;


/**
 *
 */
public interface GeDeptMailService {
   /**
    * ����������ѯ��������
    * @param deptID
    * @return
    */
   public GeDeptMail getGeDeptMailByPK(String deptMailID);
  /**
   * ���ݲ�ѯ������ѯ��Ӧ�Ļ�������
   * @param geDeptMail
   * @param pageNo
   * @param pageSize
   * @param ind
   * @return
   */
   public Page getGeDeptMailList(GeDeptMail geDeptMail,int pageNo,int pageSize,String ind,String authorityid);
   /**
    * ����һ����������
    * @param geDeptMail
    */
   public void addGeDeptMail(GeDeptMail geDeptMail);
   /**
    * ���»�������
    * @param geDeptMail
    */
   public void updateGeDeptMail(GeDeptMail geDeptMail);
   /**
    * ��������ɾ��һ����������
    * @param deptMailID
    */
   public void deleteGeDeptMailByPK(String deptMailID);
   /**
    * ��ѯ��������
    * deptId ----  ������ַ
    * functionID ----------  ����ID
    * */
   public List<GeDeptMail> findGeDeptMailByRule(String deptId,String functionId);
}
