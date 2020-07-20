<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.semi.enterprise.model.vo.*, java.util.Map.*, java.util.*, com.kh.semi.review.model.vo.*"%>
<!DOCTYPE html>
<%
EnpVO selectedEnp = (EnpVO)session.getAttribute("selectedEnp");
double rating = (double)session.getAttribute("rating");
ArrayList<ReviewVO> visitReviews = (ArrayList<ReviewVO>)session.getAttribute("visitReviews");
ArrayList<ReviewVO> normalReviews = (ArrayList<ReviewVO>)session.getAttribute("normalReviews");
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/semiproject/views/restaurantInfo/css/restaurantInfoStyle.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>YUMEET - <%= selectedEnp.getEnpName() %></title>
<link rel="shortcut icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	<div id="infoTop">
		<div id="top1">
			<span id="title"><%= selectedEnp.getEnpName() %></span>
			<img alt="별점 이미지" src="/semiproject/images/Star.png" id="star">
			<span id="score"><%= rating %></span>
			<br>
			<img alt="즐겨찾기 이미지" src="/semiproject/images/heart.png" id="heart">
			<p id="likeCount"><%= selectedEnp.getLikeCount() %></p>
			<img alt="리뷰 이미지" src="/semiproject/images/comment.png" id="comment">
			<p id="commentCount"></p>
			<script>
				$(function() {
					$.ajax({
						url: "/semiproject/countComment.re",
						type: "post",
						data: {enpNo: "<%= selectedEnp.getEnpNo() %>"},
						success: function(data) {
							$("#commentCount").html(data);
						}
					});
				});
			</script>
		</div>
		<div id="top2">
			<div id="likeDiv">
				<img alt="즐겨찾기 이미지" src="/semiproject/images/like.png" id="likeImg">
				<br>
				<span class="commentAndLike">좋아요</span>
			</div>
			<div id="commentDiv">
				<img alt="즐겨찾기 이미지" src="/semiproject/images/comment.png" id="commentImg">
				<br>
				<span class="commentAndLike">리뷰쓰기</span>
			</div>
		</div>
	</div>
	<hr class="hr">
	<div id="info">
		<div id="infoLeft">
			<table id="infoTable">
				<tr>
					<td class="infoTitle">전화번호</td>
					<td class="infoContent"><%= selectedEnp.getEnpPhone() %></td>
				</tr>
				<tr>
					<td class="infoTitle">가격대</td>
					<td class="infoContent"><%= selectedEnp.getPriceRange() %></td>
				</tr>
				<tr>
					<td class="infoTitle">메뉴</td>
					<td class="infoContent" id="menus"></td>
				</tr>
				<tr>
					<td class="infoTitle">웹 사이트</td>
					<td class="infoContent"><%= selectedEnp.getWebsite() %></td>
				</tr>
				<tr>
					<td class="infoTitle">영업시간</td>
					<td class="infoContent"><%= selectedEnp.getEnpHour() %></td>
				</tr>
				<tr>
					<td class="infoTitle">휴무일</td>
					<td class="infoContent"><%= selectedEnp.getClosedDay() %></td>
				</tr>
				<tr>
					<td class="infoTitle">주차공간</td>
					<td class="infoContent"><%= selectedEnp.getParkingPossible() %></td>
				</tr>
				<tr>
					<td class="infoTitle">해시태그</td>
					<td class="infoContent"><%= selectedEnp.getHashTags() %></td>
				</tr>
				<tr>
					<td class="infoTitle">주소</td>
					<td class="infoContent">
						<%= selectedEnp.getEnpAddress() %>
						<br>
					</td>
				</tr>
			</table>
		</div>
		<script>
			$(function() {
				$.ajax({
					url: "/semiproject/getMenu.se",
					type: "post",
					data: {enpNo: "<%= selectedEnp.getEnpNo() %>"},
					success: function(data) {
						$.each(data, function(key, value) {
							$("#menus").append(key + " " + value + "원<br>");
						});
					}
				});
			});
		</script>
		<div id="infoRight">
			<img alt="매장 대표사진" src="/semiproject/images/dishPic.png" id="dishPic">
		</div>
	</div>
	<div id="reservationDiv">
		<button>예약하기</button>
	</div>
	<script>
		var enpNo = "<%= selectedEnp.getEnpNo() %>";
		$("#reservationDiv button").click(function() {
			location.href="<%= request.getContextPath() %>/views/payment/paymentPage.jsp?enpNo=" + enpNo; 
		});
	</script>
	<div id="adDiv">
		<div id="adContent">
			<div id="adBtn">
				<div id="adCloseBtn"><img alt="광고 종료 버튼" src="/semiproject/images/adCloseBtn.png"></div>
				<div id="adInfoBtn"><img alt="광고 정보 버튼" src="/semiproject/images/adInfoBtn.png"></div>
			</div>
			<span></span>
		</div>
		<div id="adPic">
			<img alt="배너광고" src="" id="ad">
		</div>
	</div>
	<script>
		$(function() {
			$.ajax({
				url: "/semiproject/foundAllAd.ad",
				type: "get",
				success: function(data) {
					$("#adInfoBtn").click(function() {
						window.open("http://" + data.adWebsite, "_blank");
					});
					
					$("#adCloseBtn").click(function() {
						$("#adDiv").hide();
					});
					
					$("#adContent span").html(data.adContent);
					
					$("#ad").attr("src", data.filePath).css({"width":"420px", "height":"106px"});
				}
			});
		});
	</script>
	<hr class="hr">
	<div class="ReviewDiv">
		<div class="ReviewCount" id="visitReviewCount"></div>
		<script>
			$(function() {
				$.ajax({
					url: "/semiproject/countTypeReview.re",
					type: "post",
					data: {enpNo: "<%= selectedEnp.getEnpNo() %>", type: "방문"},
					success: function(data) {
						$("#visitReviewCount").html("방문자 리뷰(" + data + ")");
					}
				});
			});
		</script>
		<div class="ReviewWrite">작성하기 <img alt="리뷰 작성 버튼" src="/semiproject/images/writeReview.png" class="writeReviewBtn"></div>
	</div>
	<hr class="hr">
	<!-- visitorReview Div start -->
	<div class="visitorReview">
	<% if(visitReviews.size() != 0) {
	for(int i = 0; i < visitReviews.size(); i++) { %>
		<div class="visitorInfo">
			<table>
				<tr>
					<td><img id="profilePic<%= i %>" alt="사용자 프로필 사진" src=""></td>
				</tr>
				<tr>
					<td id="profileNickName<%= i %>"></td>
				</tr>
				<tr>
					<td>방문일 : <%= visitReviews.get(i).getVisitDate() %></td>
				</tr>
			</table>
		</div>
		<script>
			$(function() {
				var mNo = "<%= visitReviews.get(i).getMemberNo() %>";
				
				$.ajax({
					url: "/semiproject/selectMember.me",
					type: "post",
					data: {mNo: mNo},
					success: function(data) {
						$("#profilePic<%= i %>").attr("src", data.filePath).css({"width" : "110px", "height" : "110px"});
						$("#profileNickName<%= i %>").html(data.mNickname);
					}
				});
			});
		</script>
		<div class="visitorReviewContent">
			<div class="visitorReviewArticle">
				<span class="reviewDate"><%= visitReviews.get(i).getReviewDate() %></span>
				<img alt="리뷰 별점" src="/semiproject/images/Star.png" class="reviewRateStar">
				<span class="reviewRate"><%= visitReviews.get(i).getAverageRating() %></span>
				<p><%= visitReviews.get(i).getReviewContent() %></p>
			</div>
			<div class="visitorReviewPic">
				<img alt="음식 사진" src="<%= visitReviews.get(i).getFilePaths()[0] %>">
				<img alt="음식 사진" src="<%= visitReviews.get(i).getFilePaths()[1] %>">
			</div>
		</div>
		<div class="likeAndReport">
			<div class="reviewReport">
				<button class="reviewReportBtn">신고하기</button>
			</div>
		</div>
	</div>
	<!-- visitorReview Div end -->
	<hr class="hr">
	<% 	} %>
	<% } else { %>
		<div style="margin:20px 0px; text-align:center;">아직 방문자 리뷰가 없습니다.</div>
	<% } %>
	<div id="showMore">
		<button>더보기 ▼</button>
	</div>
	<br>
	<hr class="hr">
	<div class="ReviewDiv">
		<div class="ReviewCount" id="normalReviewCount"></div>
		<script>
			$(function() {
				$.ajax({
					url: "/semiproject/countTypeReview.re",
					type: "post",
					data: {enpNo: "<%= selectedEnp.getEnpNo() %>", type: "일반"},
					success: function(data) {
						$("#normalReviewCount").html("일반 리뷰(" + data + ")");
					}
				});
			});
		</script>
		<div class="ReviewWrite">작성하기 <img alt="리뷰 작성 버튼" src="/semiproject/images/writeReview.png" class="writeReviewBtn"></div>
	</div>
	<hr class="hr">
	<% if(normalReviews.size() != 0) { 
		for(int i = 0; i < normalReviews.size(); i++) { %>
	<div class="visitorReview" style="height:150px;">
		<div class="visitorInfo">
			<p id="normalProfileNickName<%= i %>"></p>
		</div>
		<script>
			$(function() {
				var mNo = "<%= normalReviews.get(i).getMemberNo() %>";
				
				$.ajax({
					url: "/semiproject/selectMember.me",
					type: "post",
					data: {mNo: mNo},
					success: function(data) {
						$("#normalProfileNickName<%= i %>").html(data.mNickname);
					}
				});
			});
		</script>
		<div class="visitorReviewContent">
			<div class="visitorReviewArticle">
				<span class="reviewDate"><%= normalReviews.get(i).getReviewDate() %></span>
				<p><%= normalReviews.get(i).getReviewContent() %></p>
			</div>
		</div>
		<div class="likeAndReport">
			<div class="reviewReport">
				<button class="reviewReportBtn">신고하기</button>
			</div>
		</div>
	</div>
	<hr class="hr">
	<% 	} %>
	<% } else { %>
		<div style="margin:20px 0px; text-align:center;">아직 일반 리뷰가 없습니다.</div>
	<% } %>
	<div id="showMore">
		<button>더보기 ▼</button>
	</div>
	<div id="restaurantCloseReport">
		<button>폐업신고</button>
	</div>
	<hr class="hr">
	<div id="yumeetRecommend">
		<img alt="여밋 로고" src="/semiproject/images/YUMEET LOGO WITH REST.png">
		추천 다음 코스
	</div>
	<hr class="hr">
	<div id="recommendDiv">
		<div id="recommendDiv1">
			<img alt="추천 맛집 첫번째" src="/semiproject/images/연어.jpg">
			<p>내 뱃살보다 두툼한 연어</p>			
			<span></span>
		</div>
		<div id="recommendDiv2">
			<img alt="추천 맛집 두번째" src="/semiproject/images/장어구이.jpg">
			<p>하나먹다 셋이죽는 장어 맛집</p>
		</div>
		<div id="recommendDiv3">
			<img alt="추천 맛집 두번째" src="/semiproject/images/죠떡.jpg">
			<p>죠스떡볶이는 전설이다...</p>
		</div>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>