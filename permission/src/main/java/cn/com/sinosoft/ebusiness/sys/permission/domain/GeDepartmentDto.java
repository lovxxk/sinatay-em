package cn.com.sinosoft.ebusiness.sys.permission.domain;

public class GeDepartmentDto {
	
	//机构编码
	private String DEPTID;
	//机构名称
	private String DEPTNAME;
	//是否为末级机构（Y/N)
	private String NEXTCODE;
	
	public String getDEPTID() {
		return DEPTID;
	}
	public void setDEPTID(String dEPTID) {
		DEPTID = dEPTID;
	}
	public String getDEPTNAME() {
		return DEPTNAME;
	}
	public void setDEPTNAME(String dEPTNAME) {
		DEPTNAME = dEPTNAME;
	}
	public String getNEXTCODE() {
		return NEXTCODE;
	}
	public void setNEXTCODE(String nEXTCODE) {
		NEXTCODE = nEXTCODE;
	}
	
	
	

}
