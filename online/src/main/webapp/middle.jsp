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
		if(userName == "" || userName == "请输入您的邮箱/手机/身份证"){
			$("#message").html("请输入登录账号!");
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
						contentStr: "证件号错误",
						width:480,
						okStr: '确定',
						cancelBtnShow:false,//是否显示关闭按钮
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
		if (userName == "请输入您的邮箱/手机/身份证" || userName == "") {
			$("#message").html("请输入登录账号!");
			return false;
		}

		var password = $("#password").val();
		if (password == "请输入密码" || password == "") {
			$("#message").html("请输入登录密码!");
			return false;
		}
		
		//error：用户名密码错误
		//success：成功
		//failed：未激活
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
				contentStr: "参数错误",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function() {
					
				}
			});
			return false;
		} else if (loginFlag == "null") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "用户名不存在",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
			}else if (loginFlag == "idNumNull") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "证件号或密码错误",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "accountLock") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "密码输入错误已超过3次，该账户已被锁定，您可在2小时后再登陆",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		}else if (loginFlag == "error") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "密码错误，请重新输入",
				subContentStr:"如果连续三次密码输入错误，帐号将被绑定",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					$("#password").attr("value", "");
				}
			});
			return false;
		}else if (loginFlag == "pwdError") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "密码错误，请重新输入",
				subContentStr:"如果连续三次密码输入错误，帐号将被绑定",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "failed") {
			$("#message").html("帐号未激活，<a href='${ctx }/register/sendEmailToActive.do?userAccount="
					+ userName + "'>立即激活</a>");
			return false;
		} else if (loginFlag == "lock") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "密码输入错误已超过3次，该账户已被锁定，您可在2小时后再登陆",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "bindFalse") {
			$("#message").html("");
			Sinosoft.alert({
				contentStr: "您尚未购买过保单或尚未进行过保单绑定，不可通过身份证号登录",
				width:480,
				okStr: '确定',
				cancelBtnShow:false,//是否显示关闭按钮
				okFunc:function(){
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
			return false;
		} else if (loginFlag == "success") {
			window.location.href= "${ctx }/login/userPersonalLogin.do";
			return false;
		} else {//同一个身份证号对应多个密码
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
				contentStr : "请选择您要登录的帐号",
				width : 480,
				okStr : '确定',
				cancelBtnShow : false,//是否显示关闭按钮
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
				contentStr : "用户名不能为空",
				width : 480,
				okStr : '确定',
				cancelBtnShow : false,//是否显示关闭按钮
				okFunc : function() {
				}
			});
		} else if (idLoginFlag == "null") {
			Sinosoft.alert({
				contentStr : "用户名不存在，请确认",
				width : 480,
				okStr : '确定',
				cancelBtnShow : false,//是否显示关闭按钮
				okFunc : function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "lock") {
			Sinosoft.alert({
				contentStr : "密码输入错误已超过3次，该账户已被锁定，您可在2小时后再登陆",
				width : 480,
				okStr : '确定',
				cancelBtnShow : false,//是否显示关闭按钮
				okFunc : function() {
					$("#user_name").attr("value", "");
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "pwdNull") {
			Sinosoft.alert({
				contentStr : "密码不能为空",
				width : 480,
				okStr : '确定',
				cancelBtnShow : false,//是否显示关闭按钮
				okFunc : function() {
					
				}
			});
		} else if (idLoginFlag == "pwdError") {
			Sinosoft.alert({
				contentStr : "密码错误，请确认输入密码是否正确",
				width : 480,
				okStr : '确定',
				cancelBtnShow : false,//是否显示关闭按钮
				okFunc : function() {
					$("#password").attr("value", "");
				}
			});
		} else if (idLoginFlag == "bindFalse") {
			Sinosoft.alert({
				contentStr : "您尚未购买过保单，不可使用身份证号登录",
				width : 480,
				okStr : '确定',
				cancelBtnShow : false,//是否显示关闭按钮
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
		var subSuccess = '<div class="alert_subscribe"><div class="alert_subscribecss">请选择您要登录的账号:</div><div class="main_content"><div class="sub_txt">';
		for ( var i = 0; i < accounts.length; i++) {
			subSuccess += '<input type="radio" name="loginAccount" value="'+accounts[i]+'">'
					+ accounts[i] + '</input><br>';
		}
		subSuccess += '</div></div></div>';

		subSuccess = $(subSuccess);
		return subSuccess;
	}
</script>
<!--页面主体部分 开始-->
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
			<div class="area_title">一生相伴</div>
			<div class="area_main">
				<div class="age_tab_main">
					<div class="active_bar"></div>
					<ul>
						<li><div>0-20岁</div>&gt;</li>
						<li class="active select"><div>20-30岁</div>&gt;</li>
						<li class="active"><div>30-40岁</div>&gt;</li>
						<li><div>40-50岁</div>&gt;</li>
						<li><div>50-60岁</div>&gt;</li>
						<li><div>60-70岁</div>&gt;</li>
						<li><div>70岁以上</div></li>
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
									<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034" target="_blank">懒人理财宝</a>
								</p>
								<p class="instro">
									<span>产品简介：</span>稳健性短期投资首选；
								</p>
								<p class="instro">全网已有5000个客户做了选择！</p>
								<p class="instro">半年、9个月、1年持有期三档任选；</p>
								<p class="instro">1年期预期年化收益率5.3%；</p>
							</div>
							<div class="age_product last">
								<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><img src="${ctx }/resources/image/index/index_age20_product2.jpg"></a>
								<p class="name">
									<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">出行无忧计划</a>
								</p>
								<p class="instro">
									<span>产品简介：</span>商务出行、蜜月度假、回家探亲必选；飞机、火车（动车、高铁、地铁）、客车、轮船、自驾车意外险；按需选择，更低价；
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
									<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank">百万身驾</a>
								</p>
								<p class="instro">
									<span>产品简介：</span>一款保额高达200万，最适合有车一族的人身保险。保障期限长达30年，满期返还保费的120%。每天只需2元，超低保费。
								</p>
							</div>
							<div class="age_product last">
								<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img
									src="${ctx }/resources/image/index/index_age30_product2.jpg"></a>
								<p class="name">
									<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">宝利来理财险</a>
								</p>
								<p class="instro">
									<span>产品简介：</span>半年、9个月、1年持有期三档任选；
								</p>
								<p class="instro">1年期预期年化收益率5.3%</p>
								<p class="instro">9个月预期年化收益率5.12%</p>
								<p class="instro">半年期预期年化收益率5.02%</p>
							</div>
						</div>
					</div>
					<div class="content">4无内容</div>
					<div class="content">5无内容</div>
					<div class="content">6无内容</div>
					<div class="content">7无内容</div>
				</div>
			</div>
		</div>
		<div class="index_area">
			<div class="area_title">适合您的产品</div>
			<div class="area_main suit_product">
				<div class="product_list">
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank"><img src="${ctx }/resources/image/index/index_suit1.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152808046" target="_blank">
								<p>只买了车险？你out了？</p>
								<p>200万私家车车主人身保险计划赶快加入你的平安才是对家人最好的关爱！</p>
							</a>
						</div>
					</div>
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img src="${ctx }/resources/image/index/index_suit2.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">
								<p>什么都在涨，只有工资不涨，怎么办？信泰保险理财计划，带你跑赢通胀！现在、即刻、马上加入吧。</p>
							</a>
						</div>
					</div>
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank"><img src="${ctx }/resources/image/index/index_suit3.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152737017" target="_blank">
								<p>安全到达目的地，拒绝裸飞裸奔！</p>
								<p>飞机？高铁？动车？</p>
								<p>乘坐啥交通工具就买啥保险！按需求选择，价格更低廉！</p>
							</a>
						</div>
					</div>
					<div class="product">
						<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank"><img src="${ctx }/resources/image/index/index_suit4.jpg"/></a>
						<div class="product_info">
							<a href="${ctx}/sale/toQuote.do?eid=G120130902152647034" target="_blank">
								<p>喊着我的小闺蜜一起存钱了！</p>
								<p>存银行？收益太低！</p>
								<p>买基金？风险太高！</p>
								<p>信泰“懒人理财宝”，满足您的需求！</p>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/web/quicktunnel/quicktunnel.jsp"></jsp:include>
	</div>
</div>
<!--页面主体部分 结束-->