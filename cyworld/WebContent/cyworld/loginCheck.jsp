<%@page import="util.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("login_id") == null) {
		JSFunction.alertLocation("회원만 접근 가능합니다.", "/cyworld/cyworld/index.jsp",out);
		return;
	}
%>