<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<title>�����������ϵͳ-��˲�Ʒ</title>
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/global/css/mis_basic_forFlowIndexJsp.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
	<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
	
	<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
	<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>

</head>
<body topmargin="0" leftmargin="0">
	<div style="height: 20px"></div>
	<div class="stpess_navigation">
	<ul>
    	<li class="stpli_b">
        	<span class="stpli_spanleft">1</span>
            <span class="stpli_spanright1">�½���Ʒ</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">2</span>
            <span class="stpli_spanright1">��������</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">3</span>
            <span class="stpli_spanright1">��ϸ����</span>
        </li>
        <li class="stpli_a">
        	<span class="stpli_spanleft">4</span>
            <span class="stpli_spanright2">���</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">5</span>
            <span class="stpli_spanright2">����</span>
        </li>
    </ul>
</div>
	<div style="height: 10px"></div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="${ctx }/productManage/updateProductStatus.do" method="post">
		<input type="hidden" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode'/>">
		<input type="hidden" name="geProductMain.productStatus" value="04">
		<input type="hidden" name="taskID" value="${taskID}">
		<input type="hidden" name="workFlowID" value="${workFlowID}">
		
		<input type="hidden" name="from" value="audit">
		
		<input type="hidden" id="dotype" name="dotype">
		
			<table class="table_style" align="center" width="70%" id="productTable" >
				<tr height="20px;"></tr>
				<tr>
					<td class="td_head td_head_center" width="150px" style="text-align: center;"  nowrap>
						<font color="#616161">Ҫ��˵Ĳ�Ʒ���ƣ�</font>
					</td>
					<td class="td_body" width="200px" nowrap>
						<span id="productCodeList"><s:property value='geProductMain.productName'/></span>
					</td>
				</tr>
				<tr>
					<td class="td_head td_head_center" style="text-align: center"  nowrap>
						<font color="#616161">Ҫ��˵Ĳ�Ʒ���룺</font>
					</td>
					<td class="td_body" nowrap>
						<!--<a href="${ctx}/productManage/toEditDetail.do?geProductMain.coreProductCode=<s:property value='geProductMain.coreProductCode'/>" >-->
						<span id="productCodeList"><s:property value='geProductMain.coreProductCode'/></span>
					</td>
				</tr>
				<tr height="180px">
					<td  class="td_head td_head_center" width="120px" nowrap  style="vertical-align: top;text-align: center">
						<span style="padding-left:50px;color:#616161 ">���������</span>
					</td>
					<td class="td_body"  nowrap>
						<div style="padding-left: 3px;width: 420px">
							<textarea  style="width: 360px;height: 100px" id="suggestion" name="suggestion" ></textarea>
	    					
	    					<div style="padding-top: 5px"><span id="suggestion_msg"></span></div>
						</div>
					</td>
				</tr>
				<tr height="20px;"></tr>
				
				<tr>
					<td colspan="2">
					<div class="public_list_title" style="width:100%;">����켣��Ϣ���£�</div>
					<div style="clear: both;"></div>
						<table class="table_Show" width="100%" align="center" border="0">
							<tr height="5%">
								<td class="search_head" width="10%">���</td>
								<td class="search_head" width="10%">������</td>
								<td class="search_head" width="10%">�����״̬</td>
								<td class="search_head" width="20%">����ʱ��</td>
								<td class="search_head" colspan="2" width="50%">�������</td>
							</tr>
							<c:if test="${fn:length(geProductMain.geProductMainProcesses) > 0}">
								<c:forEach items="${geProductMain.geProductMainProcesses}" var="detail" varStatus="detail_index">
									<tr height="5%">
										<td class="search_body_center">${detail_index.index + 1 }</td>
										<td class="search_body_center">${detail.operatorID }</td>
										<td class="search_body_center">
											<c:forEach items="${code}" var="code" varStatus="stas">
												<c:if test="${code.id.codeCode eq detail.handleStatus}">${code.codeCName}</c:if>
											</c:forEach>
										</td>
										<td class="search_body_center"><fmt:formatDate value="${detail.handleDate}" pattern="yyyy-MM-dd HH:mm"/></td>
										<td class="search_body_center" colspan="2">${detail.options}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${fn:length(geProductMain.geProductMainProcesses) <= 0}">
								<tr>
									<td  height="40px" class="search_body_center" colspan=6">�ü�¼��δ���κβ���</td>
								</tr>
							</c:if>
							<tr height=25><td  colspan="5"></td></tr> 
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table>
							<tr>
								
								
								<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" align="right" onclick="doNext(1);" nowrap>
									���ͨ��
								</td>
								<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" align="right" onclick="doNext(2);" nowrap>
									��˲�ͨ��
								</td>
								<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" align="right" onclick="javascript:window.close();" nowrap>
									�뿪
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
tt.vf.req.add("suggestion");
//�����֤
new tt.LV().set(0,150).add("suggestion");
	
	//���
	function doNext(op){
		if(! tt.validate()){
			return;
		}
		
		document.getElementById("dotype").value=op;
		document.getElementById("frmInput").submit();
	}
	function doClose(){
		document.location.href=contextRootPath+"/productManage/toSearchProduct.do";
	}
</script>
</html>
