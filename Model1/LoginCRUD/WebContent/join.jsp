<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="org.doo.*"%>
<%
	// 세션에 저장된 msg
	String msg = (String)session.getAttribute("msg");
	if(msg == null){
		msg = "모든 항목은 필수입력입니다.";
	}
	
	// 세션에 저장되어있는 member의 정보가 담긴 객체
	Member member = (Member)session.getAttribute("tempMember");
	// 처음에 로그인 폼에서 넘어올 때는 비어있어야하기 때문에 getBlankMember()메서드를 사용
	if(member == null){
		// 만약 빈 값이라면 getBlacnkMember()를 호출하여 ""값을 부여
		member = MemberManager.getBlankMember();
	}
	// 세션 사용후 삭제
	session.removeAttribute("tempMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script>
	// 회원가입
	function checkAndMove(){
		// joinForm은 form의 name값(문서상의 joinForm)
		var obj = document.joinForm;
		// 시작값 true
		var flag = true;
		// 조건 : true, obj안에 유효한 값
		for(var i = 0; flag && i<obj.length; i++){
			// joinForm안의 타입 값을 소문자로 변경 -> 타입값이 button아닐 경우
			if(obj[i].type.toLowerCase() != "button"){
				// button제외한 타입들의 값이 공백일 경우
				if(obj[i].value.trim().length == 0){
					// id가 msg인 태그에  알림말 + 공백이 있는 태그 이름을 출력
					// innerHTML -> 
					document.getElementById("msg").innerHTML =
						"필수입력항목 누락 : " + obj[i].title;
					// 공백이 있으니 다시 공백검사하러 순환
					flag = false;
				}
			}
		}
		// 공백이 없을시 
		if(flag){
			// form에 입력된 비밀번호와 재입력 값을 비교
			if(obj.u_pw.value != obj.u_re.value){
				// 일치하지 않으면 알림 출력
				document.getElementById("msg").innerHTML = "비밀번호가 일치하지 않습니다.";
				// 다시 순환
				flag = false;
			}
		}
		
		// 유효성 검사 종료후 전송
		if(flag){
			obj.method = "post";
			obj.action = "doJoin.jsp";
			obj.submit();
		}
	}
	 
	// 회원가입 취소
	function cancelJoin(){
		// 취소 메시지 전달후 이동
		location.href="cancelJoin.jsp";
	}
</script>
</head>
<body>
	<p class="titleStr">
		Join Member
	</p>
	<form name="joinForm">
		<div class="centerBox">
			<label for="u_id">ID :</label>
			<!-- value에 값 : 입력 실패시 기존의 값을 유지하기위해서 세션에서 받아옴 -->
			<input type="text" name="u_id" title="ID" value="<%=member.getU_id() %>" /><br/>
			<label for="u_pw">PW :</label>
			<input type="text" name="u_pw" title="PASSWORD" /><br/>
			<label for="u_re">RETRY :</label>
			<input type="text" name="u_re" title="RETRY" /><br/>
			<label for="u_name">NAME :</label>
			<input type="text" name="u_name" title="NAME" value="<%=member.getU_name() %>" /><br/>
			<label for="u_nick">NICK :</label>
			<input type="text" name="u_nick" title="NICKNAME" value="<%=member.getU_nick() %>" /><br/>
			<div class="btns">
				<input type="button" value="회원가입" onclick="checkAndMove()" />
				<input type="button" value="취소" onclick="cancelJoin()" />
			</div>
		</div>
		<div class="msgBox" id="msg">
			<%= msg %>
		</div>
	</form>
</body>
</html>
<%-- 세션에 담긴 모든 msg 삭제 --%>
<%@ include file="removeMessage.jspf" %>