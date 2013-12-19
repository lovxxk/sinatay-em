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

//试算
function deletefile(filename,fileType) {
	$("#updateOneFileName").val(filename);
	$("#delete_fileType").val(fileType);	
	$("#delete_from").submit();
}

</script>
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">

<!-- 投保文件  -->
<div class="public_fun_title">投保文件 </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>文件名</td>
		<td class="search_head" width="90px" height="30px" nowrap>文件大小</td>
		<td class="search_head" width="160px" height="30px" nowrap>文件修改日期</td>
		<td class="search_head" width="160px" height="30px" nowrap>可用操作</td>		
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
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','TBWJ');" >删除</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="投保文件上传" />
<input type="hidden" id="fileType" name="fileType" value="TBWJ">
</Form>
</div>
<!-- 保全文件  -->
<div class="public_fun_title">保全文件 </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>文件名</td>
		<td class="search_head" width="90px" height="30px" nowrap>文件大小</td>
		<td class="search_head" width="160px" height="30px" nowrap>文件修改日期</td>
		<td class="search_head" width="160px" height="30px" nowrap>可用操作</td>		
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
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','BQWJ');" >删除</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="保全文件上传" />
<input type="hidden" id="fileType" name="fileType" value="BQWJ">
</Form>
</div>
<!-- 理赔文件  -->
<div class="public_fun_title">理赔文件 </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>文件名</td>
		<td class="search_head" width="90px" height="30px" nowrap>文件大小</td>
		<td class="search_head" width="160px" height="30px" nowrap>文件修改日期</td>
		<td class="search_head" width="160px" height="30px" nowrap>可用操作</td>		
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
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','LPWJ');" >删除</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="理赔文件上传" />
<input type="hidden" id="fileType" name="fileType" value="LPWJ">
</Form>
</div>
<!-- 职业分类  -->
<div class="public_fun_title">职业分类 </div>
<div align="left" style="padding-left: 20px;padding-top: 20px">
<table class="table_Show" id="data_table" border="1">
	<tr>
		<td class="search_head" width="160px" height="30px" nowrap>文件名</td>
		<td class="search_head" width="90px" height="30px" nowrap>文件大小</td>
		<td class="search_head" width="160px" height="30px" nowrap>文件修改日期</td>
		<td class="search_head" width="160px" height="30px" nowrap>可用操作</td>		
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
			<a style="padding-left: 10px" href="javascript:deletefile('${keyval[1]}','ZYFL');" >删除</a>			
			</td>
		</tr>
	</c:forEach>
</table>
<Form id="up_from" method="post" enctype="multipart/form-data" action="updateFile.do">
<input type="file" name="updateOne" style="width: 300px">
                <input type="submit" value="职业分类上传" />
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