package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class AnnuityPayoutDurationMode extends EnumDictionary  {

	/**
	 * �����ȡ����ö�ٱ�
	 */
	private static final long serialVersionUID = 3436735709222364979L;
	
	public static final AnnuityPayoutDurationMode	AGE =new AnnuityPayoutDurationMode("AGE", "����ĳ����",0);
	
	public static final AnnuityPayoutDurationMode	ANNUAL =new AnnuityPayoutDurationMode("ANNUAL", "���/����",1);
	
	public static final AnnuityPayoutDurationMode	MONTHLY =new AnnuityPayoutDurationMode("MONTHLY", "�½�/����",2);
	
	public static final AnnuityPayoutDurationMode	DAILY =new AnnuityPayoutDurationMode("DAILY", "�����",3);
	
	public static final AnnuityPayoutDurationMode	SINGLE =new AnnuityPayoutDurationMode("SINGLE", "����/һ������ȡ",4);
	
	public static final AnnuityPayoutDurationMode	LIFE_TIME =new AnnuityPayoutDurationMode("LIFE_TIME", "����",5);
	
	public static final AnnuityPayoutDurationMode	RANDOM =new AnnuityPayoutDurationMode("RANDOM", "����",6);
	
	public static final AnnuityPayoutDurationMode QUARTERLY =new AnnuityPayoutDurationMode("QUARTERLY", "���ɻ���",7);
	
	public static final AnnuityPayoutDurationMode	SEMI_ANNUAL =new AnnuityPayoutDurationMode("SEMI_ANNUAL", "����ɻ������",8);
	
	public static final AnnuityPayoutDurationMode	INTERSET_ACCRUED =new AnnuityPayoutDurationMode("INTERSET_ACCRUED", "�ۻ���Ϣ",10);
	
	public static final AnnuityPayoutDurationMode	UNKNOWN =new AnnuityPayoutDurationMode("UNKNOWN", "��ȷ��",11);
	
	public static final AnnuityPayoutDurationMode	OTHER =new AnnuityPayoutDurationMode("OTHER", "����",12);
	
	public static final AnnuityPayoutDurationMode	THREE_YEAR =new AnnuityPayoutDurationMode("THREE_YEAR", "������",13);
	
	public static final AnnuityPayoutDurationMode	IMMEDIACY =new AnnuityPayoutDurationMode("IMMEDIACY", "ֱ����ȡ",14);
	
	public static final AnnuityPayoutDurationMode	LEDGER_PREMIUM =new AnnuityPayoutDurationMode("LEDGER_PREMIUM", "�ֽ�����",15);
	
	public static final AnnuityPayoutDurationMode	CASH =new AnnuityPayoutDurationMode("CASH", "�ֽ���ȡ",16);
	
	public static final AnnuityPayoutDurationMode	NONE =new AnnuityPayoutDurationMode("NONE", "δ֪",17);

	 public AnnuityPayoutDurationMode(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
			// TODO Auto-generated constructor stub
		}
	
}
