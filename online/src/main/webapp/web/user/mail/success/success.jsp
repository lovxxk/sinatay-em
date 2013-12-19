<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>

<script type="text/javascript" src="${ctx}/global/js/jquery/jquery-1.5.2.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
<script type="text/javascript">
var intervalProcess = setInterval('counttime()',1000);
var timenum = 120;
function counttime() {
	if (timenum > 0) {
		$('.time').text(timenum);
		timenum = timenum - 1;
		document.getElementById("resetSend").disabled = true;
	} else {
		clearInterval(intervalProcess);
		document.getElementById("resetSend").disabled = false;
	}
}
function resetSendEmail() {
	var userAccount = $("#userAccount").val();
	$.ajax({
		url : '${ctx}/register/resetSendEmail.do',
		type : 'POST',
		async : false,
		data : {userAccount : userAccount },
		dataType : "text",
		success : function(data){
			if (data == "success") {
				intervalProcess = setInterval('counttime()',1000);
				Sinosoft.alert({
					contentStr: "�����ʼ������·��ͣ���ע�����",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
					}
				});
// 				alert("�����ʼ������·��ͣ���ע�����");
			} else {
				Sinosoft.alert({
					contentStr: "��������æ�����Ժ�����",
					width:480,
					okStr: 'ȷ��',
					cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
					okFunc:function(){
					}
				});
// 				alert("��������æ�����Ժ�����");
			}
		}
	});
}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li class="at">�ʼ�����</li>
			</ul>
		</div>
		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip_left"></div>
				<div class="info">
					<input type="hidden" value="${customer.userAccount }" id="userAccount"/>
					<p class="info_top">
						��֤�ʼ��ѷ��͵���������<span>${customer.userAccount }</span>����ע����գ�
					</p>
					<p>1.����������¼���ĵ������������֤�ʼ���</p>
					<p>2.����24Сʱ�ڸ�����֤�����е���ʾ������ע����˺ţ�������24Сʱ��δ�����˺ţ������ٴν���ע�ᡣ</p>
					<p>3.��δ�յ���֤�ʼ���������<span class="time"></span>�����<button class="return_index_mail click_btn" id="resetSend" disabled="disabled" onclick="resetSendEmail();">���·�����֤�ʼ�</button>�ٴη�����֤�ʼ���</p>
					<p>4.����һֱ���ղ��������ʼ����������Ժ����Ի�������������������ע�ᡣ</p>
					<div class="return_index">
						<c:set var="email_login" value="${fn:substringAfter(customer.email,'@')}"></c:set>
						<button class="click_btn" onclick="javascript:window.open('http://mail.${fn:replace(email_login,'gmail','google')}')">ȥ������֤</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>