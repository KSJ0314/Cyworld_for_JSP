<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/home_music.css">
<script type="text/javascript" src="resources/js/home_music.js" ></script>

<div class="wid-90 hei-100 flex flex-column flex-shrink">
	<!-- 곡 리스트 출력과 체크 기능 (배경음악으로 등록) -->
	<form class="wid-100 hei-80" action="/cyworld/music_play.home" name="mu_frm" method="post">
		<input type="hidden" value="${param.id }" name="id">
		<input type="hidden" value="main" name="page">
		<div class="wid-100 hei-10 flex flex-between flex-ali-center border">
			<c:if test="${login_id eq param.id }" var="isMy">
				<button type="submit" class="font-07">등록</button>
			</c:if>
			<c:if test="${!isMy }">
				<button onclick="dufle()" class="font-07">퍼가기</button>
			</c:if>
			<c:if test="${login_id eq param.id }">
				<button type="button" onclick="music_add()" class="font-07">곡 추가</button>
			</c:if>
		</div>
		<div class="wid-100 hei-90">
			<!-- 표 상단부 -->
			<div id="mu_title" class="flex font-07">
				<input type="checkbox" name="allCheck" class="wid-7_5 flex flex-center" onclick="checkAll()">
				<div class="wid-7_5 flex flex-center">번호</div>
				<div class="wid-5" id="break_line">|</div>
				<div class="wid-55">곡명</div>
				<div class="wid-5" id="break_line">|</div>
				<div class="wid-30">아티스트</div>
			</div>
			<!-- 표 내용부 -->	
			<c:forEach var="music" items="${music_list }" varStatus="i">
				<div id="mu_list" class="flex font-07">
					<input type="checkbox" name="check" class="wid-7_5 flex flex-center" value="${music.no }" <c:if test="${music.isPlay }">checked</c:if>>
					<div class="wid-7_5 flex flex-center">${music_list.size()-i.index}</div>
					<div class="wid-5 flex flex-center"><img alt="btn" src="resources/img/musicBtn.png"></div>
					<div class="wid-55">${music.title}</div>
					<div class="wid-5"></div>
					<div class="wid-30">${music.artist}</div>
				</div>
			</c:forEach>
		</div>
	</form>
	
<!-- 	<div class="wid-100 hei-10 flex flex-center border">
		페이징
	</div>
	<div class="wid-100 hei-10 flex flex-center border">
		검색
	</div> -->
</div>