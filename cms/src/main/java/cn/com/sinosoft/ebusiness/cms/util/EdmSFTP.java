package cn.com.sinosoft.ebusiness.cms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * ʹ��jsch����sftpʵ��
 * @author Administrator
 *
 */
public class EdmSFTP {
	private Session sshSession = null;

	private static Logger loger = LoggerFactory.getLogger(EdmSFTP.class);
	/**
	 * ����sftp������
	 * 
	 * @param host
	 *            ����
	 * @param port
	 *            �˿�
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return
	 */
	public ChannelSftp connect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			sshSession = jsch.getSession(username, host, port);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			loger.info("���ӷ�����ʧ�ܣ�" + host + " : " + port + " : " + username + " : " + password );
			loger.info(StringUtils.exception2String(e));
		}
		return sftp;
	}

	/**
	 * �ϴ��ļ�
	 * 
	 * @param directory
	 *            �ϴ���Ŀ¼
	 * @param uploadFile
	 *            Ҫ�ϴ����ļ�
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			File file = new File(uploadFile);
			this.upload(directory, file, sftp);
		} catch (Exception e) {
			e.printStackTrace();
			loger.info("�ϴ��ļ�ʧ�ܣ�" + directory + " : " + uploadFile);
			loger.info(StringUtils.exception2String(e));
		}
	}

	public void upload(String directory, File file, ChannelSftp sftp) {
		try {
			FileInputStream fis = new FileInputStream(file);
			this.upload(directory, fis, sftp, file.getName());
		} catch (Exception e) {
			loger.info("�ϴ��ļ�ʧ�ܣ�" + directory + " : ");
			loger.info(StringUtils.exception2String(e));
		}
	}
	
	public void upload(String directory, FileInputStream fis, ChannelSftp sftp, String fileName) {
		loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP�ϴ��ļ�]...");
		try {
			sftp.cd(directory);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP�ϴ��ļ�]...sftp.cd( "+directory+" )");
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP�ϴ��ļ�]...fis.available: "+fis.available());
//			sftp.cd("/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/localhostNode01Cell/chinapay.ear/chinapay.war/upload/11");
			sftp.put(fis, fileName);
			loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP�ϴ��ļ�]...sftp.put( "+fis+", "+fileName+" )");
			System.out.println("Upload Success!");
		} catch (Exception e) {
			e.printStackTrace();
			loger.info("�ϴ��ļ�ʧ�ܣ�" + directory + " : ");
			loger.info(StringUtils.exception2String(e));
		}
	}
	
	
	/**
	 * �����ļ�
	 * 
	 * @param directory
	 *            ����Ŀ¼
	 * @param downloadFile
	 *            ���ص��ļ�
	 * @param saveFile
	 *            ���ڱ��ص�·��
	 * @param sftp
	 */
	public void download(String directory, String downloadFile,
			String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			loger.info("�����ļ�ʧ�ܣ�" + directory + " : " + downloadFile + " : " + saveFile);
			loger.info(StringUtils.exception2String(e));
		}
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param directory
	 *            Ҫɾ���ļ�����Ŀ¼
	 * @param deleteFile
	 *            Ҫɾ�����ļ�
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			loger.info("ɾ���ļ�ʧ�ܣ�" + directory + " : " + deleteFile );
			loger.info(StringUtils.exception2String(e));
		}
	}

	/**
	 * �г�Ŀ¼�µ��ļ�
	 * 
	 * @param directory
	 *            Ҫ�г���Ŀ¼
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String directory, ChannelSftp sftp)
			throws SftpException {
		return sftp.ls(directory);
	}

	/**
	 * ִ������
	 * @param cmd �����ַ���
	 * @return ִ������󷵻ؽ��
	 */
	public String execcmd(String cmd) {
		ChannelExec execChannel = null;
		try {
			Channel channel_1 = sshSession.openChannel("exec");
			execChannel = (ChannelExec) channel_1;
			execChannel.setCommand(cmd);
			execChannel.connect();
			BufferedReader fromServer;
			fromServer = new java.io.BufferedReader(new InputStreamReader(
					(execChannel.getInputStream())));
			StringBuffer sb = new StringBuffer();
			String tt = null;
			while ((tt = fromServer.readLine()) != null) {
				sb.append(tt + '\n');
			}
			fromServer.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			loger.info("ִ������ʧ�ܣ�" + cmd );
			loger.info(StringUtils.exception2String(e));
			return null;
		} finally {
			execChannel.disconnect();
		}
	}

	/**
	 * �ر�����
	 */
	public void close() {
		if (sshSession != null) {
			sshSession.disconnect();
		}
	}

	public static void main(String[] args) throws SftpException {
		EdmSFTP ftp = new EdmSFTP();
		String host = "131.252.88.102";
		int port = 22;
		String username = "root";
		String password = "rootroot";
		String directory = "/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/localhostNode01Cell/chinapay.ear/chinapay.war/upload/11/";
//		String downloadFile = "new2.txt";
//		String saveFile = "D:\\123.zip";
		String uploadFile = "D:\\1234.zip";

		ChannelSftp sftp = ftp.connect(host, port, username, password);
		ftp.execcmd("mkdir -m 777 " + directory); //ִ������,�õ�����
		ftp.upload(directory, uploadFile, sftp); //�ϴ�
		ftp.close(); //�ر�����
		
//		ftp.download(directory, downloadFile, saveFile, sftp);
//		//�鿴��ǰĿ¼�µ��ļ�
//		Vector v = ftp.listFiles(directory, sftp);
//		for (Iterator it = v.iterator(); it.hasNext();) {
//			 System.out.println(it.next());
//		}
//		System.out.println();
//		System.out.println(cmdMsg);
	}
}