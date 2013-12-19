<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%--<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include> --%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">�������</li>
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
					<div class="main_title">��������Ӧ������һ����</div>
					<p class="notice_info">���²���Ϊ�ͻ�������������ʱ����Ҫ�Ļ������ϣ������ڱ����¹ʵľ��������ͬ������˾���ܻ���Ҫ�ͻ��ṩһЩ�뱾��������ص�������</p>
					<div class="sub_title">
						<p>�����������</p>
					</div>
					<p class="notice_info">��ί�����˴���ʱ�����ṩ�ͻ���Ȩί���鼰��ί�������֤��</p>
					<div class="service_list">
						<div class="list_item tip">
							<div class="content_col num">���</div>
							<div class="content_col apply_stuff">�������</div>
							<div class="content_col description">˵��</div>
						</div>
						<div class="list_item">
							<div class="content_col num">1</div>
							<div class="content_col apply_stuff">���պ�ͬԭ��</div>
							<div class="content_col description"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">2</div>
							<div class="content_col apply_stuff">����������</div>
							<div class="content_col description">���屣���赥λ����</div>
						</div>
						<div class="list_item">
							<div class="content_col num">3</div>
							<div class="content_col apply_stuff">�����������֤��</div>
							<div class="content_col description">δ�����˿��ṩ���ڱ��򻧼�֤��</div>
						</div>
						<div class="list_item">
							<div class="content_col num">4</div>
							<div class="content_col apply_stuff">���������֤�����д��ۻ����п�</div>
							<div class="content_col description">ע���˻�������Ϊ�����˱��˻���Ȩ��ȡ���ս���</div>
						</div>
					</div>
					<div class="sub_title">
						<p>����������������������嵥</p>
					</div>
					<p class="notice_info">��ί�����˴���ʱ�����ṩ�ͻ���Ȩί���鼰��ί�������֤��</p>
					<div class="service_list">
						<div class="list_item tip">
							<div class="content_col num">���</div>
							<div class="content_col stuff">����</div>
							<div class="content_col apply_project">������Ŀ</div>
							<div class="content_col need_stuff">
								Ӧ������<span>(������Ų鿴����)</span>
							</div>
						</div>
						<div class="list_item">
							<div class="content_col num">1</div>
							<div class="content_col stuff">���պ�ͬ</div>
							<div class="content_col apply_project cross"></div>
							<div class="content_col need_stuff cross"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">2</div>
							<div class="content_col stuff">����������</div>
							<div class="content_col apply_project cross"></div>
							<div class="content_col need_stuff cross"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">3</div>
							<div class="content_col stuff">�����˴��۸�ӡ��</div>
							<div class="content_col apply_project cross">����ҽ��������</div>
							<div class="content_col need_stuff cross">1��2��3��4��5��7��8��10</div>
						</div>
						<div class="list_item">
							<div class="content_col num">4</div>
							<div class="content_col stuff">�����������֤��</div>
							<div class="content_col apply_project cross"></div>
							<div class="content_col need_stuff cross"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">5</div>
							<div class="content_col stuff">���ﲡ��</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">6</div>
							<div class="content_col stuff">��ԺС��</div>
							<div class="content_col apply_project merge">����ҽ��סԺ��</div>
							<div class="content_col need_stuff merge">1��2��3��4��5��6��7��8��10</div>
						</div>
						<div class="list_item">
							<div class="content_col num">7</div>
							<div class="content_col stuff">ҽ�Ʒ��վ�ԭ��</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">8</div>
							<div class="content_col stuff">ҽ�Ʒ�����ϸ��</div>
							<div class="content_col apply_project merge">סԺ������</div>
							<div class="content_col need_stuff merge">1��2��3��4��6��9</div>
						</div>
						<div class="list_item">
							<div class="content_col num">9</div>
							<div class="content_col stuff">ҽ�Ʒ��վݸ�ӡ��</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">10</div>
							<div class="content_col stuff">�����¹�֤��</div>
							<div class="content_col apply_project merge">סԺ������</div>
							<div class="content_col need_stuff merge">1��2��3��4��6��7��8</div>
						</div>
						<div class="list_item">
							<div class="content_col num">11</div>
							<div class="content_col stuff">�ش󼲲����֤��</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">12</div>
							<div class="content_col stuff">�˲м�������</div>
							<div class="content_col apply_project merge">�ش󼲲���</div>
							<div class="content_col need_stuff merge">1��2��3��4��11</div>
						</div>
						<div class="list_item">
							<div class="content_col num">13</div>
							<div class="content_col stuff">����֤��</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">14</div>
							<div class="content_col stuff">����ע��֤��</div>
							<div class="content_col apply_project merge">�м���</div>
							<div class="content_col need_stuff merge">1��2��3��4��12</div>
						</div>
						<div class="list_item">
							<div class="content_col num">15</div>
							<div class="content_col stuff">ʬ�崦��֤��</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">16</div>
							<div class="content_col stuff">������������֤��</div>
							<div class="content_col apply_project">�����</div>
							<div class="content_col need_stuff">1��2��3��4��13��14��15��16</div>
						</div>
					</div>
					<p class="remark_info">��ע��</p>
					<p class="remark_info">1.��������Ȩ�����Ǳ�����ͬ�����ˣ�</p>
					<p class="remark_info">2.������������ί�����˴��죬���ṩ����ǩ��ȷ�ϵ���Ȩί���飻</p>
					<p class="remark_info">3.���ϲ���Ϊ��������ʱ����Ļ������ϣ���˾���ݰ����ľ��������Ϊ��Ҫ���������ϣ������и�֪��</p>
				</div>
			</div>
		</div>
	</div>
</div>