package cn.com.sinosoft.ebusiness.payment.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.payment.domain.GeTransmsgLog;
import cn.com.sinosoft.ebusiness.payment.domain.GeWapPayconfirmResult;
/**
 * ������������־service<br>
 * ���ߣ�renjun <br>
 * �޸����ڣ�2013-1-9����03:10:05 <br>
 * E-mail:  renjun@sinosoft.com.cn <br>
 */
public interface GeTransmsgLogService{
    /**
     * ��������: insertGeTransmsgLog<br>
     * �����������־��¼
     * ����: renjun
     * �޸����ڣ�2013-1-9����12:50:28
     * @param geTransmsgLog
     */
	public String insertGeTransmsgLog(GeTransmsgLog geTransmsgLog);	
	/**
	 * ��������: modifyGeTransmsgLog<br>
	 * ������������־��¼
	 * ����: renjun
	 * �޸����ڣ�2013-1-9����12:50:48
	 * @param geTransmsgLog
	 */
	public void modifyGeTransmsgLog(GeTransmsgLog geTransmsgLog);
	/**
	 * ��������: findGeTransmsgLogById<br>
	 * ����������Id��ѯ������־��¼
	 * ����: renjun
	 * �޸����ڣ�2013-1-9����03:09:33
	 * @param id
	 * @return
	 */
    public GeTransmsgLog findGeTransmsgLogById(String id);
    /**
     * ��������: findGeTransmsgLogByOrderNo<br>
     * ��������ѯ���ýӿ���־
     * ����: renjun
     * �޸����ڣ�2013-1-10����08:33:38
     * @param orderNo
     * @param string 
     * @return
     */
	public List<GeTransmsgLog> findGeTransmsgLogByOrderNo(String orderNo, String string);

}
