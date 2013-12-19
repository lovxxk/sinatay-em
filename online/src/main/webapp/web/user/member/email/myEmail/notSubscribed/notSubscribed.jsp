<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
			<div class="h_layout">
				<div class="nav_index">
					<ul>
						<li><a href="${ctx}">首页</a><span> &gt;</span></li>
						<li class="at">会员首页</li>
					</ul>
				</div>
				<div class="member_main"><!-- item email -->
					<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
					<div class="right_content">
						<div class="title">
							<div class="block"></div>
							<p>电子函件订阅</p>
						</div>
						<h2 class="p1">低碳环保，从你我做起，信泰人寿邀请您使用电子通知书，取消纸质通知书！</h2>
						<div class="benefit">
							<h4>【使用电子函件的好处】</h4>
							<p><span>高效</span>订阅后可通过留存在公司的电子邮箱及时接收电子函件，提高信息的传递效率，及时掌握您的保单信息</p>
							<p><span>便捷</span>您随时可以登录信泰保险官网进行电子函件的查询</p>
							<p><span>安全</span>使用电子函件可以通过您指定的邮箱接收电子函件并保存，并可以随时登录信泰保险官网进行查阅和重新发送</p>
							<p><span>环保</span>节约纸质信函的使用，保护森林资源，绿化环境</p>
						</div>
						<div class="support">
							<h4>【可支持的电子函件类型】</h4>
							<p>  分红通知书、生存保险金领取通知书、万能险个单年度报告、增额红利领取通知书、自垫通知书、万能险预中止通知书。</p>
							<p>  选择电子通知书，选择全新的生活方式，只需简单改变，就可为地球留住一抹绿色！具体电子通知书查询及设置流程，详情请登入<a href="${ctx }/web/service/email/index.jsp" style="color:red;">http://wwww.sinatay.com/web/service/email/index.jsp</a>“取消纸质通知书”。</p>
						</div>
						<div class="action">
							<a class="open click_btn" href="" onclick="checkerUserInfoComplte();return false;">开通服务</a>
						</div>
						
						<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
						
						<input type="text" id="ctx" value="${ctx}" style="display:none;"/>
						<input type="text" id="ctx" value="${customer.userID}" style="display:none;"/>
					</div>
				</div>
			</div>
		</div>