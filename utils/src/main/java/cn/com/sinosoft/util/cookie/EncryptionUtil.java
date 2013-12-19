package cn.com.sinosoft.util.cookie;

import java.security.SecureRandom;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

/**
 * JAVA AES
 * ���ܼ����� util
 */
public class EncryptionUtil {
	private static Logger log = LoggerFactory.getLogger(EncryptionUtil.class);

	/**��Կ*/
	private static String key = "";
	
	static {
		log.error("��ȡsinatay_config.properties��·����"+FilePathUtil.getClassBuildPath());
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		Properties properties = PropertyFileUtils.getProperties();
		key = properties.getProperty("securityKey");
		log.error("key=" + key);
	}
	/**
	 * ����KEY
	 * @param key
	 * @return
	 */
	public static SecretKey getKey(String key) {
	     try {
	      KeyGenerator _generator=KeyGenerator.getInstance("AES");
	      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
	      secureRandom.setSeed(key.getBytes());
	      _generator.init(128,secureRandom);
	      return _generator.generateKey();
	    }  catch (Exception e) {
	    	log.error("��ʼ����Կ�����쳣>>>>errorMessage:"+e.getMessage());
			e.printStackTrace();
	        throw new RuntimeException("��ʼ����Կ�����쳣");
	    }
	  } 
	
	/**
	 * ����encrypt
	 * 
	 * @param content ��Ҫ���ܵ�����
	 * @return
	 */
	public static String encrypt(String content) {
		try {
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			kgen.init(128, new SecureRandom(key.getBytes()));
			SecretKey secretKey = getKey(key);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// ����������
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(byteContent);
			
			return parseByte2HexStr(result); // ����
		} catch(Exception e) {
			log.error("����>>>>errorMessage:"+e.getMessage());
			e.printStackTrace();
		}
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param content ����������
	 * @return
	 */
	public static String decrypt(String content) {
		try {
			byte[] decryptFrom = parseHexStr2Byte(content);
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			kgen.init(128, new SecureRandom(key.getBytes()));
//			SecretKey secretKey = kgen.generateKey();
			SecretKey secretKey = getKey(key);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// ����������
			cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(decryptFrom);
			return new String(result); // ����
		} catch(Exception e) {
			log.error("����>>>>errorMessage:"+e.getMessage());
			e.printStackTrace();
		}
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//		}
		return null;
	}
    /**��������ת����16����
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < buf.length; i++) {
                    String hex = Integer.toHexString(buf[i] & 0xFF);
                    if (hex.length() == 1) {
                            hex = '0' + hex;
                    }
                    sb.append(hex.toUpperCase());
            }
            return sb.toString();
    }
    /**��16����ת��Ϊ������
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1)
                    return null;
            byte[] result = new byte[hexStr.length()/2];
            for (int i = 0;i< hexStr.length()/2; i++) {
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                    result[i] = (byte) (high * 16 + low);
            }
            return result;
    }
	public static void main(String[] args) {
        String content = "15000814235";
        //����
        System.out.println("����ǰ��" + content);
        String encryptResultStr = encrypt(content);
        System.out.println("���ܺ�" + encryptResultStr);
        //����
        String decryptResult = decrypt(encryptResultStr);
        System.out.println("���ܺ�" + decryptResult);
	}

}
