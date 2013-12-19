package cn.com.sinosoft.ebusiness.payment.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.ebusiness.payment.domain.GeTransmsgLog;
import cn.com.sinosoft.ebusiness.payment.service.facade.GeTransmsgLogService;

public class GeTransmsgLogServiceSpringImpl extends
GenericDaoHibernate<GeTransmsgLog,String> implements GeTransmsgLogService{

	@Override
	public String insertGeTransmsgLog(GeTransmsgLog geTransmsgLog){
		// TODO Auto-generated method stub
      super.save(geTransmsgLog);
      if(null != geTransmsgLog){
    	  return geTransmsgLog.getId();
      }else{
    	  return null;
      }
	}

	@Override
	public void modifyGeTransmsgLog(GeTransmsgLog geTransmsgLog){
		// TODO Auto-generated method stub
       super.update(geTransmsgLog);
	}

	@Override
	public GeTransmsgLog findGeTransmsgLogById(String id){
		String hql = "select gt from GeTransmsgLog gt where gt.id= ?";
		List<GeTransmsgLog> geTransmsgLogs = super.findByHql(hql, id);
		return geTransmsgLogs.isEmpty()?null:geTransmsgLogs.get(0);
	}

	@Override
	public List<GeTransmsgLog> findGeTransmsgLogByOrderNo(String orderNo,String transFlag){
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("orderno", orderNo);
		queryRule.addEqual("transflag", transFlag);
		return super.find(queryRule);
	}

}
