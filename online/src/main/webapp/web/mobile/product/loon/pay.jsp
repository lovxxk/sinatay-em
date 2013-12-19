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
		<link rel="stylesheet" type="text/css" href="${ctx}/global/css/mobile/colorbox.css" charset="gbk" />
		
		<script src="${ctx}/global/js/mobile/jquery-1.9.1.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.mobile-1.3.0.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/common.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/verify.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.colorbox.js" charset="gbk"></script>
		<s:action name="provincesCode" namespace="/mobile" id="bean1" />
		<s:action name="banksCode" namespace="/mobile" id="bean2" />
		
		<script language="javascript">
		
			var ctx = "${ctx}";
			function onchangeShow(value, elementName) {
				jQuery.ajax({
					url : ctx + "/mobile/citiesCode.do",
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
							onchangecallback(data['cities'], clazz);
						
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
		
			function buy_start() {
				$.colorbox({ 
					close:"",
					width: "300px", 
					height:"179px", 
					overlayClose: false,
					html:'<table style="width:100%;margin: 0px; border: 0px; padding: 0px; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; " cellspacing="0"><tbody><tr><td style="width: 100%; height: 22px; text-align: left; padding: 3px 3px 3px 10px; margin: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-size: 13px; line-height: normal; font-family: 宋体; color: rgb(255, 255, 255); border: 1px solid rgb(153, 153, 153); cursor: move; background-color: rgb(204, 0, 12); ">请在打开的页面中完成支付</td><td style="cursor: pointer;background-color: rgb(204, 0, 12); "></td></tr><tr><td id="MsgBox" style="font-style: normal; font-variant: normal; font-weight: normal; font-size: 10pt; line-height: normal; font-family: 宋体; " colspan="2"><div style="width:100%;height:50px"></div><div style="height:60px;width:100%;"><button class="next1" type="submit" style="cursor: hand; left: 30px;position: absolute;display: block;height: 29px;width: 105px;color: white;font-family: 黑体;font-size: 15px;background: #D8231D;border: 1px solid #E3E3E3;z-index: 9999;" id="payagain" onclick="paySucc();">支付完成</button><button class="next1" type="submit" style="cursor: hand; left: 180px;position: absolute;display: block;height: 29px;width: 105px;color: white;font-family:黑体;font-size: 15px;z-index: 9999;background: #D8231D;border: 1px solid #E3E3E3;" id="payagain" onclick="closeWin();">支付失败</button></div></td></tr></tbody></table>'
				}); 
			}
			
			function closeWin(){
				$.colorbox.close();
			}
			
			function paySucc(){
				$.ajax({
					type: 'POST',
					url: ctx + '/mobile/loonCheckUpdatePayfees.do',
					data:{
						"orderId":'${orderId}'
					},
					cache:false,
					dataType:'json',
					success:function(data){
						var isupdateaddress = data.returnFlag;
						if(isupdateaddress == "true"){
							var r=confirm("投保完成,是否关闭该页面？");
							if (r==true)
							  {
								location.href = ctx + "/mobile/loonIndex.do";
							  }
							else
							  {
								$.colorbox.close();
							  }
						}else{
							alert(data.message);
							$.colorbox.close();
						}
					},
					error:function(){
						alert('系统繁忙，请稍后再试');
						ispay = false;
					}
				});
			}
			
			function _onsubmit() {
				var paymentAccount = document.getElementById("paymentAccount").value;
				if (paymentAccount == "") {
					alert("请输入退保银行账户");
					return false;
				}
				if (isNaN(paymentAccount) || paymentAccount.indexOf(".") > 0 || paymentAccount.length < 10 || paymentAccount.length > 20) {
					alert("银行帐号不合法");
					return false;
				}
				
				$.ajax({
					type:'POST',
					url: ctx + '/mobile/loonCheckUpdatePayfees.do',
					data:{
						"orderId":'${orderId}'
					},
					cache:false,
					async:false,
					dataType:'json',
					timeout:300000,
					success:function(data){
						if(data.returnFlag == "true"){
							alert(data.message);
						} else {
							buy_start();
							document.getElementById("fm").submit();
						}
					},
					error:function(){
						alert('系统繁忙，请稍后再试');
					}
				});
			}
			
			$(document).ready(function(){
				$("#bankProvince").change();
			});
		</script>
	</head>
		
	<body>
		<div data-role="page" data-theme="c">
		<div data-role="header" data-theme="c">
		    <h1>订单支付</h1>
		</div><!-- /header -->
		<div style="text-align:center;width:100%;">
		<p style="color:gray;">①.填写信息...②.确认信息...<font style="color:#c1272d">③.支付订单</font></p>
		</div>
		<hr/>
		<div data-role="content">
			<div data-role="fieldcontain">
			保险产品支付信息如下：
			</div>
			<div data-role="fieldcontain">
			<table style="width:100%;">
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">产品名称</th> 
					<td>信泰懒人理财宝</td>
				</tr>		
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">保费</th>
					<td>￥${total}</td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">状态</th>
					<td>待支付</td>
				</tr>
			</table>
			</div>
			
			<div data-role="fieldcontain">
			首期总缴费金额：<font color="red">￥${total}</font>
			</div>
			<div data-role="fieldcontain">
			<p>支付方式：支付宝网页支付</p>
			<p>交易金额：￥${total}</p>
			</div>
			
			<form id="fm" name="fm" action="${ctx}/mobile/toAliPay.do" method="post" target="_blank">
			
				<h3>退保账户</h3>
					<table>
						<tr>
							<td width="80px" height="45px">账户户名：</td>
							<td>
								${userName}
							</td>
						</tr>
						<tr>
							<td>省份城市：</td>
							<td>
								<fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
									<label for="bankProvince">SelectA</label>
									<s:select id="bankProvince" list="#bean1.provinces" name="bankProvince" 
									listKey="code" listValue="codeName"  onchange="onchangeShow(this.value,'bankCity')" theme="simple"/>
									<label for="bankCity">SelectB</label>
									<select id="bankCity" name="bankCity">
									</select>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>开户银行：</td>
							<td>
								<fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
									<label for="bankType">SelectC</label>
									<s:select id="bankType" list="#bean2.banks" name="bankType" 
										listKey="code" listValue="codeName" theme="simple"/>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>账户号码：</td>
							<td>
								<input placeholder="银行帐号" type="text" name="paymentAccount" id="paymentAccount" value="" stype="text-align:right"/>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<span style="color: #FF3333">请您填写投保人银行卡或存折账号（不接受信用卡和中国农业银行存折）</span>
							</td>
						</tr>
						<tr>
							<td height="45px">证件类型：</td>
							<td>
								身份证
							</td>
						</tr>
						<tr>
							<td height="45px">证件号码：</td>
							<td>
								${credentialsNumber}
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<span style="color: #FF3333">请确认该证件号码与您在银行开户时的证件号码一致</span>
							</td>
						</tr>
						
					</table>
					
				<input type="hidden" id="userName" name="userName" value="${userName}" />
				<input type="hidden" id="credentialsNumber" name="credentialsNumber" value="${credentialsNumber}" />
				<input type="hidden" id="total" name="total" value="${total}" />
				<input type="hidden" id="proposalContNo" name="proposalContNo" value="${proposalContNo}" />
		    	<input type="hidden" id="orderId" name="orderId" value="${orderId}" />
			</form>
			
			<table style="width:100%;"><tr><td>
			<a data-role="button" class="bt_yellow" href="javascript:void(0)" onclick="history.go(-1)">
				返回
			</a></td><td>
			<a data-role="button" class="bt_red" data-transition="slide"  onclick="_onsubmit();">
				确认支付
		    </a></td></tr></table>
<%-- 		    <form id="fm" action="${ctx}/mobile/alipayCreateMobile.do" method="post" target="_blank"> --%>
		    	
<%-- 			    <input type="hidden" name = "payInfo.beneficiary_realnames" id = "beneficiary_realnames" value="${sessionScope['CustomerInfoService.Customer'].insuranceApplicant.name }" style="display: none;" readonly="readonly"/> --%>
<%-- 				<input type="hidden" name = "payInfo.beneficiary_cert_no" id = "beneficiary_cert_no" value="${sessionScope['CustomerInfoService.Customer'].insuranceApplicant.credentialsNumber }" style="display: none;" readonly="readonly"/> --%>
<%-- 				<input type="hidden" name = "payInfo.cert_no" id = "cert_no" value="${sessionScope['CustomerInfoService.Customer'].insuranceApplicant.credentialsNumber }" style="display: none;" readonly="readonly"/> --%>
<%-- 				<input type="hidden" name = "payInfo.telephone" id = "telephone" value="${sessionScope['CustomerInfoService.Customer'].insuranceApplicant.mobilePhone }" style="display: none;" readonly="readonly"/> --%>
<%-- 				<input type="hidden"  name = "payInfo.prtNum" value="${sessionScope.forma.prtNum }" style="display: none;" readonly="readonly"/> --%>
<%-- 				<input type="hidden"  name = "payInfo.userCode" value="${sessionScope.forma.userCode }" style="display: none;" readonly="readonly"/> --%>
<%-- 				<input type="hidden" name = "payInfo.biz_params" id = "biz_params" value="${sessionScope.forma.formLSCont.prtNo }" style="display: none;" readonly="readonly"/> --%>
<!-- 			</form> -->
		</div><!-- /content -->
		
		<div data-role="footer"  data-theme="c">
		<h4>信泰人寿保险股份有限公司</h4>
		</div>
		
		</div><!-- /page -->
	</body>
</html>