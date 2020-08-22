<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("euc_kr");
%>
<!-- 자바빈에서 Member객체 등록 -->
<jsp:useBean id="member" class="org.doo.Member" />
<!-- * -> 모든 값을 setter(입력한 폼의 name 값 = 멤버변수 값 -->
<jsp:setProperty property="*" name="member"/> 

<%
	// 가입 성공
	String msg ="회원가입 성공";
	String nextPage = "loginForm.jsp";
	// 가입 실패 (addMember()에서 중복된 아이디가 있으면 false를 반환 !false
	if(!MemberManager.addMember(application, member)){
		msg = "이미 존재하는 아이디 입니다.";
		// 중복이 있을시 이전의 값들을 다시 원상복귀 시킨다.(submit()시 초기화 되기때문)
		session.setAttribute("tempMember", member);
		// 실패시 다시 Join창으로 이동
		nextPage = "join.jsp";
	}
	session.setAttribute("msg", msg);
	response.sendRedirect(nextPage);
%>