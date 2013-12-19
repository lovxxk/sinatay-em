<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>信泰保险懒人理财宝</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		
<%-- 		<%@ include file="/global/ui/taglibs_head.jsp"%> --%>
		
		<link rel="stylesheet" href="${ctx}/global/css/mobile/jquery.mobile.structure-1.3.0.css" charset="gbk" />
		<link rel="stylesheet" href="${ctx}/global/css/mobile/themes/my-custom-theme.css" charset="gbk" />
		<link rel="stylesheet" href="${ctx}/global/css/mobile/styles.css" charset="gbk" />
		
		<script src="${ctx}/global/js/mobile/jquery-1.9.1.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.mobile-1.3.0.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/common.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/verify.js" charset="gbk"></script>
		<s:action name="provincesCode" namespace="/mobile" id="bean1" />
	</head>
	<script type="text/javascript">
		var ctx = "${ctx}";
		function onchangeShow(value, elementName) {
			jQuery.ajax({
				url : ctx + "/mobile/" + elementName + "Code.do",
				data : {
					province : value,
					city : value,
					occupationRelaCode : value,
					occupationName : value
				},
				type : "post",
				cache : false,
				async : false,
				dataType : "json",
				success : function(data) {
						var clazz = $("#" + elementName);
						onchangecallback(data[elementName], clazz);
					
				}
			});
		}
		
		function onchangecallback(data, element) {
			var html = "";
			$(element).html(html);
			for ( var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].code + "'>" + data[i].codeName
						+ "</option>";
			}
			$(element).html(html);
			$(element).change();
		}
		
		$(document).ready(function(){
			var progressBar = $("#progressBar");
			var pageTable = $("#pageTable");
			
			var number = $('#number');
			var total = $('#total');
			number.change(function() {
				if (number.val() == "") {
					return false;
				}
				if (!verifyInputField(number)) {
					return false;
				}
				total.text("￥" + (number.val() * 1000.00).toFixed(2));
			}).change();
			
			var credentialsNumber = $('#credentialsNumber');
			credentialsNumber.change(function() {
				if (credentialsNumber.val() == "") {
					return false;
				}
				if (!verifyInputField(credentialsNumber)) {
					return false;
				}
				var infos = getInfoById(credentialsNumber.val());
				document.getElementById("birthDate").value = infos[0];
				document.getElementById("gender").value = infos[1];
			}).change();
			
			$('#nextBtn').click(function() {
				//var type = document.getElementById("checkbox1").checked;
				//if (type) {
					if (!verifyInput()) {
						return false;
					}
// 					var mult=$('input:radio[name="mult"]:checked').val();
// 					if (mult == null || mult == ""){
// 						alert("请选择购买份数！");
// 						return false;
// 					}
// 					var payEndYear=$('input:radio[name="payEndYear"]:checked').val();
// 					if (payEndYear == null || payEndYear == ""){
// 						alert("请选择缴费期间！");
// 						return false;
// 					}
					var hospitalHis1=$('input:radio[name="hospitalHis1"]:checked').val();
					//var hospitalHis2=$('input:radio[name="hospitalHis2"]:checked').val();
					//if (hospitalHis1 == null || hospitalHis1 == "" || hospitalHis2 == null || hospitalHis2 == ""){
					if (hospitalHis1 == null || hospitalHis1 == ""){
						alert("请选择健康告知！");
						return false;
					}
					//if(hospitalHis1 == "1" || hospitalHis2 == "1"){
					if(hospitalHis1 == "1"){
						alert("根据您健康告知情况不符合投保要求！");
						return false;
					}
					progressBar.show();
					pageTable.hide();
					jQuery.ajax({
						url : ctx + "/mobile/loonVerifyCustomInfo.do",
						data : $("#fm").serialize(),
						type : "post",
						cache : false,
						dataType : "json",
						timeout: 300000, 
						success : function(data) {
							if (data.proposalContNo) {
								document.getElementById("proposalContNo").value = data.proposalContNo;
							}
							if (data.proposalSID) {
								document.getElementById("proposalSID").value = data.proposalSID;
							}
							if (data.quoteNo) {
								document.getElementById("quoteNo").value = data.quoteNo;
							}
							if (data.orderId) {
								document.getElementById("orderId").value = data.orderId;
							}
							if (data.isSucc == "true"){
								document.getElementById("fm").action = ctx + "/mobile/loonConfirm.do";
								document.getElementById("fm").submit();
							} else {
								alert(data.message);
							}
							progressBar.hide();
							pageTable.show();
							return false;
						}
					});
				//} else {
				//	alert("请确定您已经认真阅读了用户承诺项，并在用户承诺处打勾！");
				//}
			});
			
			$("#province").change();
		});
	</script>
	<body>
		<div data-role="page" data-theme="c">
		<div data-role="header" data-theme="c">
		    <h1>填写客户信息</h1>
		</div><!-- /header -->
		<div style="text-align:center;width:100%;">
		<p style="color:gray;"><font style="color:#c1272d">①.填写信息</font>...②.确认信息...③.支付订单</p>
		</div>
		<hr/>
		<div id ="pageTable" data-role="content">
			<form id="fm" name="fm" method="post">	
				<div data-role="fieldcontain">
					<table width="100%"><tr><td colspan='2'>
						<input data-clear-btn="true" placeholder="您的姓名" name="userName" id="userName" value="" type="text" verify="姓名|NOTNULL" />
					</td></tr>
					<tr><td colspan='2'>
						<input data-clear-btn="true" placeholder="您的身份证号码" name="credentialsNumber" id="credentialsNumber" value="" type="text" verify="身份证号码|NOTNULL&IDCARD" />
					</td><td></td></tr>
					<tr><td>
						<input data-clear-btn="true" placeholder="您的手机号码" name="mobilePhone" id="mobilePhone" value="" type="tel" verify="手机号码|NOTNULL&MOBILE" />
					</td><td>
						<a href="#popupInfo" data-rel="popup" data-role="button" data-inline="true" data-transition="pop" data-icon="info" data-theme="a" data-iconpos="notext">...</a>
						<div data-role="popup" id="popupInfo" class="ui-content" style="max-width:280px" data-dismissible="false" data-position-to="window">
							<a href="#" data-rel="back" data-role="button" data-theme="a" data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>
							<p>请输入您的手机号码，以便我们为您提供后续服务</p>
						</div>
					</td></tr>
					<tr><td>
						<input data-clear-btn="true" placeholder="您的常用Email" name="email" id="email" value="" type="email" verify="Email|NOTNULL&EMAIL" />
					</td><td>
						<a href="#popupInfo2" data-rel="popup" data-role="button" data-inline="true" data-transition="pop" data-icon="alert" data-theme="a" data-iconpos="notext">...</a>
						<div data-role="popup" id="popupInfo2" class="ui-content" style="max-width:280px" data-dismissible="false" data-position-to="window">
							<a href="#" data-rel="back" data-role="button" data-theme="a" data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>
							<p>请输入您的Email地址，我们将会把您的电子保单发到这个地址（非常重要）</p>
						</div>
					</td></tr>
					</table>
				</div>
				<div data-role="fieldcontain">
					<fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
						<label for="province">SelectA</label>
<!-- 						<select id="province" name="province" onchange="onchangeShow(this.value,'counties')"> -->
<!-- 						<option value="1">北京市</option> -->
<!-- 						</select> -->
						<s:select id="province" list="#bean1.provinces" name="province" 
						listKey="code" listValue="codeName"  onchange="onchangeShow(this.value,'cities')" theme="simple"/>
						<label for="cities">SelectB</label>
						<select id="cities" name="city" onchange="onchangeShow(this.value,'counties')">
						</select>
						<label for="counties">SelectC</label>
						<select id="counties" name="county">
						</select>		
					</fieldset>
					<input data-clear-btn="true" placeholder="您的联系地址" name="address" id="address" value="" type="text" verify="联系地址|NOTNULL" />
				</div>
				
				<div data-role="fieldcontain">
				<p>产品名称：信泰懒人理财宝</p>
				<p>单价：￥1000.00</p>
				<fieldset data-role="controlgroup" data-type="horizontal" >        	
		        <input placeholder="购买份数" type="number" name="number" id="number" value="" stype="text-align:right" verify="购买份数|NOTNULL&INT&VALUE>0" />
				</fieldset>
				<p>总价：<span id="total"></span></p>
				</div>
				
				
				<div data-role="fieldcontain">
				<label>被保险人是否患有下列疾病：恶性肿瘤、脑血管疾病、心功能不全II级以上、高血压II级以上、心肌梗塞、肝硬化、慢性肾脏疾病、肾功能不全、尿毒症、再生障碍性贫血、癫痫、系统性红斑狼疮、白血病、慢性酒精中毒、精神疾病、智力障碍、阿尔茨海默氏症、帕金森氏病、重症肌无力、失明、瘫痪、聋哑、艾滋病毒或艾滋病毒携带者，被保险人是否曾或正在吸毒？
		        </label>
				<fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
		        <input name="hospitalHis1" id="radio-choice-t-6a" value="1"  type="radio" />
		        <label for="radio-choice-t-6a">是</label>
		        <input name="hospitalHis1" id="radio-choice-t-6b" value="0" type="radio" />
		        <label for="radio-choice-t-6b">否</label>        		
				</fieldset>
				</div>						
				
				<table style="width:100%;"><tr><td>
				<a data-role="button" class="bt_yellow" href="javascript:void(0)" onclick="history.go(-1)"  >
					   返回
				</a></td><td>
				<a data-role="button" class="bt_red" id="nextBtn" data-transition="slide">
						下一步
				</a></td></tr></table>
				<input type="hidden" id="proposalContNo" name="proposalContNo" />
				<input type="hidden" id="proposalSID" name="proposalSID" />
				<input type="hidden" id="quoteNo" name="quoteNo" />
				<input type="hidden" id="orderId" name="orderId" />
				<input type="hidden" value="00115600" name="productCode" id="productCode" />
				<input type="hidden" id="gender" name="gender" />
				<input type="hidden" id="birthDate" name="birthDate" />
			</form>
		</div><!-- /content -->
		<div id="progressBar" style="display: none; width: 100%; height: 200px;">
			<table align="center" style="height: 200px;">
				<tr valign="middle" align="center">
					<td>
						<img src="${ctx }/web/mobile/product/loon/images/progress.gif" width="16" height="16" style="vertical-align: middle;" />
					</td>
					<td>
						<span style="width: 10px">&nbsp;</span>
					</td>
					<td>
						<span id="progressMsg">正在处理，请稍候...</span>
					</td>
				</tr>
			</table>
		</div>
		<div data-role="footer"  data-theme="c">
		<h4>信泰人寿保险股份有限公司</h4>
		</div>
		
		</div><!-- /page -->
	</body>
</html>