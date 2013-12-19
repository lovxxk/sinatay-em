package cn.com.sinosoft.ruleEngine.service.facade;

import java.util.Properties;
import java.util.Set;

/**
 * ��������ӿ�.
 */
public interface DroolsRuleService {
	/**
	 * ����ִ��
	 * 
	 * @param ruleFlowName
	 *            ������������
	 * @param fact
	 *            ���ض���
	 * @param ruleResourceSet
	 *            ����ִ���õ������й����ļ�
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object fact ,Set<String> ruleResourceSet);
	/**
	 * ����ִ��
	 * 
	 * @param ruleFlowName
	 *            ������������
	 * @param objectMap
	 *            �����������󼯺�
	 * @param ruleResourceSet
	 *            ����ִ���õ������й����ļ�
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object returnObj,Set<Object> insertObjSet,Set<String> ruleResourceSet);
	
	/**
	 * ����ִ��(BRMS)
	 * 
	 * @param ruleFlowName
	 *            ������������
	 * @param fact
	 *            ���ض���
	 * @param propertiesFilePath
	 *            drools�����ļ�
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object fact ,String propertiesFilePath);
	
	/**
	 * ����ִ��(BRMS)
	 * 
	 * @param ruleFlowName
	 *            ������������
	 * @param returnObj
	 *            ���صĶ���
	 * @param insertObjSet
	 *            ����Ķ��󼯺�
	 * @param propertiesFilePath
	 *            drools�����ļ�
	 * @return
	 */
	public Object executeRules(String ruleFlowName, Object insertObjSet,Object returnObj, String propertiesFilePath);
	/**
	 * ����ִ��(BRMS)
	 * 
	 * @param ruleFlowName
	 *            ������������
	 * @param returnObj
	 *            ���صĶ���
	 * @param insertObjSet
	 *            ����Ķ��󼯺�
	 * @param propertiesFilePath
	 *            drools�����ļ�
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object returnObj,Set<Object> insertObjSet,String propertiesFilePath);
	
	/**
	 *  
	 * ���𼶱�������ж�Ӧת��
	 * @param prpLdeflossMain
	 * @param tabelName
	 * @param nextTableName
	 * @param strings
	 */
	
	public String getVerifyLevel(String level);
	/**
	 * ����ִ��(BRMS)
	 * 
	 * @param ruleFlowName
	 *            ������������
	 * @param returnObj
	 *            ���صĶ���
	 * @param insertObjSet
	 *            ����Ķ��󼯺�
	 * @param config
	 *            drools�����ļ�
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object returnObj,Object insertObjSet,Properties config);
}
