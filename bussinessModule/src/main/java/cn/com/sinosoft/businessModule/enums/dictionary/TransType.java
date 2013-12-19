package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

/**
 * ��������
 */
public class TransType extends EnumDictionary {
	
	private static final long serialVersionUID = 3829658758358898290L;

	public static final TransType ST000021 = new TransType("ST000021", "��������", 1, "ST000021", "ST000021");

	public static final TransType ST000022 = new TransType("ST000022", "���ߺ˱�_����", 2, "ST000022", "ST000022");

	public static final TransType ST000023 = new TransType("ST000023", "���߳б�", 3, "ST000023", "ST000023");
	
	public static final TransType ST000024 = new TransType("ST000024", "��ȡ�ֻ���̬����", 4, "ST000024", "ST000024");
	
	public static final TransType ST000026 = new TransType("ST000026", "Ӱ��֪ͨ", 5, "ST000026", "ST000026");
	
	public static final TransType ST010001 = new TransType("ST010001", "���֤У��", 6, "ST010001", "ST010001");
	
	public static final TransType ST000028 = new TransType("ST000028", "�ƶ��绰У��", 7, "ST000028", "ST000028");
	
	public static final TransType ST020001 = new TransType("ST020001", "֪ͨ�ͷ�ϵͳ��Ͷ��������ϯ�绰֧�ֽӿ�", 8, "ST020001", "ST020001");
	
	public static final TransType ST020002 = new TransType("ST020002", "�����ֻ�����", 9, "ST020002", "ST020002");
	
	public static final TransType ST000029 = new TransType("ST000029", "������ϸ��ѯ", 10, "ST000029", "ST000029");
	
	public static final TransType ST000034 = new TransType("ST000034", "���ߺ˱�_���ù�������", 11, "ST000034", "ST000034");
	
	public TransType(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
	
}
