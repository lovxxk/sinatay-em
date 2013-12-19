package cn.com.sinosoft.ebusiness.common.party.service.facade;

import java.util.List;

import javax.servlet.http.HttpSession;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;


import cn.com.sinosoft.ebusiness.common.party.domain.DistributionStatusDto;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterGoods;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterInfo;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterOrder;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterSerialNumber;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterService;

/**
 *第三方管理功能
 */
public interface ThirdParterService {
	
	/**
	 * 保存第三方合作伙伴信息-GE_ThirdParter_Info 
	 * @param geThirdParterInfo
	 */
	public void addGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo);
	/**
	 * 查询第三方合作伙伴信息
	 * @return
	 */
	public Page findGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo,int pageNo,int pageSize);
	
	/**
	 * 查询第三方合作伙伴信息 通过多个ID来查询
	 * @param geThirdParterInfo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterInfoByDeptManayBydeptId(GeThirdParterInfo geThirdParterInfo,int pageNo,int pageSize);
	/**
	 * 按条件来查询,自己组装条件
	 * @return
	 */
	public Page findGeThirdParterInfoByCondition(QueryRule queryRule,int pageNo,int pageSize);
	/**
	 * 查询第三方合作伙伴信息 按主键查询
	 * @param thirdParterID
	 * @return
	 */
	public GeThirdParterInfo findGeThirdParterInfoByThirdParterID(String thirdParterID);
	
	/**
	 * 更新 第三方合作伙伴信息-GE_ThirdParter_Info
	 * 更新联系人
	 * @param geThirdParterInfo
	 */
	public void updateGeThirdParterInfoAndGeThirdParterContact(GeThirdParterInfo geThirdParterInfo)throws Exception;
	/**
	 * 按主键删除
	 * @param geThirdParterInfo
	 */
	public boolean deleteGeThirdParterInfo(String thirdParterID);
	/**
	 * 查询第三方合作伙伴  查询全部
	 * @return
	 */
	public List<GeThirdParterInfo> findGeThirdParterInfoAll();
	/**
	 * 保存第三方服务产品
	 * @param geThirdParterService
	 */
	public void addGeThirdParterService(GeThirdParterService geThirdParterService);
	/**
	 * 删除第三方联系人 按主键删除
	 */
	public void deleteGeThirdParterContactByContactID(String contactID);
	/**
	 * 查询第三方服务产品
	 * 分页查询
	 * @param geThirdParterService
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterService(GeThirdParterService geThirdParterService,int pageNo,int pageSize);
	/**
	 * 查询第三方服务产品
	 * 不分页并且按条件查询
	 * @param geThirdParterService
	 * @return
	 */
	public List<GeThirdParterService> findGeThirdParterService(GeThirdParterService geThirdParterService);
	/**
	 * 查询第三方产品
	 * 按主键查询
	 * @param itemID
	 * @return
	 */
	
	public GeThirdParterService findGeThirdParterServiceByItemID(String itemID);
	//为查询使用
	public GeThirdParterService findGeThirdParterServiceByItemIDForView(String thirdParterID);
	public Page findGeThirdParterServiceByDefaultPermession(HttpSession session ,GeThirdParterService geThirdParterService,int pageNo,int pageSize);
	//营销活动使用，通过deptId来查询公司的
	public List findGeThirdParterServiceByDeptId(String deptId ,int pageNo,int pageSize,GeThirdParterService geThirdParterService);
	public Long findGeThirdParterServiceByDeptIdCount(String deptId,GeThirdParterService geThirdParterService);
	/**
	 * 修改第三方产品
	 * @param geThirdParterService
	 */
	public void updateGeThirdParterService(GeThirdParterService geThirdParterService);
	/**
	 *删除第三方产品
	 */
	public boolean deleteGeThirdPartterServiceByItemID(String itemID);
	/**
	 * 保存第三方产品/服务流水信息表-GE_ThirdParter_SerialNumber
	 */
	public void addGeThirdParterSerialNumber(GeThirdParterSerialNumber geThirdParterSerialNumber);
	/**
	 * 查询要赠送的商品，并且进行分页
	 * @param geThirdParterSerialNumber
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterSerialNumber(boolean isDefaultPermission,HttpSession session,GeThirdParterSerialNumber geThirdParterSerialNumber,int pageNo,int pageSize);
	/**
	 * 更新状态，将无效改为有效
	 * @param proposalNos
	 */
	public void updateGeThirdParterSerialNumberValidInd(String searialNo);
	/**
	 * 更新状态，将有效改为无效
	 * @param proposalNos
	 */
	public void updateGeThirdParterSerialNumberInValidInd(String searialNo);
	/**
	 * 根据订单号查询物流信息
	 * @param 订单号 -bookNo
	 * @return
	 */
	public DistributionStatusDto getDistributionStatus(String  bookNo);
	/**
	 * 查询第三方合作伙伴信息 
	 * 不进行分页查询分页查询
	 * @return
	 */
	public List<GeThirdParterInfo> findGethiGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo);
	/**
	 * 第三方订单关系表-GE_ThirdParter_Order
	 * @param geThirdParterOrder
	 */
	public void addGeThirdParterOrder(GeThirdParterOrder geThirdParterOrder);
	/**
	 * 通过订单号来查询
	 * @param orderNo
	 * @return
	 */
	public GeThirdParterOrder findGeThirdParterOrderByOrderNo(String orderNo);
	/**
	 * 更改第三方订单表信息
	 * @param geThirdParterOrder
	 */
	public void updateGeThirdParterOrder(GeThirdParterOrder geThirdParterOrder);
	/**
	 * 根据投保单号去查商品 
	 * @return
	 */
	public List<GeThirdParterGoods> findGeThirdParterGoods(List<String> proposalNoList);
	
	//查询赠送产品
	
	public GeThirdParterSerialNumber findGeThirdParterSerialNumberByNo(String searialNo);
}
