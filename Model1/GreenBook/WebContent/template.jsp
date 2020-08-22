<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	html {
		width : 100%;
		height : 100%;
		margin : 0;
		padding: 0;
	}
	
	body {
		width : 100%;
		height : 100%;
		margin : 0;
		padding: 0;
	}
	
	#mainFrame {
		margin : 0 auto;
		width : 70%;
		height : 80%;
		text-align : center;
		padding : 0;
	}
	
	#logo {
		width : 100%;
		height : 20%;
	}
	
	#center {
		width : 100%;
		height : 80%;
		margin : 0;
		padding : 0;
	}
	
	#menu{
		float : left;
		width : 20%;
		height : 100%;
	}
	
	#contents {
		float : right;
		width : 80%;
		height : 100%;
		text-align : left;
	}
	
	a {
		text-decoration : none;
	}
	
	a:hover {
		font-weight : bold;
	}
	
	li {
		list-style : none;
		line-height : 3em;
	}
	
	input[type=text]{
		width : 20em;
		height : 1.5em;
	}
	
	textarea {
		width : 40em;
	}
	
	#docList {
		border-collapse : collapse;
		width : 80%;
	}
	
	#docListHeader {
		background-color : #cccccc;
	}
	
	#docList td, #docList th {
		border : 1px solid black;
		text-align : center;
	}
	
	caption {
		line-height : 3em;
		font-weight : bold;
	}
	
	#docNum {
		width : 5%;
	}
	
	#docContents {
		width : 50%;
	}
	
	#docWriter {
		width : 25%;
	}
	
	fieldset{
		width : 80%;
		line-height : 2em;
	}
</style>
<%
	String contentsPage = request.getParameter("contentsPage");
	if(contentsPage == null){
		contentsPage = "list";
	}
		contentsPage += ".jsp";
%>

<%
	String msg = "어서오세요";

	Object o = session.getAttribute("msg");
	
	if(o != null){
		msg = o.toString();
	}
	
	session.removeAttribute("msg");
%>
</head>
<body>
	<div id="mainFrame">
		<div id="logo">
			<h1>Blue Book</h1>
			<p><%= msg %></p>
		</div>
		<div id="center">
			<div id="menu"><jsp:include page="menu.jsp" /></div>
			<div id="contents"><jsp:include page="<%=contentsPage %>" /></div>
		</div>
	 
	</div>	
</body>
</html>