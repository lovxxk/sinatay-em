<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/system/userManage/passport/login/login1Validate.jsp"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/login.css" />
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<!--[if IE 6]>
	<script type="text/javascript" src="${ctx}/global/js/DD_belatedPNG_0.0.8a-min.js"></script>
	<script type="text/javascript">
		DD_belatedPNG.fix('.sign_newsbg');
	</script>
<![endif]-->
<script language="javascript">
$(function(){
	$("#userCode").focus();
})
/**ajax validate rand**/
var dataFlg;
function checkCode() {
	$.ajax({
		type : "POST",
		async : false,
		url : '${ctx}/misLogin/checkAjax.do',
		dataType : 'json',
		data : {checkCode : $("#rand").val()},
		success : function(data){
			if(data.flg == "success") {dataFlg = true;} else {dataFlg = false;}
		}
	});
}

function login(){
	if($("#userCode").val() == ""){
		$("#message").html("请输入登录账号!");
		changeImage();
		$("#userCode").focus();
		return false;
	}else if($("#password").val() == ""){
		$("#message").html("请输入登录密码!");
		changeImage();
		$("#password").focus();
		return false;
	}else if($("#rand").val() == "" && 1==2){
		$("#message").html("请输入验证码!");
		changeImage();
		$("#rand").focus();
		return false;
	}else{
		//checkCode();
		dataFlg = true;
		if(dataFlg){
			$("#frmInput").submit();
		}else{
			$("#message").html("验证码输入有误!");
			changeImage();
		}
	}
}

function onReturn(evt){
	var currKey=evt.keyCode||evt.which||evt.charCode; 
	if (currKey==13)
		login();
}

function changeImage(){
	$("#mFrame").attr("src","image.jsp?id="+new Date());
}

function showErrorMessage(){
	$("#message").html("${loginMessage}");
}
</script>
</head>

<body onkeypress="return onReturn(event);" onload="showErrorMessage();">
<form name="frmInput" id="frmInput" method="post" action="${ctx}/j_spring_security_check">
<div class="sign_warpper">
	<div class="sign_newsbg"></div>
    <div class="sign_wel">
    	<div class="sign_border">
        	<div class="sign_title">
            	<span class="welcome"></span>
                <span class="picss"></span>
            </div>
            <div class="sign_wel_title"><h4>欢迎使用后台管理系统</h4></div>
            <div class="sign_text_border">
            	<div class="backstage_login_en2">
                	<div class="backstage_login_prompt"><h3 id="message"></h3></div>
                    <div class="backstage_login_write1">
                    	<span class="backstage_login_texname">用户名：</span>
                        <span class="backstage_login_textext">
							<input type="text" name="j_username" id="userCode" value="${username}" maxlength=30 class="backstage_login_input1" />
						</span>
                    </div>
                    <div class="backstage_login_write2">
                    	<span class="backstage_login_texname">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
                        <span class="backstage_login_textext">
						  <input type="password" id="password"name="j_password" class="backstage_login_input1" maxlength="20"/>
						</span>
                    </div>
                    <div class="backstage_login_write2" style="display:none;">
                    	<span class="backstage_login_texname">验证码：</span>
                        <span class="backstage_login_textext1"><input type="text" name="rand" id="rand" maxlength="4" class="backstage_login_input2" /></span>
                        <span class="backstage_login_texyan"><img id="mFrame" name="mFrame" src="image.jsp?id=0" onclick="changeImage();" class="backstage_rand_image" /></span>
                         <span class="backstage_login_texchange">（点击图片换一张）</span>
                    </div>
                </div>
            </div>
            <div class="backstage_login_en3"><input type="button" class="backstage_login_input3" onclick="login();" /></div>
        </div>
    </div>
</div>
</form>
</body>
</html>
