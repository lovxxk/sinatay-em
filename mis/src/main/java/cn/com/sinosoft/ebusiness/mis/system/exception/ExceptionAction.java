package cn.com.sinosoft.ebusiness.mis.system.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeMonitorAppException;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.ExceptionService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

public class ExceptionAction extends Struts2Action{
	private ExceptionService exceptionService ;
	private Page page;
	private GeMonitorAppException geMonitorAppException ;
	
	public GeMonitorAppException getGeMonitorAppException() {
		return geMonitorAppException;
	}

	public void setGeMonitorAppException(GeMonitorAppException geMonitorAppException) {
		this.geMonitorAppException = geMonitorAppException;
	}

	private String message;//返回页面的提示信息
	private int flag;//页面提示选择器

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}
	/**
	 * 查询异常列表
	 * @return
	 */
    public String findException(){
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		QueryRule queryRule = QueryRule.getInstance();
		if(geMonitorAppException !=null){
			if(!StringUtils.isEmpty(this.geMonitorAppException.getIdentifyFlag())){
				queryRule.addLike("identifyFlag",'%'+geMonitorAppException.getIdentifyFlag()+'%');
			}
			if(null!=this.geMonitorAppException.getExceptionTime()){
			 queryRule.addSql("this_.exceptionTime >= to_date('"+getDateToDateString(geMonitorAppException.getExceptionTime())+"','yyyy-MM-dd hh24:mi:ss')and this_.exceptionTime <to_date('"+getDateToDateString(geMonitorAppException.getExceptionTime())+"','yyyy-MM-dd hh24:mi:ss')+1 order by exceptionTime desc ");
			}
		}
		try{
			page = exceptionService.findException(queryRule, pageNo, pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS ;
  } 
    /**
     *  查看异常详细
     * @return
     */
    public String viewExceptionDetil(){
    	//String id = super.getRequest().getParameter("geMonitorAppException.serialNo");
    	geMonitorAppException = exceptionService.getExceptionDetil(geMonitorAppException.getSerialNo());
    	return SUCCESS;
    }
    private String getDateToDateString(Date date) {
    	 try {
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 return simpleDateFormat.format(date);
    	 } catch (Exception  e) {
 			e.printStackTrace();
 		}
    	 return null ;
	}
  
  private Date getDateToDate(String date) {
	 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = simpleDateFormat.parse(date); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
}
