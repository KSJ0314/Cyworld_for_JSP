<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/css/layout.css">
<title>로그인</title>
</head>
<body class="flex flex-center flex-column">
	<div class="flex flex-center flex-column">
		<a href="index.jsp"><img class="logo" alt="logo" src="resources/img/cyworld.jpg"></a>
		<form action="/cyworld/login.member" method="post">
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" />
			</div>
			<div>
				<label for="pw">비밀번호</label>
				<input type="password" name="pw" />
			</div>
			<div>
				<label id="error" class="flex flex-center">
					<c:if test="${param.error == 1}">
						존재하지 않는 아이디입니다.
					</c:if>
					<c:if test="${param.error == 2}">
						비밀번호가 맞지 않습니다.
					</c:if>
				</label>
				<input type="submit" value="로그인" />
			</div>
		</form>
		<div class="addMessage right">
			<p>계정이 없으신가요? <a href="addMember.jsp" class="point">회원가입</a></p>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>