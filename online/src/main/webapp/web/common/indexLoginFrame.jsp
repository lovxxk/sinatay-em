<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="login_frame">
	<form action="" id="login_form" method="post">
		<label class="input_label">��Ա��¼<span id="message" style=""></span></label>
		<input class="input_field" type="text" name="j_username" id="user_name"/>
		<div class="id_account">
			<div class="top_arr"></div>
			<div class="account_list">
				<div class="account_choose">��ѡ���õ�¼�ʺţ�</div>
				<div id="_account"></div>
				<!--  <div class="account_item"><input type="radio" name="account1"><label>dfds@1sina.com</label></div>
				<div class="account_item"><input type="radio" name="account1"><label>135****1111</label></div>  -->
			</div>
		</div>
		<input class="input_field" type="password" name="j_password" id="password"/>
		<div class="action">
			<input name="remeberMe" id="remeberMe" type="checkbox"/>
			<label for="remember">��ס��</label>
			<a class="forget_password" href="${ctx }/web/user/resetPwd/index.jsp">�������룿</a>
			<a class="reg" href="${ctx }/web/user/register/index.jsp">����ע��</a>
		</div>
		<div class="login_submit">
			<button type="button" class="click_btn" onclick="return login();">������¼</button>
		</div>
		<div class="actions"> 
			<img src="${ctx }/resources/image/user/alipay_icon.png"/><a href="${ctx}/web/user/login/alipayLogin/alipayapi.jsp">֧������¼</a>
			<img src="${ctx }/resources/image/user/weibo_icon.png"/><a href="${ctx}/login/sinaLogin.do">΢����¼</a>
 			<img src="${ctx }/resources/image/user/tencent_icon.png"/><a href="${ctx}/oauth/qqoauth">QQ��¼</a>
 		</div> 
	</form>
</div>