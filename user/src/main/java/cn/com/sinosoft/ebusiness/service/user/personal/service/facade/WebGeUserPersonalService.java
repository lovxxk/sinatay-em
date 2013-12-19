package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;


/**
 * 修改客户信息-LDAP服务器端
 *  
 *
 */
public interface WebGeUserPersonalService{
	
	/**
	 * 个人用户修改（修改本地库）---服务端方法
	 * @return
	 */
	public String updatePersonalUser(String reqXml,String tradeNo) throws Exception;
	
	/**
	 * 个人用户登录---服务端方法
	 * @return
	 */
	public String loginValidateServer(String requestXml, String tradeNo);
	
	/**
	 * 个人用户注册---服务端方法
	 * @return
	 */
	public String registerPersonalUser(String requestXml, String tradeNo);
	
	/**
	 * 重置密码---服务端方法
	 * @param requestXml
	 * @param tradeNo
	 * @return
	 */
	public String resetPassword(String requestXml, String tradeNo);
}
