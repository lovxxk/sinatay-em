package cn.com.sinosoft.util.encode;

import java.util.Random;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordGen {
	
	public static String genSimplePwd() {
		StringBuilder password = new StringBuilder();
		for (int i = 7; i >= 0; i--) {
			int type = new Random().nextInt(3);
			int numBoost = new Random().nextInt(10);
			int alphaBoost = new Random().nextInt(26);
			switch (type) {
			case 0:
				password.append(numBoost);
				break;
			case 1:
				password.append((char) ('a' + alphaBoost));
				break;
			case 2:
				password.append((char) ('A' + alphaBoost));
				break;
			}
		}
		return password.toString();
	}

	public static String encodePassword(String password) {
		return new Md5PasswordEncoder().encodePassword(password, null);
	}
	
	public static String getEncryptPwd(){
		return new Md5PasswordEncoder().encodePassword(genSimplePwd(),null);
	} 
}
