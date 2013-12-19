package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class QuoteStatus extends EnumDictionary {

	private static final long serialVersionUID = 2768087728634705993L;

	public static final QuoteStatus NORMAL = new QuoteStatus("NORMAL", "正常", 1, "1" ,"1");

	public static final QuoteStatus SWITCHED_INSURANCE = new QuoteStatus("SWITCHED_INSURANCE", "已转投保", 2, "2" ,"2");

	public static final QuoteStatus SWITCHED_TELEPHONE_SALES = new QuoteStatus("SWITCHED_TELEPHONE_SALES", "已转电销", 3, "3" ,"3");

	public static final QuoteStatus CANCELLED = new QuoteStatus("CANCELLED", "已取消", 4, "4" ,"4");

	public static final QuoteStatus DELETED = new QuoteStatus("DELETED", "已删除", 9, "9" ,"9");

	public static final QuoteStatus PULL_OFF_SHELVES = new QuoteStatus("PULL_OFF_SHELVES", "下架", 10, "10" ,"10");

	public QuoteStatus(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
		// TODO Auto-generated constructor stub
	}


	public QuoteStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
