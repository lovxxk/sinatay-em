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
		
		<script type="text/javascript">
			var ctx = "${ctx}";
			$(document).ready(function(){
				var progressBar = $("#progressBar");
				var pageTable = $("#pageTable");
				$('#nextBtn').click(function() {
					var type = document.getElementById('checkbox1').checked;
					if (type) {
						progressBar.show();
						pageTable.hide();
						jQuery.ajax({
							url : ctx + "/mobile/loonConfirmCustomInfo.do",
							data : $("#fm").serialize(),
							type : "post",
							cache : false,
							dataType : "json",
							timeout: 300000, 
							success : function(data) {
								if (data.isSucc == "true"){
									document.getElementById("fm").action = ctx + "/mobile/loonPay.do";
									document.getElementById("fm").submit();
								} else {
									alert(data.message);
								}
								progressBar.hide();
								pageTable.show();
							}
						});
					} else {
						alert('��ȷ��ҳ���·��������ݣ�');
					}
				});
			});
		</script>
	</head>
	<body>
		<div data-role="page" data-theme="c">
		<div data-role="header" data-theme="c">
		    <h1>��Ϣȷ��</h1>
		</div><!-- /header -->
		<div style="text-align:center;width:100%;">
		<p style="color:gray;">��.��д��Ϣ...<font style="color:#c1272d">��.ȷ����Ϣ</font>...��.֧������</p>
		</div>
		<hr/>
		<div id="pageTable" data-role="content">
			<div data-role="fieldcontain">
			<h4>������Ϣ</h4>
			<table style="width:100%;">
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">��Ʒ����</th> 
					<td>��̩������Ʊ� </td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">����</th> 
					<td>��1000.00 </td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">����</th> 
					<td>${number}��</td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">�ܼ�</th> 
					<td>��${total} </td>
				</tr>
				
			</table>
			</div>
						
			<div data-role="fieldcontain">
			<h4>Ͷ�����߱������˸�����Ϣ</h4>
			<table style="width:100%;">
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">����</th> 
					<td>${userName}</td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">֤������</th>
					<td>���֤</td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">֤������</th>
					<td>${credentialsNumber}</td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">�Ա�</th>
					<td><s:if test="%{gender==\"0\"}">��</s:if><s:else>Ů</s:else></td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">��������</th>
					<td>${birthDate}</td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">�ƶ��绰</th>
					<td>${mobilePhone}</td>
				</tr>
				<tr>
					<td style="width: 5%;"></td>
					<th style="width: 30%; text-align: left;">��������</th>
					<td>${email}</td>
				</tr>
			</table>
			</div>
					
			<div data-role="fieldcontain">	
			<h4>�����ˣ�����</h4>	
			</div>	
			
			<form>
			<input id="checkbox1" name="checkbox1" type="checkbox" />
		      <label for="checkbox1">
		          1.���˳�ŵ������������ʵһ�£�������ٺ��ش���©��<br>
				  2.����Ը��е���ʵ��֪�������������Ρ�
			</label>
			</form>
			<table style="width:100%;"><tr><td>
			<a data-role="button" class="bt_yellow" href="javascript:void(0)" onclick="history.go(-1)">
				   ����
			</a></td><td>
			<a data-role="button" class="bt_red" id="nextBtn" data-transition="slide">
				ȷ��Ͷ��
		    </a></td></tr></table>
				</div><!-- /content -->
		<div id="progressBar" style="display: none; width: 100%; height: 200px;">
			<table align="center" style="height: 200px;">
				<tr valign="middle" align="center">
					<td>
						<img src="${ctx }/web/mobile/product/loon/images/progress.gif" width="16" height="16" style="vertical-align: middle;">
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
		<form id="fm" name="fm" action="" method="post">
			<input type="hidden" id="proposalContNo" name="proposalContNo" value="${proposalContNo}" />
			<input type="hidden" id="orderId" name="orderId" value="${orderId}" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
			<input type="hidden" id="number" name="number" value="${number}" />
			<input type="hidden" id="credentialsNumber" name="credentialsNumber" value="${credentialsNumber}" />
		</form>
		<div data-role="footer"  data-theme="c">
		<h4>��̩���ٱ��չɷ����޹�˾</h4>
		</div>
		
		</div><!-- /page -->
	</body>
</html>