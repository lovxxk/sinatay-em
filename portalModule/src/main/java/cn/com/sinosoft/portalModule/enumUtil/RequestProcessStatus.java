package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class RequestProcessStatus extends EnumDictionary {


	/**
	 * ������״̬��ö��
	 */
	private static final long serialVersionUID = -3855154924768955518L;
	
	public static final RequestProcessStatus OK = new RequestProcessStatus("OK", "������ɹ�", 200);
	
	public static final RequestProcessStatus CREATED = new RequestProcessStatus("CREATED", "���󱻳ɹ�����", 201);
	
	public static final RequestProcessStatus ACCEPTED = new RequestProcessStatus("ACCEPTED", "�����Ѿ������ܣ�����δ���", 202);
    
	public static final RequestProcessStatus BADREQUEST = new RequestProcessStatus("BADREQUEST", "�������޷���������", 400);
	
	public static final RequestProcessStatus NOTFOUND = new RequestProcessStatus("NOTFOUND", "����δ�ҵ�", 402);
	
	public static final RequestProcessStatus SUCCESS = new RequestProcessStatus("SUCCESS", "���׳ɹ�", 1);
	
	public static final RequestProcessStatus VALIDATE = new RequestProcessStatus("VALIDATE", "У��ʧ��", 0);
	
	public static final RequestProcessStatus FAIL = new RequestProcessStatus("FAIL", "����ʧ��", 0);
	
	public RequestProcessStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
