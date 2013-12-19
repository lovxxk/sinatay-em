package cn.com.sinosoft.util.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {  

	public static void main(String[] args) throws IOException {
		ZipUtils.singleZip("ab.txt", "d:\\ab.txt", "d:\\123.zip");
		ZipUtils.folderlZip("d:\\pdf", "d:\\pdf.zip");
	}
	
    /**
     * 
     * ѹ�������ļ�
     * @param fileName ��ѹ���ļ��� ��aa.pdf
     * @param filePath ��ѹ���ļ�ȫ·�� �� d:/123/aa.pdf
     * @param outPath ѹ���ļ�����ȫ·�� ��d:/456/test.zip
     * @throws IOException
     */
    public static void singleZip(String fileName, String filePath, String outPath) throws IOException {  
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outPath));
        ZipEntry entry = new ZipEntry(fileName);  
        zos.putNextEntry(entry);  
        InputStream is = new FileInputStream(filePath);  
        int len = 0;  
        while ((len = is.read()) != -1)   {
            zos.write(len);  
        }
        is.close();  
        zos.close();  
    }  

       

    /**
     * 
     * ѹ���ļ���
     * @param folderPath ��ѹ���ļ���·�� ��d:/log
     * @param outPath ѹ����ѹ���ļ�����·���� d:/test.zip
     * @throws IOException
     */
    public static void folderlZip(String folderPath, String outPath) throws IOException {  
        File inFile = new File(folderPath);  
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outPath));  
        zipFile(inFile, zos, "");  
        zos.close();  

    }  
    /**
     * 
     * ѹ���ļ���
     * @param file1 �����ѹ���ļ�
     * @param outPath ѹ����ѹ���ļ�����·���� d:/test.zip
     * @throws IOException
     */
    public static boolean zipFile(String outPath, File[] file1){
		try {
			byte[] buffer = new byte[1024];
		       //���ɵ�ZIP�ļ���ΪDemo.zip
		      
		       ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outPath));
		       //��Ҫͬʱ���ص������ļ�result.txt ��source.txt
		       for(int i=0;i<file1.length;i++) {
		           FileInputStream fis = new FileInputStream(file1[i]);
		           out.putNextEntry(new ZipEntry(file1[i].getName()));
		           int len;
		           //������Ҫ���ص��ļ������ݣ������zip�ļ�
		          while((len = fis.read(buffer))>0) {
		           out.write(buffer,0,len);
		          }
		           out.closeEntry();
		           fis.close();
		       }
		        out.close();
		        System.out.println("����Demo.zip�ɹ�");
		        return true;
		} catch (Exception e) {
		}	 
		return false;
	}
    
    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {  
        if (inFile.isDirectory()) {  
            File[] files = inFile.listFiles();  
            for (int i = 0; i < files.length; i++)  {
            	File file = files[i];
                zipFile(file, zos, dir + "\\" + inFile.getName());  
            }
        } else {  
            String entryName = null;  
            if (!"".equals(dir))  {
                entryName = dir + "\\" + inFile.getName();  
            }
            else {
                entryName = inFile.getName();  
            }
            ZipEntry entry = new ZipEntry(entryName);  
            zos.putNextEntry(entry);  
            InputStream is = new FileInputStream(inFile);  
            int len = 0;  
            while ((len = is.read()) != -1)  {
                zos.write(len);  
            }
            is.close();  
        }  

    }  

} 
