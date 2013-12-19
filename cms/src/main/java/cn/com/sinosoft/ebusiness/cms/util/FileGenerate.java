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
	public static final int ABSOLUTE_PATH = 0;//��������·��
	public static final int RELATED_PATH = 1;//�������·��
	private static List<Map<String, String>> config;//ϵͳ�����ļ�������ftp����
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
	 * ��Ŀ��ftp�������ϴ��ļ���ftp�����������ļ�ftp.xml������
	 * @param desDirectory Ŀ�����������·���������ļ����������Ҫ��"/"��
	 * @param fis �ϴ��ļ���
	 * @param fileName Ŀ������������ļ���
	 */
	public void mergeFileToFtp(String desDirectory, FileInputStream fis, String fileName) {
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...");
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...config.size: "+config.size());
		try {
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...fis.available: "+fis.available());
		} catch (IOException e1) {
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...fis.available_Exception: "+e1.getMessage());
		}
		String temp_desDirectory = desDirectory;
		FileInputStream temp_fis = fis;
		for (int i = 0; i < config.size(); i++) {
			EdmSFTP ftp = new EdmSFTP();//ftpʵ����ʵ��
			Map<String, String> m = config.get(i);
			String host = m.get("host");
			String port = m.get("port");
			String username = m.get("username");
			String password = m.get("password");
			ChannelSftp sftp = ftp.connect(host, Integer.parseInt(port), username, password);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...ftp.connect( "+host+", "+Integer.parseInt(port)+", "+username+", "+password+" )");
			if(pathType == RELATED_PATH) {
				desDirectory = m.get("webpath") + temp_desDirectory;
			}
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...-----config.index: "+i);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...host: "+host);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...port: "+port);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...username: "+username);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...password: "+password);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...m.get(\"webpath\"): "+m.get("webpath"));
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...-----config.index: "+i);
			
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...ftp.desDirectory: "+desDirectory);
			ftp.execcmd("mkdir -p " + desDirectory); //ִ������,�õ�����
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...ftp.upload( "+desDirectory+", "+temp_fis+", "+sftp+", "+fileName+" )");
			try{
				loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...temp_fis.available: "+temp_fis.available());
			}catch(Exception e){
				loger.info("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, String)[��Ŀ��ftp�������ϴ��ļ�]...temp_fis.available_Exception: "+e.getMessage());
			}
			ftp.upload(desDirectory, temp_fis, sftp, fileName); //�ϴ�
			
			ftp.close(); //�ر�����
		}
	}
	
	/**
	 * ��Ŀ��ftp�������ϴ��ļ���ftp�����������ļ�ftp.xml������
	 * @param desDirectory Ŀ�����������·���������ļ����������Ҫ��"/"��
	 * @param fis �ϴ��ļ���
	 * @param fileName Ŀ������������ļ���
	 */
	public void mergeFileToFtp(String desDirectory, FileInputStream fis, File file, String fileName) {
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...");
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...config.size: "+config.size());
		try {
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...fis.available: "+fis.available());
		} catch (IOException e1) {
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...fis.available_Exception: "+e1.getMessage());
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
			EdmSFTP ftp = new EdmSFTP();//ftpʵ����ʵ��
			Map<String, String> m = config.get(i);
			String host = m.get("host");
			String port = m.get("port");
			String username = m.get("username");
			String password = m.get("password");
			ChannelSftp sftp = ftp.connect(host, Integer.parseInt(port), username, password);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...ftp.connect( "+host+", "+Integer.parseInt(port)+", "+username+", "+password+" )");
			if(pathType == RELATED_PATH) {
				desDirectory = m.get("webpath") + temp_desDirectory;
			}
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...-----config.index: "+i);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...host: "+host);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...port: "+port);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...username: "+username);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...password: "+password);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...m.get(\"webpath\"): "+m.get("webpath"));
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...-----config.index: "+i);
			
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...ftp.desDirectory: "+desDirectory);
			try{
				ftp.execcmd("mkdir -p " + desDirectory); //ִ������,�õ�����
				loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...ftp.upload( "+desDirectory+", "+temp_fis+", "+sftp+", "+fileName+" )");
				loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...temp_fis.available: "+temp_fis.available());
			}catch(Exception e){
				loger.info("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, FileInputStream, File, String)[��Ŀ��ftp�������ϴ��ļ�]...temp_fis.available_Exception: "+e.getMessage());
			}
			ftp.upload(desDirectory, temp_fis, sftp, fileName); //�ϴ�
			
			ftp.close(); //�ر�����
		}
	}
	
	/**
	 * ��Ŀ��ftp�������ϴ��ļ���ftp�����������ļ�ftp.xml������
	 * @param desDirectory Ŀ�����������·���������ļ����������Ҫ��"/"��
	 * @param file �ϴ��ļ�
	 */
	public void mergeFileToFtp(String desDirectory, File file) {
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...");
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...file.getPath: "+file.getPath());
		long fileSize = 0;
		try {
			fileSize = getFileSize(file);
		} catch (Exception e1) {
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...getFileSize(file)_Exception: "+e1.getMessage());
		}
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...file.size: "+FormetFileSize(fileSize));
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...desDirectory: "+desDirectory);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...fis: "+fis);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...fis.available: "+fis.available());
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" mergeFileToFtp(String, File)[��Ŀ��ftp�������ϴ��ļ�]...file.getName(): "+file.getName());
			this.mergeFileToFtp(desDirectory, fis, file, file.getName());
		} catch (FileNotFoundException e) {
			loger.info("�ϴ�ʧ�ܣ�" + file.getName());
			loger.info(StringUtils.exception2String(e));
		} catch (IOException e) {
			loger.info("�ϴ�ʧ�ܣ�" + file.getName());
			loger.info(StringUtils.exception2String(e));
		}
	}
	
	/**
	 * ��Ŀ��ftp�������ϴ��ļ���ftp�����������ļ�ftp.xml������
	 * @param desDirectory Ŀ�����������·���������ļ����������Ҫ��"/"��
	 * @param filePath �ļ�·��(���ļ���)
	 */
	public void mergeFileToFtp(String desDirectory, String filePath) {
		File file = new File(filePath);
		this.mergeFileToFtp(desDirectory, file);
	}
	
	/**
	 * ��ȡftp�����ļ�
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
			loger.info("��ʼ��ftp�����ļ�ʧ��ftp.xml");
			loger.info(StringUtils.exception2String(e));
		}
	}

	public int getPathType() {
		return pathType;
	}

	public void setPathType(int pathType) {
		this.pathType = pathType;
	}
	
	public long getFileSize(File f) throws Exception// ȡ���ļ��д�С
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
	
	public String FormetFileSize(long fileS) {//ת���ļ���С 
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
