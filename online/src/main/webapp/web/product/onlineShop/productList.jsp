<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">首页</a><span> &gt;</span></li>
				<li class="at">产品中心</li>
			</ul>
		</div>
		<div class="product_main">
			<div class="left_dynamic">
				<div class="product_map">
					<div class="category child">
						<div class="cate_name"><div></div>少儿保险</div>
						<a href="#">教育</a>
						<a href="#">疾病</a>
						<a href="#" class="last">意外</a>
					</div>
					<div class="category traffic">
						<div class="cate_name"><div></div>交通工具</div>
						<a href="#">飞机</a>
						<a href="#">火车</a>
						<a href="#" class="last">轮船</a>
						<a href="#">客车</a>
						<a href="#">私家车</a>
					</div>
					<div class="category finance">
						<div class="cate_name"><div></div>理财险</div>
						<a href="#">1年</a>
						<a href="#">9个月</a>
						<a href="#" class="last">6个月</a>
					</div>
					<div class="category life">
						<div class="cate_name"><div></div>人身寿险</div>
						<a href="#">50万</a>
						<a href="#">100万</a>
						<a href="#" class="last">200万</a>
					</div>
				</div>
				<div class="product_commend dynamic_frame">
					<p class="dynamic_title">产品推荐</p>
					<div class="product_container">
						<a href="#" class="car">有车一族</a>
						<a href="#" class="female">市场女性</a>
						<a href="#" class="bag">背包客</a>
						<a href="#" class="parent">健康父母</a>
						<a href="#" class="travel">商旅一族</a>
						<a href="#" class="home">温馨家庭</a>
					</div>
				</div>
				<div class="product_rank dynamic_frame">
					<p class="dynamic_title">产品销售排行</p>
					<div class="rank_list">
						<ul class="dynamic_list">
							<li>
								<div class="rank">
									<div class="rank_img car"><a href="#"></a></div>
									<p class="rank_title"><a href="#">产品名称产品名称产 品名称产品的名</a></p>
								</div>
							</li>
							<li>
								<div class="rank">
									<div class="rank_img female"><a href="#"></a></div>
									<p class="rank_title"><a href="#">产品名称产品名称产 品名称产品的名</a></p>
								</div>
							</li>
							<li>
								<div class="rank">
									<div class="rank_img bag"><a href="#"></a></div>
									<p class="rank_title"><a href="#">产品名称产品名称产 品名称产品的名</a></p>
								</div>
							</li>
							<li>
								<div class="rank">
									<div class="rank_img parent"><a href="#"></a></div>
									<p class="rank_title"><a href="#">产品名称产品名称产 品名称产品的名</a></p>
								</div>
							</li>
							<li class="last">
								<div class="rank">
									<div class="rank_img home"><a href="#"></a></div>
									<p class="rank_title"><a href="#">产品名称产品名称产 品名称产品的名</a></p>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="display"></div>
			</div>
			<div class="right_product">
				<div class="category_show">
					<img src="${ctx }/resources/image/product/child_category_bg.png">
				</div>
				<c:forEach items="${directoryAttributeRelateList}" var="geDirectory" begin="0" end="${listSize}" step="1" varStatus="status">
					
				</c:forEach>
				<div class="sub_cate_name">教育</div>
				<div class="product_list">
					<div class="product_list_main">
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_1.png"/>
							<div class="product_info">
								<div class="info_box product_title">信泰富贵人生两全保险（分红型）A款</div>
								<div class="info_box product_instro">被保险人在每年的保单周年日生存的，可获得本合同保险金额10%的高额生存保险金。生存利益每年相伴，收益持续稳定，为您的生活提供丰沛的补贴。</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">保险期间：7天</li>
										<li class="right">最高保额：5000000.00</li>
										<li class="left">保险类型：意外险</li>
										<li class="right price"><span class="p1">价格：</span><span class="p2">￥</span><span class="p3">1000</span></li>
										<li class="buy click_btn">立即购买</li>
									</ul>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_2.png"/>
							<div class="product_info">
								<div class="info_box product_title">信泰富贵人生两全保险（分红型）A款</div>
								<div class="info_box product_instro">被保险人在每年的保单周年日生存的，可获得本合同保险金额10%的高额生存保险金。生存利益每年相伴，收益持续稳定，为您的生活提供丰沛的补贴。</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">保险期间：7天</li>
										<li class="right">最高保额：5000000.00</li>
										<li class="left">保险类型：意外险</li>
										<li class="right price"><span class="p1">价格：</span><span class="p2">￥</span><span class="p3">1000</span></li>
										<li class="buy click_btn">立即购买</li>
									</ul>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_1.png"/>
							<div class="product_info">
								<div class="info_box product_title">信泰富贵人生两全保险（分红型）A款</div>
								<div class="info_box product_instro">被保险人在每年的保单周年日生存的，可获得本合同保险金额10%的高额生存保险金。生存利益每年相伴，收益持续稳定，为您的生活提供丰沛的补贴。</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">保险期间：7天</li>
										<li class="right">最高保额：5000000.00</li>
										<li class="left">保险类型：意外险</li>
										<li class="right price"><span class="p1">价格：</span><span class="p2">￥</span><span class="p3">1000</span></li>
										<li class="buy click_btn">立即购买</li>
									</ul>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_2.png"/>
							<div class="product_info">
								<div class="info_box product_title">信泰富贵人生两全保险（分红型）A款</div>
								<div class="info_box product_instro">被保险人在每年的保单周年日生存的，可获得本合同保险金额10%的高额生存保险金。生存利益每年相伴，收益持续稳定，为您的生活提供丰沛的补贴。</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">保险期间：7天</li>
										<li class="right">最高保额：5000000.00</li>
										<li class="left">保险类型：意外险</li>
										<li class="right price"><span class="p1">价格：</span><span class="p2">￥</span><span class="p3">1000</span></li>
										<li class="buy click_btn">立即购买</li>
									</ul>
								</div>
							</div>
						</div>	
					</div>
					<div class="page_index">
						<div class="page prev_page"><a href="#">&nbsp;</a></div>
						<div class="page page_num now"><a href="#">1</a></div>
						<div class="page page_num"><a href="#">2</a></div>
						<div class="page page_num"><a href="#">3</a></div>
						<div class="page page_num"><a href="#">4</a></div>
						<div class="page next_page"><a href="#">&nbsp;</a></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>