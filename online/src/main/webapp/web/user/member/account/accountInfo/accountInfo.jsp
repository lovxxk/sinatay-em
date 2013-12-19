<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<link href="${ctx}/resources/css/user/my_policy_detail.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
var timenum = 0;
var intervalProcess;
function counttime() {
	timenum = $("#num").val();
	if (timenum > 0) {
		$('.time').text(timenum);
		$('#getCode').val('('+timenum+')������·���');
		
		$('#getCode').valiCodeDisable();
		
		timenum = timenum - 1;
		$("#num").attr("value", timenum);
	} else {
		$('#getCode').val('�ٴη���');
		$('#getCode').valiCodeEnable();
		clearInterval(intervalProcess);
		$("#num").attr("value", 120);
	}
}
function sendCode() {
	var updateType = $("#updateType").val();
	var parameter = "";
	if (updateType == 'email') {
		parameter = "�����ַ";
	} else {
		parameter = "�ֻ�����";
	}
	var customerName = $("#emailOrPhone").val();
	if (customerName == "") {
		Sinosoft.alert({
			contentStr: "������Ϸ���" + parameter,
			width:480,
			okStr: 'ȷ��',
			cancelStr: 'ȡ��',
			okFunc:function(){
				doReceive(parameter, "", "");
			}
		});
		return;
	}
	if (updateType == 'mobilePhone') {
		//11λ�ֻ����Ƿ�Ϸ�
		var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName);
		if (!regmobile) {
			Sinosoft.alert({
				contentStr: "������Ϸ���" + parameter,
				width:480,
				okStr: 'ȷ��',
				cancelStr: 'ȡ��',
				okFunc:function() {
					doReceive(parameter, customerName, "");
				}
			});
			return false;
		} else {
			phoneFalg = true;
		}
	} else {
		//�ж��û�¼����Ƿ��������Լ������ַ�Ƿ�Ϸ�
		if (customerName.indexOf("@") > 0) {
			//�û�¼��������Ƿ�Ϸ�
			var regemail =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(customerName);
			if(!regemail){
				Sinosoft.alert({
					contentStr: '����дһ����ȷ��' + parameter,
					width:480,
					okStr: 'ȷ��',
					cancelStr: 'ȡ��',
					okFunc:function(){
						doReceive(parameter, customerName, "");
					}
				});
				return false;
			} else {
				emailFalg = true;
			}
		} else {
			Sinosoft.alert({
				contentStr: '����дһ����ȷ��' + parameter,
				width:480,
				okStr: 'ȷ��',
				cancelStr: 'ȡ��',
				okFunc:function(){
					doReceive(parameter, customerName, "");
				}
			});
			return;
		}
	}
	
	//��У���ֻ����Ƿ����
	if (updateType == 'mobilePhone') {
		$.ajax({
			type : "POST",
			async : false,
			url : contextRootPath+"/edit/existCustomerByUserAccount.do",
			dataType : 'json',
			data:{
				value : customerName
			},
			success : function(data) {
				console.log(data.count);
				if (data.count != '' && data.count > 0) {
					Sinosoft.alert({
						contentStr : parameter+"�Ѵ��ڣ����ܰ�",
						width : 480,
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, '');
							return false;
						}
					});
				}else{
					$.ajax({
						url : '${ctx}/edit/getUserPersonalPhonePwd.do',
						type : 'POST',
						async : false,
						data : "phoneNum=" + customerName,
						dataType : "text",
						success : function(data){
							if (data == "success") {
								var mobileStr = '';
								if(customerName != '' && customerName.length == 11){
									mobileStr = customerName.substring(0,3)+"****"+customerName.substring(7,customerName.length);
								}
								$("#alertMessage").text("��֤���ѷ��͵�" + mobileStr + ",��ע�����.");
								$("#validateCode").attr("readOnly", false);
								intervalProcess = setInterval('counttime()',1000);
							} else if (data == "limit") {
								Sinosoft.alert({
									contentStr: "�ǳ���Ǹ�����������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�",
									width:480,
									okStr: 'ȷ��',
									cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
									okFunc:function(){
										doReceive(parameter, customerName, "");
									}
								});
							}else {
								Sinosoft.alert({
									contentStr: "���͹���Ƶ�������Ժ����ԣ�",
									width:480,
									okStr: 'ȷ��',
									cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
									okFunc:function(){
										
									}
								});
							}
						}
					});
				}
			}
		});
	} else {
		$.ajax({
			type : "POST",
			async : false,
			url : contextRootPath+"/edit/existCustomerByUserAccount.do",
			dataType : 'json',
			data:{
				value : customerName
			},
			success : function(data) {
				console.log(data.count);
				if (data.count != '' && data.count > 0) {
					Sinosoft.alert({
						contentStr : parameter+"�Ѵ��ڣ����ܰ�",
						width : 480,
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc : function() {
							doReceive(parameter, customerName, '');
							return false;
						}
					});
				}else{
					$.ajax({
						url : '${ctx}/edit/sendCodeByEmail.do',
						type : 'POST',
						async : false,
						data : "email=" + customerName,
						dataType : "text",
						success : function(data) {
							if (data == "success") {
								$("#alertMessage").text("��֤���ѷ��͵�" + customerName + ",��ע�����.");
								$("#validateCode").attr("readOnly", false);
								intervalProcess = setInterval('counttime()',1000);
							} else {
								Sinosoft.alert({
									contentStr: "���ŷ��͹���Ƶ�������Ժ����ԣ�",
									width:480,
									okStr: 'ȷ��',
									cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
									okFunc:function(){
										
									}
								});
							}
						}
					});
				}
			}
		});
	}
	
}
function loadReceiveInput(parameter, param, code) {
	var success = $('<div class="bind_info"><span id="alertMessage" style="color:red;"></span>'
			+ '<div class="bind_input"><label class="bind_label">������'
			+ parameter
			+ '��</label><input class="bind_text" type="text" name="emailOrPhone" id="emailOrPhone" maxlength=50 value="'+param+'"/></div>'
			+ '<div class="bind_input"><label class="bind_label">��֤�룺</label>'
			+ '<input class="bind_text vali" type="text" readonly="true" id="validateCode" maxlength=6 value="'+code+'"/>'
			+ '<input class="send_vali" type="button" id="getCode" onclick="sendCode();" value="������֤��"/></div>'
			+ '</div>');
	return success;
}

function doAuthenticate(){
	var authTpl = '<ul style="" class="auth"><li>1������������Ͷ������֤������ݡ�<a hrf="">���ھ�ȥ</a></li><li>2�����Ƹ�����Ϣ���������±�����<a href="">���ھ�ȥ</a></li></ul>';
	new Sinosoft.InteractiveDialog({
		layout : authTpl,
		width : 490,
		cancelStr : 'ȷ��',
		okBtnShow : false,
		cancelFunc : function() {
		}
	}).open();
}

function doReceive(parameter, param, code){
		var receive_input = loadReceiveInput(parameter, param, code);
		var updateType = $("#updateType").val();

		var emailOrPhone = $("#receive_text").val();
		new Sinosoft.InteractiveDialog({
			layout : receive_input,
			width : 490,
			cancelStr : 'ȷ��',
			okBtnShow : false,
			cancelFunc : function() {
				var customerName = $("#emailOrPhone").val();
				if (customerName == "") {
					Sinosoft.alert({
						contentStr : "������Ϸ���" + parameter,
						width : 480,
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc : function() {
							doReceive(parameter, "", "");
						}
					});
					return;
				}
				if (updateType == 'mobilePhone') {
					//11λ�ֻ����Ƿ�Ϸ�
					var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(customerName);
					if (!regmobile) {
						Sinosoft.alert({
							contentStr : "������Ϸ���" + parameter,
							width : 480,
							okStr : 'ȷ��',
							cancelStr : 'ȡ��',
							okFunc : function() {
								doReceive(parameter, customerName, "");
							}
						});
						return false;
					} else {
						phoneFalg = true;
					}
				} else {
					//�ж��û�¼����Ƿ��������Լ������ַ�Ƿ�Ϸ�
					if (customerName.indexOf("@") > 0) {
						//�û�¼��������Ƿ�Ϸ�
						var regemail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
								.test(customerName);
						if (!regemail) {
							Sinosoft.alert({
								contentStr : '����дһ����ȷ��' + parameter,
								width : 480,
								okStr : 'ȷ��',
								cancelStr : 'ȡ��',
								okFunc : function() {
										(parameter, customerName, "");
								}
							});
							return false;
						} else {
							emailFalg = true;
						}
					} else {
						Sinosoft.alert({
							contentStr : '����дһ����ȷ��' + parameter,
							width : 480,
							okStr : 'ȷ��',
							cancelStr : 'ȡ��',
							okFunc : function() {
								doReceive(parameter, customerName, "");
							}
						});
						return;
					}
				}

				var validateCode = $("#validateCode").val();
				if (validateCode == "") {
					Sinosoft.alert({
						contentStr : "��������֤��",
						width : 480,
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc : function() {
							doReceive(parameter, customerName, "");
						}
					});
					return;
				}
				var validateCodeFlag = "";
				$.ajax({
					type : "POST",
					async : false,
					url : "${ctx }/edit/checkEmailValidateCode.do",
					dataType : "text",
					data : {
						pwd : validateCode,
						customerName : customerName
					},
					success : function(data) {
						validateCodeFlag = data;
					}
				});
				if (validateCodeFlag == "paramError") {
					Sinosoft.alert({
						contentStr : "��������",
						width : 480,
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, "");
							return;
						}
					});
					return;
				} else if (validateCodeFlag == "error") {
					Sinosoft.alert({
						contentStr : "��֤���������",
						width : 480,
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, "");
							return;
						}
					});
					return;
				} else if (validateCodeFlag == "invalid") {
					Sinosoft.alert({
						contentStr : "��֤����ʧЧ�������»�ȡ��֤��",
						width : 480,
						okStr : 'ȷ��',
						cancelStr : 'ȡ��',
						okFunc : function() {
							$("#num").attr("value", "120");
							clearInterval(intervalProcess);
							doReceive(parameter, customerName, "");
							return;
						}
					});
					return;
				}
				
				 if (updateType == 'mobilePhone') {
					if (!ExistCustomer("1", customerName)) {
						Sinosoft.alert({
							contentStr : "�ֻ������Ѵ��ڣ����ܰ�",
							width : 480,
							okStr : 'ȷ��',
							cancelStr : 'ȡ��',
							okFunc : function() {
								$("#num").attr("value", "120");
								clearInterval(intervalProcess);
								doReceive(parameter, customerName, "");
								return false;
							}
						});
						return;
					}
				} else {
					if (!ExistCustomer("2", customerName)) {
						Sinosoft.alert({
							contentStr : "�����ַ�Ѵ��ڣ����ܰ�",
							width : 480,
							okStr : 'ȷ��',
							cancelStr : 'ȡ��',
							okFunc : function() {
								$("#num").attr("value", "120");
								clearInterval(intervalProcess);
								doReceive(parameter, customerName, "");
								return false;
							}
						});
						return;
					}
				}
				
				$.ajax({
					url : '${ctx}/edit/supplyUserPersonal.do',
					type : 'POST',
					async : false,
					data : "param=" + customerName,
					dataType : "text",
					success : function(data) {
						if (data == 'success') {
							new Sinosoft.InteractiveDialog({
								layout : bindSuccess(),
								width:410,//�Զ��������-�������������
								cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
								okStr : 'ȷ��',
								cancelStr : 'ȡ��',
								okFunc:function(){
									window.location.href = "${ctx}/edit/accountInfo.do"
								}
							}).open();
						} else if(data == 'existsmobile'){
							Sinosoft.alert({
								contentStr: "���ֻ������ѱ��󶨣�",
								width:480,
								okStr: 'ȷ��',
								cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
								okFunc:function(){
									$("#num").attr("value", "120");
									clearInterval(intervalProcess);
									doReceive(parameter, customerName, "");
								}
							});
						} else if (data == "existsEmail") {
							Sinosoft.alert({
								contentStr: "�������ַ�ѱ��󶨣�",
								width:480,
								okStr: 'ȷ��',
								cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
								okFunc:function(){
									$("#num").attr("value", "120");
									clearInterval(intervalProcess);
									doReceive(parameter, customerName, "");
								}
							});
						} else {
							Sinosoft.alert({
								contentStr: "ϵͳ�쳣,���Ժ�����",
								width:480,
								okStr: 'ȷ��',
								cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
								okFunc:function(){
									$("#num").attr("value", "120");
									clearInterval(intervalProcess);
								}
							});
						}
					}
				});
			}
		}).open();
	}
	

//У���ֻ������Ƿ���ע��
//true����ע��;false��δע��
function ExistCustomer(index, customerName) {
	var msg = "����";
	if(index == 1) 
		msg = "�ֻ���";
	var flag = true;
	$.ajax({
		type : "POST",
		async : false,
		url : contextRootPath+"/edit/existCustomerByUserAccount.do",
		dataType : 'json',
		data:{
			value : customerName
		},
		success : function(data) {
			console.log(data.count);
			if (data.count != '' && data.count > 0) {
				Sinosoft.alert({
					contentStr : msg+"�Ѵ��ڣ����ܰ�",
					width : 480,
					okStr : 'ȷ��',
					cancelStr : 'ȡ��',
					okFunc : function() {
						$("#num").attr("value", "120");
						clearInterval(intervalProcess);
						doReceive(parameter, customerName, '');
						flag = true;
					}
				});
			}
		}
	});
	return flag;
}

function ExistCustomerEmail(customerName) {
	var flag = false;
	UserPersonalService.checkUserPersonalName(customerName, customerNameCallBack);
	function customerNameCallBack(data) {
		if (data) {
			flag = true;
		} else {
			flag = false;
		}
	}
	return flag;
}

function bindSuccess(){
	var subSuccess = $('<div class="alert_subscribe">'
			+'<div class="success"></div><div class="main_content">'
			+'<div class="sub_txt">�󶨳ɹ�</div></div></div>');
	return subSuccess;
}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li><a href="#">��Ա����</a><span> &gt;</span></li>
				<li class="at">�ʺ���Ϣ</li>
			</ul>
		</div>
		<div class="member_main">
			<input type="hidden" id="num" value="120">
			<input type="hidden" class="inputPwd"/>
			<c:if test="${fn:indexOf(customer.userAccount, '@') == -1}">
				<input type="hidden" id="updateType" value="email">
			</c:if>
			<c:if test="${fn:indexOf(customer.userAccount, '@') != -1}">
				<input type="hidden" id="updateType" value="mobilePhone">
			</c:if>
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<input type="hidden" name="id" id="id" value="${customer.userID }">
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>���Ļ�����Ϣ</p>
				</div>
				<div class="base_info">
					<p>
						<c:if test="${!empty customer.email }">
							<span class="info">
								<span class="label">��¼���䣺</span>
								<span class="value">${customer.email }</span>
							</span>
							<span>
								<c:if test="${fn:indexOf(customer.userAccount, '@') != -1}">
									<span class="bindEmail">
										ע�����Ʋ����޸�
									</span>
								</c:if>
								<c:if test="${fn:indexOf(customer.userAccount, '@') == -1}">
									<span class="bindEmail">
										<a href="#" onclick="doReceive('�����ַ', '', '');">�޸�����</a>
									</span>
								</c:if>
							</span>
						</c:if>
						<c:if test="${empty customer.email }">
							<span class="info">
								<span class="label">�����ַ��</span>
								<span class="none">����</span>
							</span>
							<span class="bindEmail">
								<a href="#" onclick="doReceive('�����ַ', '', '');">������</a>
							</span>
						</c:if>
					</p>
					<p>
						<c:if test="${!empty customer.mobilePhone }">
							<span class="info">
								<span class="label">�ֻ����룺</span>
								<span class="value">${customer.mobilePhone }</span>
							</span>
							<c:if test="${fn:indexOf(customer.userAccount, '@') != -1}">
								<span class="bindPhone">
									<a href="#" onclick="doReceive('�ֻ�����','', '');">�޸��ֻ���</a>
								</span>
							</c:if>
							<c:if test="${fn:indexOf(customer.userAccount, '@') == -1}">
								<span class="bindPhone">
									ע���ʺŲ����޸�
								</span>
							</c:if>
						</c:if>
						<c:if test="${empty customer.mobilePhone }">
							<span class="info">
								<span class="label">�ֻ����룺</span>
								<span class="none">����</span>
							</span>
							<span class="bindPhone">
								<a href="#" onclick="doReceive('�ֻ�����', '', '');">���ֻ�</a>
							</span>
						</c:if>
					</p>
					<p>
						<span class="info">
							<span class="label">�ϴε�¼��</span>
							<span class="value">
								<c:if test="${empty customer.lastLoginTime}">
									<fmt:formatDate value="${customer.currentLogintime }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</c:if>
								<c:if test="${!empty customer.lastLoginTime }">
									<fmt:formatDate value="${customer.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</c:if>
							</span>
						</span>
					</p>
				</div>
				<div class="title">
					<div class="block"></div>
					<p>���İ�ȫ����</p>
				</div>
				<div class="security">
					<div class="security_item identity">
						<c:if test="${bindPolicy == true }">
							<div class="status finished">
								�����
							</div>
						</c:if>
						<c:if test="${bindPolicy == false }">
							<div class="status unfinish">
								δ���
							</div>
						</c:if>
						<div class="name">�����֤</div>
						<div class="description">���������˺ŵİ�ȫ�Ժ����μ��𡣱���������Ͷ���Ͱ���������
							��֤�󽫲����޸���֤��Ϣ</div>
						<div class="operation">
							<c:if test="${bindPolicy == false }">
								<a href="#" onclick="doAuthenticate();">��֤</a>
							</c:if>
						</div>
					</div>
					<div class="security_item password">
							<span class="intensity">
							<c:choose>
								<c:when test="${customer.passwordGrade == '0' }">
									<div class="status low">
										ǿ�ȣ�<font color="red">��</font>
									</div>
								</c:when>
								<c:when test="${customer.passwordGrade == '2' }">
									<div class="status high">
										ǿ�ȣ�<font color="red">��</font>
									</div>
								</c:when>
								<c:otherwise>
									<div class="status mid">
										ǿ�ȣ�<font color="red">��</font>
									</div>
								</c:otherwise>
							</c:choose>
						</span>
						<div class="name">��¼����</div>
						<div class="description">��ȫ�Ըߵ��������ʹ�˺Ÿ���ȫ�����������ڸ������룬������һ��
							�������ֺ���ĸ�������ȳ���6λ���ϵ����롣</div>
						<div class="operation">
							<a href="${ctx }/web/user/member/account/changePassword/index.jsp">�޸�</a>
						</div>
					</div>
					<div class="security_item cellphone">
						<c:if test="${empty customer.mobilePhone }">
							<div class="status unbind">
								δ��
							</div>
						</c:if>
						<c:if test="${!empty customer.mobilePhone }">
							<div class="status bind">
								�Ѱ�
							</div>
						</c:if>
						<div class="name">�ֻ���</div>
						<div class="description">���ֻ���������������̩�ḻ���ֻ��������ֻ���¼���ֻ��һ�
							���롢�ֻ���֤�ȡ�</div>
						<div class="operation">
							<c:set var="userAccount" value="${customer.userAccount}"></c:set>
							<c:if test="${empty customer.mobilePhone}">
								<a href="#" onclick="doReceive('�ֻ�����', '', '');">��</a>
							</c:if>
							<c:if test="${not fn:contains(userAccount,'@')}">
								<a href="#" onclick="">�Ѱ�</a>
							</c:if> 
							
							<c:if test="${fn:contains(userAccount,'@') and  not empty customer.mobilePhone}">
								<a href="#" onclick="doReceive('�ֻ�����', '', '');">�޸�</a>
							</c:if>
							
						</div>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
