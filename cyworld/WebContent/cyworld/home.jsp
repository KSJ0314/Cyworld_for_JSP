<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="resources/js/home.js" ></script>
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/layout.css">
<link rel="stylesheet" href="resources/css/home.css">
<title>Insert title here</title>
</head>
<body>
	<!-- list 클릭시 화면 어둡게	 -->
	<div id="black"></div>
	<!-- 전체 중앙 배치 -->
	<div class="flex flex-center linear wid-100 hei-100">
		<!-- 실선 박스 -->
		<div class="body box-radius-5 flex flex-center">
			<!-- 점선 박스 -->
			<div class="body-das flex flex-center wid-100">
				<!-- 파란 배경 -->
				<div class="box-radius-5 container flex flex-around wid-100">
					<!-- 페이지 좌측 프로필 박스 -->
					<div class="wid-25 flex flex-column flex-center flex-evenly">	
						<div class="text-center flex flex-column flex-between wid-90 hei-13">
							<div class="box-radius-5 back-white line-25 font-09">${dto.id}님의 미니홈피</div>
							<div class="box-radius-5 back-white line-15 font-08">Today <span style="color: red; font-size: 1.2em;">222</span> | Total 1111</div>
						</div>
						<div class="left-box box-radius-5 flex flex-center hei-82 pad-10">
							<div class="box-radius-5 wid-100 hei-100 back-white flex flex-between flex-column pad-10">
								<div class="profile-wrapper wid-100 hei-35">
									<img class="profile wid-100 hei-100" alt="profile" src="resources/img/${dto.imgName}">
								</div>
								<div class="dot-line wid-100"></div>
								<div class="hei-25">
									<div class="box-radius-5 text-center font-08 line-17">TODAY IS... ✨ 파이팅!</div>
									<div>${dto.id}의 미니홈피에 오신것을 환영합니다!</div>
								</div>
								<div class="dot-line wid-100"></div> 
								<div class="hei-25 flex flex-column flex-between">
									<div>소개글</div>
									<select id="pageSelect" onchange="openPage()">
										<option value="">파도타기</option>
										<option value="http://www.naver.com">네이버</option>
										<option value="http://www.google.com">구글</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<!-- 연결고리 이미지 영역 -->
					<div class="ring flex flex-center flex-column flex-evenly hei-100">
						<div class="wid-100 hei-13"></div>
						<div class="wid-100 hei-82 flex flex-column flex-between">
							<div class="hei-35">
								<div class="hei-20"></div>
								<div class="hei-80 flex flex-column flex-evenly">
									<img class="wid-100" alt="ring" src="resources/img/ring.png">
									<img class="wid-100" alt="ring" src="resources/img/ring.png">
								</div>
							</div>
							<div class="hei-35">
								<div class="hei-80 flex flex-column flex-evenly">
									<img class="wid-100" alt="ring" src="resources/img/ring.png">
									<img class="wid-100" alt="ring" src="resources/img/ring.png">
								</div>
								<div class="hei-20"></div>
							</div>
						</div>
					</div>
					<!-- 페이지 우측 메인 박스 -->
					<div class="wid-70 flex flex-column flex-center flex-evenly">
						<!-- 로고 : 클릭 시 마이 홈피로 이동 -->
						<div class="box-radius-5 wid-50 hei-13 back-white flex flex-center">
							<a class="hei-80" href="home.jsp?page=main&id=${login_id}"><img class="wid-100 hei-100" alt="cy-literal-logo" src="resources/img/Cyworld-literal.svg"></a>
						</div>
						<!-- 홈, 사진첩, 방명록등의 바로가기를 우측하단 영역에 include -->
						<div class="box-radius-5 wid-100 hei-82 back-white pad-10" id="page">
							<div class="right-box hei-100 flex flex-column flex-ali-center">
								<jsp:include page="home_${param.page}.jsp" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 페이지 바로가기 버튼 영역 -->
		<div class="page_button flex flex-center">
			<form action="" class="flex flex-column flex-evenly hei-60" name="frm">
				<input type="hidden" value="${param.id}" name="id">
				<button type="submit" onsubmit="pageChange('main')" name="page" value="main" id="main">홈</button>
				<button type="submit" onsubmit="pageChange('images')" name="page" value="images" id="images">사진첩</button>
				<button type="submit" onsubmit="pageChange('guestBook')" name="page" value="guestBook" id="guestBook">방명록</button>
				<button type="submit" onsubmit="pageChange('music')" name="page" value="music" id="music">주크박스</button>
				<button type="submit" onsubmit="pageChange('main')" name="page" value="">5</button>
				<button type="submit" onsubmit="pageChange('main')" name="page" value="">6</button>
				<button type="submit" onsubmit="pageChange('main')" name="page" value="">7</button>
				<button type="submit" onsubmit="pageChange('main')" name="page" value="">8</button>
				<button type="submit" onsubmit="pageChange('main')" name="page" value="">9</button>
			</form>
		</div>
		<div style="height: 520px;">
			<!-- 나가기 버튼, index.jsp로 이동 -->
			<button class="out" onclick="location.href='/cyworld/cyworld/index.jsp'">X</button>
		</div>
	</div>
</body>
</html>