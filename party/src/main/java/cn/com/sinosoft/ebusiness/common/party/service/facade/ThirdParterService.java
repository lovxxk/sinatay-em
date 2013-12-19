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
 *������������
 */
public interface ThirdParterService {
	
	/**
	 * ������������������Ϣ-GE_ThirdParter_Info 
	 * @param geThirdParterInfo
	 */
	public void addGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo);
	/**
	 * ��ѯ���������������Ϣ
	 * @return
	 */
	public Page findGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo,int pageNo,int pageSize);
	
	/**
	 * ��ѯ���������������Ϣ ͨ�����ID����ѯ
	 * @param geThirdParterInfo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterInfoByDeptManayBydeptId(GeThirdParterInfo geThirdParterInfo,int pageNo,int pageSize);
	/**
	 * ����������ѯ,�Լ���װ����
	 * @return
	 */
	public Page findGeThirdParterInfoByCondition(QueryRule queryRule,int pageNo,int pageSize);
	/**
	 * ��ѯ���������������Ϣ ��������ѯ
	 * @param thirdParterID
	 * @return
	 */
	public GeThirdParterInfo findGeThirdParterInfoByThirdParterID(String thirdParterID);
	
	/**
	 * ���� ���������������Ϣ-GE_ThirdParter_Info
	 * ������ϵ��
	 * @param geThirdParterInfo
	 */
	public void updateGeThirdParterInfoAndGeThirdParterContact(GeThirdParterInfo geThirdParterInfo)throws Exception;
	/**
	 * ������ɾ��
	 * @param geThirdParterInfo
	 */
	public boolean deleteGeThirdParterInfo(String thirdParterID);
	/**
	 * ��ѯ�������������  ��ѯȫ��
	 * @return
	 */
	public List<GeThirdParterInfo> findGeThirdParterInfoAll();
	/**
	 * ��������������Ʒ
	 * @param geThirdParterService
	 */
	public void addGeThirdParterService(GeThirdParterService geThirdParterService);
	/**
	 * ɾ����������ϵ�� ������ɾ��
	 */
	public void deleteGeThirdParterContactByContactID(String contactID);
	/**
	 * ��ѯ�����������Ʒ
	 * ��ҳ��ѯ
	 * @param geThirdParterService
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterService(GeThirdParterService geThirdParterService,int pageNo,int pageSize);
	/**
	 * ��ѯ�����������Ʒ
	 * ����ҳ���Ұ�������ѯ
	 * @param geThirdParterService
	 * @return
	 */
	public List<GeThirdParterService> findGeThirdParterService(GeThirdParterService geThirdParterService);
	/**
	 * ��ѯ��������Ʒ
	 * ��������ѯ
	 * @param itemID
	 * @return
	 */
	
	public GeThirdParterService findGeThirdParterServiceByItemID(String itemID);
	//Ϊ��ѯʹ��
	public GeThirdParterService findGeThirdParterServiceByItemIDForView(String thirdParterID);
	public Page findGeThirdParterServiceByDefaultPermession(HttpSession session ,GeThirdParterService geThirdParterService,int pageNo,int pageSize);
	//Ӫ���ʹ�ã�ͨ��deptId����ѯ��˾��
	public List findGeThirdParterServiceByDeptId(String deptId ,int pageNo,int pageSize,GeThirdParterService geThirdParterService);
	public Long findGeThirdParterServiceByDeptIdCount(String deptId,GeThirdParterService geThirdParterService);
	/**
	 * �޸ĵ�������Ʒ
	 * @param geThirdParterService
	 */
	public void updateGeThirdParterService(GeThirdParterService geThirdParterService);
	/**
	 *ɾ����������Ʒ
	 */
	public boolean deleteGeThirdPartterServiceByItemID(String itemID);
	/**
	 * �����������Ʒ/������ˮ��Ϣ��-GE_ThirdParter_SerialNumber
	 */
	public void addGeThirdParterSerialNumber(GeThirdParterSerialNumber geThirdParterSerialNumber);
	/**
	 * ��ѯҪ���͵���Ʒ�����ҽ��з�ҳ
	 * @param geThirdParterSerialNumber
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeThirdParterSerialNumber(boolean isDefaultPermission,HttpSession session,GeThirdParterSerialNumber geThirdParterSerialNumber,int pageNo,int pageSize);
	/**
	 * ����״̬������Ч��Ϊ��Ч
	 * @param proposalNos
	 */
	public void updateGeThirdParterSerialNumberValidInd(String searialNo);
	/**
	 * ����״̬������Ч��Ϊ��Ч
	 * @param proposalNos
	 */
	public void updateGeThirdParterSerialNumberInValidInd(String searialNo);
	/**
	 * ���ݶ����Ų�ѯ������Ϣ
	 * @param ������ -bookNo
	 * @return
	 */
	public DistributionStatusDto getDistributionStatus(String  bookNo);
	/**
	 * ��ѯ���������������Ϣ 
	 * �����з�ҳ��ѯ��ҳ��ѯ
	 * @return
	 */
	public List<GeThirdParterInfo> findGethiGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo);
	/**
	 * ������������ϵ��-GE_ThirdParter_Order
	 * @param geThirdParterOrder
	 */
	public void addGeThirdParterOrder(GeThirdParterOrder geThirdParterOrder);
	/**
	 * ͨ������������ѯ
	 * @param orderNo
	 * @return
	 */
	public GeThirdParterOrder findGeThirdParterOrderByOrderNo(String orderNo);
	/**
	 * ���ĵ�������������Ϣ
	 * @param geThirdParterOrder
	 */
	public void updateGeThirdParterOrder(GeThirdParterOrder geThirdParterOrder);
	/**
	 * ����Ͷ������ȥ����Ʒ 
	 * @return
	 */
	public List<GeThirdParterGoods> findGeThirdParterGoods(List<String> proposalNoList);
	
	//��ѯ���Ͳ�Ʒ
	
	public GeThirdParterSerialNumber findGeThirdParterSerialNumberByNo(String searialNo);
}
