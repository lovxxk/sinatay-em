<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
<style type="text/css">
	.table_style_new{
	border-collapse:collapse;
	font-family:����;
	font-size:12px;
}	
#productDetail tr {
		height:85px;
}
#addRuleDiv table tr {
	height:30px;
}
body,h2{margin:0 ; padding:0;}
#BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:auto;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
#DialogDiv{position:absolute;width:auto; left:50%; top:50%; margin-left:-400px; height:auto; z-index:100;background-color:#fff; border:1px #8FA4F5 solid; padding:1px;}
#DialogDiv h2{ height:25px; font-size:14px; background-color:#27a26b; position:relative; padding-left:10px; line-height:25px;text-align: left;width: 740px}
#DialogDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}
#DialogDiv .form{padding:10px;}
</style>
</head>
<body onload="pageValidate();">
<div class="public_fun_title">
�½��ձ�<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>
<form action="${ctx}/business/cmpProductManage/riskAndKindManage/frmCreate.do" id="frmInput" method="post" target="myFrame">
<div id="BgDiv" ></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;cursor:pointer;">��&nbsp;��</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>
<table align="center" width="720px"  id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
	<td>
	
	<table>
	
	<tr >
		<td class="td_head" width="" nowrap>���ִ��룺</td>
		<td class="td_body">
			<input id="riskCodeV" type="text" name="geKind.id.riskCode" onchange="checkMain();" style="width:170px;" maxlength=80  readonly="readonly" >
			<input type="button" onclick="alertNationality();" value="����ѡ��">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�ձ���룺</td>
		<td class="td_body" >
			<input type="text" name="geKind.id.kindCode" id="kindCodeV" style="width:170px;" style="ime-mode:disabled" maxlength="6" onchange="checkMain();">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�ձ��־��</td>
		<td class="td_body" >
			<select id="kindFlag" name="geKind.kindflag" style="width:170px;" onchange="checkMain();">
					<option value="">--��ѡ��--</option> 
					<option value="01"/>���� </option>
					<option value="02"/>������ </option>
			</select>
		</td>
	</tr>
	<tr style="display:none" id="flagRelateShow">
		<td class="td_head" nowrap>�Ƿ������ձ�ά����ϵ��</td>
		<td class="td_body" >
			<select name="flagRelate" id="flagRelate" style="width:170px;" onchange="checkAddFlag();">
					<option value="1" />��</option>
					<option value="0" selected="selected"/>��</option>
			</select>
		</td>
	</tr>
     <tr>
		<td class="td_head" width="" nowrap>�ձ��������ƣ�</td>
		<td class="td_body">
			<input type="text" id="kindCName" name="geKind.kindCName" style="width:170px;" maxlength=12  >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="" nowrap>�ձ�Ӣ�����ƣ�</td>
		<td class="td_body">
			<input type="text" id="kindEName" name="geKind.kindEName" style="width:170px;" maxlength=12 onchange="checkEn(this);">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�ձ������ƣ�</td>
		<td class="td_body" >
			<input type="text" name="geKind.kindtname" style="width:170px;" maxlength=12 >
		</td>
	</tr>
	<!--<tr>
		<td class="td_head" nowrap>�ձ�����</td>
		<td class="td_body" >
			<input type="text" id="orderNo"  name="orderNo" style="width:170px;" maxlength=6 onchange="checkOrderNum();">
		</td>
	</tr>
	-->
	<tr>
		<td class="td_head" nowrap>�Ƿ�����Ͷ���������⣺</td>
		<td class="td_body" >
			<select name="geKind.nodeductflag" style="width:170px;">
					<option value="">--��ѡ��--</option> 
					<option value="0"/>����Ͷ����������</option>
					<option value="1"/>����Ͷ����������</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�Ƿ���Ч��</td>
		<td class="td_body" >
			<select name="geKind.validInd" style="width:170px;">
					<option value="">--��ѡ��--</option> 
					<option value="0"/>��Ч</option>
					<option value="1" selected="selected"/>��Ч</option>
			</select>
		</td>
	</tr>
	<!--
	<tr>
		<td class="td_head" nowrap>�ײ�������ʾ���ã�</td>
		<td class="td_body" >
			<select name="geKind.isComboFlag" style="width:170px;">
					<option value="">--��ѡ��--</option> 
					<option value="1"/>�����ײ�����</option>
					<option value="0"/>�������Ӷ��������ײ�</option>
			</select>
		</td>
	</tr>
	-->
	<tr>
		<td class="td_head" nowrap>�ձ���д��</td>
		<td class="td_body" >
		    <input type="hidden" name="geKind.isComboFlag" value="1"/>
			<input type="text" name="geKind.abbreviation" style="width:170px;" maxlength=3>
		</td>
	</tr>
	<tr>
		<td class="td_head"  style="cursor:pointer;" nowrap><a href="#" title="������������" style="cursor:pointer;vertical-align:middle;" onclick="btnShowone('memo');" id="memo"><img  src="${ctx}/global/images/help.jpg" border="0" /></a>����������</td>
		<td class="td_body"  >
			<textarea rows="8" cols="40" id="kindDescription" name="geKind.kindDescription" maxlength="600" onkeyup="textAreaMaxLen(this);"></textarea>
		</td>
	</tr>
	<tr>
	<td colspan="2">  
			<div class="frmCreate_kuang" style="margin-left:150px;width:250px;margin-top:10px; display:none;" id="kindDiv" >
			<div class="frmCreate_kuang_header">�����մ���<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
			<div style="padding-top:15px;">
			<div><table id="productCallBack"></table></div>
			<div class="frmCreate_kuang" style="width:250px;">
			<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
				<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
				<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">չ���鿴����</a></span>
				<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
				<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">����</a></span>
				<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
			</div>
			<div id="productInfo" style="padding-top:15px;clear:both;display: none">
			<div><table id="productCallBacktwo"></table></div>
			</div></div>
			</div></div>
			<div id="buttonShow" style="margin-left:150px;padding-top: 10px;text-align: left;display:none;"><input type="button" value="���Ӹ�����" onclick="addKind();" ></div>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�������</td>
		<td class="td_body">
			<select name="geKind.codeType" style="width:170px;" id="codeType" onchange="createValuerange();">
					<option value="">--��ѡ��--</option> 
					<option value="0"/>ȡֵ��Χ��ȡ </option>
					<option value="1"/>ͬ�³����ü� </option>
					<option value="2"/>ͬʵ�ʼ�ֵ </option>
					<option value="3"/>����</option>
			</select>
		</td>
	</tr>

	<tr style="display:none;" id="createInput" >
	  <td class="td_head" nowrap>&nbsp;&nbsp;�³����üۣ�</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" readonly="readonly" style="width:170px;" value="ͬ�³����ü�" id="newCar">
	  </td>
	</tr>
    <tr style="display:none" id="createInputT">
	  <td class="td_head" nowrap>ͬ����ʵ�ʼ�ֵ��</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" readonly="readonly"  style="width:170px;" value="ͬ����ʵ�ʼ�ֵ" id="carT">
	  </td>
	</tr>

	</table>
	</td>
	</tr>
	
		<!-- -----------------------    ��������ʾ  ��ʼ ------------------------------------- -->
	 <tr >
	  <td colspan="4">
	  <div id="createSelect" style="display:none">
	   <div  class="frmCreate_kuang" style="width:547px;margin-top:15px;padding-bottom: 15px;">
	  <div class="frmCreate_kuang_header"><a href="#" title="����ȡֵ��Χչʾͼ" style="cursor:pointer;vertical-align:middle;" onclick="btnShowone('setPageValue');" id="setPageValue"><img  src="${ctx}/global/images/help.jpg" border="0" /></a>����ȡֵ��Χ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
	  <div style="padding-left:3px; padding-top:15px;">
	  <table id="addDiv">
		<tr>
			<td class="td_head" nowrap >����������</td>
			<td class="td_body">
			  <input type="text" name="geKind.valuerange" id="duty" maxlength="10"/>
			</td> 
			<td class="td_body"  style="padding-left:50px">
			 <input type="button" value="�����޶�" onclick="insertMoney(); checkKind();"/>
			</td> 
		</tr>
     	</table>
  
     </div>
	</div>
	</div>
     </td>
	</tr>
   <%//----------------------    ����  ---------------------------------------------------- %>
	 <tr >
	  <td colspan="4">
	  <div id="creatOther" style="display:none">
	   <div  class="frmCreate_kuang" style="width:547px;margin-top:15px;padding-bottom: 15px;">
	  <div class="frmCreate_kuang_header">����<span  style="color:#FF9000;font-weight:bold;"></span></div>
	  <div style="padding-left:3px; padding-top:15px;">
	  <table id="addOther">
		<tr>
			<td class="td_head" nowrap >����������</td>
			<td class="td_body">
			  <input type="text" name="geKind.valuerange" id="otherDuty" maxlength="10"/>
			</td> 
			<td class="td_body"  style="padding-left:60px">
			 <input type="button" value="����" onclick="insertOther();"/>
			</td> 
		</tr>
     	</table>
     </div>
	</div>
	</div>
     </td>
	</tr>
	<%//-------------------------------- ���� ------------------------------------------ %>
		<!-- -----------------------    ��������ʾ   ����    ------------------------------------- -->
			
<!--	<tr height=15><td></td></tr> -->
	<tr>
		<td colspan=4>
		<div>
			<table width="64" align="center">
			<tr>
				<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>���� </td>
				<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
			</tr>
			</table>
			</div>
		</td>
	</tr>
 </table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript"><!--
//------------------- ��ʼ������֤ ----------------------------------------

$(document).ready(function(){
	//pop��ʾ��Ϣ
	var ids = ['kindDescription','codeType'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['����ǰ�˽����ձ���ϸ��Ϣ','����ȡ�������޶���ս��'];
		
    	for ( var i = 0 ; i < 10 ; i++ ){
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
//pop��ʾ��Ϣ����
})


function pageValidate(){
	   var ttvf = tt.vf.req;//�ǿ�
		var elements=$("input[id^='du'][id!='duty']");
		for(var i=0;i<elements.length;i++){
			tt.vf.req.addId($(elements[i]).attr("id"));
		}
	   ttvf.addId('duty', 'newCar','carT');
	   tt.vf.req.add("geKind.id.riskCode","geKind.id.kindCode","geKind.combinekindcode"
			,"geKind.kindCName","geKind.kindflag"
			,"geKind.codeType","geKind.abbreviation"
			,"geKind.validInd","geKind.nodeductflag","geKind.kindDescription"); //��֤�ǿ� //,"geKind.nodeductflag"
    // tt.vf.num.add("orderNo");  //��֤����
    var testCName = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
    testCName.add("geKind.kindCName");//��֤����
    var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "ֻ���������ֻ���ĸ"); 
    ttNumAndCharacter.add("geKind.id.kindCode");//��֤���ֻ�Ӣ��replace(/[^\d]/g,''))" 
   // var ttNumOrder = new tt.RV().set(new RegExp( "^\\d+$" ),"�Բ���ֻ������������");
   // ttNumOrder.add("orderNo");
    

    var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['˵�����½��ձ�<br/>ʹ����Ⱥ�����ղ�Ʒ����������Ա��<br/>�����������ձ��������ʹ�á�<br/>ע�����'];
    	for ( var i = 0 ; i < 10 ; i++ ){
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
					width:320,
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true,//����������Ƿ����
					padding :10
				}
			});
    	}
}
//----------------- ������֤���� --------------------------
//�����ձ�

function doCreate(){
	if( tt.validate()&& checkNum()&& checkRelate()){
		 var KindAddCodeLength = $("input[name$='id.relateKindCode']").length;
		 var kindMain = $("#kindCodeV").val();
		 var risk = $("#riskCodeV").val();
		 for(var i=0;i<KindAddCodeLength;i++){
			 $("#kindCodeV").after("<input type='hidden' value='"+kindMain+"' name='geKind.geKindRelateList["+i+"].id.kindCode'>");
			 $("#riskCodeV").after("<input type='hidden' value='"+risk+"' name='geKind.geKindRelateList["+i+"].id.riskCode'>");
		 }
		var codeType = $("#codeType").val();
		 if(codeType=='0'){
			 if(index<1){
				 alert("�Բ������������һ���޶� ");
				 return;
			 }else{
				 $("div:hidden").remove();
				 $("tr:hidden").remove();
				 $("#frmInput").submit();
			 }
		 }else{
			 $("div:hidden").remove();
			 $("tr:hidden").remove();
			 $("#frmInput").submit(); 
		 }
	}
}
//�������
function doClear(){
	document.getElementById("frmInput").reset();
}
//�������ִ���˫����
function alertNationality(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/searchRiscode/index.jsp","���ִ���" ,"top=100, left=100, width=900,height=600,toolbar=no");
}

function createValuerange(){
	var elements=$("input[id^='du'][id!='duty']");
	var other = $("input[id^='other']");
	var aother = $("input[id^='aother']");
	
	var ttvf = tt.vf.req;//�ǿ�
	var codeType = $("#codeType").val();
	if(codeType=='0'){
		//ttvf.addId('duty');
		$("#createInput").css("display","none");
		$("#createInputT").css("display","none");
		$("#createSelect").css("display","block");
		$("#creatOther").css("display","none");
		//document.getElementById("createSelect").style.display="";
		//alert(document.getElementById("createSelect"));
		tt.vf.req.rmId('newCar','carT');
		if(other.length>0){
			tt.vf.req.rmId('otherDuty');
		}
		for(var j=0;j<other.length;j++){
			tt.vf.req.rmId($(other[j]).attr("id"));
		}
		for(var k=0;k<aother.length;k++){
			tt.vf.req.rmId($(aother[k]).attr("id"));
		}
		//alert(codeType);
	}else if(codeType=='1'){
		//tt.vf.req.addId('newCar');
		//var ttvf = tt.vf.req;//�ǿ�
		$("#createSelect").css("display","none");
		$("#createInputT").css("display","none");
		$("#creatOther").css("display","none");
		$("#createInput").css("display","");
		tt.vf.req.rmId('carT');
		tt.vf.req.rmId('duty');
		if(other.length>0){
			tt.vf.req.rmId('otherDuty');
		}
		for(var i=0;i<elements.length;i++){
			tt.vf.req.rmId($(elements[i]).attr("id"));
		}
		for(var j=0;j<other.length;j++){
			tt.vf.req.rmId($(other[j]).attr("id"));
		}
		for(var k=0;k<aother.length;k++){
			tt.vf.req.rmId($(aother[k]).attr("id"));
		}
		
	}else if(codeType=='2'){
		//var ttvf = tt.vf.req;//�ǿ�
		$("#createSelect").css("display","none");
		$("#createInput").css("display","none");
		$("#creatOther").css("display","none");
		$("#createInputT").css("display","");
		tt.vf.req.rmId('newCar','duty');
		for(var i=0;i<elements.length;i++){
			tt.vf.req.rmId($(elements[i]).attr("id"));
		}
		for(var j=0;j<other.length;j++){
			tt.vf.req.rmId($(other[j]).attr("id"));
		}
		for(var k=0;k<aother.length;k++){
			tt.vf.req.rmId($(aother[k]).attr("id"));
		}
		if(other.length>0){
			tt.vf.req.rmId('otherDuty');
		}
	}else if(codeType=='3'){
		$("#createInput").css("display","none");
		$("#createInputT").css("display","none");
		$("#createSelect").css("display","none");
		$("#creatOther").css("display","block");
		tt.vf.req.rmId('newCar','duty','carT');
		
		for(var i=0;i<elements.length;i++){
			tt.vf.req.rmId($(elements[i]).attr("id"));
		}
	}else if(codeType==''){
	//	var ttvf = tt.vf.req;//�ǿ�
		$("#createSelect").css("display","none");
		$("#createInput").css("display","none");
		$("#createInputT").css("display","none");
		$("#creatOther").css("display","none");
		tt.vf.req.rmId('newCar','duty','carT');
		for(var i=0;i<elements.length;i++){
			tt.vf.req.rmId($(elements[i]).attr("id"));
		}
	}
}
var otherNum = 0;
function insertOther(){
	var dex = $("input[id^='other']").length ;
	if(dex<5){
		var divD = $("#addOther");
		var trT = $("<tr></tr>").appendTo(divD);
		var td = $("<td class='td_head' nowrap >����˵����</td> ").appendTo(trT);
		var tdText = $("<td class='td_body' >  <input type='text'  name='geKind.valuerange' onchange='checkOther(this);' id='other"+otherNum+"'   maxLength='7' /></td>  ").appendTo(trT);
		var td = $("<td class='td_head'  nowrap >����������</td> ").appendTo(trT);
		var tdText = $("<td class='td_body'>  <input type='text'  name='geKind.valuerange'  id='aother"+otherNum+"' onchange='checkOther(this);' maxLength='7'/></td>  ").appendTo(trT);
		var tdButtonD =  $("<td class='td_body' > <input type='button' style='padding-left:10px' onclick='removeMoney(this);' value='�h��' /> </td>").appendTo(trT);
		otherNum++;
	}else{
		alert("������5��");
    	return false ;
	}
}
//���dom
var index = 0;
function insertMoney(){
    var ttvf = tt.vf.req;//�ǿ�
	var dex = $("input[id^='du']").length ;
	var elements=$("input[id^='du'][id!='duty']");
    if(dex<7){
    	var divD = $("#addDiv");
    	var trT = $("<tr></tr>").appendTo(divD);
    	var td = $(" <td class='td_head' nowrap >�����޶</td> ").appendTo(trT);
    	var tdText = $(" <td class='td_body'>  <input type='text' id='du"+ index +"' onblur='fillText("+index+");' name='geKind.valuerange' style='font-style:oblique' value='' maxLength='7' onchange='checkMoney(this);' />  </td>  ").appendTo(trT);
    	var td = $(" <td class='td_head' nowrap >�޶�������</td> ").appendTo(trT);
    	var tdText = $(" <td class='td_body'>  <input type='text'  id='mi"+ index +"' name='geKind.valuerange' style='font-style:oblique'  readonly='readonly' value=''/>  </td>  ").appendTo(trT);
    	var tdButtonD =  $("<td class='td_body' > <input type='button' onclick='removeMoney(this);' value='�h��' /> </td>").appendTo(trT);  //removeCheck("+index+")
    	index++ ;
    }else{
    	alert("������6���޶�");
    	return false ;
    }
}
//���� �ձ���֤ 
function checkKind(){
	var ttvf = tt.vf.req;//�ǿ�
	var elements=$("input[id^='du'][id!='duty']");
	for(var i=0;i<elements.length;i++){
		tt.vf.req.addId($(elements[i]).attr("id"));
	}
}

// ɾ�����ӳ�����
function removeMoney(field){
	--index;
	--otherNum;
	var ttvf = tt.vf.req;//�ǿ�
	var tableObj = $(field).parent().parent();
	$(tableObj).find("input[maxlength=7]").each(function(){
		id = $(this).attr("id");
		ttvf.rmId($(this).attr("id"));
	});
	$(tableObj).empty(); 
}


//  ��֤������1000�ı���
function checkMoney(field){
	if(isNaN($(field).val())){
		alert("������������");
		$(field).attr("value","");
		return false ;
	}
	if($(field).val()%1000!=0 ){ 
		alert("��������Ϊ1000�ı���");
		$(field).attr("value","");
		return false ;
	}
}
/**
 * �ж������޶��Ƿ��ظ� 
 */
function checkNum(){
	var flag = true;
	  var tempCode = [];
	  $("input[id^='du'][id!='duty']").each(
	    function(){
	    	if(tempCode[$(this).attr('value')] == $(this).attr('value') && $(this).attr('value') !=""){
	    		alert("�����޶���ظ�����");
	    		flag = false;
	    		return false;
	    	}else{
	    		tempCode[$(this).attr('value')] = $(this).attr('value');
	    	}
	    }
	  )
	  return flag;
}


// �����ֵ
function fillText(i){
	var num = $("#du"+i).val();
	var numF = num.substring(0,1);
	if(num/10000<1){
		$("#mi"+i).attr("value",num/1000+"ǧ");
	}else if(num/10000>=1&&num/10000<10){
		$("#mi"+i).attr("value",num/10000+"��");
	}else if(num/10000>=10&&num/10000<100){
		$("#mi"+i).attr("value",num/10000+"��");
	}else if(num/10000>=100&&num/10000<1000){
		$("#mi"+i).attr("value",num/10000+"��");
	}
}
/**
 * �ж�����Ƿ��ظ� 
 */
function checkOrderNum(){
	 var riskCode = document.getElementById("riskCodeV").value;
	 var orderno = document.getElementById("orderNo").value;
	 var kindCode = document.getElementById("kindCodeV").value;
	 var kindFlag = document.getElementById("kindFlag").value;
	 var flag = false ;
	 if(kindFlag==''){
		 alert("��ѡ���ձ��־ ");
		 return flag ;
	 }
	   $.ajax({
	  		  type: "POST",
	  		  async:false,
	  		  url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/checkOrderNum.do',
	  		  dataType: 'text',
	  		  data:{"geKind.id.riskCode":riskCode,"orderNo":orderno,"geKind.kindflag":kindFlag,"geKind.id.kindCode":kindCode},
	  		  success: function(backData){
	  			  if(backData == '02'){
	  			     alert("������ѱ�ռ��");
	  			       flag = false ;
	  			  }else{
	  				  flag = true ;
	  			  }
	  		    }
	  		});
	   return flag ;
	   
}
/**
 * ���ӹ�ϵ���� ����ҳ�� 
 */
function addKind(){
	var riskCode = $("#riskCodeV").val();
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/createGeRelate/searchAddKind.jsp?riskCode="+riskCode+"","���Ӹ�����" );
}
/**
 * �ж����ӵĲ��Ƿ���ʾ���� 
 */
function checkMain(){
	//var riskCode = $("#riskCodeV").val();
	// var kindCode = $("#kindCodeV").val();
	var kindFlag = $("#kindFlag").val();
	var flagRelate = $("#flagRelate").val();
	if(kindFlag == '01'){
		$("#flagRelateShow").show();
		if(flagRelate !='' ){
			if(flagRelate=='1'){
				$("#kindDiv").show();
			    $("#buttonShow").show();
			}else{
				$("#kindDiv").hide();
			    $("#buttonShow").hide();
			}
		}
	}else{
		$("#flagRelateShow").hide();
		$("#kindDiv").hide();
	    $("#buttonShow").hide();
	}
}
/**
 * �ж� �ù�ϵ�Ƿ���� 
 */
 function checkRelate(){
	var flag = true ;
	 var KindAddCodeLength = $("input[name$='id.relateKindCode']").length;
	 var kindMain = $("#kindCodeV").val();
	 var risk = $("#riskCodeV").val();
	 var kindAddCodeValue = $("input[name$='id.relateKindCode']");
	 if(KindAddCodeLength>0){
		var  kindAddCodes = '' ;
		for(var i=0;i<KindAddCodeLength;i++){
			kindAddCodes += kindAddCodeValue[i].value+"-" ;
		}
		$.ajax({
			type :"post",
			async:false,
	  		url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/checkRelate.do',
	  		dataType: 'json',
	  	    data:{"kindMain":kindMain,"risk":risk,"kindAddCodes":kindAddCodes},
            success:function(json){
            	if(json.tempList.length<1){
            		flag = true;
            	}else {
	            	var riskCode='';
	            	var kindCode='';
	            	var relationCode ='';
	            	for(var i=0;i<json.tempList.length;i++){
	            		if(i==json.tempList.length-1){
		            		 riskCode+=json.tempList[i].id.riskCode;
		               	     kindCode+=json.tempList[i].id.kindCode;
		               		 relationCode+=json.tempList[i].id.relateKindCode;
	            		}else{
		            		 riskCode+=json.tempList[i].id.riskCode+",";
		            	     kindCode+=json.tempList[i].id.kindCode+",";
		            		 relationCode+=json.tempList[i].id.relateKindCode+",";
	            		}
                    }
	            	 flag = false ;
	                alert("�ظ������ִ���Ϊ  "+riskCode+" �ظ����ձ����Ϊ  "+kindCode+" �ձ��ϵ��  "+relationCode);   
            	}
           }
         });
	 }
	 return flag ;
   }
 
 // У�� ���ձ��б����Ƿ��и����� 
 function checkAddFlag(){
	    var flag = true ;
	    var riskCode = $("#riskCodeV").val();
		var kindCode = $("#kindCodeV").val();
		var kindFlag = $("#kindFlag").val();
		var flagRelate = $("#flagRelate").val();
		if(riskCode==''||kindCode==''){
			alert("���ֻ��ձ���벻��Ϊ��");
			 $("#flagRelate").val(0);
			return false ;
		}
		if(kindFlag=='02'){
			return true ;
		}
		if(flagRelate=='0'){
			$("#kindDiv").hide();
		    $("#buttonShow").hide();
			return true;
		}else if(flagRelate=='1'){
			$("#kindDiv").show();
		    $("#buttonShow").show();
			$.ajax({
				  type: "POST",
		  		  async:false,
		  		  url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/checkAddFlag.do',
		  		  dataType: 'text',
		  		  data:{"geKind.id.riskCode":riskCode,"geKind.id.kindCode":kindCode,"geKind.kindflag":'02'},
		  		  success: function(backData){
		  			  if(backData == '02'){
		  			     alert("����������δ���ø����գ�������Ӹ�����");
		  			     $("#kindFlag").attr("value","02");
		  			     $("#kindDiv").hide();
		  				 $("#buttonShow").hide();
		  				 $("#flagRelateShow").hide();
		  				 $("#flagRelate").val(0);
		  			      flag = false ;
		  			  }else{
		  				  flag =  true ;
		  			  }
		  		    }
			});
		}
		return flag ;
 }
 
 function checkEn(str){
	if(!/^.[A-Za-z]+$/.test(str.value)){
		alert("��������Ӣ���ַ�");
		str.value = '' ;
		return false;
	}
 }
 function checkOther(field){
   var ttvf = tt.vf.req;//�ǿ�
   var check =  $(field).parent().siblings("td").children("input");
   var checkValue = $(field).val();
    if(checkValue !=''){
    	var id = check.attr('id');
    	tt.vf.req.addId(id);
    	tt.vf.req.addId("otherDuty");
    }
 }

 function textAreaMaxLen(field){
		 var iMaxLen = parseInt(field.getAttribute('maxlength'));
		    var iCurLen = field.value.length;
		    if ( field.getAttribute && iCurLen > iMaxLen ){
		    	field.value = field.value.substring(0, iMaxLen);
		    	alert("�������600����");
		    }
	}
 
 
//ͼƬʹ��λ�ò���
 function btnShowone(showImgs)
 		{
 		   $("#"+showImgs).click(function()
 		   {
 			  $("#BgDiv").css({ display:"block",height:$(document).height()});
 		      var yscroll=document.documentElement.scrollTop;
 		      $("#DialogDiv").css("top","100px");
 		      $("#DialogDiv").css("display","block");
 		      $("#showImg").attr("src","${ctx}/global/images/"+showImgs+".jpg");
 		      document.documentElement.scrollTop=0;
 		   });
 		   $("#btnClose").click(function()
 		   {
 		      $("#BgDiv").css("display","none");
 		      $("#DialogDiv").css("display","none");
 		   });
 		}
 function btnClose(){
      $("#BgDiv").css("display","none");
      $("#DialogDiv").css("display","none");
 }
--></script>
</html>