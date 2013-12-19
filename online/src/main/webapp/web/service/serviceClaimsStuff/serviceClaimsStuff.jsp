<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%--<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include> --%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">理赔材料</li>
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
						<p>理赔材料</p>
					</div>
					<div class="main_title">理赔申请应备材料一览表</div>
					<p class="notice_info">以下材料为客户办理理赔申请时所需要的基本材料，但由于保险事故的具体情况不同，本公司可能会需要客户提供一些与本次理赔相关的其他资</p>
					<div class="sub_title">
						<p>基本申请材料</p>
					</div>
					<p class="notice_info">如委托他人代办时，需提供客户授权委托书及受委托人身份证。</p>
					<div class="service_list">
						<div class="list_item tip">
							<div class="content_col num">序号</div>
							<div class="content_col apply_stuff">申请材料</div>
							<div class="content_col description">说明</div>
						</div>
						<div class="list_item">
							<div class="content_col num">1</div>
							<div class="content_col apply_stuff">保险合同原件</div>
							<div class="content_col description"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">2</div>
							<div class="content_col apply_stuff">理赔申请书</div>
							<div class="content_col description">团体保险需单位盖章</div>
						</div>
						<div class="list_item">
							<div class="content_col num">3</div>
							<div class="content_col apply_stuff">被保险人身份证明</div>
							<div class="content_col description">未成年人可提供户口本或户籍证明</div>
						</div>
						<div class="list_item">
							<div class="content_col num">4</div>
							<div class="content_col apply_stuff">受益人身份证、银行存折或银行卡</div>
							<div class="content_col description">注：账户名必须为受益人本人或被授权领取保险金者</div>
						</div>
					</div>
					<div class="sub_title">
						<p>分险种类型理赔申请材料清单</p>
					</div>
					<p class="notice_info">如委托他人代办时，需提供客户授权委托书及受委托人身份证。</p>
					<div class="service_list">
						<div class="list_item tip">
							<div class="content_col num">序号</div>
							<div class="content_col stuff">材料</div>
							<div class="content_col apply_project">申请项目</div>
							<div class="content_col need_stuff">
								应备材料<span>(按照序号查看材料)</span>
							</div>
						</div>
						<div class="list_item">
							<div class="content_col num">1</div>
							<div class="content_col stuff">保险合同</div>
							<div class="content_col apply_project cross"></div>
							<div class="content_col need_stuff cross"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">2</div>
							<div class="content_col stuff">理赔申请书</div>
							<div class="content_col apply_project cross"></div>
							<div class="content_col need_stuff cross"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">3</div>
							<div class="content_col stuff">受益人存折复印件</div>
							<div class="content_col apply_project cross">意外医疗门诊类</div>
							<div class="content_col need_stuff cross">1、2、3、4、5、7、8、10</div>
						</div>
						<div class="list_item">
							<div class="content_col num">4</div>
							<div class="content_col stuff">被保险人身份证明</div>
							<div class="content_col apply_project cross"></div>
							<div class="content_col need_stuff cross"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">5</div>
							<div class="content_col stuff">门诊病历</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">6</div>
							<div class="content_col stuff">出院小结</div>
							<div class="content_col apply_project merge">意外医疗住院类</div>
							<div class="content_col need_stuff merge">1、2、3、4、5、6、7、8、10</div>
						</div>
						<div class="list_item">
							<div class="content_col num">7</div>
							<div class="content_col stuff">医疗费收据原单</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">8</div>
							<div class="content_col stuff">医疗费用明细单</div>
							<div class="content_col apply_project merge">住院津贴类</div>
							<div class="content_col need_stuff merge">1、2、3、4、6、9</div>
						</div>
						<div class="list_item">
							<div class="content_col num">9</div>
							<div class="content_col stuff">医疗费收据复印件</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">10</div>
							<div class="content_col stuff">意外事故证明</div>
							<div class="content_col apply_project merge">住院费用类</div>
							<div class="content_col need_stuff merge">1、2、3、4、6、7、8</div>
						</div>
						<div class="list_item">
							<div class="content_col num">11</div>
							<div class="content_col stuff">重大疾病诊断证明</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">12</div>
							<div class="content_col stuff">伤残鉴定报告</div>
							<div class="content_col apply_project merge">重大疾病类</div>
							<div class="content_col need_stuff merge">1、2、3、4、11</div>
						</div>
						<div class="list_item">
							<div class="content_col num">13</div>
							<div class="content_col stuff">死亡证明</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">14</div>
							<div class="content_col stuff">户口注销证明</div>
							<div class="content_col apply_project merge">残疾类</div>
							<div class="content_col need_stuff merge">1、2、3、4、12</div>
						</div>
						<div class="list_item">
							<div class="content_col num">15</div>
							<div class="content_col stuff">尸体处理证明</div>
							<div class="content_col apply_project"></div>
							<div class="content_col need_stuff"></div>
						</div>
						<div class="list_item">
							<div class="content_col num">16</div>
							<div class="content_col stuff">身故受益人身份证明</div>
							<div class="content_col apply_project">身故类</div>
							<div class="content_col need_stuff">1、2、3、4、13、14、15、16</div>
						</div>
					</div>
					<p class="remark_info">备注：</p>
					<p class="remark_info">1.理赔申请权利人是保单合同受益人；</p>
					<p class="remark_info">2.理赔申请人若委托他人代办，需提供本人签字确认的授权委托书；</p>
					<p class="remark_info">3.以上材料为理赔申请时所需的基本材料，公司根据案件的具体情况认为必要的其他材料，作另行告知。</p>
				</div>
			</div>
		</div>
	</div>
</div>