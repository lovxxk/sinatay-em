package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;


/**
 * �޸Ŀͻ���Ϣ-LDAP��������
 *  
 *
 */
public interface WebGeUserPersonalService{
	
	/**
	 * �����û��޸ģ��޸ı��ؿ⣩---����˷���
	 * @return
	 */
	public String updatePersonalUser(String reqXml,String tradeNo) throws Exception;
	
	/**
	 * �����û���¼---����˷���
	 * @return
	 */
	public String loginValidateServer(String requestXml, String tradeNo);
	
	/**
	 * �����û�ע��---����˷���
	 * @return
	 */
	public String registerPersonalUser(String requestXml, String tradeNo);
	
	/**
	 * ��������---����˷���
	 * @param requestXml
	 * @param tradeNo
	 * @return
	 */
	public String resetPassword(String requestXml, String tradeNo);
}
