<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/checkIdCard.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
<title>frmCreate.jsp</title>
</head>
<body onload="pageValidate();">
<div class="public_fun_title">�½�������</div>
<form action="${ctx}/business/customerManage/blacklist/addGeBlackList.do" id="frmInput" method="post" target="myFrame">
<table align="center" width="550px"id="productTable">
	<tr>
		<td class="td_head" style="text-align: right;" width="120px" nowrap>��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
		<td class="td_body">
			<input type="text" id="userName" name="geBlackList.userName" style="width:170px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" style="text-align: right;" nowrap>��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
		<td class="td_body"  >
			<select id="sex" name="geBlackList.sex" style="width:100px;">
				<option value="">--��ѡ��--</option>
				<c:forEach items="${sexList}" var="GeCode_sex" step="1" varStatus="status">
						<option value="${GeCode_sex.id.codeCode}">${GeCode_sex.codeCName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" style="text-align: right;" nowrap>�������ڣ�</td>
		<td class="td_body" >
			<input name="geBlackList.birthDay" size="25" id="birthday" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate"  style="width:170px;">
		</td>
	</tr>
	<tr>
		<td class="td_head" style="text-align: right;" nowrap>֤�����ͣ�</td>
		<td class="td_body" >
			<div style="float:left;">
				<select id="identifyType" name="geBlackList.identifyType" style="width:100px;" >
					<option value="">--��ѡ��--</option>
					<c:forEach items="${idTypeList}" var="GeCode_idType" step="1" varStatus="status">
						<option value="${GeCode_idType.id.codeCode}">${GeCode_idType.codeCName}</option>
					</c:forEach>
				</select><span class="talentReqStar">*</span>
			</div>
			<div id="identifyType_tip"></div>
		</td>
	</tr>
	<tr>
		<td class="td_head" style="text-align: right;" nowrap>֤�����룺</td>
		<td class="td_body"  >
			<div style="float:left;">
				<input type="text" id="identifyNumber" name="geBlackList.identifyNumber" style="width:170px;" maxlength="100"><span class="talentReqStar">*</span>
			</div>
			<div id="identifyNumber_tip" style="float: none;"></div>
		</td>
	</tr>
	<!-- 
	<tr>
		<td class="td_head" nowrap>�û����ͣ�</td>
		<td class="td_body">
			<select id="geBlackList.userType" name="geBlackList.userType"  style="width:170px;" >
			<option value="" selected>--��ѡ��--</option>
				<option value="01">�����û�</option>
				<option value="02">��ҵ�û�</option>
			</select>
	</tr>
	 -->
	<tr>
		<td class="td_head" style="text-align: right;" nowrap>ҵ������</td>
		<td class="td_body" >
			<select id="businessArea" name="geBlackList.businessArea"  style="width:170px;" >
				<option value="">--��ѡ��--</option>
				<c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<option value="${bussArea.id.codeCode}">${bussArea.codeCName}</option>
				</c:forEach>
			</select>
	</tr>
	<tr>
		<td class="td_head" style="text-align: right;" nowrap>���������ԭ��</td>
		<td class="td_body">
			<textarea rows="8" cols="40" id="reason" name="geBlackList.reason"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>���� </td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
				</tr>
			</table>
		</td>
	</tr>
</table> 
<input type="hidden" name="geBlackList.userType" value="01">
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
function doCreate(){
	if(checkIdentify("identifyNumber_tip")&tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.add("geBlackList.userName","geBlackList.sex","geBlackList.birthDay",
			"geBlackList.businessArea","geBlackList.reason");
	new tt.LV().set(0,200).add("geBlackList.reason");
}

//errCls: "talentErrMsg",     //������ʾ��Ϣ����ʽ
//tipCls: "talentTipMsg",     //������ʾ��Ϣ����ʽ
//errInputCls: "talentErrInput",  //��֤��ͨ��ʱ����������ʽ
//reqStarCls: "talentReqStar"  // ������������������һ���Ǻŵ���ʽ
function getErrorMsg(msg){
	return "<span class='talentErrMsg'>"+msg+"</span>";
}

function setErrorInput(id,oldCla){
	if(oldCla != ""){
		$("#"+id).removeClass(oldCla).addClass("talentErrInput");
	}else{
		$("#"+id).addClass("talentErrInput");
	}
}

function setOldInput(id,oldCla){
	if(oldCla != ""){
		$("#"+id).removeClass("talentErrInput").addClass(oldCla);
	}else{
		$("#"+id).removeClass("talentErrInput");
	}
}

//��֤֤�����ͺͺ���
function checkIdentify(obj){
	var identifyType = $("#identifyType").val();
	var identifyNumber = $.trim($("#identifyNumber").val());
	var birthday = $("#birthday").val();
	var sex = $("#sex").val();
	if(sex != "1" && sex != "2" ) sex = "";
	if((identifyType == ""&&identifyNumber == "")||(identifyType != ""&&identifyNumber != "")){
		if(identifyType=="01"){
			var checkIdResult = checkIdcard(identifyNumber,birthday.replace(/-/,"").replace(/-/,""),sex);
			if(checkIdResult != "���֤��֤ͨ��!"){
				$("#"+obj).html(getErrorMsg(checkIdResult));
				setErrorInput("identifyNumber","");
				return false;
			}else{
				setOldInput("identifyType","");
				setOldInput("identifyNumber","");
				$("#identifyNumber_tip").html("");
				$("#identifyType_tip").html("");
				return true;
			}
		}else if(identifyType==""){
			setErrorInput("identifyType","");
			setErrorInput("identifyNumber","");
			$("#identifyNumber_tip").html(getErrorMsg("Ϊ�����������!"));
			$("#identifyType_tip").html(getErrorMsg("��ѡ��!"));
			return false;
		}else{
			setOldInput("identifyType","");
			setOldInput("identifyNumber","");
			$("#identifyNumber_tip").html("");
			$("#identifyType_tip").html("");
			return true;
		}
	}else{
		if(identifyType == ""){
			$("#identifyType_tip").html(getErrorMsg("��ѡ��"));
			setErrorInput("identifyType","");
		}else{
			$("#identifyNumber_tip").html(getErrorMsg("����д����֤����Ϣ"));
			setErrorInput("identifyNumber","");
		}
		return false;
	}
}

$(function(){
	$("#identifyNumber").keyup(function(){
		$("#identifyNumber_tip").html("");
		$("#identifyType_tip").html("");
		checkIdentify("identifyNumber_tip");
	});
	$("#identifyNumber").focus(function(){
		$("#identifyNumber_tip").html("");
		$("#identifyType_tip").html("");
		checkIdentify("identifyNumber_tip");
	});
	$("#identifyType").blur(function(){
		checkIdentify("identifyType_tip");
	});
	$("#identifyType").change(function(){
		$("#identifyNumber_tip").html("");
		$("#identifyType_tip").html("");
		checkIdentify("identifyType_tip");
	});
	$("#identifyType").focus(function(){
		$("#identifyNumber_tip").html("");
		$("#identifyType_tip").html("");
	});
	$("#birthday").blur(function(){
		checkIdentify("identifyNumber_tip");
	});
	$("#birthday").focus(function(){
		$("#identifyNumber_tip").html("");
		$("#identifyType_tip").html("");
	});
	$("#sex").change(function(){
		checkIdentify("identifyNumber_tip");
	});
});
$(document).ready(function(){
	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
	var ids = ['des'];
	    	var contents = ['˵��:�ѷ��Ϲ�˾�����������Ŀͻ����뵽��������<br/>'
	    	                + 'ʹ����Ⱥ:������¼��͹�����Ա<br/>'
	    	                + '��������:<br/>'
	    	                + 'ע������:���������ԭ��Ҫ��������������Ժ���Աά��<br/>'];
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
	});
</script>
</html>
