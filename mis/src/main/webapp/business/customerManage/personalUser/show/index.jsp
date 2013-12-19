<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-���˿ͻ���ϸ��Ϣ</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			���˿ͻ���ϸ��Ϣ
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<table  class="table_style" align="center" width="650px">
		<tr>
			<td class="td_head" width="100px" nowrap>�ͻ��ţ�</td>
			<td class="td_body">${geUserPersonal.userID}</td>
			<td class="td_head" width="100px" nowrap>�ͻ�������</td>
			<td class="td_body">${geUserPersonal.userName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�ͻ��ȼ���</td>
			<td class="td_body">${geUserPersonal.userLevel }</td>
			<td class="td_head" nowrap>�Ա�</td>
			<td class="td_body">${geUserPersonal.sex=="1"?"��":(geUserPersonal.sex=="2"?"Ů":"")}</td>	
		</tr>
		<tr>
			<td class="td_head" nowrap>֤�����ͣ�</td>
			<td class="td_body">${identifyTypeName }</td>
			<td class="td_head" nowrap>֤�����룺</td>
			<td class="td_body">${geUserPersonal.identifyNumber}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�������ڣ�</td>
			<td class="td_body"><fmt:formatDate value="${geUserPersonal.birthday}" dateStyle="long"/></td>
			<td class="td_head" nowrap>ע��ʱ�䣺</td>
			<td class="td_body"><fmt:formatDate value="${geUserPersonal.makeDate}" dateStyle="long"/></td>
		</tr>
		<tr>
			<td  class="td_head" nowrap>��¼�ʺţ�</td>				
			<td class="td_body">${geUserPersonal.userAccount}</td>
			<td class="td_head" nowrap>�׿��ţ�</td>
			<td class="td_body">${geUserPersonal.piCardNo}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				���䣺
			</td>
			<td class="td_body">
				${geUserPersonal.email}
			</td>
			<td class="td_head" nowrap>
				�ֻ��ţ�
			</td>
			<td class="td_body">
				${geUserPersonal.mobilePhone}
			</td>
		</tr>

		<tr>
			<td class="td_head" nowrap>
				����״����
			</td>
			<td class="td_body">
				${marriageStatus }
			</td>
			<td class="td_head" nowrap>
				���ڵ�����
			</td>
			<td class="td_body">
				${city }
			</td>
		</tr>

		
		<tr>
			<td class="td_head" nowrap>
				������ҵ��
			</td>
			<td class="td_body">
				${industry }
			</td>
			<td class="td_head" nowrap>
				����״����
			</td>
			<td class="td_body">
				${income }
			</td>
		</tr>
		
		<tr>
			<td class="td_head" nowrap>
				��ͥ�绰��
			</td>
			<td class="td_body">
				${geUserPersonal.homePhone}
			</td>
			<td class="td_head" nowrap>
				�칫�绰��
			</td>
			<td class="td_body">
				${geUserPersonal.officePhone}
			</td>
		</tr>
		
		<tr>
		<td class="td_head" nowrap>
				֤��ʶ���� ��
			</td>
			<td class="td_body">
				${geUserPersonal.ukey}
			</td>
			<td class="td_head" nowrap>
				�ʱࣺ
			</td>
			<td class="td_body">
				${geUserPersonal.zipCode}
			</td>
		</tr>

		<tr>
			<td class="td_head" nowrap>
				�ͻ���Դ��
			</td>
			<td class="td_body">
				${userSource}
			</td>
			<td class="td_head" nowrap>
				����״����
			</td>
			<td class="td_body">
				${health}
			</td>
		</tr>

		<tr>
			<td class="td_head" nowrap>
				�ͻ�״̬��
			</td>
			<td class="td_body">
				${geUserPersonal.status=="0"?"��Ч":(geUserPersonal.status=="1"?"��Ч":(geUserPersonal.status=="2"?"δ��ͨ":""))}
			</td>
			<td class="td_head" nowrap>
				���֣�
			</td>
			<td class="td_body">
				${geUserPersonal.integral}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				��ϵ��ַ��
			</td>
			<td class="td_body" colspan="3">
				${geUserPersonal.contactAddress}
			</td>
		</tr>
		<tr height="10px">
			<td colspan="4"></td>
		</tr>
		<tr>
			<td colspan="4">
				<table width=200 align="center">
					<tr>
						<td width="20%">&nbsp;</td>
						<c:if test="${buttonFlag eq '0'}">
							<acc:showView source="ROLE_B_PUSE_U">
								<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>�༭</td>
							</acc:showView>
							<acc:showView source="ROLE_B_PUSE_B">
								<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doUnBindPolicy();" nowrap>�������</td>
							</acc:showView>
						</c:if>
						<!-- 
						<acc:showView source="ROLE_B_PUSE_N">
						<td onclick="doUnBindCard();" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">���񿨽��</td>
						</acc:showView>
						 -->
						<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>�ر�</td>
						<td  width="40%">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
//�رո�ҳ��
function doClose(){
	window.close();
}
//ת���༭ҳ��
function doEdit(){
	window.location = "${ctx }/business/customerManage/personalUser/queryGeUserPersonalForUpdateOrShow.do?handle=update&geUserPersonal.userID=${geUserPersonal.userID}";
}
//ת����������ѯҳ��
function doUnBindPolicy(){
	window.open("${ctx}/business/customerManage/personalUser/unbound/proOrPen/index.jsp?userID=${geUserPersonal.userID}&userName=${geUserPersonal.userAccount}","�������" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
//ת�����񿨽��ҳ��
function doUnBindCard(){
	//window.location = "#";
}
</script>
</body>
</html>
