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
</head>
<body class="flex flex-center flex-column">
	<div class="flex flex-center flex-column hei-80 wid-100">
		<h3 align="center">배경음악 추가</h3><br>
		<form action="/cyworld/music_add.home" method="post" name="idCheckFrm" class="wid-100 hei-80 flex flex-column flex-ali-center">
			<div class="wid-80 hei-40 flex flex-between">
				<div class="wid-45">
					<label>곡명</label>			
					<input type="text" name="title">
				</div>
				<div class="wid-45">
					<label>아티스트</label>			
					<input type="text" name="artist">
				</div>
			</div>
			<div class="wid-80 hei-40 flex flex-column">
				<div class="wid-100 flex flex-center">
					<label class="wid-15">주소</label>
					<input class="wid-85" type="text" name="path">
				</div>
				<p>※ Youtube의 링크를 복사해주세요</p>
			</div>
			<button type="submit">등록하기</button>
		</form>
	</div>
</body>
</html>