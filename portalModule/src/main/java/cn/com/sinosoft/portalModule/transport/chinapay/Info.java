package cn.com.sinosoft.portalModule.transport.chinapay;


/**
 * @author w
 *
 */
public class Info implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** ���� ���״���  */
	private String trx_Code;

	/** ���� �汾 */
	private String version;
	
	/** ���� ���ݸ�ʽ */
	private String data_Type;
	
	/** ���� ������  */
	private String level;

	/** ���� �û���*/
	private String user_Name;
	
	/** ���� �û����� */
	private String user_Pass;
	
	/** ���� ������ˮ��  */
	private String req_Sn;

	/** ���� ǩ����Ϣ */
	private String signed_Msg;
	
	/** ���� ���ش��� */
	private String ret_Code;
	
	/** ���� ������Ϣ  */
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
