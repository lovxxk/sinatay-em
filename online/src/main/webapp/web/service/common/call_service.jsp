<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="dynamic_frame call_service">
	<p class="dynamic_title">��ѯ�ͷ�</p>
	<div class="item call">
		<div class="icon"></div>
		<div class="content">
			<p><font color="black">ȫ���ͷ��绰</font></p>
			<div class="call_num"></div>
		</div>
	</div>
	<div class="item online">
		<div class="icon"></div>
		<div class="content">
			<p><font color="black">�ͷ���ѯ</font></p>
			<div class="online_serve click_btn" id="live767">���߿ͷ�</div>
		</div>
	</div>
</div>

<script language="javascript"
	src="http://care3.live800.com/live800/chatClient/staticButton.js?companyID=8037&configID=667&codeType=steady"></script>
<script id='write' language="javascript">
	function writehtml() {
		var temptext = StaticIcon_generate();
		document.getElementById('live767').innerHTML = temptext;
		setTimeout('write.src', 9000);
	}
	writehtml();
</script>
<script language="javascript"
			src="http://care3.live800.com/live800/chatClient/monitor.js?companyID=8037&configID=322&codeType=custom"></script>
