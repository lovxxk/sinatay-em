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
 * �����û���Ϣ����ӿ�
 * @version 1.0
 *  
 *
 */
public interface GeUserPersonalService extends GenericDao<GeUserPersonal, String> {
	/**
	 * ��֤ԭ�����Ƿ���Ч
	 * @param uid
	 * @param pwd
	 * @return
	 */
	public boolean valPwd(String uid,String pwd);
	
	/**
	 * �����û���Ϣע�ᣨ���ܣ�
	 * @param geUserPersonal �����û�ʵ����
	 * @return 0-���汾�ؿ�ʧ�ܣ�1-�ɹ���2-ʧ�ܣ��û����ظ�����3-ʧ�ܣ��û����ظ�����4-ʧ�ܣ������ظ�����5-ʧ�ܣ����֤�ظ�����6-ʧ�ܣ��׿����ظ���
	 */
	public List registerWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * �����û���Ϣע�ᣨ���ܣ�
	 * @param geUserPersonal �����û�ʵ����
	 * @return GeUserPersonal �����û���Ϣ������results�������ֵ�ĺ��壺 0-���汾�ؿ�ʧ�ܣ�1-�ɹ���2-ʧ�ܣ��û����ظ�����3-ʧ�ܣ��û����ظ�����4-ʧ�ܣ������ظ�����5-ʧ�ܣ����֤�ظ�����6-ʧ�ܣ��׿����ظ���
	 */
	public GeUserPersonal registerFromWap(GeUserPersonal geUserPersonal)  throws UserServiceException;
	
	/**
	 * ���ݵ�½��Ϣ��ȡ��½���󣨹��ܣ�
	 * @param geUserPersonal �����û�ʵ����
	 * @return GeUserPersonal
	 */
	public GeUserPersonal getUserPersonalForLoginWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * �����û�����
	 * @param geUserPersonal
	 * @param oldActiveCode ԭ�����루����session��Ϊ�����ͷ�ʽΪ���䷢�ͣ�
	 * @param newActiveCode �ͻ���д�Ļ���
	 * @return 00-����ʧ��  01-����ɹ� 02-��������� 03-���伤��ʱ����ԭ������Ϊ��
	 * @throws Exception
	 */
	public String activeAccountWithLDAP(GeUserPersonal geUserPersonal,String oldActiveCode,String newActiveCode) throws UserServiceException;
	
	/**
	 * �����û��������ã����ܣ�
	 * ˵�������÷�ʽ1���˻�+�ֻ��������÷�ʽ�������䣩
	 * ע���˻�ֻ�м���ע��Ĳ���
	 * @param geUserPersonal �����û�ʵ����
	 * @return 1-�ɹ���01-��д�ͻ���Ϣ����02-��������ʧ��
	 */
	public List resetPasswordWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException, CheckedMailException,UnsupportedEncodingException;
	
	/**
	 * �޸ĸ����û���Ϣ�����ܣ�
	 * @param geUserPersonal �����û�ʵ����
	 * @return 1-�ɹ���2-ʧ��(�û��Ų�����)��3-ʧ�ܣ��û����ظ�����4-ʧ�ܣ������ظ�����5-ʧ�ܣ����֤�ظ�����6-ʧ��(�׿����ظ�) 7-�޸ı��ؿ�ʧ��
	 */
	public List updateGeUserPersonalWithLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * �޸ĸ����û����루���ܣ�
	 * @param geUserPersonal �����û�ʵ����
	 * @return 00-ԭ�������1-�ɹ���2-ʧ��(�û��Ų�����)��3-ʧ�ܣ��û����ظ�����4-ʧ�ܣ������ظ�����5-ʧ�ܣ����֤�ظ�����6-ʧ��(�׿����ظ�) 7-�޸ı��ؿ�����ʧ��
	 */
	public List updatePasswordWithLDAP(GeUserPersonal geUserPersonal,String newPassword) throws UserServiceException;
	
	/**
	 * ������ldap�������صĽ����תΪ������Ϣ�Ķ�Ӧ�������أ��ɹ���������
	 * @param list ���뼯��
	 * @return ��������
	 */
	public List getInfoFromCode(List<String> list);
	
	/**
	 * ����������ѯ�����û���Ϣ
	 * @param userID
	 * @return
	 */
	public GeUserPersonal findUserByPK(String userID) throws UserServiceException;
	/**
	 * ��ѯ�����û���Ϣ����ѯ���ؿ⣩
	 * @param queryRule
	 * @return
	 */
	public GeUserPersonal findUser(QueryRule queryRule) throws UserServiceException;
	
	public GeUserPersonal findUsers(QueryRule queryRule) throws UserServiceException;
	
	/**
	 * ��ѯ�����û���Ϣ�б���ҳ��ѯ���ؿ⣩
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findUser(QueryRule queryRule,int pageNo,int pageSize) throws UserServiceException;
	
	/**
	 * ��������û���Ϣ�����汾�ؿ⣩
	 * @param geUserPersonal
	 * @return
	 */
	public boolean saveUser(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * �޸ĸ����û���Ϣ�����ؿ��޸Ķ����ֶΣ�
	 * @param geUserPersonal
	 * @param unUpdateStrings ���޸ĵ��ֶ�
	 * @return
	 * @throws Exception
	 */
	public boolean updateUser(GeUserPersonal geUserPersonal,String[] unUpdateStrings) throws UserServiceException;
	
	/**
	 * �޸ĸ����û���Ϣ�����ؿ��޸������ֶΣ�
	 * @param geUserPersonal
	 * @param updateStrings Ҫ�޸ĵ��ֶ�
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserPart(Map map,String pk) throws UserServiceException;
	
	
	/**
	 * ��LDAP��ѯһ���û���Ϣ
	 * @param geUserPersonal
	 * @return PersonalUser����(result 1-�ɹ���2-ʧ��(��ѯ��Ϣ������))
	 * @throws portalModuleException TODO
	 */
	public PersonalUserDto findUserFromLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * ��LDAP����һ���û���Ϣ
	 * @param geUserPersonal
	 * @return 1-�ɹ���2-ʧ�ܣ��û����ظ�����3-ʧ�ܣ��û����ظ�����4-ʧ�ܣ������ظ�����5-ʧ�ܣ����֤�ظ�����6-ʧ�ܣ��׿����ظ���
	 * @throws portalModuleException TODO
	 */
	public PersonalUserDto insertUserToLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * �޸�LDAP���û���Ϣ
	 * @param geUserPersonal
	 * @return 1-�ɹ���2-ʧ��(�û��Ų�����)��3-ʧ�ܣ��û����ظ�����4-ʧ�ܣ������ظ�����5-ʧ�ܣ����֤�ظ�����6-ʧ��(�׿����ظ�)
	 * @throws portalModuleException TODO
	 */
	public  PersonalUserDto updateUserToLDAP(GeUserPersonal geUserPersonal) throws UserServiceException;
	
	/**
	 * ��ȡ�û���
	 * @return
	 */
	public String getUserID() throws UserServiceException;
	
	/**
	 * ��ȡ������
	 * @return
	 */
	public String getActiveCode() throws UserServiceException;
	
	/**
	 * ���ͼ�����
	 * @param geUserPersonal
	 * @param sendType 1-�ֻ���2-����
	 * @return
	 */
	public boolean sendActiveCode(GeUserPersonal geUserPersonal,String sendType,String activeCode) throws UserServiceException, CheckedMailException,UnsupportedEncodingException;
	
	/**
	 * �ж��û�����ܿ����Ƿ�ͨ
	 * 
	 * @return
	 */
	public boolean activeSwitchIsOpen();
	
	/**
	 * ���û���Ϣ���͵�����
	 * @param geUserPersonal
	 * @return
	 * @throws CheckedMailException 
	 * @throws UnsupportedEncodingException 
	 */
	public boolean sendUserMessage(GeUserPersonal geUserPersonal,String proposalNo,String production) throws UnsupportedEncodingException, CheckedMailException;
	
	/**
	 * �����û���¼�������û�����Сд�����У�
	 * @param userAccount
	 * @return
	 * @throws UserServiceException
	 */
	public List<GeUserPersonal> getUserListByName(String userAccount)throws UserServiceException;


	void activationAccount(GeUserPersonal user);

	void saveUserPersonal(GeUserPersonal user, HttpServletRequest httpServletRequest) throws Exception;

	/**
	 * У��ͻ�ע��ʱ����������Ƿ��Ѵ��ڡ�
	 * true:������
	 * false:�Ѵ���
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
