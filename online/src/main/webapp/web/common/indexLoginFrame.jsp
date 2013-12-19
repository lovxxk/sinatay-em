<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="login_frame">
	<form action="" id="login_form" method="post">
		<label class="input_label">会员登录<span id="message" style=""></span></label>
		<input class="input_field" type="text" name="j_username" id="user_name"/>
		<div class="id_account">
			<div class="top_arr"></div>
			<div class="account_list">
				<div class="account_choose">请选择常用登录帐号：</div>
				<div id="_account"></div>
				<!--  <div class="account_item"><input type="radio" name="account1"><label>dfds@1sina.com</label></div>
				<div class="account_item"><input type="radio" name="account1"><label>135****1111</label></div>  -->
			</div>
		</div>
		<input class="input_field" type="password" name="j_password" id="password"/>
		<div class="action">
			<input name="remeberMe" id="remeberMe" type="checkbox"/>
			<label for="remember">记住我</label>
			<a class="forget_password" href="${ctx }/web/user/resetPwd/index.jsp">忘记密码？</a>
			<a class="reg" href="${ctx }/web/user/register/index.jsp">立即注册</a>
		</div>
		<div class="login_submit">
			<button type="button" class="click_btn" onclick="return login();">立即登录</button>
		</div>
		<div class="actions"> 
			<img src="${ctx }/resources/image/user/alipay_icon.png"/><a href="${ctx}/web/user/login/alipayLogin/alipayapi.jsp">支付宝登录</a>
			<img src="${ctx }/resources/image/user/weibo_icon.png"/><a href="${ctx}/login/sinaLogin.do">微博登录</a>
 			<img src="${ctx }/resources/image/user/tencent_icon.png"/><a href="${ctx}/oauth/qqoauth">QQ登录</a>
 		</div> 
	</form>
</div>