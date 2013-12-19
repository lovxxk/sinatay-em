package cn.com.sinosoft.util.encode;

import java.io.StringWriter;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class GeneratePolicySerialNumberUtils {
	
	private static final Logger logger = Logger.getLogger(GeneratePolicySerialNumberUtils.class);
	
	private static final DecimalFormat documentationTypeFormat = new DecimalFormat("00");
	
	private static final DecimalFormat decimalFormat = new DecimalFormat("0000000000");
	
	/**
	 * 保单号生成
	 * @param serialNumber 10位流水号
	 * @param documentationType 四位单证类型
	 * @return
	 */
	public static String generatePolicySerialNumber(long serialNumber, int documentationType){
		// 计算得到校验码
		long checkCode = 98 - (serialNumber * 1000000 + documentationType * 100) % 97;
		return decimalFormat.format(serialNumber) + documentationTypeFormat.format(checkCode) + documentationType;
	}
	
	
	/**
	 * 保单号生成
	 * @param serialNumber 10位流水号
	 * @param documentationType 四位单证类型
	 * @return
	 */
	public static String generatePolicySerialNumber(long serialNumber, String decimalFormatPattern, int documentationType){
		DecimalFormat decimalFormat = new DecimalFormat(decimalFormatPattern);
		// 计算得到校验码
		long checkCode = 98 - (serialNumber * 1000000 + documentationType * 100) % 97;
		return decimalFormat.format(serialNumber) + documentationTypeFormat.format(checkCode) + documentationType;
	}
	
	
	/**
	 * 保单号生成
	 * @param tSerialCode 10位流水号
	 * @param documentationType 四位单证类型
	 * @return
	 */
	public static String generatePolicySerialNumber(String tSerialCode, int documentationType){
		StringWriter sw = new StringWriter();
		if(tSerialCode.length()<11){
			for(int i=0;i<10 - tSerialCode.length();i++){
				sw.append("0");
			}
			sw.append(tSerialCode);
		}
		
		// 计算得到校验码
		String tCheckCode = Long.toString(98 - (Long.valueOf(sw.toString()) * 1000000 + documentationType * 100) % 97);
		// 得到的校验码必须是两位，如果是一位前面用“0”补充
		tCheckCode = tCheckCode.length() == 1 ? "0" + tCheckCode:tCheckCode;
		
		return sw.toString()+tCheckCode+1688;
	}
	
	public static boolean checkPolicySerialNumber(String policyNo, int documentationType){
		if(policyNo != "" && Integer.toString(documentationType).length() == 4 && documentationType != 0 ){
			//获取输入的校验码
			String tInputCertiTypeNo = policyNo.substring(12,16);
			if(Integer.valueOf(tInputCertiTypeNo) != documentationType){
				logger.info("请确认此投保单号的单证类型编码是否输入正确，应该是“" + documentationType + "”而实际是“" + tInputCertiTypeNo + "”");
				return false;
			}
			//获取投保单号的前10位流水号
			String tSerialNo  = policyNo.substring(0,10);
			//根据投保单号的流水号和单证类型编码获取校验码
			String checkNo1 = Long.toString(98 - (Long.valueOf(tSerialNo) * 1000000 + documentationType * 100) % 97);
			if (checkNo1.length() == 1){
				checkNo1 = "0" + checkNo1;
			}
			//获取录入的投保单号中的校验码
			String checkNo2 = policyNo.substring(10,12);
			//比较计算出来的校验码和录入的校验码是否相同，若不同返回false
			if(!checkNo1.equals(checkNo2)){
				logger.info("投保单号中的校验码不符合规则，应该是“" + checkNo1 + "”而实际是“" + checkNo2 + "”，请核对投保单号是否正确！");
				return false;
			}else{
				logger.info("Success！保单号验证通过！");
				return true;
			}
		}else{
			logger.info("保单号不能为空！");
			return false;
		}
	}
	
	public static void main(String[] args) {
		String policyNo = GeneratePolicySerialNumberUtils.generatePolicySerialNumber(1, 1688);
		System.out.println(policyNo);
		System.out.println(checkPolicySerialNumber(policyNo, 1688));
	}
}
