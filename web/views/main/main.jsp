<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*, com.kh.semi.board.model.vo.*"%>
<%
	String backPage = request.getContextPath() + "/ma";
	session.setAttribute("backPage", backPage);

	ArrayList<BoardUpVo> blist = (ArrayList<BoardUpVo>) request.getAttribute("boardList");
	//ArrayList<BoardUpVo> clist = (ArrayList<BoardUpVo>) request.getAttribute("courseList");
	
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<!-- Styles -->
<!-- Bootstrap CSS -->
<link href="/semiproject/views/main/css/bootstrap.min.css" rel="stylesheet">
<!-- Font awesome CSS -->
<link href="/semiproject/views/main/css/font-awesome.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="/semiproject/views/main/css/style.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- Favicon -->
<link rel="shortcut icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<link rel="icon" href="/semiproject/images/favicon.ico" type="image/x-icon">
<title>YUMEET</title>
</head>

<body id="mainWidth" onload="showImage();" style="width:1380px; margin:0; padding:0; oveflow-x:hidden !important;">
   <%@include file="/views/common/header.jsp"%>
   <div>
      <!-- banner -->
      <div class="banner" style="padding-top: -1px; margin-top: -300px; height: 500px; position: relative; oveflow-x:hidden;">
         <div>
            <img id="introImg" style="width:100%; oveflow:hidden; height: 600px;">
         </div>
         <div class="container" style="background: white; height: 60px; width: 600px; padding: 1px; margin-bottom: -100px; position: absolute; top: 120%; left: 38%; border-radius: 10px; font-size: 22px; margin-left: -50px;">
            <button id="recommendBtn" style="height: 100%; background: white; float: left; margin-left: 5px; border: 0px white;">
               <img src="/semiproject/images/Vector.png">
               
            </button>
            <input  onkeyup="if(event.keyCode === 13) { searchEnp(); }" id="search" name="search" type="search" style="height: 100%; width: 80%; border: 1px solid white; background: white; padding-left: 10px; float:left;">
            <button onclick="searchEnp();" style="height: 95%; background: white; border: 0px white; float: right; margin-right: 5px;">
                <img src="/semiproject/images/searchicon.png">
            </button>
            <script>
	            $("#recommendBtn").click(function() {
					var s = $("#search").val();
					
					if(s === "양재") {
						var flag = window.confirm("양재동은 어떠세요?");
						
						if(flag) {
							$("#search").val("양재동");
							searchEnp();
						}
					}
					
					if(s === "강남") {
						var flag = window.confirm("강남구는 어떠세요?");
						
						if(flag) {
							$("#search").val("강남구");
							searchEnp();
						}
					}
					
					if(s === "삼겹") {
						var flag = window.confirm("삼겹살은 어떠세요?");
						
						if(flag) {
							$("#search").val("삼겹살");
							searchEnp();
						}
					}
				}); 
            
	    		var windowWidth = $( window ).width();
	    			$("#mainWidth").width(windowWidth);
	    		var windowHeight = $( window ).height();
	    		$( window ).resize(function() {
	    			windowWidth = $( window ).width();
	    			if(windowWidth > 1400) {
	    				$("#mainWidth").width(windowWidth);
	    			} else {
	    				$("#mainWidth").width(1400);
	    			}
	    		});
	    		
            	function searchEnp() {
            		var search = $("#search").val();
            		
            		location.href="<%= request.getContextPath() %>/searchEnp.se?search=" + search + "&currentPage=1";
            	}
            </script>
         </div>
      </div>
      <!-- banner end -->
      <!-- works -->
      <div class="works" id="work" style="margin-top: 300px;">
         <div class="container" style="border: 0px white; width: 100%; box-shadow: 0px;">
            <h2 style="color: #D5706D; margin-left: 50px">오늘의 메뉴</h2>
            <div class="row" style="margin-top: 0px;">
               <%for(int i = 4; i < 8; i ++) {%>
                
                <div class="col-md-3">
                  <!-- work item -->
                  <div class="work-item">
                     <!-- work details image -->
                     <img class="img-responsive" src="<%=request.getContextPath()%>/thumbnail_uploadFile/<%= blist.get(i).getChangeName() %>" style="width: 100%; height: 171px;" />
                     <!-- heading -->
                     <h3>
                        <a href="/semiproject/selectMainEnp.en?enpNo=<%=blist.get(i).getEnpNo() %>" style="color: black; font-weight: 600;"><%=blist.get(i).getBoardTitle() %></a>
                     </h3>
                     <!-- brand org -->
                     <span class="org"><%=blist.get(i).getAddress() %></span>
                  </div>
               </div> 
              
               <%} %>
               <br>
               
               <%for(int i = 8; i < 12; i ++) {%>
                <div class="col-md-3">
                  <!-- work item -->
                  <div class="work-item">
                     <!-- work details image -->
                     <img class="img-responsive" src="<%=request.getContextPath()%>/thumbnail_uploadFile/<%=blist.get(i).getChangeName() %>" style="width: 100%; height: 171px;" />
                     <!-- heading -->
                     <h3>
                        <a href="/semiproject/selectMainEnp.en?enpNo=<%=blist.get(i).getEnpNo() %>" style="color: black; font-weight: 600;"><%=blist.get(i).getBoardTitle() %></a>
                     </h3>
                     <!-- brand org -->
                     <span class="org"><%=blist.get(i).getAddress() %></span>
                  </div>
               </div> 
               <%} %>
               
               
            </div>
         </div>
      </div>
      <hr
         style="border: 1px solid black; color: gray; width: 50%; align: center;">
      <br>
      <br>
      <br>
      <h2 style="color: #D5706D; margin-left: 50px;">코스 리뷰</h2>
      <div class="container" style="border: 0px white; width: 1400px; height: 450px">
	  <div class="slideshow-container">

		<%-- <div class="mySlides fade">
		  <div class="numbertext"></div>
		  <%for(int i = 0; i < 1; i ++){ %>
		  <div style="position: relative;">
		  <img onclick="click11(this);" name="<%=clist.get(i).getBoardNo()%>" style="width:47%; height: 350px; margin-right:3%; display: inline-block;" src="/semiproject/thumbnail_uploadFile/<%=clist.get(i).getChangeName() %>">
		  <img onclick="click11(this);" name="<%=clist.get(i + 1).getBoardNo()%>" style=" float:right; width:47%; height: 350px; display: inline-block;" src="/semiproject/thumbnail_uploadFile/<%=clist.get(i + 1).getChangeName() %>">
		  <div style="text-overflow:ellipsis; overflow:hidden; color: white;left: 170px; width: 400px; bottom: 160px; font-size: 1.8em; font-weight: bold; position: absolute;"><%=clist.get(i).getBoardTitle() %></div>
		  <div style="text-overflow:ellipsis; overflow:hidden; color: white;left: 900px; width: 400px; bottom: 160px; font-size: 1.8em; font-weight: bold; position: absolute;"><%=clist.get(i).getBoardTitle() %></div>
		  </div>
		  <%} %>
		</div>

		<div class="mySlides fade">
		    <%for(int i = 2; i < 3; i ++){ %>
		  <div style="position: relative;">
		  <img onclick="click11(this);" name="<%=clist.get(i).getBoardNo()%>" style="width:47%; height: 350px; margin-right:3%; display: inline-block;" src="/semiproject/thumbnail_uploadFile/<%=clist.get(i).getChangeName() %>">
		  <img onclick="click11(this);" name="<%=clist.get(i + 1).getBoardNo()%>" style=" float:right; width:47%; height: 350px; display: inline-block;" src="/semiproject/thumbnail_uploadFile/<%=clist.get(i + 1).getChangeName() %>">
		  <div style="text-overflow:ellipsis; overflow:hidden; color: white;left: 170px; width: 400px; bottom: 160px; font-size: 1.8em; font-weight: bold; position: absolute;"><%=clist.get(i).getBoardTitle() %></div>
		  <div style="text-overflow:ellipsis; overflow:hidden; color: white;left: 900px; width: 400px; bottom: 160px; font-size: 1.8em; font-weight: bold; position: absolute;"><%=clist.get(i).getBoardTitle() %></div>
		  </div>
		  <%} %>
		</div>

		<div class="mySlides fade">
		  <div class="numbertext"></div>
		   <%for(int i = 4; i < 5; i ++){ %>
		  <div style="position: relative;">
		  <img onclick="click11(this);" name="<%=clist.get(i).getBoardNo()%>" style="width:47%; height: 350px; margin-right:3%; display: inline-block;" src="/semiproject/thumbnail_uploadFile/<%=clist.get(i).getChangeName() %>">
		  <img onclick="click11(this);" name="<%=clist.get(i + 1).getBoardNo()%>" style=" float:right; width:47%; height: 350px; display: inline-block;" src="/semiproject/thumbnail_uploadFile/<%=clist.get(i + 1).getChangeName() %>">
		  <div style="text-overflow:ellipsis; overflow:hidden; color: white;left: 170px; width: 400px; bottom: 160px; font-size: 1.8em; font-weight: bold; position: absolute;"><%=clist.get(i).getBoardTitle() %></div>
		  <div style="text-overflow:ellipsis; overflow:hidden; color: white;left: 900px; width: 400px; bottom: 160px; font-size: 1.8em; font-weight: bold; position: absolute;"><%=clist.get(i).getBoardTitle() %></div>
		  </div>
		  <%} %>
		</div> --%>

		<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
		<a class="next" onclick="plusSlides(1)">&#10095;</a>

	</div>
	</div>
	</div>
<script>
function click11(data){
	console.log(data.name);
	location.href="/semiproject/selectOneCourse?no=" + data.name;
}
</script>
<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span> 
  <span class="dot" onclick="currentSlide(2)"></span> 
  <span class="dot" onclick="currentSlide(3)"></span> 
</div>
      
      <hr style="border: 1px solid black; color: gray; width: 50%; align: center;">
      <br>
      <br>
      <br>
      
      <div class="works" id="work" style="margin-top: -180px;">
         <div class="container" style="border: 0px white; width: 100%; box-shadow: 0px;">
            <h2 style="color: #D5706D; margin-left: 50px">YUMEET'S PICK</h2>
            <div class="row" style="margin-top: 0px;">
               <%for(int i = 12; i < 16; i ++) {%>
                <div class="col-md-3">
                  <!-- work item -->
                  <div class="work-item">
                     <!-- work details image -->
                     <img class="img-responsive" src="<%=request.getContextPath()%>/thumbnail_uploadFile/<%= blist.get(i).getChangeName() %>" style="width: 100%; height: 171px;" />
                     <!-- heading -->
                     <h3>
                        <a href="/semiproject/selectMainEnp.en?enpNo=<%=blist.get(i).getEnpNo() %>" style="color: black; font-weight: 600;"><%=blist.get(i).getBoardTitle() %></a>
                     </h3>
                     <!-- brand org -->
                     <span class="org"><%=blist.get(i).getAddress() %></span>
                  </div>
               </div> 
               <%} %>
               <br>
               
               <%for(int i = 16; i < 20; i ++) {%>
                <div class="col-md-3">
                  <!-- work item -->
                  <div class="work-item">
                     <!-- work details image -->
                     <img class="img-responsive" src="<%=request.getContextPath()%>/thumbnail_uploadFile/<%=blist.get(i).getChangeName() %>" style="width: 100%; height: 171px;" />
                     <!-- heading -->
                     <h3>
                        <a href="/semiproject/selectMainEnp.en?enpNo=<%=blist.get(i).getEnpNo() %>" style="color: black; font-weight: 600;"><%=blist.get(i).getBoardTitle() %></a>
                     </h3>
                     <!-- brand org -->
                     <span class="org"><%=blist.get(i).getAddress() %></span>
                  </div>
               </div> 
               <%} %>
            </div>
         </div>
      </div>
     </div> 
   <br><br><br>
<script>
	var slideIndex = 1;
	showSlides(slideIndex);
	
	function plusSlides(n) {
	  showSlides(slideIndex += n);
	}
	
	function currentSlide(n) {
	  showSlides(slideIndex = n);
	}
	
	function showSlides(n) {
	  var i;
	  var slides = document.getElementsByClassName("mySlides");
	  var dots = document.getElementsByClassName("dot");
	  if (n > slides.length) {slideIndex = 1}    
	  if (n < 1) {slideIndex = slides.length}
	  for (i = 0; i < slides.length; i++) {
	      slides[i].style.display = "none";  
	  }
	  
	  for (i = 0; i < dots.length; i++) {
	      dots[i].className = dots[i].className.replace("active", "");
	  }
	  
	  slides[slideIndex-1].style.display = "block";  
	  dots[slideIndex-1].className += " active";
	}
</script>
<%@include file="/views/common/footer.jsp" %>
      <!-- Javascript files -->
      <!-- jQuery -->
      <script src="/semiproject/views/main/js/jquery.js"></script>
      <!-- Bootstrap JS -->
      <script src="/semiproject/views/main/js/bootstrap.min.js"></script>
      <!-- Respond JS for IE8 -->
      <script src="/semiproject/views/main/js/respond.min.js"></script>
      <!-- HTML5 Support for IE -->
      <script src="/semiproject/views/main/js/html5shiv.js"></script>
      <!-- Custom JS -->
      <script src="/semiproject/views/main/js/custom.js"></script>
      
         <script type="text/javascript">
        var imgArray= new Array();
        imgArray[0]="/semiproject/images/mainImages/main1.png"; 
        imgArray[1]="/semiproject/images/mainImages/main2.png"; 
        imgArray[2]="/semiproject/images/mainImages/main3.png";    
        imgArray[3]="/semiproject/images/mainImages/main4.png";    
        imgArray[4]="/semiproject/images/mainImages/main5.png";    
        imgArray[5]="/semiproject/images/mainImages/main6.png";    
        imgArray[6]="/semiproject/images/mainImages/main7.png";    
        imgArray[7]="/semiproject/images/mainImages/main8.png";    
        imgArray[8]="/semiproject/images/mainImages/main9.png";    
        imgArray[9]="/semiproject/images/mainImages/main10.png";    
               
        function showImage()
        {
            var imgNum=Math.floor(Math.random()*10);
            var objImg=document.getElementById("introImg");
            objImg.src=imgArray[imgNum];
        }
    </script>
</body>
</html>