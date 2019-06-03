<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta name="referrer" content="no-referrer" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-transform">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>联合商城-godv旗下全球美食优选网购商城-进口食品、母婴、营养保健品、生鲜、粮油、酒水饮料、休闲食品-GODV商城</title>
<meta name="Keywords"
	content="进口食品,网上超市,网上购物,进口奶粉,GODV商城,sfbest,母婴用品,营养保健品,生鲜食品,粮油,酒水,休闲食品">
<meta name="Description"
	content="GODV商城GODV旗下全球美食优选网购商城，精选来自60多个国家和地区的进口食品，正品行货，支持货到付款。销售包括进口奶粉、母婴用品、营养保健品、生鲜食品、粮油、酒水、休闲食品等近万种商品。">
<link rel="dns-prefetch" href="//pic.e3mall.cn">
<meta property="wb:webmaster" content="3a008ad947166307">
<link rel="stylesheet" type="text/css"
	href="/css/base_w1200.css?v=2016071333">
<link rel="stylesheet" type="text/css"
	href="/css/index.css?v=2016071312">
<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/js/global_index.js"></script>
<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
	top: 18px !important;
	left: 50% !important;
	margin-left: -100px !important;
	width: 200px !important;
	border: 2px rgba(255, 255, 255, .38) solid !important;
	border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
	margin-left: 0px !important;
}
</style>
</head>
<body>
	<!-- header start -->
	<jsp:include page="commons/header.jsp" />
	<jsp:include page="commons/mainmenu.jsp" />
	<!-- header end -->
	<!----row1------->
	<div class="slide_show" id="slide_show">
		<div class="indexW">
			<div id="index_slide" class="slide_wrap">
				<ol>
					<c:forEach items="${ad1List}" var="node" varStatus="status">
						<li><a name="sfbest_hp_hp_focus_${status.index}"
							class="fore_pic trackref" href="${node.url}" target="_blank">
								<img id="lunbo_1" alt="${node.title}" src="${node.pic}">
						</a></li>
					</c:forEach>
					<!-- 首页大广告 -->
					<!-- <li><a name="sfbest_hp_hp_focus_1" class="fore_pic trackref"
						href="/html/activity/1471934470.html" target="_blank"> <img
							id="lunbo_1" alt="8.30-9.5 月饼"
							src="/images/html/aa472cb9f9624d22d1fcfd1c4dffbe42.jpg">
					</a></li>
					<li><a name="sfbest_hp_hp_focus_2" class="fore_pic trackref"
						href="/html/activity/1471514387.html" target="_blank"> <img
							id="lunbo_2" alt="8.23-8.29大闸蟹"
							src="/images/html/516e4d058d1d65a3506e179642297b2d.jpg">
					</a></li> -->
				</ol>
			</div>
			<div class="rSide">
				<c:forEach items="${ad2List}" var="node">
					<a name="sfbest_hp_hp_focus_right-ad2"
						class="a-img r-img2 trackref" href="https://www.jd.com/"
						target="_blank"> <img alt="${node.title}" src="${node.pic}">
						<div class="rmask"></div>
				</c:forEach>
				<!-- 右边边栏的静态页面 -->
				<!-- <a name="sfbest_hp_hp_focus_right-ad2" class="a-img r-img2 trackref" href="/html/activity/1472471068.html" target="_blank">
          <img s="" alt="8.30-9.5" src="/images/html/51e357022c1f826f13211222831a4b13.jpg">
          <div class="rmask"></div> -->
				</a>
			</div>
		</div>
		<ul class="none" id="lunboNum">
			<c:forEach items="${ad1List }" varStatus="status">
				<li class="<c:if test="${status.index==0 }">cur</c:if>">${status.index+1 }</li>
			</c:forEach>
			<!-- <li class="cur">1</li>
		        <li class="">2</li>
		        <li class="">3</li>
		        <li class="">4</li>
		        <li class="">5</li>
		        <li class="">6</li>
		        <li class="">7</li>
		        <li class="">8</li> -->
		</ul>
		<div class="indexbg" id="indexbg">
			<dl style="left: -1903px;">
				<dd style="width: 1903px; background: rgb(20, 16, 13);"></dd>
				<dd style="width: 1903px; background: rgb(119, 96, 86);"></dd>
				<dd style="width: 1903px; background: rgb(17, 12, 55);"></dd>
				<dd style="width: 1903px; background: rgb(239, 244, 248);"></dd>
				<dd style="width: 1903px; background: rgb(231, 32, 37);"></dd>
				<dd style="width: 1903px; background: rgb(128, 29, 92);"></dd>
				<dd style="width: 1903px; background: rgb(117, 144, 1);"></dd>
				<dd style="width: 1903px; background: rgb(253, 213, 29);"></dd>
			</dl>
		</div>
	</div>
	<!----row1 end------->
	<!-- 口碑甄选 start -->
	<div class="indexW mt1">
		<div class="bestbuy">
			<div class="b_left">
				<h2>
					优选必买<span></span>
				</h2>
				<ul class="bbig" id="bigPerfect">
					<c:forEach items="${item1List}" var="node" varStatus="status">
						<li class="price_list0" goods="${node.id}" cid="${node.cid}"
							id="${node.id}"><a href="/item/${node.id}.html"
							title="${node.title}" target="_blank"><img class="lazy"
								src="${node.image}" style="display: inline;"></a>
							<div class="gBtn p-btn bbtn" style="top: 260px;">
								<a pid="${node.id}"
									data_url="http://p02.e3mall.cn/2016/1800215383/middle_1800215383_1_1/160x160.jpg"
									href="javascript:void(0)" indexflag="1">加入购物车</a>
							</div>
							<div class="bprice" id="priceK_b_215383_0">
								<span><sup>￥</sup></span>${node.price/100}
							</div></li>
					</c:forEach>

					<!-- 静态页面 -->

					<!-- <li class="price_list0" goods="215383" eid="b_215383_0"
						id="cx_b_215383_0"><a href="#" title="红肉火龙果1000g（2个装）"
						target="_blank"><img class="lazy"
							src="/images/html/95256f5b2857ec28914f631532508d76.jpg"
							style="display: inline;"></a>
						<div class="gBtn p-btn bbtn" style="top: 260px;">
							<a pid="215383"
								data_url="http://p02.e3mall.cn/2016/1800215383/middle_1800215383_1_1/160x160.jpg"
								href="javascript:void(0)" indexflag="1">加入购物车</a>
						</div>
						<div class="bprice" id="priceK_b_215383_0">
							<span><sup>￥</sup></span>19.9
						</div></li>-->

				</ul>
			</div>
			<!-- 口碑甄选 end -->
			<div class="rSide1">

				<div class="rImg2">
					<a name="sfbest_hp_hp_news_right-ad" class="trackref"
						href="https://www.jd.com/" target="_blank"><img salt="9.2-9.5"
						src="/images/goods/751d2091c008c2a49c1934545730f041.jpg"></a>
				</div>

				<div class="sfNews">
					<div class="rTitle">
						<h2>最新动态</h2>
						<a href="/index/footerService.html" target="_blank" class="more">更多&gt;</a>
					</div>
					<ul>
						<li><a name="sfbest_hp_hp_news_1"
							href="/index/footerService.html" target="_blank"
							class=" red trackref" title="受杭州G20峰会影响的配送问题公告">受杭州G20峰会影响的配送问题公告</a></li>
						<li><a name="sfbest_hp_hp_news_2"
							href="/index/footerService.html" target="_blank"
							class=" trackref" title="购买燕麦片满49元抽奖活动中奖公告">购买燕麦片满49元抽奖活动中奖公告</a></li>
						<li><a name="sfbest_hp_hp_news_3"
							href="/index/footerService.html" target="_blank"
							class=" trackref" title="满299抽iPhone6s活动中奖公告">满299抽iPhone6s活动中奖公告</a></li>
						<li><a name="sfbest_hp_hp_news_4"
							href="/index/footerService.html" target="_blank"
							class=" trackref" title="《珍膳米，带你冲上云霄》活动中奖名单公布">《珍膳米，带你冲上云霄》活动中奖名单公布</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="clr"></div>
	</div>
	<!--楼层 start-->
	<div class="indexW mt2 full_ad"
		style="width: 1190px; padding-left: 10px;">
		<a name="sfbest_hp_hp_banner_1" class="trackref"
			href="https://www.jd.com/" target="_blank"><img alt="8.30-9.5"
			class="lazy" src="/images/goods/8f42d6d2deead3da7d50c8a702a3c939.jpg"
			style="display: inline;"></a>
	</div>
	<div class="indexW mt2 ie6 fresh">
		<div class="category">
			<ul class="mTitle">
				<li>
					<div class="cir"></div>
					<h2>
						<em></em> <a name="sfbest_hp_hp_floor1_floor-category1"
							class="trackref" href="/fresh/52-0-0-0-0-2-0-0-0-0-0.html"
							target="_blank">水果</a>&nbsp;&nbsp;<a
							name="sfbest_hp_hp_floor1_floor-category2" class="trackref"
							href="/fresh/55-0-0-0-0-2-0-0-0-0-0.html" target="_blank">蔬菜</a>
					</h2>
				</li>
			</ul>
			<div class="lCont">
				<a name="sfbest_hp_hp_floor1_left-ad" class="trackref"
					href="https://www.jd.com/" target="_blank"> <img alt="8.30-9.5"
					class="lazy"
					src="/images/goods/26336113289bf7273823080488a9d200.jpg"
					style="display: inline;"></a>
			</div>
		</div>
		<div class="sfRight">
			<div class="subCont">
				<ul class="pList" id="floor-gap-1">
					<c:forEach items="${item1List}" var="node" varStatus="status">
						<li class="price_list1" eid="${node.cid}" goods="${node.id}"
							id="${node.id}">
							<div class="pImg">
								<a href="/item/${node.id}.html" target="_blank"
									title="${node.title}"><img class="lazy"
									data="${node.image}" alt="${node.title}" src="${node.image}"
									style="display: block;"></a>
								<div class="gBtn p-btn">
									<a pid="${node.id}" data_url="/images/goods/160x160.jpg"
										href="/item/${node.id}" indexflag="1">加入购物车</a>
								</div>
							</div>
							<div class="title-a">
								<a href="/item/${node.id}.html" target="_blank"
									title="${node.title}">${node.title}</a>
							</div>
							<div class="price" id="priceL_l_218031_7_297">
								<b>￥${node.price/100}</b>
							</div>
						</li>
					</c:forEach>




					<!-- <li class="price_list1" eid="l_218031_7_297" goods="218031"
						id="cx_l_218031_7_297"><div class="pImg">
							<a
								href="http://www.e3mall.cn/html/products/219/1800218031.html#trackref=sfbest_hp_hp_floor1_item1"
								target="_blank" title="泰国金柚700-1200g"><img class="lazy"
								data="/images/goods/160x160.jpg" alt="泰国金柚700-1200g"
								src="/images/goods/160x160-1.jpg" style="display: block;"></a>
							<div class="gBtn p-btn">
								<a pid="218031" data_url="/images/goods/160x160.jpg"
									href="javascript:void(0)" indexflag="1">加入购物车</a>
							</div>
						</div>
						<div class="title-a">
							<a
								href="http://www.e3mall.cn/html/products/219/1800218031.html#trackref=sfbest_hp_hp_floor1_item1"
								target="_blank" title="泰国金柚700g-1200g">泰国金柚700g-1200g</a>
						</div>
						<div class="price" id="priceL_l_218031_7_297">
							<b>￥19.9</b>
						</div></li> -->
				</ul>
			</div>
			<div class="redge">
				<ul class="rHot">
					<li><a name="sfbest_hp_hp_floor1_Keywords1" class="trackref"
						href="/index.html" target="_blank">蓝莓</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords2" class="trackref"
						href="/index.html" target="_blank">椰青</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords3" class="trackref"
						href="/index.html" target="_blank">火龙果</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords4" class="trackref"
						href="/index.html" target="_blank">水蜜桃</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords5" class="trackref"
						href="/index.html" target="_blank">牛油果</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords6" class="trackref"
						href="/index.html" target="_blank">佳沛</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords7" class="trackref"
						href="/index.html" target="_blank">新奇士</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords8" class="trackref"
						href="/index.html" target="_blank">加工蔬菜</a></li>
					<li><a name="sfbest_hp_hp_floor1_Keywords9" class="trackref"
						href="/index.html" target="_blank">加利利</a></li>
				</ul>
				<div class="clr"></div>
				<div class="rimg">

					<a name="sfbest_hp_hp_floor1_right-ad" class="ht1 trackref"
						href="https://www.jd.com/" target="_blank"><img alt="8.30-9.5"
						class="lazy"
						data="http://001.sfimg.cn/web/1dd1130a/1dd1130a9c0103f6ec8a13fa13f27641.jpg"
						src="http://001.sfimg.cn/web/1dd1130a/1dd1130a9c0103f6ec8a13fa13f27641.jpg"
						style="display: inline;"></a>
					<div class="rbutton">
						<a href="https://www.jd.com/" target="_blank"></a>
					</div>
				</div>
			</div>
		</div>
		<!----天天生鲜 -->
		<span class="clr"></span>
	</div>
	<!--楼层 end -->

	<!-- footer start -->
	<jsp:include page="commons/footer.jsp" />
	<!-- footer end -->
</body>
</html>