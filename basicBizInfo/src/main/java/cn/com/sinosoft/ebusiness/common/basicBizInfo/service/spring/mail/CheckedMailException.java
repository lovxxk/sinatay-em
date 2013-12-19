package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail;


/**
 * �������ʼ����͹�����ʹ�õ��쳣�࣬�ڸ��쳣���ж������ʼ�����ʧ�ܵ�ԭ��

 *
 */
public class CheckedMailException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errMsg = "";
	
	public CheckedMailException(String errMsg){
		this.errMsg = errMsg;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
