<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ʒ����</li>
			</ul>
		</div>
		<div class="product_main">
			<div class="left_dynamic">
				<div class="product_map">
					<div class="category child">
						<div class="cate_name"><div></div>�ٶ�����</div>
						<a href="#">����</a>
						<a href="#">����</a>
						<a href="#" class="last">����</a>
					</div>
					<div class="category traffic">
						<div class="cate_name"><div></div>��ͨ����</div>
						<a href="#">�ɻ�</a>
						<a href="#">��</a>
						<a href="#" class="last">�ִ�</a>
						<a href="#">�ͳ�</a>
						<a href="#">˽�ҳ�</a>
					</div>
					<div class="category finance">
						<div class="cate_name"><div></div>�����</div>
						<a href="#">1��</a>
						<a href="#">9����</a>
						<a href="#" class="last">6����</a>
					</div>
					<div class="category life">
						<div class="cate_name"><div></div>��������</div>
						<a href="#">50��</a>
						<a href="#">100��</a>
						<a href="#" class="last">200��</a>
					</div>
				</div>
				<div class="product_commend dynamic_frame">
					<p class="dynamic_title">��Ʒ�Ƽ�</p>
					<div class="product_container">
						<a href="#" class="car">�г�һ��</a>
						<a href="#" class="female">�г�Ů��</a>
						<a href="#" class="bag">������</a>
						<a href="#" class="parent">������ĸ</a>
						<a href="#" class="travel">����һ��</a>
						<a href="#" class="home">��ܰ��ͥ</a>
					</div>
				</div>
				<div class="product_rank dynamic_frame">
					<p class="dynamic_title">��Ʒ��������</p>
					<div class="rank_list">
						<ul class="dynamic_list">
							<li>
								<div class="rank">
									<div class="rank_img car"><a href="#"></a></div>
									<p class="rank_title"><a href="#">��Ʒ���Ʋ�Ʒ���Ʋ� Ʒ���Ʋ�Ʒ����</a></p>
								</div>
							</li>
							<li>
								<div class="rank">
									<div class="rank_img female"><a href="#"></a></div>
									<p class="rank_title"><a href="#">��Ʒ���Ʋ�Ʒ���Ʋ� Ʒ���Ʋ�Ʒ����</a></p>
								</div>
							</li>
							<li>
								<div class="rank">
									<div class="rank_img bag"><a href="#"></a></div>
									<p class="rank_title"><a href="#">��Ʒ���Ʋ�Ʒ���Ʋ� Ʒ���Ʋ�Ʒ����</a></p>
								</div>
							</li>
							<li>
								<div class="rank">
									<div class="rank_img parent"><a href="#"></a></div>
									<p class="rank_title"><a href="#">��Ʒ���Ʋ�Ʒ���Ʋ� Ʒ���Ʋ�Ʒ����</a></p>
								</div>
							</li>
							<li class="last">
								<div class="rank">
									<div class="rank_img home"><a href="#"></a></div>
									<p class="rank_title"><a href="#">��Ʒ���Ʋ�Ʒ���Ʋ� Ʒ���Ʋ�Ʒ����</a></p>
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
				<div class="sub_cate_name">����</div>
				<div class="product_list">
					<div class="product_list_main">
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_1.png"/>
							<div class="product_info">
								<div class="info_box product_title">��̩����������ȫ���գ��ֺ��ͣ�A��</div>
								<div class="info_box product_instro">����������ÿ��ı�������������ģ��ɻ�ñ���ͬ���ս��10%�ĸ߶����汣�ս���������ÿ����飬��������ȶ���Ϊ���������ṩ����Ĳ�����</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">�����ڼ䣺7��</li>
										<li class="right">��߱��5000000.00</li>
										<li class="left">�������ͣ�������</li>
										<li class="right price"><span class="p1">�۸�</span><span class="p2">��</span><span class="p3">1000</span></li>
										<li class="buy click_btn">��������</li>
									</ul>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_2.png"/>
							<div class="product_info">
								<div class="info_box product_title">��̩����������ȫ���գ��ֺ��ͣ�A��</div>
								<div class="info_box product_instro">����������ÿ��ı�������������ģ��ɻ�ñ���ͬ���ս��10%�ĸ߶����汣�ս���������ÿ����飬��������ȶ���Ϊ���������ṩ����Ĳ�����</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">�����ڼ䣺7��</li>
										<li class="right">��߱��5000000.00</li>
										<li class="left">�������ͣ�������</li>
										<li class="right price"><span class="p1">�۸�</span><span class="p2">��</span><span class="p3">1000</span></li>
										<li class="buy click_btn">��������</li>
									</ul>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_1.png"/>
							<div class="product_info">
								<div class="info_box product_title">��̩����������ȫ���գ��ֺ��ͣ�A��</div>
								<div class="info_box product_instro">����������ÿ��ı�������������ģ��ɻ�ñ���ͬ���ս��10%�ĸ߶����汣�ս���������ÿ����飬��������ȶ���Ϊ���������ṩ����Ĳ�����</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">�����ڼ䣺7��</li>
										<li class="right">��߱��5000000.00</li>
										<li class="left">�������ͣ�������</li>
										<li class="right price"><span class="p1">�۸�</span><span class="p2">��</span><span class="p3">1000</span></li>
										<li class="buy click_btn">��������</li>
									</ul>
								</div>
							</div>
						</div>	
						<div class="product_item">
							<img src="${ctx }/resources/image/product/product_child_2.png"/>
							<div class="product_info">
								<div class="info_box product_title">��̩����������ȫ���գ��ֺ��ͣ�A��</div>
								<div class="info_box product_instro">����������ÿ��ı�������������ģ��ɻ�ñ���ͬ���ս��10%�ĸ߶����汣�ս���������ÿ����飬��������ȶ���Ϊ���������ṩ����Ĳ�����</div>
								<div class="info_box product_content">
									<ul>
										<li class="left">�����ڼ䣺7��</li>
										<li class="right">��߱��5000000.00</li>
										<li class="left">�������ͣ�������</li>
										<li class="right price"><span class="p1">�۸�</span><span class="p2">��</span><span class="p3">1000</span></li>
										<li class="buy click_btn">��������</li>
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