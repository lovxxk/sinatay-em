package cn.com.sinosoft.ebusiness.payment.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.payment.domain.GeTransmsgLog;
import cn.com.sinosoft.ebusiness.payment.domain.GeWapPayconfirmResult;
/**
 * 描述：交易日志service<br>
 * 作者：renjun <br>
 * 修改日期：2013-1-9下午03:10:05 <br>
 * E-mail:  renjun@sinosoft.com.cn <br>
 */
public interface GeTransmsgLogService{
    /**
     * 方法名称: insertGeTransmsgLog<br>
     * 描述：添加日志记录
     * 作者: renjun
     * 修改日期：2013-1-9下午12:50:28
     * @param geTransmsgLog
     */
	public String insertGeTransmsgLog(GeTransmsgLog geTransmsgLog);	
	/**
	 * 方法名称: modifyGeTransmsgLog<br>
	 * 描述：更新日志记录
	 * 作者: renjun
	 * 修改日期：2013-1-9下午12:50:48
	 * @param geTransmsgLog
	 */
	public void modifyGeTransmsgLog(GeTransmsgLog geTransmsgLog);
	/**
	 * 方法名称: findGeTransmsgLogById<br>
	 * 描述：根据Id查询交易日志记录
	 * 作者: renjun
	 * 修改日期：2013-1-9下午03:09:33
	 * @param id
	 * @return
	 */
    public GeTransmsgLog findGeTransmsgLogById(String id);
    /**
     * 方法名称: findGeTransmsgLogByOrderNo<br>
     * 描述：查询调用接口日志
     * 作者: renjun
     * 修改日期：2013-1-10下午08:33:38
     * @param orderNo
     * @param string 
     * @return
     */
	public List<GeTransmsgLog> findGeTransmsgLogByOrderNo(String orderNo, String string);

}
