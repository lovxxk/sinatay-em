<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
			<div class="h_layout">
				<div class="nav_index">
					<ul>
						<li><a href="${ctx}">��ҳ</a><span> &gt;</span></li>
						<li class="at">��Ա��ҳ</li>
					</ul>
				</div>
				<div class="member_main"><!-- item email -->
					<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
					<div class="right_content">
						<div class="title">
							<div class="block"></div>
							<p>���Ӻ�������</p>
						</div>
						<h2 class="p1">��̼������������������̩����������ʹ�õ���֪ͨ�飬ȡ��ֽ��֪ͨ�飡</h2>
						<div class="benefit">
							<h4>��ʹ�õ��Ӻ����ĺô���</h4>
							<p><span>��Ч</span>���ĺ��ͨ�������ڹ�˾�ĵ������估ʱ���յ��Ӻ����������Ϣ�Ĵ���Ч�ʣ���ʱ�������ı�����Ϣ</p>
							<p><span>���</span>����ʱ���Ե�¼��̩���չ������е��Ӻ����Ĳ�ѯ</p>
							<p><span>��ȫ</span>ʹ�õ��Ӻ�������ͨ����ָ����������յ��Ӻ��������棬��������ʱ��¼��̩���չ������в��ĺ����·���</p>
							<p><span>����</span>��Լֽ���ź���ʹ�ã�����ɭ����Դ���̻�����</p>
						</div>
						<div class="support">
							<h4>����֧�ֵĵ��Ӻ������͡�</h4>
							<p>  �ֺ�֪ͨ�顢���汣�ս���ȡ֪ͨ�顢�����ո�����ȱ��桢���������ȡ֪ͨ�顢�Ե�֪ͨ�顢������Ԥ��ֹ֪ͨ�顣</p>
							<p>  ѡ�����֪ͨ�飬ѡ��ȫ�µ����ʽ��ֻ��򵥸ı䣬�Ϳ�Ϊ������סһĨ��ɫ���������֪ͨ���ѯ���������̣����������<a href="${ctx }/web/service/email/index.jsp" style="color:red;">http://wwww.sinatay.com/web/service/email/index.jsp</a>��ȡ��ֽ��֪ͨ�顱��</p>
						</div>
						<div class="action">
							<a class="open click_btn" href="" onclick="checkerUserInfoComplte();return false;">��ͨ����</a>
						</div>
						
						<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
						
						<input type="text" id="ctx" value="${ctx}" style="display:none;"/>
						<input type="text" id="ctx" value="${customer.userID}" style="display:none;"/>
					</div>
				</div>
			</div>
		</div>