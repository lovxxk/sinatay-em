package cn.com.sinosoft.portalModule.transport.chinapay;

public class TransDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 属性 记录序号  */
	private String sn;

	/** 属性 银联网络用户编号 */
	private String e_User_Code;
	
	/** 属性银行代码*/
	private String bank_Code;
	
	/** 属性 账号类型  */
	private String account_Type;

	/** 属性 账号*/
	private String account_No;
	
	/** 属性 账号名  */
	private String account_Name;

	/** 属性 开户行所在省 */
	private String province;
	
	/** 属性 开户行所在市*/
	private String city;
	
	/** 属性 开户行名称 */
	private String bank_Name;

	/** 属性 账号属性*/
	private String account_Prop;
	
	/** 属性 金额 */
	private String amount;

	/** 属性 货币类型*/
	private String currency;
	
	/** 属性 协议号 */
	private String protocol;
	
	/** 属性 协议用户编号  */
	private String protocol_Userid;

	/** 属性 开户证件类型*/
	private String id_Type;
	
	/** 属性 证件号*/
	private String id;
	
	/** 属性 手机号/小灵通*/
	private String tel;
	
	/** 属性 清算账号*/
	private String cust_Userid;
	
	/** 属性 自定义用户号  */
	private String reckon_Account;

	/** 属性 备注*/
	private String remark;
	
	/** 属性 保留域1*/
	private String reserved1;
	
	/** 属性 保留域2 */
	private String reserved2;
	
	/** 属性 保留域3 */
	private String reserved3;
	
	/** 属性 保留域4 */
	private String reserved4;
	
	/** 属性 电子联行号 */
	private String ele_Bankno;
	
	/** 属性 摘要 */
	private String abs;
	
	/** 属性 附言 */
	private String ps;
	
	/** 属性 用途 */
	private String use;
	
	/** 属性 信用卡有效期 */
	private String cre_Val_Date;
	
	/** 属性 CVN2码*/
	private String cre_Cvn2;
	
	/** 属性 账号密码 */
	private String acc_Pass;
	
	/** 属性 返回码*/
	private String ret_Code;
	
	/** 属性 错误文本*/
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
