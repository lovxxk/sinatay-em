<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/mis_basic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<td onclick="javascript:maximizeGrid(this);">&nbsp;</td>
				<td onclick="getProduct();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">��������Ʒ����</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="idStr" id="idStr">
	<input type="hidden" name="count" id="count">
	<input type="hidden" name="status" id="status">
</body>
<script language="javascript">
	//��ʼ��toolbar��ʽ
	var toolbarTable = document.getElementById("toolbar_main");
	var toolbarRow = toolbarTable.rows[0];
	toolbarRow.className = "toolbar_bg";
	var tds = toolbarRow.cells;
	for(var i = 0; i < tds.length; i++){
		if(i == 0){
			tds[i].className = "toolbar_up";
			tds[i].title = "�������ݱ�";
		}else if(i % 2 == 0){
			tds[i].className = "toolbar_gap";
		}else{
			tds[i].className = "toolbar_normal";
		}
	}
	//�ı�toolbar��ʽ
	function changeTD_style(obj){
		if(obj.className == "toolbar_normal"){
			obj.className = "toolbar_hover";
		}else{
			obj.className = "toolbar_normal";
		}
	}
	
	//��������start
	var defaultRows = top.document.getElementById("fraTop").rows;
	var idStr = "";
	var count = 0;
	var printID = "";
	var emailID = "";
	var receiver = "";
	var flag = "";
	var status = "";
	function maximizeGrid(obj){
		var className = obj.className;
		if(className == "toolbar_up"){//����
			obj.className = "toolbar_down";
			top.document.getElementById("fraTop").rows='0,30,*,40,0';
			obj.title = "��ԭ���ݱ�";
		}else{//����
			obj.className = "toolbar_up";
			top.document.getElementById("fraTop").rows=defaultRows;
			obj.title = "�������ݱ�";
		}
	}
	
	function doSearch(){
		var fraSearchForm = top.frames[0];
		fraSearchForm.doSearch();
	}
	
	function getId(){
		try{
			top.frames[2].window.checkSingleRow();
			idStr = document.getElementById("idStr").value;
			count = document.getElementById("count").value;
			status = document.getElementById("status").value;
			return true;
		}catch (e) {
			alert("��ѡ��Ҫ�����ļ�¼��");
			return false;
		}
	}
	var top_ = (window.screen.availHeight-750)/2;
	if(top_ < 0){
		top_ = 0;
	}
	var left_ = (window.screen.availWidth-1020)/2;
	if(left_ < 2){
		left_ = 0;
	}
	var config = "width=1020,height=750,toolbar=no,statusbar=no";
	config += ",top="+top_ +",left="+left_;
	//��������end
	
	//���⴦���湦�ܲ�ͬ����ͬ
	//������Ŀ¼
	function getProduct(){
		getId();
		var valArr = idStr.split("@");
		if (count == 1) {
			if (confirm("ȷ�������ò�Ʒ?")) {
				cleanUpload();//�����һ�ε�����upload
				var coreProductCodeOld =  top.opener.document.getElementById("coreProductCodeOld").value;
				top.opener.document.getElementById("coreProductCode").value = valArr[0];
				top.opener.document.getElementById("productName").value = valArr[1];
				if(valArr[2]!=""){
					createUploadPicture(valArr[2],valArr[0],coreProductCodeOld);//����Ҫ�ϴ����ı���
				}
			}
			top.close();
		} else if (count == 0) {
			alert("��ѡ��Ҫ�����Ĳ�Ʒ��");
		} else {
			alert("ֻ�ܶԵ�����Ʒ���в�����");
		} 
	}
	//����ϴ�ͼƬ�Ĺ켣
	function cleanUpload (){
		
		var productTable = top.opener.document.getElementById("productTable");
		$(productTable).find("tr").each(function(index, value){
			if($(value).attr("name")=="upload"||$(value).attr("name")=="showUploadPicture"){
				$(value).remove();
			}
		});
	}
	//����д�ϴ�ͼƬ�Ĺ켣
	function createUploadPicture(pictureCount,productCode,coreProductCode){
		if(pictureCount!=""&&parseInt(pictureCount)>0){
			var htmlUpload = "";
			//			
			if(productCode==coreProductCode){//�ð�showUploadPicture ��ʾ����
				for(var i=0;i<parseInt(pictureCount);i++){
					//�ϴ�ͼƬչʾ
					var objShow = top.opener.document.getElementById("tableDelShowHidden");
					var objShow= $(objShow);
					
					//��һ��
					objShow.find("td").each(function(index, value){
						if(index==0){
							$(value).html("�ϴ�ͼƬ"+(i+1))	;
						}
						if(index==1){
							var picUrl = top.opener.document.getElementsByName("pictureUrl")[i].value;
							$(value).html("<img src='${ctx}/"+picUrl+"'/>");
						}
					});
					htmlUpload = htmlUpload+objShow.html();
					//�޸��ϴ�ͼƬ
					var objTemp = top.opener.document.getElementById("tableDelHidden");
					var obj = $(objTemp);
					//��һ��
					obj.find("td").each(function(index, value){
						if(index==0){
							var temp  ="�޸��ϴ�ͼƬ"+(i+1);
							$(value).html(temp)	
						}
					});
					htmlUpload = htmlUpload+obj.html();
				}//end for
			}else{
				for(var i=0;i<parseInt(pictureCount);i++){
					htmlUpload = htmlUpload+createUpload(i)
				}
			}// end if(productCode==coreProductCode){
				
			$(top.opener.document.getElementById("productType")).append(htmlUpload);
		}//end if(pictureCount!=""&&parseInt(pictureCount)>0){
	}
		
	function createUpload(i){
		var objTemp = top.opener.document.getElementById("tableDelHidden");
		var obj = $(objTemp);
		//��һ��
		obj.find("td").each(function(index, value){
			if(index==0){
				var temp  ="�ϴ�ͼƬ"+(i+1);
				$(value).html(temp)	
			}
		});
		return obj.html();
	}
	//���⴦��end
</script>
</html>
