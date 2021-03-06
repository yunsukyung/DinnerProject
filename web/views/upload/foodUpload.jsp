<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String backPage = request.getContextPath() + "/views/upload/foodUpload.jsp";
	session.setAttribute("backPage", backPage);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YUMEET</title>
<link rel="shortcut icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<style>
	.area{
		resize: none;
	}
	.food{
		display: inline-block;
		
	}
	input[type=submit]{
		background: #5BB8B4;
		color: #FFFFFF;
		border: 0;
		outline: 0;
		width: 130px;
		height: 35px;
		font-size: 24px;
		margin: 10px;
		
		
	}
	input[type=reset]{
		background: #666666;
		color: #FFFFFF;
		border: 0;
		outline: 0;
		width: 130px;
		height: 35px;
		font-size: 24px;
		margin: 10px;
		
		
	}
	label, input, select, textarea{
		margin-bottom: 10px;
		margin-top: 10px;
	}
	table{
		margin-right: 10px;
		margin-left: 50px;
	}
	#box2{
		margin-bottom: 50px;
	}
	#box3{
		margin-bottom: 20px;
	}
	.star{
		color: red;
	}
	
	input[type=text]{
		border-radius: 0;
		border: 1px solid gray;
		outline-style: none;
		height: 20px;
	}
	textarea{
		border-radius: 0;
		border: 1px solid gray;
		outline-style: none;
	}
	select{
		border-radius: 0;
		border: 1px solid gray;
		outline-style: none;
		height: 20px;
	}

</style>
</head>
<body>
<%@ include file="/views/common/header.jsp" %>
<% if(loginUser != null) {%>
	<h1 style="margin-left: 200px; margin-top: 30px;">식당 등록</h1>
		<div class="box" align="center">
		<form action="<%=request.getContextPath() %>/insert.up"  method="post" enctype="multipart/form-data">
		
	<div class="food" id="box3" align="center">
		<table>
			<tr>
				<td><label>식당명</label><label class="star">* </label></td>
				<td><input type="text" name="enpName"></td>
				
			</tr>
			<tr>
				<td><label>주소</label><label class="star">* </label></td>
				<td><input type="text" name="enpAddress"></td>
				
			</tr>
			<tr>
				<td><label>전화번호</label><label class="star">* </label></td>
				<td><input type="text" name="enpPhone"></td>
				
			</tr>
			<tr>
				<td><label>대표메뉴명</label><label class="star">* </label></td>
				<td><input type="text" name="menuName"></td>
			
			</tr>
			<tr>
				<td><label>메뉴가격</label><label class="star">* </label></td>
				<td><input type="text" name="menuPrice"></td>
			</tr>
			<tr>
				<td><label>가격대</label><label class="star">* </label></td>
				<td>
					<select name="priceRange">
						<option value="~1만원대">~1만원대</option>
						<option value="1만원 ~ 2만원">1만원 ~ 2만원</option>
						<option value="2만원 ~ 3만원">2만원 ~ 3만원</option>
						<option value="3만원 ~ 5만원">3만원 ~ 5만원</option>
						<option value="5만원 ~ 7만원">5만원 ~ 7만원</option>
						<option value="7만원대~">7만원대~</option>
					</select>
					</td>
				
			</tr>
			<tr>
				<td><label>영업시간</label></td>
				<td><input type="text" name="enpHour"></td>
			</tr>
			<tr>
			<td><label>휴무일</label></td>
			<td><input type="text" name="closedDay"></td>
			</tr>
			
			
		</table>
		
		</div>
		<div class="food" align="center">
		<hr width="1px" size="420" style="background-color: gray; border: gray; margin-left: 70px;">
		</div>
		<div class="food" id="box2" align="center">
		
		<table>
		<tr>
			<td><label>웹사이트</label></td>
			<td><input type="text" name="website"></td>
			</tr>
		<tr>
				
				<td><label>해시태그</label><label class="star">* </label></td>
				<td><input type="text" name="hashTags" placeholder="#을 이용하여 태그를 사용해보세요."></td>
			</tr>
			<tr>
				
				<td><label>소개문구</label></td>
				<td><textarea rows="5px" cols="22px" class="area" name="introduce" placeholder="가게에 대한 간단한 소개글을 작성해주세요."></textarea></td>
			</tr>
			<tr>
				
				<td><label>주차공간</label></td>
				<td>
				<select name="parkingPossible">
					<option value="주차가능">주차가능</option>
					<option value="주차공간없음">주차공간없음</option>
				</select>
				</td>
			</tr>
			<tr>
				
				<td><label>카테고리</label><label class="star">* </label></td>
				<td><select name="enpType">
					<option value="한식">한식</option>
					<option value="중식">중식</option>
					<option value="일식">일식</option>
					<option value="패스트푸드">패스트푸드</option>
					<option value="양식">양식</option>
					<option value="카페">카페</option>
					<option value="분식">분식</option>
					<option value="아시아">아시아</option>
				</select></td>
			</tr>
			<tr>
				
				<td><label>이미지</label><label class="star">* </label></td>
				<td><input type="file" id="thumbnailImg" name="thumbnailImg"></td>
				
			</tr>
			 
		</table>
	</div>
	<div align="center" style="margin-bottom: 80px;">
		<br><br><br>
		  <input type="submit" value="추 가">
          <input type="reset" value="취 소">
	</div>
		</form>
		</div>
	<%} else { %>
	<script>
		alert("로그인 후 이용하시길 바랍니다.");
		document.location.href="/semiproject/views/signIn/signIn.jsp";
	</script>
	
	<% } %>
	

<%@ include file="/views/common/footer.jsp" %>


</body>
</html>