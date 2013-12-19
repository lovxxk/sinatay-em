<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>��̩����������Ʊ�</title>
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
				total.text("��" + (number.val() * 1000.00).toFixed(2));
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
// 						alert("��ѡ���������");
// 						return false;
// 					}
// 					var payEndYear=$('input:radio[name="payEndYear"]:checked').val();
// 					if (payEndYear == null || payEndYear == ""){
// 						alert("��ѡ��ɷ��ڼ䣡");
// 						return false;
// 					}
					var hospitalHis1=$('input:radio[name="hospitalHis1"]:checked').val();
					//var hospitalHis2=$('input:radio[name="hospitalHis2"]:checked').val();
					//if (hospitalHis1 == null || hospitalHis1 == "" || hospitalHis2 == null || hospitalHis2 == ""){
					if (hospitalHis1 == null || hospitalHis1 == ""){
						alert("��ѡ�񽡿���֪��");
						return false;
					}
					//if(hospitalHis1 == "1" || hospitalHis2 == "1"){
					if(hospitalHis1 == "1"){
						alert("������������֪���������Ͷ��Ҫ��");
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
				//	alert("��ȷ�����Ѿ������Ķ����û���ŵ������û���ŵ���򹴣�");
				//}
			});
			
			$("#province").change();
		});
	</script>
	<body>
		<div data-role="page" data-theme="c">
		<div data-role="header" data-theme="c">
		    <h1>��д�ͻ���Ϣ</h1>
		</div><!-- /header -->
		<div style="text-align:center;width:100%;">
		<p style="color:gray;"><font style="color:#c1272d">��.��д��Ϣ</font>...��.ȷ����Ϣ...��.֧������</p>
		</div>
		<hr/>
		<div id ="pageTable" data-role="content">
			<form id="fm" name="fm" method="post">	
				<div data-role="fieldcontain">
					<table width="100%"><tr><td colspan='2'>
						<input data-clear-btn="true" placeholder="��������" name="userName" id="userName" value="" type="text" verify="����|NOTNULL" />
					</td></tr>
					<tr><td colspan='2'>
						<input data-clear-btn="true" placeholder="�������֤����" name="credentialsNumber" id="credentialsNumber" value="" type="text" verify="���֤����|NOTNULL&IDCARD" />
					</td><td></td></tr>
					<tr><td>
						<input data-clear-btn="true" placeholder="�����ֻ�����" name="mobilePhone" id="mobilePhone" value="" type="tel" verify="�ֻ�����|NOTNULL&MOBILE" />
					</td><td>
						<a href="#popupInfo" data-rel="popup" data-role="button" data-inline="true" data-transition="pop" data-icon="info" data-theme="a" data-iconpos="notext">...</a>
						<div data-role="popup" id="popupInfo" class="ui-content" style="max-width:280px" data-dismissible="false" data-position-to="window">
							<a href="#" data-rel="back" data-role="button" data-theme="a" data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>
							<p>�����������ֻ����룬�Ա�����Ϊ���ṩ��������</p>
						</div>
					</td></tr>
					<tr><td>
						<input data-clear-btn="true" placeholder="���ĳ���Email" name="email" id="email" value="" type="email" verify="Email|NOTNULL&EMAIL" />
					</td><td>
						<a href="#popupInfo2" data-rel="popup" data-role="button" data-inline="true" data-transition="pop" data-icon="alert" data-theme="a" data-iconpos="notext">...</a>
						<div data-role="popup" id="popupInfo2" class="ui-content" style="max-width:280px" data-dismissible="false" data-position-to="window">
							<a href="#" data-rel="back" data-role="button" data-theme="a" data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>
							<p>����������Email��ַ�����ǽ�������ĵ��ӱ������������ַ���ǳ���Ҫ��</p>
						</div>
					</td></tr>
					</table>
				</div>
				<div data-role="fieldcontain">
					<fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
						<label for="province">SelectA</label>
<!-- 						<select id="province" name="province" onchange="onchangeShow(this.value,'counties')"> -->
<!-- 						<option value="1">������</option> -->
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
					<input data-clear-btn="true" placeholder="������ϵ��ַ" name="address" id="address" value="" type="text" verify="��ϵ��ַ|NOTNULL" />
				</div>
				
				<div data-role="fieldcontain">
				<p>��Ʒ���ƣ���̩������Ʊ�</p>
				<p>���ۣ���1000.00</p>
				<fieldset data-role="controlgroup" data-type="horizontal" >        	
		        <input placeholder="�������" type="number" name="number" id="number" value="" stype="text-align:right" verify="�������|NOTNULL&INT&VALUE>0" />
				</fieldset>
				<p>�ܼۣ�<span id="total"></span></p>
				</div>
				
				
				<div data-role="fieldcontain">
				<label>���������Ƿ������м�����������������Ѫ�ܼ������Ĺ��ܲ�ȫII�����ϡ���ѪѹII�����ϡ��ļ���������Ӳ�����������༲���������ܲ�ȫ����֢�������ϰ���ƶѪ����ϵͳ�Ժ���Ǵ�����Ѫ�������Ծƾ��ж������񼲲��������ϰ��������ĺ�Ĭ��֢������ɭ�ϲ�����֢��������ʧ����̱�������ơ����̲������̲���Я���ߣ����������Ƿ���������������
		        </label>
				<fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
		        <input name="hospitalHis1" id="radio-choice-t-6a" value="1"  type="radio" />
		        <label for="radio-choice-t-6a">��</label>
		        <input name="hospitalHis1" id="radio-choice-t-6b" value="0" type="radio" />
		        <label for="radio-choice-t-6b">��</label>        		
				</fieldset>
				</div>						
				
				<table style="width:100%;"><tr><td>
				<a data-role="button" class="bt_yellow" href="javascript:void(0)" onclick="history.go(-1)"  >
					   ����
				</a></td><td>
				<a data-role="button" class="bt_red" id="nextBtn" data-transition="slide">
						��һ��
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
						<span id="progressMsg">���ڴ������Ժ�...</span>
					</td>
				</tr>
			</table>
		</div>
		<div data-role="footer"  data-theme="c">
		<h4>��̩���ٱ��չɷ����޹�˾</h4>
		</div>
		
		</div><!-- /page -->
	</body>
</html>