<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<!--ie6版本过低提示-->
		<div class="notice">
			<div class="notice_panel">
				<div class="notice_tip">您目前使用的浏览器版本过低，为了更好的浏览效果，建议使用<a href="http://windows.microsoft.com/zh-CN/internet-explorer/products/ie/home" target="view_window">IE7</a>以上或<a href="http://www.firefox.com.cn/download/" target="view_window">firefox</a>、<a href="http://www.google.com/chrome/" target="view_window">chrome</a>浏览器</div>
				<div class="notice_close">关闭</div>
			</div>
		</div>
		<!--加载页-->
<!-- 		<div id="loading"> -->
<%-- 			<script src="${ctx}/global/js/common/loading.js" type="text/javascript"></script> --%>
<!-- 			<div class="load_bg"></div> -->
<!-- 			<div class="cmsg">  -->
<!-- 				<div class="msg">正在加载中 <span id="load_percent">69</span>%</div>  -->
<!-- 				<div class="lpb">  -->
<!-- 					<div class="lpt"> -->
<!-- 						<div class="lptl"></div> -->
<!-- 						<div id="lptc" class="lptc"></div> -->
<!-- 						<div class="lptr"></div> -->
<!-- 					</div>  -->
<!-- 				</div>  -->
<!-- 			</div> -->
<!-- 		</div> -->
		<!--页头部分 开始-->
		<div class="header">
			<div class="top_nav">
				<div class="h_layout">
					<p class="login_info">
					<c:if test="${geUserPersonal == null }">
						朋友，欢迎您来到信泰！请 
						<a href="${ctx}/web/user/login/index.jsp" class="login">登录</a> 
						<a href="${ctx }/web/user/register/index.jsp" class="reg">免费注册</a>
					</c:if>
					<c:if test="${geUserPersonal != null }">
						您好，
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
								
						，欢迎登录
						<a href="${ctx}/login/loginOut.do" class="login">退出</a>
					</c:if>
					</p>
					<ul class="quick_menu">
						<li class="menu_item"><a href="${ctx}/memberCenter/homePage.do">会员中心</a></li>
						<li class="menu_item"><a href="${ctx}/web/map/index.jsp">网站地图</a></li>
						<li class="menu_item"><a href="http://weibo.com/xintairenshou" target="_blank">官方微博</a></li>
						<li class="menu_item last"><a href="<%=basePath %>/index.jsp" onclick="window.external.addFavorite(this.href,this.title);return false;" title='信泰保险网上商城' rel="sidebar">加入收藏</a></li>
					</ul>
					<!-- <div class="search">
						<form action="#" name="search_form">
							<div class="search_input">
								<label id="search_label" for="search">查询您需要的产品</label>
								<input id="search" name="search" type="text"/>
								<div id="search_icon"></div>
							</div>
							<button type="submit">查询</button>
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
						信泰首页
					</div>
					<!--主菜单 开始-->
					<ul class="nav_menu">
						<li class="nav_item select"><a class="menu_a" href="${ctx}/index.jsp">首页</a></li>
						<li class="nav_item product_buy">
							<a class="menu_a" href="${ctx}/productsDisplay/onlineShop.do">产品选购</a>
							<div class="product_buy_select">
								<ul>
									<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152647034">理财险</a></li>
									<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152737017">意外险</a></li>
									<li><a href="${ctx}/sale/toQuote.do?eid=G120130902152808046">人身寿险</a></li>
								</ul>
							</div>
						</li>
						<li class="nav_item"><a class="menu_a" href="${ctx}/web/service/index.jsp">服务中心</a></li>
					</ul>
					<!--主菜单 结束-->
				</div>
			</div>
		</div>
		<!--页头部分 结束-->