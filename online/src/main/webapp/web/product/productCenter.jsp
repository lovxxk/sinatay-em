<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp ">首页</a><span> &gt;</span></li>
				<li class="at">产品选购</li>
			</ul>
		</div>
		<div class="product_main">
			<div class="left_dynamic">
				<jsp:include page="/web/product/common/product_map.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_commend.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_rank_left.jsp"></jsp:include>
				<div class="display"></div>
			</div>
			<div class="right_product">
				<div class="center_top">
					<div class="product_display">
						<a class="active" href="${ctx }/productsDisplay/onlineShop.do"><img src="${ctx }/resources/image/product/product_center_show1.jpg"></a>
						<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046"><img src="${ctx }/resources/image/product/product_center_show2.jpg"></a>
						<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034"><img src="${ctx }/resources/image/product/product_center_show3.jpg"></a>
						<div class="display_nav">
							<div class="active"></div>
							<div></div>
							<div></div>
						</div>
					</div>
					<div class="product_quote">
						<div class="quote_tab">
							<div class="tab_item select">交通工具</div>
						</div>
						<div class="quote_main">
							<div class="quote_body traffic">
								<div class="input_field selector">
									<label class="input_label">交通类型：</label>
									<ul class="input_select">
										<li class="select" tag="type"><div>飞机</div></li>
										<li tag="type"><div>火车</div></li>
										<li tag="type"><div>客运</div></li>
										<li tag="type"><div>轮船</div></li>
										<li tag="type"><div>自驾车</div></li>
									</ul>
								</div>
								<div class="input_field bottom selector">
									<label class="input_label">保险期间：</label>
									<ul class="input_select">
										<li class="select" tag="day"><div>1个月</div></li>
										<li tag="day"><div>6个月</div></li>
										<li tag="day"><div>1年</div></li>
									</ul>
								</div>
								<div class="action">
									<input type="hidden" id="type" name="type" value="0" />
									<input type="hidden" id="day" name="day" value="0" />
									<button class="quote_btn click_btn">立即报价</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="sub_cate_name">热销产品</div>
				<div class="product_area product_list">
					<div class="product_list_main page1">
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=0&day=0"><img src="${ctx}/resources/image/product/product_center_icon1.jpg"/></a>
								<div class="product_info">
									<div class="product_title accident"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=0&day=0">百万身驾—私家车车主专享保险计划（标准版）</a></div>
									<div class="base_info"><span>年龄：18-50岁</span><span>保险期间：30年</span><span>交费年期：5年、10年</span></div>
									<div class="product_instro">有车一族必备保险！100000名私家车主的共同选择，截止目前已累计提供1000亿的保障。</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li>私家车意外身故：<span>100万</span></li>
										<li>交通工具意外身故：<span>57.5万</span></li>
										<li>意外身故/伤残：<span>7.5万</span></li>
										<li>一般身故保障</li>
										<li>满期生存保障金</li>
										<li></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">1127.5</span><span class="p2">元/年起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=0&day=0">了解详情</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=1&day=0"><img src="${ctx}/resources/image/product/product_center_icon2.jpg"/></a>
								<div class="product_info">
									<div class="product_title accident"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=1&day=0">百万身驾—私家车车主专享保险计划（豪华版）</a></div>
									<div class="base_info"><span>年龄：18-50岁</span><span>保险期间：30年</span><span>交费年期：5年、10年</span></div>
									<div class="product_instro">有车一族必备保险！私家车意外最高保障高达200万；100000名私家车主的共同选择，截止目前已累计提供1000亿的保障。</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li>私家车意外身故：<span>200万</span></li>
										<li>交通工具意外身故：<span>115万</span></li>
										<li>一般身故保障</li>
										<li>意外身故/伤残：<span>15万</span></li>
										<li>满期生存保障金</li>
										<li></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">2255</span><span class="p2">元/年起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=1&day=0">了解详情</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=0&day=0"><img src="${ctx}/resources/image/product/product_center_icon3.jpg"/></a>
								<div class="product_info">
									<div class="product_title finance"><a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=0&day=0">懒人理财宝—<span>稳健性短期投资首选！</span></a></div>
									<div class="base_info"><span>年龄：18-64岁</span><span>购买额度：500元起</span><span>单笔最高购买金额：19万</span></div>
									<div class="product_instro">比存银行收益高，比购买基金风险小，最低购买额度500元起；无初始扣费，保单管理费；线上领取更便捷！三个方案，供您挑选！</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li>持有期：<span>6个月</span></li>
										<li>持有期：<span>9个月</span></li>
										<li>持有期：<span>1年</span></li>
										<li>预期收益：<span>5.02%</span></li>
										<li>预期收益：<span>5.12%</span></li>
										<li>预期收益：<span>5.3%</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">500</span><span class="p2">元起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=0&day=0">了解详情</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=0&day=0"><img src="${ctx}/resources/image/product/product_center_icon4.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=0&day=0">航空意外伤害保险</a></div>
									<div class="base_info"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>
									<div class="product_instro">你还在买着单次20元60万航空意外险吗？信泰航空意外险7天100万只需要7元钱，有比较才有差异，赶紧下手吧！</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">保险期间：<span> 1个月 ／ 6个月 ／ 1年</span></li>
										<li>保额：<span>50万－100万</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">5</span><span class="p2">元起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=0&day=0">了解详情</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=1&day=0"><img src="${ctx}/resources/image/product/product_center_icon5.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=1&day=0">轨道意外伤害保险</a></div>
									<div class="base_info"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>
									<div class="product_instro">动车、火车、高铁、地铁都在保障范围，出差、旅行、度假、探亲、上下班，多一份保障，多一份安心！</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">保险期间：<span>1个月 ／ 6个月 ／ 1年</span></li>
										<li>保额：<span>10万－50万</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">1.4</span><span class="p2">元起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=1&day=0">了解详情</a></div>
								</div>
							</div>
						</div>	
					</div>
					<div class="product_list_main page2">
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=4&day=0"><img src="${ctx}/resources/image/product/product_center_icon6.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=4&day=0">乘用车意外伤害保险</a></div>
									<div class="base_info"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>
									<div class="product_instro">旅游、度假、自驾游、探亲、上下班，按需选择，价格低廉，快乐出行，安全第一！</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">保险期间：<span>1个月 ／ 6个月 ／ 1年</span></li>
										<li>保额：<span>10万－50万</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">12</span><span class="p2">元起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=4&day=0">了解详情</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=2&day=0"><img src="${ctx}/resources/image/product/product_center_icon7.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=2&day=0">客运汽车意外伤害保险</a></div>
									<div class="base_info"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>
									<div class="product_instro">你还在客运汽车站买保险吗？一次2元，保额2万？ 双城生活的夫妻、情侣和常回家探望父母的孝顺儿女们，赶紧重新做选择吧！</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">保险期间：<span> 1个月 ／ 6个月 ／ 1年</span></li>
										<li>保额：<span>10万－50万</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">5</span><span class="p2">元起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=2&day=0">了解详情</a></div>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<div class="">
								<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=3&day=0"><img src="${ctx}/resources/image/product/product_center_icon8.jpg"/></a>
								<div class="product_info">
									<div class="product_title transport"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=3&day=0">轮船意外伤害保险</a></div>
									<div class="base_info"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>
									<div class="product_instro">你是否正在计划与你的那个ta来一次泰坦尼克的浪漫之旅，面向大海，沐浴海风，感受滚滚红尘间的万种风情！海上风险，信泰来担！</div>
								</div>
							</div>
							<div class="more_info">
								<div class="product_clause">
									<ul>
										<li class="c1">保险期间：<span>1个月 ／ 6个月 ／ 1年</span></li>
										<li>保额：<span>10万－50万</span></li>
									</ul>
								</div>
								<div class="product_price">
									<div class="price"><span class="p1">3</span><span class="p2">元起</span></div>
									<div class="buy click_btn"><a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=3&day=0">了解详情</a></div>
								</div>
							</div>
						</div>
					</div>
					<div class="page_index">
						<div class="page prev_page" title="1"><a>上一页</a></div>
						<div class="page page_num now" title="1"><a>1</a></div>
						<div class="page page_num" title="2"><a>2</a></div>
						<div class="page next_page" title="2"><a>下一页</a></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>