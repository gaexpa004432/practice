<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.id == null}">
	<li><a href="/practice/member/login.jsp">로그인</a>
</c:if>
<c:if test="${not empty sessionScope.id}">  <%-- ${sessionScope.id != null}와 같다 --%>
	<li>${sessionScope.id}님 <a href="memberLogout">로그아웃</a>

</c:if>
<a href="">멤버등록</a>
<a href="emp/empInsert">사원등록</a>
<a href="emp/empSelectAll">사원보기</a>
<a href="dept/deptInsert">부서등록</a>
<a href="dept/deptSelectAll">부서보기</a>

</body>
</html>