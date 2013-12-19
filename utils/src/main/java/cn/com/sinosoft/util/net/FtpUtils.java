package cn.com.sinosoft.util.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpUtils {

	private String host;
	private int port;
	private String userName;
	private String password;
	private static String path = "/data/onlinefile/";
	
	private FTPClient client = new FTPClient();
	
	public FtpUtils(String host,int port,String userName,String password,String serverRootPath){
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
		if(serverRootPath != null && !"".equals(serverRootPath)){
			path = serverRootPath;
		}
	}
	
	/**
	 * µ«¬Ω
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	private boolean connect() throws SocketException, IOException{
		client.connect(host, port);
		boolean flag = client.login(userName, password);
		return flag;
	}
	
	/**
	 * …œ‘ÿ
	 * @param in
	 * @param fileName
	 * @return
	 * @throws SocketException 
	 * @throws IOException
	 */
	public boolean upload(InputStream in,String fileName) throws SocketException, IOException{
		if(connect()){
			try{
				client.setFileType(FTP.BINARY_FILE_TYPE); 
				String uploadFile = path+fileName;
				boolean flag = client.storeFile(uploadFile, in);
				return flag;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}else{
			System.out.println("µ«¬Ω ß∞‹");
			return false;
		}
		
	}
	
	public boolean getFile(String fileName,OutputStream out) throws SocketException, IOException{
		if(connect()){
			client.setFileType(FTP.BINARY_FILE_TYPE);
			boolean flag = client.retrieveFile(path+fileName, out);
			if(flag){
				System.out.println("œ¬‘ÿ≥…π¶");
				return true;
			}else{
				System.out.println("œ¬‘ÿ ß∞‹");
				return false;
			}
		}else{
			System.out.println("µ«¬Ω ß∞‹");
			return false;
		}
		
	}
	public boolean rename(String fileName,String to)throws SocketException, IOException{
		if(connect()){
			boolean flag = client.rename(path+fileName, path+to);
			return flag;
		}else{
			System.out.println("µ«¬Ω ß∞‹");
			return false;
		}
	}
	public boolean removeFile(String fileName)throws SocketException, IOException{
		if(connect()){
			boolean flag = client.deleteFile(path+fileName);
			return flag;
		}else{
			System.out.println("µ«¬Ω ß∞‹");
			return false;
		}
	}
	
	
	public void close() throws IOException{
		client.disconnect();
	}
	
	public static void main(String args[]) throws IOException{
		String host = "10.0.2.41";
		String userName = "online";
		String password = "onlineftp";
		File f = new File("D:/1.txt");
		FtpUtils ftpUtils = new FtpUtils(host,21,userName,password,null);
		InputStream in = new FileInputStream(f);
		
		boolean flag = ftpUtils.upload(in, f.getName());
		if(flag){
			System.out.println("…œ‘ÿ≥…π¶");
		}else{
			System.out.println("…œ‘ÿ ß∞‹");
		}
		/*OutputStream out = new FileOutputStream(new File("D:/1.txt"));
		boolean flag = ftpUtils.getFile("ip.txt", out);
		if(flag){
			System.out.println("œ¬‘ÿ≥…π¶");
		}else{
			System.out.println("œ¬‘ÿ ß∞‹");
		}*/
//		out.flush();
//		out.close();
		ftpUtils.close();
	}
	
}
