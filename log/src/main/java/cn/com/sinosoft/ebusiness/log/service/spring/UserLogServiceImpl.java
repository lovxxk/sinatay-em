package cn.com.sinosoft.ebusiness.log.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.ebusiness.log.FootprintUtils;
import cn.com.sinosoft.ebusiness.log.domain.GeMonitorUserlog;
import cn.com.sinosoft.ebusiness.log.domain.LogTraceDto;
import cn.com.sinosoft.ebusiness.log.service.facade.UserLogService;

public class UserLogServiceImpl extends GenericDaoHibernate<GeMonitorUserlog, String> implements UserLogService {

	private Thread t;
	
	public void save(GeMonitorUserlog geMonitorUserlog){
		super.save(geMonitorUserlog);
	}
	
	/**
	 * 服务启动时就队列进行监控
	 */
	public void init(){
		t = new Thread(new SaveFootPrint());
		t.start();
	}
	
	public void destory(){
		t.interrupt();
	}
	
	public class SaveFootPrint implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					LogTraceDto logTraceDto = FootprintUtils.queue.take();
					if(logTraceDto != null){
						GeMonitorUserlog geMonitorUserlog = new GeMonitorUserlog();
						generateGeUserlog(logTraceDto,geMonitorUserlog);
						List<GeMonitorUserlog> children = new ArrayList<GeMonitorUserlog>(logTraceDto.getChildren().size());
						short index = 0;
						for(LogTraceDto childLog : logTraceDto.getChildren()){
							GeMonitorUserlog childUserLog = new GeMonitorUserlog();
							generateGeUserlog(childLog,childUserLog);
							childUserLog.setSerialNo((short)index++);
							childUserLog.setGeMonitorUserlog(geMonitorUserlog);
							children.add(childUserLog);
						}
						geMonitorUserlog.setGeMonitorUserlogs(children);
						save(geMonitorUserlog);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		private void generateGeUserlog(LogTraceDto logTraceDto,GeMonitorUserlog geMonitorUserlog){
			geMonitorUserlog.setIp(logTraceDto.getIp());
			geMonitorUserlog.setMakeDate(logTraceDto.getMakeDate());
			geMonitorUserlog.setResult(logTraceDto.getResult());
			geMonitorUserlog.setUserID(logTraceDto.getUserId());
			geMonitorUserlog.setUserType(logTraceDto.getUserType());
			geMonitorUserlog.setOperation(logTraceDto.getOperation());
			geMonitorUserlog.setOperationtype(logTraceDto.getOperationType());
		}
	}
	
	
}
