<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/home_guestBook.css">
<!-- 방명록 작성 -->
<c:if test="${login_id!=param.id }">
	<form action="/cyworld/writeBook.home" method="post" class="gB_Box wid-90 flex-shrink">
		<input type="hidden" name="id" value="${param.id}">
		<input type="hidden" name="page" value="${param.page}">
		<div class="gB_title flex flex-ali-center wid-100 hei-15">
			${login_id}
		</div>
		<div class="gB_main flex flex-ali-center">
			<div class="gB_profile wid-23 hei-100">
				<img alt="profile" src="resources/img/${login_dto.imgName}" class="wid-100 hei-100">
			</div>
			<div class="gB_content write wid-70 hei-100">
				<input class="wid-100 hei-100" type="text" name="content">
			</div>
		</div>
		<div class="gB_bottom flex flex-ali-center flex-jus-end">
			<button type="submit">등록</button>
		</div>
	</form>
</c:if>
<!-- 방명록 목록 출력 -->
<c:forEach var="gB_dto" items="${guestbook_list}" varStatus="i">
	<div class="gB_Box wid-90 flex-shrink">
		<div class="gB_title wid-100 flex flex-between">
			<div class="flex flex-ali-center">
				NO.${guestbook_list.size()-i.index } &ensp; ${gB_dto.post_id} &ensp; <a href="home.jsp?page=main&id=${gB_dto.post_id}" class="flex flex-center"><img alt="home" src="resources/img/홈.png"></a> &ensp; (${gB_dto.created})
			</div>
			<!-- 방명록 삭제 버튼 -->
			<c:if test="${dto.id eq login_id || gB_dto.post_id eq login_id}">
				<form action="/cyworld/deleteBook.home" method="post" onsubmit="return confirm('정말로 삭제하시겠습니까?')">
					<input type="hidden" name="no" value="${gB_dto.no}">
					<input type="hidden" name="id" value="${param.id}">
					<input type="hidden" name="page" value="${param.page}">
					<button type="submit">X</button>
				</form>
			</c:if>
		</div>
		<div class="gB_main flex flex-ali-center">
			<div class="gB_profile wid-23 hei-100">
				<img alt="profile" src="resources/img/${gB_dto.imgName}" class="wid-100 hei-100">
			</div>
			<div class="gB_content">${gB_dto.content}</div>
		</div>
		<!-- 방명록의 댓글 목록 출력 -->
		<c:forEach var="re" items="${gB_dto.reply}">
			<div class="re flex flex-between">
				<div class="flex flex-ali-center wid-90">
			        <div class="wid-20">${re.id}</div>
			        <div class="wid-40">(${re.created})</div>
			        <div>${re.content}</div>
		        </div>
		        <!-- 댓글 삭제 버튼 -->
		        <div class="wid-10">
	        		<c:if test="${login_id eq re.id || login_id eq param.id}">
	        			<form action="/cyworld/deleteRe.home" method="post" onsubmit="return confirm('정말로 삭제하시겠습니까?')">
							<input type="hidden" name="r_no" value="${re.r_no}">
							<input type="hidden" name="id" value="${param.id}">
							<input type="hidden" name="page" value="${param.page}">
			        		<button type="submit">X</button>
	        			</form>	
	        		</c:if>
	        	</div>
			</div>
        </c:forEach>
		<!-- 방명록에 댓글 작성 -->
		<form action="/cyworld/writeReply.home" method="post" class="re flex flex-ali-center gB_bottom" style="height: 44px">
			<input type="hidden" name="id" value="${param.id}">
			<input type="hidden" name="page" value="${param.page}">
			<input type="hidden" name="g_no" value="${gB_dto.no }">
			<div class="wid-18">${login_id}</div>
			<div class="write wid-82 hei-100 writeBtnBox">
				<input class="hei-100 wid-88" type="text" name="content" maxlength="50">
				<input class="writeBtn" type="submit" value="작성" />
			</div>
		</form>
	</div>
</c:forEach>
