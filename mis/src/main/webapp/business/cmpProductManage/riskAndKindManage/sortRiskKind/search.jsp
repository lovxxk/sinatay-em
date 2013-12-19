<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
	<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
    <script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>险别顺序</title>
	<style type="text/css">
		span.talentErrMsg{
			padding-left:17px;
		}
	</style>
</head>
<body>
<div class="public_fun_title" style="margin-bottom:-22px;">险别顺序<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span></div>
<form action="${ctx}/business/cmpProductManage/riskAndKindManage/sortRiskKind.do" id="riskKindFm" method="post" >
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" nowrap width="5%" id="idNum">序号</td>
		<td class="search_head" width="20%" nowrap>险别代码</td>
		<td class="search_head" width="25%" nowrap>险别中文名称</td>
		<td class="search_head" width="25%" nowrap>险别标志</td>
		<td class="search_head" width="25%" nowrap>操作</td>
	</tr>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="geKind" status="status">	
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}" >
			<input type="hidden" name="geKindRiskCode" value="<s:property value="#geKind.id.riskCode"/>"/>
			<input type="hidden" name="geKindKindCode" value="<s:property value="#geKind.id.kindCode"/>"/>
			<td class="search_body_center" width="5%" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" width="20%" nowrap>
				<s:property value="#geKind.id.kindCode"/>
			</td>
			<td class="search_body_center" width="25%" nowrap>
				<s:property value="#geKind.kindCName"/>
			</td>
			<td class="search_body_center" style="min-width:25%;" nowrap>
				<s:if test="#geKind.kindflag==01">主险</s:if>
				<s:if test="#geKind.kindflag==02">附加险</s:if>
			</td>
			<td class="search_body_center" width="25%" nowrap>
				<a href="javascript:void(0);" onclick="lastIndex('${status.index}')">上移</a> 
				<a href="javascript:void(0);" onclick="nextIndex('${status.index}')">下移</a>
			</td>
		</tr>
	</s:iterator>
	</s:if>
	  <c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	<tr height=3><td></td></tr> 
	<tr>
		<td colspan=8>
			<table width=64 align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="riskSortSubmit()"  nowrap>
                                                             保存
                </td>
                <td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>重置</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
</body>
<script type="text/javascript">
$(function(){
	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：更新险种顺序。<br/>使用人群：车险产品管理配置人员。<br/>配置条件：先配置险别信息。<br/>注意事项：'];
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

function showReason(obj){
	alert("加入黑名单原因:"+obj);
}
function kindView(url){
	window.open(url, "", "top=100, left=100, width=900, height=600, scrollbars=yes, resizable=yes");
}
function doSearch(){
	window.location.href="${ctx}/business/cmpProductManage/riskAndKindManage/findGeKindList.do";
}
function riskSortSubmit(){
	var strPrimay;
	document.getElementById("riskKindFm").submit();
}
function nextIndex(index){
   var total =${page.totalCount};
   var temp;
   var nowIdIndex;
   var nextIdIndex;
   var nowIndex="#tr_"+index;
   var nextIndex="#tr_"+(parseInt(index)+1);
   if(index == total-1){
      alert("数据已移至末尾");
   }else {

        for(var j=1;j < 4;j++){
            temp=$(nowIndex).find("td").eq(j).html();
            $(nowIndex).find("td").eq(j).html($(nextIndex).find("td").eq(j).html());
            $(nextIndex).find("td").eq(j).html(temp);
        }
        for(var i=0;i< 2;i++){
	          temp=$(nowIndex).find("input").eq(i).val();
	          $(nowIndex).find("input").eq(i).val($(nextIndex).find("input").eq(i).val());
	          $(nextIndex).find("input").eq(i).val(temp);
	   }
   }
}   
function lastIndex(index){
   var temp;
   var nowIdIndex;
   var lastIdIndex;
   var nowIndex="#tr_"+index;
   var lastindex="#tr_"+(parseInt(index)-1);
   if(index==0){
      alert("数据已移至首部");
   }else{
	   for(var j=1;j< 4;j++){
          temp=$(nowIndex).find("td").eq(j).html();
           $(nowIndex).find("td").eq(j).html($(lastindex).find("td").eq(j).html());
           $(lastindex).find("td").eq(j).html(temp);
       }
	   for(var i=0;i< 2;i++){
	          temp=$(nowIndex).find("input").eq(i).val();
	          $(nowIndex).find("input").eq(i).val($(lastindex).find("input").eq(i).val());
	          $(lastindex).find("input").eq(i).val(temp);
	   }
    }
   
}

</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
