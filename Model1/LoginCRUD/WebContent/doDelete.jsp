<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%@ include file="checkSession.jspf" %>    
<%	
	// deleteMember() -> application <-> session(u_id)일치하는 것이 있다면 정상적으로 삭제
	if(MemberManager.deleteMember(application, member.getU_id())){
		session.invalidate();
		session = request.getSession();
		session.setAttribute("msg", "회원탈퇴가 완료되었습니다.");
		response.sendRedirect("loginForm.jsp");
	}else{
		// 없으면 오류 알림 후 값 들고 info로 다시 이동
		session.setAttribute("msg", "회원탈퇴중 오류가 발생했습니다.");
%>
	<jsp:forward page="info.jsp" />
<%
	}
%>