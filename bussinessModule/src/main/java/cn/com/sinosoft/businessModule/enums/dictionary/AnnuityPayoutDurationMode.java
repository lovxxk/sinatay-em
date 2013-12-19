package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class AnnuityPayoutDurationMode extends EnumDictionary  {

	/**
	 * 年金领取期限枚举表
	 */
	private static final long serialVersionUID = 3436735709222364979L;
	
	public static final AnnuityPayoutDurationMode	AGE =new AnnuityPayoutDurationMode("AGE", "缴至某年龄",0);
	
	public static final AnnuityPayoutDurationMode	ANNUAL =new AnnuityPayoutDurationMode("ANNUAL", "年缴/年领",1);
	
	public static final AnnuityPayoutDurationMode	MONTHLY =new AnnuityPayoutDurationMode("MONTHLY", "月缴/月领",2);
	
	public static final AnnuityPayoutDurationMode	DAILY =new AnnuityPayoutDurationMode("DAILY", "按天缴",3);
	
	public static final AnnuityPayoutDurationMode	SINGLE =new AnnuityPayoutDurationMode("SINGLE", "趸缴/一次性领取",4);
	
	public static final AnnuityPayoutDurationMode	LIFE_TIME =new AnnuityPayoutDurationMode("LIFE_TIME", "终身",5);
	
	public static final AnnuityPayoutDurationMode	RANDOM =new AnnuityPayoutDurationMode("RANDOM", "任意",6);
	
	public static final AnnuityPayoutDurationMode QUARTERLY =new AnnuityPayoutDurationMode("QUARTERLY", "季缴或季领",7);
	
	public static final AnnuityPayoutDurationMode	SEMI_ANNUAL =new AnnuityPayoutDurationMode("SEMI_ANNUAL", "半年缴或半年领",8);
	
	public static final AnnuityPayoutDurationMode	INTERSET_ACCRUED =new AnnuityPayoutDurationMode("INTERSET_ACCRUED", "累积生息",10);
	
	public static final AnnuityPayoutDurationMode	UNKNOWN =new AnnuityPayoutDurationMode("UNKNOWN", "不确定",11);
	
	public static final AnnuityPayoutDurationMode	OTHER =new AnnuityPayoutDurationMode("OTHER", "其它",12);
	
	public static final AnnuityPayoutDurationMode	THREE_YEAR =new AnnuityPayoutDurationMode("THREE_YEAR", "三年领",13);
	
	public static final AnnuityPayoutDurationMode	IMMEDIACY =new AnnuityPayoutDurationMode("IMMEDIACY", "直接领取",14);
	
	public static final AnnuityPayoutDurationMode	LEDGER_PREMIUM =new AnnuityPayoutDurationMode("LEDGER_PREMIUM", "抵交保费",15);
	
	public static final AnnuityPayoutDurationMode	CASH =new AnnuityPayoutDurationMode("CASH", "现金领取",16);
	
	public static final AnnuityPayoutDurationMode	NONE =new AnnuityPayoutDurationMode("NONE", "未知",17);

	 public AnnuityPayoutDurationMode(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
			// TODO Auto-generated constructor stub
		}
	
}
