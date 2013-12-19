package cn.com.sinosoft.lang.math;

import java.util.Random;

public class RandomNumber {

	public static String getRandNumByLength(int numLength) {
		String randNum = "";
		if (numLength < 0)
			return "000000000";
		if (numLength > 9) {
			int i = numLength / 9;
			int j = numLength % 9;
			for (int t = 0; t < i; t++) {
				Random rand = new Random();
				String randDate = "000000000" + rand.nextInt(1000000000);
				randNum += randDate.substring(randDate.length() - 9, randDate.length());
			}
			if (j > 0) {
				Random rand = new Random();
				String randDate = "000000000" + rand.nextInt((int) (Math.pow(10, j)));
				randNum += randDate.substring(randDate.length() - j, randDate.length());
			}
			return randNum;
		} else {
			Random rand = new Random();
			String randDate = "000000000" + rand.nextInt((int) (Math.pow(10, numLength)));
			// System.out.println(randDate);
			randNum = randDate.substring(randDate.length() - numLength, randDate.length());
		}
		return randNum;
	}

	/**
	 * ��ȡnum����ô���14λ,��С��14λ���˷����˻�Ϊ����������ɣ�λ��ˮ�ţ�Ҫ���ظ���
	 * @param num ��ˮ�ŵ�λ��
	 * @return
	 */
	public static synchronized String getSerialNumber(int num) {
		String currentTime = System.nanoTime() + "";
		if (num < currentTime.length())
			return currentTime.substring(currentTime.length() - num, currentTime.length());
		return currentTime + getRandNumByLength(num - currentTime.length());
	}

	/**
	 * �����������
	 * @param nRandomCount �������λ��
	 * @param isNumber ---true�������ȫΪ����;false�����Ϊ������ĸ���
	 * @return
	 */
	public static synchronized String getRandomString(int nRandomCount, boolean isNumber) {
		char[] a = null;
		if (isNumber) {
			a = new char[10];
			for (int i = 48, j = 0; i <= 57; i++, j++) {
				a[j] = (char) i;
			}
		} else {
			// ASCII 48~57, String 0~9;
			// ASCII 65~90, String A-Z;
			// ASCII 97~122,String a-z;
			a = new char[62];
			for (int i = 48, j = 0; i <= 122; i++) {
				if ((i > 57 && i < 65) || (i > 90 && i < 97)) {
					continue;
				} else {
					a[j] = (char) i;
					j++;
				}
			}
		}
		String strRand = "";
		int LengthOfRandom = a.length;
		Random random = new Random();
		for (int i = 0; i < nRandomCount; i++) {
			int nRand = random.nextInt(LengthOfRandom);
			strRand += a[nRand];
		}
		return strRand;
	}

}
