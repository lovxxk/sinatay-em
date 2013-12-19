package cn.com.sinosoft.portalModule.transport.chinapay;

public class TransDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** ���� ��¼���  */
	private String sn;

	/** ���� ���������û���� */
	private String e_User_Code;
	
	/** �������д���*/
	private String bank_Code;
	
	/** ���� �˺�����  */
	private String account_Type;

	/** ���� �˺�*/
	private String account_No;
	
	/** ���� �˺���  */
	private String account_Name;

	/** ���� ����������ʡ */
	private String province;
	
	/** ���� ������������*/
	private String city;
	
	/** ���� ���������� */
	private String bank_Name;

	/** ���� �˺�����*/
	private String account_Prop;
	
	/** ���� ��� */
	private String amount;

	/** ���� ��������*/
	private String currency;
	
	/** ���� Э��� */
	private String protocol;
	
	/** ���� Э���û����  */
	private String protocol_Userid;

	/** ���� ����֤������*/
	private String id_Type;
	
	/** ���� ֤����*/
	private String id;
	
	/** ���� �ֻ���/С��ͨ*/
	private String tel;
	
	/** ���� �����˺�*/
	private String cust_Userid;
	
	/** ���� �Զ����û���  */
	private String reckon_Account;

	/** ���� ��ע*/
	private String remark;
	
	/** ���� ������1*/
	private String reserved1;
	
	/** ���� ������2 */
	private String reserved2;
	
	/** ���� ������3 */
	private String reserved3;
	
	/** ���� ������4 */
	private String reserved4;
	
	/** ���� �������к� */
	private String ele_Bankno;
	
	/** ���� ժҪ */
	private String abs;
	
	/** ���� ���� */
	private String ps;
	
	/** ���� ��; */
	private String use;
	
	/** ���� ���ÿ���Ч�� */
	private String cre_Val_Date;
	
	/** ���� CVN2��*/
	private String cre_Cvn2;
	
	/** ���� �˺����� */
	private String acc_Pass;
	
	/** ���� ������*/
	private String ret_Code;
	
	/** ���� �����ı�*/
	private String err_Msg;

	
	
	public String getRet_Code() {
		return ret_Code;
	}

	public void setRet_Code(String ret_Code) {
		this.ret_Code = ret_Code;
	}

	public String getErr_Msg() {
		return err_Msg;
	}

	public void setErr_Msg(String err_Msg) {
		this.err_Msg = err_Msg;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getE_User_Code() {
		return e_User_Code;
	}

	public void setE_User_Code(String e_User_Code) {
		this.e_User_Code = e_User_Code;
	}

	public String getBank_Code() {
		return bank_Code;
	}

	public void setBank_Code(String bank_Code) {
		this.bank_Code = bank_Code;
	}

	public String getAccount_Type() {
		return account_Type;
	}

	public void setAccount_Type(String account_Type) {
		this.account_Type = account_Type;
	}

	public String getAccount_No() {
		return account_No;
	}

	public void setAccount_No(String account_No) {
		this.account_No = account_No;
	}

	public String getAccount_Name() {
		return account_Name;
	}

	public void setAccount_Name(String account_Name) {
		this.account_Name = account_Name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBank_Name() {
		return bank_Name;
	}

	public void setBank_Name(String bank_Name) {
		this.bank_Name = bank_Name;
	}

	public String getAccount_Prop() {
		return account_Prop;
	}

	public void setAccount_Prop(String account_Prop) {
		this.account_Prop = account_Prop;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getProtocol_Userid() {
		return protocol_Userid;
	}

	public void setProtocol_Userid(String protocol_Userid) {
		this.protocol_Userid = protocol_Userid;
	}

	public String getId_Type() {
		return id_Type;
	}

	public void setId_Type(String id_Type) {
		this.id_Type = id_Type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCust_Userid() {
		return cust_Userid;
	}

	public void setCust_Userid(String cust_Userid) {
		this.cust_Userid = cust_Userid;
	}

	public String getReckon_Account() {
		return reckon_Account;
	}

	public void setReckon_Account(String reckon_Account) {
		this.reckon_Account = reckon_Account;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getReserved4() {
		return reserved4;
	}

	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}

	public String getEle_Bankno() {
		return ele_Bankno;
	}

	public void setEle_Bankno(String ele_Bankno) {
		this.ele_Bankno = ele_Bankno;
	}

	public String getAbs() {
		return abs;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getCre_Val_Date() {
		return cre_Val_Date;
	}

	public void setCre_Val_Date(String cre_Val_Date) {
		this.cre_Val_Date = cre_Val_Date;
	}

	public String getCre_Cvn2() {
		return cre_Cvn2;
	}

	public void setCre_Cvn2(String cre_Cvn2) {
		this.cre_Cvn2 = cre_Cvn2;
	}

	public String getAcc_Pass() {
		return acc_Pass;
	}

	public void setAcc_Pass(String acc_Pass) {
		this.acc_Pass = acc_Pass;
	}
	
	
}
