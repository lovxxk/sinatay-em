package cn.com.sinosoft.util.encode;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {
	
	private final static boolean defualtEncrypt = true;
	
	private final static String MESSAGEDIGEST_MD5 = "MD5";
	
	private final static String SECRET_ALGORITHM = "AES";
	
	private final static String CIPHER_KEY = "AES/ECB/PKCS5Padding";

	private final static byte[] keyByte = { 0x01, 0x22, 0x4F, 0x58,
			(byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x13, 0x79, 0x51, (byte) 0xCB,
			(byte) 0xDD, 0x55, 0x76 };

	private static String byteArrayToHexString(byte[] b) {
		StringBuilder builder = new StringBuilder();
		String value = null;
		for (int i = 0; i < b.length; i++) {
			value = Integer.toHexString(0xFF & b[i]);
			if (value.length() == 1) {
				builder.append("0").append(value);
			} else {
				builder.append(value);
			}
		}
		return builder.toString();
	}

	public static String EncodeMD5(String value) throws Exception {
		MessageDigest md = MessageDigest.getInstance(MESSAGEDIGEST_MD5);
		return byteArrayToHexString(md.digest(value.getBytes()));
	}

	public static String EnCodeAES(String value, String key) throws Exception {
		byte[] keys = null;
		if (StringUtils.isEmpty(key)) {
			keys = keyByte;
		} else {
			keys = key.getBytes("UTF-8");
		}
		SecretKey secretKey = new SecretKeySpec(keys, SECRET_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_KEY);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] text = cipher.doFinal(value.getBytes("UTF-8"));
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(text);
	}

	public static String DeCodeAES(String value, String key) throws Exception {
		byte[] keys = null;
		if (StringUtils.isEmpty(key)) {
			keys = keyByte;
		} else {
			keys = key.getBytes("UTF-8");
		}
		SecretKey secretKey = new SecretKeySpec(keys, SECRET_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_KEY);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] text = cipher.doFinal(decoder.decodeBuffer(value));
		return new String(text, "UTF-8");
	}

	public static String EnCode(String OriString) {
		try {
			if (defualtEncrypt) {
				return EncodeMD5(OriString);
			} else {
				return EnCodeAES(OriString, null);
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static String DeCode(String OriString) throws Exception {
		if (defualtEncrypt) {
			return null;
		} else {
			return DeCodeAES(OriString, null);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Encrypt.EnCode("密码123"));
		System.out.println(Encrypt.EnCode("12345678"));
		System.out.println(Encrypt.EnCode("sinatay123456"));
		System.out.println(Encrypt.EnCode("sinatay"));
	}
	
}