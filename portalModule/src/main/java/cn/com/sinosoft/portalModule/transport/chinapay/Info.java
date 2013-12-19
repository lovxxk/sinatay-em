package cn.com.sinosoft.portalModule.transport.chinapay;


/**
 * @author w
 *
 */
public class Info implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 属性 交易代码  */
	private String trx_Code;

	/** 属性 版本 */
	private String version;
	
	/** 属性 数据格式 */
	private String data_Type;
	
	/** 属性 处理级别  */
	private String level;

	/** 属性 用户名*/
	private String user_Name;
	
	/** 属性 用户密码 */
	private String user_Pass;
	
	/** 属性 交易流水号  */
	private String req_Sn;

	/** 属性 签名信息 */
	private String signed_Msg;
	
	/** 属性 返回代码 */
	private String ret_Code;
	
	/** 属性 错误信息  */
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

	public String getTrx_Code() {
		return trx_Code;
	}

	public void setTrx_Code(String trx_Code) {
		this.trx_Code = trx_Code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getData_Type() {
		return data_Type;
	}

	public void setData_Type(String data_Type) {
		this.data_Type = data_Type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getUser_Pass() {
		return user_Pass;
	}

	public void setUser_Pass(String user_Pass) {
		this.user_Pass = user_Pass;
	}

	public String getReq_Sn() {
		return req_Sn;
	}

	public void setReq_Sn(String req_Sn) {
		this.req_Sn = req_Sn;
	}

	public String getSigned_Msg() {
		return signed_Msg;
	}

	public void setSigned_Msg(String signed_Msg) {
		this.signed_Msg = signed_Msg;
	}
	
	
	

}
