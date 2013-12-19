package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class CampaignStatus extends EnumDictionary  {

	/**
	 * 营销活动状态
	 */
	private static final long serialVersionUID = 3436735709222364979L;
	
	public static final CampaignStatus	CLOSE = new CampaignStatus("CLOSE", "关闭", 0);
	
	public static final CampaignStatus	OPEN = new CampaignStatus("OPEN", "开启", 1);
	
	public static final CampaignStatus	MAINTAIN = new CampaignStatus("MAINTAIN", "维护", 2);

	 public CampaignStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		}
	
}
