<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<script>
   $(function(){
	  	$("#listArea td div").mouseenter(function(){
	  		$(this).parent().css({"background":"darkgray","cursor":"pointer"});
	  	}).mouseout(function(){
	  		$(this).parent().css({"background":"white"});
	  	}).click(function(){
	  		var num = $(this).parent().children().eq(0).text();
	  		
	  		console.log(num);
	  		location.href="<%=request.getContextPath()%>/selectOne.no?num=" + num;
	  	})
   });
   </script>
<style>
.info {
	font-family: Roboto;
	font-style: normal;
	font-weight: 500;
	font-size: 15px;
	line-height: 18px;
}

.text {
	font-family: Roboto;
	font-style: normal;
	font-weight: 600;
	font-size: 16px;
	line-height: 18px;
	color: #343434;
}
.text2 {
	font-family: Roboto;
	font-style: normal;
	font-weight: 500;
	font-size: 14px;
	line-height: 18px;
	color: #343434;
}
.text3 {
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
	<script>
		$.ajax({
			type : "post",
			url : "/semiproject/selectPoint.me",
			data : {
				mNo: "<%=loginUser.getmNo()%>"
			},
			success: function(data){
				console.log("point성공입니다.");

				$("#currentPoint").html("보유포인트 : " + data + "p");
			},
			error: function(){
			}
		});
	</script>
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
					<br>
					<h1 style="color: #DA817F;">좋아요</h1>
					<br>
					<div style="width: 100%; background-color: pink; height: 30px; vertical-align: middle; padding-top:10px;">
						<label style="margin-left: 30px;"class="text">번호</label>
						<label style="margin-left: 80px;"class="text">제목</label>
						<label style="margin-left: 80px;"class="text">작성일자</label>
						<label style="margin-left: 80px;"class="text">평점</label>
						<label style="margin-left: 80px;"class="text">좋아요 수</label>
					</div>
					<table style="border-bottom: 1px solid pink" id="listArea">
						<tr>
							<td><input type="checkbox"></td>
							<td><div style="width:72px; margin-left:25px;"><label class="text2">5</label></div></td>
							<td><div style="width:60px; margin-left:20px;"><label class="text2">ym_r_01</label></div></td>
							<td><div style="width:100px; margin-left:60px;"><label class="text2">2020-05-11</label></div></td>
							<td><div style="width:80px; margin-left:55px;"><label class="text2">4.5</label></div></td>
							<td><div style="width:80px; margin-left:40px;"><label class="text2">450</label></div></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td><div style="width:72px; margin-left:25px;"><label class="text2">4</label></div></td>
							<td><div style="width:60px; margin-left:20px;"><label class="text2">ym_r_01</label></div></td>
							<td><div style="width:100px; margin-left:60px;"><label class="text2">2020-05-11</label></div></td>
							<td><div style="width:80px; margin-left:55px;"><label class="text2">4.5</label></div></td>
							<td><div style="width:80px; margin-left:40px;"><label class="text2">450</label></div></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td><div style="width:72px; margin-left:25px;"><label class="text2">3</label></div></td>
							<td><div style="width:60px; margin-left:20px;"><label class="text2">ym_r_01</label></div></td>
							<td><div style="width:100px; margin-left:60px;"><label class="text2">2020-05-11</label></div></td>
							<td><div style="width:80px; margin-left:55px;"><label class="text2">4.5</label></div></td>
							<td><div style="width:80px; margin-left:40px;"><label class="text2">450</label></div></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td><div style="width:72px; margin-left:25px;"><label class="text2">2</label></div></td>
							<td><div style="width:60px; margin-left:20px;"><label class="text2">ym_r_01</label></div></td>
							<td><div style="width:100px; margin-left:60px;"><label class="text2">2020-05-11</label></div></td>
							<td><div style="width:80px; margin-left:55px;"><label class="text2">4.5</label></div></td>
							<td><div style="width:80px; margin-left:40px;"><label class="text2">450</label></div></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td><div style="width:72px; margin-left:25px;"><label class="text2">1</label></div></td>
							<td><div style="width:60px; margin-left:20px;"><label class="text2">ym_r_01</label></div></td>
							<td><div style="width:100px; margin-left:60px;"><label class="text2">2020-05-11</label></div></td>
							<td><div style="width:80px; margin-left:55px;"><label class="text2">4.5</label></div></td>
							<td><div style="width:80px; margin-left:40px;"><label class="text2">450</label></div></td>
						</tr>
					</table>
					<br>
					<div align="center">
						<button class="text3" style="background: #E4E4E4;">취소</button>
						<button class="text3" style="background: #97D3D3; color:white">삭제</button>
					</div>	
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

	<script type="text/javascript">
    var minidaum_options = {

        enableLogoutRetun: false,
        returnUrl: '',
        disableHotissue: false,
        disableLogo: false,
        disableTracker: false,
        enableShield: false
    }
</script>

	<script type="text/javascript">
    $(document).ready(function(){
        Home.init();
        initProfileImage();
    });

    function hideElement(id) {
        $("#"+id).hide();
    }

    var Home= {
        init: function() {
            if(!false) {
                $("#recommnendContents").addClass("info_set")
                $("#recommnendContents").text("로그인시, 이중 보안되는 2단계 인증을 사용해보세요!")
            } else if(!true) {
                $("#recommnendContents").addClass("info_allow")
                $("#recommnendContents").text("로그인 허용 국가가 올바른지 확인해 보세요!")
            } else if(!false) {
                $("#recommnendContents").addClass("info_noticepw")
                $("#recommnendContents").text("비밀번호 변경알림을 이메일로 받아보세요!")
            } else if (2617 > 180){
                $("#recommnendContents").addClass("info_modifypw")
                $("#recommnendContents").text("6개월마다 새로운 비밀번호로 안전하게 관리해 주세요!")
            } else {
                $("#recommnendContents").addClass("info_safe")
                $("#recommnendContents").text("내정보 보호설정으로 안전하게 관리되고 있습니다!")
            }

            $(".link_user").on("focus", function(){
                $(".profile_edit").css({'display': 'none'});
            });

            $("#contactLink").on("focus", function(){
                $(".profile_edit").css({'display': 'none'});
            });
            $("#helpBtn").on("click", function(){
                $(".layer_certify").toggle();
            });
            $("#closeHelpBtn").on("click", function(){
                $(".layer_certify").css({'display': 'none'});
            })
        }
    }

    function initProfileImage() {
        $("#profileImageContainer").click(function () {
            profileImageHandler.hasProfileImageAlready('98f9',
                    function exist() {
                        $("#profileImageChangeDialog").show();
                        $("#profileImageAgreeDialog").hide();
                    },
                    function notExist() {
                        $("#profileImageChangeDialog").hide();
                        $("#profileImageAgreeDialog").show();
                    }
            );
        });

        profileImageHandler.initProfileImageUpload('98f9',
                'profileImageAgreeInput',
                function() {
                    $('#profileImageAgreeDialog').hide();
                },
                function(url) {
                    $('#profileImage').attr('src', url);
                }
        );
        profileImageHandler.initProfileImageUpload('98f9',
                'profileImageChangeInput',
                function() {
                    $('#profileImageChangeDialog').hide();
                },
                function(url) {
                    $('#profileImage').attr('src', url);
                }
        );

        $("#profileImageDisagree").click(function () {
            $("#profileImageAgreeDialog").hide();
        });

        $("#profileImageDelete").click(function () {
            $("#profileImageChangeDialog").hide();
            profileImageHandler.deleteProfileImage('98f9');
        });
    }
</script>



	<script type="text/javascript"
		src="//t1.daumcdn.net/tiara/js/v1/tiara.min.js"></script>
	<script type='text/javascript'>

    var profile = "";
    var isDevMode = "false";
    var customProps = { profile: profile, isDev: isDevMode };
    var deployment = "production"
    if (isDevMode === "true") {
        deployment = "dev";
    }
    TiaraTracker.getInstance()
            .setSvcDomain("member.daum.net")
            .setDeployment(deployment)
            .trackPage("Home")
            .setSection("member.web.home")
            .setPage("내정보_홈")
            .setReferrer(document.referrer)
            .setEnableHash(true)
            .customProps(customProps)
            .setAutoClick(true)
            .track();
</script>

</body>