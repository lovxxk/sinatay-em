<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">

<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">

function downfile(filename,fileType) {
	$("#down_filename").val(filename);
	$("#down_fileType").val(fileType);
	$("#down_from").submit();
}

//����
function deletefile(filename,fileType) {
	$("#updateOneFileName").val(filename);
	$("#delete_fileType").val(fileType);	
	$("#delete_from").submit();
}

</script>
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">

<!-- Ͷ���ļ�  -->
<div class="public_fun_title">Ͷ���ļ� </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ���</td>
		<td class="search_head" width="90px" height="30px" nowrap>�ļ���С</td>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ��޸�����</td>
		<td class="search_head" width="160px" height="30px" nowrap>���ò���</td>		
	</tr> 
	<c:forEach items="${tbwjFiles}" var="keyval" step="1"
		varStatus="status">
		<tr>		
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:downfile('${keyval[1]}','TBWJ');" >${keyval[0]}</a>			
			</td>
			<td style="padding-right: 10px" align="right" >${keyval[2]}bytes</td>
			<td align="center" >${keyval[3]}</td>
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','TBWJ');" >ɾ��</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="Ͷ���ļ��ϴ�" />
<input type="hidden" id="fileType" name="fileType" value="TBWJ">
</Form>
</div>
<!-- ��ȫ�ļ�  -->
<div class="public_fun_title">��ȫ�ļ� </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ���</td>
		<td class="search_head" width="90px" height="30px" nowrap>�ļ���С</td>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ��޸�����</td>
		<td class="search_head" width="160px" height="30px" nowrap>���ò���</td>		
	</tr>
	<c:forEach items="${bqwjFiles}" var="keyval" step="1"
		varStatus="status">
		<tr>		
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:downfile('${keyval[1]}','BQWJ');" >${keyval[0]}</a>			
			</td>
			<td style="padding-right: 10px" align="right" >${keyval[2]}bytes</td>
			<td align="center" >${keyval[3]}</td>
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','BQWJ');" >ɾ��</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="��ȫ�ļ��ϴ�" />
<input type="hidden" id="fileType" name="fileType" value="BQWJ">
</Form>
</div>
<!-- �����ļ�  -->
<div class="public_fun_title">�����ļ� </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ���</td>
		<td class="search_head" width="90px" height="30px" nowrap>�ļ���С</td>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ��޸�����</td>
		<td class="search_head" width="160px" height="30px" nowrap>���ò���</td>		
	</tr> 
	<c:forEach items="${lpwjFiles}" var="keyval" step="1"
		varStatus="status">
		<tr>		
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:downfile('${keyval[1]}','LPWJ');" >${keyval[0]}</a>			
			</td>
			<td style="padding-right: 10px" align="right" >${keyval[2]}bytes</td>
			<td align="center" >${keyval[3]}</td>
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','LPWJ');" >ɾ��</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="�����ļ��ϴ�" />
<input type="hidden" id="fileType" name="fileType" value="LPWJ">
</Form>
</div>
<!-- ְҵ����  -->
<div class="public_fun_title">ְҵ���� </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ���</td>
		<td class="search_head" width="90px" height="30px" nowrap>�ļ���С</td>
		<td class="search_head" width="160px" height="30px" nowrap>�ļ��޸�����</td>
		<td class="search_head" width="160px" height="30px" nowrap>���ò���</td>		
	</tr> 
	<c:forEach items="${zyflFiles}" var="keyval" step="1"
		varStatus="status">
		<tr>		
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:downfile('${keyval[1]}','ZYFL');" >${keyval[0]}</a>			
			</td>
			<td style="padding-right: 10px" align="right" >${keyval[2]}bytes</td>
			<td align="center" >${keyval[3]}</td>
			<td align="left" >
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','ZYFL');" >ɾ��</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="ְҵ�����ϴ�" />
<input type="hidden" id="fileType" name="fileType" value="ZYFL">
</Form>
</div>


<Form id="delete_from" method="post"  action="deleteFile.do">
	<input type="hidden" id="updateOneFileName" name="updateOneFileName">
	<input type="hidden" id="delete_fileType" name="fileType">
</Form>

<Form id="down_from" method="post" enctype="multipart/form-data" target="downloadSubmit" action="downloadFile.do">
	<input type="hidden" id="down_filename" name="down_filename" />
	<input type="hidden" id="down_fileType" name="fileType" />
</Form>
<iframe style="display:none" id="downloadSubmit" name="downloadSubmit" src="about:blank" ></iframe>
<iframe style="display:none" id="fraSubmit" name="fraSubmit" src="about:blank" ></iframe>