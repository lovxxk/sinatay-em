package cn.com.sinosoft.util.encode;

import cn.com.sinosoft.util.string.StringUtils;

public class TBEncryption {
	
	public static boolean requestMD5SignChecker(String com_Verify, String sign, String reqXml){
		boolean isSuccess = false;
		if(StringUtils.isNotBlank(com_Verify) && StringUtils.isNotBlank(sign) && StringUtils.isNotBlank(reqXml)){
			String expectedSign = outboundMD5Signer(com_Verify, reqXml);
			if(StringUtils.equalsIgnoreCase(expectedSign, sign)){
				isSuccess = true;
			}else{
				StringBuilder s = new StringBuilder();
				s.append("\n--------------------------------------\n");
				s.append("Sign check Failed \nKEY: ");
				s.append(com_Verify);
				s.append("\nEXPECTED: ");
				s.append(expectedSign);
				s.append("\nGOT: ");
				s.append(sign);
				s.append("\n--------------------------------------\n");
				System.out.println(s.toString());
			}
		}
		return isSuccess;
	}
	
	@SuppressWarnings("static-access")
	public static String outboundMD5Signer(String com_Verify, String reqXml){
		String expectedSign = "";
		if(StringUtils.isNotBlank(reqXml)){
			FastMd5 fastMd5 = new FastMd5();
			String signStr = com_Verify+reqXml;
			fastMd5.Update(signStr);
			expectedSign = fastMd5.asHex();
			Md5 md5 = new Md5();
			expectedSign = md5.getMD5(signStr.getBytes());
		}
		return expectedSign;
	}
}