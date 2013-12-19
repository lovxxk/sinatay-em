<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��ϸ��Ϣ</title>
	<link href="/mis/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/mis/global/js/jquery-1.6.2.min.js"></script>

	<link type="text/css" rel="stylesheet" href="/mis/global/js/validate/css/validate.css" />
	<script type="text/javascript" src="/mis/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
</head>


<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			��ϸ��Ϣ
		</div>
	</div>

	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form name="sub" action="/mis/productManage/subDetail.do" method="post">
			<input type="hidden" name="geProductMain.coreProductCode" value="${geProductMain.coreProductCode}"/>
			<input type="hidden" name="type" id="in_type"/>
			<input type="hidden" name="taskID" value="${taskID}"/>
			<input type="hidden" name="workFlowID" value="${workFlowID}"/>
	
	<table class="table_Show" align="center" width="650px" border="0">
	
	<c:if test="${geProductMain.productStatus =='06'}">
	<tr>
		<td class="td_head" width="70px">ҵ������</td>
		<td class="td_body" colspan="3">
			<input type="radio" name="newBusinessArea" value="2" ${geProductMain.businessArea == '2'?"checked":""}>����
			<input type="radio" name="newBusinessArea" value="3" ${geProductMain.businessArea == '3'?"checked":""}>����
			<input type="radio" name="newBusinessArea" value="4" ${geProductMain.businessArea == '4'?"checked":""}>������
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td class="td_head" width="100px">��Ʒ���룺</td>
		<td class="td_body" width="255px">${geProductMain.coreProductCode}</td>
		<td class="td_head" width="70px">��Ʒ���ƣ�</td>
		<td class="td_body">${geProductMain.productName}</td>
	</tr>
	<tr>

		<td class="td_head">�ύʱ�䣺</td>
		<td class="td_body"> <fmt:formatDate value="${geProductMain.createDate}" pattern="yyyy-MM-dd"/> </td>
		<td class="td_head">��Ʒ״̬��</td>
		<td class="td_body">
			<c:forEach items="${code}" var="code" varStatus="stas">
					<c:if test="${code.id.codeCode eq geProductMain.productStatus}">${code.codeCName}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td class="td_head"  width="70px">�������̣�</td>

		<td class="td_body" colspan="3">${geProductMain.productFlow}</td>
	</tr>
	<tr>
		<td class="td_head"  width="70px">��Ʒ��飺</td>
		<td class="td_body" colspan="3">${geProductMain.coreProductSimpleName}</td>
	</tr>
	
	
	<tr height="10px"><td colspan="4">&nbsp;</td></tr>
	
	<!-- 
	<c:if test="${'workflow' == type}">
		<c:if test="${geProductMain.productStatus=='03' || geProductMain.productStatus=='04'}">
		<tr>
			<td colspan="4" class="frmCreate_kuang" valign="top">
			<div class="frmCreate_kuang_header">
				�������
			</div>
			<div style="padding-left:3px;">
				<textarea id="suggestion" name="suggestion" class="textarea_disabled"></textarea>
	    			<span id="suggestion_msg"></span>
			</div>
			</td>
		</tr>
		</c:if>
	</c:if>
	 -->

	<tr height="10px"><td></td></tr> 
</table>
</form>



<table class="table_Show" width="650px" align="center" border="0">

<c:if test="${geProductMain.productStatus=='06'}">
   <tr height="5%">
   		<td height="40px"  colspan="6" align="left">

	   		<table  border="0" width=100% align="center">
   				<tr height="10px">
					<td width="75px" align="left" nowrap>������ʾ��</td>
					<td  align="left" colspan=3>��������·ַ�����ť����Ͷ��������Ϣ״̬����Ϊ�������С��������ü�¼�ַ���ָ����ҵ������</td>		
				</tr>
				<tr height="10px">
					<td nowrap></td>
					<td  align="left"  colspan=3>������رա���ť���رյ�ǰ�Ի���</td>

				</tr>
			</table>
   		</td>
   	</tr>
</c:if>
   	
   	<tr height="80">
   		<td  colspan="6" align="center">
	   		<table  align="center">			
				<!-- �����ѯ�����й����ģ��ſ������������ˡ���������
				�Ǵ����в�ѯ�����ģ�����ǻ��˿��������·ַ�-->
				<tr>
				
				<c:choose>
				<c:when test="${'workflow' == type && (geProductMain.productStatus=='03' || geProductMain.productStatus=='04') }">
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="changeValue(5);" nowrap>����</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="changeValue(2);" nowrap>��������</td>
						
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>�ر�</td>
				</c:when>
				<c:otherwise>
					<c:if test="${geProposalIntention.status =='06' }">
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="changeValue(3);" nowrap>���·ַ�</td>
					</c:if>
					
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'"  onclick="window.close();" nowrap>�ر�</td>
				</c:otherwise>
				</c:choose>
				
				
				</tr>
			</table>
   		</td>
   	</tr>
	</table>
</div>
</body>
<script type="text/javascript">
var suggestion =new tt.Field("��������ʱ���������","","suggestion").setMsgId("suggestion_msg");
//�ǿ���֤
tt.vf.req.add(suggestion);

function changeValue(op){
	document.getElementById("in_type").value=op;
	// ����������дԭ��
	if(op==2){
		if(tt.validate()){
			document.sub.submit();
		}
	}else if(op==3){
		var buss;
		if($("input:checked").val()==2){
			buss = "����";
		}else if($("input:checked").val()==3){
			buss = "����";
		}else{
			buss = "������"
		}
		if(confirm('��ȷ�������������·ַ���--'+buss+'����?')){
			document.sub.submit();
		}
	}else{
		document.sub.submit();
	}
}

</script>
</html>