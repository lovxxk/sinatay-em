<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
var intervalProcess = setInterval('counttime()',1000);
var timenum = 10;
function counttime() {
	if (timenum > 0) {
		$('.time').text(timenum);
		timenum = timenum - 1;
	} else {
		clearInterval(intervalProcess);
		window.location.href='${ctx }/web/user/login/index.jsp';
	}
}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li class="at">��������ɹ�</li>
			</ul>
		</div>

		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip_left"></div>
				<div class="info">
					<p class="info_top">��ϲ���������óɹ���</p>
					<p>�����Ʊ��ܺ��������룬��Ҫ����й©�����ˣ������ϵ���ƭ��  ϵͳ��<span class="time"></span> ����Զ���ת����½ҳ�档</p>
					<div class="return_index">
						<button class="click_btn" onclick="javascript:window.location.href='${ctx }/web/user/login/index.jsp'">������¼</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>