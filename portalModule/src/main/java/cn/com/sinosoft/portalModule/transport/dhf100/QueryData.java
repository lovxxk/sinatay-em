package cn.com.sinosoft.portalModule.transport.dhf100;

public class QueryData {

	private int pageNo;
	private int pageRowNum;
	private int pageTotal;
	private int totalRowNum;
	private String queryType;
	private Params params;
	private Datas datas;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageRowNum() {
		return pageRowNum;
	}
	public void setPageRowNum(int pageRowNum) {
		this.pageRowNum = pageRowNum;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public Params getParams() {
		return params;
	}
	public void setParams(Params params) {
		this.params = params;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getTotalRowNum() {
		return totalRowNum;
	}
	public void setTotalRowNum(int totalRowNum) {
		this.totalRowNum = totalRowNum;
	}
	public Datas getDatas() {
		return datas;
	}
	public void setDatas(Datas datas) {
		this.datas = datas;
	}
	
	
}
