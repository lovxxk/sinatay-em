<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<% 
System.out.println(request.getContextPath());

%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx}/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">��������</li>
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
								<p>��������</p>
							</div>
							<div class="sub_title"><p>ȫ�������������</p></div>
							<p class="notice_info">ȫ���ͷ��绰��<span>4006008890</span></p>
							<div class="hotline_list">
								<div class="hotline tip">	
									<div class="content_col num">���</div>
									<div class="content_col name">�ֹ�˾��ַ</div>
									<div class="content_col address">�ֹ�˾�绰</div>
									<div class="content_col time">����ʱ��</div>
								</div>
								<div class="hotline">
									<div class="content_col num">1</div>
									<div class="content_col name">�㽭</div>
									<div class="content_col address">0571-87116823</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">2</div>
									<div class="content_col name">����</div>
									<div class="content_col address">025-68536149&nbsp;&nbsp;&nbsp;025-68536145</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">3</div>
									<div class="content_col name">���� </div>
									<div class="content_col address">010-52612019</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">4</div>
									<div class="content_col name">�ӱ�</div>
									<div class="content_col address">0311-85298966</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">5</div>
									<div class="content_col name">����</div>
									<div class="content_col address">0591-88013801-673</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">6</div>
									<div class="content_col name">����</div>
									<div class="content_col address">0371-65358558</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">7</div>
									<div class="content_col name">ɽ��</div>
									<div class="content_col address">0531-83183922</div>
									<div class="content_col time cross">��һ ~ ����</div>
								</div>
								<div class="hotline">
									<div class="content_col num">8</div>
									<div class="content_col name">������</div>
									<div class="content_col address">0451-55550840</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">9</div>
									<div class="content_col name">����</div>
									<div class="content_col address">024-22562277</div>
									<div class="content_col time cross">���磺9:00��11:30</div>
								</div>
								<div class="hotline">
									<div class="content_col num">10</div>
									<div class="content_col name">����</div>
									<div class="content_col address">0574-83899507</div>
									<div class="content_col time cross">���磺13:30��17:30</div>
								</div>
								<div class="hotline">
									<div class="content_col num">11</div>
									<div class="content_col name">�Ϻ�</div>
									<div class="content_col address">021-60650303</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">12</div>
									<div class="content_col name">����</div>
									<div class="content_col address">0791-86156507</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">13</div>
									<div class="content_col name">����</div>
									<div class="content_col address">027-59365700-46094</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">14</div>
									<div class="content_col name">����</div>
									<div class="content_col address">0592-5896904</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">15</div>
									<div class="content_col name">�㶫</div>
									<div class="content_col address">020-66815955</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">16</div>
									<div class="content_col name">�ൺ</div>
									<div class="content_col address">0532-86681826</div>
									<div class="content_col time cross"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">17</div>
									<div class="content_col name">����</div>
									<div class="content_col address">0755-23912362</div>
									<div class="content_col time"></div>
								</div>
								<div class="hotline">
									<div class="content_col num">18</div>
									<div class="content_col name">ȫ���ͷ�����</div>
									<div class="content_col address">400-600-8890</div>
									<div class="content_col time">8:30-20:30</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
		