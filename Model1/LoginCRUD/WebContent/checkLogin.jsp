<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%
	String u_id = request.getParameter("u_id");
	String u_pw = request.getParameter("u_pw");
	// findMember() -> id와 일치하는 객체를 찾는다.
	Member member = MemberManager.findMember(application, u_id);
	String msg;
	String nextPage = "loginForm.jsp";
	// 아이디가 application영역상에 존재 하지 않을때
	if(member == null){
		msg = "가입 후 이용해 주세요";
	}else{
		// 존재하지만 비밀번호가 다를때
		if(!member.getU_pw().equals(u_pw)){
			msg = "비밀번호가 일치하지 않습니다.";
		}else{
			// 모든 조건에 만족할때
			msg = member.getU_nick() + "님 환영합니다.";
			nextPage = "loginSuccess.jsp";
			// 세션에 로그인 정보(아이디, 비밀번호) 저장
			session.setAttribute("member", member);
		}
	}
	session.setAttribute("msg", msg);
%>
<jsp:forward page="<%= nextPage %>" />
