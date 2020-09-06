<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=request.getAttribute("errormsg") %>
<form method="post" action="memberLogin" name="frm" id="frm">
	<div>
	<P><label for="id">아이디 (숫자만 가능)</label> : <INPUT type="text" id="id" name="id" size="20" value="" required></P>
	<P><label for="password">비밀번호</label> : <INPUT type="password" name="pw" id="password" size="20"></P>
	
	
	</div>
	<button>로그인</button>
</form>
</body>
</html>