package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail.CheckedMailException;
import cn.com.sinosoft.ebusiness.infomanage.domain.PolicyList;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.PersonalUserDto;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.UserServiceException;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.portalModuleException;

/**
 * 个人用户信息服务接口
 * @version 1.0
 *  
 *
 */
public interface GeUserPersonalService extends GenericDao<GeUserPersonal, String> {
	/**
	 * 验证原密码是否有效
	 * @param uid
	 * @param pwd
	 * @return
	 */
	public boolean valPwd(String uid,String pwd);
	
	/**
	 * 个人用户信息注册（功能）
	 * @param geUserPersonal 个人用户实体类
	 * @return 0-保存本地库失败，1-成功，2-失败（用户号重复），3-失败（用户名重复），4-失败（邮箱重复），5-失败（身份证重复），6-失败（鹤卡号重复）
	 */
	public List registerWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * 个人用户信息注册（功能）
	 * @param geUserPersonal 个人用户实体类
	 * @return GeUserPersonal 个人用户信息，属性results里面包含值的含义： 0-保存本地库失败，1-成功，2-失败（用户号重复），3-失败（用户名重复），4-失败（邮箱重复），5-失败（身份证重复），6-失败（鹤卡号重复）
	 */
	public GeUserPersonal registerFromWap(GeUserPersonal geUserPersonal)  throws UserServiceException;
	
	/**
	 * 根据登陆信息获取登陆对象（功能）
	 * @param geUserPersonal 个人用户实体类
	 * @return GeUserPersonal
	 */
	public GeUserPersonal getUserPersonalForLoginWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * 个人用户激活
	 * @param geUserPersonal
	 * @param oldActiveCode 原激活码（来自session，为空则发送方式为邮箱发送）
	 * @param newActiveCode 客户填写的活码
	 * @return 00-激活失败  01-激活成功 02-激活码错误 03-邮箱激活时库中原激活码为空
	 * @throws Exception
	 */
	public String activeAccountWithLDAP(GeUserPersonal geUserPersonal,String oldActiveCode,String newActiveCode) throws UserServiceException;
	
	/**
	 * 个人用户密码重置（功能）
	 * 说明：重置方式1（账户+手机），重置方式二（邮箱）
	 * 注：账户只有集团注册的才有
	 * @param geUserPersonal 个人用户实体类
	 * @return 1-成功，01-填写客户信息有误，02-重置密码失败
	 */
	public List resetPasswordWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException, CheckedMailException,UnsupportedEncodingException;
	
	/**
	 * 修改个人用户信息（功能）
	 * @param geUserPersonal 个人用户实体类
	 * @return 1-成功，2-失败(用户号不存在)，3-失败（用户名重复），4-失败（邮箱重复），5-失败（身份证重复），6-失败(鹤卡号重复) 7-修改本地库失败
	 */
	public List updateGeUserPersonalWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * 修改个人用户密码（功能）
	 * @param geUserPersonal 个人用户实体类
	 * @return 00-原密码错误，1-成功，2-失败(用户号不存在)，3-失败（用户名重复），4-失败（邮箱重复），5-失败（身份证重复），6-失败(鹤卡号重复) 7-修改本地库密码失败
	 */
	public List updatePasswordWithLDAP(GeUserPersonal geUserPersonal,String newPassword) throws UserServiceException;
	
	/**
	 * 处理与ldap交互返回的结果，转为错误信息的对应描述返回，成功不做处理
	 * @param list 代码集合
	 * @return 描述集合
	 */
	public List getInfoFromCode(List<String> list);
	
	/**
	 * 根据主键查询个人用户信息
	 * @param userID
	 * @return
	 */
	public GeUserPersonal findUserByPK(String userID) throws UserServiceException;
	/**
	 * 查询个人用户信息（查询本地库）
	 * @param queryRule
	 * @return
	 */
	public GeUserPersonal findUser(QueryRule queryRule) throws UserServiceException;
	
	public GeUserPersonal findUsers(QueryRule queryRule) throws UserServiceException;
	
	/**
	 * 查询个人用户信息列表（分页查询本地库）
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findUser(QueryRule queryRule,int pageNo,int pageSize) throws UserServiceException;
	
	/**
	 * 保存个人用户信息（保存本地库）
	 * @param geUserPersonal
	 * @return
	 */
	public boolean saveUser(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * 修改个人用户信息（本地库修改多数字段）
	 * @param geUserPersonal
	 * @param unUpdateStrings 不修改的字段
	 * @return
	 * @throws Exception
	 */
	public boolean updateUser(GeUserPersonal geUserPersonal,String[] unUpdateStrings) throws UserServiceException;
	
	/**
	 * 修改个人用户信息（本地库修改少数字段）
	 * @param geUserPersonal
	 * @param updateStrings 要修改的字段
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserPart(Map map,String pk) throws UserServiceException;
	
	
	/**
	 * 从LDAP查询一个用户信息
	 * @param geUserPersonal
	 * @return PersonalUser对象(result 1-成功，2-失败(查询信息不存在))
	 * @throws portalModuleException TODO
	 */
	public PersonalUserDto findUserFromLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * 向LDAP插入一个用户信息
	 * @param geUserPersonal
	 * @return 1-成功，2-失败（用户号重复），3-失败（用户名重复），4-失败（邮箱重复），5-失败（身份证重复），6-失败（鹤卡号重复）
	 * @throws portalModuleException TODO
	 */
	public PersonalUserDto insertUserToLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * 修改LDAP的用户信息
	 * @param geUserPersonal
	 * @return 1-成功，2-失败(用户号不存在)，3-失败（用户名重复），4-失败（邮箱重复），5-失败（身份证重复），6-失败(鹤卡号重复)
	 * @throws portalModuleException TODO
	 */
	public  PersonalUserDto updateUserToLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * 获取用户号
	 * @return
	 */
	public String getUserID() throws UserServiceException;
	
	/**
	 * 获取激活码
	 * @return
	 */
	public String getActiveCode() throws UserServiceException;
	
	/**
	 * 发送激活码
	 * @param geUserPersonal
	 * @param sendType 1-手机，2-邮箱
	 * @return
	 */
	public boolean sendActiveCode(GeUserPersonal geUserPersonal,String sendType,String activeCode) throws UserServiceException, CheckedMailException,UnsupportedEncodingException;
	
	/**
	 * 判断用户激活功能开关是否开通
	 * 
	 * @return
	 */
	public boolean activeSwitchIsOpen();
	
	/**
	 * 将用户信息发送到邮箱
	 * @param geUserPersonal
	 * @return
	 * @throws CheckedMailException 
	 * @throws UnsupportedEncodingException 
	 */
	public boolean sendUserMessage(GeUserPersonal geUserPersonal,String proposalNo,String production) throws UnsupportedEncodingException, CheckedMailException;
	
	/**
	 * 根据用户登录名查找用户（大小写不敏感）
	 * @param userAccount
	 * @return
	 * @throws UserServiceException
	 */
	public List<GeUserPersonal> getUserListByName(String userAccount)throws UserServiceException;


	void activationAccount(GeUserPersonal user);

	void saveUserPersonal(GeUserPersonal user, HttpServletRequest httpServletRequest) throws Exception;

	/**
	 * 校验客户注册时输入的邮箱是否已存在。
	 * true:不存在
	 * false:已存在
	 */
	boolean checkUserPersonalName(String email);

	GeUserPersonal validateUserPersonal(GeUserPersonal userPersonal);

	GeUserPersonal sinaLogin(HttpServletRequest request);

	GeUserPersonal checkMailOrPhone(String resetPassword);

	GeUserPersonal resetPassword(String param);

	GeUserPersonal alipayLoginInfo(HttpServletRequest request);

	boolean checkUserPersonalMobile(String mobile);

	public boolean userPersonalNumberValidate(String number);

	GeUserPersonal getUserPersonalByUserId(String userId);

	GeUserPersonal updateUserPersonal(GeUserPersonal UserPersonal);

	GeUserPersonal getUserPersonalByName(String username);

	boolean userPersonalMobileValidate(String mobile);

	public GeUserPersonal qqLogin(HttpServletRequest request);

	public GeUserPersonal validateUniqueId(String id);

	public GeUserPersonal getUserPersonalByEmailOrPhone(String emailOrPhone);
	
	public String getPhoneDynamicNumber(String mobile);

	public String sendValidateCodeByEmail(String email);

	public String getIdTypes();

	public String getRelatedToApplicants();

	public GeUserPersonal getUserPersonalByUserAccount(String userAccount);

	public GeUserPersonal checkLogin(String userName, String password);

	public abstract boolean checkChanged(GeUserPersonal customer);

	public boolean checkBindPolicy(String userID);

	public String checkPasswordGrade(String pwd);
	public String sendCodeByEmail(String email);
	
	public void getAuthorities(GeUserPersonal userPersonal, String real_name) ;

	public List<PolicyList> findPolicyList(String userId);

	public boolean findPolicyByOrder(String userID);
	
	public int countUserPersonalByMobile(String mobile);
	
	public int existCustomerByUserAccount(String value);
	
	public int checkIsCustomer(String date);

	public List<GeUserPersonal> getUserPersonalByIdNum(String idNum, String password);

	public boolean updateLoginFailedCount(GeUserPersonal geUserPersonal);
	
	public void unlockUserAccount(String userId) ;

	public void updateGeUserPersonalBySql(GeUserPersonal customer);
	
	public Integer getTransmissionTimesByAccount(String account);

	public void saveTransmissionTimes(String account, Integer count);

	public void saveMobilePhoneCode(String phoneNum, String validateCode);
	
	public MobilePhoneCode findMobilePhoneCode(String phoneNum);
}
