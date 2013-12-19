<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript"  src="${ctx}/business/customerManage/party/thirdParterInfo/third.js"></script>
 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
</head>
<body>
<div class="public_fun_title" style="width:980px">
新建供应商<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>
<%//隐藏域表格Start %>
<table  id="tableInfoHidden" width="100%" style="display: none;">
<tr>
	<td>
		<table width="100%" style="border-top:1px solid #E3E3E3;margin-left: 20px;">
		<tr><td colspan="5"><span id="count" style="font-weight: bold;">联系人1</span></td></tr>
		<tr>
			<td width="6%" >姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
			<td width="46%"><input name="userName" type="text" maxlength="10"/>&nbsp;</td>
			<td width="5%" align="right">证件类型：</td>
			<td width="45%" colspan ="2">
				<select name="identifyType" style="width:130px;">
					<s:if test="geCodeIdentifyTypeList!=null">
						<s:iterator value="geCodeIdentifyTypeList" var="geCodeIdentifyType">
						<s:if test="#geCodeIdentifyType.validInd==1">
						<option value="<s:property value="#geCodeIdentifyType.id.codeCode"/>"><s:property value="#geCodeIdentifyType.codeCName"/></option>
						</s:if>
						</s:iterator>							
					</s:if>
				</select>
			</td>
		</tr>
		
		<tr>
			<td style="text-align: right;">证件号码：</td>
			<td>
			<input type="text" name="identifyNumber" maxlength="20" onkeyup="validateIDNO(this);" onfocus="validateIDNO(this);">
			</td>
			
			<td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
			<td colspan ="2">
				<select name="sex" style="width:130px;">
				<s:if test="geCodeSexList!=null">
					<s:iterator value="geCodeSexList" var="geCodeSex">
					<option value="<s:property value="#geCodeSex.id.codeCode"/>"><s:property value="#geCodeSex.codeCName"/></option>
					</s:iterator>
				</s:if>
				</select>
			</td>
		</tr>
		
		<tr>
			<td style="text-align: right;">出生日期：</td>
		<td>
			<input type="text" name="birthday" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate"/>
		</td>
		<td>手机号码：</td>
			<td colspan ="2"><input type="text" name="telePhone" maxlength="11"></td>
		</tr>
		<tr>
			<td>电子邮箱：</td>
			<td ><input type="text" name="email" maxlength="35" ></td>
		</tr>
		<tr>
			<td>联系地址：</td>
			<td colspan='4'>
				<textarea   name="contactAddress" maxlength="150" cols="83" onkeyup='textAreaMaxLen(this);' rows="3"></textarea>
			</td>
		</tr>
		<tr><td colspan="4" style="padding-left:10px;"><input  type='button' value='删除' onclick='deleteTable(this);'/></td></tr>
		</table>
	</td>
</tr>
</table>
<%//隐藏域表格End %>


<form action="${ctx}/party/addThirdParterInfoAndThirdParterContact.do" id="frmInput" method="post" target="myFrame">
<div class="table_style" align="center" style="display: inline;overflow: auto; ">
<table class="table_style" align="center" width="800px" id="productTable">


	
	<tr>
		<td class="td_head" width="20%" nowrap>公司名称：</td>
		<td class="td_body" width="80%" ><%//以前宽度是170 %>
			<input type="text" id="thirdParterName" name="geThirdParterInfo.thirdParterName" style="width:237px;" maxlength=200 onkeyup="countThirdParterName()" onkeypress="countThirdParterName()" onblur="countThirdParterName();" onkeyup="countThirdParterName();" >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司网址：</td>
		<td class="td_body" >
			<input type="text" id="url" name="geThirdParterInfo.url"   style="width:237px;" maxlength=180 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司电子邮箱：</td>
		<td class="td_body" >
			<input type="text" id="companyEmail" name="geThirdParterInfo.email" style="width:237px;" maxlength=40 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司电话：</td>
		<td class="td_body"  >
			<input type="text" id="companyPhone" name="geThirdParterInfo.companyPhone" style="width:237px;" maxlength=13 >
		</td>
	</tr>
	<tr>
		<td class="td_head" style="vertical-align: top;" nowrap>公司地址：</td>
		<td class="td_body" colspan="2">
			<textarea id="address" name="geThirdParterInfo.address" rows="3" cols="36.5" ></textarea>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>业务：</td>
		<td class="td_body" >
		<s:iterator value="geBusinessAreaTypeList" var="geBusinessAreaType">
			<input type="Radio" name="BusinessArea" value="<s:property value="#geBusinessAreaType.id.codeCode"/>" onclick="selectProvince();"><s:property value="#geBusinessAreaType.codeCName"/>&nbsp;
		</s:iterator>
		</td>
	</tr>
		
	<tr>
		<td class="td_head" nowrap>活动地域：</td>
		<td class="td_body">
			<div style="float:left">
				<select id="mechanismArea" name="geThirdParterInfo.businessArea" style="width:130px;" onchange="selectCity();">
				<option>--请选择--</option>
				</select>
			</div>
			<div style="float:left;display: none;" id="suv">
				<select id="cityId"  name="geThirdParterInfo.deptID">
					<option value="">--请选择--</option>
				</select>
			</div>
			<div style="clear:both"></div>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司类型：</td>
		<td class="td_body"  >
			<select id="companyType" name="geThirdParterInfo.companyType" style="width:130px;">
				<option value="">--请选择--</option>
				<s:if test="geCodeCompanyTypeList!=null">
					<s:iterator value="geCodeCompanyTypeList" var="geCodeCompanyType">
					<option value="<s:property value="#geCodeCompanyType.id.codeCode"/>"><s:property value="#geCodeCompanyType.codeCName"/></option>
					</s:iterator>							
				</s:if>
			</select>
		</td>
	</tr>
</table>

<div style="background-color:#E9F8FF;border:1px #E3E3E3 solid;border-bottom-style:none;width: 750px;line-height: 30px;text-align: left ;">
	<span style="padding-left: 20px" class="td_head">公司联系人</span>
</div>
<table id="tableInfo" width="750px" style="border:1px #E3E3E3 solid; line-height: 30px;">
			<tr id="0">
				<td style="text-align: center;'">
				<table  width="100%" style="border-collapse:collapse; margin-left:20px;">
				<tr><td colspan="5" style="padding:3px 0px 5px 0px;"><span id="count" style="font-weight: bold;">联系人1</span></td></tr>
				<tr>
				<td width="6%" style="text-align: left;">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
				<td width="45%"><input id="userName0" name="userName" maxlength="10" type="text"/>&nbsp;</td>
				<td width="5%" align="left">证件类型：</td>
				<td width="45%" colspan="2">
					<select id="identifyType0" name="identifyType" style="width:130px;">
						<s:if test="geCodeIdentifyTypeList!=null">
							<s:iterator value="geCodeIdentifyTypeList" var="geCodeIdentifyType">
							<s:if test="#geCodeIdentifyType.validInd==1">
							<option value="<s:property value="#geCodeIdentifyType.id.codeCode"/>"><s:property value="#geCodeIdentifyType.codeCName"/></option>
							</s:if>
							</s:iterator>							
						</s:if>
					</select>
				</td>
				</tr>
				
				<tr>
				<td>证件号码：</td>
				<td>
				<input id="identifyNumber0" type="text" maxlength="20" name="identifyNumber" onkeyup="validateIDNO(this);" onfocus="validateIDNO(this);">
				</td>
				
				<td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
				<td colspan="2">
					<select id="sex0" name="sex" style="width:130px;">
					<s:if test="geCodeSexList!=null">
						<s:iterator value="geCodeSexList" var="geCodeSex">
						<option value="<s:property value="#geCodeSex.id.codeCode"/>"><s:property value="#geCodeSex.codeCName"/></option>
						</s:iterator>
					</s:if>
					</select>
				</td>
				</tr>
				
				<tr>
				<td>出生日期：</td>
				<td>
					<input type="text" id="birthday0" name="birthday" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate"/>
				</td>
				<td>手机号码：</td>
				<td colspan="2"><input id="telePhone0" type="text" name="telePhone" maxlength="11"></td>
				</tr>
				
				<tr>
				<tr>
				<td>电子邮箱：</td>
				<td><input id="email0" type="text" name="email" maxlength="35"></td>
				</tr>
				<tr>
				<td>联系地址：</td>
				<td colspan='4'>
					<textarea  id="contactAddress0" name="contactAddress" maxlength="150" cols="83" onkeyup='textAreaMaxLen(this);' rows="3"></textarea>
				</td>
				</tr>
				<tr>
				<td colspan='4' style="padding-left:10px">
				<input type='button'  value='删除' onclick='deleteTable(this);'/>
				</td>
				</tr>
				</table>
				</td>
				</tr>
			</table>
			<div style="border:0px #E3E3E3 solid;width: 700px;height:30px;padding-top:5px; text-align: right;">
				<div style="padding-right:58px"><input type="button" value="增加联系人" onclick="insertTable();"/></div>
			</div>
			
			<table>
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doCreate();" nowrap>创建</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
			</tr>
			</table>
			</div>
<div id="message"></div>
<%//<input type="hidden" id="userType" name="geBlackList.userType" value="01">%>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
var ttvf = tt.vf.req;
var ttvfemail=tt.vf.email;   
var ttidCard = tt.vf.idcard;
var tttel = tt.vf.tel;
var ttMobile = new tt.RV().set(new RegExp("^(13[0-9]{9})|(15[0-9]{9})|(18[0-9]{9})$"), "手机号不正确！");
var ttCompanyCode = new tt.RV().set(new RegExp("^([A-Za-z0-9]{8}-[A-Za-z0-9]{1})$"), "公司代码不正确！");
var telCompany = new tt.RV().set(new RegExp("^[0-9]{1}[0-9-]+$"), "公司电话不正确！")
var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "只能输入数字或字母！"); 
//var ttUrl = new tt.RV().set(new RegExp("(\\w+(-\\w+)*)(\\./\/(\\w+(-\\w+)*))*(\\?\\S*)?$"),"请输入正确的网址！"); 

ttvf.addId("thirdParterName");//公司名称[.]
ttvf.addId("url");//公司网址
//ttUrl.addId("url");
ttvf.addId("companyPhone");<%//公司电话%>
new tt.LV().set(0,200).add("geThirdParterInfo.address");//活动方式字数限制
ttvf.addId("address");//公司地址
ttvf.addId("companyEmail");//公司电子邮箱
ttvf.addId("companyType");//公司类型
ttvf.addId("mechanismArea");//活动省区
ttvf.addId("cityId");//活动省区

ttvf.addId("userName0");//联系人姓名
ttvf.addId("identifyNumber0");//证件号码
ttvf.addId("birthday0");//出生日期
ttvf.addId("telePhone0");//手机号码
ttvf.addId("contactAddress0");//联系地址
ttvf.addId("email0");//联系人邮箱

//email
ttvfemail.addId("companyEmail");//公司电子邮箱
ttvfemail.addId("email0");//联系人邮箱
//身份证号码
telCompany.addId("companyPhone");
ttMobile.addId('telePhone0');


function doClear(){
	document.getElementById("frmInput").reset();
}

//弹出一棵树
function alterTree(){
	/*
	var authorityid = document.getElementById("authorityid").value;
	var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?checkType=2&authorityid=ROLE_B_OORD_M&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	*/
	var authorityid = document.getElementById("authorityid").value;
	var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
	window.open('${ctx}/marketing/selectDeptAuthId.do?operation=thirdPartterInfo&checkType=2&authorityid=ROLE_B_AAGA&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}
$(function(){
	//pop提示信息
	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：新建供应商增加联系人<br/>使用人群：配置供应商操作人员 <br/>配置条件：<br/>注意事项：'];
		
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
					width:300,
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