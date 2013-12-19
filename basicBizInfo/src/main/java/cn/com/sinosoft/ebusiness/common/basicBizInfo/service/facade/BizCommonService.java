package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade;


import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import cn.com.sinosoft.businessModule.network.domain.Network;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeArea;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelate;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelateId;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRiskClassCodeAndRiskCode;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskAndKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskClauseKindData;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskInputCoreDto;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;


/**
 * ����Service�ӿ�,�ṩ���뷭��,��ȡ���ŵ�ͨ�ù���
 *
 */
public interface BizCommonService {
	/**
	 * ���ݴ�������,�����ô�������
	 * @param codeType ��������
	 * @param codeCode ����
	 * @return ��������
	 */
	public String findCodeName(String codeType, String codeCode);
	/**
	 * ���ݴ�������,��ø�����ȫ�����뼯��
	 * @param codeType ��������
	 * @return ���뼯��
	 */
	public List<GeCode> findCodeList(String codeType);
	/**
	 * ��ȡ��ǰҵ������к�
	 * 
	 * @param typeCode ҵ�����
	 * @param date     ҵ������
	 * @param clazz    ����ʱ����,Ϊ���ø÷���Ҫ����Ķ�Ӧ��hibernateʵ����
	 * 01 --��������
	 * 02 --�������㵥��
	 * 03 --��ҵID��ˮ��
	 * �ڵ������ ��ˮ��  ��ʱ��  һ��Ҫ����� ˭���������ˮ�ĵ�һ������.
	 * @return (�����к�����ʱ,��������,������ʱ,����ȱʧ����С��)
	 */
	public  String takeSerialNo(String typeCode, Date dateTemp,Class<?> clazz);
	/**
	 * ��ҵ�����ɾ����Ӧ�����к�ʱ()��Ҫ���ø÷���,��ɾ�������к��ͻ��������
	 * @param serialNo ��ɾ�������
	 */
	public void revertMaxNo(String serialNo);
	/**
	 * ���ʡ���б�
	 * @return ʡ���б���
	 */
	public List<GeArea> findAllProvince();
	
	/**
	 * ����ʡ�ݴ����ø�ʡ���³����б���
	 * ���upperareacodeΪnull,"","   "������ȫ�����г����б�
	 * @param upperareacode ʡ�ݴ���
	 * @return ���м���
	 */
	public List<GeArea> findCityListByProvince(String upperareacode);
	
	
	/**
	 * �������ִ��������ֶ���
	 * @param riskCode ���ִ���
	 * @return ���ֶ���
	 */
	public GeRisk findGeRiskByCode(String riskCode);
	
	


	/**
	 * ���ݵ��������õ�������
	 * @param areacode ��������
	 * @return ��������or NULL
	 */
	public GeArea findAreaByAreacode(String areacode);
	
	/**
	 * ��ѯ���е�����
	 * @return
	 */
	public List<GeRisk> findAllRiskCode(QueryRule queryRule);
	/**
	 * ��ѯ���֣���������ѯ ��ҳ��ѯ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeRiskList(QueryRule queryRule,int pageNo,int pageSize);
	
	public List<GeRisk> findGeRiskListWithEid(GeRisk geRisk);
	public Page findGeRiskListWithEid(GeRisk geRisk,int pageNo,int pageSize);
	/**
	 * ��ѯָ�����ִ����������ձ�
	 * @param riskCode
	 * @return
	 */
	public List<GeKind> findKindByRiskCode(String riskCode);
	
	//�����������
	public String findEidName(String riskCode);
	/**
	 * �����ձ�����ȡ��Ч���ձ�
	 * @param kindCodes
	 * @return
	 */
	public List<GeKind> findValidateKind(List<String> kindCodes);
	/**
	 * �ײ�չʾ����ȡ��Ч�ձ�
	 * @return
	 */
	public List<GeKind> findInvalidateKind();
	
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�������ձ��б�
	 * @param isFreeCombo true �����ɶ��ƣ�false �ǹ̶��ײ�
	 * @return
	 */
	public List<GeKind> findValidateMainKind(boolean isFreeCombo);
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�������ձ��б�
	 * @param isFreeCombo true �����ɶ��ƣ�false �ǹ̶��ײ�
	 * @return
	 */
	public List<GeKind> findValidateMainKind(boolean isFreeCombo,String riskcode);
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�ĸ������ձ��б�
	 * @param isFreeCombo true �����ɶ��ƣ�false �ǹ̶��ײ�
	 * @return
	 */
	public List<GeKind> findValidateAttachKind(boolean isFreeCombo);
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�ĸ������ձ��б�
	 * @param isFreeCombo true �����ɶ��ƣ�false �ǹ̶��ײ�
	 * @return
	 */
	public List<GeKind> findValidateAttachKind(boolean isFreeCombo,String riskCode);
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�Ĳ��������ձ��б�
	 * @param isFreeCombo true �����ɶ��ƣ�false �ǹ̶��ײ�
	 * @return
	 */
	public List<GeKind> findValidateDeductKind(boolean isFreeCombo);
	/**
	 * 
	 * ��ѯ�����ײ�չʾ�Ĳ��������ձ��б�
	 * @param isFreeCombo true �����ɶ��ƣ�false �ǹ̶��ײ�
	 * @return
	 */
	public List<GeKind> findValidateDeductKind(boolean isFreeCombo,String riskCode);
	
	/**
	 * �������ִ���,�����ձ�����ȡ�����������еĸ�����
	 * @param riskCode
	 * @param kindCode
	 * @return
	 */
	public List<GeKind> findKindByMain(String riskCode, String kindCode);
	/**
	 * �õ� ���ĵ������ձ�ӿ�
	 */
	public void getCoreRiskAndKind(List<GeRiskClassCodeAndRiskCode> riskCodeList ,java.util.Map<String,RiskAndKind> kindMap);
	/**
	 * ͬ�����ĵ������ձ�ӿ�
	 * @param riskInputCoreDto
	 * @return
	 * @throws AxisFault 
	 */
	public List<RiskClauseKindData> synchCoreRiskAndKind(List<RiskInputCoreDto> riskInputCoreDtoList);
	
	public Map<String,List<GeKindRelate>> getKindRelateByRiskCode(String riskCode);
	
	//�������صĽӿ�
	//public boolean sendMessage(String mobile,String content);
	public boolean sendMessage(String functionId,String[] params,String mobile);
	
	/**
	 * ���շ��Ͷ��Žӿڣ��������ͣ�
	 * @param phones �ֻ�����ɵļ���
	 * @param functionId 
	 * @param params
	 * @param beginTime ��ʽ��"yyyyMMdd HHmmss"
	 * @param endTime ��ʽ��"yyyyMMdd HHmmss"
	 * @return
	 */
	public boolean sendSMFromLife(List<String> phones,String functionId,String[] params,String beginTime,String endTime);
	
	/**
	 * ���շ��Ͷ��Žӿ�(��������)
	 * @param phone �ֻ���
	 * @param functionId
	 * @param params
	 * @param beginTime ��ʽ��"yyyyMMdd HHmmss"
	 * @param endTime ��ʽ��"yyyyMMdd HHmmss"
	 * @return
	 */
	public boolean sendSMFromLife(String phone,String functionId,String[] params,String beginTime,String endTime);
	
	/**
	 * ���ռ�ʱ���Ͷ��ţ��������ͣ�
	 * @param phones �ֻ�����ɵļ���
	 * @param functionId 
	 * @param params
	 * @return
	 */
	public boolean sendSMFromLife(List<String> phones,String functionId,String[] params);
	
	
	/**
	 * ���ռ�ʱ���Ͷ���
	 * @param phone
	 * @param functionId
	 * @param params
	 * @return
	 */
	public boolean sendSMFromLife(String phone,String functionId,String[] params);
	
	/**
	 * ���ռ�ʱ���Ͷ��ţ��������ͣ�
	 * @param phones �ֻ�����ɵļ���
	 * @param content ��������
	 * @return
	 */
	public boolean sendSMFromLife(List<String> phones,String content);
	
	/**
	 * ���ռ�ʱ���Ͷ���
	 * @param phone
	 * @param content
	 * @return
	 */
	public boolean sendSMFromLife(String phone,String content);
	
	public void addGeRisk(GeRisk geRisk);

	
	/**
	 * ��������ձ�
	 * @param geKind
	 * @return 
	 */
	public String addKind(GeKind geKind);
	
	/**
	 * ��ѯ���ִ��룬��������ѯ ��ҳ��ѯ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeKindList(QueryRule queryRule,int pageNo,int pageSize);
    /**
     * 
     * @param riskCode
     * @param kindCode
     * @return GeKind
     * �������ֱ������ձ�����ѯ
     */
	public GeKind findGeKindByCode(String riskCode,String kindCode);
	/**
	 * 
	 * @param riskCode
	 * @param kindCode
	 * @return GeKind
	 * �޸��ձ���Ϣǰ�Ĳ�ѯ
	 */
	public GeKind prepareUpdateGeKind(String riskCode,String kindCode);
	
	/**
	 * �޸��ձ���Ϣ
	 */
	public String updateGeEnKind(GeKind geKind);
	/**
	 * 
	 * @param list
	 *  ɾ������
	 */
	public void deleteByList(List<GeKind> list);
	/**
	 * @param riskCode
	 * @param kindCode
	 *  �����ձ�codeɾ��һ��
	 */
	public void deleteById(String riskCode,String kindCode);
	

	
	public String updateGeRisk(GeRisk geRisk);
	
	public void deleteGeRiskListById(String riskCode);
	
	public String saveGeRisk(GeRisk geRisk);
	
	public List<GeRisk> findGeRiskListBy(String riskCode,String riskCName);
	public Page getGeCardProductList(GeRisk geRisk,int pageNo,int pageSize);
	
	public List<String> findEid(String riskCode);
	
	/**
	 * 
	 * @param kindCode
	 * @return ��������
	 *  У����ձ��Ƿ����ײ�����ʹ��
	 */
	public String checkKindCodeUnique(String kindCode,String riskCode);
	
	/**
	 * 
	 * @param gekind
	 * @return ��������
	 * У��orderno�Ƿ��ظ�
	 */
	public String checkOrderNo(GeKind gekind);
	
	/**
	 *  ������ո����չ�ϵ
	 */
	public String addKindRelate(GeKindRelate geKindRelate);
	/**
	 * ��ѯ�ձ��ϵ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findKindRelateList(QueryRule queryRule,int pageNo,int pageSize);
	/**
	 * �ձ��ϵά���޸�
	 * @param geKindRelate
	 * @return
	 */
	public String modifyKindRelate(GeKindRelate geKindRelate);
	public GeKindRelate viewKindRelate(GeKindRelate geKindRelate);
	
	/**
	 * У���ձ��ϵ�Ƿ��ظ�
	 * @return
	 */
	public Map<String,Object> checkRelate(GeKindRelateId relateId);
	public String checkAddFlag(GeKind ge);
	public String checkFlag(GeKindRelate geRelate,String flag);
	/**
	 * 
	 * @param riskCode
	 * @param relateKindCode
	 * @return ���븽���ղ�ѯ������
	 */
	public List<GeKindRelate> findRelateKindCode(String riskCode, String relateKindCode);
	
	/**
	 *  �жϸø������Ƿ������չ�ϵ 
	 * @param riskCode
	 * @param kindCode
	 * @param kindFlag
	 * @return
	 */
	public Map<String,Object> vaildateRelateCode(String riskCode,String kindCode);
	/**
	 *  ��������orderNO���� 
	 * @param riskCode
	 * @param kindCode
	 */
	public void updateGeEnKindOrderNo(GeKind geKind);
}
