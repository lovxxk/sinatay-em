package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class RequestProcessStatus extends EnumDictionary {


	/**
	 * 请求处理状态码枚举
	 */
	private static final long serialVersionUID = -3855154924768955518L;
	
	public static final RequestProcessStatus OK = new RequestProcessStatus("OK", "请求处理成功", 200);
	
	public static final RequestProcessStatus CREATED = new RequestProcessStatus("CREATED", "请求被成功创建", 201);
	
	public static final RequestProcessStatus ACCEPTED = new RequestProcessStatus("ACCEPTED", "请求已经被接受，处理未完成", 202);
    
	public static final RequestProcessStatus BADREQUEST = new RequestProcessStatus("BADREQUEST", "服务器无法理解的请求", 400);
	
	public static final RequestProcessStatus NOTFOUND = new RequestProcessStatus("NOTFOUND", "请求未找到", 402);
	
	public static final RequestProcessStatus SUCCESS = new RequestProcessStatus("SUCCESS", "交易成功", 1);
	
	public static final RequestProcessStatus VALIDATE = new RequestProcessStatus("VALIDATE", "校验失败", 0);
	
	public static final RequestProcessStatus FAIL = new RequestProcessStatus("FAIL", "交易失败", 0);
	
	public RequestProcessStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
