package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.productManage.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DirCopy {

	public static void fileCopy(File srcPath, File dstPath) // 复制文件
	{ 
		try {
			if (srcPath.isDirectory()) {
				if (!dstPath.exists()) {
					dstPath.mkdirs();
				}
				String files[] = srcPath.list();
				for (int i = 0; i < files.length; i++) {
					fileCopy(new File(srcPath, files[i]), new File(dstPath,
							files[i]));
				}
			} else {
				if (!srcPath.exists()) {
					System.out.println("File or directory does not exist.");
				} else {
					InputStream in = new FileInputStream(srcPath);
					OutputStream out = new FileOutputStream(dstPath);// Transfer
																		// bytes
																		// from in
																		// to out
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
				}
			}
		} catch (Exception e) {
			System.out.println("复制 文件未成功。");
			return;
		}
	}
	
	public static void main(String[] args) throws IOException {
		DirCopy cd = new DirCopy();

		cd.fileCopy(new File("D:/test"), new File("D:/test1"));
	}
	
}
