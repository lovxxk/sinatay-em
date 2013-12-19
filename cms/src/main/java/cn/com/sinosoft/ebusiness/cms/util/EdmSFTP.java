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
 * 使用jsch连接sftp实例
 * @author Administrator
 *
 */
public class EdmSFTP {
	private Session sshSession = null;

	private static Logger loger = LoggerFactory.getLogger(EdmSFTP.class);
	/**
	 * 连接sftp服务器
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
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
			loger.info("连接服务器失败：" + host + " : " + port + " : " + username + " : " + password );
			loger.info(StringUtils.exception2String(e));
		}
		return sftp;
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			File file = new File(uploadFile);
			this.upload(directory, file, sftp);
		} catch (Exception e) {
			e.printStackTrace();
			loger.info("上传文件失败：" + directory + " : " + uploadFile);
			loger.info(StringUtils.exception2String(e));
		}
	}

	public void upload(String directory, File file, ChannelSftp sftp) {
		try {
			FileInputStream fis = new FileInputStream(file);
			this.upload(directory, fis, sftp, file.getName());
		} catch (Exception e) {
			loger.info("上传文件失败：" + directory + " : ");
			loger.info(StringUtils.exception2String(e));
		}
	}
	
	public void upload(String directory, FileInputStream fis, ChannelSftp sftp, String fileName) {
		loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP上传文件]...");
		try {
			sftp.cd(directory);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP上传文件]...sftp.cd( "+directory+" )");
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP上传文件]...fis.available: "+fis.available());
//			sftp.cd("/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/localhostNode01Cell/chinapay.ear/chinapay.war/upload/11");
			sftp.put(fis, fileName);
			loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" upload(String, FileInputStream, ChannelSftp, String)[EdmSFTP上传文件]...sftp.put( "+fis+", "+fileName+" )");
			System.out.println("Upload Success!");
		} catch (Exception e) {
			e.printStackTrace();
			loger.info("上传文件失败：" + directory + " : ");
			loger.info(StringUtils.exception2String(e));
		}
	}
	
	
	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public void download(String directory, String downloadFile,
			String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			loger.info("下载文件失败：" + directory + " : " + downloadFile + " : " + saveFile);
			loger.info(StringUtils.exception2String(e));
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			loger.info("删除文件失败：" + directory + " : " + deleteFile );
			loger.info(StringUtils.exception2String(e));
		}
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String directory, ChannelSftp sftp)
			throws SftpException {
		return sftp.ls(directory);
	}

	/**
	 * 执行命令
	 * @param cmd 命令字符串
	 * @return 执行命令后返回结果
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
			loger.info("执行命令失败：" + cmd );
			loger.info(StringUtils.exception2String(e));
			return null;
		} finally {
			execChannel.disconnect();
		}
	}

	/**
	 * 关闭连接
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
		ftp.execcmd("mkdir -m 777 " + directory); //执行命令,得到返回
		ftp.upload(directory, uploadFile, sftp); //上传
		ftp.close(); //关闭连接
		
//		ftp.download(directory, downloadFile, saveFile, sftp);
//		//查看当前目录下的文件
//		Vector v = ftp.listFiles(directory, sftp);
//		for (Iterator it = v.iterator(); it.hasNext();) {
//			 System.out.println(it.next());
//		}
//		System.out.println();
//		System.out.println(cmdMsg);
	}
}