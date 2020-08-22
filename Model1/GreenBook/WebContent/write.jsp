<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<fieldset>
	<legend>글쓰기</legend>
	<!-- 사용자 입력값 -->
	<form action="doWrite.jsp" method="post">
		<input type="text" name="title" placeholder="제목" required/>
		<br/> 
		<input type="text" name="writer" placeholder="작성자" required />
		<br/><br/>
		<textarea name="contents" rows="5" placeholder="내용" required></textarea>
		<br>
		<input type="password" name="pw" placeholder="비밀번호" required/>
		<br/>
		<input type="submit" value="완료" />
		<input type="reset" value="초기화" />
	</form> 
</fieldset>