<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('.content').hover(function() {
			$(this).addClass('select');
			}, 
			function() {
				$(this).removeClass('select');
		});
	})
	function searchRewardResult() {
		new Sinosoft.InteractiveDialog({
			layout : bindSuccess(),
			width:410,//�Զ��������-�������������
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okStr : 'ȷ��',
			cancelStr : 'ȡ��',
			okFunc:function(){
				
			}
		}).open();
	}
	
	function bindSuccess(){
		var subSuccess = $('<div class="alert_subscribe">'
				+'<div class="success"></div><div class="main_content">'
				+'<div class="sub_txt">��ϲ�������һ̨Iphone5S</div></div></div>');
		return subSuccess;
	}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li><a href="#">��Ա��ҳ</a><span> &gt;</span></li>
				<li class="at">�ҵĻ</li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>�����</p>
				</div>
				<div class="email_list">
					<div class="headcontent">
						<div class="head num">�����</div>
						<div class="head name">�����</div>
						<div class="head email">���ϸ</div>
						<div class="head operation">����</div>
					</div>
					<div class="content">
						<div class="content_col num">˫ʮһ�齱���������</div>
						<div class="content_col name">2012/11/11-2013/11/11</div>
						<div class="content_col email">�ѳ齱</div>
						<div class="content_col operation">
							<a href="javascript:searchRewardResult()">�鿴�н����</a>
						</div>
					</div>
					<div class="content select">
						<div class="content_col num">˫ʮһ�齱�</div>
						<div class="content_col name">2012/11/11-2013/11/11</div>
						<div class="content_col email">δ�齱</div>
						<div class="content_col operation">
							<a href="#" class="gohappy"></a>
						</div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
				</div>
				<div class="titleblock"></div>
				<div class="title">
					<div class="block"></div>
					<p>��Ƽ�</p>
				</div>
				<div class="product_activity_img">
					<div class="activity_img"></div>
				</div>

				<div class="title">
					<div class="block"></div>
					<p>������Ʒ</p>
					<div class="more">
						<a href="#">����&gt;&gt;</a>
					</div>
				</div>
				<div class="product_list">
					<div class="product"></div>
					<div class="product"></div>
					<div class="product"></div>
				</div>
			</div>
		</div>
	</div>
</div>