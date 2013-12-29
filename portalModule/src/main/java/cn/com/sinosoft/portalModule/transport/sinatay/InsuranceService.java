package cn.com.sinosoft.portalModule.transport.sinatay;

public abstract class InsuranceService {
	
	public String process(InsuranceVerifiable ins){
		//���õķ����ĵķ���,�������ת��xml���͸����ġ�
		//�˴����Ǵ󲿷ֽӿڶ�����Ҫ�����ֱ�ӷ������ģ����Կ��ǲ�������xml��������ValidationContext���Ӷ�Ӧ�����xml��������ʵ��InsuranceVerifiable�ӿڣ��� context����˷�����
		beforeProcess("");
		String xmlMsg = "InsuranceVerifiable convert to xml string";
		
		//�˴���ʵ�ֹ��ýӿڣ�ʡ��
		Object  convertFromReturnXml = new Object();
		afterProcess(convertFromReturnXml);
		return null;
	}
	
	public abstract void beforeProcess(Object reObjectObj);
	
	public abstract void afterProcess(Object reObjectObj);
}
