<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>���������̨����ϵͳ-�ǳ��ղ�Ʒ����-��ѯ��Ʒ</title>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
	<style type="text/css">
		input,select {
			width:170px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			var coreProductCode = "${param.coreProductCode}";
			if (coreProductCode != "") {
				$("#coreProductCode").val(coreProductCode);
				$('form:first').submit();
			};
		});
		
		$(function(){
			$.ajax({
				   async: false,
				   cache :false,
				   type: "POST",
				   url: '${ctx}/productManage/findDataFromDic.do',
				   data: {
						"codeType":"BusinessArea",
						"menuCode":"ROLE_B_CRPR"
					},//�������
				   dataType:"json",
				   success: function(data){
					   $("#businessArea").html();
					   var appendHTML = "<option value=''>--��ѡ��--</option>";
				    	for(var i = 0; i < data.mapList.length; i++){
				    		var baObj = data.mapList[i];
				    		 appendHTML += "<option value='" + baObj.codeCode + "'>" + baObj.codeName + "</option>";
				    	}
				    	$("#businessArea").append(appendHTML);
					},
				   error:function(XMLHttpRequest, textStatus, errorThrown){
					   
				   }
			});
		});
		

	</script>
</head>
<body topmargin="0" leftmargin="0">
		<!-- �ж�Ȩ�� -->
		<acc:showView source="ROLE_B_CRPR_H">
			<c:set var="canDo_audit" value="yes"></c:set>
		</acc:showView>
		<acc:showView source="ROLE_B_CRPR_F">
			<c:set var="canDo_publish" value="yes"></c:set>
		</acc:showView>
		
	<div class="public_fun_title">��Ʒά��</div> 
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="${ctx}/productManage/searchAllProduct.do" method="post" target="fraSearchList">

			<table class="table_style" align="center" width="500px">
				<tr>
					<td class="td_head" width="80" nowrap>
						��Ʒ���룺   
					</td>
					<td class="td_body">
						<input type="text" id="coreProductCode" name="geProductMain.coreProductCode" maxlength="25">
					</td>
					<td class="td_head" width="80" nowrap>
						��Ʒ���ƣ�
					</td>
					<td class="td_body">
						<input type="text" id="productName" name="geProductMain.productName" maxlength="25">
					</td>
				</tr>
				<tr>
					<td class="td_head" width="80" nowrap>
						ҵ������
					</td>
					<td class="td_body">
						<select id="businessArea"  name="geProductMain.businessArea">
						</select>
					</td>
					<td class="td_head" width="80" nowrap>
						״̬��   
					</td>
					<td class="td_body">
						<select id="productStatus" name="geProductMain.productStatus">
							<acc:showView source="ROLE_ALL">
								<option value="all">--��ѡ��--</option>
							</acc:showView>
							<acc:showView source="ROLE_CREATE">
								<option value="01">�Ѵ���</option>
							</acc:showView>
							<acc:showView source="ROLE_FLOW">
								<option value="02">�Ѷ�������</option>
							</acc:showView>
							<acc:showView source="ROLE_DE">
								<option value="03">����ϸ����</option>
							</acc:showView>
							<acc:showView source="ROLE_PU">
								<option value="04">�����</option>
							</acc:showView>
							<acc:showView source="ROLE_AUDIT">
								<option value="05">�ѷ���</option>
							</acc:showView>
						</select>
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="8" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>��ѯ</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" align="right" onclick="javascript:$('form')[0].reset();" nowrap>���</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

<script type="text/javascript">
doSearch();

function doSearch(){
	if($("#productStatus").val()=='03' || $("#productStatus").val()=='04'){
		document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
	}else{
		document.frmInput.action = "${ctx}/productManage/searchAllProduct.do";
	}
	document.frmInput.submit();
}

//����ҳ������ģ���������Ȩ�ޣ���������ϸ�������ݣ�����з���Ȩ�ޣ��������������
$(document).ready(function(){

	var fm = "${param.fm}";
	if("welcome" == fm){
		if("${canDo_publish}"=="yes" && "${canDo_audit}"==""){
			$("#productStatus").val("04");
			document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
			document.getElementById("frmInput").submit();
		}else if("${canDo_publish}"=="yes" && "${canDo_audit}"=="yes"){
			$("#productStatus").val("03");
			document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
			document.getElementById("frmInput").submit();
		}else if("${canDo_publish}"=="" && "${canDo_audit}"=="yes"){
			$("#productStatus").val("03");
			document.frmInput.action = "${ctx}/productManage/searchFromWorkFlow.do?type=workflow";
			document.getElementById("frmInput").submit();
		}
	}
		});
		
$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
var ids = ['des'];
    	var contents = ['˵��:��ѯ���޸Ļ�ɾ���ǳ��ղ�Ʒ<br/>'
    	                + 'ʹ����Ⱥ:��Ʒ������Ա<br/>'
    	                + '��������:��Ҫ��������������<br/>'
    	                + 'ע������:����ʱ�ϸ���������<br/>'];
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
</html>
