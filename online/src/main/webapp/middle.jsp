<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>

<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/global/css/emailpop.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery/emailpop.js"></script>
<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$("#user_name").emailpop();
		$("#user_name").blur(function(){
			//hideIdAccount();
			checkUserName();
		});
	});
	function checkUserName(){
		var userName = $("#user_name").val();
		if(userName == "" || userName == "��������������/�ֻ�/���֤"){
			$("#message").html("�������¼�˺�!");
			return false;
		}else{
			var regmobile =  /^(1[3|5|8|4][0-9]\d{8})$/.test(userName);
			var regemail = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(userName);
			var eighteenCard = /^[\d]{6}(19|20)*[\d]{2}((0[1-9])|(10|11|12))([012][\d]|(30|31))[\d]{3}[xX\d]$/.test(userName);
			var fifteenCard = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(userName);
			var card = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(userName);
			if(regmobile||regemail){
				hideIdAccount();
			}else{
				if((userName.length == 15&& fifteenCard) || (userName.length == 18 && eighteenCard)){
					var result = "";
					$.ajax({
						type : "POST",
						async : false,
						url : "${ctx}/login/checkUserName.do",
						dataType : 'text',
						data : {userName : userName },
						success: function(data) {
							result = data;
						}
					});
					if(result.indexOf("%") > 0){
						var accounts = result.split("%%%");
						var accs = '';
						for (var i = 0;i < accounts.length; i ++) {
							var account = accounts[i];
							if(accounts[i].indexOf("@") < 0){
								account = account.substring(0,3)+"****"+account.substring(7,account.length);
							}
							accs = accs + '<div class="account_item"><input type="radio" name="account1" value="'+accounts[i]+'">'+account+'</input><br></div>';
						}
						$(".account_item").remove();
						$("#_account").append(accs);
						$('.id_account').show();
					}else{
						hideIdAccount();
					}
				}else if(card){
					Sinosoft.alert({
						contentStr: "֤���Ŵ���",
						width:480,
						okStr: 'ȷ��',
						cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
						okFunc:function(){
						}
					});
					return false;
				}
			}
		}
	}
	function showIdAccount(){
		$('.id_account').show();
		$('.login_frame').css({
			'margin-top':'8px',
			'height':'auto'
		});
		$('.login_submit').css('margin-bottom','26px');
	}
	
	function hideIdAccount(){
		$('.id_account').hide();
		$('.login_frame').removeAttr('style');
		$('.login_submit').css('margin-bottom','0');
	}
	
	function login() {
		var userName = $("#user_name").val();
		if (userName == "��������������/�ֻ�/���֤" || userName == "") {
			$("#message").html("�������¼�˺�!");
			return false;
		}

		var password = $("#password").val();
		if (password == "����������" || password == "") {
			$("#message").html("�������¼����!");
			return false;
		}
		
		//error���û����������
		//success���ɹ�
		//failed��δ����
		var loginFlag = "";
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/login/checkLogin.do",
			dataType : 'text',
			data : {
				userName : userName,
				password : password
			},
			success : function(data) {
				loginFlag = data;
			}
		});
		if (loginFlag == "userNameNull") {
			Sinosoft.alert({
				contentStr: "��������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function() {
					
				}
			});
			return false;
		} else if (loginFlag == "null") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "�û���������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
			}else if (loginFlag == "idNumNull") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "֤���Ż��������",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "accountLock") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		}else if (loginFlag == "error") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "�����������������",
				subContentStr:"�����������������������ʺŽ�����",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					$("#password").attr("value", "");
				}
			});
			return false;
		}else if (loginFlag == "pwdError") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "�����������������",
				subContentStr:"�����������������������ʺŽ�����",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "failed") {
			$("#message").html("�ʺ�δ���<a href='${ctx }/register/sendEmailToActive.do?userAccount="
					+ userName + "'>��������</a>");
			return false;
		} else if (loginFlag == "lock") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "bindFalse") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "����δ�������������δ���й������󶨣�����ͨ�����֤�ŵ�¼",
				width:480,
				okStr: 'ȷ��',
				cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
				okFunc:function(){
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "success") {
			window.location.href= "${ctx }/login/userPersonalLogin.do";
			return false;
		} else {//ͬһ�����֤�Ŷ�Ӧ�������
			console.log(loginFlag);
			var accounts = loginFlag.split("%%%");
			openLoginWindow(accounts);
			return false;
		}
	}

	function openLoginWindow(accounts) {
		var account = $('input[name="account1"]:checked').val();

		if (account == null || account == "") {
			Sinosoft.alert({
				contentStr : "��ѡ����Ҫ��¼���ʺ�",
				width : 480,
				okStr : 'ȷ��',
				cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
				okFunc : function() {
					
				}
			});
			return false;
		}
		var flag = document.getElementById('remeberMe').checked;
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/login/remeber.do",
			dataType : 'text',
			data : {
				remeber : flag
			},
			success : function(data) {
			}
		});

		var idLoginFlag = "";
		var password = $("#password").val();
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/login/checkLogin.do",
			dataType : 'text',
			data : {
				userName : account,
				password : password,
				loginType : "idNum"
			},
			success : function(data) {
				idLoginFlag = data;
			}
		});
		if (idLoginFlag == "userNameNull") {
			Sinosoft.alert({
				contentStr : "�û�������Ϊ��",
				width : 480,
				okStr : 'ȷ��',
				cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
				okFunc : function() {
				}
			});
		} else if (idLoginFlag == "null") {
			Sinosoft.alert({
				contentStr : "�û��������ڣ���ȷ��",
				width : 480,
				okStr : 'ȷ��',
				cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
				okFunc : function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "lock") {
			Sinosoft.alert({
				contentStr : "������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½",
				width : 480,
				okStr : 'ȷ��',
				cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
				okFunc : function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "pwdNull") {
			Sinosoft.alert({
				contentStr : "���벻��Ϊ��",
				width : 480,
				okStr : 'ȷ��',
				cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
				okFunc : function() {
					
				}
			});
		} else if (idLoginFlag == "pwdError") {
			Sinosoft.alert({
				contentStr : "���������ȷ�����������Ƿ���ȷ",
				width : 480,
				okStr : 'ȷ��',
				cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
				okFunc : function() {
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "bindFalse") {
			Sinosoft.alert({
				contentStr : "����δ���������������ʹ�����֤�ŵ�¼",
				width : 480,
				okStr : 'ȷ��',
				cancelBtnShow : false,//�Ƿ���ʾ�رհ�ť
				okFunc : function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "success") {
			window.location.href = "${ctx }/login/userPersonalLogin.do";
		}
	}

	function onReturn(evt) {
		var currKey = evt.keyCode || evt.which || evt.charCode;
		if (currKey == 13) {
			login();
		}
	}

	function loginByIdNum(accounts) {
		var subSuccess = '<div class="alert_subscribe"><div class="alert_subscribecss">��ѡ����Ҫ��¼���˺�:</div><div class="main_content"><div class="sub_txt">';
		for ( var i = 0; i < accounts.length; i++) {
			subSuccess += '<input type="radio" name="loginAccount" value="'+accounts[i]+'">'
					+ accounts[i] + '</input><br>';
		}
		subSuccess += '</div></div></div>';

		subSuccess = $(subSuccess);
		return subSuccess;
	}
</script>
<!--ҳ�����岿�� ��ʼ-->
<div class="middle">
	<div class="index_head">
		<div class="index_head_layout">
			<div class="head_action">
				<div class="action_layout head1 active">
					<a href="${ctx }/productsDisplay/onlineShop.do" target="_blank"></a>
				</div>
				<div class="action_layout head2">
					<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046" target="_blank"></a>
				</div>
				<div class="action_layout head3">
					<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034" target="_blank"></a>
				</div>
				<div class="action_layout head4">
					<a href="${ctx }/web/user/login/index.jsp" target="_blank"></a>
				</div>
			</div>
			<div class="display_nav">
				<div class="active">&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
			</div>
		</div>
		<div class="h_layout">
			<c:choose>
				<c:when test="${!empty geUserPersonal}">
					<jsp:include page="web/common/indexUserInfoFrame.jsp"></jsp:include>
				</c:when>
				<c:when test="${empty geUserPersonal}">
					<jsp:include page="web/common/indexLoginFrame.jsp"></jsp:include>
				</c:when>
			</c:choose>
		</div>
	</div>
	<div class="h_layout">
		<div class="index_area">
			<div class="area_title">һ�����</div>
			<div class="area_main">
				<div class="age_tab_main">
					<div class="active_bar"></div>
					<ul>
						<li><div>0-20��</div>&gt;</li>
						<li class="active select"><div>20-30��</div>&gt;</li>
						<li class="active"><div>30-40��</div>&gt;</li>
						<li><div>40-50��</div>&gt;</li>
						<li><div>50-60��</div>&gt;</li>
						<li><div>60-70��</div>&gt;</li>
						<li><div>70������</div></li>
					</ul>
				</div>
				<div class="age_tab_container">
					<div class="content"></div>
					<div class="content show age20">
						<div class="talk"></div>
						<div class="age_product_list">
							<div class="age_product first">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img src="${ctx }/resources/image/index/index_age20_product1.jpg"></a>
								<p class="name">
									<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034" target="_blank">������Ʊ�</a>
								</p>
								<p class="instro">
									<span>��Ʒ��飺</span>�Ƚ��Զ���Ͷ����ѡ��
								</p>
								<p class="instro">ȫ������5000���ͻ�����ѡ��</p>
								<p class="instro">���ꡢ9���¡�1�������������ѡ��</p>
								<p class="instro">1����Ԥ���껯������5.3%��</p>
							</div>
							<div class="age_product last">
								<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><img src="${ctx }/resources/image/index/index_age20_product2.jpg"></a>
								<p class="name">
									<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">�������Ǽƻ�</a>
								</p>
								<p class="instro">
									<span>��Ʒ��飺</span>������С����¶ȼ١��ؼ�̽�ױ�ѡ���ɻ����𳵣����������������������ͳ����ִ����Լݳ������գ�����ѡ�񣬸��ͼۣ�
								</p>
							</div>
						</div>
					</div>
					<div class="content age30">
						<div class="talk"></div>
						<div class="age_product_list">
							<div class="age_product first">
								<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank"><img src="${ctx }/resources/image/index/index_age30_product1.jpg"></a>
								<p class="name">
									<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank">�������</a>
								</p>
								<p class="instro">
									<span>��Ʒ��飺</span>һ���ߴ�200�����ʺ��г�һ��������ա��������޳���30�꣬���ڷ������ѵ�120%��ÿ��ֻ��2Ԫ�����ͱ��ѡ�
								</p>
							</div>
							<div class="age_product last">
								<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img
									src="${ctx }/resources/image/index/index_age30_product2.jpg"></a>
								<p class="name">
									<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">�����������</a>
								</p>
								<p class="instro">
									<span>��Ʒ��飺</span>���ꡢ9���¡�1�������������ѡ��
								</p>
								<p class="instro">1����Ԥ���껯������5.3%</p>
								<p class="instro">9����Ԥ���껯������5.12%</p>
								<p class="instro">������Ԥ���껯������5.02%</p>
							</div>
						</div>
					</div>
					<div class="content">4������</div>
					<div class="content">5������</div>
					<div class="content">6������</div>
					<div class="content">7������</div>
				</div>
			</div>
		</div>
		<div class="index_area">
			<div class="area_title">�ʺ����Ĳ�Ʒ</div>
			<div class="area_main suit_product">
				<div class="product_list">
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank"><img src="${ctx }/resources/image/index/index_suit1.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank">
								<p>ֻ���˳��գ���out�ˣ�</p>
								<p>200��˽�ҳ����������ռƻ��Ͽ�������ƽ�����ǶԼ�����õĹذ���</p>
							</a>
						</div>
					</div>
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img src="${ctx }/resources/image/index/index_suit2.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">
								<p>ʲô�����ǣ�ֻ�й��ʲ��ǣ���ô�죿��̩������Ƽƻ���������Ӯͨ�ͣ����ڡ����̡����ϼ���ɡ�</p>
							</a>
						</div>
					</div>
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><img src="${ctx }/resources/image/index/index_suit3.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">
								<p>��ȫ����Ŀ�ĵأ��ܾ�����㱼��</p>
								<p>�ɻ���������������</p>
								<p>����ɶ��ͨ���߾���ɶ���գ�������ѡ�񣬼۸��������</p>
							</a>
						</div>
					</div>
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img src="${ctx }/resources/image/index/index_suit4.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">
								<p>�����ҵ�С����һ���Ǯ�ˣ�</p>
								<p>�����У�����̫�ͣ�</p>
								<p>����𣿷���̫�ߣ�</p>
								<p>��̩��������Ʊ�����������������</p>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/web/quicktunnel/quicktunnel.jsp"></jsp:include>
	</div>
</div>
<!--ҳ�����岿�� ����-->