package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class CampaignStatus extends EnumDictionary  {

	/**
	 * Ӫ���״̬
	 */
	private static final long serialVersionUID = 3436735709222364979L;
	
	public static final CampaignStatus	CLOSE = new CampaignStatus("CLOSE", "�ر�", 0);
	
	public static final CampaignStatus	OPEN = new CampaignStatus("OPEN", "����", 1);
	
	public static final CampaignStatus	MAINTAIN = new CampaignStatus("MAINTAIN", "ά��", 2);

	 public CampaignStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		}
	
}
