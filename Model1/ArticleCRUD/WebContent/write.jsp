<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>write.jsp</title>
</head>
<body>
	<form action="addArticle.jsp" method="post">
		���� : <input type="text" name="title" placeholder="����" />
		<br/>
		�۾��� : <input type="text" name="writer" placeholder="�ۼ���"  />
		<br/>
		��й�ȣ : <input type="password" name="pw" placeholder="��й�ȣ" />
		<br/>
		����
		<br/>
		<textarea name="contents" rows="5" cols="50" ></textarea>
		<br/>
		<input type="submit" />&nbsp;
		<input type="reset" />
	</form>
</body>
</html>