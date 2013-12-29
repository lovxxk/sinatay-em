package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class SaveMessageType extends EnumDictionary {

	private static final long serialVersionUID = 6504795456968392122L;

	public static final SaveMessageType OTHER = new SaveMessageType("OTHER", "其他", "Other", 0);
	
	public static final SaveMessageType DATABASE = new SaveMessageType("DATABASE", "数据库", "DATABASE", 1);
	
	public static final SaveMessageType FILESYSTEM = new SaveMessageType("FILESYSTEM", "文件", "FILE", 2);
	
	public static final SaveMessageType DATABASE_FILESYSTEM = new SaveMessageType("DATABASE_FILESYSTEM", "数据库and文件", "DATABASE_FILESYSTEM", 3);
	
	
	public SaveMessageType(String enumName, String dataName, String dataENName,
			Integer value) {
		super(enumName, dataName, dataENName, value);
		// TODO Auto-generated constructor stub
	}
	
}
