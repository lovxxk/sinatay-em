<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>�����������ϵͳ-�������β�ѯ</title>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<style type="text/css">
		#operatorTable td {
				vertical-align:middle;
				text-align:center;
				width:82px;
		}
		
		input,select {
			width:170px;
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">��������ά��</div>
	<div class="table_content" style="padding-top:1px;">
		<form id="frmInput" name="frmInput" action="${ctx}/productManage/findGeRiskConfig.do" method="post" target="fraSearchList">
			<table  align="center" width="528px">
			<tr>
					<td class="td_head" width="80" nowrap>���ִ��룺</td>
					<td class="td_body"> <input type="text" id="riskCode" name="geRiskConfig.riskCode" maxlength="25">
					</td>
					<td class="td_head td_head_center" width="108" nowrap>
						�������ִ��룺
					</td>
					<td class="td_body">
						<input type="text" id="coreRiskCode" name="geRiskConfig.coreRiskCode" maxlength="25">
					</td>
				</tr>
				<tr>
					<td class="td_head td_head_center" width="80" nowrap>�������ƣ�</td>
					<td class="td_body"> <input type="text" id="riskName" name="geRiskConfig.riskName" maxlength="25">
					</td>
					<td class="td_head" width="px" nowrap>ҵ������</td>
					<td class="td_body" colspan="3">
						<select id="businessArea" name="geRiskConfig.businessArea">
							<option value="" selected>--��ѡ��--</option>
						</select>
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="center">
						<table id="operatorTable">
							<tr>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="10">
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>��ѯ</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:$('form')[0].reset();" nowrap>���</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		window.onload = function (){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		};
	
		function doSearch() {
			window.parent.fraToolbar.document.getElementById("idStr").value = "";
			window.parent.fraToolbar.document.getElementById("count").value = "";
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		}
		
		$(function(){
			$.ajax({
				   cache :false,
				   type: "POST",
				   url: '${ctx}/productManage/findDataFromDic.do',
				   data: {
						"codeType":"BusinessArea",
						"menuCode":"ROLE_DUTYKINDMAINTAIN"
					},//�������
				   dataType:"json",
				   success: function(data){
					   $("#businessArea").empty();
					   $("#businessArea").append("<option value=''>--��ѡ��--</option>");
				    	for(var i = 0; i < data.mapList.length; i++){
				    		var baObj = data.mapList[i];
				    		$("#businessArea").append("<option value='" + baObj.codeCode + "'>" + baObj.codeName + "</option>");
				    	}
					},
				   error:function(XMLHttpRequest, textStatus, errorThrown){
					   
				   }
			});
		});
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['˵��:��ѯ���޸Ļ�ɾ������������Ϣ��<br/>'
		    	               + 'ʹ����Ⱥ:��������������Ա<br/>'
		    	               + '��������:<br/>'
		    	               + 'ע������:����ʱÿ��������������Ҫ��һ������<br/>'];
		        	for ( var i = 0 ; i < ids.length ; i++ ){
		    			$('#' + ids[i]).qtip({ 
		    				content:contents[i], 
		    				position: { 
								corner: { 
								tooltip: 'topMiddle',
								target: 'bottomMiddle'
								} ,
								adjust: { 
									screen: true 
									}
							}, 
							 style: {
									border: { 
										width: 1,
										radius: 1,
										color: '#00739f'
										},
										width:450,
										textAlign: 'left',
										background: '#e0f2ff', 
										tip:true,//����������Ƿ����
										padding :10
									}
								});
		        	}
	</script>
</body>
</html>
