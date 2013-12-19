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
 * 加密及解密 util
 */
public class EncryptionUtil {
	private static Logger log = LoggerFactory.getLogger(EncryptionUtil.class);

	/**密钥*/
	private static String key = "";
	
	static {
		log.error("获取sinatay_config.properties的路径："+FilePathUtil.getClassBuildPath());
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		Properties properties = PropertyFileUtils.getProperties();
		key = properties.getProperty("securityKey");
		log.error("key=" + key);
	}
	/**
	 * 生成KEY
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
	    	log.error("初始化密钥出现异常>>>>errorMessage:"+e.getMessage());
			e.printStackTrace();
	        throw new RuntimeException("初始化密钥出现异常");
	    }
	  } 
	
	/**
	 * 加密encrypt
	 * 
	 * @param content 需要加密的内容
	 * @return
	 */
	public static String encrypt(String content) {
		try {
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			kgen.init(128, new SecureRandom(key.getBytes()));
			SecretKey secretKey = getKey(key);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			
			return parseByte2HexStr(result); // 加密
		} catch(Exception e) {
			log.error("加密>>>>errorMessage:"+e.getMessage());
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
	 * 解密
	 * 
	 * @param content 待解密内容
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
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(decryptFrom);
			return new String(result); // 加密
		} catch(Exception e) {
			log.error("解密>>>>errorMessage:"+e.getMessage());
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
    /**将二进制转换成16进制
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
    /**将16进制转换为二进制
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
        //加密
        System.out.println("加密前：" + content);
        String encryptResultStr = encrypt(content);
        System.out.println("加密后：" + encryptResultStr);
        //解密
        String decryptResult = decrypt(encryptResultStr);
        System.out.println("解密后：" + decryptResult);
	}

}
