<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<!--ie6�汾������ʾ-->
		<div class="notice">
			<div class="notice_panel">
				<div class="notice_tip">��Ŀǰʹ�õ�������汾���ͣ�Ϊ�˸��õ����Ч��������ʹ��<a href="http://windows.microsoft.com/zh-CN/internet-explorer/products/ie/home" target="view_window">IE7</a>���ϻ�<a href="http://www.firefox.com.cn/download/" target="view_window">firefox</a>��<a href="http://www.google.com/chrome/" target="view_window">chrome</a>�����</div>
				<div class="notice_close">�ر�</div>
			</div>
		</div>
		<!--����ҳ-->
<!-- 		<div id="loading"> -->
<%-- 			<script src="${ctx}/global/js/common/loading.js" type="text/javascript"></script> --%>
<!-- 			<div class="load_bg"></div> -->
<!-- 			<div class="cmsg">  -->
<!-- 				<div class="msg">���ڼ����� <span id="load_percent">69</span>%</div>  -->
<!-- 				<div class="lpb">  -->
<!-- 					<div class="lpt"> -->
<!-- 						<div class="lptl"></div> -->
<!-- 						<div id="lptc" class="lptc"></div> -->
<!-- 						<div class="lptr"></div> -->
<!-- 					</div>  -->
<!-- 				</div>  -->
<!-- 			</div> -->
<!-- 		</div> -->
		<!--ҳͷ���� ��ʼ-->
		<div class="header">
			<div class="top_nav">
				<div class="h_layout">
					<p class="login_info">
					<c:if test="${geUserPersonal == null }">
						���ѣ���ӭ��������̩���� 
						<a href="${ctx}/web/user/login/index.jsp" class="login">��¼</a> 
						<a href="${ctx }/web/user/register/index.jsp" class="reg">���ע��</a>
					</c:if>
					<c:if test="${geUserPersonal != null }">
						���ã�
							<c:choose>
								<c:when test="${!empty geUserPersonal.userName }">
									${geUserPersonal.userName }
								</c:when>
								<c:when test="${!empty geUserPersonal.alias }">
									${geUserPersonal.alias }
								</c:when>
								<c:otherwise>
									${geUserPersonal.userAccount }
								</c:otherwise>
							</c:choose>
								
						����ӭ��¼
						<a href="${ctx}/login/loginOut.do" class="login">�˳�</a>
					</c:if>
					</p>
					<ul class="quick_menu">
						<li class="menu_item"><a href="${ctx}/memberCenter/homePage.do">��Ա����</a></li>
						<li class="menu_item"><a href="${ctx}/web/map/index.jsp">��վ��ͼ</a></li>
						<li class="menu_item"><a href="http://weibo.com/xintairenshou" target="_blank">�ٷ�΢��</a></li>
						<li class="menu_item last"><a href="<%=basePath %>/index.jsp" onclick="window.external.addFavorite(this.href,this.title);return false;" title='��̩���������̳�' rel="sidebar">�����ղ�</a></li>
					</ul>
					<!-- <div class="search">
						<form action="#" name="search_form">
							<div class="search_input">
								<label id="search_label" for="search">��ѯ����Ҫ�Ĳ�Ʒ</label>
								<input id="search" name="search" type="text"/>
								<div id="search_icon"></div>
							</div>
							<button type="submit">��ѯ</button>
						</form>
					</div> -->
				</div>
			</div>
			<div class="head_mid">
				<div class="h_layout">
					<div class="logo">
						<a href="${ctx }/index.jsp"></a>
					</div>
					<div class="main_title">
						��̩��ҳ
					</div>
					<!--���˵� ��ʼ-->
					<ul class="nav_menu">
						<li class="nav_item select"><a class="menu_a" href="${ctx}/index.jsp">��ҳ</a></li>
						<li class="nav_item product_buy">
							<a class="menu_a" href="${ctx}/productsDisplay/onlineShop.do">��Ʒѡ��</a>
							<div class="product_buy_select">
								<ul>
									<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152647034">�����</a></li>
									<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017">������</a></li>
									<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152808046">��������</a></li>
								</ul>
							</div>
						</li>
						<li class="nav_item"><a class="menu_a" href="${ctx}/web/service/index.jsp">��������</a></li>
					</ul>
					<!--���˵� ����-->
				</div>
			</div>
		</div>
		<!--ҳͷ���� ����-->