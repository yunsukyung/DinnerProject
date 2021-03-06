<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String backPage = request.getContextPath() + "/views/myPage/myPage.jsp";
	session.setAttribute("backPage", backPage);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YUMEET</title>
<link rel="shortcut icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css"
	href="/semiproject/views/myPage/css/myPage.css" />
<style>
.info {
	font-family: Roboto;
	font-style: normal;
	font-weight: 700;
	font-size: 15px;
	line-height: 18px;
}
.info2{
	font-family: Roboto;
	font-style: normal;
	font-weight: 500;
	font-size: 15px;
	line-height: 18px;
}
.navbar{
	color: #666666;
	font-size: 20px;
	
	
}
.left{
	list-style-type:none;
	float:left;
	margin-left: 20px;
}
.left2:hover{
	text-decoration: none;
	border-bottom: 3px #E1A9A9 solid;
}
ul li a span:hover{
	color: pink;
}
input{
	border-radius: 0;
	border: 1px solid gray;
	outline-style: none;
}
</style>
</head>
<body>

	<%@ include file="/views/common/header.jsp" %>
	<script>
	console.log("<%=loginUser.getFilePath()%>");
		$.ajax({
			type : "post",
			url : "/semiproject/selectPoint.me",
			data : {
				mNo: "<%=loginUser.getmNo()%>"
			},
			success: function(data){
				$("#currentPoint").html("보유포인트 : " + data + "p");
			},
			error: function(){
			}
		});
	</script>
	<div id="daumWrap" class="userinfo_type1 ">
		<div id="daumHead" role="banner">
			<div class="inner_head" style="padding-right:30px;">
				<h1>
					<a href="/semiproject/views/myPage/myPage.jsp" id="daumServiceLogo"><span class="ir_wa">내정보</span></a>
				</h1>
				<!-- PC 웹 내정보 GNB -->
				<div id="" role="navigation">
					<ul style="padding-top: 10px; padding-left:-30px;">
						<li class="left"><a class="left2" href="/semiproject/views/myPage/myPage.jsp"><span class="navbar" style="color: #E1A9A9;">내정보 홈</span></a></li>
						<li class="left"><a class="left2"  href="/semiproject/views/myPage/checkingPassword.jsp?num=1"><span class="navbar">내정보 관리</span></a></li>
						<li class="left"><a class="left2"  href="/semiproject/views/myPage/checkingPassword.jsp?num=2"><span class="navbar">비밀번호 변경</span></a></li>
						<li class="left"><a class="left2"  href=""><span class="navbar">고객센터</span></a></li>
						<li class="left"><a class="left2"  href="/semiproject/views/myPage/checkingPassword.jsp?num=3"><span class="navbar">회원탈퇴</span></a></li>
					</ul>
				</div>
			</div>
		</div>

		<hr class="hide" />
		<div id="daumContent" role="main">
			<div id="cMain">
				<div id="mArticle">
					<h2 id="daumBody" class="screen_out">내정보 홈 본문</h2>
					<div class="info_servicelist">
						<div class="bg_box box_tit" style="display: inline-block; border: 1px solid darkgray; width:277px;">
							<h1 style="color: #D5706D;">YUMEET 프로필</h1>
							<form id="sub" action="/semiproject/changeMemImg.ma" method="post" enctype="multipart/form-data">
							<input name="memimg" type="file" style="display: none;" id="image" accept="image/png, image/jpeg">
							<div class="section_myinfo" style="border-radius: 70%; overflow: hidden; width:50px; height:50px;">
							<%if(loginUser.getFilePath() == null) {%>
								<img onclick="change();" style="width:50px; height:50px;"src="/semiproject/images/signinImg.png">
								<%} else { %>
								<img onclick="change();" style="width:50px; height:50px;"src="/semiproject/thumbnail_uploadFile/<%=loginUser.getFilePath()%>">
								<%} %>
							</div>
							</form>
							<script>
								function change(){
									$("#image").click();
								}
								
								$("input[name='memimg']").change(function(e){
									if(confirm("정말 수정하시겠습니까?") == true){
										$("#sub").submit();
									} else {
										console.log(123123123);
										return false;
									}
								})
							</script>
							<div style="margin-top: -10px;">
								<label class="info">이름 : </label> <label class="info2"><%=loginUser.getmName() %></label><br>
								<label class="info">닉네임 : </label> <label class="info2"><%=loginUser.getmNickname() %></label><br> 
								<label class="info">이메일 : </label> <label class="info2"><%=loginUser.getmEmail() %></label><br> 
								<label class="info">번호 : </label> <label class="info2"><%=loginUser.getmPhone() %></label>
							</div>
						</div>
						<a href="<%=request.getContextPath()%>/selectPostList.me" class="bg_box" style="display: inline-block; border: 1px solid darkgray;"> <!-- 미사용시 class 'box_off', 사용시 class 'box_on' -->
							<h2>내가쓴 게시글</h2> <span class="txt_desc">"내가쓴 게시글이 한눈에!" </span>
						</a> 
						
						<a href="/semiproject/views/myPage/writeReviewsByMe.jsp" class="bg_box" style="display: inline-block; border: 1px solid darkgray;">
							<h2>내가쓴 리뷰</h2> <span class="txt_desc">"리뷰들을 모아서 보자!" </span>
						</a> 
						
						<a href="/semiproject/views/myPage/likePosts.jsp" class="bg_box" style="display: inline-block; border: 1px solid darkgray;"> <!-- 미사용시 class 'box_off2', 사용시 class 'box_on2' -->
							<h2>좋아요</h2>
							<ul id="likeEnpList">
							</ul>
						</a>
						<script>
							$(function() {
								$.ajax({
									url: "/semiproject/getLikeEnps.se",
									type: "post",
									data: {mNo: "<%= loginUser.getmNo() %>"},
									success: function(data) {
										console.log(data);
										if(data.length > 0){
										for(var i = 0; i < data.length; i++) {
											$("#likeEnpList").append("<li>· " + data[i] + "</li>");
										  }
										}
									}
								});
							});
						</script>
						<a style="padding-left:0px; display:block; padding-top: 30px;">
						<img src="/semiproject/images/YUMEET LOGO.png" style="width:300px; height:100px;">
						</a>
						
					</div>
					<ul class="ir_pm"
						style="width: 678px; height: 116px; margin: 0 0 52px 16px; overflow: hidden; background: url(images/subbar.png) no-repeat 0 0">
						<li
							style="float: left; width: 161px; height: 116px; margin-right: 55px;">
							<form action="<%=request.getContextPath()%>/views/myPage/myPoint.jsp">
								<button style="display: block; width: 50px; height: 16px; margin-left: 16px;margin-top: 90px; maring-right:30px;"></button>
							</form>
						</li>

						<li
							style="float: left; width: 161px; height: 116px; margin-right: 55px;">
							<form action="<%=request.getContextPath()%>/selectList.py">
								<button style="display: block; width: 50px; height: 16px; margin-top: 90px; maring-right:30px;"></button>
							</form>
						</li>
						<li
							style="float: left; width: 161px; height: 116px; margin-right: 65px;">
							
							<form action="<%=request.getContextPath()%>/selectInquiryList.py">
								<button style="display: block; width: 50px; height: 16px; margin-top: 90px; maring-right:30px;"></button>
							</form>
						</li>
					</ul>
				</div>
				
				<!--// mArticle -->
				<div id="mAside">
					<div class="wing_userinfo">
						<div class="section_myinfo">
							<div align="center" style="border-radius: 70%; overflow: hidden; width:150px; height:150px;">
							<%if(loginUser.getFilePath() == null) {%>
								<img onclick="change();" src="/semiproject/images/signinImg.png" style="max-width: 100%; max-height: 100%">
								<%} else { %>
								<img onclick="change();" src="/semiproject/thumbnail_uploadFile/<%=loginUser.getFilePath()%>" style="max-width: 100%;">
								<%} %>
							</div>
							 <a href="/semiproject/views/myPage/checkingPassword.jsp" class="link_user"
								data-tiara-action-name="내_정보"><%=loginUser.getmName()%>님<br>
							</a><a class="link_user" href="/semiproject/views/myPage/myPoint.jsp">
							<label id="currentPoint" >포인트 : </label>	
							</a>
							
							<div id="profileImageAgreeDialog" class="layer_manage"
								style="display: none">
							</div>
							
							<div id="profileImageChangeDialog" class="layer_manage"
								style="display: none">
							</div>
						</div>
						<div id="profileImageEditor"
							style="display: none; position: absolute; left: 50%; margin-left: -400px; top: 100px; z-index: 998; width: 800px; height: 600px"></div>
						<div class="section_info section_activity">
							<h4 class="txt_comm txt_activity">내 활동 내역</h4>
							<ul class="list_activity">
								
							</ul>
						</div>
						
					</div>
				</div>
				<!--// mAside -->
			</div>
			<!-- // cMain -->

		</div>
		<!-- // daumContent -->
	<%@ include file="/views/common/footer.jsp" %>
		<!-- // daumFoot -->

		<div id="wrapMinidaum"></div>
	</div>




</body>