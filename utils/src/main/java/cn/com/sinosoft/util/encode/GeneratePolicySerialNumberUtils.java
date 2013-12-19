package cn.com.sinosoft.util.encode;

import java.io.StringWriter;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class GeneratePolicySerialNumberUtils {
	
	private static final Logger logger = Logger.getLogger(GeneratePolicySerialNumberUtils.class);
	
	private static final DecimalFormat documentationTypeFormat = new DecimalFormat("00");
	
	private static final DecimalFormat decimalFormat = new DecimalFormat("0000000000");
	
	/**
	 * ����������
	 * @param serialNumber 10λ��ˮ��
	 * @param documentationType ��λ��֤����
	 * @return
	 */
	public static String generatePolicySerialNumber(long serialNumber, int documentationType){
		// ����õ�У����
		long checkCode = 98 - (serialNumber * 1000000 + documentationType * 100) % 97;
		return decimalFormat.format(serialNumber) + documentationTypeFormat.format(checkCode) + documentationType;
	}
	
	
	/**
	 * ����������
	 * @param serialNumber 10λ��ˮ��
	 * @param documentationType ��λ��֤����
	 * @return
	 */
	public static String generatePolicySerialNumber(long serialNumber, String decimalFormatPattern, int documentationType){
		DecimalFormat decimalFormat = new DecimalFormat(decimalFormatPattern);
		// ����õ�У����
		long checkCode = 98 - (serialNumber * 1000000 + documentationType * 100) % 97;
		return decimalFormat.format(serialNumber) + documentationTypeFormat.format(checkCode) + documentationType;
	}
	
	
	/**
	 * ����������
	 * @param tSerialCode 10λ��ˮ��
	 * @param documentationType ��λ��֤����
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
		
		// ����õ�У����
		String tCheckCode = Long.toString(98 - (Long.valueOf(sw.toString()) * 1000000 + documentationType * 100) % 97);
		// �õ���У�����������λ�������һλǰ���á�0������
		tCheckCode = tCheckCode.length() == 1 ? "0" + tCheckCode:tCheckCode;
		
		return sw.toString()+tCheckCode+1688;
	}
	
	public static boolean checkPolicySerialNumber(String policyNo, int documentationType){
		if(policyNo != "" && Integer.toString(documentationType).length() == 4 && documentationType != 0 ){
			//��ȡ�����У����
			String tInputCertiTypeNo = policyNo.substring(12,16);
			if(Integer.valueOf(tInputCertiTypeNo) != documentationType){
				logger.info("��ȷ�ϴ�Ͷ�����ŵĵ�֤���ͱ����Ƿ�������ȷ��Ӧ���ǡ�" + documentationType + "����ʵ���ǡ�" + tInputCertiTypeNo + "��");
				return false;
			}
			//��ȡͶ�����ŵ�ǰ10λ��ˮ��
			String tSerialNo  = policyNo.substring(0,10);
			//����Ͷ�����ŵ���ˮ�ź͵�֤���ͱ����ȡУ����
			String checkNo1 = Long.toString(98 - (Long.valueOf(tSerialNo) * 1000000 + documentationType * 100) % 97);
			if (checkNo1.length() == 1){
				checkNo1 = "0" + checkNo1;
			}
			//��ȡ¼���Ͷ�������е�У����
			String checkNo2 = policyNo.substring(10,12);
			//�Ƚϼ��������У�����¼���У�����Ƿ���ͬ������ͬ����false
			if(!checkNo1.equals(checkNo2)){
				logger.info("Ͷ�������е�У���벻���Ϲ���Ӧ���ǡ�" + checkNo1 + "����ʵ���ǡ�" + checkNo2 + "������˶�Ͷ�������Ƿ���ȷ��");
				return false;
			}else{
				logger.info("Success����������֤ͨ����");
				return true;
			}
		}else{
			logger.info("�����Ų���Ϊ�գ�");
			return false;
		}
	}
	
	public static void main(String[] args) {
		String policyNo = GeneratePolicySerialNumberUtils.generatePolicySerialNumber(1, 1688);
		System.out.println(policyNo);
		System.out.println(checkPolicySerialNumber(policyNo, 1688));
	}
}
