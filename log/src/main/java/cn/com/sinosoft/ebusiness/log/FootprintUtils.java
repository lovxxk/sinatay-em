package cn.com.sinosoft.ebusiness.log;

import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import cn.com.sinosoft.ebusiness.log.domain.LogTraceDto;

public class FootprintUtils {

	private static final ThreadLocal<LogTraceDto> footMap = new ThreadLocal<LogTraceDto>();
	
	public static final BlockingQueue<LogTraceDto> queue = new LinkedBlockingQueue<LogTraceDto>();


	public static LogTraceDto get() {
		return footMap.get();
	}


	public static void beginLog(String actionCode){
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			WebAuthenticationDetails webDetail = (WebAuthenticationDetails) SecurityContextHolder 
			.getContext().getAuthentication().getDetails(); 
			String ipAddress = webDetail.getRemoteAddress();
			String webIp = webDetail.getRemoteAddress(); 
			String hostAddress = ""; 
			try {
				InetAddress localhost = InetAddress.getLocalHost();
				hostAddress = localhost.getHostAddress();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			LogTraceDto traceDto = new LogTraceDto();
			traceDto.setIp(webIp);
			traceDto.setUserId(name);
			traceDto.setUserType("0");
			traceDto.setOperationType(actionCode);
			traceDto.setMakeDate(new Date());
			footMap.set(traceDto);
		}
	}
	
	public static void endLog(String resultCode,String resultReason){
		LogTraceDto traceDto = footMap.get();
		if(traceDto !=null){
			traceDto.setResult(resultCode);
			queue.offer(traceDto);
			footMap.remove();
		}
	}
}
