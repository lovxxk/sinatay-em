package cn.com.sinosoft.ebusiness.cms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

import com.jcraft.jsch.ChannelSftp;

public class FileGenerate {
	public static final int ABSOLUTE_PATH = 0;//参数绝对路径
	public static final int RELATED_PATH = 1;//参数相对路径
	private static List<Map<String, String>> config;//系统配置文件，配置ftp属性
	private int pathType = ABSOLUTE_PATH;
	
	private static Logger loger = LoggerFactory.getLogger(FileGenerate.class);
	
	static {
		initConfig();
	}
	
	public static void main(String[] args) {
		FileGenerate fg = new FileGenerate();
		fg.mergeFileToFtp("/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/localhostNode01Cell/chinapay.ear/chinapay.war/upload/11/22", "d://pdf.zip");
	}
	
	/**
	 * 向目标ftp服务器上传文件，ftp参数在配置文件ftp.xml中配置
	 * @param desDirectory 目标服务器保存路径（不含文件名，最后需要加"/"）
	 * @param fis 上传文件流
	 * @param fileName 目标服务器保存文件名
	 */
	public void mergeFileToFtp(String desDirectory, FileInputStream fis, String fileName) {
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...");
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...config.size: "+config.size());
		try {
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...fis.available: "+fis.available());
		} catch (IOException e1) {
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...fis.available_Exception: "+e1.getMessage());
		}
		String temp_desDirectory = desDirectory;
		FileInputStream temp_fis = fis;
		for (int i = 0; i < config.size(); i++) {
			EdmSFTP ftp = new EdmSFTP();//ftp实现类实例
			Map<String, String> m = config.get(i);
			String host = m.get("host");
			String port = m.get("port");
			String username = m.get("username");
			String password = m.get("password");
			ChannelSftp sftp = ftp.connect(host, Integer.parseInt(port), username, password);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...ftp.connect( "+host+", "+Integer.parseInt(port)+", "+username+", "+password+" )");
			if(pathType == RELATED_PATH) {
				desDirectory = m.get("webpath") + temp_desDirectory;
			}
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...-----config.index: "+i);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...host: "+host);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...port: "+port);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...username: "+username);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...password: "+password);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...m.get(\"webpath\"): "+m.get("webpath"));
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...-----config.index: "+i);
			
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...ftp.desDirectory: "+desDirectory);
			ftp.execcmd("mkdir -p " + desDirectory); //执行命令,得到返回
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...ftp.upload( "+desDirectory+", "+temp_fis+", "+sftp+", "+fileName+" )");
			try{
				loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...temp_fis.available: "+temp_fis.available());
			}catch(Exception e){
				loger.info("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[向目标ftp服务器上传文件]...temp_fis.available_Exception: "+e.getMessage());
			}
			ftp.upload(desDirectory, temp_fis, sftp, fileName); //上传
			
			ftp.close(); //关闭连接
		}
	}
	
	/**
	 * 向目标ftp服务器上传文件，ftp参数在配置文件ftp.xml中配置
	 * @param desDirectory 目标服务器保存路径（不含文件名，最后需要加"/"）
	 * @param fis 上传文件流
	 * @param fileName 目标服务器保存文件名
	 */
	public void mergeFileToFtp(String desDirectory, FileInputStream fis, File file, String fileName) {
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...");
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...config.size: "+config.size());
		try {
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...fis.available: "+fis.available());
		} catch (IOException e1) {
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...fis.available_Exception: "+e1.getMessage());
		}
		String temp_desDirectory = desDirectory;
		File temp_File = file;
		FileInputStream temp_fis = null;
		for (int i = 0; i < config.size(); i++) {
			try {
				temp_fis = new FileInputStream(temp_File);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			EdmSFTP ftp = new EdmSFTP();//ftp实现类实例
			Map<String, String> m = config.get(i);
			String host = m.get("host");
			String port = m.get("port");
			String username = m.get("username");
			String password = m.get("password");
			ChannelSftp sftp = ftp.connect(host, Integer.parseInt(port), username, password);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...ftp.connect( "+host+", "+Integer.parseInt(port)+", "+username+", "+password+" )");
			if(pathType == RELATED_PATH) {
				desDirectory = m.get("webpath") + temp_desDirectory;
			}
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...-----config.index: "+i);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...host: "+host);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...port: "+port);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...username: "+username);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...password: "+password);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...m.get(\"webpath\"): "+m.get("webpath"));
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...-----config.index: "+i);
			
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...ftp.desDirectory: "+desDirectory);
			try{
				ftp.execcmd("mkdir -p " + desDirectory); //执行命令,得到返回
				loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...ftp.upload( "+desDirectory+", "+temp_fis+", "+sftp+", "+fileName+" )");
				loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...temp_fis.available: "+temp_fis.available());
			}catch(Exception e){
				loger.info("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[向目标ftp服务器上传文件]...temp_fis.available_Exception: "+e.getMessage());
			}
			ftp.upload(desDirectory, temp_fis, sftp, fileName); //上传
			
			ftp.close(); //关闭连接
		}
	}
	
	/**
	 * 向目标ftp服务器上传文件，ftp参数在配置文件ftp.xml中配置
	 * @param desDirectory 目标服务器保存路径（不含文件名，最后需要加"/"）
	 * @param file 上传文件
	 */
	public void mergeFileToFtp(String desDirectory, File file) {
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...");
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...file.getPath: "+file.getPath());
		long fileSize = 0;
		try {
			fileSize = getFileSize(file);
		} catch (Exception e1) {
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...getFileSize(file)_Exception: "+e1.getMessage());
		}
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...file.size: "+FormetFileSize(fileSize));
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...desDirectory: "+desDirectory);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...fis: "+fis);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...fis.available: "+fis.available());
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[向目标ftp服务器上传文件]...file.getName(): "+file.getName());
			this.mergeFileToFtp(desDirectory, fis, file, file.getName());
		} catch (FileNotFoundException e) {
			loger.info("上传失败：" + file.getName());
			loger.info(StringUtils.exception2String(e));
		} catch (IOException e) {
			loger.info("上传失败：" + file.getName());
			loger.info(StringUtils.exception2String(e));
		}
	}
	
	/**
	 * 向目标ftp服务器上传文件，ftp参数在配置文件ftp.xml中配置
	 * @param desDirectory 目标服务器保存路径（不含文件名，最后需要加"/"）
	 * @param filePath 文件路径(含文件名)
	 */
	public void mergeFileToFtp(String desDirectory, String filePath) {
		File file = new File(filePath);
		this.mergeFileToFtp(desDirectory, file);
	}
	
	/**
	 * 读取ftp配置文件
	 */
	private static void initConfig() {
		try {
			SAXReader sax = new SAXReader();
			
			String systemPath = CmsLoader.getCmsProperties("systemPath");
			
//			String configFilePath = FileGenerate.class.getClassLoader().getResource("").toURI().getPath() + "/ftp.xml";
			String configFilePath = systemPath +  "WEB-INF/classes/config/ftp.xml";
			
			
			File file = new File(configFilePath);
			Document doc = sax.read(file);
			
			Element root = doc.getRootElement();
			List<Element> l = root.elements();
			
			config = new ArrayList<Map<String,String>>();
			for (int i = 0; i < l.size(); i++) {
				Element ele = l.get(i);
				Map<String, String> m = new HashMap();
				m.put("port", ele.elementText("port"));
				m.put("host", ele.elementText("host"));
				m.put("username", ele.elementText("username"));
				m.put("password", ele.elementText("password"));
				m.put("webpath", ele.elementText("webpath"));
				config.add(m);
				loger.debug("host: "+ele.elementText("host")+", webpath:"+ele.elementText("webpath"));
			}
			
		} catch (Exception e) {
			loger.info("初始化ftp配置文件失败ftp.xml");
			loger.info(StringUtils.exception2String(e));
		}
	}

	public int getPathType() {
		return pathType;
	}

	public void setPathType(int pathType) {
		this.pathType = pathType;
	}
	
	public long getFileSize(File f) throws Exception// 取得文件夹大小
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	} 
	
	public String FormetFileSize(long fileS) {//转换文件大小 
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
	
}
