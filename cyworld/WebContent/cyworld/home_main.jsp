<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/home_main.css">
<div style="display: none;">
	<div id="music_list_path">${music_list_path }</div>
	<div id="music_list_title">${music_list_title }</div>
</div>
<div class="wid-100 hei-100 flex flex-column flex-ali-center">
	<div class="wid-90">
		<!-- BGM영역 -->
		<div class="wid-100 flex flex-jus-end">
			<div class="videoWrapper wid-44 flex flex-between flex-ali-center font-07">
				<div class="wid-70 flex flex-ali-center">
					<div id="BGM"></div>
					<img class="wid-30" alt="bgm" src="resources/img/bgm.png" height="10">
					<p class="wid-65" id="bgmTitle"></p>
				</div>
				<div class="wid-30 hei-90 flex flex-ali-center">
					<img id="musicBtn" class="wid-20 hei-100" onclick="playVideo()" alt="bgm" src="resources/img/play.png" />
					<img id="musicBtn" class="wid-20 hei-100" onclick="nextVideo()" alt="bgm" src="resources/img/next.png" />
					<img id="musicBtn" class="wid-20 hei-100" onclick="pauseVideo()" alt="bgm" src="resources/img/pause.png" />
					<div id="musicList" class="wid-40 hei-100">
						<img id="musicBtn" class="wid-100 hei-100" onclick="music_select_on()" alt="bgm" src="resources/img/list.png" />
						<!-- 음악 선택 박스 -->
						<div id="music_select">
							<div class="wid-100 hei-100 flex flex-column flex-shrink">
								<div id="mu_title" class="flex" style="border-bottom: 1px solid #ABABAB;">
									<div class="wid-5"></div>
									<div class="wid-55">곡명</div>
									<div class="wid-5" id="break_line">|</div>
									<div class="wid-35">아티스트</div>
								</div>
								<div id="mu_list" class="flex flex-column">
									<c:forEach var="music" items="${music_list_true }" varStatus="i">
										<div class="flex">
											<div class="wid-5 flex flex-center"><img alt="play" src="resources/img/musicBtn.png" onclick="selectVideo(${i.index})"></div>
											<div class="wid-55" id="title">${music.title}</div>
											<div class="wid-5"></div>
											<div class="wid-30">${music.artist}</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 메뉴 영역 -->
		<div class="right-box-content flex flex-between">
			<!-- 최신 다이어리, 사진첩의 제목 출력 -->
			<div class="flex flex-column wid-53" style="justify-content: center;">
				<ul style="list-style: disc inside;">
					<li>그간의 일기...</li>
					<li id="title">
						<c:choose>
							<c:when test="${not empty lastTitle }">${lastTitle}</c:when>
							<c:otherwise>등록된 사진이 없습니다.</c:otherwise>
						</c:choose>
					</li>
				</ul>
			</div>
			<!-- 메뉴 바로가기, new 이미지 -->
			<div class="menuBox">
				<div class="flex">
					<p class="menu">투데이</p>
					<a class="menu" href='home.jsp?page=music&id=${param.id}'>주크박스</a>
				</div>
				<div class="flex">
					<a class="menu" href='home.jsp?page=images&id=${param.id}'>사진첩
						<!-- 오늘 날짜 기준으로 새로운 사진첩이 있는지 판단하여 new 이미지 출력 -->
						<c:if test="${dateNew}">
							<img alt="new" src="resources/img/New.png">
						</c:if>
					</a>
					<a class="menu" href='home.jsp?page=guestBook&id=${param.id}'>방명록
						<!-- 홈페이지 주인이 새로운 방명록을 확인했는지 판단하여 new 이미지 출력 -->
						<c:if test="${dto.id eq login_id && !isGBCheck}">
							<img alt="new" src="resources/img/New.png">
						</c:if>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="flex flex-center hei-79 wid-90">
		<img class="box-border wid-100 hei-100" alt="big" src="resources/img/Big.jpg">
	</div>
</div>
<script src="https://www.youtube.com/iframe_api"></script>
<script type="text/javascript" src="resources/js/home_main.js" ></script>