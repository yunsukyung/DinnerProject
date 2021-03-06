<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String num = (String)request.getParameter("num");
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
.text {
	font-family: Roboto;
	font-style: normal;
	font-weight: normal;
	font-size: 20px;
	line-height: 21px;
	color: #000000;
	display: inline-block;
}

.box {
	background: #FFFFFF;
	border: 1px solid #6D6D6D;
	box-sizing: border-box;
	border-radius: 5px;
}

.text2 {
	font-family: Roboto;
	font-style: normal;
	font-weight: 200;
	font-size: 16px;
	line-height: 16px;
	text-align: center;
	border-radius: 5px;
	width: 100px;
	height: 30px;
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
</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	<div id="daumIndex">
		<!-- 웹접근성용 바로가기 링크 모음 -->
		<a href="#daumBody" data-tiara-action-name="본문_바로가기">본문 바로가기</a> <a
			href="#daumGnb" data-tiara-action-name="메뉴_바로가기">메뉴 바로가기</a>
	</div>

	<div id="daumHead" role="banner">
			<div class="inner_head" style="padding-right:30px;">
				<h1>
					<a href="/semiproject/views/myPage/myPage.jsp" id="daumServiceLogo"><span class="ir_wa">내정보</span></a>
				</h1>
				<!-- PC 웹 내정보 GNB -->
				<div id="" role="navigation">
					<ul style="padding-top: 10px; padding-left:-30px;">
						<li class="left"><a class="left2" href="/semiproject/views/myPage/myPage.jsp"><span class="navbar"">내정보 홈</span></a></li>
						<li class="left"><a class="left2"  href="/semiproject/views/myPage/checkingPassword.jsp"><span class="navbar">내정보 관리</span></a></li>
						<li class="left"><a class="left2"  href="/semiproject/views/myPage/checkingPassword.jsp"><span class="navbar">비밀번호 변경</span></a></li>
						<li class="left"><a class="left2"  href=""><span class="navbar">고객센터</span></a></li>
						<li class="left"><a class="left2"  href="/semiproject/views/myPage/checkingPassword.jsp"><span class="navbar">회원탈퇴</span></a></li>
					</ul>
				</div>
			</div>
		</div>

	<div
		style="width: 500px; height: 500px; margin-left: auto; margin-right: auto;">
		<table>
			<tr>
				<td><img src=images/checkPassword.PNG; style="width: 500px"></td>
			<tr>
			<tr>
				<td>
					<hr style="background: #EFECE8;"> <br> <br>
				</td>
			</tr>
			<tr>
				<td><label class="text" style="margin-left: 50px">YUMEET
						아이디 : </label> <label class="text"
					style="margin-left: 50px; font-size: 22px;"><%= loginUser.getmId() %></label><br>
					<br></td>
			</tr>
			<tr>
				<td style="margin-top: 10px;"><label class="text"
					style="margin-left: 50px">비밀번호 확인 : &nbsp;</label> <input
					class="box" type="password" style="margin-left: 50px;" id="password">
					<button style="background: #C4C4C4; border-radius: 5px;" id="pwdShowHide">보기</button>
					<br>
					<label id="check" class="text" style="color:red; font-size:15px; margin-left: 250px;">비밀번호를 확인하세요</label>
					<br> <br> <br></td>
			</tr>
			<tr>
				<td>
					<hr style="background: #EFECE8;"> <br> <br>
				</td>
			</tr>
			<tr align="center">
				<td>
					<button id="goBack" class="text2" style="background: #E4E4E4;">이전으로</button>
					<button id="pwdCheck" class="text2" style="background: #97D3D3; color: white">확인</button>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
	<script>
		$("#check").hide();
		
		$(function(){
		    $('#pwdShowHide').on('click', function(){
		        $('.box').toggleClass('active');
		        
		        if(!$('input').hasClass('active')) {
		            $("#password").attr('type', "password");
		        } else {
		            $("#password").attr('type', "text");
		        }
		    });
		});
		
		$("#goBack").click(function() {
			history.back();
		});
		
		$("#password").bind("input", function() {
			$("#check").hide();
		});
		
		$("#password").click(function() {
			$("#password").val("");
		});
		
		$("#pwdCheck").click(function() {
			var password = $("#password").val();
			
			$.ajax({
				url: "/semiproject/pwdCheck.me",
				type: "post",
				data: {
					id: "<%= loginUser.getmId() %>", 
					password: password,
					num : "<%= num%>"
					},
				success: function(data) {
					if(data === "success1") {
						location.href = "/semiproject/views/myPage/changeMyProfile.jsp";
					} else if(data === "success2") {
						location.href = "/semiproject/views/myPage/changePassword.jsp";
					} else if(data === "success3") {
						location.href = "/semiproject/views/myPage/withdrawalFromMembership.jsp";
					} else {
						$("#check").show();
					}
				}
			});
		});
	</script>
</body>
</html>