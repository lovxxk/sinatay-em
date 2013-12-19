
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
    * 根据主键查询机构邮箱
    * @param deptID
    * @return
    */
   public GeDeptMail getGeDeptMailByPK(String deptMailID);
  /**
   * 根据查询条件查询相应的机构邮箱
   * @param geDeptMail
   * @param pageNo
   * @param pageSize
   * @param ind
   * @return
   */
   public Page getGeDeptMailList(GeDeptMail geDeptMail,int pageNo,int pageSize,String ind,String authorityid);
   /**
    * 新增一条机构邮箱
    * @param geDeptMail
    */
   public void addGeDeptMail(GeDeptMail geDeptMail);
   /**
    * 跟新机构邮箱
    * @param geDeptMail
    */
   public void updateGeDeptMail(GeDeptMail geDeptMail);
   /**
    * 根据主键删除一条机构邮箱
    * @param deptMailID
    */
   public void deleteGeDeptMailByPK(String deptMailID);
   /**
    * 查询机构邮箱
    * deptId ----  机构地址
    * functionID ----------  功能ID
    * */
   public List<GeDeptMail> findGeDeptMailByRule(String deptId,String functionId);
}
