<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">��֤����</li>
			</ul>
		</div>
		<div class="service_main">
			<div class="left_dynamic">
				<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_rank_left.jsp"></jsp:include>
			</div>
			<div class="right_content">
				<jsp:include page="../common/service_head.jsp"></jsp:include>
				<div class="service_area">
					<div class="title">
						<p>�������</p>
					</div>
					<div class="download_list">
						<h4>�ƶ�Ӧ��</h4>
						<ul>
							<li><span class="block"></span><a href="http://www.sinatay.com/download/IPadEform/" target="_blank">��̩����IPAD����Ͷ��Ӧ������</a></li>
							<li><span class="block"></span><a href="http://www.sinatay.com/download/mobileOA/sinatayoa.html" target="_blank">�ƶ��칫�������</a></li>
						</ul>
					</div>
					<br>
					
					<span class="block"></span><a href="">${m.Name}</a>
					<span class="block"></span><a href="">${m.Name}</a>
					<div class="title">
						<p>��֤����</p>
					</div>
					<div class="download_list">
						<h4>Ͷ���ļ�<a href="#"  class="download_more" style="display: none">����&gt;&gt;</a></h4>
						<ul>
							<c:forEach items="${tbwjFiles}" var="m" step="1"
								varStatus="status">
								<li><span class="block"></span><a href="javascript:downfile('${m.DName}','TBWJ');">${m.Name}</a><span class="date">[${m.LastModifiedDate}]</span></li>
							</c:forEach>							
						</ul>
					</div>
					<div class="download_list">
						<h4>��ȫ�ļ�<a href="#" class="download_more" style="display: none">����&gt;&gt;</a></h4>
						<ul>
							<c:forEach items="${bqwjFiles}" var="m" step="1"
								varStatus="status">
								<li><span class="block"></span><a href="javascript:downfile('${m.DName}','BQWJ');">${m.Name}</a><span class="date">[${m.LastModifiedDate}]</span></li>
							</c:forEach>	
						</ul>
					</div>
					<div class="download_list">
						<h4>�����ļ�<a href="#" class="download_more" style="display: none">����&gt;&gt;</a></h4>
						<ul>
							<c:forEach items="${lpwjFiles}" var="m" step="1"
								varStatus="status">
								<li><span class="block"></span><a href="javascript:downfile('${m.DName}','LPWJ');">${m.Name}</a><span class="date">[${m.LastModifiedDate}]</span></li>
							</c:forEach>	
						</ul>
					</div>
					<div class="download_list">
						<h4>ְҵ����<a href="#" class="download_more" style="display: none">����&gt;&gt;</a></h4>
						<ul>
							<c:forEach items="${zyflFiles}" var="m" step="1"
								varStatus="status">
								<li><span class="block"></span><a href="javascript:downfile('${m.DName}','ZYFL');">${m.Name}</a><span class="date">[${m.LastModifiedDate}]</span></li>
							</c:forEach>	
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<Form id="down_from" method="post" enctype="multipart/form-data" target="downloadSubmit" action="${ctx }/dcenter/downloadFile.do">
	<input type="hidden" id="down_filename" name="down_filename" />
	<input type="hidden" id="down_fileType" name="fileType" />
</Form>
<iframe style="display:none" id="downloadSubmit" name="downloadSubmit" src="about:blank" ></iframe>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">

function downfile(filename,fileType) {
	$("#down_filename").val(filename);
	$("#down_fileType").val(fileType);
	$("#down_from").submit();
}
</script>