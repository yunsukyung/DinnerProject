<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pag1 = (String)session.getAttribute("backPage");
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="/semiproject/views/signIn/css/signInStyle.css"/>
<title>YUMEET 로그인</title>
</head>
<body>
	<img alt="여밋 로고" src="/semiproject/images/YUMEET LOGO WITH REST.png" id="logo">
	<p id="login">로그인</p>
	<hr class="hr">
	<form action="<%= request.getContextPath() %>/signIn.me" method="post" onsubmit="return check();">
		<input type="text" name="id" id="id">
		<br>
		<input type="password" name="password" id="password">
		<br>
		<input type="checkbox" id="pwdShowHide">
		<label id="pwdLabel" for="pwdShowHide">비밀번호 보이기</label>
		<br>
		<input type="submit" value="로그인" id="submitBtn">
	</form>
	<hr class="hr">
	<div id="find">
		<a id="findId" href="/semiproject/views/findId/findId.jsp">아이디 찾기</a> | 
		<a id="findPassword" href="/semiproject/views/findPwd/findPwd.jsp">비밀번호 찾기</a> | 
		<a id="signUp" href="/semiproject/views/signUp/signUp.jsp">회원 가입</a>
	</div>
	<br><br>
	<script type="text/javascript" src="/semiproject/views/signIn/js/signInJs.js"></script>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>