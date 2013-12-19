<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link type="text/css" rel="stylesheet" href="${ctx }/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body style="border-top: 1px solid #a1a29c;">
<script type="text/javascript">
</script>
<div id="page">
	<div style="height:15px;">&nbsp;</div>
	<table id="pageContent" class="page_bg" align="center" border=0>
		<tr>
			<td align="center" valign="middle">
				<table align="center" style="height:24px;text-align:center;" cellspacing="0" cellpadding="0" border=0>
					<tr>
						<td valign="middle"><a href="#" id="tdFirst">&nbsp;</a></td>
						<td style="padding-right:6px;"><a href="#" id="tdPrevious">&nbsp;</a></td>
						<c:if test="${param.totalPage gt 6 }">
							<td id="pageSpan" valign="middle">
								<c:if test="${param.totalPage ne param.pageNo }">
									<c:if test="${param.pageNo gt 4 }">
										<label style="float:left;color:6c6c6c;padding:7px 0px 0px 2px;">…</label>
									</c:if>
					        		<c:if test="${param.pageNo + 2 - param.totalPage gt 0}">
					        			<c:if test="${param.pageNo - 3 ge 0}">
					 						<c:forEach begin="${param.totalPage - 5 }" end="${param.totalPage}" varStatus="item">
					 					 		<a href="#" onclick="goPage('${item.index}');" class="otherPage">${item.index}</a>
					 						</c:forEach>
					 					</c:if>
					        		</c:if>
					        		<c:if test="${param.pageNo -3  gt 0 && param.totalPage - param.pageNo ge 2}">
					         			<c:forEach begin="${param.pageNo - 3}" end="${param.pageNo + 2}" varStatus="item">
					        				<a href="#" onclick="goPage('${item.index}');" class="otherPage">${item.index}</a>
					        			</c:forEach>
					          		</c:if>
					        		<c:if test="${param.pageNo - 3  le 0 }">
								 		<c:forEach begin="1" end="${param.pageNo + 2 lt 6 ? '6' : param.pageNo + 2}" varStatus="item">
							 				<a href="#" onclick="goPage('${item.index}');" class="otherPage">${item.index}</a>
										</c:forEach>
					          		</c:if>
									<c:if test="${param.pageNo + 2  lt param.totalPage }">
					         			<label style="float:left;color:6c6c6c;padding:7px 2px 0px 0px;">…</label>
					          		</c:if>
					          	</c:if>
					          	<c:if test="${param.totalPage eq param.pageNo }">
					          		<label style="float:left;color:6c6c6c;padding:7px 0px 0px 2px;">…</label>
					       			<c:forEach begin="${param.totalPage - 5}" end="${param.totalPage }" varStatus="item">
					       				<a href="#" onclick="goPage('${item.index}');" class="otherPage">${item.index}</a>
					     			</c:forEach>
					       		</c:if>
					       		&nbsp;
							</td>
						</c:if>
						<c:if test="${param.totalPage le 6 }">
							<td id="pageSpan">
								<c:forEach begin="1" end="${param.totalPage }" varStatus="item">
					       			<a href="#" onclick="goPage('${item.index}');" class="otherPage">${item.index}</a>
					     		</c:forEach>
					     		&nbsp;
							</td>
						</c:if>
						<td valign="middle"><a href="#" id="tdNext">&nbsp;</a></td>
						<td><a href="#" id="tdEnd">&nbsp;</a></td>
					</tr>
				</table>
			</td>
			<td id="totalPage" style="display:none;width:180px;padding-right:15px;">
				<div id="spinfo" style="float:right;"></div>
				<c:if test="${!empty param.totalPage}">
					<div style="padding-top:3px;float:right;">
						<select id='allPage' name='allPage' onchange='javascript:goSearch(this.selectedIndex + 1)' >
							<c:forEach  begin="1" end="${param.totalPage}" step="1" varStatus="status">
						 		<option value="${status.index}" ${status.index == param.pageNo?"selected":""}>${status.index}</option>
							</c:forEach>
						</select>
					</div>
					<div style="float:right;" id="spselect">跳转至&nbsp;</div>
				</c:if>
			</td>
			<td width="125px">
				<div style="float:left;">每页显示&nbsp;</div>
				<div style="padding-top:3px;float:left;">
					<select id="pageSize" name="pageSize" onchange="goSearchByPageSize();">
						<option value="10" ${param.pageSize == 10 ?"selected":""}>10</option>
						<option value="20" ${param.pageSize == 20||empty param.pageSize ?"selected":""}>20</option>
						<option value="50" ${param.pageSize == 50 ?"selected":""}>50</option>
					</select>
				</div>
				<div style="float:left;">&nbsp;条</div>
			</td>
		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
	var frmSearch = window.parent.frames[0];
	var search = window.parent.frames[2];
	var pageSize = 0;
	var pageNo = 0;
	var totalCount = 0;
	var totalPage = 0;
	if("${param.pageSize}" != ""){
		pageNo = ${empty param.pageNo ? 0 : param.pageNo};
		pageSize = ${empty param.pageSize ? 0 : param.pageSize};
		totalCount = ${empty param.totalCount ? 0 : param.totalCount};
		totalPage = ${empty param.totalPage ? 0 : param.totalPage};
	}
	
	$(document).ready(function(){
		if(pageNo > 4 && totalPage > 6){/**home*/
			$("#tdFirst").removeClass("tdFirst_disabled").addClass("tdFirst");
			$("#tdFirst").bind({
				click:function(){
					goSearch(1);
				}
			});
		}else{
			$("#tdFirst").removeClass("tdFirst").addClass("tdFirst_disabled");
			$("#tdFirst").unbind("click");
		}
		
		if(pageNo > 1){/**pgUp*/
			$("#tdPrevious").removeClass("tdPrevious_disabled").addClass("tdPrevious");
			$("#tdPrevious").bind({
				click:function(){
					goSearch(pageNo-1);
				}
			});
		}else{
			$("#tdPrevious").removeClass("tdPrevious").addClass("tdPrevious_disabled");
			$("#tdPrevious").unbind("click");
		}
		
		if(totalPage > pageNo){/**pgDown*/
			$("#tdNext").removeClass("tdNext_disabled").addClass("tdNext");
			$("#tdNext").bind({
				click:function(){
					goSearch(pageNo+1);
				}
			});
		}else{
			$("#tdNext").removeClass("tdNext").addClass("tdNext_disabled");
			$("#tdNext").unbind("click");
		}
		
		if(totalPage > pageNo+2 && totalPage > 6){/**end*/
			$("#tdEnd").removeClass("tdEnd_disabled").addClass("tdEnd");
			$("#tdEnd").bind({
				click:function(){
					goSearch(totalPage);
				}
			});
		}else{
			$("#tdEnd").removeClass("tdEnd").addClass("tdEnd_disabled");
			$("#tdEnd").unbind("click");
		}
		
		$("#pageSpan").find("a").each(function(){
			if($(this).text() == (pageNo)){
				$(this).removeClass("otherPage").addClass("currentPage");
				//$(this).css("color", "#FFFFFF");
			}
				$(this).bind({
					mousemove : function() {
						if($(this).text() != $("#pageNo").val()){
							$(this).css("color", "#FFFFFF");
						}
					},
					mouseout:function(){
						if($(this).attr("class") != "currentPage"){
							$(this).addClass("otherPage");
							$(this).css("color", "#6c6c6c");
						}
					}
					
				});
		});
	
		/** set spinfo text */
		if(pageNo != null && pageNo != "") {
			$("#spinfo").html("&nbsp;&nbsp;共" + totalCount +"条");
			$("#totalPage").show();
		}

		/** set pageSize */
		var obj = document.getElementById("pageSize");
		if(pageSize == 10) {
			$("#pageSize").attr("selectedIndex",0);
		} else if(pageSize == 50) {
			$("#pageSize").attr("selectedIndex",2);
		} else {
			$("#pageSize").attr("selectedIndex",1);
		}
	});
	
	function goSearchByPageSize(){
		pageNo = 1;
		goSearch(pageNo);
	}
	
	function goSearch(x) {
		frmSearch.document.getElementById("pageNo").value = x;
		frmSearch.document.getElementById("pageSize").value = document.getElementById("pageSize").value;
		frmSearch.document.getElementById("frmInput").submit();
	}
	function goPage(pageNo){
		frmSearch.document.getElementById("pageNo").value = pageNo;
		frmSearch.document.getElementById("pageSize").value = document.getElementById("pageSize").value;
		frmSearch.document.getElementById("frmInput").submit();
	}
</script>
</html>
