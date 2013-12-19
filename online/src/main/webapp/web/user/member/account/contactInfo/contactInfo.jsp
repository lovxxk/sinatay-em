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
			$('.validdateMobilePhoneNumber').text("手机号码填写不正确");
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
			$(".validatePhoneNum").text("固定电话格式有误.比如(0123-33445566)");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}

		if (areacode != "" && phonenum == "") {
			$(".validatePhoneNum").text("请输入固定电话");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode == "" && phonenum != "") {
			$(".validatePhoneNum").text("请输入固定电话对应的区号");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode.length < 3 || phonenum.length < 8) {
			$(".validatePhoneNum").text("固定电话格式有误.比如(0123-33445566)");
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
			$(".validatePhoneNum").text("固定电话格式有误.比如(0123-33445566)");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}

		if (areacode != "" && phonenum == "") {
			$(".validatePhoneNum").text("请输入固定电话");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode == "" && phonenum != "") {
			$(".validatePhoneNum").text("请输入固定电话对应的区号");
			$(".validatePhoneNumFlag").attr("value", "false");
			return;
		}
		if (areacode.length < 3 || phonenum.length < 8) {
			$(".validatePhoneNum").text("固定电话格式有误.比如(0123-33445566)");
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
			$('.validEmail').text("请填写一个正确的邮箱地址");
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
				<li><a href="#">首页</a><span> &gt;</span></li>
				<li class="at">会员首页</li>
			</ul>
		</div>
		<div class="member_main"><!-- item account -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>新增联系信息</p>
				</div>
				<div class="member_area contact_info">
					<form action="#">
						<div class="input_area">
							<div class="input_row cellphone">
								<label class="input_label" for="cellphone"><span class="name">手机号码：</span></label>
								<input class="input_text mobilePhoneNumber" name="cellphone" type="text"/>
							</div>
							<div class="input_row email">
								<label class="input_label" for="email"><span class="name">电子邮箱：</span></label>
								<input class="input_text email" name="email" type="text"/>
							</div>
							<div class="input_row telephone">
								<label class="input_label" for="telephone"><span class="name">固定电话：</span></label>
								<input class="input_text areacode" name="areacode" type="text"/>
								<input class="input_text phonenum" name="phonenum" type="text"/>
							</div>
						</div>
						<div class="action">
							<button class="save click_btn">保&nbsp;&nbsp;&nbsp;&nbsp;存</button>
						</div>
					</form>
				</div>
				<div class="saved_info">
					<p class="info_title">已保存的联系信息</p>
					<div class="info_area">
						<div class="info_row info_name">
							<div class="info_col phone">手机号</div>
							<div class="info_col email">电子邮箱</div>
							<div class="info_col telephone">固定电话</div>
							<div class="info_col bind_account">绑定账号</div>
							<div class="info_col bind">&nbsp;</div>
							<div class="info_col operation">操作</div>
						</div>
						<div class="info_row info_value">
							<div class="info_col phone">15845678945</div>
							<div class="info_col email">aliwangwang_zizhao@ hotmail.com</div>
							<div class="info_col telephone">0571-88888889</div>
							<div class="info_col bind_account">aliwangwang_zizhao88</div>
							<div class="info_col bind"><div class="bind_btn click_btn">账号绑定</div></div>
							<div class="info_col operation"><a href="#">修改</a>&nbsp;|&nbsp;<a href="#">删除</a></div>
						</div>
						<div class="info_row info_value">
							<div class="info_col phone">15845678945</div>
							<div class="info_col email">zizhao@taobao.com</div>
							<div class="info_col telephone">0571-88888889</div>
							<div class="info_col bind_account">aliwangwang88</div>
							<div class="info_col bind"><div class="bind_btn click_btn">账号绑定</div></div>
							<div class="info_col operation"><a href="#">修改</a>&nbsp;|&nbsp;<a href="#">删除</a></div>
						</div>
					</div>
				</div>
				<div class="title">
					<div class="block"></div>
					<p>新增联系地址信息</p>
				</div>
				<div class="member_area contact_info">
					<form action="#">
						<div class="input_area">
							<div class="input_row address">
								<label class="input_label" for="cellphone"><span class="name">通讯地址：</span></label>
								<input class="input_text province" name="province" type="text"/>
								<label class="tip_label" for="validity">省</label>
								<input class="input_text city" name="city" type="text"/>
							</div>
							<div class="input_row complete_address">
								<label class="input_label" for="complete_address"><span class="name">&nbsp;</span></label>
								<input class="input_text" name="complete_address" type="text"/>
							</div>
							<div class="input_row postcode">
								<label class="input_label" for="postcode"><span class="name">邮政编码：</span></label>
								<input class="input_text" name="postcode" type="text"/>
							</div>
						</div>
						<div class="action">
							<button class="save click_btn">保&nbsp;&nbsp;&nbsp;&nbsp;存</button>
						</div>
					</form>
				</div>
				<div class="saved_info">
					<p class="info_title">已保存的地址信息</p>
					<div class="info_area">
						<div class="info_row info_name">
							<div class="info_col area">所在地区</div>
							<div class="info_col street_info">街道信息</div>
							<div class="info_col postcode">邮编</div>
							<div class="info_col operation">操作</div>
						</div>
						<div class="info_row info_value">
							<div class="info_col area">北京市朝阳区</div>
							<div class="info_col street_info">朝外大街20号联合大厦朝外大街20号联合大厦 朝外大街20号联合大厦</div>
							<div class="info_col postcode">310000</div>
							<div class="info_col operation"><a href="#">修改</a>&nbsp;|&nbsp;<a href="#">删除</a></div>
						</div>
						<div class="info_row info_value">
							<div class="info_col area">浙江省杭州市江干区</div>
							<div class="info_col street_info">朝外大街20号联合大厦</div>
							<div class="info_col postcode">310000</div>
							<div class="info_col operation"><a href="#">修改</a>&nbsp;|&nbsp;<a href="#">删除</a></div>
						</div>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>