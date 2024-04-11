<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/layout.css">
<script src="resources/js/idCheck.js" ></script>
</head>
<body class="flex flex-center flex-column">
	<div class="flex flex-center flex-column hei-80 wid-55">
		<h2>아이디 중복 체크</h2><br>
		<form action="/cyworld/login.member" method="get" name="idCheckFrm" class="flex flex-between wid-100" style="margin-bottom: 20px">
			<input type="text" name="id">
			<input type="submit" value="중복확인">
		</form>
		<div id="message" class="flex flex-column felx-center wid-100">
			<c:if test="${param.code == 0}">
				<h4 align="center"><span id="id">${param.idCheck }</span>은 사용 가능한 아이디입니다.</h4>
				<button style='margin-top:20px;' onclick='enter();'>사용하기</button>
			</c:if>
			<c:if test="${param.code == 1}">
				<h4 align="center">중복된 아이디입니다.</h4>
			</c:if>
		</div>
	</div>
</body>
</html>