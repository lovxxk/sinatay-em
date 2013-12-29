package cn.com.sinosoft.portalModule.transport.sinatay;

public abstract class InsuranceService {
	
	public String process(InsuranceVerifiable ins){
		//共用的发报文的方法,处理过后转成xml发送给核心。
		//此处若是大部分接口都不需要处理可直接发往核心，可以考虑参数接受xml。或者在ValidationContext增加对应对象的xml报文让其实现InsuranceVerifiable接口，将 context传入此方法。
		beforeProcess("");
		String xmlMsg = "InsuranceVerifiable convert to xml string";
		
		//此处需实现公用接口，省略
		Object  convertFromReturnXml = new Object();
		afterProcess(convertFromReturnXml);
		return null;
	}
	
	public abstract void beforeProcess(Object reObjectObj);
	
	public abstract void afterProcess(Object reObjectObj);
}
