<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-编辑黑名单信息</title>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/checkIdCard.js"></script>
</head>
<body  onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			编辑黑名单信息
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form id="frmInput" action="${ctx}/business/customerManage/blacklist/updateGeBlackList.do" method="post" target="myFrame">
	<table class="table_style" align="center" width="550px" id="productTable">
	<tr>
		<td class="td_head" width="120px" nowrap>姓名：</td>
		<td class="td_body">
			<input type="text" id="userName" name="geBlackList.userName" value="${geBlackList.userName}" style="width:170px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>性别：</td>
		<td class="td_body">
			<select id="sex" name="geBlackList.sex" style="width:100px;">
				<option value="">--请选择--</option>
				<c:forEach items="${sexList}" var="GeCode_sex" step="1" varStatus="status">
					<option value="${GeCode_sex.id.codeCode}" ${GeCode_sex.id.codeCode == geBlackList.sex?"selected":""}>${GeCode_sex.codeCName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>出生日期：</td>
		<td class="td_body">
			<input name="geBlackList.birthDay" size="25" id="birthday" readonly style="width:170px;"  onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate" value="${geBlackList.birthDay }">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>证件类型：</td>
		<td class="td_body">
			<div style="float:left;">
				<select id="identifyType" name="geBlackList.identifyType" style="width:100px;" >
					<option value="">--请选择--</option>
					<c:forEach items="${idTypeList}" var="GeCode_idType" step="1" varStatus="status">
						<option value="${GeCode_idType.id.codeCode}" ${GeCode_idType.id.codeCode == geBlackList.identifyType?"selected":"" }>${GeCode_idType.codeCName}</option>
					</c:forEach>
				</select><span class="talentReqStar">*</span>
			</div>
			<div id="identifyType_tip"></div>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>证件号码：</td>
		<td class="td_body">
			<div style="float:left;">
				<input type="text" id="identifyNumber" name="geBlackList.identifyNumber" value="${geBlackList.identifyNumber}" style="width:170px;" maxlength=40><span class="talentReqStar">*</span>
			</div>
			<div id="identifyNumber_tip" style="float: none;"></div>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>业务领域：</td>
		<td class="td_body">
			<select id="businessArea" name="geBlackList.businessArea" style="width:170px;">
				<option value="">--请选择--</option>
				<c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<option value="${bussArea.id.codeCode}" ${geBlackList.businessArea==bussArea.id.codeCode?"selected":"" }>${bussArea.codeCName}</option>
				</c:forEach>
				<!--<option value="1" ${geBlackList.businessArea=="1"?"selected":"" }>集团</option>
				<option value="2" ${geBlackList.businessArea=="2"?"selected":"" }>财险</option>
				<option value="3" ${geBlackList.businessArea=="3"?"selected":"" }>寿险</option>
				<option value="4" ${geBlackList.businessArea=="4"?"selected":"" }>养老险</option>
				<option value="9" ${geBlackList.businessArea=="9"?"selected":"" }>其他</option>-->
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>加入黑名单原因：</td>
		<td class="td_body">
			<textarea rows="8" cols="40" id="reason" name="geBlackList.reason">${geBlackList.reason}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan=2>
			<table width=300 align="center">
			<tr>
			<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>	
				<acc:showView source="ROLE_B_BLIS_U">
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改</td>
					<td>&nbsp;</td>
				</acc:showView>
				<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>	
									<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>
			</table>
		</td>
	</tr>
	</table>
	<input type="hidden" name="geBlackList.id" id="id" value="${geBlackList.id }">
	<input type="hidden" id="userType" name="geBlackList.userType" value="${geBlackList.userType}">
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
function doUpdate(){
	if(checkIdentify("identifyNumber_tip")&tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function doBack(){
	window.location = "${ctx }/business/customerManage/blacklist/queryGeBlackListForShow.do?geBlackList.id=${geBlackList.id }";
}

function pageValidate(){
	tt.vf.req.addName("geBlackList.userName","geBlackList.sex","geBlackList.birthDay",
			"geBlackList.businessArea","geBlackList.reason");
	new tt.LV().set(0,200).add("geBlackList.reason");
}

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

//验证证件类型和号码
function checkIdentify(obj){
	var identifyType = $("#identifyType").val();
	var identifyNumber = $.trim($("#identifyNumber").val());
	var birthday = $("#birthday").val();
	var sex = $("#sex").val();
	if(sex != "1" && sex != "2" ) sex = "";
	if((identifyType == ""&&identifyNumber == "")||(identifyType != ""&&identifyNumber != "")){
		if(identifyType=="01"){
			var checkIdResult = checkIdcard(identifyNumber,birthday.replace(/-/,"").replace(/-/,""),sex);
			if(checkIdResult != "身份证验证通过!"){
				$("#"+obj).html(getErrorMsg(checkIdResult));
				setErrorInput("identifyNumber","");
				return false;
			}else{
				setOldInput("identifyType","");
				setOldInput("identifyNumber","");
				return true;
			}
		}else if(identifyType==""){
			setErrorInput("identifyType","");
			setErrorInput("identifyNumber","");
			$("#identifyNumber_tip").html(getErrorMsg("为必输项，请输入!"));
			$("#identifyType_tip").html(getErrorMsg("请选择!"));
			return false;
		}else{
			setOldInput("identifyType","");
			setOldInput("identifyNumber","");
			return true;
		}
	}else{
		if(identifyType == ""){
			$("#identifyType_tip").html(getErrorMsg("请选择"));
			setErrorInput("identifyType","");
		}else{
			$("#identifyNumber_tip").html(getErrorMsg("请填写完整证件信息"));
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
</script>
</body>
</html>
