<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head> 
	<title>�����������ϵͳ-��Ʒ��ϸ��Ϣ-��������</title>
	<meta http-equiv="content-type" content="text/html; charset=GBK">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script><link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form name="frmInput" id="frmInput" action="${ctx}/productManage/calculationPageFileUpload.do" method="post" enctype="multipart/form-data" >
	
	<div style="height:50px"></div>
	<div class="editor" style="text-align:left;text-align:center;padding-bottom:10px;">
		<table id="productTable" class="table_style" align="center">
		<tr>
			<td class="td_head" width="190px" nowrap>��ѡ��Ҫ�ϴ��ı��������ļ���</td>
			<td class="td_body" id="fileUp">
				<s:file name="productUploadFile" label="File"></s:file>
			</td>
		</tr>
		</table>
	</div>
	<table align="center" style="margin-bottom:30px;">
	<tr>
		<td id="SubmitButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" nowrap>�ϴ�</td>
		<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.close();" nowrap >�ر�</td>
	</tr>
	</table>
	<input type="hidden" id="elementCode" name="elementCode" value="${param.elementCode}" />
	<input type="hidden" id="coreProductCode" name="coreProductCode" value="${param.coreProductCode}" />
	</form>

<script type="text/javascript">
//���ύ
$("#SubmitButton").click(function(){
	if($("#productUploadFile").val()==null||$("#productUploadFile").val()==""){
		alert('��ѡ��Ҫ�ϴ����ļ�');	
		return false;
	}
	if($("#productUploadFile").val().indexOf(".html") <= 0 && $("#productUploadFile").val().indexOf(".jsp") <= 0){
		alert("ֻ���ϴ�.jsp��.html�ļ�");
		document.getElementById("productUploadFile").focus();
		return false;
	}
	
	$("#frmInput").submit();
});
var result = "${message}";
if (result == "success") {
	alert("���������ļ��ϴ��ɹ���");
} else if (result == "failure") {
	alert("���������ļ��ϴ�ʧ�ܣ�");
}else if("nullFile" == result){
	alert("�����ϴ��յ��ļ���");
}
var ids = ['fileUp'];
   		var contents = ['���ݲ�ͬ���򷽰������Ʒ�۸��ҳ�棬ֻ���ϴ�.jsp�ļ���'];
       	for ( var i = 0 ; i < ids.length; i++ ){
   			$('#' + ids[i]).qtip({ 
   				content:contents[i], 
   				position: { 
   					corner: { 
   					tooltip: 'leftMiddle', 
   					target: 'rightMiddle'
   					} 
   				}, 
   				 style: { 
   				border: { 
   					width: 2,
   					radius: 2,
   					color: '#00739f'
   					},
   					width:100,
   					padding: 10, 
   					textAlign: 'left',
   					background: '#e0f2ff', 
   					tip:true//����������Ƿ����
   					//name: 'green' 
   				} 
   			}); 
       	}


</script>
</body>
</html>
