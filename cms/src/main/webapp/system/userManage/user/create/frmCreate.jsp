<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
</head>
<body>
<div class="public_fun_title">新建用户</div>
<div style="height:10px">&nbsp;</div>
<form action="${ctx}/system/userManage/create.do" id="frmInput" method="post" target="myFrame">
<table id="kuang" class="frmCreate_kuang"  width="753px" align="center" cellspacing="0" cellpadding="0" >
<tr>
		<td class="frmCreate_kuang_header" style="text-align:center;border-right:1px solid #dcdcdc;width:436px;">用户基本信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;width:317px;">用户组信息</td>
</tr>
<tr>
	<td valign="top" id="basicInfo" style="border-right:1px solid #dcdcdc;">
		<table align="left" style="line-height:25px;">
			<tr>
				<td class="td_head" width="70px" nowrap>登录账户：</td>
				<td class="td_body">
					<input id="operatorId" name="geOperator.operatorid" onblur="readonblur();" type="text" style="width:130px;" value="" class=required maxlength=20>
					<span id="operatorId_msg"></span>
				</td >	
			</tr>
			<tr>
				<td class="td_head" nowrap>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
				<td  class="td_body">
					<input id="pwd" name="geOperator.pwd" type="password" style="width:130px;" value="" class=required maxlength=16>
					<span id='pwd_msg'></span>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>确认密码：</td>
				<td  class="td_body">
					<input id="pwdAgain" name="pwdAgain"  type="password" style="width:130px;" value="" class=required maxlength=16>
					<span id='pwdAgain_msg'></span>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>用户姓名：</td>
				<td class="td_body" >
					<input id="operatorName" name="geOperator.operatorname" type="text" style="width:130px;" value="" maxlength=20 class=required>
					<span id='operatorName_msg'></span>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
				<td class="td_body">
					<input type="radio" class="no_border_input" name="geOperator.sex" value="1" checked>男
					<input type="radio" class="no_border_input" name="geOperator.sex" value="2">女
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>联系电话：</td>
				<td class="td_body">
					<input id="phone" name="geOperator.phone" type="text" style="width:130px;" value="" maxlength=20>
					<span id='phone_msg'></span>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>电子邮箱：</td>
				<td class="td_body">
					<input id="email" name="geOperator.email" type="text" style="width:130px;" value="" maxlength=40>
					<span id='email_msg'></span>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>用户状态：</td>
				<td class="td_body">
					<input type="radio" class="no_border_input" name="geOperator.state" value="01" checked>可用
					<input type="radio" class="no_border_input" name="geOperator.state" value="02">不可用
				</td>
			</tr>
			<!--<acc:showView source="ROLE_S_GROUP_M_US">
			<tr>
				<td class="td_head" nowrap>加入已有组：</td>
				<td class="td_body" id="comGroup">
					  <input type="radio" class="no_border_input" name="isAddUserToGroup" value="0" checked>不加入
					<input type="radio" class="no_border_input" name="isAddUserToGroup" value="1">加入
				</td>
			</tr>
			</acc:showView>-->
			<tr class="">
				<td class="td_head" nowrap>所属机构：</td>
				<td class="td_body" id="comChecked">
					<div>
						<div style="width:190px;float:left;">
							<input type="text" id="authorityDepartmentManager" value="" style="width:170px;" readonly>
							<input type="hidden" id="authorityid" name="geOperator.deptid" value=""/>
						</div>
						<div style="float:left;">
							<input style="width:90px;" onclick="deptAuthIdQuery();" type="button" value="选择机构" />
					</div>
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><span id='authorityid_msg'></span></td>
			</tr>
		</table>
	</td>
	
	<td valign="top">
		<!--  	<div id="showListLoading"  style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
				 <iframe name="groupLoad" style="width:100%;height:100%"></iframe>
			</div>-->
			<table style="width:100%;" style="text-align:center">
					<tr>
						<td>&nbsp;&nbsp; <select id="groupTypeId" name="geGroup.grouptypeid" onchange="queryGroups();">
														<option value="">--请选择--</option>
														<c:forEach items="${geGrouptypeList}" var="grouptype">
														<option value="${grouptype.grouptypeid}">${grouptype.grouptypename} </option>
														</c:forEach>
													</select>
						</td>
					</tr>
				</table>
				<div id="groupload" style="overflow-y:scroll;height:365px;">
				<table class="public_table1" style="width:300px;"id="groupTable">
					<tr id="title">
						<td class="search_head" width="40px"><input type="checkbox" onclick="checkAll(this)" style="border:0px solid black;"></td>
						<td class="search_head" width="125" nowrap id="groupId">用户组编号</td>
						<td class="search_head" nowrap id="groupName">用户组名称</td>
					</tr>
					<c:forEach items="${geGroupList}" var="geGroup" varStatus="status">
					<tr>
						<td class="search_body_center">
							<input type="checkbox" id="checkbox_roleAll" name="checkbox_roleAll" value="${geGroup.groupid}" style="border:0px solid black;" onClick= "">
						</td>
						<td class="search_body_center" >${geGroup.groupid}</td>
						<td class="search_body_center" >${geGroup.groupname}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
	</td>
	
</tr>
 </table>
<table align="center" height="60px;">
<tr>
	<td>
		<table width="100%" align="center">
			<tr>
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<table id="demo" style="display:none">
	<tr>
		<td class="search_body_center">
			<input type="checkbox" class="checkbox_roleAll" name="checkbox_roleAll" style="border:0px solid black;">
		</td>
		<td class="search_body_center groupIdClass"></td>
		<td class="search_body_center groupNameClass" ></td>
	</tr>
</table>
<input type="hidden" name="geOperator.businessarea" id="businessarea" value="${geOperator.businessarea}"/>
<input type="hidden" name="checkGeGroups" id="group" value=""/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
if(window.navigator.userAgent.indexOf("MSIE")>=1){//如果浏览器为IE
	$("#kuang").css("height",window.parent.document.getElementById('fraLEFT').height-140);
}

//var userManageTree = document.getElementById("userManageTree");
//userManageTree.style.height = document.body.clientHeight-165;

var basicInfo = document.getElementById("basicInfo");
basicInfo.style.height = document.body.clientHeight-165;
function doCreate(){
	if(tt.validate()&&readonblur()){
		var roleid = getCheckedOperators();
		$("#group").val(roleid);
		$("#frmInput").submit();
		
/*	$.ajax({
		cache :false,
		type: "POST",
		url:"${ctx}/system/userManage/create.do",
		//data: $("#frmInput").serialize(),//{"checkGeGroup":roleid},
		data: {"checkGeGroup":roleid}&&$("#frmInput").serialize(),//,"createUserId":$("#createUserId").val()
		dataType:"json",
		success: function(data){
			if(data == "success"){
				if(confirm("新建用户成功,是否继续添加用户?")){
					window.location = "${ctx}/system/userManage/user/create/frmCreate.jsp";
				}else{
					document.getElementById("myFrame").src = "${ctx}/system/userManage/createResult.do";
				}
			}else{
				alert(data);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		}
	}); */
	
	}
}

function doClear(){
	$("#frmInput")[0].reset();
}
</script>
<script type="text/javascript">
//设置显示区域
var operatorId =new tt.Field("","","operatorId").setMsgId("operatorId_msg");
var pwd =new tt.Field("","","pwd").setMsgId("pwd_msg");
var pwdAgain =new tt.Field("","","pwdAgain").setMsgId("pwdAgain_msg");
var operatorName =new tt.Field("","","operatorName").setMsgId("operatorName_msg");

var email =new tt.Field("","","email").setMsgId("email_msg");
var authorityid =new tt.Field("","","authorityDepartmentManager").setMsgId("authorityid_msg");
//非空验证
tt.vf.req.add(operatorId,pwd,pwdAgain,operatorName,email,authorityid);
//针对不同需求的正则表达式验证
new tt.CV().add(pwdAgain).set('v', "==", pwd,false);
new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"由6-16位数字.字母.'_'或'-'组成").add(pwd);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"只输入数字、字母和下划线!").add(operatorId);
var phone =new tt.Field("","","phone").setMsgId("phone_msg");
new tt.RV().set(new RegExp(/^(\d{11})$|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/), "如010-12345678或11位手机号").add(phone); 
new tt.RV().set(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/),"合法格式（email@email.email）").add(email);
</script>
<script type="text/javascript">

function deptAuthIdQuery(){
	window.open('${ctx}/system/userManage/geUserDept.do','机构','top=100, left=500, width=400,height=500,toolbar=no');
}
//选择全部
function checkAll(obj){
	var checkArray = document.getElementsByName("checkbox_roleAll");
	for(var i = 0; i < checkArray.length; i++){
		checkArray[i].checked = obj.checked;
	}
}

//处理选择用户组字符串
function getCheckedOperators(){
	var checkedOperators = "";
	var checkArray = document.getElementsByName("checkbox_roleAll");
	for(var i = 0; i < checkArray.length; i++){
		if(checkArray[i].checked == true){
			if(checkedOperators == ""){
				checkedOperators = checkArray[i].value;
			}else{
				checkedOperators += "," + checkArray[i].value;
			}
		}
	}
	return checkedOperators;
}

var bol=false;
function readonblur(){
			var operatorId = $("#operatorId").val();
			$.ajax({
				url : "${ctx }/system/userManage/getOperatorbyId.do",
				data : {
					"geOperator.operatorid" : operatorId
				},//传入参数
				type : 'POST',
				dataType : 'json',
				error : function() {
				},
				cache :false,
				success : function(data) {
					if (data.resultFlag == "success") {
						//$("#operatorId").val("");
						$("#coreProductCodeContent").remove();
						$("#operatorId").parent().append("<span id='coreProductCodeContent'><span class='talentErrMsg'>该员工编号已存在!</span></span>")
						return bol;
					} else {
						$("#coreProductCodeContent").remove();
						bol=true;
					}
				}
			});
			return bol;
}

function queryGroups(){
	$.ajax({
		cache :false,
		type: "POST",
		url:"${ctx}/system/userManage/queryGroups.do",
		data: {"grouptypeid":$("#groupTypeId").val()},
		dataType:"json",
		success: function(data){
			if(data == "false"){
				alert("查询失败");
			}else{
				$("#groupTable").find("tr:gt(0)").remove();
				$.each(data, function(index, item){
					var num = parseInt(index+1);
					var row = $("#demo").find("tr:first").clone();
					row.find(".checkbox_roleAll").val(item.groupid);
					row.find(".groupIdClass").text(item.groupid);
					row.find(".groupNameClass").text(item.groupname);
					$("#groupTable").append(row);
				});
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		}
	}); 
}
var ids = ['operatorId'];
var contents = ['用户唯一标识'];
for ( var i = 0 ; i < ids.length ; i++ ){
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
$(document).ready(function(){
	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
	var ids = ['des'];
	    	var contents = ['说明:后台登录用户<br/>'
	    	                + '使用人群:用户管理人员<br/>'
	    	                + '配置条件:要先配置需要加入的用户组（如果已有需要的用户组则不用）<br/>'
	    	                + '注意事项:要想登录后有权限需要把用户加入相应的用户组中<br/>'];
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
									tip:true,//控制左侧尖角是否出现
									padding :10
								}
							});
	        	}
	});
</script>
</html>
