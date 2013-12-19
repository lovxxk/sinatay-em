package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

/**
 * 交易类型
 */
public class TransType extends EnumDictionary {
	
	private static final long serialVersionUID = 3829658758358898290L;

	public static final TransType ST000021 = new TransType("ST000021", "在线试算", 1, "ST000021", "ST000021");

	public static final TransType ST000022 = new TransType("ST000022", "在线核保_存数", 2, "ST000022", "ST000022");

	public static final TransType ST000023 = new TransType("ST000023", "在线承保", 3, "ST000023", "ST000023");
	
	public static final TransType ST000024 = new TransType("ST000024", "获取手机动态密码", 4, "ST000024", "ST000024");
	
	public static final TransType ST000026 = new TransType("ST000026", "影像通知", 5, "ST000026", "ST000026");
	
	public static final TransType ST010001 = new TransType("ST010001", "身份证校验", 6, "ST010001", "ST010001");
	
	public static final TransType ST000028 = new TransType("ST000028", "移动电话校验", 7, "ST000028", "ST000028");
	
	public static final TransType ST020001 = new TransType("ST020001", "通知客服系统—投保过程坐席电话支持接口", 8, "ST020001", "ST020001");
	
	public static final TransType ST020002 = new TransType("ST020002", "发送手机短信", 9, "ST020002", "ST020002");
	
	public static final TransType ST000029 = new TransType("ST000029", "保单明细查询", 10, "ST000029", "ST000029");
	
	public static final TransType ST000034 = new TransType("ST000034", "在线核保_调用规则引擎", 11, "ST000034", "ST000034");
	
	public TransType(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
	
}
