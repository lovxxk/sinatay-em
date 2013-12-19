package cn.com.sinosoft.ebusiness.service.epolicy.service.facade;

import cn.com.sinosoft.ebusiness.service.epolicy.domain.EFile;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.EpolicyServiceException;

public interface EPolicyService {
	
	/**
	 * ���ӱ�������
	 * @param eFile
	 * @return
	 */
	public EFile download(String policyNo)throws EpolicyServiceException;
	
	/**
	 * ���ӱ�������
	 * @param eFile
	 * @return
	 */
	public Object verify(String policyNo,String filePath)throws EpolicyServiceException;
	

}
