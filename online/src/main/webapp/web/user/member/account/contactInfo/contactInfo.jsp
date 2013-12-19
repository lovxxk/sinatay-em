<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".mobilePhoneNumber").blur(function(){
		var mobilePhoneNumber = $('.mobilePhoneNumber').val();
		var regmobile = /^(1[3|5|8|4][0-9]\d{8})$/.test(mobilePhoneNumber);
		if (mobilePhoneNumber == "") {
			$('.validdateMobilePhoneNumber').text("");
			$(".validdateMobilePhoneNumberFlag").attr("value", "true");
			return;
		}
		if (!regmobile) {
			$('.validdateMobilePhoneNumber').text("�ֻ�������д����ȷ");
			$(".validdateMobilePhoneNumberFlag").attr("value", "false");
			return;
		} 

		$('.validdateMobilePhoneNumber').text("");
		$(".validdateMobilePhoneNumberFlag").attr("value", "true");
	});
	$(".areacode").blur(function() {
		var phonenum = $(".phonenum").val();
		var areacode = $(".areacode").val();
		if (phonenum == "" && areacode == "") {
			$(".validatePhoneNum").text("");
			$(".validatePhoneNumFlag").attr("value", "true");
			return;
		}
		
		if (isNaN(phonenum) || phonenum.indexOf(".") > 0 || areacode.indexOf(".") > 0 || isNaN(areacode)){
			$(".validatePhoneNum").text("�̶��绰��ʽ����.����(0123-33445566)");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}

		if (areacode != "" && phonenum == "") {
			$(".validatePhoneNum").text("������̶��绰");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode == "" && phonenum != "") {
			$(".validatePhoneNum").text("������̶��绰��Ӧ������");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode.length < 3 || phonenum.length < 8) {
			$(".validatePhoneNum").text("�̶��绰��ʽ����.����(0123-33445566)");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		$(".validatePhoneNum").text("");
		$(".validatePhoneNumFlag").attr("value", "true");
		$(".phoneNumber").attr("value", areacode + "-" + phonenum);
		
	});
	$(".phonenum").blur(function(){
		var phonenum = $(".phonenum").val();
		var areacode = $(".areacode").val();
		if (phonenum == "" && areacode == "") {
			$(".validatePhoneNum").text("");
			$(".validatePhoneNumFlag").attr("value", "true");
			return;
		}

		if (isNaN(phonenum) || phonenum.indexOf(".") > 0 || areacode.indexOf(".") > 0 || isNaN(areacode)){
			$(".validatePhoneNum").text("�̶��绰��ʽ����.����(0123-33445566)");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}

		if (areacode != "" && phonenum == "") {
			$(".validatePhoneNum").text("������̶��绰");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode == "" && phonenum != "") {
			$(".validatePhoneNum").text("������̶��绰��Ӧ������");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode.length < 3 || phonenum.length < 8) {
			$(".validatePhoneNum").text("�̶��绰��ʽ����.����(0123-33445566)");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		$(".validatePhoneNum").text("");
		$(".validatePhoneNumFlag").attr("value", "true");
		$(".phoneNumber").attr("value", areacode + "-" + phonenum);
	});
	$(".email").blur(function(){
		var email = $(".input_row #email").val();
		if (email == "") {
			$('.validEmail').text("");
			$(".validEmailFlag").attr("value", "true");
			return;
		}
		var regemail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9|_|\_|\.]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(email);
		if (!regemail) {
			$('.validEmail').text("����дһ����ȷ�������ַ");
			$(".validEmailFlag").attr("value", "false");
			return false;
		} else {
			$('.validEmail').text("");
			$(".validEmailFlag").attr("value", "true");
		}
	});
	
});
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
			</ul>
		</div>
		<div class="member_main"><!-- item account -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>������ϵ��Ϣ</p>
				</div>
				<div class="member_area contact_info">
					<form action="#">
						<div class="input_area">
							<div class="input_row cellphone">
								<label class="input_label" for="cellphone"><span class="name">�ֻ����룺</span></label>
								<input class="input_text mobilePhoneNumber" name="cellphone" type="text"/>
							</div>
							<div class="input_row email">
								<label class="input_label" for="email"><span class="name">�������䣺</span></label>
								<input class="input_text email" name="email" type="text"/>
							</div>
							<div class="input_row telephone">
								<label class="input_label" for="telephone"><span class="name">�̶��绰��</span></label>
								<input class="input_text areacode" name="areacode" type="text"/>
								<input class="input_text phonenum" name="phonenum" type="text"/>
							</div>
						</div>
						<div class="action">
							<button class="save click_btn">��&nbsp;&nbsp;&nbsp;&nbsp;��</button>
						</div>
					</form>
				</div>
				<div class="saved_info">
					<p class="info_title">�ѱ������ϵ��Ϣ</p>
					<div class="info_area">
						<div class="info_row info_name">
							<div class="info_col phone">�ֻ���</div>
							<div class="info_col email">��������</div>
							<div class="info_col telephone">�̶��绰</div>
							<div class="info_col bind_account">���˺�</div>
							<div class="info_col bind">&nbsp;</div>
							<div class="info_col operation">����</div>
						</div>
						<div class="info_row info_value">
							<div class="info_col phone">15845678945</div>
							<div class="info_col email">aliwangwang_zizhao@ hotmail.com</div>
							<div class="info_col telephone">0571-88888889</div>
							<div class="info_col bind_account">aliwangwang_zizhao88</div>
							<div class="info_col bind"><div class="bind_btn click_btn">�˺Ű�</div></div>
							<div class="info_col operation"><a href="#">�޸�</a>&nbsp;|&nbsp;<a href="#">ɾ��</a></div>
						</div>
						<div class="info_row info_value">
							<div class="info_col phone">15845678945</div>
							<div class="info_col email">zizhao@taobao.com</div>
							<div class="info_col telephone">0571-88888889</div>
							<div class="info_col bind_account">aliwangwang88</div>
							<div class="info_col bind"><div class="bind_btn click_btn">�˺Ű�</div></div>
							<div class="info_col operation"><a href="#">�޸�</a>&nbsp;|&nbsp;<a href="#">ɾ��</a></div>
						</div>
					</div>
				</div>
				<div class="title">
					<div class="block"></div>
					<p>������ϵ��ַ��Ϣ</p>
				</div>
				<div class="member_area contact_info">
					<form action="#">
						<div class="input_area">
							<div class="input_row address">
								<label class="input_label" for="cellphone"><span class="name">ͨѶ��ַ��</span></label>
								<input class="input_text province" name="province" type="text"/>
								<label class="tip_label" for="validity">ʡ</label>
								<input class="input_text city" name="city" type="text"/>
							</div>
							<div class="input_row complete_address">
								<label class="input_label" for="complete_address"><span class="name">&nbsp;</span></label>
								<input class="input_text" name="complete_address" type="text"/>
							</div>
							<div class="input_row postcode">
								<label class="input_label" for="postcode"><span class="name">�������룺</span></label>
								<input class="input_text" name="postcode" type="text"/>
							</div>
						</div>
						<div class="action">
							<button class="save click_btn">��&nbsp;&nbsp;&nbsp;&nbsp;��</button>
						</div>
					</form>
				</div>
				<div class="saved_info">
					<p class="info_title">�ѱ���ĵ�ַ��Ϣ</p>
					<div class="info_area">
						<div class="info_row info_name">
							<div class="info_col area">���ڵ���</div>
							<div class="info_col street_info">�ֵ���Ϣ</div>
							<div class="info_col postcode">�ʱ�</div>
							<div class="info_col operation">����</div>
						</div>
						<div class="info_row info_value">
							<div class="info_col area">�����г�����</div>
							<div class="info_col street_info">������20�����ϴ��ó�����20�����ϴ��� ������20�����ϴ���</div>
							<div class="info_col postcode">310000</div>
							<div class="info_col operation"><a href="#">�޸�</a>&nbsp;|&nbsp;<a href="#">ɾ��</a></div>
						</div>
						<div class="info_row info_value">
							<div class="info_col area">�㽭ʡ�����н�����</div>
							<div class="info_col street_info">������20�����ϴ���</div>
							<div class="info_col postcode">310000</div>
							<div class="info_col operation"><a href="#">�޸�</a>&nbsp;|&nbsp;<a href="#">ɾ��</a></div>
						</div>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>