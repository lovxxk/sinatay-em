package cn.com.sinosoft.ebusiness.mis.business.businessManage.downloadfile.web;


import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.productDirectory.web.ProductDirectoryAction;
import cn.com.sinosoft.util.string.StringUtils;

public class DownloadFileMngAction extends Struts2Action {
	
	private static final long serialVersionUID = 1L;
	
	public final static String BASE_FILEPATH = "/business/businessManage/downloadFileMng/";
	
	public final static String TBWJ = "TBWJ";
	public final static String BQWJ = "BQWJ";
	public final static String LPWJ = "LPWJ";
	public final static String ZYFL = "ZYFL";
	
//	public final static String TBWJ_FILEPATH = "/business/businessManage/downloadFileMng/TBWJ";
//	public final static String BQWJ_FILEPATH = "/business/businessManage/downloadFileMng/BQWJ";
//	public final static String LPWJ_FILEPATH = "/business/businessManage/downloadFileMng/LPWJ";
//	public final static String ZYFL_FILEPATH = "/business/businessManage/downloadFileMng/ZYFL";
	
	private String down_filename;
	
	private String fileType;
	
	//投保文件
	private String[][] tbwjFiles;
	//保全文件
	private String[][] bqwjFiles;
	//理赔文件
	private String[][] lpwjFiles;
	//职业分类
	private String[][] zyflFiles;
	


	private File updateOne;
	private String updateOneFileName;
	private String updateOneContentType;
	
	
	public String[][] getTbwjFiles() {
		return tbwjFiles;
	}

	public void setTbwjFiles(String[][] tbwjFiles) {
		this.tbwjFiles = tbwjFiles;
	}

	public String[][] getBqwjFiles() {
		return bqwjFiles;
	}

	public void setBqwjFiles(String[][] bqwjFiles) {
		this.bqwjFiles = bqwjFiles;
	}

	public String[][] getLpwjFiles() {
		return lpwjFiles;
	}

	public void setLpwjFiles(String[][] lpwjFiles) {
		this.lpwjFiles = lpwjFiles;
	}

	public String[][] getZyflFiles() {
		return zyflFiles;
	}

	public void setZyflFiles(String[][] zyflFiles) {
		this.zyflFiles = zyflFiles;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public File getUpdateOne() {
		return updateOne;
	}

	public void setUpdateOne(File updateOne) {
		this.updateOne = updateOne;
	}

	public String getUpdateOneFileName() {
		return updateOneFileName;
	}

	public void setUpdateOneFileName(String updateOneFileName) {
		this.updateOneFileName = updateOneFileName;
	}

	public String getUpdateOneContentType() {
		return updateOneContentType;
	}

	public void setUpdateOneContentType(String updateOneContentType) {
		this.updateOneContentType = updateOneContentType;
	}

	public String getDown_filename() {
		return down_filename;
	}

	public void setDown_filename(String down_filename) {
		this.down_filename = down_filename;
	}


	
	public String init() throws Exception {		
		String realpath = getServletContext().getRealPath("").replaceAll("\\\\", "/");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		
		//投保文件
		String filepath = realpath + BASE_FILEPATH + TBWJ;
		File file = new File(filepath);
		File[] files = file.listFiles(new FileFilter(){
			@Override
			public boolean accept(File pathname) {
				if(pathname.isFile())
					return true;
				return false;
			}			
		});
		if (files.length > 0) {
			tbwjFiles = new String[files.length][4];
			for (int i = 0; i < files.length; i++) {
				tbwjFiles[i][0] = files[i].getName();
				tbwjFiles[i][1] = encryption(files[i].getName());
				tbwjFiles[i][2] = "" + files[i].length();
				Date modifyDate = new Date(files[i].lastModified());
				tbwjFiles[i][3] = sdf.format(modifyDate);
			}
		}
		
		//保全文件
		filepath = realpath + BASE_FILEPATH + BQWJ;
		file = new File(filepath);
		files = file.listFiles(new FileFilter(){
			@Override
			public boolean accept(File pathname) {
				if(pathname.isFile())
					return true;
				return false;
			}			
		});
		if (files.length > 0) {
			bqwjFiles = new String[files.length][4];
			for (int i = 0; i < files.length; i++) {
				bqwjFiles[i][0] = files[i].getName();
				bqwjFiles[i][1] = encryption(files[i].getName());
				bqwjFiles[i][2] = "" + files[i].length();
				Date modifyDate = new Date(files[i].lastModified());
				bqwjFiles[i][3] = sdf.format(modifyDate);
			}
		}
		
		//理赔文件
		filepath = realpath + BASE_FILEPATH + LPWJ;
		file = new File(filepath);
		files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					return true;
				return false;
			}
		});
		if (files.length > 0) {
			lpwjFiles = new String[files.length][4];
			for (int i = 0; i < files.length; i++) {
				lpwjFiles[i][0] = files[i].getName();
				lpwjFiles[i][1] = encryption(files[i].getName());
				lpwjFiles[i][2] = "" + files[i].length();
				Date modifyDate = new Date(files[i].lastModified());
				lpwjFiles[i][3] = sdf.format(modifyDate);
			}
		}
		
		//职业分类
		filepath = realpath + BASE_FILEPATH + ZYFL;
		file = new File(filepath);
		files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					return true;
				return false;
			}
		});
		if (files.length > 0) {
			zyflFiles = new String[files.length][4];
			for (int i = 0; i < files.length; i++) {
				zyflFiles[i][0] = files[i].getName();
				zyflFiles[i][1] = encryption(files[i].getName());
				zyflFiles[i][2] = "" + files[i].length();
				Date modifyDate = new Date(files[i].lastModified());
				zyflFiles[i][3] = sdf.format(modifyDate);
			}
		}
		
		return SUCCESS;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "新建文本文档.txt";
		String ss = DownloadFileMngAction.encryption(s);
		System.out.println(ss);

		String rr = decryption(ss);
		System.out.println(rr);

	}
	
	
	

	public String updateFile() throws Exception {
		System.out.println(fileType);

		String realpath = getServletContext().getRealPath("").replaceAll("\\\\", "/")
				+ BASE_FILEPATH + fileType + "/";

		if (updateOne != null) {
			File savefile = new File(new File(realpath), updateOneFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(updateOne, savefile);
			System.out.println("保存文件："+savefile.getAbsolutePath());
		}
		return SUCCESS;
	}
	
	public String deleteFile() throws Exception {
		System.out.println(fileType);

		String realpath = getServletContext().getRealPath("").replaceAll("\\\\", "/")
				+ BASE_FILEPATH + fileType + "/";
		
		String name = decryption(updateOneFileName);
		File deleteFile = new File(realpath+name);		
		if (deleteFile.exists()&&deleteFile.isFile()) {
			deleteFile.delete();
		}
		return SUCCESS;
	}

	/**
	 * 加密字符串
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryption(String string) {// ASCII转换为字符串
		// 转义符转换，注意转换后不能少于6个字符，所以前面加点固定的字符
		String s0 = "";
		try {
			s0 = URLEncoder.encode("中科软信泰项目组" + string, "GBK").replaceAll("%",
					"`");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// 计算子字符串的长度
		int length = s0.length() / 4 + (s0.length() % 4 == 0 ? 0 : 1);
		// 分解成4个子字符串
		String s1 = s0.substring(0, length);
		String s2 = s0.substring(length, length * 2);
		String s3 = s0.substring(length * 2, length * 3);
		String s4 = s0.substring(length * 3);
		// s4补~字符
		switch (length - s4.length()) {
		case 1:
			s4 = s4 + "~";
			break;
		case 2:
			s4 = s4 + "~~";
			break;
		case 3:
			s4 = s4 + "~~~";
			break;
		default:
			break;
		}
		// 重新排列成新的字符串
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(s1.charAt(i));
			sb.append(s2.charAt(i));
			sb.append(s3.charAt(i));
			sb.append(s4.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 解密字符串
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decryption(String string) {
		int length = string.length();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		for (int i = 0; i < length;) {
			sb1.append(string.charAt(i));
			i++;
			sb2.append(string.charAt(i));
			i++;
			sb3.append(string.charAt(i));
			i++;
			sb4.append(string.charAt(i));
			i++;
		}
		String s0 = "" + sb1 + sb2 + sb3 + sb4;
		// 消除最后的"~"字符,最多有3个，所以执行3遍
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		// 转义符转换
		s0 = s0.replaceAll("`", "%");
		try {
			s0 = URLDecoder.decode(s0, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		s0 = s0.replaceFirst("中科软信泰项目组", "");
		return s0;
	}

	/**
	 * 获取项目物理路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public static String getProjectLocalPath() {
		try {
			String path = ProductDirectoryAction.class.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public InputStream getInputStream() throws Exception {	
		
		String name = decryption(down_filename);
		String path = BASE_FILEPATH + fileType + "/"+name;
		InputStream i = ServletActionContext.getServletContext().getResourceAsStream(path);		
		return i;  
    }

	public String getDownloadFileName() {
		return decryption(down_filename);
	}
		
}
