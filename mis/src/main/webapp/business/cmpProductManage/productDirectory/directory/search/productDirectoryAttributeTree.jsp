<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>��ƷĿ¼��������</title>
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx }/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<style>
.tdA {
	width: 90%;
	height: 25px;
	text-align: center;
}

.buttonA {
	width: 25%;
	height: 25px;
	text-align: center;
}

.diretoryAttributeTableStyle {
	width: 100%;
	height: 800px;
}
#operatorTable td {
		vertical-align:middle;
		text-align:center;
		width:82px;
}
</style>
</head>
<body leftmargin="0" topmargin="0">
<div class="public_fun_title">��ƷĿ¼������</div>
<div style="height:10px;clear:both;">&nbsp;</div>
<center>
<table id="productAttributeRelateTree" align="center" width="50%" cellspacing="0" cellpadding="0">
	<tr>
		<td class="frmCreate_kuang frmCreate_kuang_header">��ƷĿ¼������</td>
	</tr>
	<tr>
		<td class="td_head" style="text-align:left;border-left:1px solid #dcdcdc;border-right:1px solid #dcdcdc;border-bottom:1px solid #dcdcdc; padding-top:15px;" valign="top">
			<div id="attributeTree" style="overflow-x:hidden;"></div>
		</td>
	</tr>
	<tr>
		<td align="right" style="padding-top:10px;">
			<table id="operatorTable">
				<tr>
					<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/productDirectory/productDetail.do?geDirectory.eid=<%=request.getParameter("eId")%>'; ">����</td>
					<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="bindProductAttribute();">����</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</center>
</body>
<script type="text/javascript">
	tree = new dhtmlXTreeObject("attributeTree", "100%", "100%", 0);
	tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableThreeStateCheckboxes(true);
	tree.enableCheckBoxes(true, true);
	tree.loadXMLString("${treeXml}");
	//��Ʒ������
	function bindProductAttribute(){
		children = tree.getAllCheckedBranches();
		$.ajax({
		    url: "${ctx}/productDirectory/addProductDirectoryAttributeRelate.do",
		    data: {"eId":"<%=request.getParameter("eId")%>","attributes":children},//�������
		    type: 'POST',
		    dataType: 'json',
		    error: function(){
		        alert("����ʧ��,���Ժ����!");
		    },
		    success: function(data){
			    if(data.resultFlag == "success"){
					alert("��ƷĿ¼���Թ�����ӳɹ���");
					window.close();
					window.opener.parent.frames[0].doSearch();
				}
		    }
		});
	}
	
	var productAttributeRelateTree = document.getElementById("productAttributeRelateTree");
	var attributeTree = document.getElementById("attributeTree");
	productAttributeRelateTree.style.height = document.body.clientHeight - 100;
	attributeTree.style.height = document.body.clientHeight - 140;
	if(document.body.clientWidth > 550){
		productAttributeRelateTree.style.width = 550;
	}
</script>

</html>