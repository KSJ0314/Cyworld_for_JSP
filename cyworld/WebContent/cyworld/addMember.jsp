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
		<a href="index.jsp"><img class="logo" alt="logo" src="resources/img/cyworld.jpg"></a>
		<form name="frm" action="/cyworld/addMember.member" method="post" onsubmit="return pwCheck();" enctype="multipart/form-data">
			<div>
				<div class="wid-40">
					<label>아이디</label>
				</div>
				<div class="wid-60 flex flex-between">
					<input type="text" name="id" class="wid-45" readonly id="id"/>
					<button type="button" class="wid-45" onclick="idCheck()">중복확인</button>
				</div>
			</div>
			<div>
				<div class="wid-40">
					<label>비밀번호</label>
				</div>
				<div class="wid-60 flex flex-between">
					<input type="password" name="pw" class="wid-100" maxlength="8" id="pw"/>
				</div>
			</div>
			<div>
				<div class="wid-40">
					<label>비밀번호 확인</label>
				</div>
				<div class="wid-60 flex flex-between">
					<input type="password" name="pw2" class="wid-100" maxlength="8" id="pw2"/>
				</div>
			</div>
			<div class="pwCheckBox">
			</div>
			<div>
				<div class="wid-40">
					<label>이메일</label>
				</div>
				<div class="wid-60 flex flex-between">
					<input type="text" name="email" class="wid-45">@
					<select name="domain" class="wid-45">
						<option value="@naver.com">naver.com</option>
						<option value="@kakao.com">kakao.com</option>
						<option value="@google.com">google.com</option>
					</select>
				</div>
			</div>
			<div>
				<div class="wid-40">
					<label>전화번호</label>
				</div>
				<div class="wid-60 flex flex-between">
					<select name="tel1" class="wid-30">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
					</select>
					<input type="text" name="tel2" class="wid-30" maxlength="4"/>
					<input type="text" name="tel3" class="wid-30" maxlength="4"/>
				</div>
			</div>
			<div>
				<div class="wid-40">
					<label>직급</label>
				</div>
				<div class="wid-60">
					<input type="radio" name="rank" checked value="common">&ensp;일반&ensp;
					<input type="radio" name="rank" value="admin">&ensp;관리자&ensp;
				</div>
			</div>
			<div>
				<div class="wid-40">
					<label>사진 추가</label>
				</div>
				<div class="wid-60 flex flex-between">
					<input type="file" name="file" class="wid-100"/>
				</div>
			</div>
			<div class="flex flex-center">
				<input type="submit" value="회원가입" />
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>