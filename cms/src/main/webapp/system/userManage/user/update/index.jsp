<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:set var="GeOperator" value="${requestScope.GeOperatorForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<title>电子商务后台管理系统-编辑用户信息</title>
</head>
<body>
<form action="${ctx}/system/userManage/updateGeOperator.do" id="frmInput" method="post" target="myFrame">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			编辑用户
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:10px">&nbsp;</div>
<table id="kuang" class="frmCreate_kuang" width="753" align="center" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="frmCreate_kuang_header" style="text-align:center;width:436px;border-right:1px solid #dcdcdc;">用户基本信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;width:317px;">所属用户组信息</td>
	</tr>
	<tr>
		<td valign="top" id="basicInfo" style="border-right:1px solid #dcdcdc;">
			<table align="left" style="line-height:25px;">
				<tr>
					<td class="td_head" width="70px" nowrap>登录账户：</td>
					<td class="td_body">
					<div id='userId' style="width:90">
						${geOperator.operatorid}
						<input type="hidden" id="operatorId" name="geOperator.operatorid" type="text" value="${geOperator.operatorid}"  class=required maxlength=20 readonly>
					</div>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="70px" nowrap>用户姓名：</td>
					<td class="td_body" >
						<input id="operatorName" name="geOperator.operatorname" type="text" value="${geOperator.operatorname}"  style="width:130px;" maxlength=20 class=required>
						<span id='operatorName_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="70px" nowrap>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
					<td class="td_body">
						<input type="radio" class="no_border_input" name="geOperator.sex" value="1" ${geOperator.sex == '1'?'checked':''}>男
						<input type="radio" class="no_border_input" name="geOperator.sex" value="2" ${geOperator.sex == '2'?'checked':''}>女
					</td>
				</tr>
				<tr>
					<td class="td_head" width="70px" nowrap>联系电话：</td>
					<td class="td_body" >
						<input id="phone" name="geOperator.phone" type="text" value="${geOperator.phone}"  style="width:130px;" maxlength=20>
						<span id='phone_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="70px" nowrap>电子邮箱：</td>
					<td class="td_body" >
						<input id="email" name="geOperator.email" type="text"  value="${geOperator.email}"  style="width:130px;" maxlength=40>
						<span id='email_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="70px" nowrap>用户状态：</td>
					<td class="td_body">
						<input type="radio" class="no_border_input" name="geOperator.state" value="01" <c:if test="${geOperator.state eq '01'}"> checked</c:if>>可用
						<input type="radio" class="no_border_input" name="geOperator.state" value="02" <c:if test="${geOperator.state eq '02'}"> checked</c:if>>不可用
					</td>
				</tr>
				<tr>
					<td class="td_head" nowrap>所属机构：</td>
					<td class="td_body" id="comChecked">
					
							<div style="width:149px;float:left;">
								<input type="text" id="authorityDepartmentManager" value="${cityOld}" style="width:130px;" readonly>
								<input type="hidden" id="authorityid" name="geOperator.deptid" value="${geOperator.deptid}"/>
							</div>
							<div style="float:left;">
								<input style="width:100px;" onclick="deptAuthIdQuery();" type="button" value="选择机构…" />
						</div>
					<span id='authorityid_msg'></span>
				</td>
				</tr>
			</table>
		</td>
		<td valign="top">
			<!-- <div id="showListLoading" style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
			</div>
			<div id="userManageTree" style="width:100%;height:420px;"></div> -->
			<div id="groupload" style="overflow-y:scroll;height:365px">
				<table class="public_table1" style="width:300;"id="groupTable">
					<tr id="title">
						<td class="search_head" width="40px"><input type="checkbox" onclick="checkAll(this)" style="border:0px solid black;"></td>
						<td class="search_head" width="125" nowrap id="groupId">用户组编号</td>
						<td class="search_head" nowrap id="groupName">用户组名称</td>
					</tr>
					<c:forEach items="${geGroupList}" var="geGroup" varStatus="status">
					<tr>
						<td class="search_body_center">
						 	<c:forEach items="${geGroupUserList}" var="geGroupUser" step="1" varStatus="status"> 
								<c:if test="${geGroup.groupid==geGroupUser.GROUPID }">
									<c:set var="flag" value="1"></c:set>
									<input type="checkbox" id="checkbox_roleAll" name="checkbox_roleAll" value="${geGroup.groupid}" style="border:0px solid black;" checked>
								</c:if>
							 </c:forEach>
							 <c:if test="${flag != '1' }">
							 	<input type="checkbox" id="checkbox_roleAll" name="checkbox_roleAll" value="${geGroup.groupid}" style="border:0px solid black;">
							 </c:if>
							 <c:set var="flag" value="0"></c:set> 
						</td>
						<td class="search_body_center">${geGroup.groupid}</td>
						<td class="search_body_center">${geGroup.groupname}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</td>
	</tr>
</table>
<table align="center" style="width:100%;line-height:25px;">
	<tr height="60px">
		<td colspan="2">
			<table width="100" align="center">
				<tr>
					<td id="updateButtons" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>
					<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改</td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>

				</tr>
			</table>
		</td>
	</tr>
</table>	
<!-- 获取加载时的group的值 -->
<c:forEach items="${geGroupUserList}" var="geGroupUser">
	<c:forEach items="${geGroupList}" var="geGroup">
		<c:if test="${geGroup.groupid==geGroupUser.GROUPID }">
		<c:set var="flag" value="1"></c:set>
		<input type="checkbox" id="checkbox_roleAlls" name="checkbox_roleAlls" value="${geGroup.groupid}" style="display:none" checked>
		</c:if>
	</c:forEach>
</c:forEach>					 			 
<input type="hidden" name="geOperator.businessarea" id="businessarea" value="${geOperator.businessarea }"/>
<input type="hidden" id="deptid" value="${geOperator.deptid}"/>
<input type="hidden" name="checkGeGroups" id="group" value=""/>
<input type="hidden" name="oldCheckGeGroup" id="group1" value=""/>
<input type="hidden" name="geOperator.flag" id="flag" value="${geOperator.flag}"/>
</form>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
kuang.style.height = document.body.clientHeight-140;

if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

var userManageTrees = document.getElementById("userManageTree");
userManageTrees = document.body.clientHeight-165;

var basicInfo = document.getElementById("basicInfo");
basicInfo.style.height = 400px;

var tree=new dhtmlXTreeObject("userManageTree","100%","100%",0); 
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.setOnClickHandler(tonclick);
tree.loadXML("${ctx}/system/userManage/operatorManageDeptTree.do?id=0",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);
function tonclick(id){
	if(tree.hasChildren(id) != 0){
		noStyle(id);
		if(tree.getOpenState(id) == 1){
			tree.closeItem(id);
		}else{
			tree.openItem(id);
		}
		return false;
	}else{
		var level = tree.getLevel(id);
		changeStyle(id);
		$("#businessarea").val(id.substring(0,1));
		$("#dep_id").val(id);
		
		var comName = "";
		var pId = id;
		for(var i = level; i > 0; i--){
			if(i == level){
				comName = tree.getItemText(pId);
			}else{
				comName = tree.getItemText(pId)+"/" + comName;
			}
			var pId = tree.getParentId(pId);
		}
		$("#comChecked").html(comName);
		return false;
	}
}

function changeStyle(itemId){
	var idArr = tree.getAllSubItems('0').split(",");
	for(var i = 0; i < idArr.length; i++){
		tree.setItemStyle(idArr[i],"background:none;color:black;font-weight:normal;");
	}
	tree.setItemStyle('0',"background:none;color:black;font-weight:normal;");
	tree.setItemStyle(itemId,"background:#819FF7;color:white;font-weight:bold;");
}

function noStyle(itemId){
	tree.setItemStyle(itemId,"background:none;color:black;font-weight:normal;");
}

function loadOver(sIdNow){
	$("#showListLoading").hide();
}
</script>
<script type="text/javascript">

function doBack(){
	window.location.href="${ctx}/system/userManage/queryGeOperatorForDetail.do?geOperator.operatorid=${geOperator.operatorid}";
}

function doUpdate(){
	if(tt.validate()){	
		var roleid = getCheckedOperators();
		var oldroleid=getCheckedOperat();
		$("#group").val(roleid);
		$("#group1").val(oldroleid);
		$("#frmInput").submit();
	}	
}

function doClear(){

	$("#frmInput")[0].reset();
	var deptids= $("#deptid").val();
	$("#authorityid").val(deptids);
}

	//设置显示区域
	var pwd =new tt.Field("","","pwd").setMsgId("pwd_msg");
	var pwdAgain =new tt.Field("","","pwdAgain").setMsgId("pwdAgain_msg");
	var operatorName =new tt.Field("","","operatorName").setMsgId("operatorName_msg");
	var email =new tt.Field("","","email").setMsgId("email_msg");
	var authorityid =new tt.Field("","","authorityDepartmentManager").setMsgId("authorityid_msg");
	//非空验证
	tt.vf.req.add(pwd,pwdAgain,operatorName,email,authorityid);
	//针对不同需求的正则表达式验证
	new tt.CV().add(pwdAgain).set('v', "==", pwd);
	new tt.RV().set(new RegExp(/^\w{6,}$/),"不能少于6位!").add(pwd);
	var phone =new tt.Field("","","phone").setMsgId("phone_msg");
	new tt.RV().set(new RegExp(/^(\d{11})$|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/), "如010-12345678或11位手机号").add(phone); 
	new tt.RV().set(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/),"合法格式（email@email.email）").add(email);


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

	//处理选择新用户组字符串
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
	
	//处理选择原用户组字符串
	function getCheckedOperat(){
		var checkedOperators = "";
		var checkArray = document.getElementsByName("checkbox_roleAlls");
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
	
	var ids = ['userId'];
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
</script>
</html>
