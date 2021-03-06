<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.kh.semi.ad.model.vo.PartnerVO"%>
<%@ page import="com.kh.semi.admin.model.vo.PageInfo" %>
<%
	ArrayList<PartnerVO> list = (ArrayList<PartnerVO>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YUMEET 관리자페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<link rel="shortcut icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<style>
.hide {
	background-color: white;
	border: 0px;
	height: 30px;
}

#wrapper {
	width: 80%;
	height: auto;
	margin-left: 230px;
	padding-top: 30px;
	padding-left: 30px;
}

#title-box {
	font-family: Noto Sans KR;
	font-size: 30px;
	font-weight: bolder;
	background: #F9F9F9;
	width: 100%;
	margin-bottom: 10px;
	height: 50px;
	padding-left: 10px;
	padding-right: 10px;
}

#inner-box {
	width: 100%;
	height: 700px;
}

#inner-wrap {	
	padding-left: 10px;
	padding-right: 10px;
	background: white;
	width: 100%;
	padding-top: 30px;
	height: 80%;
}

#send-btn {
	color: white;
	background-color: #E07370;
	border: none;
	width: 40px;
	height: 25px;
}

#sendcom-btn {
	color: white;
	background-color: #A0A0A0;
	border: none;
	width: 70px;
	height: 25px;
}

td {
	text-align: center;
}

#review-tb {
	border-collapse: collapse;
}

tr {
	border-bottom: 0.5px solid #9F9F9F;
	height: 40px;
}

#confirm-before-btn {
	width: 80px;
	height: 25px;
	border: none;
	background: #E07370;
	border-radius: 2px;
	color: white;
	font-size: 15px;
}

#confirm-after-btn {
	width: 80px;
	height: 25px;
	border: none;
	background: #C4C4C4;
	border-radius: 2px;
	color: black;
	font-size: 15px;
}

#search-btn {
	width: 53px;
	height: 24px;
	background: #C4C4C4;
	color: black;
	border: none;
}

#title {
	text-align: left;
	padding-left: 30px;
	width: 40%;
}

#toggle {
	background: url("/semiproject/images/toggle.png") no-repeat;
	width: 10px;
	height: 10px;
	border: none;
}

.checkBtn {
	background: gray;
	color: #FFFFFF;
	border: 0;
	outline: 0;
	width: 70px;
	height: 25px;
	font-size: 14px;
}

.innerText1 {
	display: none;
}

.productBtn {
	background: #E07370;
	color: #FFFFFF;
	border: 0;
	outline: 0;
	width: 80px;
	height: 25px;
	font-size: 14px;
}
#goNotice:link,#goNotice:visited, #goNotice:active, #goNotice:hover {
	text-decoration:none; color:black;
	text-decoration:none; color:black;
	text-decoration:none; color:black;
	text-decoration:none; color:black;
}
</style>
</head>
<body style="background: lightgray;">
	<%@ include file="/views/admin/common/sidebar.jsp"%>
	<div id="wrapper">
		<div id="title-box">
			<p>제휴 문의 관리</p>
			<br>
		</div>
		<div id="inner-wrap">
			<div id="inner-box">
				<div style="height: 30px;"></div>
				<form>
					<table class="listArea" id="review-tb" style="width: 100%;">
						<tr>
							<th>글번호</th>
							<th>회원명</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th>업체명</th>
							<th>업체 종류</th>
							<th>상품 선택</th>
							<th>신청 일자</th>
						</tr>
						<%
							for(PartnerVO p : list) {
						%>
						<tr>
							<td><%=p.getPartnerQuestionNo() %></td>
							<td><%=p.getPartQName() %></td>
							<td><%=p.getPartQPhone() %></td>
							<td><%=p.getPartQEmail() %></td>
							<td><%=p.getPartQTitle() %></td>
							<td><%=p.getPartQEnpType() %></td>
							<td><%=p.getPartQType() %></td>
							<td><%=p.getPartQDate() %></td>
						</tr>
						<% 
							}
						%>
					</table>
				</form>
				<div style="height: 30px;"></div>
		<div class="pageingArea" align="center">
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectclist.no?currentPage=1'"><<</button>

			<%
				if (currentPage <= 1) {
			%>
			<button disabled><</button>
			<%
				}
			%>

			<%
				for (int p = startPage; p <= endPage; p++) {
					if (p == currentPage) {
			%>
			<button disabled><%=p%></button>
			<%
				} else {
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectclist.no?currentPage=<%=p%>'"><%=p%></button>
			<%
				}
				}
			%>

			<%
				if (currentPage >= maxPage) {
			%>
			<button disabled>></button>
			<%
				} else {
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectclist.no?currentPage=<%=currentPage + 1%>'">></button>
			<%
				}
			%>

			<button
				onclick="location.href='<%=request.getContextPath()%>/selectclist.no?currentPage=<%=maxPage%>'">>></button>
		</div>
		</div>
		</div>
		</div>
	<script>
		$(function() {
			$(".listArea td").mouseenter(function() {
				$(this).parent().css({"background":"#E4E4E4", "cursor":"pointer"});
			}).mouseout(function() {
				$(this).parent().css({"background":"white"});
			}).click(function() {
				
				var num = $(this).parent().children().eq(0).text();
				
				location.href="<%=request.getContextPath()%>/selectOne.pa?num="+ num;
							});
		});
	</script>
</body>
</html>