<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-�༭���˿ͻ���Ϣ</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
</head>
<body onload="pageValidate();">
<div>
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				�༭���˿ͻ���Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div class="table_content" style="padding-top:20px;height:190px">
		<form id="frmInput" method="post" action="${ctx}/business/customerManage/personalUser/updateGeUserPersonal.do" target="myFrame">
			<div class="frmCreate_kuang" style="width:650px;margin:0px auto;clear:both;">
				<div class="frmCreate_kuang_header">���ɱ༭��Ϣ</div>
				<div style="padding-left:3px;">
					<table align="center" width="645px">
						<tr>
							<td class="td_head" width="100px" nowrap>�ͻ��ţ�</td>
							<td class="td_body" width="222px">
								<input style="width:170px;" type="hidden" id="geUserPersonalID" name="geUserPersonal.userID" value="${geUserPersonal.userID}">
								${geUserPersonal.userID}
							</td>
							<td class="td_head" nowrap width="100px">�ͻ�������</td>
							<td class="td_body" width="223px">
								${geUserPersonal.userName}
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>�Ա�</td>
							<td class="td_body">
								${geUserPersonal.sex=="1"?"��":(geUserPersonal.sex=="2"?"Ů":"")}
							</td>
							<td class="td_head" nowrap>֤�����ͣ�</td>
							<td class="td_body">
								${identifyTypeName}
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>֤�����룺</td>
							<td class="td_body">
								${geUserPersonal.identifyNumber}
							</td>
							<td class="td_head" nowrap>�������ڣ�</td>
							<td class="td_body">
								<fmt:formatDate value="${geUserPersonal.birthday}" pattern="yyyy-MM-dd"/>
							</td>
						</tr>				
						<tr>
							<td class="td_head" nowrap>ע��ʱ�䣺</td>
							<td class="td_body">
								<fmt:formatDate value="${geUserPersonal.makeDate}" pattern="yyyy-MM-dd"/>
							</td>
							<td class="td_head" nowrap>�ͻ���Դ��</td>
							<td class="td_body">
								<c:forEach items="${bussList }" var="userSource">
									<c:if test="${geUserPersonal.userSource == userSource.id.codeCode }">
										${userSource.codeCName }
									</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>�׿��ţ�</td>
							<td class="td_body">
								${geUserPersonal.piCardNo}
							</td>
							<td class="td_head" nowrap>���֣�</td>
							<td class="td_body">
								<input type="hidden" id="integral" name="geUserPersonal.integral" style="width:170px;" value="${geUserPersonal.integral}">${geUserPersonal.integral}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div style="height:15px;clear:both;">&nbsp;</div>
			<div class="frmCreate_kuang" style="width:650px;margin:0px auto;height:460px;">
				<div class="frmCreate_kuang_header">�ɱ༭��Ϣ</div>
				<div style="padding-left:3px;">
					<table  align="left" width="645px">
						<tr>
							<td width="100px" class="td_head" nowrap>��¼�ʺţ�</td>				
							<td width="545px" class="td_body" colspan="3">
								<input type="text" id="geUserPersonalAccount" name="geUserPersonal.userAccount" style="width:170px;" maxlength="30" value="${geUserPersonal.userAccount}">
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>���䣺</td>
							<td class="td_body" colspan="3">
								<input type="text" id="email" name="geUserPersonal.email" style="width:170px;" maxlength="50" value="${geUserPersonal.email}">
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>�ֻ��ţ�</td>
							<td class="td_body" colspan="3">
								<input type="text" id="mobilePhone" name="geUserPersonal.mobilePhone" style="width:170px;" maxlength="11" value="${geUserPersonal.mobilePhone}">
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>����״����</td>
							<td class="td_body" colspan="3">
								<select id="marriageStatus" name="geUserPersonal.marriageStatus">
									<option value="9" ${geUserPersonal.marriageStatus=="9"?"selected":""}>--��ѡ��--</option>
									<c:forEach items="${maritalStatusList }" var="marriageStatus">
										<c:if test="${marriageStatus.id.codeCode != '9'}">
											<option value="${marriageStatus.id.codeCode }" ${geUserPersonal.marriageStatus == marriageStatus.id.codeCode?"selected":""}>${marriageStatus.codeCName }</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>���ڵ�����</td>
							<td class="td_body" colspan="3">
								<select id="provinces" name="geUserPersonal.provinces">
									<option value="">--��ѡ��--</option>
									<c:forEach items="${provinces }" var="proGeArea">
										<option value="${proGeArea.gid }" ${proGeArea.gid == province?"selected":""}>${proGeArea.gname }</option>
									</c:forEach>
								</select>&nbsp;ʡ/��&nbsp;
								<select id="city" name="geUserPersonal.city" style="display: ${not empty cities?'':'none' };">
									<option value="">--��ѡ��--</option>
									<c:forEach items="${cities }" var="cityGeArea">
										<option value="${cityGeArea.gid }" ${cityGeArea.gid == city?"selected":""}>${cityGeArea.gname }</option>
									</c:forEach>
								</select><span id="city1" style="display: ${not empty cities?'':'none' };">&nbsp;��/��&nbsp;</span>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>������ҵ��</td>
							<td class="td_body" colspan="3">
								<select id="industry" name="geUserPersonal.industry">
									<option value="">--��ѡ��--</option>
									<c:forEach items="${industryList }" var="industry">
										<option value="${industry.id.codeCode }" ${geUserPersonal.industry == industry.id.codeCode?"selected":""}>${industry.codeCName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>����״����</td>
							<td class="td_body" colspan="3">
								<select size="1" id="income" name="geUserPersonal.income">
									<option value="">--��ѡ��--</option>
									<c:forEach items="${incomeList }" var="income">
										<option value="${income.id.codeCode }" ${geUserPersonal.income == income.id.codeCode?"selected":""}>${income.codeCName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>��ͥ�绰��</td>
							<td class="td_body" colspan="3">
								<input type="text" id="homePhone" name="geUserPersonal.homePhone" style="width:170px;" maxlength="20" value="${geUserPersonal.homePhone}">
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>�칫�绰��</td>
							<td class="td_body" colspan="3">
								<input type="text" id="officePhone" name="geUserPersonal.officePhone" style="width:170px;" maxlength="20" value="${geUserPersonal.officePhone}">
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>��ϵ��ַ��</td>
							<td class="td_body" colspan="3">
								<textarea id="contactAddress" name="geUserPersonal.contactAddress" rows="4" style="width:170px;overflow: hidden;">${geUserPersonal.contactAddress}</textarea>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>�ʱࣺ</td>
							<td class="td_body" colspan="3">
								<input type="text" id="zipCode" name="geUserPersonal.zipCode" style="width:170px;" maxlength="6" value="${geUserPersonal.zipCode}">
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>����״����</td>
							<td class="td_body" colspan="3">
								<select id="health" name="geUserPersonal.health">
									<option value="" ${geUserPersonal.health==""?"selected":""}>--��ѡ��--</option>
									<c:forEach items="${healthList }" var="health">
										<option value="${health.id.codeCode }" ${geUserPersonal.health == health.id.codeCode?"selected":""}>${health.codeCName }</option>
									</c:forEach>
								</select>		
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>�ͻ�״̬��</td>
							<td class="td_body" colspan="3">
								<select id="status" name="geUserPersonal.status" style="width:170px;">
									<option value="0" ${geUserPersonal.status=="0"?"selected":""}>��Ч</option>
									<option value="1" ${geUserPersonal.status=="1"?"selected":""}>��Ч</option>
									<option value="2" ${geUserPersonal.status=="2"?"selected":""}>δ��ͨ</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>�ͻ��ȼ���</td>
							<td class="td_body" colspan="3">
								<select id="status" name="geUserPersonal.userLevel" style="width:170px;">
									<option value="">--��ѡ��--</option>
									<c:forEach items="${userLevelList }" var="userLevel">
										<option value="${userLevel.id.codeCode }" ${geUserPersonal.userLevel == userLevel.id.codeCode?"selected":""}>${userLevel.id.codeCode }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td_head" nowrap>RA��֤��</td>
							<td class="td_body" colspan="3">
								<select id="raInd" name="geUserPersonal.raInd" style="width:170px;">
									<option value="0" ${geUserPersonal.raInd=="0"?"selected":""}>δ��֤</option>
									<option value="1" ${geUserPersonal.raInd=="1"?"selected":""}>����֤</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div style="height:15px;clear:both;">&nbsp;</div>
			<div style="margin:0px auto;width:200px;">
				<table width=200 align="center">
					<tr>
						<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx }/business/customerManage/personalUser/queryGeUserPersonalForUpdateOrShow.do?handle=show&buttonFlag=0&geUserPersonal.userID=${geUserPersonal.userID}';" nowrap>����</td>
						<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>�޸�</td>
						<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>	
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >�ر�</td>
					</tr>
				</table>
			</div>
			<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
//���ύ
function doUpdate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}
//������
function doClear(){
	document.getElementById("frmInput").reset();
}
//������ϸҳ��
function doBack(){
	window.location = "${ctx }/business/customerManage/personalUser/queryGeUserPersonalForUpdateOrShow.do?handle=show&geUserPersonal.userID=${geUserPersonal.userID}";
}

function pageValidate(){
	tt.vf.req.addName("geUserPersonal.userAccount");
	tt.vf.req.addName("geUserPersonal.email");
	tt.vf.email.addName("geUserPersonal.email");
	//tt.vf.tel.addName("geUserPersonal.homePhone","geUserPersonal.officePhone");
	tt.vf.num.addName("geUserPersonal.zipCode");
	new tt.RV().set(new RegExp(/^(\d{11})$|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/),"�Ϸ���ʽ��010-85623545��15162365236��").add("geUserPersonal.homePhone").add("geUserPersonal.officePhone");
	new tt.RV().set(new RegExp(/^[0-9]{8,11}$/),"��8-11λ�������").add("geUserPersonal.mobilePhone");
	new tt.LV().set(0,100).add("geUserPersonal.contactAddress");
}

var us = "${geUserPersonal.userSource }";
if(us == "1"){
	us = "2";
}
$("#provinces").bind("change", function(){
	ajaxDepartment($(this).val(), "2", "city");
});
function ajaxDepartment(pgid,glevel, selectID){
	$.ajax({
		 type: "POST",
		 url: "${ctx}/business/customerManage/personalUser/findDepartment.do",
	     dataType: "json",
	     cache: false,
	     data:{"pgid":pgid,"glevel":glevel},
	     success:function(data){
	    	 if(selectID=="city"){
	    		if(data==""){	
	    			$("#city").hide();
	    			$("#city1").hide();
	    			$("#city").append("<option value='" + pgid + "' selected> </option>");
	    			return;
	    		}else{
	    			$("#city").empty();
	    			$("#city").show();
	    			$("#city1").show();
	    		}
		    }
	    	$("#" + selectID).empty();
	    	//$("#" + selectID).append("<option value=''>--��ѡ��--</option>");
	    	for(var i = 0; i < data.length; i++){
	    		$("#" + selectID).append("<option value='" + data[i][0]+ "'>" + data[i][1] + "</option>");
	    	}
	     }
	});
}
var ids = ['status','raInd'];
var contents = ['�ÿͻ��ڵ���������û���������޸Ŀ���߻򽵵��û�����','Ԥ�����ݲ�ʹ��'];
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
			width:130,
			padding: 10, 
			textAlign: 'left',
			background: '#e0f2ff', 
			tip:true//����������Ƿ����
			//name: 'green' 
		} 
	}); 
}
</script>
</html>
