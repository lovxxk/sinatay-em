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
	font-family:宋体;
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
新建险别<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>
<form action="${ctx}/business/cmpProductManage/riskAndKindManage/frmCreate.do" id="frmInput" method="post" target="myFrame">
<div id="BgDiv" ></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;cursor:pointer;">关&nbsp;闭</a></h2>
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
		<td class="td_head" width="" nowrap>险种代码：</td>
		<td class="td_body">
			<input id="riskCodeV" type="text" name="geKind.id.riskCode" onchange="checkMain();" style="width:170px;" maxlength=80  readonly="readonly" >
			<input type="button" onclick="alertNationality();" value="险种选择">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>险别代码：</td>
		<td class="td_body" >
			<input type="text" name="geKind.id.kindCode" id="kindCodeV" style="width:170px;" style="ime-mode:disabled" maxlength="6" onchange="checkMain();">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>险别标志：</td>
		<td class="td_body" >
			<select id="kindFlag" name="geKind.kindflag" style="width:170px;" onchange="checkMain();">
					<option value="">--请选择--</option> 
					<option value="01"/>主险 </option>
					<option value="02"/>附加险 </option>
			</select>
		</td>
	</tr>
	<tr style="display:none" id="flagRelateShow">
		<td class="td_head" nowrap>是否配置险别维护关系：</td>
		<td class="td_body" >
			<select name="flagRelate" id="flagRelate" style="width:170px;" onchange="checkAddFlag();">
					<option value="1" />是</option>
					<option value="0" selected="selected"/>否</option>
			</select>
		</td>
	</tr>
     <tr>
		<td class="td_head" width="" nowrap>险别中文名称：</td>
		<td class="td_body">
			<input type="text" id="kindCName" name="geKind.kindCName" style="width:170px;" maxlength=12  >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="" nowrap>险别英文名称：</td>
		<td class="td_body">
			<input type="text" id="kindEName" name="geKind.kindEName" style="width:170px;" maxlength=12 onchange="checkEn(this);">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>险别繁体名称：</td>
		<td class="td_body" >
			<input type="text" name="geKind.kindtname" style="width:170px;" maxlength=12 >
		</td>
	</tr>
	<!--<tr>
		<td class="td_head" nowrap>险别排序：</td>
		<td class="td_body" >
			<input type="text" id="orderNo"  name="orderNo" style="width:170px;" maxlength=6 onchange="checkOrderNum();">
		</td>
	</tr>
	-->
	<tr>
		<td class="td_head" nowrap>是否允许投保不计免赔：</td>
		<td class="td_body" >
			<select name="geKind.nodeductflag" style="width:170px;">
					<option value="">--请选择--</option> 
					<option value="0"/>不能投保不计免赔</option>
					<option value="1"/>可以投保不计免赔</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>是否有效：</td>
		<td class="td_body" >
			<select name="geKind.validInd" style="width:170px;">
					<option value="">--请选择--</option> 
					<option value="0"/>无效</option>
					<option value="1" selected="selected"/>有效</option>
			</select>
		</td>
	</tr>
	<!--
	<tr>
		<td class="td_head" nowrap>套餐配置显示配置：</td>
		<td class="td_body" >
			<select name="geKind.isComboFlag" style="width:170px;">
					<option value="">--请选择--</option> 
					<option value="1"/>用于套餐配置</option>
					<option value="0"/>用于增加定制自由套餐</option>
			</select>
		</td>
	</tr>
	-->
	<tr>
		<td class="td_head" nowrap>险别缩写：</td>
		<td class="td_body" >
		    <input type="hidden" name="geKind.isComboFlag" value="1"/>
			<input type="text" name="geKind.abbreviation" style="width:170px;" maxlength=3>
		</td>
	</tr>
	<tr>
		<td class="td_head"  style="cursor:pointer;" nowrap><a href="#" title="解释特性描述" style="cursor:pointer;vertical-align:middle;" onclick="btnShowone('memo');" id="memo"><img  src="${ctx}/global/images/help.jpg" border="0" /></a>特性描述：</td>
		<td class="td_body"  >
			<textarea rows="8" cols="40" id="kindDescription" name="geKind.kindDescription" maxlength="600" onkeyup="textAreaMaxLen(this);"></textarea>
		</td>
	</tr>
	<tr>
	<td colspan="2">  
			<div class="frmCreate_kuang" style="margin-left:150px;width:250px;margin-top:10px; display:none;" id="kindDiv" >
			<div class="frmCreate_kuang_header">附加险代码<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
			<div style="padding-top:15px;">
			<div><table id="productCallBack"></table></div>
			<div class="frmCreate_kuang" style="width:250px;">
			<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
				<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
				<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">展开查看更多</a></span>
				<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
				<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">收起</a></span>
				<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
			</div>
			<div id="productInfo" style="padding-top:15px;clear:both;display: none">
			<div><table id="productCallBacktwo"></table></div>
			</div></div>
			</div></div>
			<div id="buttonShow" style="margin-left:150px;padding-top: 10px;text-align: left;display:none;"><input type="button" value="增加附加险" onclick="addKind();" ></div>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>计算类别：</td>
		<td class="td_body">
			<select name="geKind.codeType" style="width:170px;" id="codeType" onchange="createValuerange();">
					<option value="">--请选择--</option> 
					<option value="0"/>取值范围里取 </option>
					<option value="1"/>同新车购置价 </option>
					<option value="2"/>同实际价值 </option>
					<option value="3"/>其它</option>
			</select>
		</td>
	</tr>

	<tr style="display:none;" id="createInput" >
	  <td class="td_head" nowrap>&nbsp;&nbsp;新车购置价：</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" readonly="readonly" style="width:170px;" value="同新车购置价" id="newCar">
	  </td>
	</tr>
    <tr style="display:none" id="createInputT">
	  <td class="td_head" nowrap>同车辆实际价值：</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" readonly="readonly"  style="width:170px;" value="同车辆实际价值" id="carT">
	  </td>
	</tr>

	</table>
	</td>
	</tr>
	
		<!-- -----------------------    隐藏与显示  开始 ------------------------------------- -->
	 <tr >
	  <td colspan="4">
	  <div id="createSelect" style="display:none">
	   <div  class="frmCreate_kuang" style="width:547px;margin-top:15px;padding-bottom: 15px;">
	  <div class="frmCreate_kuang_header"><a href="#" title="设置取值范围展示图" style="cursor:pointer;vertical-align:middle;" onclick="btnShowone('setPageValue');" id="setPageValue"><img  src="${ctx}/global/images/help.jpg" border="0" /></a>设置取值范围<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
	  <div style="padding-left:3px; padding-top:15px;">
	  <table id="addDiv">
		<tr>
			<td class="td_head" nowrap >责任描述：</td>
			<td class="td_body">
			  <input type="text" name="geKind.valuerange" id="duty" maxlength="10"/>
			</td> 
			<td class="td_body"  style="padding-left:50px">
			 <input type="button" value="增加限额" onclick="insertMoney(); checkKind();"/>
			</td> 
		</tr>
     	</table>
  
     </div>
	</div>
	</div>
     </td>
	</tr>
   <%//----------------------    其它  ---------------------------------------------------- %>
	 <tr >
	  <td colspan="4">
	  <div id="creatOther" style="display:none">
	   <div  class="frmCreate_kuang" style="width:547px;margin-top:15px;padding-bottom: 15px;">
	  <div class="frmCreate_kuang_header">其它<span  style="color:#FF9000;font-weight:bold;"></span></div>
	  <div style="padding-left:3px; padding-top:15px;">
	  <table id="addOther">
		<tr>
			<td class="td_head" nowrap >责任描述：</td>
			<td class="td_body">
			  <input type="text" name="geKind.valuerange" id="otherDuty" maxlength="10"/>
			</td> 
			<td class="td_body"  style="padding-left:60px">
			 <input type="button" value="增加" onclick="insertOther();"/>
			</td> 
		</tr>
     	</table>
     </div>
	</div>
	</div>
     </td>
	</tr>
	<%//-------------------------------- 结束 ------------------------------------------ %>
		<!-- -----------------------    隐藏与显示   结束    ------------------------------------- -->
			
<!--	<tr height=15><td></td></tr> -->
	<tr>
		<td colspan=4>
		<div>
			<table width="64" align="center">
			<tr>
				<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
				<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
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
//------------------- 开始处理验证 ----------------------------------------

$(document).ready(function(){
	//pop提示信息
	var ids = ['kindDescription','codeType'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['用于前端介绍险别详细信息','用于取出责任限额或保险金额'];
		
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
					tip:true//控制左侧尖角是否出现
					//name: 'green' 
				} 
			}); 
    	}
//pop提示信息结束
})


function pageValidate(){
	   var ttvf = tt.vf.req;//非空
		var elements=$("input[id^='du'][id!='duty']");
		for(var i=0;i<elements.length;i++){
			tt.vf.req.addId($(elements[i]).attr("id"));
		}
	   ttvf.addId('duty', 'newCar','carT');
	   tt.vf.req.add("geKind.id.riskCode","geKind.id.kindCode","geKind.combinekindcode"
			,"geKind.kindCName","geKind.kindflag"
			,"geKind.codeType","geKind.abbreviation"
			,"geKind.validInd","geKind.nodeductflag","geKind.kindDescription"); //验证非空 //,"geKind.nodeductflag"
    // tt.vf.num.add("orderNo");  //验证数字
    var testCName = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "对不起，只能输入汉字！");
    testCName.add("geKind.kindCName");//验证汉字
    var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "只能输入数字或字母"); 
    ttNumAndCharacter.add("geKind.id.kindCode");//验证数字或英文replace(/[^\d]/g,''))" 
   // var ttNumOrder = new tt.RV().set(new RegExp( "^\\d+$" ),"对不起只能输入正整数");
   // ttNumOrder.add("orderNo");
    

    var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：新建险别。<br/>使用人群：车险产品管理配置人员。<br/>配置条件：险别配合险种使用。<br/>注意事项：'];
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
					tip:true,//控制左侧尖角是否出现
					padding :10
				}
			});
    	}
}
//----------------- 处理验证结束 --------------------------
//创建险别

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
				 alert("对不起您必须添加一个限额 ");
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
//清空输入
function doClear(){
	document.getElementById("frmInput").reset();
}
//处理险种代码双击域
function alertNationality(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/searchRiscode/index.jsp","险种代码" ,"top=100, left=100, width=900,height=600,toolbar=no");
}

function createValuerange(){
	var elements=$("input[id^='du'][id!='duty']");
	var other = $("input[id^='other']");
	var aother = $("input[id^='aother']");
	
	var ttvf = tt.vf.req;//非空
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
		//var ttvf = tt.vf.req;//非空
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
		//var ttvf = tt.vf.req;//非空
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
	//	var ttvf = tt.vf.req;//非空
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
		var td = $("<td class='td_head' nowrap >责任说明：</td> ").appendTo(trT);
		var tdText = $("<td class='td_body' >  <input type='text'  name='geKind.valuerange' onchange='checkOther(this);' id='other"+otherNum+"'   maxLength='7' /></td>  ").appendTo(trT);
		var td = $("<td class='td_head'  nowrap >责任描述：</td> ").appendTo(trT);
		var tdText = $("<td class='td_body'>  <input type='text'  name='geKind.valuerange'  id='aother"+otherNum+"' onchange='checkOther(this);' maxLength='7'/></td>  ").appendTo(trT);
		var tdButtonD =  $("<td class='td_body' > <input type='button' style='padding-left:10px' onclick='removeMoney(this);' value='刪除' /> </td>").appendTo(trT);
		otherNum++;
	}else{
		alert("最多添加5对");
    	return false ;
	}
}
//添加dom
var index = 0;
function insertMoney(){
    var ttvf = tt.vf.req;//非空
	var dex = $("input[id^='du']").length ;
	var elements=$("input[id^='du'][id!='duty']");
    if(dex<7){
    	var divD = $("#addDiv");
    	var trT = $("<tr></tr>").appendTo(divD);
    	var td = $(" <td class='td_head' nowrap >责任限额：</td> ").appendTo(trT);
    	var tdText = $(" <td class='td_body'>  <input type='text' id='du"+ index +"' onblur='fillText("+index+");' name='geKind.valuerange' style='font-style:oblique' value='' maxLength='7' onchange='checkMoney(this);' />  </td>  ").appendTo(trT);
    	var td = $(" <td class='td_head' nowrap >限额描述：</td> ").appendTo(trT);
    	var tdText = $(" <td class='td_body'>  <input type='text'  id='mi"+ index +"' name='geKind.valuerange' style='font-style:oblique'  readonly='readonly' value=''/>  </td>  ").appendTo(trT);
    	var tdButtonD =  $("<td class='td_body' > <input type='button' onclick='removeMoney(this);' value='刪除' /> </td>").appendTo(trT);  //removeCheck("+index+")
    	index++ ;
    }else{
    	alert("最多添加6个限额");
    	return false ;
    }
}
//增加 险别验证 
function checkKind(){
	var ttvf = tt.vf.req;//非空
	var elements=$("input[id^='du'][id!='duty']");
	for(var i=0;i<elements.length;i++){
		tt.vf.req.addId($(elements[i]).attr("id"));
	}
}

// 删除增加出来的
function removeMoney(field){
	--index;
	--otherNum;
	var ttvf = tt.vf.req;//非空
	var tableObj = $(field).parent().parent();
	$(tableObj).find("input[maxlength=7]").each(function(){
		id = $(this).attr("id");
		ttvf.rmId($(this).attr("id"));
	});
	$(tableObj).empty(); 
}


//  验证数字与1000的倍数
function checkMoney(field){
	if(isNaN($(field).val())){
		alert("必须输入数字");
		$(field).attr("value","");
		return false ;
	}
	if($(field).val()%1000!=0 ){ 
		alert("必须输入为1000的倍数");
		$(field).attr("value","");
		return false ;
	}
}
/**
 * 判断责任限额是否重复 
 */
function checkNum(){
	var flag = true;
	  var tempCode = [];
	  $("input[id^='du'][id!='duty']").each(
	    function(){
	    	if(tempCode[$(this).attr('value')] == $(this).attr('value') && $(this).attr('value') !=""){
	    		alert("责任限额不能重复请检查");
	    		flag = false;
	    		return false;
	    	}else{
	    		tempCode[$(this).attr('value')] = $(this).attr('value');
	    	}
	    }
	  )
	  return flag;
}


// 填充数值
function fillText(i){
	var num = $("#du"+i).val();
	var numF = num.substring(0,1);
	if(num/10000<1){
		$("#mi"+i).attr("value",num/1000+"千");
	}else if(num/10000>=1&&num/10000<10){
		$("#mi"+i).attr("value",num/10000+"万");
	}else if(num/10000>=10&&num/10000<100){
		$("#mi"+i).attr("value",num/10000+"万");
	}else if(num/10000>=100&&num/10000<1000){
		$("#mi"+i).attr("value",num/10000+"万");
	}
}
/**
 * 判断序号是否重复 
 */
function checkOrderNum(){
	 var riskCode = document.getElementById("riskCodeV").value;
	 var orderno = document.getElementById("orderNo").value;
	 var kindCode = document.getElementById("kindCodeV").value;
	 var kindFlag = document.getElementById("kindFlag").value;
	 var flag = false ;
	 if(kindFlag==''){
		 alert("请选择险别标志 ");
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
	  			     alert("该序号已被占用");
	  			       flag = false ;
	  			  }else{
	  				  flag = true ;
	  			  }
	  		    }
	  		});
	   return flag ;
	   
}
/**
 * 增加关系代码 弹出页面 
 */
function addKind(){
	var riskCode = $("#riskCodeV").val();
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/createGeRelate/searchAddKind.jsp?riskCode="+riskCode+"","增加附加险" );
}
/**
 * 判断增加的层是否显示出来 
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
 * 判断 该关系是否存在 
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
	                alert("重复的险种代码为  "+riskCode+" 重复的险别代码为  "+kindCode+" 险别关系码  "+relationCode);   
            	}
           }
         });
	 }
	 return flag ;
   }
 
 // 校验 该险别列表中是否有附加险 
 function checkAddFlag(){
	    var flag = true ;
	    var riskCode = $("#riskCodeV").val();
		var kindCode = $("#kindCodeV").val();
		var kindFlag = $("#kindFlag").val();
		var flagRelate = $("#flagRelate").val();
		if(riskCode==''||kindCode==''){
			alert("险种或险别代码不能为空");
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
		  			     alert("该险种下暂未配置附加险，请先添加附加险");
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
		alert("请您输入英文字符");
		str.value = '' ;
		return false;
	}
 }
 function checkOther(field){
   var ttvf = tt.vf.req;//非空
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
		    	alert("最多输入600个字");
		    }
	}
 
 
//图片使用位置操作
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