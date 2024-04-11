<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/layout.css">
<link rel="stylesheet" href="resources/css/index.css">
<title>홈</title>
</head>
<body class="flex flex-center flex-column">
	<div class="flex flex-center flex-column">
		<!-- 로고 클릭 시 홈피로 이동 -->
		<a href="home.jsp?page=main&id=${login_id}"><img class="logo" alt="logo" src="resources/img/cyworld.jpg"></a>
		<h3 class="welcome">싸이월드에 오신것을 환영합니다 !</h3>
		<div>
			<c:choose>
				<c:when test="${empty login_id}">
					<a href="login.jsp" class="point">로그인</a>
				</c:when>
				<c:otherwise>
					<a href="/cyworld/logout.member" class="point">로그아웃</a>
				</c:otherwise>
			</c:choose>&emsp;&ensp;
			<a href="addMember.jsp">회원가입</a>	
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>