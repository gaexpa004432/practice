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
	<li><a href="/practice/member/login.jsp">�α���</a>
</c:if>
<c:if test="${not empty sessionScope.id}">  <%-- ${sessionScope.id != null}�� ���� --%>
	<li>${sessionScope.id}�� <a href="memberLogout">�α׾ƿ�</a>

</c:if>
<a href="">������</a>
<a href="emp/empInsert">������</a>
<a href="emp/empSelectAll">�������</a>
<a href="dept/deptInsert">�μ����</a>
<a href="dept/deptSelectAll">�μ�����</a>

</body>
</html>