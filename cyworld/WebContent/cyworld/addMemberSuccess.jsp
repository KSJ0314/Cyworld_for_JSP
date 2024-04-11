<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/layout.css">
<link rel="stylesheet" href="resources/css/addMember.css">
<script type="text/javascript" src="resources/js/addMember.js" ></script>
<title>회원가입</title>
</head>
<body class="flex flex-center flex-column">
	<div class="flex flex-center flex-column">
		<a href="index.jsp"><img alt="logo" src="resources/img/cyworld.jpg"></a>
		<h3>${param.id}님 가입을 환영합니다.</h3>
		<a href='login.jsp'>로그인 페이지로 이동</a>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>