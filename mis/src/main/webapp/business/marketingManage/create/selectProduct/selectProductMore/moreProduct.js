
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
	//var netSaleProductType = document.getElementById("netSaleProductType");
	// netSaleProductTypeChange(netSaleProductType);
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
	document.getElementById("recommendProductQueryForm").submit();
}
//���
function doClean(){
	var isParentFlag = $("#isParentFlag").val();
	if(isParentFlag=="yes"){
		$(':input')   
		 .not(':button, :submit, :reset, :hidden')   
		 .val('')   
		 .removeAttr('checked')   
		 .removeAttr('selected');
	}else{
		var authorityDepartmentManager = $("#authorityDepartmentManager").val();
		var authorityid  = $("#authorityid").val();
		$(':input')   
		 .not(':button, :submit, :reset, :hidden')   
		 .val('')   
		 .removeAttr('checked')   
		 .removeAttr('selected');
		$("#authorityDepartmentManager").attr("value",authorityDepartmentManager);
		$("#authorityid").attr("value",authorityid);
	}
	
}

//���ӷ���
function addRecommendProduct(){
	getId(document.getElementById("noRecommendProduct"));//��ȫ�ָ�ֵ
	if (count > 0) {
		var noRecommendProductTrIdStrs = idStr.split(",");
		var trId = "";
		for (var i = 0; i < noRecommendProductTrIdStrs.length; i++) {
			var trs =  noRecommendProductTrIdStrs[i].split("@");
			var eid = trs[0];
			var  productName= trs[1];
			var coreProductCode = trs[2];
			var selectType = getSelectType();
			
			if(!containt(eid,'recommendProduct')){//��������������Ҳ����
				var appendHTML = "";
				appendHTML = appendHTML+"<tr id='tr_" + eid + "' class='search_tr_selected' >";
				appendHTML = appendHTML+"<td class='search_body_center' nowrap>";
				appendHTML = appendHTML+"<input type='checkbox' name='checkChild' checked='checked' id='"+eid+"' onclick='checkSingleRow();' value='"+eid+"@"+coreProductCode+"@"+productName+"@"+selectType+"'/>";
				appendHTML = appendHTML+"</td>";
				appendHTML = appendHTML+"<td class='search_body_center' nowrap width='200px' style='height: 28px'>"+eid+"</td>";
				appendHTML = appendHTML+"<td class='search_body_center' width='250px' nowrap>"+productName+"</td>";
				appendHTML = appendHTML+"<td class='search_body_center' width='80px' nowrap>"+coreProductCode+"</td>";
				appendHTML = appendHTML+"</tr>";
				$("#recommendProduct").contents().find("#recommendProductTable").append(appendHTML);
				$("#noRecommendProduct").contents().find("#tr_"+eid).remove();//����������ɾ����//ɾ�������
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
			var eid = trs[0];
			var productName = trs[1];
			var  coreProductCode = trs[2];
			//var reSelectType = trs[3];
			//var noSelectType = getSelectType();
			
			//�Ƴ�
		   $("#recommendProduct").contents().find("#tr_"+eid).remove();
		//if(reSelectType==noSelectType){//���Ҳ��е�Ԫ���Ƶ����
				if(!containt(eid,'noRecommendProduct')){//�������в�������Ԫ��
					var appendHTML = "";
					appendHTML = appendHTML+"<tr id='tr_"+eid+"' class='search_tr_selected' >";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>";
					appendHTML = appendHTML+"<input type='Checkbox' name='checkChild' id='"+eid+"' checked='checked' onclick='checkSingleRow();' value='"+eid+"@"+coreProductCode+"@"+productName+"'>";
					appendHTML = appendHTML+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+eid+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+coreProductCode+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+productName+"</td>";
					appendHTML = appendHTML+"</tr>";
					$("#noRecommendProduct").contents().find("#noRecommendProductTable").append(appendHTML);
				}
				
			//}
		}
		
		if ($("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked") == "checked") {
			$("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked",false);
			$("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked",true);
		} 
		
	} else {
		alert("��ѡ��Ҫ�����ļ�¼��");
	}
}

//�������
function getSelectType(){
	var selectType =$("#netSaleProductType").val();
	if(selectType=="01"){//��
		return "Car";
	}else if(selectType=="02"){//�ǳ�
		return "ProductMain";
	}else{
		return "Card";
	}
}



//��� ��ť����
function saveRecommendProduct(){
	var obj = document.getElementById("recommendProduct");
		obj.contentWindow.getAddProductInfo();
		var nameCount = document.getElementById("nameCount").value;
		var eids = document.getElementById("eids").value.split(",");
		var productNames = document.getElementById("productNames").value.split(",");
		var productCallBack = window.opener.document.getElementById("productMoreCallBack");
		var temps = window.opener.document.getElementsByName("itemID");
		var productCallBacks = window.opener.document.getElementsByName("productMoreCallBack");
		var productCallBacktwo = window.opener.document.getElementById("productMoreCallBacktwo");
		var showInfo = window.opener.document.getElementById("showInfo");
		var appendHtml = "";
		var appendHtm2 = "";
		for(var i=0;i<eids.length;i++){
			if(eids[i]!=""&&eids[i]!=undefined&&productNames[i]!=""&&productNames[i]!=undefined){
					appendHtml = appendHtml+"<tr  class='resetNode'>";
					appendHtml = appendHtml+"<td>"+(i+1)+".</td>";
					appendHtml = appendHtml+"<td>"+productNames[i]+"</td>";
					appendHtml = appendHtml+"</tr>";
			}
		}
		$(temps[nameCount]).val(document.getElementById("eids").value);
		$(productCallBacks[nameCount]).html(appendHtml);
		
		window.close();
}

//�Ҳ��Ƿ���������eid
function containt(eid,iframeType){
	if(iframeType=="recommendProduct"){
		
		var value = $("#recommendProduct").contents().find("#tr_"+eid);
		if(value.length>0){
			$("#noRecommendProduct").contents().find("#tr_"+eid).remove();//����������ɾ����//ɾ�������
			$("#recommendProduct").contents().find("#"+eid).attr("checked",true);//�������Ҳ�δѡ��,���Ҳ�ѡ��
			return true;
		}else{
			return false;
		}
		
	}else{
		
		var value = $("#noRecommendProduct").contents().find("#tr_"+eid);
		if(value.length>0){
			$("#recommendProduct").contents().find("#tr_"+eid).remove();//
			$("#noRecommendProduct").contents().find("#"+eid).attr("checked",true);//�������Ҳ�δѡ��,���Ҳ�ѡ��
			return true;
		}else{
			return false;
		}
		
	}
	
}

