
//ȫ��
var idStr = "";
var count = 0;

//��ȫ�ָ�ֵ
function getId(obj) {
	try{
		obj.contentWindow.checkSingleRow();
		idStr = document.getElementById("idStr").value;
		count = document.getElementById("count").value;
		return true;
	} catch (e) {
		alert("��ѡ��Ҫ�����ļ�¼��");
		return false;
	}
}

//��ʼ��ҳ������
function init(){
//	var netSaleProductType = document.getElementById("netSaleProductType");
//	netSaleProductTypeChange(netSaleProductType);
	doSearch();//�ύ��
 	recommendProductSrc();//�����ӵĲ�Ʒ
	$("#open_titleDIV").css("width",  document.body.scrollWidth);//�������Ӧ
}
//���������б�����ʾ������ʾ
function netSaleProductTypeChange(obj){
	var type = $(obj).val();
	if(type == "01"){//��
		$("#trtd13").html("���ִ��룺");//��1��  ��3�� 
		$("#trtd14").html("<input type='text' id='riskCode' name='riskCode' />");//��1��  ��4�� 
		$("#trtd21").html("�������ƣ�");//��2�� ��1��
		$("#trtd22").html("<input id='riskCName' name='riskCName'/>");//��2��  ��2��
		$("#trtd23").hide();//��2��  ��3��
		$("#trtd24").hide();//��2��  ��4��
	}else if(type == "02"){//�ǳ�
		$("#trtd13").html("��Ʒ���룺");//��1��  ��3�� 
		$("#trtd14").html("<input type='text' id='coreProductCode' name='coreProductCode' />");//��1��  ��4�� 
		$("#trtd21").html("��Ʒ���ƣ�");//��2�� ��1��
		$("#trtd22").html("<input id='coreProductSimpleName' name='coreProductSimpleName'/>");//��2��  ��2��
		$("#trtd23").show();
		$("#trtd23").html("��<span style=\"letter-spacing:20px\">&nbsp;</span>����");//��2��  ��3��
		$("#trtd24").show();
		setAreaInnerHtml("ProductMain");//���õ�����HTML
	}else{//��
		$("#trtd13").html("����Ʒ���ƣ�");//��1��  ��3�� 
		$("#trtd14").html("<input name='cardProductName' type='text'/>");//��1��  ��4��
		$("#trtd21").html("��<span style=\"letter-spacing:20px\">&nbsp;</span>����");//��2�� ��1��
		$("#trtd22").html("");//��2��  ��2��
		$("#trtd23").hide();
		$("#trtd23").html("");//��2��  ��3��
		$("#trtd24").hide();
		$("#trtd24").html("");
		setAreaInnerHtml("Card");//���õ�����HTML
	}
}

//���õ�����HTML
function setAreaInnerHtml(selectType){
	var isParentFlag = $("#isParentFlag").val();
	var parentAreaHtml = "";
	if(isParentFlag=="yes"){//�����
		parentAreaHtml = parentAreaHtml+"<input type='text' id='authorityDepartmentManager' name='saleDeptSelect' />";  //����������
		parentAreaHtml = parentAreaHtml+"<input type='hidden' id='authorityid' name='saleDeptSelectCode' />";//����ID
		
		parentAreaHtml = parentAreaHtml+"<span  id='buttonId'>";//��ѡ��ť start
		parentAreaHtml = parentAreaHtml+"<input type='button' value='��ѡ��' onclick='alertTree();' >";
		parentAreaHtml = parentAreaHtml+"</span>";
	}else{//�ӽ��
		var deptName = $("#deptName").val();
		var deptId = document.getElementsByName("saleDept").value;
		parentAreaHtml = parentAreaHtml+"<input type='text' id='authorityDepartmentManager' name='saleDeptSelect' value='"+deptName+"'/>";//����������
		parentAreaHtml = parentAreaHtml+"<input type='hidden' id='authorityid' name='saleDeptSelectCode' value='"+deptId+"'/> ";
	}
	
	if(selectType=="ProductMain"){
		$("#trtd24").html(parentAreaHtml);
	}else{
		$("#trtd22").html(parentAreaHtml);
	}
	
}

//����һ����
function alertTree(){
	var parentFilterId = document.getElementsByName("saleDept")[0].value; 
	window.open(contextRootPath+'/deptAuthority/DeptAuthIdQuery.do?&authorityid=ROLE_B_AAGA&type=2&parentFilterId='+parentFilterId,'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
}

//�ύ
function doSearch(){
		$("#recommendProductQueryForm").submit();
}
//�����ӵĲ�Ʒ
function recommendProductSrc(){
	var recommendProduct = document.getElementById("recommendProduct");
	recommendProduct.src = contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/createGeRelate/addKindUse.jsp";
//	recommendProduct.src = contextRootPath+"/business/cmpProductManage/riskAndKindManage/recommendKind.do?riskV=&idsValue=";
}
//���
function doClean(){
	$("#cleanKindCode").attr("value","");
}

//���ӷ���
function addRecommendProduct(){
	getId(document.getElementById("noRecommendProduct"));//��ȫ�ָ�ֵ
	if (count > 0) {
		var noRecommendProductTrIdStrs = idStr.split(",");
		var trId = "";
		for (var i = 0; i < noRecommendProductTrIdStrs.length; i++) {
			var trs =  noRecommendProductTrIdStrs[i].split("@");
			var riskCode = trs[0];
			var kindCode = trs[1];
			var kindCName = trs[2];
			if(!containt(kindCode,'recommendProduct')){//��������������Ҳ����
				var appendHTML = "";
				appendHTML = appendHTML+"<tr id='tr_" + kindCode + "' class='search_tr_selected' >";
				appendHTML = appendHTML+"<td class='td_border_body_top_none' nowrap>";
				appendHTML = appendHTML+"<input type='checkbox' name='checkChild' checked='checked' id='"+kindCode+"' onclick='checkSingleRow();' value='"+riskCode+"@"+kindCode+"@"+kindCName+"'/>";
				appendHTML = appendHTML+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' nowrap style='height: 28px'>"+riskCode+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' nowrap>"+kindCode+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' width='200px' nowrap>"+kindCName+"</td>";
				appendHTML = appendHTML+"</tr>";
				$("#recommendProduct").contents().find("#recommendProductTable").append(appendHTML);
				$("#noRecommendProduct").contents().find("#tr_"+kindCode).remove();//����������ɾ����//ɾ�������
			}
		}
		
		//checkboxȫѡ�Ĳ���
		if ($("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked") == "checked") {
			$("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked",false);
			$("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked",true);
		} 
		
	} else {
		alert("��ѡ��Ҫ�����ļ�¼��");
	}
}

//�Ƴ�
function removeRecommendProduct(){
	getId(document.getElementById("recommendProduct"));
	if (count > 0) {
		var recommendProductTrIdStrs = idStr.split(",");
		var trId = "";
		for (var i = 0; i < recommendProductTrIdStrs.length; i++) {
			var trs =  recommendProductTrIdStrs[i].split("@");
			var riskCode = trs[0];
			var kindCode = trs[1];
			var kindCName = trs[2];
			
			//�Ƴ�
			$("#recommendProduct").contents().find("#tr_"+kindCode).remove();
				if(!containt(kindCode,'noRecommendProduct')){//�������в�������Ԫ��
					var appendHTML = "";
					appendHTML = appendHTML+"<tr id='tr_"+kindCode+"' class='search_tr_selected' >";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>";
					appendHTML = appendHTML+"<input type='Checkbox' name='checkChild' id='"+kindCode+"' checked='checked' onclick='checkSingleRow();' value='"+riskCode+"@"+kindCode+"@"+kindCName+"'>";
					appendHTML = appendHTML+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+riskCode+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+kindCode+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+kindCName+"</td>";
					appendHTML = appendHTML+"</tr>";
					$("#noRecommendProduct").contents().find("#noRecommendProductTable").append(appendHTML);
				}
		}
		
		if ($("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked") == "checked") {
			$("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked",false);
			$("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked",true);
		} 
		
	} else {
		alert("��ѡ��Ҫ�����ļ�¼��");
	}
}



//��� ��ť����     �ύ
function saveRecommendProduct(){

    	var obj = document.getElementById("recommendProduct");
	    obj.contentWindow.getAddProductInfo();
		var kinds = document.getElementById("kinds").value.split(",");
	//	var riskCodes = document.getElementById("riskCodes").value.split(",");
	//	var mainCodes = document.getElementById("mainCodes").value.split(",");
		var productNames = document.getElementById("productNames").value.split(",");
		//����һ��ҳ���д
			var productCallBack = window.opener.document.getElementById("productCallBack");
			var productCallBacktwo = window.opener.document.getElementById("productCallBacktwo");
		
			var showInfo = window.opener.document.getElementById("showInfo");
			var appendHtml = "";
			var appendHtm2 = "";
			for(var i=0;i<kinds.length;i++){
				if(kinds[i]!=""&&kinds[i]!=undefined&&productNames[i]!=""&&productNames[i]!=undefined){
					if(i<=9){
						appendHtml = appendHtml+"<tr  class='resetNode'>";
						appendHtml = appendHtml+"<td>"+(i+1)+".</td>";
						appendHtml = appendHtml+"<td>"+"<input type='hidden'  name='coreProductCode' value='"+kinds[i]+"'>"+"</td>";
						appendHtml = appendHtml+"<td>"+productNames[i]+"<input type='hidden' value='"+kinds[i]+"' name='geKind.geKindRelateList["+i+"].id.relateKindCode'/></td>";
						appendHtml = appendHtml+"</tr>";
					} else {
						showInfo.style.display = "";
						appendHtm2 = appendHtm2+"<tr  class='resetNode' >";
						appendHtm2 = appendHtm2+"<td>"+(i+1)+".</td>";
						appendHtm2 = appendHtm2+"<td>"+productNames[i]+"<input type='hidden' value='"+kinds[i]+"' name='geKind.geKindRelateList["+i+"].id.relateKindCode'/></td>";
						appendHtm2 = appendHtm2+"</tr>";
					}
				}
			}
			$(productCallBack).html(appendHtml);
			$(productCallBacktwo).html(appendHtm2);
			window.close();
		
}

//�Ҳ��Ƿ���������eid
function containt(kindCode,iframeType){
	if(iframeType=="recommendProduct"){
		
		var value = $("#recommendProduct").contents().find("#tr_"+kindCode);
		if(value.length>0){
			$("#noRecommendProduct").contents().find("#tr_"+kindCode).remove();//����������ɾ����//ɾ�������
			$("#recommendProduct").contents().find("#"+kindCode).attr("checked",true);//�������Ҳ�δѡ��,���Ҳ�ѡ��
			return true;
		}else{
			return false;
		}
		
	}else{
		
		var value = $("#noRecommendProduct").contents().find("#tr_"+kindCode);
		if(value.length>0){
			$("#recommendProduct").contents().find("#tr_"+kindCode).remove();//
			$("#noRecommendProduct").contents().find("#"+kindCode).attr("checked",true);//�������Ҳ�δѡ��,���Ҳ�ѡ��
			return true;
		}else{
			return false;
		}
		
	}
	
}

//չʾ�����Ʒ��Ϣ
function showInfo(){
	var productInfo = document.getElementById("productInfo");
	var showText  = document.getElementById("showText");
	var showPicture = document.getElementById("showPicture");
	var hideText  = document.getElementById("hideText");
	var hidePicture  = document.getElementById("hidePicture");
	showText.style.display = "none";
	showPicture.style.display = "none";
	hideText.style.display ="";
	hidePicture.style.display ="";
	productInfo.style.display = "";
	
}
//��������Ʒ��Ϣ
function hideInfo(){
	var productInfo = document.getElementById("productInfo");
	var hideText  = document.getElementById("hideText");
	var hidePicture  = document.getElementById("hidePicture");
	var showText  = document.getElementById("showText");
	var showPicture = document.getElementById("showPicture");
	productInfo.style.display = "none";
	hideText.style.display ="none";
	hidePicture.style.display ="none";
	showText.style.display = "";
	showPicture.style.display = "";
}

