<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<fieldset>
	<legend>�۾���</legend>
	<!-- ����� �Է°� -->
	<form action="doWrite.jsp" method="post">
		<input type="text" name="title" placeholder="����" required/>
		<br/> 
		<input type="text" name="writer" placeholder="�ۼ���" required />
		<br/><br/>
		<textarea name="contents" rows="5" placeholder="����" required></textarea>
		<br>
		<input type="password" name="pw" placeholder="��й�ȣ" required/>
		<br/>
		<input type="submit" value="�Ϸ�" />
		<input type="reset" value="�ʱ�ȭ" />
	</form> 
</fieldset>