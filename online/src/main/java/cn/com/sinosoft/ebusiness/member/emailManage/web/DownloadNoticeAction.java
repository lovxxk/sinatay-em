package cn.com.sinosoft.ebusiness.member.emailManage.web;

import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;



public class DownloadNoticeAction extends Struts2Action {

	private static final long serialVersionUID = -5033554416980848147L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-16
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// 文件名
	private String fileName;
	private static Logger log = LoggerFactory
			.getLogger(DownloadNoticeAction.class);
	private String url;
	@Autowired
	private GeCodeService geCodeService;
	// 返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
	public InputStream getDownloadFile() throws Exception {
		
		this.url = geCodeService.findCodeCName("01", "FileDownPath")+"/"+url;
		this.fileName = url.substring(url.lastIndexOf("/") + 1);
		// 解解乱码
		this.fileName = new String(this.fileName.getBytes("GBK"), "ISO-8859-1");
		log.info("通知书下载.....:"+url);
		System.out.println("通知书下载.....:"+url);
		FileInputStream is = new FileInputStream(new File(url));
		return is;
	}

	
	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
