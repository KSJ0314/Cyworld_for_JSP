<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/home_images.css">
<script type="text/javascript" src="resources/js/home_images.js" ></script>

<!-- 사진첩 게시물 작성 버튼 -->
<c:if test="${login_id eq param.id}">
	<div class="wid-90 hei-6 flex flex-jus-end" id="im_wr_btn" style="display: flex;">
		<a onclick="im_wr_onoff()"><img alt="im_wr" src="resources/img/im_wr.png" class="im_wr_btn hei-90"></a>
	</div>
</c:if>

<!-- 사진첩 게시물 작성 -->
<form action="/cyworld/writeImages.home" name="im_wr_frm" method="post" enctype="multipart/form-data" class="wid-90 hei-100 flex flex-column flex-shrink" id="im_wr" style="display: none;">
	<input type="hidden" name="id" value="${param.id}">
	<input type="hidden" name="page" value="${param.page}">
	<div class="wid-100 hei-9 flex flex-ali-center im_wr_title">
		<label>제목</label>&ensp;
		<input type="text" name="title" class="input wid-90 hei-100">
	</div>
	<div class="wid-100 hei-9 flex flex-ali-center im_wr_file">
		<input type="file" name="file" onchange="setThumbnail(event)" />
	</div>
	<div class="hei-72 pad im_wr_img">
		<div class="hei-80 photo-container" id="photo-container"></div>
		<textarea class="input wid-100 hei-20" name="content"></textarea>
	</div>
	<div class="wid-100 hei-10 im_wr_footer flex flex-ali-center flex-jus-end pad">
		<button type="button" onclick="location.reload()">취소</button>&ensp;
		<button type="submit">등록</button>
	</div>
</form>

<!-- 사진첩 게시물 출력 -->
<c:forEach var="images" items="${images_list}">
	<div class="wid-90 hei-100 flex flex-column flex-shrink">
		<div class="wid-100 hei-6 flex flex-center im_wr_title">
			<div class="im_li_title">${images.title}</div>&ensp;
		</div>
		<div class="wid-100 hei-6 flex flex-ali-center im_wr_file">
			<div class="im_li_id">${images.id}</div>&ensp;
			<div class="im_li_created">${images.created}</div>
		</div>
		<div class="hei-88 pad">
			<div class="hei-85 photo-container flex flex-center">
				<img class="im_img" alt="photo" src="resources/img/images/${images.imgName}">
			</div>
			<div class="input wid-100 hei-15">${images.content}</div>
		</div>
	</div>
</c:forEach>

<!-- 사진첩에 등록된 게시물이 없을 시 기본 이미지 -->
<c:if test="${images_list.size() == 0 }">
	<c:choose>
		<c:when test="${login_id eq param.id}">
			<div class="hei-90 flex flex-center">
				<img class="hei-50" alt="noImage" src="resources/img/images/owner_images.png">
			</div>
		</c:when>
		<c:otherwise>
			<div class="hei-100 flex flex-center">
				<img class="im_img" alt="noImage" src="resources/img/images/user_images.png">
			</div>
		</c:otherwise>
	</c:choose>
</c:if>










